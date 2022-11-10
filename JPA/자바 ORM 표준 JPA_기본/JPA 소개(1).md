### 목차
- [SQL 중심적인 개발의 문제점](#sql-중심적인-개발의-문제점)
  - [객체 CRUD](#객체-crud)
  - [객체와 관계형 데이터베이스의 차이](#객체와-관계형-데이터베이스의-차이)
    - [1. 상속](#1-상속)
  - [연관관계](#연관관계)
  - [객체 그래프 탐색](#객체-그래프-탐색)
  - [비교하기](#비교하기)
- [JPA 소개](#jpa-소개)
  - [JPA 동작](#jpa-동작)
  - [JPA 소개](#jpa-소개-1)
  - [JPA 사용 당위성](#jpa-사용-당위성)
    - [생산성_ JPA와 CRUD](#생산성_-jpa와-crud)
    - [유지보수](#유지보수)
  - [JPA와 패러다임의 불일치 해결](#jpa와-패러다임의-불일치-해결)
    - [JPA와 상속](#jpa와-상속)
    - [JPA와 연관관계](#jpa와-연관관계)
    - [JPA 객체 그래프 탐색](#jpa-객체-그래프-탐색)
    - [JPA와 비교하기](#jpa와-비교하기)
  - [JPA의 성능 최적화 기능](#jpa의-성능-최적화-기능)
    - [1차 캐시와 동일성 보장](#1차-캐시와-동일성-보장)
    - [트랜잭션을 지원하는 쓰기 지연 - INSERT](#트랜잭션을-지원하는-쓰기-지연---insert)
    - [트랜잭션을 지원하는 쓰기 지연 - UPDATE](#트랜잭션을-지원하는-쓰기-지연---update)
    - [지연 로딩과 즉시 로딩](#지연-로딩과-즉시-로딩)
## SQL 중심적인 개발의 문제점
- 지금 시대는 객체를 관계형 DB를 통해 관리
  - 데이터베이스는 SQL만 알아들을 수 있으니, SQL 중심적인 개발이 됨
- 무한 반복, 지루한 코드
  - CRUD
    - INSERT INTO.., UPDATE.., SELECT.., DELETE.., 자바 객체를 SQL로.. , SQL을 자바 객체로..
    - 개발자는 SQL 매퍼가 아님!!
### 객체 CRUD
- 기존
  - ![image](https://user-images.githubusercontent.com/102513932/199677411-2ffe4712-4266-44dd-a4bc-c9a2e94c374e.png)
- 필드 추가시
  - ![image](https://user-images.githubusercontent.com/102513932/199677475-6e855ae2-750f-4d1e-92f2-a9df576931f9.png)
- 관계형 DB를 사용하면 SQL에 의존적인 개발을 피하기 어려움
  - 반복 작업이 많아지고 , 오류 가능성도 높아지는 단점 존재
### 객체와 관계형 데이터베이스의 차이
#### 1. 상속
- 관계형 DB에는 상속관계가 없음!
- ![image](https://user-images.githubusercontent.com/102513932/199679560-1d41befb-061e-49e1-b0a3-48f7fcd92747.png)
  - 슈퍼타입 서브관계가 그나마 상속관계와 유사함
- Album 저장
  - 1. 객체 분해
  - 2. INSERT INTO ITEM ...
  - 3. INSERT INTO ALBUM ...
  - 테이블이 두개로 쪼개져 있으니 INSERT를 두 번 진행
- Album 조회 
  - 1. 각각 테이블에 따른 조인 SQL 작성 ..
  - 2. 각각의 객체 생성 ..
  - 3. 상상만 해도 복잡
  - 4. 더 이상의 설명 생략
  - 5. 따라서 DB에 저장할 객체에는 상속 관계를 쓰지 않음
- 자바 컬렉션에 저장 시
  - `list.add(album);`
- 자바 컬렉션에서 조회 시
  - `Album album = list.get(albumId);`
  - 부모 타입으로 조회 후 다형성 활용
    - `Item item = list.get(albumId);`
- 자바 컬렉션에서 활용할 때는 간단한 작업이, 관계형 DB에 넣으려면 상당히 복잡한 작업으로 수행됨
### 연관관계
- 객체는 참조를 사용: meber.getTeam()
- 테이블은 외래 키를 사용: JOIN ON M.TEAM_ID = T.TEAM_ID
  - ![image](https://user-images.githubusercontent.com/102513932/199682417-0f73e37b-eab0-49e7-823c-6df98b1db4d5.png)
  - 객체는 Member -> team 참조 가능, team -> Member 참조 불가능
  - 테이블은 상호 조인 가능
    - 차이점 존재!
- 1. 객체를 테이블에 맞춰 모델링
```java
class Member{
    String id; //MEMBER_ID 컬럼 사용
    Long teamId; //TEAM_ID FK 컬럼 사용 
    String username; //USERNAME 컬럼 사용
}

class Team{
    Long id; //TEAM_ID PK 사용
    String name; //NAME 컬럼 사용
}
```
- 1.1 테이블에 맞춘 객체 저장
  - ![image](https://user-images.githubusercontent.com/102513932/199684793-ca4ea36b-fb5b-465e-87ca-1a7c3e57d2ac.png)
    - 객체지향스럽지 않은 것 같은 의문점이 생김
      - DB의 외래키는 객체 지향의 관점에서 봤을 때 참조인 것 같음, 따라서 Member가 외래키 값을 갖는게 아닌 team의 참조값을 갖게 하고 싶은 상황
- 2. 객체다운 모델링
```java
class Member{
    String id; //MEMBER_ID 컬럼 사용
    Team team; //참조로 연관관계를 맺는다 
    String username; //USERNAME 컬럼 사용

    Team getTeam(){
        return team;
    }
}

class Team{
    Long id; //TEAM_ID PK 사용
    String name; //NAME 컬럼 사용
}
```
- 2.2 객체 모델링 저장
  - ![image](https://user-images.githubusercontent.com/102513932/199687010-0f7b3a82-c37b-48e2-9172-e1a3cc97919e.png)
    - DB insert 시 외래키값이 없어서 넣어주지 못함
      - Team의 참조값만 있는 상황
    - `member.getTeam().getId();` 통해서 외래키 값을 가져와 넣어줌
    - 저장은 큰 문제가 없다!
- 2.3 객체 모델링 조회
  - ![image](https://user-images.githubusercontent.com/102513932/199688040-927a98d1-6b37-4d01-a5b7-d562b0e57b2b.png)
  - 굉장히 복잡함
- 객체 모델링을 자바 컬렉션에서 관리할 시
```java
// member를 넣고
list.add(member);

// id값으로 조회
Member member = list.get(memberId);
// team도 알아서 딸려옴
Team team = meber.getTeam();
```
- 이처럼 간단한 작업이, 관계형 DB에 넣으려면 상당히 복잡한 작업으로 수행 

### 객체 그래프 탐색
- 객체는 자유롭게 객체 그래프를 탐색할 수 있어야 함
  - ![image](https://user-images.githubusercontent.com/102513932/199688910-2a34cf8c-def5-466f-856b-b2f24fe5e1b8.png)
  - 레퍼런스가 있다는 가정하에, get등을 통해 자유롭게 탐색할 수 있어야 함
- 하지만 DB에서는 처음 실행하는 SQL에 따라 탐색 범위가 결정됨
  - ![image](https://user-images.githubusercontent.com/102513932/199689247-5544d674-0f50-44f5-83a8-bd96cfc5bb19.png)
    - SQL 실행시 MEMBER와 TEAM만 채웠기 때문에 Order값을 꺼낼 수 없음
  - 엔티티 신뢰 문제 발생
    - ![image](https://user-images.githubusercontent.com/102513932/199689483-2bc43947-6748-4b32-bbfd-c986e75602fa.png)
      - memberDAO: DB 연동 담당
      - memberDAO 안에서 어떤 쿼리가 동작했고, 데이터를 조립했는지 직접 확인하지 않는 이상 반환된 객체를 신뢰하고 사용할 수 없는 문제가 발생함
    - 그렇다고 모든 객체를 미리 로딩할 수는 없음
      - 대안으로 설정하는 것이 상황에 따라 회원 조회 메서드를 여러개 생성하는 것임
        - ![image](https://user-images.githubusercontent.com/102513932/199689888-709d55f2-04f8-41cb-8dfb-1590f3bb863a.png)
        - 딱 봐도 비효율적임
- 진정한 의미의 **계층 분할**이 어려워짐
  - 물리적으로는 계층이 DAO와 같이 나눠져있지만, 논리적으로는 결합되어 있음
### 비교하기
```java
String memberId = "100";
Member member1 = memberDAO.getMember(memberId);
Member member2 = memberDAO.getMember(memberId);
```
- member1과 member2 생성

```java
member1 == member2; //다르다.
```
```java
class MemberDAO{
    public member getMember(String memberId){
        String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
        ...
        //JDBC API, SQL 실행
        return new Member(...);
    }
}
```
- SQL에서는 당연히 다름
  - memberDAO 코드를 보면, new Member를 통해 반환하는 것을 확인할 수 있음

```java
String memberId = "100";
Member member1 = list.get(memberId);
Member member2 = list.get(memberId);

member1 == member2; //같다
```
- 자바에서는 참조값이 같기 때문에, 당연히 같음

- 자바에서 데이터를 다룰 때와, 관계형 DB에서 데이터를 다룰 때 여러 mismatch가 생김
- 객체답게 모델링 할수록 매핑 작업만 늘어남
  - 그래서 SQL에 객체를 맞춰버리게 됨
- 그렇다면, 객체를 자바 컬렉션에 저장 하듯이 DB에 저장할 수는 없을까?
  - 답은 **JPA**
## JPA 소개
- JPA
  - Java Persistence API
  - 자바 진영의 ORM 기술 표준
- ORM
  - Object-relational mapping
    - 객체 관계 매핑
  - 객체는 객체대로 설계
  - 관계형 DB는 관계형 DB대로 설계
  - ORM 프레임워크가 중간에서 매핑
  - 대중적 언어에는 대부분 ORM 기술 존재

### JPA 동작
- JPA는 애플리케이션과 JDBC 사이에서 동작
  - ![image](https://user-images.githubusercontent.com/102513932/199707286-f940f987-9a61-4bef-a0ed-05b01c8f3b7b.png)
    - JPA에게 명령을 하면 JPA가 JDBC API를 사용하여 DB와 동작
    - JDBC(Java Database Connectivity): 자바에서 데이터베이스에 접속할 수 있도록 하는 자바 API
- JPA 동작 - 저장
  - ![image](https://user-images.githubusercontent.com/102513932/199707677-e7d7fae8-25ec-4d1b-8ec4-2c88b29c8071.png)
    - JPA에게 멤버 객체를 넘김
    - JPA가 분석 -> 쿼리 생성 -> JDBC API 사용하여 통신
    - 쿼리를 대신 만들어줌! + 패러다임 불일치 해결
- JPA 동작 - 조회
  - ![image](https://user-images.githubusercontent.com/102513932/199707770-117f7bd8-c98d-4051-b655-e08ccc186571.png)
    - 쿼리 생성 -> 통신 -> 결과를 객체에 매핑
### JPA 소개
  - EJB_엔티티 빈(자바 표준) -> 하이버네이트(오픈 소스) -> JPA(자바 표준)
- JPA는 표준 명세
  - JPA는 인터페이스의 모음
  - JPA 2.1 표준 명세를 구현한 3가지 구현체
  - 하이버네이트, EclipseLink, DataNucleus
  - ![image](https://user-images.githubusercontent.com/102513932/199708892-2df63938-3eca-4a53-81d5-f9cf781f3325.png)
- JPA 버전
  - 1.0(JSR 220) 2006년: 초기 버전. 복합 키와 연관관계 기능이 부족
  - 2.0(JSR 317) 2009년: 대부분의 ORM 기능을 포함, JPA Criteria 추가
  - 2.1(JSR 338) 2013년: 스토어드 프로시저 접근, 컨버터(Converter), 엔티티 그래프 기능 추가
### JPA 사용 당위성
- SQL 중심적 개발 -> 객체 중심으로 개발
- 생산성
- 유지보수
- 패러다임 불일치 해결
- 성능
- 데이터 접근 추상화와 벤더 독립성
- 표준
#### 생산성_ JPA와 CRUD
- 저장: **jpa.persist**(member)
- 조회: Member member = **jpa.find**(memberId)
- 수정: **member.setName**("변경할 이름")
- 삭제: **jpa.remover**(member)

#### 유지보수
- 기존: 필드 변경 시 모든 SQL 수정
  - ![image](https://user-images.githubusercontent.com/102513932/199709809-9011990f-a034-47eb-bf42-bcce6ec12433.png)
- JPA: 필드만 추가, SQL은 JPA가 처리
  - ![image](https://user-images.githubusercontent.com/102513932/199709913-b69d4708-09f7-4f5a-99fa-4123ffa7f97d.png)

### JPA와 패러다임의 불일치 해결
#### JPA와 상속
- ![image](https://user-images.githubusercontent.com/102513932/199710078-c6af166d-c701-42f0-95b5-d63bf9fc37ce.png)
- 저장
  - ![image](https://user-images.githubusercontent.com/102513932/199710151-af4b29d3-d97c-44e0-8c12-8925db60cc9e.png)
    - 2개의 쿼리문을 각각 만들어줌
- 조회
  - ![image](https://user-images.githubusercontent.com/102513932/199710220-37d0f93f-52f2-4631-94c6-ce6140cde3ca.png)
    - 식별자가 알아서 JOIN해서 데이터를 가져오고, 패러다임을 일치 시켜줌
#### JPA와 연관관계
- JPA 사용 전과 비교해볼 것
  - ![image](https://user-images.githubusercontent.com/102513932/199710640-ab81c80d-299a-485d-ab64-15c683fa0d60.png)
#### JPA 객체 그래프 탐색
- JPA 사용 전과 비교해 볼 것
  - ![image](https://user-images.githubusercontent.com/102513932/199710683-d08a0b61-6423-488b-81b8-70714d5389db.png)
    - 마치 자바 Collection처럼 get을 이용해 데이터를 가져올 수 있음
- 신뢰할 수 있는 엔티티, 계층
- ![image](https://user-images.githubusercontent.com/102513932/199711019-1f4b4a10-d00a-4a0a-a4ce-023dc2edf0ef.png)
  - 원래 쿼리를 확인하기 전에는 자유롭게 호출할 수 없었음
  - JPA를 통해 멤버 객체를 가져올 때, 지연 로딩을 이용해 그래프를 자유롭게 탐색할 수 있음
    - 이제 엔티티를 신뢰할 수 있음! 
#### JPA와 비교하기
```java
String memberId = "100";
Member member1 = jpa.find(Member.class, memberId);
Member member2 = jpa.find(Member.class, memberId);

member1 == member2; //같다.
```
- **동일한 트랜잭션에서 조회한 엔티티는 같음을 보장**!

### JPA의 성능 최적화 기능
- JPA라는 중간 계층을 이용하면 버퍼링과 캐싱을 사용 가능함
  - JPA를 잘 이용하면 성능을 더 끌어올릴 수 있음!
1. 1차 캐시와 동일성(identity) 보장
2. 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
3. 지연 로딩(Lazy Loading)

#### 1차 캐시와 동일성 보장
1. 같은 트랜잭션 안에서는 같은 엔티티를 반환 - 약간의 조회 성능 향상
2. DB Isolation Level이 Read Commit이어도 애플리케이션에서 Repeatable Read 보장
  - 복잡한 내용이라 설명 생략, 크게 중요하진 않음
```java
String memberId = "100";
Member m1 = jpa.find(Member.class, memberId); //SQL
Member m2 = jpa.find(Member.class, memberId); //캐시

println(m1 == m2); //true.
```
- SQL 한 번만 실행

#### 트랜잭션을 지원하는 쓰기 지연 - INSERT
1. 트랜잭션을 커밋할 때까지 INSERT SQL을 모음
2. JDBC BATCH SQL 기능을 사용해서 한 번에 SQL 전송
```java
transaction.begin(); //[트랜잭션] 시작

em.persist(memberA);
em.persist(memberB);
em.persist(memberC);
// 여기까지 INSERT SQL을 데이터베이스에 보내지 않음
// 버퍼링(버퍼를 채우는 동작)을 통해 작동

// 커밋하는 순간 데이터베이스에 INSERT SQL을 모아서 보낸다.
transaction.commit(); //[트랜잭션] 커밋
```

#### 트랜잭션을 지원하는 쓰기 지연 - UPDATE
1. UPDATE, DELETE로 인한 로우(ROW)락 시간 최소화
2. 트랜잭션 커밋 시 UPDATE, DELETE SQL 실행하고, 바로 커밋
```java
transaction.begin(); // [트랜잭션] 시작

changeMember(memberA);
deleteMember(memberB);
비즈니스_로직_수행(); // 비즈니스 로직 수행 동안 DB 로우 락이 걸리지 않는다.

//커밋하는 순간 데이터베이스에 UPDATE, DELETE SQL을 보낸다.

transaction.commit(); // [트랜잭션] 커밋
```
- 부연 설명없이 그냥 넘어감

#### 지연 로딩과 즉시 로딩
- 지연 로딩: 객체가 실제 사용될 때 로딩
- 즉시 로딩: JOIN SQL로 한 번에 연관된 객체까지 미리 조회
- ![image](https://user-images.githubusercontent.com/102513932/199712831-74af163d-4f8b-4c7b-8e98-5f3490a2a262.png)
  - JPA는 옵션을 통해 로딩 방식을 지정할 수 있음
  - Member객체가 사용될 때 웬만하면 Team 객체가 같이 사용되면 즉시 로딩을 사용
  - Member 객체와 Team 객체가 따로 사용된다면 지연 로딩 사용
  - 처음에 지연 로딩으로 작성을 하고, 나중에 필요하다면 즉시 로딩으로 최적화를 할 수 있도록 하자


> **ORM은 객체와 RDB 두 기둥위에 있는 기술이다!** <br>
> 둘 다 잘해야 되지만, RDB에 대한 공부를 게을리하지 않도록 하자
