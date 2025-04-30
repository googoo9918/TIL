## Dto의 상속 과정 생성 중 Mapstruct mapper에서 생긴 문제
### 문제 상황
- Dto에서 중복된 구조가 발생하여, 포함 구조와 상속 구조 둘 중 선택을 해야하는 상황
- 포함 구조를 사용하려면, 클라이언트에서 요청 json의 형식을 한 번 wrapping 해야 되는데, 이는 적절치 않은 상황이었음
- 따라서 상속 구조를 활용하고자 하였는데
- mapper을 사용할 때, request로 상속한 Dto 클래스를 넣어주었을 때는 아무런 문제가 발생하지 않았지만
- response를 생성하고자 할 때는 문제가 발생하였음.
- 문제는 다음과 같음
- 기존 코드

```java
    @Getter
    @AllArgsConstructor
    public static class VisitMasterCreate {
        private String visitorName;

        private String visitorCompany;

        private String visitorPhoneNumber;
    }
    
    @Getter
    @AllArgsConstructor
    public static class VisitMasterUpdate {
        private String vstId;
        
        private String visitorName;

        private String visitorCompany;

        private String visitorPhoneNumber;
    }
```

- 여기서 상속 구조를 취하면 다음과 같음

```java
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor //추가
    public static class VisitMasterCreate {
        private String visitorName;

        private String visitorCompany;

        private String visitorPhoneNumber;
    }
    
    @Getter
    @AllArgsConstructor
    public static class VisitMasterUpdate extends VisitMasterCreate{
        private String vstId;
    }
```

- 그런데 여기서 `@NoArgsConstructor`을 추가하자, 다음과 같은 문제 발생

```java
//visitMapperImpl
    @Override
    public VisitWriteRequestDto.VisitMasterCreate visitRequestToVisitMasterCreate(VisitRequestDto.VisitRequest visitRequest) {
        if ( visitRequest == null ) {
            return null;
        }

        VisitWriteRequestDto.VisitMasterCreate visitMasterCreate = new VisitWriteRequestDto.VisitMasterCreate();

        return visitMasterCreate;
    }
```
- impl이 기본 생성자를 사용해 빈 객체를 만들어내고 있음
    - 아마 `@AllArgsConstructor`보다 `@NoArgsConstructor`를 Mapstruct가 우선 인식하는 듯?
- 그렇다고 `@Setter`을 추가하기엔, 객체 불변성과 변경 가능성 여부 때문에 추가하고 싶지 않았음
    - 물론 `@Setter`을 추가하면 코드는 정상 동작한다.
- 따라서 `@Builder`을 추가하였으나, 다음과 같은 문제 발생

```java
// 상위 객체에만 @Builder을 추가한 경우
error: incompatible types: VisitMasterCreate cannot be converted to VisitMasterUpdate
        return visitMasterUpdate.build();
                                      ^

// 하위 객체에도 @Builder을 추가한 경우
error: builder() in VisitMasterUpdate cannot hide builder() in VisitMasterCreate
    @Builder
    ^
  return type VisitMasterUpdateBuilder is not compatible with VisitMasterCreateBuilder                                      
```

### 해결책
- `@SuperBuilder`을 사용해 해결하였음
    - 기존에 겪었던 문제인데, 왜 기억하지 못하였는지는 모르겠음 ㅠㅠ
- 최종 코드는 다음과 같다
```java
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    public static class VisitMasterCreate {
        private String visitorName;

        private String visitorCompany;

        private String visitorPhoneNumber;
    }
    
    @Getter
    @AllArgsConstructor
    @SuperBuilder
    public static class VisitMasterUpdate extends VisitMasterCreate {
        private String vstId;
    }
    
// VisitMapperImpl
    @Override
    public VisitWriteRequestDto.VisitMasterCreate visitRequestToVisitMasterCreate(VisitRequestDto.VisitRequest visitRequest) {
        if ( visitRequest == null ) {
            return null;
        }

        VisitWriteRequestDto.VisitMasterCreate.VisitMasterCreateBuilder<?, ?> visitMasterCreate = VisitWriteRequestDto.VisitMasterCreate.builder();

        visitMasterCreate.visitorName( visitRequest.getVisitorName() );
        visitMasterCreate.visitorCompany( visitRequest.getVisitorCompany() );
        visitMasterCreate.visitorPhoneNumber( visitRequest.getVisitorPhoneNumber() );

        return visitMasterCreate.build();
    }
    
    @Override
    public VisitWriteRequestDto.VisitMasterUpdate visitUpdateRequestToVisitMasterUpdate(VisitRequestDto.VisitUpdateRequest visitUpdateRequest) {
        if ( visitUpdateRequest == null ) {
            return null;
        }

        VisitWriteRequestDto.VisitMasterUpdate.VisitMasterUpdateBuilder<?, ?> visitMasterUpdate = VisitWriteRequestDto.VisitMasterUpdate.builder();

        visitMasterUpdate.visitorName( visitUpdateRequest.getVisitorName() );
        visitMasterUpdate.visitorCompany( visitUpdateRequest.getVisitorCompany() );
        visitMasterUpdate.visitorPhoneNumber( visitUpdateRequest.getVisitorPhoneNumber() );
        visitMasterUpdate.vstId( visitUpdateRequest.getVstId() );

        return visitMasterUpdate.build();
    }
```