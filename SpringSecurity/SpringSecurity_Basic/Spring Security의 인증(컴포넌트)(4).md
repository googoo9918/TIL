## Spring Security의 인증 처리 흐름
- Spring Security Filter Chain의 특정 Filter에 도달했을 때, Spring Security의 컴포넌트들이 어떤 과정을 거쳐 사용자의 인증 요청을 처리하는가?

### Spring Security의 컴포넌트로 보는 인증처리 흐름
- ![umOelmfteLy2sKugXu4f6-1663488227312](https://user-images.githubusercontent.com/102513932/203187756-9f74b9b4-4b9e-4f1e-812c-6165d34214d1.png)
  - 사용자가 로그인 인증을 위한 요청을 전송할 경우, Spring Security에서 해당 인증을 어떻게 처리하는지 확인 가능
    - Spirng Security의 핵심 컴포넌트로 구성된 인증 처리 흐름
    - 로그인 인증에 대한 처리 흐름
- (1)에서 사용자가 로그인 폼 등을 이용해 Username과 Password를 포함한 request를 Spring Security가 적용된 애플리케이션에 전송
  - 사용자의 로그인 요청이 Spring Security의 Filter Chain까지 들어오면 여러 Filter 중 `UsernamePasswordAuthenticationFilter`가 해당 요청을 전달 받음
- 사용자의 로그인 요청을 전달 받은 `UsernamePasswordAuthenticationFilter`는 Username과 Password를 이용해 (2)와 같이 `UsernamePasswordAuthenticationToken`을 생성
  - `UsernamePasswordAuthenticationToken`은 `Authentication` 인터페이스를 구현한 클래스
    - 여기서 `Authentication`은 아직 인증이 되지 않은 `Authentication`임
- 아직 인증되지 않은 `Authentication`을 갖고 있는 `UsernamePasswordAuthenticationFilter`는 (3)과 같이 해당 `Authentication`을 `AuthenticationManager`에게 전달함
  - `AuthenticationManager`는 인증 처리를 총괄하는 매니저 역할을 하는 인터페이스
    - `AuthenticationManager`를 구현한 구현 클래스가 바로 `ProviderManager`임
      - **즉, `ProviderManager`가 인증이라는 작업을 총괄하는 실질적인 매니저**임
        - 직접 인증을 처리하는 것이 아니라 인증을 처리할 누군가를 찾은 뒤, 인증 처리를 대신 맡김
          - 이 '누군가'는 `AutehnticationProvider`임
- (4)와 같이 `ProviderManager`로 부터 Authentication을 전달 받은 `AuthenticationManager`는 (5)와 같이 `UserDetailsService`를 이용해 `UserDetails`를 조회함
  - `UserDetails`는 DB 등의 저장소에 저장된 사용자의 Username과 사용자의 자격을 증명해주는 `크리덴셜`인 Password, 그리고 사용자의 권한 정보를 포함하고 있는 컴포넌트임
    - 이 `UserDetails`를 제공하는 컴포넌트가 바로 `UserDetailsService`임
- `UserDetailsService`는 (5)에서 처럼 DB 등의 저장소에서 사용자의 크리덴셜을 포함한 사용자의 정보를 조회함
- DB등의 저장소에서 조회한 사용자의 크리덴셜을 포함한 사용자의 정보를 기반으로 (7)과 같이 `UserDetails`를 생성한 후, 생성된 `UserDetails`를 다시 `AuthenticationProvider`에게 전달함(8)
- `UserDetails`를 전달 받은 `AuthenticationProvider`는 PasswordeEncoder를 이용해 `UserDetails`에 포함된 암호화 된 Password와 인증을 위한 `Authentication` 안에 포함된 Password가 일치하는지 검증
  - 검증에 성공하면 `UserDetails`를 이용해 인증된 Authentication을 생성함(9)
  - 만약 검증에 성공하지 못하면 Exception을 발생, 인증 처리를 중단함
- `AuthenticationProvicer`는 인증된 Authentication을 `ProviderManager`에게 전달함(10)
  - (2)에서의 `Authentication`은 인증을 위해 필요한 사용자의 로그인 정보를 갖고 있음
  - 이 단계에서 `ProviderManger`에게 전달한 `Authentication`은 **인증에 성공**한 사용자의 정보(Principal, Credential, GrantedAuthorities)를 갖고 있음
- 이제 `ProviderManager`는 (11)과 같이 인증된 `Authentication`를 다시 UsernamePasswordAuthenticationFilter에게 전달함
- 인증된 Authentication을 전달 받은 UsernamePasswordAuthenticationFilter는 마지막으로 (12)와 같이 `SecurityContextHolder`를 이용해 `SecurityContext`에 인증된 Authentication을 저장함
  - SecurityContext는 Spring Security의 세션 정책에 따라 HttpSession에 저장되어 사용자의 인증 상태를 유지하기도 하고, HttpSession을 생성하지 않고 무상태를 유지하기도 함

## Spring Security의 인증 컴포넌트
- ![umOelmfteLy2sKugXu4f6-1663488227312](https://user-images.githubusercontent.com/102513932/203187756-9f74b9b4-4b9e-4f1e-812c-6165d34214d1.png)

### UsernamePasswordAuthenticationFilter
- 사용자의 로그인 request를 제일 먼저 만나는 컴포넌트
- 일반적으로 로그인 폼에서 제출되는 Username과 Password를 통한 인증을 처리하는 Filter
- 클라이언트로부터 전달 받은 Username과 Password를 Spring Security가 인증 프로세스에서 이용할 수 있도록 `UsernamePasswordAuthenticationToken`을 생성
```java
public class UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter { // (1)

	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username"; // (2)

	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password"; // (3)

	private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login","POST"); // (4)

  ...
  ...

	public UsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager); // (5)
	}

  // (6)
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
    // (6-1)
		if (this.postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
    ...

		String password = obtainPassword(request);
    ...
		
    // (6-2)
    UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
		...

		return this.getAuthenticationManager().authenticate(authRequest); // (6-3)
	}

	...
  ...
}
// (코드 4-42) UsernamePasswordAuthenticationFilter의 코드 일부
```
- `UsernamePasswordAuthenticationFilter`는 (1)과 같이 AbstractAuthenticationProcessingFilter를 상속
  - AbstractAuthenticationProcessingFilter 클래스가 doFilter() 메서드를 포함함
  - 결과적으로 사용자의 로그인 request를 제일 먼저 전달 받는 클래스는 AbstractAuthenticationProcessingFilter 클래스임
- (2)와 (3)을 통해 클라이언트 로그인 폼을 통해 전송되는 request parameter의 디폴트 name은 `username`과 `password`임
- (4)의 `AntPathRequestMatcher`는 클라이언트의 URL에 매치되는 매처
  - (4)를 통해 클라이언트의 URL이 "`/login`"이고, HTTP Method가 `POST`일 경우 매치 될거라는 사실을 예측할 수 있음
  - (4)에서 생성되는 AntPathRequestMatcher의 객체(`DEFAULT_ANT_PATH_REQUEST_MATCHER`)는 (5)에서 상위 클래스인 AbstractAuthenticationProcessingFilter 클래스에 전달됨
    - Filter가 구체적인 작업을 수행할지 특별한 작업 없이 다른 Filter를 호출할지 결정하는데 사용됨
- (5)에서 AntPathRequestMatcher의 객체(`DEFAULT_ANT_PATH_REQUEST_MATCHER`)와 `AuthenticationManager`를 상위 클래스인 AbstractAuthenticationProcessingFilter에 전달함
- (6)의 `attemptAuthentication()` 메서드는 클라이언트에서 전달한 username과 password 정보를 이용해 인증을 시도하는 메서드임
  - 상위 클래스인 AbstractAuthenticationProcessingFilter `doFilter()` 메서드에서 호출됨
  - (6-1)에서 HTTP Method가 POST가 아니면 Exception을 throw
  - (6-2)에서는 클라이언트가 전달한 username과 password 정보를 이용해 `UsernamePasswordAuthenticationToken`을 생성
  - (6-3)에서 AuthenticationManager의 authenticate()메서드를 호출해 인증 처리를 위임함

### AbstractAuthenticationProcessingFilter
- UsernamePasswordAuthenticationFilter가 상속하는 상위 클래스로써 Spring Security에서 제공하는 Filter 중 하나임
- HTTP 기반의 인증 요청을 처리, 실직적인 인증 시도는 하위 클래스에 맡김
  - 인증에 성공하면 인증된 사용자의 정보를 SecurityContext에 저장함 
```java
public abstract class AbstractAuthenticationProcessingFilter extends GenericFilterBean
		implements ApplicationEventPublisherAware, MessageSourceAware {

	...
  ...

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
	}

  // (1)
	private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
    // (1-1)
		if (!requiresAuthentication(request, response)) {
			chain.doFilter(request, response);
			return;
		}
		try {
			Authentication authenticationResult = attemptAuthentication(request, response); // (1-2)
			if (authenticationResult == null) {
				// return immediately as subclass has indicated that it hasn't completed
				return;
			}
			this.sessionStrategy.onAuthentication(authenticationResult, request, response);
			// Authentication success
			if (this.continueChainBeforeSuccessfulAuthentication) {
				chain.doFilter(request, response);
			}
			successfulAuthentication(request, response, chain, authenticationResult); // (1-3)
		}
		catch (InternalAuthenticationServiceException failed) {
			this.logger.error("An internal error occurred while trying to authenticate the user.", failed);
			unsuccessfulAuthentication(request, response, failed);  // (1-4)
		}
		catch (AuthenticationException ex) {
			// Authentication failed
			unsuccessfulAuthentication(request, response, ex);
		}
	}

	
  // (2)
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if (this.requiresAuthenticationRequestMatcher.matches(request)) {
			return true;
		}
		if (this.logger.isTraceEnabled()) {
			this.logger
					.trace(LogMessage.format("Did not match request to %s", this.requiresAuthenticationRequestMatcher));
		}
		return false;
	}

  ...
  ...

  // (3)
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult);
		SecurityContextHolder.setContext(context);
		this.securityContextRepository.saveContext(context, request, response);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", authResult));
		}
		this.rememberMeServices.loginSuccess(request, response, authResult);
		if (this.eventPublisher != null) {
			this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
		}
		this.successHandler.onAuthenticationSuccess(request, response, authResult);
	}

	
  // (4)
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		this.logger.trace("Failed to process authentication request", failed);
		this.logger.trace("Cleared SecurityContextHolder");
		this.logger.trace("Handling authentication failure");
		this.rememberMeServices.loginFail(request, response);
		this.failureHandler.onAuthenticationFailure(request, response, failed);
	}

  ...
  ...
}
// (코드 4-43) AbstractAuthenticationProcessingFilter 클래스의 코드 일부
```
- (1)을 통해 AbstractAuthenticationProcessingFilter 클래스가 Spring Security의 Filter임을 알 수 있음
  - (1-1)에서는 `AbstractAuthenticationProcessingFilter` 클래스가 인증 처리를 해야되는지 아니면 다음 Filter를 호출할지 여부를 결정함
    - (1-1)에서 호출하는 requiresAuthentication() 메서드는 (2)에서 확인할 수 있듯이 하위 클래스에서 전달받은 `requiresAuthenticationRequestMatcher` 객체를 통해 들어오는 요청이 인증 처리를 해야 되는지 여부를 결정함 
  - (1-2)에서는 하위 클래스에 인증을 시도해 줄 것을 요청
    - 이때 하위 클래스는 (코드 4-42)의 `UsernamePasswordAuthenticationFilter`임
  - (1-3)에서는 인증에 성공하면 처리할 동작을 수행하기 위해 `successfulAuthentication()` 메서드를 호출함
    - `successfulAuthentication()` 메서드는 (3)에서 확인할 수 있다시피 인증에 성공한 이후
      - **SecurityContextHolder를 통해 사용자의 인증 정보를 SecurityContext에 저장한 뒤, SecurityContext를 HttpSession에 저장**
  - 만약 인증에 실패한다면 (1-4)와 같이 `unsuccessfulAuthentication()` 메서드를 호출해 인증 실패 시 처리 동작 수행
    - SeucurityContext를 초기화 하고, `AuthenticationFailureHandler` 를 호출

### UsernamePasswordAuthenticationToken
- `UsernamePasswordAuthenticationToken`은 Spring Security에서 Username/Password로 인증을 수행하기 위해 필요한 토큰
  - 인증 성공 후 인증에 성공한 사용자의 인증 정보가 UsernamePasswordAuthenticationToken에 포함되어 Authentication 객체 형태로 SecurityContext에 저장
```java
public class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken {

	...

	private final Object principal;

	private Object credentials;

  ...
  ...

	
  // (1)
	public static UsernamePasswordAuthenticationToken unauthenticated(Object principal, Object credentials) {
		return new UsernamePasswordAuthenticationToken(principal, credentials);
	}

	
  // (2)
	public static UsernamePasswordAuthenticationToken authenticated(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
	}
  ...
  ...
}
// [코드 4-44] UsernamePasswordAuthenticationToken 클래스의 코드 일부
```
- `Principal`은 Username 등의 신원을 의미
- `credentials`는 Password를 의미
- (1)의 `unauthenticated()` 메서드는 인증에 필요한 용도의 UsernamePasswordAuthenticationToken 객체를 생성
- (2)의 `authenticated()` 메서드는 인증에 성공한 이후 SecurityContext에 저장될 UsernamePasswordAuthenticationToken 객체를 생성

### Authentication
- Spring Security에서의 인증 자체를 표현하는 인터페이스
- 이전에 AbstractAuthenticationToken 추상 클래스를 상속하는 확장 클래스이자 Authentication 인터페이스의 메서드 일부를 구현하는 구현 클래스인 `UsernamePasswordAuthenticationToken`을 확인했음
  - 인증을 위해 생성되는 인증 토큰 또는 인증 성공 후 생성되는 토큰은 `UsernamePasswordAuthenticationToken`과 같은 하위 클래스의 형태로 생성됨
    - 생성된 토큰을 리턴 받거나 SecurityContext에 저장될 경우, Authentication 형태로 리턴 받거나 저장됨
```java
public interface Authentication extends Principal, Serializable {
	Collection<? extends GrantedAuthority> getAuthorities();
	Object getCredentials();
	Object getDetails();
	Object getPrincipal();
	boolean isAuthenticated();
	void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;
}
// (코드 4-44) Authentication 인터페이스의 코드
```
- Princiapl
  - `princiapl`은 사용자를 식별하는 고유 정보
    - 일반적으로 Username/Password 기반 인증에서 `Username`이 Principal이 되며, 다른 인증 방식에서는 `UserDetails`가 Principal이 됨
- Credentials
  - 사용자 인증에 필요한 Password를 의미
    - 인증이 이뤄지고 난 직후, `ProviderManager`가 해당 Credentials를 삭제함
- Authorities
  - `AuthenticationProvider`에 의해 부여된 사용자의 접근 권한 목록
    - 일반적으로 `GrantedAuthority` 인터페이스의 구현 클래스는 `SimpleGrantedAuthority`임

### AuthenticationManager
- 인증 처리를 총괄하는 매니저 역할을 하는 인터페이스
```java
public interface AuthenticationManager {

	Authentication authenticate(Authentication authentication) throws AuthenticationException;

}
```
- AuthenticationManager에는 `authenticate()`메서드 하나만 정의되어 있음
- 인증을 위한 Filter는 AuthenticationManager를 통해 느슨한 결합을 유지
  - 인증을 위한 실질적인 관리는 AuthenticationManager를 구현하는 구현 클래스를 통해 이뤄짐

### ProviderManager
- AuthenticationManager를 구현하는 것은 어떤 클래스이든 가능하지만, 일반적으로 `ProviderManager`를 가리킴
- `ProviderManager`는 AuthenticationProvider를 관리하고, AuthenticationProvider 에게 인증 처리를 위임하는 역할 
```java
public class ProviderManager implements AuthenticationManager, MessageSourceAware, InitializingBean {
  ...
  ...

  // (1)
	public ProviderManager(List<AuthenticationProvider> providers, AuthenticationManager parent) {
		Assert.notNull(providers, "providers list cannot be null");
		this.providers = providers;
		this.parent = parent;
		checkState();
	}

  ...
  ...

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Class<? extends Authentication> toTest = authentication.getClass();
		AuthenticationException lastException = null;
		AuthenticationException parentException = null;
		Authentication result = null;
		Authentication parentResult = null;
		int currentPosition = 0;
		int size = this.providers.size();

    // (2)
		for (AuthenticationProvider provider : getProviders()) {
			if (!provider.supports(toTest)) {
				continue;
			}
			if (logger.isTraceEnabled()) {
				logger.trace(LogMessage.format("Authenticating request with %s (%d/%d)",
						provider.getClass().getSimpleName(), ++currentPosition, size));
			}
			try {
				result = provider.authenticate(authentication);  // (3)
				if (result != null) {
					copyDetails(authentication, result);
					break;
				}
			}
			catch (AccountStatusException | InternalAuthenticationServiceException ex) {
				prepareException(ex, authentication);
				throw ex;
			}
			catch (AuthenticationException ex) {
				lastException = ex;
			}
		}

		...
    ...

		if (result != null) {
			if (this.eraseCredentialsAfterAuthentication && (result instanceof CredentialsContainer)) {
				((CredentialsContainer) result).eraseCredentials(); // (4)
			}

			if (parentResult == null) {
				this.eventPublisher.publishAuthenticationSuccess(result);
			}

			return result;
		}
    
    ...
    ...
	}

  ...
  ...
}
// (코드 4-46) ProviderManager 클래스 코드 일부
```
- (1)에서 ProviderManager 클래스가 Bean으로 등록 시, `List<AuthenticationProvider>` 객체를 DI 받는다는 것을 알 수 있음
- DI 받은 List를 이용해 (2)와 같이 for문으로 적절한 AuthenticationProvider를 찾음
- 적절한 AuthenticationProvider를 찾았다면 (3)과 같이 해당 AuthenticationProvider에게 인증 처리를 위임함
- 인증이 정상적으로 처리되었다면 (4)와 같이 인증에 사용된 Credentials를 제거

### AuthenticationProvider
- AuthenticationManager로부터 인증 처리를 위임 받아 실질적인 인증 수행을 담당하는 컴포넌트임
- Username/Password 기반의 인증 처리는 `DaoAuthenticationProvider`가 담당, `DaoAuthenticationProvider`는 UserDetailsService로 부터 전달 받은 UserDetails를 이용해 인증 처리 
```java
public class DaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider { // (1)
  ...
  ...

	private PasswordEncoder passwordEncoder;

	...
  ...

  // (2)
	@Override
	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		prepareTimingAttackProtection();
		try {
			UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username); // (2-1)
			if (loadedUser == null) {
				throw new InternalAuthenticationServiceException(
						"UserDetailsService returned null, which is an interface contract violation");
			}
			return loadedUser;
		}
		catch (UsernameNotFoundException ex) {
			mitigateAgainstTimingAttack(authentication);
			throw ex;
		}
		catch (InternalAuthenticationServiceException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
		}
	}

  // (3)
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			this.logger.debug("Failed to authenticate since no credentials provided");
			throw new BadCredentialsException(this.messages
					.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		String presentedPassword = authentication.getCredentials().toString();
		if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) { // (3-1)
			this.logger.debug("Failed to authenticate since password does not match stored value");
			throw new BadCredentialsException(this.messages
					.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
	}

  ...
  ...
}
// (코드 4-47) DaoAuthenticationProvider 클래스 코드 일부
```
- (1)을 보면 `DaoAuthenticationProvider`는 `AbstractUserDetailsAuthenticationProvider`를 상속하는 것을 확인 가능
  - AuthenticationProvider 인터페이스의 구현 클래스는 `AbstractUserDetailsAuthenticationProvider`임
  - DaoAuthenticationProvider는 `AbstractUserDetailsAuthenticationProvider`를 상속한 확장 클래스임
  - 따라서 `AbstractUserDetailsAuthenticationProvider` 추상 클래스의 `authenticate()` 메서드에서부터 실질적인 인증 처리가 시작됨
- (2)의 `retrieveUser()` 메서드는 UserDetailsService로부터 UserDetails를 조회하는 역할을 함
  - 조회된 UserDetails는 사용자를 인증하는데 사용될 뿐만 아니라 인증에 성공할 경우, 인증된 Authentication 객체를 생성
  - (2-1)의 `this.getUserDetailsService().loadUserByUsername(username);`에서 UserDetails를 조회하는 것을 확인 가능함
- (3)의 `additionalAuthenticationChecks()`메서드에서 PasswordEncoder를 이용해 사용자의 패스워드를 검증
  - (3-1)에서 클라이언트로부터 전달 받은 패스워드와 DB에서 조회한 패스워드가 일치하는지 검증
- 메서드가 호출되는 순서가 중요함
  - 1. `AbstractUserDetailsAuthenticationProvider`의 authenticated() 메서드 호출
  - 2. `DaoAuthenticationProvider`의 retrieveUser() 메서드 호출
  - 3. `DaoAuthenticationProvider`의 additionalAuthenticationChecks() 메서드 호출
  - 4. `DaoAuthenticationProvider` 의 createSuccessAuthentication() 메서드 호출
  - 5. `AbstractUserDetailsAuthenticationProvider` 의 createSuccessAuthentication() 메서드 호출
  - 6. 인증된 Authentication을 `ProviderManager`에게 리턴

### UserDetails
- `UserDetails`는 DB 등의 저장소에 저장된 사용자의 Username과 사용자의 자격을 증명하는 `크리덴셜`인 Password, 그리고 사용자의 권한 정보를 포함하는 컴포넌트임
  - `AuthenticationProvider`는 `UserDetails`를 이용해 자격 증명을 수행
```java
public interface UserDetails extends Serializable {

	Collection<? extends GrantedAuthority> getAuthorities(); // (1) 권한 정보
	String getPassword(); // (2) 패스워드
	String getUsername(); // (3) Username

	boolean isAccountNonExpired();  // (4)
	boolean isAccountNonLocked();   // (5)
	boolean isCredentialsNonExpired(); // (6)
	boolean isEnabled();               // (7)
}
// (코드 4-48) UserDetails 인터페이스 코드
```
- `UserDetails` 인터페이스 코드
  - 사용자의 권한 정보(1), 패스워드 (2), Username(3) 포함
  - 계정 만료 여부(4), 계정의 lock 여부(5), Credentials(Password)의 만료 여부(6), 사용자의 활성화 여부(7)에 대한 정보를 포함

### UserDetailsService
- UserDetails를 로드(load)하는 핵심 인터페이스
```java
public interface UserDetailsService {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
// (코드 4-49) UserDetailsService 
```
- UserDetailsService는 `loadUserByUsername(String username)` 메서드 하나만 정의
  - UserDetailsService 구현 클래스는 이를 통해 사용자의 정보를 로드함
    - 어디서 로드하는지는 애플리케이션에 따라 달라짐
    - 어디서 로드하든 Spring Security가 이해할 수 있는 UserDetails로 리턴해주기만 하면 됨

### SecurityContext와 SecurityContextHolder
- `SecurityContext`는 인증된 Authentication 객체를 저장하는 컴포넌트
- `SecurityContextHolder`는 SecurityContext를 관리하는 역할을 담당
- Spring Security 입장에서는 SecurityContextHolder에 의해 SecurityContext에 값이 채워져 있다면 인증된 사용자로 간주함
- ![image](https://user-images.githubusercontent.com/102513932/203237559-f252c585-b76e-4c09-9866-9feb612571a7.png)
  - SecurityContext가 인증된 Authentication을 포함
    - 이 SecurityContext를 다시 SecurityContextHolder가 포함
      - `SecurityContextHolder`를 통해 인증된 Authentication를 SecurityContext에 설정 가능
      - `SecurityContextHolder`를 통해 인증된 Authentication 객체에 접근 가능
```java
public class SecurityContextHolder {
  ...
  ...

  private static SecurityContextHolderStrategy strategy;  // (1)
  
  ...
  ...

  // (2)
	public static SecurityContext getContext() {
		return strategy.getContext();
	}

  ...
  ...

  // (3)
	public static void setContext(SecurityContext context) {
		strategy.setContext(context);
	}

  ...
  ...
}
// (코드 4-50) SecurityContextHolder 클래스의 코드 일부
```
- (1)은  SecurityContextHolder에서 사용하는 전략을 의미하며, SecurityContextHolder 기본 전략은 `ThreadLocalSecurityContextHolderStrategy`임
  - 이 전략은 현재 실행 쓰레드에 SecurityContext를 연결하기 위해 ThreadLocal을 사용하는 전략
- (2)의 `getContext()` 메서드를 통해 현재 실행 쓰레드에서 SecurityContext를 얻을 수 있음
- (3)의 setContext() 메서드는 현재 실행 쓰레드에 SecurityContext를 연결
  - setContext()는 대부분 인증된 Authentication을 포함한 SecurityContext를 현재 실행 쓰레드에 연결하는데 사용

## 핵심 포인트
- `UsernamePasswordAuthenticationFilter` 는 클라이언트로부터 전달 받은 Username과 Password를 Spring Security가 인증 프로세스에서 이용할 수 있도록 `UsernamePasswordAuthenticationToken` 을 생성
- `AbstractAuthenticationProcessingFilter`는 HTTP 기반의 인증 요청을 처리하지만 실질적인 인증 시도는 하위 클래스에 맡기고, 인증에 성공하면 인증된 사용자의 정보를 SecurityContext에 저장
- `Authentication`은 Spring Security에서의 인증 자체를 표현하는 인터페이스
- `AuthenticationManager`는 이름 그대로 인증 처리를 총괄하는 매니저 역할을 하는 인터페이스이며, 인증을 위한 실질적인 관리는 AuthenticationManager를 구현하는 구현 클래스를 통해 이루어짐
- `ProviderManager`는 AuthenticationProvider를 관리하고, AuthenticationProvider에게 인증 처리를 위임하는 역할
- `AuthenticationProvider`는 AuthenticationManager로부터 인증 처리를 위임 받아 실질적인 인증 수행을 담당하는 컴포넌트
- `UserDetails` 는 데이터베이스 등의 저장소에 저장된 사용자의 Username과 사용자의 자격을 증명해주는 크리덴셜(Credential)인 Password 그리고 사용자의 권한 정보를 포함하는 컴포넌트
  - `AuthenticationProvider` 는 UserDetails를 이용해 자격 증명을 수행
- `UserDetailsService`는 UserDetails를 로드(load)하는 핵심 인터페이스
- `SecurityContext`는 인증된 Authentication 객체를 저장하는 컴포넌트
- `SecurityContextHolder`는 SecurityContext를 관리하는 역할을 담당



