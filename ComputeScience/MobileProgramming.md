## Lecture1
### cpp vs java
  - OS마다 별도 실행 파일 생성 vs Write Once, Run Anywhere
  - 클래스 이름과 파일 이름 일치 필요x vs 클래스 이름과 파일 이름 반드시 일치
  - 무슨 타입이던지 stack과 heap 할당 가능 vs 객체 타입은 오직 heap(기본은 둘다 ㄱㄴ)
  - 포인터, 참조, pass by value, pass by reference 지원 vs 기본은 pass by value, 객체는 pass by reference, 배열 경계 체크 존재
  - 소멸자 지원 vs garbage collection(gc) 지원
  - 연산자 오버로딩 o vs x
  - 정적 클래스 vs 동적 클래스
  - 정적 바인딩 기본 vs 동적 바인딩 기본
### 객체 생성
```java
Matrix m1 = new Matrix();
matrix m2 = m1; //x
matrix m2 = new Matrix(m1); //o
```
### 생성자 작성법
  - 리턴형x, public
  - 복잡한 동작x
  - 서브클래스 생성자는 수퍼클래스 생성자 반드시 호출
### 접근제어자
  - private
    - 해당 클래스 내부
  - default
    - 해당 클래스 내부
    - 클래스 간 공유하는 변수 정의 시 유용
  - protected
    - default + 서브 클래스
    - get_dy, get_dx와 같은 메서드 사용 대신 직접 접근 가능
    - 재사용성 증가
### final 키워드
  - 상수 or 오버라이딩 불가 or 상속 불가 클래스
    - 변수는 블록 안에서 사용 전에 1회만 초기화 가능
  - 파라미터
    - 해당 메서드 안에서 변경 불가
    - 다만, 파라미터가 객체라면, 객체 내부 필드는 변경 가능함 
```java
public final class FinalClass {
    public final int FINAL_VAR = 100;

    public final void finalMethod(){}
}
```

### 실습 1~3
```java
String.format();
Double.parseDouble();
String.valueOf();

int a2[] = {1, 2, 3, 4, 5};
int[] a4 = new int[]{1,2,3,4,5};

a2.equals(a4); // false
Arrays.equlas(a2,a4) // true
```

### Static 선언
  - 변수
    - 인스턴스 사이에서 공유되는 변수
    - 클래스에 소속, 객체에 소속되지 않음
  - 메서드
    - 서브클래스에서 재정의 불가
    - 인스턴스 변수에 접근 불가, static 변수에만 접근 가능
  - 클래스
    - 블록 밖에서 선언된 클래스는 static 클래스
    - 블록 안은 기본적으로 dynamic 클래스
  - 결국 중요한 것은, 생성 시점이 언제인가?
```java
class Nested{
  int dy;
  static int dx;
  Nested(int dy, int dx){
    this.dy = dy;
    this.dx = dx;
  }

  public class InnerD{
    public int get_dy(){return dy;}
  }

  public static class InnerS{
    public int get_dx(){return dx;}
  }
}

public static void main(String[] args){
  Nested m1 = new Nested(1,2); 
  Nested m2 = new Nested(3,4);
  System.out.println(m1.getDy(), m1.getDx()); //1, 4
  System.out.println(m1.getDy(), m1.getDx()); //3, 4

  Nested.InnerD d1 = m1.new InnerD(); //d1.dy:1 d1.dx:4
  //외부 클래스를 생성해야 내부 클래스를 생성 가능하다

  Nested.InnerS s1 = new Nested.InnerS(); //s1.dx:4
  //외부 클래스 생성 없이 바로 내부 클래스 생성 가능하다(static)
}
```
```java
public class StaticExample {
    static int staticVar = 0;

    static void display() {
        System.out.println("Static variable: " + staticVar);
    }

    public static void main(String[] args) {
        StaticExample.display(); // 클래스 이름으로 호출
        StaticExample.staticVar = 10;
        StaticExample.display();
    }
}
```

## Lecture2
### Object
- Object 클래스는 모든 클래스의 슈퍼 클래스
- `finalize()`
  - 객체가 gc에 의해 회수되기 전에 finalize가 호출
  - 오버라이드 시 상위 클래스에 finalize를 호출해야 함
- `wait()`, `notify()`, `norifyAll()`
  - 쓰레드 동기화에 사용

