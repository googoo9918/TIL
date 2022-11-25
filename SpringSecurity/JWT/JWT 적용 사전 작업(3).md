# JKWT 적용을 위한 사전 작업
## 의존 라이브러리 추가
```java
dependencies{
    implementation 'org.springframework.boot:spring-boot-starter-security' // (1)

  // (2) JWT 기능을 위한 jjwt 라이브러리
	implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
	runtimeOnly	'io.jsonwebtoken:jjwt-jackson:0.11.5'
}
```
- (1)과 같이 애플리케이션에 Spring Security를 적용하기 위해 추가
- (2)와 같이 Spring Security 기반의 애플리케이션에 JWT를 적용하기 위해 `jjwt` 라이브러리를 추가함

## 애플리케이션 실행
- ![image](https://user-images.githubusercontent.com/102513932/203696721-d9af2a06-6ea8-4f32-992b-ae3f42abe51a.png)
  - Username : user
  - Password : 로그 출력 부분 확인
- ![image](https://user-images.githubusercontent.com/102513932/203696807-6b67bcd2-f0e3-444e-86e5-7910e1a157cc.png)
  - 로그인 인증에는 성공했지만 Controller 같은 엔드포인트가 없어서 발생하는 404 에러
- Spring Security 기반 기본적인 프로젝트 설정은 정상적으로 완료된 것을 확인!

## SecurityConfiguration 추가
- JWT를 적용하기 전 Spring Security를 이용한 보안 강화를 위해 최소한의 보안 구성을 진행
```java
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().sameOrigin() // (1)
            .and()
            .csrf().disable()        // (2)
            .cors(withDefaults())    // (3)
            .formLogin().disable()   // (4)
            .httpBasic().disable()   // (5)
            .authorizeHttpRequests(authorize -> authorize
                    .anyRequest().permitAll()                // (6)
            );
        return http.build();
    }

    // (7)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // (8)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));   // (8-1)
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));  // (8-2)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();   // (8-3)
        source.registerCorsConfiguration("\/**", configuration);      // (8-4)     주의 사항: 컨텐츠 표시 오류로 인해 '/**'를 '\/**'로 표기했으니 실제 코드 구현 시에는 '\(역슬래시)'를 빼 주세요.
        return source;
    }
}
// [코드 4-61] SecurityConfiguration(V1) 설정
```
- H2 웹 콘솔의 화면 자체가 내부적으로 태그를 사용
  - 개발 환경에서는 H2 웹 콘솔을 정상적으로 사용할 수 있도록 (1)과 같이 .`frameOptions().sameOrigin()` 을 추가
    - `.frameOptions().sameOrigin()` 을 호출하면 동일 출처로부터 들어오는 request만 페이지 렌더링을 허용
- (2)와 같이 CSRF(Cross-Site Request Forgery) 공격에 대한 Spring Security에 대한 설정을 비활성화
- (3)에서는 CORS 설정을 추가합니다. `.cors(withDefaults()`)` 일 경우, `corsConfigurationSource`라는 이름으로 등록된 Bean을 이용
  - CORS를 처리하는 가장 쉬운 방법은 CorsFilter를 사용하는 것인데 CorsConfigurationSource Bean을 제공함으로써 CorsFilter를 적용할 수 있음
> CORS(Cross-Origin Resource Sharing) <br>
> 애플리케이션 간에 출처(Origin)가 다를 경우, 스크립트 기반의 HTTP 통신(XMLHttpRequest, Fetch API)을 통한 리소스 접근이 제한되는데, CORS는 출처가 다른 스크립트 기반 HTTP 통신을 하더라도 선택적으로 리소스에 접근할 수 있는 권한을 부여하도록 브라우저에 알려주는 정책이다. <br>
> 로컬 환경에서 Postman을 사용하여 애플리케이션의 엔드포인트를 호출할 경우, CORS 설정이 필요없지만 프로젝트 수행 시 프론트엔드 웹앱과의 HTTP 통신에서 에러를 만날 수 있으므로 사전 학습 차우너에서 설정을 참고함

- Hello Spring Security 챕터에서 사용한 로그인 인증 방식이 SSR(Server Side Rendering) 애플리케이션에서 주로 사용하는 폼 로그인 방식임
  - 이번 챕터에서는 CSR(Client Side Rendering) 방식에서 주로 사용하는 JSON 포맷으로 Username과 Password를 전송하는 방식을 사용
    - (4)와 같이 폼 로그인 방식을 비활성화
- HTTP Basic 인증은 request를 전송할 때 마다 Username/Password 정보를 HTTP Header에 실어서 인증을 하는 방식으로 현재는 사용하지 않으므로 (5)와 같이 HTTP Basic 인증 방식을 비활성화
> 폼 로그인과 HTTP Basic 인증을 disable하면 해당 인증과 관련된 Security Filter(`UsernamePasswordAuthenticationFilter`, `BasicAuthenticationFilter` 등)가 비활성화

- (6)에서는 JWT를 적용하기 전이므로 우선은 모든 HTTP request 요청에 대해서 접근을 허용하도록 설정
  - 추후 JWT 적용 후, URL 별로 적절한 권한 적용 예정
- (7)에서 PasswordEncoder Bean 객체를 생성
- (8)에서는 CorsConfigurationSource Bean 생성을 통해 구체적인 CORS 정책을 설정
  - (8-1)에서 `setAllowedOrigins()`을 통해 모든 출처(Origin)에 대해 스크립트 기반의 HTTP 통신을 허용하도록 설정
    - 이 설정은 운영 서버 환경에서 요구사항에 맞게 변경이 가능
  - (8-1)에서는 setAllowedMethods()를 통해 파라미터로 지정한 HTTP Method에 대한 HTTP 통신을 허용
  - (8-3)에서는 CorsConfigurationSource 인터페이스의 구현 클래스인 UrlBasedCorsConfigurationSource 클래스의 객체를 생성
  - (8-4)에서는 모든 URL에 앞에서 구성한 CORS 정책(CorsConfiguration)을 적용

## 회원 가입 로직 수정
- Spring Security가 적용되지 않았던 커피 주문 샘플 애플리케이션에서는 회원 등록 시, 회원의 인증과 관련된 정보(패스워드, 사용자권한)가 필요 없었지만 이번 챕터에서는 필요함

### MemberDto.Post 클래스에 패스워드 필드 추가
```java
public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        @Email
        private String email;

        // (1) 패스워드 필드 추가
        @NotBlank
        private String password;

        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        private String phone;
    }

    ...
    ...
}
// (코드 4-62) 패스워드 필드가 추가된 MemberDto.Post
```
- 실제 서비스에서는 패스워드를 한 번만 입력하지 않고, 사용자가 입력한 패스워드가 맞는지 재확인 하기 위해 패스워드 입력 확인 필드가 추가로 존재하는 경우가 대부분
  - 입력한 두 패스워드가 일치하는지 검증하는 로직 필요
- 또한 패스워드 생성 규칙에 대한 유효성 검증 또한 실시함
- 현재는 JWT 적용에 집중하기 위해 간단한 예시를 듬

### Member 엔티티 클래스에 패스워드 필드 추가
```java
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    // (1) 추가
    @Column(length = 100, nullable = false)
    private String password;

    ...
    ...

    // (2) 추가
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    ...
    ...
}
// (코드 4-63) Member 엔티티 클래스에 패스워드 필드 추가
```
- 코드 4-63의 (1)과 같이 Member 엔티티 클래스에도 패스워드 필드를 추가
  - password는 암호화 되어 저장되기 때문에 컬럼의 길이는 100으로 지정
    - 패스워드 입력 규칙에 따라 password 길이는 달라질 수 있음
- (2)에서는 @ElementCollection 애너테이션을 이용해 사용자 등록 시, 사용자의 권한을 등록하기 위한 권한 테이블을 생성

### 사용자 등록 시, 패스워드와 사용자 권한 저장
```java
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher publisher;

    // (1) 추가
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    // (2) 생성자 DI용 파라미터 추가
    public MemberService(MemberRepository memberRepository,
                         ApplicationEventPublisher publisher,
                         PasswordEncoder passwordEncoder,
                         CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.publisher = publisher;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());

        // (3) 추가: Password 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        // (4) 추가: DB에 User Role 저장
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        Member savedMember = memberRepository.save(member);

        publisher.publishEvent(new MemberRegistrationApplicationEvent(this, savedMember));
        return savedMember;
    }

    ...
    ...
}
// [코드 4-64] 사용자 등록 시, 패스워드와 권한 정보 추가
```
- (1)과 (2)에서는 `PasswordEncoder`와 `CustomAuthorityUtils` 클래스를 DI 받도록 필드를 추가
  - `CustomAuthorityUtils`는 따로 생성
- (3)에서는 패스워드를 단방향 암호화
- (4)에서는 등록하는 사용자의 권한 정보를 생성

- JWT 적용을 위한 사전 작업은 끝남
- Spring Security 기반의 애플리케이션에 **JWT를 적용하기 위해서는 jjwt나 Java JWT 같은 별도 라이브러리가 필요**함을 기억하자