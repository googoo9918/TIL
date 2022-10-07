### 목차
- [HTTP](#http)
  - [Stateless](#stateless)
  - [HTTP messages](#http-messages)
## HTTP
- HTTP(HypertText Transfer Protocol)
  - HTML과 같은 문서를 전송하기 위한 Application Layer 프로토콜
  - 웹 브라우저와 웹 서버의 소통을 위해 ㅣ자인
  - 무상태성을 지님
### Stateless
  - HTTP로 클라이언트와 서버가 통신을 주고받는 과정에서, HTTP가 클라이언트나 서버의 상태를 확인하지 않음
  - 클라이언트에서 발생한 모든 상태를 HTTP가 추적하지 않음
    - 단지 통신 규약이기 때문
    - 쿠키-세션, API로 상태 확인
### HTTP messages
  - 클라이언트와 서버 사이에서 데이터가 교환되는 방식
  - 요청, 응답의 두 가지 유형으로 나뉨
  - 구성 파일, API, 기타 인터페이스에서 거의 자동 완성됨
  - ![image](https://user-images.githubusercontent.com/102513932/194322666-e1a07835-36c3-41ef-99e1-331aecde9832.png)
  - HTTP messages의 구조
    - 1. start line
      - 요청이나 응답의 상태를 나타냄
        - 항상 첫 번쨰 줄에 위치
        - 응답에서는 status line이라 지칭
    - 2. HTTP headers
      - 요청을 지정하거나, 메시지에 포함된 본문을 설명하는 헤더 집합
    - 3. empty line
      - 헤더와 본문을 구분하는 빈 줄
    - 4. body
      - 요청과 관련된 데이터, 응답과 관련된 데이터, 문서 포함
      - 요청과 응답의 유형에 따라 선택적 사용
    - start line, HTTP headers를 묶어 head
    - payload는 body라 지칭