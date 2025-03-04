## 마이바티스 매핑 에러
```sql
<select id = "selectIssueList" parameterType="Map" resultType="Map">
        SELECT sc1.code_nm
             , ir.req_no
             , sc2.code_nm as req_stat
             , ir.title
             , sc3.code_nm as req_type
             , DATE_FORMAT(ir.reg_date, '%Y-%m-%d %H:%i:%s') as reg_date
             , iu.user_nm as reg_user
        FROM itsm_request ir
                 LEFT JOIN sys_code sc1 ON ir.client_id = sc1.code_id AND sc1.grp_id = 'G0000'
                 LEFT JOIN sys_code sc2 ON ir.req_stat = sc2.code_id AND sc2.grp_id = 'G0008'
                 LEFT JOIN sys_code sc3 ON ir.req_type = sc3.code_id AND sc3.grp_id = 'G0021'
                 LEFT JOIN inc_user iu ON ir.req_id = iu.user_id
        WHERE 1=1
           <if test="client_id != null and client_id != ''">
               AND ir.client_id = #{client_id}
           </if>
           <if test="reg_user != null and reg_user != ''">
               AND iu.user_nm = #{reg_user}
           </if>
           <if test="isMine != null and isMine == 'Y'">
               AND ir.req_id = #{user_id}
           </if>
           <if test="keyword != null and keyword != ''">
               AND ir.title LIKE CONCAT('%', #{keyword}, '%')
           </if>
    </select>
```
- 위와 같은 SQL 쿼리에서 에러가 발생
    - 에러 로그를 확인해 보았으나, 큰 이상을 찾지 못했음
    - 다만, 쿼리에서 문제가 발생한 것은 확실
```sql

           <if test="isMine != null and isMine == 'Y'">
               AND ir.req_id = #{user_id}
           </if>
```
- 특히 위 쿼리에서 문제 발생
  
## 시도 방법
- 일단 user_id가 제대로 들어가는지 확인하기 위해 Controller에서 print를 찍어봤음
    - log를 조금 더 잘 봤으면 안 해도 되는 방법(이미 log에 파라미터는 다 들어가 있었다)
- DBeaver에서 같은 SQL문으로 쿼리 실행시켜봄
    - 잘 동작했다!!
    - 그렇다면 쿼리의 문제는 아니란 소리고.
        - 파라미터의 문제도 아니란 소리니
        - ???!
        - 여기서부터 모르겠어서 준호프로님께 요청을 청했음
- 준호 프로님은 디버깅을 먼저 찍어보셨음
    - 큰 의미 없었음
- **catch 부문에, `e.printStackTrac()` 찍어보심**
    - 자세한 로그를 통해, isMine의 `NumberFormatException`임을 확인할 수 있었다.
    - 그렇다면 왜 MyBatis는 Y를 숫자로 변환하려고 하였을까??
        - 전체 검색으로 isMine을 찾아봤을 때, isMine이 Boolean형으로 선언된 것을 확인할 수 있었음
```java
Boolean isMine = true;
if(isMine)
map.put("isMine", "Y")
```
- 위와 같은 식으로 boolean으로 선언하고 map에는 String을 넣어버리니
    - myBatis에서 Boolean 값으로 인식하고 숫자로 변환을 시도하면서 예외가 발생함!!

## 해결책
- 임시방편
```sql
           <if test='isMine != null and isMine == "Y"'>
```
- 이게 임시방편인 해결책이고, Boolean 값으로 제대로 지정해줘야 한다고 생각했는데
- 사실 잘 살펴보면, ParameterType이 Map이기 때문에, 기존에 Boolean으로 선언했다고 하여, Mapper가 여기까지 영향을 미칠 확률은 없다고 봐도 무방하다.
- 따라서 '`Y'`는 Character 타입으로 인식하여, `NumberFormatException`이 발생했던 것임
    - 실제로 String으로 인식하게 하기 위해서, "Y"를 사용해 주는 것이 맞다.
    - 아니면 `isMine != ''`로 해줘도.. 큰 문제는 없을 것이나....
