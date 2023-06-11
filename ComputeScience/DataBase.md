# 데이터베이스
## 데이터베이스 시스템 주요 기능
### 뷰
- 뷰는 특정 사용자로부터 특정 속성을 숨기는 기능
  - 특정 사용자는 데이터 존재 여부를 인지하지 못함
  - for 데이터 보호, 사용자 편리성 제공
- 뷰 테이블은 터플을 실제 뷰 내부에 저장하지 않음 -> 가상관계(<-> 베이스 관계)
  - 따라서 뷰는 가장 최신 데이터를 갖고 있는 것처럼 보임
  - 뷰에 대한 질의가 들어오면 해당 뷰를 치환화여 베이스 테이블에 대한 질의문이 되도록 함
- 순환뷰
  - 자신 뷰를 이용하여 새로운 뷰를 정의
```sql
Create view myProfessor as
select pID, name, deptName
from professor;
-- 생성된 뷰는 질의문에서 일반 테이블처럼 사용 가능
```
- 뷰 확장
  - view A가 talbe B를 참조하여 생성
  - view B가 view A를 참조하여 생성
  - 뷰 확장 시, view B는 talbe B만 참조해서 변환 가능
- 뷰 변경(입력, 삭제, 갱신)
```sql
Create view myProfessor as
selcect pID, name, deptName
from professor;

-- Insert into myProfessor values('12345', 'Lee', 'CS');
-- 이 insert 문은 professor table에는 ('12345', 'Lee', 'CS', null)로 들어가게 된다.
-- salary 속성에는 null값 지정 주의

-- 특정 변경 연산은 지원할 수 없음
-- ex) 조인한 테이블을 참조해서 만든 뷰, 집계 함수를 사용해서 만든 뷰

-- 변경 가능 뷰(변경 연산이 지원되는 뷰)
-- group by, having, distinct, 집합 연산, 집계함수, order by 등이 뷰 정의에 들어가 있으면
-- 그 뷰는 변경 가능하지 않음

-- with check option
Create view CSProfessor as
select *
from professor where deptName = 'CS';
Insert into CSProfessor values('255', 'Brown', 'EE', 100000);
-- deptName이 'EE'라서 insert해도 professor에는 들어가지면 CSProfessor에서는 확인할 수 없음
-- 따라서 with check option을 통해 deptName이 'EE'일 때만 값을 넣을 수 있게 함
with check option;
-- 마지막에 붙여주면 된다.
```
- 뷰 제약
  - 뷰에 대한 색인(index) 불가
    - 뷰는 터플을 갖지 않아 색인은 의미가 없음
  - 뷰에 대한 키 속성 또는 무결성 제약 정의 불가

### 무결성 제약
- 무결성 제약은 DB 시스템이 항상 만족하여야 하는 조건
- 단일 테이블 제약
  - not null
    - 개별 속성에 적용 가능
  - primary key
    - 한 개 이상 속성에 적용 가능
    - null값을 가질 수 없음
  - unique
    - 한 개 이상 속성에 적용 가능
    - null값을 가질 수 **있음**
  - check(p), where p is a predicate
    - `check(semester in('Spring', 'Summer', 'Fall', 'Winter'))`
    - 위 4개 외의 값으로 변경 시 실행되지 않음
- 참조 무결성 제약
  - 외래 키의 나오는 모든 값은 외래 키가 참조하는 테이블의 주키 값으로 나와야 함
  - 외래 키는 널 값을 가질 수 **있음**
  - 외래 키가 참조하는 주키는 널 값이 나올 수 없음
