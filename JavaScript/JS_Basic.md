# JS 문법 기초
## 목차
- [JS 문법 기초](#js-문법-기초)
    - [목차](#목차)
    - [변수](#변수)
        - [var, let, const](#var-let-const)
    - [기본형](#기본형)
    - [참조형](#참조형)
    - [연산자](#연산자)
    - [조건문, 반복문](#조건문-반복문)
    - [함수](#함수)
    - [코어 라이브러리](#코어-라이브러리)
    - [jQuery](#jquery)
        - [jQuery 문법 구조](#jquery-문법-구조)
        - [jQuery 선택자](#jquery-선택자)
        - [jQuery 주요 메서드](#jquery-주요-메서드)
        - [jQuery 이벤트 처리](#jquery-이벤트-처리)
    - [DOM](#dom)
        - [DOM 요소 선택](#dom-요소-선택)
        - [DOM 요소(노드)의 CRUD](#dom-요소노드의-crud)
    - [스타일 변경 메서드](#스타일-변경-메서드)
    - [속성 변경 메서드](#속성-변경-메서드)
    - [요소의 위치 및 크기](#요소의-위치-및-크기)
    - [애니메이션](#애니메이션)
    - [클래스](#클래스)
## 변수
### var, let, const
- 재선언, 재할당 가능
```js
var message = "Hello";
var message = "World";
// 같은 변수 이름으로 재선언 가능
console.log(message); //world
```
- 코드가 길어지면 의도치 않게 변수 값이 덮어씌워질 수 있음
- 함수 스코프 O, 블록 스코프 무시 
    - 선언된 함수 내부에서 유효
    - `{}`에서 선언된 변수는 블록을 벗어나도 그대로 접근 가능
- 변수 호이스팅(hoisting) 발생
```js
console.log(num); //undefined
var num = 10;
console.log(num); //10

//실제로 에러가 나야 하는 코드지만
//var num;이 가장 위로 끌여올려지기 때문에
//첫 번째 console.log에서 에러가 나지 않고, undefinde가 출력됨
//컴파일에러를 유도하지 못함, 버그 유발 가능성 높음
```

- let과 const는 흔히 사용하던 방식으로 사용됨
    - let은 재선언 불가능, 재할당 가능
    - const는 재선언, 재할당 불가능
        - 다만 객체나 배열같은 참조형 데이터는 내부 변경 가능
        - 배열 자체 변경은 불가능, 요소는 변경 가능
    - let과 const도 호이스팅이 발생하긴 함
        - 다만 var과 달리, 초기화 전에 접근은 불가능
        - 이를 TDZ(Temporal Dead Zone, 일시적 사각지대)라고 지칭
```js
const person = {name: "Alice", age: 25};
person.age = 30; //가능
person = {name: "Bob", age: 30} // 오류 발생
```

| 선언 방식 | 재선언 가능? | 재할당 가능? | 스코프 | 호이스팅 발생? |
|-----------|--------------|--------------|----------|----------------|
| `var` | ✅ 가능 | ✅ 가능 | 함수 스코프 (Function Scope) | ✅ 발생 (undefined) |
| `let` | ❌ 불가능 | ✅ 가능 | 블록 스코프 (Block Scope) | ✅ 발생 (오류) |
| `const` | ❌ 불가능 | ❌ 불가능 | 블록 스코프 (Block Scope) | ✅ 발생 (오류) |

## 기본형
- 값 자체 할당, stack 저장
- Symbol(ES6)
    - `Symbol("id")`
    - 고유 식별자
- BigInt(ES11)
    - `123n`
## 참조형
- 값이 아니라 메모리 주소 저장
- Heap 저장
- `Object`
    - `{name: "Alice", age:25 }`
    - js는 클래스 생성하지 않고도 객체 생성 가능
        - 블록 스코프 사용
- `Array`
    - `[1,2,3,4,5]`
- `Function`
    - `function() {}`
- 변수 할당 시 메모리 주소 저장
- Spread 연산자를 통한 깊은 복사
```js
let obj1 = { name: "Alice" };
let obj2 = { ...obj1 }; //새 객체 생성

obj2.name = "Bob";

console.log(obj1.name); //"Alice"
console.log(obj2.name); //"Bob"
```

## 연산자
- `**`
    - 거듭제곱
- `===`(일치 연산자)
    - 값과 타입까지 비교

## 조건문, 반복문
- 기존에 알고있던 것과 다른 것 없음
- for each가 for...in과 for...of로 나눠짐
    - for...in은 객체의 속성을 반복할 때 사용
    - for...of는 배열의 요소 반복 시 사용
```js
let person = {name: "Alice", age:25, city: "Seoul"};

for(let key in person){
    console.log(key + ": " + person[key]);
}
```
```

```js
let numbers = [1, 2, 3];

for(let number of numbers){
    console.log(number);
}
```

## 함수
- 함수 선언
```js
function 함수이름(매개변수){
    return 결과값;
}
```

- Anonymous Function
```js
let sh = function(){
    console.log("hello!");
};
sh(); //hello!
```

- Arrow Function
    - ES6에서 추가
```js
const add = (a,b) => a+b;
console.log(add(3,4)); // 7
```

- 즉시 실행 함수(IIFE)
    - 정의되자마자 즉시 실행
    - 전역 변수 오염 방지
```js
(function(){
    console.log("즉시 실행 함수 실행");
})();
```

- 콜백 함수
    - 다른 함수의 인자로 전달
    - 특정 조건에서 실행되도록 예약
    - 비동기 처리에서 많이 사용
    - 문제점
        - 콜백 헬
        - 계속 중첩해서 사용 시 코드가 복잡해짐
```js
function greet(name, callback){
    console.log("이름은, " + name);
    callback();
}

function sayBye(){
    console.log("잘 가요!");
}

greet("Alice", sayBye);
// 이름은, Alice
// 잘 가요!
```

```js
//setTimeout()은 지정한 시간 후에 콜백 함수를 실행
setTimeout(function(){
    console.log("3초 후 실행");
}, 3000);
```

- 클로저(Closure) 함수
    - 함수 내부에서 선언된 함수가 외부 함수의 변수를 기억하는 현상
    - 외부 함수 실행 후에도 내부 함수가 변수에 접근 가능
    - 데이터 은닉, 상태 유지 등
```js
function outer(){
    let count = 0;
    
    function inner(){
        count++;
        console.log("현재 카운트:", count);
    }
    
    return inner;
}

const counter = outer();

counter(); // "현재 카운트: 1"
counter(); // "현재 카운트: 2"
counter(); // "현재 카운트: 3"

//inner()함수가 outer()의 변수를 기억하고 있음
//counter() 실행 마다 count 유지 -> 클로저가 상태 기억
//즉, 마치 class에서 private으로 필드 걸어놓고 메서드를 통해 값 조절을 하듯이
//캡슐화 및 데이터 은닉이 가능함
```

- 함수 커링
    - 클로저 사용 시 함수의 매개변수를 나눠 받을 수 있음
```js
function multiply(x){
    return function(y){
        return x * y;
    };
}

const double = multiply(2); //x=2가 기억됨
console.log(double(5)); //10
console.log(double(10)); //20
```

## 코어 라이브러리
- 전반적으로 Java와 비슷함
- Json
```js
let jsonStr = '{"name": "Alice", "age":25}';

let obj = JSON.parse(jsonStr); // JSON -> Object 변환
console.log(obj.name); // "Alice"

letstr = JSON.stringify(obj); // Object -> JSON 변환
console.log(str); //'{"name": "Alice", "age":25}'
```

## jQuery
- jQuery는 js 라이브러리
- HTML 요소 조작, 이벤트 처리, 애니메이션, AJAX 요청 처리 가능
- 비교 예제
```js
//js로 HTML 요소 숨기기
document.getElementById("box").style.display = "none";

//jQuery로 HTML 요소 숨기기
$("#box").hide();
```

### jQuery 문법 구조
```js
$(선택자).동작();
```
- `$`: jQuery 실행 기호
- `선택자`: 조작할 HTML 요소
    - `id`, `class`, `태그` 등
- `동작()`
    - `hide()`, `show()`, `css()`, `click()` 등
- 예제
```js
<button id="hideBtn">숨기기</button>
<div id="box" style="width:100px; height:100px; background: red;"></div>

<script>
    $(document).ready(function(){
        $("#hideBtn").click(function(){
            $("#box").hide();
        });
    });
</script>
// $(document).ready(function(){...})
// 문서 로드 후 jQuery 코드가 실행되도록 보장함

// $("#hideBtn").click(function(){...})
// 버튼 클릭 시 #box를 숨김
```

### jQuery 선택자
- 선택자를 이용, HTML 요소 쉽게 선택 가능

**1️⃣ 기본 선택자**
| 선택자 | 설명 | 예제 |
|--------|------|------|
| `$("*")` | 모든 요소 선택 | `$("*")` |
| `$("tag")` | 특정 태그 선택 | `$("p")` (모든 `<p>` 요소 선택) |
| `$("#id")` | `id` 속성으로 요소 선택 | `$("#title")` |
| `$(".class")` | `class` 속성으로 요소 선택 | `$(".box")` |

- 이 외에도, 계층, 속성, 필터, 폼 등 여러 선택자 존재

```js
<p>첫 번째 문장</p>
<p class="text">두 번째 문장</p>
<p id="special">세 번째 문장</p>

<script>
    $(document).ready(function(){
        $("p").css("color", "blue"); //모든 p태그 색상 변경
        $(".text").css("font-weight", "bold"); //class="text"만 굵게 처리
        $("#special").css("background", "yellow"); // id="special"만 배경색 가능
    })
</script>
```

### jQuery 주요 메서드
- `hide()`
    - 요소 숨기기
- `show()`
    - 요소 보이기
- `toggle()`
    - 요소 숨기기/보이기 전환
- `fadeIn()`
    - 서서히 나타나기
- `fadeOut()`
    - 서서히 사라지기
- `slideUp()`
    - 위로 접기
- `slideDown()`
    - 아래로 펼치기
- `addClass()`
    - 클래스 추가
- `removeClass()`
    - 클래스 제거
- ex
```js
<button id="fadeOutBtn">사라지기</button>
<button id="fadeInBtn">나타나기</button>
<div id="box" style="width:100px; height:100px; background: red;"></div>

<script>
    $(document).ready(function(){
        $("#fadeOutBtn").click(function(){
            $("#box").fadeOut();
        });
        
        $("#fadeInBtn").click(function(){
            $("#bokx").fadeIn();
        });
    });
</script>
```

### jQuery 이벤트 처리

- HTML 요소에 이벤트 추가

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `.click()` | 클릭 이벤트 | `$("#btn").click(function() {...});` |
| `.mouseover()` | 마우스 오버 이벤트 | `$("#box").mouseover(function() {...});` |
| `.keydown()` | 키 입력 이벤트 | `$("#input").keydown(function() {...});` |
| `.change()` | 입력 값 변경 감지 | `$("#dropdown").change(function() {...});` |
| `.on("이벤트", "선택자", function() {...})` | 동적으로 추가된 요소에도 이벤트 적용 | `$(document).on("click", "#btn", function() {...});` |


```js
<button id="btn">클릭하세요</button>
<p id="text">변경될 문장</p>

<script>
    $(document).ready(function(){
        $("#btn").click(function(){
            $("#text").text("문장이 변경되었습니다!");
        });
    });
</script>
``` 

- 동적 요소 이벤트 추가 (`.on()`)
    - 동적으로 추가된 요소에도 이벤트 적용
    - 일반적인 이벤트 등록 방식은, 초기 존재 요소에만 적용
    - `.on()` 사용 시 추후 동적으로 추가된 요소에도 이벤트 자동 적용
    - 여기서 추후란, html이 렌더링 된 이후, js가 실행되는 순간을 얘기함

```JS
//추후 새로운 .dynamic-btn 버튼 추가 시, 이벤트 적용 불가
$(".dynamic-btn").click(function(){
    alert("클릭되었습니다!");
});

//추후 추가된 요소에도 자동으로 이벤트 적용
//동적으로 추가되는 요소에 이벤트 적용 시 사용할 것
//당연히 AJAX로 추가된 요소에도 .click()이 아닌 .on()을 사용해야 한다.
$(document).on("click", ".dynamic-btn", function(){
    alert("동적으로 추가된 버튼 클릭");
});

//다만, 항상 이벤트를 document에 위임하면 성능이 저하될 수 있음
//이벤트가 포함될 가능성이 높은 상위 요소에 이벤트를 위임
```

## DOM
- HTML을 JavaScript에서 조작할 수 있도록 만드는 인터페이스
    - 웹 페이지 요소 동적 변경 가능
- Document Object Model
    - HTML 문서를 트리(Tree)구조의 객체로 표현
    - JS를 통해 HTML요소 추가, 삭제, 변경
    - 이벤트와 연동, 동적 웹 페이지 제작 가능
- 각 HTML 요소를 Node라 지칭
    - js와 jQuery이용, CRUD 가능

```html
<!DOCTYPE html>
<html>
<head>
    <title>DOM 학습</title>
</head>
<body>
    <h1 id="title">Hello DOM!</h1>
    <p class="text">JavaScript로 조작할 수 있어요.</p>
    <button onclick="changeText()">클릭하세요</button>

    <script>
        function changeText() {
            document.getElementById("title").innerText = "DOM이 변경됨!";
        }
    </script>
</body>
</html>


document
 ├── html
 │    ├── head
 │    │    └── title
 │    └── body
 │         ├── h1 (id="title")
 │         ├── p (class="text")
 │         └── button (onclick="changeText()")
```

### DOM 요소 선택
- JS에서 노드 찾기

| 선택 방법 | 설명 | 예제 |
|------------|-----------------------------|----------------------------------|
| `getElementById` | `id` 속성으로 요소 선택 | `document.getElementById("title")` |
| `getElementsByClassName` | `class` 속성으로 요소 선택 (배열 반환) | `document.getElementsByClassName("text")` |
| `getElementsByTagName` | 특정 태그 이름으로 요소 선택 | `document.getElementsByTagName("p")` |
| `querySelector` | CSS 선택자 방식 (첫 번째 요소 선택) | `document.querySelector(".text")` |
| `querySelectorAll` | CSS 선택자 방식 (모든 요소 선택) | `document.querySelectorAll("p")` |

- jQuery에서 노드 찾기

| 선택자 | 설명 | 예제 |
|--------|--------------|---------|
| `$("태그")` | 특정 태그 선택 | `$("p")` |
| `$("#id")` | `id` 속성으로 요소 선택 | `$("#title")` |
| `$(".class")` | `class` 속성으로 요소 선택 | `$(".box")` |

```js
let title = document.getElementById("title"); // ID로 선택
let paragraphs = document.getElementsByClassName("text"); // class로 선택
let firstParagraph = document.querySelector("p"); // 첫 번째 p 태그 선택
let allParagraphs = document.querySelectorAll("p"); // 모든 p 태그 선택

console.log(title.innerText); // "Hello DOM!"
console.log(paragraphs[0].innerText); // "JavaScript로 조작할 수 있어요."
```

### DOM 요소(노드)의 CRUD

- **1️⃣ 노드 생성 (Create)**

| 방법 | 설명 | 예제 |
|------|------|------|
| `document.createElement("태그")` | 새로운 HTML 요소 생성 | `let newDiv = document.createElement("div");` |
| `.appendChild(노드)` | 부모 요소에 자식 요소 추가 | `parent.appendChild(newDiv);` |
| `.insertBefore(새 노드, 기준 노드)` | 특정 요소 앞에 새 요소 삽입 | `parent.insertBefore(newDiv, referenceNode);` |
| `.append("<태그>...</태그>")` (jQuery) | HTML 요소를 추가 | `$("#container").append("<p>새로운 문장</p>");` |
| `.prepend("<태그>...")` (jQuery) | 부모 요소의 맨 앞에 추가 | `$("#container").prepend("<h2>제목</h2>");` |

---

- **2️⃣ 노드 읽기 (Read)**

| 방법 | 설명 | 예제 |
|------|------|------|
| `document.getElementById("id")` | `id`로 요소 찾기 | `document.getElementById("title");` |
| `document.querySelector("선택자")` | CSS 선택자 방식으로 첫 번째 요소 찾기 | `document.querySelector(".text");` |
| `document.querySelectorAll("선택자")` | CSS 선택자로 모든 요소 찾기 | `document.querySelectorAll("p");` |
| `.text()` (jQuery) | 요소의 텍스트 가져오기 | `$("#title").text();` |
| `.html()` (jQuery) | 요소의 HTML 가져오기 | `$("#title").html();` |

---

- **3️⃣ 노드 수정 (Update)**

| 방법 | 설명 | 예제 |
|------|------|------|
| `.innerText` | 요소의 텍스트 변경 | `title.innerText = "새로운 제목";` |
| `.innerHTML` | 요소의 HTML 변경 (태그 포함) | `title.innerHTML = "<b>강조된 제목</b>";` |
| `.setAttribute("속성", "값")` | 요소의 속성 변경 | `img.setAttribute("src", "new.jpg");` |
| `.style.property` | 요소의 스타일 변경 | `title.style.color = "blue";` |
| `.text("텍스트")` (jQuery) | 요소의 텍스트 변경 | `$("#title").text("새 제목");` |
| `.html("HTML")` (jQuery) | 요소의 HTML 변경 | `$("#title").html("<b>강조된 제목</b>");` |
| `.attr("속성", "값")` (jQuery) | 요소 속성 변경 | `$("#image").attr("src", "new.jpg");` |
| `.css("속성", "값")` (jQuery) | 스타일 변경 | `$("#title").css("color", "blue");` |

---

- **4️⃣ 노드 삭제 (Delete)**

| 방법 | 설명 | 예제 |
|------|------|------|
| `.removeChild(노드)` | 특정 자식 요소 삭제 | `parent.removeChild(childNode);` |
| `.remove()` (jQuery) | 요소 삭제 | `$("#deleteMe").remove();` |

---

- **5️⃣ 노드 이동 (Move)**

| 방법 | 설명 | 예제 |
|------|------|------|
| `.appendChild(노드)` | 부모 요소의 마지막으로 이동 | `parent.appendChild(moveNode);` |
| `.insertBefore(노드, 기준 노드)` | 특정 요소 앞에 이동 | `parent.insertBefore(moveNode, referenceNode);` |
| `.appendTo("선택자")` (jQuery) | 특정 요소의 마지막으로 이동 | `$("#moveMe").appendTo("#container");` |
| `.prependTo("선택자")` (jQuery) | 특정 요소의 맨 앞으로 이동 | `$("#moveMe").prependTo("#container");` |

---

```html
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <div id="container">
        <h1 id="title">Hello DOM!</h1>
        <p class="text">JavaScript로 조작</p>
        <button id="add">추가</button>
        <button id="update">수정</button>
        <button id="delete">삭제</button>
        <button id="move">이동</button>
    </div>

    <script>
        $(document).ready(function() {
            // CREATE: 새로운 요소 추가
            $("#add").click(function() {
                $("#container").append("<p class='text'>새로운 문장 추가됨</p>");
            });

            // UPDATE: 요소 내용 변경
            $("#update").click(function() {
                $("#title").text("DOM이 변경됨!");
                $(".text").css("color", "blue");
            });

            // DELETE: 요소 삭제
            $("#delete").click(function() {
                $(".text:last").remove();
            });
            
            // MOVE: 요소 이동
            $("#move").click(function() {
                $(".text:first").appendTo("#container");
            });
        });
    </script>
</body>
</html>
```

## 스타일 변경 메서드

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `.css("속성", "값")` | 특정 CSS 스타일 변경 | `$("#title").css("color", "blue");` |
| `.addClass("클래스명")` | 클래스 추가 | `$("#title").addClass("highlight");` |
| `.removeClass("클래스명")` | 클래스 제거 | `$("#title").removeClass("highlight");` |
| `.toggleClass("클래스명")` | 클래스 추가/제거 전환 | `$("#title").toggleClass("active");` |
| `element.style.property = "값"` | JavaScript로 특정 스타일 변경 | `document.getElementById("title").style.color = "red";` |
| `element.classList.add("클래스명")` | JavaScript로 클래스 추가 | `title.classList.add("highlight");` |
| `element.classList.remove("클래스명")` | JavaScript로 클래스 제거 | `title.classList.remove("highlight");` |
| `element.classList.toggle("클래스명")` | JavaScript로 클래스 추가/제거 전환 | `title.classList.toggle("active");` |

---

## 속성 변경 메서드
| 메서드 | 설명 | 예제 |
|--------|------|------|
| `.attr("속성")` | 속성 값 가져오기 | `$("#image").attr("src");` |
| `.attr("속성", "값")` | 속성 값 설정 | `$("#image").attr("src", "new.jpg");` |
| `.removeAttr("속성")` | 속성 제거 | `$("#image").removeAttr("alt");` |
| `element.getAttribute("속성")` | JavaScript로 속성 값 가져오기 | `let src = img.getAttribute("src");` |
| `element.setAttribute("속성", "값")` | JavaScript로 속성 값 설정 | `img.setAttribute("src", "new-image.jpg");` |
| `element.removeAttribute("속성")` | JavaScript로 속성 제거 | `img.removeAttribute("alt");` |
| `element.hasAttribute("속성")` | 속성 존재 여부 확인 (`true`/`false`) | `img.hasAttribute("src");` |

---

## 요소의 위치 및 크기 
- **1️⃣ 요소의 위치 관련 메서드**
| 메서드 | 설명 | 예제 |
|--------|------|------|
| `.offset()` | 요소의 문서(document) 기준 위치 반환 | `$("#box").offset().top` (Y축 위치) |
| `.position()` | 요소의 부모(parent) 기준 위치 반환 | `$("#box").position().left` (X축 위치) |
| `.scrollTop()` | 문서 또는 요소의 스크롤된 위치 반환 | `$(window).scrollTop()` |
| `.scrollLeft()` | 가로 스크롤 위치 반환 | `$(window).scrollLeft()` |

- **2️⃣ 요소의 크기 관련 메서드**
| 메서드 | 설명 | 예제 |
|--------|------|------|
| `.width()` | 요소의 내용(Content) 너비 반환 | `$("#box").width()` |
| `.height()` | 요소의 내용(Content) 높이 반환 | `$("#box").height()` |
| `.innerWidth()` | 패딩 포함 너비 반환 | `$("#box").innerWidth()` |
| `.innerHeight()` | 패딩 포함 높이 반환 | `$("#box").innerHeight()` |
| `.outerWidth()` | 패딩 + 테두리 포함 너비 반환 | `$("#box").outerWidth()` |
| `.outerHeight()` | 패딩 + 테두리 포함 높이 반환 | `$("#box").outerHeight()` |
| `.outerWidth(true)` | 패딩 + 테두리 + 마진 포함 너비 반환 | `$("#box").outerWidth(true)` |


## 애니메이션
- **1️⃣ 기본 애니메이션 메서드**
| 메서드 | 설명 | 예제 |
|--------|------|------|
| `.hide()` | 요소 숨기기 | `$("#box").hide();` |
| `.show()` | 요소 보이기 | `$("#box").show();` |
| `.toggle()` | 요소 보이기/숨기기 전환 | `$("#box").toggle();` |

- **2️⃣ 페이드(Fade) 효과**
| 메서드 | 설명 | 예제 |
|--------|------|------|
| `.fadeIn()` | 서서히 나타나기 | `$("#box").fadeIn();` |
| `.fadeOut()` | 서서히 사라지기 | `$("#box").fadeOut();` |
| `.fadeToggle()` | 서서히 나타나기/사라지기 전환 | `$("#box").fadeToggle();` |

- **3️⃣ 슬라이드(Slide) 효과**
| 메서드 | 설명 | 예제 |
|--------|------|------|
| `.slideUp()` | 위로 접기 (숨기기) | `$("#box").slideUp();` |
| `.slideDown()` | 아래로 펼치기 (보이기) | `$("#box").slideDown();` |
| `.slideToggle()` | 펼치기/접기 전환 | `$("#box").slideToggle();` |

- 커스텀 애니메이션(`.animate()`)
    - CSS 속성 값 변경 애니메이션 적용 가능
    - (left, top, width, height, opacity) 등
    - `.animate()` 메서드 체이닝 가능
```js
$(document).ready(function(){
    $("#move-btn").click(function(){
        $("#box").animate({
            left: "200px"
        }, 1000);
    });
});
// 참고로, 당연히 콜백 함수 사용 또한 가능하다

```

## 클래스
- 함수는 기본적으로 this를 자동 결정
- `call()`, `apply()`, `bind()` 사용 시 this를 원하는 객체로 지정 가능
  
- **call(), apply(), bind() 메서드 비교**

| 메서드 | 실행 방식 | 인자 전달 방식 | 반환 값 | 주요 활용 |
|--------|--------|------------|--------|--------|
| `call()` | 즉시 실행 | 개별 인자 전달 | 함수 실행 결과 | 다른 객체에도 메서드 적용 |
| `apply()` | 즉시 실행 | **배열로** 인자 전달 | 함수 실행 결과 | 배열 형태의 인자 전달이 필요할 때 |
| `bind()` | **즉시 실행되지 않음** | 개별 인자 전달 | **새로운 함수 반환** | 이벤트 핸들러에서 `this` 고정 |

```js
class Person {
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }

    introduce(job, location) {
        console.log(`안녕하세요, 저는 ${this.name}, ${this.age}살이고, ${location}에서 일하는 ${job}입니다.`);
    }

    increaseAge(years) {
        this.age += years;
        console.log(`${this.name}의 나이가 ${this.age}살로 증가했습니다.`);
    }
}

// 객체 생성
let person1 = new Person("철수", 25);
let person2 = new Person("영희", 30);

// --------------------
// call() 사용 (즉시 실행 & 개별 인자 전달)
// --------------------
console.log("call() 사용");
person1.introduce.call(person2, "디자이너", "서울");
// "안녕하세요, 저는 영희, 30살이고, 서울에서 일하는 디자이너입니다."

// --------------------
// apply() 사용 (즉시 실행 & 인자를 배열로 전달)
// --------------------
console.log("apply() 사용");
person1.introduce.apply(person2, ["개발자", "부산"]);
// "안녕하세요, 저는 영희, 30살이고, 부산에서 일하는 개발자입니다."

// --------------------
// bind() 사용 (즉시 실행되지 않고, 새로운 함수 반환)
// --------------------
console.log("bind() 사용");
let increaseAgeForPerson2 = person1.increaseAge.bind(person2, 5);
increaseAgeForPerson2(); 
// "영희의 나이가 35살로 증가했습니다."

// --------------------
// bind()를 활용한 이벤트 핸들러
// --------------------
console.log("bind()를 활용한 버튼 이벤트");

// 버튼 추가 (HTML 요소가 필요함)
let button = document.createElement("button");
button.innerText = "나이 증가";
document.body.appendChild(button);

// `this`를 person1로 고정하여 이벤트 핸들러 설정
button.addEventListener("click", person1.increaseAge.bind(person1, 1));
```