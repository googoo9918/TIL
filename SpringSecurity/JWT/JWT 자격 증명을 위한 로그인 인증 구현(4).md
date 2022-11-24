# JWT 자격 증명을 위한 로그인 인증 구현
- 사용자의 Username과 Password로 로그인 인증에 성공 시 로그인 인증에 성공한 사용자에게 JWT 생성 및 발 급

- 사용자의 로그인 인증 성공 후, JWT가 클라이언트에게 전달되는 과정
  - 1. 클라이언트가 서버 측에 로그인 인증 요청(Username/Password를 서버 측에 전송)
  - 2. 로그인 인증을 담당하는 Security Filter(`JwtAuthenticationFilter`)가 클라이언트의 로그인 인증 정보 수신
  - 3. Security Filter가 수신한 로그인 인증 정보를 AuthenticationManager에게 전달해 인증 처리를 위임
  - 4. AuthenticationManager가 **Custom UserDetailsService**(`MemberDetailsService`)에게 사용자의 UserDetails 조회를 위임
  - 5. **custom UserDetailsService**(`MemberDetailsService`)가 사용자의 크리덴셜을 DB에서 조회한 후, AuthenticationManager에게 사용자의 **UserDetails**를 전달
  - 6. AuthenticationManager가 **로그인 인증 정보와 UserDetails의 정보를 비교해 인증 처리**
  - 7. JWT 생성 후, 클라이언트의 응답으로 전달
- 4번과 6번은 Spring Security의 authenticationManger가 대신 처리해줌

## JWT 자격 증명을 위한 로그인 인증 구현
### Custom UserDetailsService 구현
- Spring Security에서 사용자의 로그인 인증을 처리하는 가장 단순하고 효과적인 방법은 DB에서 사용자의 크리덴셜을 조회
  - 조회한 크리덴셜을 AuthenticationManger에게 전달하는 Custom UserDetailsService를 구현하는 것임
```java
@Component
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    public MemberDetailsService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return new MemberDetails(findMember);
    }

    private final class MemberDetails extends Member implements UserDetails {
        // (1)
        MemberDetails(Member member) {
            setMemberId(member.getMemberId());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
            setRoles(member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
// (코드 4-65) UserDetailsService를 구현한 MemberDetailsService
```
- MemberDetailsService 클래스는 앞에서 학습했던 `HelloUserDetailsService(코드4-28)` 과 거의 동일한 코드
  - Spring/SpringSecurity_Basie/SpringSecurityBasic(2) 참고

### 로그인 인증 정보 역직렬화(Deserialization)를 위한 LoginDTO 클래스 생성
- LoginDTO 클래스는 클라이언트가 전송한 Username/Password 정보를 Security Filter에서 사용할 수 있도록 역질렬화 하기 위한 DTO 클래스임

```java
@Getter
public class LoginDto {
    private String username;
    private String password;
}
// (코드 4-66) 클라이언트의 인증 정보를 수신할 LoginDto
```

### JWT를 생성하는 JwtTokenizer 구현
- JwtTokenizer 클래스는 로그인 인증에 성공한 클라이언트에게 JWT를 생성 및 발급, 클라이언트의 요청이 들어올 때 마다 전달된 JWT를 검증하는 역할
```java
// (1)
@Component
public class JwtTokenizer {
    @Getter
    @Value("${jwt.secret-key}")
    private String secretKey;       // (2)

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;        // (3)

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;          // (4)

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

    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claims;
    }

    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }

    // (5)
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
```
- Spring/SpringSecurity/JWT/JWT 생성 및 검증 테스트 `JwtTokenizer` 클래스 코드 참고
- 추가된 부분
  - (1)에는 `JwtTokenizer` 클래스를 Spring Container(ApplicationContext)에 Bean으로 등록하기 위해 `@Component` 애너테이션을 추가
  - (2), (3), (4)는 JWT 생성 시 필요한 정보, 해당 정보는 applciation.yml 파일에서 로드함
    - (2)는 JWT 생성 및 검증 시 사용되는 Secret Key 정보
    - (3)은 Access Token에 대한 만료 시간 정보
    - (4)는 Refresh Token에 대한 만료 시간 정보
    - (5)의 `getTokenExpiration()` 메서드는 JWT의 만료 일시를 지정하기 위한 메서드로, JWT 생성 시 사용

