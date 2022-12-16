### 목차
- [Controller 클래스 설계 및 구조 생성](#controller-클래스-설계-및-구조-생성)
  - [패키지 구조 생성](#패키지-구조-생성)
  - [커피 주문 애플리케이션의 Controller 설계](#커피-주문-애플리케이션의-controller-설계)
    - [커피 주문 애플리케이션에 필요한 리소스](#커피-주문-애플리케이션에-필요한-리소스)
  - [엔트리포인트 클래스 작성](#엔트리포인트-클래스-작성)
  - [커피 주문 애플리케이션의 Controller 구조 작성](#커피-주문-애플리케이션의-controller-구조-작성)
- [핸들러 메서드](#핸들러-메서드)
  - [핸들러 메서드 적용](#핸들러-메서드-적용)
  - [현재 코드에서 개선부](#현재-코드에서-개선부)
- [응답 데이터에 ResponseEntity 적용](#응답-데이터에-responseentity-적용)
  - [코드 개선](#코드-개선)

# Controller 클래스 설계 및 구조 생성
- ![image](https://user-images.githubusercontent.com/102513932/204945546-6f22bbe3-8865-4922-8926-0252f3118177.png)
  - API 계층
    - 클라이언트의 요청을 직접적으로 전달 받음
## 패키지 구조 생성
- ![image](https://user-images.githubusercontent.com/102513932/204945766-9163957c-7f0e-4870-a082-0fd25a3b0af0.png)
  - 기능 기반 패키지 구조 권장

## 커피 주문 애플리케이션의 Controller 설계
- 클라이언트로부터 발생할 요청에는 어떤 것이 있는가
  - 클라이언트 요청을 처리할 서버 애플리케이션의 기능으로 무엇이 필요한가
### 커피 주문 애플리케이션에 필요한 리소스
- ![image](https://user-images.githubusercontent.com/102513932/204946201-d3ab7c8a-57b4-4d00-b028-dcb3982f5f6a.png)
  - 리소스에 해당하는 Controller 클래스를 작성하라!

## 엔트리포인트 클래스 작성
- Spring Initializr 를 통해 이미 작성되어 있음
```java
@SpringBootApplication   // (1)
public class Section3Week1Application {

	public static void main(String[] args) {
    // (2)
		SpringApplication.run(Section3Week1Application.class, args);
	}
}
```
- (1) `@SpringBootApplication`
  - 자동 구성을 활성화함
  - 패키지 내에서 `@Component`가 붙은 클래스를 검색한 후 Spring Bean으로 등록하는 기능을 활성화함
  - `@Configuration`이 붙은 클래스를 자동으로 찾아줌
    - 추가적으로 Spring Bean을 등록하는 기능을 활성화
- (2) `SpringApplication.run(Section3Week1Application.class, args);`
  - Spring 애플리케이션을 부트스트랩 및 실행
    - 부트스트랩 : 실행되기 전 여러 설정 작업을 수행하여 실행 가응한 애플리케이션으로 만드는 단계

## 커피 주문 애플리케이션의 Controller 구조 작성
```java
@RestController //(1)
@RequestMapping("/v1/members") //(2)
public class MemberController{

}
```
- (1) `@RestController`
  - 해당 클래스가 Rest API의 리소스를 처리하기 위한 API 엔드포인트로 동작함을 정의함
  - 애플리케이션 로딩 시, Spring Bean으로 등록
- (2) `@RequestMapping`
  - 클라이언트 요청을 처리하는 핸들러 메서드를 매핑
  - 클래스 전체에 사용되는 공통 URL 설정


# 핸들러 메서드
- ![image](https://user-images.githubusercontent.com/102513932/204946997-50314283-7f54-48d8-8f2b-e26f6667b431.png)
  - Member Controller에 POST 요청 전송 예시
  - 에러 응답을 받는 이유는 `MemberController`에 클라이언트 요청을 처리할 핸들러 메서드가 아직 없기 때문
## 핸들러 메서드 적용
```java
@RestController
@RequestMapping(value = "/v1/members", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {
    @PostMapping
    public String postMember(@RequestParam("email") String email,
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone) {
        System.out.println("# email: " + email);
        System.out.println("# name: " + name);
        System.out.println("# phone: " + phone);
        
        String response =
                "{\"" + 
                   "email\":\""+email+"\"," + 
                   "\"name\":\""+name+"\",\"" + 
                   "phone\":\"" + phone+ 
                "\"}";
        return response;
    }

    @GetMapping("/{member-id}")
    public String getMember(@PathVariable("member-id")long memberId) {
        System.out.println("# memberId: " + memberId);

        // not implementation
        return null;
    }

    @GetMapping
    public String getMembers() {
        System.out.println("# get Members");

        // not implementation
        return null;
    }
}
```
- `@RequestMapping`에 추가된 항목
  - produces
    - `produces` 애트리뷰트는 응답 데이터를 어떤 미디어 타입으로 클라이언트에게 전송할지를 설정
    - 현재 `MediaType.APPLICATION_JSON_VALUE` 값 설정
      - JSON 형식의 응답 데이터 전송
      - 설정하지 않으면, JSON 형식의 데이터를 전송하지 않고 문자열 자체를 전송함
- postMember() 메서드 설명
  - 회원 정보를 등록해주는 핸들러 메서드
  - `@PostMapping`
    - 클라이언트의 요청 데이터를 서버에 생성 시 사용하는 애너테이션
      - ![image](https://user-images.githubusercontent.com/102513932/204948676-3609fa14-31bd-4eeb-8acc-0d8f05c850bd.png)
  - `@RequestParam`
    - 핸들러 메서드의 파라미터 종류 중 하나
    - 클라이언트 쪽에서 전송하는 요청 데이터를 쿼리 파라미터, 폼 데이터 형식으로 전송할 때 사용하는 애너테이션
- 리턴 값
  - 현재 리턴 값은 String 타입
    - 단, 클라이언트 쪽에서 JSON 형식의 데이터를 받아야 하기 때문에 JSON 형식에 맞춰 작성해줌
    - 이런 방식은 번거롭고, 에러를 발생시킬 수 있기 때문에 개선 요망
- getMember() 메서드 설명
  - 특정 회원의 정보를 클라이언트 쪽에 제공하는 핸들러 메서드
  - `@GetMapping`
    - 클라이언트가 서버에 리소스를 조회할 때 사용
    - 전체 HTTP URI의 일부를 애트리뷰트로 사용
      - 클라이언트 쪽에서 메서드에 요청을 보낼 경우
        - 최종 URI : `/v1/members/{member-id}`
  - `@PathVariable`
    - 핸들러 메서드의 파라미터 종류 중 하나
    - 괄호 안 문자열 값은 `@GetMapping("/{member-id}")`의 중괄호 안 문자열과 동일해야함
## 현재 코드에서 개선부
- 개발자가 수작업으로 JSON 문자열을 만드는 부분
- `@RequestParam` 애너테이션을 사용한 요청 파라미터 수신
  - 만약 클라이언트로부터 전달 받아야하는 요청 파라미터가 다섯 개라면 핸들러 메서드의 파라미터로 총 다섯 개의 `@RequestParameter`를 사용해 입력해야 함

# 응답 데이터에 ResponseEntity 적용
## 코드 개선
```java
@RestController
@RequestMapping("/v1/members") // (1) produces 설정 제거됨
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone) {
        // (2) JSON 문자열 수작업을 Map 객체로 대체
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name))
        map.put("phone", phone);

        // (3) 리턴 값을 ResponseEntity 객체로 변경
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        System.out.println("# memberId: " + memberId);

        // not implementation

        // (4) 리턴 값을 ResponseEntity 객체로 변경
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Members");

        // not implementation

        // (5) 리턴 값을 ResponseEntity 객체로 변경
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
```
- (2) JSON 문자열을 수작업으로 작성하던 부분이 Map 객체로 대체됨
  - 이를 통해 `@RequestMapping`의 produces 애트리뷰트 생략
  - Map 객체 리턴 시 JSON 형식으로 자동 변환해줌
- (3) `ResponseEntity` 객체 리턴으로 리턴값 변환
  - `new ResponseEntity<>(map, HttpStatus.CREATED);`처럼 ResponseEntity 객체를 생성하며 응답 데이터와 HTTP 응답 상태를 함께 전달
    - HTTP 응답 상태를 명시적으로 전달함을 통해 클라이언트의 요청을 서버가 어떻게 처리했는지 알 수 있음
  - 단순 Map 객체를 리턴해도 클라이언트 쪽에서는 정상적으로 JSON 형식 데이터를 받을 수 있음
    - 다만 `ResponseEntity` 객체로 데이터를 래핑하여 보다 세련된 방식으로 응답 데이터를 생성함
- (4), (5)
  - 리턴 값 변환