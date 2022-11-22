# Hello Spring Security 샘플 애플리케이션을 통한 Sping Security 기본 구조
  - `ideaProjects/be-template-hello-spring-security` 템플릿 프로젝트 사용
  - 템플릿 프로젝트 실행 후 `localhost:8080` 접속
## 애플리케이션 화면
### 샘플 애플리케이션 홈 화면
- ![image](https://user-images.githubusercontent.com/102513932/202606151-d298f57d-2eff-4c7a-86c8-f95c4b408f4e.png)
### 회원 가입 화면
- ![image](https://user-images.githubusercontent.com/102513932/202606239-3342b66f-a750-4c1e-b4cd-a7f292f2d136.png)
### 로그인 화면
- ![image](https://user-images.githubusercontent.com/102513932/202606600-e0c6ca59-a0f7-4b82-bc09-85574a7e457b.png)
  - HTML의 form 방식으로 로그인 인증 진행 (폼 로그인 인증)
  - 현재 로그인 화면은 커스텀 로그인 페이지 설정을 하기 전까지는 직접적인 기능을 하지 않음
### 커피 보기 화면
- ![image](https://user-images.githubusercontent.com/102513932/202606729-d64951ff-276d-4dff-92c9-3e9f85c3f035.png)
  - Spring Security를 적용해 모든 사용자들이 접근 가능한 화면이 되도록 설정
### 전체 주문 목록 보기 화면
- ![image](https://user-images.githubusercontent.com/102513932/202607235-3d95c3b0-f573-4420-bf62-d492a496004f.png)
  - Spring Security 적용 시, 관리자만 접근 가능한 페이지로 설정
### 마이페이지 화면
- ![image](https://user-images.githubusercontent.com/102513932/202607699-d19d267a-d61c-4dff-9f73-5c82ef440d5e.png)
  - Spring Security 적용 시, 일반 사용자만 접근 가능한 페이지

## Spring Security 적용
### Hello Spring Security 샘플 애플리케이션의 문제점
- 현재 로그인 기능이 구체적으로 구현되어 있지 않음
```java
@Controller
@RequestMapping("/auths")
public class AuthController {
    @GetMapping("/login-form")
    public String loginForm() {
        return "login";
    }

    //(1)
    @PostMapping("/login")
    public String login() {
        System.out.println("Login successfully!");
        return "home";
    }
}
// 로그인 인증을 위한 AuthController (코드 4-3)
```
- 그림4-3의 로그인 폼에서 로그인 버튼 클릭 시 위 코드 (1)의 login() 메서드로 요청이 전달됨
  - 현재 코드 상에서는 로그인을 위한 인증 처리 없이 home 화면으로 이동하도록 구성되어 있음
- 인증이 정상적으로 이뤄지지 않았기 때문에 **누구든 모든 화면에 자유롭게 접근할 수 있는 문제점을 가짐**

### Hello Spring Security 샘플 애플리케이션에 Spring Security 적용
- 의존 라이브러리 추가(build.gradle)
```java
dependencies {
	implementation 'org.springframework.sadasdboot:spring-boot-starter-security'   // (1)
}
```
- Spring Security 적용됨
- 이후 애플리케이션 재실행 시, `localhost:8080`로 접속하면 인증을 위한 로그인 화면으로 리다이렉트 됨
  - ![image](https://user-images.githubusercontent.com/102513932/202608618-3567c174-e6fd-4b02-871c-aa7722c55132.png)
    - 이 화면은 우리가 직접 만든 로그인 페이지가 아님
    - Spring Security가 내부적으로 제공해주는 디폴트 로그인 페이지임
    - 이때, Username과 Password 또한 디폴트로 제공함
      - Username: `user`
      - password: 실행 시 로그에서 확인 가능
        - `Using generated security password: 0d582299-f5c0-4264-8fee-3526eaec7765`
  - ![image](https://user-images.githubusercontent.com/102513932/202608768-befc925e-fb3e-4425-a8f9-e9f7b388d59f.png)
    - 잘못된 인증 정보 입력할 경우 예외 처리도 가능
  - 그런데, 이 방식은 실무에서 사용하기에는 무리가 있음
    - 애플리케이션을 실행할 때마다 패스워드가 바뀌는 문제점
    - 회원 각자의 인증 정보로 로그인을 하는 것 역시 불가능
    - 앞에서 직접 작성한 로그인 페이지 사용 불가
  - 따라서 Spring Security의 Configuration을 통해 입맛에 맞는 인증 방식을 설정하면 됨

## Spring Security Configuration 적용
- Spring Security Configuration 적용 시 원하는 인증 방식과 웹 페이지에 대한 접근 권한을 설정할 수 있음

### Spring Security Configuration의 기본 구조
```java
@Configuration
public class SecurityConfiguration {
    // 여기에 Spring Security의 설정을 진행합니다.
}
```

### InMemory User로 인증하기
```java
@Configuration
public class SecurityConfiguration {
    @Bean
    public UserDetailsManager userDetailsService() {
        // (1)
        UserDetails userDetails =
                User.withDefaultPasswordEncoder()    // (1-1)
                        .username("kevin@gmail.com") // (1-2)
                        .password("1111")            // (1-3)
                        .roles("USER")               // (1-4)
                        .build();
        // (2)
        return new InMemoryUserDetailsManager(userDetails);
    }
}
//(코드 4-6) InMemory Single User 인증 정보 설정
```
- 코드는 애플리케이션이 실행된 상태에서 사용자 인증을 위한 계정 정보를 메모리 상에 고정된 값으로 설정한 예임
  - 매번 랜덤하게 생성됐던 패스워드 => 사용자의 계정 정보(고정 값)를 메모리 상에 지정
- (1)의 `UserDetails` 인터페이스는 인증된 사용자의 핵심 정보를 포함하며, `UserDetails` 구현체인 (2)의 User 클래스를 이용해 사용자의 인증 정보를 생성함
  - `withDefaultPasswordEncoder()`는 디폴트 패스워드 인코더를 이용해 사용자 패스워드를 암호화 함
    - 현재 예제 코드에서는 (1-3)의 `passwor()` 메서드의 파라미터로 전달한 "1111"을 암호화 해줌
  - `username()` 메서드는 사용자의 username을 설정함
    - 여기서 username은 사람의 이름이 아닌, 고유한 사용자를 식별할 수 있는 사용자 아이디 같은 값임
  - `password()` 메서드는 사용자의 password를 설정함
    - 파라미터로 지정한 값은 1-1의 `withDefaultPasswordEncoder()` 로 인해 암호화 됨
  - `roles()` 메서드는 사용자의 Role, 즉 역할을 지정하는 메서드임
    - ex) 일반 사용자 or 관리자
- Spring Security 에서는 사용자의 핵심 정보를 포함한 UserDetails를 관리하는 `UserDetailsManager`라는 인터페이스를 제공함
  - 현재 예제에서는 메모리 상에서 UserDetails를 관리하므로 `InMemoryUserDetailsManaber`라는 구현체를 사용함
  - (2)와 같이 `new InMemoryUserDetailsManager(userDetails)`를 통해 `UserDetailsManager` 객체를 **Bean 으로 등록**
  - Spring에서 해당 Bean이 갖고 있는 사용자의 인증 정보가 클라이언트의 요청으로 넘어올 경우, 정상적인 인증 프로세스를 수행함
- 애플리케이션 재실행 후, Spring security에서 제공하는 로그인 화면에서 위 username과 password 정보로 로그인 시 정상적으로 로그인 가능
  - 그러나, 실제 서비스에서 이런식으로 사용자 계정 정보를 고정 시켜 사용하진 않을 것임
  - **테스트 환경 또는 데모 환경에서만 유용하게 사용 가능**

> 참고: Deprecated 상태인 `withDefaultPasswordEncoder()` <br>
> API 문서에서 흔히 볼 수 있는 Deprecated라는 용어는 해당 API가 향후 버전에서는 더 이상 사용되지 않고 제거될 수 있다는 의미이고, 따라서 Deprecated라고 표시된 API 사용은 권장하지 않습니다. <br>
> 그런데 `withDefaultPasswordEncoder()` 메서드의 Deprecated는 특이하게도 향후 버전에서 제거됨을 의미하는 것이 아닌, Production 환경에서 인증을 위한 사용자 정보를 고정해서 사용하지 말라는 **경고의 의미**를 나타내고 있는 것이니 반드시 테스트 환경이나 데모 환경에서만 사용해야 합니다. 

### HTTP 보안 구성 기본
```java
@Configuration
public class SecurityConfiguration {
    // (1)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // HttpSecurity를 통해 HTTP 요청에 대한 보안 설정을 구성한다.
        ...
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        ...
        // 내용 생략
    }
}
// (코드 4-7), Spring Security의 HTTP 보안 설정 기본
```
- Spring Security에서 HTTP 보안을 설정하기 위한 기본 코드임
- (1)과 같이 `HttpSecurity`를 파라미터로 갖고, SecurityFilterChain을 리턴하는 형태의 메서드를 정의하면 HTTP 보안 설정 구성 가능함
- `HttpSecurity`는 **HTTP 요청에 대한 보안 설정을 구성하기 위한 핵심 클래스**입니다.

> 참고: Spring Security 5.7 이전 버전에서는 HTTP 보안 설정을 구성하기 위해 WebSecurityConfigurerAdapter를 상속하는 형태의 방법을 주로 사용했지만, WebSecurityConfigurerAdapter는 5.7.0에서 Deprecate 되었다. <br>
> 따라서 코드 4-7과 같이 `SecurityFilterChain`을 Bean으로 등록해서 HTTP 보안 설정을 구성하는 방식을 권장한다는 것을 기억하자.

### 커스텀 로그인 페이지 지정하기
- 앞에서 로그인을 위해 사용한 로그인 페이지는 Spring Security에서 내부적으로 제공하는 로그인 페이지임
- 만들어 두었던 로그인 페이지(그림 4-3)를 사용하도록 코드 4-7의 기본 구성을 기반으로 설정을 추가
```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //HttpSecurity를 통해 HTTP 요청에 대한 보안 설정을 구성함
        http
                .csrf().disable() //(1)
                .formLogin()    //(2)
                .loginPage("/auths/login-form") //(3)
                .loginProcessingUrl("/process_login") //(4)
                .failureUrl("/auths/login-form?error") //(5)
                .and() //(6)
                .authorizeHttpRequests() //(7)
                .anyRequest() //(8)
                .permitAll(); //(9)

        return http.build();
    }
    @Bean
    public UserDetailsManager userDetailsService(){
    ... // 내용 생략
    }
}
// (코드 4-8) 커스텀 로그인 페이지 설정
```
- 코드 4-8에서는 Spring Security의 보안 구성 중에서 우리가 만들어 둔 커스텀 로그인 페이지를 사용하기 위한 최소한의 설정만 추가한 코드임
- 기능 설명
  - (1)에서는 CSRF(Cross-Site Request Forgery) 공격에 대한 Spring Security에 대한 설정을 비활성화 하고 있음
    - Spring Security는 기본적으로 아무 설정을 하지 않으면 csrf() 공격을 방지하기 위해 클라이언트로부터 CSRF Token을 수신 후, 검증함
    - 현재 예제는 로컬 환경에서 Spring Security에 대한 학습을 진행
      - CSRF 공격에 대한 설정이 필요하지 않음
      - 만약 `csrf().disable()`설정을 하지 않는다면 403에러 발생함
  - (2)의 `formLogin()`을 통해 기본적인 인증 방법을 폼 로그인 방식으로 지정함
  - (3)의 `loginPage("/auths/login-form")` 메서드를 통해 현재 템플릿 프로젝트에서 미리 만들어 둔 커스텀 로그인 페이지를 사용하도록 설정
  - `/auths/login-form`은 AuthController의 `loginForm()` 메서드에 요청을 전송하는 요청 URL임
  - (4)의 `loginProcessingUrl("/process_login")` 메서드를 통해 로그인 인증 요청을 수행할 요청 URL 지정
    - `/process_login`은 우리가 만들어 둔 login.html에서 form 태그의 action 속성에 지정한 URL과 동일함

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="layouts/common-layout">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Hello Spring Security Coffee Shop</title>
</head>
<body>
    <div layout:fragment="content">
        <form action="**/process_login**" method="post">
            <p><input type="email" name="username" placeholder="Email" /></p>
            <p><input type="password" name="password" placeholder="Password" /></p>
            <p><button>로그인</button></p>
        </form>
        <a href="/members/register">회원가입</a>
    </div>
</body>
</html>
<!-- (코드 4-9) 커스텀 로그인 페이지(login.html) -->
```
- 템플릿 프로젝트에 작성해 둔 login.html 코드임
  - form 태그 확인 시 , `<form action = "**/process_login**" method = "post">` 같이 `/process_login` URL이 지정되어 있는 것을 확인할 수 있음
  - 커스텀 로그인 화면에서 로그인 버튼 클릭 시, form 태그의 action 속성에 지정된 `/process_login` URL로 사용자 인증을 위한 email 주소와 패스워드를 전송하게 됨
- 그렇다면 `/process_login` URL로 요청 전송 시, 해당 요청은 누가 처리하는가?
  - 아직 AutoContorller에서 별도의 인증 프로세스 로직을 작성하지 않음
  - 결국 `/process_login` URL로 요청을 전송 시, 여전히 Spring Security에서 내부적으로 인증 프로세스를 진행함

> 현재 상태에서는 login.html 같은 커스텀 로그인 페이지를 통해 Spring Security가 로그인 인증 처리를 하기 위한 요청 URL을 지정하는 것일 뿐임!!

- 코드 4-8의 기능설명 (위에서 이어짐)
  - (5)의 `failureUrl("/auths/login-from?error")` 메서드를 통해 로그인 인증에 실패할 경우 어떤 화면으로 리다이렉트 할 것인지 지정
    - 로그인에 실패할 경우, 로그인 인증에 실패했다는 메시지를 표시
      - 파라미터로 커스텀 로그인 페이지의 URL인 `"/auths/login-form?error` 지정
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="layouts/common-layout">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Hello Spring Security Coffee Shop</title>
    </head>
    <body>
        <div class="container" layout:fragment="content">
            <form action="/process_login" method="post">
                <!-- (1) 로그인 실패에 대한 메시지 표시 -->
                <div class="row alert alert-danger center" role="alert" th:if="${param.error != null}">
                    <div>로그인 인증에 실패했습니다.</div>
                </div>
                <div class="row">
                    <div class="col-xs-2">
                        <input type="email" name="username"  class="form-control" placeholder="Email" />
                    </div>
                </div>
                <div class="row" style="margin-top: 20px">
                    <div class="col-xs-2">
                        <input type="password" name="password"  class="form-control" placeholder="Password" />
                    </div>
                </div>

                <button class="btn btn-outline-secondary" style="margin-top: 20px">로그인</button>
            </form>
            <div style="margin-top: 20px">
                <a href="/members/register">회원가입</a>
            </div>
        </div>
    </body>
</html>
<!-- 코드(4-10) 인증 실패 메시지 표시 로직을 추가한 커스텀 로그인 페이지(login.html) -->
```
- 코드(4-10)은 로그인 인증에 실패할 경우, 인증에 실패했다는 메시지를 표기하기 위한 로직을 추가한 `login.html`임
  - (1)을 보면, `${param.error}`의 값을 통해 로그인 인증 실패 메시지 표시 여부를 결정함
- `${param.error}`는 Spring Security Configuration에서 `failureUrl("auths/login-form?error")`의 `?error` 부분에 해당하는 쿼리 파라미터를 의미함
- 인증 실패 시 커스텀 로그인 페이지
  - ![image](https://user-images.githubusercontent.com/102513932/202694621-b1e9501a-4832-4e97-a582-1e3213e77c35.png)
<br>

- 코드 4-8의 기능설명 (위에서 이어짐22)
  - (6)의 `and()` 메서드를 통해 Spring Securtity 보안 설정을 메서드 체인 형태로 구성할 수 있음
  - (7), (8), (9)를 통해서 클라이언트의 요청에 대해 접근 권한을 확인함
    - 접근을 허용할지 여부 결정
    - (7)의 `authorizeHttpRequests()` 메서드를 통해 클라이언트의 요청이 들어오면 접근 권한을 확인하겠다고 정의
    - (8)과 (9)의 `anyRequest().permitAll()` 메서드를 통해 클라이언트의 모든 요청에 대해 접근을 허용함
      - 현재는 로그인 인증 여부, 접근 권한 부여 여부와 상관없이 모든 페이지에 대한 접근을 허용한 상태임

### request URI에 접근 권한 부여
- 현재 SecurityConfiguration 클래스에서 설정한 사용자 계정(`kevin@gmail.com`)을 통해 로그인 인증에 성공 가능함
  - 이제부터는 사용자에게 부여된 Role을 이용해 샘플 애플리케이션의 request URI에 접근 권한을 부여
- 현재 SecurityConfiguration 클래스에서 설정해 둔 사용자 계정 정보는 다음과 같음
  - email : `kevin@gmail.com`
  - password: `1111`
  - Role: `USER`

```java
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin()
            .loginPage("/auths/login-form")
            .loginProcessingUrl("/process_login")
            .failureUrl("/auths/login-form?error")
            .and()
            .exceptionHandling().accessDeniedPage("/auths/access-denied")   // (1)
            .and()
            .authorizeHttpRequests(authorize -> authorize                  // (2)
                    .antMatchers("/orders/**").hasRole("ADMIN")        // (2-1)
                    .antMatchers("/members/my-page").hasRole("USER")   // (2-2)
                    .antMatchers("⁄**").permitAll()                    // (2-3)
            );
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // 내용 생략
    }
    // (코드 4-11) 요청 URI에 대한 접근 권한 부여 
```
- 코드 4-8에서 `.authorizeHttpRequests().anyRequest().permitAll();` 설정을 통해 로그인 인증에 성공할 경우, 모든 화면에 접근할 수 있도록 했음
  - 코드 4-11에서는 사용자의 Role 별로 reuqest URI에 접근 권한이 부여되도록 수정
- 코드 4-11 설명
  - (1)에서는 `exceptionHandling().accessDeniedPage("/auths/access-denied")`를 통해 권한이 없는 사용자가 특정 request URI에 접근할 경우 발생하는 `403(Forbidden)` 에러를 처리하기 위한 페이지를 설정함
    - 즉, 권한이 없는 사용자가 특정 request URI로 request를 전송할 경우 아래와 같은 화면(그림 4-9)이 표시됨
      - ![image](https://user-images.githubusercontent.com/102513932/202702024-8f1423e9-1958-47f5-a452-a41be466f529.png)
      - `exceptionHandling()`메서드는 메서드의 이름 그대로 Exception을 처리하는 기능을 함
        - `ExceptionHandlingConfigurer` 객체를 리턴하며 구체적인 Exception 처리를 할 수 있음
      - `accessDeniedPage()` 메서드는 403 에러 발생 시, 파라미터로 지정한 URL로 리다이렉트 되게 해줌
        - 화면으로 보여지는 html 페이지는 `access-denied.html(src/main/resource/tempaltes)`임
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org"
            xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
            layout:decorate="layouts/common-layout">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Hello Spring Security Coffee Shop</title>
        </head>
        <body>
        <div layout:fragment="content">
        <h2 style="color: red">접근 권한이 없는 계정으로 로그인 했습니다.</h2>
        </div>
        </body>
        </html>        
        ```
        - 접근 권한이 없는 페이지에 대한 Mapping AuthControoler에 추가 
        ```java
        @GetMapping("/access-denied")
        public String accessDenied(){
            return "access-denied";
            }
        ```
  - `authorizeHttpRequests()` 메서드는 (2)와 같이 람다 표현식을 통해 request URI에 대한 접근 권한 부여 가능
    - `antMatchers()` 메서드는 이름 그대로 **ant** 빌드 툴에서 사용되는 `Path Pattern`을 이용하여 매치되는 URL을 표현함
      - (2-1)의 `.antMatchers("/orders/**").hasRole("ADMIN")`은 ADMIN Role을 부여 받은 사용자는 **/orders로 시작하는 모든 URL에 접근**할 수 있다는 의미
        - 만약 `/orders/*` 라는 URL을 지정했다면 `/orders/1` 과 같이 /orders의 하위 URL의 depth가 1인 URL만 포함
      - (2-2)의 `antMatchers("/members/my-page").hasRole("USER")` 은 `USER` Role을 부여 받은 사용자만 `/members/my-page` URL에 접근할 수 있음을 나타냄
      - (2-3)의 `.antMatchers("/**").permitAll()`은 앞에서 지정한 URL 이외의 나머지 모든 URL은 Role에 상관없이 접근이 가능함을 의미

#### antMatchers()를 이용한 접근 권한 부여 시, 주의사항
- antMatcher() 메서드를 아래와 같이 순서를 바꿔 실행했다고 가정
```java
.authorizeHttpRequests(authorize -> authorize
                    .antMatchers("⁄**").permitAll() // 이 표현식이 제일 앞에 올 때
                    .antMatchers("/orders/**").hasRole("ADMIN")
                    .antMatchers("/members/my-page").hasRole("USER")
            );
```
- `antMatchers("/**").permitAll()`이 제일 앞에 위치하면 Spring Security에서는 Role에 상관없이 모든 request URL에 대한 접근을 허용함
  - 다음에 오는 메서드는 제 기능을 하지 못하게 되고, 결과적으로 사용자의 Role과는 무관하게 모든 request URL에 접근이 가능함
  - 위와 같은 설정 오류를 방지하기 위해 더 구체적인 URL 경로부터 접근 권한을 부여한 다음 덜 구체적은 URL 경로에 접근 권한을 부여하는 습관을 들일 것

> Ant는 Maven과 Gradle에 밀려 거의 사용되지 않는 빌드 툴이지만, Ant에서 사용되는 Ant Pattern은 URL 경로 등을 지정하기 위한 Pattern 표현식으로 여러 오픈 소스에서 사용되고 있음

### 관리자 권한을 가진 사용자 정보 추가
- 현재까지 작성한 SecurityConfiguration 클래스에는 USER Role을 갖는 하나의 사용자만 InMemory User로 등록이 된 상태임
- 이제 ADMIN Role을 갖는 사용자 추가를 통해 ADMIN으로 설정한 화면에 접근이 가능한지 확인
```java
@Configuration
public class SecurityConfiguration {
    ...
    ...

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("kevin@gmail.com")
                        .password("1111")
                        .roles("USER")
                        .build();

        // (1)
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin@gmail.com")
                        .password("2222")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
// (코드 4-12) ADMIN Role을 가진 사용자 추가
```
- (1)과 같이 `admin@gmail.com` 이라는 InMemory User 하나를 추가함
  - `admin@gmail.com` 에게는 `ADMIN` Role이 부여됨

### 로그인 한 사용자 아이디 표시 및 사용자 로그아웃
- 현재 화면에서는 사용자가 로그인 한 후에 어떤 사용자가 로그인 했는지 알 수 없음
  - 또한 로그인 한 사용자가 로그아웃을 할 수 있는 기능도 없음
  - 또한 마이 페이지 링크 역시 로그인 한 사용자에게만 보이는 것이 좋음
```html
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5"> <!-- (1) -->
    <body>
        <div align="right" th:fragment="header">
            <a href="/members/register" class="text-decoration-none">회원가입</a> |
            <span sec:authorize="isAuthenticated()"> <!-- (2) -->
                <span sec:authorize="hasRole('USER')">  <!-- (3) -->
                    <a href="/members/my-page" class="text-decoration-none">마이페이지</a> |
                </span>
                <a href="/logout" class="text-decoration-none">로그아웃</a>  <!-- (4) -->
                <span th:text="${#authentication.name}">홍길동</span>님  <!-- (5) -->
            </span>
            
            <span sec:authorize="!isAuthenticated()"> <!-- (6) -->
                <a href="/auths/login-form" class="text-decoration-none">로그인</a>
            </span>
        </div>
    </body>
</html>
<!-- 코드(4-13) 로그아웃 및 권한별 메뉴 표시를 위한 코드 수정(templates/fragments/header.html) -->
```
- 타임리프 기반의 HTML 템플릿에서 사용자의 인증 정보나 권한 정보를 이용해 어떤 로직을 처리하가 위해서는 먼저 (1)과 같이 `sec` 태그를 사용하기 위한 XML 네임스페이스를 지정함
- (2)와 같이 태그 내부에서 `sec:authorize="isAuthenticated()"`를 지정하면 현재 페이지에 접근한 사용자가 인증에 성공한 사용자인지를 체크함
  - 즉, `isAuthenticated()`의 값이 `true`이면 태그 하위에 포함된 컨텐츠를 화면에 표시
- 마이페이지의 경우 ADMIN Role을 가진 사용자는 필요없는 기능이므로, (3)과 같이 `sec:authorize="hasRole('USER')"` 를 지정해서 USER Role을 가진 사용자에게만 표시
- (2)에서 isAuthenticated()의 값이 true 라는 의미는 이미 로그인 한 사용자라는 의미이므로 [로그인] 메뉴 대신에 (4)와 같이 [로그아웃] 메뉴를 표시합니다. (4)의 href="/logout" 에서 “/logout” URL은 SecutiryConfiguration 클래스에서 설정한 값과 같아야 함
- (5)에서는 th:text="${#authentication.name}"를 통해 로그인 사용자의 username을 표시하고 있음
  - 이 곳에는 우리가 로그인할 때 사용한 username이 표시됨
- (6)에서는 sec:authorize="!isAuthenticated()"를 통해 로그인한 사용자가 아니라면 [로그인] 버튼이 표시 되도록 함

> 코드 4-13과 같이 sec 태그를 사용하기 위해서는 아래와 같이 build.gradle의 dependecies{}에 의존 라이브러리를 추가해야함 <br>
> `dependencies {...implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'}`

- `SecurityConfiguration`에서 로그아웃 기능을 사용하기 위한 설정
```java
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin()
            .loginPage("/auths/login-form")
            .loginProcessingUrl("/process_login")
            .failureUrl("/auths/login-form?error")
            .and()
            .logout()                        // (1)
            .logoutUrl("/logout")            // (2)
            .logoutSuccessUrl("/")  // (3)
            .and()
            .exceptionHandling().accessDeniedPage("/auths/access-denied")
            .and()
            .authorizeHttpRequests(authorize -> authorize
                    .antMatchers("/orders/**").hasRole("ADMIN")
                    .antMatchers("/members/my-page").hasRole("USER")
                    .antMatchers("⁄**").permitAll()
            );
        return http.build();
    }
    
    ...
    ...
}
// (코드 4-14) 로그아웃 설정이 추가된 SecurityConfiguration 클래스
```
- 로그아웃에 대한 추가 설정을 위해서는 (1)과 같이 `logout()`을 먼저 호출해야 함
  - `logout()` 메서드는 로그아웃 설정을 위한 `LogoutConfigurer` 를 리턴
- (2)에서는 logoutUrl("/logout")을 통해 사용자가 로그아웃을 수행하기 위한 request URL을 지정
  - 여기서 설정한 URL은 코드 4-13 header.html의 로그아웃 메뉴에 지정한 href=”/logout”과 동일해야 함
- (3)에서는 로그아웃을 성공적으로 수행한 이후 리다이렉트 할 URL을 지정
  - 로그아웃 이후 샘플 애플리케이션의 메인 화면으로 리다이렉트
- 로그인 시 화면
  - ![image](https://user-images.githubusercontent.com/102513932/202981045-0eb2580e-5e91-4bda-b357-039d2822b1fc.png)