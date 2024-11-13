## Lecutre6
### 레이아웃
- ![image](https://github.com/user-attachments/assets/716dc4d6-edf9-4b48-ad48-3db227449c70)
  - LinearLayout
    - 선형 레이아웃, 수평 또는 수직 방향으로 위젯 배치 
  - TableLayout
    - 행과 열로 구성된 테이블 레이아웃
  - GridLayout
    - TableLayout과 유사하지만 보다 유연한 그리드 설정 가능

### LinearLayout
- 속성
  - orientation
    - `setOrientation(int)`
      - horizontal은 수평, vertical은 수직으로 배치
  - gravity
    - `setGravity(int)`
      - x축과 y축 상에 자식을 어떻게 배치할 것인지?
  - baselineAligned
    - `setBaselineAligned(boolean)`
      - false로 설정 시, 자식뷰들의 기준선을 정렬하지 않음 
- setOrientation 예시
  - ![image](https://github.com/user-attachments/assets/a4463cde-fe8b-4a8d-8397-90bd71806fa7)
- Gravity 속성 값
  - top, bottom, left, right, center, center_vertical, fill_vertical, center_horizontal, fill_horizontal, fill 등
  - 예시
    - ![image](https://github.com/user-attachments/assets/f0e188ef-f38b-45c2-80f6-41ff65b07d35)
- gravity vs layout_gravity
  - gravity는 컨테이너에 자식 위젯들을 어떻게 배치하느냐
  - layout_gravity는 자신의 위치를 부모 레이아웃의 어디에 위치시킬 것인지
    - ![image](https://github.com/user-attachments/assets/a60449f3-6d20-425b-9806-8d95c3bba0e2)
  - ![image](https://github.com/user-attachments/assets/23a2ad7d-a95c-432f-af63-05592f7020b3)
    - `android:gravity = "center"`
      - 이 설정은 레이아웃 안의 모든 자식 뷰(버튼 포함)에게 영향을 미침

### 가중치(weight)
- ![image](https://github.com/user-attachments/assets/85904a77-39eb-4b4a-8427-fc05d86b3674)
  - 남아있는 공간의 1/6, 2/6, 3/6을 할당받음
- ![image](https://github.com/user-attachments/assets/898b9a63-91d1-462b-87ea-3de2332bbe73)
  - 가중치를 1로 선언한 2개의 텍스트 뷰는 남아있는 공간을 동일하게 차지
- ![image](https://github.com/user-attachments/assets/19d6fbbd-5fec-465b-91c7-a055b9f08a6a)
  - 에디트 텍스트만 가중치가 1이고 나머지는 전부 0
- ![image](https://github.com/user-attachments/assets/38a61e20-cc71-43ed-bf87-c61f8750d2e1)
  - `android:layout_weight="1"`을 통해 가중치를 1로 설정

### 중첩 선형 레이아웃
- LinearLayout을 중첩해서 사용하면 성능 문제가 발생할 수 있음
  - `ConstraintLayout`을 사용하는 것을 권장
    - 중첩 레이아웃 없이도 다양한 화면 구성 가능, 속도가 더 빠름

## Lecture 7
### 이벤트 처리
- Android는 event-driven 방식을 사용함
  - 애플리케이션이 다른 작업을 하다 사용자의 입력으로 인해 이벤트 발생 시, 이벤트를 처리
- 이벤트를 처리하는 3가지 방법
  - XML 파일에 이벤트 처리 메서드 등록
  - 이벤트 처리하는 객체를 생성하여 이벤트 처리
  - 뷰 클래스의 이벤트 처리 메서드 재정의
- 이벤트 처리 객체 사용
  - ![image](https://github.com/user-attachments/assets/df35881f-0cc1-4f1e-9e26-118f4d90c6fc)
    - 이벤트를 처리하는 콜백 메서드가 정의된 인터페이스를 이벤트 리스너라고 지칭
  - 이벤트 리스너
    - 특정 이벤트를 감지하고 해당 이벤트 발생 시 실행되어야 하는 코드를 정의하는 인터페이스
  - 예시
    - ![image](https://github.com/user-attachments/assets/45dca26f-a651-4c8d-9d81-e0080f443032)
- 리스너 객체 생성 방법
  - 리스너 클래스를 익명 클래스로 정의
  - 람다식 이용
- 익명 클래스로 처리
  - 익명 클래스는 클래스를 정의하면서 동시에 객체 생성
    - 한 번만 사용 가능함
  - ![image](https://github.com/user-attachments/assets/1c0c5a0d-c6dd-4cdc-9cbd-00f55f543d9c)
    - 버튼이 클릭되는 경우에 발생하는 클릭 이벤트 처리 시
      - `OnClickListener` 인터페이스를 구현하는 객체 생성 -> `setOnClickListener()` 호출 및 버튼에 설정
  - ![image](https://github.com/user-attachments/assets/d6277cce-47a1-415f-b946-3cf5fe9e4aa6)
### 이벤트 처리 예제
- 화면에 이미지가 있고, 하단에 버튼 4개
  - 각 버튼 클릭 시 이미지 배경색이 변경되는 앱 작성
- ![image](https://github.com/user-attachments/assets/ef96eb14-ab33-4569-a2db-2dc630e0c4ab)
- 람다식 이용법
  - 이벤트 리스너가 하나의 콜백 메소드만을 갖고 있는 경우에만 가능
```java
Button.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
        v++;
    }
});

Button.setOnClickListener(e->{v++});

Button colorButton1 = findViewById(R.id.colorButtion1);

colorButton1.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
        changeClothingColor(Color.RED);
    }
});

colorButton1.setOnClickListener(view -> changeClothingColor(Color.RED));
```

## Lecture 8
### 앱과 액티비티
- 앱은 하나 이상의 액티비티들로 구성
- 액티비티는 앱을 구성하는 컴포넌트 중 하나
- 태스크
  - 스택에 있는 액티비티
  - Back 키를 누르면 현재 액티비티를 제거하고, 이전 액티비티로 되돌아감
  - 사용자가 방문한 액티비티들은 종료되지 않고, 백그라운드에 존재
- 인텐트
  - 다른 화면에 정보를 보내줘야 함
  - 정보를 인텐트에 실어서 보냄(Dto 느낌인듯)
  - 종류
    - 명시적 인텐트
      - `Intent intent = new Intent(this, NextActivity.class)`
        - 실행하고자 하는 액티비티의 이름을 적어줌(NextActivity)
      - `startActivity(intent)`
        - intent 객체에 기술된 액티비티 시작
    - 암시적 인텐트
      - 액티비티 A가 인텐트 생성해서 startActivity()에 넘기면
      - 안드로이드 시스템이 모든 애플리케이션 대상으로 인텐트 필터를 검색
      - 일치하는 애플리케이션 발견 시 일치하는 액티비티 B를 시작, `onCreate()`를 호출하고 인텐트를 인수로 넘김
- 명시적 인텐트 예시
```java
//intent 객체를 생성해서 ACtivity1에서 Activity2로 전환할 준비
public void onClick(View v){
    Intent intent = new Intent(Activity1.this, Activity2.class);
    startActivity(intent);
}
```
- 명시적 인텐트 예시2
  - 2초 동안 스플래시 화면을 보여주고, 2초가 지나면 로그인 화면 표시
```java
new Handler().postDelayed(new Runnable(){ //1
    @Override
    public void run(){
        Intent intent = new Intent(MainActivity.this, LogitnActivity.class); //2
        startActivity(intent); //3
        finish(); //4
    }
}, SPLASH_TIMEOUT);

//1. Handler와 Runnable을 통해 일정 시간 지연을 생성
//2. 로그인 화면으로 이동하기 위한 Intent 생성
//3. 생성한 Intent로 로그인 화면 실행
//4. 스플래시 화면 종료
```
- Handler의 필요성
  - ![image](https://github.com/user-attachments/assets/1f8e4522-8017-42ff-aac3-da867fbe1158)
    - Main Thread(UI Thread)
      - 안드로이드 앱에서 UI를 업데이트하거나 상호작용하는 대부분의 작업은 메인 스레드에서 수행됨
      - `onClick`, `onDraw`등
    - Message Queue와 Message
      - 메인 스레드는 내부적으로 메시지 큐를 갖고 있으며, 큐에 들어오는 msg를 처리함
      - msg는 여러 이벤트나 작업에 대한 요청
    - Handler의 역할
      - Handler는 MessageQueue에 메시지를 추가하고, 메인 스레드가 이 메시지를 처리하도록 연결하는 역할을 함
  - ![image](https://github.com/user-attachments/assets/9c271658-6109-47e6-9045-ff8ab9d16fba)
    - Looper는 Message Queue에서 메시지를 하나씩 꺼내서 처리
    - Looper는 계속 반복(loop)하면서 메시지를 확인, 메시지가 들어오면 처리하도록 Handler에게 전달
    - Handler와 Thread
      - 여러 개 쓰레드에서 각각 Handler를 생성할 수 있고, Message Queue에 메시지를 추가할 수 있음
- 액티비티에서 결과받기
  - 서브 액티비티로부터 결과를 받아야 하는 경우가 있음
  - `startActivity()`를 호출하는 것이 아닌 `startActivityForResult()`를 사용해야 한다
  - 서브 액티비티는 작업을 완료한 후 결과를 `setResult()`로 설정하고 종료
  - 메인 액티비티는 `onActivityResult`메서드에서 해당 결과값을 처리함
  - Intent를 통한 값 전달
    - Intent 객체의 `Extras` 필드를 사용함
      - `putExtra()`로 추가, `getExtra()`로 추출
- 예제
  - 로그인 액티비티에서 id와 pw 입력, 서브 액티비티로 넘기고, 서브 액티비티에서 id와 pw를 인증, 인증 결과를 메인 액티비티로 넘기는 앱 작성
```java
ActivityResultLauncher<Intent> launcher;

Intent intent = new Intent(MainActivity.this, SecondActivity.class);
intent.putExtra("ID", email);
intent.putExtra("Password", password);
launcher.launch(intent);


launcher = registerForActivityResult(new ActivityResultContracts.
StartActivityForResult(),
result ->{

});


Intent intent = getIntent();
```


- 암시적인 인텐트
  - 특정 작업을 하길 원하지만, 그 작업을 담당하는 컴포넌트의 이름을 명확히 모르는 경우
```java
Intent intent = new Intent(Intent.ACTION_SEND);
intent.putExtra(Intent.EXTRA_EMAIL, recipientArray);
startActivity(intent);
```
- 예시
  - ![image](https://github.com/user-attachments/assets/1775dff6-4d52-49dd-88c4-264953f7d9a7)
```java
public void onClick(View view) {
    Intent intent = null;
    switch (view.getId()) {
        case R.id.web:
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
            break;

        case R.id.call:
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)12345789"));
            break;

        case R.id.map:
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.30,127.27?z=10"));
            break;

        case R.id.contact:
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
            break;
    }
    if (intent != null) {
        startActivity(intent);
    }
}
```

- 멀티태스킹
  - 동시에 여러 태스크 진행
  - ex) 안드로이드 홈버튼을 통해 현재 task를 background로 보냄

### Activity Lifecycle
- 세 가지 주요 상태
  - Resumed
    - 포커스를 받아 사용자에게 보이는 상태
  - Paused
    - 포커스는 없지만, 여전히 화면에 보이는 상태
  - Stopped
    - 안 보이는 상태, 메모리 상태는 유지
- 주요 콜백 메서드
  - onCreate
    - 액티비티 처음 생성 시 호출, 초기화 작업
  - onStart
    - 사용자에게 보이기 직전에 호출
  - onResume
  - onPause
  - onStop
  - onDestroy
- ![image](https://github.com/user-attachments/assets/1ae80e99-2d67-4899-84ba-c10a1948a1d4)
- onPause의 중요성
  - onPuase 이후에 앱의 프로세스가 시스템에 의해 종료 가능
  - 앱 프로세스가 종료되기 전 반드시 호출되는 마지막 메서드, 중요한 데이터는 이 시점에 저장
  - 다른 액티비티 호출 시 onPause가 가장 먼저 호출되어 데이터 일관성을 유지함
- 예제
  - ![image](https://github.com/user-attachments/assets/2e44c36b-0506-487c-80c5-a137b54f13f1)
  - ![image](https://github.com/user-attachments/assets/2f780ba9-656a-4359-8f01-e3966bbe2ced)


### Android Activity 상태 저장
- Activity 상태 저장 필요성
  - 시스템이 메모리를 확보하기 위해 강제로 액티비티를 종료하는 경우, 상태가 손실될 수 있음
  - 입력한 데이터나 UI 상태를 유지하려면 상태 저장 필요
- Bundle 클래스
  - key-value 쌍으로 데이터를 저장하는 클래스
- 상태 저장 및 복원 메서드
  - `onSaveInstanceState(Bundle outState)`
    - 시스템이 액티비티 강제 종료 시 호출, Bundle 객체에 상태 저장
  - `onRestoreInstanceState(Bundle savedInstanceState)`
    - 액티비티 재생성 시 호출, 저장된 데이터 복원

```java
if(savedInstanceState != null){
    count = savedInstanceState.getInt("count");
}

@Override
public void onSaveInstanceState(Bundle outState){
    super.onSaveInstanceState(outState);
    outState.putInt("count", count);
}
```