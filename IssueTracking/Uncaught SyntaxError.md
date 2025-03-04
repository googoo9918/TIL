## 문제 상황

### 컨트롤러
```java
// 1. 객체를 JSON으로 변환
List<Map<String, Object>> rqCategoryList = itsmService.selectCategoryList();
String rqCategoryJson = new Gson().toJson(rqCategoryList);
modelMap.put("rq_category", rqCategoryJson);

// 2. 변환하지 않고 그냥 사용
List<Map<String, Object>> rqCategoryList = itsmService.selectCategoryList();
modelMap.put("rq_category", rqCategoryList);
```

```js
//1. JSON 문자열로 받고
//2. JSON.parse를 통해 js에서 이를 실제 객체로 사용
var itsm_m01_s01 = {
        // 카테고리 목록 (JSON 문자열)
        categoryList: '${rq_category}'
        
        init: function(){
            itsm_m01_s01.parse_data();       // 카테고리 JSON 파싱
        }
        
         parse_data: function () {
            // categoryList를 실제 JS 배열로 변환
            // 컨트롤러에서 DB조회 후 JSON으로 전달받은 것을 화면에서 파싱
            try {
                itsm_m01_s01.categoryList = JSON.parse(itsm_m01_s01.categoryList);
            } catch (e) {
                // 파싱 실패 시 빈 배열로 세팅
                itsm_m01_s01.categoryList = [];
            }
        }
}
```

- `Gson.toJson()`을 사용하지 않은 경우
    - `${}`을 사용할 수 없다.
- 만약 `rq_category`가 다음과 같은 데이터를 갖고 있다 가정하자
```java
rq_category = [
    { id=1, name="Category1" },
    { id=2, name="Category2" }
];
```
- 이를 다음과 같이 사용하면
```js
var categoryList = ${rq_category};
```
- 실제 출력되는 JavaScript 코드는 다음과 같다
```js
var categoryList = [{id=1, name="Category1"}, {id=2, name="Category2"}];
```
- 그런데, JS에서는 객체의 key-value를 `=`이 아니라, `:`로 구분해야 한다
- 따라서 SyntaxError가 발생한다
- 올바른 JS 객체 형태는 다음과 같다
```js
var categoryList = [{ "id": 1, "name": "Category1" }, { "id": 2, "name": "Category2" }];
```
- 따라서 ''를 통해서 JSON을 문자열로 변경하고,
- `JSON.parse()`를 통해서 JSON 문자열을 JS 객체로 변환한다.

- 또 다른 문제 상황이 발생하여, 이에 공유하고자 한다.
    - 위의 해결 방법과 같이 문제를 해결하려고 했으나, Syntax Error가 지속적으로 발생하였다.
    - 기존에 사용하였던 코드는 다음과 같다.
```java
modelMap.put("rq", new Gson().toJson(rq));
```

