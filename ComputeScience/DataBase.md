## 목차
- [데이터베이스](#데이터베이스)
  - [데이터베이스 시스템 주요 기능](#데이터베이스-시스템-주요-기능)
    - [뷰](#뷰)
    - [무결성 제약](#무결성-제약)
    - [트리거](#트리거)
    - [권한(Grant, Revoke)](#권한grant-revoke)
    - [권한 그래프](#권한-그래프)
    - [뷰 권한](#뷰-권한)
    - [롤](#롤)
    - [순환 질의](#순환-질의)
  - [오라클 실습2](#오라클-실습2)
    - [오라클 SQL](#오라클-sql)
    - [ROWNUM](#rownum)
    - [동의어](#동의어)
  - [응용 개발](#응용-개발)
    - [내장(embedded) SQL](#내장embedded-sql)
    - [커서(cursor)](#커서cursor)
    - [Dynamic SQL](#dynamic-sql)
    - [ODBC, JDBC](#odbc-jdbc)
    - [응용 구조](#응용-구조)
  - [SQL 확장](#sql-확장)
    - [SQL 절차적 확장](#sql-절차적-확장)
    - [SQL::1999](#sql1999)
    - [PL/SQL](#plsql)
  - [개체-관계성 데이터 모델](#개체-관계성-데이터-모델)
    - [개체 및 관계성](#개체-및-관계성)
    - [ER 다이어그램](#er-다이어그램)
    - [관계형 스키마로 변환](#관계형-스키마로-변환)
    - [설계 이슈](#설계-이슈)
  - [데이터베이스 설계 이론](#데이터베이스-설계-이론)
    - [좋은 스키마와 나쁜 스키마](#좋은-스키마와-나쁜-스키마)
    - [설계 목표](#설계-목표)
    - [함수 종속성 이론](#함수-종속성-이론)
    - [암스트롱 공리](#암스트롱-공리)
    - [속성 폐포](#속성-폐포)
    - [정규 커버(최소 커버)(Canonical Cover)](#정규-커버최소-커버canonical-cover)
    - [정규형](#정규형)
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

-- with force, read only
create or replace force view view_name
with read only
-- create or replace 형식을 사용하면 기존에 존재하는 view_name 정의를 수정하기 수월함
-- force 옵션을 사용하면, 베이스 테이블 존재 여부와 상관없이 뷰 생성 가능(defaultnoforce)
-- with read only 옵션 사용 시, 뷰에 대해 검색만 가능하고 뷰를 통한 베이스 테이블 변경은 불가능함
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
- **데이터베이스에 대한 변경 연산이 발생할 때마다 DBMS에 의해 자동적으로 호출, 수행되는 명령 문장들을 트리거라고 함**
  - **ECA(Event, Condition, Action)룰을 가짐.**
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

Grant create session to user1 with admin option;
-- 시스템 권한 부여 시 사용, with admin option은 부여 권한도 같이 부여한다.
-- 이 예제 말고 다른 예제는 객체 권한 부여시에 사용함
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
  - substr('abcde', 2, 3) -> 2번째 위치에서 세글자 반환 -> bcd
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

### 동의어
```sql
-- user 1
Grant select on student to user2;
-- user 2
Select * from user1.student;
-- sys
Grant create synonym to user2;
-- user 2
Create synonym myStudent for user1.student;
-- user 2
Select * from myStudent;
-- 스키마 명시 없이 user1 소유 테이블 접근 가능
```
## 응용 개발
### 내장(embedded) SQL
- direct SQL 방식
  - 사용자가 화면상에서 SQL 문장 입력 시 결과를 화면에 보이게함
- Embedded SQL 방식
  - 호스트 언어 중간 중간에 SQL을 직접 삽입하는 형식
  - 전처리 과정을 꼭 거쳐야함

### 커서(cursor)
- sql문의 결과를 일반 pl 변수가 직접 받을 수 없음
- 불일치 해소를 위해 cursor기능 제공
- **Embedded SQL에서 호스트 언어와 SQL 간의 불일치, 즉 SQL의 레코드 집합 단위 처리와 호스트 언어의 레코드 단위 차이 사이에 교량적 역할을 하는 것을 cursor이라 한다**

### Dynamic SQL
- 동적 SQL은 프로그램 run time에 SQL 문장이 생성되는 SQL임
- 정적 SQL은 DBMS가 전처리 단계 혹은 컴파일 단계에서 작업이 가능하나
  - 동적 SQL은 이러한 작업이 불가능함

### ODBC, JDBC
- 동적 방식의 ODBC, JDBC는 응용 프로그램에서 데이터베이스 시스템에 연결하여 데이터베이스 연산을 요청하고, 이에 대한 결과를 받는 방식을 제공하는 API임
- ODBC는 기본적으로 동적 SQL 접근 방식을 제공함
- SQL injection이란 데이터 침입 및 해킹을 목적으로 SQL 코드를 악의적으로 삽입하는 기술임
  - prepare 기능을 사용하여 미리 컴파일 작업을 하는 등으로 방지할 수 있음
- ODBC는 메타 데이터에 대한 접근 기능을 제공
  - DB에 저장된 관계 및 속성에 대한 질의 가능
- ODBC 트랜잭션
  - 트랜잭션의 기본 값은 각 SQL 문장을 트랜잭션으로 취급
  - 각 SQL 문장 수행이 완료되면 트랜잭션이 commiit 된 것으로 처리함(AutoCommit)
  - off된 상태에서는 여러 개의 SQL 문장으로 트랜잭션 구성
    - SQLTransact()로 트랜잭션 종료해야함
- JDBC
  - 자바 언어에서 DB 서버를 연결하게 하는 API
  - SQLJ
    - 자바 언어에서 내장 SQL 기능 제공
    - SQLJ 프로그램 컴파일 이전에 반드시 전처리 과정을 거쳐야 함
- ADO.NET
  - 관계형 DB, 비관계형 시스템에 대한 접근 가능
- Static VS Dynamic
  - Embedded SQL, SQLJ는 static
  - 전처리 과정이 필요함
    - 전처리 과정에서 구문검사, 권한 검사, 실행 계획 수립 등이 가능하며 그 결과로 DB 연산이 신속하게 수행할 수 있는 장점 존재
    - 실행 코드 작성 시 두 단계를 거쳐야 하는 번거로움

### 응용 구조
- Two-tier vs Three-tier
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/5ab272ed-8ef7-466a-b840-173bc66b4483)
  - two-tier구조
    - 응용 프로그램이 클라이언트에 존재
    - 서버는 DB만 관리
    - 클라이언트는 ODBC/JDBC등을 이용하여 DB에 접근
    - 응용프로그램 변경 시 바꿀게 많음
  - three-tier 구조
    - 응용 프로그램이 클라이언트와 서버에 분배되어 존재
    - 비즈니스 로직 변경 시 서버에 존재하는 응용 프로그램만 수정 및 보완
    - 대규모 응용 환경이나 웹 환경에서 적합
- Three-layer Web vs Two-Layer Web
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/9b5e5739-778b-4c18-a277-f392506e92f5)
  - 3층 웹 구조
    - 웹 <-> 응용 서버 <-> DB 서버
      - 오버헤드 다소 존재
  - 2층 웹 구조
    - DB 서버 독립적 운용, 웹 서버와 응용 서버가 결합된 서버 형태로 운영
- 쿠키
  - HTTP 프로토콜은 통신 연결이 유지되지 않는 connection-less 통신을 함
  - DB 환경에서 클라이언트가 서버와 연결을 하는 경우, 서버와의 연결이 일정시간 동안 유지되기를 원함
    - 클라이언트가 SQL문장에 대핸 결과를 손쉽게 받을 수도 있고, 사용자의 응답에 따라 다른 SQL 문장을 서버로 손쉽게 보낼 수 있기 때문
    - 이러한 DB 요구사항을 해결하는 것이 쿠키

## SQL 확장
### SQL 절차적 확장
- SQL/PSM
  - DB의 임의 연산을 수행하는 함수를 개발할 수 있음
  - 외부 프로그래밍 언어를 이용하여 함수와 프로시저 개발
```sql
Create function deptCountProc(deptName varchar(20))
reutrns integer
language C
external name 'usr/shlee/bin/deptCount`;
```
  - 장단점
    - 외부언어를 사용하는 함수는 효율적으로 실행되어 시스템 전체적으로 성능향상은 기대할 수 있지만
    - 사용자 프로그램에 오류가 있는 경우, DB 시스템의 오류가 될 수 있음(시스템 보안 문제)
  - 보안 이슈 해결
    - 자바나 C#같은 안전한 언어로 외부 함수(프로시저)를 작성
      - 코드를 DB query 실행 프로세스 자체 내에 있는 샌드박스에서 실행
      - 샌드박스는 자바나 C#코드가 자신이 갖고 있는 메모리 영역으로 접근하는 것은 허용하지만
      - 질의 실행 프로세스의 메모리를 읽거나 갱신하는 등, 파일 시세틈에 접근하는 것은 허용하지 않음
- 저장 프로시저
  - 데이터베이스 시스템 서버에서 데이터 베이스 객체(테이블, 뷰, sequence 등)로 관리됨
  - 장점
    - SQL 문장 묶음이 서버에서 실행 -> 네트워크 부하 감소
    - 미권한 데이터 접근 가능

### SQL::1999
- SQL 함수 예제
```sql
Create function profC(deptName varchar(20)) returns integer
begin
    Declare pCount integer;
    Select count(*) into pCount
    form professor
    where professor.deptName = profC.deptName;
    Return pCount;
end;

Select deptName, budget
from department 
where profC(deptName) > 5;
```
- 테이블 함수 예제
```sql
Create function myProf(deptName varchar(20))
  returns table(pID char(5),
                name varchar(20),
                deptName varchar(20),
                salary numeric(10,2))
  return table(select pID, name, deptName, salary
                from professor
                where professor.deptName =
                myProf.deptName);

Select * from table(myProf('CS'));
-- returns는 함수 선언 헤드에 나오는 키워드
-- return은 함수 몸체에 나오는 키워드
-- 테이블 함수 호출 시 테이블 키워드를 사용하고 있음을 주목하라
```

- SQL 프로시저
  - SQL 함수와는 다르게 인자에 대해 입력 및 출력을 명시함
  - 출력 인자가 2개 이상인 경우에는 SQL 프로시저를 사용해야 함
  - 인자 개수와 인자 타입으로 오버라이딩 가능함
```sql
Create procedure profC2(in deptName varchar(20), out pCount integer)
begin
  Select count(*) into pCount from professor
  where professor.deptName = profC2.deptName;
end

Declare myProcCount integerm
Call profC2('CS', myProcCount);
```

- 절차 생성자
  - PSM(persistent storage module)은 SQL의 절차적 요소 확장에 관한 표준을 정의함
- for 루프
```sql
Declare n integer default 0;
For r as
        select totalCredit from student
        where deptName = 'CS'
do
        set n = n + r.totalCredit
end for;
```

- 조건식
```sql
If Boolean expression
  then statement
elseif Boolean expression
  then statement
else statement
end if
```
- 함수 연습
  - 정원 내에서 학생을 과목에 수강 신청하는 연산을 수행하는 함수
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/dabb6c52-aca2-418a-9b21-a473ae6f27e6)

### PL/SQL
- Procedure Language extension to SQL
  - 블룩 구조로 사용됨
  - 각 문장은 ;로 끝남
  - 프로그램의 마짐가은 END로 끝남
- 선언부
  - `height integer := 2;`
  - `width integer;`
  - `sName student.name%type;`
    - name과 같은 타입 사용
- 조건 로직
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ae4dadd9-50dc-40f1-b377-428ce67eb085)
- 간단한 루프
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/d21292c5-a435-49b5-9dfc-887cdd530bf6)
- WHILE 루프
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ff7ab3d5-dc8c-4a75-9aaf-aa1e9d0a919f)
- for 루프
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1bc3e9d0-eb6b-4aa3-855d-00f8533a2922)
- 예외 예제
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/248c421c-71a6-40a3-88e9-a4f2dbe6e565)
  - 0으로 나눔, studnet.sID에 중복값 나옴, 다른 예외 시
- 커서 예제
  - select 문장이 하나 이상의 터플을 반환하는 경우
  - Select문장이 반환하는 속성을 저장하는 변수 선언 -> 커서를 선언 -> 커서를 오픈 -> 커서로부터 터플을 fetch -> 커서를 닫음
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/bc40855c-8846-40c9-a4e0-b6b495fa1495)
  - for 루프와 커서를 함께 사용 시
    - 커서 오픈 및 닫기 연산을 명시적으로 할 필요가 없음, for 루프에서 fetch 연산을 자동적으로 해줌
- Package
  - 서로 관련이 되는 함수 및 프로시저를 묶어 Package를 생성할 수 있음

## 개체-관계성 데이터 모델
### 개체 및 관계성
- ER 데이터 모델링
  - 데이터베이스를 개체와 관계로 모델링함
  - 원소: 개체, 관계
    - 개체와 관계는 속성만을 가짐
  - 객체 관계형 데이터 모델은 객체만 존재함
    - 객체는 속성, 관계성, 메서드를 가짐
- 개체(entity)
  - 개체는 구별이 가능한 객체를 의미함
  - 개체는 속성(특성)을 가짐
  - 개체 집합 예시
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/afcdcb37-c10b-4edf-8df3-0a6ca87d1088)
- 관계성
  - 개체집합 내 개체는 다른 개체집합의 개체와 연관성이 있을 수 있으며, 의미는 무수함
  - 관계성 집합 예시(takes)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/c1416811-d936-48ce-86cf-00aab81ced16)
    - '과목 조교를 하다'등 다른 의미를 갖는 관계성 집합이 존재할 수 있음
  - 관계성 집합은 개체 집합과 마찬가지로 속성을 가질 수 있음
    - ex) takes 관계성 집합이 '수강 학기', '연도'를 속성으로 가질 수 있음
  - 관계성 집합 차수
    - 관련되는 개체의 개수를 의미함
    - binary가 가장 흔하며, 3진(tenary), 4진(quaternary), 5진(quinary)등의 차수도 가능함
- 속성
  - 개체 또는 관계성이 갖는 특성
  - 단순 속성과 복합 속성으로 구분 가능
  - 단일 값 속성과 다수 값 속성으로도 구분 가능
  - Derived attributes(유도된 속성)
    - 다른 속성을 이용하여 속성 값을 구할 수 있는 속성
    - 생년월일 속성 -> 나이 속성을 구할 수 있음
      - 나이는 Derived attributes임
  - 복합 속성 예제
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/39ad8553-b048-4fbc-8ea4-710ddf99814d)
      - 이름은 이름, 중간이름, 성으로 구성
      - 주소는 거리, 도시, 도, 우편번호로 구성
        - 거리는 거리번호, 거리명, 아파트번호로 구성
      - 복합속성은 이름, 주소, 거리
  - 카디날리티 제약(Cardinality Constraints)
    - 일대일, 일대다, 다대일, 다대다
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/e1890d84-785d-4a03-a7bc-d9f19bb6f0d8)
- 키
  - 개체에 대한 슈퍼 키, 후보 키, 주 키에 대한 개념은 테이블에 대한 개념과 동일함

### ER 다이어그램
- **E-R(Entity-Relationship) 데이터 모델에서는 실세계의 데이터를 '개체(Entity)'와 '관계(Relationship)'의 관점으로 표현**
- ER 다이어그램은 ER 모델의 결과물임
  - 사각형은 개체 집합, 마름모형은 관계성 집합, 밑줄은 주 키 속성을 의미, 속성은 사각형 안에 나열
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0014e0ae-01ce-4af3-af0c-f01d2be1f378)
- 복합 속성을 가진 개체
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2a61f7d5-1244-4248-9b6e-8919caa0eb65)
  - name, address, street은 복합 속성
  - phoneNumber는 하나 이상의 값을 가진 다수값 속성
  - age는 dateOfBirth로 부터 유도된 속성임
- 속성을 가진 관계성 집합
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/3292a022-cd89-40e7-812f-15736d9c798d)
    - date 속성을 가진 관계성 집합 takes
- 카디날리티 제약
  - 화살표가 일(one)을 의미, 화살표가 없으면 다(many)를 의미함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/8ebcaff7-d75d-43a2-aec7-1cd6b63871a5)
    - 교수가 다, 학생이1인 erd 예시
- 참여 제약
  - 단일선은 부분 참여, 이중선은 전체 참여를 의미함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/cd9721a8-1e57-4598-ab71-851a95b1d5ff)
    - 학생 1명은 여러개의 수업 수강 가능, 1개의 수업의 여러 학생 수강 가능 -> 다대다 카디날리티 제약
    - student 개체는 부분 참여
      - 학생 중에는 수업을 수강하지 않는 학생도 있음
    - course는 전체 참여
      - 모든 과목은 학생이 수강하고 있음(학생이 수강하지 않는 과목은 없음)
- **카디날리티 제약 예시**
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/4a8902c9-a940-48e6-ba86-ef9117a408c1)
- 3진 관계성 ER 다이어그램
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/419d287a-51ff-4950-bee3-c59132c3a114)
  - 3진 관계성 집합 카디날리티 제약
    - 일에 대응이 되는 개체 집합을 최대 하나만 허용함
- 롤
  - 개체 집합이 관계성 집합에 참여할 때, 반드시 한 번만 참여하지 않아도 됨
  - 동일 개체 집합이 두 번 이상 관계성 집합에 참여 하는 경우 참여에 대한 의미를 구분하기 위해 롤 표시를 해야함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/63d7e93f-be07-4d34-8263-af47cce295c7)
    - 한 번은 과목으로, 다른 한 번은 선수 과목으로 참여
    - 과목 참여는 일에 대응, 선수 과목 참여는 다에 대응
      - 한 과목에 여러 개의 선수 과목이 존재
    - 모든 참여가 부분 참여
      - 모든 과목에 선수 과목이 있지 않음, 모든 과목이 선수 과목으로 참여하지 않음
  - 예제
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/b6fab680-dd86-4b5c-a536-9c4cf3707a56)
      - people 개체는 spouse 관계성에 1:1 제약으로 부분 참여
      - people 중 배우자가 없는 사람도 존재
      - 배우자가 있다면, 배우자는 한 명만 존재
  - **다대다 롤 변환 예제**
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/a08885ee-7aaa-40d8-aed5-94bbb63afeae)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/5c95c860-3359-48d8-8c92-7fd13c75a5d6)
      - `ship(sID, name, plant, weight)`
      - `sister-of(ship-id, sister-id)`
  - **다대일 롤 변환 예제**
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/decd5303-170f-49da-a418-9e8b09a276a0)
    - `people(ID(pk), name, address, phone)`
    - `fatherhood(father-id,children-id(pk))`
- 약한 개체 집합
  - **E-R 다이어그램에서 약한 개체 집합(weak entity set)이 포함되면, 약한 개체 집합과 연결된 관계 집합(relationship set)의 차수(mapping cardinality)는 항상 '다대일(many-to-one)'이고, 참여(participation)는 항상 '전체(total)'이다.**
  - 여태 본 개체는 자체적으로 주 키를 갖고 있었음
  - 주 키가 없는 개체 집합을 약한 개체 집합이라고 칭함
  - 약한 개체 존재는 이를 구분하는 강한 개체(identifying entity)에 의존적
    - 약한 개체는 강한 개체 존재 없이 존재할 수 없음
  - 관계성(identifying relationship) 구성
    - 약한 개체는 반드시 전체 참여
    - 구분하는 강한 개체가 일에 대응, 약한 개체가 다에 대응
  - 약한 개체 집합 내에서는 부분 키가 존재할 수 있음
    - 약한 개체 집합의 주 키는 구분하는 개체의 주 키와 약한 개체 집합의 부분 키의 결합임
  - 예제
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/2964932f-2127-4048-96d9-a8dcf4f959e8)
      - 과목(course)에는 과목명, 학점등의 속성을 지님
      - 학생들이 수강하고 교수들이 강의하는 것은 분반(section) 개체임
      - 분반의 분반 번호, 연도, 학기는 과목 하나 안에서는 유일하나 분반 전체에서는 유일하지 않음
        - 이 경우, 분반은 주 키를 갖지 못하는 약한 개체임
      - 분반 개체는 과목 개체 존재에 의존적, 과목 개체에 연관이 없는 분반 개체는 존재할 수 없음
        - 따라서 분반은 전체참여 제약
      - 과목 개체 하나에 분반 개체 다수 존재 -> 1:N 카디날리티 제약
      - 만약 과목 cID를 분반의 속성으로 추가하게 되면, 분반 개체도 강한 개체가 되며 과목과 분반 간의 관계성은 불필요하게 됨
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/3861cd18-585a-4d25-882e-c1319ca353bd)
      - ERD 조건 맞춰서 그릴 수 있지?

### 관계형 스키마로 변환
- 개체 집합과 관계성 집합은 각각 테이블로 속성과 함께 변환됨
- 약한 개체 변환 예제
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0476b6bd-d2d3-49f4-99f9-d78af04b69e2)
  - 약한 개체를 변환할 때 강한 개체의 주 키를 포함함 주의
  - 약한 개체를 구분하는 관계성은 테이블로 변환되지 않음
- 다대다 관계성 집합
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/97275e10-1b8e-4312-be5b-c76769cf644d)
  - 다대다 관계성은 반드시 독립적인 테이블로 변환됨
  - 속성은 관여하는 개체 집합의 주 키를 포함함
- 다대일 관계성 집합
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/d38d826c-a017-40a5-82a2-a1038dd4f79e)
    - 독립적인 테이블로 변환하는 경우
      - own 테이블의 주 키는 다(many)측의 주 키만 포함하면 됨
        - car 개체의 주 키만으로 구분할수 있기 때문
    - 다(many)측 개체로 병합되어 테이블로 변환되는 경우
      - 이때, car 개체는 pID를 포함해야함 주의
        - car 개체의 주 키는 vehicleId임
        - 관계성에 참여하지 않는 car 터플의 pID값은 널값이 됨
  - **다대일 관계성 집합 변환 예제**
    - - ![image](https://github.com/googoo9918/TIL/assets/102513932/edaa9103-b367-41bc-8d44-a5f32ae7e285)
```sql
Create table myStudent (
		sID	varchar(20) primary key,
		sname	varchar(20),
		time 	varchar(20),
		pID	varchar(20) not null
    );
```
- 일대일 관계성 집합
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e6d02318-4cb8-458e-846b-652cb8690ff9)
    - 독립적인 테이블로 변환
      - 이 방식에서 own 테이블의 주 키는 pID 또는 vehicleID임
    - 한쪽(1)에 병합
      - car에 병합
      - 이 경우, car에 own의 속성과 people의 주 키 포함
    - 한쪽(2)에 병합
      - people에 병합
      - 이 방식에서 people에 own의 속성과 car의 주 키 포함
- 복합 속성
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e9618f96-6f21-4880-a891-65a979b9edd0)
    - 복합 속성 name, address, street은 하위 속성으로 바뀜(상위 복합 속성은 들어가지 않음)
    - 다수 값을 가지는 phoneNumber는 결과 테이블 스키마에 포함되지 않으며, 독립 테이블로 변환됨
    - 유도된 속성 age()는 스키마에는 명시적으로 포함됨
      - 객체지향 데이터 모델에서 유도된 속성은 메서드로 변환할 수 있음
      - 혹은 객체 관계형 데이터 모델에서는 속성 값으로 집합을 허용하므로, 속성 값으로 남아있을 수 있음
- 다수값 속성
  - `employeePhone(ID,phoneNumber)`
    - 다수값 속성은 단일 테이블로 변환될 때 관련 개체의 주 키 속성을 포함하게됨
- **관계형 스키마 변환 예제**
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/31dca8ba-9346-4952-98d3-a542c2f2cc7e)
    - 교수(교수번호(pk), 이름, 학과)
    - 지도(교수번호, 학번(pk))
      - 현재 교수와 학생은 다대일 -> 관계를 테이블로 만들려면 연결된 테이블의 pk를 속성으로 가져오고, 그 중 '다' 속성을 pk로 설정
    - 학생(학번(pk), 이름, 우편번호, 집주소)
      - 주소는 복합 속성이기 때문에, 우편번호와 집주소로 변환된다
    - 수강(학번(pk), 성적, 과목번호(pk))
      - 다대다는 관계된 테이블의 pk의 집합을 pk로 설정
    - 교과목(과목번호(pk), 과목이름)
      - 선수과목은 list라 제외됨
    - 강의(교수번호(pk), 과목번호(pk))
      - 수강의 논리와 동일
- **최소한의 스키마 변환 예제**
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1c34f729-0f53-443f-a264-8a1bf27c5162)
  - `branch(bname, city, assets)`
  - `loan(loan-number, amount, bname)`
  - `payment(loan-number, sequence, date, amount)`

### 설계 이슈
- 개체 대 속성
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/d3bba1db-b59e-4d4f-8884-807f9c6f25a8)
    - 개체로 저장할 것인가, 속성으로 표현할 것인가?
    - 전화번호에 대한 다양한 정보가 함께 저장된다면 테이블로 빼는게 맞고, 아니면 속성 값으로 냅두는게 맞음
- 개체 대 관계성
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2dd8fe4c-9248-4293-8f6e-a81fb8fd555c)
    - 일반적으로 명사는 개체로 표현, 명사 간 동사는 관계성으로 표현됨
    - 위 그림에서는 첫 erd가 적합
- 속성 위치
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/417713ce-b18d-465d-a87a-a449518e779b)
    - 단일 아이디어는 단일 개체 또는 단일 관계성으로 표현해야함
    - 관계성의 속성 위치는 다대다 카디날리티 제약에서는 관계성에 반드시 위치해야함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1c106fe6-cc82-4fd8-ba0e-c69e67d1b459)
    - 일대일인 경우에는 관계성 속성은 임의의 관련 개체에 위치할 수 있음
    - 위 그림처럼 일대 다인 경우에는 관계성 속성은 다(many)쪽 개체에 위치할 수 있음
    - `people(ID, name, address, age)`
    - `car(vehicleId, make, model, year, color, registrationDate, pID)`
- 중복 속성
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/8aa21e1b-ea9e-4886-a588-3f168de330c5)
    - professor 개체의 deptName은 중복되는 속성으로, 제거되어야 함
    - 다대다니까 스키마로 변환하면 `belongs(pID,dName)`이 될텐데, dName이 deptName과 중복되는 상황
- 이진 관계성 대 다진 관계성
  - Binary vs Non-binary Relationships
  - 다진 관계성이 다수 개체 간의 관계를 명확히 표현함
  - 다진 관계성을 다수 개의 이진 관계성으로 변환은 가능
    - 다만, 변환 시 다진 관계성의 모든 정보가 완전히 변환되지 않고 일부 정보가 유실되는 경향이 있음
- 이진 관계성 대 삼진 관계성
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/196379b9-4fc4-4288-8118-1b07167af91a)
    - 삼진 관계성
      - parenthood 관계성은 아버지/어머니/자식에 대한 정보가 정확히 기록되어야 함
      - 아버지/어머니/자식 정보를 반드시 함께 기록할 때 사용
      - 삼진 관계성은 fatherhood 관계성과 motherhood 관계성을 동시에 표현함
    - 이진 관계성
      - fatherhood와 motherhood 관계성은 서로 관련이 없으므로 아버지 정보만 기록이 가능함
      - 부분 정보만이라도 표현할 시 사용
      - 이진 관계성 2개가 반드시 삼진 관계성을 표현하지는 않음
- 다진 관계성 변환
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ea195ba7-8965-4ec8-88c7-0e7d58244bd5)
  - 이러한 변환의 문제점은 다진 관계성에 존재하는 제약 사항을 새로운 다이어그램에서 정확히 표현하지 못하는 것임

## 데이터베이스 설계 이론
### 좋은 스키마와 나쁜 스키마
- 나쁜 스키마 예시
  - 나쁜 스키마는 **Update anomaly(갱신 이상), Delete anomaly(삭제 이상), Insert anomaly(입력 이상)**를 지님
  - `mybadtable1(cID(pk), title, deptName, credit, chairman, building, budget)`
    - course 테이블과 department 테이블 자연 조인으로 생성
  - 테이블 인스턴스
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/0f7fa0c4-9167-4831-b0e6-c21730276339)
    - 갱신 이상(Update anomaly)
      - `department(deptName(pk), chairman, building, budget)`
        - chariman, building, budget은 deptName에 의존적임
        - deptName에 갱신이 생기면, 의존적인 나머지 속성을 동시에 갱신해야함
    - 삭제 이상(Delete anomaly)
      - CS 부서에서 마지막으로 제공하는 과목 삭제 시 학과 정보도 함께 삭제됨
    - 입력 이상(Insert anomaly)
      - 새 학과가 창설되어 학과에 대한 정보를 DB에 입력하고자 해도, 그 확과개 개설하는 과목이 없으면 주 키가 cID이기 때문에 학과 정보를 입력할 수 없음
  - `mybadtable2(cID(pk), title, deptName, credit, roomId(pk), building, capacity)`
    - course 테이블과 room 테이블을 합성하여 생성
    - 갱신 이상
      - 한 cID에 대해 roomID가 반복적으로 나타남(building과 capacitiy도..)
      - 갱신 시 반복되는 모든 값을 동시에 갱신해야함
    - 삭제 이상
      - 마지막 cID가 삭제되면 서로 관련없는 roomID도 함께 삭제됨
    - 입력 이상
      - 서로 관련 없는 cID와 roomID값이 동시에 있어야 입력 가능함

### 설계 목표
- 목적
  - 주어진 관계 스키마가 좋은 스키마인지 결정
  - 좋지 않은 스키마이면, 다수의 다른 관계 스키마로 분해
    - 분해된 스키마는 좋은 형태여야 하고, 분해 과정은 무손실 조인 분해여야 함
  - 함수 종속성과 다치 종속성을 이용하여 정규화 이론 설명

### 함수 종속성 이론
- 함수 종속성(Functional Dependencies)
  - 유효한 관계 인스턴스에 대한 제약(키 개념의 일반화)
  - 일부 속성의 값이 다른 속성의 값을 유일하게 결정
- 함수 종속성 정의
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/785a7524-4494-4e5e-a4a1-7700456e8a80)
  - 관계 인스턴스에서 A 속성 값이 동일한 임의의 터플 두 개에서 B 속성 값이 항상 동일하면
    - A는 B를 함수정으로 결정(B는 A에 함수적으로 의존)한다고 함
  - 예제에서 A는 B를 함수적으로 결정, B는 A를 함수적으로 결정하지 않음
- 함수 종속성과 키
  - 슈퍼 키는 관계 속성의 일부분으로서 전체 속성을 함수적으로 결정하는 속성을 의미함
  - 후보키는 슈퍼 키 중에서 필요한 속성만을 가짐
  - ex
    - `professor(pID(pk), name, deptName, salary)`
      - pID가 후보키이면, pID 속성은 모든 속성을 결정함
  - 함수 종속성은 주어진 관계 인스턴스의 유효성을 검사하는데 사용할 수 있음
  - 테이블의 제약 조건을 명시하는 데 함수 종속성을 사용할 수 있음
- 일반적으로 설계자에 의해 함수 종속성 결정
- 무의미(trivial) 함수 종속성
  - 함수 종속성이 테이블의 모든 인스턴스에 대해 만족이 되는 경우가 있음
  - 이러한 경우 무의미(trivial)하다 함
  - AB->A , A->A, ABC->BC 와 같이 오른쪽에 있는 속성이 종속성의 왼쪽에 있는 속성 집합에 항상 포함되는 경우
- 함수 종속성 폐포(closure)
  - 함수 종속성은 다른 함수 종속성으로부터 유추할 수 있음
  - A->B , B->C 이면 A->C를 유추할 수 있음

### 암스트롱 공리
- 암스트롱 공리
  - 암스트롱 공리는 새로운 함수 종속성을 유추할 수 있는 추론 규칙임
  - 세가지 추론 규칙으로 구성
    - 재귀성(reflexivity) 규칙
      - 무의미한 함수 종속성을 생성
    - 부가성 규칙(augmentation)
      - 주어진 함수 종속성에 동일한 속성을 양쪽에 추가하여도 됨
    - 이행성(transivity) 규칙
      - 추론의 이행성을 설명
- 암스트롱 공리 예제
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/9835076d-e522-447f-9e26-086bb05802b0)
  - A->H
    - A->B, B->H --> A->H
  - AG->I
    - A->C -->  AG->CG, CG->I --> AG->I
  - CG->HI
    - CG->H --> CG->CGH
    - CG->I --> CGH->HI
    - --> CG->HI
  - A->BC
    - A->B --> A->AB
    - A->C --> AB->BC
    - --> A->BC
    - 합성 규칙을 기억! X->Y, X->Z 일때 X->YZ가 참임
- 암스트롱 공리는 건전하고 완전함
  - 건전성(sound)
    - 새롭게 생성되는 함수 종속성이 유효하므로, 암스트롱 공리는 건전함
  - 완전함(complete)
    - 모든 유효한 함수 종속성을 암스트롱 공리를 이용하여 생성할 수 있음
- 추가 추론 규칙
  - 합성 규칙(union)
    - 왼쪽 속성이 동일하면 오른쪽 속성의 합집함을 결정하는 함수 종속성 생성 가능
    - A->B, A->C --> A->BC
  - 분해 규칙(decomposition)
    - 왼쪽 속성이 오른쪽 속성의 일부분도 각각 결정함
    - A->BC --> A->B, A->C
  - **분해 규칙 증명**
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/a1dd47b1-fc04-4f45-baaf-039cec3288b2)
      - a->By에서 재귀성(reflexivity rule)을 이용하여 By->B를 유도할 수 있고, 이행성(transitivity rule)을 이용하여 a->B를 유도할 수있음
      - a->By에서 재귀성을 이용하여 By->y를 유도할 수 있고, 이행성을 이용하여 a->y를 유도할 수 있음
  - Pseudotransitivity
    - A->B, BC->D --> AC->D

### 속성 폐포
- 속성 폐포(closure of attributes)
  - 주어진 속성이 함수적으로 결정할 수 있는 모든 속성을 속성 폐포(closure of attributes)라고 함
    - 주어진 속성에 주어진 함수 종속성을 적용하여 함수적으로 결정할 수 있는 모든 속성
  - 속성 폐포와(Closure of Attribute Sets) 함수 종속성 폐포(Closure of set of functional dependencies)는 다름
- **암스트롱의 공리를 이용하여 주어진 함수종속 집합 F를 최대한 확장시켰을 때, 그 최종 함수 종속 집합을 F의 '함수 종속성 폐포'라고 하며, 또한 주어진 속성집합 a로 시작되는 모든 함수속성 a->B를 구하였을 때 a가 결정하는 모든 B들의 집합을 a의 '속성 폐포'라고 한다.**
- 속성 폐포 예제
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2146fcab-cb10-48b8-91bd-3e42f34a9f66)
  - AG의 폐포((AG)*)를 구하시오
    - AG
    - AG->BG, AG->CG, AG->AG --> AG->ABCG
    - BG->HG, AG->BG --> AG->HG, AG->ABCG --> AG->ABCGH
    - AG->ABCGH, AG->CG->I --> AG->ABCGHI
      - 그냥 AG->ABCGH이고, CG->I 인데, CG를 ABCGH에서 포함하니까 AG-> ABCGHI라고 볼 수 있음
  - AG는 후보키인가?
    - AG가 모든 속성을 결정하므로 슈퍼 키 조건을 만족함
    - AG의 부분집합이 슈퍼 키 조건을 만족하는가?
      - A 또는 G 모두가 슈퍼 키 조건을 만족하지 않으므로
      - AG는 후보키임
  - 즉, AG는 슈퍼키이면서 후보키임
- **후보키 예시**
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/3a210037-8e06-428d-86bb-233fa453d2ab)
- 속성 폐포 사용
  - 주어진 속성이 슈퍼 키인지 검증할 때 사용
    - 위 예시를 참고하라
  - 주어진 함수 종속성이 유효한지 검증
  - 속성 폐포를 이용하요 함수 종속성의 폐포를 구할 수 있음
  
### 정규 커버(최소 커버)(Canonical Cover)
- 함수 종속성 집합에는 중복되는 함수 종속성이 존재하기도 하고, 개별 함수 종속성에도 불필요한 속성을 가지기도 함
  - `A->B, B->C, A->CD` can be simplified to `A->B, B->C, A->D`
    - A->CD에서 A->C 및 A->D 유추 가능
    - A->C는 중복적으로 유추가 가능하여 필요 없음
  - `A->B, B->C, AC->D` can be simplified to `A->B, B->C, A->D`
    - AC->D에서 A->D, C->D 는 분해 규칙이 아님!! 정신차려!
    - A->B, B->C에서 A->C가 유추됨
      - 따라서 제외됨(중복 뿐 아니라 함수 종속성 폐포로 유추 가능하면 제외)
    - A->C에서 A->AC이므로 A->D가 유추됨
      - 따라서 AC->D 에서 C가 필요 없음이 자명함
- 정규 커버 계산 예제
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f30cb0c2-0e42-4a5b-bc91-9484b65d8840)
    - A->B가 있으니 A->BC는 필요없음
    - B->C가 있으니 AB->C도 필요없음
    - 따라서 canonical cover는 `A->B, B->C`

### 정규형
- 정규화형
  - 제1정규형(1NF), 제2정규형(2NF), 제3정규형(3NF), 제4정규형(4NF), 제5정규형(5NF), BCNF(Boyce/Codd Normal Form)
    - 1,2,3 BCNF는 함수 종속성을 이용하여 정의
    - 제4정규형은 다치 종속성을 이용하여 정의
- 제1정규형
  - 도메인의 모든 값이 원자 값이면 제1정규형
    - 원자값이 아닌 값
      - 집합, 리스트, 백, 복합 속성
- 다양한 함수 종속성
  - 임의의 후보 키에 속하는 속성은 주요 속성(prime attribute)
    - 테이블에 다수 개의 후보 키가 존재하는 경우, 최소한 하나 후보 키에 속해도 주요 속성임
  - 그렇지 않은 속성은 비주요 속성(nonprime attribute)
  - 완전 함수 종속성
    - A->B(학번을 통해 이름이 결정될 때)에서 A(학번)의 부분집합 R(학번 앞 2자리)에 대하여 R->B가 성립하지 않으면(학번 앞 2자리로 이름이 결정되지 않으면) 이 함수 종속성은 완전 함수 종속성임
  - 부분 함수 종속성
    - A의 부분집합 R로도 B를 결정할 수 있을 때
      - 즉, A->B 외에도 R->B가 성립하는 경우
  - 이행(Transitive) 함수 종속성
    - A->B, B->R이 존재하는 경우 A는 R을 이행적으로 결정
- 제 2정규형
  - 제1정규형(모든 값이 원자값) 중에서 모든 비주요 속성이 모든 후보 키에 완전 의존적이어야 함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/aec90e08-d763-4517-a10a-0899b50f39fe)
    - 이와 같은 함수 종속성에서
      - 비주요 속성인 eName이 후보 키에 부분적으로 의존함으로, 테이블 R은 제 2정규형이 아님
        - SSN에만 완전 의존하고, pNumber에 의존하지 않기 때문
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/5d39071a-33a3-4154-a263-37dee440afc3)
      - 위 3개의 테이블은 모든 비주요 속성이 후보 키에 완전 희존하므로, 모두가 제 2정규형에 속함
- 제1정규형, 제2정규형 예제
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/eeae0b6b-97e5-4b0f-b64b-c41130234ea5)
    - 이 테이블은 비주요 속성인 city가 후보 키에 부분적으로 의존하므로 제2정규형이 아님
    - 나쁜 스키마임, 따라서 세 가지 Anomalies 발생
      - Insert
        - City 속성은 S#에만 의존적이어서, 공급자는 S#(부품 번호)가 없으면 공급자의 City(도시)를 입력할 수 없음
      - Update
        - City 속성은 S#에만 의존적이어서, S#번호가 반복되면 동일한 City 속성 값이 반복적으로 나타나고, 갱신 시 이 모든게 갱신되어야함
      - Deletion
        - 공급자가 마지막 부품 공급을 삭제하면 City정보도 삭제되게 됨
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/85ec9412-895e-43a1-933f-cf98866e1abb)
    - Second는 비주요 속성이 후보키에 완전 의존되므로 제2정규형임
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ba1294ce-a13c-42ee-9e3a-e802dc70bf09)gi
- 제3정규형
  - 제2정규형 중에서 비주요 속성이 모든 후보 키에 이행적으로 의존적이 아니면 제 3정규형이다
  - 즉, 모든 의미 있는 함수 종속성 A->B에서 A가 슈퍼 키이거나 B가 주요 속성이어야 한다
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/461857f2-b982-424f-8420-f31bc6180062)
    - 현재 비주요속성 Dname과 Dmgr은 이행적으로 후보 키 SSN에 의존적이므로
    - 2정규형은 되지만, 3정규형은 되지 않음
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/5a5a249b-271e-428c-bf33-6caa30809a4c)
    - 이와 같이 분해해야 제3정규형임
  - 제2정규형 및 제3정규형 예제
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/735dd6e8-42e8-4a90-8b88-9bddf4f234c9)
      - status는 S#에 직접적으로 의존적이지만, 이행적 종속도 이뤄지고 있음
      - 모든 비주요 속성이 기본 키에 대해 이행적 종속이 없어야 되므로, 제3정규형이 아님
      - 따라서 잘못된 스키마로 인해 세 가지 Anomalies 발생
        - Insert
          - S# 없이는 City에 대한 Status 정보를 입력할 수 없음
        - Update
          - City가 반복될 때마다 Status값이 반복적으로 나오게 됨
          - City에 대한 Status 값의 변경이 있으면 다수개의 값을 동시에 갱신해야 되는 상황 초래
        - Deletion
          - 마지막 S#을 삭제하면 S#과 관련 없고 City에 관련이 있는 Status값도 함께 삭제되는 현상
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/94945a08-a993-4dc3-910d-b365f450cd29)
      - 이렇게 분해해야 제3 정규형임
- BCNF
  - 관계형 스키마가 모든 의미 있는 함수 종속성 A->B에서 A가 슈퍼 키이면 BCNF 정규형이 됨
  - 즉, 모든 속성이 기본 키에 의존해야 한다!
- 제3정규형과 BCNF 예제
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/caca663b-d4f0-412b-9599-82e75f7c7d95)
    - 이행적 종속 -> 제3정규형 아님
    - studioName이 슈퍼키가 아님
      - BCNF 아님 
    - 제2정규형임
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/266cac0d-50fd-436e-8a9f-332bf7dcd56a)
    - 이렇게 분리해야 BCNF임
    - 모든 속성이 기본 키에 의존하고 있음
  - 속성이 두 개인 테이블은 모두 BCNF임
    - 함수 종속성이 없거나, 단방향으로 있거나, 양방향으로 있거나 다 BCNF임
- 연습1
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/bb225917-0a92-40d9-b5b0-94567a47e748)
    - 정규형을 결정하라
    - 후보키 : AB
    - 주요 속성: A, B
    - 비주요 속성: C, D, E, F
    - 비주요 속성이 후보키에 부분적으로 의존하므로 1NF임
    - 나쁜 스키마의 전형적인 사례, ACD와 BEF는 전혀 관련이 없음
      - --> 어떤 anomaly가 생길까?
- 연습2
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/3ff3431d-b43e-4b0f-a80e-507dc33181a5)
    - 후보키: AB
    - 비주요 속성끼리 함수 종속성을 가지는 것은 2NF에서는 상관없음
    - 2NF임
    - 마찬가지로 여러 anomaly를 발생시킴
      - 속성 D는 속성 C에만 의존적
        - 속성 D 값이 반복되면 속성 C 값도 반복됨(갱신 이상)
        - 속성 AB 값이 없으면 속성 C에만 의존적인 속성 D 값을 테이블에 입력할 수 없음(입력 이상)
        - 속성 C에 대한 마지막 터플이 삭제되면 속성 D 값도 함꼐 삭제(삭제 이상)
- 연습3
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/3ef57ee1-4b3d-4799-81fa-273e0726df7e)
    - 후보키가 A인 2NF
    - 2NF에서는 이행적 종속이 가능하다.
- 연습4
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/48d8db83-181f-4b0b-809d-fc2124546f03)
    - 후보키가 AB인 3NF임
    - 3NF에서는 비주요 속성이 주요 속성을 가리킬 수 있음
- 연습5
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/432ddb2a-40a1-465d-98b5-4aed7ec3386a)
  - 조심!! 후보키는 A와 D임
  - 주요속성만 가리키고 있으니 BCNF임
- 연습6
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/bdc328fe-a7d7-48c4-9656-412e5c8cf3c4)
  - 말할 것도 없이 BCNF, 후보키는 A임
- 연습7
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/3079836a-a1f6-4a1a-9836-f866ccfec420)
  - A~D에 순환이 형성되어 있음
  - A,B,C,D 모두 후보키
  - 비주요 속성은 E밖에 없음
  - 후보키만 가리키고 있으니, BCNF임
