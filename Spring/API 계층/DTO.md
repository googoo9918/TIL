### 목차
- [HTTP 요청/응답에서의 DTO(Data Transfer Object)](#http-요청응답에서의-dtodata-transfer-object)
  - [DTO(Data Transfer Object)란?](#dtodata-transfer-object란)
  - [DTO가 필요한 이유](#dto가-필요한-이유)
  - [데이터 유효성(Validation) 검증의 단순화](#데이터-유효성validation-검증의-단순화)
  - [HTTP 요청/응답 데이터에 DTO 적용](#http-요청응답-데이터에-dto-적용)
    - [DTO 생성](#dto-생성)
    - [@RequestBody 애너테이션](#requestbody-애너테이션)
    - [@ResponseBody 애너테이션](#responsebody-애너테이션)
- [DTO 유효성 검증(Validation)](#dto-유효성-검증validation)
  - [DTO 클래스에 유효성 검증 적용하기](#dto-클래스에-유효성-검증-적용하기)
    - [유효성 검증을 위한 의존 라이브러리 추가](#유효성-검증을-위한-의존-라이브러리-추가)
    - [MemberPostDto 유효성 검증](#memberpostdto-유효성-검증)
    - [쿼리 파라미터 및 @Pathvariable에 대한 유효성 검증](#쿼리-파라미터-및-pathvariable에-대한-유효성-검증)
  - [Custom Validator 를 사용한 유효성 검증](#custom-validator-를-사용한-유효성-검증)
    - [Custom Annotation 정의](#custom-annotation-정의)
    - [Custom Validator 구현](#custom-validator-구현)
    - [유효성 검증을 위해 Custom Annotation 추가](#유효성-검증을-위해-custom-annotation-추가)
# HTTP 요청/응답에서의 DTO(Data Transfer Object)
## DTO(Data Transfer Object)란?
- 데이터를 전송하기 위한 용도의 객체
## DTO가 필요한 이유
- `@RequestParam`을 제거함으로써 코드의 간결성을 증대시킬 수 있음
  - 파라미터가 늘어나면 `@RequestParam`의 개수 또한 증가
- DTO 사용 전
```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        return new ResponseEntity<Map>(map, HttpStatus.CREATED);
    }

		...
		...
}
```
- DTO 사용 후
  - `@RequestParam`이 사라지고 `MemberDto memberDto`로 대체
```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(MemberDto memberDto) {
        return new ResponseEntity<MemberDto>(memberDto, HttpStatus.CREATED);
    }
}
```

## 데이터 유효성(Validation) 검증의 단순화
- 여태까지는 요청 데이터의 유효성 검증 작업을 거치지 않음
  - 이메일, 비밀번호, 번호등은 형식을 갖춰야 함
  - 이를 유효성 검증이라 지칭
- DTO를 사용하지 않은 유효성 검증 예시
```java
@RestController
@RequestMapping("/no-dto-validation/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone) {
				// (1) email 유효성 검증
        if (!email.matches("^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new InvalidParameterException();
        }
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        return new ResponseEntity<Map>(map, HttpStatus.CREATED);
    }
		...
		...
}
```
- (1)과 같이 정규 표현식을 사용해 이메일 주소의 유효성을 검증
  - 핸들러 메서드의 코드 복잡도가 높아지게 됨
- **HTTP 요청을 받는 핸들러 메서드는 요청을 전달 받는 것이 주 목적이기 때문에 최대한 간결하게 작성해야함**
- DTO를 통해 유효성 검증을 핸들러 메서드 외부에서 처리
```java
public class MemberDto {
    @Email
    private String email;
    private String name;
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
// [코드 3-28] MemberDto 클래스의 email 멤버 변수에 유효성 검증을 적용하는 예
```
- `@Email` 에너테이션을 추가를 통해 유효성 검증 진행
- DTO에서 유효성 검증을 진행하는 핸들러 메서드 예시
```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@Valid MemberDto memberDto) {
        return new ResponseEntity<MemberDto>(memberDto, HttpStatus.CREATED);
    }
}
```
- `@Valid`
  - MemberDto 객체에 유효성 검증을 적용하게 해주는 애너테이션

## HTTP 요청/응답 데이터에 DTO 적용
### DTO 생성
- DTO 적용을 위한 리팩토링 절차
  - 회원 정보를 전달 받을 DTO 클래스 생성
  - 클라이언트의 요청 데이터를 `@RequestParam` 애너테이션으로 전달 받는 핸들러 메서드를 찾음
    - Request Body가 필요한 핸들러는 POST, PATCH, PUT 같이 추가나 변경이 필요할 때임
    - `@PostMapping`, `PatchMapping`을 찾는 것과 동일
  - `@RequestParam` 코드를 DTO 클래스 객체로 수정
  - Map 객체로 작성되어 있는 Response Body를 DTO 클래스 객체로 변경
- MemberPostDto 클래스
```java
public class MemberPostDto {
    private String email;
    private String name;
    private String phone;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
```
- memberPatchDto 클래스
```java
public class MemberPatchDto {
    private long memberId;
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
```
- DTO 클래스를 만들 때 주의점
  - 각 멤버 변수에 해당하는 getter 메서드가 있어야 함
    - getter 메서드가 없으면 Response Body에 해당 멤버 변수에 값이 포함되지 않는 문제 발생
    - setter는 필수는 아님

- DTO가 적용되지 않은 기존 코드
```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    // 회원 정보 등록
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("name", name);
        body.put("phone", phone);

        return new ResponseEntity<Map>(body, HttpStatus.CREATED);
    }

    // 회원 정보 수정
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestParam String phone) {
        Map<String, Object> body = new HashMap<>();
        body.put("memberId", memberId);
        body.put("email", "hgd@gmail.com");
        body.put("name", "홍길동");
        body.put("phone", phone);

        // No need Business logic

        return new ResponseEntity<Map>(body, HttpStatus.OK);
    }
}
```
- DTO가 적용된 코드
```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    // 회원 정보 등록
    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberPostDto memberPostDto) {
        return new ResponseEntity<>(memberPostDto, HttpStatus.CREATED);
    }

    // 회원 정보 수정
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);
        memberPatchDto.setName("홍길동");

        // No need Business logic

        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }
}
```
- `@RequestParam` 수차례 사용 -> `@ResponseBody MemberDto` 
- ![image](https://user-images.githubusercontent.com/102513932/204969845-0461ff8d-da74-40de-bbd8-9d5042baf7f8.png)
  - postMember() 핸들러 메서드 호출 결과
- ![image](https://user-images.githubusercontent.com/102513932/204969972-a0c04f79-1126-46db-8473-8283f193462d.png)
  - patchMember() 핸들러 메서드 호출 결과

### @RequestBody 애너테이션
- MemberPostDto 클래스 앞에 붙은 `@RequestBody` 애너테이션은 JSON 형식의 Request Body를 MemberPostDto 클래스의 객체로 변환 시켜줌
  - 당연히 클라이언트 쪽에서 전송하는 데이터는 JSON 형식이어야 함
    - 다른 형식 전송 시, 에러 발생

### @ResponseBody 애너테이션
- JSON 형식의 Response Body를 클라이언트에게 전달하기 위해 DTO 클래스의 객체를 JSON 형식으로 변환
- Spring MVC에서는 핸들러 메서드에 `@ResponseBody` 애너테이션이 붙거나 핸들러 메서드의 리턴 값이 `ResponseEntity`일 경우, 내부적으로 응답 객체를 JSON 형식으로 바꿔줌
  - 따라서 위 코드에서 `@ResponseBody`가 사용되지 않았음

# DTO 유효성 검증(Validation)
## DTO 클래스에 유효성 검증 적용하기
### 유효성 검증을 위한 의존 라이브러리 추가
- build.gradle에 추가
```java
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-validation'
}
```
### MemberPostDto 유효성 검증
- email
  - 값이 비어있지 않거나 공백이 아니어야 함
  - 유효한 이메일 주소 형식이어야 함
- name
  - 값이 비어있지 않거나 공백이 아니어야 함
- phone
  - 값이 비어있지 않거나 공백이 아니어야 함
  - 010으로 시작하는 11자리 숫자와 '-'로 구성된 문자열이어야 함
- MemberPostDto에 유효성 검증 적용
```java
public class MemberPostDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String name;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String phone;
```
- `@NotBlank`
  - 이메일 정보가 비어있지 않은지를 검증
  - null 값이나 공백(""), 스페이스(" ")같은 값들을 모두 허용하지 않음
  - 유효성 검증에 실패하면 에러 메시지가 콘솔에 출력됨
- `@Email`
  - 유효한 이메일 주소인지를 검증함
  - 유효성 검증에 실패하면 내장된 디폴트 에러 메시지가 콘솔에 출력됨
  - Deprecated된 애너테이션은 Hibernate Validator에서 지원하는 애너테이션
    - 현재 사용하는 것은 javax에서 지원하는 표준 Email 애너테인셔임
  - 현실적으로 `naver.com`, `gmail.com`과 같이 최상위 도메인까지 포함되어야 하는 경우, 정규 표현식을 이용해 보다 세밀한 유효성 검사 조건을 지정할 수 있음
- `@Pattern`
  - 정보가 정규표현식에 매치되는지 확인
  - 검증에 실패하면 내장된 디폴트 에러 메시지가 콘솔에 출력됨
- DTO 클래스에 유효성 검증 애너테이션을 추가함으로써 핸들러 메서드의 복잡도를 높이지 않고, 깔끔히 유효성 검증 로직을 분리함
- 유효성 검증 애너테이션을 추가한 핸들러 메서드
```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        return new ResponseEntity<>(memberDto, HttpStatus.CREATED);
    }
}
```
- `@Valid` 애너테이션이 추가됨
- 콘솔에 출력되는 예외처리 메세지를 클라이언트에 전달하는 것은 [예외 처리] 유닛에서 학습 예정

### 쿼리 파라미터 및 @Pathvariable에 대한 유효성 검증
- 핸들러 메서드의 URI path에서 사용되는 `@PathVariable("member-id") long memberId` 변수에 대한 유효성 검증
```java
@RestController
@RequestMapping("/v1/members")
@Validated   // (1)
public class MemberController {
		...
		...

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Min(1) long memberId,
                                    @Valid @RequestBody MemberPatchDto memberPatchDto)
```
- id가 1 이상의 숫자일 경우에만 통과하도록 함
- @PathVariable이 추가된 변수에 유효성 검증이 정상적으로 수행되기 위해서는 (1)과 같이 클래스 레벨에 `@Validated` 애너테이션을 반드시 붙여줘야 함

## Custom Validator 를 사용한 유효성 검증
- DTO 클래스에 유효성 검증을 적용하다보면 Jakarta Bean Validation에 내장된 애너테이션 중 목적에 맞는 애너테이션이 존재하지 않을 수 있음
  - 애너테이션을 직접 만들어 유효성 검증에 적용해야함
- 1. Cusotm Validator를 사용하기 위한 Custom Annotation을 정의함
- 2. 정의한 Custom Annotation에 바인딩 되는 Custom Validator를 구현
- 3. 유효성 검증이 필요한 DTO 클래스의 멤버 변수에 Custom Annotation을 추가함

### Custom Annotation 정의
```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotSpaceValidator.class}) // (1)
public @interface NotSpace {
    String message() default "공백이 아니어야 합니다"; // (2)
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
//  공백을 허용하지 않는 Custom Annotation
```
- `NotSpace` 애너테이션이 멤버 변수에 추가되었을 때, 동작 할 Custom Validatior을 (1)과 같이 추가해야함
- (2)는 애너테이션 추가 시 별도로 정의하지 않으면 유효성 검증 실패 시 표시되는 디폴트 메시지 

### Custom Validator 구현
```java
public class NotSpaceValidator implements ConstraintValidator<NotSpace, String> {

    @Override
    public void initialize(NotSpace constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || StringUtils.hasText(value);
    }
}
```
- CustomValidator 구현을 위해 `ConstraintValidator` 인터페이스를 구현
- `ConstraintValidator<NotSpace, String>`에서 `NotSpace`는 CustomValidator와 매핑된 Custom Annotation을 의미함
  - `String`은 Custom Annotation으로 검증할 대상 멤버 변수의 타입을 의미함

### 유효성 검증을 위해 Custom Annotation 추가
```java
public class MemberPatchDto {
    private long memberId;

    @NotSpace(message = "회원 이름은 공백이 아니어야 합니다") // (1)
    private String name;
}
```
- name 멤버 변수에 적용된 `@Pattern(regexp ="^\\S+(\\s?\\S+)*$")` 애너테이션을 제거
  - Custom Annotation인 `@NotSpace`를 추가함