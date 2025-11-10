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
  - [1. Main 페이지 진입부터 PUSH 테스트 버튼 클릭까지 전체 동작 과정](#1-main-페이지-진입부터-push-테스트-버튼-클릭까지-전체-동작-과정)
    - [1.0 전체 플로우 개요](#10-전체-플로우-개요)
    - [1.1 Phase 1: Main 페이지 진입 및 초기화 (페이지 로드 시)](#11-phase-1-main-페이지-진입-및-초기화-페이지-로드-시)
      - [1.1.1 사용자 액션](#111-사용자-액션)
      - [1.1.2 서버 측 처리 (Spring MVC)](#112-서버-측-처리-spring-mvc)
      - [1.1.3 클라이언트 측 HTML 수신 및 파싱](#113-클라이언트-측-html-수신-및-파싱)
      - [1.1.4 push\_init.js 즉시 실행 단계](#114-push_initjs-즉시-실행-단계)
      - [1.1.5 DOM Ready 이벤트 발생](#115-dom-ready-이벤트-발생)
      - [1.1.6 FCM 토큰 생성 과정 (비동기)](#116-fcm-토큰-생성-과정-비동기)
      - [1.1.7 페이지 렌더링 완료](#117-페이지-렌더링-완료)
    - [1.2 Phase 2: PUSH 테스트 버튼 클릭 및 푸시 알림 전송](#12-phase-2-push-테스트-버튼-클릭-및-푸시-알림-전송)
      - [1.2.1 사용자 액션](#121-사용자-액션)
      - [1.2.2 클릭 이벤트 발생](#122-클릭-이벤트-발생)
      - [1.2.3 200ms 후 실행 (setTimeout 콜백)](#123-200ms-후-실행-settimeout-콜백)
      - [1.2.4 서버 측 처리](#124-서버-측-처리)
      - [1.2.5 Firebase Cloud Messaging 서버 처리](#125-firebase-cloud-messaging-서버-처리)
      - [1.2.6 클라이언트 메시지 수신](#126-클라이언트-메시지-수신)
      - [1.2.7 AJAX 응답 처리](#127-ajax-응답-처리)
    - [1.3 전체 타임라인 요약](#13-전체-타임라인-요약)
    - [1.1 전체 플로우 다이어그램 (간략 버전)](#11-전체-플로우-다이어그램-간략-버전)
    - [1.2 단계별 상세 설명](#12-단계별-상세-설명)
      - [1.2.1 단계 1: 버튼 클릭 이벤트 (프론트엔드)](#121-단계-1-버튼-클릭-이벤트-프론트엔드)
      - [1.2.2 단계 2: AJAX 요청 처리 (프론트엔드)](#122-단계-2-ajax-요청-처리-프론트엔드)
      - [1.2.3 단계 3: CSRF 필터 검증 (서버)](#123-단계-3-csrf-필터-검증-서버)
      - [1.2.4 단계 4: API 컨트롤러 처리 (서버)](#124-단계-4-api-컨트롤러-처리-서버)
      - [1.2.5 단계 5: 푸시 서비스 처리 (서버)](#125-단계-5-푸시-서비스-처리-서버)
      - [1.2.6 단계 6: FCM 서버 전송](#126-단계-6-fcm-서버-전송)
      - [1.2.7 단계 7: 클라이언트 메시지 수신](#127-단계-7-클라이언트-메시지-수신)
        - [A. 포그라운드 수신 (앱이 열려있을 때)](#a-포그라운드-수신-앱이-열려있을-때)
        - [B. 백그라운드 수신 (앱이 닫혀있을 때)](#b-백그라운드-수신-앱이-닫혀있을-때)
        - [C. 알림 클릭 이벤트](#c-알림-클릭-이벤트)
  - [2. 전반적인 푸시 알림 동작 방식](#2-전반적인-푸시-알림-동작-방식)
    - [2.0 FCM 토큰 생성 위치 및 과정](#20-fcm-토큰-생성-위치-및-과정)
      - [2.0.1 생성 위치](#201-생성-위치)
      - [2.0.2 생성 과정 상세](#202-생성-과정-상세)
      - [2.0.3 생성 시점](#203-생성-시점)
      - [2.0.4 생성 전제 조건](#204-생성-전제-조건)
      - [2.0.5 토큰 생성 플로우 다이어그램](#205-토큰-생성-플로우-다이어그램)
      - [2.0.6 중요 사항](#206-중요-사항)
    - [2.1 푸시 알림 아키텍처](#21-푸시-알림-아키텍처)
    - [2.2 초기화 과정](#22-초기화-과정)
      - [2.2.1 페이지 로드 시 초기화](#221-페이지-로드-시-초기화)
      - [2.2.2 토큰 발급 및 등록 과정](#222-토큰-발급-및-등록-과정)
      - [2.2.3 서버 토큰 저장](#223-서버-토큰-저장)
    - [2.3 메시지 전송 플로우](#23-메시지-전송-플로우)
      - [2.3.1 서버에서 메시지 전송 트리거](#231-서버에서-메시지-전송-트리거)
      - [2.3.2 Firebase Admin SDK 설정](#232-firebase-admin-sdk-설정)
    - [2.4 메시지 수신 및 처리](#24-메시지-수신-및-처리)
      - [2.4.1 포그라운드 수신](#241-포그라운드-수신)
      - [2.4.2 백그라운드 수신](#242-백그라운드-수신)
    - [2.5 알림 클릭 처리](#25-알림-클릭-처리)
  - [3. CSRF 필터 동작 방식](#3-csrf-필터-동작-방식)
    - [3.1 CSRF 공격이란?](#31-csrf-공격이란)
    - [3.2 CSRF 방어 메커니즘](#32-csrf-방어-메커니즘)
      - [3.2.1 토큰 기반 방어](#321-토큰-기반-방어)
    - [3.3 필터 등록 및 적용 범위](#33-필터-등록-및-적용-범위)
      - [3.3.1 필터 등록](#331-필터-등록)
      - [3.3.2 적용 범위](#332-적용-범위)
    - [3.4 필터 동작 흐름 상세](#34-필터-동작-흐름-상세)
      - [3.4.1 전체 흐름도](#341-전체-흐름도)
      - [3.4.2 단계별 상세 설명](#342-단계별-상세-설명)
    - [3.5 CSRF 토큰 생성](#35-csrf-토큰-생성)
      - [3.5.1 생성 시점](#351-생성-시점)
      - [3.5.2 토큰 저장](#352-토큰-저장)
    - [3.6 클라이언트에서 토큰 전송](#36-클라이언트에서-토큰-전송)
      - [3.6.1 AJAX 요청 (자동)](#361-ajax-요청-자동)
      - [3.6.2 폼 제출 (수동)](#362-폼-제출-수동)
    - [3.7 보안 특징 및 고려사항](#37-보안-특징-및-고려사항)
      - [3.7.1 보안 특징](#371-보안-특징)
      - [3.7.2 주의사항](#372-주의사항)
    - [3.8 필터 체인에서의 위치](#38-필터-체인에서의-위치)
    - [3.9 디버깅 및 문제 해결](#39-디버깅-및-문제-해결)
      - [3.9.1 일반적인 오류](#391-일반적인-오류)
      - [3.9.2 로그 확인](#392-로그-확인)
  - [4. 통합 동작 시나리오](#4-통합-동작-시나리오)
    - [4.1 PUSH 테스트 버튼 클릭 시 전체 플로우](#41-push-테스트-버튼-클릭-시-전체-플로우)
    - [4.2 CSRF 필터가 없는 경우의 위험](#42-csrf-필터가-없는-경우의-위험)
  - [5. 파일 구조 요약](#5-파일-구조-요약)
    - [5.1 PUSH 알림 관련 파일](#51-push-알림-관련-파일)
    - [5.2 CSRF 필터 관련 파일](#52-csrf-필터-관련-파일)
  - [6. 참고 사항](#6-참고-사항)
    - [6.1 Firebase 설정](#61-firebase-설정)
    - [6.2 보안 고려사항](#62-보안-고려사항)
    - [6.3 개선 제안](#63-개선-제안)
  - [7. 결론](#7-결론)
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
1. [PUSH 테스트 버튼 클릭 시 동작 방식](#1-push-테스트-버튼-클릭-시-동작-방식)
2. [전반적인 푸시 알림 동작 방식](#2-전반적인-푸시-알림-동작-방식)
   - [FCM 토큰 생성 위치 및 과정](#20-fcm-토큰-생성-위치-및-과정)
3. [CSRF 필터 동작 방식](#3-csrf-필터-동작-방식)

---

## 1. Main 페이지 진입부터 PUSH 테스트 버튼 클릭까지 전체 동작 과정

### 1.0 전체 플로우 개요

이 섹션에서는 사용자가 **Main 페이지(`/mobile/mobile_main`)에 진입하는 순간부터 FCM 토큰이 생성되고, PUSH 테스트 버튼을 클릭하여 푸시 알림이 전송되는 전체 과정**을 밀리초 단위로 상세하게 설명합니다.

### 1.1 Phase 1: Main 페이지 진입 및 초기화 (페이지 로드 시)

#### 1.1.1 사용자 액션
- 사용자가 브라우저에서 `/mobile/mobile_main` URL로 접근
- 또는 로그인 후 자동으로 Main 페이지로 리다이렉트

#### 1.1.2 서버 측 처리 (Spring MVC)

**1단계: 요청 수신**
```
HTTP GET /mobile/mobile_main
Headers: Cookie: JSESSIONID=xxx (세션 쿠키)
```

**2단계: 컨트롤러 매핑**
- Spring MVC가 요청을 적절한 컨트롤러 메서드에 매핑
- `@RequestMapping("/mobile/mobile_main")` 어노테이션으로 매핑된 메서드 실행

**3단계: JSP 렌더링**
- 서버에서 `mobile_main.jsp` 파일을 렌더링
- JSP 엔진이 JSP 코드를 HTML로 변환
- 서버 측 변수 및 세션 데이터를 HTML에 삽입

**4단계: HTTP 응답 전송**
```
HTTP 200 OK
Content-Type: text/html; charset=UTF-8
Body: 완성된 HTML 문서
```

#### 1.1.3 클라이언트 측 HTML 수신 및 파싱

**1단계: HTML 수신**
- 브라우저가 서버로부터 HTML 문서 수신
- 네트워크 탭에서 확인 가능: `mobile_main` 요청, Status 200

**2단계: HTML 파싱 시작**
- 브라우저 엔진이 HTML을 파싱하기 시작
- DOM 트리 구축 시작

**3단계: `<head>` 섹션 처리**

브라우저가 `<head>` 태그를 만나면 다음 순서로 처리:

**A. `<jsp:include page="/mobile/mobileHeader" />` 처리**
- JSP include 지시어로 인해 `mobileHeader.jsp` 내용이 삽입됨
- 실제 HTML에는 다음과 같이 포함됨:

```html
<head>
    <meta charset="UTF-8">
    <meta name="_csrf" content="550e8400-e29b-41d4-a716-446655440000">
    <meta name="_csrf_header" content="X-CSRF-TOKEN">
    <title>DB스마트오피스</title>
    <!-- 기타 CSS, 스크립트 링크들 -->
</head>
```

**중요**: CSRF 토큰이 메타 태그에 삽입됨
- `${sessionScope.sessionVO.csrfToken}` 값이 실제 UUID로 치환됨
- 예: `content="550e8400-e29b-41d4-a716-446655440000"`

**B. CSS 파일 로드**
```html
<link rel="stylesheet" href="/resources/mobile/assets/css/common.style.css">
<link rel="stylesheet" href="/resources/mobile/assets/css/style.css?ver=202303275">
```
- 브라우저가 CSS 파일을 비동기로 다운로드
- 파싱은 계속 진행 (CSS는 렌더링을 블로킹하지 않음)

**C. JavaScript 파일 로드 시작**

브라우저가 `<script>` 태그를 만나면 **동기적으로** 처리:

**① jQuery 로드** (`mobileHeader.jsp` 29줄)
```html
<script src="/resources/js/jquery/jquery-1.12.4.min.js"></script>
```
- 브라우저가 jQuery 파일 다운로드
- 다운로드 완료 후 즉시 실행
- `$` 및 `jQuery` 전역 변수 생성
- **이 시점**: jQuery 사용 가능

**② 기타 유틸리티 스크립트 로드** (`mobileHeader.jsp` 30-36줄)
```html
<script src="/resources/mobile/assets/js/moment.min.js"></script>
<script src="/resources/mobile/assets/js/jquery.daterangepicker.min.js"></script>
<script src="/resources/mobile/assets/js/common.js"></script>
<script src="/resources/js/common/common.js"></script>
<script src="/resources/js/common/module.js"></script>
<script src="/resources/js/common/util.js"></script>
```
- 각 스크립트가 순차적으로 로드 및 실행
- 전역 변수 및 함수들이 등록됨

**③ CSRF 토큰 자동 추가 스크립트 실행** (`mobileHeader.jsp` 40-51줄)
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

**실행 순서**:
1. `$(function() { ... })` → jQuery의 `$(document).ready()` 축약형
2. DOM이 준비되면 실행
3. 메타 태그에서 CSRF 토큰 읽기: `token = "550e8400-e29b-41d4-a716-446655440000"`
4. 헤더 이름 읽기: `header = "X-CSRF-TOKEN"`
5. `ajaxSend` 이벤트 리스너 등록
   - 이후 모든 AJAX 요청 전에 자동으로 `X-CSRF-TOKEN` 헤더 추가

**④ 전역 변수 설정** (`mobileHeader.jsp` 54-70줄)
```javascript
var _CONTEXT_PATH = '/';
var _TOP_MENU_ID = '';
var _CURRENT_GROUP_ID = '';
var _CURRENT_MENU_ID = '';
var isMobile = false; // 모바일 체크 로직
```

**⑤ ajaxCommon.js 로드** (`mobileHeader.jsp` 71줄)
```html
<script type="text/javascript" src="/resources/js/common/ajaxCommon.js"></script>
```
- `ajax` 객체 생성
- `ajax.json()`, `ajax.post()`, `ajax.get()` 등 함수 정의

**4단계: `<body>` 섹션 처리**

브라우저가 `<body>` 태그를 만나면:

**A. Firebase SDK 로드** (`mobile_main.jsp` 8-9줄)
```html
<script src="https://www.gstatic.com/firebasejs/10.13.1/firebase-app-compat.js"></script>
<script src="https://www.gstatic.com/firebasejs/10.13.1/firebase-messaging-compat.js"></script>
```

**로드 과정**:
1. 브라우저가 CDN에서 Firebase SDK 다운로드
2. `firebase-app-compat.js` 먼저 로드 및 실행
   - `firebase` 전역 객체 생성
   - `firebase.initializeApp`, `firebase.app()` 등 함수 등록
3. `firebase-messaging-compat.js` 로드 및 실행
   - `firebase.messaging()` 함수 등록
   - **이 시점**: Firebase SDK 사용 가능

**B. push_init.js 로드 및 즉시 실행** (`mobile_main.jsp` 11줄)
```html
<script type="text/javascript" src="/push_init.js"></script>
```

**중요**: `push_init.js`는 **IIFE (Immediately Invoked Function Expression)**로 작성되어 있어서 로드되자마자 즉시 실행됩니다.

```javascript
// push_init.js
(() => {
    'use strict';
    // 코드가 여기서 즉시 실행됨
})();
```

#### 1.1.4 push_init.js 즉시 실행 단계

**1단계: Firebase Config 설정** (5-14줄)
```javascript
const firebaseConfig = {
    apiKey: "AIzaSyAjZkzvIfunxSwZocYTgkpLNNvIHMec-cg",
    authDomain: "mobilepwa-b4c16.firebaseapp.com",
    projectId: "mobilepwa-b4c16",
    // ... 기타 설정
};
```
- Firebase 프로젝트 설정 정보를 객체로 정의
- **이 시점**: 아직 Firebase 초기화 안 됨

**2단계: Firebase SDK 존재 확인** (16-20줄)
```javascript
if (typeof firebase === 'undefined') {
    console.error('Firebase SDK not loaded!');
    return;
}
```
- `firebase` 객체가 존재하는지 확인
- 없으면 에러 로그 후 종료
- **정상 시**: 다음 단계 진행

**3단계: Firebase 초기화** (22-24줄)
```javascript
firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();
```

**실행 내용**:
1. `firebase.initializeApp(firebaseConfig)` 호출
   - Firebase 앱 인스턴스 생성
   - 설정 정보를 Firebase에 등록
   - **이 시점**: Firebase 사용 준비 완료
2. `firebase.messaging()` 호출
   - Firebase Messaging 서비스 인스턴스 생성
   - `messaging` 변수에 할당
   - **이 시점**: FCM 토큰 발급 준비 완료

**4단계: 사용자 ID 설정** (26-27줄)
```javascript
const MEMBER_ID = 'jhchoi@dbinc.co.kr';
```
- 하드코딩된 사용자 ID (실제로는 세션에서 가져와야 함)

**5단계: 전역 함수 정의** (29-72줄)
```javascript
window.requestPermissionAndGetToken = function() {
    // 함수 본문 (아직 실행 안 됨, 정의만 됨)
};
```
- 함수를 정의만 하고 실행하지 않음
- `window` 객체에 등록하여 어디서든 호출 가능

**6단계: 토스트 메시지 함수 정의** (74-99줄)
```javascript
function showToast(title, message) {
    // 함수 정의만 됨
}
```
- 포그라운드 메시지 수신 시 사용할 함수

**7단계: 서버 전송 함수 정의** (101-117줄)
```javascript
window.sendTokenToServer = function(fcmToken) {
    // 함수 정의만 됨
};
```
- FCM 토큰을 서버에 전송하는 함수

**8단계: 초기화 함수 정의 및 실행 대기** (120-145줄)

```javascript
function initializePush() {
    // 포그라운드 메시지 리스너 등록
    messaging.onMessage((payload) => {
        // 메시지 수신 시 실행될 코드
    });
    
    // FCM 토큰 요청
    if (typeof window.requestPermissionAndGetToken === 'function') {
        window.requestPermissionAndGetToken();
    }
}
```

**중요**: `initializePush()` 함수는 **정의만 되고 아직 실행되지 않음**

**9단계: DOM Ready 이벤트 리스너 등록** (147-157줄)

```javascript
if (typeof $ !== 'undefined' && typeof $.fn !== 'undefined') {
    $(document).ready(initializePush);
} else {
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initializePush);
    } else {
        initializePush();
    }
}
```

**실행 로직**:
1. jQuery가 로드되어 있으면: `$(document).ready(initializePush)` 등록
2. jQuery가 없으면: 네이티브 `DOMContentLoaded` 이벤트 사용
3. 이미 로드 완료되었으면: 즉시 `initializePush()` 실행

**이 시점**: DOM이 준비되면 `initializePush()`가 실행될 예정

#### 1.1.5 DOM Ready 이벤트 발생

브라우저가 HTML 파싱을 완료하고 DOM 트리를 구축하면:

**1단계: jQuery Ready 이벤트 발생**
- `$(document).ready()` 콜백 실행
- 또는 `DOMContentLoaded` 이벤트 발생

**2단계: `initializePush()` 함수 실행**

**A. 포그라운드 메시지 리스너 등록** (125-140줄)
```javascript
try {
    if (messaging && typeof messaging.onMessage === 'function') {
        messaging.onMessage((payload) => {
            console.log('[FCM] Foreground message:', payload);
            const title = payload.data?.title || '새 알림';
            const body = payload.data?.body || '내용 없음';
            showToast(title, body);
        });
    }
} catch (error) {
    console.error('Error setting up onMessage listener:', error);
}
```

**실행 내용**:
1. `messaging.onMessage()` 호출
2. 콜백 함수 등록
3. **이 시점**: 포그라운드에서 메시지 수신 시 자동으로 토스트 표시

**B. FCM 토큰 요청 시작** (142-144줄)
```javascript
if (typeof window.requestPermissionAndGetToken === 'function') {
    window.requestPermissionAndGetToken();
}
```

**이 시점**: `requestPermissionAndGetToken()` 함수가 **비동기로** 실행 시작

#### 1.1.6 FCM 토큰 생성 과정 (비동기)

**1단계: 브라우저 지원 확인** (30-39줄)

```javascript
if (!('serviceWorker' in navigator)) {
    console.warn('Service Worker not supported');
    return; // 종료
}

if (!('PushManager' in window)) {
    console.warn('Push API not supported');
    return; // 종료
}
```

**확인 내용**:
- `navigator.serviceWorker` 존재 여부
- `window.PushManager` 존재 여부
- **지원 안 하면**: 함수 종료, 토큰 생성 불가

**2단계: 알림 권한 요청** (41-47줄)

```javascript
Notification.requestPermission()
    .then(permission => {
        if (permission !== 'granted') {
            console.warn('Notification permission denied');
            return; // 종료
        }
        console.log('Notification permission granted');
        // 다음 단계로 진행
    });
```

**실행 과정**:
1. `Notification.requestPermission()` 호출
2. 브라우저가 사용자에게 알림 권한 요청 팝업 표시
   - **첫 방문**: "이 사이트에서 알림을 보내도록 허용하시겠습니까?" 팝업
   - **이전에 허용**: 즉시 `'granted'` 반환
   - **이전에 거부**: 즉시 `'denied'` 반환
3. 사용자가 "허용" 클릭
4. Promise가 `'granted'`로 resolve
5. `.then()` 콜백 실행
6. **권한 거부 시**: 함수 종료, 토큰 생성 불가

**3단계: Service Worker 등록** (49-50줄)

```javascript
return navigator.serviceWorker.register('/firebase-messaging-sw.js');
```

**실행 과정**:
1. `navigator.serviceWorker.register()` 호출
2. 브라우저가 `/firebase-messaging-sw.js` 파일 다운로드
3. Service Worker로 등록
4. Service Worker가 백그라운드에서 실행 시작
5. `registration` 객체 반환
   - `registration.scope`: Service Worker 스코프
   - `registration.active`: 활성화된 Service Worker

**4단계: FCM 토큰 발급 요청** (56-59줄)

```javascript
return messaging.getToken({
    serviceWorkerRegistration: registration,
    vapidKey: 'BEcW_Ep6uWwbA64qsiU64TLQ_wj6AXgJbcjszjLdVOIG1gZ8rVM2XCSHNG8A8M4l5v6CZVfLdu_EBqKxjMlyj7c'
});
```

**실행 과정**:
1. `messaging.getToken()` 호출
2. **파라미터 전달**:
   - `serviceWorkerRegistration`: 등록된 Service Worker
   - `vapidKey`: VAPID 공개 키
3. **Firebase SDK 내부 동작**:
   - 브라우저의 Push Subscription 생성
   - Firebase 서버에 토큰 발급 요청
   - Firebase 서버가 브라우저/디바이스를 식별하여 고유한 FCM 토큰 생성
4. Promise가 FCM 토큰 문자열로 resolve
   - 예: `"dK3jF8xYz...약150자...ABC123"`

**5단계: 토큰 수신 및 서버 전송** (61-64줄)

```javascript
.then(token => {
    if (token) {
        console.log('FCM Token:', token);
        sendTokenToServer(token);
    }
});
```

**실행 내용**:
1. 토큰이 정상적으로 수신되었는지 확인
2. 콘솔에 토큰 출력 (개발자 도구에서 확인 가능)
3. `sendTokenToServer(token)` 호출

**6단계: 서버에 토큰 전송** (101-117줄)

```javascript
window.sendTokenToServer = function(fcmToken) {
    $.ajax({
        url: '/api/push/registerToken',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            memberId: MEMBER_ID,
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

**실행 과정**:
1. `$.ajax()` 호출로 AJAX 요청 생성
2. **요청 내용**:
   - URL: `/api/push/registerToken`
   - Method: POST
   - ContentType: `application/json`
   - Body: `{"memberId": "jhchoi@dbinc.co.kr", "fcmToken": "dK3jF8..."}`
3. **CSRF 토큰 자동 추가**:
   - `ajaxSend` 이벤트 리스너가 자동으로 `X-CSRF-TOKEN` 헤더 추가
   - 헤더 값: `550e8400-e29b-41d4-a716-446655440000`
4. HTTP 요청 전송

**7단계: 서버 측 토큰 저장 처리**

**A. CSRF 필터 검증** (`CsrfFilter.java`)
- 세션 확인: 있음
- HTTP 메서드: POST → 토큰 검증 필요
- 헤더에서 토큰 추출: `550e8400-e29b-41d4-a716-446655440000`
- 세션 토큰과 비교: 일치
- 통과

**B. API 컨트롤러 처리** (`PushRestController.java` 27-32줄)
```java
@PostMapping("/registerToken")
public @ResponseBody ResponseEntity<String> registerToken(@RequestBody PushTokenDto requestDto) {
    pushService.saveOrUpdateToken(requestDto.getMemberId(), requestDto.getFcmToken());
    return ResponseEntity.ok("Token registered successfully.");
}
```

**실행 내용**:
1. `@RequestBody`로 JSON을 `PushTokenDto`로 변환
   - `memberId = "jhchoi@dbinc.co.kr"`
   - `fcmToken = "dK3jF8..."`
2. `pushService.saveOrUpdateToken()` 호출

**C. 서비스 처리** (`PushService.java` 29-34줄)
```java
public void saveOrUpdateToken(String memberId, String fcmToken) {
    PushTokenDto dto = new PushTokenDto();
    dto.setMemberId(memberId);
    dto.setFcmToken(fcmToken);
    pushTokenRepository.saveOrUpdateToken(dto);
}
```

**실행 내용**:
1. DTO 객체 생성 및 값 설정
2. Repository의 `saveOrUpdateToken()` 호출
3. **DB 작업**:
   - 해당 `memberId`의 토큰이 있으면 UPDATE
   - 없으면 INSERT
   - 결과: DB에 FCM 토큰 저장 완료

**8단계: 응답 수신**

```javascript
success: function(response) {
    console.log('Token sent to server:', response);
}
```

- 서버로부터 `"Token registered successfully."` 응답 수신
- 콘솔에 로그 출력
- **이 시점**: FCM 토큰이 DB에 저장 완료

#### 1.1.7 페이지 렌더링 완료

**1단계: DOM 완성**
- 모든 HTML 요소가 DOM 트리에 추가됨
- `#btnPushTest` 버튼도 DOM에 존재

**2단계: 버튼 이벤트 리스너 등록** (`mobile_main.jsp` 13-159줄)

```javascript
$(document).ready(function() {
    // ... 다른 이벤트 리스너들 ...
    
    // PUSH 테스트
    $('#btnPushTest').on('click', function() {
        // 클릭 시 실행될 코드
    });
});
```

**실행 내용**:
1. `$(document).ready()` 콜백 실행
2. `$('#btnPushTest').on('click', ...)` 실행
3. 버튼에 클릭 이벤트 리스너 등록
4. **이 시점**: 버튼 클릭 대기 상태

**3단계: 페이지 표시**
- 브라우저가 완성된 페이지를 화면에 렌더링
- 사용자가 페이지를 볼 수 있음
- **PUSH 테스트** 버튼이 화면에 표시됨

### 1.2 Phase 2: PUSH 테스트 버튼 클릭 및 푸시 알림 전송

#### 1.2.1 사용자 액션
- 사용자가 마우스로 **"PUSH 테스트"** 버튼 클릭
- 또는 터치 디바이스에서 버튼 터치

#### 1.2.2 클릭 이벤트 발생

**1단계: 브라우저 이벤트 시스템**
- 브라우저가 클릭 이벤트 감지
- 이벤트 객체 생성
- 이벤트 버블링 시작

**2단계: jQuery 이벤트 핸들러 실행** (`mobile_main.jsp` 134-157줄)

```javascript
$('#btnPushTest').on('click', function() {
    setTimeout(function() {
        // 실제 로직
    }, 200);
});
```

**실행 순서**:
1. 클릭 이벤트가 `#btnPushTest` 요소에 도달
2. 등록된 이벤트 핸들러 실행
3. `setTimeout()` 호출로 200ms 지연
   - **이유**: UI 반응성 향상, 로딩 표시 시간 확보

#### 1.2.3 200ms 후 실행 (setTimeout 콜백)

**1단계: 파라미터 객체 생성** (136-138줄)

```javascript
let param = new Object();
param["targetId"] = 'jhchoi@dbinc.co.kr';
let params = JSON.stringify(param);
```

**실행 내용**:
1. 빈 객체 생성: `param = {}`
2. `targetId` 속성 추가: `param = {targetId: "jhchoi@dbinc.co.kr"}`
3. JSON 문자열로 변환: `params = '{"targetId":"jhchoi@dbinc.co.kr"}'`

**2단계: AJAX 요청 생성** (140-148줄)

```javascript
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
    // 에러 처리
}
```

**`ajax.json()` 함수 내부 실행** (`ajaxCommon.js` 25-73줄):

**A. 파라미터 처리** (27-35줄)
```javascript
if( $.type(param) == 'object' ){
    param["currentMenuId"] = _CURRENT_MENU_ID;
    param = JSON.stringify(param);
} else {
    param = JSON.parse(param);
    param["currentMenuId"] = _CURRENT_MENU_ID;
    param = JSON.stringify(param);
}
```

**실행 내용**:
1. `param`이 문자열이므로 `else` 블록 실행
2. JSON 파싱: `param = {targetId: "jhchoi@dbinc.co.kr"}`
3. `currentMenuId` 추가: `param = {targetId: "jhchoi@dbinc.co.kr", currentMenuId: ""}`
4. 다시 JSON 문자열로 변환: `param = '{"targetId":"jhchoi@dbinc.co.kr","currentMenuId":""}'`

**B. 비동기 설정** (37-41줄)
```javascript
if (doAsync) {
    setAsync = doAsync;
} else {
    setAsync = false;  // 동기 방식
}
```
- `false`로 설정되어 동기 방식 (응답을 기다림)

**C. jQuery AJAX 요청 생성** (46-71줄)

```javascript
return $.ajax({
    url : url,  // "/api/push/test"
    data : param,  // JSON 문자열
    type : 'post',
    dataType : 'json',
    async : false,  // 동기
    contentType : "application/json; charset=UTF-8",
    success : function(data) {
        // 성공 콜백
    },
    error : function(e) {
        // 에러 콜백
    }
});
```

**3단계: ajaxSend 이벤트 발생 및 CSRF 토큰 추가**

jQuery가 AJAX 요청을 보내기 전에 `ajaxSend` 이벤트 발생:

```javascript
$(document).ajaxSend(function(e, xhr, options) {
    if (options.type && ['POST', 'PUT', 'DELETE', 'PATCH'].includes(options.type.toUpperCase())) {
        xhr.setRequestHeader(header, token);
    }
});
```

**실행 내용**:
1. `options.type`이 `'post'`이므로 조건 만족
2. 메타 태그에서 토큰 읽기: `token = "550e8400-e29b-41d4-a716-446655440000"`
3. 헤더 이름 읽기: `header = "X-CSRF-TOKEN"`
4. `xhr.setRequestHeader("X-CSRF-TOKEN", "550e8400-e29b-41d4-a716-446655440000")` 실행
5. **이 시점**: 요청 헤더에 CSRF 토큰 포함됨

**4단계: HTTP 요청 전송**

**요청 내용**:
```
POST /api/push/test HTTP/1.1
Host: example.com
Content-Type: application/json; charset=UTF-8
X-CSRF-TOKEN: 550e8400-e29b-41d4-a716-446655440000
Cookie: JSESSIONID=xxx

{"targetId":"jhchoi@dbinc.co.kr","currentMenuId":""}
```

#### 1.2.4 서버 측 처리

**1단계: CSRF 필터 검증** (`CsrfFilter.java`)

```java
HttpSession session = request.getSession(true);
SessionVO vo = (SessionVO) session.getAttribute("sessionVO");
if (vo == null || vo.getUserId() == null || "".equals(vo.getUserId())) {
    chain.doFilter(request, response);
    return;
}

String method = request.getMethod().toUpperCase();  // "POST"
if ("GET".equals(method) || "HEAD".equals(method)) {
    chain.doFilter(request, response);
    return;
}

String headerToken = request.getHeader("X-CSRF-TOKEN");  // "550e8400-e29b-41d4-a716-446655440000"
String paramToken = request.getParameter("csrfToken");  // null

boolean isValid = vo.getCsrfToken().equals(headerToken) || 
                  vo.getCsrfToken().equals(paramToken);
if (isValid) {
    chain.doFilter(request, response);  // 통과
} else {
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF Token");
}
```

**실행 내용**:
1. 세션에서 `sessionVO` 조회: 있음
2. HTTP 메서드 확인: POST → 토큰 검증 필요
3. 헤더에서 토큰 추출: `"550e8400-e29b-41d4-a716-446655440000"`
4. 세션 토큰과 비교: 일치
5. **통과**: 다음 필터/컨트롤러로 요청 전달

**2단계: API 컨트롤러 처리** (`PushRestController.java` 37-51줄)

```java
@PostMapping("/test")
public @ResponseBody ResponseEntity<String> triggerApprovalNotification(
        @RequestBody PushTokenDto pushToken) {
    
    pushService.triggerApprovalPushNotification(
        pushToken.getTargetId(), 
        "새로운 회의실 예약!", 
        "4-B 회의실 예약시간이 30분 남았습니다. 회의실 예약을 확인해 주세요.", 
        "/reserve_main"
    );
    
    return ResponseEntity.ok("Approval processed and push notification sent.");
}
```

**실행 내용**:
1. `@PostMapping("/test")` 매핑 매칭
2. `@RequestBody`로 JSON 파싱:
   - `pushToken.targetId = "jhchoi@dbinc.co.kr"`
   - `pushToken.currentMenuId = ""` (무시됨)
3. `pushService.triggerApprovalPushNotification()` 호출
   - 파라미터:
     - `approvalTargetId = "jhchoi@dbinc.co.kr"`
     - `title = "새로운 회의실 예약!"`
     - `body = "4-B 회의실 예약시간이 30분 남았습니다. 회의실 예약을 확인해 주세요."`
     - `link = "/reserve_main"`

**3단계: 푸시 서비스 처리** (`PushService.java` 39-72줄)

**A. FCM 토큰 조회** (40-46줄)

```java
List<String> tokens = pushTokenRepository.selectTokensForApprovalTarget(approvalTargetId);

if (tokens.isEmpty()) {
    System.out.println("No token found for member: " + approvalTargetId);
    return;
}
```

**실행 내용**:
1. `pushTokenRepository.selectTokensForApprovalTarget("jhchoi@dbinc.co.kr")` 호출
2. **DB 쿼리 실행**:
   - `SELECT fcm_token FROM push_token WHERE member_id = 'jhchoi@dbinc.co.kr'`
   - 결과: `["dK3jF8xYz...ABC123"]` (이전에 저장한 토큰)
3. 토큰이 있으면 다음 단계 진행

**B. FCM 메시지 구성** (48-62줄)

```java
Message message = Message.builder()
        .putData("title", title)
        .putData("body", body)
        .putData("link", link)
        .setToken(tokens.get(0))
        .setWebpushConfig(WebpushConfig.builder()
            .setNotification(WebpushNotification.builder()
                .setTitle(title)
                .setBody(body)
                .setIcon("/resources/icons/icon-192x192.png")
                .build())
            .putHeader("Urgency", "high")
            .build())
        .build();
```

**메시지 구조**:
```json
{
  "token": "dK3jF8xYz...ABC123",
  "data": {
    "title": "새로운 회의실 예약!",
    "body": "4-B 회의실 예약시간이 30분 남았습니다. 회의실 예약을 확인해 주세요.",
    "link": "/reserve_main"
  },
  "webpush": {
    "notification": {
      "title": "새로운 회의실 예약!",
      "body": "4-B 회의실 예약시간이 30분 남았습니다. 회의실 예약을 확인해 주세요.",
      "icon": "/resources/icons/icon-192x192.png"
    },
    "headers": {
      "Urgency": "high"
    }
  }
}
```

**C. Firebase Admin SDK로 메시지 전송** (64-71줄)

```java
try {
    String response = FirebaseMessaging.getInstance().send(message);
    System.out.println("Successfully sent message: " + response);
} catch (FirebaseMessagingException e) {
    System.err.println("Sending FCM message failed: " + e.getMessage());
}
```

**실행 내용**:
1. `FirebaseMessaging.getInstance()` 호출
   - Firebase Admin SDK 싱글톤 인스턴스 가져오기
   - `serviceAccountKey.json` 파일로 인증
2. `send(message)` 호출
   - 메시지를 Firebase Cloud Messaging 서버로 전송
   - **네트워크 요청**: HTTPS POST to `https://fcm.googleapis.com/v1/projects/{projectId}/messages:send`
3. Firebase 서버가 메시지 수신 및 처리
4. 성공 시 메시지 ID 반환: `"projects/mobilepwa-b4c16/messages/0:1234567890"`
5. 콘솔에 로그 출력

**4단계: 응답 반환**

```java
return ResponseEntity.ok("Approval processed and push notification sent.");
```

- HTTP 200 OK 응답
- Body: `"Approval processed and push notification sent."`

#### 1.2.5 Firebase Cloud Messaging 서버 처리

**1단계: 메시지 수신**
- Firebase 서버가 메시지 수신
- 메시지 구조 검증

**2단계: 토큰 검증**
- FCM 토큰이 유효한지 확인
- 토큰에 연결된 디바이스/브라우저 식별

**3단계: 메시지 라우팅**
- 해당 디바이스/브라우저로 메시지 전송
- 디바이스가 오프라인이면 큐에 저장 후 온라인 시 전송

**4단계: 푸시 알림 전송**
- 브라우저의 Push Service로 메시지 전송
- Service Worker가 메시지 수신

#### 1.2.6 클라이언트 메시지 수신

**시나리오 A: 포그라운드 (앱이 열려있을 때)**

**1단계: onMessage 이벤트 발생** (`push_init.js` 127-134줄)

```javascript
messaging.onMessage((payload) => {
    console.log('[FCM] Foreground message:', payload);
    
    const title = payload.data?.title || '새 알림';
    const body = payload.data?.body || '내용 없음';

    showToast(title, body);
});
```

**실행 내용**:
1. Firebase SDK가 메시지 수신
2. `onMessage` 콜백 실행
3. `payload` 객체:
   ```javascript
   {
     data: {
       title: "새로운 회의실 예약!",
       body: "4-B 회의실 예약시간이 30분 남았습니다. 회의실 예약을 확인해 주세요.",
       link: "/reserve_main"
     }
   }
   ```
4. `showToast()` 함수 호출

**2단계: 토스트 메시지 표시** (`push_init.js` 75-99줄)

```javascript
function showToast(title, message) {
    const container = document.getElementById('toast-container');
    const toast = document.createElement('div');
    toast.style.cssText = `...`;
    toast.innerHTML = `<strong>${title}</strong><span>${message}</span>`;
    container.appendChild(toast);
    
    setTimeout(() => {
        toast.style.animation = 'slideOut 0.3s ease';
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}
```

**실행 내용**:
1. `toast-container` 요소 찾기
2. 토스트 DOM 요소 생성
3. 스타일 및 내용 설정
4. DOM에 추가 (화면에 표시)
5. 3초 후 자동 제거

**시나리오 B: 백그라운드 (앱이 닫혀있거나 다른 탭에 있을 때)**

**1단계: Service Worker 메시지 수신** (`firebase-messaging-sw.js` 22-39줄)

```javascript
messaging.onBackgroundMessage((payload) => {
    console.log('[firebase-messaging-sw.js] Received background message:', payload);

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

    self.registration.showNotification(notificationTitle, notificationOptions);
});
```

**실행 내용**:
1. Service Worker가 백그라운드에서 메시지 수신
2. `onBackgroundMessage` 콜백 실행
3. 알림 옵션 구성
4. `self.registration.showNotification()` 호출
5. **브라우저 네이티브 알림 표시**

**2단계: 사용자가 알림 클릭** (`firebase-messaging-sw.js` 56-79줄)

```javascript
self.addEventListener('notificationclick', (event) => {
    console.log('[Service Worker] Notification click:', event);

    event.notification.close();

    const link = event.notification.data?.link || '/';

    event.waitUntil(
        clients.matchAll({ type: 'window', includeUncontrolled: true })
            .then((clientList) => {
                for (const client of clientList) {
                    if (client.url.includes(link) && 'focus' in client) {
                        return client.focus();
                    }
                }
                if (clients.openWindow) {
                    return clients.openWindow(link);
                }
            })
    );
});
```

**실행 내용**:
1. 사용자가 알림 클릭
2. `notificationclick` 이벤트 발생
3. 알림 닫기
4. `link` 추출: `"/reserve_main"`
5. 열려있는 탭 확인
6. 해당 URL이 포함된 탭이 있으면 포커스
7. 없으면 새 탭으로 `/reserve_main` 열기

#### 1.2.7 AJAX 응답 처리

**1단계: 응답 수신** (`ajaxCommon.js` 53-66줄)

```javascript
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
}
```

**실행 내용**:
1. 서버로부터 응답 수신
2. `data = "Approval processed and push notification sent."` (문자열)
3. `data.exceptionMsg` 없음 → else 블록 실행
4. `fnCallback(data)` 호출

**2단계: 콜백 함수 실행** (`mobile_main.jsp` 143-146줄)

```javascript
function(res) {
    console.log("" + res); 
    hideLoading();
}
```

**실행 내용**:
1. 콘솔에 응답 로그 출력
2. `hideLoading()` 호출 (로딩 표시 제거)
3. **이 시점**: 전체 프로세스 완료

### 1.3 전체 타임라인 요약

```
[0ms]    사용자가 /mobile/mobile_main 접근
[10ms]   서버에서 JSP 렌더링 시작
[50ms]   HTML 응답 전송 시작
[100ms]  브라우저 HTML 수신 및 파싱 시작
[150ms]  mobileHeader.jsp 포함, CSRF 토큰 메타 태그 삽입
[200ms]  jQuery 로드 및 실행
[250ms]  CSRF 자동 추가 스크립트 실행 (리스너 등록)
[300ms]  Firebase SDK 로드
[350ms]  push_init.js 로드 및 즉시 실행
[400ms]  Firebase 초기화 완료
[450ms]  DOM Ready 이벤트 발생
[500ms]  initializePush() 실행
[550ms]  onMessage 리스너 등록
[600ms]  requestPermissionAndGetToken() 시작
[650ms]  알림 권한 요청 (사용자 허용)
[700ms]  Service Worker 등록
[800ms]  messaging.getToken() 호출
[1200ms] FCM 토큰 수신 (예: "dK3jF8...")
[1250ms] sendTokenToServer() 호출
[1300ms] /api/push/registerToken 요청 전송
[1400ms] 서버에서 토큰 DB 저장 완료
[1450ms] 페이지 렌더링 완료, 버튼 표시
[5000ms] 사용자가 "PUSH 테스트" 버튼 클릭
[5200ms] setTimeout 200ms 경과, ajax.json() 호출
[5250ms] CSRF 토큰 자동 추가
[5300ms] /api/push/test 요청 전송
[5400ms] 서버 CSRF 필터 검증 통과
[5500ms] PushRestController 수신
[5600ms] PushService.triggerApprovalPushNotification() 호출
[5700ms] DB에서 FCM 토큰 조회
[5800ms] FCM 메시지 구성
[6000ms] Firebase Admin SDK로 메시지 전송
[6500ms] Firebase 서버 메시지 라우팅
[7000ms] 클라이언트 메시지 수신
[7100ms] 포그라운드: 토스트 표시 또는 백그라운드: 브라우저 알림 표시
[7200ms] AJAX 응답 수신 및 콜백 실행
[7300ms] 프로세스 완료
```

### 1.1 전체 플로우 다이어그램 (간략 버전)

```
[사용자 클릭] 
    ↓
[프론트엔드: 버튼 이벤트 핸들러]
    ↓
[프론트엔드: AJAX 요청 생성 + CSRF 토큰 자동 추가]
    ↓
[서버: CSRF 필터 검증]
    ↓
[서버: API 컨트롤러 수신]
    ↓
[서버: PushService 처리]
    ↓
[서버: DB에서 FCM 토큰 조회]
    ↓
[서버: Firebase Admin SDK로 메시지 전송]
    ↓
[FCM 서버: 메시지 라우팅]
    ↓
[클라이언트: 메시지 수신]
    ├─ 포그라운드: onMessage 이벤트 → 토스트 표시
    └─ 백그라운드: Service Worker → 브라우저 알림 표시
```

### 1.2 단계별 상세 설명

#### 1.2.1 단계 1: 버튼 클릭 이벤트 (프론트엔드)

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
- `#btnPushTest` 버튼 클릭 시 이벤트 발생
- 200ms 지연 후 실행 (UI 반응성 향상)
- `targetId`를 `'jhchoi@dbinc.co.kr'`로 하드코딩하여 설정
- JSON 객체를 문자열로 변환
- `ajax.json()` 함수를 통해 `/api/push/test` 엔드포인트로 POST 요청
- 동기 방식(`false`)으로 실행하여 응답을 기다림
- 성공 시 콜백에서 응답 로그 출력 및 로딩 숨김
- 실패 시 에러 정보를 객체로 만들어 처리

#### 1.2.2 단계 2: AJAX 요청 처리 (프론트엔드)

**파일 위치**: `src/main/webapp/resources/js/common/ajaxCommon.js` (25-73줄)

**코드**:
```javascript
json : function(url, param, fnCallback, doAsync) {
    try {
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
1. **파라미터 처리**:
   - 파라미터가 객체면 `currentMenuId` 추가 후 JSON 문자열로 변환
   - 파라미터가 문자열이면 JSON 파싱 후 `currentMenuId` 추가하고 다시 문자열로 변환
   - 최종적으로 `{"targetId": "jhchoi@dbinc.co.kr", "currentMenuId": "..."}` 형태가 됨

2. **비동기 설정**:
   - `doAsync` 파라미터에 따라 동기/비동기 결정
   - PUSH 테스트에서는 `false`로 전달되어 동기 방식

3. **AJAX 요청 생성**:
   - URL: `/api/push/test`
   - Method: POST
   - ContentType: `application/json; charset=UTF-8`
   - DataType: `json` (응답을 JSON으로 파싱)

4. **CSRF 토큰 자동 추가**:
   - `mobileHeader.jsp`의 `ajaxSend` 이벤트에서 자동 처리 (아래 참조)

5. **응답 처리**:
   - 성공: 예외 메시지가 있으면 메인 페이지로 이동, 없으면 콜백 실행
   - 실패: 에러 응답을 콜백으로 전달

**CSRF 토큰 자동 추가 메커니즘**:
- 파일 위치: `src/main/webapp/WEB-INF/views/include/mobileHeader.jsp` (40-51줄)
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
- 모든 AJAX 요청 전에 `ajaxSend` 이벤트 발생
- POST/PUT/DELETE/PATCH 메서드인 경우 자동으로 `X-CSRF-TOKEN` 헤더 추가
- 토큰 값은 메타 태그에서 읽어옴: `<meta name="_csrf" content="${sessionScope.sessionVO.csrfToken}">`

#### 1.2.3 단계 3: CSRF 필터 검증 (서버)

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/config/CsrfFilter.java`

**동작 흐름**:
1. 요청이 서버에 도착
2. `CsrfFilter`의 `doFilter()` 메서드 실행
3. 세션 확인:
   - 세션이 없거나 `sessionVO`가 없으면 → 검증 생략, 통과
   - 세션이 있으면 → 다음 단계 진행
4. HTTP 메서드 확인:
   - OPTIONS → 검증 생략 (CORS Preflight)
   - GET, HEAD → 검증 생략 (안전한 메서드)
   - POST, PUT, DELETE, PATCH → CSRF 토큰 검증 수행
5. CSRF 토큰 검증:
   - `X-CSRF-TOKEN` 헤더 또는 `csrfToken` 파라미터에서 토큰 추출
   - 세션의 `csrfToken`과 비교
   - 일치하면 통과, 불일치하면 403 Forbidden 반환

**상세 코드 분석**:
```java
@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    HttpSession session = request.getSession(true);
    
    SessionVO vo = (SessionVO) session.getAttribute("sessionVO");
    if (vo == null || vo.getUserId() == null || "".equals(vo.getUserId())) {
        // 세션이 없는경우 Check 제외  
        chain.doFilter(request, response);
        return;
    } 
     
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

    // 위험한 메서드 → 토큰 검증
    String headerToken = request.getHeader(CSRF_HEADER);  // "X-CSRF-TOKEN"
    String paramToken = request.getParameter(CSRF_PARAM); // "csrfToken"

    boolean isValid = vo.getCsrfToken().equals(headerToken) || 
                      vo.getCsrfToken().equals(paramToken);
    if (isValid) {
        chain.doFilter(request, response);
    } else {
        log.error("CSRF Token Error: {}---{}---{}", 
                  request.getRequestURI(), headerToken, paramToken);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF Token");
    }
}
```

#### 1.2.4 단계 4: API 컨트롤러 처리 (서버)

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/web/controller/push/PushRestController.java` (37-51줄)

**코드**:
```java
@PostMapping("/test")
public @ResponseBody ResponseEntity<String> triggerApprovalNotification(
        @RequestBody PushTokenDto pushToken) {
    
    // 실제 결재 로직 처리...
    
    // 결재 건수 발생 후 알림 전송
    pushService.triggerApprovalPushNotification(
        pushToken.getTargetId(), 
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

**PushTokenDto 구조**:
```java
@Data
public class PushTokenDto {
    private String memberId;
    private String fcmToken;
    private String targetId;
}
```

#### 1.2.5 단계 5: 푸시 서비스 처리 (서버)

**파일 위치**: `src/main/java/kr/co/dbinc/smartofficepwa/web/service/push/PushService.java` (39-72줄)

**코드**:
```java
public void triggerApprovalPushNotification(String approvalTargetId, String title, 
                                           String body, String link) {
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

1. **FCM 토큰 조회**:
   - `PushTokenRepository.selectTokensForApprovalTarget()` 호출
   - 대상 사용자 ID로 DB에서 FCM 토큰 조회
   - 토큰이 없으면 로그 출력 후 종료

2. **FCM 메시지 구성**:
   - **Data Payload**: 
     - `title`: 알림 제목
     - `body`: 알림 본문
     - `link`: 클릭 시 이동할 URL
   - **WebpushConfig**: 웹 푸시 알림 설정
     - `Notification`: 브라우저 알림 표시용 설정
       - `title`, `body`: 알림에 표시될 텍스트
       - `icon`: 알림 아이콘 경로
     - `Urgency`: "high"로 설정하여 우선순위 높임

3. **Firebase Admin SDK로 전송**:
   - `FirebaseMessaging.getInstance().send(message)` 호출
   - FCM 서버로 메시지 전송
   - 성공 시 응답 ID 반환
   - 실패 시 예외 발생 (토큰 무효, 네트워크 오류 등)

**Repository 인터페이스**:
```java
@Mapper
public interface PushTokenRepository {
    int saveOrUpdateToken(PushTokenDto dto);
    PushTokenDto selectTokenByMemberId(String memberId);
    List<String> selectTokensForApprovalTarget(String targetMemberId);
}
```

#### 1.2.6 단계 6: FCM 서버 전송

**동작 내용**:
- Firebase Cloud Messaging 서버가 메시지를 받음
- FCM 토큰을 기반으로 대상 디바이스 식별
- 해당 디바이스의 브라우저/앱으로 메시지 라우팅
- 디바이스가 오프라인이면 큐에 저장 후 온라인 시 전송

#### 1.2.7 단계 7: 클라이언트 메시지 수신

##### A. 포그라운드 수신 (앱이 열려있을 때)

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
- Firebase Messaging SDK의 `onMessage` 이벤트 리스너
- 앱이 포그라운드에 있을 때 메시지 수신
- `payload.data`에서 `title`, `body` 추출
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

##### B. 백그라운드 수신 (앱이 닫혀있을 때)

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

##### C. 알림 클릭 이벤트

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
- 사용자가 알림을 클릭하면 `notificationclick` 이벤트 발생
- 알림 닫기
- 알림의 `data.link`에서 이동할 URL 추출
- 열려있는 탭 중 해당 URL이 포함된 탭이 있으면 포커스
- 없으면 새 탭으로 URL 열기

---

## 2. 전반적인 푸시 알림 동작 방식

### 2.0 FCM 토큰 생성 위치 및 과정

**핵심 답변: FCM 토큰은 클라이언트 측(브라우저)에서 Firebase Messaging SDK를 통해 생성됩니다.**

#### 2.0.1 생성 위치

**파일**: `src/main/webapp/static-global/push_init.js` (56-59줄)

**생성 코드**:
```javascript
return messaging.getToken({
    serviceWorkerRegistration: registration,
    vapidKey: 'BEcW_Ep6uWwbA64qsiU64TLQ_wj6AXgJbcjszjLdVOIG1gZ8rVM2XCSHNG8A8M4l5v6CZVfLdu_EBqKxjMlyj7c'
});
```

#### 2.0.2 생성 과정 상세

**1. Firebase Messaging 객체 생성**:
```javascript
// push_init.js (23-24줄)
firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();
```
- Firebase SDK 초기화 후 `messaging` 객체 생성
- 이 객체가 FCM 토큰 발급을 담당

**2. 토큰 발급 호출**:
```javascript
messaging.getToken({
    serviceWorkerRegistration: registration,
    vapidKey: 'VAPID_KEY'
})
```
- `messaging.getToken()` 메서드 호출
- **파라미터**:
  - `serviceWorkerRegistration`: 등록된 Service Worker (백그라운드 메시지 수신용)
  - `vapidKey`: VAPID (Voluntary Application Server Identification) 키

**3. 실제 생성 위치**:
- **클라이언트 측**: `messaging.getToken()` 호출
- **Firebase 서버**: 실제 토큰 생성 및 발급
  - Firebase Cloud Messaging 서버가 브라우저/디바이스를 식별하여 고유한 토큰 생성
  - 토큰은 해당 브라우저 인스턴스와 Firebase 프로젝트를 연결

**4. 토큰 형식**:
- 긴 문자열 형태 (예: `dK3jF8...` 형태의 약 150자 정도)
- Firebase 프로젝트와 디바이스/브라우저 조합에 대한 고유 식별자

#### 2.0.3 생성 시점

**자동 생성** (페이지 로드 시):
```javascript
// push_init.js (142-144줄)
if (typeof window.requestPermissionAndGetToken === 'function') {
    window.requestPermissionAndGetToken();
}
```

**수동 생성** (필요 시):
```javascript
// 전역 함수로 노출되어 있어서 어디서든 호출 가능
window.requestPermissionAndGetToken();
```

#### 2.0.4 생성 전제 조건

1. **브라우저 지원**:
   - Service Worker 지원 (`navigator.serviceWorker`)
   - Push API 지원 (`window.PushManager`)

2. **알림 권한**:
   - 사용자가 알림 권한을 허용해야 함
   - `Notification.requestPermission()` → `'granted'`

3. **Service Worker 등록**:
   - `/firebase-messaging-sw.js` 파일이 Service Worker로 등록되어야 함

4. **Firebase 초기화**:
   - Firebase SDK가 로드되고 초기화되어야 함
   - 올바른 Firebase Config 필요

#### 2.0.5 토큰 생성 플로우 다이어그램

```
[페이지 로드]
    ↓
[Firebase 초기화]
    ↓
[requestPermissionAndGetToken() 호출]
    ↓
[브라우저 지원 확인]
    ├─ 미지원 → 종료
    └─ 지원 → 다음 단계
        ↓
[알림 권한 요청]
    ├─ 거부 → 종료
    └─ 허용 → 다음 단계
        ↓
[Service Worker 등록]
    ├─ 실패 → 종료
    └─ 성공 → 다음 단계
        ↓
[messaging.getToken() 호출]
    ↓
[Firebase 서버와 통신]
    ↓
[FCM 토큰 생성 및 반환]
    ↓
[클라이언트: 토큰 수신]
    ↓
[서버에 토큰 전송 (/api/push/registerToken)]
    ↓
[DB에 토큰 저장]
```

#### 2.0.6 중요 사항

1. **서버에서 생성하지 않음**:
   - FCM 토큰은 서버에서 생성할 수 없음
   - 반드시 클라이언트(브라우저)에서 Firebase SDK를 통해 생성

2. **토큰의 고유성**:
   - 각 브라우저/디바이스마다 고유한 토큰
   - 같은 사용자라도 다른 브라우저/디바이스에서는 다른 토큰

3. **토큰 갱신**:
   - 브라우저 데이터 삭제, 앱 재설치 시 토큰 변경 가능
   - `messaging.getToken()`을 다시 호출하면 새 토큰 발급 가능

4. **토큰 저장**:
   - 클라이언트: 브라우저에 자동 저장 (IndexedDB 등)
   - 서버: `/api/push/registerToken` API를 통해 DB에 저장

### 2.1 푸시 알림 아키텍처

```
┌─────────────────┐
│   클라이언트     │
│  (브라우저/PWA)  │
│                 │
│  ┌───────────┐  │
│  │Firebase   │  │
│  │Messaging  │  │
│  │SDK        │  │
│  └─────┬─────┘  │
│        │        │
│  ┌─────▼─────┐  │
│  │Service    │  │
│  │Worker     │  │
│  └───────────┘  │
└────────┬────────┘
         │
         │ FCM 토큰 등록
         │
┌────────▼────────┐
│   서버          │
│                 │
│  ┌───────────┐  │
│  │PushService│  │
│  └─────┬─────┘  │
│        │        │
│  ┌─────▼─────┐  │
│  │Firebase   │  │
│  │Admin SDK  │  │
│  └─────┬─────┘  │
└────────┼────────┘
         │
         │ FCM 메시지 전송
         │
┌────────▼────────┐
│  Firebase Cloud │
│  Messaging      │
│  (FCM 서버)     │
└────────┬────────┘
         │
         │ 메시지 라우팅
         │
┌────────▼────────┐
│   클라이언트     │
│  (디바이스)     │
└─────────────────┘
```

### 2.2 초기화 과정

#### 2.2.1 페이지 로드 시 초기화

**파일 위치**: `src/main/webapp/static-global/push_init.js` (1-137줄)

**1. Firebase 설정 및 초기화**:
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

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();
```

**2. 자동 토큰 요청**:
```javascript
$(document).ready(function() {
    if (typeof window.requestPermissionAndGetToken === 'function') {
        window.requestPermissionAndGetToken();
    }
});
```

#### 2.2.2 토큰 발급 및 등록 과정

**함수**: `window.requestPermissionAndGetToken()` (30-72줄)

**단계별 동작**:

1. **브라우저 지원 확인**:
```javascript
if (!('serviceWorker' in navigator)) {
    console.warn('Service Worker not supported');
    return;
}

if (!('PushManager' in window)) {
    console.warn('Push API not supported');
    return;
}
```

2. **알림 권한 요청**:
```javascript
Notification.requestPermission()
    .then(permission => {
        if (permission !== 'granted') {
            console.warn('Notification permission denied');
            return;
        }
        console.log('Notification permission granted');
        // 다음 단계로 진행
    });
```
- 사용자에게 알림 권한 요청
- `granted`: 허용
- `denied`: 거부
- `default`: 아직 결정하지 않음

3. **Service Worker 등록**:
```javascript
return navigator.serviceWorker.register('/firebase-messaging-sw.js');
```
- `firebase-messaging-sw.js` 파일을 Service Worker로 등록
- 백그라운드 메시지 수신을 위해 필요

4. **FCM 토큰 발급**:
```javascript
return messaging.getToken({
    serviceWorkerRegistration: registration,
    vapidKey: 'BEcW_Ep6uWwbA64qsiU64TLQ_wj6AXgJbcjszjLdVOIG1gZ8rVM2XCSHNG8A8M4l5v6CZVfLdu_EBqKxjMlyj7c'
});
```
- VAPID 키를 사용하여 FCM 토큰 발급
- 이 토큰은 해당 디바이스/브라우저를 고유하게 식별

5. **서버에 토큰 전송**:
```javascript
.then(token => {
    if (token) {
        console.log('FCM Token:', token);
        sendTokenToServer(token);
    }
});
```

**서버 전송 함수** (111-127줄):
```javascript
window.sendTokenToServer = function(fcmToken) {
    $.ajax({
        url: '/api/push/registerToken',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            memberId: MEMBER_ID,
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

#### 2.2.3 서버 토큰 저장

**API 엔드포인트**: `POST /api/push/registerToken`

**컨트롤러** (`PushRestController.java` 27-32줄):
```java
@PostMapping("/registerToken")
public @ResponseBody ResponseEntity<String> registerToken(@RequestBody PushTokenDto requestDto) {
    pushService.saveOrUpdateToken(requestDto.getMemberId(), requestDto.getFcmToken());
    return ResponseEntity.ok("Token registered successfully.");
}
```

**서비스** (`PushService.java` 29-34줄):
```java
public void saveOrUpdateToken(String memberId, String fcmToken) {
    PushTokenDto dto = new PushTokenDto();
    dto.setMemberId(memberId);
    dto.setFcmToken(fcmToken);
    pushTokenRepository.saveOrUpdateToken(dto);
}
```

- 사용자 ID와 FCM 토큰을 DB에 저장 또는 업데이트
- 동일 사용자가 여러 디바이스에서 로그인할 경우 최신 토큰으로 업데이트

### 2.3 메시지 전송 플로우

#### 2.3.1 서버에서 메시지 전송 트리거

**트리거 시점 예시**:
- 회의실 예약 시간 30분 전
- 결재 요청 발생
- 시스템 알림
- 사용자 정의 이벤트

**전송 과정**:
1. 비즈니스 로직에서 `PushService.triggerApprovalPushNotification()` 호출
2. 대상 사용자 ID로 FCM 토큰 조회
3. FCM 메시지 구성
4. Firebase Admin SDK로 전송

#### 2.3.2 Firebase Admin SDK 설정

**설정 파일**: `src/main/resources/application.yml` (40-44줄)
```yaml
firebase:
  admin:
    sdk:
      path: "classpath:serviceAccountKey.json"
```

- Firebase 프로젝트의 서비스 계정 키 파일 경로
- 서버에서 FCM에 인증하기 위해 필요

### 2.4 메시지 수신 및 처리

#### 2.4.1 포그라운드 수신

**조건**: 앱이 열려있고 활성 탭에 있을 때

**처리 방식**:
- `messaging.onMessage()` 이벤트 리스너로 수신
- JavaScript로 직접 처리
- 토스트 메시지 또는 인앱 알림으로 표시

**장점**:
- 사용자가 앱을 사용 중일 때 즉시 알림
- 커스텀 UI로 표시 가능

#### 2.4.2 백그라운드 수신

**조건**: 앱이 닫혀있거나 다른 탭에 있을 때

**처리 방식**:
- Service Worker의 `onBackgroundMessage()`로 수신
- 브라우저 네이티브 알림 API 사용
- 시스템 알림으로 표시

**장점**:
- 앱이 실행되지 않아도 알림 수신
- 사용자 주의를 끌 수 있음

### 2.5 알림 클릭 처리

**이벤트**: `notificationclick`

**처리 로직**:
1. 알림 닫기
2. 알림의 `data.link`에서 URL 추출
3. 열려있는 탭 확인
4. 해당 URL이 포함된 탭이 있으면 포커스
5. 없으면 새 탭으로 URL 열기

**사용자 경험**:
- 알림 클릭 시 관련 페이지로 자동 이동
- 중복 탭 방지

---

## 3. CSRF 필터 동작 방식

### 3.1 CSRF 공격이란?

**Cross-Site Request Forgery (CSRF)**:
- 사용자가 의도하지 않은 요청을 강제로 실행시키는 공격
- 예: 사용자가 로그인한 상태에서 악성 사이트가 사용자 모르게 서버에 요청 전송

**공격 시나리오**:
1. 사용자가 `example.com`에 로그인 (세션 쿠키 저장)
2. 사용자가 악성 사이트 `evil.com` 방문
3. `evil.com`이 `example.com`에 POST 요청 전송 (세션 쿠키 자동 포함)
4. 사용자 모르게 `example.com`에서 악의적인 작업 실행

### 3.2 CSRF 방어 메커니즘

#### 3.2.1 토큰 기반 방어

**원리**:
- 사용자 세션마다 고유한 CSRF 토큰 생성
- 모든 상태 변경 요청(POST, PUT, DELETE 등)에 토큰 포함 필수
- 서버에서 토큰 검증 후 요청 처리

**왜 효과적인가?**:
- 악성 사이트는 사용자의 CSRF 토큰을 알 수 없음
- Same-Origin Policy로 인해 다른 도메인에서 토큰 읽기 불가
- 토큰이 없거나 잘못된 토큰이면 요청 거부

### 3.3 필터 등록 및 적용 범위

#### 3.3.1 필터 등록

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

#### 3.3.2 적용 범위

**모든 HTTP 요청에 적용**:
- 정적 리소스 요청 포함
- API 요청 포함
- 페이지 요청 포함

**예외 처리**:
- 세션이 없는 경우 검증 생략
- 특정 HTTP 메서드는 검증 생략 (아래 참조)

### 3.4 필터 동작 흐름 상세

#### 3.4.1 전체 흐름도

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

#### 3.4.2 단계별 상세 설명

**1단계: 요청 수신 및 세션 확인**

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

```java
boolean isValid = vo.getCsrfToken().equals(headerToken) || 
                  vo.getCsrfToken().equals(paramToken);
if (isValid) {
    chain.doFilter(request, response);
} else {
    log.error("CSRF Token Error: {}---{}---{}", 
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

### 3.5 CSRF 토큰 생성

#### 3.5.1 생성 시점

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

#### 3.5.2 토큰 저장

**저장 위치**:
- 서버: `HttpSession`의 `SessionVO.csrfToken`
- 클라이언트: 메타 태그 또는 폼 hidden input

### 3.6 클라이언트에서 토큰 전송

#### 3.6.1 AJAX 요청 (자동)

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
1. 페이지 로드 시 메타 태그에서 토큰과 헤더 이름 읽기
2. `ajaxSend` 이벤트 리스너 등록
3. 모든 AJAX 요청 전에 실행
4. POST/PUT/DELETE/PATCH 메서드인 경우 자동으로 `X-CSRF-TOKEN` 헤더 추가

**장점**:
- 개발자가 수동으로 토큰을 추가할 필요 없음
- 모든 AJAX 요청에 일관되게 적용
- 실수로 토큰을 빼먹는 경우 방지

#### 3.6.2 폼 제출 (수동)

**파일 위치**: `src/main/webapp/WEB-INF/views/include/csrf.jsp`

**코드**:
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty sessionScope.CSRF_TOKEN}">
    <input type="hidden" name="csrfToken" value="<c:out value="${sessionScope.CSRF_TOKEN}" />" />
</c:if>
```

**사용 방법**:
```jsp
<form method="post" action="/some-action">
    <jsp:include page="/WEB-INF/views/include/csrf.jsp" />
    <!-- 다른 폼 필드들 -->
    <button type="submit">제출</button>
</form>
```

**동작**:
- 폼에 hidden input으로 `csrfToken` 추가
- 폼 제출 시 파라미터로 전송
- 필터에서 `request.getParameter("csrfToken")`으로 읽음

### 3.7 보안 특징 및 고려사항

#### 3.7.1 보안 특징

1. **세션 기반 토큰**:
   - 각 사용자 세션마다 고유한 토큰
   - 세션이 만료되면 토큰도 무효화

2. **안전한 메서드 제외**:
   - GET, HEAD는 검증 생략
   - 상태 변경이 없는 요청은 CSRF 공격 대상이 아님

3. **이중 검증 경로**:
   - 헤더 또는 파라미터 모두 허용
   - 다양한 클라이언트 환경 지원

4. **CORS 대응**:
   - OPTIONS 요청은 검증 생략
   - Preflight 요청 정상 처리

#### 3.7.2 주의사항

1. **세션 없는 요청**:
   - 세션이 없으면 검증 생략
   - 로그인 전 요청은 CSRF 보호 없음
   - **이유**: 로그인하지 않은 사용자는 공격 대상이 아님

2. **API 경로**:
   - `/api/**` 경로도 필터 적용
   - AJAX 요청 시 토큰 포함 필수

3. **토큰 노출 위험**:
   - 메타 태그에 토큰이 노출됨
   - 하지만 Same-Origin Policy로 다른 도메인에서 읽기 불가
   - XSS 공격에 취약할 수 있으므로 XSS 방어도 필요

4. **토큰 갱신**:
   - 현재 구현에서는 로그인 시에만 토큰 생성
   - 세션 유지 중 토큰은 변경되지 않음
   - 보안 강화를 위해 주기적 토큰 갱신 고려 가능

### 3.8 필터 체인에서의 위치

**필터 실행 순서**:
1. Spring Security 필터 (있는 경우)
2. **CsrfFilter** (현재 필터)
3. 기타 커스텀 필터
4. DispatcherServlet
5. 컨트롤러

**중요**:
- CSRF 필터는 인증 필터 이후에 실행되어야 함
- 세션 정보가 필요하므로 세션 필터 이후에 실행

### 3.9 디버깅 및 문제 해결

#### 3.9.1 일반적인 오류

**403 Forbidden "Invalid CSRF Token"**:
- 원인:
  - 토큰이 요청에 포함되지 않음
  - 토큰이 잘못됨
  - 세션의 토큰과 불일치
- 해결:
  - 브라우저 개발자 도구에서 요청 헤더 확인
  - 세션의 토큰과 비교
  - 메타 태그에 토큰이 올바르게 설정되었는지 확인

**토큰이 null인 경우**:
- 원인:
  - 로그인 시 토큰 생성 실패
  - 세션에 `sessionVO`가 없음
- 해결:
  - 로그인 로직 확인
  - 세션 저장 로직 확인

#### 3.9.2 로그 확인

**에러 로그 형식**:
```
@@@@@@@@@@@@@@@@@@@@@@@@@@@ ERROR @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
{URI}---{headerToken}---{paramToken}
```

- URI: 요청한 경로
- headerToken: 헤더에서 추출한 토큰 (없으면 null)
- paramToken: 파라미터에서 추출한 토큰 (없으면 null)

---

## 4. 통합 동작 시나리오

### 4.1 PUSH 테스트 버튼 클릭 시 전체 플로우

```
1. 사용자가 "PUSH 테스트" 버튼 클릭
   ↓
2. JavaScript 이벤트 핸들러 실행
   - targetId 설정: 'jhchoi@dbinc.co.kr'
   - JSON 파라미터 생성
   ↓
3. ajax.json() 함수 호출
   - currentMenuId 자동 추가
   - JSON 문자열로 변환
   ↓
4. jQuery ajaxSend 이벤트 발생
   - POST 요청이므로 X-CSRF-TOKEN 헤더 자동 추가
   - 메타 태그에서 토큰 읽기
   ↓
5. HTTP POST 요청 전송
   - URL: /api/push/test
   - Headers: X-CSRF-TOKEN: {토큰}
   - Body: {"targetId": "jhchoi@dbinc.co.kr", "currentMenuId": "..."}
   ↓
6. 서버: CsrfFilter 실행
   - 세션 확인: 있음
   - HTTP 메서드: POST → 토큰 검증 필요
   - 헤더에서 토큰 추출
   - 세션 토큰과 비교: 일치
   - 통과
   ↓
7. 서버: PushRestController 수신
   - @PostMapping("/test") 매핑
   - @RequestBody로 JSON 파싱
   - PushTokenDto 객체 생성
   ↓
8. 서버: PushService.triggerApprovalPushNotification() 호출
   - targetId로 FCM 토큰 조회
   - FCM 메시지 구성
   - Firebase Admin SDK로 전송
   ↓
9. Firebase Cloud Messaging 서버
   - 메시지 수신
   - FCM 토큰으로 디바이스 식별
   - 메시지 라우팅
   ↓
10. 클라이언트: 메시지 수신
    - 포그라운드: onMessage → 토스트 표시
    - 백그라운드: Service Worker → 브라우저 알림
    ↓
11. 사용자가 알림 클릭 (백그라운드인 경우)
    - notificationclick 이벤트
    - /reserve_main 페이지로 이동
```

### 4.2 CSRF 필터가 없는 경우의 위험

**시나리오**: 악성 사이트가 사용자 모르게 PUSH 테스트 요청 전송

```
1. 사용자가 로그인 상태로 example.com 사용 중
2. 사용자가 새 탭에서 evil.com 방문
3. evil.com의 JavaScript 코드:
   fetch('https://example.com/api/push/test', {
       method: 'POST',
       headers: {'Content-Type': 'application/json'},
       body: JSON.stringify({targetId: 'victim@example.com'})
   });
4. 브라우저가 자동으로 쿠키(세션) 포함
5. 서버가 요청 처리
6. 사용자 모르게 PUSH 알림 전송
```

**CSRF 필터가 있으면**:
- 요청에 CSRF 토큰이 없음
- 필터가 403 Forbidden 반환
- 공격 차단

---

## 5. 파일 구조 요약

### 5.1 PUSH 알림 관련 파일

```
프론트엔드:
├── src/main/webapp/WEB-INF/views/mobile/mobile_main.jsp
│   └── PUSH 테스트 버튼 및 클릭 이벤트
├── src/main/webapp/static-global/push_init.js
│   └── Firebase 초기화, 토큰 발급, 포그라운드 메시지 수신
├── src/main/webapp/static-global/firebase-messaging-sw.js
│   └── Service Worker, 백그라운드 메시지 수신, 알림 클릭 처리
└── src/main/webapp/WEB-INF/views/include/mobileHeader.jsp
    └── CSRF 토큰 메타 태그 및 AJAX 자동 헤더 추가

서버:
├── src/main/java/kr/co/dbinc/smartofficepwa/web/controller/push/PushRestController.java
│   └── /api/push/test, /api/push/registerToken 엔드포인트
├── src/main/java/kr/co/dbinc/smartofficepwa/web/service/push/PushService.java
│   └── FCM 토큰 저장, 푸시 알림 전송 로직
├── src/main/java/kr/co/dbinc/smartofficepwa/web/repository/push/PushTokenRepository.java
│   └── FCM 토큰 DB 조회 인터페이스
└── src/main/java/kr/co/dbinc/smartofficepwa/web/dto/push/PushTokenDto.java
    └── FCM 토큰 DTO

설정:
└── src/main/resources/application.yml
    └── Firebase Admin SDK 경로 설정
```

### 5.2 CSRF 필터 관련 파일

```
서버:
├── src/main/java/kr/co/dbinc/smartofficepwa/config/CsrfFilter.java
│   └── CSRF 필터 구현
└── src/main/java/kr/co/dbinc/smartofficepwa/web/controller/login/LoginViewController.java
    └── 로그인 시 CSRF 토큰 생성

프론트엔드:
├── src/main/webapp/WEB-INF/views/include/mobileHeader.jsp
│   └── CSRF 토큰 메타 태그, AJAX 자동 헤더 추가
└── src/main/webapp/WEB-INF/views/include/csrf.jsp
    └── 폼용 CSRF 토큰 hidden input
```

---

## 6. 참고 사항

### 6.1 Firebase 설정

- **Firebase 프로젝트**: `mobilepwa-b4c16`
- **VAPID 키**: `BEcW_Ep6uWwbA64qsiU64TLQ_wj6AXgJbcjszjLdVOIG1gZ8rVM2XCSHNG8A8M4l5v6CZVfLdu_EBqKxjMlyj7c`
- **Service Account Key**: `classpath:serviceAccountKey.json`

### 6.2 보안 고려사항

1. **VAPID 키 노출**:
   - 현재 코드에 VAPID 키가 하드코딩되어 있음
   - 공개되어도 문제없지만, 환경 변수로 관리 권장

2. **Firebase 설정 노출**:
   - Firebase Config가 클라이언트에 노출됨
   - 정상적인 동작 (클라이언트에서 사용해야 함)

3. **Service Account Key**:
   - 서버에만 존재해야 함
   - Git에 커밋하지 않도록 주의

4. **CSRF 토큰**:
   - 세션 기반이므로 XSS 공격에 취약할 수 있음
   - XSS 방어도 함께 구현 필요

### 6.3 개선 제안

1. **PUSH 테스트**:
   - 현재 `targetId`가 하드코딩되어 있음
   - 현재 로그인한 사용자로 자동 설정하거나 입력 필드 추가

2. **에러 처리**:
   - FCM 전송 실패 시 상세한 에러 로깅
   - 무효한 토큰 자동 삭제 로직 추가

3. **CSRF 토큰**:
   - 주기적 토큰 갱신 기능
   - 토큰 만료 시간 설정

---

## 7. 결론

이 문서는 PUSH 알림 시스템과 CSRF 필터의 상세한 동작 방식을 설명합니다. 

**PUSH 알림**은 Firebase Cloud Messaging을 활용하여 실시간 알림을 제공하며, 클라이언트와 서버 간의 협업으로 구현됩니다.

**CSRF 필터**는 토큰 기반 방어 메커니즘으로 사용자를 보호하며, 세션 기반으로 동작합니다.

두 시스템이 함께 작동하여 안전하고 사용자 친화적인 웹 애플리케이션을 제공합니다.




