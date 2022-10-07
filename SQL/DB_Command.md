### 목차
- [데이터베이스 관련 명령어](#데이터베이스-관련-명령어)
  - [데이터베이스 생성](#데이터베이스-생성)
  - [데이터베이스 삭제](#데이터베이스-삭제)
  - [데이터베이스 사용](#데이터베이스-사용)
  - [테이블 생성](#테이블-생성)
  - [테이블 삭제](#테이블-삭제)
  - [테이블 내용 삭제](#테이블-내용-삭제)
  - [테이블 수정하기](#테이블-수정하기)
  - [테이블 정보 확인](#테이블-정보-확인)
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
