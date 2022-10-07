### 목차
- [요청](#요청)
  - [Start line](#start-line)
  - [Headers](#headers)
  - [Body](#body)
  - [HTTP 요청 메서드](#http-요청-메서드)
    - [GET](#get)
    - [HEAD](#head)
    - [POST](#post)
    - [PUT](#put)
    - [CONNECT](#connect)
    - [OPTIONS](#options)
    - [TRACE](#trace)
    - [PATCH](#patch)
## 요청
### Start line
- Start line에 존재하는 세 가지 요소
- 1. 수행할 작업(GET, PUT, POST 등)이나 방식(HEAD or OPTIONS)을 설명하는 HTTP method를 나타냄
- 2. 요청 대상(일반적으로 URL, URI)또는 프로토콜, 포트, 도메인의 절대 경로는 요청 컨텍스트에 작성됨
  - 형식은 HTTP method 마다 다름
  - origin 형식: `?`와 쿼리 문자열이 붙는 절대 경로
    - POST, GET, HEAD, OPTIONS 등의 method와 함께 사용
      - ex)`POST / HTTP 1.1GET /background.png HTTP/1.0HEAD /test.html?query=alibaba HTTP/1.1OPTIONS /anypage.html HTTP/1.0`
  - absolute 형식: 완전한 URL형식, 프록시에 연결하는 경우 대부분 GET method와 함께 사용
    - ex) `GET http://developer.mozilla.org/en-US/docs/Web/HTTP/Messages HTTP/1.1` 
  - authority 형식: 도메인 이름과 포트 번호로 이루어진 URL의 authority component
    - HTTP 터널을 구축하는 경우, `connect`와 함께 사용 가능
    - ex) `CONNECT developer.mozilla.org:80 HTTP/1.1`
  - asterisk 형식 : `OPTIONS`와 함께 별포(*) 하나로 서버 전체를 표현
    - ex) `OPTIONS * HTTP/1.1`
- 3. HTTP 버전에 따라 HTTP message의 구조가 달라짐 따라서 start line에 HTTP 버전을 함께 입력

### Headers
- Headers는 기본 구조를 따름
  - 헤더 이름(대소문자 구분이 없는 문자열)
  - 콜론(:)
  - 값
    - 값은 헤더에 따라 다름
- Header의 종류
  - General headers
    - 메시지 전체에 적용되는 헤더, body를 통해 전송되는 데이터와는 관련 없는 헤더
  - Request headers
    - fetch를 통해 가져올 리소스나 클라이언트 자체에 대한 자세한 정보를 포함하는 헤더
    - User-Agent, Accept-Type, Accept-Language와 같은 헤더는 요청을 보다 구체화함 
  - Representaiton headers
    - 이전에는 Entity headers로 불렸음
      - body에 담긴 리소스의 정보(콘텐츠 길이, MIME 타입)를 포함
  - ![image](https://user-images.githubusercontent.com/102513932/194328493-b023aa4e-a4ee-4215-9fd9-8a5c753f5e1b.png)

### Body
- 요청의 body는 HTTP messages 구조의 마지막에 위치
- 모든 요청에 body가 필요하진 않음
  - GET, HEAD, DELETE, OPTIONS처럼 서버에 리소스를 요청하는 경우, body 필요X
  - POST나 PUT과 같은 일부 요청은 데이터를 업데이트 하기 위해 사용
- body의 두 종류
  - Single-resource bodies(단일-리소스 본문)
    - 헤더 두 개(Content-Type, Content-Length)로 정의된 단일 파일로 구성
  - Multiple-resource bodies(다중-리소스 본문)
    - 여러 파트로 구성된 본문에서는 각 파트마다 다른 정보를 지님
      - 보통 HTML form과 관련

### HTTP 요청 메서드
- 주어진 리소스에 수행하길 원하는 행동을 나타냄
#### GET
- GET 메서드는 특정 리소스의 표시 요청
  - 오직 데이터를 받기만 함
#### HEAD
- HEAD 메서드는 GET 요청과 동일한 응답 요구
  - 단, 응답 본문을 포함하지 않음
#### POST
- POST 메서드는 특정 리소스에 엔티티를 제출할 때 쓰임
  - 종종 서버의 상태의 변화나 부작용을 일으킬 수 있음
#### PUT
- PUT 메서드는 목적 리소스 모든 현재 표시를 요청 payload로 바꿈
#### CONNECT
- CONNECT 메서드는 목적 리소스로 식별되는 서버로의 터널을 맺음
#### OPTIONS
- OPTIONS 메서드는 목적 리소스의 통신을 설정하는 데 쓰임
#### TRACE
- TRACE 메서드는 목적 리소스의 경로를 따라 메시지 loop-back 테스트를 실행
#### PATCH
- PATCH 메서드는 리소스의 부분만을 수정하는 데 사용