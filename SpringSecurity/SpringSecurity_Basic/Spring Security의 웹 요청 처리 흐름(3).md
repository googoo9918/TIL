# Spring Security의 웹 요청 처리 흐름
- 내부적으로 Spring Security에서 제공하는 컴포넌트들이 애플리케이션 내부에서 User의 인증과 권한에 대한 처리를 알아서 진행해 줌
  
## 보안이 적용된 웹 요청의 일반적인 처리 흐름
- ![image](https://user-images.githubusercontent.com/102513932/203105990-b4a9adef-dd4c-46ff-8a5e-63ff866ba3d8.png)
  - (1)에서 사용자가 보호된 리소스를 요청
  - (2)에서 인증 관리자 역할을 하는 컴포넌트가 사용자의 크리덴셜(Credential)을 요청
    - 사용자의 크리덴셜 : 해당 사용자를 증명하기 위한 구체적인 수단
      - 일반적으로 사용자의 패스워드를 의미
  - (3)에서 사용자는 인증 관리자에게 크리덴셜을 제공
  - (4)에서 인증 관리자는 크리덴셜 저장소에서 사용자의 크리덴셜을 조회
  - (5)에서  인증 관리자는 사용자가 제공한 크리덴셜과 크리덴셜 저장소에 저장된 크리덴셜을 비교해 검증 작업을 수행
  - (6) 유효한 크리덴셜이 아니라면 Exception을 throw
  - (7) 유효한 크리덴셜이라면 (8)에서 접근 결정 관리자 역할을 하는 컴포넌트는 사용자가 적절한 권한을 부여받았는지 검증
  - (9) 적절한 권한을 부여 받지 못한 사용자라면 Exception을 throw
  - (10) 적절한 권한을 부여 받은 사용자라면 보호된 리소스의 접근을 허용

## 웹 요청에서의 서블릿 필터와 필터 체인의 역할 
- 사용자의 웹 요청이 Controller 같은 엔드포인트를 거쳐 접근하려는 리소스에 도달하기 전에 인증 관리자나 접근 결정 관리자 같은 컴퍼넌트가 중간에 웹 요청을 가로채 사용자의 크리덴셜과 접근 권한을 검증함
- 서블릿 기반 애플리케이션의 경우, 애플리케이션의 엔드포인트에 요청이 도달하기 전에 중간에서 요청을 가로챈 후 특정한 처리를 할 수 있는 적절한 포인트를 제공함
  - 이러한 포인트를 서블릿 필터라 칭함

> 서블릿 필터는 자바에서 제공하는 API로, javax.servlet 패키지에 인터페이스 형태로 정의됨 <br>
> javax.srvlet.Filter 인터페이스를 구현한 서블릿 필터는 웹 요청을 가로채 특정 처리(전처리)를 할 수 있으며, 엔드포인트에서 요청 처리가 끝난 후 전달되는 응답을 클라이언트에게 전달하기 전에 특정한 처리(후처리)를 할 수 있음

- 서블릿 필터는 하나 이상의 필터들을 연결해 필터 체인 구성 가능
  - ![image](https://user-images.githubusercontent.com/102513932/203110089-81a92ab5-4a99-4215-a9ce-eb8d4364e5fd.png)
    - Spring Framework의 DispatcherServlet에 클라이언트의 요청이 전달되기 전에 필터 체인(Filter Chain)을 구성한 예
    - 서블릿 필터는 각각의 필터들이 `doFilter()`라는 메서드를 구현해야 함
      - `doFilter()` 메서드 호출을 통해 필터 체인을 형성함
  - 위 그림처럼 Filter 인터페이스를 구현한 다수의 Filter 클래스를 구현할 시
    - 생성한 서블릿 필터에서 특별한 작업을 수행한 뒤, HttpServlet을 거쳐 DispatcherServlet에 요청이 전달
      - 반대로 DispatcherSerblet에서 전달한 응답에 대해 역시 특별한 작업 수행 가능

## Spring Security에서의 필터 역할
- 서블릿 필터는 클라이언트의 요청 중간에 끼어들어 추가적인 작업을 할 수 있음
  - 따라서 Spring Security에서도 이 필터를 이용, 클라이언트의 요청을 중간에 가로챈 뒤 추가적인 작업을 진행
- ![image](https://user-images.githubusercontent.com/102513932/203111458-6172fb85-ff51-4b72-8c15-f62d109b4e04.png)
  - `DelegatingFilterProxy` 와 `FilterChainProxy` 클래스는 Filter 인터페이스를 구현하기 때문에 엄연히 서블릿 필터로써의 역할을 함

### DelegatingFilterProxy
- Spring Security 역시 Spring의 핵심인 ApplicationContext 이용
  - `DelegatingFilterProxy` 가 ApplicationContext에 Bean으로 등록된 Spring Security의 필터를 사용하는 시작점임
  - 이 때 `DelegatingFilterProxy`는 서블릿 컨테이너 영역의 필터와 ApplicationContext에 Bean으로 등록된 필터들을 연결해주는 브릿지 역할을 함

### FilterChainProxy
- ![image](https://user-images.githubusercontent.com/102513932/203112557-5f654b0c-0719-4cf9-b3ae-501775e7f083.png)
  - Spring Security의 Filter Chain은 말 그대로 Spring Security에서 보안을 위한 작업을 처리하는 필터의 모음
    - 이 Spring Security의 Filter를 사용하기 위한 진입점이 바로 `FilterChainProxy`임
      - FilterChainProxy부터 Spring Security에서 제공하는 보안 필터들이 필요한 작업을 수행함
  - Spring Security의 Filter Chain은 URL 별로 여러개 등록 가능
    - Filter Chain이 있을 때 어떤 Filter Chain을 사용할지는 FilterChainProxy가 결정
      - 가장 먼저 매칭된 Filter Chain 실행
  - ex)
    - `/api/**` 패턴의 Filter Chain이 있고, `/api/message URL` 요청이 전송하는 경우
      - `/api/**` 패턴과 제일 먼저 매칭되므로, 디폴트 패턴인 `/**`도 일치하지만 가장 먼저 매칭되는 `/api/**` 패턴과 일치하는 Filter Chain만 실행
    - /message/** 패턴의 Filter Chain이 없는데 `/message/` URL 요청을 전송하는 경우
      - 매칭되는 Filter Chain이 없으므로 디폴트 패턴인 `/**` 패턴의 Filter Chain을 실행

## 핵심 포인트
- **서블릿 필터(Servlet Filter)**는 서블릿 기반 애플리케이션의 엔드포인트에 요청이 도달하기 전에 중간에서 요청을 가로챈 후 어떤 처리를 할 수 있도록 해주는 Java의 컴포넌트
- Spring Security의 필터는 클라이언트의 요청을 중간에서 가로챈 뒤, 보안에 특화된 작업을 처리하는 역할 진행
- `DelegatingFilterProxy`라는 이름에서 알 수 있듯이 서블릿 컨테이너 영역의 필터와 ApplicationContext에 Bean으로 등록된 필터들을 연결해주는 브릿지 역할
- Spring Security의 Filter Chain은 Spring Security에서 보안을 위한 작업을 처리하는 필터의 모음
  - pring Security의 Filter를 사용하기 위한 진입점이 바로 `FilterChainProxy`