### 목차
- [No Authentication](#no-authentication)
- [Authentication(Plaintext)](#authenticationplaintext)
- [Encryption](#encryption)
- [Hashing](#hashing)
- [Authentication(Hashing)](#authenticationhashing)
- [Salt](#salt)
  - [Salt 사용 시 주의점](#salt-사용-시-주의점)
  - [Authentication(Hashing + Salt)](#authenticationhashing--salt)

## No Authentication
- ![image](https://user-images.githubusercontent.com/102513932/202368594-e513e8d1-810c-4fc0-ace0-5802847ac40b.png)
  - 이메일과 관련된 정보를 얻을 때 어떠한 인증 과정을 거치지 않는다면?
    - 누구라도 이메일 정보만 알 고 있으면 모든 정보에 접근 가능
      - 보안 이슈 발생 
## Authentication(Plaintext)
- ![image](https://user-images.githubusercontent.com/102513932/202368704-def6172c-0c33-4502-9de5-ef7bacb515f1.png)
  - 비밀번호를 이용한 인증 흐름 설계
    - 서버는 이메일과 비밀번호 정보를 받고, DB에서 이를 비교
      - 정보 일치 시 클라이언트가 요청하는 데이터를 DB에서 찾아 전달
    - 해커가 DB에 접근해 비밀번호 탈취 문제 존재
## Encryption
- Encryption
  - 암호화
    - 일련의 정보를 임의의 방식을 사용해 다른 형태로 변환
    - 해당 방식에 대한 정보를 소유한 사람을 제외하고 이해할 수 없도록 *알고리즘*을 이용해 정보를 관리하는 과정
## Hashing
- 특정 문자열에 **임의의 연산**을 적용하여 다른 문자열로 변환
  - 1. 모든 값에 대해 해시 값을 계산하는데 오래걸리지 않아야 함
    - 해독할 때는 많은 시간이 걸려야 하지만 만드는 데엔 오래걸리지 않아야 함
      - 로그인 할 때마다 오랜 시간을 소요할 수 없기 때문
  - 2. 최대한 해시 값을 피해야 하며, 모든 값은 고유한 해시값을 가져야 함
  - 3. 아주 작은 단위의 변경이라도 완전히 다른 해시 값을 가져야 함
    - ex) backend vs Backend
  - 대표적 해시 알고리즘으로 SHA1 존재, 최근엔 SHA256 주로 사용
  - ![image](https://user-images.githubusercontent.com/102513932/202369159-2e859c2a-bd3b-47e8-92bc-3e5f2c219e67.png)
## Authentication(Hashing)
- ![image](https://user-images.githubusercontent.com/102513932/202369500-3cdbdecf-9ba0-4d5b-bddc-b046ff5f1a61.png)
  - 해시 알고리즘을 통해 비밀번호를 해시값으로 변환하여 DB에 저장
  - 이후 로그인이나 인증이 필요한 요청 시, 입력받은 비밀번호를 해시값으로 바꾸고 DB에 저장되어있는 해시 값 비밀번호와 비교
    - DB 비밀번호가 탈취당해도 해시 값이기 때문에 악용될 여지가 줄어듬
## Salt
- 암호화 해야 하는 값에 특정 '별도의 값'을 추가하여 결과를 변형하는 것
  -  암호화만 해놓는다면 해시된 결과가 늘 동일함
    - 해시된 값과 원래 값을 테이블(레인보우 테이블)로 만들어 decoding 하는 경우 발생 가능
  - 원본값에 임의로 약속된 별도의 문자열을 추가하여 해시 진행
    - 기존 해시값과 전혀 다른 해시값이 반환
      - 알고리즘이 노출 되더라도 원본값 보호 가능
  - 기존: 암호화 하려는 값 => hash 값
    - Salt 사용: 암호화 하려는 값 + Salt 용 값 => hash 값
### Salt 사용 시 주의점
- Salt는 유저와 패스워드 별로 유일한 값을 가져야 함
- 사용자 계정을 생성할 때와 비밀번호를 변경할 때 마닫 새로운 임의의 Salt를 사용해 해싱해야 함
- Salt는 절대 재사용하지 말아야 함
- Salt는 DB의 유저 테이블에 같이 저장되어야 함
### Authentication(Hashing + Salt)
- ![image](https://user-images.githubusercontent.com/102513932/202370602-b7a01fd5-fdd5-4309-8884-141db1fa59ec.png)
  - 유저마다 Salt를 다르게 하기 위해 DB에서도 각자 다른 Salt 값을 가짐
  - Salt를 문자열 "test"라 가정
    - 회원가입 시 입력받은 비밀번호에 문자열 "test"를 더한 후 해싱 알고리즘을 적용한 반환값이 DB에 저장
  - 이후 로그인이나 인증이 필요한 요청 시
    - 입력받은 비밀번호에 해당 유저의 Salt값을 더해 해싱 알고리즘의 결과 값과 DB에 저장된 유저 비밀번호 비교
  - 알고리즘이 노출되더라도 Salt 값을 모르기 때문에 보안적으로 더 안전할 수 있음