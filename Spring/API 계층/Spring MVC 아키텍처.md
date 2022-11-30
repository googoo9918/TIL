### 목차
- [Spring MVC란?](#spring-mvc란)
  - [Spring MVC란?](#spring-mvc란-1)
  - [Model](#model)
  - [View](#view)
  - [Controller](#controller)
  - [정리](#정리)
  - [Spring MVC의 동작 방식과 구성 요소](#spring-mvc의-동작-방식과-구성-요소)

# Spring MVC란?
## Spring MVC란?
- 서블릿 API를 기반으로 클라이언트의 요청을 처리하는 모듈 : `spring-webmvc`
  - spring-webmvc 모듈이 Spring MVC임
    - Spring MVC는 웹 프레임워크의 한 종류이기 때문에, Spring MVC 프레임워크로 지칭하기도 함
  - 서블릿은 클라이언트의 요청을 처리하도록 특정 규약에 맞춰 Java 코드로 작성하는 클래스 파일, 단순히 **컨테이너**라 생각할 것
    - ex) 아파치 톰캣
## Model
- 클라이언트의 요청을 전달 받으면 요청 사항을 처리하기 위한 작업을 함
  - 처리한 작업의 결과 데이터를 클라이언트에게 응답으로 돌려주는데, 이 때 응답으로 돌려주는 **작업의 처리 결과**를 **Model**이라 지칭함

## View
- Model 데이터를 이용해 웹 브라우저 같은 클라이언트 애플리케이션의 **화면에 보여지는 리소스를 제공**
  - HTML 페이지의 출력
  - PDF, Excel 등의 문서 형태로 출력
  - XML, JSON 등 특정 형식의 포맷으로의 변환

## Controller
- 클라이언트 측의 요청을 직접적으로 전달 받는 엔드포인트
  - Model과 View 중간에서 상호 작용
    - 클라이언트 측의 요청을 전달 받아 비즈니스 로직을 거친 후 Model 데이터 생성
    - 이후 Controller가 이 Model 데이터를 View로 전달함

## 정리
- Client가 요청 전송 -> Controller가 요청 데이터 수신 -> 비즈니스 로직 처리 -> Model 데이터 생성 -> Controller에게 Model 데이터 전달 -> Controller가 View 에게 Model 데이터 전달 -> View가 응답 데이터 생성

## Spring MVC의 동작 방식과 구성 요소
- ![image](https://user-images.githubusercontent.com/102513932/204834355-85b580f4-dcb0-405a-b4fb-889c7e576651.png)
- (1) 클라이언트 요청 시 `DispatcherServlet`이라는 클래스에 요청이 전달됨
- (2) `DispatcherServlet`은 클라이언트의 요청을 처리할 Contorller에 대한 검색을 HandlerMapping 인터페이스에게 요청함
- (3) HandlerMapping은 클라이언트 요청과 매핑되는 핸들러 객체를 다시 DispatcherServlet에게 리턴함
  - 핸들러 객체는 해당 핸들러의 Handler 메서드 정보를 포함함
  - Handler 메서드는 Controller 클래스 안에 구현된 요청 처리 메서드를 의미
- (4) 요청을 처리할 Controller 클래스를 찾았으니 이제는 실제로 클라이언트 요청을 처리할 Handler 메서드를 찾아서 호출함
  - `DistpatcherServlet`은 Handler 메서드를 직접 호출하지 않고 HandlerAdapter에게 Handler 메서드 호출을 위임함
- (5) `HandlerAdapter`는 DispatcherServlet으로부터 전달 받은 Controller 정보를 기반으로 해당 Controller의 Handler 메서드를 호출함
- (6) `Controller`의 Handler 메서드는 비즈니스 로직 처리 후 리턴 받은 Model 데이터를 HandlerAdapter에게 전달함
- (7) `HandlerAdapter`는 전달받은 Model 데이터와 View 정보를 다시 DispatcherServlet에게 전달
- (8) `DispatcherServlet`은 전달 받은 View 정보를 다시 ViewResolver에게 전달해서 View 검색을 요청함
- (9) `ViewResolver`는 View 정보에 해당하는 View를 찾아 View를 다시 리턴해줌
- (10) `DispatcherServlet`은 ViewResolver로부터 전달 받은 View 객체를 통해 Model 데이터를 넘겨주며 클라이언트에게 전달할 응답 데이터 생성을 요청
- (11) View는 응답 데이터를 생성해 다시 DispatcherServlet에게 전달
- (12) `DispatcherServlet`은 View로부터 전달 받은 응답 데이터를 최종적으로 클라이언트에게 전달함