```sql
foreign key(pID) references professor
-- 구체적 행동 없이 명시 -> 참조 무결성 위반 연산은 허용되지 안흥ㅁ

foreign key(pID) references professor
on delete cascade,
on update cascade
-- 참조 무결 성 위반 시 이를 해결하는 구체적 행동까지 명시
-- 여기서 언급되는 delete, update 연산은 참조되는 테이블(professor)에 대한 삭제 및 갱신을 의미함
-- cascade, set null, set default 지정 가능
```
- ![image](https://github.com/googoo9918/TIL/assets/102513932/e44149ce-bd16-415b-8809-71fd9769e92a)
  - professor 테이블의 pID 100을 삭제할 경우
    - teaches 테이블의 두 개 tuple에서 참조 무결성이 위배됨
      - 이때, cascade인 경우 삭제 연산이 teaches 테이블에 파급되어 두 개 tuple 또한 삭제됨
      - set null인 경우, 두 개 tuple의 pID값을 null으로 변경함
        - 다만, pk값이라면 null로 변경하지 못함(지금 teaches가 그러하다)
    - teaches 테이블의 터플을 삭제할 경우 -> 상관 없음
  - professor 테이블의 pID 100을 400으로 update하는 경우
    - 특정 행동이 명시되지 않는 경우 -> 연산 자체가 수행 안됨
    - cascade인 경우 -> teaches테이블의 두 개 터플 또한 400으로 변경
    - set null인 경우 -> 두 개 터플 null으로 변경, 다만 여기서는 PK이므로 갱신 연산이 허용되지 않음
    - teaches 테이블의 터플을 변경하는 경우 -> 참조 무결성 위반으로 갱신 연산이 아예 수행되지 않음
- **참조 무결성 예제**
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2b773b63-ff56-4258-ab99-37018b22b73c)
    - (1) 수행 시
      - Employee에서 Park 삭제 -> Manages에서도 Park 삭제됨
      - 결과
        - ![image](https://github.com/googoo9918/TIL/assets/102513932/32fb4ea1-8be8-493d-9c5a-bf75f39e92aa)
    - (2) 수행 시
      - 더 이상 Employee 테이블에 "Park"가 남아있지 않기 때문에, Park로 변경하는 것은 불가능함
- 터플 입력 문제
  - 외래 키로 선언된 속성을 입력하려면, 테이블에 대한 외래 키 정보가 존재해야 되는 상황임
    - 따라서 null값을 넣어놓고, 나중에 외래키가 입력되면 추가적인 갱신을 진행
    - 무결성 제약을 연기하여 점검하는 기능 제공
    - `initially deferred`
- 복잡한 무결성 제약
  - `pID char(5) check(pID in (select pID from profeesor))`
    - professor 테이블에 변화가 있어도 무결성 제약을 점검하지 않는 문제 존재
    - check절에 서브질의를 허용하지는 않음
  - assertion도 있는데.. 너무 복잡하고, 대신 trigger를 사용하고 있음
### 트리거
- 트리거는 무결성제약 관리를 위해 지원하는 기능
  - 사건, 조건, 행동으로 구성
  - 사건(event)
    - 터플 인스턴스 변화(입력, 삭제, 갱신)
    - `referencing old row as`
      - for **deletes** and updates
    - `referencing new row as`
      - for **inserts** and updates
```sql
-- 학점 취득 시 학생이 취득한 총학점을 변경하는 트리거
-- 사건 : takes의 grade를 update
-- 조건 : old grade 변경 전에는 F이거나 널 값/ new grade는 f나 널값이 아니어야 함
-- 행동 : student의 totalCredit을 변경(course의 crdit을 추가함으로써)
Create trigger myCred after update of grade on takes
referencing new row as nrow
referencing old row as orow
for each row
when nrow.grade <> 'F'and nrow.grade is not null
  and (orow.grad = 'F' or orow.grade is null)
begin
  Update student
  set totalCredit = totalCredit +
      (select credit
      from course
      where cID = nrow.cID)
  where sId = nrow.sID;
  end;
-- when절은 조건 명시, begin 블록이 트리거의 행동 명시
-- for each row는 조건과 행동을 값이 변경된 각 터플(grade 속성 값이 변경된 터플)을 기준으로 수행하는 것을 의미
-- 즉, takes 테이블의 update된 각 터플에 대해 조건을 검사하고, 조건이 만족하면 트리거를 수행
-- 만약 begin atomic을 사용한다면, 블럭 내 sql 문장이 모두 수행되거나, 아무것도 수행되지 않는다.

-- 예제 2
emplyee(name, eID, salary, dNumber)
department(dname, dno, totalSalary)
create trigger myTotalSalary
after update of salary on employee
referencing new row as nrow
referencing old row as orow
for each row
when(nrow.dNumber is not null)
update department
  set totalSalary = totalSalary + nrow.salary - orow.salary
  where dno = nrow.dNumber;
-- employee table의 salary에 update가 일어난 경우
-- dNumber가 null이 아닌 경우(null이면 소속 부서를 모르므로 department의 totalSalary의 속성 값 갱신 불가능)
-- department 테이블의 totalSarary를 변경

Create trigger mySetNull before update on takes
-- 이처럼 이벤트 전에 트리거를 수행할 수도 있음
-- event를 발생하는 연산이 invalid한 상황에 처해지는 경우, 이를 해결하는 조취를 선제적으로 취할 수 있음
```
- **예제 3**
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b5059f6e-7053-497f-9ffc-250fd753dc30)
    - account 테이블에서는 계좌 번호와 잔고 속성
    - 계좌번호를 가진 고객이름은 depositor 테이블에 저장
    - 은행에서 고객이 대출을 받는 경우
      - loan 테이블은 대출번호와 대출금 속성 저장
      - 대출 고객명은 borrower 테이블에서 나옴
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/8d5537f0-ca79-4c6b-a671-4e101adfdaf2)
    - 잔고보다 더 많은 금액의 수표가 발생되는 경우, 대출 계좌를 사용자 명의로 개설하고 대출금을 overdraft된 금액으로 설정
    - `when nrow.balance<0`
      - 잔고보다 더 많은 금액의 수표 발행
      - 현재 nrow는 account 테이블에 대한 것
    - `Insert into borrower(select cName, aNumber from depositor where nrow.aNumber = depositor.aNumber);`
      - borrower 테이블에, account의 nrow.aNumber와 depositor.aNumber가 같은 cName과 aNumber 삽입
    - `Insert into loan values(nrow.aNumber, -nrow.balance);`
      - loan 테이블에 nrow.aNumber(대출 번호), -norw.balance(대출금, 음수를 붙여 양수로 저장됨)
    - `Update account set balance =0 where account.aNumber = nrow.aNumber`
      - account의 balance(잔고)가 음수인건 말이 안되니, 0으로 설정해줌
