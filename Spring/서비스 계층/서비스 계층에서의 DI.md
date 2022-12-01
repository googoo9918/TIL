### 목차
- [DI를 통한 서비스 계층 \<-\> API 계층 연동](#di를-통한-서비스-계층---api-계층-연동)
  - [비즈니스 로직을 처리하는 Service 클래스 작성](#비즈니스-로직을-처리하는-service-클래스-작성)
    - [MemberService 클래스 기본 구조 작성](#memberservice-클래스-기본-구조-작성)
    - [Member 클래스 구현](#member-클래스-구현)
  - [DI를 적용한 비즈니스 계층과 API 계층 연동](#di를-적용한-비즈니스-계층과-api-계층-연동)
    - [개선점](#개선점)
- [매퍼(Mapper)를 이용한 DTO 클래스 \<-\> 엔티티(Entity) 클래스 매핑](#매퍼mapper를-이용한-dto-클래스---엔티티entity-클래스-매핑)
  - [Mapper 클래스 구현](#mapper-클래스-구현)
    - [MemberResponseDto 클래스 생성](#memberresponsedto-클래스-생성)
  - [MemberController의 핸들러 메서드에 매퍼 클래스 적용](#membercontroller의-핸들러-메서드에-매퍼-클래스-적용)
  - [MapStruct를 이용한 Mapper 자동 생성](#mapstruct를-이용한-mapper-자동-생성)
    - [MapStruct 의존 라이브러리 설정](#mapstruct-의존-라이브러리-설정)
    - [MapStruct 기반의 매퍼 인터페이스 정의](#mapstruct-기반의-매퍼-인터페이스-정의)
  - [DTO 클래스와 엔티티 클래스의 역할 분리가 필요한 이유](#dto-클래스와-엔티티-클래스의-역할-분리가-필요한-이유)
# DI를 통한 서비스 계층 <-> API 계층 연동
- API 계층과 서비스 계층의 연동은 API 계층에서 구현한 Controller 클래스와 서비스 계층의 Service 클래스가 메서드 호출을 통해 상호 작용하는 것임
## 비즈니스 로직을 처리하는 Service 클래스 작성
- API 계층에서 만든 Controller 클래스를 기반으로 Service 클래스의 큰 틀을 만들 수 있음
- Service와 연동되기 전 MemberController
```java
@RestController
@RequestMapping("/v1/members")
@Validated
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        return new ResponseEntity<>(memberDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        // No need Business logic

        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        System.out.println("# memberId: " + memberId);

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Members");

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        System.out.println("# memberId: " + memberId);
        // No need business logic

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
// MemberService와 연동되기 전 MemberController
```
- MemberController에는 총 다섯 개의 핸들러 메서드 존재
  - `postMember()` : 1명의 회원 등록
  - `patchMember()` : 1명의 회원 수정
  - `getMember()` : 1명의 회원 정보 조회
  - `getMembers()` : N명의 회원 정보 조회
  - `deleteMember()` : 1명의 회원 정보 삭제
- 이 다섯 개의 핸들러 메서드가 전달 받은 요청을 처리하는 메서드를 MemberService 클래스에 작성하면 됨

### MemberService 클래스 기본 구조 작성
```java
public class MemberService {
    public Member createMember(Member member) {
        return null;
    }

    public Member updateMember(Member member) {
        return null;
    }

    public Member findMember(long memberId) {
        return null;
    }

    public List<Member> findMembers() {
        return null;
    }

    public void deleteMember(long memberId) {

    }
}
// MemberService 클래스 기본 구조
```
- `MemberController`가 전달 받은 요청을 실제로 처리하는 `MemberServcie` 클래스의 기본 구조
  - MemeberController 클래스의 핸들러 메서드와 1대1로 매치가 됨

### Member 클래스 구현
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
```
- `@Getter`, `@Setter` 애너테이션은 lombok
- `@AllargsConstructor`
  - 현재 Member 클래스의 모든 멤버 변수를 파라미터로 갖는 Member 생성자를 자동으로 생성함
- `@NoAtgsConstructor`
  - 파라미터가 없는 기본 생성자 자동으로 추가


## DI를 적용한 비즈니스 계층과 API 계층 연동
```java
@RestController
@RequestMapping("/v3/members")
@Validated
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
        ...
		...
}
```
- Spring이 애플리케이션 로드 시, ApplicationContext에 있는 MemberService 객체를 주입함
  - 주입 받는 클래스와 주입 대상 클래스 모두 Spring Bean이어야 함
  - MemberController -> RestController를 통해 Spring Bean임
  - MemberService -> `@Service` 어노테이션 추가
- 생성자가 하나인 경우, `@Autowired` 애너테이션을 붙이지 않아도 됨
  - 생성자가 하나가 아닌 경우, `@Autowired`를 필히 붙일 것

### 개선점
- 1. Controller 핸들러 메서드의 역할은 클라이언트로부터 받은 요청 데이터를 Service 클래스로 전달하는 것과 응답 데이터를 클라이언트로 다시 전송해주는 것임
  - 그런데 지금은 핸들러 메서드가 DTO 클래스를 엔티티 객체로 변환하는 작업까지 도맡아서 하고있음!!!
    - 역할을 분리해줘야함
- 2. Service 계층에서 사용되는 엔티티 객체를 클라이언트의 응답으로 전송하고 있음
  - **DTO 클래스는 API 계층**에서만 데이터를 처리하는 역할을 함
  - **엔티티 클래스는 서비스 계층**에서만 데이터를 처리해야함
  - 엔티티 클래스의 객체를 클라이언트의 응답으로 전송해서 계층 간의 역할 분리가 이뤄지지 않음
 
# 매퍼(Mapper)를 이용한 DTO 클래스 <-> 엔티티(Entity) 클래스 매핑
- 현재 두 가지 문제점 존재
  - MemberController의 핸들러 메서드가 DTO 클래스를 엔티티 클래스로 변환하는 작업까지 도맡아 하고 있음
  - 엔티티 클래스의 객체를 클라이언트의 응답으로 전송함
    - 계층 간 역할 분리가 이뤄지지 않음
- DTO 클래스와 엔티티 클래스를 서로 변환해주는 **매퍼(Mapper)**가 필요한 상황

## Mapper 클래스 구현
```java
@Component  // (1)
public class MemberMapper {
		// (2) MemberPostDto를 Member로 변환
    public Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        return new Member(0L,
                memberPostDto.getEmail(), 
                memberPostDto.getName(), 
                memberPostDto.getPhone());
    }

		// (3) MemberPatchDto를 Member로 변환
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {
        return new Member(memberPatchDto.getMemberId(),
                null, 
                memberPatchDto.getName(), 
                memberPatchDto.getPhone());
    }

    // (4) Member를 MemberResponseDto로 변환
    public MemberResponseDto memberToMemberResponseDto(Member member) {
        return new MemberResponseDto(member.getMemberId(),
                member.getEmail(), 
                member.getName(), 
                member.getPhone());
    }
}
```
- (1) MemberMapper를 Spring Bean으로 등록하기 위해 `@Component` 애너테이션 추가
- (2) `MemberPostDto` 클래스를 Member 클래스로 변환
- (3) `MemberPatchDto` 클래스를 Member 클래스로 변환
- (4) Member 클래스를 `MemberResponseDto`클래스로 변환

### MemberResponseDto 클래스 생성
- 응답 데이터의 역할을 해주는 DTO 클래스
```java
@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private long memberId;

    private String email;

    private String name;

    private String phone;
}
```

## MemberController의 핸들러 메서드에 매퍼 클래스 적용
```java
@RestController
@RequestMapping("/v4/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

		// (1) MemberMapper DI
    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
				// (2) 매퍼를 이용해서 MemberPostDto를 Member로 변환
        Member member = mapper.memberPostDtoToMember(memberDto);

        Member response = memberService.createMember(member);

				// (3) 매퍼를 이용해서 Member를 MemberResponseDto로 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), 
                HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

				// (4) 매퍼를 이용해서 MemberPatchDto를 Member로 변환
        Member response = 
              memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        // (5) 매퍼를 이용해서 Member를 MemberResponseDto로 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), 
                HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member response = memberService.findMember(memberId);

				// (6) 매퍼를 이용해서 Member를 MemberResponseDto로 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), 
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> members = memberService.findMembers();

				// (7) 매퍼를 이용해서 List<Member>를 MemberResponseDto로 변환
        List<MemberResponseDto> response =
                members.stream()
                        .map(member -> mapper.memberToMemberResponseDto(member))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        System.out.println("# delete member");
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
```
- (1) Spring Bean에 등록된 MemberMapper 객체를 MemberController에서 사용하기 위해 DI로 주입 받음
- (2) MemberMapper 클래스를 이용, MemberPostDto를 Member로 변환
- (3) MemberMapper 클래스를 이용해 Member를 MemberResponseDto로 변환
- (4) MemberMapper 클래스를 이용, MemberPatchDto를 Member로 변환
- (5), (6)에서는 MemberMapper 클래스를 이용, Member를 MemberResponseDto로 변환
- (7)의 경우, `MemberService.findMembers()`를 통해 리턴되는 값이 List 이므로 List 안의 Member 객체를 하나씩 꺼내서 MemberResponseDto 객체로 변환

- 문제점 해결
  - MemberController 핸들러 메서드가 DTO를 엔티티로 변환하는 작업까지 하는 문제
    - Mapper를 통해 변환 작업 위임, Controller는 변환 작업을 신경쓰지 않아도 됨
    - 역할 분리로 코드 복잡성 감소
  - 엔티티 클래스의 객체를 클라이언트의 응답으로 전송하는 문제
    - Mapper가 엔티티 클래스를 DTO 클래스로 변환, 서비스 계층에 있는 엔티티를 API 계층에서 직접적으로 사용하는 문제 해결

## MapStruct를 이용한 Mapper 자동 생성
- 도메인 업무 기능이 늘어날 때 마다 일일이 수작업으로 매퍼 클래스를 만드는 것은 비효율적임
- **MapStruct**가 매퍼 클래스를 자동으로 구현해줌

### MapStruct 의존 라이브러리 설정
```java
dependencies {
	...
	...
	implementation 'org.mapstruct:mapstruct:1.4.2.Final'
	annotationProcessor 'org.mapstruct:mapstruct-processor:1.4.2.Final'
}
```
### MapStruct 기반의 매퍼 인터페이스 정의
- DTO 클래스를 변환하는 매퍼를 자동으로 생성하기 위해, 매퍼 인터페이스를 먼저 정의해야함
```java
@Mapper(componentModel = "spring")  // (1)
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
}
```
- `MemberMapper` 인터페이스에 정의한 세 개의 메서드가 수작업으로 작성한 MemberMapper 클래스를 대체함
- `@Mapper` 매퍼 인터페이스로 정의됨
- `(ComponentModel = "spring")`를 통해 Spring Bean으로 등록됨

## DTO 클래스와 엔티티 클래스의 역할 분리가 필요한 이유
- 계층별 관심사의 분리
  - 사용되는 계층이 다르고, 기능에 대한 관심사가 다름
    - DTO 클래스는 API 계층에서 데이터를 전달 받고 데이터를 전송하는 것이 주 목적
    - Entity 클래스는 서비스 계층에서 비즈니스 로직의 결과로 생성된 데이터를 다루는 것이 주 목적임
- 코드 구성의 단순화
  - DTO 클래스에서 사용하는 유효성 검사 애너테이션이 Entity 클래스에서 사용된다면 JPA에서 사용하는 애너테이션과 뒤섞인 상태가 됨
    - 유지보수 난이도 증가
- REST API 스펙의 독립성 확복
  - DTO 클래스 이용 시 클라이언트에게 원하는 정보만 제공 가능