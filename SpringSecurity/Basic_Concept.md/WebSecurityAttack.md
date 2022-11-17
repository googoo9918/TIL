## SQL Injection
- SQL Injection(SQL 삽입)
  - 임의의 SQL문을 실행할 수 있도록 명령어를 삽입하는 공격 유형
  - 응용 프로그램의 보안상의 허점을 이용하여 DB를 비정상적으로 조작
    - 기록이 삭제되거나 데이터 유출 가능
  - ![image](https://user-images.githubusercontent.com/102513932/202381060-7006b3af-4955-491a-9f57-4cb1d35e1c80.png)
### SQL injection 대응 방안
1. 입력(요청) 값 검증
- SQL문의 키워드를 제한하기에는 한계가 존재함
  - 블랙리스트가 아닌 화이트리스트 방식으로 해당 키워드가 들어오면 다른 값으로 치환하여 대응

> 보안에서 **화이트리스트**란 기본 정책이 모두 차단인 상황에서 예외적으로 접근이 가능한 대상을 지정하는 방식 또는 지정된 대상을 뜻함

2. Prepared Statement 구문 사용
- Prepared Statement 구문 사용 시 사용자의 입력이 SQL문으로부터 분리되어 SQL Injection을 방어할 수 있음
  - 사용자의 입력 값이 전달 되기 전에 DB가 미리 컴파일하여 SQL을 바로 실행하지 않고 대기함
  - 사용자의 입력값을 단순 텍스트로 인식
  - 입력 값이 SQL문이 아닌 단순 텍스트로 적용되어 공격에 실패 
3. Error Message 노출 금지
- 공격자는 DB의 Error Message를 통해 테이블이나 컬럼 등 DB의 정보를 얻을 수 있음
  - 에러가 발생한 SQL문과 에러 내용이 클라이언트에 노출되지 않도록 별도의 에러 핸들링 필요

## CSRF
- Cross Site Request Forgery
  - 다른 오리진(Cross-site)에서 유저가 보내는 요청(request)을 조작(forgery)하는 것
    - ex) 이메일에 첨부된 링크를 누르면 내 은행계좌의 돈이 빠져나감
  - 해커가 직접 데이터를 접근할 수 없음
    - ex) 다른 오리진이기 때문에 response에 접근할 수 없음
- CSRF 공격을 하기 위한 조건
  - 쿠키를 사용한 로그인
    - 유저가 로그인 했을 때, 쿠키로 어떤 유저인지 알 수 있어야 함
  - 예측할 수 있는 요청/parameter를 가지고 있어야 함
    - request에 해커가 모를 수 있는 정보가 담겨있으면 안됨
- GET 요청으로 CSRF 공격하기
  - 계좌이체에 사용되는 GET 요청
  - `GET https://codestatesbank.com/transfer?account_number=username&amount=1000$`
    - 코드스테이츠 은행의 고객인 김코딩이 은행 웹사이트에 로그인함
      - 현재 세션이 살아있고, 로그인 정보가 쿠키에 담겨있음
      - 김코딩의 계좌로 1000$를 이체하는 요청 GET 
        - 김코딩의 계좌를 해커 계좌번호로 변환
        - 김코딩의 브라우저 환경에서 GET 요청을 보내면 됨
      - 은행 입장에서는 김코딩의 로그인한 상태이고, 김코딩이 해커 계좌로 이체 요청을 보냈으니 요청을 승인하게 됨
- POST 요청으로 CSRF 공격하기
  - POST는 REQUEST BODY에 정보를 담아 요청함
```sql
POST 
http://codestatesbank.com/password/change 
body: {password:user's-new-password}
``` 
  - ![image](https://user-images.githubusercontent.com/102513932/202384689-39bca875-ef71-4e56-b128-234bdc960404.png)
    - 해커가 웹사이트를 하나 만들어서 document에 form을 작성함
    - 페이지가 로딩되는 순간 숨겨진 form이 비밀번호를 바꾸는 POST 요청이 진행되게 됨
### CSRF를 방지하는 방법
- CSRF 토큰 사용
  - 서버측에서 CSRF공격에 보호하기 위한 문자열을 유저의 브라우저와 웹 앱에만 사용
- Same-site cookie 사용
  - 같은 도메인에서만 세션/쿠키를 사용할 수 있음
    - 다른 웹사이트를 통해 다른 작업을 하는 것을 방지할 수 있음
  - 해커가 보낸 화이트링크를 클릭하여 요청을 하더라도 도메인이 달라 쿠키가 전송되지 않음