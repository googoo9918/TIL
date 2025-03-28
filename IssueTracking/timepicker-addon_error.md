## timepicker-addon을 사용하며 생긴 에러
- 기존 로직에서는, jquery-ui.js에 포함되어 있는 datepicker를 사용하여, date만 설정하고, 시간과 분은 Select Box를 이용하여 직접 입력받고 있었다.
- 나는 이러한 로직이 마음에 들지 않아, datetimepicker를 사용하고자 하였다.
- 물론 최근에 나온 datetimepicker library를 사용할 수도 있었으나, 그렇게 된다면 기존 css나 js를 다 변경했어야 하기 때문에
    - timepicker를 사용하여 기존 datepicker에 추가, datetimepicker를 사용할 수 있게 하는 `timepicker-addon_error` 라이브러리를 추가하게 되었다.
    - 문제는 이미 deprecated된 library인지라, 사용법 등을 확인하기가 매우 불편했다.
- 변경된 코드들
- 기존
```js
function datePickerInit(objId) {
    $('#' + objId).datepicker({
        showOn : "both",
        buttonImage : "/resources/images/ico_calendar.gif", // 버튼 이미지
        buttonImageOnly : true,
        buttonText : "", // 날짜선택
        dateFormat : "yy-mm-dd",
        changeYear : true,
        changeMonth : true,
        closeText: '닫기',
        currentText: '오늘',
        prevText: '이전달',
        nextText: '다음달',
        yearRange: 'c-60:c+10',
        monthNames: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: ' '

    }
```
- 이후
```js
function datePickerInit(objId) {
    $('#' + objId).datetimepicker({
        showOn : "both",
        buttonImage : "/resources/images/ico_calendar.gif", // 버튼 이미지
        buttonImageOnly : true,
        buttonText : "", // 날짜선택
        dateFormat : "yy-mm-dd",
        timeFormat: "HH:mm",
        changeYear : true,
        changeMonth : true,
        closeText: '닫기',
        currentText: '오늘',
        prevText: '이전달',
        nextText: '다음달',
        yearRange: 'c-60:c+10',
        monthNames: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: ' ',
        showSecond: false,
        stepHour: 1,
        stepMinute: 10,
        timeText: '시간',
        hourText: '시',
        minuteText: '분',
        controlType: 'select',  // 시간 및 분을 스크롤(바꾸려면 slider)이 아닌 드롭다운 메뉴로 선택
        oneLine: true         // 시간과 분을 한 줄에 표시
    }
```
- 기존 코드에서 showSecond부터 8개 필드가 추가되었다
- 나는 다음과 같이 유효성 검사를 하려고 했는데, 문제가 발생하였다.
```js
$('#visit_date_start').on('change', function (e) {

    var startTime = new Date($(this).val());
    var currentTime = new Date();
    currentTime.setMinutes(currentTime.getMinutes() - 10);  // 현재 시간 10분 전으로 설정
    console.log("변화 전 startTime:" + startTime);
    console.log("currentTime: " + currentTime);

    // 방문 시작 시간이 현재 시간 이전인 경우
    if (startTime< currentTime) {
    gfnAlertMsg2('방문 시작 시간은 현재 시간 이후로 설정해야 합니다.', function() {
            $('#visit_date_start').datetimepicker("setDate", currentTime.val())
        });
        return;
    }

});

// 방문 종료 시간 유효성 검사
$('#visit_date_end').off('focusout').on('change', function () {
    var endTime = new Date($(this).val());
    var startTime = new Date($('#visit_date_start').val());
    var currentTime = new Date();

    // 방문 종료 시간이 현재 시간 이전인 경우
    if (endTime < currentTime) {
        gfnAlertMsg2('방문 종료 시간은 현재 시간 이후로 설정해야 합니다.', function() {
        // 종료 시간을 현재 시간으로 설정
            $('#visit_date_end').datepicker("setDate", currentTime.val());
        });
        return;
    }

    // 종료 시간이 시작 시간보다 이전이면 시작 시간으로 설정
    if (endTime < startTime) {
        $('#visit_date_end').val($('#visit_date_start').val());  // 종료 시간을 시작 시간으로 설정
        gfnAlertMsg2('방문 종료 시간은 방문 시작 시간 이후로 설정해야 합니다.', function() {
        // 종료 시간을 시작 시간으로 설정
        $('#visit_date_end').datepicker("setDate", startTime.val());
        });
    }
});
```
- 문제는 날짜를 바꿨을 때 이벤트 핸들러가 두 번씩 동작하여, 팝업이 여러개가 중복해서 뜬다는 것이었다.
    - 콘솔을 찍어봐도 마찬가지, 핸들러가 여러번 동작하고 있었다.
    - 이벤트 중복 호출에 대해 찾아보니
        - target 요소의 부모 태그가 이벤트에 반응할 수 있다고 하여(이벤트 버블링)
        - `stopPropagation()`, `off()` 등을 적용해 보았으나
            - 정상 동작하지 않았다.
    - 혹시나 regist_event나 init이 중복 호출되었나? 다른 이벤트 핸들러에도 같은 문제가 발생하는가? 등을 확인해 보았으나
        - 모두 아니었음.
    - 이외에도 여러가지 방식을 시도해보았으나, 다 실패하였다
        - ex)init을 할 때도 값을 변경했다고 인식하는지 등... / mutex같은 개념을 통해 lock을 걸려고 하였으나.. timestamp 차이가 거의 나지 않아 정상적인 동작은 실패하였다.
    - 그러다 생각이 든 것이, datetimepicker라는 것이 datepicker에 timepicker를 추가하여 datetimepicker를 만들어 낸 것이니
        - 날짜와 시간을 다른 리소스로 인식하고 `.on('change')`에 대해 두 번 동작하는 것인가? 라는 생각이 들었다.
        - 혹시나 해서 시간만 바꿔보았더니, 콘솔이 한 번만 찍히는 것을 확인할 수 있었다.
            - **애초에 여러 방식으로 테스트를 진행해 보았다면, 더 빨리 찾을 수 있던 케이스였다.**
        - 이제 문제 원인을 확인했으니, 해결책은 금방이겠거니 생각했지만..
            - deprecated된 코드라, 사용 방법 등을 찾기가 매우 어려웠고(github에 있는 사용 예시 판매글 또한 403 Forbidden으로 변해있는 상태)
            - gpt는 하등 아무 도움이 되지 않았다.
            - 구글을 열심히 뒤져보니, 변경 이벤트에 대해 핸들링을 적용한 코드를 찾아볼 수 있었다.
            - 수정된 코드는 다음과 같다.
