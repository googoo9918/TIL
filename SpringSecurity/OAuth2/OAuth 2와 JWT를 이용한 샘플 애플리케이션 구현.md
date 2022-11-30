# OAuth 2와 JWT를 이용한 샘플 애플리케이션 구현
- 여태까지는 SSR 방식으로 인증 시스템을 구현함
- 지금은 CSR 방식의 애플리케이션에 Google의 OAuth 2 인증 시스템을 적용
  - OAuth 2 인증 시스템을 통해 인증에 성공한 사용자에 대한 자격 증명 정보를 JWT로 제공

### Frontend와 Backend 간의 OAuth 2 인증 처리 흐름
- ![image](https://user-images.githubusercontent.com/102513932/204718935-1070ceba-e28e-481b-883f-6e59d60a485a.png)
  -  Frontend와 Backend 간의 OAuth 2 인증 처리 흐름
  -  (1) Resource Owner가 웹 브라우저에서 ‘Google 로그인 링크’를 클릭
  -  (2) Frontend 애플리케이션에서 Backend 애플리케이션의 `http://localhost:8080/oauth2/authorization/google로` request를 전송
     -  URI의 request는 `OAuth2LoginAuthenticationFilter` 가 처리
  -  (3) Google의 로그인 화면을 요청하는 URI로 리다이렉트
     -  Authorization Server가 Backend 애플리케이션 쪽으로 Authorization Code를 전송할 Redirect URI(`http://localhost:8080/login/oauth2/code/google`)를 쿼리 파라미터로 전달함
        -  Redirect URI는 Spring Security가 내부적으로 제공
  -  (4) Google 로그인 화면을 오픈
  -  (5) Resource Owner가 Google 로그인 인증 정보를 입력해 로그인을 수행
  -  (6) 로그인에 성공 시 (3)에서 전달한 Backend Redirect URI(`http://localhost:8080/login/oauth2/code/google`)로 Authorization Code를 요청
  -  (7) Authorization Server가 Backend 애플리케이션에서 Authorization code를 응답으로 전송
  -  (8) Backend 애플리케이션이 Authorization Server에게 Access Token을 요청
  -  (9) Authorization Server가 Backend 애플리케이션에게 Access Token을 응답으로 전송
     - 여기서의 Access Token은 Google Resource Server에게 Resource를 요청하는 용도로 사용
  -  (10) Backend 애플리케이션이 Resource Server에게 User Info를 요청
     - 여기서의 User Info는 Resource Owner에 대한 이메일 주소, 프로필 정보 등을 의미
  -  (11) Resource Server가 Backend 애플리케이션에게 User Info를 응답으로 전송
  -  (12) Backend 애플리케이션은 JWT로 구성된 Access Token과 Refresh Token을 생성
     -  Frontend 애플리케이션에게 JWT(Access Token과 Refresh Token)를 전달하기 위해 Frontend 애플리케이션 (`http://localhost?access_token={jwt-access-token}&refresh_token={jwt-refresh-token}`)으로 Redirect함
- (6)부터 (11)까지는 Spring Security에서 내부적으로 알아서 처리해주기 때문에, 따로 작성할 것은 없음
- OAuth 2 인증 처리 정상 동작 확인을 위해 웹서버에서 실행되는 Frontend 애플리케이션 필요

## Frontend 애플리케이션 준비
### 아파치 웹서버 설치
- [아파치 웹 서버 다운로드](https://www.apachelounge.com/download/)
- 압축 해제 후
  - `Apache24` 디렉토리를 C:\ 디렉토리로 이동
    - 최종 경로 : `C:\Apache24`
  - conf 파일의 httpd.conf 파일을 메모장 등의 에디터로 오픈
    - ServerName 주석 해제 후 수정
      - `ServerName localhost:80`
      - 나머지는 디폴트 값을 그대로 사용
  - cmd를 관리자 모드로 실행
    - `c:\Apache24\bin` 디렉토리로 이동
    - `httpd.exe -k install` 명령 입력
  - Apach24\bin\ApacheMonitor.exe로 아파치 웹 서버 실행
    - 우측 하단 빠른 실행 화면에서 실행/중단 설정 가능
  - 웹 브라우저에서 `http:localhost`로 접속 시 아래와 같은 화면 출력
    - 아파치 웹서버 정상 실행 시
    - ![image](https://user-images.githubusercontent.com/102513932/204725931-0f491421-5bf5-4cd9-895c-c78f6ccfcde3.png)

### Frontend 샘플 애플리케이션을 아파치 웹서버에 배포
세 개의 HTML 파일을 에디터로 작성한 후, `C:\Apache24\htdocs` 디렉토리로 위치시킴
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OAuth2 + JWT Frontend</title>
</head>
<body>
    <h2>Welcome to OAuth 2.0 + JWT Spring Security</h2>
    <a href="http://localhost:8080/oauth2/authorization/google">Google로 로그인</a>
</body>
</html>
```
- `index.html`에서 [Google로 로그인]버튼 클릭 시 Backend 애플리케이션으로 request 전송
  - Google 로그인 화면 오픈

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OAuth2 + JWT My page</title>
</head>
<body>
    <script type="text/javascript">
        let accessToken = (new URL(location.href)).searchParams.get('access_token');
        let refreshToken = (new URL(location.href)).searchParams.get('refresh_token');

        localStorage.setItem("accessToken", accessToken)
        localStorage.setItem("refreshToken", refreshToken)

        location.href = 'my-page.html'
    </script>
</body>
</html>
```
- receive-token.html은 Backend 애플리케이션에서 전달 받은 JWT Access Token과 Refresh Token을 웹 브라우저의 LocalStorage에 저장한 후, my-page.html로 이동함

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OAuth2 + JWT My page</title>
</head>
<body>
    <h2>My Page</h2>
    <h3>아래의 토큰을 이용해서 Backend 애플리케이션의 리소스를 요청할 수 있습니다.</h3>
    <p>
        <span>Access Token: </span><span id="accessToken" style="color: blue"></span>
    </p>
    <p>
        <span>Refresh Token: </span><span id="refreshToken" style="color: blue"></span>
    </p>
    <script type="text/javascript">
        let accessToken = localStorage.getItem('accessToken')
        let refreshToken = localStorage.getItem('refreshToken');

        document.getElementById("accessToken").textContent = accessToken;
        document.getElementById("refreshToken").textContent = refreshToken;
    </script>
</body>
</html>
```
- my-page.html에서는 LocalStorage에 저장된 JWT Access Token과 Refresh Token을 로그해 웹 브라우저에 표시함