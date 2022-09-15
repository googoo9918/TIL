## 목차
- [Java 기초 문법](#자바의-특징)<br>
  * [자바의 특징](#자바의-특징)<br>
  * [기본 타입과 참조 타입](#기본-타입과-참조-타입)<br>
  * [주석문](#주석문)<br>
  * [애너테이션(Annotation)](#애너테이션annotation)
    * * [표준 애너테이션](#표준-애너테이션)
    * [메타 애너테이션](#메타-애너테이션)
    * [사용자 정의 애너테이션](#사용자-정의-애너테이션)
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
  * [java IO 패키지](#java-io-패키지)<br>
  + [람다(Lambda)](#람다lambda)
      - [람다식 기본 문법](#람다식-기본-문법)
      - [함수형 인터페이스](#함수형-인터페이스)
        * [1. 매개변수와 리턴값이 없는 람다식](#1-매개변수와-리턴값이-없는-람다식)
        * [2. 매개변수가 있는 람다식](#2-매개변수가-있는-람다식)
        * [3.리턴값이 있는 람다식](#3리턴값이-있는-람다식)
      - [메서드 참조(레퍼런스)](#메서드-참조레퍼런스)
        * [정적 메서드와 인스턴스 메서드 참조](#정적-메서드와-인스턴스-메서드-참조)
        - [생성자 참조](#생성자-참조)
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

### 애너테이션(Annotation)
- 주석
  - 소스 코드를 읽는 사람에게 정보를 전달
- 애너테이션
  - 특정 코드를 사용하는 프로그램에게 정보를 전달
    - 컴파일러에게 문법 에러를 체크하도록 정보 제공
    - 프로그램 빌드시 코드 자동 생성 가능하게 정보 제공
    - 런타임에 특정 기능을 실행하도록 정보 제공
```java
@Test //아래 메서드가 테스트 대상임을 테스트 프로그램에게 알리는 애너테이션
public void run() {}
//애너테이션이 붙여진 run() 메서드만 테스트 프로그램에게 테스팅이 필요한 메서드로 전달 됨.
// 다른 프로그램들에게는 아무런 영향을 주지 않음

public void stop() {}
```
- 종류
  - #### 표준 애너테이션
    - ![image](https://user-images.githubusercontent.com/102513932/190326064-dcfb3357-dba6-44b0-8e5e-b691aa849139.png)
    - @Override
      - 메서드 앞에서만 붙일 수 있음
      - 상위 클래스의 메서드를 오바리이딩 한다는 것을 컴파일러에게 알려줌
      - 개발자의 실수로 오버라이딩이 잘못 이뤄졌을 때, 에러를 발생시켜 알려줌.
    - @Deprecated
      - 새로운 것으로 대체되었으니 기존의 것을 사용하지 않을 것을 권장
      - 하위 버전 호환성 문제로 삭제하기는 곤란하나, 사용하는 것을 권장하지 않을 때 사용
    - @SuppressWarnings
      - 컴파일 경고 메시지가 나타나지 않도록 함
      - ![image](https://user-images.githubusercontent.com/102513932/190326632-1ed05183-b47c-4af3-9fa6-a58b4a7a9965.png)
```java
@SuppressWarnings({"deprecation", "unused", "null"})
```
    - @FunctionallInterface
      - 함수형 인터페이스 선언시, 컴파일러가 선언이 바르게 되었는지 확인
      - 함수형 인터페이스는 단 하나의 추상 메서드만을 가져야 함
```java
@FunctionalInterface
public interface Runnable{
  public abstract void runt (); //단 하나의 추상 메서드
}
```      
      - 코드 작성과정에서 실수를 방지하기 위한 확인용 애너테이션
  - #### 메타 애너테이션
    - ![image](https://user-images.githubusercontent.com/102513932/190326123-a66c54c0-3e3f-427b-8e26-812493884d6f.png)
    - @Target
      - 애너태이션을 지정할 대상을 지정하는데 사용
      - ```java.lang.annotation.ElementType```에 정의
      - ![image](https://user-images.githubusercontent.com/102513932/190327088-b203ec00-65ab-4732-8957-d23ec3954a20.png)
```java
import static java.lang.annotation.ElementType.*;

@Target({FIELD, TYPE, TYPE_USE}) //적용 대상이 FIELD, TYPE
public @interface CustomAnnotation{} //CustomAnnotation 정의

@CustomAnnotation //적용 대상이 TYPE인 경우
class main{

  @CustomAnnotation //적용 대상이 FIELD인 경우
  int i;
}
```      
      - 사용자 정의 애너테이션의 적용 범위 설정 가능
    - @Documented
      - 애너테이션에 대한 정보가 javadoc으로 작성한 문서에 포함되도록 하는 설정
      - @Override와 @SuppressWarinings를 제외하고 모두 @Documented 적용
```java
@Documented
@Target(ElementType.Type)
public @interface CustomAnnotation { }
```
    - @Inherited
      - 하위 클래스가 애너테이션을 상속받도록 함
      - 상위 클래스에 부착시, 하위 클래스도 애너테이션이 동일하게 적용됨
```java
@Inherited // @SuperAnnotation이 하위 클래스까지 적용
@interface SuperAnnotation{ }

@SuperAnnotation
class Super { }

class Sub extends Super{ } // Sub에 애너테이션이 붙은 것으로 인식
```
      - ```sub``` 하위 클래스는 상위 클래스와 동일하게 @SuperAnnotation에 정의된 내용들을 적용받게 된다.
    - @Retention
      - 애너테이션의 지속 시간 결정
      - ![image](https://user-images.githubusercontent.com/102513932/190334180-8ac607a9-821c-4b47-a221-cd3ae588f789.png)
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE) 
//오버라이딩이 제대로 되었는지 컴파일러가 확인하는 용도 
//클래스 파일에 남길 필요 없이 컴파일시에만 확인하고 사라짐
public @interface Override(){ }
```     
      - 위 예제에서 ```Override``` 애너테이션은 컴파일러가 사용하면 끝
        - 실행시에는 더이상 사용되지 않음을 의미
    - @Repetable
      - 애너테이션을 여러 번 붙일 수 있도록 허용
```java
@Repeatable(Works.class) // ToDo 애너테이션을 여러 번 반복해서 쓸 수 있게 한다.  
@interface Work{  
    String value();  
}

@Work("코드 업데이트")  
@Work("메서드 오버라이딩")  
class Main{  
	... 생략 ...
}
```  
    - 사용자 타입 애너테이션 ```TODO``` 정의 후 ```@Repeatable``` 애너테이션 사용하여 여러번 사용할 수 있도록 함
    - 단, 같은 이름의 애너테이션이 여러번 적용될 수 있음
      - 애너테이션들을 하나로 묶어주는 애너테이션 별도 작성 요망
```java
@interface Works {  // 여러개의 ToDo애너테이션을 담을 컨테이너 애너테이션 ToDos
    Work[] value(); 
}

@Repeatable(Works.class) // 컨테이너 애너테이션 지정 
@interface Work {
	String value();
}
```
  - #### 사용자 정의 애너테이션
    - 사용자가 직접 정의하는 애너테이션
    - 인터페이스 정의와 유사
```java
@interface 애너테이션명 { // 인터페이스 앞에 @기호만 붙이면 애너테이션을 정의할 수 있습니다. 
	타입 요소명(); // 애너테이션 요소를 선언
}
```
    - 단, 애너테이션은 java.lang.annotation 인터페이스를 상속 받음
      - 다른 클래스나 인터페이스 상속 불가능

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
#### entrySet, keyset을 이용한 출력
```java
HashMap<Integer, String> map = new HashMap<Integer, String>()
//entrySet() 기본 선언
Set<Entry<Integer, String>> entry = map.entrySet();

//entrySet() 활용
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println("[Key]:" + entry.getKey() + " [Value]:" + entry.getValue());
}

//keySet() 기본 선언
Set<Integer> keys = map.keySet();

//KeySet() 활용 -> 저장된 key들을 set 객체로 리턴.
for(Integer i : map.keySet()){ //저장된 key값 확인
    System.out.println("[Key]:" + i + " [Value]:" + map.get(i));
}
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


### 람다(Lambda)
- 함수형 프로그래밍 기법을 지원하는 문법요소
- 메서드를 하나의 '식'으로 표현
  - 코드 간결하고 명확하게 표현 가능
- 함수형 인터페이스 사용
#### 람다식 기본 문법
```java
//기존 메서드 표현 방식
void sayhello() {
	System.out.println("HELLO!")
}

//위의 코드를 람다식으로 표현한 식
() -> System.out.println("HELLO!")
```
- 반환 타입과 이름 생략 가능
  - 익명 함수라 지칭하기도
```java
// 기존 메서드 표현 방식
int sum(int num1, int num2) {
	return num1 + num2;
}

// 람다식으로 표현한 식
(int num1, int num2) -> { // 반환타입과 메서드명 제거 + 화살표 추가
	return num1 + num2; //return 생략 가능
}

// 기존 메서드 표현 방식
void example1() {
	System.out.println(5);
}
// 람다식으로 표현한 식
() -> {System.out.println(5);}

// 기존 메서드 표현 방식
void example3(String str) {
	System.out.println(str);
}
// 람다식으로 표현한 식
(String str) -> {	System.out.println(str);}
```
- 특정 조건 충족시 더욱 축약 가능
  - 실행문이 하나만 존재할 때 중괄호 생략 가능
  - 매개변수 타입을 쉽게 유추할 수 있는 경우, 매개변수 타입 생략 가능
```java
// 기존 방식
int sum(int num1, int num2) {
	return num1 + num2;
}

// 람다식
(int num1, int num2) -> {
	num1 + num2
}

// 축약1
(int num1, int num2) -> num1 + num2

// 축약2
(num1, num2) -> num1 + num2
```
#### 함수형 인터페이스
- [함수형 인터페이스_codechacha.com](https://codechacha.com/ko/java8-functional-interface/)
- 람다식 또한 사실은 객체
  - 이름이 없기 때문에 익명 클래스
    - 객체의 선언과 생성을 동시에 하여 하나의 객체 생성, 단 한번만 사용되는 일회용 클래스
```java
//sum 메서드 람다식
(num1, num2) -> num1 + num2

//람다식 객체 표현
new Object() { 
  int sum(int num1, int num2){
    return num1 + num2;
  }
}
```
- 람다 객체에 접근하기 위해 참조변수가 필요함.
  - 위 처럼 Object 클래스에 담는다 하더라도 클래스 내에 sum 메서드가 없기 때문에 sum 메서드 사용 불가능
    - 람다식 함수형 인터페이스를 통해 참조변수 문제 해결
    - 람다식도 하나의 객체이기 때문에 인터페이스에 정의된 추상메서드 구현 가능
    -  단 하나의 추상 메서드 선언 가능
    -  람다식과 인터페이스 메서드 1:1 매칭 요망
```java
public class LamdaExample1 {
    public static void main(String[] args) {

		ExampleFunction exampleFunction = (num1, num2) -> num1 + num2
		System.out.println(exampleFunction.sum(10,15))
  }
}
@FunctionalInterface // 컴파일러가 인터페이스가 바르게 정의되었는 지 확인할 수 있도록
interface ExampleFunction {
		public abstract int sum(int num1, int num2);
}

// 출력값
25
```
- 위 코드 처럼, 함수형 인터페이스 사용으로 원하는 메서드에 접근 가능
- ##### 1. 매개변수와 리턴값이 없는 람다식
```java
@FunctionalInterface
public interface MyFunctionalInterface {
    public void accept();
}
```
```java
public class MyFunctionalInterfaceExample {
	public static void main(String[] args) throws Exception {
		MyFunctionalInterface example;
		example = () -> {
			String str = "첫 번째 메서드 호출!";
			System.out.println(str);
		};
		example.accept();

		example = () -> System.out.println("두 번째 메서드 호출!");
		//실행문이 하나라면 중괄호 { }는 생략 가능
		example.accept();
	}
}

// 출력값
첫 번째 메서드 호출!
두 번째 메서드 호출!
```
  - 이처럼 함수형 인터페이스를 사용해 람다식을 다룰 수 있다.
- ##### 2. 매개변수가 있는 람다식
```java
@FunctionalInterface
public interface MyFunctionalInterface {
    public void accept(int x);
} // 매개변수가 있고 리턴값이 없는 추상 메서드를 가진 함수형 인터페이스
```
```java
public class MyFunctionalInterfaceExample {
    public static void main(String[] args) throws Exception {
        MyFunctionalInterface example;
        example = (x) -> {
            int result = x * 5;
            System.out.println(result);
        };
        example.accept(2);

        example = (x) -> System.out.println(x * 5);
        example.accept(2);
    }
}

// 출력값
10
10
```
  - 람다식이 대입된 인터페이스 참조 변수는 ```aceept()``` 호출 가능
- ##### 3.리턴값이 있는 람다식
```java
public interface MyFunctionalInterface {
    public int accept(int x, int y);
}// 매개 변수와 리턴값을 갖는 추상 메서드를 포함하는 함수형 인터페이스
```
```java
public class MyFunctionalInterfaceExample {
    public static void main(String[] args) throws Exception {
        MyFunctionalInterface example;
        example = (x, y) -> {
            int result = x + y;
            return result;
        };
        int result1 = example.accept(2, 5);
        System.out.println(result1);
        

        example = (x, y) -> { return x + y; };
        int result2 = example.accept(2, 5);
        System.out.println(result2);
       

	      example = (x, y) ->  x + y;
				// 실행문이 한 줄인 경우, 중괄호 {}와 return문 생략가능
        int result3 = example.accept(2, 5);
        System.out.println(result3);
       

        example = (x, y) -> sum(x, y);
				// 실행문이 한 줄인 경우, 중괄호 {}와 return문 생략가능
        int result4 = example.accept(2, 5);
        System.out.println(result4);
 
    }

    public static int sum(int x, int y){
        return x + y;
    }
}

//출력값
7
7
7
7
```

#### 메서드 참조(레퍼런스)
- 람다식에서 불필요한 매개변수 제거시 주로 사용
- 인터페이스의 익명 구현 객체로 생성
  - 인터페이스의 추상 메서드가 어떤 매개 변수를 갖는지
  - 리턴 타입이 무엇인가에 따라 달라짐
- ex)
```java
// 개선 전
(left, right) -> Math.max(left, right);
// 입력값과 출력값의 반환타입 쉽게 유추 가능

// 개선 후
// 클래스이름 ::메서드이름
Math :: max; //메서드 참조
```
##### 정적 메서드와 인스턴스 메서드 참조
- 정적 메서드 참조 시
  - 클래스 이름 뒤에 ``` :: ``` 기호 붙이고 정적 메서드 이름 기술
    - ```클래스 :: 메서드```
- 인스턴스 메서드 참조 시
  - 객체 생성 후 참조 변수 뒤 ```::``` 기호 붙이고 메서드 이름 기술
    - ```참조 변수 :: 메서드```
- Caluclator 정적 및 인스턴스 메서드 참조 예시
```java
//Calculator.java
public class Calculator {
  public static int staticMethod(int x, int y) {
   return x + y;
  }

  public int instanceMethod(int x, int y) {
   return x * y;
  }
}
import java.util.function.IntBinaryOperator;

public class MethodReferences {
  public static void main(String[] args) throws Exception {
    IntBinaryOperator operator;

    /*정적 메서드
		클래스이름::메서드이름
		*/
    operator = Calculator::staticMethod;
    System.out.println("정적메서드 결과 : " + operator.applyAsInt(3, 5));

    /*인스턴스 메서드
		인스턴스명::메서드명
		*/
		
    Calculator calculator = new Calculator();
    operator = calculator::instanceMethod;
    System.out.println("인스턴스 메서드 결과 : "+ operator.applyAsInt(3, 5));
  }
}
/*
정적메서드 결과 : 8
인스턴스 메서드 결과 : 15
*/
```

##### 생성자 참조
- 생성자 참조는 객체 생성을 의미
```java
//개선 전
(a,b) -> {return new 클래스(a,b);};

//개선 후
//생성자 참조 문법
클래스 :: new
```
- 클래스 이름 뒤에 ```::``` 기호 붙이고 new 연산자 기술
- 생성자 오버로딩시, 함수형 인터페이스의 추상 메서드와 동일한 매개변수 타입과 개수를 갖고 있는 생성자 찾아 실행
```java
//Member.java
public class Member {
  private String name;
  private String id;

  public Member() {
    System.out.println("Member() 실행");
  }

  public Member(String id) {
    System.out.println("Member(String id) 실행");
    this.id = id;
  }

  public Member(String name, String id) {
    System.out.println("Member(String name, String id) 실행");
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

public String getId() {
    return id;
  }
}
```
```java
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConstructorRef {
  public static void main(String[] args) throws Exception {
    Function<String, Member> function1 = Member::new;
    Member member1 = function1.apply("kimcoding");

    BiFunction<String, String, Member> function2 = Member::new;
    Member member2 = function2.apply("kimcoding", "김코딩");
  }
}

/*
Member(String id) 실행
Member(String name, String id) 실행
*/
```
- 생성자 참조를 이용해 두 가지 방법으로 Member 객체 생성
  - ```Function<String, Member> 함수형 인터페이스의 ```Member apply(String)``` 메서드를 이용해 Member 객체 생성
  - ```BiFunction<String, String, Member> 함수형 인터페이스의 Member 객체 생성
  - 매개변수의 타입과 개수에 따라 다른 생성자 실행

### 스트림
#### 스트림의 특징
- 배열, 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리해주는 반복자
- 선언형으로 데이터 소스를 처리한다
  - "어떻게"가 아닌 "무엇을" 수행하는지에 초점
```java
// 스트림을 사용하지 않은 List의 요소 순차적 처리
import java.util.List;

public class ImperativeProgrammingExample {
    public static void main(String[] args){
        // List에 있는 숫자들 중에서 4보다 큰 짝수의 합계 구하기
        List<Integer> numbers = List.of(1, 3, 6, 7, 8, 11);
        int sum = 0;

        for(int number : numbers){
            if(number > 4 && (number % 2 == 0)){
                sum += number;
            }
        }

        System.out.println("# 명령형 프로그래밍 : " + sum);
    }
}
```

```java
//스트림을 사용한 List의 순차적 요소 처리
import java.util.List;

public class DeclarativeProgramingExample {
    public static void main(String[] args){
        // List에 있는 숫자들 중에서 4보다 큰 짝수의 합계 구하기
        List<Integer> numbers = List.of(1, 3, 6, 7, 8, 11);

        int sum =
                numbers.stream()
                        .filter(number -> number > 4 && (number % 2 == 0))
                        .mapToInt(number -> number)
                        .sum();

        System.out.println("# 선언형 프로그래밍: " + sum);
    }
}
```
- Stream 사용시 단순화, 코드 가독성 증대

##### 람다식으로 요소 처리 코드 제공
- Stream이 제공하는 대부분의 요소 처리 메서드는 함수형 인터페이스 매개타입을 가짐
  - 람다식 또는 메서드 참조를 이용, 요소 처리 내용을 매개값으로 전달 가능
```java
public class Student {
    private String name;
    private int score;

    public Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }
}
```
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamLambdaExample {
    public static void main(String[] args) throws Exception {
        List<Student> list = Arrays.asList(
            new Student("김코딩", 95),
            new Student("이자바", 92)
        );

        Stream<Student> stream = list.stream();
        stream.forEach( s -> {
            String name = s.getName();
            int score = s.getScore();
            System.out.println(name+ " - " +score);
        });
    }
}
/*
김코딩 - 95
이자바 - 92
*/
//컬렉션에 저장된 Student를 하나씩 가져와 
//학생 이름과 성적을 콘솔에 출력하도록 
//forEach() 메서드에 매개값으로 람다식을 주는 코드.
```

##### 내부 반복자 사용으로 인한 병렬 처리
- 외부 반복자
  - 개별자가 코드로 직접 컬렉션의 요소를 반복해서 가져오는 코드 패턴
  - index를 사용하는 for문, iterator을 이용하는 while문
- 내부 반복자
  - 컬렉션 내부에서 요소들을 반복시킴
  - 개발자는 요소당 처리해야할 코드만 제공하는 코드 패턴
- ![image](https://user-images.githubusercontent.com/102513932/190397519-ff2e090a-a7cf-431d-8895-d599f1b2f9b4.png)
- 내부 반복자의 이점
  - 요소 반복의 여지는 컬렉션에게 맡김
  - 개발자는 요소 처리 코드에만 집중
  - 병렬 작업을 할 수 있게 도와 줌
    - 요소들의 반복 순서 변경
    - 멀티 코어 CPU 활용을 위한 요소 분배
  - 중간 연산과 최종 연산을 할 수 있다
    - 중간 연산 ex) 매핑, 필터링, 정렬
    - 최종 연산 ex) 반복, 카운팅, 평균, 총합

#### 파이프라인 구성
- 리덕션(Reduction)
  - 대량의 데이터를 가공 후 축소하는 것
  - ex) 합계, 평균값, 카운팅, 최대값, 최소값
- 파이프라인
  - 파이프라인은 여러개의 스트림이 연결되어 있는 구조
  - 최종 연산을 제외하고는 모두 중간 연산 스트림
  - 스트림은 데이터의 중간 연산과 최종 연산을 파이프라인으로 해결
  - ![image](https://user-images.githubusercontent.com/102513932/190398614-9833e6ce-ff26-4165-851d-94100d106ed4.png)
  - 최종 연산 시작시 중간 연산 시작
    - 중간 스트림이 생성될 때 중간 연산 시작 X
    - 중간 연산 메소드는 중간 연산된 스트림을 리턴
    - 스트림에서 다시 중간 연산 메소드를 호출해서 파이프라인 형성
  - ![image](https://user-images.githubusercontent.com/102513932/190399019-9259fd4d-7b2a-4925-8b2d-8b8e65bcc0d6.png)
    - 컬렉션에서 남자만 필터링 하는 중간 스트림 연결
    - 나이로 매핑하는 스트림 연결
    - 최종적으로 평균 나이 집계
  - 위 사진 코드로 표현시
```java
Stream<Member> maleFemaleStream = list.stream();
Stream<Member> maleStream = maleFemaleSTream.filter(m -> m.getGender() == Member.MALE);
//남자 member 객체를 요소로 하는 새로운 스트림 생성
IntStream ageStream = maleStream.mapToInt(Member::getAge);
// member 객체를 age 값으로 매핑, age를 요소로 하는 새로운 스트림 생성
OptionalDouble opd = ageStream.average();
// age요소의 평균을 OptionalDouble에 저장
double ageAve = opd.getAsDouble();
// 평균값을 읽기 위한 메소드 호출
```

#### 스트림 생성, 중간 연산, 최종 연산
##### 스트림 생성
- Collection 인터페이스에 stream() 정의
- ```stream()``` 사용시 해당 Collection의 객체를 소스로 하는 Stream 반환
```java
//List로부터 스트림 생성
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> listStream = list.stream();
listStream.forEach(System.out::println); //스트림의 모든 요소 출력
```
- 배열의 원소들을 소스로 하는 Stream 생성
  - Stream의 of 메서드 사용
  - Arrays의 stream 메소드 사용
```java
// 배열로부터 스트림을 생성
Stream<String> stream = Stream.of("a", "b", "c"); //가변인자
Stream<String> stream = Stream.of(new String[] {"a", "b", "c"});
Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"});
Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"}, 0, 3); //end 범위 미포함
```

- int와 long, double 같은 자료형을 사용하기 위한 스트림
  - Stream(IntStream,LongStream,DoubleStream)
  - Intstream은 range() 함수를 사용하여 기존 for 문 대체 가능
```java
//4이상 10미만의 숫자를 갖는 IntStream
IntStream stream = IntStream.rangr(4, 10);
```
- ![image](https://user-images.githubusercontent.com/102513932/190414594-c0c53661-b6b5-4a25-801d-976eab7b518c.png)
- 스트림 사용시 주의점
  - 스트림은 데이터를 읽기만 할 뿐 변경하지 않음(Read-only)
  - 스트림은 일회용. 사용했다면 새로운 스트림을 만들어야 한다.

##### 중간연산
-![image](https://user-images.githubusercontent.com/102513932/190414920-9dcffaf1-8cd2-49e5-ae06-68a18a3ae834.png)
- 중간 연산은 연산 결과를 스트림으로 반환, 연속해서 여러 번 수행 가능
###### 필터링(filter(),distinct())
- ```filter()```
  - 조건에 맞는 데이터만을 정제하여 더 작은 컬렉션 생성
  - 매개값으로 조건이 주어지고, 조건이 참이 되는 요소만 필터링
- ```distinct()```
  - Stream의 요소들에 중복된 데이터가 존재하는 경우, 중복 제거를 위해 사용
```java
import java.util.Arrays;
import java.util.List;

public class FilteringExample {
    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("김코딩", "이자바", "김인기", "김코딩");

        names.stream()
						 .distinct() //중복제거
						 .forEach(n -> System.out.println(n));
        System.out.println();

        names.stream()
						 .filter(n -> n.startsWith("김")) //필터링
						 .forEach(n -> System.out.println(n));
        System.out.println();

        names.stream()
						 .distinct() //중복제거
						 .filter(n -> n.startsWith("김")) //필터링
						 .forEach(n -> System.out.println(n));
    }
}
/*
김코딩
이자바
김인기

김코딩
김인기
김코딩

김코딩
김인기
*/
```
###### 매핑(map())
- ```map()```
  - 기존의 Stream 요소들을 대체하는 요소로 구성된 새로운 Stream을 형성하는 연산
  - 저장된 값을 특정한 형태로 변환하는데 주로 사용
  - map 함수의 인자로 함수형 인터페이스 function을 받고 있음
```java
List<String> names = Arrays.asList("kimcoding", "javalee", "ingikim", "kimcoding");
    names.stream()
				 .map(s -> s.toUpperCase())
				 .forEach(n->System.out.println(n));
/*
KIMCODING
JAVALEE
INGIKIM
KIMCODING
*/
```
  - map() 이외에도 mapToInt(), MapToLong(), MapToDouble() 존재
  - map()은 Stream 객체와 원시 Stream 사이 교환시 사용
    - 원시 객체는 mapToObject를 통해 일반적인 Stream 객체로 바꿀 수 있음
- ```flatMap()```
  - 요소를 대체하는 복수 개의 요소들로 구성된 새로운 스트림 리턴
  - map()은 스트림의 스트림을 반환
    - 리턴 타입 Stream<Stream>
  - flatMap()은 스트림을 반환
    -  asd