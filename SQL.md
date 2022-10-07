## SQL
[SQL Cheat Sheet](https://websitesetup.org/wp-content/uploads/2020/08/SQL-Cheat-Sheet-websitesetup.pdf)
- SQL(Structured Query Language)
  - 구조화된 Query 언어
  - 데이터베이스용 프로그래밍 언어
    - 데이터베이스에 query를 보내 원하는 데이터를 필터링및 삽입 할 수 있음
    - 단, 데이터의 구조가 고정되어 있어야 함
      - MongoDB와 같은 NoSQL에서는 사용 불가
    - 주로 관계형 데이터베이스에서 사용
      - MySQL, Oracle, SQLite, PostgreSQL등 다양한 데이터베이스에서 SQL 구문 사용 가능
- Query
  - 질의문
    - ex) 검색창에 적는 검색어
  - 저장되어있는 정보를 필터링 하기 위한 질문
- #### SQL 종류
  - SQL에서도 역할에 다라 문법이 다양하게 존재
  - ##### Data Definition Language(DDL)
    - 데이터 정의 시 사용
    - 테이블을 만들 때 사용하는 `CREATE`, 제거 시 사용되는 `DROP`
  - ##### Data Manipulation Language(DML)
    - 데이터 변경 시 사용
    - `INSERT`, `DELETE`, `UPDATE`
  - ##### Data Control Language(DCL)
    - DB 접근 권한 관련 문법
    - 권한을 주는 `GRANT`, 권한을 가져가는 `REVOKE`
  - ##### Data Query Language(DQL)
    - 정해진 스키마 내에서 쿼리할 수 있는 언어
    - `SELECT`
    - DQL을 DML의 일부분으로 취급하기도 함
  - ##### Transaction Control Language(TCL)
    - DML을 거친 데이터의 변경사항 수정 가능
      - DML이 작업한 내용을 데이터베이스에 커밋하는 `COMMIT`
      - 커밋했던 내용을 롤백하는 `ROLLBACK`

### 데이터베이스 필요성
  - In-memory
    - 프로그램 종료 시 프로그램이 사용하던 데이터도 사라짐
    - 변수 등에 저장한 데이터가 프로그램 실행에 의존함
    - 데이터의 수명이 프로그램의 수명에 의존하게 됨
  - File I/O
    - 데이터가 필요할 때마다 전체 파일을 읽어야 함
      - 파일의 크기가 커질수록 비효율적
    - 파일 손상, 여러 파일 작업 등 복잡하고 데이터량이 많아질수록 데이터 로딩 비효율적

## 데이터베이스 관련 명령어

### 데이터베이스 생성
```SQL
CREATE DATABASE 데이터베이스_이름;
```
### 데이터베이스 삭제
```SQL
DROP DATABASE 데이터베이스_이름;
```
### 데이터베이스 사용
- DB를 이용해 테이블을 만들거나 수정, 삭제하는 등의 작업 시 사용 명령을 먼저 전달해야 한다.

```SQL
USE 데이터베이스_이름;
```

