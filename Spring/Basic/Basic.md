### 목차
- [Framework & Library](#framework--library)
  - [Framework](#framework)
  - [Library](#library)
  - [Library VS Framework](#library-vs-framework)
- [Spring](#spring)
- [POJO(Plain Old Java Obiect)](#pojoplain-old-java-obiect)
  - [IoC(Inversion of Control)](#iocinversion-of-control)
    - [Java 웹 애플리케이션에서 IoC가 적용되는 예](#java-웹-애플리케이션에서-ioc가-적용되는-예)
  - [DI(Dependency Injection)](#didependency-injection)
  - [AOP(Aspect Oriented Programming)](#aopaspect-oriented-programming)
    - [공통 관심 사항(Cross-cutting concern)](#공통-관심-사항cross-cutting-concern)
    - [핵심 관심 사항(Core concern)](#핵심-관심-사항core-concern)
  - [PSA(Portable Service Abstraction)](#psaportable-service-abstraction)
- [아키텍처](#아키텍처)
  - [시스템 아키텍처](#시스템-아키텍처)
  - [소프트웨어 아키텍처 / 애플리케이션 아키텍처](#소프트웨어-아키텍처--애플리케이션-아키텍처)
  - [계층형 애플리케이션 아키텍처](#계층형-애플리케이션-아키텍처)
    - [API 계층](#api-계층)
    - [서비스 계층](#서비스-계층)
    - [데이터 액세스 계층](#데이터-액세스-계층)
- [Spring Boot](#spring-boot)
## Framework & Library
### Framework
- 프로그래밍을 하기 위한 특정 틀이나 구조
  - ex) Collections, Interface
- 소프트웨어의 구체적인 부분에 해당하는 설계와 구현을 재사용이 가능하게끔 일련의 협업화된 형태로 클래스들을 제공하는 것
- 장점
  - 효율적으로 코드 작성 가능
    - 다양한 기능을 Framework가 라이브러리 형태로 제공
    - 개발자가 개발에만 집중할 수 있도록 함
  - 정해진 규약으로 애플리케이션 효율적 관리 가능
    - 유지보수 용이, 문제점 파악 용이
    - 코드 재사용 용이, 기능 확장 쉽게 가능
- 단점
  - 사용하고자 하는 Framework에 대한 학습 필요
  - 자유롭고 유연한 개발이 어려움

### Library 
- 애플리케이션을 개발하는데 사용되는 일련의 데이터 및 프로그램이 코드
  - 필요한 기능을 미리 구현해놓은 집합체
  - 필요한 기능이 있을 때 해당 라이브러리 호출 후 사용

### Library VS Framework
- Framework
  - 뼈대
  - 한 번 정해진 Framework를 교체하는 일은 어려움
  - 애플리케이션 흐름의 주도권이 개발자가 아닌 Framework에 있음
- Library
  - 다양한 기능을 제공하는 부품
  - 쉽게 교체가 가능, 선택적 사용 가능
  - 애플리케이션 흐름의 주도권이 개발자에게 있음
    - 필요한 기능이 있으면 라이브러리 호출해 사용하기 때문

## Spring
- 도입 배경
  - 기존에는 JSP(Java Server Page)나 Servlet 기술을 사용한 Model1, Model2 아키텍쳐를 기반으로 한 Java 웹 애플리케이션 제작
    - Servlet : 클라이언트 웹 요청 처리에 특화된 Java 클래스의 일종
      - [자바 서블릿](https://ko.wikipedia.org/wiki/자바_서블릿)
    - Servlet Container
      - 서블릿 기반의 웹 애플리케이션 실행
      - 서블릿 생명 주기 관리
      - 쓰레드 풀을 생성해 Servlet과 Thread 매핑
      - [서블릿 컨테이너](https://ko.wikipedia.org/wiki/%EC%9B%B9_%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88)
      - 대표적으로 [아파치톰캣](https://ko.wikipedia.org/wiki/%EC%95%84%ED%8C%8C%EC%B9%98_%ED%86%B0%EC%BA%A3) 존재
  - Spring MVC가 도입되며 방식이 획기적으로 변화
    - 여러 작업을 Spring에서 알아서 처리
    - 애플리케이션의 기본 구조를 잡는 설정 작업의 어려움
  - Spring MVC 설정의 복잡함과 어려움 극복을 위해 Spring Boot 탄생
    - 개발자는 애플리케이션의 비즈니스 로직에만 집중할 수 있게 됨

## POJO(Plain Old Java Obiect)
![image](https://user-images.githubusercontent.com/102513932/195294069-388d5d9c-495b-4f6f-822f-0bd59d2ed694.png)
- Java로 생성하는 순수한 객체
  - 1. Java나 Java의 스펙(사양)에 정의된 것 이외에는 다른 기술이나 규약에 얽매이지 않아야 함
  - 2. 특정 환경에 종속적이지 않아야 함
  - 필요성
    - 종속적이지 않다면 재사용 가능, 확장 가능한 유연한 코드 작성 가능
    - 디버깅, 테스트 상대적 용이
    - **객체지향적 설계 제한없이 적용 가능**
  - Spring은 POJO를 지향하는 Framework
    - POJO를 위해 IOC/DI, AOP, PSA 기술 제공

### IoC(Inversion of Control)
- 제어의 역전
  - 애플리케이션 흐름의 주도권이 뒤바뀐 것
#### Java 웹 애플리케이션에서 IoC가 적용되는 예
![image](https://user-images.githubusercontent.com/102513932/195296992-6316bd87-a05b-4283-91c7-6f09886b4b44.png)
> 엔트리 포인트: main() 메서드처럼 애플리케이션이 시작되는 지점을 
> 컨테이너 : 개발자 대신 객체의 생성과 소멸 같은 부분을 전담해주는 역할
- 서블릿 컨테이너는 서블릿 사양에 맞게 작성된 서블릿 클래스만 존재
  - 별도의 엔트리 포인트 존재x
    - 클라이언트의 요청이 들어올 때마다 서블릿 컨테이너 내의 컨테이너 로직이 서블릿을 직접 실행시켜 주기 때문
- 서블릿 컨테이너가 서블릿을 제어
  - 애플리케이션의 주도권은 서블릿 컨테이너에 존재
  - IoC 개념 적용돼 있음

### DI(Dependency Injection)
> [클래스 다이어그램 도구](https://online.visual-paradigm.com/diagrams/features/)
- 클래스의 생성자로 특정 객체를 전달 받는 것
- 강하게 결합(Tight Coupling)
  - new 키워드 사용해서 객체 생성 시, 참조할 클래스가 바뀌게 될 경우 모든 사용처에서 수정을 해야함
- 느슨한 결합(Loose Coupling)
  - 인터페이스 사용
  - 인터페이스와 업캐스팅을 통한 의존성 주입
  - 남은 new 키워드는 Spring이 제거, 의존 관계를 더욱 느슨하게 함
    - Spring이 의존 객체들을 주입해줌
      - 애플리케이션 코드 유연하게 구성 가능

### AOP(Aspect Oriented Programming)
- 관점 지향 프로그래밍
  - 특정 로직을 기준으로 핵심적 관점과 부가적 관점으로 분류, 그 관점을 기준으로 각각 모듈화
  - aspect를 애플리케이션의 공통 관심사로 생각
- ![image](https://user-images.githubusercontent.com/102513932/195315121-64a855f3-c560-479e-9e28-192fd30cdb1f.png)
  - 커피 주문 애플리케이션 예시
  - 애플리케이션의 핵심 업무 로직에서 로깅이나 보안, 트랜잭션과 같은 공통 기능 로직들을 분리
- 장점
  - 코드의 간결성 유지
  - 객체 지향 설계 원칙에 맞는 코드 구현
  - 코드의 재사용
#### 공통 관심 사항(Cross-cutting concern)
- 애플리케이션 전반에 걸쳐 공통적으로 사용되는 기능에 대한 관심
- 부가적인 관심 사항이라 지칭하기도 함
#### 핵심 관심 사항(Core concern)
- 비즈니스 로직, 즉 애플리케이션의 주 묵족을 달성하기 위한 핵심 로직에 대한 관심사

### PSA(Portable Service Abstraction)
- 일관된 서비스 추상화
  - 환경의 변화와 관계없이 일관된 방식의 가술로의 접근 환경을 제공하려는 추상화 구조
  - 서비스의 기능에 접근하는 방식 자체를 일관되게 유지하며, 기술 자체를 유연하게 사용할 수 있도록 함
    - 본질적인 특성만을 추출해서 일반화 
  - 접근 방식을 일관된 방식으로 유지함
    - 애플리케이션에서 사용하는 기술이 변경되더라도 최소한의 변경으로 변경된 요구사항 반영 가능
      - 애플리케이션의 요구 사항 변경에 유연하게 대처 가능

## 아키텍처
- 특정 건물이나 구조물에 대한 컨셉
  - 최대한 간결함을 유지하는 것이 좋다
### 시스템 아키텍처
- 하드웨어와 소프트웨어를 모두 포함하는 특정 시스템의 전체적 구성 표현
  - ![image](https://user-images.githubusercontent.com/102513932/195317863-2464733c-3383-4e1e-808d-feb17919613d.png)
### 소프트웨어 아키텍처 / 애플리케이션 아키텍처
- 소프트웨어의 구성을 큰 그림으로 표현
- ![image](https://user-images.githubusercontent.com/102513932/195318112-c4995cc6-6b2a-4d26-a87b-06cf206fee1a.png)
  - Java SE 아키텍처
    - Java 플랫폼에 대한 아키텍처를 마치 벽돌을 쌓은 듯한 모습으로 표현
### 계층형 애플리케이션 아키텍처
- ![image](https://user-images.githubusercontent.com/102513932/195319256-c4d16ae4-5887-40b1-9358-c3840d5b48e3.png)
#### API 계층
- 클라이언트의 요청을 받아들이는 계층
- 보통 표현계층이라 지칭
  - REST API를 제공하는 애플리케이션의 경우 API 계층이라 표현
#### 서비스 계층
- API 계층에서 잔달 받은 요청을 업무 도메인의 요구 사항에 맞게 처리
#### 데이터 액세스 계층
- 비즈니스 계층에서 처리된 데이터를 데이터베이스 등의 데이터 저장소에 저장하기 위한 계층

## Spring Boot
- Spring Framework의 편리함에도 불구, 설정의 복잡합의 문제점을 해결하기 위해 생겨난 Spring Project 중 하나
- 장점
  - XML 기반 복잡한 설계 방식 지양
    - Spring boot 이전의 복잡한 설정 방식에서 벗어남
  - 의존 라이브러리 자동 관리
    - 기존 일일이 추가하던 라이브러리 이름과 버전을 자동으로 관리 가능
  - 애플리케이션 설정의 자동 구성
    - ex) `implementation 'org.springframework.boot:spring-boot-starter-jdbc`
      - 데이터베이스 연결이 필요하다고 추측, JDBC 설정을 자동으로 구성
  - 프로덕션급 애플리케이션의 손쉬운 빌드
    - 직접 빌드 결과물을 WAR 파일 형태로 WAS(Web Application Server)에 올릴 필요가 없음
> JAVA 기반의 웹 어플리케이션을 배포하는 일반적 방식은 개발자가 구현한 애플리케이션 코드를 WAR(Web applicatin ARchive)파일 형태 빌드한 후에 WAS라는 서버에 배포 후 해당 애플리케이션을 실행하는 것임. 즉, WAS는 코드를 빌드해서 나온 결과물을 실제 웹 애플리케이션으로 실행되게 해주는 서버
  - 내장된 WAS를 통한 손쉬운 배포
    - Apache Tomcat이라는 WAS 내장, 별도의 WAS 구축 필요X