## MyBatis에서의 DTO, VO, Entity
- MyBatis에서는 서비스 계층에서 영속성 객체가 아니라 DTO를 사용하는가?
    - 그렇다. DTO를 쓰는 것이 일반적이다.
    - MyBatis를 사용할 경우에는, 도메인 객체(Entity)를 사용하는 것보다
        - 요청 DTO -> 도메인 DTO/VO -> Mapper 전달 형태로 분리하는 것이 일반적
        - MyBatis는 **Entity Lifecycle을 관리하지 않기 때문**
            - MyBatis는 단순 SQL Mapper이다. 그저 값이 있는 객체를 SQL에 매핑해서 실행할 뿐...
            - 해당 객체는 영속성 컨텍스트에서 관리되지 않는다.
                - 당연히 dirty checking, cascade, lazy loading등을 적용할 수 없음
                - Entity 생성/수정 시 자동 이벤트를 처리할 수 없다는 것이다.
            - 객체 중심 제어가 아닌, SQL 중심 제어인 것이다..
- 그렇다면 VO와 DTO의 차이가 무엇인가?
    - DTO는 이미 잘 알고 있음
    - MyBatis의 경우에는, VO와 Entity의 경계 자체가 명확하지 않음
        - 그냥 역할에 대한 명확한 구분 정도?
    - 코드 상으로는 차이가 거의 없음
        - 둘다 POJO이고, 클래스 이름이나 사용하는 컨텍스트, 내부 필드 성격 등을 통해 의도적으로 구분할 뿐임
    - 주로 다음과 같이 분류
        - VO
            - `VisitorVo`
            - 조회(select) 결과 전용, 화면 구성 목적
            - 혹은 InsertDto 등에 포함하여 사용
        - Entity 처럼 쓰이는 도메인 객체
            - `Visitor`
            - Insert/Update/Delete 공용, 도메인 중심 등
            - Entity는 DB 테이블 구조와 1:1 매핑되게끔 설계 될 것임
    - 그냥 읽기 전용과 쓰기/변경 목적의 객체를 구분하는 목적일 뿐임!
- 정리해보자면, JPA 사용 시에는
    - requestDto(controller) -> 변환(mapper) -> Entity(service) -> DB 처리(repository) -> 반환(service) -> 변환(mapper) -> responseDTO로 반환(controller) 이었다면
    - MyBatis 사용 시에는
        - Select의 경우
            - requestDto(controller) -> Dto그대로 사용(혹은 필요한 경우 service에서 전처리) -> mybatis mapper 호출(service) -> DB 처리(mapper) -> (Record or VO)로 반환(service) -> 변환(RecordDto -> ResponseDto) -> controller로 반환
            - VO와 ResponseDto를 분리하는 이유는, DB 의존도를 낮추기 위해
                - DB가 변경된다고 API까지 변경되면 안 되겠지 
        - Insert/Update/Delete의 경우
            - requestDto(controller) -> 변환(mapper) -> InsertDto or VO (service) -> mybatis mapper -> Record 등으로 반환 -> 변환(RecordDto -> ResponseDto) -> controller로 반환
                - 클라이언트 전용 구조와, 내부 로직 전용 구조를 위해 requestDto와 InsertDto 분리
                - 반환 타입이 int나 long(영향을 받은 행 수 반환)인 경우가 많음
                - 후속 작업을 해야 하는 경우, id값을 미리 받고 다시 조회를 통해 진행하는 경우가 일반적
- 내가 생각하는 이상적 설계의 결론은 다음과 같다
1. select 시
: requestDto 사용 -> mapper 반환값은 VO 사용으로 바인딩 -> VO를 ResponseDto로 변환하여 컨트롤러에서 클라이언트로 반환

2. 쓰기 작업 시
: reuqestDto 사용 -> requestDto를 테이블에 맞는 객체로 변환([TableName]WriteVO)


### 생각이 좀 바뀜
- 생각이 좀 바뀌었다.
    - select 시, 조회 결과를 꼭 VO로 받아야 하는가??
        - VO는 DB 테이블과 1:1로 매핑되어 있어야 하니, 단일 테이블에 대한 쿼리면 VO를 작성하는게 맞겠으나...
        - 사실 이럴 일이 거의 없음
        - select는 웬만하면 join이랑 같이 나가니까!!!
    - 따라서 웬만하면 DTO로 필요 컬럼만 골라서 받자
        - 그렇다면 responseDto로 한 번 더 변환하는게 맞는가??
        - 이건 맞는 것 같음
        - 컬럼을 그대로 내보내는게 아니라, 후처리가 필요할 수 있음
        - 그리고 DB의 영향도를 차단해야함(의존성 분리 + 독립성 제공)
    - 그럼 사실 원래 생각하던거에서 VO가 DTO로 바뀐건데..
        - VO와 DTO의 역할을 명확히 했다는 점에서 만족하자.