### 테이블 생성
- ```USE```를 이용해 데이터베이스 선택 시, 테이블 생성 가능
- 테이블은 필드와 함께 만들어야 함.
- EX) `user` 테이블 생성, 테이블 예시
- ![image](https://user-images.githubusercontent.com/102513932/193962914-1f61e4d0-c61d-41c0-b97b-f6ee8ff7f9e3.png)

### 테이블 삭제
```SQL
DROP TABLE 테이블_이름;
```

### 테이블 내용 삭제
```SQL
TRUNCATE TABLE 테이블_이름;
-- 테이블의 모든 데이터가 삭제되지만 테이블의 구조는 삭제되지 않음
```

### 테이블 수정하기
```sql
ALTER TABLE 테이블_이름 DROP COLUMN 특성이름_1
-- column 삭제

ALTER TABLE 테이블_이름 ADD 특성이름_1 데이터형식 NULL
-- column 추가
-- 테이블에 특성이름_1 컬럼 추가, 형식 지정 및 NULL값 허용

ALTER TABLE 테이블_이름 ALTER COLUMN 특성이름_1 데이터형식 NOT NULL
-- 테이블에 특성이름_1의 데이터형식 변경 및 NULL값 허용X
```

```SQL
CREATE TABLE user(
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    email varchar(255)
);
```
- SQL 콘솔에서 `Enter`키를 이용해 여러 줄의 코드 입력 가능

### 테이블 정보 확인

```SQL
DESCRIBE user;
```
- user 테이블의 정보 확인

```SQL
mysql> describe user;
+-------+--------------+------+-----+---------+----------------+
| Field | Type         | Null | Key | Default | Extra          |
+-------+--------------+------+-----+---------+----------------+
| id    | int          | NO   | PRI | NULL    | auto_increment |
| name  | varchar(255) | YES  |     | NULL    |                |
| email | varchar(255) | YES  |     | NULL    |                |
+-------+--------------+------+-----+---------+----------------+
3 rows in set (0.00 sec)
```

## SQL 관련 명령어

### SELECT
- SLELECT는 CRUD중 Read
```SQL
SELECT 'hello world' -- 일반 문자열
SELECT 2  -- 숫자
SELECT 15 +3  -- 간단한 연산
```
### INSERT
- INSERT는 CRUD 중 Create
```SQL
INSERT INTO 테이블(필드이름1, 필드이름2)
VALUES('값1', '값2')
```
### UPDATE
- CRUD 중 Update
```SQL
UPDATE 테이블 SET 필드이름1=값1, 필드이름2= 값2;
WHERE 조건문
-- 필드이름1 열의 값을 값1로 설정하고 필드이름2 열의 값을 값2로 설정하되, 조건문에 해당하는 값만 설정할 것
-- WHERE이 없으면 그냥 설정이 되겠지? 
```
### DELETE
- CRUD 중 Delete
```SQL
DELETE FROM 테이블
WHERE 조건문
```
### FROM
- 테이블 관련 작업 시 반드시 입력
- FROM뒤에는 결과를 도출할 DB 테이블 명시

```SQL
SELECT 특성_1 
FROM 테이블_이름
-- 특정 특성을 테이블에서 조회

SELECT 특성_1, 특성_2
FROM 테이블_이름
-- 몇 가지의 특성을 테이블에서 조회

SELECT *
FROM 테이블_이름
-- 테이블의 모든 특성을 조회
```
> *는 와일드카드로, 전부 선택 시 사용됨

### WHERE
- 필터 역할을 하는 쿼리문, 선택적 사용 가능
```SQL
SELECT 특성_1, 특성_2
FROM 테이블_이름
WHERE 특성_1 = "특정 값"
-- 특정 값과 동일한 데이터 찾기

SELECT 특성_1, 특성_2
FROM 테이블_이름
WHERE 특성_2 <> = "특정 값"
-- 특정 값을 제외한 값을 찾기
```
#### AND, OR, IN
```SQL
SELECT 특성_1
FROM 테이블_이름
WHERE 특성1 AND 특성2
-- AND 연산자는 WHERE 절에서 모든 조건이 충족하는 행 반환

SELECT 특성_1
FROM 테이블_이름
WHERE 특성1 OR 특성2
-- OR 연산자는 WHERE 절에서 조건 중 하나 이상 충족하는 행 반환

SELECT 특성_1
FROM 테이블_이름
WHERE 특성1 IN('특정 값1','특정 값2')
-- 조건의 범위를 지정하는 데 사용, 값은 콤마로 구분하여 괄호 내에 묶음
-- 이 값 중에서 하나 이상과 일치하면 조건에 맞는 것으로 평가
-- 목록에 넣을 값이 여러 개일 때 사용

SELECT 특성_1
FROM 테이블_이름
WHERE 특성_1 NOT IN('특정값1', '특정값2')
```
#### BETWEEN
```SQL
SELECT 특성_1
FROM 테이블_이름
WHERE 특성_1 BETWEEN 800 AND 1500 
-- 특성1>=800 AND 특성1<= 1500

WHERE NOT 특성_1 BETWEEN 800 AND 1500
-- 특성1<800 OR SAL > 1500

WHERE 특성_1 BETWEEN '문자열1' AND '문자열2'
-- 묵시적 형변환을 통해 문자열 사이 값도 조회 가능


```
#### >, <, <=, >=
```SQL
SELECT 특성_1, 특성_2
FROM 테이블_이름
WHERE 특성_1 > "특정 값"

SELECT 특성_1, 특성_2
FROM 테이블_이름
WHERE 특성_1 <= "특정 값"
-- 특정 값보다 크거나 작은 데이터를 필터할 때는 `<`. `>`, 비교하는 값을 포함하는 이상,이하 값은 '<=','>=' 사용
```
#### LIKE (with Wildcard)
```SQL
-- 패턴에는 %와 _가 사용
-- %는 모든 문자라는 의미, _는 한 글자라는 의미
SELECT 특성_1, 특성_2
FROM 테이블_이름
WHERE 특성_2 LIKE '%특정 문자열%'
-- 문자열에서 특정 값과 비슷한 값들을 필터링 할 때는 'LIKE'와 '%' 혹은 '*' 사용
-- '특정 문자열' -> 문자 그대로 가진 ROW만 출력
-- %특정 문자열% -> 앞뒤에 무슨 글자가 오던지 '특정 문자열'이라는 문자가 있는 ROW 출력
-- %특정 문자열 -> 앞에 무슨 글자가 오던지 '특정 문자열'로 끝나는 문자가 있는 ROW 출력
-- 특정 문자열% -> 뒤에 무슨 글자가 오던지 '특정 문자열'로 시작하는 문자가 있는 ROW 출력
-- _특정 문자열% -> 뒤에는 아무 글자 오던 상관X, 맨 앞에 한 글자 뒤에 '특정 문자열' 글자가 있는 ROW 출력
-- __특정 문자열& -> 위에서 한 글자가 두 글자로 변환
-- __특정 문자열__ -> 문자 앞뒤로 2개의 문자가 있는 ROW 출력
-- [bsp]% -> b, s, 또는 p 로 시작하는 ROW
-- [a-f]% -> a에서 f로 시작하는 ROW
-- [!bsp]% -> b,s,p로 시작하지 않는 ROW

```

#### NULL, NOT NULL
```SQL
SELECT *
FROM 테이블_이름
WHERE 특성_1 IS NULL
-- 값이 없는 경우 'NULL'을 찾을 때 'IS'와 같이 사용

SELECT *
FROM 테이블_이름
WHERE 특성_1 IS NOT NULL
-- 값이 없는 경우를 제외할 때는 'NOT' 추가 이용
```
### CASE
- SQL에서도 if문과 같은 기능 사용 가능
- 특정 조건에 따라 다른 결과 도출 가능
```sql
SELECT CASE
      WHEN CustomerId <= 25 THEN 'GROUP 1'
      WHEN CustomerId <= 50 THEN 'GROUP 2'
      ELSE 'GROUP 3'
   END
  FROM customers
```
### Aliases
- 테이블 또는 테이블의 열에 임시 이름을 지정하는 데 사용
  - alias는 칼럼 이름을 읽기 쉽게 하기 위해 사용
  - 별명은 조회 기간 동안만 존재
```SQL
SELECT column_name AS alias_name
FROM table_name;
-- 열에 임시 이름 저장

SELECT column_name
FROM table_name AS alias_name;
-- 테이블에 임시 이름 저장
```
### COUNT
- 집합으로부터 하나의 값을 계산하는 집계 함수 중 하나
  - 결괏값으로 하나의 행을 반환
  - `*`를 인수로 하는 유일한 집계함수
  - 집계함수에서는 집합 안에 NULL값이 있을 경우 무시함
- 다른 집계 함수
  - AVG(), MIN(), MAX(), SUM()
```SQL
SELECT *, COUNT(*)
FROM 테이블_이름
GROUP BY 특성_1;
-- 모든 레코드에 대한 COUNT 함수 사용

SELECT 특성_1, COUNT(*)
FROM 테이블_이름
GROUP BY 특성_1;
-- 각 특성_1에 해당하는 레코드의 갯수를 확인

SELECT 특성1, COUNT(특성_1), COUNT(특성_2)
FROM 테이블_이름;
-- 인수로 열명을 지정, 열에 한하여 행의 개수를 구할 수 있음

SELECT 특성1, SUM(특성2)
FROM 테이블_이름
GROUP BY 특성1;
-- 테이블에서 특성1을 기준으로 그룹화하고, 특성2 필드 값의 합을 구함
-- MIN, MAX 사용 예시 동일
```

### GROUP BY
- 데이터를 그룹화 하는 명령어, 주로 집계 함수와 같이 사용
  - 특정 칼럼을 기준으로 집계 함수를 사용하여 데이터 추출할 때 사용
- ![image](https://user-images.githubusercontent.com/102513932/194049146-23196926-3d43-44be-a0b1-5b64018f11b1.png)
- ![image](https://user-images.githubusercontent.com/102513932/194049441-99d6e3b5-298a-4ea8-a02d-22c73f5ce61a.png)
  - GROUP BY 절의 칼럼은 SELECT 절에 존재해야 사용할 수 있음
  - SELECT 절에서 집계 함수를 제외한 칼럼을 GROUP BY 절에 기술
- ![image](https://user-images.githubusercontent.com/102513932/194050230-46a696ea-c930-4bf0-be27-5aaaa565b351.png)
  - 그룹 칼럼 여러 개 지정
    - 첫 번째 칼럼으로 먼저 그룹이 묶이고
    - 두 번째 칼럼으로 집계가 됨

#### HAVING
- HAVING은 GROUP BY로 조회된 결과 필터링 가능
```SQL
SELECT CustomerId, AVG(Total)
FROM invoices
GROUP BY CustomerId
HAVING AVG(Total) > 6.00
-- invocies 테이블을 CustomerId로 그룹화하고, 평균이 6을 초과한 결과를 조회
-- HAVING은 그룹화된 결과에 대한 필터
-- WHERE은 저장된 레코드를 필터링, 그룹화 전에 필터링 시 WHERE 사용
```
### ORDER BY
- 돌려받는 데이터 결과를 어떤 기준으로 정렬할지 결정
  - 선택적 사용 가능

```SQL
SELECT *
FROM 테이블_이름
ORDER BY 특성_1
-- 기본 정렬은 오름차순

SELECT *
FROM 테이블_이름
ORDER BY 특성_1 DESC
-- 내림차순(Descending) 정렬도 가능

ORDER BY 특성_1 , 특성_2
-- 특성_1로 정렬 후 특성_2로 정렬
```

### LIMIT
- 결과로 출력할 데이터 갯수 지정
  - 선택적 사용 가능
- 커리문에서 사용 시 가장 마지막에 추가

```SQL
SELECT *
FROM 테이블_이름
LIMIT 200
-- 데이터 결과를 200개만 출력
```

### DISTINCT
- 중복된 값 제거시 사용
```SQL
SELECT DISTINCT 특성_1
FROM 테이블_이름
-- 특성_1을 기준으로 중복되지 않은 값들만 선택

SELECT
    DISTINCT
        특성_1
        , 특성_2
        , 특성_3
FROM 테이블_이름
-- 특성_1, 특성_2, 특성_3의 중복되지 않은 '조합' 값들 선택
```
### SUBQUERY
- 쿼리문 작성 시, 다른 쿼리문 포함 가능
- 쿼리에 중첩으로 위치해 정보 전달, 소괄호로 감싸야 함
- 서브쿼리의 결과는 개별 값 OR 레코드 리스트
  - 서브커리의 결과를 하나의 칼럼으로도 이용 가능
```SQL
SELECT CustomerId, CustomerId = (SELECT CustomerId FROM customers WHERE CustomerId = 2)
FROM customers
WHERE CustomerId<6
```
#### IN, NOT IN
- IN은 특정한 값이 서브쿼리에 있는지 확인 가능
```sql
SELECT *
FROM customers
WHERE CustomerId IN (SELECT CustomerId FROM customers WHERE CustomerId <10)
-- customers 테이블에서 'CustomerId'의 값이 서브쿼리에서 돌려받는 값에 속한 결과들만 조회
-- NOT IN 사용 시 서브코리에서 조회된 10을 초과하는 레코드 조회
```
#### EXISTS, NOT EXISTS
- 돌려받은 서브쿼리에 존재하는 레코드 확인
  - 레코드 존재 시 TRUE, 없을 시 FALSE 리턴
```SQL
SELECT EmployeeId
FROM employees e
WHERE EXISTS(
  SELECT 1
  FROM customers c
  WHERE c.SupportRepId = e.EmployeeId
)
  ORDER BY EmployeeId
-- employees 테이블에서 'EmployeeId'필드 조회
-- 서브쿼리로 customers 테이블의 'SupportRepId' 필드값과 employees 테이블의 'EmployeeId' 필드값을 비교해 일치하는 레코드들을 가져옴
```

#### FROM
- FROM에도 서브쿼리 사용 가능
- 조회된 결과의 하나의 테이블이나 조회할 대상으로 지정해 사용 가능
```SQL
SELECT *
FROM (
  SELECT CustomerId
  FROM customers
  WHERE CustomerId <10
)
```


### INNER JOIN
- `INNER JOIN` 이나 `JOIN`으로 실행 가능
- 테이블의 공통된 부분 기준 연결
- 서로 매칭되는 것만 엮어 조회함
```SQL
SELECT *
FROM 테이블_1
JOIN 테이블_2 ON 테이블_1.특성_A = 테이블_2.특성_B
-- 둘 이상의 테이블을 서로 공통된 부분을 기준으로 연결
-- 기준 테이블(테이블_1)과 조인 테이블(테이블_2) 모두 데이터가 존재해야 조회됨
```

### OUTER JOIN
- 매칭 뿐 아니라 미매칭 데이터도 함께 조회
- LEFT, RIGHT는 미매칭 데이터도 조회할 테이블 방향
  - Left Outer Join은 왼쪽에 기입한 테이블이 매칭여부와 관계없이 모두 나오게 됨
```SQL
SELECT *
FROM 테이블_1
LEFT OUTER JOIN 테이블_2 ON 테이블_1.특성_A = 테이블_2.특성_B
-- 'LEFT OUTER JOIN'으로 LEFT INCLUSIVE 실행

SELECT *
FROM 테이블_1
RIGHT OUTER JOIN 테이블_2 ON 테이블_1.특성_A = 테이블_2.특성_B
-- 'RIGHT OUTER JOIN'으로 RIGHT INCLUSIVE 실행
```

### JOIN별 비교

1) 테이블 A에만 존재하는 코드 조회 시
![image](https://user-images.githubusercontent.com/102513932/193969210-8e701034-12d5-4af0-9f3c-d215e9721749.png)
```SQL
SELECT *
FROM A LEFT OUTER JOIN B ON A.CODE = B.CODE
WHERE B.CODE IS NULL
```
2) 테이블 A와 테이블 B 모두에 존재하는 코드 조회 시
![image](https://user-images.githubusercontent.com/102513932/193969391-6d4dc45a-2bf9-4333-b50f-f852d9c3ea76.png)
```SQL
SELECT *
FROM A INNER JOIN B ON A.CODE = B.CODE
```
3) 테이블 A의 모든 코드는 매칭여부와 관계없이 모두 조회
![image](https://user-images.githubusercontent.com/102513932/193969479-34022f9d-7306-45fe-a73d-c1587838121e.png)
```SQL
SELECT *
FROM A LEFT OUTER JOIN B ON A.CODE = B.CODE
```

4) A테이블과 B테이블 매칭이 안되는 나머지 모두 조회
![image](https://user-images.githubusercontent.com/102513932/193969598-c53a9941-1375-42a4-8f1a-1f3b80c09c89.png)
```SQL
SELECT *
FROM A FULL OUTER JOIN B ON A.CODE = B.CODE
WHERE A.CODE IS NULL OR B.CODE IS NULL
```

### 여러 쿼리문 한 번에 사용하기
- Brazil에서 온 고객을 도시별로 묶고, 각 도시 수에 따라 내림차순 정렬 후 Customerld에 따라 오름차순으로 정렬한 3개의 결과만 요청하는 예시

> 같은 결과를 출력하는 쿼리문은 여러 개 존재함

```SQL
SELECT c.CustomerID, c.FisrtName, count(c.City) as 'City Count'
From customers AS c
JOIN employees AS e ON c.SupportRepId = e.EmployeeId
WHERE c.Country = 'Brazil'
GROUP BY c.City
ORDER BY 3 DESC, c.CustomerId ASC
LIMIT 3
```
### SELECT 실행 순서
- FROM -> WHERE -> GROUP BY -> HAVING -> SELECT -> ORDER BY
```SQL
SELECT CustomerId, AVG(Total)
FROM invoices
WHERE CustomerId >= 10
GROUP BY CustomerId
HAVING SUM(Total) >= 30
ORDER BY 2
```
- FROM : invoices 테이블에 접근
- WHERE : CustomerId 필드가 10 이상인 레코드 조회
- GROUP BY : CustomerId를 기준으로 그룹화
- HAVING : Total 필드의 총합이 30 이상인 결과만 필터링
- SELECT : 조회된 결과에서 CustomerId 필드와 Total 필드의 평균값을 구함
- ORDER BY : 필드를 기준으로 오름차순 정렬한 결과를 리턴

## ACID

### 트랜잭션
- 여러 개의 작업을 하나로 묶은 실행 유닛
  - 각 트랜잭션은 하나의 특정 작업으로 시작
    - 묶여있는 모든 작업들을 다 완료해야 정상적으로 종료
    - 하나의 트랜잭션에 속한 여러 작업 중 단 하나라도 실패하면, 이 트랜잭션에 속한 모든 작업을 실패한 것으로 판단
  - 작업이 하나라도 실패 시 트랜잭션도 실패
  - 모든 작업이 성공 시 트랜잭션 성공
- 성공/ 실패 두 개의 결과만 존재
  - 미완료된 작업없이 모든 작업을 성공해야 함
- ACID 라는 특성 지님

### ACID
- #### Atomicity(원자성)
  - 하나의 트랜잭션에 속해있는 모든 작업이 전부 성공하거나 전부 실패해 결과를 예측할 수 있어야 함
  - 하나라도 실패하면, 하나의 단위로 묶인 모든 작업이 실패하게 만들어 기존 데이터 보호
  - SQL에서도 마찬가지로 특정 쿼리가 부분적으로 실패 시, 전부 실패하도록 구현됨
    - 때때로 충돌 요인에 대해 선택지 제공
- #### Consistency(일관성)
  - 데이터베이스의 상태는 일관되어야 함
  - 하나의 트랜잭션 이전과 이후, 데이터베이스의 상태는 이전과 같이 유효해야 함
    - 데이터베이스의 제약이나 규칙을 만족해야 한다는 의미
- #### Isolation(격리성, 고립성)
  - 각각의 트랜잭션은 다른 트랜잭션으로부터 독립되어야 함
    - 다른 트랜잭션의 작업 내용을 알 수 없음
  - 동시에 여러 개의 트랜잭션 수행 시, 각 트랜젝션은 고립되어 있어 연속으로 실행된 것과 동일한 결과를 나타냄
- #### Durability(지속성)
  - 하나의 트랜잭션이 성공적으로 수행 시, 로그가 남아야 함
  - 런타임 오류나 시스템 오류 발생 시, 해당 기록은 영구적이어야 함

## SQL VS NoSQL
- ### 관계형 데이터베이스
  - SQL 기반
  - 테이블의 구조와 데이터 타입 사전 정의
    - 정의된 내용에 알맞은 형태의 데이터만 삽입 가능
  - 행과 열로 구성된 테이블에 데이터 저장
    - 열은 하나의 속성에 대한 정보 저장
    - 행은 각 열의 데이터 형식에 맞는 데이터 저장
  - SQL을 활용해 원하는 정보 쿼리 가능
    - 스키마가 뚜렷하게 보임
    - 관계형 DB에서는 테이블 간의 관계 직관적 파악 가능
- ### 비관계형 데이터베이스
  - NoSQL로 데이터 다룸
  - 데이터가 고정되지 않은 데이터베이스
  - 스키마가 반드시 없는 것은 아님
    - 데이터를 읽어올 때 스키마에 따라 데이터 읽어옴
    - schema on read
- ### 데이터 저장
  - NoSQL은 Key-value, document, wide-column, graph등의 방식으로 데이터 저장
  - 관계형 데이터베이스는 SQL을 이용해 데이터를 테이블에 저장
    - 미리 작성한 스키마를 기반으로 정해진 형식에 맞게 데이터 저장
- ### 스키마(Schema)
  - SQL 사용시, 고정된 형식의 스키마 필요
    - 처리하는 데이터 속성별로 열에 대한 정보가 미리 정해져야함
    - 스키마는 추후 변경할 수 있지만, 데이터베이스 전체를 수정하거나 오프라인으로 전환해야함
  - NoSQL은 관계형 DB보다 동적으로 스키마 형태 관리 가능
    - 행 추가시 즉시 새로운 열 추가 가능
    - 개별 속성에 대해 모든 열에 대한 데이터를 반드시 입력하지 않아도 됨
- ### 쿼리(Querying)
  - 관계형 DB는 테이블의 형식과 관계에 맞춰 데이터 요청
    - 정보 요청 시 SQL과 같이 구조화된 쿼리 언어 사용
  - 비관계형 DB의 쿼리는 데이터 그룹 자체를 조회
    - 구조화 되지 않은 쿼리 언어로도 데이터 요청 가능
      - UnQL(UnStructed Query Language)
- ### 확장성(Scalability)
  - 관계형 DB는 수직적 확장
    - 높은 메모리, CPU 사용
      - 하드웨어 성능을 많이 이용, 비용이 많이 듬
    - 여러 서버에 걸쳐 관계 정의 가능하지만, 복잡하고 시간이 많이 소모
  - NoSQL 구성 DB는 수평적 확장
    - 보다 값싼 서버 증설, 클라우드 서비스를 이용하는 확장
    - 많은 트래픽을 보다 편리하게 처리가능
    - 저렴한 범용 하드웨어나 클라우드 기반 인스턴스에 호스팅 가능, 상대적으로 비용 저렴

### NoSQL
- Key-Value 타입 
  - 속성을 ```key-value```의 쌍으로 나타내는 데이터를 배열의 형태로 저장
  - key는 속성 이름, value는 속성에 연결된 데이터 값
  - ex) Redis, Dynamo
- 문서형 데이터베이스
  - 데이터를 테이블이 아닌 문서처럼 저장하는 데이터베이스
  - JSON과 유사한 형식의 데이터를 문서화하여 저장
  - 각각의 문서는 하나의 속성에 대한 데이터 지님
  - 컬렉션이라는 그룹으로 묶어서 관리
  - ex) MongoDB
- Wide-Column 데이터베이스
  - 열에 대한 데이터를 집중적으로 관리
  - 각 열에는 key-value 형식으로 데이터 저장
  - 컬럼 패밀리(column families)라고 하는 열의 집합체 단위로 데이터 처리
  - 한 행에 많은 열 포함, 유연성 증가
  - 규모가 큰 데이터 분석에 주로 사용
  - ex) Cassandra, HBase
- 그래프 데이터베이스
  - 자료구조의 그래프와 비슷한 형식으로 데이터 간 관계 구성
  - 노드에 속성별로 데이터 저장
  - 각 노드간 관계는 선으로 표현
  - ex) Neo4J, InfiniteGraph
  