```java

public class Main {
    public static void main(String[] args) throws Exception {
        Matrix m = new Matrix(10, 10); // 기본 객체 하나 생성

        for (int i = 0; i < 999; i++) {
            m = new Matrix(10, 10);
        }

        //garbage Collcection 강제 호출(객체 소멸 유도)
        System.gc();

        System.out.println("nAlloc = " + m.get_nAlloc()); // 생성된 객체 수 출력, 1000
        System.out.println("nFree = " + m.get_nFree());   // 소멸된 객체 수 출력, 999
        //gc에 실행에 따라, 다른 수가 나올 수 있음(비결정적)
    }
}
class Matrix {
    private static int nAlloc = 0; // 생성된 객체 수
    private static int nFree = 0;  // 소멸된 객체 수
    public Matrix(int cy, int cx) {
        nAlloc++;
    }
    @Override
    //finalize는 Throwable을 던질 수 있도록 선언되어있음 주의
    //Object에서 protected로 선언되어 있고, 재정의할 때 접근 범위를 축소할 수 없음
    //동일하게 protected로 선언
    protected void finalize() throws Throwable {
        super.finalize(); // 부모 클래스(Object)의 finalize 호출
        nFree++; // 소멸된 객체 수 증가
    }
    public int get_nAlloc() {
        return nAlloc;
    }
    public int get_nFree() {
        return nFree;
    }
}
```
### Exception
- 에러 발생 지점과 에러 원인 메시지를 출력해 디버깅을 도움
- 메서드에서 에러 발생 시 그 지점에서 적절한 exception 객체 생성하여 throw 
  - 해당 메서드 안에서 발생 가능한 모든 exception을 커버할 수 있어야 함
- 발생 지점 이후 처음에 만나는 try-catch 블록에서 처리
- Throwable 클래스
  - Error 클래스
    - ex) OutOfMemoryError, StackOverflowError
  - Exception 클래스
    - Checked Exceptions
      - 컴파일 타임에 반드시 처리하거나, throws로 선언
      - ex) IOException, SQLException
      - 많은 exception 타입 열거 대신, exception tree 설계 필요
    - Unchecked Exceptions(Runtime Exceptions)
      - 런타임에서만 발생, 컴파일러가 처리 여부를 강제하지 않음
      - ex) NullPointerException, ArrayIndexOutOfBoundsException
- 당연히 catch는 Exception의 하위 클래스부터 진행해야 함
## Lecture3
- 안드로이드 특징
  - 리눅스 커널 기반
  - 애플리케이션 프레임워크
  - 그래픽 OpenGL ES 2.0 규격 기반
  - SQLite DB 지원
  - 블루투스, EDGE, 3G, WiFi 지원
  - 카메라, GPS, 나침판, 가속도계 지원
  - 에뮬레이터, 메모리와 성능 프로파일링
- 안드로이드의 구조
  - 리눅스 커널, HAL(Hardware Abstraction Layer), Native c/c++ Libiraries + Android Runtime, Java API Framework, System Apps
- 이종 개발 환경
  - 안드로이드 스튜디오로 개발, 에뮬레이터(AVD)에서 실행
- 안드로이드 스튜디오
  - Gradle 기반
  - 다중 apk 파일 생성
  - 공통 특징을 지원하는 코드 템플릿 제공
  - 드래그앤 드롭 방식의 레이아웃 에디터
  - 구글 클라우드 플랫폼 지원

## Lecture4
### 컴포넌트
- 앱은 컴포넌트로 이뤄짐
  - 적어도 하나의 액티비티는 필수적으로 포함
  - 다 사용할 필요는 없음
  - 액티비티(activity), 서비스(service), 방송 수신자(broadcast receiver), 컨텐트 제공자(content provider)
  - 액티비티
    - UI를 관리하는 앱 화면의 단위
  - 서비스
    - 백그라운드 작업 수행 
  - Broadcast receiver
    - 시스템 및 앱 간 이벤트 수신 및 처리
  - Content provider
    - 앱 간 데이터 공유
- Pc vs Android App
  - PC에서는 한 프로그램이 다른 프로그램의 코드 호출
  - 안드로이드에서는 한 앱에서 다른 앱의 컴포넌트를 호출
- 인텐트(intent)
  - 애플리케이션 컴포넌트 간 작업 요청이나 데이터 전달을 위한 메시징 객체
