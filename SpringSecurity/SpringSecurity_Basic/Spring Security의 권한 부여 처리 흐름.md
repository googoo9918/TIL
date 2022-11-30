### 목차
- [Spring Security의 권한 부여 처리 흐름](#spring-security의-권한-부여-처리-흐름)
	- [Spring Security의 컴포넌트로 보는 권한 부여(Authorization) 처리 흐름](#spring-security의-컴포넌트로-보는-권한-부여authorization-처리-흐름)
	- [Spring Security의 권한 부여 컴포넌트](#spring-security의-권한-부여-컴포넌트)
		- [AuthorizationFilter](#authorizationfilter)
		- [AuthorizationManger](#authorizationmanger)
		- [RequestMatcherDelegatingAuthorizationManager](#requestmatcherdelegatingauthorizationmanager)
	- [핵심 포인트](#핵심-포인트)

# Spring Security의 권한 부여 처리 흐름
- Spring Security Filter Chain에 도달한 사용자의 인증 요청 처리 후, 인증된 사용자임을 확인했다 가정
  - 인증된 사용자는 권한 부여(인가)를 받아야 특정 리소스에 접근 가능함

## Spring Security의 컴포넌트로 보는 권한 부여(Authorization) 처리 흐름
- ![Q5eOpRTCUDlvqS_OL497Y-1663489878112](https://user-images.githubusercontent.com/102513932/203240080-efe2a802-6b23-491e-ab8e-b77524e44714.png)
  - (그림 4-18)을 통해 사용자가 로그인 인증에 성공한 이후, Spring Security에서 인증된 사용자에게 어떻게 권한을 부여하는지 처리흐름을 알 수 있음
  - Spring Security Filter Chain에서 URL을 통해 사용자의 액세스를 제한하는 권한 부여 Filter는 바로 `AuthorizationFilter`
    - `AuthorizationFilter` 는 먼저 (1)과 같이 SecurityContextHolder로 부터 Authentication을 획득
  - (2)와 같이 SecurityContextHolder로 부터 획득한Authentication과 HttpServletRequest를 `AuthorizationManager` 에게 전달
  - `AuthorizationManager` 는 권한 부여 처리를 총괄하는 매니저 역할을 하는 인터페이스
    - `RequestMatcherDelegatingAuthorizationManager` 는 `AuthorizationManager` 를 구현하는 구현체 
  - `RequestMatcherDelegatingAuthorizationManager` 는 RequestMatcher 평가식을 기반으로 해당 평가식에 매치되는 `AuthorizationManager` 에게 권한 부여 처리를 위임하는 역할
    - `RequestMatcherDelegatingAuthorizationManager`가 직접 권한 부여 처리를 하는 것이 아닌, `RequestMatcher`를 통해 매치되는 `AuthorizationManager` 구현 클래스에게 위임만 함
  - `RequestMatcherDelegatingAuthorizationManager` 내부에서 매치되는 `AuthorizationManager` 구현 클래스가 있다면 해당 `AuthorizationManager` 구현 클래스가 사용자의 권한을 체크(3)
  - 적절한 권한이라면 (4)와 같이 다음 요청 프로세스를 계속 이어나감
  - 만약 적절한 권한이 아니라면 (5)와 같이 `AccessDeniedException`이 throw되고 ExceptionTranslationFilter가 `AccessDeniedException`을 처리하게 됨

> Spring Security 5.5 버전부터 AuthorizationFilter를 통해 간결한 `AuthorizationManger` API를 이용해 권한 부여 처리를 할 수 있게됨 <br>
> 따라서 metadata sources, config attrivutes, decision managers, voters 등을 이용한 권한 부여 처리 과정에 대해서는 학습하지 않음
## Spring Security의 권한 부여 컴포넌트
### AuthorizationFilter
- `AuthorizationFilter`는 URL을 통해 사용자의 액세스를 제한하는 권한 부여 Filter임
  - Spring Security 5.5 부터 FilterSecurityInterceptor를 대체
```java
public class AuthorizationFilter extends OncePerRequestFilter {

	private final AuthorizationManager<HttpServletRequest> authorizationManager;
  
  ...
  ...
	
  // (1)
	public AuthorizationFilter(AuthorizationManager<HttpServletRequest> authorizationManager) {
		Assert.notNull(authorizationManager, "authorizationManager cannot be null");
		this.authorizationManager = authorizationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		AuthorizationDecision decision = this.authorizationManager.check(this::getAuthentication, request); // (2)
		this.eventPublisher.publishAuthorizationEvent(this::getAuthentication, request, decision);
		if (decision != null && !decision.isGranted()) {
			throw new AccessDeniedException("Access Denied");
		}
		filterChain.doFilter(request, response);
	}
  ...
  ...
}
// (코드 4-51) AuthorizationFilter 코드 일부
```
- (1)과 같이 AuthorizationFilter 객체 생성 시, AuthorizationManger를 DI 받음
  - DI 받은 AuthorizationManager를 통해 권한 부여 처리를 진행함
- (2)와 같이 DI 받은 AuthorizationManager의 check() 메서드를 호출해 적절한 권한 부여 여부를 체크
  - AuthorizationManager의 `check()` 메서드는 AuthorizationManager 구현 클래스에 따라 권한 체크 로직이 다름
  - URL 기반으로 권한 부여 처리를 하는 AuthorizationFilter는 AuthorizationManager의 구현 클래스로 `RequestMatcherDelegatingAuthorizationManager`를 사용

### AuthorizationManger
- `AuthorizationManger`는 권한 부여 처리를 총괄하는 매니저 역할을 하는 인터페이스
```java
@FunctionalInterface
public interface AuthorizationManager<T> {
  ...
  ...

	@Nullable
	AuthorizationDecision check(Supplier<Authentication> authentication, T object);
}
// (코드 4-52) AuthorizationManager 코드 일부
```
- AuthorizationManager 인터페이스는 check() 메서드 하나만 정의되어 있음
  - Supplier와 제네릭 타입의 객체를 파라미터로 가짐 

### RequestMatcherDelegatingAuthorizationManager
- `RequestMatcherDelegatingAuthorizationManager` 는 AuthorizationManger의 구현 클래스 중 하나임
  - 직접 권한 부여 처리를 수행하지 않고 `RequestMatcher`를 통해 매치되는 `AuthorizationManger` 구현 클래스에게 권한 부여 처리를 위임함
```java
public final class RequestMatcherDelegatingAuthorizationManager implements AuthorizationManager<HttpServletRequest> {

  ...
  ...

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, HttpServletRequest request) {
		if (this.logger.isTraceEnabled()) {
			this.logger.trace(LogMessage.format("Authorizing %s", request));
		}

    // (1)
		for (RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> mapping : this.mappings) {

			RequestMatcher matcher = mapping.getRequestMatcher(); // (2)
			MatchResult matchResult = matcher.matcher(request);
			if (matchResult.isMatch()) {   // (3)
				AuthorizationManager<RequestAuthorizationContext> manager = mapping.getEntry();
				if (this.logger.isTraceEnabled()) {
					this.logger.trace(LogMessage.format("Checking authorization on %s using %s", request, manager));
				}
				return manager.check(authentication,
						new RequestAuthorizationContext(request, matchResult.getVariables()));
			}
		}
		this.logger.trace("Abstaining since did not find matching RequestMatcher");
		return null;
	}
}
// [코드 4-53] RequestMatcherDelegatingAuthorizationManger 클래스의 코드 일부
```
- check() 메서드의 내부에서 (1)과 같이 루프를 돌며 `RequestMatcherEntry` 정보를 얻은 후에 (2)와 같이 `RequestMatcher` 객체를 얻음
- (3)과 같이 MatchResult.isMatch()가 true이면 AuthorizationManager 객체를 얻은 뒤, 사용자의 권한을 체크함
  -  여기서의 `RequestMatcher`는 SecurityConfiguration에서 `.antMatchers("/orders/**").hasRole("ADMIN")` 와 같은 메서드 체인 정보를 기반으로 생성됨 

## 핵심 포인트
- `AuthorizationFilter`는 URL을 통해 사용자의 액세스를 제한하는 권한 부여 Filter이며, Spring Security 5.5 버전부터 FilterSecurityInterceptor를 대체
- `AuthorizationManager`는 이름 그대로 권한 부여 처리를 총괄하는 매니저 역할을 하는 인터페이스
- `RequestMatcherDelegatingAuthorizationManager` 는 AuthorizationManager의 구현 클래스 중 하나
  - 직접 권한 부여 처리를 수행하지 않고, `RequestMatcher`를 통해 매치되는 `AuthorizationManager` 구현 클래스에게 권한 부여 처리를 위임함
    - `RequestMatcher`는 SecurityConfiguration에서 `.antMatchers("/orders/**").hasRole("ADMIN")`와 같은 메서드 체인 정보를 기반으로 생성됨