- 그렇다면 쓰기작업에서는 어떨까?
    - 이것도 마찬가지로, requestDto를 그대로 사용할 수 있는 케이스가 있을까?
        - 저장 구조와 요청 구조가 일치할 때
        - 한 개 Table에 단건으로 요청 날릴 때
        - Dto를 그대로 사용해도 될 것 같음.
    - WriteVO로 변환하는 경우는?
        - 한 개의 DTO가 여러 Table에 insert 되는 경우
        - DB 작업을 위해 별도 작업이 필요한 경우
            - ex) 한 개의 필드 -> 3개의 필드로 분리
            - ex2) 하이픈 제거 등
- 그렇다면, 조회 결과 Vo와 WriteVo는 왜 엄격성에 차이가 존재하는가?
    - selectVo는 있는 그대로 수신하지 않으면(컬럼을 정확히 매핑하지 않으면)
        - 값이 누락되거나, 매핑 오류가 발생하거나, 의도하지 않은 구조가 만들어질 수 있음
    - WriteVo는 무엇을 저장할지만 표현하면 된다.

### 한 가지 의문점
- 한 가지 의문점이 생김
    - select의 조회 결과를 받는 DTO는, 컬럼명을 그대로 따라가지 않고 alias를 사용해서 받아도 괜찮을까?
    - 옳지 않다는 생각이 들었음
    - DTO 필드명 변경을 한다고 했을 때, SQL까지 변경 영향도가 끼치면 안될테니..
- 해결책
    - alias를 사용하지 않고, `mapUnderscoreToCamelCase` 정도만 사용하여 QueryResponseDto의 네이밍을 DB 컬럼에 맞춤
        - `mapUnderscoreToCamelCase` 사용하지 않으면, Java 네이밍 문제 존재
        - 스태틱 매서드(of)나, Mybatis Mapper의 @Mapping 등을 사용, ResponseDto와 필드명을 매핑
        - 물론 테이블 컬럼 명이 스네이크 케이스가 아니라면 어떻게 할 것인가? 에 대한 예외 정도는 남아있는 듯(이 경우는 resultMap을 사용하는게 좋지 않을까)
        - 객체 간 관계를 매핑해야 하는 경우에는, 이 방식만으로 해결할 수 없음
        - 또한 매핑이 실패해도 에러가 명시적으로 드러나진 않아서 공수가 좀 더 들어감
        - DB 테이블의 네이밍 컨벤션이 잘 맞는 경우에 최적의 선택임!!
- 다른 방법
    - myBatis의 ResultMap을 사용할 수 있을 듯
        - 다만, alias를 사용하지 않을 뿐이지 xml이 너무 복잡해지고, 쿼리의 수정이냐 mapper의 수정이냐 일뿐 큰 차이가 느껴지지 않았음
        - 물론 독립시켜서 관리한다는 점에서 그 의의는 분명히 존재한다.
        - 매핑 로직이 MyBatis XML 설정 안에 들어가기 때문

## 또 한 가지 의문점
```
mapstruct Mapper를 사용하여 @Mapping을 사용하여 필드명을 변환해주고 있는데, 여기서 고민이 생긴거지. 20개 이상의 컬럼을 조회하는 SQL문이 있는데 이 경우 @Mapping이 너무 많이 생기게 되는거야.

그렇다고 static 메서드인 of를 쓰자니, 필드 간의 매핑이 명시적으로 드러나지 않는다고 생각했어. 컬럼명(QueryResponseDto)와 ResponseDto 간 필드명이 대충 비슷하기만 하다면 크게 문제가 되지 않겟지만. 지금은 상당히 상이하거든.

그리고 보일러플레이트 코드 또한 너무 많아지는 것 같고... 만약 이 코드를 처음 보는 사람이라면, mapper 계층을 열어보고, of 메서드를 본 다음에야 어떤 필드가 어떤 필드와 매핑되는지 눈알 빠지게 찾아본 다음에야 알게 되겠지.

그런데 그렇다고 모든 매핑을 @Mapping으로 처리하자니, 추후에 마스킹이나 포매팅 등 QueryResponseDto -> responseDto로 변환하는데 별도 로직이 생긴다면, 모든 @Mapping을 삭제하고 of 메서드 등으로 수정해야 하잖아. 이건 너무 변경에 닫혀있는거 아닐까??

근데 이렇게 생각해보면, 사실 QueryResponseDto와 responseDto로 분리한 이유가 위와 같은 별도 로직을 위해서가 가장 크니까.. 아예 mapstruct mapper를 사용 안 하는게 맞나 싶기도 하고??

완전 간단한 변환 로직을 제외하고는 다 static 메서드 등을 사용하여 변환을 하는게 맞는 것 같은데
```
- 위는 프롬프트 중 일부이다.
    - mybatis mapstruct의 이해가 부족해서 생긴 의문점이었던 듯
    - myBatis 인터페이스 내의 별도 커스텀 로직을 구현할 수도 있고, 별도 유틸리트 클래스를 만들 수도 있음
    - 또한 dateFormat을 만들 수도 있음..
    - 다시 잘 공부해보자.