```js
// 방문 시작 시간 유효성 검사
                $('#visit_date_start').datepicker("option", "onSelect", function (selectedDate) {
                    var startTime = new Date(selectedDate);
                    console.log("selectDate: " + selectedDate);
                    var currentTime = new Date();
                    currentTime.setMinutes(currentTime.getMinutes() - 10); // 현재 시간 10분 전으로 설정

                    console.log("변화 전 startTime:" + startTime);
                    console.log("currentTime: " + currentTime);

                    // 방문 시작 시간이 현재 시간 이전인 경우
                    if (startTime < currentTime) {
                        gfnAlertMsg2('방문 시작 시간은 현재 시간 이후로 설정해야 합니다.', function() {
                            $('#visit_date_start').datepicker("setDate", currentTime);
                        });
                        return;
                    }

                    // 종료 시간을 시작 시간 이후로 설정
                    var endTime = new Date($('#visit_date_end').val());
                    if (endTime < startTime) {
                        $('#visit_date_end').val($('#visit_date_start').val()); // 종료 시간을 시작 시간으로 설정
                        gfnAlertMsg2('방문 종료 시간은 방문 시작 시간 이후로 설정해야 합니다.', function() {
                            $('#visit_date_end').datepicker("setDate", startTime);
                        });
                    }
                });

                // 방문 종료 시간 유효성 검사
                $('#visit_date_end').datepicker("option", "onSelect", function (selectedDate) {
                    var endTime = new Date(selectedDate);
                    var startTime = new Date($('#visit_date_start').val());
                    var currentTime = new Date();

                    // 방문 종료 시간이 현재 시간 이전인 경우
                    if (endTime < currentTime) {
                        gfnAlertMsg2('방문 종료 시간은 현재 시간 이후로 설정해야 합니다.', function() {
                            $('#visit_date_end').datepicker("setDate", currentTime);
                        });
                        return;
                    }

                    // 종료 시간이 시작 시간보다 이전이면 시작 시간으로 설정
                    if (endTime < startTime) {
                        $('#visit_date_end').val($('#visit_date_start').val());  // 종료 시간을 시작 시간으로 설정
                        gfnAlertMsg2('방문 종료 시간은 방문 시작 시간 이후로 설정해야 합니다.', function() {
                            $('#visit_date_end').datepicker("setDate", startTime);
                        });
                    }
                });
```
- datepicker의 onSelect option을 사용해서 핸들링을 처리하니, 핸들러가 정상적으로 동작하는 것을 확인할 수 있었다.


>> 혹은, js 코드를 직접 까봤다면 조금 더 생산적으로 접근할 수 있지 않았을까?? **너무 사용 예시에 의존하지 않도록 하자**.