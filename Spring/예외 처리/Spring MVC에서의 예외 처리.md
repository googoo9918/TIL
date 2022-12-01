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

# @RestControllerAdvice를 이용한 예외처리
## @RestControllerAdvice를 사용한 예외 처리 공통화
- 특정 클래스에 `@RestControllerAdvice` 애너테이션 사용 시 여러 개의 Controller 클래스에서 `@ExceptionHandler`, `@InitBinder`, `@ModelAttribue`가 추가된 메서드를 공유해서 사용할 수 있음 
  - 즉, 예외 처리를 공통화 할 수 있음!
- `@ExceptionHandler`를 통해 진행하던 예외처리를 `@RestContorllerAdvice`애너테이션을 이용하는 방식으로 전환

> 참고: `@InitBinder`, `@ModelAttribue` 는 주로 서버 사이드 렌더링(SSR) 방식에서 주로 사용

- MemberController 클래스에서 `@ExceptionHandler` 로직 제거