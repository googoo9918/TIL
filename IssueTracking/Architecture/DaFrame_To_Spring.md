## DaFrame -> Spring 변환 정리
### Client 기본 컨셉
- 결국 JSP + SpringBoot를 사용한 SSR 방식이고, SPA를 구현할 수는 없음
    - 기존 PPR(Partial Page Rendering) 방식 + window.hisotry를 추가하는 방식으로 구현
        - ITMS에는 PPR은 구현되어 있으나, 새로고침 시 main.jsp 부터 다시 시작 하는 불편함 존재
    - 현재는 controller 단 AJAX 요청 체크(URL 직접 접근 및 새로고침 방지) + window.onpopstate를 통해 앞으로가기, 뒤로가기 요청 처리
    - ENUM을 통해 페이지 URL 관리

- 기존에는 모든 api 요청은, POST로 처리 / jsp 파일 요청 ajax는 GET으로 처리
    - 방문관리 시스템은 동작하고자 하는 Http Method에 따라 Restful Api 규약에 맞춰 getHtml, getRequest, postRequest, putRequest, deleteRequest 등으로 구분

### Controller 기본 컨셉
- jsp를 반환하는 ViewController와, Rest api 요청을 처리하는 RestController로 분리

- RESTful API 설계
    - ITMS는 리소스 기반이 아닌 동작 위주의 URL / Http Method는 모두 POST를 사용(페이지 요청 제외) / HTTP 상태 코드 활용하지 않음(에러 발생 시에도 200 반환)
        - 위와 같은 이유로 RESTful API라고 보기 어려움
        - 따라서 방문관리 시스템에서는 최대한 RESTful api url 규약에 맞춰, 엔드포인트를 설계하고자 했음
    - ViewController는 화면 기준 도메인으로 작성, jsp 파일 네이밍은 기존 ITMS 방식을 차용하였음
    - RestController는 리소스 기반 URL 작성, 예를 들어 admin_m01_s02에서 사용하는 API라고 하더라도, VisitRestController에 존재.
- 예외 처리 및 HTTP 상태 코드 활용
    - ITMS에서는 모든 Controller가 try-catch로 작성되어 있음
        - 다만, 각각의 컨트롤러에서 별도의 커스텀 에러처리를 하고 있는 것처럼 보이진 않음(모두 try-catch로 이루어져있는 의미가 없음)
        - 따라서 try-catch가 우선 적용되어, @RestControllerAdvice가 실제 적용되지 않는 것으로 확인 됨
    - 따라서 방문관리 시스템에서는 RestControllerAdvice + 커스텀 에러를 통해 Exception Handler를 최대한 전역적으로 해결하고자 했음.
        - ITMS 에서는 에러가 발생했을 때 응답을 BodyResponse로 Wrapping하여, HttpStatus는 항상 200을 반환하고, SuccessYN을 통해 응답의 성공/실패 여부를 확인할 수 있었음
        - 클라이언트에서도 에러가 발생했을 때, 200 응답을 받기 때문에 ajax의 success 콜백에서 별도 분기 처리를 통해 처리하는 것을 확인할 수 있었음
    - 따라서 방문관리 시스템에서는 Response에 400번대, 500번대 HttpStatus를 같이 반환함으로써 ajax의 success, error 콜백을 사용하여 Status에 맞는 응답 처리를 진행하였음
        - 보다 세부적으로 예를 들어 보자면, 세션 인증의 경우에는, ITMS에서는 AJAX 요청을 보내기 전 SessionCheck API를 통해 해결하고 있지만
        - 방문관리 시스템의 AJAX 요청의 경우 Session Exception이 발생한 경우 401에러와 함께 redirectUrl을 반환함으로써 error 콜백에서 로그인 페이지로 전환할 수 있게 처리
        - 혹은 페이지 요청의 경우(Window History, url 직접 접근 등) 어차피 서버에서 HttpStatus 및 응답을 받을 수 없으니, 서버 측에서 로그인 페이지로 리다이렉션 하도록 진행
        - 이러한 작업을 통해 클라이언트 단에서 주도적으로 처리하기 보단, 서버의 응답을 통해 클라이언트의 행동을 지정하도록 하였음.
    - 정리하자면, 기존 API 에서는 AJAX 요청을 보낼 때, 클라이언트 단에서 추가적인 AJAX 요청을 통해 세션을 먼저 확인하고, 이후 AJAX 요청에서 에러가 발생한다 하더라도 200 응답과 함께 Success 콜백에서 처리하고 있지만
    - 현재 API에서는 AJAX 요청은 한 번만 이뤄지고, 세션에 대한 인증 또한 서버에서 진행되며, 클라이언트단에서 HTTP 상태 코드에 따라 작업을 진행하게 됨.

