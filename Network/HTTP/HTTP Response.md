### 목차
- [응답](#응답)
  - [Status line](#status-line)
    - [HTTP 상태 코드](#http-상태-코드)
  - [Headers](#headers)
  - [Body](#body)
## 응답
### Status line
- 응답의 첫 줄
  - 1. 현재 프로토콜의 버전
    - ex) HTTP/1.1
  - 2. 상태 코드 - 요청의 결과
    - ex) 200, 302, 404
  - 3. 상태 텍스트
    - 상태 코드에 대한 설명
    - ex) Not Found.
  - `HTTP/1.1 404 NOT Found`
#### [HTTP 상태 코드](https://developer.mozilla.org/ko/docs/Web/HTTP/Status)
- 100번대 : 정보 응답
- 200번대 : 성공 응답
- 300번대 : 리다이렉션 응답
- 400번대 : 클라이언트 에러 응답
- 500번대 : 서버 에러 응답

### Headers
- 요청 헤더와 동일한 구조 지님
- 대소 구분 없는 문자열, 콜론(:), 값
  - 값은 헤더에 따라 다름
- General headers
  - 메시지 전체에 적용되는 헤더, body를 통해 전송되는 데이터와는 관련이 없는 헤더
- Response headers
  - 위치 또는 서버 자체에 대한 정보(이름, 버전)와 같이 응답에 대한 부가적인 정보를 갖는 헤더
  - Vary, Accept-Ranges와 같이 상태 줄에 넣기에는 공간이 부족했던 추가 정보 제공
- Representation headers
  - 이전에는 Entity headers라 지칭, body에 담긴 리소스의 정보(콘텐츠 길이, MIME 타입)를 포함하는 헤더
- ![image](https://user-images.githubusercontent.com/102513932/194331300-e402c321-69e3-4d42-a0fb-bb2521232430.png)

### Body
- 응답의 body는 HTTP messages 구조의 마지막에 위치
  - 모든 응답에 body가 필요하지 않음
  - 201, 204와 같은 상태 코드를 갖는 응답에는 body 필요X
- Single-resource bodies(단일-리소스 본문)
  - 길이가 알려진 단일-리소스 본문은 두 개의 헤더(Content-Type, Content-Length)로 정의
  - 길이를 모르는 단일 파일로 구성된 단일-리소스 본문은 Transfer-Encoding이 `chunked`로 설정되어 있음
    - 파일은 chunk로 나뉘어 인코딩
- Multiple-resource bodies(다중-리소스 본문)
  - 서로 다른 정보를 담고 있는 body