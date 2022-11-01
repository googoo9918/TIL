## 목차
- [목차](#목차)
- [회원 웹 기능 - 홈 화면 추가](#회원-웹-기능---홈-화면-추가)
  - [회원 관리용 홈](#회원-관리용-홈)
  - [홈 컨트롤러 추가](#홈-컨트롤러-추가)
- [회원 웹 기능 - 등록](#회원-웹-기능---등록)
  - [회원 등록 폼 HTML](#회원-등록-폼-html)
  - [회원 등록 폼 컨트롤러](#회원-등록-폼-컨트롤러)
- [회원 웹 기능 조회](#회원-웹-기능-조회)
  - [회원 리스트 HTML](#회원-리스트-html)
  - [회원 컨트롤러에서 조회 기능](#회원-컨트롤러에서-조회-기능)

## 회원 웹 기능 - 홈 화면 추가
### 회원 관리용 홈
```HTML
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
    <div>
        <h1>Hello Spring</h1>
        <p>회원 기능</p>
        <p>
            <a href="/members/new">회원 가입</a>
            <a href="/members">회원 목록</a>
        </p>
    </div>
</div> <!-- /container -->
</body>
</html>
```
### 홈 컨트롤러 추가
```java
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
    // localhost:8080 접속 시 home.html 내려 받는다
    // 컨트롤러가 정적 파일보다 우선순위가 높다
    // static에 index.html 만들었으니까 원래 아무것도 없으면 welcome page가 실행되야 하는데
    // 이전 정적 컨텐츠 이미지를 보면, 스프링 컨테이너에서 관련 컨트롤러가 있는지 확인하고
    // 없으면 static 파일을 실행하게 되어있다.
    // 따라서 지금은 홈 화면 매핑된 URL이 있으므로 컨트롤러를 우선 호출하게 됨
}
```
- 컨트롤러가 정적 파일보다 우선순위가 높다

## 회원 웹 기능 - 등록
### 회원 등록 폼 HTML
- resources/templates/members/CreateMemberForm.html
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
    <form action="/members/new" method="post">
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" placeholder="이름을
입력하세요">
        </div>
        <button type="submit">등록</button>
    </form>
</div> <!-- /container -->
</body>
</html>

```
### 회원 등록 폼 컨트롤러
```java
@Controller
public class MemberController {
   // 설명은 스프링 빈과 의존관계 파일 참고
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    // 웹 어플리케이션에서 /members/new 입력시 해당 메서드(createForm) 호출
    // return "members/createMemberForm" -> resources의 템플릿에 있는 members/createMemberForm에 찾아가서 랜더링 하라
    // 뷰 리졸버가 화면을 찾아서 처리하게 됨
    // 스프링 부트 템플릿엔진 기본 viewName 매핑 
    // 'resources:templates/' + {ViewName} + '.html'

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
    // 1. 회원 가입을 누르면 /members/new로 접속하게 됨(getmapping)
    // 2. members/createMemberForm 리턴
    // 3. 템플릿에서 해당 파일 찾음 -> 뷰 리졸버에 의해 선택 -> 타임리프 랜더링
    // 4. 등록 버튼을 누르면 action url로 post방식으로 넘어옴
    // 5. **멤버 컨트롤러 -> postmapping(데이터를 폼에 전달하는 경우 보통 사용)**
    // url은 같지만 get이냐 post냐에 따라 다르게 매핑 가능
}
```
## 회원 웹 기능 조회

### 회원 리스트 HTML
- resources/templates/members/memberList.html
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
 <div>
     <table>
         <thead>
         <tr>
             <th>#</th>
             <th>이름</th>
         </tr>
         </thead>
         <tbody>
         <tr th:each="member : ${members}">
             <td th:text="${member.id}"></td>
             <td th:text="${member.name}"></td>
         </tr>
         <!-- 루프를 돌면서 member에 있는 값들을 꺼냄 -->
         </tbody>
     </table>
 </div>
</div> <!-- /container -->
</body>
</html>

```

### 회원 컨트롤러에서 조회 기능
```java
@GetMapping("/members") //회원 웹기능_조회
    public String list(Model model) {

        List<Member> members = memberService.findMembers();
        // 멤버 전체 조회해서 members에 저장
        model.addAttribute("members", members);
        // 멤버 리스트 전체를 model에 담아 화면에 넘김
        return "members/memberList";

    }
```