### SQL 기반 DB 사용 케이스
- 1. DB의 ACID 성질 준수하는 경우
  - 안전성을 보장해야 하는 경우
    - 상호 작용 방식을 정확하게 규정
      - 예외적 상황을 줄임
      - 무결성 보호
  - 전자 상거래를 비슷한 모든 금융 서비스를 위한 소프트웨어 개발
- 2. 소프트웨어에 사용되는 데이터가 구조적이고 일관적인 경우
  - 프로젝트의 규모가 많은 서버를 필요로 하지 않고 일관된 데이터를 사용하는 경우
    - 다양한 데이터 유형과 높은 트래픽을 지원하도록 설계된 NoSQL DB를 사용할 이유가 없음

### NoSQL 기반의 비관계형 DB 사용 케이스
- 1. 데이터의 구조가 거의 또는 전혀 없는 대용량의 데이터를 저장하는 경우
  - 저장할 수 있는 데이터의 유형에 제한이 없음
    - 데이터의 새 유형 추가 가능
  - 개발에 정형화 되지 않은 많은 양의 데이터가 필요한 경우
- 2. 클라우드 컴퓨팅 및 저장공간을 최대한 활용하는 경우
  - 클라우드 기반 DB 저장소 구축 시, 저렴한 비용의 솔루션 제공 가능
  - DB의 확장성 중요 시, 별다른 코스트 없이 확장 가능한 NoSQL 사용