- 문장 수준 트리거
  - 터플 단위가 아닌 SQL 문장 단위로 트리거 행동을 수행
  - 사건 전후 테이블을 테이블 단위로 참조
    - 많은 터플에 변화가 있는 경우, 문장 수준 트리거가 유용함
```sql
emplyoee(name, eID, salary, dNumber)
department(dname, dno, totalSalary)
Create trigger myTotalSalaryStateLevel
after update of salary on employee
referencing old table as O
referencing new Talbe as N
for each statement
when exists(select * from N where N.dnumber is not null) or
     exists(select * from O where O.dnumber is not null)
update department as D
set D.totalSalary = D.totalSalary
  +(select sum(N.salary) from N where D.dno = N.dnumber)
  -(select sum(O.salary) from O where D.dno = O.dnumber)
where D.dno in((select Dnumber from N) union (select dnumber from O));
-- when -> 구 테이블이나 신 테이블의 dNumber가 null이 아니면 만족
-- 터플별로 수행하는게 아니라 전체 테이블에 대하여 일괄적으로 totalSalary 속성 값을 변경함 
```

### 권한(Grant, Revoke)
- 사용자는 연산에 필요한 권한을 갖고 있어야 함
  - DBA는 모든 권한을 갖고, 특정 사용자에게 특정 권한을 부여할 수 있음
- 인스턴스에 대한 권한
  - 읽기, 입력, 갱신, 삭제
- 스키마에 대한 권한
  - 색인 생성/삭제, 테이블 생성, 테이블 속성 변경, 테이블 삭제
- SQL 언어 권한
  - select/insert/update/delete
  - references
    - 외래키 선언 권한
  - usage
    - 도메인 사용 권한
- Grant 문장
  - 권한을 부여하는 기능
  - public은 모든 사용자를 의미
  - `with grant option` 사용 시, 권한을 받은 사용자가 부여 받은 권한을 다른 사용자에게 부여할 수 있음
```sql
Grant select on professor to U1,U2,U3 with grant option;
-- professor 테이블에 대한 select 권한을 부여(부여할 수 있는 권한도 부여)

Grant update(balance) on acoount to teller;
-- update(attribute)!!

Grant references (deptName) on department to Lee;
-- Lee에게 department 테이블의 deptName 속성을 참조하는 외래키를 생성하는 권한 부여
-- Lee 사용자는 본인 소유 테이블에서 department(deptName)을 참조할 수 있음
```

