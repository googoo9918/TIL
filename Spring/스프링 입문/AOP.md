## AOP가 필요한 상황
- 모든 메소드의 호출 시간을 측정하고 싶다면?
- 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)
  - 
- 회원 가입 시간, 회원 조회 시간을 측정하고 싶다면?
- ![image](https://user-images.githubusercontent.com/102513932/196461199-5fe0d028-8c58-4543-8b73-09aa217e5710.png)
  - 모든 메서드에 시간 측정 로직을 넣어줘야 한다
### MemberService 회원 조회 시간 측정 추가
```java
@Transactional 
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    public Long join(Member member){ //회원 가입
        long start =System.currentTimeMillis();
        try{
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        }
    }
    public List<Member> findMembers(){ // 회원 조회
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }
}
```
- 문제
  - 회원 가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아님
  - 시간을 측정하는 로직은 공통 관심 사항임
  - 시간을 측정하는 로직과 핵심 비즈니스 로직이 섞여 유지보수 어려움
  - 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어려움
  - 시간을 측정하는 로직 변경 시 모든 메서드를 찾아가면서 변경해야 함
## AOP 적용
- AOP(Aspect Oriented Programming)
  - 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)분리
  - ![image](https://user-images.githubusercontent.com/102513932/196468109-a26c525f-bf45-43e2-9fd0-9463eb7b5f8f.png)

### 시간 측정 AOP 등록
```java
@Component
// 혹은 SpringConfig 파일에
// @Bean
// public TimeTraceAop timeTraceAop(){
//     return new TimeTraceAop();
// }
// 를 통해 등록해줄 수도 있는데, 이 방식이 AOP임을 명시하는데에도 좋다
@Aspect
public class TimeTraceAop {
    //@Around를 통해 문서에 대해 타겟팅을 할 수 있다.
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws  Throwable{

        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
```
- 해결
  - 회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리
  - 시간을 측정하는 로직을 별도의 공통 로직으로 만듬
  - 핵심 관심 사항을 깔끔히 유지 가능
  - 변경이 필요하다면 해당 로직만 변경
  - 원하는 적용 대상 선택 가능
    - @Around 사용

## 스프링의 AOP 동작 방식
### AOP 적용 전 의존관계
![image](https://user-images.githubusercontent.com/102513932/196475161-211ba1b4-342e-4a7f-9652-5fabcf05cf54.png)
- 의존관계가 있고, 서비스를 호출함
### AOP 적용 후 의존관계
![image](https://user-images.githubusercontent.com/102513932/196475222-e4f43bd6-43b5-4d20-be11-302895844f8e.png)
- helloController가 프록시를 호출
- 가짜 스프링 빈(프록시)를 세워 놓고 프록시 종료 후 진짜 서비스 호출함
### AOP 적용 전 전체 그림
![image](https://user-images.githubusercontent.com/102513932/196475272-c3d46776-8256-4f36-8881-071939df7911.png)
### AOP 적용 후 전체 그림
![image](https://user-images.githubusercontent.com/102513932/196475332-7acb72d1-10a8-4139-b9f4-75c4ff71998f.png)
- DI를 해주기 때문에 AOP 또한 가능한 것
  - helloController에서 new로 memberService를 부르면 프록시 호출이 불가능함