### 목차
- [@ExceptionHandler를 이용한 예외 처리](#exceptionhandler를-이용한-예외-처리)
  - [@ExceptionHandler를 이용한 Controller 레벨에서의 예외 처리](#exceptionhandler를-이용한-controller-레벨에서의-예외-처리)
    - [MemberController에 @ExceptionHandler 적용](#membercontroller에-exceptionhandler-적용)
    - [ErrorResponse 클래스 적용](#errorresponse-클래스-적용)
    - [ErrorResponse를 사용하도록 handleException() 메서드 수정](#errorresponse를-사용하도록-handleexception-메서드-수정)
  - [@ExceptionHandler 단점](#exceptionhandler-단점)
- [@RestControllerAdvice를 이용한 예외처리](#restcontrolleradvice를-이용한-예외처리)
  - [@RestControllerAdvice를 사용한 예외 처리 공통화](#restcontrolleradvice를-사용한-예외-처리-공통화)
    - [ExceptionAdivce 클래스 정의](#exceptionadivce-클래스-정의)
    - [Exception 핸들러 메서드 구현](#exception-핸들러-메서드-구현)
    - [ErrorResponse 수정](#errorresponse-수정)
    - [Exception 핸들러 메서드 수정](#exception-핸들러-메서드-수정)
# @ExceptionHandler를 이용한 예외 처리
- ![image](https://user-images.githubusercontent.com/102513932/205066879-da7e5b70-838c-4362-9ccc-38e9e769a6f8.png)
  - 클라이언트 요청 데이터의 유효성 검증에 실패할 경우 응답 메시지 예시
  - Response Body의 내용만으로는 요청 데이터 중 어떤 항목이 유효성 검증에 실패했는지 알 수 없음
    - 클라이언트 쪽에서 더 자세한 에러메시지를 확인할 수 있어야 함

## @ExceptionHandler를 이용한 Controller 레벨에서의 예외 처리
- Spring이 처리하던 에러 응답 메시지를 직접 처리하도록 코드 수정

### MemberController에 @ExceptionHandler 적용
```java
@RestController
@RequestMapping("/v6/members")
@Validated
@Slf4j
public class MemberControllerV6 {
    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException e) {
        // (1)
        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        // (2)
        return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
    }
}
```
- MemberController(v6)에 `@ExceptionHandler` 애너테이션을 이용, 예외를 처리하도록 `handleException()` 메서드를 추가함
- ex) 회원 등록 과정에서 Request Body의 유효성 검증에 실패했을 때 예외 처리 과정
  - 클라이언트 쪽에서 회원 등록을 위해 `MemberController`의 `postMember()` 핸들러 메서드에 요청을 전송
  - `RequqestBody`에 유효하지 않은 요청 데이터 포함 -> 유효성 검증 실패
    - `MethodArgumentNotValidException` 발생
  - `MemberController`에는 `@ExceptionHandler` 애너테이션이 추가된 예외 처리 메서드인 `handleException()`이 존재함
    - 유효성 검증 과정에서 내부적으로 던져진 `MethodArgumentNotValidException`을 `handleException()` 메서드가 전달 받음
  - (1)과 같이 `MethodArgumentNotValidException` 객체에서 `getBindingResult().getFieldErrors()`를 통해 발생한 에러 정보 확인
  - (1)에서 얻은 에러 정보를 (2)에서 `ResponseEntity`를 통해 Response Body로 전달함
- ![image](https://user-images.githubusercontent.com/102513932/205071274-cb2e4011-5eeb-42dd-8bcf-9360f398790f.png)
  - 다른 응답 메시지를 수신, 회원은 이제 어느 곳에 문제가 있는지 구체적으로 알 수 있게 됨
  - 다만 클라이언트 입장에서는 굳이 Response Body 전체 정보를 다 전달 받을 필요는 없음
  - 문제가 된 프로퍼티는 무엇인지와 에러 메시지 정도만 전달 받도록 코드 변경
    - 에러 정보를 기반으로 한 Error Response 클래스를 만들어 필요한 정보를 담은 후 클라이언트에 전달
### ErrorResponse 클래스 적용
```java
@Getter
@AllArgsConstructor
public class ErrorResponse {
    //(1) 
    private List<FieldError> fieldErrors;
    
    @Getter
    @AllArgsConstructor
    public static class FieldError{
        private String field;
        private Object rejectedValue;
        private String reason;
    }
}
```
- DTO 클래스의 유효성 검증 실패 시, 실패한 필드에 대한 Error 정보만 담아 응답으로 전송하기 위한 클래스
- 유효성 검증에 실패하는 멤버 변수가 하나 이상 될 수 있기 때문에 (1)과 같이 List 객체 사용
  - 필드 에러 정보는 `FieldError`라는 별도의 static class를 `ErrorResponse` 클래스의 멤버 클래스로 정의함
    - 이는 내부 클래스라기 보다는 `ErrorResponse` 클래스의 static 멤버 클래스라 지칭하는 것이 적절함
    - 두 클래스 다 에러 정보만 담는 클래스 이기 때문에, ErrorResponse의 멤버로 표현

### ErrorResponse를 사용하도록 handleException() 메서드 수정
```java
@RestController
@RequestMapping("/v7/members")
@Validated
@Slf4j
public class MemberControllerV7 {
    
    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException e) {
        // (1)
        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        // (2)
        List<ErrorResponse.FieldError> errors =
                fieldErrors.stream()
                            .map(error -> new ErrorResponse.FieldError(
                                error.getField(),
                                error.getRejectedValue(),
                                error.getDefaultMessage()))
                            .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }
}
```
- 이전에는 (1)의 List<FieldError>를 통째로 `ResponseEntity` 클래스에 실어서 전달 -> (2)와 같이 필요한 정보만 선택적으로 골라서 `ErrorResponse.FieldError` 클래스에 담아서 List로 변환 후, `List<ErrorResponse.FieldError>`를 `ResponseEntity` 클래스에 실어서 전달함 
- ![image](https://user-images.githubusercontent.com/102513932/205087062-eac71407-02aa-40ce-9960-34a822b66ee8.png)
  - ErrorResponse 적용 후
    - 유효성 검증에 실패한 필드가 두 개이기 때문에 에러 정보 역시 두 개이며, 필요한 정보만 표시하는 것을 볼 수 있음

## @ExceptionHandler 단점
- 각각의 Controller 클래스에서 `@ExceptionHandler` 애너테이션을 사용하여 Request Body에 대한 유효성 검증 실패에 대한 처리를 해야함
  - 각 Controller 클래스마다 코드 중복 발생
- Controller에서 처리해야 되는 예외가 유효성 검증 실패에 대한 예외(`MethodArgumentNotValidException`)만 있는 것이 아님
  - 하나의 Controller 클래스 내에서 `@ExceptionHandler`를 추가한 에러 처리 핸들러 메서드가 늘어남
    - ex) `patchMember()` 핸들러 메서드의 URI에 유효하지 않은 변수 값을 전송하는 경우
      - `ConstraintViolationException` 발생 -> 핸들러 메서드가 늘어남

# @RestControllerAdvice를 이용한 예외처리
## @RestControllerAdvice를 사용한 예외 처리 공통화
- 특정 클래스에 `@RestControllerAdvice` 애너테이션 사용 시 여러 개의 Controller 클래스에서 `@ExceptionHandler`, `@InitBinder`, `@ModelAttribue`가 추가된 메서드를 공유해서 사용할 수 있음 
  - 즉, 예외 처리를 공통화 할 수 있음!
- `@ExceptionHandler`를 통해 진행하던 예외처리를 `@RestContorllerAdvice`애너테이션을 이용하는 방식으로 전환

> 참고: `@InitBinder`, `@ModelAttribue` 는 주로 서버 사이드 렌더링(SSR) 방식에서 주로 사용

- MemberController 클래스에서 `@ExceptionHandler` 로직 제거

### ExceptionAdivce 클래스 정의
```java
@RestControllerAdvice
public class GlobalExceptionAdvice {
}
```
- Controller 클래스의 예외 공통으로 처리할 클래스
- 이제 Controller에서 발생하는 예외를 도맡아서 처리하게 됨

### Exception 핸들러 메서드 구현
```java
@RestControllerAdvice
public class GlobalExceptionAdvice {
    // (1)
    @ExceptionHandler
    public ResponseEntity handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e){
        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<ErrorResponse.FieldError> errors =
                fieldErrors.stream()
                        .map(error -> new ErrorResponse.FieldError(
                                error.getField(),
                                error.getRejectedValue(),
                                error.getDefaultMessage()))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    //(2)
    @ExceptionHandler
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        // TODO implement

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
```
- 예외 처리 공통화를 통해 각 Controller 마다 추가되는 `@ExceptionHandler` 로직에 대한 중복 코드를 제거하고, Controller 코드를 단순화 할 수 있음

### ErrorResponse 수정
- 이제 GlobalExceptionAdvice를 통해 Controller 클래스에서 발생하는 RequestBody의 유효성 검증에 대한 에러는 유연한 처리가 가능함
- 다만 아직 URI 변수의 유효성 검증에 대한 에러(`ConstraintViolationException`) 처리는 아직 구현하지 않음
  - 구현을 위한 ErrorResponse 클래스 수정

```java
@Getter
public class ErrorResponse {
    private List<FieldError> fieldErrors; // (1)
    private List<ConstraintViolationError> violationErrors;  // (2)

		// (3)
    private ErrorResponse(final List<FieldError> fieldErrors,
                          final List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

		// (4) BindingResult에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult), null);
    }

		// (5) Set<ConstraintViolation<?>> 객체에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of(violations));
    }

		// (6) Field Error 가공
    @Getter
    public static class FieldError {
        private String field;
        private Object rejectedValue;
        private String reason;

				private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                                                        bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ?
                                            "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

		// (7) ConstraintViolation Error 가공
    @Getter
    public static class ConstraintViolationError {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

				private ConstraintViolationError(String propertyPath, Object rejectedValue,
                                   String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<ConstraintViolationError> of(
                Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()
                    )).collect(Collectors.toList());
        }
    }
}
```
- ErrorResponse 클래스가 ConstraintViolationException에 대한 Error Response까지 생성 가능하도록 수정된 코드
- 수정된 ErrorResponse는 총 두 개의 예외 유형을 처리해 Error Response에 포함할 수 있음 (`MethodArgumentNotValidException`, `ConstraintViolationException`)
- (1)은 `MethodArgumentNotValidException`로부터 발생하는 에러 정보를 담는 멤버 변수임
  - DTO 멤버 변수 필드의 유효성 검증 실패로 발생한 에러 정보를 담는 멤버 변수
- (2)는 `ConstraintVoilationException` 으로부터 발생하는 에러 정보를 담는 멤버 변수임
  - URI 변수 값의 유효성 검증에 실패로 발생한 에러 정보를 담는 멤버 변수
- (3)은 ErrorResponse 클래스의 생성자
  - 생성자 앞에 Private를 붙임, new 방식으로 `ErrorResponse` 생성 불가능
  - 대신 (4)와 (5) 처럼 `of()` 메서드를 이용해 객체를 생성함
    - 객체를 생성함과 동시에 역할을 명확히 해줌
- (4)는 `MethodArgumentNotValidException`에 대한 ErrorResponse 객체를 생성함
  - `MethodArgumentNotValidException`에서 에러 정보를 얻기 위해 `BindingResult` 필요함, 따라서 `of()` 메서드를 호출하는 쪽에서 `BindingResult` 객체를 파라미터로 넘김
  - `BindingResult` 객체를 가지고 에러 정보를 추출하고 가공하는 일은 ErrorResponse 클래스의 static 멤버 클래스인 `FieldError` 클래스에게 위임함
- (5)는 `ConstraintViolationException`에 대한 ErrorResponse 객체를 생성함
  - Set<ConstraintViolation<?>> 객체가 에러 정보를 얻기 위해 필요함
  - `of()` 메서드를 호출하는 쪽에서 `Set<CosntraintViolation<?>>`객체를 파라미터로 넘김
  - `Set<CosntraintViolation<?>>` 객체를 갖고 에러 정보를 추출하고 가공하는 일은 ErrorResponse 클래스의 static 멤버 클래스인 `ConstraintViolationError` 클래스에게 위임함
- (6)에서는 필드(DTO 클래스 멤버 변수)의 유효성 검증에서 발생하는 에러 정보를 생성함
- (7)에서는 URI 변수 값에 대한 에러 정보를 생성함

> 기능이 늘어남에 따라 ErrorResponse 클래스의 구현 복잡도가 늘어났다. <br>
> 하지만 에러 유형에 따른 에러 정보 생성 역할을 분리함으로써 사용하는 입장에서는 한층 더 사용하기 편리해졌다.


> `of()` 메서드 <br>
> `of()` 메서드는 Java 8의 API에서도 흔히 볼 수 있는 네이밍 컨벤션이다. <br>
> 주로 객체를 생성할 때 어떤 값들**의(of~)** 객체를 생성한다는 의미에서 `of()` 메서드를 사용한다.

### Exception 핸들러 메서드 수정
```java
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());

        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(
            ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());

        return response;
    }
}
```
- 수정된 ErrorResponse 클래스를 사용하도록 개선된 GlobalExceptionAdvice 클래스 코드
  - Error Response 정보를 만드는 역할을 ErrorResponse 클래스가 대신 진행해줌
    - 코드 복잡성 감소
  - 이전에는 ErrorResponse 객체를 ResponseEntity로 래핑하여 리턴한 반면, 지금은 `ResponseEntity`가 사라지고 ErrorResponse 객체를 바로 리턴함
  - `@ResponseStatus` 애너테이션을 이용, HTTP Status를 HTTP Response에 포함함

> `@RestControllerAdvice` vs `@ControllerAdvice` <br>
> `@RestControllerAdvice` = `@ControllerAdvice` + `@ResponseBody` <br>
> `@RestControllerAdvice` 애너테이션은 JSON 형식의 데이터를 Response Body로 전송하기 위해 ResponseEntity로 데이터를 래핑할 필요 없음