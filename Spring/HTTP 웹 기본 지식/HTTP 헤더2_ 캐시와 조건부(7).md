## 캐시 기본 동작
### 캐시가 없을 때
![image](https://user-images.githubusercontent.com/102513932/199150631-e7c29138-0ff1-4dda-b40d-4def7652c3fc.png)
- 첫 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199150642-7307dd92-df74-45ea-b028-f39166ca3611.png)
- 두 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199150660-40e08585-7b57-452f-9428-57cb1e9ad360.png)
- **느린 사용자 경험**
  - 데이터가 변경되지 않아도 계속 네트워크를 통해 데이터를 다운받아야 함
  - 인터넷 네트워크는 매우 느리고 비쌈
  - 브라우저 로딩 속도가 느림

### 캐시 적용
![image](https://user-images.githubusercontent.com/102513932/199150820-1bf0e4e8-ef38-424e-861d-b03604afc05f.png)
- 첫 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199150846-5d2cc98b-3542-455f-af33-2abed8e798ea.png)
  - ![image](https://user-images.githubusercontent.com/102513932/199150862-890f48bf-8ac0-4045-98c6-8aa4f6db9bee.png)
- 두 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199150949-91d2eed8-dfac-454c-9cc7-907ad34c557b.png)
  - ![image](https://user-images.githubusercontent.com/102513932/199150966-4752f799-ee68-4043-9e7b-75db75ee58e8.png)
- **빠른 사용자 경험**
  - 캐시 덕분에 캐시 가능 시간동안 네트워크를 사용하지 않아도 됨
  - 비싼 네트워크 사용량을 줄일 수 있음
  - 브라우저 로딩 속도가 매우 빠름
- 세 번째 요청_ 캐시 시간 초과 시
  - ![image](https://user-images.githubusercontent.com/102513932/199157728-6afb52bf-5523-42da-8c75-e931baaef808.png)
  - ![image](https://user-images.githubusercontent.com/102513932/199157743-44f07ced-aa22-4e20-99c2-58a85f8c7b9e.png)
    - 캐시 유효 시간 초과 시, 서버를 통해 데이터를 다시 조회한 후 캐시를 갱신
      - 이때 다시 네트워크 다운로드가 발생
## 검증 헤더와 조건부 요청1
- 캐시 유효 시간이 초과해 서버에 다시 요청하면 두 가지 상황 발생 가능
  - 1. 서버에서 기존 데이터를 변경
  - 2. 서버에서 기존 데이터 변경X
- 캐시 만료후에도 서버에서 데이터를 변경하지 않았을 때
  - 데이터를 전송하는 대신 저장해 두었던 캐시 재사용 가능
  - 단, 클라이언트의 데이터와 서버의 데이터가 같다는 사실을 확인할 수 있는 방법 필요
- 첫 번째 요청
  - ![image](https://user-images.githubusercontent.com/102513932/199158048-1370f5ed-8651-4ea4-be0c-39ca3ec85030.png)
## 검증 헤더와 조건부 요청2
## 캐시와 조건부 요청 헤더
## 프록시 캐시
## 캐시 무효화