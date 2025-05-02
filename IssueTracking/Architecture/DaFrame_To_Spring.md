## 목차
- [목차](#목차)
- [DaFrame -\> SpringBoot 변환 과정 중 컨셉 정리](#daframe---springboot-변환-과정-중-컨셉-정리)
    - [Client 기본 컨셉](#client-기본-컨셉)
        - [브라우저 탐색 이벤트 처리](#브라우저-탐색-이벤트-처리)
        - [HTTP Method 분리](#http-method-분리)
    - [Controller 기본 컨셉](#controller-기본-컨셉)
        - [ViewController(Jsp 페이지 반환)](#viewcontrollerjsp-페이지-반환)
        - [RESTful API](#restful-api)
        - [예외 처리 with HTTP 상태 코드](#예외-처리-with-http-상태-코드)
    - [DTO 기본 컨셉](#dto-기본-컨셉)
    - [DTO vs Map 비교 요약](#dto-vs-map-비교-요약)
    - [Mapper 계층 컨셉](#mapper-계층-컨셉)
- [이슈 정리](#이슈-정리)
    - [Mapper 사용 시 이슈](#mapper-사용-시-이슈)
    - [세팅 이슈](#세팅-이슈)
    - [MyBatis 이슈](#mybatis-이슈)
    - [기능 이슈](#기능-이슈)

## DaFrame -> SpringBoot 변환 과정 중 컨셉 정리
### Client 기본 컨셉
#### 브라우저 탐색 이벤트 처리
- 결국 JSP + SpringBoot를 사용한 SSR 방식이고, SPA를 구현할 수는 없음
    - 기존에는 PPR(Partial Page Rendering) 방식 사용
        - **ITMS에는 PPR은 구현되어 있으나, 새로고침 시 main.jsp 부터 다시 시작 하는 불편함 존재**
    - 현재는 PPR방식에 `window.onpopstate`를 활용, 앞으로 가기/뒤로가기 요청 처리
        - ![Image](https://github.com/user-attachments/assets/164db518-3aa2-4f59-ba2e-892510a21449)
#### HTTP Method 분리
- 기존에는 모든 api 요청은, POST로 처리 / jsp 파일 요청 ajax는 GET으로 처리
- 방문관리 시스템은 동작하고자 하는 Http Method에 따라 Restful Api 규약에 맞춰 다음과 같이 구분 
    - getHtml(페이지 요청), getRequest, postRequest, putRequest, deleteRequest로 구분(Patch 미사용)
    - `getHtml`
        - JSP 페이지 요청 function
        - ![Image](https://github.com/user-attachments/assets/4e90395f-aef3-4987-961f-0f6cf9ac818c)
    - `handleError`
        - Rest API 공통 에러 함수
        - ![Image](https://github.com/user-attachments/assets/5be6ba1e-0f5d-476c-81fe-0772e0493ea9)
    - `getRequest`
        - ![Image](https://github.com/user-attachments/assets/e6ad483c-4642-41c0-993c-7d488ea261ac)
    - `postRequest`
        - ![Image](https://github.com/user-attachments/assets/6c65e7e5-154d-4d2e-bafc-95b5fb58e56a)
    - `putRequest`
        - ![Image](https://github.com/user-attachments/assets/a890978e-0eb5-40f2-990f-87fc2379a345)
    - `deleteRequest`
        - ![Image](https://github.com/user-attachments/assets/30635062-92fb-4e84-a758-c85f1c42c845)

### Controller 기본 컨셉
- 도메인 별로, jsp를 반환하는 ViewController와 Rest api 요청을 처리하는 RestController로 분리
    - ![Image](https://github.com/user-attachments/assets/f8edd7f2-3089-46ac-ab3b-facdf866fd28), ![Image](https://github.com/user-attachments/assets/6f314bdd-0acb-4b33-bf23-3babf4c4bf01)

#### ViewController(Jsp 페이지 반환)
- ViewController에서는 Controller 계층 AJAX 요청 체크 및 ENUM을 통한 페이지 URL 관리
    - ViewController는 화면 기준 도메인으로 작성, jsp 파일 네이밍은 기존 ITMS 방식을 차용하였음
    - Controller 계층 AJAX 요청 체크
        - ![Image](https://github.com/user-attachments/assets/52b574f0-15c4-4071-a4a6-b3effcc92c21)
            - AJAX 요청 시 Partial Page 반환
            - url 직접 접근 or 새로고침 시 layout 포함하여 반환
        - ![Image](https://github.com/user-attachments/assets/c2504d5c-6644-4c05-94c2-ff86358991f2)
            - 일반적으로 `X-Requested-with` 헤더는 일반적으로 Ajax 요청에서만 포함
            - 추가적으로 `XMLHttpRequest`인지 확인
        - ![Image](https://github.com/user-attachments/assets/26615eeb-9dae-4e9e-8c85-9e922caa3ae6)
            - `PageType` ENUM을 통해 페이지 URL 관리

#### RESTful API
- RESTful API 설계
    - **ITMS는 리소스 기반이 아닌 동작 위주의 URL / Http Method는 모두 POST를 사용(페이지 요청 제외) / HTTP 상태 코드 활용하지 않음(에러 발생 시에도 200 반환)**
        - 위와 같은 이유로 RESTful API라고 보기 어려움
        - 따라서 방문관리 시스템에서는 최대한 RESTful api url 규약에 맞춰, 엔드포인트를 설계하고자 했음
    - RestController는 리소스 기반 URL 작성, 예를 들어 ADMIN 도메인인 admin_m01_s02 페이지에서 사용하는 API라고 하더라도, 엔드포인트는 VisitController에 존재(리소스 기반)
        - URL 예시
            - `VisitRestController`
                - ![Image](https://github.com/user-attachments/assets/b69e7cd0-da09-41d6-81b4-d01ad31c16dc)
                - ![Image](https://github.com/user-attachments/assets/3bc72207-7b82-4ac3-9c51-5ae803564bb0)
                - ![Image](https://github.com/user-attachments/assets/0ae954aa-2045-4036-9c8b-e912a1267582)
                - ![Image](https://github.com/user-attachments/assets/bd206765-a348-492c-aa53-0d04e62846a8)
                - ![Image](https://github.com/user-attachments/assets/1bc2cef4-17cf-44b1-b308-5bb1f8ae1595)
                - ![Image](https://github.com/user-attachments/assets/be589b27-408f-4467-a9b7-1abe98bfeea6)
            - [RESTful API 네이밍 래퍼런스](https://restfulapi.net/resource-naming/)
#### 예외 처리 with HTTP 상태 코드
- ITMS에서는 모든 Controller가 try-catch로 작성되어 있음
    - 다만, 각각의 컨트롤러에서 별도의 커스텀 에러처리를 하고 있는 것처럼 보이진 않음(모두 try-catch로 이루어져있는 의미가 없음)
    - 또한 위와 같은 문제 때문에 try-catch가 우선 적용되어, `@RestControllerAdvice`로 작성된 `ExceptionHandler`가 실제로 적용되지 않는 것으로 확인 됨
    - 또한 ITMS에서는 에러가 발생했을 때 응답을 BodyResponse로 Wrapping하여, HttpStatus는 항상 200을 반환하고, SuccessYN을 통해 응답의 성공/실패 여부를 확인함
        - 클라이언트에서도 에러가 발생했을 때, 200 응답을 받기 때문에 ajax의 success 콜백에서 별도 분기 처리를 통해 처리하는 것을 확인할 수 있었음
        - 내부적인 규약에 따른 것이긴 하지만, 일반적으로 통용되는 RESTful API는 아님
- 방문관리 시스템에서는 `@RestControllerAdvice` + 커스텀 에러를 통해 Exception Handler를 최대한 전역적으로 해결하고자 했음.
    - 또한 방문관리 시스템에서는 Response에 400번대, 500번대 HttpStatus를 같이 반환함으로써 ajax의 success, error 콜백을 사용하여 Status에 맞는 응답 처리를 진행하였음
        - [HTTP Method 분리](#http-method-분리) 참고
    - 보다 세부적으로 예를 들어 보자면, 세션 인증 예외처리의 경우에는, ITMS에서는 AJAX 요청을 보내기 전 추가적인 SessionCheck AJAX 요청을 통해 해결하고 있지만
    - ![Image](https://github.com/user-attachments/assets/122dc625-8c80-4d10-a06e-fa6523e037a1)
        - 방문관리 시스템의 AJAX 요청의 경우 Session Exception이 발생한 경우 401에러와 함께 redirectUrl을 반환함으로써 error 콜백에서 로그인 페이지로 전환할 수 있게 처리함
        - 혹은 페이지 요청의 경우(Window History, url 직접 접근 등) 어차피 서버에서 HttpStatus 및 응답을 받을 수 없으니, 서버 측에서 로그인 페이지로 리다이렉션 하도록 진행
    - 위와 같은 작업을 통해 클라이언트 단에서 주도적으로 처리하기 보단, 서버의 응답을 통해 클라이언트의 행동을 지정하도록 하였음.
- 정리하자면, 세션 인증에서 ITMS API에서는 AJAX 요청을 보낼 때, 클라이언트 단에서 선제적인 AJAX 요청을 통해 세션을 먼저 확인하고, 이후 AJAX 요청에서 에러가 발생한다 하더라도 200 응답과 함께 Success 콜백에서 처리하고 있음
    - 불필요한 통신 + 컨트롤러 기준 try-catch로 에러 핸들링 + RESTFul Api 규약에 맞지 않는 Http Status
- 현재 API에서는 AJAX 요청은 한 번만 이뤄지고, ExceptionHandler 또한 전역적으로 진행되며, 클라이언트단에서 HTTP 상태 코드에 따라 작업을 진행하게 됨.

### DTO 기본 컨셉
- 기존 ORM(JPA) 사용 시
    - ![Image](https://github.com/user-attachments/assets/2c476c1f-a20c-4c32-99c7-41916fd4d4cc)
        - Service 계층 및 Repository 계층에서 Entity를 사용하고, **영속성 컨텍스트** 관리를 통한 용이성 제공
        - Controller 계층과의 명확한 분리를 통해 여러 장점 제공
- ITMS에서는 원시 자료구조인 Map을 통해 모든 Rest API의 요청과 응답을 처리
    - ![Image](https://github.com/user-attachments/assets/3e948760-2f46-468e-8aa0-189d7887c310)
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
    - MyBatis는 Entity LifeCycle을 관리하지 않기에 영속성 컨텍스트에서 관리되진 않지만, 다음과 같은 이유로 DTO를 사용
    - 조회(select) 작업의 경우
        - ![Image](https://github.com/user-attachments/assets/3eb9ac41-d45f-43b7-8bae-63011b5d8df6)
    - 쓰기(insert, update, delete) 작업의 경우
        - ![Image](https://github.com/user-attachments/assets/bcc89692-6f1a-4a0b-8331-246b87d6cf23)
        - 장점
            - 타입 안정성
                - DTO 사용 시, 명확한 타입 정의 가능
            - 가독성 향상
                - 요청 및 응답 데이터 명확히 정의
            - Validation 및 제약 조건 적용 가능
                - 검증 용이
            - API 문서화 용이(Swagger, Spring Rest Docs 등)
            - 각 작업에 맞는 DTO 사용(단일 책임)
                - 유지보수 용이 
                    - 코드 재사용성 증가, 데이터 명확성
        - 단점
            - DTO 클래스 및 이를 변환하는 코드 추가적으로 필요
                - 개발 시간 증가, 코드량 증가
            - 유연성 감소
                - API 변경 시, DTO 클래스 수정으로 이어짐
- [DTO 사용 관련 고찰](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Architecture/MyBatis-DTO%2CVO%2CEntity.md)
    - MyBatis에서의 DTO 사용에 대한 고민 흔적입니다.

### DTO vs Map 비교 요약
| 기준                | `requestDto` (DTO)                                | `Map`                                    |
|-------------------|-------------------------------------------------|---------------------------------------------|
| **타입 안전성**      | 강함 (컴파일 타임에 체크)                        | 약함 (런타임에 오류 발생 가능)               |
| **가독성**           | 좋음 (명확한 필드 명)                           | 나쁨 (필드가 동적으로 관리되어 직관적이지 않음)|
| **유지보수 용이성**   | 좋음 (명확한 객체 설계, 변경 시 수월)           | 낮음 (데이터 구조 변경 시 코드 수정 필요)    |
| **유연성**           | 낮음 (고정된 구조로 제한됨)                     | 높음 (동적으로 필드 추가 및 삭제 가능)       |
| **성능**             | 좋음 (객체를 이용한 데이터 처리)               | 좋음 (단순히 키-값으로 처리)                |
| **코드 작성의 편리함**| 낮음 (DTO 객체 생성 필요)                       | 높음 (직접적으로 Map으로 처리 가능)         |


### Mapper 계층 컨셉
- DTO 간 자동 변환을 진행하기 위해, `org.mapstruct.Mapper`를 사용
- 요청 타입과 응답 타입만 지정해주면, 자동 변환 진행
    - 필드 명이 다른 경우, `@Mapping(target = " ", source = " ")`을 통해 지정 요망
- 변환 뿐 아니라 간단한 포맷팅, 마스킹 등의 Java 메서드(표현식 등) 사용 가능(`@expression` or `@qualifiedByName`, `@Named` 사용)

>> cf) 이름 혼용 방지를 위해 기존 MyBatis Mapper를 의미하던 Mapper 계층은, Repository 계층으로 이름 변경
- 예시 코드
```java
/**
 * 도메인 계층 간 변환 역할을 하는 Mapper 인터페이스
 * mapstruct.mapper를 사용, 필드명이 같은 경우 자동 변환(build 폴더에 MapperImpl 생성)
 * 만약 빌더 패턴이 생성되어 있으면 빌더로 생성/ @Getter와 @AllArgsConstruct만 있는 경우 / @Setter와 @NoargsConstruct가 있는 경우 모두 다르게 생성
 * 1. 조회 결과의 QueryResponseDto를 ResponseDto로 변환
 * 2. RequestDto를 쓰기 작업(insert, update, delete 등)의 WriteRequestDto 로 변환
 */
@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "adminId", source = "id")
    @Mapping(target = "adminPassword", source = "pass")
    @Mapping(target = "adminType", source = "type")
    @Mapping(target = "adminSysCode", source = "syscode")
    AdminResponseDto.AdminResponse adminQueryResponseToAdminResponse(AdminQueryResponseDto.AdminQueryResponse adminQueryResponse);

    @Mapping(target = "lockerRoomNumber", source = "codeNm")
    @Mapping(target = "visitorName", source = "vstNm", defaultValue = "-") //defalutValue: null인 경우 기본 값
    AdminResponseDto.LockerRoomResponse lockerRoomQueryResponseToLockerRoomResponse(AdminQueryResponseDto.LockerRoomQueryResponse lockerRoomQueryResponse);

    List<AdminResponseDto.LockerRoomResponse> lockerRoomQueryResponseListToLockerRoomResponseList(List<AdminQueryResponseDto.LockerRoomQueryResponse> lockerRoomQueryResponseList);


    AdminWriteRequestDto.AdminActionHistoryCreate visitUpdateRequestToAdminActionHistoryCreate(VisitRequestDto.VisitUpdateRequest visitUpdateRequest);

    AdminWriteRequestDto.AdminActionHistoryCreate visitDeleteRequestToAdminActionHistoryCreate(VisitRequestDto.VisitDeleteRequest visitDeleteRequest);

    AdminWriteRequestDto.AdminUpdate adminUpdateRequestToAdminUpdate(AdminRequestDto.AdminUpdateRequest adminUpdateRequest);
}
```

```java
/**
 * 도메인 계층 간 변환 역할을 하는 Mapper 인터페이스
 * mapstruct.mapper를 사용, 필드명이 같은 경우 자동 변환(build 폴더에 MapperImpl 생성)
 * 만약 빌더 패턴이 생성되어 있으면 빌더로 생성/ @Getter와 @AllArgsConstruct만 있는 경우 / @Setter와 @NoargsConstruct가 있는 경우 모두 다르게 생성
 * 1. 조회 결과의 QueryResponseDto를 ResponseDto로 변환
 * 2. RequestDto를 쓰기 작업(insert, update, delete 등)의 WriteRequestDto 로 변환
 */
@Mapper(componentModel = "spring")
public interface VisitMapper {
    @Mapping(target = "managerId", source = "empId")
    @Mapping(target = "managerName", source = "empNm")
    @Mapping(target = "managerPosition", source = "titleNm")
    @Mapping(target = "managerDepartment", source = "orgNm")
    @Mapping(target = "managerPhoneNumber", source = "hpNo")
    @Mapping(target = "managerOfficeNumber", source = "officeTel")
    VisitResponseDto.ManagerResponse managerQueryResponseToManagerResponse(VisitQueryResponseDto.ManagerQueryResponse managerQueryResponse);

    List<VisitResponseDto.ManagerResponse> managerQueryResponseListToManagerResponseList(List<VisitQueryResponseDto.ManagerQueryResponse> managerQueryResponseList);

    @Mapping(target = "visitStartYmd", expression = "java(visitRequest.getVisitDateStart().substring(0, 8))")
    @Mapping(target = "visitStartHour", expression = "java(visitRequest.getVisitDateStart().substring(8, 10))")
    @Mapping(target = "visitStartMinute", expression = "java(visitRequest.getVisitDateStart().substring(10, 12))")
    @Mapping(target = "visitEndYmd", expression = "java(visitRequest.getVisitDateEnd().substring(0, 8))")
    @Mapping(target = "visitEndHour", expression = "java(visitRequest.getVisitDateEnd().substring(8, 10))")
    @Mapping(target = "visitEndMinute", expression = "java(visitRequest.getVisitDateEnd().substring(10, 12))")
    VisitWriteRequestDto.VisitHistoryCreate visitRequestToVisitHistoryCreate(VisitRequestDto.VisitRequest visitRequest);

//    @InheritConfiguration(name = "visitRequestToVisitMasterCreate")
    VisitWriteRequestDto.VisitMasterCreate visitRequestToVisitMasterCreate(VisitRequestDto.VisitRequest visitRequest);

    @Mapping(target = "cDiskImportSize", source = "cDiskImportSize")
    @Mapping(target = "dDiskImportSize", source = "dDiskImportSize")
    @Mapping(target = "eDiskImportSize", source = "eDiskImportSize")
    VisitWriteRequestDto.VisitItemCreate visitRequestToVisitItemCreate(VisitRequestDto.VisitRequest visitRequest);

    @Mapping(target = "rowNumber", source = "rownum")
    @Mapping(target = "visitorName", source = "vstNm", qualifiedByName = "maskSecondCharacter") //인터페이스 하단부 변경 메서드(maskSecondCharacter) 사용
    //자바 표현식 사용(formatDate는 인터페이스 하단부 구현)
    @Mapping(target = "visitDateStart", expression = "java(formatDate(visitQueryResponse.getVstStaYmd(), visitQueryResponse.getVstStaHh(), visitQueryResponse.getVstStaMm()))")
    @Mapping(target = "visitDateEnd", expression = "java(formatDate(visitQueryResponse.getVstEndYmd(), visitQueryResponse.getVstEndHh(), visitQueryResponse.getVstEndMm()))")
    @Mapping(target = "visitorCompany", source = "comNm")
    @Mapping(target = "visitPurpose", source = "codeNm")
    @Mapping(target = "visitorPhoneNumber", source = "hpNo", qualifiedByName = "maskPhoneNumber")
    @Mapping(target = "managerName", source = "apprId", qualifiedByName = "maskSecondCharacter")
    @Mapping(target = "accessCardNumber", source = "passNo")
    @Mapping(target = "transferItem", source = "itemNm")
    @Mapping(target = "vehicleNumber", source = "vstCarNo")
    @Mapping(target = "lockerRoom", source = "roomId")
    @Mapping(target = "visitState", source = "state")
    VisitResponseDto.VisitResponse visitQueryDtoToVisitResponse(VisitQueryResponseDto.VisitQueryResponse visitQueryResponse);

    List<VisitResponseDto.VisitResponse> visitQueryResponseListToVisitResponseList(List<VisitQueryResponseDto.VisitQueryResponse> visitQueryResponseList);

    VisitWriteRequestDto.VisitMasterUpdate visitUpdateRequestToVisitMasterUpdate(VisitRequestDto.VisitUpdateRequest visitUpdateRequest);

    @Mapping(target = "visitStartYmd", expression = "java(visitUpdateRequest.getVisitDateStart().substring(0, 8))")
    @Mapping(target = "visitStartHour", expression = "java(visitUpdateRequest.getVisitDateStart().substring(8, 10))")
    @Mapping(target = "visitStartMinute", expression = "java(visitUpdateRequest.getVisitDateStart().substring(10, 12))")
    @Mapping(target = "visitEndYmd", expression = "java(visitUpdateRequest.getVisitDateEnd().substring(0, 8))")
    @Mapping(target = "visitEndHour", expression = "java(visitUpdateRequest.getVisitDateEnd().substring(8, 10))")
    @Mapping(target = "visitEndMinute", expression = "java(visitUpdateRequest.getVisitDateEnd().substring(10, 12))")
    VisitWriteRequestDto.VisitHistoryUpdate visitUpdateRequestToVisitHistoryUpdate(VisitRequestDto.VisitUpdateRequest visitUpdateRequest);

    VisitWriteRequestDto.VisitItemDetailCreate visitUpdateRequestToItemCreate(VisitRequestDto.VisitUpdateRequest visitUpdateRequest);

    @Mapping(target = "cDiskImportSize", source = "cDiskImportSize")
    @Mapping(target = "cDiskExportSize", source = "cDiskExportSize")
    @Mapping(target = "dDiskImportSize", source = "dDiskImportSize")
    @Mapping(target = "dDiskExportSize", source = "dDiskExportSize")
    @Mapping(target = "eDiskImportSize", source = "eDiskImportSize")
    @Mapping(target = "eDiskExportSize", source = "eDiskExportSize")
    VisitWriteRequestDto.VisitItemUpdate visitUpdateRequestToItemUpdate(VisitRequestDto.VisitUpdateRequest visitUpdateRequest);

    @Mapping(target = "vstId", source = "vstId")
    @Mapping(target = "vstNo", source = "vstNo")
    @Mapping(target = "visitorName", source = "vstNm")
    @Mapping(target = "visitorCompany", source = "comNm")
    @Mapping(target = "phonePrefix", expression = "java(visitHistoryDetailQueryResponse.getHpNo().substring(0, 3))")
    @Mapping(target = "phoneMiddle", expression = "java(visitHistoryDetailQueryResponse.getHpNo().substring(3, 7))")
    @Mapping(target = "phoneEnd", expression = "java(visitHistoryDetailQueryResponse.getHpNo().substring(7, 11))")
    @Mapping(target = "visitPurpose", source = "vstPurpose")
    @Mapping(target = "otherPurpose", source = "vstPurposeEtc")
    @Mapping(target = "visitLocation", source = "vstZone")
    @Mapping(target = "visitClient", source = "companyId")
    @Mapping(target = "otherClient", source = "companyName")
    @Mapping(target = "vehicleModel", source = "vstCarType")
    @Mapping(target = "vehicleNumber", source = "vstCarNo")
    @Mapping(target = "visitDateStart", expression = "java(formatDate(visitHistoryDetailQueryResponse.getVstStaYmd(), visitHistoryDetailQueryResponse.getVstStaHh(), visitHistoryDetailQueryResponse.getVstStaMm()))")
    @Mapping(target = "visitDateEnd", expression = "java(formatDate(visitHistoryDetailQueryResponse.getVstEndYmd(), visitHistoryDetailQueryResponse.getVstEndHh(), visitHistoryDetailQueryResponse.getVstEndMm()))")
    @Mapping(target = "accessCardNumber", source = "passNo")
    @Mapping(target = "idCardCheckYn", source = "identityYn")
    @Mapping(target = "systemCheckYn", source = "systemCk")
    @Mapping(target = "lockerRoom", source = "roomId")
    @Mapping(target = "visitState", source = "state")
    VisitResponseDto.VisitHistoryDetailResponse visitHistoryDetailQueryResponseToVisitHistoryDetailResponse(VisitQueryResponseDto.VisitHistoryDetailQueryResponse visitHistoryDetailQueryResponse);

    @Mapping(target = "transferItem", source = "itemNm")
    @Mapping(target = "transferReason", source = "itemReason")
    @Mapping(target = "cDiskImportSize", source = "inDisk")
    @Mapping(target = "cDiskExportSize", source = "outDisk")
    @Mapping(target = "dDiskImportSize", source = "DInDisk")
    @Mapping(target = "dDiskExportSize", source = "DOutDisk")
    @Mapping(target = "eDiskImportSize", source = "EInDisk")
    @Mapping(target = "eDiskExportSize", source = "EOutDisk")
    @Mapping(target = "otherDiskImportSize", source = "FInDisk")
    @Mapping(target = "otherDiskExportSize", source = "FOutDisk")
    @Mapping(target = "diskSizeChangeReason", source = "itemInReason")
    VisitResponseDto.VisitItemDetailResponse visitItemDetailQueryResponseToVisitItemDetailResponse(VisitQueryResponseDto.VisitItemDetailQueryResponse visitItemDetailQueryResponse);

    // -----------------------------
    // 유틸리티 메서드
    // -----------------------------

    // 두번째 글자 마스킹
    @Named("maskSecondCharacter")
    default String maskSecondCharacter(String name) {
        if (name != null && name.length() > 1) {
            char[] arr = name.toCharArray();
            arr[1] = '*';
            return new String(arr);
        }
        return name;
    }

    // 날짜 포매팅
    default String formatDate(String ymd, String hh, String mm) {
        if (ymd == null || ymd.length() < 8) {
            return "";
        }
        return String.format("%s/%s/%s %s:%s",
                ymd.substring(0, 4),   // 년
                ymd.substring(4, 6),   // 월
                ymd.substring(6, 8),   // 일
                hh,                    // 시
                mm                     // 분
        );
    }

    // 번호 마스킹
    @Named("maskPhoneNumber")
    default String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() > 7) {
            return phoneNumber.substring(0, 3)
                    + "-****-"
                    + phoneNumber.substring(7);
        }
        return phoneNumber;
    }
}
```
- 쿼리 단에서 Alias 등을 사용하면 위와 같이 Mapper 사용을 하지 않을 수 있지만(Alias를 사용하여 QueryResponse 필드명과 일치)
    - 코드 수정이 쿼리에 영향을 끼치는 것은 적합하지 않다 생각해 위와 같은 구조를 취하였음
    - 물론 DB 컬럼과 같은 필드명을 사용한다면, 위와 같은 @Mapping은 모두 필요 없음
- 위에 대한 고민은 [DTO 사용 관련 고찰](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Architecture/MyBatis-DTO%2CVO%2CEntity.md)에 자세히 나와 있으며, 일부를 발췌하자면 다음과 같다.
```
mapstruct Mapper를 사용하여 @Mapping을 사용하여 필드명을 변환해주고 있는데, 여기서 고민이 생김 -> 20개 이상의 컬럼을 조회하는 SQL문이 있는데 이 경우 @Mapping이 너무 많이 생기게 됨

그렇다고 static 메서드를 써서 생성하자니, 필드 간의 매핑이 명시적으로 드러나지 않는다고 생각 -> 컬럼명(QueryResponseDto)와 ResponseDto 간 필드명이 대충 비슷하기만 하다면 크게 문제가 되지 않겠지만 비정규화가 많이 되어있는 테이블이기 때문에 지금은 상당부분 상이함(테이블 명과 일치 시킬 수 있다면 아무런 문제가 되지 않는다.)

그리고 보일러플레이트 코드 또한 너무 많아지는 것 같고... 만약 static method를 사용하여 매핑을 진행한다면, mapper 계층을 열어보고, of 메서드를 본 다음에야 어떤 필드가 어떤 필드와 매핑되는지 찾아본 다음에야 알게 될 것임.

그런데 그렇다고 모든 매핑을 @Mapping으로 처리하자니, 추후에 마스킹이나 포매팅 등 QueryResponseDto -> responseDto로 변환하는데 별도 로직이 생긴다면, 모든 @Mapping을 삭제하고 of 메서드 등으로 수정해야 하잖아. 이건 너무 변경에 닫혀있는거 아닐까??

근데 이렇게 생각해보면, 사실 QueryResponseDto와 responseDto로 분리한 이유가 위와 같은 별도 로직을 위해서가 가장 크니까.. 아예 mapstruct mapper를 사용 안 하는게 맞나 싶기도 하고??

완전 간단한 변환 로직을 제외하고는 다 static 메서드 등을 사용하여 변환을 하는게 맞는 것 같은데..
```

## 이슈 정리
### Mapper 사용 시 이슈
- [상속 구조 사용 시 Mapper 에러](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Framework/Mapstruct_mapper_extends.md)
- [Mapstruct 매핑 관련 에러(자바빈 프로퍼티)](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Framework/MapStruct_Unmapped%20target%20property.md)

### 세팅 이슈
- [DaFrame 내부 톰캣 세팅](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Setting/DaFrameIntellijSetting.md)
- [폐쇄망 gradle 세팅](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Setting/Intellij_gradle_setting_error.md)
- [폐쇄망 Dependency 추가 세팅](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Setting/Intellij_oracle_setting_error.md)

### MyBatis 이슈
- [MyBatis-Dto 이슈](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Framework/MyBatis-Dto%20Mapping%20Error.md)
- [MyBatis/RequestDto Enum 사용](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Framework/MyBatis-Dto%20Mapping%20Error.md)

### 기능 이슈
- [Timepicker-addon](https://github.com/googoo9918/TIL/blob/main/IssueTracking/Framework/timepicker-addon_error.md)