### DTO 기본 컨셉
- ITMS에서는 원시 자료구조인 Map을 통해 모든 Rest API의 요청과 응답을 처리
    - 클라이언트에서 보내는 request를 @RequestBody Map<String, String>으로 받아 service, repository(Mybatis)에서 모두 Map으로 처리하고, 응답 또한 Map으로 내보내 service, controller, 클라이언트 응답 모두 Map으로 처리하는 경우
        - 장점
            - 유연성
                - 클라이언트 전달 데이터 형식에 관계없이 유연한 처리 가능(동적 변경 데이터 or 필드 대응 용이)
            - 간결성
                - 추가적인 DTO 클래스 없이, 코드 복잡성이 줄어들 수 있음
            - 빠른 개발
                - 데이터 처리 클래스나 구조 변경 가능성이 크지 않다면, 신속한 개발이 가능함
        - 단점
            - 타입 안정성 부족
                - ex) 동적 형변환 시 문제 발생 가능 (데이터 바인딩 시 문제 VS 형 변환 시 문제)
                    - 이러한 문제 때문에 DB 컬럼의 거의 모든 타입이 varchar로 되어 있다 사료됨
            - 가독성 및 유지보수 어려움
                - 요청 및 응답에서 어떤 필드가 들어가는지 정확하게 파악하기 어렵고, 유지보수 시 실수가 발생할 위험이 커짐
            - 유효성 검증이 어려움
                - `@Valid` 등의 어노테이션을 사용할 수 없고, 필드별 유효성 검증을 진행하기 어려움
            - 클라이언스 - 서버 간 규약 부족
                - 클라이언트 - 서버 간 명확한 데이터 구조를 제공할 수 없음
                - 특히, API 명세를 작성하기 매우 어려움
- 방문관리 시스템에서의 DTO 사용
    - 조회(select) 작업의 경우, 클라이언트에서 보내는 request(requestBody, requestParam)를 requestDto로 받아 service 계층, repository에서 사용하고 repository의 응답을 QueryResponseDto로 받아서 서비스에서 사용, controller에 ResponseDto로 변환하여 응답하고 client 단에 ResponseDto를 응답으로 보내주는 경우
    - 쓰기(insert, update, delete) 작업의 경우 클라이언트에서 요청하는 request를 requestDto로 받아 service 계층에서 사용, repository로 넘길 때 VisitRequestDto로 변환
        - 장점
            - 타입 안정성
                - DTO 사용 시, 명확한 타입 정의 가능
            - 가독성 향상
                - 요청 및 응답 데이터 명확히 정의
            - Validation 및 제약 조건 적용 가능
                - 검증 용이
            - API 문서화 용이
            - 각 작업에 맞는 DTO 사용(단일 책임)
                - 유지보수 용이 
                    - 코드 재사용성 증가, 데이터 명확성
        - 단점
            - DTO 클래스 및 이를 변환하는 코드 추가적으로 필요
                - 개발 시간 증가, 코드량 증가
            - 유연성 감소
                - API 변경 시, DTO 클래스 수정으로 이어짐

### DTO vs Map 비교 요약
| 기준                | `requestDto` (DTO)                                | `Map`                                    |
|-------------------|-------------------------------------------------|---------------------------------------------|
| **타입 안전성**      | 강함 (컴파일 타임에 체크)                        | 약함 (런타임에 오류 발생 가능)               |
| **가독성**           | 좋음 (명확한 필드 명)                           | 나쁨 (필드가 동적으로 관리되어 직관적이지 않음)|
| **유지보수 용이성**   | 좋음 (명확한 객체 설계, 변경 시 수월)           | 낮음 (데이터 구조 변경 시 코드 수정 필요)    |
| **유연성**           | 낮음 (고정된 구조로 제한됨)                     | 높음 (동적으로 필드 추가 및 삭제 가능)       |
| **성능**             | 좋음 (객체를 이용한 데이터 처리)               | 좋음 (단순히 키-값으로 처리)                |
| **코드 작성의 편리함**| 낮음 (DTO 객체 생성 필요)                       | 높음 (직접적으로 Map으로 처리 가능)         |


### 