#### application.yml
```yml
jwt:
  secret-key: ${JWT_SECRET_KEY}               
  # 민감한 정보는 시스템 환경 변수에서 로드한다.
  access-token-expiration-minutes: 30
  refresh-token-expiration-minutes: 420
#   (코드 4-27) JWT 정보가 포함된 application.yml
```
- 이때, application.yml에 정의한 프로퍼티명의 문자열과 시스템 환경 변수 값이 같지 않도록 주의해야함
- access-token-expiration-minutes는 Access Token의 만료 시간이며, 30분으로 설정
- refresh-token-expiration-minutes는 Refresh Token의 만료 시간이며, 420분으로 설정
- Access Token의 만료 시간이 Refresh Token의 만료 시간보다 짧은 것이 권장됨
  - 보안 강화를 이유로 Refresh Token을 제공하지 않는 애플리케이션도 있음

### 로그인 인증 요청을 처리하는 Custom Security Filter 구현
- 클라이언트의 로그인 인증 정보를 직접적으로 수신하여 인증 처리의 엔트리포인트(EntryPoint) 역할을 하는 Custom Filter 구현

```java
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {  // (1)
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;

    // (2)
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
    }

    // (3)
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();    // (3-1)
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class); // (3-2)

        // (3-3)
        UsernamePasswordAuthenticationToken authenticationToken =
                                                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);  // (3-4)
    }

    // (4)
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        Member member = (Member) authResult.getPrincipal();  // (4-1)

        String accessToken = delegateAccessToken(member);   // (4-2)
        String refreshToken = delegateRefreshToken(member); // (4-3)

        response.setHeader("Authorization", "Bearer " + accessToken);  // (4-4)
        response.setHeader("Refresh", refreshToken);                   // (4-5)
    }

    // (5)
    private String delegateAccessToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getEmail());
        claims.put("roles", member.getRoles());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    // (6)
    private String delegateRefreshToken(Member member) {
        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
// (코드 4-79) 로그인 인증을 처리하는 JwtAuthenticationFilter
```
- JwtAuthenticationFilter는 클라이언트의 로그인 인증 요청을 처리하는 엔트리포인트(Entrypoint)의 역할을 함
  - (1)에서는 `UsernamePasswordAuthenticaitonFilter`를 상속함
    - `UsernamePasswordAuthenticaitonFilter`는 폼 로그인 방식에서 사용하는 디폴트 Security Filter
      - 폼 로그인이 아니더라도 Username/Password 기반의 인증을 처리하기 위해 `UsernamePasswordAuthenticaitonFilter`를 확장해서 구현할 수 있음
- (2)에서는 AuthenticationManager와 JwtTokenizer를 DI 받고 있음
  - DI 받은 `AuthenticationManager` 는 로그인 인증 정보를 전달 받아 UserDetailsService와 인터랙션, 인증 여부를 판단함
  - DI 받은 `JwtTokenizer`는 클라이언트가 인증에 성공할 경우, JWT를 생성 및 발급하는 역할
- (3)의 `attemptAuthentication()`는 메서드 내부에서 인증을 시도하는 로직을 구현하면 됨
  - (3-1)에서는 클라이언트에게 전송한 Username과 Password를 DTO 클래스로 역직렬화 하기 위해 ObjectMapper 인스턴스 생성
  - (3-2)에서는 objectMapper.readValue(request.getInputStream(), LoginDto.class)를 통해 `ServletInputStream` 을 `LoginDto` 클래스의 객체로 역직렬화(Deserialization)
  - (3-3)에서는 Username과 Password 정보를 포함한 UsernamePasswordAuthenticationToken을 생성함
  - (3-4)에서는 UsernamePasswordAuthenticationToken을 AuthenticationManager에게 전달하면서 인증 처리를 위임함
- (4)의 `successfulAuthentication()` 메서드는 클라이언트의 인증 정보를 이용해 인증에 성공할 경우 호출됨
  - (4-1)에서 `authResult.getPrincipal()`로 Member 엔티티 클래스의 객체를 얻음
    - AuthenticationManager 내부에서 인증에 성공하면 인증된 Authentication 객체가 생성되면서 principal 필드에 Member 객체가 할당됨
  - (4-2)에서 `delegateAccessToken(member)` 메서드를 이용해 Access Token을 생성함
  - (4-3)에서 `delegateAccessToken(member)` 메서드를 이용해 Refresh Token을 생성함