- 앱의 구성
  - XML, Java, 리소스
    - 사용자 인터페이스
    - 앱의 로직
- 패키지 폴더 설명
  - manifest
    - 앱의 전반적 정보(이름, 컴포넌트 구성)
    - 앱에 적재된 모든 컴포넌트에 대해 기술
  - res
    - 레이아웃, 이미지, 문자열 모두 리소스로 취급
    - XML 이용 정의
  - MainActivity.java
    - 메인 액티비티를 정의
    - 앱이 시작할 때 가장 먼저 보여지는 화면
  - activity_main.xml
    - MainActivity.java에서 정의한 액티비티의 UI 설계 및 레이아웃 정의
```java
public class MainActivity extends AppCompatActivity{
  @Override
  //Activity 생성 시 호출
  protected void onCreate(Bundle savedInstanceState){
    //기본 초기화 작업 수행
    super.onCreate(savedInstanceState);
    //Activity에서 사용할 화면 레이아웃을 지정
    setContentView(R.layout.activity_main);
  }
}
```
- 안드로이드에는 main()이 없음
  - 액티비티별로 실행
  - 액티비티 중에서는 `onCreate()`메서드가 가장 먼저 실행됨
- XML을 사용한 UI
  - 선언적 방식
    - UI 구성을 선언적으로 표현
  - UI와 로직 분리 
    - XML은 UI의 외형 담당
    - 자바는 로직을 담당하여 분리
  - 빠른 UI 구축
    - 직관적 XML 태그와 속성으로 UI를 빠르게 설계하고 수정
### XML
- 요소
  - `<Greeting> Hello, World. </Greeting>`
- 속성
  - `<img src="madonna.jpg" alt='by Raphael'/>`
