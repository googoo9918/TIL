## JQuery 개요
- AJAX는 비동기 방식으로 서버와 데이터를 주고받음
- `$.ajax()` 기본 구조
```javascript
$.ajax({
    url: "data.json", //데이터를 요청할 서버 주소
    method: "GET", //HTTP 요청 방식
    dataType: "json", //응답 데이터 형식(json, text, html 등)
    // 요청이 성공했을 때 실행할 코드
    success: function(response){
        console.log("데이터 수신 성공:", response);
    },
    // 요청이 실패했을 때 실행할 코드
    error: function(xhr, status, error){
        console.log("에러 발생:", error);
    },
    // 요청 완료 후 실행할 코드(성공/실패 무관)
    complete: function(){
        console.log("AJAX 요청 완료!");
    }
})
```
- `$.get()` 
    - jQuery 에서는 `$.ajax()`보다 더 간단한 방법으로 AJAX 요청 가능
```javascript
$.get("data.json", fuction(response){
    console.log("GET 요청 응답:", response);
    // data.json에서 데이터를 가져와 출력
})
```
- `$.post()`
    - 위와 같음
```javascript
$.post("server.php", { name: "Alice", age: 25}, function(response) {
    console.log("POST 요청 응답:", response);
});
// server.php로 { name: "Alice", age: 25 } 데이터를 보냄
```
- AJAX 응답 데이터를 HTML에 추가
```javascript
$.get("data.json", function(response){
    response.forEach(function(user){
        $("#userList").append('<li>${user.name} - ${user.email}</li>');
    });
});
// data.json에서 데이터를 가져와 <ul id="userList"> 내부에 <li>로 추가
```
- AJAX 요청 중 로딩 표시
```javascript
$("loadData").click(function(){
    $("loading").show(); //로딩 화면 표시
    
    $.ajax({
        url: "data.json",
        method: "GET",
        success: function(response){
            $("#content").html(JSON.stringify(response)); //데이터를 화면에 표시
        },
        complete: function(){
            $("#loading").hide(); //로딩 화면 숨기기
        }
    })
})
// AJAX 요청 전에 #loading 요소를 보이게 함
// 요청이 끝나면 #loading 요소를 숨김
```

- `fetch()` vs jQuery AJAX
    - 최근에는 `fetch()` API도 많이 이용되는 추세
    - Fetch API는 모던 JavaScript 표준, AJAX는 오래된 방식
```javascript
$.get("data.json", function(response){
    console.log(response);
})

fetch("data.json")
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.log("에러 발생:", error));
```

- Fetch API vs jQuery AJAX 비교

| 기능 | Fetch API | jQuery AJAX |
|------|----------|------------|
| **라이브러리 필요 여부** | ✅ 기본 JavaScript 기능 | ❌ jQuery 필요 |
| **Promise 지원** | ✅ 기본 지원 | ❌ 콜백 방식 (ES6 이전 방식) |
| **`async/await` 사용 가능 여부** | ✅ 사용 가능 | ❌ 불편함 (Promise 변환 필요) |
| **JSON 자동 변환** | ❌ `response.json()` 필요 | ✅ `dataType: "json"` 설정 가능 |
| **에러 처리** | ❌ HTTP 상태 코드(404, 500)를 자동으로 감지하지 않음 (`if (!response.ok)`) | ✅ `error()` 콜백으로 쉽게 처리 가능 |
| **코드 가독성** | ✅ `async/await`로 가독성 좋음 | ❌ 콜백 체이닝으로 복잡할 수 있음 |
| **파일 업로드 지원** | ✅ `fetch()`로 가능 (`FormData` 사용) | ✅ `$.ajax()`에서 더 다양한 옵션 제공 |
| **타임아웃 설정** | ❌ 직접 `AbortController()` 사용 필요 | ✅ `timeout` 옵션 제공 |
| **IE(Internet Explorer) 지원** | ❌ 기본 지원 안 됨 (폴리필 필요) | ✅ IE11에서 사용 가능 |
| **최신 웹 API와의 호환성** | ✅ Service Worker, Cache API와 호환 | ❌ 최신 API와 호환성 낮음 |

- AJAX 응용: 폼 데이터 서버에 전송
```javascript
$("#submitForm").click(function()){
    let userData = {
        name: $("#name").val(),
        email: $("#email").val()
    };
    
    $.post("server.php", userData, function(response){
        alert("서버 응답: " + response); //화면 팝업
    });
};
//사용자가 입력한 name과 email 데이터를 서버로 전송
```

## $()와 $(this) 차이
- `$()`는 jQuery에서 HTML 요소 선택 시 사용 함수
- js의 `document.querySelector()` 또는 `document.getElementById()`와 비슷한 역할을 함
    - 다만 위 두 함수와 다르게, jQuery 객체이므로 `.css()`와 같은 메서드 사용 가능
- `$(this)`
    - 이벤트 헨들러 내부에서 사용될 때, 현재 이벤트가 발생한 요소
    - 사용자가 클릭한 요소 or 이벤트가 발생한 요소 동적 조작 시 사용
```js
$("button").click(function(){
    $(this).css("background", "blue");
});
// 사용자가 클릭한 버튼의 배경색을 파란색으로 변경
// 여러 개의 버튼이 있어도, 클릭한 버튼만 변경됨
```
- `this` vs `$(this)`
    - `this`
        - 순수 JS 객체(DOM 요소)
    - `$(this)`
        - jQuery 객체(메서드 사용 가능)
```javascript
$("p").click(function(){
    console.log(this); //JavaScript 객체 (DOM 요소)
    console.log($(this)); //jQuery 객체
    $(this).css("font-weihgt", "bold"); //jQuery 메서드 사용 가능
})
// $(this) 사용 시, jQuery 메서드 적용
// this만 사용하면, this.style.color = "red" 처럼 직접 속성 설정
```

- `$(this)`와 `event.target` 차이
    - `event.target`
        - 이벤트가 발생한 순수 JavaScript DOM 요소
    - `$(this)`
        - 이벤트 발생 요소를 jQuery 객체로 변환
```javascript
$("ul").on("click", "li", function(event) {
    console.log("this:", this);          // 순수 JavaScript 요소
    console.log("event.target:", event.target);  // 클릭된 요소
    console.log("$(this):", $(this));    // jQuery 객체
});

// this와 event.target은 거의 유사하다고 봐도 무방
// $(this)만 jQuery 메서드 사용할 수 있도록 변환
```