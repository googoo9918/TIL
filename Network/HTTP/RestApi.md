### 목차
- [## Rest Api](#-rest-api)
  - [좋은 REST API를 디자인하는 방법](#좋은-rest-api를-디자인하는-방법)
    - [리차드슨의 4단계 성숙도 모델](#리차드슨의-4단계-성숙도-모델)
    - [REST 성숙도 모델 - 0단계](#rest-성숙도-모델---0단계)
    - [REST 성숙도 모델 - 1단계](#rest-성숙도-모델---1단계)
    - [REST 성숙도 모델 - 2단계](#rest-성숙도-모델---2단계)
    - [REST 성숙도 모델 - 3단계](#rest-성숙도-모델---3단계)
  - [Open API와 API kEY](#open-api와-api-key)
    - [Open API](#open-api)
    - [API KEY](#api-key)
## Rest Api
---------------------------
- [5가지의 기본적인 REST API 디자인 가이드](https://blog.restcase.com/5-basic-rest-api-design-guidelines/)
- [구글 API 작성 가이드](https://cloud.google.com/apis/design?hl=ko)
- [MS의 REST API 가이드라인](https://github.com/Microsoft/api-guidelines/blob/master/Guidelines.md)
- Rest(Representational State Transfer) API(Application Programming Interface)
  - 웹에서 사용되는 데이터나 자원(Resource)를 HTTP URI로 표현 후 HTTP 프로토콜을 통해 요청과 응답을 정의
  - ex) 깔끔하게 정리된 메뉴판

### 좋은 REST API를 디자인하는 방법
#### 리차드슨의 4단계 성숙도 모델
![image](https://user-images.githubusercontent.com/102513932/193714881-49691f38-677e-4d28-bb64-d8784b3c15a9.png)
- 총 4단계(0~3단계)로 나누어짐
- 실제로 엄밀하게 3단계까지 지키기 어려움, 2단계까지만 적용해도 좋은 API 디자인
  - 이 경우, HTTP API라고도 지칭

#### REST 성숙도 모델 - 0단계
- 좋은 REST API 작성을 위한 기본 단계
- 단순히 HTTP 프로토콜 사용
- ex) 허준이라는 이름의 주치의의 예약 가능한 시간 확인 후 특정 시간에 예약하는 상황
- ![image](https://user-images.githubusercontent.com/102513932/193715156-bc1e1131-f380-40b4-ac3e-0b130e7a7f07.png)

#### REST 성숙도 모델 - 1단계
- 개별 리소스와의 통신 준수
  - REST API는 모든 Resource를 HTTO URI로 표현
    - 모든 Resource는 개별 Resource에 맞는 Endpoint를 사용해야 함
    - 받은 자원에 대한 정보를 응답으로 전달해야 함
  - 0단계에서는 모든 요청에서 Endpoint로 ```/appointment``` 사용 1단계에서는 각기 다른 Endpoint로 구분하여 사용
    - ex) doctors, slots
    - ![image](https://user-images.githubusercontent.com/102513932/193716905-357cc4ce-f0ad-4f27-9683-37c99581e959.png)
      - 첫 번째 요청의 응답은 허준이라는 의사의 예약 가능한 시간대
        - 요청 시 ```/doctors/허준```이라는 Endpoint 사용
      - 두 번째 요청에 의해 실제 slot이라는 Resource의 123이라는 id를 가진 Resource가 변경됨
        - 실제 변경되는 Resource인 ```/slots/123```을 엔드포인트로 사용
    - Resource의 변화와 응답 제공에 따라 각기 다른 Endpoint 사용, 적절한 Endpoint 작성 요망
      - 동사, HTTP 메서드, 행위에 대한 단어 사용 지양
      - Resource에 집중해 명사 형태의 단어 작성 지향
    - 응답으로 Resource 전달 시에도 사용한 Resource에 대한 정보, Resource 사용에 대한 성공/실패 여부 반환
      - ex) ![image](https://user-images.githubusercontent.com/102513932/193718156-c61ecc6d-9683-469a-b65b-a56b47069764.png)

#### REST 성숙도 모델 - 2단계
- CRUD에 맞게 적절한 HTTP 메서드 사용
  - 0~1단계 에서는 ```POST```, ```OK```만 사용
- ![image](https://user-images.githubusercontent.com/102513932/193718313-8f52c1cc-4a2a-4a7b-bef1-2cd11bad71cb.png)
  - 조회를 위한 ```GET``` 메서드
    - ```body```를 갖지 않기 때문에 query parameter를 사용하여 필요한 리소스 전달
  - 예약을 생성 하기 위한 ```POST``` 메서드
    - 응답이 어떻게 반환되는지 또한 중요
    - 응답으로 새롭게 생성된 리소스 송신
      - ```201 Created```로 명확히 작성
  - 관련 Resource를 클라이언트가 Location 헤더에 작성된 URI를 통해 확인할 수 있어야 2단계 충족
- 메서드 사용 규칙
  - GET
    - 서버의 데이터를 변화시키지 않는 요청에 사용
  - POST
    - 요청마다 새로운 리소스 생성
  - PUT
    - 요청마다 같은 리소스 반환 (멱등(idempotent)하다)
    - 교체의 용도로 사용
  - PATCH
    - 수정의 용도로 사용
- 2단계까지 적용하면, 대체적으로 잘 작성된 API
  - 3단계까지 적용한 경우는 극히 드뭄

#### REST 성숙도 모델 - 3단계
- HATEOAS(Hypertext As The Engine Of Application State)라는 약어로 표현되는 하이퍼미디어 컨트롤 적용
- 리소스의 URI를 포함한 링크 요소를 삽입하여 작성한다는 것이 2단계와 차이
  - 링크 요소는 응답을 받은 다음 할 수 있는 다양한 액션을 위해 많은 하이퍼미디어 컨트롤 포함
- ![image](https://user-images.githubusercontent.com/102513932/193719575-18da8f59-5341-43ae-9793-1a047ffc3d32.png)
  - 응답 내에 새로운 링크를 넣어 새로운 기능에 접근할 수 있도록 하는 것이 3단계의 중요 포인트
    - 예약가능 시간 확인 후 예약할 수 있는 링크 삽입
    - 예약 완료 후 예약을 다시 확인할 수 있는 링크 삽입

### Open API와 API kEY

#### Open API
- 누구에게나 열려있는 API
  - 무제한으로 이용할 수 있다는 의미는 아님
  - 기관, API마다 정해진 이용 수칙 존재
    - 제한사항 존재 가능
  - ex) [Open Weather Map](https://openweathermap.org/api)
    - 제한적으로 무료 날씨 API 사용 가능
    - 데이터 JSON 형태로 응답

#### API KEY
- API를 이용하기 위해 API KEY 필요
- 클라이언트에 요청에 의해 서버 응답
  - 서버 운용시 비용 발생
  - 서버 입장에서, 아무에게나 데이터를 제공할 의무와 이유 존재 X
- 로그인 이용자에게만 자원에 접근할 수 있는 API 권한 : API KEY