- 3. 빠르게 서비스를 구축하는 과정에서 데이터 구조를 자주 업데이트 하는 겨웅
  - 스키마를 미리 준비할 필요가 없기 때문에 빠르게 개발하는 과정에 매우 유리
    - 시장에 빠르게 프로토타입을 출시해야 하는 경우
    - 소프트웨어 버전별로 많은 다운타임 없이 데이터 구조를 자주 업데이트 하는 경우
      - 다운타임 : 데이터베이스 서버를 오프라인으로 전환하여 데이터 처리를 진행하는 작업 시간

## 데이터베이스 설계

### Schema 
- 스키마(schema)
  - 데이터베이스에서 데이터가 구성되는 방식
  - 서로 다른 엔티티 간의 관계에 대한 설명
  - 데이터베이스의 청사진
- 엔티티(Entity)
  - 고유한 정보의 단위
  - 테이블로 표시됨
    - 테이블에 저장된 모든 항목은 필드로 포함된다.

### 관계형 데이터베이스
- 구조화된 데이터는 하나의 테이블로 표현 가능
  - 사전 정의된 테이블을 relation 이라고 칭하기도 함
  - 테이블을 사용하는 관계형 DB를(Relational DB)라 지칭
- 데이터 : 각 항목에 저장되는 값
- 테이블 : 사전에 정의된 칼럼대로 작성된 데이터가 행으로 축적
- 칼럼 : 테이블의 한 열을 가리킴
- 레코드 : 테이블의 한 행에 저장된 데이터
- 키 : 테이블의 각 레코드를 구분할 수 있는 값
  - 각 레코드마다 고유한 값을 가짐
  - 기본키(Primary Key), 외래키(Foreign Key)
    - 기본키
      - 각 엔티티를 식별할 수 있는 대표키
      - 테이블에서 중복되지 않는 값
      - NULL일 수 없다
    - 외래키 
      - 다른 테이블의 기본키를 참조
      - 모든 필드는 참조하는 기본키와 동일한 도메인(값의 종류&번위)을 가짐
      - 모든 필드 값은 참조하는 기본키와 동일하거나 NULL 일 수 있음

