## Request Param에 Enum 사용법
- Request Param에 Enum을 사용하고 싶어짐
- 현재 방문상태에 대한 코드값은 SysCode에 존재하고 있음
- 그러나 각 코드가 무엇을 의미하는지 DB에 존재하지 않음
- 따라서 이를 단순히 Service 계층에서 switch-case 등을 사용하여 바꾼다면 다음과 같은 문제가 존재함
    - 문자열이나 숫자 상수로 분기 처리를 하게 될텐데, 검증 미흡(단순 switch-case를 보고 비즈니스 로직을 파악해라?)
    - 가독성도 떨어짐
    - 재사용성/ 유지보수 / 확장성 모두 미흡
    - 또 뭐가 있을까..
        - 억지는 아닌가?
- 물론 자주 상태코드가 변경되는 상황이라면 DB와 매핑을 하는 것이 가장 적절하겠으나..
    - 그런 상황은 아니기에..
    - 하지만 내 방식이 맞는지도 모르겠음 ㅠ
    - 사실 그냥 switch-case를 쓰는게 편하지 않을까?? 장점이 그렇게 많나 싶은데;
- 일단 기술해보자면
```java
    @Getter
    @AllArgsConstructor
    public static class VisitSearchParam {
        private String visitorName;

        private String visitorPhoneNumber;

        private String visitDateStart;

        private String visitDateEnd;

        private String vehicleNumber;

//        private String visitState;
        private VisitState visitState;

        /**
         * visitState의 code 값을 반환
         * MyBatis에서 #{visitStateCode} 바인딩 시 사용
         */
        public String getVisitStateCode() {
            return visitState != null ? visitState.getCode() : null;
        }
    }
    
    package com.inc.visit.domain.admin.constant;

import com.inc.visit.common.error.ErrorCode;
import com.inc.visit.common.error.exception.BusinessException;

public enum VisitState {
    방문신청("S"),
    방문승인("Y"),
    방문중("I"),
    방문종료("E"),
    방문거부("B");

    private final String code;

    VisitState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    // 한글 enum에서 code 가져오기
    public static String getCodeByName(String name) {
        try {
            return VisitState.valueOf(name).getCode();
        } catch (IllegalArgumentException e) {
            throw new BusinessException(ErrorCode.STATE_LABEL_NOT_EXISTS);
        }
    }

    // code → name(enum) 변환
    public static VisitState getByCode(String code) {
        for (VisitState state : values()) {
            if (state.code.equals(code)) {
                return state;
            }
        }
        throw new BusinessException(ErrorCode.STATE_CODE_NOT_EXISTS);
    }
}

// myBatis에서 #{visitState} -> #{visitStateCode}로 수정
```
- 위와 같이 처리하였다.
- 여기서 가장 놀라운 점은, Service 계층에서 아무 처리도 진행하지 않아도 됐다는 것.
- MyBatis에서 `#{}`구문은 SQL 매핑 시 **Java 객체의 "getter 메서드"를 기준으로 값을 읽어오기 때문**
    - 이유는 다음과 같다.
    - MyBatis는 JavaBeans 컨벤션에 따라 객체를 다룬다.
    - JavaBeans에서는 property를 getter, setter를 통해 접근 가능한 필드로 정의한다.
    - 즉, name이라는 필드는 getName()또는 isName()이 있어야 속성으로 간주한다.
- 그럼 map을 파라미터로 넘겨줄 때는, 데이터를 어떻게 읽어올까?
    - `map.get(key)`를 통해 데이터를 읽어온다.

### 추가적 문제점
- 문제가 발생했던 것은, requestParam에서 한글로 인코딩해서 전송해주는 것을, Spring에서 자동으로 Enum으로 바인딩하지 못하여 null값이 계속 넘어온다는 것이었다.
- 내가 선택할 수 있는 방법은 두 가지정도 였는데, 별도 Converter Component를 지정하는 것과, 일단 String으로 받고 내부적으로 Enum 값으로 변환하는 것이었다.
- 후자를 선택, 코드는 다음과 같다.
```java
@Getter
@AllArgsConstructor
public static class VisitSearchParam {
    private String visitorName;

    private String visitorPhoneNumber;

    private String visitDateStart;

    private String visitDateEnd;

    private String vehicleNumber;

    // Enum VisitState로 스프링 자동 바인딩 실패, 별도 Converter Component 설정 대신 String으로 받기
    private String visitState;
//  private VisitState visitState;

    // String visitState --> VisitState(Enum) visitState
    public VisitState getVisitStateEnum(){
        if(visitState == null || visitState.isBlank()) return null;

        return VisitState.fromLabel(visitState);
    }
    /**
     * visitState의 code 값을 반환
     * MyBatis에서 #{visitStateCode} 바인딩 시 사용
    */
    public String getVisitStateCode() {
        VisitState state = getVisitStateEnum();
        return state != null ? state.getCode() : null;
    }
}
    
    
public enum VisitState {
    방문신청("S"),
    방문승인("Y"),
    방문중("I"),
    방문종료("E"),
    방문거부("B");

    private final String code;

    VisitState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    // 한글 String → Enum 변환
    public static VisitState fromLabel(String label) {
        for (VisitState vs : values()) {
            if (vs.name().equals(label)) {
                return vs;
            }
        }
        throw new BusinessException(ErrorCode.STATE_LABEL_NOT_EXISTS);
    }
}
```
- 실행 흐름은 다음과 같다.
    - 마이바티스에서 getVisitStateCode 사용 시 ->  getVisisStateEnum 호출 -> fromLabel 호출
    - String visitState가 VisitState visitState로 변환, visitState의 code값을 반환.