# OAuth 2와 JWT를 이용한 샘플 애플리케이션 구현
- 여태까지는 SSR 방식으로 인증 시스템을 구현함
- 지금은 CSR 방식의 애플리케이션에 Google의 OAuth 2 인증 시스템을 적용
  - OAuth 2 인증 시스템을 통해 인증에 성공한 사용자에 대한 자격 증명 정보를 JWT로 제공

### Frontend와 Backend 간의 OAuth 2 인증 처리 흐름
- ![image](https://user-images.githubusercontent.com/102513932/204718935-1070ceba-e28e-481b-883f-6e59d60a485a.png)
  -  Frontend와 Backend 간의 OAuth 2 인증 처리 흐름
  -  (1) Resource Owner가 웹 브라우저에서 ‘Google 로그인 링크’를 클릭
  -  (2) Frontend 애플리케이션에서 Backend 애플리케이션의 `http://localhost:8080/oauth2/authorization/google로` request를 전송
     -  URI의 request는 `OAuth2LoginAuthenticationFilter` 가 처리
  -  (3) Google의 로그인 화면을 요청하는 URI로 리다이렉트
     -  Authorization Server가 Backend 애플리케이션 쪽으로 Authorization Code를 전송할 Redirect URI(`http://localhost:8080/login/oauth2/code/google`)를 쿼리 파라미터로 전달함
        -  Redirect URI는 Spring Security가 내부적으로 제공
  -  (4) Google 로그인 화면을 오픈
  -  (5) Resource Owner가 Google 로그인 인증 정보를 입력해 로그인을 수행
  -  (6) 로그인에 성공 시 (3)에서 전달한 Backend Redirect URI(`http://localhost:8080/login/oauth2/code/google`)로 Authorization Code를 요청
  -  (7) Authorization Server가 Backend 애플리케이션에서 Authorization code를 응답으로 전송
  -  (8) Backend 애플리케이션이 Authorization Server에게 Access Token을 요청
  -  (9) Authorization Server가 Backend 애플리케이션에게 Access Token을 응답으로 전송
     - 여기서의 Access Token은 Google Resource Server에게 Resource를 요청하는 용도로 사용
  -  (10) Backend 애플리케이션이 Resource Server에게 User Info를 요청
     - 여기서의 User Info는 Resource Owner에 대한 이메일 주소, 프로필 정보 등을 의미
  -  (11) Resource Server가 Backend 애플리케이션에게 User Info를 응답으로 전송
  -  (12) Backend 애플리케이션은 JWT로 구성된 Access Token과 Refresh Token을 생성
     -  Frontend 애플리케이션에게 JWT(Access Token과 Refresh Token)를 전달하기 위해 Frontend 애플리케이션 (`http://localhost?access_token={jwt-access-token}&refresh_token={jwt-refresh-token}`)으로 Redirect함
- (6)부터 (11)까지는 Spring Security에서 내부적으로 알아서 처리해주기 때문에, 따로 작성할 것은 없음
- OAuth 2 인증 처리 정상 동작 확인을 위해 웹서버에서 실행되는 Frontend 애플리케이션 필요

## Frontend 애플리케이션 준비
### 아파치 웹서버 설치
- [아파치 웹 서버 다운로드](https://www.apachelounge.com/download/)
- 압축 해제 후
  - `Apache24` 디렉토리를 C:\ 디렉토리로 이동
    - 최종 경로 : `C:\Apache24`
  - conf 파일의 httpd.conf 파일을 메모장 등의 에디터로 오픈
    - ServerName 주석 해제 후 수정
      - `ServerName localhost:80`
      - 나머지는 디폴트 값을 그대로 사용
  - cmd를 관리자 모드로 실행
    - `c:\Apache24\bin` 디렉토리로 이동
    - `httpd.exe -k install` 명령 입력
  - Apach24\bin\ApacheMonitor.exe로 아파치 웹 서버 실행
    - 우측 하단 빠른 실행 화면에서 실행/중단 설정 가능
  - 웹 브라우저에서 `http:localhost`로 접속 시 아래와 같은 화면 출력
    - 아파치 웹서버 정상 실행 시
    - ![image](https://user-images.githubusercontent.com/102513932/204725931-0f491421-5bf5-4cd9-895c-c78f6ccfcde3.png)

### Frontend 샘플 애플리케이션을 아파치 웹서버에 배포
세 개의 HTML 파일을 에디터로 작성한 후, `C:\Apache24\htdocs` 디렉토리로 위치시킴
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OAuth2 + JWT Frontend</title>
</head>
<body>
    <h2>Welcome to OAuth 2.0 + JWT Spring Security</h2>
    <a href="http://localhost:8080/oauth2/authorization/google">Google로 로그인</a>
</body>
</html>
```
- `index.html`에서 [Google로 로그인]버튼 클릭 시 Backend 애플리케이션으로 request 전송
  - Google 로그인 화면 오픈

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OAuth2 + JWT My page</title>
</head>
<body>
    <script type="text/javascript">
        let accessToken = (new URL(location.href)).searchParams.get('access_token');
        let refreshToken = (new URL(location.href)).searchParams.get('refresh_token');

        localStorage.setItem("accessToken", accessToken)
        localStorage.setItem("refreshToken", refreshToken)

        location.href = 'my-page.html'
    </script>
</body>
</html>
```
- receive-token.html은 Backend 애플리케이션에서 전달 받은 JWT Access Token과 Refresh Token을 웹 브라우저의 LocalStorage에 저장한 후, my-page.html로 이동함

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OAuth2 + JWT My page</title>
</head>
<body>
    <h2>My Page</h2>
    <h3>아래의 토큰을 이용해서 Backend 애플리케이션의 리소스를 요청할 수 있습니다.</h3>
    <p>
        <span>Access Token: </span><span id="accessToken" style="color: blue"></span>
    </p>
    <p>
        <span>Refresh Token: </span><span id="refreshToken" style="color: blue"></span>
    </p>
    <script type="text/javascript">
        let accessToken = localStorage.getItem('accessToken')
        let refreshToken = localStorage.getItem('refreshToken');

        document.getElementById("accessToken").textContent = accessToken;
        document.getElementById("refreshToken").textContent = refreshToken;
    </script>
</body>
</html>
```
- my-page.html에서는 LocalStorage에 저장된 JWT Access Token과 Refresh Token을 로그해 웹 브라우저에 표시함


## Backend 애플리케이션에 OAuth 2 인증 기능 적용
### JwtTokenizer 추가
- JWT를 생성하고 JWT를 검증해주는 JwtTokenizer 구현
```java
@Component
public class JwtTokenizer {
    @Getter
    @Value("${jwt.key.secret}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // 검증 후, Claims을 반환 하는 용도
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claims;
    }

    // 단순히 검증만 하는 용도로 쓰일 경우
    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }

    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }
}
// [코드 4-104] JwtTokenizer 코드
// JWT 유닛 코드 참고
```

### application.yml 설정
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
    properties:
      hibernate:
        format_sql: true  # (3) SQL pretty print
  sql:
    init:
      data-locations: classpath*:db/h2/data.sql
  security:
    oauth2:
      client:
        registration:
          google:
            clientId: ${G_CLIENT_ID}
            clientSecret: ${G_CLIENT_SECRET}
            scope:
              - email                              // (1)
              - profile                            // (2)
logging:
  level:
    org:
      springframework:
        orm:
          jpa: DEBUG
server:
  servlet:
    encoding:
      force-response: true
mail:
  address:
    admin: admin@gmail.com
jwt:
  key:
    secret: ${JWT_SECRET_KEY}               # 민감한 정보는 시스템 환경 변수에서 로드한다.
  access-token-expiration-minutes: 30
  refresh-token-expiration-minutes: 420
```
- JWT와 OAuth 2를 함께 사용, JWT와 OAuth2의 설정이 합쳐짐
- (1), (2)와 같이 scope 값을 직접 지정 시 해당 범위만큼의 Resource를 Client(백엔드 애플리케이션)에게 제공함
  - (1)은 이메일 정보
  - (2)는 프로필 정보를 의미

### JwtVerificationFilter 추가
- `JwtVerificationFilter`는 OAuth 2 인증에 성공 시 Frontend 애플리케이션 쪽에서 request를 전송할 때 마다 Authorization header에 실어 보내는 Access Token에 대한 검증을 수행하는 Filter
  - [JWT를 이용한 자격 증명 및 검증 구현](https://github.com/ssu18/TIL/blob/main/SpringSecurity/JWT/JWT%20%EC%83%9D%EC%84%B1%20%EB%B0%8F%20%EA%B2%80%EC%A6%9D%20%ED%85%8C%EC%8A%A4%ED%8A%B8(2).md#jwt-%EA%B2%80%EC%A6%9D-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84) 챕터 참고

### AuthenticationSuccessHandler 구현
- OAuth2 인증에 성공하면 호출되는 핸들러
  - JWT를 생성하고, Frotend 쪽으로 JWT를 전송하기 위해 Redirect하는 로직 구현

```java
public class OAuth2MemberSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {   // (1)
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;

    // (2)
    public OAuth2MemberSuccessHandler(JwtTokenizer jwtTokenizer,
                                      CustomAuthorityUtils authorityUtils,
                                      MemberService memberService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var oAuth2User = (OAuth2User)authentication.getPrincipal();
        String email = String.valueOf(oAuth2User.getAttributes().get("email")); // (3)
        List<String> authorities = authorityUtils.createRoles(email);           // (4)

        saveMember(email);  // (5)
        redirect(request, response, email, authorities);  // (6)
    }

    private void saveMember(String email) {
        Member member = new Member(email);
        member.setStamp(new Stamp());
        memberService.createMember(member);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String username, List<String> authorities) throws IOException {
        String accessToken = delegateAccessToken(username, authorities);  // (6-1)
        String refreshToken = delegateRefreshToken(username);     // (6-2)

        String uri = createURI(accessToken, refreshToken).toString();   // (6-3)
        getRedirectStrategy().sendRedirect(request, response, uri);   // (6-4)
    }

    private String delegateAccessToken(String username, List<String> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", authorities);

        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(String username) {
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
//                .port(80)
                .path("/receive-token.html")
                .queryParams(queryParams)
                .build()
                .toUri();
    }

}
// [코드 4-106] OAuth2MemberSuccessHandler
```
- OAuth 2 인증이 성공적으로 수행될 때 호출되는 `OAuth2MemberSuccessHandler` 코드
  - `OAuth2MemberSuccessHandler` 클래스는 OAuth 2 인증 후, Frontend 애플리케이션 쪽으로 JWT를 전송하는 핵심 역할 담당
- (1)과 같이 `simpleUrlAuthenticationSuccessHandler` 상속 시 Redirect를 손쉽게 할 수 있는 `getRedirectStrategy().sendRedirect()`같은 API 사용 가능
- (2)와 같이 필요한 객체 DI받음
- (3)에서는 Authentication 객체로부터 얻어낸 OAuth2User 객체로부터 Resource Owner의 이메일 주소를 얻음
- (4)에서는 CustomAuthorityUtils를 이용해 권한 정보를 생성함
  - CustomAuthorityUtils의 코드는 JWT에서 사용한 코드를 그대로 사용
- (5)에서는 Resource Owner의 이메일 주소를 DB에 저장함
  - OAuth 2의 특성상 Resource Owner의 크리덴셜을 Backend 애플리케이션에서 관리하지는 않음
    - 다만 Backend 애플리케이션의 Resource와 연관 관계를 맺기 위해 최소한의 정보는 Backend 쪽에서 관리해도 무방함
- (6)에서는 Access Token과 Refresh Token을 생성해 Frontend 애플리케이션에 전달하기 위해 Redirect함
  - (6-1)과 (6-2)에서는 JWT Acccess Token과 Refresh Token을 생성함
    - JWT 유닛에서 이미 사용한 코드와 동일한 코드
  - (6-3)에서는 Frontend 애플리케이션 쪽의 URL 생성
    - `createURI()`메서드에서 `UriComponentsBuilder`를 이용해 Access Token과 Refresh Token을 포함한 URL을 생성함
      - Port 설정을 따로 하지 않으면 기본값은 80포트임
  - (6-4)에서는 SimpleUriAuthenticationSuccessHandler에서 제공하는 `sendRedirect()`메서드를 이용해 Frontend 애플리케이션 쪽으로 리다이렉트 함

### SecurityConfiguration 설정
- 이전 유닛에서 모두 학습한 내용임
```java
import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer,
                                   CustomAuthorityUtils authorityUtils,
                                   MemberService memberService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberService = memberService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().sameOrigin()
            .and()
            .csrf().disable()
            .cors(withDefaults())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling()  // 추가
            .authenticationEntryPoint(new MemberAuthenticationEntryPoint())  // 추가
            .accessDeniedHandler(new MemberAccessDeniedHandler())            // 추가
            .and()
            .apply(new CustomFilterConfigurer())  // 추가
            .and()
                .authorizeHttpRequests(authorize -> authorize // url authorization 전체 추가
//                        .antMatchers(HttpMethod.POST, "/*/members").permitAll()    // OAuth 2로 로그인하므로 회원 정보 등록 필요 없음.
//                        .antMatchers(HttpMethod.PATCH, "/*/members/**").hasRole("USER") // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
//                        .antMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN")  // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
//                        .antMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("USER", "ADMIN")  // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
//                        .antMatchers(HttpMethod.DELETE, "/*/members/**").hasRole("USER") // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
                        .antMatchers(HttpMethod.POST, "/*/coffees").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/*/coffees/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/*/coffees/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/*/coffees").permitAll()
                        .antMatchers(HttpMethod.DELETE, "/*/coffees").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/*/orders").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/*/orders").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/*/orders/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/*/orders").hasRole("USER")
                        .anyRequest().permitAll()
            )
            .oauth2Login(oauth2 -> oauth2
                    .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService))  // (1)
            );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("\/**", configuration); // 주의 사항: 컨텐츠 표시 오류로 인해 '/**'를 '\/**'로 표기했으니 실제 코드 구현 시에는 '\(역슬래시)'를 빼 주세요.
        return source;
        return source;
    }
    
    // 추가
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
            
            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // (2)
        }
    }
}
// [코드 4-107] SecurityConfiguration
```
- `//추가`라고 표시된 코드들은 JWT 유닛에서 모두 설명한 코드임
- (1)에서는 OAuth 2 로그인 설정에 `.successHandler()`를 통해 OAuth 2 인증이 성공한 뒤 실행되는 핸들러를 추가함
  - `OAuth2MemberSuccessHandler` 객체를 생성하며 OAuth2MemberSuccessHandler에서 필요한 의존 객체를 DI함
- (2)와 같이 JwtVerificationFilter를 OAuth2LoginAuthenticationFilter 뒤에 추가함
- OAuth 2 인증을 사용하므로 Backend 애플리케이션 쪽에서는 MemberController를 사용할 일이 현재로서는 없음
  - MemberController의 핸들러 메서드 쪽 URI에 대한 접근 권한은 주석 처리

## 애플리케이션 테스트
- Backend 애플리케이션을 IDE에서 실행
- Frontend 쪽 아파치 웹서버가 실행되어있는지 확인
- 웹 브라우저에 `http:localhost` 입력
- Front 애플리케이션의 화면을 오픈
  - ![image](https://user-images.githubusercontent.com/102513932/204800295-09814ab7-6f38-41e7-8b8b-6570a55946b8.png)
    - Frontend 애플리케이션 화면
      - [Google로 로그인]버튼 클릭
        - 로그인 인증에 성공 시 다음과 같은 화면 출력
  - ![image](https://user-images.githubusercontent.com/102513932/204800553-febd0cf3-74bc-412e-8845-f52ea4783fa6.png)
    - JWT Access Token과 Refresh Token이 화면에 표시된 모습
    - JWT Access Token은 Backend 애플리케이션의 Resource를 요청하는 Controller의 핸들러 메서드를 호출할 때 Authorization header에 추가해서 사용함
    - Refresh Token은 Access Token이 만료되었을 때, Access Token을 새로 발급받고자 할 때 사용
      - 이 두 Token을 얼마나 안전히 잘 보관하고 사용할지는 Frontend 애플리케이션의 책임 영역임

### 정리
- 여태 학습한 OAuth 2 인증 방식은 Google의 OAuth 2 인증 시스템을 이용한 방식임
- Google과 같은 OAuth 2 인증 시스템을 자체적으로 구축하기 위해 Authorization Server와 Resource Server를 구현할 수 있음
- 이 경우, Authorization Server와 Resource Server 간에도 JWT 이용 가능
  - Spring Security에서는 Authorization SErver와 Resource Server 간의 통신에 JWT를 이용할 수 있는 API를 제공
  - 또한 이전에는 JWT에 대한 서명을 대칭키 방식을 사용했지만, JWT의 보안성을 강화하기 위해 비대칭키 방식의 서명 또한 사용할 수 있음
- 비대칭키로 JWT를 암복호화 하는 방식은 앞서 학습한 `OAuth2MemberSuccessHandler`에서 Frontend 애플리케이션 쪽으로 JWT를 쿼리파라미터로 추가한 뒤 리다이렉트 할 경우에도 사용 가능함