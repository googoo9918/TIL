### 목차
- [Hello JPA - 프로젝트 생성](#hello-jpa---프로젝트-생성)
- [Hello JPA - 애플리케이션 개발](#hello-jpa---애플리케이션-개발)
  - [실습\_JPA 동작 확인](#실습_jpa-동작-확인)
  - [회원 등록 실습](#회원-등록-실습)
  - [회원 수정, 조회, 삭제 실습](#회원-수정-조회-삭제-실습)
  - [주의](#주의)
  - [JPQL 소개](#jpql-소개)
## Hello JPA - 프로젝트 생성
- H2 Database 설치
  - 최고의 실습용 DB
  - 용량이 적음
  - 웹용 쿼리툴 제공
  - MySQL 데이터베이스 시물레이션 기능
  - 시퀀스, AUTO INCREMENT 기능 지원
- 실습 Gradle이 아닌 Maven으로 진행
  - ![image](https://user-images.githubusercontent.com/102513932/199727800-a2cc62f9-d8f5-4894-ac1b-a59c99f402ea.png)
  - 왜 Gradle을 안쓰는지는 잘..
- 메이븐 설정
  - groupId: jpa-basic
  - artifactId: ex1-hello-jpa
    - build시 이름
  - version: 1.0.0
- pom.xml 라이브러리 추가
- JPA 설정 파일 - persistence.xml 추가
```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
    <persistence-unit name="hello">
        <properties>
            <!-- 필수 속성 -->
            <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
            <property name="javax.persistence.jdbc.user" value="sa"/>
            <property name="javax.persistence.jdbc.password" value=""/>
            <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>

            <!-- 옵션 -->
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
            <property name="hibernate.use_sql_comments" value="true"/>
            <!--<property name="hibernate.hbm2ddl.auto" value="create" />-->
        </properties>
    </persistence-unit>
</persistence>
```
- `<persistence-unit name="hello">`
  - JPA를 쓸 때 이름을 뭘 쓸 것인지
  - 데이터베이스 하나당 한 개 작성
- 필수 속성
  - DB 접근 정보
- `<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>`
  - 데이터베이스 방언
    - JPA는 특정 DB에 종속 X
    - 각각의 DB가 제공하는 SQL 문법과 함수는 조금씩 다름
      - ex)
        - 가변 문자: MySQL은 VARCHAR, Oracle은 VARCHAR2
        - 문자열 자르는 함수: SQL 표준은 SUBSTRING(), Oracle은 SUBSTR()
        - 페이징: MySQL은 LIMIT, Oracle은 ROWNUM
    - 방언: SQL 표준을 지키지 않는 특정 데이터베이스만의 고유한 기능 
      - 따라서 H2Dialect 라고 지정해주면 JPA가 알아서 번역해서 사용 가능
  - ![image](https://user-images.githubusercontent.com/102513932/199732238-7b53f5c4-7595-4bcc-a28f-193dc0b4ae0e.png)
    - 하이버네이트는 40가지 이상의 데이터베이스 방언 지원
    - ex)
      - MySQL: org.hibernate.dialect.MySQL5InnoDBDialect
      - Oracle 10g: org.hibernate.dialect.Oracle10gDialect
## Hello JPA - 애플리케이션 개발
- JPA 구동 방식
  - ![image](https://user-images.githubusercontent.com/102513932/199733785-a325b772-7cf9-413c-ba86-e905c6d79438.png)
  - Persistence에서 시작
    - 1. 설정 정보 조회
    - 2. EntityManagerFactory 클래스 생성
    - 3. 필요할 때 마다 EntityManager 생성해서 사용
### 실습_JPA 동작 확인
- JPA Main 클래스 생성
```java
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // pom.xml에서 지정한 persistence-unit name 삽입
        // EntityManagerFactory를 만들면 DB와 연결 가능

        // 각종 쿼리를 사용하기 위해 entityManagerFactory에서 entityManger 생성 (트랜잭션 단위로 생성하자)
        EntityManager em = emf.createEntityManager();
        // em 생성과 종료 사이에 code 작성

        em.close();

        // 실제 애플리케이션이 완전히 끝나면 닫아줌
        emf.close();
    }
}
```
- 객체와 테이블을 생성하고 매핑
  - 주의점 : H2 실행시 JDBC URL을 persistence.xml 설정파일의 URL과 맞춰줄 것
```sql
create table Member ( 
    id bigint not null, 
    name varchar(255), 
    primary key (id) 
);
```
- H2 Database에 table 생성
```java
@Entity //jpa 사용 인식
public class Member {

    @Id //pk 매핑
    private Long id;
    private String name;

    //Getter, Setter 생략
}
```
  - Member class 생성
    - `@Entity` : JPA가 관리할 객체 
    - `@Id` : 데이터베이스 PK와 매핑

### 회원 등록 실습
```java
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // pom.xml에서 지정한 persistence-unit name 삽입
        // EntityManagerFactory를 만들면 DB와 연결 가능

        // 각종 쿼리를 사용하기 위해 entityManagerFactory에서 entityManger 생성 (트랜잭션 단위로 생성)
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // em 생성과 종료 사이에 code 작성
        Member member = new Member();

        member.setId(1L);
        member.setName("HelloA");

        em.persist(member); //member 저장

        tx.commit();

        em.close();

        // 실제 애플리케이션이 완전히 끝나면 닫아줌
        emf.close();
    }
}
```
- 위 코드와 비교해보자
  - JPA의 모든 데이터 변화는 Transaction 안에서 일어나야함!!
    - `EntityTransaction tx = em.getTransaction();`를 통해 트랜잭션 생성
      - `tx.begin();` : 트랜잭션 시작
      - `tx.commit();` : 변경사항 커밋
  - member 객체 쿼리문 작성

```
Hibernate: 
    /* insert hellojpa.Member
        */ insert 
        into
            Member
            (name, id) 
        values
            (?, ?)
```
- 코드 실행 시 출력
  - pesistence.xml 설정 파일 
    - `show_sql` : 쿼리문 출력
    - `format_sql` : formatting해서 출력
    - `use_sql_comments` : `inser hellojpa.Member`와 같이 쿼리문이 왜 나왔는지 알려줌
- H2 DB 확인 시 저장 확인 가능
- 추가적인 매핑 ex)
  - `@Table(name = "USER")`
    - 테이블 이름 설정 가능
  - `@Column(name = "username")`
    - 컬럼 이름 설정 가능
```java
try {
            // em 생성과 종료 사이에 code 작성
            Member member = new Member();

            member.setId(1L);
            member.setName("HelloA");

            em.persist(member); //member 저장

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
```
- 코드 수정
  - DB 동작 중 오류가 발생할 수 있으니 위와 같이 오류 처리를 해주는게 맞음
  - 사실 스프링이 다 해줄거라 상관은 없지만.. 알아는 두자

### 회원 수정, 조회, 삭제 실습
```java
try{
    Member findMember = em.find(Member.class, 1L); //회원 조회
    findMember.setName("HelloJPA"); //회원 수정
    // 수정하고 저장하지 않아도 됨!
    // 자바 컬렉션에서 수정한다고 새로 저장하지 않자너
    em.remove(findMember); //회원 삭제
}
```
- JPA가 관리하는 객체(Entity)는 커밋하는 시점에 데이터가 변경되었는지 체크함, 변경이 되었으면 UPDATE 쿼리를 만들어서 전송함
  - 수정하고 새로 저장하지 않아도 됨!

### 주의
- 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
- 엔티티 매니저는 쓰레드간 공유X (사용하고 버려야 함)
  - 엔티티 매니저는 고객의 요청마다 생성
- **JPA의 모든 데이터 변경은 트랜잭션 안에서 실행**


### JPQL 소개
- JPA를 사용하면 엔티티 객체를 중심으로 개발함
  - 문제는 검색 쿼리
  - 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색함
  - 모든 DB 데이터를 객체로 변환해 검색하는 것은 불가능함
    - 애플리케이션이 필요한 데이터만 DB에서 불러오려면, 결국 검색 조건이 포함된 SQL이 필요함
  - 이때 JPQL을 이용, 해당 문제를 해결할 수 있음
  - 즉, JPQL은 객체와 RDB 사이의 괴리를 좁힐 수 있는 언어임!
- JPQL은 SQL을 추상화한 객체 지향 쿼리 언어
  - SQL 문법과 유사함, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원
  - JPQL은 엔티티 객체를 대상으로 쿼리
  - SQL은 데이터베이스 테이블을 대상으로 쿼리
- 가장 단순한 조회 방법
  - `EntityManager.find()`
- 나이가 17살 이상인 회원을 모두 검색하고 싶다면?
  - JPQL 사용!
  - `List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();`
    - 여기서 m은 Entity Member를 가리키게 됨
- `List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(5).setMaxResults(8).getResultList();`
  - 페이징 시 위와 같은 코드를 작성하게 되는데, 설정 파일에서 dialect만 변경해 준다면 각 DB에 맞게 번역해줌