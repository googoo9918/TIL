# To-Do App
## CORS(Cross-Origin Resource Sharing)
- CORS 정책에 대한 개념과 이를 설정하는 코드
  - [참고](https://github.com/ssu18/TIL/blob/main/SpringSecurity/JWT/JWT%20%EC%A0%81%EC%9A%A9%20%EC%82%AC%EC%A0%84%20%EC%9E%91%EC%97%85(3).md)
- SOP(Same Origin Policy)
  - 애플리케이션 간 출처가 다른 경우, 스크립트 기반 HTTP 통신을 통한 리소스 접근이 제한되는 정책 
  - CORS는 이 접근 제한의 예외 조항으로, 사전 설정을 통해 리소스에 선택적으로 접근할 수 있는 권한을 부여하도록 브라우저에 알려주는 정책임
  - 즉, 브라우저는 SOP에 의해 기본적으로 다른 출처의 리소스 공유를 막지만, CORS를 사용하면 접근 권한을 얻을 수 있게 되는 것임
- CORS 관련 에러 발생 시
  - ![image](https://user-images.githubusercontent.com/102513932/207755187-023f502e-895e-4eaf-84c8-654001631481.png)
    - 다른 출처의 리소스를 가져오려 했지만 SOP 때문에 접근 불가능
    - CORS 설정을 통해 서버 응답 헤더에 'Access-Control-Allow-Origin' 작성 시 접근 권한을 얻을 수 있음

### CORS 정책 동작 방식
- 프리플라이트 요청(Preflight Request)
  - 실제 요청을 보내기 전, OPTIONS 메서드로 사전 요청을 보내 해당 출처 리소스에 접근 권한이 있는지부터 확인하는 것을 의미함
  - 브라우저는 서버에 실제 요청을 보내기 전에 프리폴라이트 요청을 보냄
    - 응답 헤더의 `Access-Control-Allow-Origin`으로 요청을 보낸 출처가 돌아오면 실제 요청을 보내게 됨
    - 요청을 보낸 출처가 접근 권한이 없으면 브라우저에서 CORS 에러를 띄우고, 실제 요청은 전달되지 않음
- 단순 요청
  - 단순 요청은 특정 조건이 만족되면 프리플라이트 요청을 생략하고 요청을 보내는 것을 말함
- 인증정보를 포함한 요청(Credentialed Request)
  - 요청 헤더에 인증 정보를 담아 보내는 요청임
    - 출처가 다를 경우에는 별도의 설정을 하지 않으면 쿠키를 보낼 수 없음
  - **이 경우 프론트, 서버 양측 모두 CORS 설정이 필요함**

### CORS 정책 설정 방법
- Global 설정 클래스를 이용해 특정 도메인에 모두 적용하는 방법
  - 상단 링크 참고
- 애너테이션을 이용해 컨트롤러 단에서 적용하는 방법
  - `@CrossOrigin` 애너테이션 이용
  - 애너테이션이 붙은 컨트롤러에서만 적용, 원하는 요청에 따른 응답에만 CORS 설정을 할 수 있음
```java
@CrossOrigin(origins = "https://codestates.com")
//Controller에 애너테이션을 이용해 설정, 옵션을 이용해 세부 설정 추가 가능
@RestController
public class HelloController{

}
// 컨트롤러 단에서 애너테이션을 이용해 CORS 설정

@RestController
public class HelloController{

    @CrossOrigin //해당하는 메서드에 애너테이션을 이용해 설정
    @GetMapping
    public ResponseEntity<> getHello(){

    }
}
// 메서드 단에서 애너테이션을 이용해 CORS 설정
```

### CSRF(Cross-Origin Reqeust Forgery)
- [참고](https://github.com/ssu18/TIL/blob/main/SpringSecurity/Basic_Concept.md/WebSecurityAttack.md#csrf)
- CORS는 Cross-Origin(다른 출처), CSRF는 Cross-Site(다른 사이트)일 때의 설정을 해줘야 하는 점에서 매우 유사함
- CSRF는 사이트 간 요청을 위조하는 공격을 의미함
  - 신뢰할 수 있는 사용자를 가장하여 웹 사이트에 원치 않는 명령을 보내는 방식
- Spring Security는 기본적으로 아무 설정을 하지 않아도 CSRF 공격을 방지 하기 위해 클라이언트로부터 CSRF Token을 수신 후 검증함
  - 403 에러를 마주할 수 있어, `disable`처리한 후 진행해야되는 경우도 존재

## To-Do App 개발 요구사항
### 기능 요구사항
- Create
  - 할 일 목록 등록
- Read
  - 전체 할 일 목록 조회
  - 등록되어있는 할 일의 특정 id 입력하여 조회
- Update
  - 이미 한 일에는 완료 표시
  - 할 일의 내용 수정
- Delete
  - 등록된 전체 할 일 삭제
  - 등록된 할 일의 특정 id를 입력해 삭제
### API 명세
- ![image](https://user-images.githubusercontent.com/102513932/207758156-2ae02eb5-63d7-4a80-a4ff-b57824c64ea7.png)
- ![image](https://user-images.githubusercontent.com/102513932/207758215-e4986b5d-c766-4a7b-8878-feed79d331e9.png)

### 테이블 구성 명세
- ![image](https://user-images.githubusercontent.com/102513932/207758589-91440945-ffda-429f-bc1e-3c4c34a2cd9e.png)

## 코드
### application.yml
```yml
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/ToDoList
    username: sa
    password:
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create
    properites:
      hibernate:
        show_sql: true
        format_sql: true

logging.level:
  org.hibernate.SQL: debug
#   org.hibernate.type:trace
```
### Controller
```java
package com.example.TodoList.controller;

import com.example.TodoList.dto.TodoDto;
import com.example.TodoList.dto.TodoPatchDto;
import com.example.TodoList.dto.TodoPostDto;
import com.example.TodoList.dto.TodoResponseDto;
import com.example.TodoList.entity.Todo;
import com.example.TodoList.mapper.TodoMapper;
import com.example.TodoList.response.MultiResponseDto;
import com.example.TodoList.service.TodoService;
import com.example.TodoList.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@CrossOrigin("https://todobackend.com") //CORS
@RestController
@RequestMapping("/todos")
@Validated
@Slf4j
public class TodoController {

    private final static String MEMBER_DEFAULT_URL = "localhost:8080";
    private final TodoService todoservice;
    private final TodoMapper mapper;

    public TodoController(TodoService todoservice, TodoMapper mapper) {
        this.todoservice = todoservice;
        this.mapper = mapper;
    }

    @PostMapping()
    public ResponseEntity PostTodo(@Valid @RequestBody TodoPostDto requestBody){
        Todo todo = mapper.todoPostDtoToTodo(requestBody);

        Todo createdTodo = todoservice.createTodo(todo);
//        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdTodo.getId());
        // (defaultUri, resourceId)

//        return ResponseEntity.created(location).build();
        return new ResponseEntity<>(mapper.todoToTodoDto(createdTodo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity GetTodo(@Positive @PathVariable("id") int id) {

        Todo todo = todoservice.findTodo(id);

        return new ResponseEntity<>(mapper.todoToTodoDto(todo), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity GetTodos(@Positive @RequestParam int page,
//                                   @Positive @RequestParam int size){
//        // DTO로 해결할 수 있는 부분이 아니니 @RequestParam이 붙겠지
//        Page<Todo> pageTodos = todoservice.findTodos(page - 1, size);
//        // Spirng 에서는 기본적으로 page 번호 0부터 시작 , 클라이언트 측에서는 1부터 사용하게 page -1 처리
//        List<Todo> todos = pageTodos.getContent();
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(mapper.todoToTodoDtos(todos), pageTodos)
//                , HttpStatus.OK);
// }
// Todo Backend 사이트에서 페이지네이션을 지원하지 않아 주석 처리

    @GetMapping
    public ResponseEntity GetTodos(){
        List<Todo> todoList = todoservice.findTodos();

        return new ResponseEntity<>(mapper.todoToTodoDtos(todoList), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity PatchTodo(@PathVariable("id") @Positive int id,
                                    @Valid @RequestBody TodoPatchDto reqeustBody) {
        reqeustBody.updateId(id); // 요청 들어올 떄 Id 같이 안들어오는데 해당 id를 수정해줘야되니까 설정

        Todo todo = todoservice.updateTodo(mapper.todoPatchDtoToTodo(reqeustBody));

        return new ResponseEntity<>(mapper.todoToTodoDto(todo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity DeleteTodo(@PathVariable("id") @Positive int id) {

        todoservice.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity DeleteTodos(){
        todoservice.deleteTodos();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
```

### DTO
```java
@Getter
@AllArgsConstructor
public class TodoPatchDto {
    private int id;

//    @NotBlank(message = "할 일은 공백이 아니어야 합니다")
    private String title;

//    @NotBlank(message = "우선순위는 공백이 아니어야 합니다")
    private int todo_order;

    private boolean completed;

    public void updateId(int id) {
        this.id = id;
    }
}

@Getter
@AllArgsConstructor
public class TodoPostDto {
//    @NotBlank(message = "할 일은 공백이 아니어야 합니다")
    private String title;

//    @NotBlank(message = "우선순위는 공백이 아니어야 합니다")
    private int todo_order;

    private boolean completed;
}

@Getter
@AllArgsConstructor
public class TodoResponseDto {
    private int id;

    private String title;

    private int todo_order;

    private boolean completed;
}
```

### Entity
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo {

    @GeneratedValue
    @Id
    private int id;

    private String title;

    private int todo_order;

    private boolean completed;

    public void updateTodo_order(int todo_order) {
        this.todo_order = todo_order;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateTodoCompeleted(boolean completed) {
        this.completed = completed;
    }
}
```

### Exception
```java
public class BusinessLogicException extends RuntimeException {

    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptioncode) {
        super(exceptioncode.getMessage());
        this.exceptionCode = exceptioncode;
    }
}

public enum ExceptionCode {

    TODO_NOT_FOUND(404, "Todo not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
```

### Mapper
```java
@Mapper(componentModel = "spring")
public interface TodoMapper {

    Todo todoPostDtoToTodo(TodoPostDto reqeustBody);

    Todo todoPatchDtoToTodo(TodoPatchDto requestBody);

    TodoResponseDto todoToTodoDto(Todo todo);

    List<TodoResponseDto> todoToTodoDtos(List<Todo> todos);
    //전체조회에서 사용
}

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-13T11:09:26+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.16 (Azul Systems, Inc.)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todo todoPostDtoToTodo(TodoPostDto reqeustBody) {
        if ( reqeustBody == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setTitle( reqeustBody.getTitle() );
        todo.setTodo_order( reqeustBody.getTodo_order() );
        todo.setCompleted( reqeustBody.isCompleted() );

        return todo;
    }

    @Override
    public Todo todoPatchDtoToTodo(TodoPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setId( requestBody.getId() );
        todo.setTitle( requestBody.getTitle() );
        todo.setTodo_order( requestBody.getTodo_order() );
        todo.setCompleted( requestBody.isCompleted() );

        return todo;
    }

    @Override
    public TodoResponseDto todoToTodoDto(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        int id = 0;
        String title = null;
        int todo_order = 0;
        boolean completed = false;

        id = todo.getId();
        title = todo.getTitle();
        todo_order = todo.getTodo_order();
        completed = todo.isCompleted();

        TodoResponseDto todoResponseDto = new TodoResponseDto( id, title, todo_order, completed );

        return todoResponseDto;
    }

    @Override
    public List<TodoResponseDto> todoToTodoDtos(List<Todo> todos) {
        if ( todos == null ) {
            return null;
        }

        List<TodoResponseDto> list = new ArrayList<TodoResponseDto>( todos.size() );
        for ( Todo todo : todos ) {
            list.add( todoToTodoDto( todo ) );
        }

        return list;
    }
}
```

### Repository
```java
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
```

### Response
- 페이지네이션에서 사용
```java
@Getter
public class MultiResponseDto<T> {

    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page){
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}


@AllArgsConstructor
@Getter
public class PageInfo {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
```

### Service
```java
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getId()); //객체 검증 받음

        Optional.ofNullable(todo.getTitle()) //인자에 바꿀 내용 있는지 확인
                .ifPresent(title -> findTodo.updateTitle(title)); //있으면 바꿔주기

        Optional.ofNullable(todo.getTodo_order()) //인자에 바꿀 내용 있는지 확인
                .ifPresent(todo_order -> findTodo.updateTodo_order(todo_order)); //있으면 바꿔주기

        Optional.ofNullable(todo.isCompleted()) //인자에 바꿀 내용 있는지 확인
                .ifPresent(completed -> findTodo.updateTodoCompeleted(completed)); //있으면 바꿔주기

        // 현재는 엔티티가 하나라 직접 작성해 주었지만, 엔티티가 많아질 경우 CustomBeanUtils를 사용할 것!
        return todoRepository.save(findTodo);
    }

    public Todo findTodo(int id) {
        return findVerifiedTodo(id);
    }

//    public Page<Todo> findTodos(int page, int size) {
//        return todoRepository.findAll(PageRequest.of(page, size,
//                Sort.by("id").descending()));
//    }
    
    public List<Todo> findTodos(){
        return todoRepository.findAll();
    }

    public void deleteTodo(int id) {
        Todo findTodo = findVerifiedTodo(id);

        todoRepository.delete(findTodo);
    }

    public void deleteTodos(){
        todoRepository.deleteAll();
    }
    private Todo findVerifiedTodo(int id) {
        Optional<Todo> optionaltodo = todoRepository.findById(id);
        // 레포지토리에서 findBy 하면 Optional 래핑 되어 나옴 -> 래핑 벗겨서 리턴할거임
        Todo findTodo = optionaltodo.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        // 만약에 존재하지 않는 id면 에러 발생시키기 (존재하지 않는데 update를 어떻게 해)
        return findTodo;
        // 검증된 객체 리턴
    }
}
```

### Utils
```java
public class UriCreator {

    public static URI createUri(String defaultUrl, long resourceId) {
        return UriComponentsBuilder
                .newInstance()
                .path(defaultUrl + "/{resource-id}")
                .buildAndExpand(resourceId)
                .toUri();
    }
}
```

## Tomcat을 이용한 수동 배포
- 여태까지는 Spring boot에 내장되어 있는 톰캣을 기본으로 사용하여 JAR로 패키징 된 애플리케이션을 실행함
- 하지만 내장 컨테이너보다 톰캣을, JAR보단 WAR로 패키징한 경우 더 다양한 설정과 웹 환경을 구성할 수 있음
  - ex) 하나의 톰캣 서버로 여러 도메인이 각각의 웹 애플리케이션을 구성할 수 있는 Virtual Host 기능
  - 톰캣의 자체 기능으로 하나의 애플리케이션에서 다양한 웹 앱을 구성할 수 있다는 장점이 있지만, 비교적 경량으로 포함되어 있는 내장 컨테이너에선 불가능함
- JAR은 자바 프로젝트를 압축한 파일로, JRE만 있어도 쉽게 실행 가능함
- WAR은 웹 관련 자원을 포함하며 웹 애플리케이션을 압축한 파일임
  - 기본적으로 별도의 웹 서버가 필요하며 JAR보다 더 넓은 범위를 압축하기 위한 포맷임

## JRE 설치
- [다운로드](https://www.azul.com/downloads/?version=java-11-lts&os=windows&architecture=x86-64-bit&package=jre)
- ![image](https://user-images.githubusercontent.com/102513932/207761015-5edda57d-67d0-4ae7-9f39-88952331f010.png)
- 환경 변수 설정
  - ![image](https://user-images.githubusercontent.com/102513932/207761069-b0755675-8f6c-4cdd-8805-b6194a06f372.png)

## 톰캣 설치
- [다운로드](https://tomcat.apache.org/download-90.cgi)
  - ![image](https://user-images.githubusercontent.com/102513932/207761158-9d01dc4f-2d7f-403d-87a0-1c39af5fe55f.png)
  - 현재 JDK 11을 사용하기 때문에 가장 호환성이 높은 9 버전 설치
  - 압축 해제 시
    - bin : 실행하거나 종료할 수 있는 스크립트 파일 존재
    - conf : 서버 설정 파일 존재
    - webapps: 톰캣 위에서 실행할 웹 애플리케이션의 기본 저장 경로
      - **`.war` 파일을 이 곳에 이동시키거나, 설정파일에서 경로를 변경하여 실행할 수 있음**

## 서버 실행 및 종료 명령어
- cmd 창을 이용해 진행
```
C:\Users\...\apache-tomcat-9.0.68\bin> startup.bat 
# 톰캣을 실행합니다.
C:\Users\...\apache-tomcat-9.0.68\bin> shutdown.bat 
# 톰캣을 종료합니다.
```

### 실행 시 보이는 화면
- `localhost:8080`
- ![image](https://user-images.githubusercontent.com/102513932/207761682-d7ed018e-b620-4c97-b57b-947eb1381b5f.png)

## 톰캣으로 웹 어플리케이션 실행
### JAR -> WAR 포맷 변경
- `build.gradle` 수정
```
plugins {
    id 'org.springframework.boot' version '2.4.2'
    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
    id 'war' // 추가합니다.
    id 'java'
}
```
- `{project}Applicaion` 파일 수정
```java
@SpringBootApplication
public class TodoListApplication extends SpringBootServletInitializer {
  // SpringBootServletInitializer를 상속

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

// config를 Override합니다. 이 때 return되는 값의 클래스 이름에 유의
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TodoListApplication.class);
	}
}
```
### 프로젝트 빌드
- 프로젝트를 빌드하면, 기존의 `.jar` 파일 대신 `.war` 파일 확인 가능
  - `.war` 파일을 톰캣의 webapps 디렉토리 하단으로 이동함
  - ![image](https://user-images.githubusercontent.com/102513932/207762119-736f1a04-f1f0-4fc7-bcc3-aae595d670ac.png)
    - webapps 디렉 내부에 `.war` 파일을 옮겨 놓으면, 톰캣이 실행되며 해당 애플리케이션을 실행함

### server.xml 파일 수정
```xml
...
<Host name="localhost"  appBase="webapps" unpackWARs="true" autoDeploy="true">
        <Context path="/" docBase="TodoList-0.0.1-SNAPSHOT"  reloadable="false" > </Context>
        
        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log" suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />
</Host>
...
```
- Host 내부에 Context 설정 추가
  - docBase에는 sample 대신 개인이 설정한 프로젝트 이름 작성
  - 이후 톰캣 실행 시 `.war` 파일을 압축해제하여 webapps에 동일한 이름의 디렉토리가 구성됨

### 톰캣 실행 후 확인
- `localhost:8080` 접속 시 webapps 디렉에 옮겨놓은 `.war` 파일 실행 확인 가능
  - 접속 시 WhiteLableError 페이지 확인! (별다른 설정 없을 시)

### 톰캣 포트 변경
- `server.xml` 파일 수정
```xml
<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />
```
- **주의: 개인 회원가입을 하고 토큰을 생성 받아야 포트 변경이 가능하다.**
  - 섣부르게 변경하지 말 것

## Ngrok을 이용해 로컬에서 서버 실행
### Ngrok
- 네트워크 설정을 하지 않아도 방화벽을 넘어 외부에서 로컬 환경에 접근할 수 있게 해주는 터널링 프로그램
  - 무료 플랜의 경우, 연결 세션이 약 2시간가량 유지됨
    - AutoToken 등록 시 시간 제한 없이 사용 가능
  - 개발 목적으로 임시 도메인을 발급받아 테스팅하기에 유용함
- 개발 영역이 나눠져있는 환경에서 통신테스트 할 때 유용하게 사용 가능
  - 프론트와 백이 완전 분리되어있는 상황에서 일부 기능을 구현한 후 통신 테스트 가능
- Ngrok 사용 시 특정 포트를 실행하면 임시 도메인이 할당
  - 특정 사용자가 로컬 환경에 접근 가능
  - 토큰을 등록하지 않아도 간단한 테스트 용이함

### Ngrok 설치
- [다운로드](https://ngrok.com/download)
- ![image](https://user-images.githubusercontent.com/102513932/207804720-31800b73-122c-48df-a76d-f475add4fcff.png)
  - 화면의 Download 버튼을 누른 후, 압축을 해제함
  - 압축 해제 후 디렉토리를 열어보면, ngrok 명령어를 입력할 수 있는 응용 프로그램 확인 가능
- 프로그램 실행
  - ![image](https://user-images.githubusercontent.com/102513932/207804956-904cc0fe-7d83-4af2-84d8-b4f1f0f90d63.png)

### Ngrok 실행
```
// ngrok http {port} 의 형태로 원하는 포트를 연결
// 임시 도메인을 연결하여 외부에서 접근할 수 있도록 설정
ngrok http 8080
```
- ![image](https://user-images.githubusercontent.com/102513932/207805190-eda6dd6a-72b5-4fd4-b594-f8ddfe922cca.png)
  - 8080 포트로 포워딩하는 임시 도메인 확인 가능
  - 해당 연결의 세션 지속시간 등을 확인 가능

### Postman을 이용한 결과 확인
- ![image](https://user-images.githubusercontent.com/102513932/207805637-8425c101-4224-4d22-89d7-e24291bc2742.png)

### Todo-backend 사이트를 이용한 결과 확인
- `https://todobackend.com/client/index.html?{임시domain}/todos`
  - 접속 시 생성된 목록 모두 조회 가능
- ![image](https://user-images.githubusercontent.com/102513932/207805992-018e8cbc-1841-44ff-b740-df6ab236cd57.png)
