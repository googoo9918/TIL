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