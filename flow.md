# API 계층
## Spring MVC 아키텍처
### Spring MVC
#### View, Modle, Controller, 내부 동작 흐름
## Controller
### Controller 클래스 설계 및 구조 생성
#### Controller 생성 및 설계 (@RequestMapping, @RestController)
### 핸들러 메서드
#### 핸들러메서드 생성(@RequestMapping(produces), @PostMapping, @RequestParam, @GetMapping, @PathVariable)
#### 코드 개선점 (JSON 직접 작성, @RequestParam 없애기)
### ResponseEntity 적용
#### 코드 개선(JSON수작업 -> Map객체 (produces 삭제), 리턴 값 String -> ResponseEntity(HTTP 상태 코드 추가))
## DTO
### HTTP 요청/응답에서의 DTO
#### DTO를 통한 코드 개선(@RequestParam -> MemberDto, 유효성 검증(@Valid))의 당위성
#### DTO 생성 및 적용(@RequestParam -> @RequsetBody MemberDto, map -> memberDto, @ResponseBody)
### DTO 유효성 검증
#### DTO 클래스에 유효성 검증 적용(@NotBlank, @Email, @Pattern, 정규표현식) 및 핸들러 메서드 수정(@Valid)
#### 쿼리 파라미터 및 @Pathvariable에 대한 유효성 검증(@Min) 및 클래스 레벨 애너테이션 추가(@Validated)
#### Custom Validator를 사용한 유효성 검증
# 서비스 계층
## DI를 통한 서비스 계층 <-> API 계층 연동
### MemberService 클래스 기본 구현(MemberController와 1:1)
### Member 클래스 구현(@Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor)
### DI를 적용한 비즈니스 계층과 API 계층 연동(DI, @Service, @Autowired)
### 개선점(Controller 핸들러 메서드의 역할 분리(DTO <-> 엔티티), 엔티티를 클라이언트 응답으로 전송하고 있음(계층 간 역할 분리 X))
## 매퍼(Mapper)를 이용한 DTO 클래스 <-> 엔티티(Entity) 클래스 매핑
### Mapper 클래스 구현
#### xxxResponseDto 클래스 구현
### Controller의 핸들러 메서드에 매퍼 클래스 적용(매퍼를 통해 상기 두 가지 문제점 해결)
### MapStruct를 이용한 Mapper 자동 생성(@Mapper((ComponentModel = "spring")))
# 예외 처리
## Spring MVC에서의 예외 처리
### @ExceptionHandler를 이용한 예외 처리
#### Controller 레벨에서 예외 처리(@Exceptionandler, ErrorResponse 클래스 적용 전후 차이(로그 출력 간소화))
#### @ExceptionHandler 단점(Controller 클래스마다 중복, 다수의 핸들러 메서드로 코드 복잡성 증가)
### @RestControllerAdvice를 이용한 예외 처리(공통화) 
#### ExceptionAdvice 클래스 정의(공통화) 및 핸들러 메서드 구현(@RestControllerAdvice)
#### ErrorResponse 수정(MethodArgumentNotValidException  -> ConstraintViolationException 추가)
#### Exception 핸들러 메서드 수정(수정된 ErrorResponse 클래스 메서드 사용, @ResponseStatus)
## 비즈니스 로직에대한 예외 처리
### 비즈니스적인 예외 던지기(throw)및 예외 처리
#### 체크, 언체크 예외
#### 의도적인 예외 던지기/받기(throw/catch)(GlobalExceptionAdivce -> RuntimeException)(문제점: 메서드 하나로 예외 상황을 포괄적으로 담지 못함, RuntimeExcpetion은 너무 추상적임)
#### 사용자 정의 예외 사용(ExceptionCode, BusinessLogicException)
# JPA 기반 데이터 엑세스 계층
## JPA 개요
### 영속성 컨텍스트(1차 캐시, 쓰기 지연 SQL 저장소)
#### 사전 설정(build.gradle, application.yml, JpaBasicConfig)
#### 엔티티 저장 (@Id, @Entity, @GeneratedValue)
#### 영속성 컨텍스트와 테이블(em.persist(), tx.commit(), em.find(), 쓰기 지연)
## JPA 엔티티 매핑과 연관 관계 매핑
### 엔티티 매핑
### 엔티티 간의 연관 관계 매핑
## Spring Data JPA
### Spring Data JPA를 통한 데이터 액세스 계층 구현
