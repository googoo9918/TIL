### 목차
- [Hello Spring Security 샘플 애플리케이션을 통한 Sping Security 기본 구조(2)](#hello-spring-security-샘플-애플리케이션을-통한-sping-security-기본-구조2)
  - [데이터베이스 연동없는 로그인 인증](#데이터베이스-연동없는-로그인-인증)
    - [PasswordEncoder Bean 등록](#passwordencoder-bean-등록)
    - [MemberService Bean 등록을 위한 JavaConfiguration 구성](#memberservice-bean-등록을-위한-javaconfiguration-구성)
      - [InMemory User 등록을 위한 InMemoryMemberService 클래스](#inmemory-user-등록을-위한-inmemorymemberservice-클래스)
      - [데이터베이스에 User를 등록하기 위한 DBMemberService 클래스](#데이터베이스에-user를-등록하기-위한-dbmemberservice-클래스)
      - [JavaConfiguration 구성](#javaconfiguration-구성)
      - [InMemoryMemberService 구현](#inmemorymemberservice-구현)
  - [데이터베이스 연동을 통한 로그인 인증](#데이터베이스-연동을-통한-로그인-인증)
    - [Custom UserDetailsService를 사용하는 방법](#custom-userdetailsservice를-사용하는-방법)
      - [SecurityConfiguration의 설정 변경 및 추가](#securityconfiguration의-설정-변경-및-추가)
      - [JavaConfiguration의 Bean 등록 변경](#javaconfiguration의-bean-등록-변경)
      - [DBMemberService 구현](#dbmemberservice-구현)
      - [Custom UserDetailsService 구현](#custom-userdetailsservice-구현)
      - [H2 웹 콘솔에서 등록한 회원 정보 확인 및 로그인 인증 테스트](#h2-웹-콘솔에서-등록한-회원-정보-확인-및-로그인-인증-테스트)
      - [Custom UserDetails 구현](#custom-userdetails-구현)
    - [User의 Role을 DB에서 관리하기](#user의-role을-db에서-관리하기)
      - [User의 권한 정보를 저장하기 위한 테이블 생성](#user의-권한-정보를-저장하기-위한-테이블-생성)
      - [회원 가입 시, User의 권한 정보를 DB에 저장](#회원-가입-시-user의-권한-정보를-db에-저장)
      - [로그인 인증 시, User의 권한 정보를 DB에서 조회하는 작업](#로그인-인증-시-user의-권한-정보를-db에서-조회하는-작업)
    - [Custom AuthentiacationProvider를 사용하는 방법](#custom-authentiacationprovider를-사용하는-방법)
  - [핵심 포인트](#핵심-포인트)
# Hello Spring Security 샘플 애플리케이션을 통한 Sping Security 기본 구조(2)
- 현재 회원 가입으로 전달 받은 회원 정보를 Spring Security가 알 수 있는 어떠한 처리도 하지 않았음 
  - 현재 Inmemory User 사용, 애플리케이션 실행 후 InMemory User 추가 등록할 수 있게 변경

## 데이터베이스 연동없는 로그인 인증
- 새로운 User 등록 작업 추가

> 참고 : 여기서 User 등록은 DB에 회원 정보를 등록하는 것이 아님 <br>
> 현재 DB를 연동하지 않고, Spring Security에서 지원하는 InMemory User를 사용 중이므로, 현재 User 등록은 메모리에 등록하는 InMemory User임

- 회원 가입 폼을 통한 InMemory User 등록
  - PasswordEncoder Bean 등록
  - MemberService Bean 등록을 위한 JavaConfiguration 구성
  - InMemoryMemberService 클래스 구현

### PasswordEncoder Bean 등록
- PasswordEncoder는 Spring Security에서 제공하는 패스워드 암호화 기능을 제공하는 컴포넌트
  - 회원 가입 폼을 통해 애플리케이션에 전달되는 패스워드는 암호화 되지 않은 플레인 텍스트(Plain Text)임
  - 따라서 회원 가입 폼에서 전달 받은 패스워드는 InMemory User로 등록하기 전에 암호화 되어야 함

> 참고: PasswordEncoder는 다양한 암호화 방식을 제공하며, Spring Security에서 지원하는 PasswordEncoder의 디폴트 암호화 알고리즘은 bcrypt이다.

```java
@Configuration
public class SecurityConfiguration {
    
    @Bean
    public UserDetailsManager userDetailsService(){
    // 내용 생략
    }
    
    // (1)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();  // (1-1)
    }
}
//([코드 4-16) PasswordEncoder Bean 등록
```
- 코드 4-16에서는 (1)과 같이 SecurityConfiguration 클래스에서 PasswordEncdoer를 Bean으로 등록함
  - (1-1)의 `PasswordEncoderFactories.createDelegatingPasswordEncoder();`를 통해 `DelegatingPasswordEncoder`를 먼저 생성
    - `DelegatingPasswordEncoder`가 실질적으로 PasswordEncoder 구현 객체를 생성

> 우리가 userDetailsService() 메서드에서 미리 생성하는 InMemoryUser의 패스워드는 내부적으로 디폴트 PasswordEncoder를 통해 암호화

### MemberService Bean 등록을 위한 JavaConfiguration 구성
- 학습 목적을 위해 편의상 클래스가 아닌 인터페이스로 구현
```java
public interface MemberService {
    Member createMember(Member member);
}
// 코드 (4-17) MemberService 인터페이스
```
- Hello Spring Security 샘플 애플리케이션은 회원 가입 폼에서 전달 받은 정보를 이용해 새로운 사용자를 추가하는 기능만 있으면 됨
  - createMember() 하나만 구현하는 구현체가 있으면 됨

#### InMemory User 등록을 위한 InMemoryMemberService 클래스
```java
public class InMemoryMemberService implements MemberService {
    public Member createMember(Member member) {

        return null;
    }
}
// (코드 4-18) InMemory User 등록을 위한 InMemoryMemberService 클래스
// 추후 구현 예정
```

#### 데이터베이스에 User를 등록하기 위한 DBMemberService 클래스
```java
@Transactional
public class DBMemberService implements MemberService {
    public Member createMember(Member member) {
         return null;
    }
}
// [코드 4-19] 데이터베이스에 User를 등록하기 위한 DBMemberService 클래스
// 추후 구현 예정 (현재는 InMemory User 등록 학습)
```

#### JavaConfiguration 구성
```java
@Configuration
public class JavaConfiguration {
    // (1)
    @Bean
    public MemberService inMemoryMemberService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        return new InMemoryMemberService(userDetailsManager, passwordEncoder);
    }
}
// (코드 4-20) MemberService Bean 등록을 위한 JavaConfiguration 구성
```
- `MemberService` 인터페이스의 구현 클래스인 `InMemoryMemberService`를 Spring Bean으로 등록
  - (1)에서는 `MemberService` 인터페이스의 구현체인 `InMemoryMemberService` 클래스의 Bean 객체를 생성
    - `InMemoryMemberService` 클래스는 **데이터베이스 연동 없이 메모리에 Spring Security의 User를 등록**
      - `UserDetailsManager` 객체가 필요
    - 또한 User 등록 시, 패스워드를 암호화 한 후에 등록해야 함
      - Spring Security에서 제공하는 `PasswordEncoder` 객체가 필요
- 따라서 이 두 객체를 `InMemoryMemberSerivce` 객체 생성 시, DI

#### InMemoryMemberService 구현
```java
public class InMemoryMemberService implements MemberService {  // (1)
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    // (2)
    public InMemoryMemberService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Member createMember(Member member) {
        // (3)
        List<GrantedAuthority> authorities = createAuthorities(Member.MemberRole.ROLE_USER.name());

        // (4)
        String encryptedPassword = passwordEncoder.encode(member.getPassword());

        // (5)
        UserDetails userDetails = new User(member.getEmail(), encryptedPassword, authorities);

        // (6)
        userDetailsManager.createUser(userDetails);

        return member;
    }

    private List<GrantedAuthority> createAuthorities(String... roles) {
        // (3-1)
        return Arrays.stream(roles)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}
// (코드 4-21) 메모리에 Spring Security User를 등록해주는 InMemoryMemberService 클래스
```
- 코드 4-21은 회원 가입 정보를 전달 받아 Spring Security의 User 메모리에 등록해 주는 `InMemoryMemberService` 클래스의 코드임
  - `InMemoryMemberService` 클래스는 `MemberService` 인터페이스를 구현하는 구현 클래스임으로 (1)과 같이 `implements MemberService`를 지정
    - 여태까지는 `@Service`를 이용해 서비스 클래스를 Bean을 등록했음
      - 현재는 JavaConfiguration을 이용해 Bean을 등록하고 있음
  - (2)에서는 UserDetailsManager와 PasswordEncoder를 DI 받음
    - UserDetailsManager는 Spring Security의 User를 관리하는 관리자 역할을 함
      - `SecurityConfiguration`에서 `InMemoryUserDetailsManager`를 Bean으로 등록함
        - DI 받은 UserDetaiosManager 인터페이스의 하위 타입은 `InMemoryUserDetailsManager`임
      - `PasswordEncoder`는 Spring Security User를 등록할 때 패스워드를 암호화 해주는 클래스
        - Spring Security5 에서는 InMemory User의 경우에도 패스워드의 암호화가 필수임
          - DI 받은 `PasswordEncoder`를 이용, User 패스워드를 암호화 해줘야 함
  - (3)에서는 User의 권한을 지정함
    - `createAuthorities(Member.MemberRole.ROLE_USER.name());`를 이용해 User의 권한 목록을 `List<GrantedAuthority>`로 생성
      - Member 클래스에는 `MemberRole` enu이 정의되어 있음
        - `ROLE_USER`와 `ROLE_ADMIN` enum 타입이 정의되어 있음
    - Spring Security 에서는 `SimpleGrantedAuthority`를 사용해 Role 베이스 형태의 권한 지정 시 **`'Roll_' + 권한명` 형태로 지정**해 줘야함
      - (3-1)에서는 Java의 Stream API를 이용해 생성자 파라미터로 해당 User의 Role을 전달하면서 SimpleGrantedAuthority 객체를 생성한 후, List<SimpleGrantedAuthority> 형태로 리턴함
  - (4)에서는 `PasswordEncoder` 를 이용해 등록할 User의 패스워드를 암호화
  - (5)에서는 Spring Security User로 등록하기 위해 `UserDetails` 를 생성
    - **Spring Security에서는 Spring Security에서 관리하는 User 정보를 UserDetails로 관리한다는 사실을 꼭 기억**
  - (6)에서는 `UserDetailsManager`의 `createUser()` 메서드를 이용해서 User를 등록
- 이제 애플리케이션을 다시 실행 시키고 [회원 가입] 메뉴에서 회원 정보를 등록한 후, 등록한 회원 정보로 로그인을 수행하면 정상적으로 로그인이 되는 것을 확인할 수 있음


## 데이터베이스 연동을 통한 로그인 인증
- 단한 Memebr 엔티티 클래스를 이용해서 회원의 인증 정보를 포함한 회원 정보를 데이터베이스 테이블에서 관리

> InMemory User를 사용하는 방식은 **테스트 환경**이나 **데모**에서 사용

### Custom UserDetailsService를 사용하는 방법 
- Spring Security에서는 User의 인증 정보를 테이블에 저장하고, 테이블에 저장된 인증 정보를 이용해 인증 프로세스를 진행할 수 있는 몇 가지 방법이 존재
  - 그 중 하나인 Custom UserDetailsService를 이용하는 방법

> 일반적으로 Spring Security에서는 인증을 시도하는 주체를 User(비슷한 의미로 Principal도 있음)라고 부름 <br>
> Principal은 User의 더 구체적인 정보를 의미하며, 일반적으로 Spring Security에서의 Username을 의미 <br>
> 샘플 애플리케이션에서는 Member 엔티티 클래스가 로그인 인증 정보를 포함할텐데 이 Member 엔티티가 Spring Security의 User 정보를 포함

#### SecurityConfiguration의 설정 변경 및 추가
- 지금부터는 로그인 인증을 위해 DB에 저장되어 있는 인증 정보를 저장
  - InMemory User를 위한 설정들은 더 이상 필요없으므로 제거
```java
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().sameOrigin() // (1)
            .and()
            .csrf().disable()
            .formLogin()
            .loginPage("/auths/login-form")
            .loginProcessingUrl("/process_login")
            .failureUrl("/auths/login-form?error")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
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

   // ======================================================== 여기부터
   /**
    * InMemory User를 위한 설정이므로, 제거 대상.
    */
    @Bean
    public UserDetailsManager userDetailsService() {    // (2)
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("kevin@gmail.com")
                        .password("1111")
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin@gmail.com")
                        .password("2222")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
   // ======================================================== 여기까지 제거

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
// (코드 4-22) 변경된 SecurityConfiguration(V1) 클래스
```
- 1은 웹 브라우저에서 H2 웹 콘솔을 정상적으로 사용하기 위한 설정임

> `frameOptions()`는 HTML 태그 중에서 <frame>이나 <iframe>, <object> 태그에서 페이지를 렌더링 할지의 여부를 결정하는 기능을 함 <br>
> Spring Security에서는 Clickjacking 공격을 막기 위해 기본적으로 `frameOptions()` 기능이 활성화 되어 있으며 디폴트 값은 DENY임 <br>
> 즉, HTML 태그를 이용한 페이지 렌더링을 허용하지 않겠다는 의미임

> (1)과 같이 `.frameOptions().sameOrigin()`을 호출하면 동일 출처로부터 들어오는 request만 페이지 렌더링을 허용함 <br>
> H2 웹 콘솔의 화면 자체가 내부적으로 태그를 사용하고 있기 때문에 개발 환경에서는 H2 웹 콘솔을 정상적으로 사용할 수 있도록 (1)과 같이 설정

- 이제 DB에서 user를 등록하고 인증 정보를 사용할 것이므로, `userDetailsSerivce()` 메서드를 제거함

#### JavaConfiguration의 Bean 등록 변경
```java
@Configuration
public class JavaConfiguration {
    // (1)
    @Bean
    public MemberService dbMemberService(MemberRepository memberRepository,
                                         PasswordEncoder passwordEncoder) {
        return new DBMemberService(memberRepository, passwordEncoder); 
//        (1-1)
    }
}
// (코드 4-23) 데이터베이스를 사용하기 위해 변경된 JavaConfiguration 클래스
```
- User 정보를 저장하기 위해 MemberService 인터페이스의 구현 클래스를 DBMemberService로 변경
  - DBMemberService는 내부에서 데이터를 DB에 저장 후 패스워드를 암호화
    - (1-1)과 같이 `MemberRepository`와 `PasswordEncdoer` 객체를 DI 해줌

#### DBMemberService 구현 
- User의 인증 정보를 DB에 저장하는 역할
```java
@Transactional
public class DBMemberService implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // (1)
    public DBMemberService(MemberRepository memberRepository,
                             PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());  // (2)
        member.setPassword(encryptedPassword);    // (3)

        Member savedMember = memberRepository.save(member);

        System.out.println("# Create Member in DB");
        return savedMember;
    }
    
    ...
    ...
}
// (코드 4-24) 회원 정보를 DB에 등록하는 DBMemberSerivce 클래스
```
- (1)의 생성자를 통해 MemberRepository와 PasswordEncoder Bena 객체 DI 받음
- (2)에서 PasswordEncoder를 이용해 패스워드를 암호화 함
- (3)에서 암호화 된 패스워드를 password 필드에 다시 할당함

> 패스워드의 암호화 <br>
> 회원의 패스워드를 암호화해서 데이터베이스에 저장하는건 개발자 입장에서는 정말 당연한 이야기인데도 불구하고, 회원 등록 로직을 구현할 때 패스워드를 암호화 하지 않고 평문(Plain Text) 그대로 저장하는 경우는 실무에서도 종종 볼 수 있는 일이다. <br>
> 패스워드 같은 민감한(sensitive) 정보는 **반드시 암호화** 되어 저장되어야 한다. <br>
> 또한, 패스워드는 암호화 된 상태에서 복호화 할 이유가 없기 때문에 **단방향 암호화** 방식으로 암호화 되어야 한다는 사실을 기억하라

#### Custom UserDetailsService 구현 
- DB에서 조회한 User의 인증 정보를 기반으로 인증을 처리하는 Custom UserDetailsService 구현

> UserDetailsService <br>
> Spring Security에서 제공하는 컴포는트 중 하나인 `UserDetailsService`는 User 정보를 로드하는 핵심 인터페이스임<br>
> 여기서 로드(load)의 의미는 인증에 필요한 User 정보를 어딘가에서 가지고 온다는 의미 (어딘가는 메모리가 될 수도, DB가 될 수도 있음) <br>
> InMemory User를 등록하는데 사용했던 `InMemoryUserDetailsManager` 는 `UserDetailsManager` 인터페이스의 구현체이고, `UserdetailsManager`는 `UserDetailsService`를 상속하는 확장 인터페이스인 것을 기억하라.

```java
@Component
public class HelloUserDetailsService implements UserDetailsService {   // (1)
    private final MemberRepository memberRepository;
    private final HelloAuthorityUtils authorityUtils;

    // (2)
    public HelloUserDetailsServiceV1(MemberRepository memberRepository, HelloAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    // (3)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        // (4)
        Collection<? extends GrantedAuthority> authorities = authorityUtils.createAuthorities(findMember.getEmail());

        // (5)   
        return new User(findMember.getEmail(), findMember.getPassword(), authorities);
    }
}
// (코드 4-25) 데이터베이스의 인증 정보로 인증을 처리하는 Custom UserDetailsService
```
- 코드 4-25는 데이터베이스에서 조회한 인증 정보를 기반으로 인증을 처리하는 Custom UserDetailsService인 HelloUserDetailsService 클래스의 코드
  - HelloUserDetailsService와 같은 Custom UserDetailsService를 구현하기 위해서는 (1)과 같이 `UserDetailsService` 인터페이스를 구현해야 함
  - HelloUserDetailsService는 데이터베이스에서 User를 조회하고, 조회한 User의 권한(Role) 정보를 생성하기 위해 (2)와 같이 `MemberRepository`와 `HelloAuthorityUtils` 클래스를 DI 받음
  - `UserDetailsService` 인터페이스를 implements 하는 구현 클래스는 (3)과 같이 `loadUserByUsername(String username)` 이라는 추성 메서드를 구현해야 함
  - (4)에서는 HelloAuthorityUtils 를 이용해 데이터베이스에서 조회한 회원의 이메일 정보를 이용해 Role 기반의 권한 정보(GrantedAuthority) 컬렉션을 생성(HelloAuthorityUtils 코드는 바로 아래에서 설명)
  - 데이터베이스에서 조회한 인증 정보와 (4)에서 생성한 권한 정보를 Spring Security에서는 아직 알지 못하기 때문에 Spring Security에 이 정보들을 제공해 주어야함
    - (5)에서는 UserDetails 인터페이스의 구현체인 User 클래스의 객체를 통해 제공
  - (5)와 같이 데이터베이스에서 조회한 User 클래스의 객체를 리턴하면 Spring Security가 이 정보를 이용해 인증 절차를 수행
- 즉, 이터베이스에서 User의 인증 정보만 Spring Security에게 넘겨주고, 인증 처리는 Spring Security가 대신함

> UserDetails <br>
> `UserDetails`는 UserDetailsService에 의해 로드(load)되어 인증을 위해 사용되는 핵심 User 정보를 표현하는 인터페이스임 <br>
> UserDetails 인터페이스의 구현체는 Spring Security에서 보안 정보 제공을 목적으로 직접 사용되지는 않고, Authentication **객체로 캡슐화 되어 제공**됨

```java
@Component
public class HelloAuthorityUtils {
    // (1)
    @Value("${mail.address.admin}")
    private String adminMailAddress;

    // (2)
    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    // (3)
    private final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    
    public List<GrantedAuthority> createAuthorities(String email) {
        // (4)
        if (email.equals(adminMailAddress)) {
            return ADMIN_ROLES;
        }
        return USER_ROLES;
    }
}
// (코드 4-26) User의 권한을 매핑, 생성하는 HelloAuthorityUtils
```
- (1)은 application.yml에 추가한 프로퍼티를 가져오는 표현식임
  - (1)과 같이 `@Value("${프로퍼티 경로}")`의 표현식 형태로 작성하면 application.yml에 정의되어 있는 프로퍼티의 값을 클래스 내에서 사용 가능함
  - (1)에서는 application.yml에 미리 정의한 관리자 권한을 가질 수 있는 이메일 주소를 불러오고 있음
  - application.yml 파일에 정의한 관리자용 이메일 주소는 회원 등록 시, 특정 이메일 주소에 관리자 권한을 부여할 수 있는지 여부를 결정하기 위해 사용
  - applicaiton.yml 파일에 다음과 같이 관리자 이메일 주소를 정의할 것
```yml
mail:
  address:
    admin: admin@gmail.com
```
- (2)에서는 Spring Security에서 지원하는 `AuthorityUtils` 클래스를 이용해서 관리자용 권한 목록을 `List<GrantedAuthority>` 객체로 미리 생성
  - 관리자 권한의 경우, 일반 사용자의 권한까지 추가로 포함되어 있음
- (3)에서는 Spring Security에서 지원하는 `AuthorityUtils` 클래스를 이용, 일반 사용 권한 목록을 `List<GrantedAuthority>` 객체로 미리 생성
- (4)에서는 파라미터로 전달 받은 이메일 주소가 application.yml 파일에서 가져온 관리자용 이메일 주소와 동일하다면, 관리자용 권한인 `List<GrantedAuthority> ADMIN_ROLES`를 리턴
  - 물론 실무에서는 관리자로서 등록하기 위한 추가적인 인증 절차가 존재함

#### H2 웹 콘솔에서 등록한 회원 정보 확인 및 로그인 인증 테스트
- 회원 가입 메뉴에서 회원 등록 시 DB에 비밀번호가 **암호화** 되어 저장됨
  - ![image](https://user-images.githubusercontent.com/102513932/203026023-a621c2c2-63d7-40c6-998c-a569d0f7baff.png)

#### Custom UserDetails 구현
- 지금가지 작성한 것 만으로도 DB에 회원의 인증 정보 저장 및 저장 정보 기반 로그인 인증은 문제 없이 작동됨
  - 보다 유연하고 깔끔한 코드 구성을 위해 `HelloUserDetailsService` 클래스 개선
```java
@Component
public class HelloUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HelloAuthorityUtils authorityUtils;

    public HelloUserDetailsService(MemberRepository memberRepository, HelloAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        Collection<? extends GrantedAuthority> authorities = authorityUtils.createAuthorities(findMember);

        // (1) 개선하면 좋은 포인트
        return new User(findMember.getEmail(), findMember.getPassword(), authorities);
    }
}
// (코드 4-27) 현재까지 작성된 HelloUserDetailsService
```
```java
@Component
public class HelloUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HelloAuthorityUtils authorityUtils;

    public HelloUserDetailsServiceV2(MemberRepository memberRepository, HelloAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return new HelloUserDetails(findMember);  // (1) 개선된 부분
    }

    // (2) HelloUserDetails 클래스 추가
    private final class HelloUserDetails extends Member implements UserDetails { // (2-1)
        // (2-2)
        HelloUserDetails(Member member) {
            setMemberId(member.getMemberId());
            setFullName(member.getFullName());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getEmail());  // (2-3) 리팩토링 포인트
        }

        // (2-4)
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
// (코드 4-28) 유연하게 개선된 HelloUserDetailsService
```
- 코드 4-28은 코드 4-27의 코드를 개선한 `HelloUserDetailsService`의 코드임
  - 기존에는 loadUserByUsername() 메서드의 리턴 값으로 `new User(findMember.getEmail(), findMember.getPassword(), authorities);`을 리턴
    - 개선된 코드에서는 (1)과 같이 `new HelloUserDetails(findMember);`라는 Custom UserDetails 클래스의 생성자로 findMember를 전달
    - 또한 기존 코드에서 loadUserByUsername() 메서드 내부에서 User 권한 정보를 생성하는 `Collection<? extends GrantedAuthority> authorities = authorityUtils.createAuthorities(findMember);` 코드가 사라짐
      - (2)에서 정의한 HelloUserDetails 클래스 내부로 포함됨 
  - (2)의 `HelloUserDetails` 클래스는 `UserDetails` 인터페이스를 구현하고 있고 또한 `Member` 엔티티 클래스를 상속하고 있음
    - 이렇게 구성하면 데이터베이스에서 조회한 회원 정보를 Spring Security의 User 정보로 변환하는 과정과 User의 권한 정보를 생성하는 과정을 캡슐화 할 수 있음
    - 또한 `HelloUserDetails` 클래스는 `Member` 엔티티 클래스를 상속하고 있음
      - 따라서 HelloUserDetails를 리턴 받아 사용하는 측에서는 두 개 클래스의 객체를 모두 다 손쉽게 캐스팅해서 사용 가능함
  - (2-3)에서는 `HelloAuthorityUtils`의 `createAuthorities()` 메서드를 이용해 User의 권한 정보를 생성함
    - 이 코드는 기존에는 `loadUserByUsername()` 메서드 내부에 있었지만, 지금은 `HelloUserDetails` 클래스 내부에서 사용되도록 캡슐화 됨
  - (2-4)에서는 Spring Security에서 인식할 수 있는 username을 Member 클래스의 email 주소로 채우고 있음
    - `getUsername()`의 리턴 값은 null일 수 없음
  - 기타 UserDetails 인터페이스의 추상 메서드를 구현한 부분은 지금은 크게 중요하지 않으므로, 모두 `true`를 리턴하고 있음

### User의 Role을 DB에서 관리하기
- 일반적으로 User의 인증 정보 같은 보안과 관련된 정보는 DB 같은 영구 저장소에 안전하게 보관함
  - 그런데 현재까지 User의 권한 정보는 DB에서 관리하는 것이 아닌 DB에서 조회한 User 정보를 기준으로 코드 상에서 조건에 맞게 생성하고 있음
  - USer의 권한 정보를 DB에서 관리하도록 코드를 수정
- User의 권한 정보를 저장하기 위한 테이블 생성
- 회원 가입 시, User의 권한 정보(Role)를 DB에 저장하는 작업
- 로그인 인증 시, User의 권한 정보를 DB에서 조회하는 작업

#### User의 권한 정보를 저장하기 위한 테이블 생성
- JPA를 이용, User와 User의 권한 정보 간 연관 관계를 맺음
```java
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends Auditable implements Principal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 100, nullable = false)
    private String fullName;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    // (1) User의 권한 정보 테이블과 매핑되는 정보
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Member(String email) {
        this.email = email;
    }

    public Member(String email, String fullName, String password) {
        this.email = email;
        this.fullName= fullName;
        this.password = password;
    }

    @Override
    public String getName() {
        return getEmail();
    }

    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
           this.status = status;
        }
    }

    public enum MemberRole {
        ROLE_USER,
        ROLE_ADMIN
    }
}
// (코드 4-29) Spring Security의 User 역할을 하는 Member 엔티티 클래스에 User 권한 정보 매핑
```
- 코드 4-29는 Member 엔티티 클래스와 User의 권한 정보를 매핑
  - Member 엔티티 클래스와 User의 권한 정보를 매핑하는 것은 (1)과 같이 간단하게 처리 가능
  - (1)과 같이 List, Set 같은 컬렉션 타입의 필드는 @ElementCollection 애너테이션을 추가 시 User 권한 정보와 관련된 별도의 엔티티 클래스를 생성하지 않아도 간단하게 매핑 처리 가능
  - 애플리케이션 실행 시 다음과 같은 테이블 생성
    - ![image](https://user-images.githubusercontent.com/102513932/203093497-026acfc5-97c7-48c3-bc1a-a4864a2c2a83.png)
      - 한 명의 회원이 한 개 이상의 Role을 가질 수 있으므로, MEMBER 테이블과 `MEMBER_ROLES` 테이블은 1대N 관계임
      - 회원 가입을 통해 회원 정보가 MEMBER 테이블에 저장 시 
        - `MEMBER_ROLES` 테이블의 MEMBER_MEMBER_ID 컬럼에는 MEMBER 테이블의 기본키 값이, ROLES 컬럼에는 권한 정보가 저장됨

#### 회원 가입 시, User의 권한 정보를 DB에 저장
- 테이블도 만들었으니 회원 가입 시 해당 회원의 권한 정보를 `MEMBER_ROLES` 테이블에 저장함
```java
@Transactional
public class DBMemberService implements MemberService {
    ...
    ...
  
    private final HelloAuthorityUtils authorityUtils;

    ...
    ...

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        // (1) Role을 DB에 저장
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    ...
    ...
}
// (코드 4-30) 회원 등록 시 , 권한 정보를 DB에 저장
```
- 코드 4-30에서는 DBMemberService에서 회원 등록 시, 회원의 권한 정보를 데이터베이스에 저장하는 코드가 추가
  - (1)에서는 `authorityUtils.createRoles(member.getEmail());`를 통해 회원의 권한 정보(`List<String> roles`)를 생성한 뒤 member 객체에 넘김

```java
@Component
public class HelloAuthorityUtils {
    @Value("${mail.address.admin}")
    private String adminMailAddress;

    ...
    ...

    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");

    ...
    ...

    // (1) DB 저장 용
    public List<String> createRoles(String email) {
        if (email.equals(adminMailAddress)) {
            return ADMIN_ROLES_STRING;
        }
        return USER_ROLES_STRING;
    }
}
// (코드 4-31) 회원의 Role 정보를 생성하는 createRoles() 메서드가 추가된 HelloAuthorityUtils
```
- (1)에서는 파라미터로 전달 된 이메일 주소가 application.yml 파일의 mail.address.admin 프로퍼티에 정의된 이메일 주소와 동일할 시 
  - 관리자 Role 목록(`ADMIN_ROLES_STRING`)을 리턴, 그 외에는 일반 사용자 Role 목록(`USER_ROLES_STRING`)을 리턴

#### 로그인 인증 시, User의 권한 정보를 DB에서 조회하는 작업
```java
@Component
public class HelloUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HelloAuthorityUtils authorityUtils;

    public HelloUserDetailsServiceV3(MemberRepository memberRepository, HelloAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return new HelloUserDetails(findMember);
    }

    private final class HelloUserDetails extends Member implements UserDetails {
        HelloUserDetails(Member member) {
            setMemberId(member.getMemberId());
            setName(member.getName());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
            setRoles(member.getRoles());        // (1)
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            // (2) DB에 저장된 Role 정보로 User 권한 목록 생성
            return authorityUtils.createAuthorities(this.getRoles());
        }

        ...
        ...
    }
}
// (코드 4-32) DB에서 조회한 Role을 기반으로 User의 권한 정보 생성
```
- 코드 4-32는 데이터베이스의 MEMBER_ROLES 테이블에서 조회한 Role을 기반으로 User의 권한 목록(`List<GrantedAuthority>`)을 생성하는 로직이 추가된 HelloUserDetailsService 클래스
  - (1)에서는 HelloUserDetails가 상속하고 있는 Member(`extends Member`)에 데이터베이스에서 조회한 `List<String> roles`를 전달
  - (2)에서 다시 Member(`extends Member`)에 전달한 Role 정보를 authorityUtils.createAuthorities() 메서드의 파라미터로 전달해서 권한 목록(`List<GrantedAuthority>`)을 생성 

>데이터베이스에서 Role 정보를 가지고 오지 않았을 때에는 authorityUtils.createAuthorities(this.getRoles()); 아니라 authorityUtils.createAuthorities(this.getEmail()); 이었다.

```java
@Component
public class HelloAuthorityUtils {
    @Value("${mail.address.admin}")
    private String adminMailAddress;

    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    private final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");

    // 메모리 상의 Role을 기반으로 권한 정보 생성.
    public List<GrantedAuthority> createAuthorities(String email) {
        if (email.equals(adminMailAddress)) {
            return ADMIN_ROLES;
        }
        return USER_ROLES;
    }

    // (1) DB에 저장된 Role을 기반으로 권한 정보 생성
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
       List<GrantedAuthority> authorities = roles.stream()
               .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // (2)
               .collect(Collectors.toList());
       return authorities;
    }

    ...
    ...
}
// (코드 4-33) 데이터베이스에서 조회한 Role 정보를 기반으로 User의 권한 목록 생성
```
- 코드 4-33의 (1)을 보면 기존에는 application.yml 파일의 mail.address.admin 프로퍼티에 정의된 관리자용 이메일 주소를 기준으로 관리자 Role을 추가했지만 이제는 그럴 필요가 없음
- **단순히 데이터베이스에서 가지고 온 Role 목록(List<String> roles)을 그대로 이용해서 권한 목록(authorities)을 만들자**
  - 주의점은 (2)와 같이 SimpleGrantedAuthority 객체를 생성할 때 생성자 파라미터로 넘겨주는 값이 “ USER" 또는 “ADMIN"으로 넘겨주면 안됨
    - “ROLE_USER" 또는 “ROLE_ADMIN" 형태로 넘겨주어야 함

### Custom AuthentiacationProvider를 사용하는 방법
- 앞에서 Custom UserDetailsService를 사용해 로그인 인증을 처리하는 방식은 Spring Security가 내부적으로 인증을 대신 처리해주는 방식임
- 이번에는 마지막으로  Custom AuthenticationProvider를 이용해 우리가 직접 로그인 인증을 처리하는 방법을 살펴볼 것
  - Spring Security의 핵심 컴포넌트인 `AuthenticationProvider`를 이해하는데 도움이 되므로 Spring Security의 기본 구조를 이해한다는 관점
```java
@Component
public class HelloUserAuthenticationProvider implements AuthenticationProvider {   // (1)
    private final MemberService memberService;
    private final HelloAuthorityUtils authorityUtils;
    private final PasswordEncoder passwordEncoder;

    public HelloUserAuthenticationProvider(MemberService memberService,
                                           HelloAuthorityUtils authorityUtils,
                                           PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.authorityUtils = authorityUtils;
        this.passwordEncoder = passwordEncoder;
    }

    // (3)
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;  // (3-1)

        // (3-2)
        String username = authToken.getName();
        Optional.ofNullable(username).orElseThrow(() -> new UsernameNotFoundException("Invalid User name or User Password"));

        // (3-3)
        Member member = memberService.findMember(username);

        String password = member.getPassword();
        verifyCredentials(authToken.getCredentials(), password);    // (3-4)

        Collection<? extends GrantedAuthority> authorities = authorityUtils.createAuthorities(member.getRoles());  // (3-5)

        // (3-6)
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    // (2) HelloUserAuthenticationProvider가 Username/Password 방식의 인증을 지원한다는 것을 Spring Security에게 알려준다.
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

    private void verifyCredentials(Object credentials, String password) {
        if (!passwordEncoder.matches((String)credentials, password)) {
            throw new BadCredentialsException("Invalid User name or User Password");
        }
    }
}
// (코드 4-34) Custom AuthenticationProvider인 HelloUserAuthenticationProvider
```
- (1)과 같이 AuthenticationProvider 인터페이스의 구현 클래스로 정의
  - 따라서 AuthenticationProvider의 구현 클래스로써의 HelloUserAuthenticationProvider를 구현해야 함
  - Spring Security는 코드 4-34와 같이 AuthenticationProvider를 구현한 구현 클래스가 Spring Bean으로 등록되어 있다면 해당 AuthenticationProvider를 이용해서 인증을 진행
  - 따라서 클라이언트쪽에서 로그인 인증을 시도하면 우리가 구현한 HelloUserAuthenticationProvider가 직접 인증을 처리하게 됨
- AuthenticationProvider 인터페이스의 구현 클래스는 `authenticate(Authentication authentication)` 메서드와 `supports(Class<?> authentication)` 메서드를 구현해야 함
  - 그 중 (2)의 `supports(Class<?> authentication)` 메서드는 우리가 구현하는 Custom AuthenticationProvider(`HelloUserAuthenticationProvider`)가 Username/Password 방식의 인증을 지원한다는 것을 Spring Security에게 알려주는 역할
- (3)의 `authenticate(Authentication authentication)`에서 우리가 직접 작성한 인증 처리 로직을 이용해 사용자의 인증 여부를 결정
  - (3-1)에서 authentication을 캐스팅하여 UsernamePasswordAuthenticationToken을 얻음
  - `UsernamePasswordAuthenticationToken` 객체에서 (3-2)와 같이 해당 사용자의 Username을 얻은 후, 존재하는지 체크
  - Username이 존재한다면, (3-3)과 같이 DB에서 해당 사용자를 조회
  - (3-4)에서 로그인 정보에 포함된 패스워드(`authToken.getCredentials()`)와 DB에 저장된 사용자의 패스워드 정보가 일치하는지를 검증
  - (3-4)의 검증 과정을 통과했다면 로그인 인증에 성공한 사용자이므로 (3-5)와 같이 해당 사용자의 권한을 생성
  - 마지막으로 (3-6)과 같이 인증된 사용자의 인증 정보를 리턴값으로 전달
- 이 인증 정보는 내부적으로 Spring Security에서 관리하게 됨 

> AuthenticationProvider AuthenticationProvider는 Spring Security에서 클라이언트로부터 전달받은 인증 정보를 바탕으로 인증된 사용자인지에 대한 인증 처리를 수행하는 Spring Security 컴포넌트 <br>
> AuthenticationProvider는 인터페이스 형태로 정의되어 있으며, Spring Security에서는 AnonymousAuthenticationProvider,DaoAuthenticationProvider,JwtAuthenticationProvider,RememberMeAuthenticationProvider,OAuth2LoginAuthenticationProvider` 등 다양한 유형의 AuthenticationProvider 구현체를 제공

## 핵심 포인트
- Spring Security에서 지원하는 InMemory User는 말 그대로 메모리에 등록되어 사용되는 User이므로 애플리케이션 실행이 종료되면 InMember User 역시 메모리에서 사라짐
- InMemory User를 사용하는 방식은 테스트 환경이나 데모 환경에서 사용할 수 있는 방법
- Spring Security는 사용자의 크리덴셜(Credential, 자격증명을 위한 구체적인 수단)을 암호화 하기 위한 PasswordEncoder를 제공
  - PasswordEncoder는 다양한 암호화 방식을 제공
  - Spring Security에서 지원하는 PasswordEncoder의 디폴트 암호화 알고리즘은 bcrypt
- 패스워드 같은 민감한(sensitive) 정보는 반드시 암호화 되어 저장되어야 함
  - 패스워드는 복호화 할 이유가 없기 때문에 단방향 암호화 방식으로 암호화 되어야 함
- Spring Security에서 `SimpleGrantedAuthority` 를 사용해 Role 베이스 형태의 권한을 지정할 때 ‘Roll_’ + 권한명 형태로 지정해 주어야 함
- Spring Security에서는 Spring Security에서 관리하는 User 정보를 `UserDetails`로 관리
- `UserDetails`는 UserDetailsService에 의해 로드(load)되는 핵심 User 정보를 표현하는 인터페이스
- `UserDetailsService`는 User 정보를 로드(load)하는 핵심 인터페이스
- 일반적으로 Spring Security에서는 인증을 시도하는 주체를 `User`(비슷한 의미로 Principal도 있음)라고 부름
  - rincipal은 User의 더 구체적인 정보를 의미하며, 일반적으로 Username을 의미
- Custom UserDetailsService를 사용해 로그인 인증을 처리하는 방식은 Spring Security가 내부적으로 인증을 대신 처리해주는 방식
- `AuthenticationProvider`는 Spring Security에서 클라이언트로부터 전달받은 인증 정보를 바탕으로 인증된 사용자인지를 처리하는 Spring Security의 컴포넌트