- Revoke 문장
  - 부여한 권한을 철회하는 기능
    - 만약 U1, U2가 동일 권한을 U3에게 부여하는 경우, U1이 권한 취소를 해도 U3는 U2에게 받은 권한 때문에 권한을 계속 갖고 있음
  - cascade 옵션
    - `Revoke select on professor from U1,U2,U3 cascade`
      - 만약 U1~3이 다른 사용자에게 select 권한을 줬을 경우, cascade로 인해 그 권한 마저도 다 취소가 됨
    - `Revoke select on professor from U1,U2,U3 restrict;`
      - 다른 권한이 함께 취소되어야 하는 경우에는 권한 취소 연산 자체가 수행되지 않음
      - 만약 취소되면, U1,U2,U3는 아직 권한을 갖고있겠지
  - public
    - 권한 취소를 받는 사용자가 public이면 모든 사용자에게서 권한을 취소
    - 다른 사용자로부터 동일 권한을 이미 받았으면 그 권한까지 취소되진 않음
    - `Revoke grant option for select on professor from U5;`
      - grant option 권한만 취소하는 것도 가능함

### 권한 그래프
- 그래프의 뿌리는 DBA, 노드는 사용자, edge는 권한 부여를 나타냄
  - 그래프의 모든 Edge는 DBA로 Path가 있어야 함
- 예제 1
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b54215d0-f614-414e-9191-a63d7f29a952)
    - 만약 DBA가 U2의 권한 취소 시, U2가 U3, U4에게 부여한 권한이 함께 취소됨
- 예제 2
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0121c38a-3325-468a-bda2-b76f35bcc9f2)
    - DBA -> U7 에지 취소 시, U7->U8 및 U8및 U7 에지도 함께 취소됨
      - 단, cascade로 취소해야겠지?
    - `Revoke select on professor from U7 restricted;`
      - 권한 취소 연산 수행 시 실행 오류 -> 아무것도 취소되지 않음

### 뷰 권한
- 뷰는 베이스 테이블의 조합으로 생성되지만, 기본적으로 베이스 테이블에 대한 권한과 뷰에 대한 권한은 독립적임
  - 사용자에게 뷰에 대한 권한을 부여하더라도 사용자가 베이스 테이블에 대한 권한을 얻는 것은 아님
  - 그러나 뷰를 생성하려면 베이스 테이블에 대한 최소한의 읽기 권한은 필요함
  - 또한, 생성된 뷰에 대해서도 베이스 테이블에 대한 권한을 능가하는 권한을 가질 수는 없음
- 뷰 생성자는 resource권한은 필요하지 않음
  - 일반 테이블을 생성하는 것이 아니기 때문
- 일반 테이블 생성자는 테이블에 대한 모든 권한을 가짐
  - 뷰 생성자는 뷰에 대한 모든 권한을 갖지 못함
  - 베이스 테이블에 대한 select 권한이 있어야 뷰 생성이 가능
    - 뷰 생성자는 뷰에 대한 select권한은 갖게됨
  - 그 이상의 권한은 베이스 테이블에 대한 권한에 의존적
    - 본인이 생성된 뷰에 대해서도 갱신 권한을 갖지 못할 수 있음
      - 뷰를 통한 갱신은 실제로는 베이스 테이블 갱신이기 때문
  - 사용자가 베이스 테이블에 갱신 권한을 갖고 있으면, 뷰에 대한 갱신 권한도 당연히 가짐
- 예제
```sql
create view myTeach as
select name,title
from professor,teaches,course
where teaches.pID=professor.pID and course.cID=teaches.cID
and semester = 'Fall' and year = 2015;
-- 2015 가을학기에 강의하는 과목은 접근 가능하게 하고
-- 교수 봉급은 접근하지 못하게 하기 위함
-- myTeach 뷰를 생성하여 읽기 권한을 부여함
-- 만약 상기 뷰에 대한 query가 들어오면
-- 뷰를 확장하게 될텐데, 뷰 확장은 professor, coures, teaches에 대해 동작함
-- 따라서 뷰에 대한 접근 권한 검사는 뷰가 확장되기 전에 수행되어야 함

user1 > Create view CSProfessor as 
        (select * from professor where deptName = 'CS');
user1 > Grant select on CSProfessor to staff;
staff > Select * from CSProfessor;
-- staff는 professor에 대한 읽기 권한이 없지만, 뷰에 대한 읽기 권한을 갖고
-- CSProfessor 뷰를 접근할 수 있음 (그 결과 professor 테이블에 접근을 하긴 함)
-- 뷰 생성자 user1이 professor 테이블에 대해 읽기(selecet) 권한만 갖고 있으면 뷰는 생성할 수 있음
```
### 롤
- 롤은 사용자의 집합
  - 사용자 다수에게 동일한 권한을 부여하는 경우 -> 다수 사용자를 동일한 롤로 정의 후 롤에 권한 부여
  - 롤을 통해 사용자를 계층적으로 관리 가능함