- (5)와 (6)은 Access Token과 Refresh Token을 생성하는 구체적인 로직
  - `delegateAccessToken(member)`와 `delegateRefreshToken(member)` 메서드의 구체적인 로직은 Spring/SpringSecurity/JWT/JWT 생성 및 검증 테스트/JwtTokenizerTest 클래스 테스트 케이스 참고

### Custom Filter 추가를 위한 SecurityConfiguration 설정 추가 
- Custom Filter인 `JwtAuthenticationFilter`의 구현이 끝났다면 `JwtAuthenticationFilter`를 Spring Security Filter Chain에 추가해 로그인 인증을 처리하도록 해야 함
```java
public class SecurityConfigurationV2 {
    private final JwtTokenizer jwtTokenizer;

    public SecurityConfigurationV2(JwtTokenizer jwtTokenizer) {
        this.jwtTokenizer = jwtTokenizer;
    }

@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())  // 추가 (1)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                );
        return http.build();
    }
    // 추가(2)
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/v11/auth/login");

            builder.addFilter(jwtAuthenticationFilter);
        }
    }
}
// (코드 4-80) JwtAuthenticationFilter 추가를 위한 SecurityConfiguration 설정 추가
```
- Spring Security에서는 개발자가 직접 Custom Configurer를 구성해 Spring Security의 Configuration을 커스터마이징 할 수 있음
  - (1)과 같이 `apply()` 메서드에 Custom Configurer를 추가해 커스터마이징 된 Configuration을 추가할 수 있음
  - Custom Configurer는 쉽게 말해 Spring Security의 Configuration을 **개발자 입맞에 맞게 정의**할 수 있는 기능임
- (2)는 Custom Configurer인 `CustomFilterConfigurer`클래스임
  - `CustomFilterConfigurer`는 우리가 구현한 JwtAuthenticationFilter를 등록하는 역할을 함
  - (2-1)과 같이 AbstractHttpConfigurer를 상속해서 Custom Configurer를 구현 가능
    - `AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity>`와 같이 `AbstractHttpConfigurer` 를 상속하는 타입과 `HttpSecurityBuilder` 를 상속하는 타입을 제너릭 타입으로 지정 가능
  - (2-2)와 같이 configure() 메서드를 오버라이드해 Configutration을 커스터마이징 할 수 있음
  - (2-3)과 같이 `getSharedobject(AuthenticationManager.class)`를 통해 AuthenticationManager의 객체를 얻을 수 있음
    - `getSharedObject()`를 통해 Spring Security의 설정을 구성하는 SecurityConfigurer 간에 공유되는 객체를 얻을 수 있음
  - (2-4)에서 JwtAuthenticationFilter를 생성하며 JwtAuthenticationFilter에서 사용되는 AuthenticationManager와 JwtTokenizer를 DI 해줌
  - (2-5)에서는 setFilterProcessUrl() 메서드를 통해 디폴트 request URL인 "`/login`"을 "/v11/auth/login"으로 변경
  - (2-6)에서 addFilter() 메서드를 통해 JwtAuthenticationFilter를 Spring Security Filter Chain에 추가함
- 구현한 JwtAuthenticationFilter를 SecurityConfiguration에 추가했음
  - 로그인 인증 시, JWT 토큰이 response로 잘 전달되는지 테스트를 통해 확인