- 기존 코드
```java
package com.inc.visit.domain.admin.mapper;

import com.inc.visit.domain.admin.dto.AdminQueryResponseDto;
import com.inc.visit.domain.admin.dto.AdminResponseDto;
import com.inc.visit.domain.admin.vo.AdminSelectVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "adminId", source = "id")
    @Mapping(target = "adminPassword", source = "pass")
    @Mapping(target = "adminType", source = "type")
    @Mapping(target = "adminSysCode", source = "syscode")
    AdminResponseDto.AdminResponse adminVoToAdminResponse(AdminSelectVo.AdminVo adminVo);

   default AdminResponseDto.VisitResponse visitQueryDtoToVisitResponse(AdminQueryResponseDto.VisitQueryResponse visitQueryResponse) {
       return AdminResponseDto.VisitResponse.of(visitQueryResponse);
   }

    List<AdminResponseDto.VisitResponse> visitQueryResponseListToVisitResponseList(List<AdminQueryResponseDto.VisitQueryResponse> visitQueryResponseList);

    @Mapping(target = "lockerRoomNumber", source = "codeNm")
    @Mapping(target = "visitorName", source = "vstNm", defaultValue = "-")
    AdminResponseDto.LockerRoomResponse lockerRoomQueryResponseToLockerRoomResponse(AdminQueryResponseDto.LockerRoomQueryResponse lockerRoomQueryResponse);

    List<AdminResponseDto.LockerRoomResponse> lockerRoomQueryResponseListToLockerRoomResponseList(List<AdminQueryResponseDto.LockerRoomQueryResponse> lockerRoomQueryResponseList);

    default AdminResponseDto.VisitHistoryDetailResponse visitHistoryDetailQueryResponseToVisitHistoryDetailResponse(AdminQueryResponseDto.VisitHistoryDetailQueryResponse visitHistoryDetailQueryResponse) {
        return AdminResponseDto.VisitHistoryDetailResponse.of(visitHistoryDetailQueryResponse);
    }

    default AdminResponseDto.VisitManagerDetailResponse visitManagerDetailQueryResponseToVisitManagerDetailResponse(AdminQueryResponseDto.VisitManagerDetailQueryResponse visitManagerDetailQueryResponse){
        return AdminResponseDto.VisitManagerDetailResponse.of(visitManagerDetailQueryResponse);
    }

    default AdminResponseDto.VisitItemDetailResponse visitItemDetailQueryResponseToVisitItemDetailResponse(AdminQueryResponseDto.VisitItemDetailQueryResponse visitItemDetailQueryResponse){
        return AdminResponseDto.VisitItemDetailResponse.of(visitItemDetailQueryResponse);
    }
```

- 현재 코드
    - 물론 of 메서드는 다 주석처리 해주면 된다.
