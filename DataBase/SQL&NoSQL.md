### 목차
- [SQL](#sql)
    - [SQL 종류](#sql-종류)
      - [Data Definition Language(DDL)](#data-definition-languageddl)
      - [Data Manipulation Language(DML)](#data-manipulation-languagedml)
      - [Data Control Language(DCL)](#data-control-languagedcl)
      - [Data Query Language(DQL)](#data-query-languagedql)
      - [Transaction Control Language(TCL)](#transaction-control-languagetcl)
  - [데이터베이스 필요성](#데이터베이스-필요성)
- [SQL VS NoSQL](#sql-vs-nosql)
  - [NoSQL](#nosql)
  - [관계형 데이터베이스](#관계형-데이터베이스)
  - [비관계형 데이터베이스](#비관계형-데이터베이스)
  - [데이터 저장](#데이터-저장)
  - [스키마(Schema)](#스키마schema)
  - [쿼리(Querying)](#쿼리querying)
  - [확장성(Scalability)](#확장성scalability)
  - [SQL 기반 DB 사용 케이스](#sql-기반-db-사용-케이스)
  - [NoSQL 기반의 비관계형 DB 사용 케이스](#nosql-기반의-비관계형-db-사용-케이스)
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
#### SQL 종류
  - SQL에서도 역할에 다라 문법이 다양하게 존재
##### Data Definition Language(DDL)
  - 데이터 정의 시 사용
  - 테이블을 만들 때 사용하는 `CREATE`, 제거 시 사용되는 `DROP`
##### Data Manipulation Language(DML)
  - 데이터 변경 시 사용
  - `INSERT`, `DELETE`, `UPDATE`
##### Data Control Language(DCL)
  - DB 접근 권한 관련 문법
  - 권한을 주는 `GRANT`, 권한을 가져가는 `REVOKE`
##### Data Query Language(DQL)
  - 정해진 스키마 내에서 쿼리할 수 있는 언어
  - `SELECT`
  - DQL을 DML의 일부분으로 취급하기도 함
##### Transaction Control Language(TCL)
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

## SQL VS NoSQL
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
  
### 관계형 데이터베이스
  - SQL 기반
  - 테이블의 구조와 데이터 타입 사전 정의
    - 정의된 내용에 알맞은 형태의 데이터만 삽입 가능
  - 행과 열로 구성된 테이블에 데이터 저장
    - 열은 하나의 속성에 대한 정보 저장
    - 행은 각 열의 데이터 형식에 맞는 데이터 저장
  - SQL을 활용해 원하는 정보 쿼리 가능
    - 스키마가 뚜렷하게 보임
    - 관계형 DB에서는 테이블 간의 관계 직관적 파악 가능
### 비관계형 데이터베이스
  - NoSQL로 데이터 다룸
  - 데이터가 고정되지 않은 데이터베이스
  - 스키마가 반드시 없는 것은 아님
    - 데이터를 읽어올 때 스키마에 따라 데이터 읽어옴
    - schema on read
### 데이터 저장
  - NoSQL은 Key-value, document, wide-column, graph등의 방식으로 데이터 저장
  - 관계형 데이터베이스는 SQL을 이용해 데이터를 테이블에 저장
    - 미리 작성한 스키마를 기반으로 정해진 형식에 맞게 데이터 저장
### 스키마(Schema)
  - SQL 사용시, 고정된 형식의 스키마 필요
    - 처리하는 데이터 속성별로 열에 대한 정보가 미리 정해져야함
    - 스키마는 추후 변경할 수 있지만, 데이터베이스 전체를 수정하거나 오프라인으로 전환해야함
  - NoSQL은 관계형 DB보다 동적으로 스키마 형태 관리 가능
    - 행 추가시 즉시 새로운 열 추가 가능
    - 개별 속성에 대해 모든 열에 대한 데이터를 반드시 입력하지 않아도 됨
### 쿼리(Querying)
  - 관계형 DB는 테이블의 형식과 관계에 맞춰 데이터 요청
    - 정보 요청 시 SQL과 같이 구조화된 쿼리 언어 사용
  - 비관계형 DB의 쿼리는 데이터 그룹 자체를 조회
    - 구조화 되지 않은 쿼리 언어로도 데이터 요청 가능
      - UnQL(UnStructed Query Language)
### 확장성(Scalability)
  - 관계형 DB는 수직적 확장
    - 높은 메모리, CPU 사용
      - 하드웨어 성능을 많이 이용, 비용이 많이 듬
    - 여러 서버에 걸쳐 관계 정의 가능하지만, 복잡하고 시간이 많이 소모
  - NoSQL 구성 DB는 수평적 확장
    - 보다 값싼 서버 증설, 클라우드 서비스를 이용하는 확장
    - 많은 트래픽을 보다 편리하게 처리가능
    - 저렴한 범용 하드웨어나 클라우드 기반 인스턴스에 호스팅 가능, 상대적으로 비용 저렴

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
