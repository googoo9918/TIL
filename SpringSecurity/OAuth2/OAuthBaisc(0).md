### 목차
- [OAuth 2란?](#oauth-2란)
  - [OAuth 2란?](#oauth-2란-1)
  - [OAuth 2를 사용하는 애플리케이션 유형](#oauth-2를-사용하는-애플리케이션-유형)
    - [써드 파티 애플리케이션에서 제공하는 API의 직접적인 사용](#써드-파티-애플리케이션에서-제공하는-api의-직접적인-사용)
    - [추가적인 인증 서비스 제공 용도](#추가적인-인증-서비스-제공-용도)
- [OAuth 2의 동작 방식](#oauth-2의-동작-방식)
  - [OAuth 2 인증 컴포넌트(Componetnt, 구성요소)들의 역할](#oauth-2-인증-컴포넌트componetnt-구성요소들의-역할)
    - [Resource Owner](#resource-owner)
    - [Client](#client)
    - [Resource Server](#resource-server)
    - [Authorization Server](#authorization-server)
    - [컴포넌트 상호작용](#컴포넌트-상호작용)
  - [OAuth 2 인증 프로토콜에서 사용되는 용어](#oauth-2-인증-프로토콜에서-사용되는-용어)
    - [Authorization Grant](#authorization-grant)
    - [Access Token](#access-token)
    - [Scope](#scope)
  - [Authorization Grant 유형](#authorization-grant-유형)
    - [Authorization Code Grant : 권한 부여 승인 코드 방식](#authorization-code-grant--권한-부여-승인-코드-방식)
    - [Implict Grant: 암묵적 승인 방식](#implict-grant-암묵적-승인-방식)
    - [Resource Owner Password Credential Grant: 자원 소유자 자격 증명 승인 방식](#resource-owner-password-credential-grant-자원-소유자-자격-증명-승인-방식)
    - [Client Credentials Grant: 클라이언트 자격 증명 승인 방식](#client-credentials-grant-클라이언트-자격-증명-승인-방식)

# OAuth 2란?

## OAuth 2란?
- 전통적으로 특정 애플리케이션의 서비스를 이용하는 사용자에 대한 인증 처리는 해당 서비스를 직접적으로 제공하는 애플리케이션에서 담당해 왔음
  - 해당 서비스를 이용하는 사용자의 크리덴셜을 직접적으로 관리하는 것이 일반적
- ex) 사용자에 대한 일정 관리 서비스를 제공하는 애플리케이션
  - 이 서비스는 자체적으로 회원의 크리덴셜을 관리하는 단순한 애플리케이션임
    - ![image](https://user-images.githubusercontent.com/102513932/203917657-6e8e2b15-b2e4-4e54-88f5-fcb3374e140e.png)
      - (그림 4-29) 전통적인 애플리케이션에서 사용자의 크리덴셜을 저장하는 아키텍쳐
      - OAuth2는 위 그림과 같은 전통적 인증 처리 방식과 다른 방식으로 동작함
- ex) 위 서비스에서 사용자에게 캘린더 형태의 서비스를 제공하기 위해 Google의 Calendar Api를 이용하는 기능이 추가
  - 이 경우, 아래 그림과 같이 회원 가입을 통해 사용자의 크리덴셜을 관리함과 동시에 Google에서 사용하는 사용자의 크리덴셜까지 일정 관리 서비스와 공유해야함
    - ![image](https://user-images.githubusercontent.com/102513932/203918016-f9b6b90c-61b3-4d53-8922-fd5f7fb770d6.png)
      - (그림 4-30) 써드 파티 API 사용을 위해 써드 파티 애플리케이션의 크리덴셜을 저장하는 아키텍쳐
      - 이 경우, 서비스를 사용하는 시점에 Google Calaendar API를 이용하기 위해 Google에서 사용하는 사용자의 크리덴셜을 일정관리 애플리케이션에 추가적으로 제공해야 함
        - 제공한 Google에 대한 크리덴셜은 일정 관리 서비스 애플리케이션 어딘가에 또 저장되게 됨
      - 문제점
        - 사용자에게 가장 중요한 정보인 크리덴셜이 두 개나 관리되어야 함
        - Google 사이트에서 패스워드를 변경하더라도 변경된 Google의 패스워드를 일정 관리 서비스 애플리케이션에서 추가로 업데이트 해줘야 함
        - 일정 관리 애플리케이션이 Google 사이트에서 사용하는 크리덴셜을 직접적으로 갖고 있는 것도 보안상 심각한 문제임
    - 이 문제점을 보완하는 효과적인 방법 중 하나는 Google에서 사용하는 사용자의 크리덴셜을 일정 관리 서비스에서 직접적으로 관리하지 않으면서도 Google Calendar API를 이용할 수 있게 하는 것임
      - OAuth 2 인증 프로토콜 사용 시 가능
- OAuth2는 특정 애플리케이션에서 사용자의 인증을 직접 처리하는 것이 아닌, 사용자 정보를 보유하고 있는 신뢰할 만한 써드 파티 애플리케이션(GitHub, Google, Fadebook 등)에서 사용자의 인증을 대신 처리해 줌
  - Resource에 대한 자격 증명용 토큰을 발급한 후, Client가 해당 토큰을 이용해 써드 파티 애플리케이션의 서비스를 사용하게 해주는 방식임
  - 즉, 위 그림에서 일정 관리 서비스를 사용하는 사용자의 Google 전용 크리덴셜이 일정 관리 서비스 애플리케이션에 직접적으로 제공되지 않아도 됨
  - 로그인 자체는 구글 로그인 인증을 이용, 구글 로그인에 성공하면 Access Token을 전달 받아 Google Calendar API를 사용하기 위해 Access Token을 이용함
    - ![2XJCUdi2R9arTMg2WqU5G-1664237960637](https://user-images.githubusercontent.com/102513932/203920198-79e38420-f7ea-4154-9a11-cc34a8b6867f.png)
      - (그림 4-31) 써드 파티 애플리케이션의 크리덴셜을 저장하지 않는 아키텍처
      - 구글의 크리덴셜이 직접적으로 제공되지 않기 때문에 사용자의 크리덴셜을 이중으로 관리하지 않아도 됨
        - 관리하는 크리덴셜이 줄어든다는 것은 그만큼 보안성도 향상된다는 의미임

## OAuth 2를 사용하는 애플리케이션 유형
### 써드 파티 애플리케이션에서 제공하는 API의 직접적인 사용
- 첫 번째로, Google, GitHub, Facebook과 같은 신뢰할만한 써드 파티 애플리케이션에서 제공하는 API를 직접적으로 사용하는 애플리케이션을 구현하는데 OAuth 2를 사용 가능함
- 사용자가 OAuth 2 인증 프로토콜을 이용해 파티 애플리케이션에 대한 인증에 성공 시, 써드 파티 애플리케이션에서 제공하는 API를 활용한 커스텀 서비스를 제공하는 것임

### 추가적인 인증 서비스 제공 용도
- 써드 파티 애플리케이션의 서비스를 직접 이용하는 것 뿐 아니라, 추가적인 인증 서비스를 제공하기 위한 용도로 사용
  - 일반적으로 제공하는 아이디/패스워드 로그인 인증 이외에 OAuth 2를 이용한 로그인 인증 방법을 추가적으로 제공함
  - 만약 특정 서비스를 제공하는 애플리케이션에서 사용자의 크리덴셜을 남기고 싶지 않은 경우
    - OAuth 2 로그인 인증 방법으로 로그인

# OAuth 2의 동작 방식

## OAuth 2 인증 컴포넌트(Componetnt, 구성요소)들의 역할
### Resource Owner
- `Resource Owner`는 사용하고자 하는 Resource의 소유자를 말함
  - Google등의 서비스를 이용하는 사용자라 생각
    - Bob이라는 사용자가 구글 계정으로 구글 서비스 이용 시
      - Bob이 Google 서비스라는 Resource에 대한 Resource Owner가 됨
### Client
- Resource Owner를 대신해 보호된 Resource에 액세스하는 애플리케이션
  - `Client`는 서버, 데스크탑, 모바일 또는 기타 장치에서 실행되는 애플리케이션이 될 수 있음
  - ex) Bob이라는 사용자가 A라는 애플리케이션을 통해 Google의 소셜로그인을 이용한다면, 애플리케이션A가 `Client`가 됨
    - 어떤 서비스를 이용하고자 하는 쪽은 Client임!
    - 외부의 API 서비스를 이용하는 측면에서는 외부 서비스를 제공하는 쪽이 Server, 이용하는 쪽이 CLient임
### Resource Server
- OAuth 2 인증 처리 흐름 상에서 `Resource Server`는 Client의 요청을 수락하고 Resouce Owner에게 해당하는 Resource를 제공하는 서버임
  - ex) A라는 애플리케이션(Client)이 Google Photo 에서 Bob이라는 Resource Owner의 사진(Resource)을 가져오는 경우, Google Photo 서비스를 제공하는 애플리케이션이 `Resource Server`가 됨
### Authorization Server
- OAuth 2 인증 처리 흐름에서 `Authorization Server`는 Client가 Resource Server에 접근할 수 있는 권한을 부여하는 서버임
  - Resource Owner가 인증에 성공 시 , `Authorization Server`는 Client에게 Access Token 형태로 Resource Owner의 Resource에 접근할 수 있는 권한을 부여함
  - Bob이라는 사용자(Resource Owner)가 구글 로그인 인증에 성공 시 , A라는 애플리케이션(Client)이 `Authorization Server`로부터 Google Photo에 저장되어 있는 Bob의 사진(Resource)에 접근할 수 있는 권한(Access Token)을 부여 받음

### 컴포넌트 상호작용
- ![nN_Y6RgU1iiUftpVNYyVo-1664238216432](https://user-images.githubusercontent.com/102513932/203923107-a6ec96d9-a01c-4e3f-95ce-a4692cd3d046.png)
  - (그림 4-32) OAuth 2 컴포넌트 간의 상호 작용을 통한 인증 처리 흐름
  - (1) Resourcce Owner는 **Client 역할을 하는 웹 애플리케이션**에게 OAuth2 인증을 요청 
    - Resource Owner는 자신의 계정 정보를 관리하고 있는 써드 파티 애플리케이션에 로그인 하길 원하고 있으며, 이 요청을 Client인 웹 애플리케이션에 전송
  - (2) Client는 Resource Owner가 계정 정보를 관리하고 있는 써드 파티 애플리케이션에 로그인 할 수 있도록 써드 파티 애플리케이션의 로그인 페이지로 리다이렉트
  - (3) Resource Owner는 로그인 인증을 진행하고 로그인 인증에 성공 시
  - (4) Authorization Server가 Resource Owner의 로그인 인증이 성공적으로 수행되었음을 증명하는 Access Token을 Client에게 전송함
    - Resource Owner에게 전송하는 것이 아닌 Client 애플리케이션에 전송하는 것임을 기억하라
      - Client는 Resource를 사용하는 대리인 역할을 하고 있음
  - (5) Access Token을 전달 받은 Client는 이제 Resource Owner의 대리인 역할을 수행할 수 있게됨
    - Resource Server에게 Resource Owner 소유의 Resource를 요청함
  - (6) Resource Server는 Client가 전송한 Access Token을 검증, 자격이 증명되면 Resource Owner의 Resource를 Client에게 전송함
- 핵심은 어떤 Resource를 소유하고 있는 Resource Owner를 대신하는 누군가(Client)가 Resource Owner의 대리인 역할을 수행한다는 것
- ![U5LbdpMRsyx61r2gJ7H27-1664238301497](https://user-images.githubusercontent.com/102513932/203925570-22455e57-a4aa-4ce2-9502-a06438917b51.png)
  - 위 예시에서 일정 관리 애플리케이션이 Google의 Calaendar API를 이용해 사용자에게 Calendar 서비스를 제공하는 흐름
  - Google이라는 구체적인 써드 파티 애플리케이션
    - Google 네트워크 영역에 있는 부분은 로고가 빨간색 박스로 칠해짐 참고

## OAuth 2 인증 프로토콜에서 사용되는 용어
### Authorization Grant
- Authorization Grant는 Client 애플리케이션이 Access Token을 얻기 위한 수단
  - 네 가지 타입 존재
  - Authorization Code
  - Implict Grant Type
  - Client Credentials
  - Resource Owner Password Credentials
### Access Token
- `Access Token`은 Client가 Resource Server에 있는 보호된 Resource에 액세스하기 위해 사용하는 자격 증명용 토근임
- Authorization Code와 Client Secret을 이용해 Authorization Server로 부터 전달 받은 Access Token으로 자격을 증명하면, Resource Server에 접근 가능
### Scope
- `Scope`는 주어진 액세스 토큰을 사용하여 액세스 할 수 있는 Resource의 범위를 의미함
  - 예를 들어 Scope 설정을 통해 해당 Resource Owner의 사진첩에는 접근할 수 있지만, 연락처 등 다른 Resource에는 접근할 수 없도록 접근 권한을 지정할 수 있음

## Authorization Grant 유형
### Authorization Code Grant : 권한 부여 승인 코드 방식
- Authorization Code Grant는 권한 부여 승인을 위해 자체 생성한 Authorization Code를 전달하는 방식
  - 가장 많이 쓰이고 기본이 되는 방식임
- Authorization Code Grant 방식에는 Refresh Token을 사용할 수 있음
- 승인 요청 시 응답 타입(`response_type`)을 `code`로 지정하여 요청함
- ![m83K6ajL7KIIrxvQjto71-1664238393898](https://user-images.githubusercontent.com/102513932/203927304-7bba04a8-392c-4859-9ed9-0b76ea52e9d0.png)
  - (그림 4-34) Authorization Code Grant 방식의 인증 처리 흐름
  - 1. Resource Owner는 소셜 로그인 버튼을 누르는 등의 서비스 요청을 Client(애플리케이션)에게 전송함
  - 2. Client는 Authorization Sever에 Authorization Code를 요청함
    - 이 때, 미리 생성한 Client ID, Redirect URI, 응답 타입을 함께 전송
  - 3. Resource Owner는 로그인 페이지를 통해 로그인 진행
  - 4. 로그인 확인 시 Authorization Server는 Authorization Code를 Client에게 전달함
    - 이전에 요청과 함께 전달한 Redirect URI로 Code를 전달함
  - 5. Client는 전달받은 Authorization Code를 이용해 Access Token 발급을 요청
    -  AccessToken을 요청할 때 미리 생성한 Client Secret, Redirect URI, 권한 부여 방식, **Authorization Code를 함께** 전송
  - 6. 요청 정보를 확인한 후 Redirect URI로 Access Token을 발급함
  - 7. Client는 발급받은 Access Token을 이용해 Resource Server에 Resource를 요청함
  - 8. Access Token을 확인한 후 요청 받은 Resource를 Client에게 전달

### Implict Grant: 암묵적 승인 방식
- 별도의 Authorization Code 없이 바로 Access Token을 발급하는 방식
  - 자격증명을 안전하게 저장하기 힘든 Client(자바스크립트 등 스크립트 언어를 사용하는 브라우저)에게 최적화된 방식임
  - Refresh Token 사용이 불가능하며, 이 방식에서 Authorization Server는 Client Secret을 통해 클라이언트 인증 과정을 생략함
- 권한 부여 승인 요청 시 응답 타입(`response_type`)을 `token`으로 지정하여 요청
  - <img width="924" alt="W5UMsrihBRxXo12snXvUa-1664238452834" src="https://user-images.githubusercontent.com/102513932/203975382-651e42a0-21c3-49ce-a535-78d1eb1f85e5.png">
    - (그림 4-35) Implict Grant 방식의 인증 처리 흐름
    - 1. Resource Owner는 소셜 로그인 버튼을 누르는 등의 서비스 요청을 Client(애플리케이션)에게 전송
    - 2. Client는 Authorization Server에게 접근 권한 요청을 보냄
      - 요청과 함께 미리 생성한 Client ID, Redirect URI, 응답타입을 전송(Authroization Code를 획득하기 위한 요청이 아님)
    - 3. Resource Owner는 로그인 페이지를 통해 로그인을 진행
    - 4. 로그인이 확인되면 Authorization Server는 Client에게 Access Token을 전달
    - 5. Client는 Access Token을 이용해 Resource Server에게 Resource를 요청
    - 6. Access Token을 확인한 후 요청 받은 Resource를 전달

### Resource Owner Password Credential Grant: 자원 소유자 자격 증명 승인 방식
- 간단히 로그인 시 필요한 정보로 Access Token을 발급받는 방식
  - 자신의 서비스에서 제공하는 애플리케이션의 경우에만 사용됨
  - Refresh Token의 사용 또한 가능
- ex) 네이버 계정으로 네이버 웹툰 애플리케이션에 로그인, 카카오 계정으로 카카오 지도 애플리케이션에 로그인하는 경우
- 다시 말해 Authorization Server, Resource Server, Client가 모두 같은 시스템에 속해 있을 때만 사용이 가능함
  - <img width="924" alt="MgcaGyneLt5yHT-onZpjb-1664238503667" src="https://user-images.githubusercontent.com/102513932/203976846-38712ec7-fe44-4323-80a7-1d618400463b.png">
    - 1. Resource Owner는 로그인 버트를 누르는 등의 서비스 요청을 Client에게 전송함
      - 이 때 로그인에 필요한 정보를 이용해 요청
    - 2. Client에서는 Resource Owner에게서 전달받은 로그인 정보를 통해 Authorization Server에 Access Token을 요청함
      - 이 때 미리 생성한 Client ID, 권한 부여 방식, 로그인 정보를 함께 전달함
    - 3. 요청과 함께 온 정보들을 확인한 후 Client에게 Access Token을 전달함
    - 4. Client는 Access Token을 이용하여 Resource Server에게 Resource를 요청함
    - 5. Access Token을 확인한 후 요청 받은 Resource를 전달함

### Client Credentials Grant: 클라이언트 자격 증명 승인 방식 
- Client 자신이 관리하는 Resource이거나, 혹은 Authorization Server에 해당 Client를 위해 제한된 Resource 접근 권한이 설정되어 있는 경우 사용 가능한 방식
  - 자격 증명을 안전히 보관할 수 있는 Client에서만 사용되어야 함
  - Refresh Token 사용 불가능


