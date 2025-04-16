## CniApp(방문 시스템 관리) SpringFramework Converting 중 생긴 이슈
### 1. 기능적 이슈
- 방문 신청에서 락커룸을 받아오든, 관리자 신청 화면에서 락커룸을 받아오든 현재 '방문 중'인 로직을 제외하고 모든 락커룸을 가져온다.
    - 그런데, 이미 방문 신청을 특정 락커룸의 번호로 진행한 상황임에도 불구하고 다른 사람이 방문 신청을 할 때, 동일 번호의 락커룸으로 신청을 진행할 수 있다.
    - 이 경우에는, 관리자가 관리자 화면에서 락커룸 번호를 별도로 확인 하고, 방문 신청을 방문 승인 등으로 처리할 때 신청한 락커룸의 번호를 수정하고 승인을 진행해야 한다.
    - 이 얼마나 비효율적일 수 있는가??
        - 신청 당시에 방문 신청한 케이스를 확인할 수 있다면, 미리 중복된 락커룸으로 신청하지 않고, 관리자가 별도의 작업을 처리하지 않아도 괜찮을 것이다.
        - 심지어, 관리자 화면에서 락커룸을 조회하는 버튼(화면)자체가 필요 없을 가능성이 크다.
            - 물론 현재는 날짜로만 조건을 걸고 있기 때문에, 락커룸 시간 등으로 해당 날짜에 락커룸이 부족해질 수 있는 상황을 대비하여
                - 신청 당시에 락커룸의 유효성을 엄격하게 진행할 수 있어야 할 것이다.
                    - 이건 또 이거 나름대로 문제가 있을지도...
                    - 시간을 정하고 락커룸을 정할 수 있게 하면 괜찮지 않나??
                        - 시간에 따라 락커룸을 동적으로 조회할 수 있게 설정해야 할 듯.
        - 만일 관리자 화면에서 조회 버튼이 필요하다 하더라도, 방문 중, 방문 신청 등으로 이름 옆에 괄호로 보여줄 수 있을 것이다.
    - 물론 내가 운영하고 있는 시스템이 아니고, 데이터센터의 상황을 정확히 알 수 없기 때문에
        - 일단 기존 시스템 로직을 그대로 따라가기로 하였다.(상사 지시)
        - 추후 내가 운영을 맡게 된다면, 최대한 사용자의 편의를 들어줄 수 있는 시스템을 구축해 보고 싶다.

### 2. 성능적 이슈
- 기존 SQL은 다음과 같다.
```sql
SELECT CODE_NM
       ,(SELECT     B.VST_NM 
           FROM     VST_HST A, VST_MST B
          WHERE     A.VST_ID = B.VST_ID
            AND     ROOM_ID = CODE_NM
            AND     CODE_GROUP_ID = '0004' 
            AND     VST_STA_YMD = TO_CHAR(SYSDATE,'YYYYMMDD')
            AND     STATE = 'I'
            ) VST_NM
FROM VST_SYS_CODE 
WHERE CODE_GROUP_ID = '0004'
-- 사실 이것도 가독성이 좋지 않아서, 다음과 같은 SQL로 수정하였다.

SELECT C.CODE_NM
       ,(SELECT     A.VST_NM 
           FROM     VST_MST A, VST_HST B
          WHERE     B.VST_ID = A.VST_ID
            AND     B.ROOM_ID = C.CODE_NM
            AND     B.VST_STA_YMD = TO_CHAR(SYSDATE,'YYYYMMDD')
            AND     B.STATE = 'I'
            ) VST_NM
FROM VST_SYS_CODE C
WHERE C.CODE_GROUP_ID = '0004';
``` 
- 서브쿼리 기반인 것이 마음에 들지 않아, 다음과 같이 JOIN 기반으로 수정해보았다.

```sql
SELECT 
    C.CODE_NM,
    V.VST_NM
FROM 
    VST_SYS_CODE C
LEFT JOIN (
    SELECT 
        B.ROOM_ID, A.VST_NM
    FROM 
        VST_HST B
    INNER JOIN 
        VST_MST A ON A.VST_ID = B.VST_ID
    WHERE 
        B.STATE = 'I'
        AND B.VST_STA_YMD = TO_CHAR(SYSDATE, 'YYYYMMDD')
) V ON V.ROOM_ID = C.CODE_NM
WHERE 
    C.CODE_GROUP_ID = '0004'
```

- 성능 비교
    - ![Image](https://github.com/user-attachments/assets/a28f9e1b-da48-4927-b75c-c575fb014aab)
        - `NESTED LOOP`
            - 행별 반복 진행
        - COST:728
    - ![Image](https://github.com/user-attachments/assets/ffbe36b4-9e99-4ef8-8446-a4299d4a177d)
        - `HASH JOIN OUTER`
            - 한 번에 매핑
        - COST:76
        - 뷰 먼저 만들고 조인
    - 물론 COST기반 비교이기 때문에, 정확한 성능 향상률 산출은 아니지만
        - 이론적으로는 89.56% 상승

> COST: Oracle 옵티마이저가 판단한 상대적 리소스 소비량

- 문제점
    - 그런데 첫 쿼리에서는 NESTED LOOP였기 때문에 컬럼 기준으로 정렬된 형태로 데이터를 반환하였지만
    - 두 번째 쿼리에서는 HASH JOIN OUTER로 진행되었기 때문에 값이 중구난방으로 튀어나왔음.
        - 그런데 ORDER BY로 해결하고자 하였으나 CODE_NM이 VARCHAR이었기 때문에
        - `ORDER BY TO_NUMBER(C.CODE_NM);`
        - 위와 같이 **TO_NUMBER**을 사용하여 해결하였다.