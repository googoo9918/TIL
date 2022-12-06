[자바 ORM 표준 JPA_기본_엔티티 매핑](https://github.com/ssu18/TIL/blob/main/JPA/%EC%9E%90%EB%B0%94%20ORM%20%ED%91%9C%EC%A4%80%20JPA_%EA%B8%B0%EB%B3%B8/%EC%97%94%ED%8B%B0%ED%8B%B0%20%EB%A7%A4%ED%95%91(4).md#transient)로 대체
<br>

중요 내용만 정리
- 엔티티와 테이블 매핑 권장 사용법
  - 클래스 이름 중복 등의 특별한 이유가 없다면 `@Entity`와 `@Id` 애너테이션만 추가
    - 만약 엔티티 클래스가 테이블 스키마 명세의 역할을 하기 바란다면 `@Table` 애너테이션을 통해 테이블명을 지정
  - 기본키 생성 전략은 DB에서 지원해주는 AUTO_INCREMENT 또는 SEQUENCE를 이용할 수 있도록 IDENTITY 또는 SEQUENCE 전략을 사용하는 것이 좋음
  - `@Column` 정보를 명시적으로 모두 지정하는 것은 번거롭지만 타인이 테이블 설계를 한 눈에 알아볼 수 있는 장점이 있음
  - 엔티티 클래스 필드 타입이 Java 원시타입인 경우, `@Column` 애너테이션을 생략하지 말고 최소한 **nullable = false** 설정을 해줄 것
  - `@Enumerated` 애너테이션 사용 시, `EnumType.ORDINAL`을 사용하면 enum의 순서가 뒤바뀔 가능성이 존재
    - 처음부터 **`EnumType.STRING`**을 사용하는 것이 좋음
- 핵심
  - `@Entity` 애너테이션을 클래스 레벨에 추가하면 JPA의 관리대상 엔티티가 됨
  - `@Table` 애너테이션은 엔티티와 매핑할 테이블을 지정함
  - `@Entity` 애너테이션과 `@Id` 애너테이션은 필수로 추가해야함
  - JPA는 다양한 기본키 생성 전략 지원
    - 직접 할당
    - IDENTITY 
      - 기본 키 생성 DB에 위임
    - SEQUENCE 전략
      - DBD에서 제공하는 시퀀스를 사용해 기본키를 생성함
    - TABLE 전략
      - 별도의 키 생성 테이블 사용
    - AUTO 전략
      - JPA가 DB의 Dialect에 따라 적절한 전략 자동 선택
  - Java의 원시 타입 필드에서 `@Column` 애너테이션이 없거나 `@Column`애너테이션이 있지만 애트리뷰틀르 생략한 경우, **최소한 `nullable=false`는 설정하는 것이 에러를 미연에 방지할 수 있음**
  - `java.util.Date`, `java.util.Calendar` 타입으로 매핑하기 위해서는 `@Temporal` 애너테이션 추가해야함
    - `LocalDate`, `@LocalDateTime` 타입일 경우, **`@Temporal` 애너테이션 생략 가능**
  - `@Transient` 애너테이션 필드 추가 시, JPA가 테이블 컬럼과 매핑하지 않겠다는 의미로 인식함
  - 테이블에 이미 저장되어 있는 enum 순서 번호와 enum에 정의되어 있는 순서가 일치하지 않는 문제가 발생하지 않도록 `EnumType.STRING` 사용 권장