### 목차
- [Postman 사용법](#postman-사용법)
  - [HTTP API 테스트 도구](#http-api-테스트-도구)
  - [Postman 사용하기(GET)](#postman-사용하기get)
  - [Postman 화면 보기(GET)](#postman-화면-보기get)
  - [Postman 사용하기 (POST)](#postman-사용하기-post)
  - [Postman 화면보기 (POST)](#postman-화면보기-post)
## Postman 사용법

### HTTP API 테스트 도구
- 웹 개발의 대표적인 클라이언트는 브라우저
  - 브라우저는 서버에 HTTP 요청을 보낼 수 있음
    - 주로 웹 페이지를 받아오는 GET요청에 사용
  - 브라우저 주소창에 URL 입력 시
    - 해당 URL root-endpoint로 GET 요청 송신
  - 테스트를 위해 GET이 아닌 다른 요청 보낼 시
    - 개발자 도구 콘솔 창에서 내장 함수 ```fetch``` 이용
      - 매번 코드를 작성하는 대신, HTTP 요청을 테스트하는 다양한 도구를 사용해보자.
- CLI API 테스트 도구
  - ```curl```
    - 대부분 리눅스 환경에 내장
  - [wuzz](https://github.com/asciimoo/wuzz)

- GUI HTTP API 테스트 도구
  - [Postman](https://www.postman.com/)
  - [Insomnia](https://insomnia.rest/)

### Postman 사용하기(GET)
- 이미 만들어져 있는 API 서버가 주어지는 경우
  - HTTP로 소통하기 위해서는 API 서버의 endpoint가 URL로 주어져야 함.
- GET 요청하기
  - API 문서 EX
    - ![image](https://user-images.githubusercontent.com/102513932/193732901-82bad69a-d362-4b5e-8202-9e6c776bc61d.png)
    - ![image](https://user-images.githubusercontent.com/102513932/193732919-e841b6d0-4e7d-473a-9c4e-773540b96730.png)
    - ![image](https://user-images.githubusercontent.com/102513932/193732942-eb5d4518-6537-4fa1-8f9c-fb969e0f93bd.png)

### Postman 화면 보기(GET)
- ![image](https://user-images.githubusercontent.com/102513932/193733387-7b5c4bf7-5c4c-48ed-be11-e9424850d25f.png)
- 1. 새로운 탭 오픈
  - 요청/응답을 여러 개 확인 가능
- 2. HTTP 메서드 선택
  - GET, POST, DELETE 등과 같은 메서드 중 하나 선택
    - 예시에서 GET 사용
- 3. URL 입쳑 창
  - URL과 Endpoint를 입력
    - API 문서에 따라, ```http://3.36.72.17:3000/kimcoding/messages``` 입력
- 4. HTTP 요청 버튼
  - 요청을 보냄
- 5. HTTP 요청시 설정할 수 있는 각종 옵션
  - 추가적인 파라미터나, 요청 본문(body)을 추가할 수 있음
    - API 문서에 따라, ```roomname``` 파라미터 사용 가능
    - 필수는 아니지만, 파라미터 사용 시 Params 탭의 ```KEY```, ```VALUE```에 각각 ```roomname```과 필요한 값 입력
- 6. HTTP 응답 화면
  - 응답을 보낸 후 응답 확인

### Postman 사용하기 (POST)
- GET은 브라우저로 테스트 할 수 있는 반면, POST는 GET과 다르게 본문(body)를 포함하는 경우가 많음
  - 위 그림 **(5) - HTTP 요청 설정화면**에서 본문 입력
  - API문서 EX
    - ![image](https://user-images.githubusercontent.com/102513932/193734735-839c956c-0d61-4342-9aa3-85b31380d6d0.png)
    - ![image](https://user-images.githubusercontent.com/102513932/193734746-3c892f40-eb94-4237-ace1-285202db8fbd.png)

### Postman 화면보기 (POST)
- ![image](https://user-images.githubusercontent.com/102513932/193735185-4877f7fd-c9d4-407f-a1df-8760dd8150b8.png)
  - 1. 본문 형식 선택
    - JSON 형식 사용 시, ```raw``` 선택
  - 2. 본문 형식 선택(2)
    - 형식에 맞는 정확한 타입 선택
  - 3. 본문 내용
    - 본문 입력
      - API문세어 따르면, ```username```, ```text```, ```roomname```을 형식에 맞게 적어줘야함
  - 1번과 2번 과정은 HTTP 요청 헤더에 다음과 같이 작성하는 것과 동일
    - ```Content-Type: application/json```

