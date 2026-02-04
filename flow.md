### 목차
- [web 패키지](#web-패키지)
  - [카카오 로그인(회원가입)](#카카오-로그인회원가입)
- [api 패키지](#api-패키지)
  - [발급받은 카카오 Access 토큰 기반 로그인(회원가입)](#발급받은-카카오-access-토큰-기반-로그인회원가입)
  - [로그아웃](#로그아웃)
  - [회원정보조회](#회원정보조회)
  - [Access Token 재발급](#access-token-재발급)
- [domain 패키지](#domain-패키지)
  - [Auditable(BaseEntity, BaseTimeEntity)](#auditablebaseentity-basetimeentity)
  - [Member 패키지](#member-패키지)
- [external/oauth 패키지](#externaloauth-패키지)
  - [kakao 패키지](#kakao-패키지)
  - [Model 패키지](#model-패키지)
  - [Service 패키지](#service-패키지)
- [global 패키지](#global-패키지)
  - [config/jpa 패키지](#configjpa-패키지)
  - [config/web 패키지](#configweb-패키지)
  - [config/나머지 클래스](#config나머지-클래스)
    - [FeignConfiguration](#feignconfiguration)
    - [JasyptConfig](#jasyptconfig)
    - [SecurityConfig](#securityconfig)
    - [SwaggerConfig](#swaggerconfig)
  - [error/exception 패키지](#errorexception-패키지)
  - [error/ 나머지 클래스](#error-나머지-클래스)
    - [ErrorCode](#errorcode)
    - [ErrorResponse](#errorresponse)
    - [FeignClientExceptionErrorDecoder](#feignclientexceptionerrordecoder)
    - [GlobalExceptionHandler](#globalexceptionhandler)
  - [interceptor 패키지](#interceptor-패키지)
    - [AdminAuthorizationInterceptor](#adminauthorizationinterceptor)
    - [AuthenticationInterceptor](#authenticationinterceptor)
  - [jwt 패키지](#jwt-패키지)
    - [constant 패키지](#constant-패키지)
    - [dto 패키지](#dto-패키지)
    - [service 패키지](#service-패키지-1)
  - [resolver 패키지](#resolver-패키지)
    - [memberinfo 패키지](#memberinfo-패키지)
  - [Util 패키지](#util-패키지)
  - [appication.yml](#appicationyml)
- [PUSH 알림 및 CSRF 필터 상세 설명서](#push-알림-및-csrf-필터-상세-설명서)
  - [목차](#목차-1)
  - [1. mobile\_main 접속 시 PUSH 알림 동작 방식](#1-mobile_main-접속-시-push-알림-동작-방식)
    - [1.1 전체 플로우 다이어그램](#11-전체-플로우-다이어그램)
    - [1.2 Phase 1: 페이지 접속 및 초기화](#12-phase-1-페이지-접속-및-초기화)
      - [1.2.1 페이지 로드 시작](#121-페이지-로드-시작)
      - [1.2.2 push\_init.js 실행](#122-push_initjs-실행)
      - [1.2.3 requestPermissionAndGetToken() 실행](#123-requestpermissionandgettoken-실행)
    - [1.3 Phase 2: PUSH 테스트 버튼 클릭](#13-phase-2-push-테스트-버튼-클릭)
      - [1.3.1 버튼 클릭 이벤트](#131-버튼-클릭-이벤트)
      - [1.3.2 AJAX 요청 처리 (ajax.json)](#132-ajax-요청-처리-ajaxjson)
      - [1.3.3 CSRF 토큰 자동 추가](#133-csrf-토큰-자동-추가)
      - [1.3.4 서버: CSRF 필터 검증](#134-서버-csrf-필터-검증)
      - [1.3.5 서버: API 컨트롤러 처리](#135-서버-api-컨트롤러-처리)
      - [1.3.6 서버: PushService 처리](#136-서버-pushservice-처리)
      - [1.3.7 클라이언트: 메시지 수신](#137-클라이언트-메시지-수신)
  - [2. CSRF 필터 동작 방식](#2-csrf-필터-동작-방식)
    - [2.1 CSRF 공격이란?](#21-csrf-공격이란)
    - [2.2 CSRF 방어 메커니즘](#22-csrf-방어-메커니즘)
      - [2.2.1 토큰 기반 방어](#221-토큰-기반-방어)
    - [2.3 필터 등록 및 적용 범위](#23-필터-등록-및-적용-범위)
      - [2.3.1 필터 등록](#231-필터-등록)
      - [2.3.2 적용 범위](#232-적용-범위)
    - [2.4 필터 동작 흐름 상세](#24-필터-동작-흐름-상세)
      - [2.4.1 전체 흐름도](#241-전체-흐름도)
      - [2.4.2 단계별 상세 설명](#242-단계별-상세-설명)
    - [2.5 CSRF 토큰 생성](#25-csrf-토큰-생성)
      - [2.5.1 생성 시점](#251-생성-시점)
      - [2.5.2 토큰 저장](#252-토큰-저장)
    - [2.6 클라이언트에서 토큰 전송](#26-클라이언트에서-토큰-전송)
      - [2.6.1 AJAX 요청 (자동)](#261-ajax-요청-자동)
      - [2.6.2 폼 제출 (수동)](#262-폼-제출-수동)
    - [2.7 CSRF 필터 동작 예시](#27-csrf-필터-동작-예시)
      - [2.7.1 정상 요청 (AJAX)](#271-정상-요청-ajax)
      - [2.7.2 공격 요청 (CSRF)](#272-공격-요청-csrf)
      - [2.7.3 GET 요청 (검증 생략)](#273-get-요청-검증-생략)
  - [요약](#요약)
    - [PUSH 알림 동작 요약](#push-알림-동작-요약)
    - [CSRF 필터 동작 요약](#csrf-필터-동작-요약)
# web 패키지
- web 패키지를 제외하고는 패키지 이름 순서대로 설명 기술
## 카카오 로그인(회원가입)
- 전체 flow
  - ![image](https://user-images.githubusercontent.com/102513932/211477661-c1da4255-69bf-4bd8-b20e-751b29bfb828.png)
- web/kakotoken/controller login() 메서드 -> loginForm.html 페이지 불러옴 
  - ![image](https://user-images.githubusercontent.com/102513932/211471652-997811e5-e055-4d4f-aaab-a15b8fe9fd2d.png)
- 카카오 로그인 버튼 클릭(2. 카카오 로그인 페이지 요청) -> /oauth/kakao/callback 리다이렉트(3.카카오 로그인 페이지 제공) -> requestKaKaoToken(43줄) 메서드 실행(4.카카오 계정 로그인) -> 토큰 받아와서 -> kakaoToken에 저장됨(5.Authorization code 발급)
  - ![image](https://user-images.githubusercontent.com/102513932/211471813-0a1e8022-8d5b-4bde-8cc9-802eb5a4122f.png)
  - ![image](https://user-images.githubusercontent.com/102513932/211472214-9578d322-ea35-4277-b7ff-9fd71e77621b.png)
    - response 데이터 내용

# api 패키지
## 발급받은 카카오 Access 토큰 기반 로그인(회원가입)
- 위에서 발급받은 카카오 토큰을 기반으로 로그인 진행
  - 36번째 줄 socialLoginApiservice.getUserInfo
- api/login/contorller oauthLogin 메서드
  - **주의 MemberType이 String 값으로 requestBody에 포함되야함**
  - **주의 Authrization 헤더 앞에 Bearer이 포함되어야 함**
    - ex) `Bearer QFshOekL_lGgWR_vEyilaFSjUl2MLoxy5peeflcJCisNHgAAAYWaPSak`
- `validateAuthorization`, `validateMemberType` 을 통해 토큰, MemberType(kakao) 유효성 검증
- `oauthLogin`를 통해 레포에 회원 정보 저장 (OauthLoginService에서 로직 진행)
  - ![image](https://user-images.githubusercontent.com/102513932/211491008-e5d20221-ca3d-4806-a0fd-8e44229075d6.png)
  - ![image](https://user-images.githubusercontent.com/102513932/211491055-5b606828-4df0-46cb-ae78-daf06f7a82aa.png)

## 로그아웃
- api/logout/controller logout 메서드
- `validateAuthorization` 통해 토큰 유효성 검증
- ![image](https://user-images.githubusercontent.com/102513932/211491779-0792dbeb-c63b-4264-8efb-ebc6b970699a.png)
  - 로그아웃 시 현재 시간으로 refresh 토큰 만료시간 설정
  - LogoutService에서 로직 진행

## 회원정보조회
- api/member/controller getMemberInfo 메서드
  - 로그인한 토큰값을 통해서 현재 로그인한 회원의 정보를 불러옴 (프로젝트에서 굳이 필요한가? 사용된다면 마이페이지 등에서 이용)
- global/resolver/memberinfo 패키지에서 MemberInfo 어노테이션 제작
  - MemberInfoArgumentResolver(memberInfo 구현부)에서 토큰에 있는 정보를 MemberInfoDto로 변환 후 파라미터 값으로 넣어줌
  - MemberInfoDto에서 MemberId값 받아서 service단에서 회원 정보 세팅해주고, 반환해줌
- ![image](https://user-images.githubusercontent.com/102513932/211494424-548b9db8-fd03-4787-bffc-68ee741edc3a.png)

## Access Token 재발급
- ![image](https://user-images.githubusercontent.com/102513932/211496523-fed91425-8d4a-4058-ad9c-9b201b2d0d0c.png)
- api/token/controller createAccessToken 메서드
  - 로그인 시 발급받은 refreshToken을 통해 재발급
- ![image](https://user-images.githubusercontent.com/102513932/211496661-c123bd68-2c15-4a2a-a74a-0e552c91da54.png)

# domain 패키지
## Auditable(BaseEntity, BaseTimeEntity)
- domain/common/ 패키지에 BaseEntity, BaseTimeEntity 구현
  - BaseEntity는 엔티티가 생성 및 수정된 엔드포인트의 값 저장 ex) /api/oauth/login
  - BaseTimeEntity는 엔티티 생성 및 수정 시간 저장
- global/config/jpa JpaConfig 파일의 @EnableJpaAuditing 어노테이션 을 통해 auditing 사용 가능 및 구현체 설정 및 빈 등록
- global/config/jpa AuditorAwareImpl 클래스를 통해 BaseEntity에서 uri정보 추출 가능

## Member 패키지
- constant 패키지에는 어느 소셜 로그인을 통해 로그인 하는지에 대한 MemberType값이 enum 타입으로 들어가 있음
  - ![image](https://user-images.githubusercontent.com/102513932/211540799-9cea419e-0cba-4522-8cbe-464dcd55669b.png)
  - 사진과 같은 Naver 인증 추가에 대한 다형성 대비
- Role 패키지에는 유저 권한 설정에 대한 값이 enum으로 들어가 있음
  - 추후 인가 설정에서 사용 가능 예정(**상담사 추가 요망**)
- entity 패키지에는 유저 엔티티에 대한 필드가 들어가있음
- repository 패키지 설명 생략
- service 패키지 설명 생략

# external/oauth 패키지
## kakao 패키지
- ![image](https://user-images.githubusercontent.com/102513932/211540799-9cea419e-0cba-4522-8cbe-464dcd55669b.png)
  - 회원 로그인은 위 사진의 흐름대로 이뤄짐
  - 로그인 진행 시 api/login/service의 OauthLoginService에서  getSocialLoginApiServiceMemberType(33번째 줄)을 이용하여 oauth/service  패키지의 SocialLoginApiService 구현체를 가져옴
    - 현재는 KakaoLoginApiServiceImpl
  - getUserInfo(36번째 줄)을 통해 회원 정보를 가져옴
    - 현재 KakaoLoginApiServiceImpl에서 구현되어 있음
- Client 패키지의 KakaoUserinfoClient는 KaKoLoginApiServiceImpl 에서 카카오 개발자 센터를 통해 유저 정보를 가져오기 위해 FeignClient로 구현되어 있음
- dto 패키지의 KakaoUserInfoResponseDto는 카카오 개발자 센터 기준으로 작성됨
- service 패키지의 KakaoLoginApiService는 회원 정보를 가져와 통일한 형식(OAuthAttributes)로 변환해줌

## Model 패키지
- OAuthAttributes는 각 개발자 센터에서 가져오는 회원 정보의 반환 형태가 다 다르니 통일된 규격을 통해 홈페이지 회원가입을 용이하게 함

## Service 패키지
- socialLoginApiService는 다형성을 대비한 interface, KakaoLoginApiService의 부모 클래스임
- socialLoginApiServiceFactory는 socialLoginApiService의 구현체들을 정리하고 멤버 타입에 맞춰 구현체를 반환해주는 클래스임

# global 패키지
## config/jpa 패키지
- 앞서 auditable에서 설명했듯이, AuditorAwareImpl은 BaseEntity에서 생성자와 수정자에 API request uri를 설정하기 위한 클래스
- JpaConfig는 AuditorAwareImpl을 사용하기위한 설정 파일

## config/web 패키지
- WebConfig
- addCorsMappings 메서드
  - cors 적용 메서드
  - ![image](https://user-images.githubusercontent.com/102513932/211573977-89aac8d1-c077-42aa-b325-d8e659f52ac9.png)
- addInterceptors
  - 토큰 검증과 같은 인증과, Member_Role에 해당하는 인가 인터셉터 적용 메서드
  - 인증은 예외 uri 작성
  - 인가는 포함 uri 작성(**상담사 부분 추가 요망**)
- addArgumentResolvers
  - 갖고있는 유저의 정보를 컨트롤러 클래스 메소드의 파라미터로 받을 수 있도록 설정(MemberInfoArgumentResolver을 위한 설정)

## config/나머지 클래스
### FeignConfiguration
- feign 설정 파일을 만들어 로깅레벨과 구현한 errorDecoder, 재시도를 위한 Retryer를 빈으로 등록
- 카카오 토큰 발급 시(로그인), 카카오 유저 정보 받아올 시 사용하는 Feign을 위한 설정 클래스

### JasyptConfig
- yml 암호화를 위한 설정 파일
- 팀원들끼리 **Vm Options에서 같은 비밀번호를 공유해야함**
- yml 파일에 들어가 있는 카카오 client id/password , db 패스워드, token secret key 등에 사용
- `-Djasypt.password=sakncksjallkasdkl#$@^#*asdsiajodias2737`
- test/app/JasyptTest 
  - yml 암호화 진행 구현부 -> test를 통해서 yml 암호화 진행(복사 -> 붙여넣기)

### SecurityConfig
- 토큰 생성 및 사용에 필요한 application.yml에서 설정한 값들을 빈으로 등록함
- tokenSecret, accessTokenExpirationTime, refreshTokenExpirationTime 

### SwaggerConfig
- Swagger 사용 시 설정 정보 등록 클래스
- ![image](https://user-images.githubusercontent.com/102513932/211581387-0541e62d-f6d4-4a9b-9aff-88bbb659f131.png)

## error/exception 패키지
- `AuthenticationException`, `EntityNotFoundException`는 BusinessException 상속
  - 인증처리 관련 에러와 엔티티 발생 에러 클래스 분류
- `BusinessException`는 `RuntimeException` 상속
  - 비즈니스 로직을 수행하다 조건이 맞지 않을 경우 BusinessException 발생
  - 사용할 CustomException 정의

## error/ 나머지 클래스
### ErrorCode
-  반환할 http status 값, 에러 코드, 에러메세지를 관리하는 Enum 클래스
   -  위에서 설정한 클래스 별 에러코드 분류 요망
### ErrorResponse
- 클라이언트 쪽으로 반환할 ErrorResponse
  - 에러코드와 에러메시지 반환

### FeignClientExceptionErrorDecoder
- @FeignClient 사용 시 발생하는 예외 처리
  - 문서 설명 주석 참고

### GlobalExceptionHandler
- @RestController에서 발생하는 전역적으로 발생하는 예외를 처리
  - 문서 설명 주석 참고

## interceptor 패키지
### AdminAuthorizationInterceptor
- 회원의 role을 검증하는 인가 인터셉터
  - 컨트롤러 로직을 수행하기 전에 먼저 수행되는 preHandle 메서드 구현(관련 설정 WebConfig 참고)

### AuthenticationInterceptor
- 회원의 토큰을 검증하는 인증 인터셉터
  - 컨트롤러 로직을 수행하기 전에 먼저 수행되는 preHandle 메서드 구현(관련 설정 WebConfig 참고)

## jwt 패키지
### constant 패키지
- GrantType 
  - 토큰의 정보 설정을 위한 enum
- TokenType
  - 토큰의 역할 분류를 위한 enum

### dto 패키지
- JwtTokenDto
  - 토큰 정보 저장을 위한 클래스

### service 패키지
- TokenManager
  - 토큰 생성 및 유효성 확인, 만료시간 리턴에 대한 메서드 포함 클래스

## resolver 패키지
### memberinfo 패키지
- MemberInfo
  - 어노테이션 생성
- MemberInfoArgumentResolver
  - @MemberInfo에 대한 구현부
  - 토큰에 있던 정보를 MemberInfoDto로 만들어 전달
- MemberInfoDto
  - 반환 데이터 dto 생성

## Util 패키지
- AuthorizationHeaderUtils
  - authorizationHeader에 값이 들어있는지, Bearer 타입이 맞는지 확인
- DateTimeUtils
  - Date -> LocalDateTime으로 변환하는 메서드
  - Member 엔티티에 있는 tokenExpirationTime과 jwtTokenDto에 있는 refreshTokenExpireTime 타입을 맞춰주기 위한 클래스

## appication.yml
```yml
#default 프로젝트 설정 값
# 실행하는 profile에 따라 yml에 default로 설정해둔 값이 오버라이드 됨
server:
  port: 8080
  servlet:
    context-path: /


spring:
  # todo 개발 데이터베이스 연결
  datasource:
    url: jdbc:h2:tcp://localhost/~/test
    username: sa
    password:
    driver-class-name: org.h2.Driver


  jpa:
    hibernate:
      ddl-auto: create # 애플리케이션 실행 시 기존 테이블 삭제 후 테이블 새로 생성
      # 추후 validate나 none으로 변경할 것
    show-sql: true # 콘솔창에 sql 출력
    properties:
      hibernate:
        format_sql: true # sql 예쁘게 출력
        default_batch_fetch_size: 500 # 일대다 컬렉션 조회 시 성능 최적화
        # 쿼리에서 조건문 in절로 수행하게됨, n번 쿼리 수행 -> 1번 쿼리에서 수행(n개를 파라미터로 넣어줌)
        # DB와 애플리케이션 부하를 고려해서 개수를 설정할 것
        # oracle의 경우 최대 1000개
    open-in-view: false # 영속성 컨텍스트의 생존 범위를 트랜잭션 범위로 한정
    # 기본값 true -> View 랜더링 또는 API 요청일 경우 클라이언트 응답까지 영속성 컨텍스트 생존
    # false 설정 시 -> 생존 범위가 트랜잭션 범위로 줄어들게 됨
      # -> 컨트롤러에서 지연로딩으로 연관된 엔티티를 가져오려고 하면 에러 발생
    # 영속성 컨텍스트를 오래 유지하면 DB Connection도 오래 갖고 있으므로 DB Connection이 부족할 수 있음
      # -> 실시간 트래픽이 중요한 API 애플리케이션에서는 false로 유지할 것
  servlet:
    multipart:
      max-file-size: 10MB # 파일 업로드 요청 시 하나의 파일 크기를 10MB 제한
      max-request-size: 100MB # 파일 업로드 요청 시 모든 파일 크기합을 100MB 제한

  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
#     swagger 사용을 위한 설정

logging:
  level:
    org.hibernate.type: debug # 콘솔창에 조건에 바인딩되는 값 및 조회 결과 출력
    com.app: debug # todo 패키지 수정
#    spring cloud openfeign(외부 API 호출을 쉽게 할 수 있도록 도와줌)
#    의 로그를 보기 위해서 프로젝트 루트 패키지 기준으로 debug 설정

feign:
  client:
    config:
      default:
        connectTimeout: 5000
        readTimeout: 5000
# 3-way-handshake 과정의 시간 설정(5초)
# 연결은 됐는데 요청한 서버에서 응답이 5초내로 오지 않으면 에러 발생(readTimeout)
kakao:
  client:
    id: ENC(2VWXhZZPewn1zK6e3ODVGcHbYJLRUmCw6zd6oc4ITu+V7LETI9sxmLLHsYbcP6s1)
#   RESTAPI 키를 복사
    secret: ENC(uWuzB+AjzEhpdi6RMDgNfJW/KPSkTfTPRsIECN3H09rUM3q2doWVr1WBDY/jUGtz)
#   보안 - 코드 값 복사

token:
  secret:  ENC(eIFwp/mynS6nbxDmqjNMzy+qVPlKB7H7LEk04Q85ggmsS34hVRDWl8BKiyg17HiF+KdNFuzF7S4=)
  access-token-expiration-time: 900000  # 15분 1000(ms) x 60(s) x 15(m)
  refresh-token-expiration-time: 1209600000 # 2주 1000(ms) x 60 (s) x 60(m) x 24(h) x 14(d)

jasypt:
  password: ${PASSWORD}
#  vmoption에 지정됨
```






# PUSH 알림 및 CSRF 필터 상세 설명서

## 목차
1. [mobile_main 접속 시 PUSH 알림 동작 방식](#1-mobile_main-접속-시-push-알림-동작-방식)
2. [CSRF 필터 동작 방식](#2-csrf-필터-동작-방식)

---

## 1. mobile_main 접속 시 PUSH 알림 동작 방식

### 1.1 전체 플로우 다이어그램

```
[사용자가 mobile_main 페이지 접속]
    ↓
[페이지 로드 시작]
    ├─ Firebase SDK 로드 (firebase-app-compat.js, firebase-messaging-compat.js)
    ├─ push_init.js 스크립트 로드
    └─ mobile_main.jsp HTML 렌더링
    ↓
[$(document).ready() 실행]
    ├─ push_init.js 내부 코드 실행
    └─ window.requestPermissionAndGetToken() 자동 호출
    ↓
[PUSH 알림 초기화 프로세스]
    ├─ Service Worker 지원 확인
    ├─ Push API 지원 확인
    ├─ 알림 권한 요청 (Notification.requestPermission())
    ├─ Service Worker 등록 (/firebase-messaging-sw.js)
    ├─ FCM 토큰 발급 (VAPID 키 사용)
    └─ 서버에 토큰 전송 (/api/push/registerToken)
    ↓
[페이지 완전 로드 완료]
    └─ 사용자가 "PUSH 테스트" 버튼 클릭 대기
    ↓
[사용자가 "PUSH 테스트" 버튼 클릭]
    ↓
[클라이언트: AJAX 요청 생성]
    ├─ CSRF 토큰 자동 추가 (X-CSRF-TOKEN 헤더)
    └─ POST /api/push/test 요청 전송
    ↓
[서버: CSRF 필터 검증]
    ├─ 세션 확인
    ├─ HTTP 메서드 확인 (POST)
    └─ CSRF 토큰 검증
    ↓
[서버: API 컨트롤러 처리]
    └─ PushRestController.triggerApprovalNotification()
    ↓
[서버: PushService 처리]
    ├─ DB에서 FCM 토큰 조회 (targetId 기준)
    ├─ FCM 메시지 구성
    └─ Firebase Admin SDK로 메시지 전송
    ↓
[FCM 서버: 메시지 라우팅]
    └─ 클라이언트 디바이스로 메시지 전달
    ↓
[클라이언트: 메시지 수신]
    ├─ 포그라운드 (앱 열려있을 때): onMessage 이벤트 → 토스트 표시
    └─ 백그라운드 (앱 닫혀있을 때): Service Worker → 브라우저 알림 표시
```

---

### 1.2 Phase 1: 페이지 접속 및 초기화

#### 1.2.1 페이지 로드 시작

**파일 위치**: `src/main/webapp/WEB-INF/views/mobile/mobile_main.jsp` (7-11줄)

**코드**:
```jsp
<!-- Firebase SDK 우선 -->
<script src="https://www.gstatic.com/firebasejs/10.13.1/firebase-app-compat.js"></script>
<script src="https://www.gstatic.com/firebasejs/10.13.1/firebase-messaging-compat.js"></script>

<script type="text/javascript" src="/push_init.js"></script>
```

**동작 내용**:
1. **Firebase SDK 로드**:
   - `firebase-app-compat.js`: Firebase 핵심 라이브러리
   - `firebase-messaging-compat.js`: Firebase Cloud Messaging (FCM) 라이브러리
   - Compat 버전 사용 (v9+ 모듈식 API를 v8 스타일로 사용)

2. **push_init.js 로드**:
   - 푸시 알림 초기화 스크립트
   - Firebase 설정 및 토큰 발급 로직 포함

#### 1.2.2 push_init.js 실행

**파일 위치**: `src/main/webapp/static-global/push_init.js`

**1단계: Firebase 초기화** (6-24줄)

```javascript
const firebaseConfig = {
    apiKey: "AIzaSyAjZkzvIfunxSwZocYTgkpLNNvIHMec-cg",
    authDomain: "mobilepwa-b4c16.firebaseapp.com",
    projectId: "mobilepwa-b4c16",
    storageBucket: "mobilepwa-b4c16.firebasestorage.app",
    messagingSenderId: "985969643759",
    appId: "1:985969643759:web:acd44c09122176edd34b1a",
    measurementId: "G-C4KJ5YX5F8"
};

// Firebase SDK 확인
if (typeof firebase === 'undefined') {
    console.error('Firebase SDK not loaded!');
    return;
}

// Firebase 초기화
firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();
```

**동작 내용**:
- Firebase 프로젝트 설정 정보로 Firebase 앱 초기화
- `firebase.messaging()` 객체 생성 (FCM 메시징 기능 사용)

**2단계: 포그라운드 메시지 리스너 등록** (102-109줄)

```javascript
messaging.onMessage((payload) => {
    console.log('[FCM] Foreground message:', payload);
    
    const title = payload.data?.title || '새 알림';
    const body = payload.data?.body || '내용 없음';
    
    showToast(title, body);
});
```

**동작 내용**:
- 앱이 열려있을 때(포그라운드) 메시지 수신 시 실행
- `payload.data`에서 제목과 본문 추출
- `showToast()` 함수로 토스트 메시지 표시

**3단계: 페이지 로드 시 자동 실행** (131-135줄)

```javascript
$(document).ready(function() {
    if (typeof window.requestPermissionAndGetToken === 'function') {
        window.requestPermissionAndGetToken();
    }
});
```

**동작 내용**:
- DOM이 완전히 로드되면 자동으로 `requestPermissionAndGetToken()` 호출
- 푸시 알림 권한 요청 및 토큰 발급 프로세스 시작

#### 1.2.3 requestPermissionAndGetToken() 실행

**파일 위치**: `src/main/webapp/static-global/push_init.js` (30-72줄)

**전체 프로세스**:

```javascript
window.requestPermissionAndGetToken = function() {
    // 1. Service Worker 지원 확인
    if (!('serviceWorker' in navigator)) {
        console.warn('Service Worker not supported');
        return;
    }

    // 2. Push API 지원 확인
    if (!('PushManager' in window)) {
        console.warn('Push API not supported');
        return;
    }

    // 3. 알림 권한 요청
    Notification.requestPermission()
        .then(permission => {
            if (permission !== 'granted') {
                console.warn('Notification permission denied');
                return;
            }
            console.log('Notification permission granted');

            // 4. Service Worker 등록
            return navigator.serviceWorker.register('/firebase-messaging-sw.js');
        })
        .then(registration => {
            if (!registration) return;
            console.log('Service Worker registered:', registration.scope);

            // 5. FCM 토큰 발급
            return messaging.getToken({
                serviceWorkerRegistration: registration,
                vapidKey: 'BEcW_Ep6uWwbA64qsiU64TLQ_wj6AXgJbcjszjLdVOIG1gZ8rVM2XCSHNG8A8M4l5v6CZVfLdu_EBqKxjMlyj7c'
            });
        })
        .then(token => {
            if (token) {
                console.log('FCM Token:', token);
                // 6. 서버에 토큰 전송
                sendTokenToServer(token);
            } else {
                console.warn('No token received');
            }
        })
        .catch(err => {
            console.error('Token error:', err);
        });
};
```

**단계별 상세 설명**:

**1단계: Service Worker 지원 확인**
- 브라우저가 Service Worker를 지원하는지 확인
- 지원하지 않으면 프로세스 종료

**2단계: Push API 지원 확인**
- 브라우저가 Push API를 지원하는지 확인
- 지원하지 않으면 프로세스 종료

**3단계: 알림 권한 요청**
- `Notification.requestPermission()` 호출
- 사용자에게 알림 권한 요청 팝업 표시
- 결과:
  - `granted`: 허용 → 다음 단계 진행
  - `denied`: 거부 → 프로세스 종료
  - `default`: 아직 결정하지 않음 → 프로세스 종료

**4단계: Service Worker 등록**
- `navigator.serviceWorker.register('/firebase-messaging-sw.js')` 호출
- 백그라운드 메시지 수신을 위해 Service Worker 등록
- 등록 성공 시 `registration` 객체 반환

**5단계: FCM 토큰 발급**
- `messaging.getToken()` 호출
- VAPID 키를 사용하여 FCM 토큰 발급
- 이 토큰은 해당 브라우저/디바이스를 고유하게 식별
- 예시 토큰: `dK3xYz9...` (긴 문자열)

**6단계: 서버에 토큰 전송**

**파일 위치**: `src/main/webapp/static-global/push_init.js` (111-127줄)

```javascript
window.sendTokenToServer = function(fcmToken) {
    $.ajax({
        url: '/api/push/registerToken',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            memberId: MEMBER_ID,  // 'jhchoi@dbinc.co.kr'
            fcmToken: fcmToken
        }),
        success: function(response) {
            console.log('Token sent to server:', response);
        },
        error: function(xhr, status, error) {
            console.error('Send token failed:', error, xhr.responseText);
        }
    });
};
```

**동작 내용**:
- AJAX POST 요청으로 `/api/push/registerToken` 호출
- `memberId`와 `fcmToken`을 JSON으로 전송
- 서버에서 DB에 저장 (나중에 푸시 알림 전송 시 사용)

**서버 처리** (`PushRestController.registerToken()`):
```java
@PostMapping("/registerToken")
public @ResponseBody ResponseEntity<String> registerToken(@RequestBody PushTokenDto requestDto) {
    pushService.saveOrUpdateToken(requestDto.getMemberId(), requestDto.getFcmToken());
    return ResponseEntity.ok("Token registered successfully.");
}
```

---

### 1.3 Phase 2: PUSH 테스트 버튼 클릭

#### 1.3.1 버튼 클릭 이벤트

**파일 위치**: `src/main/webapp/WEB-INF/views/mobile/mobile_main.jsp` (133-157줄)

**코드**:
```javascript
$('#btnPushTest').on('click', function() {
    setTimeout(function() {
        let param = new Object();
        param["targetId"] = 'jhchoi@dbinc.co.kr';
        let params = JSON.stringify(param);
        try {
            ajax.json(
                "/api/push/test",
                params,
                function(res) {
                    console.log("" + res); 
                    hideLoading();
                },
                false  // 동기 방식
            );               
        } catch(e) {
            let param = new Object();
            param.err_yn = "Y";
            param.err_msg = e.toString();
            hideLoading();
        }
    }, 200);
});
```

**동작 내용**:
1. `#btnPushTest` 버튼 클릭 시 이벤트 발생
2. 200ms 지연 후 실행 (UI 반응성 향상)
3. `targetId`를 `'jhchoi@dbinc.co.kr'`로 하드코딩하여 설정
4. JSON 객체를 문자열로 변환
5. `ajax.json()` 함수를 통해 `/api/push/test` 엔드포인트로 POST 요청
6. 동기 방식(`false`)으로 실행하여 응답을 기다림
7. 성공 시 콜백에서 응답 로그 출력 및 로딩 숨김
8. 실패 시 에러 정보를 객체로 만들어 처리

#### 1.3.2 AJAX 요청 처리 (ajax.json)

**파일 위치**: `src/main/webapp/resources/js/common/ajaxCommon.js` (25-73줄)

**코드**:
```javascript
json : function(url, param, fnCallback, doAsync) {
    try {
        // 파라미터에 currentMenuId 추가
        if( $.type(param) == 'object' ){
            param["currentMenuId"] = _CURRENT_MENU_ID;
            param = JSON.stringify(param);
        } else {
            param = JSON.parse(param);
            param["currentMenuId"] = _CURRENT_MENU_ID;
            param = JSON.stringify(param);
        }

        if (doAsync) {
            setAsync = doAsync;
        } else {
            setAsync = false;
        }

        return $.ajax({
            url : url,
            data : param,
            type : 'post',
            dataType : 'json',
            async : setAsync,
            contentType : "application/json; charset=UTF-8",
            success : function(data) {
                if( data && data.exceptionMsg){
                    location.href = _CONTEXT_PATH + _CURRENT_MAIN_PAGE;
                } else {
                    try {
                        fnCallback(data);
                    } catch(e) {
                        location.href = _CONTEXT_PATH + _CURRENT_MAIN_PAGE;
                    }
                }
            },
            error : function(e) {
                e.responseJSON.successYN = 'N';
                fnCallback(e.responseJSON);
            } 
        });
    } catch (err) {}
}
```

**동작 내용**:
1. 파라미터에 `currentMenuId` 자동 추가
2. JSON 문자열로 변환
3. jQuery `$.ajax()` 호출
4. **CSRF 토큰 자동 추가** (아래 참조)

#### 1.3.3 CSRF 토큰 자동 추가

**파일 위치**: `src/main/webapp/WEB-INF/views/include/mobileHeader.jsp` (40-51줄)

**코드**:
```javascript
$(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        if (options.type && ['POST', 'PUT', 'DELETE', 'PATCH'].includes(options.type.toUpperCase())) {
            xhr.setRequestHeader(header, token);
        }
    });
});
```

**동작 내용**:
- 모든 AJAX 요청 전에 `ajaxSend` 이벤트 발생
- POST, PUT, DELETE, PATCH 메서드인 경우
- 메타 태그에서 CSRF 토큰 읽기
- `X-CSRF-TOKEN` 헤더에 자동 추가

**메타 태그 설정** (16-17줄):
```jsp
<meta name="_csrf" content="${sessionScope.sessionVO.csrfToken}">
<meta name="_csrf_header" content="X-CSRF-TOKEN">
```

#### 1.3.4 서버: CSRF 필터 검증

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/config/CsrfFilter.java`

**검증 프로세스**:
1. 세션 확인
2. HTTP 메서드 확인 (POST)
3. CSRF 토큰 추출 (`X-CSRF-TOKEN` 헤더 또는 `csrfToken` 파라미터)
4. 세션의 토큰과 비교
5. 일치하면 통과, 불일치하면 403 Forbidden

(자세한 내용은 2장 참조)

#### 1.3.5 서버: API 컨트롤러 처리

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/web/controller/push/PushRestController.java` (37-51줄)

**코드**:
```java
@PostMapping("/test")
public @ResponseBody ResponseEntity<String> triggerApprovalNotification(@RequestBody PushTokenDto pushToken) {
    
    // 실제 결재 로직 처리...
    
    // 결재 건수 발생 후 알림 전송
    pushService.triggerApprovalPushNotification(
        pushToken.getTargetId(),  // 'jhchoi@dbinc.co.kr'
        "새로운 회의실 예약!", 
        "4-B 회의실 예약시간이 30분 남았습니다. 회의실 예약을 확인해 주세요.", 
        "/reserve_main" // 클릭 시 이동할 URL
    );
    
    return ResponseEntity.ok("Approval processed and push notification sent.");
}
```

**동작 내용**:
- `@PostMapping("/test")`로 `/api/push/test` 엔드포인트 매핑
- `@RequestBody`로 JSON을 `PushTokenDto` 객체로 자동 변환
- `targetId`를 추출하여 `PushService.triggerApprovalPushNotification()` 호출
- 하드코딩된 제목, 본문, 링크로 알림 전송
- 성공 시 문자열 응답 반환

#### 1.3.6 서버: PushService 처리

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/web/service/push/PushService.java` (39-72줄)

**코드**:
```java
public void triggerApprovalPushNotification(String approvalTargetId, String title, String body, String link) {
    // 1. 알림 대상자의 FCM 토큰 조회
    List<String> tokens = pushTokenRepository.selectTokensForApprovalTarget(approvalTargetId);

    if (tokens.isEmpty()) {
        System.out.println("No token found for member: " + approvalTargetId);
        return;
    }

    // 2. FCM 메시지 구성 (Data Payload에 이동할 URL 포함)
    Message message = Message.builder()
            .putData("title", title)
            .putData("body", body)
            .putData("link", link)
            .setToken(tokens.get(0)) // 단일 토큰 예시
            .setWebpushConfig(WebpushConfig.builder()
                .setNotification(WebpushNotification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .setIcon("/resources/icons/icon-192x192.png")
                    .build())
                .putHeader("Urgency", "high")
                .build())
            .build();

    // 3. FCM 전송
    try {
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    } catch (FirebaseMessagingException e) {
        System.err.println("Sending FCM message failed: " + e.getMessage());
        // 토큰이 유효하지 않은 경우 DB에서 삭제하는 로직 추가 필요
    }
}
```

**동작 내용**:

**1단계: FCM 토큰 조회**
- `PushTokenRepository.selectTokensForApprovalTarget()` 호출
- 대상 사용자 ID(`targetId`)로 DB에서 FCM 토큰 조회
- 토큰이 없으면 로그 출력 후 종료

**2단계: FCM 메시지 구성**
- **Data Payload**: 
  - `title`: 알림 제목
  - `body`: 알림 본문
  - `link`: 클릭 시 이동할 URL
- **WebpushConfig**: 웹 푸시 알림 설정
  - `Notification`: 브라우저 알림 표시용 설정
    - `title`, `body`: 알림에 표시될 텍스트
    - `icon`: 알림 아이콘 경로
  - `Urgency`: "high"로 설정하여 우선순위 높임

**3단계: Firebase Admin SDK로 전송**
- `FirebaseMessaging.getInstance().send(message)` 호출
- FCM 서버로 메시지 전송
- 성공 시 응답 ID 반환
- 실패 시 예외 발생 (토큰 무효, 네트워크 오류 등)

#### 1.3.7 클라이언트: 메시지 수신

**A. 포그라운드 수신 (앱이 열려있을 때)**

**파일 위치**: `src/main/webapp/static-global/push_init.js` (102-109줄)

**코드**:
```javascript
messaging.onMessage((payload) => {
    console.log('[FCM] Foreground message:', payload);
    
    const title = payload.data?.title || '새 알림';
    const body = payload.data?.body || '내용 없음';
    
    showToast(title, body);
});
```

**동작 내용**:
- 앱이 열려있을 때 메시지 수신
- `payload.data`에서 알림 정보 추출
- `showToast()` 함수로 토스트 메시지 표시

**토스트 메시지 함수** (75-99줄):
```javascript
function showToast(title, message) {
    const container = document.getElementById('toast-container');
    if (!container) return;

    const toast = document.createElement('div');
    toast.style.cssText = `
        background: #333; color: white; padding: 16px; border-radius: 8px;
        margin-top: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.3);
        max-width: 300px; animation: slideIn 0.3s ease;
        pointer-events: auto; font-size: 14px;
    `;

    toast.innerHTML = `
        <strong style="display: block; margin-bottom: 4px;">${title}</strong>
        <span>${message}</span>
    `;

    container.appendChild(toast);
    
    // 3초 후 사라짐
    setTimeout(() => {
        toast.style.animation = 'slideOut 0.3s ease';
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}
```

**B. 백그라운드 수신 (앱이 닫혀있을 때)**

**파일 위치**: `src/main/webapp/static-global/firebase-messaging-sw.js` (22-39줄)

**코드**:
```javascript
messaging.onBackgroundMessage((payload) => {
    console.log('[firebase-messaging-sw.js] Received background message:', payload);

    // payload.data 기반 알림 (FCM 권장 방식)
    const notificationTitle = payload.data?.title || '알림';
    const notificationOptions = {
        body: payload.data?.body || '새로운 알림이 도착했습니다.',
        icon: '/images/icon-192x192.png',
        badge: '/images/badge-72x72.png',
        tag: payload.data?.tag || 'default-tag',
        data: {
            link: payload.data?.link || '/'
        }
    };

    // 알림 표시
    self.registration.showNotification(notificationTitle, notificationOptions);
});
```

**동작 내용**:
- Service Worker 내에서 실행 (브라우저 백그라운드)
- 앱이 닫혀있거나 다른 탭에 있을 때 메시지 수신
- `payload.data`에서 알림 정보 추출
- `self.registration.showNotification()`으로 브라우저 네이티브 알림 표시
- 알림 옵션:
  - `title`: 알림 제목
  - `body`: 알림 본문
  - `icon`: 알림 아이콘
  - `badge`: 배지 아이콘
  - `tag`: 알림 그룹핑용 태그
  - `data.link`: 클릭 시 이동할 URL

**C. 알림 클릭 이벤트**

**파일 위치**: `src/main/webapp/static-global/firebase-messaging-sw.js` (56-79줄)

**코드**:
```javascript
self.addEventListener('notificationclick', (event) => {
    console.log('[Service Worker] Notification click:', event);

    event.notification.close();

    const link = event.notification.data?.link || '/';

    event.waitUntil(
        clients.matchAll({ type: 'window', includeUncontrolled: true })
            .then((clientList) => {
                // 이미 열린 탭 중 동일한 URL 있으면 포커스
                for (const client of clientList) {
                    if (client.url.includes(link) && 'focus' in client) {
                        return client.focus();
                    }
                }
                // 없으면 새 탭 열기
                if (clients.openWindow) {
                    return clients.openWindow(link);
                }
            })
            .catch(err => console.error('Notification click error:', err))
    );
});
```

**동작 내용**:
- 사용자가 알림을 클릭하면 실행
- 알림 닫기
- `data.link`에서 URL 추출
- 이미 열린 탭 중 동일한 URL이 있으면 포커스
- 없으면 새 탭으로 URL 열기

---

## 2. CSRF 필터 동작 방식

### 2.1 CSRF 공격이란?

**CSRF (Cross-Site Request Forgery)**: 사용자가 의도하지 않은 요청을 강제로 실행시키는 공격

**공격 시나리오**:
1. 사용자가 `example.com`에 로그인 (세션 쿠키 저장)
2. 사용자가 `evil.com` 방문
3. `evil.com`이 `example.com`에 POST 요청 전송 (세션 쿠키 자동 포함)
4. 사용자 모르게 `example.com`에서 악의적인 작업 실행

### 2.2 CSRF 방어 메커니즘

#### 2.2.1 토큰 기반 방어

**원리**:
- 사용자 세션마다 고유한 CSRF 토큰 생성
- 모든 상태 변경 요청(POST, PUT, DELETE 등)에 토큰 포함 필수
- 서버에서 토큰 검증 후 요청 처리

**왜 효과적인가?**:
- 악성 사이트는 사용자의 CSRF 토큰을 알 수 없음
- Same-Origin Policy로 인해 다른 도메인에서 토큰 읽기 불가
- 토큰이 없거나 잘못된 토큰이면 요청 거부

### 2.3 필터 등록 및 적용 범위

#### 2.3.1 필터 등록

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/config/CsrfFilter.java`

**등록 방식**:
```java
@Slf4j
@Component
public class CsrfFilter implements Filter {
    // ...
}
```

- `@Component` 어노테이션으로 Spring Bean으로 등록
- Spring Boot가 자동으로 필터 체인에 등록
- 모든 요청에 대해 실행

#### 2.3.2 적용 범위

**모든 HTTP 요청에 적용**:
- 정적 리소스 요청 포함
- API 요청 포함
- 페이지 요청 포함

**예외 처리**:
- 세션이 없는 경우 검증 생략
- 특정 HTTP 메서드는 검증 생략 (아래 참조)

### 2.4 필터 동작 흐름 상세

#### 2.4.1 전체 흐름도

```
[HTTP 요청 도착]
    ↓
[CsrfFilter.doFilter() 실행]
    ↓
[세션 확인]
    ├─ 세션 없음 → 통과 (검증 생략)
    └─ 세션 있음 → 다음 단계
        ↓
[HTTP 메서드 확인]
    ├─ OPTIONS → 통과 (CORS Preflight)
    ├─ GET, HEAD → 통과 (안전한 메서드)
    └─ POST, PUT, DELETE, PATCH → 토큰 검증
        ↓
[CSRF 토큰 추출]
    ├─ X-CSRF-TOKEN 헤더에서 추출
    └─ csrfToken 파라미터에서 추출
        ↓
[토큰 검증]
    ├─ 일치 → 통과
    └─ 불일치 → 403 Forbidden
```

#### 2.4.2 단계별 상세 설명

**1단계: 요청 수신 및 세션 확인**

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/config/CsrfFilter.java` (30-39줄)

```java
HttpServletRequest request = (HttpServletRequest) req;
HttpServletResponse response = (HttpServletResponse) res;
HttpSession session = request.getSession(true);

SessionVO vo = (SessionVO) session.getAttribute("sessionVO");
if (vo == null || vo.getUserId() == null || "".equals(vo.getUserId())) {
    // 세션이 없는경우 Check 제외  
    chain.doFilter(request, response);
    return;
}
```

**동작**:
- `request.getSession(true)`: 세션이 없으면 생성
- `sessionVO`가 없거나 `userId`가 없으면 검증 생략
- **이유**: 로그인하지 않은 사용자는 CSRF 공격 대상이 아님

**2단계: HTTP 메서드 확인**

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/config/CsrfFilter.java` (42-55줄)

```java
String method = request.getMethod().toUpperCase();

// OPTIONS 요청은 CSRF 검증 생략 (Preflight)
if ("OPTIONS".equals(method)) {
    response.setStatus(HttpServletResponse.SC_OK);
    chain.doFilter(request, response);
    return;
}

// GET, HEAD → 통과
if ("GET".equals(method) || "HEAD".equals(method)) {
    chain.doFilter(request, response);
    return;
}
```

**OPTIONS 요청**:
- CORS Preflight 요청
- 브라우저가 실제 요청 전에 보내는 사전 요청
- 상태 변경을 일으키지 않으므로 검증 생략

**GET, HEAD 요청**:
- **안전한 메서드 (Safe Methods)**: 서버 상태를 변경하지 않음
- RFC 7231에 따라 GET, HEAD는 멱등성과 안전성 보장
- CSRF 공격의 목적은 상태 변경이므로 안전한 메서드는 검증 생략

**3단계: CSRF 토큰 추출**

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/config/CsrfFilter.java` (57-59줄)

```java
String headerToken = request.getHeader(CSRF_HEADER);  // "X-CSRF-TOKEN"
String paramToken = request.getParameter(CSRF_PARAM); // "csrfToken"
```

**헤더에서 추출**:
- AJAX 요청에서 주로 사용
- `X-CSRF-TOKEN` 헤더에 토큰 포함

**파라미터에서 추출**:
- 폼 제출에서 주로 사용
- `csrfToken` 파라미터에 토큰 포함

**4단계: 토큰 검증**

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/config/CsrfFilter.java` (61-67줄)

```java
boolean isValid = vo.getCsrfToken().equals(headerToken) || 
                  vo.getCsrfToken().equals(paramToken);
if (isValid) {
    chain.doFilter(request, response);
} else {
    log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@ ERROR @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}---{}---{}",
              request.getRequestURI(), headerToken, paramToken);
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF Token");
}
```

**검증 로직**:
- 세션의 `csrfToken`과 요청의 토큰 비교
- 헤더 또는 파라미터 중 하나라도 일치하면 통과
- 둘 다 일치하지 않으면 403 Forbidden 반환

**에러 처리**:
- 에러 로그에 URI, 헤더 토큰, 파라미터 토큰 기록
- 클라이언트에는 "Invalid CSRF Token" 메시지 반환

### 2.5 CSRF 토큰 생성

#### 2.5.1 생성 시점

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/web/controller/login/LoginViewController.java` (141줄)

**코드**:
```java
SessionVO sessionVO = (SessionVO) SessionUtil.getAttribute("sessionVO");
sessionVO.setCsrfToken(UUID.randomUUID().toString());
```

**동작**:
- 로그인 성공 시 실행
- `UUID.randomUUID().toString()`으로 고유한 토큰 생성
- 세션의 `SessionVO` 객체에 저장

**예시 토큰**: `550e8400-e29b-41d4-a716-446655440000`

#### 2.5.2 토큰 저장

**저장 위치**:
- 서버: `HttpSession`의 `SessionVO.csrfToken`
- 클라이언트: 메타 태그 또는 폼 hidden input

### 2.6 클라이언트에서 토큰 전송

#### 2.6.1 AJAX 요청 (자동)

**파일 위치**: `src/main/webapp/WEB-INF/views/include/mobileHeader.jsp` (40-51줄)

**메타 태그 설정** (16-17줄):
```jsp
<meta name="_csrf" content="${sessionScope.sessionVO.csrfToken}">
<meta name="_csrf_header" content="X-CSRF-TOKEN">
```

**자동 헤더 추가 스크립트**:
```javascript
$(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        if (options.type && ['POST', 'PUT', 'DELETE', 'PATCH'].includes(options.type.toUpperCase())) {
            xhr.setRequestHeader(header, token);
        }
    });
});
```

**동작**:
- 모든 AJAX 요청 전에 `ajaxSend` 이벤트 발생
- POST, PUT, DELETE, PATCH 메서드인 경우
- 메타 태그에서 토큰 읽기
- `X-CSRF-TOKEN` 헤더에 자동 추가

#### 2.6.2 폼 제출 (수동)

**파일 위치**: `src/main/webapp/WEB-INF/views/mobile/mobile_main.jsp` (985-987줄)

**코드**:
```jsp
<form id="frm" name="frm">
    <!-- CSRF 토큰 추가 -->
    <input type="hidden" id="csrfToken" name="csrfToken" value="${sessionScope.sessionVO.csrfToken}" />
</form>
```

**동작**:
- 폼에 hidden input으로 토큰 포함
- 폼 제출 시 `csrfToken` 파라미터로 전송
- 서버에서 파라미터로 토큰 검증

### 2.7 CSRF 필터 동작 예시

#### 2.7.1 정상 요청 (AJAX)

**요청**:
```
POST /api/push/test HTTP/1.1
Host: example.com
Cookie: JSESSIONID=ABC123
X-CSRF-TOKEN: 550e8400-e29b-41d4-a716-446655440000
Content-Type: application/json

{"targetId": "user@example.com"}
```

**필터 처리**:
1. 세션 확인: ✅ 세션 존재
2. HTTP 메서드: POST → 토큰 검증 필요
3. 토큰 추출: `X-CSRF-TOKEN: 550e8400-e29b-41d4-a716-446655440000`
4. 토큰 검증: 세션 토큰과 일치 ✅
5. 결과: 통과 → 컨트롤러로 전달

#### 2.7.2 공격 요청 (CSRF)

**요청** (악성 사이트에서):
```
POST /api/push/test HTTP/1.1
Host: example.com
Cookie: JSESSIONID=ABC123  (자동 포함)
Content-Type: application/json

{"targetId": "attacker@example.com"}
```

**필터 처리**:
1. 세션 확인: ✅ 세션 존재
2. HTTP 메서드: POST → 토큰 검증 필요
3. 토큰 추출: `X-CSRF-TOKEN` 헤더 없음, `csrfToken` 파라미터 없음
4. 토큰 검증: 토큰 없음 ❌
5. 결과: 403 Forbidden 반환

#### 2.7.3 GET 요청 (검증 생략)

**요청**:
```
GET /api/mobile/getFloorList?towerCd=001 HTTP/1.1
Host: example.com
Cookie: JSESSIONID=ABC123
```

**필터 처리**:
1. 세션 확인: ✅ 세션 존재
2. HTTP 메서드: GET → 검증 생략
3. 결과: 통과 → 컨트롤러로 전달

---

## 요약

### PUSH 알림 동작 요약

1. **페이지 접속 시**:
   - Firebase SDK 로드
   - push_init.js 실행
   - 알림 권한 요청
   - Service Worker 등록
   - FCM 토큰 발급 및 서버 전송

2. **PUSH 테스트 버튼 클릭 시**:
   - AJAX 요청 생성 (CSRF 토큰 자동 포함)
   - 서버에서 CSRF 검증
   - API 컨트롤러 처리
   - PushService에서 FCM 토큰 조회
   - Firebase Admin SDK로 메시지 전송
   - 클라이언트에서 메시지 수신 (포그라운드/백그라운드)

### CSRF 필터 동작 요약

1. **모든 요청에 적용**
2. **검증 생략 조건**:
   - 세션이 없는 경우
   - OPTIONS 메서드 (CORS Preflight)
   - GET, HEAD 메서드 (안전한 메서드)
3. **검증 대상**:
   - POST, PUT, DELETE, PATCH 메서드
4. **검증 방법**:
   - `X-CSRF-TOKEN` 헤더 또는 `csrfToken` 파라미터에서 토큰 추출
   - 세션의 토큰과 비교
   - 일치하면 통과, 불일치하면 403 Forbidden

