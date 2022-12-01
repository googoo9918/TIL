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