```sql
Create role teller;
Create role manager;
Grant select on branch to teller;
Grant update(balance) on account to teller;
Grant all privileges on account to manager;

Grant teller to manager;
-- teller가 가진 모든 권한을 manager에게 부여
```

### 순환 질의
- `prereq(x,y)`
  - x를 수강하기 위해 미리 수강하여야 하는 선수 과목이 y임을 의미함
- prereq 테이블을 활용하여 선수과목을 모두 구할 수 있는 recPrereq 순환 뷰를 정의할 수 있음
- 계산 예제
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f37a0805-093e-4fa3-a0ed-98250c105280)
  - 더 이상 새로운 선수과목이 생성되지 않으면 반복 연산을 종료
  - 종료시점에는 모든 값을 구할 수 있음
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/26fe00de-bb2a-414b-8148-205abe276afb)
    - 가볍게 참조할 것
## 오라클 실습2
### 오라클 SQL
- LOB 타입은 대용량 데이터를 저장/관리하기 위해 사용
  - BLOB, CLOB, NCLOB, BFILE
  - BFILE 제외 나머지 ACID 지원
  - BFILE은 read only
- 임시 데이터를 저장할 수 있는 dual 테이블
  - 속성과 터플이 하나
  - sys 소유
- 문자 처리 함수
  - Lower("ABC") -> abc
  - Upper("abc") -> ABC
  - Initcap("abcd") -> Abcd
  - instr('MILLER', 'L', 1,2)
    - MILLER에서 1번째 위치부터 검색하여 2번째 나오는 L 위치 반환 -> 4\
    - 찾고자 하는 문자가 없으면 0 반환
  - replace('jack and jue', 'j', 'bl') -> black and blue
  - length('abc') -> 3
  - concat('a', 'b') -> ab
  - substr('abcde', 2, 3) -> 1번째 위치에서 두글자 반환 -> bcd
- 숫자형 처리 함수
  - round(456.789, 2)
    - 소수 셋째자리에서 반올림(2자리가 되도록..) -> 456.79
  - round(456.789, -1)
    - 정수부 첫자리 반올림 -> 460 
  - round(456.789, 0)
    - 457
  - trunc()는 round와 자릿수 따지는건 똑같음, 다만 절삭
    - trunc(456.789, 2) -> 456.78
  - sign()
    - 양수(1), 음수(-1), 0(0) 판단
- 날짜 처리 함수
  - sysdate
    - 현재 날짜 반환, YY/MM/DD
    - 가감 연산 및 round()가능
      - round는 16일을 기준으로 반올림 -> 다음달 1일 혹은 해당 달 1일
  - months_between()
    - 날짜 사이의 달 수 기준으로 반환
- 형 변환 함수
  - to_char()
    - 날짜나 숫자를 문자형으로
  - to_number
    - 숫자형으로 데이터 변환
```sql
select sysdate, to_char(sysdate, 'YYYY-MM-DD'), to_char(sysdat, 'YY-MM-DD DAY') from dual
-- 19/04/29    2919-04-29    19-04-29 월요일

select to_char(123456), to_char(123456, '0000000000'), to_char(123456, '999,999,999') from dual
-- 123456 000123456 123,456
-- 0과 9로 형식 표현
-- 0은 자릿수가 맞지 않으면 채움, 9는 자릿수가 맞지 않아도 채우지 않음

select to_number('20000', '99999') - to_number('10000', '99999') from dual
-- 10000 
```

### ROWNUM
- ROWNUM 숫자 1부터 시작하는 pseudo-column
- 테이블에 실제 존재하거나 저장 및 관리되지 않음
- where절을 통과한 터플에 대해 부여됨
- ROWNUM이 특정 터플에 배정된 후 +1

```sql
Select * from student where rownum <= 5;
-- db가 접근하는 순서대로 5개 터플 출력

Select * from student where rownum <= 5
order by gpa desc;
-- 5개 출력해서 gpa로 내림차순 정렬

Select *
from (select * from student order by gpa desc)
where rownum <= 5; 
-- 학점 높은 학생 5명 출력

Select * from student
where rownum > 1; 
-- 공집합, ROWNUM은 where절을 지나고 부여되는데
-- where절을 지난 터플이 없으니 다 0임
```

### 트리거
```sql

```