## 모든 것이 HTTP
- HTTP
  - HyperText Transfer Protocol
  - 지금은 HTTP 메시지에 모든 것을 전송
    - HTML, TEXT
    - 이미지, 음성, 영상, 파일
    - JSON, XML(API)
  - 거의 모든 형태의 데이터 전송 가능
  - 서버간에 데이터를 주고받을 때도 대부분 HTTP 사용
### 기반 프로토콜
- TCP : HTTP/1.1 , HTTP/2
  - 2는 2015년 성능 개선 버전
- UDP : HTTP/3
  - 애플리케이션 레벨에서 성능 최적화를 위해 나온것이 HTTP/3
- 현재 HTTP/1.1 주로 사용
  - HTTP/2, HTTP/3도 사용량 점점 증가
  - HTTP/1을 성능 개선한 것으로, 1을 잘 공부해 두자
  - 22년 기준으로 구글은 3버전, 네이버는 2버전 사용 확인 가능
    - 1은 도태됐을지도..?
### HTTP 특징
- 클라이언트 서버 구조
- 무상태 프로토콜(Stateless), 비연결성
- HTTP 메시지
- 단순함, 확장 가능
## 클라이언트 서버 구조
- Request Response 구조
  - 클라이언트와 서버를 개념적으로 분리
  - 서버는 비즈니스 로직과 데이터에, 클라이언트는 UI와 사용성에 집중
    - 클라이언트와 서버가 독립적으로 진화 가능
- 클라이언트는 서버에 요청을 보내고, 응답을 대기
- 서버가 요청에 대한 결과를 만들어서 응답 
## Stateful, Stateless
### 상태 유지 - Stateful
- 기본 예제 
```
Q. 고객: 이 노트북 얼마인가요?  A. 점원: 100만원 입니다. (노트북 상태 유지)
Q. 고객: 2개 구매하겠습니다 A. 점원: 200만원 입니다. 신용카드, 현금 중 어떤 걸로 구매하시겠어요? (노트북, 2개 상태 유지)
Q. 고객: 신용카드로 구매하겠습니다 A. 점원: 200만원 결제 완료되었습니다(노트북, 2개, 신용 카드 상태 유지)
```
- 예제, 점원이 중간에 바뀌면?
```
Q. 고객: 이 노트북 얼마인가요?  A. 점원1: 100만원 입니다.
Q. 고객: 2개 구매하겠습니다 A. 점원2 : ?? 뭘 2개 구매하시겠어요??
Q. 고객: 신용카드로 구매하겠습니다 A. 점원3 : ??? 무슨 제품 몇개를 신용카드로 구매하시겠어요?
```
- 점원이 바뀌면 오류 발생!
### 무상태 - Stateless
- 예제
```
Q. 고객: 이 노트북 얼마인가요?  A. 점원1: 100만원 입니다. 
Q. 고객: 2개 구매하겠습니다 A. 점원2: 200만원 입니다. 신용카드, 현금 중 어떤 걸로 구매하시겠어요? 
Q. 고객: 신용카드로 구매하겠습니다 A. 점원3: 200만원 결제 완료되었습니다
```

