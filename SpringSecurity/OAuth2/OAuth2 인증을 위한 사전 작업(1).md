### 목차
- [OAuth 2 인증을 위한 사전 작업](#oauth-2-인증을-위한-사전-작업)
  - [구글 API 콘솔에서 OAuth2 설정](#구글-api-콘솔에서-oauth2-설정)
    - [프로젝트 생성](#프로젝트-생성)
    - [OAuth 동의 화면 만들기](#oauth-동의-화면-만들기)
    - [사용자 인증 정보 생성](#사용자-인증-정보-생성)
# OAuth 2 인증을 위한 사전 작업
- 구글에서 제공하는 OAuth2 인증 시스템 이용
## 구글 API 콘솔에서 OAuth2 설정
- [구글 API 및 서비스 콘솔](https://console.cloud.google.com/apis)
  - OAUth2 시스템을 이용하기 위한 클라이언트 ID와 Secret 생성
### 프로젝트 생성
- ![image](https://user-images.githubusercontent.com/102513932/204691489-5dc1c426-c58d-4795-919a-76b41dff2946.png)
  - 프로젝트 만들기 링크 클릭
    - 만약 링크가 보이지 않는다면 상단 프로젝트 선택 클릭 후 오픈된 팝업에서 [새 프로젝트]링크 클릭
- ![image](https://user-images.githubusercontent.com/102513932/204691644-4d38728e-6725-4483-9e7b-e9ccf7004eb0.png)
  - 새로운 프로젝트 생성
    - 이름은 편한 이름
    - 나머지 항목은 디폴트
- ![image](https://user-images.githubusercontent.com/102513932/204691703-33bd7b87-8d31-4e0f-b9c0-844c3fc72be9.png)
  - 사용 설정된 API 및 서비스 상단에 [프로젝트 선택] 메뉴 클릭
    - 생성된 프로젝트 선택
      - 혹은 최근 프로젝트 선택 클릭

### OAuth 동의 화면 만들기
- ![image](https://user-images.githubusercontent.com/102513932/204692115-d90f03a2-5881-4e9c-b65c-9a04511df79f.png)
  - 생성된 프로젝트 선택 시 API 및 서비스 대시보드 확인 가능
    - [OAuth 동의 화면] 클릭
  - ![image](https://user-images.githubusercontent.com/102513932/204692315-06650cd0-cbea-4e5c-9268-0caa5ac68ab2.png)
    - OAuth 동의 화면에서 User Type을 "외부"로 선택 체크한 뒤 [만들기] 버튼 클릭
  - ![image](https://user-images.githubusercontent.com/102513932/204692463-3490dafc-1afd-4e53-a4df-6a569fb28f80.png)
    - [앱 등록 수정] 화면에서 '앱 이름'과 '사용자 지원 이메일'
    - 개발자 연락처 정보에서 '이메일 주소' 입력 후 [저장 후 계속]버튼 클릭
  - ![image](https://user-images.githubusercontent.com/102513932/204692571-fb4eceee-e6d4-4649-a499-fc0cb2b3c41c.png)
    - [테스트 사용자] 화면에서 [저장 후 계속] 버튼 클릭
### 사용자 인증 정보 생성
- ![image](https://user-images.githubusercontent.com/102513932/204692627-d1abc3c2-2304-48bd-b996-36b4582c0c11.png)
  - 왼쪽 목록에서 [사용자 인증 정보] 클릭
- ![image](https://user-images.githubusercontent.com/102513932/204692680-3e956897-72a5-4a56-afef-6965098238a0.png)
  - [사용자 인증 정보 만들기] 클릭 후 , [OAuth 클라이언트 ID] 클릭
- ![image](https://user-images.githubusercontent.com/102513932/204692737-15a2abf0-464b-41e9-b2e6-203889c21353.png)
  - 애플리케이션 유형은 **웹 애플리케이션** 선택
  - 애플리케이션 이름 입력
  - 승인된 리디렉션 URI에 `http://localhost:8080/login/oauth2/code/google`입력
    - 당연히 달라져야 함
  - 만들기 버튼 클릭
- ![image](https://user-images.githubusercontent.com/102513932/204692967-f8015baf-58c8-46f7-93d5-77824532a937.png)
  - OAuth 클라이언트 생성 화면과 같이 ID와 비밀번호 확인 가능
  - ID와 비밀번호는 Spring Security 기반의 애플리케이션의 설정 정보로 사용됨
    - **안전하게 잘 보관할 것**