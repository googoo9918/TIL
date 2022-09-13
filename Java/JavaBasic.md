## 목차
- [Java 기초 문법](#자바의-특징)<br>
  * [자바의 특징](#자바의-특징)<br>
  * [기본 타입과 참조 타입](#기본-타입과-참조-타입)<br>
  * [주석문](#주석문)<br>
  * [For](#for)<br>
  * [Logical Operation](#logical-operation)<br>
  * [Switch - Case](#switch---case)<br>
  * [Ternary Operator](#ternary-operator)<br>
  * [열거형(Enum)](#열거형enum)<br>
    * [열거형 메서드](#열거형에서-사용-가능한-메서드)
  * [Math Class](#math-class)<br>
  * [String Class](#string-class)<br>
    * [length, concat, trim, substring, 문자열 덧셈](#length-concat-trim-substring-문자열-덧셈)
    * [StringTokenizer, StringBuilder, StringBuffer](#stringtokenizer-stringbuilder-stringbuffer)
  * [Exception Handling](#exception-handling)<br>
    * [try-catch](#try---catch-문)
    * [예외 전가](#예외-전가)
  * [Custom Exception](#custom-exception)<br>
  * [Generic Type](#generic-type)<br>
    * [클래스 및 인터페이스 선언](#클래스-및-인터페이스-선언)
    * [기초 예시 및 다형성 적용](#기초-예시-및-다형성-적용)
    * [제한된 제네릭 클래스](#제한된-제네릭-클래스)
    * [제네릭 메서드](#제네릭-메서드)
    * [와일드 카드](#와일드-카드)
  * [java.util 패키지](#javautil-패키지)<br>
  * [java IO 패키지](#java-io-패키지)<br>
    * [컬렉션 프레임워크](#컬렉션-프레임워크)
      * [Iterator](#0-iterator)
      * [Set](#1-set)
        * [HashSet](#hashset)
        * [TreeSet](#treeset)
      * [List](#2-list)
        * [ArrayList](#arraylist)
        * [LinkedList](#linkedlist)
      * [Map](#3-map)
        * [HashMap](#hashmap)
      * [Date](#4-date)
      * [Calendar](#5-calendar)
<br>

Java 기초 문법
============
자바의 특징
-----------------------------------
[자바 명명 규칙](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
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


열거형(Enum)
---------------------
- 열거형은 서로 연관된 상수들의 집합
  - 몇 가지로 한정된 변하지 않는 데이터
  - 상수명의 중복을 피할 수 있음
  - 타입에 대한 안정성 보장
  - 간결하고 가독성 좋은 코드
  - swithch문 작동 가능
```java

public class Enumeration {
	public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";
    // 상수 사용시 전체 변수명 대문자 관례!
    
    enum Gender {
    	MALE,FEMALE //세미콜론 X,대문자 사용, 열거형 선언
    	};//0부터 시작해서 자동으로 정수값 할당됨 (0, 1,..)
    
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
  //enum은 switch-case 구문도 사용 가능하다.     
	}
}
```
#### 열거형에서 사용 가능한 메서드
  - 모든 열거형의 조상인 ```java.lang.Enum```에 정의
  - ![image](https://user-images.githubusercontent.com/102513932/189788057-1a615daf-fbcb-4919-adaa-3f2a8e1a134f.png)
```java
enum Level{
  LOW, //0
  MEDIUM, //1
  HIGH //2
}

public class EnumTest{
  public static void main(String[] args){
    Level level = Level.MEDIUM;

    Level[] allLevels = Level.values();
    for(Level x : allLevels){
      System.out.printf("%s=%d%n", x.name(), x.ordinal());
// LOW=0
// MEDIUM=1
// HIGH=2
    }
  }
}
```

Math Class
---------------------------
- Math 클래스는 생성자가 private으로 되어 있기 때문에 new 연산자 사용 불가능
- 객체를 생성할 수는 없지만, 모든 메소드와 속성이 static 이기 때문에 객체를 생성하지 않고도 사용 가능
```java
int value1 = Math.max(5,20); //최대값
int value2 = Math.min(5,-5);//최소값
int value3 = Math.abs(-10); // 절대값
int value4 = Math.pow(2,10); // 2^10
double value5 = Math.random();//0~1 random 실수값
double value6 = Math.sqrt(25); //5.0
double value7 = Math.log10(200); // log200 = 2.3010..
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
#### length, concat, trim, substring, 문자열 덧셈
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
```java
String str1 = "hello world";
String str2 = "world";
String str3 = str1 + str2;
// 문자열과 문자열을 더하게 되면 내부적으로는 다음과 같은 코드 실행
// String str3 = new StringBuffer().append(str1).append(str2).toString();
// 반복문에서 문자열을 더할 경우, new 연산자의 사용이 많아져 성능상 문제가 생길 수 있음. 
// 반복문에서는 직접 append를 사용하여 처리할 수 있도록 하자.
```
#### StringTokenizer, StringBuilder, StringBuffer
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
      - 결과 출력시 ```toString``` 이용
      ```java
      StringBuffer sb2 = new StringBuffer();
      StringBuffer sb3 = sb2.append("hello");
      if(sb2 == sb3)
        System.out.println("sb2 == sb3");
      // sb2 == sb3 출력
      ```
      - StringBuffer가 갖고 있는 메소드는 대부분 자기 자신, this를 반환
        - 자기 자신의 메소드를 호출하여 자기 자신의 값을 바꿔나가는 것을 메소드체이닝 이라 지칭
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

예외 처리(Exception Handling)
-------------------------
- 컴파일 에러
  - 컴파일 할 때 발생
  - 주로 문법 오류, 신택스 에러라 지칭하기도 함
    - ide(컴파일러)를 통해 확인 가능, 비교적 쉽게 해결 가능
    - ex) ```java: ')' expected```
- 런타임 에러
  - 프로그램이 실행될 때 발생하는 에러
    - 자바 가상 머신(JVM)에 의해 감지
    - ex) 특정 숫자를 0으로 나눴을 때 발생 
- 예외 클래스의 상속 계층도
  - ![image](https://user-images.githubusercontent.com/102513932/189837930-a5d15e31-03f8-4b79-801b-e6835f782e67.png)
  - 일반 예외 클래스(Exception)
    - 컴파일러가 실행 전 예외 처리 코드 여부 검사 , checked 예외라 지칭하기도
    - 잘못도니 클래스명, 데이터 형식 등 
  - 실행 예외 클래스(Runtime Exception)
    - 런타임시 발생하
    - 컴파일러가 검사하지 않음, unchecked 예외라 지칭하기도
    - 클래스 간 형변환 오류, 벗어난 배열 범위 지정, null 참조변수 사용 등
#### try - catch 문
```java
try {
    // 예외가 발생할 가능성이 있는 코드를 삽입
} 
catch (ExceptionType1 e1) {
    // ExceptionType1 유형의 예외 발생 시 실행할 코드
} 
catch (ExceptionType2 e2) {
    // ExceptionType2 유형의 예외 발생 시 실행할 코드
} 
finally {
    // finally 블럭은 옵셔널
    // 예외 발생 여부와 상관없이 항상 실행
}
```
```java
public class RuntimeExceptionTest {

    public static void main(String[] args) {

        try {
            System.out.println("[소문자 알파벳을 대문자로 출력하는 프로그램]");
            printMyName(null); // (1) 예외 발생
            printMyName("abc"); // 이 코드는 실행되지 않고 catch 문으로 이동
        } 
        catch (ArithmeticException e) {
            System.out.println("ArithmeticException 발생!"); // (2) 첫 번째 catch문
        } 
        catch (NullPointerException e) { // (3) 두 번째 catch문
            System.out.println("NullPointerException 발생!"); 
            System.out.println("e.getMessage: " + e.getMessage()); // (4) 예외 정보를 얻는 방법 - 1
            System.out.println("e.toString: " + e.toString()); // (4) 예외 정보를 얻는 방법 - 2
            e.printStackTrace(); // (4) 예외 정보를 얻는 방법 - 3
        } 
        finally {
            System.out.println("[프로그램 종료]"); // (5) finally문
        }
    }

    static void printMyName(String str) {
        String upperCaseAlphabet = str.toUpperCase();
        System.out.println(upperCaseAlphabet);
    }
}

// 출력값
[소문자 알파벳을 대문자로 출력하는 프로그램]
NullPointerException 발생!
e.getMessage: null
e.toString: java.lang.NullPointerException
[프로그램 종료]
java.lang.NullPointerException
	at RuntimeExceptionTest.printMyName(RuntimeExceptionTest.java:20)
	at RuntimeExceptionTest.main(RuntimeExceptionTest.java:7)
```
1. 예외 발생
2. 첫 번째 catch
- 첫 번째 catch문은 예외가 적합하지 않기 때문에 생략
- 예외 발생은 위에서부터 순차적으로 검사 진행 
  - 검사는 ```instanceOf``` 연산자 이용
  - 구체적 예외 클래스인 하위클래스 상단 배치 권장
3. 두 번째 catch
- 발생한 예외와 일치하는 조건, 코드블럭 순차적 실행
- 예외 정보를 얻는 법
  - e.getMessage()
    - 에러 원인 간단히 출력
  - e.toString()
    - 에러의 Exception 내용과 원인을 출력
  - e.printStackTrace()
    - 에러의 발생근원지를 찾아 단계별로 에러 출력
4. finally
- 꼭 포함되어야 하진 않음
- 포함 되어 있다면 예외 발생 여부와 관계없이 무조건 실행

#### 예외 전가
- 예외를 호출한 곳으로 다시 예외를 떠넘기는 방법
  - 메서드 선언부 끝에 throws 키워드 이용
  - 메소드를 사용하는 곳으로 책임 전가
```java
반환타입 메서드명(매개변수, ...) throws 예외클래스1, 예외클래스2, ... {
}
void ExampleMethod() throws Exception{
}
```  
```java
public class ThrowExceptionTest {

    public static void main(String[] args) {
        try {
            throwException();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage()); //출력
        }
    }

    static void throwException() throws ClassNotFoundException, NullPointerException {//throws로 인해 예외 처리 main에서 진행
        Class.forName("java.lang.StringX"); //런타임 에러 발생
    }
}
//출력값
java.lang.StringX
```

#### 의도적 예외 발생
- throw
  - 예외를 강제로 발생시킴
  - ```throw new 발생시킬 예외```
- 기타 예제 후술 요망

제네릭(Generic Type)
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
	- 타입 매개변수라 지칭
#### 클래스 및 인터페이스 선언
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
    ClassName<String, Integer> a = new ClassName<>(); //위와 같은 코드.
    
	}
}
//  타입 파라미터로 명시할 수 있는 것은 참조 타입(Reference Type)밖에 올 수 없다.
// int, double, char 같은 primitive type은 올 수 없다는 것
// int형 double형 등 primitive Type의 경우 Integer, Double 같은 Wrapper Type으로 쓰는 이유이다.
```
```java
public class ClassName <T> { ... }
 
public class Student { ... }
 
public class Main {
	public static void main(String[] args) {
		ClassName<Student> a = new ClassName<Student>();
	}
}
 // 사용자 정의 클래스도 당연히 사용 가능하다.
```
### 기초 예시 및 다형성 적용
```java
public class Box{
  private Object obj;

  public void setObj(Object obj){
    this.obj = obj;
  }
  
  public Object getObj(){
    return obj;
  }

  public static void main(String[] args){
    Box box = new Box();
    box.setObj("hello"); 
    //object니까 무슨 형태든 매개변수로 넘겨줄 수 있음.
    String str = (String)box.getObj();
    //다만 값을 꺼낼 때, 형 변환이 필수적임
  }
}
```
```java
public class Box<E>{ //Object -> E로 변환
 //가상의 타입으로 선언
  private E obj;
  static E item1; //오류 발생 
  // 클래스변수(static 변수)에는 타입 매개변수 사용 불가능
  // 클래스 변수는 값과 메모리를 공유하기 때문에 인스턴스 별로 달라지면 안됨
  public void setObj(E obj){
    this.obj = obj;
  }
  
  public E getObj(){
    return obj;
  }

  public static void main(String[] args){
    Box<String> box2 = new Box<>();
    // 구체적 타입을 설정해서 사용 
    box2.setObj("Hello");
    String str = box2.getObj(); 
    // 더 이상 형변환을 하지 않아도 된다
  }
}
```
```java
// 마찬가지로 다향성 적용 가능
class Flower{...}
class Rose extends Flower{...}
class RosePasta{...}

class Basket<T>{
  private T item;

  public T getItem(){
    return item;
  }

  public void setItem(T item){
    this.item = item;
  }
}
public static void main(String[] args){
  Basket<Flower> flowerBasket = new Basket<>();
  flowerBasket.setItem(new Rose()); //다형성 적용
  flowerBasket.setItem(new RosePasta()); //에러
  // new Rose()를 통해 생성된 인스턴스는 Rose 타입, Rose타입은 Flower 클래스를 상속받음
  // 따라서 Basket<Flower>의 item에 할당될 수 있음.
}
```
#### 제한된 제네릭 클래스
1. 클래스 제한 
```java
class Basket<T>//을
class Basket<T extends Flower>//로 변경시
```
```basket```클래스를 인스턴스화 할 때 타입으로 ```Flower``` 클래스의 하위 클래스만 지정하도록 제한된다.

2. 인터페이스 구현 클래스 제한
```java
interface Plant { ... }
class Flower implements Plant { ... }
class Rose extends Flower implements Plant { ... }

class Basket<T extends Plant> {
    private T item;
	
		...
}
public static void main(String[] args) {

		// 인스턴스화 
		Basket<Flower> flowerBasket = new Basket<>();
		Basket<Rose> roseBasket = new Basket<>();
}
```
특정 인터페이스를 구현한 클래스만 타입으로 지정할 수 있도록 제한 가능
3. 특정 클래스 상속 & 특정 인터페이스 구현 클래스 지정
```java
class Basket<T extends Flower & Plant> { // (1)
    private T item;
	
		...
}// 클래스를 인터페이스보다 앞에 위치시켜야 됨 주의
```
#### 제네릭 메서드
- 클래스 내부 특정 메서드만 제네릭 선언 가능
- 반환타입 앞에서 타입 매개변수 선언
  - 해당 메서드 내에서만 선언한 타입 매개변수 사용 가능
  - 제네릭 클래스의 타입 매개변수와 제네릭 매서드의 타입 매개변수는 별개.
    - 클래스의 타입 매개변수
      - 클래스가 인스턴스화 될 때 타입 지정
    - 메서드의 타입 매개변수
      - 메서드가 호출될 때 타입 지정.
      - 클래스와 달리 static 메서드에서도 선언하여 사용 가능
      - ```length()```와 같은 특정 클래스 메서드 사용 불가능
        - 제네릭 메서드를 정의하는 시점에서 어떤 타입이 입력 되는지 알 수 없기 때문
        - 단, object 클래스의 메서드는 사용 가능
          - 모든 클래스는 object 클래스를 상속받기 때문
          - ex) ```equals()``` , ```toString()```
```java
class Basket<T> {                // 1 : 여기에서 선언한 타입 매개변수 T와 
		...
		public <T> void add(T element) { // 2 : 여기에서 선언한 타입 매개변수 T는 서로 다른 것입니다.
				...
    static <T> int setPrice(T element){ 
      // 메서드 에서는 static 메서드에서도 선언 및 사용 가능
      // 클래스의 클래스 변수에서는 타입 매개변수 사용 불가능
    }

    public <T> void print(T item){
      System.out.println(item.length()); //불가능
    }
    public <T> void getPrint(T item){
      System.out.println(item.equals("kim coding")); //가능
    }
		}
}
Basket<String> basket = new Bakset<>(); // 위 예제의 1의 T가 String으로 지정됩니다. 
basket.<Integer>add(10);                // 위 예제의 2의 T가 Integer로 지정됩니다. 
basket.add(10);                         // 타입 지정을 생략할 수도 있습니다. 
```
#### 와일드 카드
- 어떠한 타입으로든 대체될 수 있는 타입 파라미터
  - 기호 ```?```로 와일드카드 사용
    - ```extends```와 ```super``` 키워드 조합
```java
<? extends T>
<? super T>
```    
    - ```<? extends T>```는 와일드 카드의 상한 제한
      - T와 T를 상속받는 하위 클래스 타입만 타입 파라미터로 받을 수 있도록 지정
    - ```<? super T>```는 와일드카드에 하한 제한
      - T와 T의 상위 클래스만 타입 파라미터로 받도록 한다.
- 예제
  - ![image](https://user-images.githubusercontent.com/102513932/189833746-72480feb-0ab3-436e-9296-154e35d68e00.png)
  - call :통화 기능, 모든 휴대폰에서 사용 가능
    - ? extends Phone으로 타입 제한
  - faceId : 애플의 안면 인식 보안 기능, 아이폰만 사용 가능
    - ? extends IPhone으로 타입 제한
  - samsungPay : 삼성 휴대폰의 결제 기능, 삼성 휴대폰에서만 사용 가능
    - ? extends Galaxy로 타입 제한
  - recordVoice : 통화 녹음 기능, 안드로이드 휴대폰에서만 사용 가능
    - ? super Galaxy로 타입 제한
```java
class PhoneFunction {
    public static void call(User<? extends Phone> user) {
  
    }
    public static void faceId(User<? extends IPhone> user) {
    }
    public static void samsungPay(User<? extends Galaxy> user) {
    }
    public static void recordVoice(User<? super Galaxy> user) {

    }
}
public class Example {
    public static void main(String[] args) {
        PhoneFunction.call(new User<Phone>(new Phone()));
        PhoneFunction.call(new User<IPhone>(new IPhone()));
        PhoneFunction.call(new User<Galaxy>(new Galaxy()));
        PhoneFunction.call(new User<IPhone12Pro>(new IPhone12Pro()));
        PhoneFunction.call(new User<IPhoneXS>(new IPhoneXS()));
        PhoneFunction.call(new User<S22>(new S22()));
        PhoneFunction.call(new User<ZFlip3>(new ZFlip3()));

        System.out.println("\n######################################\n");

//        PhoneFunction.faceId(new User<Phone>(new Phone())); // X
        PhoneFunction.faceId(new User<IPhone>(new IPhone()));
        PhoneFunction.faceId(new User<IPhone12Pro>(new IPhone12Pro()));
        PhoneFunction.faceId(new User<IPhoneXS>(new IPhoneXS()));
//        PhoneFunction.faceId(new User<Galaxy>(new Galaxy())); // X
//        PhoneFunction.faceId(new User<S22>(new S22())); // X
//        PhoneFunction.faceId(new User<ZFlip3>(new ZFlip3())); // X

        System.out.println("\n######################################\n");

//        PhoneFunction.samsungPay(new User<Phone>(new Phone())); // X
//        PhoneFunction.samsungPay(new User<IPhone>(new IPhone())); // X
//        PhoneFunction.samsungPay(new User<IPhone12Pro>(new IPhone12Pro())); // X
//        PhoneFunction.samsungPay(new User<IPhoneXS>(new IPhoneXS())); // X
        PhoneFunction.samsungPay(new User<Galaxy>(new Galaxy()));
        PhoneFunction.samsungPay(new User<S22>(new S22()));
        PhoneFunction.samsungPay(new User<ZFlip3>(new ZFlip3()));

        System.out.println("\n######################################\n");

        PhoneFunction.recordVoice(new User<Phone>(new Phone()));
//        PhoneFunction.recordVoice(new User<IPhone>(new IPhone())); // X
//        PhoneFunction.recordVoice(new User<IPhone12Pro>(new IPhone12Pro())); // X
//        PhoneFunction.recordVoice(new User<IPhoneXS>(new IPhoneXS())); // X
        PhoneFunction.recordVoice(new User<Galaxy>(new Galaxy()));
//        PhoneFunction.recordVoice(new User<S22>(new S22())); // X
//        PhoneFunction.recordVoice(new User<ZFlip3>(new ZFlip3())); // X
    }
}
```  
- ![image](https://user-images.githubusercontent.com/102513932/189835393-15e11307-4d34-41f4-932f-cd7072f3139d.png)


java.util 패키지
-----------------------
#### 컬렉션 프레임워크
- 자료구조 클래스들을 컬렉션 프레임워크라 지칭
  - ![image](https://user-images.githubusercontent.com/102513932/189911067-6321a007-bc69-4abb-99b2-ec3e62510110.png)
    - Set
      - 중복 허용 x, 순서 유지 x
      - HashSet, TreeSet
      - collection 인터페이스 상속 받음
    - List
      - 중복 허용, 순서 기억
      - ArrayList, Vector, Stack, LinkedList
      - collection 인터페이스 상속 받음
    - Map
      - key, value값 존재
      - 데이터 순서 유지x
      - key 중복 불가능, 값 중복 가능
      - HashMap, HashTable, TreeMap, Properties
- 컬렉션 인터페이스
  - ![image](https://user-images.githubusercontent.com/102513932/189912063-485983f9-0132-42ae-9593-3f1fc172f248.png)

### 0. Iterator
- 컬렉션에 저장된 요소 순차적으로 읽어옴
  - Collection 인터페이스에 정의된 ```iterator()``` 호출시
    - Iterator 타입의 인스턴스 반환
    - List와 Set 인터페이스 구현 클래스는 ```iterator()``` 메서드 사용 가능
  - Iterator 인터페이스에 정의된 메서드
    - ![image](https://user-images.githubusercontent.com/102513932/189920731-0c60c1a2-43d6-41e4-a49f-0974c1b9acd4.png)
```java
ArrayList<String> list = ...;
Iterator<String> iterator = list.iterator();

while(iterator.hasNext()) {     // 읽어올 다음 객체가 있다면 
	String str = iterator.next(); // next()를 통해 다음 객체를 읽어옵니다. 
	if(str.equals("str과 같은 단어")){ // 조건에 부합한다면
		iterator.remove();            // 해당 객체를 컬렉션에서 제거합니다. 
	}
} //remove는 실제 삭제, next는 읽어오기만 함
```
- 
### 1. Set
- 집합
  - 요소의 중복 허용 x
  - 저장 순서를 유지하지 않음
  - ![image](https://user-images.githubusercontent.com/102513932/189921378-ee6f68c3-bc71-4ae6-b883-355f139b249c.png)
#### HashSet
```java
import java.util.Set; 

public class setExam{
  public static void main(String[] args){
    Set<String> set1 = new HashSet<>();
    //Set은 인터페이스라 객체 생성 불가능, HashSet 통해 인스턴스 생성
    //HashSet<String> set1 = new HashSet<>(); 가능
    set1.add("Hello");
    //제너릭이 String이니 String값 추가
    //set은 중복을 허용하지 않는 자료구조
    //add 메서드 사용시 boolean 값 리턴
    //같은 값이 이미 존재한다면 false, 없다면 true 리턴
    int size = set1.size();
    //자료의 크기 확인 가능 //1

    //들어있는 값을 하나씩 꺼내보기 위해서는 Iterator 인터페이스/for each 이용해야함
    Iterator<String> iter = set1.iterator();
    while(iter.hasNext()){ //data가 유무 조건
      String str = iter.next(); // 값을 꺼내고, 다음 값을 참조하게됨
      System.out.println(str);
    }
    // 인터페이스 보다 for each 구문이 간편함
    for(String str : set1){
      System.out.println(str);
    }
  }
}
```
#### TreeSet
- 이진 탐색 트리 형태로 데이터 저장
  - 데이터 중복 x, 저장 순서 유지 x
  - 모든 왼쪽 자식의 값이 루트나 부모보다 작음
  - 모든 오른쪽 자식의 값이 루트나 부모보다 큰 값을 가짐
  - ![image](https://user-images.githubusercontent.com/102513932/189933833-e99dd711-7890-426f-b36d-f905564855df.png) 
```java
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {

				// TreeSet 생성
        TreeSet<String> workers = new TreeSet<>();

				// TreeSet에 요소 추가
        workers.add("Lee Java");
        workers.add("Park Hacker");
        workers.add("Kim Coding");

        System.out.println(workers); 
        //전체 출력[Kim Coding, Lee Java, Park Hacker]
        System.out.println(workers.first());
        //최소값 출력 Kim Coding
        System.out.println(workers.last());
        //최대값 출력 Park Hacker
        System.out.println(workers.higher("Lee"));
        //입력값보다 큰 데이터 중 최소값 출력, 없으면 null / Lee Java
        System.out.println(workers.subSet("Kim", "Park"));
        //kim~park 까지의 객체들을 반환 / [Kim Coding, Lee Java]
    }
}
```
![image](https://user-images.githubusercontent.com/102513932/189937071-1083720b-c39c-4d2b-a2c8-c51ca7f9ef16.png)
### 2. List
- 순서는 존재하지만 자료의 길이를 제한하지 않는 자료구조
- 배열과 같이 객체를 일렬로 늘어 놓은 구조
  - 자동으로 인덱스 부여, 인덱스로 객체 검색, 추가, 삭제 기능 제공
  - ![image](https://user-images.githubusercontent.com/102513932/189915797-d74bad69-e545-48fc-bc23-c47b4e2dea9d.png)
#### ArrayList
- List의 인터페이스 구현
- 객체가 인덱스로 관리
  - 데이터 연속적으로 존재, 순서 유지
- 객체 추가시 자동으로 저장 용량이 늘어남
```java
import java.util.ArrayList;
import java.util.List;

public class ListExam{
  public static void main(String[] args){
    ArrayList<타입 매개변수> 객체명 = new ArrayList<타입 매개변수>(초기 저장 용량);
    List<String> list = new ArrayList<>();
    //List는 인터페이스라 객체 생성 불가능, ArrayList로 인스턴스 생성할 것
    //혹은 ArrayList<String> arrayList = new ArrayList<>(); 이용
    //list에 3개의 문자열을 저장합니다.
    list.add("kim");
    list.add("lee");
    list.add("kim");

    System.out.println(list.size()); //3
    for(int i=0; i< list.size(); i++){
      String str = list.get(i); 
      System.out.println(str); // kim, lee, kim

    list.remove(0);

    for(String s : list){
      System.out.println(s); // lee, kim
    }
    }
  }
}
```
#### LinkedList
- 데이터를 효율적으로 추가, 삭제, 변경하기 위해 사용
  - 데이터 불연속적 존재, 데이터 서로 연결
  - ![image](https://user-images.githubusercontent.com/102513932/189917860-0c53302c-cbde-4276-b80d-d088053a5219.png)
  - 자신과 연결된 이전 요소 및 다음 요소의 주소값 + 데이터로 구성
  - 데이터 이동시 복사를 하지 않기 때문에 속도가 훨씬 빠름
    - ArrayList는 데이터 추가 및 삭제 시 데이터 복사 多
      - 중간에 위치한 객체 추가 및 삭제 시 속도 저하
        - 순차적 추가, 삭제시에는 유리
      - 검색 측면에서는 유리
    - LinkedList는 데이터 추가 및 삭제 시 주소값만 변경
      - 중간에 데이터를 추가 및 삭제 시, 비교적 빠른 속도
      - 검색 측면에서는 불리
  - 데이터의 잦은 변경시 LinkedList, 데이터의 개수가 변하지 않는다면 ArrayList 사용 권장
```java
ArrayList<String> list = new LinkedList<>(); // 선언부
```  
![image](https://user-images.githubusercontent.com/102513932/189936989-8897402c-f824-4f78-98e7-d9f5df61fd54.png)
### 3. Map
- 키와 값으로 구성된 객체 저장 (Entry 객체)
  - ![image](https://user-images.githubusercontent.com/102513932/189934358-74bf7f9e-db0b-42bb-9efe-8d89b2983599.png)
- 키는 중복 저장 x , 값은 중복 저장 가능
  - 동일 키로 값 저장시 기존 값 새로운 값으로 대체
- ![image](https://user-images.githubusercontent.com/102513932/189934726-f93505e7-7a22-45b1-ad2e-7a45813c9f3e.png)
#### HashMap
 - 해시 함수를 통해 저장되는 위치 결정
   - 사용자는 위치를 알 수없음
   - 삽입되는 순서와 위치는 관계성 x
 - 많은 양의 데이터를 검색 시 뛰어난 성능
 - ![image](https://user-images.githubusercontent.com/102513932/189935163-eacfbdfe-ceb1-410c-949b-eab79c69f509.png)
```java
import java.util.HaspMap;
import java.util.Map;

public class MapExam{
  public static void main(String[] args){
    Map<String,String> map = new HashMap<>();
    // HashMap<String,String> hashmap = new HashMap<>(); 가능
    map.put("001", "kim"); // key, value
    map.put("002", "lee");
    map.put("003", "choi");

    map.put("001", "kang"); //001에 kim 대신 kang 입력

    System.out.println(map.size()); //3 출력
    System.out.println(map.get("001")); //"kang" 출력

    Set<String> keys = map.keySet();
    //key값 set 자료구조에 저장 , 순회하기 위해 필요.

    //for each를 통한 자료구조 출력
    for each(String str: keys){
      System.out.println(str); //key값 출력
      System.out.println(map.get(str));// value값 출력
    } 

    //while문과 iteratior을 활용한 자료구조 출력
    Iterator<String> iter = keys.iterator();
    while(iter.hasNext()){
      String key = iter.next(); //key값
      String value = map.get(key); //value 값
      System.out.println(key + ":" + value);
    }

    //객체 삭제
    map.remove("001");

    //객체 전체 삭제
    map.clear();
  }
}
![image](https://user-images.githubusercontent.com/102513932/189936912-38754084-1a90-445e-869f-8c9471fa9f73.png)
```

#### 4. Date
- 지역화에 대해 고려되지 않음
  - 대부분 Deprecated
```java
Date date = new Date();
        System.out.println(date.toString());
        // Thu Sep 08 17:21:38 KST 2022
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss a zzz");
        // yyyy는 년도, MM은 월, dd는 일 표시
        // hh는 시간, mm은 분, ss는 초를 표현 a는 오전/오후
        // zzz는 TimeZone을 나타낸다. 한국표준시 KST
        System.out.println(ft.format(date));
        // 2022.09.08 at 05:21:38 오후 KST
        long today = System.currentTimeMillis();
        // 현재 시간 Long 타입으로 저장
        System.out.println(today - date.getTime());
        // 현재시간 - date 생성 시간 == 코드 사이 걸린 시간
```
#### 5. Calendar
```java
Calendar cal = Calendar.getInstance();
        // calendar는 추상 클래스, new로 인스턴스를 생성할 수 없음
        // calendar.getInstance(); 메소드는 calendar의 자식 클래스인 그레고리안 인스턴스의 값을 리턴해줌
        // 추상 클래스의 리턴 값은 자식 클래스들이 리턴될 수 있음
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH)+1); //0부터 출력됨, +1 해줄
        System.out.println(cal.get(Calendar.DATE));

        System.out.println(cal.get(Calendar.HOUR));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));

        cal.add(Calendar.HOUR,5);
        // add 메소드를 이용해 값을 더하고 뺼 수 있음.
```
6. java.time 패키지도 있음, 이하 생략


java IO 패키지 
-------------------------
- 추후 프로그래머스 참고할 것
- 입출력을 위한 인터페이스와 클래스들
  - 크게 byte 단위 입출력과 문자 단위 입출력 클래스로 나뉨
    - byte 단위는 InputStream과 OutputStream 추상클래스를 상속받아 만들어짐
    - 문자(char)단위는 Reader와 Writer 추상클래스를 상속받아 만들어짐
1. Byte 단위 입출력
2. 다양한 타입의 입출력
3. char 단위 입출력
