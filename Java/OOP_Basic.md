객체 지향 프로그래밍 (Object Oriented Programming)
===============

클래스(Class)
----------------
- 클래스(Class)
  - 객체를 정의한 설계도 또는 틀
    - 객체 그 자체가 아닌, 객체를 생성하는 데 사용되는 하나의 틀
  - 객체를 생성하는데 사용됨
  - 반대로 객체는 클래스에 정의되고 설계된 내용대로 생성됨
  - 클래스를 잘 정의해 놓아야 유지보수 및 생성 용이
```java
public class ExampleClass { //클래스명 대문자 시작
	int x = 10; // (1)필드
	void printX() {...} // (2)메서드
	ExampleClass {...} // (3)생성자
	class ExampleClass2 {...} // (4)이너 클래스
} 
``` 
  - (1) 필드
    - 클래스의 속성을 나타냄
      - ex) 자동차의 모델명, 컬러, 바퀴 수
  - (2) 메서드
    - 클래스의 기능을 나타냄
      - ex) 자동차의 시동하기, 가속하기, 정지하기
  - (3) 생성자
    - 클래스의 객체 생성
  - (4) 이너 클래스
    - 클래스 내부의 클래스
  - (1), (2), (4)를 통틀어 클래스의 멤버라 지칭
- 인스턴스(instance)
  - 클래스를 통해 생성된 객체
    - 이 과정을 인스턴스화라고 지칭
  - 객체는 모든 인스턴스를 포괄하는 개념
  - 인스턴스는 해당 객체가 어떤 클래스로부터 생성된 것인지를 강조
  - ![image](https://user-images.githubusercontent.com/102513932/187853920-a9e323c9-3979-4386-aaf1-cf827fbc44c6.png)
    - 설계도와 집의 관계

객체(object)
---------------------------
- 속성과 기능이라는 두가지 구성요소
  - 속성(필드)
  - 기능(메서드)
  - ![image](https://user-images.githubusercontent.com/102513932/187854862-2a846a70-fbf5-43d2-aa9f-ff29ea7f043f.png)
- 객체의 생성
 ```java
 클래스명 참조_변수명 = new 생성자();
 // 생성자는 클래스와 동일한 이름을 가졌지만 뒤에 소괄호가 붙는다.
 ``` 
  - ```new``` 키워드 이용
    - 생성된 객체를 힙 메모리에 넣으라는 의미
  - 참조 변수는 데이터가 저장된 heap 메모리의 주소값을 가르킴
  - 즉, 해당 객체를 힙 메모리에 넣고 주소값을 참조변수에 저장.
  - ![image](https://user-images.githubusercontent.com/102513932/187856131-b462a893-0030-4361-a65a-21f06286b3fb.png)
    - 생성자로 만들어진 인스턴스는 힙 메모리 영역에 위치
    - 객체 내부에는 클래스의 멤버들 위치
    - 참조변수는 힙에 저장되어 있는 주소값을 가리킨다
    - 메서드 구현 코드는 클래스 영역에 저장
      - 객체 안에서 그 위치를 가리킴
      - 같은 클래스로 만든 모든 객체는 메서드 값을 공유
      - 메서드를 한 번만 저장하고, 찾아서 사용
    - 필드값은 실제 저장공간이 객체 내부이다 
    - 메서드는 다른 영역에 하나만 저장해놓고 공유
- 객체의 활용
  - ```포인트 연산자(.)``` 사용
    - 해당 위치에 있는 객체 확인
  ```java
  참조 변수명.피들명 //필드값 불러오기
  참조 변수명.메서드명() //메서드 호출
  ```
- [중첩, 내부, 지역, 익명, 추상 클래스 / 인터페이스](http://wiki.hash.kr/index.php/%ED%81%B4%EB%9E%98%EC%8A%A4)

필드(Field)
-----------------------------------
- [필드 심화_해시넷](http://wiki.hash.kr/index.php/%ED%95%84%EB%93%9C_(%EC%9E%90%EB%B0%94)
- 클래스에 포함된 변수(속성)
  - 클래스 변수와 인스턴스 변수는 필드 변수
  - 클래스 변수
    - static 키워드가 함께 선언
  - 인스턴스 변수
    - static 키워드가 없는 경우
  - 지역 변수
    - 필드에 속하지 않으면서 메서드 내에 포함된 경우
 ```java
 class Example { // => 클래스 영역
	int instanceVariable; // 인스턴스 변수
	static int classVariable; // 클래스 변수(static 변수, 공유변수)

	void method() { // => 메서드 영역
		int localVariable = 0; // 지역 변수(local variable). {}블록 안에서만 유효
	}
}
 ``` 
  - 인스턴스 변수
    - 인스턴스가 갖는 고유한 속성을 저장하기 위한 변수
    - new 생성자() 를 통해 인스턴스가 생성될 때 생성
  - 클래스 변수
    - 독립적인 저장 공간을 갖는 인스턴스 변수와 다르게, 공통된 저장공간 공유
    - 한 클래스로부터 생성되는 모든 인스턴스가 특정 값을 공유해야될 때 선언 및 사용
    - 인스턴스를 따로 생성하지 않고 ```클래스명.클래스변수명```을 통해 사용이 가능
      - 메서드처럼 클래스 변수 또한 클래스 영역에 저장되어 값을 공유하기 때문에 가능
  - 지역변수
    - 메서드 내에 선언
    - 메서드 내 ```{}블록``` 에서만 사용가능
    - 스택 메모리에 저장
      - 메서드 종료시 소멸
      - 필드 변수는 객체가 없어지지 않는 이상 사라지지 않음.
      - 지역변수는 한동안 사용되지 않는 경우 가상 머신에 의해 자동 삭제
  - 직접 초기화 하지 않으면 오류 발생 가능성
    - 스택 메모리는 강제로 초기화 되지 않는다.
    - 필드 변수는 자동으로 강제 초기화가 일어난다.
      - String --> null / int --> 0
      - 힙 메모리에는 빈공간이 저장될 수 없음.
    - ![image](https://user-images.githubusercontent.com/102513932/187927964-a1ff7ea1-d26e-4e64-820c-33e70ca8a96c.png)



Static 키워드
-------------------------
- 모든 객체와 메모리를 공유
- static은 클래스의 멤버(필드, 메서드, 이너 클래스)에 사용하는 키워드
  - static 키워드가 붙어있는 멤버
    - 정적 멤버
    - 인스턴스의 생성 없이 ```클래스명.멤버명```으로 사용 가능
      - 정적 멤버임을 표시하기 위해 클래스명.멤버명 사용 권장
      - 클래스 내부에 저장공간을 갖기 때문에 객체 생성 필요 x 
  - static 키워드가 붙어있지 않은 멤버
    - 인스턴스 멤버
    - 객체 생성 이후에 변수와 메서드에 접근하여 사용가능
    - new 키워드를 통해 생성
      - 힙 메모리에 생성, 독립적 저장공간 소유
  ```java
  public class StaticTest {
    public static void main(String[] args) {
        StaticExample staticExample = new StaticExample();
        System.out.println("인스턴스 변수: " + staticExample.num1); 
        // static 키워드가 없는 인스턴스 변수, 참조변수.변수명
        System.out.println("클래스 변수: " + StaticExample.num2); 
        //static 키워드가 있는 클래스 변수, 클래스명.변수명
        }
    }

    class StaticExample {
    int num1 = 10;
    static int num2 = -10;
    }

    //출력값   
    인스턴스 변수: 10
    클래스 변수: -10
  ```
  - 정적 필드는 객체 간 공유 변수 성질 지님
    - 메서드에도 동일 적용
    - 정적 메서드는 클래스명으로 바로 접근 가능
  ```java
  public class StaticFieldTest {
    public static void main(String[] args) {
        StaticField staticField1 = new StaticField(); // 객체 생성
        StaticField staticField2 = new StaticField();

        staticField1.num1 = 100; 
        staticField2.num1 = 1000;

        System.out.println(staticField1.num1);
        System.out.println(staticField2.num1);

        staticField1.num2 = 150;
        staticField2.num2 = 1500;
        System.out.println(staticField1.num2);
        System.out.println(staticField2.num2);

            }
        }

        class StaticField {
        int num1 = 10;
        static int num2 = 15;
        }

        //출력값
        100
        1000
        1500
        1500
    ```   
  - num1은 각각 고유성을 지님
    - 100과 100으로 따로 출력
  - num2는 값 공유가 일어남
    - 1500으로 두 번 반복
  - static 키워드 이용시 모든 인스턴스에 공통적으로 적용되는 값 공유 가능
```java
Class car{
    public String instanceVar = "나는 인스턴스 변수";
    public String classVar = "나는 클래스 변수";

    public static void ClassMethod(){
        System.out.println(instanceVar); // 오류 발생
        System.out.println(classVar);
    }

    public void InstanceMethod(){
        System.out.println(instanceVar);
        System.out.println(classVar);
    }
}
```
  - 정적 메서드는 인스턴스 변수 또는 인스턴스 메서드 사용 불가능
      - 정적 메서드는 인스턴스 생성 없이 호출 가능
        - 바로 호출시 인스턴스 변수와 인스턴스 메서드 사용 불가능하기 때문
- 총 정리
  - static 키워드는 멤버 앞에 붙일 수 있음
  - 인스턴스 생성하지 않아도 됨
  - 특징은 메모리의 저장위치와 관련이 있음

메서드(Method)
---------------------------
- [메소드 심화_해시넷](http://wiki.hash.kr/index.php/%EB%A9%94%EC%86%8C%EB%93%9C)
- 특정 작업을 수행하는 일련의 명령문들의 집합

```java
자바제어자 반환타입 메서드명(매개 변수) { // 메서드 시그니처
	메서드 내용 // 메서드 바디
}
```
- 메서드 호출
  - 클래스 외부에서 메서드 사용시
    - 인스턴스 생성이 우선
      - 포인트 연산자 이용
  - 클래스 내부에서 매서드 사용시
    - 객체 생성하지 않고 호출 가능
- 메서드 오버로딩(Method Overloading)
  - 하나의 클래스 안에 같은 이름의 메서드 여러 개 정의
    - "overload" : 과적하다 / 부담을 지우다
  - 메서드 시그니처
    - 메서드명 / 매개변수의 타입
    - 메서드 시그니처가 다르면 JVM은 다른 메서드라고 인식.
  - 오버로딩의 조건
    - 메서드의 이름이 같아야 함
    - 매개변수의 개수 또는 타입이 달라야 함
    - 반환 타입은 영향을 주지 못함
```java
class Shape {
    public void area() { // 메서드 오버로딩. 같은 이름의 메서드 4개.
        System.out.println("넓이");
    }
    public void area(int r) {
        System.out.println("원 넓이 = " + 3.14 * r * r);
    }

    public void area(int w, int l) {
        System.out.println("직사각형 넓이 = " + w * l);
    }

    public void area(double b, double h) {
        System.out.println("삼각형 넓이 = " + 0.5 * b * h);
    }
}
```

생성자(Constructor
--------------------------
[생성자_해시넷](http://wiki.hash.kr/index.php/%EC%83%9D%EC%84%B1%EC%9E%90)
- 객체 생성 역할, 인스턴스 생성시 호출되는 인스턴스 초기화 메서드
  - new 키워드 사용시 호출
  - 인스턴스 생성은 new 키워드가 담당
  - 생성자는 인스턴스 변수 초기화
- 메서드와의 차이
  - 생성자의 이름은 클래스의 이름과 같음
  - 생성자는 리턴 타입이 없음
```java
클래스명(매개변수){ // 생성자 기본 구조
}
```  
- 생성자도 오버로딩 가능
```java
        String name;
		int number; 
		// 이렇게까지만 만들면 (생성자를 만들지 않으면) 매개변수가 없는 생성자가 컴파일 할 때 자동으로 만들어진다.
		// 매개변수가 없는 생성자를 기본 생성자라 칭한다.
		// 생성자를 하나라도 만들었다면, 기본생성자는 만들어지지 않는다.
		
		public Car(String name) {
			//this.name = name;
			this(name,0);
		}
		// 이처럼 만들면, Car이 객체가 될 때 반드시 이름을 가질 것이다.
		// 생성자는 객체가 될 때 field를 초기화 할 수 있다. 
		// 이제는 Car c1 = new car(); 하면 오류 발생. Car c1 = new car("소방차"); 와 같이 명명해 줘야함.
		// name = name 이라고 사용하게 되면, 가깝게 선언된 변수를 우선 사용하기 때문에 
		// 매개변수 name에 매개변수 name의 값을 입력하게 된다.
		// 이 경우, field 라는 것을 컴파일러와 JVM에게 알려주기 위해 this키워드를 사용해야 한다.
		// 앞의 this.name은 필드 name을 의미하고, 뒤의 name은 매개변수를 의미하게 된다.
		// 클래스 안에서 자기 자신이 갖고 있는 메소드를 사용할 때에도, this.메소드명()으로 호출할 수 있다.
		// 밑에 있는 두 개의 생성자를 참고하면, this(name, 0)으로 초기화도 가능하다 
		
		public Car() {
			// this.name = "이름없음";
			// this.number = 0;
			this("이름없음, 0");
		}
		// 그렇다면 기본생성자는 앞으로 쓸 수 없는 것일까? 그렇지 않다. 위 처럼 표기하면 기본 생성자를 만들 수 있다. (매개변수가 없다)
        // 기본 생성자 - public Car(){}
        // 매개변수가 없다면 기본 생성자이다.
		// 또한 , this를 이용하여 초기화를 시킬 수도 있지만, 밑에 생성자와 코드의 중복이 일어난다.
		// 따라서, 밑에 있는 Car 생성자를 이용하여 this("이름없음, 0")처럼 초기화도 가능하다.
		// 	자기 자신의 생성자를 호출하여, 비슷한 코드의 중복을 방지할 수 있다.
	
		public Car(String name, int number) {
			this.name = name;
			this.number = number;
		}
		// 생성자의 매개변수의 유형과 개수를 다르게 하여 오버로딩을 할 수 있다.
		
```
- ```this()```
  - 클래스 내부에서 다른 생성자를 호출하는 경우 사용
  - 생성자의 내부에서만 사용할 수 있음
  - 반드시 생성자의 첫 줄에 위치해야 함
  - 오버로딩의 관점에서 다른 생성자를 호출 할 수 있음.
    - 위 코드 확인
- ```this```
  - 인스턴스 자신을 가르킴
  - 참조변수를 통해 인스턴스의 멤버를 접근하는 것과 같이, this를 통해 자신의 변수에 접근
  - 주로 인스턴스의 필드명과 지역변수를 구분하기 위해 사용

내부 클래스
----------------------------
- 클래스 내에 선언된 클래스
  - 외부 클래스와 내부 클래스 서로 연관시 사용
  - 사용시 외부 클래스 멤버에 쉽게 접근 가능
  - 코드의 복잡성 하락
```java
class Outer { // 외부 클래스
	
	class Inner {
		// 인스턴스 내부 클래스	
	}
	
	static class StaticInner {
		// 정적 내부 클래스
	}

	void run() {
		class LocalInner {
		// 지역 내부 클래스
		}
	}
} 
```
![image](https://user-images.githubusercontent.com/102513932/188101787-8bdbdc3d-7822-4638-ba19-99a8987dfc4f.png)
- 인스턴스 내부 클래스
  - 객체 내부에 멤버 형태로 존재
  - 외부 클래스의 모든 접근 지정자의 멤버에 접근 가능
```java
class Outer { //외부 클래스
    private int num = 1; //외부 클래스 인스턴스 변수
    private static int sNum = 2; // 외부 클래스 정적 변수

    private InClass inClass; // 내부 클래스 자료형 변수 선언

    public Outer() {
        inClass = new InClass(); //외부 클래스 생성자
    }

    class InClass { //인스턴스 내부 클래스
        int inNum = 10; //내부 클래스의 인스턴스 변수

        void Test() {
            System.out.println("Outer num = " + num + "(외부 클래스의 인스턴스 변수)");
            System.out.println("Outer sNum = " + sNum + "(외부 클래스의 정적 변수)");
        }
    }

    public void testClass() {
        inClass.Test();
    }
}

public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer();
        System.out.println("외부 클래스 사용하여 내부 클래스 기능 호출");
        outer.testClass(); // 내부 클래스 기능 호출
    }
}

// 출력값

외부 클래스 사용하여 내부 클래스 기능 호출
Outer num = 1(외부 클래스의 인스턴스 변수)
Outer sNum = 2(외부 클래스의 정적 변수)
```
  - 외부 클래스의 생성자 설정으로 외부 클래스 생성시 내부 클래스도 생성
    - 내부 클래스 기능 호출로 출력값 생성됨

```java
public class InnerExam1 {

	class Cal{
		int value = 0;
		public void plus() {
			value++;
		}
	} 
// 필드를 선언하는 위치에 선언되는 경우, 중첩클래스 혹은 인스턴스클래스라고 지칭.

	public static void main(String[] args) {
		InnerExam1 t = new InnerExam1();
		InnerExam1.Cal cal = t.new Cal();
		//내부의 Cal을 이용하기 위해서는, 밖에서 InnerExam 객체를 만든 다음 Cal 객체를 생성해 주어야 한다.
		cal.plus();
		System.out.println(cal.value);
	}
}
```
  - 인스턴스 내부 클래스는 반드시 외부 클래스를 생성한 이후에 사용해야 함
    - 따라서 정적 변수와 정적 메서드는 인스턴스 내부 클래에서 선언 불가능
    - 첫 번째 예시 코드에서는 생성자를 통해 자동으로 연속 생성 유도
    - 두 번째 예시 코드에서는 첫 번째 인스턴스를 이용

- 정적 내부 클래스
  - 내부 클래스가 외부 클래스의 존재와 무관하게 정적 변수 사용 가능
```java
class Outer { //외부 클래스
    private int num = 3; //내부 클래스의 인스턴스 변수
    private static int sNum = 4;

    void getPrint() {
        System.out.println("인스턴스 메서드");
    }

    static void getPrintStatic() {
        System.out.println("스태틱 메서드");
    }

    static class StaticInClass { // 정적 내부 클래스
        void test() {
            System.out.println("Outer num = " +sNum + "(외부 클래스의 정적 변수)");
            getPrintStatic();
            // num 과 getPrint() 는 정적 멤버가 아니라 사용 불가.
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Outer.StaticInClass a = new Outer.StaticInClass(); //정적 이너 클래스의 객체 생성
        a.test();
    }
}

//출력값
Outer num = 4(외부 클래스의 정적 변수)
스태틱 메서드
```
  - 스태틱 내부 클래스는 외부 클래스의 생성 없이 사용할 수 있음
  - 스태틱 내부 클래스에서 정적이지 않은 변수와 메서드는 사용 불가
  
- 지역 내부 클래스
  - 메서드 내에서 정의되는 클래스
    - 메서드 내부에서만 사용 가능
    - 메서드 안에서 선언 후 바로 객체 생성 일반적
```java
class Outer { //외부 클래스
    int num = 5;
    void test() {
        int num2 = 6;
        class LocalInClass { //지역 내부 클래스
            void getPrint() {
                System.out.println(num);
                System.out.println(num2);
            }
        }
        LocalInClass localInClass = new LocalInClass();
        localInClass.getPrint();
    }
}
public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.test();
    }
}

//출력값
5
6
```
  - LocalInClass가 메서드 안에서 선언 및 생성
    - 정의된 메서드 호출하여 외부 클래스 변수 출력
- 내부 클래스는 개발자의 편의를 위해 서로 연관있는 클래스들을 연결


상속
----------------------------

- 기존의 클래스를 재활용하여 새로운 클래스를 작성
  - 하위 클래스는 상위클래스가 가진 모든 멤버를 상속받음
  - 하위 클래스 멤버 개수 >= 상위 클래스 멤버 개수
  - 조상 - 자손 관계로 표현하기도 함
  - 하위 클래스는 상위 클래스로부터 확장된 것
    - ```extends```
  - ![image](https://user-images.githubusercontent.com/102513932/188534905-e37a882f-cffa-49f0-bea3-df95b0f2803f.png)\
  - 코드 재사용을 통해 코드 중복 제거 가능
  - 다형적 표현 가능
- 코드 예제
```java
class Person {
    String name;
    int age;

    void learn(){
        System.out.println("공부를 합니다.");
    };
}
class Programmer extends Person { // Person 클래스로부터 상속. extends 키워드 사용 
    String companyName;

    void coding(){
        System.out.println("코딩을 합니다.");
    };
}

public class HelloJava {
    public static void main(String[] args){

        //Person 객체 생성
        Person p = new Person();
        p.name = "김코딩";
        p.age = 24;
        p.learn();
        System.out.println(p.name);

        //Programmer 객체 생성
        Programmer pg = new Programmer();
        pg.name = "박해커";
        pg.age = 26;
        pg.learn(); // Persons 클래스에서 상속받아 사용 가능
        pg.coding(); // Programmer의 개별 기능
        System.out.println(pg.name);

    }
}
//출력값
공부를 합니다.
김코딩
공부를 합니다.
코딩을 합니다.
박해커
``` 
  - 단일 상속만 허용 (다중 상속 허용 X)

포함 관계
---------------------------
- 상속처럼 클래스를 재사용할 수 있는 방법
  - 클래스의 멤버로 다른 클래스 타입의 참조변수 선언
```java
public class Employee {
    int id;
    String name;
    Address address;

    public Employee(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    void showInfo() {
        System.out.println(id + " " + name);
        System.out.println(address.city+ " " + address.country);
    }

    public static void main(String[] args) {
        Address address1 = new Address("서울", "한국");
        Address address2 = new Address("도쿄", "일본");

        Employee e = new Employee(1, "김코딩", address1);
        Employee e2 = new Employee(2, "박해커", address2);

        e.showInfo();
        e2.showInfo();
    }
}

class Address {
    String city, country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }
}

// 출력값
1 김코딩
서울 한국
2 박해커
도쿄 일본
```
- ```Address``` 클래스로 변수들을 묶고, 참조변수 선언
  - 코드 중복 감소, 포함관계로 재사용
  - 상속보다 포함이 더 자주 쓰이는 경우 많음
- 포함 관계와 상속 관계의 구분
  - '~은 ~이다'(IS-A) 관계 VS `~은 ~을 가지고 있다(HAS-A)관계
  - Employee는 Address이다. VS Employee는 Address를 갖고 있다.
    - 후자
  - Programmers는 Person이다. VS Programmers는 Person을 갖고 있다.
    - 전자

메서드 오버라이딩(Method Overriding)
----------------------
- 상위 클래스로부터 상속받은 메서드와 동일한 이름의 메서드 재정의
```java
class Vehicle {
    void run() {
        System.out.println("Vehicle is running");
    }
}

public class Bike extends Vehicle { // Vehicle 클래스 상속
    void run() {
        System.out.println("Bike is running"); // 메서드 오버라이딩
    }

    public static void main(String[] args) {
        Bike bike = new Bike();
        bike.run();
    }
}

// 출력값
"Bike is running"
```  
- ```run()``` 메서드 오버라이딩
- 오버라이딩을 위한 조건
  - 1. 메서드의 선언부(이름, 매개변수, 반환타입)가 상위클래스와 일치
  - 2. 접근 제어자의 범위가 상위 클래스의 메서드보다 같거나 넓어야 함
  - 3. 예외는 상위 클래스의 메서드보다 많이 선언할 수 없다.
```java
// 위 예시처럼 .run 메소드에 대해 오버라이딩을 했다 가정
 Vehicle bike2 = new Bike(); // 상위 클래스 타입으로 선언 + 각각 타입으로 객체 생성
 Vehicle car2 = new Car();
 Vehicle motorBike2 = new MotorBike();
 Vehicle[] vehicles = new Vehicle[] { new Bike(), new Car(), new MotorBike()};
for (Vehicle vehicle : vehicles) {
		vehicle.run();
}

// 출력값
Bike is running
Car is running
MotorBike is running
```
- 상위 클래스 타입 하나로 선언시 간편하게 배열로 선언하여 관리할 수 있는 편의성

super 키워드와 super()
---------------------------
- ```super```
  - 상위 클래스의 객체
```java
public class Super {
    public static void main(String[] args) {
        Lower l = new Lower();
        l.callNum();
    }
}

class Upper {
    int count = 20; // super.count
}

class Lower extends Upper {
    int count = 15; // this.count

    void callNum() {
        System.out.println("count = " + count);
        System.out.println("this.count = " + this.count);
        System.out.println("super.count = " + super.count);
    }
}

// 출력값
count = 15
count = 15
count = 20
```
- ```super()```
  - 상위 클래스의 생성자 호출
```java
public class Test {
    public static void main(String[] args) {
        Student s = new Student();
    }
}

class Human {
    Human() {
        System.out.println("휴먼 클래스 생성자");
    }
}

class Student extends Human { // Human 클래스로부터 상속
    Student() {    
        super(); // Human 클래스의 생성자 호출
        System.out.println("학생 클래스 생성자");
    }
}

// 출력값
휴먼 클래스 생성자
학생 클래스 생성자
```  
  - 생성자 안에서만 사용가능
  - **반드시 첫 줄에 와야 함**