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