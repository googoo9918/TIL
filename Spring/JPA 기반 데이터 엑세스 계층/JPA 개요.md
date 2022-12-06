### 목차
- [JPA란?](#jpa란)
  - [Hibernate ORM](#hibernate-orm)
- [데이터 액세스 계층에서 JPA 위치](#데이터-액세스-계층에서-jpa-위치)
- [JPA에서의 P의 의미](#jpa에서의-p의-의미)
  - [영속성 컨텍스트(Persistence Context)](#영속성-컨텍스트persistence-context)
  - [JPA API로 영속성 컨텍스트 이해](#jpa-api로-영속성-컨텍스트-이해)
    - [사전 준비](#사전-준비)
    - [영속성 컨텍스트에 엔티티 저장](#영속성-컨텍스트에-엔티티-저장)
    - [영속성 컨텍스트와 테이블에 엔티티 저장](#영속성-컨텍스트와-테이블에-엔티티-저장)
    - [쓰기 지연을 통한 영속성 컨텍스트와 테이블에 엔티티 일괄 저장](#쓰기-지연을-통한-영속성-컨텍스트와-테이블에-엔티티-일괄-저장)
    - [영속성 컨텍스트와 테이블에 엔티티 업데이트](#영속성-컨텍스트와-테이블에-엔티티-업데이트)
    - [영속성 컨텍스트와 테이블의 엔티티 삭제](#영속성-컨텍스트와-테이블의-엔티티-삭제)
## JPA란?
- JPA(Java Persistence API)는 Java 진영에서 사용하는 ORM(Object-Relational Mapping) 기술의 표준 사양
  - 표준 사양이라는 것은, Java의 인터페이스로 사양이 정의되어 있기 때문에 JPA라는 표준 사양을 구현한 구현체는 따로 있다는 것을 의미함
### Hibernate ORM
- JPA 표준 사양을 구현한 구현체는 Hirbernate ORM, EclipseLink, DataNuclues 등이 있음
  - 학습할 구현체는 Hibernate ORM 임

## 데이터 액세스 계층에서 JPA 위치
- ![image](https://user-images.githubusercontent.com/102513932/205662986-c1144ada-be77-4897-b614-07511a4a8b8e.png)
  - 데이터 엑세스 계층에서 JPA는 데이터 엑세스 계층 상단에 위치함
  - 데이터 저장, 조회 등의 작업은 JPA를 거쳐 JPA의 구현체인 Hibernate ORM을 통해 이뤄짐
  - Hibernate ORM은 내부적으로 JDBC API를 이용해 DB에 접근

## JPA에서의 P의 의미
- Persistence : 영속성, 지속성

### 영속성 컨텍스트(Persistence Context)
- ORM은 객체와 DB 테이블의 매핑을 통해 엔티티 클래스 객체 안에 포함된 정보를 테이블에 저장함
- JPA에서는 테이블과 매핑되는 엔티티 객체 정보를 **영속성 컨텍스트**에 보관해 애플리케이션 내에서 오래 지속 되도록 함
  - 보관된 엔티티 정보는 DB 테이블에 데이터를 저장, 수정, 조회, 삭제하는데 사용
- ![image](https://user-images.githubusercontent.com/102513932/205665474-9f3d4dcb-912a-417e-af64-0c23b424039e.png)
  - 엔티티 정보를 영속성 컨텍스트에 저장하는 API 사용 시, 영속성 컨텍스트의 1차 캐시에 엔티티 정보 저장됨

### JPA API로 영속성 컨텍스트 이해
#### 사전 준비
- build.gradle 설정
```java
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa' // (1)
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```
- JPA 설정(application.yml)
```yml
spring:
  h2:
    console:
      enabled: true
      path: /h2     
  datasource:
    url: jdbc:h2:mem:test
  jpa:
    hibernate:
      ddl-auto: create  # (1) 스키마 자동 생성
    show-sql: true      # (2) SQL 쿼리 출력
```
  - (1)을 통해 JPA가 자동으로 DB에 테이블을 생성
  - (2) 설정 시, JPA API에서 실행되는 SQL 쿼리를 로그로 출력
- 샘플 코드 실행을 위한 Configuration 클래스 생성
```java
// (1)
@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

		// (2)
    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            // (3) 이 곳에 학습할 코드를 타이핑합니다.
        };
    }
}
```
  - (1)추가를 통해 (2)와 같이 @Bean 애너테이션 메서드에서 리턴하는 객체를 Spring Bean으로 추가
  - (3)과 같이 CommandLineRunner 객체를 람다 표현식 정의 시
    - 애플리케이션 부트스트랩 과정 완료 후에 람다 표현식에 정의한 코드를 실행

#### 영속성 컨텍스트에 엔티티 저장
```java
@Getter
@Setter
@NoArgsConstructor
@Entity  // (1)
public class Member {
    @Id  // (2)
    @GeneratedValue  // (3)
    private Long memberId;

    private String email;

    public Member(String email) {
        this.email = email;
    }
}
```
  - `@Entity`와 `@Id`를 통해 엔티티 클래스로 인식
  - (3)은 식별자를 생성해주는 전략을 지정, 추후 설명
```java
@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory){ // (1)
        this.em = emFactory.createEntityManager();  // (2)
        this.tx = em.getTransaction();

        return args -> {
            example01();
        };
    }

    private void example01(){
        Member member = new Member("hgd@gmail.com");

        //(3)
        em.persist(member);

        Member resultMember = em.find(Member.class, 1L);
        System.out.println("Id: " + resultMember.getMemberId() + ", email: " + resultMember.getEmail());
    }
}
```
- Member 엔티티 클래스의 객체를 JPA의 영속성 컨텍스트에 저장하는 예제 코드
  - JPA의 영속성 컨텍스트는 `EntityManager` 클래스에 의해 관리됨, (1)과 같이 `EntityManagerFactory` 객체를 Spring으로부터 DI 받을 수 있음
  - (2)와 같이 `EntityManagerFactory`의 `createEntityManager()` 메서드를 이용해 `EntityManager` 클래스의 객체를 얻을 수 있음
    - 객체를 통해 JAP 메서드 사용 가능
  - (3)과 같이 `persist(member)` 메서드 호출 시, 영속성 컨텍스트에 member 객체의 정보들이 저장됨
  - (4)에서는 member 객체가 잘 저장되었는지 `find(Member.calss, 1L)` 메서드로 조회함
    - find() 메서드의 파라미터
      - 첫 번째 파라미터는 **조회 할 엔티티 클래스의 타입**
      - 두 번째 파라미터는 **조회 할 엔티티 클래스의 식별자 값**
- ![image](https://user-images.githubusercontent.com/102513932/205674643-7e1b23d9-5f81-4840-b9f9-33947fc11859.png)
  - 1차 캐시에 `member` 객체 저장, `member` 객체는 쓰기 지연 SQL 저장소에 INSERT 쿼리 형태로 등록됨
    - 단, 실제 테이블에 회원 정보를 저장하지는 않음

#### 영속성 컨텍스트와 테이블에 엔티티 저장
- 위 member 정보를 실제 테이블에 저장
```java
@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory){
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            example02();
        };
    }

    private void example02(){
        //(2)
        tx.begin();
        Member member = new Member("hgd@gmail.com");

        //(3)
        em.persist(member);
        
        //(4)
        tx.commit();
        
        //(5)
        Member resultMember1 = em.find(Member.class, 1L);
        
        System.out.println("Id: " + resultMember1.getMemberId() + ", email: " + resultMember1.getEmail());
        
        //(6)
        Member resultMember2 = em.find(Member.class, 2L);
        
        //(7)
        System.out.println(resultMember2 ==null);
    }
}
```
- (1)에서는 `EntityManger`를 통해 `Transaction` 객체를 얻음
  - JPA에서는 `Transaction` 객체를 기준으로 DB 테이블에 데이터를 저장
- Transaction을 시작하기 위해 `tx.begin()` 메서드 호출
- (3)에서는 member 객체를 영속성 컨텍슽트에 저장
- (4)와 같이 `tx.commit()`을 호출하는 시점에서 영속성 컨텍스트에 저장되어 있는 객체를 DB 테이블에 저장
- (5)에서 `em.find(Member.class, 1L)`을 호출하면 (3)에서 영속성 컨텍스트에 저장한 `member` 객체를 1차 캐시에서 조회
  - **테이블에 SELECT 쿼리를 전송하지 않고, 1차 캐시에서 객체를 가져옴**
- (6)에서 식별자 값이 2L인 member 객체를 조회
  - 식별자 값이 2L인 member 객체는 존재하지 않기 때문에 (7)은 true
  - (6)에서는 영속성 컨텍스트에 식별자 값이 2L인 객체가 존재하지 않기 때문에 테이블에 직접 SELECT 쿼리를 전송함
- ![image](https://user-images.githubusercontent.com/102513932/205689255-2e355b83-97bb-4635-8fd1-ac6f618ec01c.png)
  - `tx.commit()`을 했기 때문에 `member`에 대한 INSERT 쿼리는 실행됨
    - 쓰기 지연 SQL 저장소에서 사라짐

#### 쓰기 지연을 통한 영속성 컨텍스트와 테이블에 엔티티 일괄 저장
```java
@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
                example03();
        };
    }

    private void example03() {
        tx.begin();
            
        Member member1 = new Member("hgd1@gmail.com");
        Member member2 = new Member("hgd2@gmail.com");

        em.persist(member1);  // (1)
        em.persist(member2);  // (2)

				
        tx.commit(); // (3)
     }
}
// 쓰기 지연을 통한 Member 객체 저장 예제
```
- ![image](https://user-images.githubusercontent.com/102513932/205689918-9f7e3638-5173-4453-b583-fc7b4c4b4573.png)
  - 위 코드에 tx.commit()이 실행되기 전 영속성 컨텍스트 상태
  - `tx.commit()`을 하기 전까지는 INSERT 쿼리가 실행되지 않음
- ![image](https://user-images.githubusercontent.com/102513932/205690436-cb1df47a-f119-4ff5-be1e-acee38d39c47.png)
  - `tx.commit()`이 실행된 후에 영속성 컨텍스트 상태 표현
    - 쓰기 지연 SQL 저장소에 등록된 INSERT 쿼리가 모두 실행되고 실행된 쿼리는 제거됨
      - 테이블에 데이터 저장

#### 영속성 컨텍스트와 테이블에 엔티티 업데이트
```java
private void example04() {
       tx.begin();
       em.persist(new Member("hgd1@gmail.com"));    // (1)
       tx.commit();    // (2)


       tx.begin();
       Member member1 = em.find(Member.class, 1L);  // (3)
       member1.setEmail("hgd1@yahoo.co.kr");       // (4)
       tx.commit();   // (5)
    }
```
- (3)에서도 member 객체를 1차 캐시에서 조회함
  - 테이블에서 조회하는 것이 아님 주의
- (4)에서 setter 메서드로 이메일 정보 변경
  - em.update() 같은 JPA API가 있을 것 같지만, (4)와 같이 setter 메서드로 값을 변경하기만 하면 [변경 감지](https://github.com/ssu18/TIL/blob/main/JPA/%EC%9E%90%EB%B0%94%20ORM%20%ED%91%9C%EC%A4%80%20JPA_%EA%B8%B0%EB%B3%B8/%EC%98%81%EC%86%8D%EC%84%B1%20%EA%B4%80%EB%A6%AC_%EB%82%B4%EB%B6%80%20%EB%8F%99%EC%9E%91%20%EB%B0%A9%EC%8B%9D(3).md#%EC%97%94%ED%8B%B0%ED%8B%B0-%EC%88%98%EC%A0%95_%EB%B3%80%EA%B2%BD-%EA%B0%90%EC%A7%80)에 의해 자동 처리됨
    - 자세한 내용은 링크를 통해 확인

#### 영속성 컨텍스트와 테이블의 엔티티 삭제

```java
 private void example05() {
        tx.begin();
        em.persist(new Member("hgd1@gmail.com"));  // (1)
        tx.commit();    //(2)

        tx.begin();
        Member member = em.find(Member.class, 1L);   // (3)
        em.remove(member);     // (4)
        tx.commit();     // (5)
    }
```
- (4)에서 em.remove(member)를 통해 영속성 컨텍스트의 1차 캐시에 있는 엔티티 제거를 요청함
- (5)에서 tx.commit()을 실행하면 영속성 컨텍스트의 1차 캐시에 있는 엔티티를 제거
  - 쓰기 지연 SQL 저장소에 등록된 DELETE 쿼리가 실행됨