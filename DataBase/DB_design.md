### 목차
- [데이터베이스 설계](#데이터베이스-설계)
  - [Schema](#schema)
  - [관계형 데이터베이스](#관계형-데이터베이스)
  - [관계 종류](#관계-종류)
    - [1:1 관계](#11-관계)
    - [1:N 관계](#1n-관계)
    - [N:N 관계](#nn-관계)
    - [Self Referencing 관계](#self-referencing-관계)
  - [Desiging Schema 예시](#desiging-schema-예시)
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
#### 1:1 관계
  - 하나의 레코드가 다른 테이블의 레코드 한 개와 연결된 경우
  - ![image](https://user-images.githubusercontent.com/102513932/194222897-5e01d494-b09c-45e0-8ed9-b84a8c1ca970.png)
  - User 테이블의 phone_id는 외래키(foreign key)로써, Phonebook 테이블의 phone_id와 연결됨
  - 각 전화번호가 단 한 명의 유저와 연결 , 각 유저가 단 한 개의 전화번호와 연결
  - 1:1 관계는 자주 사용하지 않음, 차라리 User 테이블에 phone_id 대신 phone_number을 저장하는게 효율적임
#### 1:N 관계
  - 하나의 레코드가 서로 다른 여러 개의 레코드와 연결된 경우
  - ![image](https://user-images.githubusercontent.com/102513932/194223222-5a3d30b8-859d-4f27-9af3-b5c9259cdab1.png)
  - 한 명의 유저가 여러 전화번호를 가질 수 있음
  - 여러 명의 유저가 하나의 전화번호를 가질 수는 없음
  - User(부모) / Phonebook(자식)
    - 부모 테이블의 Primary Key를 자식 테이블에 Foreign Key로 집어 넣어 관계를 표현함
#### N:N 관계
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
#### Self Referencing 관계
  - 테이블 내 관계
  - ![image](https://user-images.githubusercontent.com/102513932/194225486-d03643fb-670f-446e-9fa4-71d3864b4f0e.png)
  - 한 명의 유저는 한 명의 추천인을 가질 수 있음
  - 여러 명이 한 명의 유저를 추천인으로 등록할 수 있음
  - 같은 테이블 내에서 1:N 관계를 가질 수 있음 

### Desiging Schema 예시
![image](https://user-images.githubusercontent.com/102513932/194537158-0a7c1dd7-e115-4233-9d14-1f01711524d5.png)
![image](https://user-images.githubusercontent.com/102513932/194537313-bbf56e72-fd60-4c0d-9e76-70a7f02a01d6.png)
![image](https://user-images.githubusercontent.com/102513932/194537364-12b21bc6-60a3-404c-932b-34f4fb86938c.png)
![image](https://user-images.githubusercontent.com/102513932/194537405-7f7c3038-1fe3-42ee-ae3d-9cfb2940412c.png)
```sql
CREATE TABLE user (
  id int not NULL PRIMARY KEY auto_increment default NULL,
  name varchar(255) not NULL default NULL,
  email varchar(255) not NULL default NULL,
  roleId int,
);

CREATE TABLE content (
  id int not NULL PRIMARY KEY AUTO_INCREMENT,
  title varchar(255) not NULL DEFAULT NULL,
  body varchar(255) not NULL DEFAULT NULL,
  created_at timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
  userId int DEFAULT NULL,
);

CREATE TABLE role (
  id int not NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(255) not NULL
);

CREATE TABLE category (
  id int not NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(255) not NULL
);

CREATE TABLE content_category (
  id int not NULL PRIMARY KEY AUTO_INCREMENT,
  contentId int not NULL,
  categoryId int not NULL
);

ALTER TABLE content ADD FOREIGN KEY (userId) REFERENCES user (id);
ALTER TABLE user ADD FOREIGN KEY (roleId) REFERENCES role (id);
ALTER TABLE content_category ADD FOREIGN KEY (contentId) REFERENCES content (id);
ALTER TABLE content_category ADD FOREIGN KEY (categoryId) REFERENCES category (id);
```