### 관계 종류
- #### 1:1 관계
  - 하나의 레코드가 다른 테이블의 레코드 한 개와 연결된 경우
  - ![image](https://user-images.githubusercontent.com/102513932/194222897-5e01d494-b09c-45e0-8ed9-b84a8c1ca970.png)
  - User 테이블의 phone_id는 외래키(foreign key)로써, Phonebook 테이블의 phone_id와 연결됨
  - 각 전화번호가 단 한 명의 유저와 연결 , 각 유저가 단 한 개의 전화번호와 연결
  - 1:1 관계는 자주 사용하지 않음, 차라리 User 테이블에 phone_id 대신 phone_number을 저장하는게 효율적임
- #### 1:N 관계
  - 하나의 레코드가 서로 다른 여러 개의 레코드와 연결된 경우
  - ![image](https://user-images.githubusercontent.com/102513932/194223222-5a3d30b8-859d-4f27-9af3-b5c9259cdab1.png)
  - 한 명의 유저가 여러 전화번호를 가질 수 있음
  - 여러 명의 유저가 하나의 전화번호를 가질 수는 없음
  - User(부모) / Phonebook(자식)
    - 부모 테이블의 Primary Key를 자식 테이블에 Foreign Key로 집어 넣어 관계를 표현함
- N:N 관계
  - Join 테이블을 새로 생성해 관리
  - ![image](https://user-images.githubusercontent.com/102513932/194224413-fa40ec15-1d24-4033-b5b4-04ed280180ed.png)
    - 고객 한 명은 여러 개의 여행 상품 구매 가능
    - 여행 상품 하나는 여러 명의 고객 구매 가능
  - ![image](https://user-images.githubusercontent.com/102513932/194224479-1195db72-b0da-4d9e-a423-473ea86d8d6d.png)
    - customer_package 
      - 테이블에서는 고객 한 명이 여러 개의 여행 상품을 가질 수 있음
      - 여행 상품 하나가 여러 개의 고객을 가질 수 있음
      - customer_id와 package_id를 묶어주는 역할
      - 조인 테이블을 위한 기본키(cp_id)도 반드시 존재해야 함
- Self Referencing 관계
  - 테이블 내 관계
  - ![image](https://user-images.githubusercontent.com/102513932/194225486-d03643fb-670f-446e-9fa4-71d3864b4f0e.png)
  - 한 명의 유저는 한 명의 추천인을 가질 수 있음
  - 여러 명이 한 명의 유저를 추천인으로 등록할 수 있음
  - 같은 테이블 내에서 1:N 관계를 가질 수 있음 

## 데이터베이스 정규화
- Database Normalization
- 데이터베이스 정규화는 데이터베이스의 설계와 연관
  - 데이터베이스 설계가 결론적으로 데이터가 어떻게 저장될지 구조 저장
- ### Data redundancy
  - 데이터 중복
    - 데이터 중복은 실제 데이터의 동일한 복사본이나 부분적인 복사본을 뜻함
    - 데이터 복구 시 더 수월할 수 있으나, 문제점을 지니기도 함
      - 일관된 자료 처리의 어려움
      - 저장 공간 낭비
      - 데이터 효율성 감소
- ### Data integrity
  - 데이터 무결성
    - 데이터의 수명 주기 동안 정확성과 일관성을 유지하는 것
    - 입력된 데이터가 오염되지 않고 입력된 그대로 데이터를 사용할 수 있다는 뜻
    - 데이터 정규화는 데이터 무결성을 강화하기 위한 목적도 지님
- ### Anomaly
  - 데이터 이상 현상
    - #### 갱신 이상 (update anomaly)
      - 동일한 데이터가 여러 행에 걸쳐 있을 때 어느 데이터를 갱신해야 하는지에 대한 논리적 일관성 부재 시 발생
      - ![image](https://user-images.githubusercontent.com/102513932/194449125-52d12808-c724-420a-af21-fa33a6296404.png)
        - 중복된 Employee ID중 어떤 데이터를 갱신 해야 하는가?
    - #### 삽입 이상 (insertion anomaly)
      - 데이터 삽입을 못하는 경우
      - ![image](https://user-images.githubusercontent.com/102513932/194449222-cc265dd1-1fca-4c52-becd-3a8f973265fb.png)
        - Course Code가 NULL이 아닌 이상, 신규 학생은 데이터에 추가되지 못함
    - #### 삭제 이상 (deletion anomaly)
      - 데이터의 특정 부분을 지울 때 의도치 않게 다른 부분들도 함께 지워지는 현상