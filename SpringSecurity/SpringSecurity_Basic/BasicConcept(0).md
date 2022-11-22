## Spring Security
- Spring Security는 Spring MVC 기반 애플리케이션의 인증(Authentication)과 인가(Authorization or 권한부여) 기능을 지원하는 보안 프레임워크
  - Spring MVC 기반 애플리케이션에 보안을 적용하기 위한 사실상의 표준임

> 참고: 인증은 사용자의 신원을 검증하는 행위로, 보안 프로세스에서 첫 번째 단계이고 <br>
> 인가는 사용자에게 특정 리소스나 기능에 액세스 할 수 있는 권한을 부여하는 프로세스이다.

### Spring Security로 할 수 있는 보안 강화 기능
- 다양한 유형(폼 로그인 인증, 토큰 기반 인증, OAuth2 기반 인증, LDAP 인증)의 사용자 인증 기능 적용
- 애플리케이션 사용자의 역할(Role)에 따른 권한 레벨 적용
- 애플리케이션에서 제공하는 리소스에 대한 접근 제어
- 민감한 정보에 대한 데이터 암호화
- SSL 적용
- 일반적으로 알려진 웹 보안 공격 차단
- 이외에도 SSO, 클라이언트 인증서 기반 인증, 메서드 보안, 접근 제어 목록 같은 보안을 위한 대부분의 기능 지원

### Spring Security에서 사용하는 용어 정리
- **Princiapl(주체)**
  - `Princiapl`은 애플리케이션에서 작업을 수행할 수 있는 사용자 또는 디바이스, 시스템 등이 될 수 있음
  - 일반적으로 인증 프로세스가 성공적으로 수행된 사용자의 *계정 정보를* 의미함
- **Authentication(인증)**
  - 애플리케이션을 사용하는 사용자가 본인이 맞음을 증명하는 절차
  - 정상적으로 수행하기 위해서는 사용자를 식별하기 위한 정보가 필요
    - 이를 `Credential(신원 증명 정보)`라고 칭함
      - ex) 주민번호, 패스워드
- **Authorization(인가 또는 권한 부여)**
  - 정상적으로 수행된 사용자에게 하나 이상의 권한(authority)를 부여하여 특정 애플리케이션의 특정 리소스에 접근할 수 있게 허가하는 과정
  - Authentication 과정 이후 수행되어야 하며 권한은 일반적으로 역할(Role) 형태로 부여됨
- **Access Control(접근 제어)**
  - 사용자가 애플리케이션의 리소스에 접근하는 행위를 제어하는 것을 의미함

> 참고 : [세션 고정 공격](https://owasp.org/www-community/attacks/Session_fixation) 

> 참고 : [클랙재킹 공격](https://ko.wikipedia.org/wiki/클릭재킹)

> 참고 : [CSRF](https://namu.wiki/w/CSRF)