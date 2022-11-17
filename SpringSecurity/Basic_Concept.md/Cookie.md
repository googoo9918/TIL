- [쿠키](https://github.com/ssu18/TIL/blob/main/Network/HTTP%20%EC%9B%B9%20%EA%B8%B0%EB%B3%B8%20%EC%A7%80%EC%8B%9D/HTTP%20%ED%97%A4%EB%8D%941_%EC%9D%BC%EB%B0%98(6).md#%EC%BF%A0%ED%82%A4)
## 쿠키
- 서버가 웹 브라우저에 정보를 저장하고 불러올 수 있는 수단
  - 해당 도메인에 대해 쿠키 존재 시, 웹 브라우저는 도메인에게 http 요청 시 쿠키를 함께 전달함
    - ![image](https://user-images.githubusercontent.com/102513932/202376532-92f9736a-0cd1-478b-a752-8802aeacce8d.png)
- 사용자 선호, 테마 등 장시간 보존해야하는 정보 저장에 적합
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
    - 해당 옵션이 true로 설정된 경우임
- HttpOnly
  - XSS 공격 방지
    - ![image](https://user-images.githubusercontent.com/102513932/202376977-6102a027-def9-4bab-855a-81aea9937b03.png)
  - 자바스크립트에서 접근 불가(document.cookie)
    - 해당 옵션이 true로 설정한 경우임
    - 명시되지 않는 경우, 기본값 false
  - HTTP 전송에만 사용
- SameSite
  - XSRF 공격 방지
    - ![image](https://user-images.githubusercontent.com/102513932/202377122-50e214ef-483a-42a3-b5ab-edf4ade5a5a9.png)
  - Lax : Cross-Origin 요청이면 GET 메서드에 대해서만 쿠키 전송
  - Strict: 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송
    - Cross-Origin 아닌 경우
    - 도메인, 프로토콜, 포트가 다 같아야 함
  - None: 항상 쿠키를 보내줄 수 있음
    - 다만 쿠키 옵션 중 `Secure` 옵션 필요