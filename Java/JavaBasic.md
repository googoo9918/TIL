Java 기초 문법
============
자바의 특징
-----------------------------------
1. 운영체제와 독립적
- JRE(Java Runtime Environment) 이용
  - JVM(자바 가상머신) + 표준 클래스 라이브러리
  - ![image](https://user-images.githubusercontent.com/102513932/187331048-b1bd2547-78cb-483d-b8b8-78440fc7de1c.png)
  - ![image](https://user-images.githubusercontent.com/102513932/187331108-87c393d9-4637-4ca4-a632-0c8a64899c3b.png)
- JDK(Java Development Kit)
  - JRE + 개발 도구
  - OracleJDK
    - 회사 관리 버전
  - OpenJDK
    - 오픈소스
- 모든 운영체제에서 실행 가능
2. 객체 지향 언어(Object Oriented Programming)
- 유지보수 용이, 확장성 높음.
3. 함수형 프로그래밍 지원
- 람다식 ,스트링
4. 자동 메모리 관리(Garbage Collection)
- C,C++ 은 메모리의 생성과 소멸을 개발자가 직접 설계
- Java는 가비지 컬렉터를 실행시켜 자동으로 사용하지 않는 메모리 수거. <br><br>

기본 타입과 참조 타입
----------------------
- 기본 타입 (primitive type)
  - 값 저장시, 데이터의 실제 값 저장
  - 정수 타입(byte, short, int, long), 실수 타입(float, double), 문자 타입(char), 논리 타입(boolean)
  - float 뒤에 f, long 뒤에 L 사용
- 참조 타입 (reference type)
  - 값 저장시, 데이터의 주소 값 저장
  - 기본 타입을 제외한 나머지
<br><br>
주석문
-----------
+ 문서화 주석
  +  alt + shift + j
+ 블럭단위 주석
  + shift + ctrl + /
+ 한줄 주석
  + ctrl + /

Import
-------------
- import java.util.*;
  - java.util 패키지 안 모든 클래스 import

Final
-------------
- 상수는 대문자 명명규칙 사용
  - [자바 명명 규칙](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
  - final double PI = 3.14
  - 단어와 단어 사이 _(underbar)로 작성할 것.

DoWhile
-----------
	do {
		<수행할 문장>
	}while(조건문);

For
------------
- continue, break를 이용하여 문장을 간결화 해보자.
- for each
```java
for(int value:iarr) {
			System.out.println(value); 
            // 배열이나 자료구조의 크기를 알 필요가 없다는 장점 존재.
		} 
```

Logical Operation
-------------

- &&는 둘다 true 여야 true. (and)   
  
-   ||는 둘 중 하나라도 true면 true. (or)   
*  !는 부정 연산 ex) !a
 *  ^는 배타적 논리합, 서로가 달라야 true.  
  
 *  논리 연산에서 &&가 ||보다 우선순위가 높다는 것에 주의하자.


Switch - Case
--------------
```java
switch(입력변수) {
    case 입력값1: ...
         break;
    default: ...
         break;
}
```
- case 1 : case 4 : case 7 : 처럼 조건 한 번에 달아줄 수 있음.
- 문자열도 switch - case 가능

Ternary Operator
------------

- (a>b) ? 50 : 40 ;
  - 맞으면 앞(true), 틀리면 뒤(false) 

Array
------------
```java
        int [] array1 = new int[100]; // 정석
		int [] array2 = new int[] {1,2,3,4}; // 요소 지정만 가능
		int [] array3 = {1,2,3,4};   // new int[] 안붙여도 가능.
```

Two Demension Array
```java
        int[][] array4 = new int[3][4]; //3행 4열짜리 배열 생성.
		// array4[N]는 각 행을 표현하기 위한 참조 변수로 사용.
		 	
		int[][] array5 = new int[3][]; // 3행 ?열 배열 생성.
        // 이 경우 array5.length = 3이다.
		// 참조변수까지만 생성됨.
		// array5[0][0] = 10; 사용시 오류 발생.

		array5[0] = new int[1];
		array5[1] = new int[2];
		array5[2] = new int[3]; 

		// 열 개수가 다른 2차원 배열 생성됨...
        // 1행 열개수 1개 , 2행 2개, 3행 3개!
	
		int [][] array6 = {{1},{1,2},{1,2,3}}; // 이렇게도 가능함! 
```


Enum
---------------------
```java

public class Enumeration {
	public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";
    // 상수 사용시 전체 변수명 대문자 관례!
    
    enum Gender {
    	MALE,FEMALE //세미콜론 X, 열거형 선언.
    	};
    
	public static void main(String[] args) {
		 String gender1;

         gender1 = Enumeration.MALE;
         gender1 = Enumeration.FEMALE;            	
         gender1 = "boy"; // 그냥 다른 값 넣어도 가능함.
         
         Gender gender2;
         gender2 = Gender.MALE;
         gender2 = Gender.FEMALE;
         // gender2 = "boy"; //오류 발생
         // 특정 값만 사용한다면, 열거형을 사용하는 것이 좋다.
         
	}
}
```

String Class
---------------
```java
    String str1 = "hello"; 
        //"hello"라는 문자열이 메모리 중에서 상수가 저장되는 영역에 저장된다. 상수는 변하지 않는 값을 의미.
		String str2 = "hello"; 
        // 이 문장이 실행될 때에 hello 라는 문자열 상수는 이미 만들어져 있으므로 str1이 참조하는 인스턴스를 str2도 참조한다.
		
		String str3 = new String("hello");
         //new연산자를 이용하여 인스턴스를 만들면 인스턴스는 무조건 새롭게 만들어진다.
		// -> heap에 새로운 "hello"를 만들고 참조하게 된다.
		
		String str4 = new String("hello"); 
        // 이 문장이 실행될때도 새롭게 만들게 되므로, str3 과 str4는 서로 다른 인스턴스를 참조한다
		
		if(str1 == str2) {
			System.out.println("같은 곳을 가리키고 있습니다.");
		}
		// 참조형의 경우, 값끼리 서로 비교 연산을 하는 것이 아니라 가리키는 주소값이 같은지 비교한다.
		// 값을 비교하려면 if(str1.equals(str2)) 이용.
		if(str3 == str4) {
			System.out.println("같은 곳을 가리키고 있습니다.");
		}
		// 주소값이 다르기 때문에, 당연히 실행 안되겠지? new로 진행했으니 새로 만든 것임!
		
		String str5 = "hello world";
		String str6 = str5.substring(3);
		//substring은 문자열을 자른 결과를 반환하는 메소드이다. 해당 코드가 실행되어도 str5는 변하지 않는다.
		//str6은 str5가 가지고 있는 문자열 중 3번째 위치부터 자른 결과 즉 새로운 String을 참조하게 된다.
```

```java
	String str = "hello";
		
		System.out.println(str.length()); //5
		
		System.out.println(str.concat(" world")); //hello world
		
		System.out.println(str);  //hello
        //위에서 concat을 통해 hello world를 만들어 줬지만 string 객체는 불변 클래스 이기 때문에, 
		// str이 hello를 가리킨다는 사실은 변하지 않는다.
		
		str = str.concat(" world"); 
        //reference를 바꾸고 싶은 경우, 이와 같이 사용해줘야 한다.
		//hello world

		str = str.trim();
		// 해당 문자열의 맨 앞과 맨 뒤에 포함된 모든 공백 문자 제거
		// helloworld		
		System.out.println(str.substring(3)); 
        // 3번째 index ~ 끝까지 출력
		System.out.println(str.substring(3,5)); 
        // start ~ end 까지 출력.
```

- StringTokenizer
  - 지정한 구분자로 문자열을 쪼개주는 class
  - 쪼개어진 문자열을 토근(Token)이라 지칭.
    - int countTokens()
      - 남아있는 Token 개수 반환
      - 전체 개수 아님 주의
    - boolean hasMoreElements() , boolean hasMoreTokens()
      - 사용할 수 있는 토큰이 남아있는 경우 -> true / 아니면 false 리턴
    - Object nextElement(), String nextToken()
      - 다음 토큰 반환, 반환형 다름 주의.
- StringBuilder
  - String은 불변 객체이므로, 새 String을 생성하는 경우가 많음
  - 매번 메모리 해제와 할당을 발생시킴, 성능적으로 좋지 않다.
  - StringBuilder는 새 객체를 생성하지 않고 기존 데이터에 더하는 방식 사용
  - 속도 상승, 메모리 부하 감소
- StringBuffer
  - String 클래스의 인스터스는 생성만 가능한 반면
  - StringBuffer 클래스의 인스턴스는 값 변경, 추가 가능
  - 인스턴스 생성시 16개 문자를 더 저장할 수 있게 생성
  - 문자열 결합등 변경시 속도 상승, 메모리 부하 감소
    - ```append()```
    - ```capacity()```
      - 현재 버퍼 크기 확인
    - ```delete()```
      - 해당 문자열 특정 부분 제거
      - ```str.delete(4,8)```
    - ```deleteCharAt()```
      - ```str.deleteCharAt(1);```
    - ```insert()```
      - 중간에 다른 문자열 삽입
      - ```str.insert(4,"Script");```
<br><br>


Abstract Class
-----------------
```java
public class Inheritance  extends Car{
	// public class 자식클래스 extends 부모클래스
	// 부모클래스가 갖고 있는 것을 자식클래스가 상속 받을 수 있음.
	// 만들 때 superclass - browse를 통해서도 만들 수 있음.
	public static void main(String[] args) {
		
		Inheritance a = new Inheritance();
		
		a.run(); // Car 클래스가 갖고 있는 메소드 사용 가능.

	}
}
```

Access Modifier
------------------
```java

/** 
 * public
어떤 클래스든 접근할 수 있다는 것을 의미
 
 * protected
자기 자신, 같은 패키지, 서로 다른 패키지다 하더라도 상속받은 자식 클래스에서는 접근할수 있다는 것을 의미

 * private
자기 자신만 접근할 수 있다는 것을 의미
 
 * 접근제한자를 적지 않으면 default접근 지정자
자기자신과 같은 패키지에서만 접근할 수 있다는 것을 의미
 *
 *  public > protected > default > private.
 *         > 다른 패키지면 상속 받아야 가능 > 다른 패키지면 불가능 > 같은 패키지여도 불가능. 자기 자신만 가능.
 */
public class AccessModifier {
	 
	 private int i = 1;
     int k = 2; // default접근 제한자
     public int p = 3;
     protected int p2 = 4;
     
	public static void main(String[] args) {
		
		}
	}
```

Abstract Class
---------------------

```java
public abstract class AbstractClass_bird {
	public abstract void sing(); 
	// 중괄호 열고 닫지 않았으므로 구현하지 않았다 판단. 
	// 구체적이지 않은 것을 구현한 클래스를 추상 클래스라 한다!
	// 추상 메소드를 한개 이상 포함하면, class 앞에도 항상 abstract가 붙어야 한다.
	// 추상 클래스는 인스턴스를 생성할 수 없다.
	
	public void fly() {
		System.out.println("날다");
	} 
	// 추상 클래스라 하더라도, 일반 메소드(concreate method)를 만들 수 있다.
}
```

```java
public class AbstractClass_duck extends AbstractClass_bird {
// 추상 클래스를 상속 받으면, 추상 메소드를 반드시 구현해야 한다.
// (구현해도 오류가 뜨는 경우가 있으니, sing()을 썼다가 다시 지워볼 것)
	public void sing(){
		System.out.println("꽥꽥");
	}
// 추상클래스를 구현해 줘야 한다.
// 구현하지 않았을 때, 해당 클래스도 추상 클래스가 된다.
}
```

```java
public class AbstractClass_duckexam {
	public static void main(String[] args) {
	AbstractClass_duck duck = new AbstractClass_duck();
	duck.fly();
	duck.sing();
	// AbstractClass_bird bird = new AbstractClass_bird() {
	// 추상 클래스는 인스턴스를 생성할 수 없다.	
	}
}
```

Super and Constructor
---------------------
```java

public class SuperKeyword_car {
	public SuperKeyword_car() {
		System.out.println("SuperKeyword_car의 기본 생성자 입니다.");
	}
}

```

```java

public class SuperKeyword_truck extends SuperKeyword_car {
	public SuperKeyword_truck() {
		// super(); --> 부모 class에서 기본생성자 밖에 없을 때, 컴파일러가 자동으로 실행. 
		// 만약 상위 class에서 SuperKeyword_car가 Superkeyword_Car(String name) 으로 변경되었다면,
		// 현재 클래스에서 오류가 발생하게 된다. 이 경우, super("소방차")와 같이 따로 명명 해줘야 한다.
		System.out.println("SuperKeyword_truck의 기본 생성자 입니다.");
		// 정리하자면, 기본생성자가 있으면 super을 생략해도 되지만, 기본생성자가 없다면 직접 부모의 생성자를 호출해 줘야 한다.
		// 또한 super 키워드는 자식에서 부모의 메소드나 필드를 사용할 때도 사용한다.
	}
}

```

```java

public class SuperKeyword_tuckexm {
	
	public static void main(String args[]) {
			
		SuperKeyword_truck t1 = new SuperKeyword_truck();
		
		// 객체가 생성이 될 때, 반드시 생성자를 실행하고 생성이 된다. 
		// 이때, truck이 메모리에 올라갈 때 부모 생성자인 Car도 함께 메모리에 올라가는 것을 알 수 있다.
		// 부모 클래스에서 기본 생성자밖에 없을 때, super();이라는 코드를 컴파일러가 자동으로 실행하는 것이다. 
		// 또한, 부모 생성자가 먼저 메모리에 올라가고, 그 이후에 자식 생성자가 메모리에 올라간다.
		// 따라서 car의 print문이 먼저 출력되고, 이후에 truck의 print문이 출력되는 것이다.
		// 출력 :	SuperKeyword_car의 기본 생성자 입니다.
		//			SuperKeyword_truck의 기본 생성자 입니다.

	}
}

```

OverRiding
-----------------
```java
public class Overriding_car {
	public void run() {
		System.out.println("car의 run 메소드");
	}
}
```

```java
public class Overriding_Bus extends Overriding_car {
	public void run() {
		super.run(); 
		// super.run()을 통해 오버라이딩을 하면서 부모의 메소드를 포함시킬 수도 있음. 
		System.out.println("bus의 run 메소드");
	}
}	

```

```java
public class Overriding_busexam {

	public static void main(String[] args) {
		Overriding_Bus bus = new Overriding_Bus();
		bus.run();
		// bus에 별다른 메소드가 없었을 때는 부모 class인 car의 메소드가 실행 되었는데,
		// bus에 car에 있는 메소드와 같은 형식의 메소드를 생성하자 car에 있는 메소드는 실행 되지 않고
		// bus에 있는 메소드만 실행되었음. 
		// 이처럼, 부모가 갖고 있는 메소드와 
		// 똑같은 모양의 메소드를 자식이 갖고 있음으로 메소드를 재정의 하는 것이 오버라이딩이다.
	}

}
```

Class Cast
-----------------
```java
public class ClassCast_car {
	public void run() {
		System.out.println("car의 run 메소드.");
	}
}
```

```java
public class ClassCast_bus extends ClassCast_car {
	public void ppangppang() {
		System.out.println("빵빵");
	}
}
```

```java
public class ClassCast_busexam {
	public static void main(String [] args) {
		ClassCast_car c1 = new ClassCast_bus();
		// 부모타입인 car로 자식타입인 bus를 가리킬 수 있다. (사실 이 때도 형변환이 이루어 진 것) 
		c1.run();
		// 단, 이때 car 타입의 run은 사용할 수 있지만 bus 타입의 ppangppang은 사용할 수 없다.
		// 즉, 부모타입으로 자식객체를 참고하게 되면 부모가 가지고 있는 메소드만 사용할 수 있다. 
		
		// Bus bus =c ; --> 오류 발생 
		// c는 현재 car 타입 이기 때문에 bus가 가리킬 수 없음.
		ClassCast_bus bus = (ClassCast_bus) c1; 
		// 따라서 이렇게 형변환을 해 준다면, 가능하다!
		// 이게 가능한 이유는, 첫 줄에서 c1변수가 car 타입이지만 실제로 bus로 생성되었기 때문에 가능한 것이다.
		bus.run();
		bus.ppangppang();
		// 따라서 둘 다 가능하다!
	}
}

```

```java
public class ClassCastExam {
	public class GasStation{
    /**
     * @param GasStation코드를 살펴보면 3개의 fill메소드가 있습니다. 
     * 매개변수로 받아들이는 3종류의 다른 차량에 대해서 기름을 넣어주는 동작을 하는것 뿐인데 
     * 3개의 중복된 코드가 들어있어서 비효율적이지요. 
     * Car클래스에 있는 gas라는 속성을 공통적으로 사용하므로 이럴경우 fill메소드의 매개변수를 Car로 하면 됩니다. 
     * 그러면 Suv, Truck, Bus클래스가 Car클래스로 형변환 되므로 하나의 fill메소드로도 같은 동작을 할 수 있습니다.
     */
    public static void main(String[]args){
        GasStation gasStation = new GasStation(); //gasStation인스턴스 생성
        Suv suv = new Suv(); //suv 인스턴스 생성
        Truck truck = new Truck(); //truck 인스턴스 생성
        Bus bus = new Bus(); //bus 인스턴스 생성
        
        //gasStation에서 suv에 기름을 넣습니다.
        gasStation.fill(suv);
        
        //gasStation에서 truck에 기름을 넣습니다.
        gasStation.fill(truck);
        
        //gasStation에서 bus에 기름을 넣습니다.
        gasStation.fill(bus);
        
    }
    
    //public void fill(Suv suv){
    //    System.out.println("Suv에 기름을 넣습니다.");
    //    suv.gas += 10;
    //    System.out.println("기름이 "+suv.gas+"리터 들어있습니다.");
    //}
    
    //public void fill(Truck truck){
    //    System.out.println("Truck에 기름을 넣습니다.");
    //    truck.gas += 10;
    //    System.out.println("기름이 "+truck.gas+"리터 들어있습니다.");
    //}
    
    //public void fill(Bus bus){
    //    System.out.println("Bus에 기름을 넣습니다.");
    //    bus.gas += 10;
    //    System.out.println("기름이 "+bus.gas+"리터 들어있습니다.");
    //}
    
     public void fill(Car car){
         //참고. car.getClass().getName()은 car오브젝트가 실제로 어떤 클래스인지를 알려줍니다.
         System.out.println(car.getClass().getName()+"에 기름을 넣습니다.");

         car.gas += 10;
         System.out.println("기름이 "+car.gas+"리터 들어있습니다.");
    	 }
	}
}
```

Interface
----------------------
```java
public interface Tv {
	public int MIN_VOLUME =0;
	public int MAX_VOLUME =100;
	
	
	public void turnOn(); 
	// 추상 메소드와 비슷한.. 다만, interface는 직접 구현을 하는 것이 아니라 ~가 있을 것이다 라고 작성하는 것이기 때문에
	// {}를 통해 구현을 직접 하지 않아도 괜찮다.
	public void turnOff();
	public void changeVolume(int volume);
	public void changeChannel(int channel);
}
	// 인터페이스에서 정의된 메소드는 모두 추상 메소드이다.
	// 위에서 선언된 상수는 컴파일 시 public static final int MIN_VOLUME =0; 으로 바뀌고
	// 메소드는 public abstract void turnOn()으로 바뀌게 된다.
```

```java
public class LedTv implements Tv {
	// LedTv는 Tv를 구현하겠다는 의미.
	// interface -> add를 이용해 추가.
	// 간혹 참조 후 메소드 구현을 한 경우에도 에러가 사라지지 않는 경우가 있는데, 무시하고 컴파일을 진행해도 된다.
	// 하나라도 구현을 하지 않는 경우, 추상클래스가 되겠지?
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("켜다");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("끄다");
	}

	@Override
	public void changeVolume(int volume) {
		// TODO Auto-generated method stub
		System.out.println("볼륨을 조정하다");
	}

	@Override
	public void changeChannel(int channel) {
		// TODO Auto-generated method stub
		System.out.println("채널을 조정하다");
		}
	}
```

```java
public class LedExam {
	public static void main(String[] args) {
		Tv tv = new LedTv();
		//인터페이스도 타입이 될 수 있다!
		// 여기서 인터페이스의 중요성이 드러나는데, 만약 내가 Lcd tv에 대한 코드를 만들게 된다면
		// 위 코드에서 new LcdTv();로 바꿔주기만 하면 되겠지.. (물론 LcdTV 클래스 만들고 구현)
		// 결국 메소드 이름은 동일하게 하면서도 안에서 구현되는 세부 내용이 다를 때 인터페이스 사용하면 좋을 듯
		tv.turnOn();
		tv.turnOff();
		tv.changeChannel(0);
		tv.changeVolume(0);
	}
}
```

```java
package part08;

public interface Calculator {
	public int plus(int i,int j);
	public int multiple(int i, int j);
	// 원래 배웠던 추상 메소드, 구현하면 에러가 발생한다.
	
	
	default int exec(int i, int j) {
		return i + j;
	}
	// java8 부터는 interface에서 default를 붙이면 메소드를 구현할 수 있음!
	
	public static int exec2(int i, int j) {
		return i*j;
	}
}
```
```java
public class MyCal implements Calculator {

	@Override
	public int plus(int i, int j) {
		// TODO Auto-generated method stub
		return i+j;
	}

	@Override
	public int multiple(int i, int j) {
		// TODO Auto-generated method stub
		return i*j;
	}

}
```

```java
public class MyCalTest {
	public static void main(String[] args) {
		Calculator cal = new MyCal();
		
		cal.plus(3, 4);
		cal.multiple(3, 4);
		// Mycal에 작성한 메소드는 당연히 사용 가능.
		
		cal.exec(3, 4);
		// 인터페이스인 calculator에 구현한 method 또한 사용 가능.
		
		// cal.exec2 는 사용할 수 없음!! static 키워드를 이용해서 메소드 구현했기 때문에.
		Calculator.exec2(3,4);
		// 인터페이스명.메소드명(); 으로 사용해야 한다.
	}
}
```
Inner Class
---------------------------

```java
public class InnerExam3 {
	public void exec() {
		class Cal{
			int value = 0;
			public void plus() {
				value++;
			}
		}
		 Cal cal = new Cal();
         cal.plus();
         System.out.println(cal.value);
     }
	// 이처럼 메소드 안에 클래스를 선언한 경우, 지역 중첩 클래스 또는 지역 클래스라고 지칭한다.
	// 메소드 안에서 해당 클래스를 사용할 수 있다.

     public static void main(String args[]){
         InnerExam3 t = new InnerExam3();
         t.exec();
     }
 }

```

Anonymous Class
------------------------
```java
public abstract class Action{
    public abstract void exec();
}
```

```java
public class MyAction extends Action{
    public void exec(){
        System.out.println("exec");
    }
}
//추상클래스 Action을 상속받은 클래스 MyAction
```

```java
//MyAction을 사용하는 클래스 ActionExam 
/*public class ActionExam{
  public static void main(String args[]){
      Action action = new MyAction();
      // action은 추상클래스니까 생성은 MyAction인 자식 클래스로 해야겠지?
      action.exec();
  }
}
*/
//MyAction을 사용하지 않고 Action을 상속받는 익명 클래스를 만들어서 사용하도록 수정해 보도록 하겠습니다.
public class ActionExam{
  public static void main(String args[]){
      Action action = new Action(){
          public void exec(){
              System.out.println("exec");
          }
      };
      action.exec();
  }
}
// 생성자 다음에 중괄호 열고 닫고가 나오면, 
// 해당 생성자 이름에 해당하는 클래스를 상속받는 이름없는 객체를 만든다는 것을 뜻한다.
// 중괄호 안에는 메소드를 구현하거나 메소드를 추가할 수 있다. 
// 이렇게 생성된 이름 없는 객체를 action이라는 참조변수가 참조하도록 하고, exec()메소드를 호출
// 익명클래스를 만드는 이유는 Action을 상속받는 클래스를 만들 필요가 없을 경우이다.
// Action을 상속받는 클래스가 해당 클래스에서만 사용되고 다른 클래스에서는 사용되지 않는 경우이다.
```


Exception Handling
-------------------------
```java
public class Exception {
	public static void main(String[] args) {
		int i = 10;
		int j = 0;
		
		try {
		int k = i/j;
		// j가 0이기때문에 정상 작동하지 않음!
		System.out.println(k);
		}catch(ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다."+ e.toString());
		}finally {
            System.out.println("오류가 발생하든 안하든 무조건 실행되는 블록입니다.");
        }
	}
}
```

```java
public class ExceptionExam2 {

    public static void main(String[] args) {
        int i = 10;
        int j = 0;
        try{
            int k = divide(i, j);
            System.out.println(k);
        } catch(ArithmeticException e){
            System.out.println("0으로 나눌수 없습니다.");
        }

    }

    public static int divide(int i, int j) throws ArithmeticException{
        int k = i / j;
        return k;
    }

}
// 메소드 선언 뒤에 throws ArithmeticException 이 적혀있는 것을 알 수 있습니다. 
// 이렇게 적어놓으면 divide메소드는 ArithmeticException이 발생하니 
// divide메소드를 호출하는 쪽에서 오류를 처리하라는 뜻입니다.

```

```java
 public class ExceptionExam3 {
        public static void main(String[] args) {
            int i = 10;
            int j = 0;
            int k = divide(i, j);
            System.out.println(k);

        }       
        public static int divide(int i, int j) throws IllegalArgumentException{
            if(j == 0){
                throw new IllegalArgumentException("0으로 나눌 수 없어요.");
            }
            int k = i / j;
            return k;
        }   
    }
	
	// j가 0일 경우에 new연산자를 통하여 
	// IllegalArgumentException 객체가 만들어 진다.
	// new 앞에 throw 는 해당 라인에서 익셉션이 발생한다는 의미이다.
	// 즉 그 줄에서 오류가 발생했다는 것이다. 
	// 0으로 나눌수 없습니다. 라는 오류가 발생한것이다.
	// Exception클래스 이름을 보면 아규먼트가 잘못되었기 때문에 
	// 발생한 오류라는 것을 알 수 있다.
```

Custom Exception
-------------------

+ Exception 클래스를 상속 받아 정의한 checked Exception
  + 반드시 오류를 처리 해야만 하는 Exception
  + 예외 처리하지 않으면 컴파일 오류를 발생 시킨다.
+ RuntimeException 클래스를 상속 받아 정의한 unChecked Exception
  + 예외 처리하지 않아도 컴파일 시에는 오류를 발생시키지 않는다.

```java
 public class BizException extends RuntimeException {
        public BizException(String msg){
            super(msg);
        }       
        public BizException(Exception ex){
            super(ex);
        }
    }
```

```java
  public class BizService {
        public void bizMethod(int i)throws BizException{
            System.out.println("비지니스 로직이 시작합니다.");
            if(i < 0){
                throw new BizException("매개변수 i는 0이상이어야 합니다.");
            }
            System.out.println("비지니스 로직이 종료됩니다.");
        }
    }
```

```java
   public class BizExam {  
        public static void main(String[] args) {
            BizService biz = new BizService();
            biz.bizMethod(5);
            try{
                biz.bizMethod(-3);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
```

+ 실행결과
  + 비지니스 로직이 시작합니다.
  + 비지니스 로직이 종료됩니다.
  + 비지니스 로직이 시작합니다.
  + javaStudy.BizException: 매개변수 i는 0이상이어야 합니다.
  + at javaStudy.BizService.bizMethod(BizService.java:7)
  + at javaStudy.BizExam.main(BizExam.java:9)
  <br><br>

Generic Type
------------------------

-  제네릭(Generic)은 클래스 내부에서 지정하는 것이 아닌 외부에서 사용자에 의해 지정되는 것을 의미
   - 특정(Specific) 타입을 미리 지정해주는 것이 아닌 필요에 의해 지정할 수 있도록 하는 일반(Generic) 타입
   - 타입의 경계를 지정하고, 컴파일 때 해당 타입으로 캐스팅하여 매개변수화 된 유형을 삭제하는 것
- 장점
  - 1. 제네릭을 사용하면 잘못된 타입이 들어올 수 있는 것을 컴파일 단계에서 방지할 수 있다.
  - 2. 클래스 외부에서 타입을 지정해주기 때문에 따로 타입을 체크하고 변환해줄 필요가 없다. 즉, 관리하기가 편하다.
  - 3. 비슷한 기능을 지원하는 경우 코드의 재사용성이 높아진다.
- 범용적 규칙
	- ![image](https://user-images.githubusercontent.com/102513932/173493996-d695ac84-2eb8-418b-8a89-7129419d2523.png)
- 클래스 및 인터페이스 선언
```java
public class ClassName <T> { ... }
public Interface InterfaceName <T> { ... }
//T 타입은 해당 블럭 { ... } 안에서까지 유효하다.
```
```java
public class ClassName <T, K> { ... }
 
public class Main {
	public static void main(String[] args) {
		ClassName<String, Integer> a = new ClassName<String, Integer>();
	}
}
//  타입 파라미터로 명시할 수 있는 것은 참조 타입(Reference Type)밖에 올 수 없다.
// int, double, char 같은 primitive type은 올 수 없다는 것
// int형 double형 등 primitive Type의 경우 Integer, Double 같은 Wrapper Type으로 쓰는 이유이다.
```
```java
ublic class ClassName <T> { ... }
 
public class Student { ... }
 
public class Main {
	public static void main(String[] args) {
		ClassName<Student> a = new ClassName<Student>();
	}
}
 // 사용자 정의 클래스도 당연히 사용 가능하다.
```
- 활용 예시
```java
class ClassName<E> {
	
	private E element;	// 제네릭 타입 변수
	
	void set(E element) {	// 제네릭 파라미터 메소드
		this.element = element;
	}
	
	E get() {	// 제네릭 타입 반환 메소드
		return element;
	}
	
}
 
class Main {
	public static void main(String[] args) {
		
		ClassName<String> a = new ClassName<String>();
		ClassName<Integer> b = new ClassName<Integer>();
		
		a.set("10");
		b.set(10);
	
		System.out.println("a data : " + a.get());
		// 반환된 변수의 타입 출력 
		System.out.println("a E Type : " + a.get().getClass().getName());
		
		System.out.println();
		System.out.println("b data : " + b.get());
		// 반환된 변수의 타입 출력 
		System.out.println("b E Type : " + b.get().getClass().getName());
		
	}
}
// ClassName이란 객체를 생성할 때 <> 안에 타입 파라미터(Type parameter)를 지정
//  a객체의 ClassName의 E 제네릭 타입은 String으로 모두 변환
// b객체의 ClassName의 E 제네릭 타입은 Integer으로 모두 변환
```
- 실행 결과
  - ![image](https://user-images.githubusercontent.com/102513932/173494890-4274c0ab-c4ff-45d9-8531-9bd09ca5b0fe.png)


 