```js

var itsm_m01_s01 = {
        rq: '${rq}'
}

init: function () {
                itsm_m01_s01.set_form();         // 폼 초기 세팅
            },
            
set_form: function () {
                itsm_m01_s01.rq = itsm_m01_s01.rq.replaceAll(/\r/gi,"\\r");
                itsm_m01_s01.rq = itsm_m01_s01.rq.replaceAll(/\n/gi,"\\n");

                try {
                    itsm_m01_s01.rq = JSON.parse(itsm_m01_s01.rq);
                } catch (e) {
                    // 실패 시 빈 객체
                    console.log(e.toString());
                    itsm_m01_s01.rq = {};
                }
                
// 이 상황에서, e.toString()을 사용하니, char중 하나에서 에러가 발생한 것을 알 수 있었다.
// 따라서, replaceAll에서 문제가 발생했음을 확인할 수 있었다.
// 실제로 로그를 찍어봤을 떄, 데이터 형식은 다음과 같다.
// replaceAll 하기 전(json으로 받아서 '${rq}'를 통해 문자열로 변환한 경우)

// {"urgency":"G002202","reg_user":"20210235","due_date":"20250214","req_type":"G002102","rq_contents":"DB그룹홈페이지 개발 서버 구성을 위한 도메인 등록 요청 합니다.

// kor-test.dbinc.co.kr      210.127.40.79 (국문)
// eng-test.dbinc.co.kr     210.127.40.79 (영문)
// m-test.dbinc.co.kr	      210.127.40.79 (모바일)
// ","req_no":"CSD2025021400002","client_id":"G000001","rq_title":"도메인생성요청"}

// \n이나, \r, \t가 들어가 있는 것을 알 수 있다.

// replaceAll을 진행한 후, 다음과 같이 데이터가 변환된 것을 확인할 수 있다.
// {"urgency":"G002202","reg_user":"20210235","due_date":"20250214","req_type":"G002102","rq_contents":"DB그룹홈페이지 개발 서버 구성을 위한 도메인 등록 요청 합니다.\r\n\r\nkor-test.dbinc.co.kr      210.127.40.79 (국문)\r\neng-test.dbinc.co.kr     210.127.40.79 (영문)\r\nm-test.dbinc.co.kr	      210.127.40.79 (모바일)\r\n","req_no":"CSD2025021400002","client_id":"G000001","rq_title":"도메인생성요청"}
```
- **즉, JSON.parse()를 사용하기 위해서는 JSON 문자열이 정확한 형식을 따라야 한다.**
    - `\n`, `\r\n`과 같은 이스케이프 시퀀스를 사용해야 한다.
    - 이를 사용하지 않으면, 줄바꿈이 문자 그대로 해석되어 파서가 오류를 발생시킬 수 있다.
- 그렇다면, replaceAll()을 사용했음에도 문제가 발생한 이유는 무엇인가??
- 다음의 해결책으로 이해할 수 있다.

```js
var itsm_m01_s01 = {
        rq: {}
}

init: function () {
                itsm_m01_s01.set_form();         // 폼 초기 세팅
            },
            
set_form: function () {
                try {
                    itsm_m01_s01.rq = JSON.parse(JSON.stringify(${rq}));
                } catch (e) {
                    // 실패 시 빈 객체
                    console.log(e.toString());
                    itsm_m01_s01.rq = {};
                }
}

// 이 코드를 설명하면 다음과 같다.
// 일단 자바에서 Gson.toJson()을 통해 Json으로 문자를 넘기는 형식까지는 동일하다.
// 일단 rq를 빈 객체로 생성하고, JSON.stringify를 통해서 js 객체나 값을 JSON 문자열로 변환한다.
// 이때, 모든 제어문자가 처리된다.
// 이후에 JSON 문자열을 JSON.parse를 통해서 JS 객체로 변환한다.
// 실제로 출력값은 다음과 같다.
// {"urgency":"G002202","reg_user":"20210235","due_date":"20250214","req_type":"G002102","rq_contents":"DB그룹홈페이지 개발 서버 구성을 위한 도메인 등록 요청 합니다.\r\n\r\nkor-test.dbinc.co.kr      210.127.40.79 (국문)\r\neng-test.dbinc.co.kr     210.127.40.79 (영문)\r\nm-test.dbinc.co.kr\t      210.127.40.79 (모바일)\r\n","req_no":"CSD2025021400002","client_id":"G000001","rq_title":"도메인생성요청"}
// 위 코드에서 replaceAll을 한 코드와 비교해 보면, \t가 추가되어 있는 것을 알 수 있다.
// 그렇다. replaceAll에서 Tab을 처리하지 않아 여태껏 문제가 발생했던 것이다.
// 따라서  itsm_m01_s01.rq = itsm_m01_s01.rq.replaceAll(/\t/gi,"\\t");와 같은 코드를 추가했을 때, 문제가 사라진 것을 확인할 수 있었다.
```

- 결론
    - 제어문자 처리가 필요한 경우에는, `JSON.stringify()`를 사용하자!!