### 정리
- 상태 유지
  - 중간에 다른 점원으로 바뀌면 안됨
  - 중간에 다른 점원으로 바뀔 시 상태 정보를 다른 점원에게 미리 알려줘야 함
  - ![image](https://user-images.githubusercontent.com/102513932/198198134-c5531ff1-a1e7-4d6d-aa16-5a24bebc0e4f.png)
    - 클라이언트A는 서버1번 이랑만 통신을 해야함
    - 서버1 장애 발생 시, 통신을 처음부터 다시 해야함
- 무상태
  - 중간에 다른 점원으로 바뀌어도 됨
    - 갑자기 고객이 증가해도 점원 대거 투입 가능
    - 갑자기 클라이언트 요청이 증가해도 서버 대거 투입 가능
  - 무상태는 응답 서버를 쉽게 바꿀 수 있음
    - **무한한 서버 증설 가능**
  - ![image](https://user-images.githubusercontent.com/102513932/198198318-e3e9eb30-3351-4482-b63d-d364d2a9d136.png)
    - 서버가 장애가 나도, 다른 서버와 통신을 지속할 수 있음
  - ![image](https://user-images.githubusercontent.com/102513932/198198545-9c72d9c8-e06c-4566-b30c-457a7cf5c9d5.png)
- 무상태의 실무 한계
  - 모든 것을 무상태로 설계 할 수 있는 경우도 있고, 없는 경우도 있음
  - 무상태
    - ex) 로그인이 필요 없는 단순한 서비스 소개 화면
  - 상태 유지
    - ex) 로그인
  - 로그인한 사용자의 경우, 로그인 했다는 상태를 서버에 유지
  - 일반적으로 브라우저 쿠키와 서버 세션등을 사용해 상태 유지
  - 상태 유지는 **최소한**만 사용!
  - 데이터량이 많다는 단점도 존재함
## 비 연결성(connectionless)
- 연결을 유지하는 모델
  - ![image](https://user-images.githubusercontent.com/102513932/198201459-52c08d57-e33c-46d3-a322-5e33771f54f8.png)
  - 자원 낭비가 심함
- 연결을 유지하지 않는 모델 
  - ![image](https://user-images.githubusercontent.com/102513932/198201518-a9f7946a-adfc-4a77-a9f0-1821930a02cd.png)
  - ![image](https://user-images.githubusercontent.com/102513932/198201552-87964a27-98d6-401c-8f03-78bb1bfd4cc1.png)
  - 요청을 주고받을 때만 자원 사용, 최소한의 자원 사용 가능
- HTTP는 기본적으로 연결을 유지하지 않는 모델
- 일반적으로 초 단위 이하의 빠른 속도로 응답
- 1시간 동안 수천명이 서비스 사용 시, 실제 서버에서 동시에 처리하는 요청은 수십개 이하로 매우 작음
  - ex) 웹 브라우저에서 계속 연속해서 검색 버튼을 누르지는 않음
- 서버 자원을 매우 효율적으로 사용 가능
### 한계와 극복
- TCP/IP 연결을 새로 맺어야함 -> 3 way handshake 시간 추가
- 웹 브라우저로 사이트 요청 시 HTML 뿐 아니라 JS, CSS, 추가 이미지 등 수많은 자원이 함께 다운로드
  - 지금은 HTTP 지속연결(Persistent Connections)로 문제 해결
- HTTP/2, HTTP/3에서 더 많은 최적화
### HTTP 초기 - 연결, 종료 낭비
![image](https://user-images.githubusercontent.com/102513932/198202594-6a2697bc-7375-4b3e-a772-ea8bd80eebe0.png)
### HTTP 지속 연결
![image](https://user-images.githubusercontent.com/102513932/198202616-4dcfb9bf-e968-4e7d-b7d3-4253a761249c.png)
### 스테이스리스를 기억하자
- 서버 개발자들이 어려워하는 업무
  - 같은 시간에 딱 맞춰 발생하는 대용량 트래픽
  - ex) 선착순 이벤트, 명절 KTX 예약, 수강신청
- 최대한 Stateless하게 설계하라!

## HTTP 메시지
- HTTP 메시지 구조
  - start-line (시작 라인)
  - header 헤더
  - empty line (공백 라인)(CRLF)
  - mseeage body
  - ![image](https://user-images.githubusercontent.com/102513932/198204937-b07a8f90-5e98-4689-a8e7-5c6688ddef89.png)

### 시작 라인
- 요청 메시지
  - `GET /search?q=hello&hl=ko HTTP/1.1`
  - start line = request-line
  - request-line = method SP(공백) request-target SP HTTP-version CRLF(개행)
    - HTTP 메서드 (GET)
      - ex) GET, POST, PUT, DELETe...
      - 서버가 수행해야 할 동작 지정
    - 요청 대상 (/search?q=hello&hl=ko)
      - absolute-path[?query] (절대경로[?쿼리])
      - 절대경로(/로 시작)로 시작
    - HTTP Version (HTTP/1.1)
- 응답 메시지
  - `HTTP/1.1 200 OK`
  - start-line = status-line
  - status-line = HTTP-version SP status_code SP reason-phrase CRLF
  - HTTP 버전(HTTP/1.1)
  - HTTP 상태 코드(200)
    - 200: 성공, 400: 클라이언트 요청 요류, 500: 서버 내부 오류
  - 이유 문구(OK)
    - 사람이 이해할 수 있는 짧은 상태 코드 설명 글
### HTTP 헤더
- header-field = field-name ":" OWS field-value OWS (OWS: 띄어쓰기 허용)
  - field-name과 `:` 띄어쓰지 않도록 주의할 것
- field-name은 대소문자 구분X
- ex) `Host: www.google.com` `Content-Type: text/html;charset=UTF-8` `Content-Length: 3423`
- 용도
  - HTTP 전송에 필요한 모든 부가정보
  - ex) 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트(브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정보...
  - 표준 헤더가 너무 많음
  - 필요 시 임의의 헤더 추가 가능
### HTTP 메시지 바디
- 실제 전송할 데이터
- HTML 문서, 이미지, 영상, JSON 등등 byte로 표현할 수 있는 모든 데이터 전송 가능 
- ex)
```html
<html>
  <body>...</body>
</html>
```