```java
package com.inc.visit.domain.admin.mapper;

import com.inc.visit.domain.admin.dto.AdminQueryResponseDto;
import com.inc.visit.domain.admin.dto.AdminResponseDto;
import com.inc.visit.domain.admin.vo.AdminSelectVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "adminId", source = "id")
    @Mapping(target = "adminPassword", source = "pass")
    @Mapping(target = "adminType", source = "type")
    @Mapping(target = "adminSysCode", source = "syscode")
    AdminResponseDto.AdminResponse adminVoToAdminResponse(AdminSelectVo.AdminVo adminVo);

    @Mapping(target = "rowNumber",          source = "rownum")
    @Mapping(target = "visitorName",        source = "vstNm", qualifiedByName = "maskSecondCharacter")
//    @Mapping(target = "vstNo",              source = "vstNo")
    @Mapping(target = "visitDateStart",     expression = "java(formatDate(visitQueryResponse.getVstStaYmd(), visitQueryResponse.getVstStaHh(), visitQueryResponse.getVstStaMm()))")
    @Mapping(target = "visitDateEnd",       expression = "java(formatDate(visitQueryResponse.getVstEndYmd(), visitQueryResponse.getVstEndHh(), visitQueryResponse.getVstEndMm()))")
    @Mapping(target = "visitorCompany",     source = "comNm")
    @Mapping(target = "visitPurpose",       source = "codeNm")
    @Mapping(target = "visitorPhoneNumber", source = "hpNo", qualifiedByName = "maskPhoneNumber")
    @Mapping(target = "managerName",        source = "apprId", qualifiedByName = "maskSecondCharacter")
    @Mapping(target = "accessCardNumber",   source = "passNo")
    @Mapping(target = "transferItem",       source = "itemNm")
    @Mapping(target = "vehicleNumber",      source = "vstCarNo")
    @Mapping(target = "lockerRoom",         source = "roomId")
    @Mapping(target = "visitState",         source = "state")
    AdminResponseDto.VisitResponse visitQueryDtoToVisitResponse(AdminQueryResponseDto.VisitQueryResponse visitQueryResponse);
//    default AdminResponseDto.VisitResponse visitQueryDtoToVisitResponse(AdminQueryResponseDto.VisitQueryResponse visitQueryResponse) {
//        return AdminResponseDto.VisitResponse.of(visitQueryResponse);
//    }

    List<AdminResponseDto.VisitResponse> visitQueryResponseListToVisitResponseList(List<AdminQueryResponseDto.VisitQueryResponse> visitQueryResponseList);

    @Mapping(target = "lockerRoomNumber", source = "codeNm")
    @Mapping(target = "visitorName", source = "vstNm", defaultValue = "-")
    AdminResponseDto.LockerRoomResponse lockerRoomQueryResponseToLockerRoomResponse(AdminQueryResponseDto.LockerRoomQueryResponse lockerRoomQueryResponse);

    List<AdminResponseDto.LockerRoomResponse> lockerRoomQueryResponseListToLockerRoomResponseList(List<AdminQueryResponseDto.LockerRoomQueryResponse> lockerRoomQueryResponseList);

    default AdminResponseDto.VisitHistoryDetailResponse visitHistoryDetailQueryResponseToVisitHistoryDetailResponse(AdminQueryResponseDto.VisitHistoryDetailQueryResponse visitHistoryDetailQueryResponse) {
        return AdminResponseDto.VisitHistoryDetailResponse.of(visitHistoryDetailQueryResponse);
    }

    default AdminResponseDto.VisitManagerDetailResponse visitManagerDetailQueryResponseToVisitManagerDetailResponse(AdminQueryResponseDto.VisitManagerDetailQueryResponse visitManagerDetailQueryResponse){
        return AdminResponseDto.VisitManagerDetailResponse.of(visitManagerDetailQueryResponse);
    }

    default AdminResponseDto.VisitItemDetailResponse visitItemDetailQueryResponseToVisitItemDetailResponse(AdminQueryResponseDto.VisitItemDetailQueryResponse visitItemDetailQueryResponse){
        return AdminResponseDto.VisitItemDetailResponse.of(visitItemDetailQueryResponse);
    }

    // -----------------------------
    // 유틸리티 메서드
    // -----------------------------
    @Named("maskSecondCharacter")
    default String maskSecondCharacter(String name) {
        if (name != null && name.length() > 1) {
            char[] arr = name.toCharArray();
            arr[1] = '*';
            return new String(arr);
        }
        return name;
    }

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

- 코드 변경 과정에서 생긴 문제점
    - 다 expression으로 처리하다 보니, Ambiguous expresiion 에러가 발생하였다.
    - `@Named` 등을 붙이지 않을 경우, mapstruct는 인자와 반환값으로 인식하기 때문에
    - expression을 사용하지 않은 `adminVoToAdminResponse` 등에서도
        - maskSecondCharacter, maskPhoneNumber 등의 메서드를 사용하려고 하였다.
    - 따라서 `@Named`, `qualifiedByName`등을 사용하여 해결하였다.

### 추가적 의문점
- 사실 어떻게 생각해보면 당연한 것인데, 왜 여태까지 이런 생각이 안 들었는지는 모르겠다.
- 현재 쓰기 작업의 경우, reuqestDto를 WriteVo 등으로 변환해주고 있는데, 이러면 Service 계층에서 쓸만한 데이터 구조가 존재하지 않는다.
- requestDto를 쓰기에도 역할에 맞지 않고, Vo를 쓰기에도 불변 객체라는 점에서 비즈니스 로직을 처리하기에는 부적합하다.
- 그렇다고 Service 계층을 위한 별도 데이터 구조를 추가하기에도 복잡성 측면에서 무리가 있는지라, 다음과 같은 구조를 취하기로 했다.
    - WriteVo를 QueryReqeustDto로 변환, 이를 Service 계층에서 사용하도록 하였다.
- 조회 작업
    - requestDto -> QueryResponseDto -> ResponseDto
- 쓰기 작업(insert, update, delete)
    - requestDto -> QueryRequestDto