- ![image](https://github.com/user-attachments/assets/a88f87f0-2aa8-4071-b984-424e6f64af97)
- 코드에서 리소스를 참조하는 법
  - `setContentView(R.layout.activity_main)`

### Gradle
- 빌드 및 종속성 관리
- `compileSdk`
  - 컴파일러가 사용하는 SDK 버전
- `minSdk`
  - 앱을 설치할 수 있는 장치의 최소 SDK 버전
- `targetSdk`
  - 목표로 하는 타겟 장치의 SDK 버전

### Summary
- 앱은 컴포넌트들의 조합
- 코드와 리소스는 철저히 분리
- 코드와 리소스는 R.java에 의해 서로 연결

## Lecutre5
### UI
- View, ViewGroup
  - 레이아웃
  - 컨트롤(위젯)
- ![image](https://github.com/user-attachments/assets/9492c82f-d7d2-458a-b033-0889a214524f)
  - TextView는 아이디가 필요 없음
  - 나머지는 아이디 설정, `findViewById`로 위젯을 찾아서 작업
- 위젯의 위치와 크기
  - ![image](https://github.com/user-attachments/assets/dc2dc864-f70a-4644-89bf-8073e24e699e)
    - 픽셀은 권장 x, 화면마다 밀도 다름
    - dp로 지정하면 화면의 밀도가 다르더라도 항상 동일한 크기로 표시
    - sp는 사용자가 지정한 폰트 크기에 영향
    - pt는 1/72인치
    - px는 픽셀
    - mm은 밀리미터
    - in은 인치
- 마진과 패딩
  - ![image](https://github.com/user-attachments/assets/e88cfcee-6d71-4d02-8a1f-6e2692bd9304)
- 화면에 보이기 속성
  - visible(0)
    - 화면에 보이게 함
  - invisible(1)
    - 표시되지 않음, 그러나 배치에서 공간 차지
  - gone(2)
    - 완전히 숨겨짐
### 버튼의 이벤트 처리
- XML 파일에 이벤트 처리 메서드 등록
  - 클릭 이벤트만 처리 가능
  - 버튼 이벤트 처리 메서드 조건
    - public
    - void
    - View를 메서드의 인자로 가짐
```java
public class MainActivity extends AppCompatActivity{
  EditText eText1;
  EditText eText2;
  EditText eText3;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button bPlus = (Button)findViewById(R.id.buttion1);
    eText1 = (EditText)findViewById(R.id.edit1);
    eText2 = (EditText)findViewById(R.id.edit2);
    eText3 = (EditText)findViewById(R.id.edit3);
  }

  public void cal_plus(View e){
    String s1 = eText1.getText().toString();
    String s2 = eText2.getText().toString();
    int result = Integer.parseInt(s1) + Integer.parseInt(s2);
    eText.setText("" + result);
  }
}
```
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

## Lecture 9
### 커스텀 뷰
- 직접 View 클래스를 상속받아 새로운 위젯을 만들 수 있음
- ![image](https://github.com/user-attachments/assets/a6ed98c2-9896-48a6-9853-ca71c4e09d14)
- ![image](https://github.com/user-attachments/assets/26efd5ee-bee0-420e-a7c6-b37d5f617b6c)
- ![image](https://github.com/user-attachments/assets/b3b86973-fe07-449c-ad3f-f7b2dfd43f69)
- ![image](https://github.com/user-attachments/assets/9c7e1f7b-4e24-4938-abbb-3fb7f2a6b513)
- 커스텀 뷰를 XML에서 참조하기
  - CustomView.java 파일을 생성하고 레이아웃 파일에서 참조
```java
//XML에서 뷰를 참조하려면 이 생성자를 반드시 구현해야 함
public CustomView(Context context, AttributeSet attrs){
  super(context);
  setBackGroundColor(Color.YELLOW);
}
```
  - acitity_main.xml에 커스텀 뷰의 풀네임을 적어주기

### 색상
- R, G, B 성분을 8비트로 표시하여 나타냄
  - 24bit면 하나의 색상 표현 가능
  - 16진수로 표시하는 것이 일반적
  - ![image](https://github.com/user-attachments/assets/cf3fa179-63e8-4e12-8d8c-cce5ad7a0329)
  - ![image](https://github.com/user-attachments/assets/62c155c5-14b4-438d-b32b-667224d005dd)

### 콜백 메서드
- 키 이벤트 처리 시 `onKeyDown()` 메서드 재정의
- 터치 이벤트 처리 시 `onTouchEvent` 재정의
- 터치 이벤트 종류
  - ![image](https://github.com/user-attachments/assets/48718694-e953-4bcd-b981-8c28e3c64d8f)

## Lecutre 10
- 기본적으로 애플리케이션 안의 모든 컴포넌트는 동일 프로세스의 동일 스레드로 실행됨
  - 이 기본적 스레드를 메인 스레드로 지칭
- ![image](https://github.com/user-attachments/assets/86b677f8-4c89-4ced-8050-2b2970730e9e)
- ![image](https://github.com/user-attachments/assets/f0e4cb3e-017b-4693-881f-3280b7c5a750)
  - 메인 스레드는 UI 위젯에게 이벤트를 전달하거나 화면을 그림
  - UI 스레드로 지칭하기도 함
    - UI 스레드는 블록시키면 안 되고
    - 외부에서 안드로이드 UI 툴킷을 조적하면 안 됨
- 작업 스레드
  - 별도 생성 스레드
  - 배경 스레드라고도 지칭
  - 작성법
    - ![image](https://github.com/user-attachments/assets/e26bcf37-4c8d-4ba5-bf59-a7e8b0758cf6)
    - Thread 클래스를 상속받아서 스레드 작성
    - Runnable 인터페이스 구현 후 Thread 객체에 전달
```java
class Worker implements Runnable{
  public void run(){}// 작업 기술
}

public class ThreadTest extends Activity{
  public void onStart(){
    w = new Thread(new Worker());
    w.start();
  }
}
```
- ![image](https://github.com/user-attachments/assets/44396bda-fe56-4160-be51-bb7e1e17b3e8)

### 스레드에서 UI를 조작하는 방법
- 스레드에서 직접적으로 UI 위젯을 변경하면 안 됨
- 해결법
  - `View.post(Runnable)` 사용
    - 작업 포스트에서 `Vies.post()`호출, UI 업데이트 스케줄링
```java
yourView.post(new Runnable(){
  @Override
  public void run(){
    yourView.setText("새로운 텍스트");
  }
});
```
- Post 메서드는 메인 스레드와 작업 스레드 간 작업을 전달하기 위해 사용
  - Runnable 작업을 메인 스레드에 메시지 큐에 추가하고 실행
- Handler 사용법

```java
new Thread(new Runnable(){
  @Override
  public void run(){
    handler.post(new Runnable(){
      @Override
      public void run(){
        textView.setText("새로운 텍스트");
      }
    });
  }
})
```