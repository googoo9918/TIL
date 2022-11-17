### 목차
- [HTTP 헤더 개요](#http-헤더-개요)
  - [HTTP 헤더](#http-헤더)
  - [HTTP BODY](#http-body)
- [표현](#표현)
  - [Content-Type](#content-type)
  - [Content-Encoding](#content-encoding)
  - [Content-Language](#content-language)
  - [Content-Length](#content-length)
- [콘텐츠 협상](#콘텐츠-협상)
  - [협상과 우선순위1](#협상과-우선순위1)
  - [협상과 우선순위2](#협상과-우선순위2)
  - [협상과 우선순위3](#협상과-우선순위3)
- [전송 방식](#전송-방식)
- [일반 정보](#일반-정보)
  - [From](#from)
  - [Referer](#referer)
  - [User-Agent](#user-agent)
  - [Server](#server)
  - [Date](#date)
- [특별한 정보](#특별한-정보)
  - [Host](#host)
  - [Location](#location)
  - [Allow](#allow)
  - [Retry-After](#retry-after)
  - [인증](#인증)
- [쿠키](#쿠키)
  - [쿠키 미사용 시 예시](#쿠키-미사용-시-예시)
  - [Stateless](#stateless)
  - [쿠키 사용 시 예시](#쿠키-사용-시-예시)
  - [정리](#정리)
  - [생명주기](#생명주기)
  - [도메인](#도메인)
  - [경로](#경로)
  - [보안](#보안)

## HTTP 헤더 개요
### HTTP 헤더
- header-field = field-name ":" OWS field-value OWS (OWS: 띄어쓰기 허용)
  - filed-name과 ":" 사이에 띄어쓰기 없음 주의!
- field-name은 대소문자 구분 없음
  - ![image](https://user-images.githubusercontent.com/102513932/198533689-d3639943-358f-4b21-812f-c33f6d71a37b.png)
- 용도
  - HTTP 전송에 필요한 모든 부가정보
    - ex) 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트, 서버 정보, 캐시 관리 정보
  - 표준 헤더 상당히 많음
  - 필요 시 임의의 헤더 추가 가능
    - ex) helloworld: hihi
- 분류 : RFC2616(과거)
  - ![image](https://user-images.githubusercontent.com/102513932/198534091-d71fc881-f3f5-4d63-aeae-95354f8bff94.png)
  - 헤더 분류
    - General 헤더 : 메시지 전체에 적용되는 정보
      - ex) Connection: close
    - Request 헤더 : 요청 정보
      - ex) User-Agent: Mozilla/5.0(Macintosh;..)
    - Resoponse 헤더 : 응답 정보
      - ex) Server: Apache
    - Entity 헤더 : 엔티티 바디 정보
      - ex) Content-Type: text/html, Content-Length:3423
### HTTP BODY
- message body : RFC2616(과거)
  - ![image](https://user-images.githubusercontent.com/102513932/198534872-4324bea0-33c1-4dda-a00c-836b929d537f.png)
    - 메시지 본문(message body)는 엔티티 본문(entity body)을 전달하는데 사용
    - 엔티티 본문은 요청이나 응답에서 전달할 실제 데이터
    - 엔티티 헤더는 엔티티 본문의 데이터를 해석할 수 있는 정보 제공
      - 데이터 유형(html,json), 데이터 길이, 압축 정보 등등
- HTTP 표준  RFC2616 -> RFC7230~7235로 변경
- RFC723x 변화
  - 엔티티(Entity) -> 표현(Representation)
  - Representation = representation Metadata + Representation Data
  - 표현 = 표현 메타데이터 + 표현 데이터
- message body : RFC7230(최신)
  - ![image](https://user-images.githubusercontent.com/102513932/198536851-4c5ccf76-1531-4d7e-bb51-357f9f47a6de.png)
  - 메시지 본문을 통해 표현 데이터 전달
  - 메시지 본문 = 페이로드(payload)
  - 표현은 요청이나 응답에서 전달할 실제 데이터
  - 표현 헤더는 표현 데이터를 해석할 수 있는 정보 제공
    - 데이터 유형(html, json), 데이터 길이, 압축 정보 등등
  - 참고: 표현 헤더는 표현 메타데이터와, 페이로드 메시지를 구분해야 하지만 여기서는 생략함

## 표현
- Content-Type : 표현 데이터의 형식
- Content-Encoding: 표현 데이터의 압축 방식
- Content-Language: 표현 데이터의 자연 언어
- Content-Length: 표현 데이터의 길이
- 표현 헤더는 전송, 응답 둘 다 사용
### Content-Type
- 표현 데이터의 형식 설명
  - 미디어 타입, 문자 인코딩
  - ex) text/html;charset=UTF-8
  - ex) application/json
  - ex) image/png
### Content-Encoding
- 표현 데이터 인코딩
  - 표현 데이터를 압축하기 위해 사용
  - 데이터를 전달하는 곳에서 압축 후 인코딩 헤더 추가
  - 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축 해제
  - ex)
    - gzip 
    - deflate
    - identity
### Content-Language
- 표현 데이터의 자연 언어
  - 표현 데이터의 자연 언어를 표현
  - ex)
    - ko
    - en
    - en-US
### Content-Length
- 표현 데이터의 길이
  - 바이트 단위
  - Transfer-Encoding(전송 코딩)을 사용하면 Content-Length를 사용하면 안됨

## 콘텐츠 협상
- 클라이언트가 선호하는 표현 요청
  - Accept: 클라이언트가 선호하는 미디어 타입 전달
  - Accept-Charset: 클라이언트가 선호하는 문자 인코딩
  - Accept-Encoding: 클라이언트가 선호하는 압축 인코딩
  - Accept-Language: 클라이언트가 선호하는 자연 언어
- 협상 헤더는 요청시에만 사용
- 서버에서 무엇을 제공하는지 미리 알고 있어야함!
- Accept-Language 적용 전
  - ![image](https://user-images.githubusercontent.com/102513932/198540079-53fbf91b-d628-4948-8ca2-73b7f7a8873a.png)
- Accept-Language 적용 후
  - ![image](https://user-images.githubusercontent.com/102513932/198540120-bd3a1529-f865-443f-8137-a552346e11a8.png)
- Accept-Language 복잡한 예시
  - ![image](https://user-images.githubusercontent.com/102513932/198540403-56beda8d-80fa-420e-8064-4b1278cda8e5.png)
    - 서버에서 한국어는 지원 안함
      - 적어도 독일어가 아닌 영어로 받고 싶음
      - 우선순위 필요!

### 협상과 우선순위1
- Quality Values(q)
  - ![image](https://user-images.githubusercontent.com/102513932/198541026-4a96c8c8-3a83-44d1-bd4c-4aa3bedb47ef.png)
  - Quality Values(q) 값 사용
  - 0~1, 클수록 높은 우선순위
  - 생략하면 1
  - `Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7`
    - 1. ko-KR;q=1 (q 생략)
    - 2. ko;q=0.9
    - 3. en-US;q=0.8
    - 4. en:q=0.7
- 예시
  - ![image](https://user-images.githubusercontent.com/102513932/198541191-37c860da-2252-4d4a-9d92-ba3e76603b41.png)
### 협상과 우선순위2
- Quality Values(q)
  - ![image](https://user-images.githubusercontent.com/102513932/198541315-5e18db6f-36dc-4ad2-b8bb-0e9d908b0009.png)
  - 구체적인 것이 우선함
  - `Accept: text/*,text/plain,text/plain;format=flower,*/*`
    - 1. text/plain;format=flowed
    - 2. text/plain
    - 3. text/*
    - 4. */*
### 협상과 우선순위3
- Quality Values(q)
  - 구체적인 것을 기준으로 미디어 타입을 맞춤
    - `Accept: text/*;q=0.3, text/html;q=0.7, text/html;level=1, text/html;level=2;q=0.4, */*;q=0.5`
    - ![image](https://user-images.githubusercontent.com/102513932/198542219-a5edae49-6f23-4352-a184-44d07e108a71.png)
## 전송 방식
- Transfer-Encoding
- Range, Content-Range
- 단순 전송
  - Content-Length
  - ![image](https://user-images.githubusercontent.com/102513932/198542607-82812131-6103-4e07-9870-b4c09313a4ad.png)
- 압축 전송
  - Content-Encoding
  - ![image](https://user-images.githubusercontent.com/102513932/198542599-79e0bef2-4919-4d9f-9da8-2e910e143630.png)
- 분할 전송
  - Transfer-Encoding
  - ![image](https://user-images.githubusercontent.com/102513932/198542593-23513bf8-0fbb-46be-a9e7-c3fab8fa55f4.png)
    - 바이트 단위로 나눠서 보낼 수 있음
      - 5 Byte Hello / 5 Byte World / 0 Byte \r\n 
    - 분할 전송 시에는 Content-Length 내용이 들어가면 안됨
      - Content-Length가 처음에 예상이 안됨
      - chunked 단위 마다 바이트 정보가 들어가 있음
- 범위 전송
  - Range, Content-Range
  - ![image](https://user-images.githubusercontent.com/102513932/198542587-efed75c8-949a-4e18-bf08-cfabf1f792aa.png)
    - 범위를 지정해서 요청 가능

## 일반 정보
### From
- 유저 에이전트의 이메일 정보
  - 일반적으로 잘 사용되지 않음
  - 검색 엔진 같은 곳에서 주로 사용
  - 요청에서 사용
### Referer
- 이전 웹 페이지 주소
  - 현재 요청된 페이지의 이전 웹 페이지 주소
  - A -> B로 이동하는 경우 B를 요청할 때 Referer: A를 포함해서 요청
  - Referer를 사용해서 유입 경로 분석 가능
  - 요청에서 사용
  - 참고: referer는 단어 referrer의 오타
### User-Agent
- 유저 에이전트 애플리케이션 정보
  - `user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) ApplewWebKit/537.36 (KHTML, like Gecko)Chrome/86.0.4240.183 Safari/537.36`
  - 클라이언트의 애플리케이션 정보(웹 브라우저 정보, 등등)
  - 통계 정보
  - 어떤 종류의 브라우저에서 장애가 발생하는지 파악 가능
  - 요청에서 사용
### Server
- 요청을 처리하는 ORIGIN 서버의 소프트웨어 정보
  - ORIGIN 서버는 중간에 있는 캐시 서버가 아닌, 요청이 도달한 마지막 서버를 의미함
  - Server: Apache/2.2.22(Debian)
  - server: nginx
  - 응답에서 사용
### Date
- 메시지가 발생한 날짜와 시간
  - Date: Tue, 15 Nov 1994 08:12:31 GMT
  - 응답에서 사용
## 특별한 정보
- Host: 요청한 호스트 정보(도메인)
- Location: 페이지 리다이렉션
- Allow: 허용 가능한 HTTP 메서드
- Retry-After: 유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간
### Host
- 요청한 호스트 정보(도메인)
  - ![image](https://user-images.githubusercontent.com/102513932/198544206-e472161c-2eb9-403d-a0f8-3182b2800072.png)
  - 요청에서 사용
  - 필수
  - 하나의 서버가 여러 도메인을 처리해야 할 때
  - 하나의 IP 주소에 여러 도메인이 적용되어 있을 때
- ![image](https://user-images.githubusercontent.com/102513932/198545925-3373bad4-0693-4a73-a639-eaf350feb8a0.png) 
  - 가상 호스트를 통해 여러 도메인을 한 번에 처리할 수 있는 서버가 있다고 가정, 이 서버에서는 실제 애플리케이션이 여러개 구동될 수 있음
  - `GET /hello HTTP/1.1` 전송 시 서버에서 알아듣지 못함
  - Get /hello HTTP/1.1 <br> Host: aaa.com 으로 처리

### Location
- 페이지 리다이렉션
  - 웹 브라우저는 3xx 응답 결과에 Location 헤더가 있으면, Location 위치로 자동 이동(리다이렉트)
  - 응답코드 3xx에서 설명
  - 201(Created): Location 값은 요청에 의해 생성된 리소스 URI
  - 3xx (Redirection): Location 값은 요청을 자동으로 리디렉션하기 위한 대상 리소스를 가리킴
### Allow
- 허용 가능한 HTTP 메서드
  - URL 경로는 있는데, 허용하지 않는 메서드가 존재하는 경우
  - 405(Method Not Allowed)에서 응답에 포함해야함
  - Allow: GET, HEAD, PUT
    - POST 메서드 사용 불가
### Retry-After
- 유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간
  - 503(Service Unavailable): 서비스가 언제까지 불능인지 알려줄 수 있음
  - Retry-After: Fri, 31 Dec 1999 23:59:59 GMT(날짜 표기)
  - Retry-After: 120 (초단위 표기)
### 인증
- Authorization: 클라이언트 인증 정보를 서버에 전달
  - Authorization: Basic xxxxxxxxxxxxx
    - 인증하는 방법은 여러 매커니즘 존재, 다 다른 형태로 나타남
- WWW-Authenticate: 리소스 접근시 필요한 인증 방법 정의
  - 리소스 접근시 필요한 인증 방법 정의
  - 401 Unauthorized 응답과 함께 사용
    - 인증이 제대로 안됐을 시
  - `WWW-Authenticate: Newauth realm="apps", type=1. title="Login to \"apps\"", Basic realm="simple"`
## 쿠키
- Set-Cookie: 서버에서 클라이언트로 쿠키 전달(응답)
- Cookie: 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달
### 쿠키 미사용 시 예시
- 처음 welcome 페이지 접근
  - ![image](https://user-images.githubusercontent.com/102513932/198549291-ee5b3530-fcc6-4161-b180-8d3831333e21.png)
- 로그인
  - ![image](https://user-images.githubusercontent.com/102513932/198549846-6b02544a-4fd8-44df-9a14-6fee4be51d8a.png)
- 로그인 이후 welcome 페이지 접근
  - ![image](https://user-images.githubusercontent.com/102513932/198550298-25e25fbb-2fdb-45e2-846d-a3548f9850fe.png)
    - 안녕하세요 홍길동님을 기대했는데 안녕하세요 손님이 나옴
    - 서버입장에서 로그인한 회원인지 아닌지 구별할 수 있는 방법이 없음
### Stateless
- HTTP는 무상태(Stateless) 프로토콜임
- 클라이언트와 서버가 요청과 응답을 주고 받으면 연결이 끊어짐
- 클라이언트가 다시 요청하면 서버는 이전 요청을 기억하지 못함
  - 위 예시에서 로그인한 회원인지 아닌지 구별할 수 없음
- 클라이언트와 서버는 서로 상태를 유지하지 않음

### 쿠키 사용 시 예시
- 로그인
  - ![image](https://user-images.githubusercontent.com/102513932/199134221-a771735f-a3ff-42f9-b883-9be6c2e1e4f8.png)
    - 서버는 응답에서 회원 정보를 쿠키로 만듬
    - 웹 브라우저는 쿠키 저장소에 서버에서 만든 쿠키를 저장
- 로그인 이후 welcome 페이지 접근
  - ![image](https://user-images.githubusercontent.com/102513932/199134346-303d1826-077a-4e01-a028-9db1e04570ce.png)
    - 웹 브라우저는 요청을 보낼때마다 쿠키 저장소를 확인함
    - 쿠키 정보를 포함한 요청을 보냄
- 모든 요청에 쿠키 정보 자동 포함
  - ![image](https://user-images.githubusercontent.com/102513932/199134391-5032bc0d-8674-427b-b473-0fe274d27ba5.png)
    - 모든 요청에 대한 쿠키 정보를 자동 저장

### 정리
- ex) set-cookie: sessionId=abcde1234; expires=Sat, 26-Dec-2020 00:00:00 GMT; path=/; domain=.google.com; Secure
- 사용처
  - 사용자 로그인 세션 관리
  - 광고 정보 트래킹
- 쿠키 정보는 항상 서버에 전송됨
  - 네트워크 트래픽 추가 유발 가능
  - 최송한의 정보만 사용 권장(세션 id, 인증 토큰)
  - 서버에 전송하지 않고, 웹 브라우저 내부에 데이터 저장 시 웹 스토리지(localStorage, sessionStorage) 
- 주의!
  - 보안에 민감한 데이터는 저장하면 안됨
    - ex) 주민번호, 신용카드 번호
### 생명주기
- Set-Cookie: expires=Sat, 26-Dec-2020 04:39:21 GMT
  - 만료일이 되면 쿠키 삭제
- Set-Cookie: max-age = 3600 (3600초)
  - 0이나 음수를 지정하면 쿠키 삭제
- 세션 쿠키: 만료 날짜를 생략하면 브라우저 종료시 까지만 유지
- 영속 쿠키: 만료 날짜를 입력하면 해당 날짜까지 유지
### 도메인
- ex) domain=example.org
- 명시: 명시한 문서 기준 도메인 + 서브 도메인 포함
  - domain=example.org를 지정해서 쿠키 생성 시
    - example.org, dev.example.org 쿠키 접근
- 생략: 현재 문서 기준 도메인만 적용
  - example.org에서 쿠키 생성, domain 지정 생략 시
    - example.org에서만 쿠키 접근
    - dev.example.org는 쿠키 미접근
### 경로
- ex) path=/home
- 이 경로를 포함한 하위 경로 페이지만 쿠키 접근
- 일반적으로 path=/ 루트로 지정
- ex)
  - path=/home 지정
  - /home -> 가능
  - /home/level1 -> 가능
  - /home/level1/level2 -> 가능
  - /hello -> 불가능
### 보안
- Secure
  - 쿠키는 http, https를 구분하지 않고 전송
  - Secure를 적용하면 https인 경우에만 전송
- HttpOnly
  - XSS 공격 방지
  - 자바스크립트에서 접근 불가(document.cookie)
  - HTTP 전송에만 사용
- SameSite
  - XSRF 공격 방지
  - 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송