## 로그인 인증 테스트
### 1. 회원 가입 요청
- ![zeHz6LyysvIq5PW-aijca-1663828171421 (1)](https://user-images.githubusercontent.com/102513932/203760534-8a38b8c3-f921-4624-91b6-b3af39e2cfb8.png)

### 로그인 인증 요청
- 회원가입 request 전송 시 입력한 이메일 주소와 패스워드로 로그인 인증 request를 전송 
- ![_PJ0L-GkJfdCBpTiBgUhL-1663828252467](https://user-images.githubusercontent.com/102513932/203763916-1d16ea65-9b22-4b6e-b8ea-9fe53356c236.png)
  - JWT 자격 증명을 위한 로그인 인증
  - 애플리케이션에서 로그인 인증이 성공적으로 수행 시, 아래쪽 Headers 탭에서 Authorization 키의 값으로 Access Token이, Refresh 값으로 Refresh Token이 포함되어 있음
  - 클라이언트 쪽에서는 서버 측의 리소스를 사용하기 위한 request를 전송할 때 마다 전달 받은 JWT를 request header에 포함 후 , 클라이언트의 자격 증명 정보로 사용하면 됨

### 로그인 인증 성공 및 실패에 따른 추가 처리
- 보다 나은 애플리케이션 구현을 위해 기능 추가
- Spring Security에서는 Username/Password 기반 로그인 인증에 성공 시 , 로그를 기록한다거나 로그인에 성공한 사용자 정보를 response로 전송하는 등의 추가 처리를 할 수 있는 핸들러(`AuthenticationSuccessHandler`)를 지원
  - 로그인 인증 실패 시에도 마찬가지로 인증 실패에 대해 추가 처리를 할 수 있는 핸들러(`AuthenticationFailureHandler`)를 지원함

### AuthenticationSuccessHandler 구현
```java
@Slf4j
@Component   // (1)
public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {  // (2)
    // (3)
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 인증 성공 후, 로그를 기록하거나 사용자 정보를 response로 전송하는 등의 추가 작업을 할 수 있다.
        log.info("# Authenticated successfully!");
    }
}
// (코드 4-81) 로그인 인증 성공 시 추가 작업을 할 수 있는 MemberAuthenticationSuiccessHandler
```
- MemberAuthenticationSuccessHandler가 ApplicationContext의 등록 대상이 되도록 (1)과 같이 `@Component` 애너테이션을 추가함
  - ApplicationContext에 등록된 `MemberAuthenticationSuccessHandler` Bean 객체는 SecurityConfiguration에서 `JwtAuthenticationFilter` 추가 시, AuthenticationSuccessHandler로 등록할 예정임
- 우리가 직접 정의하는 Custom AuthenticationSuccessHandler는 (2)와 같이 `AuthenticationSuccessHandler` 인터페이스를 구현해야 함
- AuthenticationSuccessHandler 인터페이스에는 `onAuthenticationSuccess()`추상 메서드가 정의되어 있음
  - `onAuthenticationSucess()`메서드를 구현해서 추가 처리 할 것
  - (3)에서는 단순히 로그만 출력하고 있지만, **Authentication 객체에 사용자 정보를 얻은 후, HttpServletResponse로 출력 스트림을 생성하여 response를 전송할 수 있음**

### AuthenticationFailureHandler 구현
```java
@Slf4j
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {  // (1)
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // 인증 실패 시, 에러 로그를 기록하거나 error response를 전송할 수 있다.
        log.error("# Authentication failed: {}", exception.getMessage());

        sendErrorResponse(response);  // (2)
    }
    
    private void sendErrorResponse(HttpServletResponse response) throws IOException {
        Gson gson = new Gson();     // (2-1)
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.UNAUTHORIZED); // (2-2)
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);    // (2-3)
        response.setStatus(HttpStatus.UNAUTHORIZED.value());          // (2-4)
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));   // (2-5)
    }
}
// (코드 4-82) 로그인 인증 실패 시, 추가 작업을 할 수 있는 MemberAuthenticationFailureHandler
```
- 우리가 직접 정의하는 Custom AuthenticationFailureHandler는 (1)과 같이 AuthenticationFailureHandler 인터페이스를 구현해야함
- AuthenticationSuccessHandler 인터페이스에는 `onAuthenticationFailure()`추상 메서드가 정의되어 있으며, `onAuthenticationFailure()` 메서드를 구현해서 추가 처리
- (2)에서는 바로 아래에 있는 sendErrorResponse() 메서드를 호출해 출력 스트림에 Error 정보를 담고있음
  - (2-1)에서는 Error 정보가 담긴 객체(ErrorResponse)를 JSON 문자열로 변환하는데 사용되는 Gson 라이브러리의 인스턴스를 생성함
  - (2-2)에서는 ErrorResponse 객체를 생성함
    - ErrorResponse.of() 메서드로 `Httpstatus.UNAUTHORIZED` 상태 코드를 전달
    - `HttpStatus.UNAUTHORIZED(401)` 상태 코드는 인증에 실패할 경우 전달할 수 있는 HTTP status임
  - (2-3)에서는 response의 Contetnt Type이 "`application/json`"이라는 것을 클라이언트에게 알려줄 수 있도록 `MediaType.APPLICATION_JSON_VALUE`를 HTTP Header에 추가함
  - (2-4)에서는 response의 status가 401임을 클라이언트에게 알려줄 수 있도록 `HttpStatus.UNAUTHORIZED.value()`를 HTTP Header에 추가함
  - (2-5)에서는 Gson을 이용해 ErrorResponse 객체를 JSON 포맷 문자열로 변환 후, 출력 스트림을 생성