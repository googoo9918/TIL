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