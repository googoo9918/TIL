### 목차
- [데이터베이스 관련 명령어](#데이터베이스-관련-명령어)
  - [데이터베이스 생성(CREATE)](#데이터베이스-생성create)
  - [데이터베이스 삭제(DROP)](#데이터베이스-삭제drop)
  - [데이터베이스 사용(USE)](#데이터베이스-사용use)
  - [테이블 생성(CREATE)](#테이블-생성create)
  - [테이블 삭제(DROP)](#테이블-삭제drop)
  - [테이블 내용 삭제(TRUNCATE)](#테이블-내용-삭제truncate)
  - [테이블 수정하기(ALTER)](#테이블-수정하기alter)
  - [테이블 구조 확인(DESCRIBE)](#테이블-구조-확인describe)
  - [다양한 정보 확인(SHOW)](#다양한-정보-확인show)
- [SQL 관련 명령어](#sql-관련-명령어)
  - [SELECT](#select)
  - [INSERT](#insert)
  - [UPDATE](#update)
  - [DELETE](#delete)
  - [FROM](#from)
  - [WHERE](#where)
    - [AND, OR, IN](#and-or-in)
    - [BETWEEN](#between)
    - [>, <, <=, >=](#---)
    - [LIKE (with Wildcard)](#like-with-wildcard)
    - [NULL, NOT NULL](#null-not-null)
  - [CASE](#case)
  - [Aliases](#aliases)
  - [COUNT](#count)
  - [GROUP BY](#group-by)
    - [HAVING](#having)
  - [ORDER BY](#order-by)
  - [LIMIT](#limit)
  - [DISTINCT](#distinct)
  - [SUBQUERY](#subquery)
    - [IN, NOT IN](#in-not-in)
    - [EXISTS, NOT EXISTS](#exists-not-exists)
    - [FROM](#from-1)
  - [INNER JOIN](#inner-join)
  - [OUTER JOIN](#outer-join)
  - [JOIN별 비교](#join별-비교)
  - [여러 쿼리문 한 번에 사용하기](#여러-쿼리문-한-번에-사용하기)
  - [SELECT 실행 순서](#select-실행-순서)
## 데이터베이스 관련 명령어

### 데이터베이스 생성(CREATE)
```SQL
CREATE DATABASE 데이터베이스_이름;
```
### 데이터베이스 삭제(DROP)
```SQL
DROP DATABASE 데이터베이스_이름;
```
### 데이터베이스 사용(USE)
- DB를 이용해 테이블을 만들거나 수정, 삭제하는 등의 작업 시 사용 명령을 먼저 전달해야 한다.

```SQL
USE 데이터베이스_이름;
```

### 테이블 생성(CREATE)
- ```USE```를 이용해 데이터베이스 선택 시, 테이블 생성 가능
- 테이블은 필드와 함께 만들어야 함.
- EX) `user` 테이블 생성, 테이블 예시
- ![image](https://user-images.githubusercontent.com/102513932/193962914-1f61e4d0-c61d-41c0-b97b-f6ee8ff7f9e3.png)

```SQL
CREATE TABLE user(
    id int not NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) not NULL DEFAULT NULL,
    email varchar(255)
);
```
- SQL 콘솔에서 `Enter`키를 이용해 여러 줄의 코드 입력 가능

### 테이블 삭제(DROP)
```SQL
DROP TABLE 테이블_이름;
```

### 테이블 내용 삭제(TRUNCATE)
```SQL
TRUNCATE TABLE 테이블_이름;
-- 테이블의 모든 데이터가 삭제되지만 테이블의 구조는 삭제되지 않음
```

### 테이블 수정하기(ALTER)
```sql
ALTER TABLE 테이블_이름 DROP COLUMN 특성이름_1
-- column 삭제

ALTER TABLE 테이블_이름 ADD 특성이름_1 데이터형식 NULL
-- column 추가
-- 테이블에 특성이름_1 컬럼 추가, 형식 지정 및 NULL값 허용

ALTER TABLE 테이블_이름 ALTER COLUMN 특성이름_1 데이터형식 NOT NULL
-- 테이블에 특성이름_1의 데이터형식 변경 및 NULL값 허용X
```

### 테이블 구조 확인(DESCRIBE)

```SQL
DESCRIBE user;
```
- user 테이블의 구조 확인

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

### 다양한 정보 확인(SHOW)
```SQL
SHOW DATABASES; -- 데이터베이스 목록 확인
SHOW TABLES; -- 현재 데이터베이스에 테이블 목록 확인
SHOW TABLES FROM 데이터베이스; --특정 데이터베이스에 테이블 목록 확인
SHOW INDEX FROM 테이블; --특정 테이블 인덱스 확인
SHOW COLUMNS FROM 테이블; -- 특정 테이블 칼럼 확인
SHOW TABLE STATUS; --현재 데이터베이스 모든 테이블 정보 확인
SHOW TABLE STATUS FROM 데이터베이스; --특정 데이터베이스 모든 테이블 정보 확인
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
WHERE 특성_1 = '특정 값'
-- 특정 값과 동일한 데이터 찾기

SELECT 특성_1, 특성_2
FROM 테이블_이름
WHERE 특성_2 <> = '특정 값'
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
WHERE 특성_1 > '특정 값'

SELECT 특성_1, 특성_2
FROM 테이블_이름
WHERE 특성_1 <= '특정 값'
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
