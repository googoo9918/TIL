Java 기초 문법
============

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

Class
--------------
```java
    Car c1 = new Car(); 
	Car c2 = new Car(); 
	    /*
		 * new 연산자는 new연산자 뒤에 나오는 생성자를 이용하여
            * 메모리에 객체를 만들라는 명령.
		 * 이때 기본형 타입은 stack에 저장
            * 참조형 타입은 heap 메모리에 저장된다. 
		  
		 * 메모리에 만들어진 객체를 인스턴스(instance)라고도 한다.
            * -> car이 객체가 되겠지?

		 * 이렇게 만들어진 객체를 참조하는 변수가 c1 , c2 이다.
            * -> instance를 가리키는(참조하는) 변수!

		 * 위의 코드가 실행되면 Car라는 객체가
		 * 2개가 만들어지고 각각의 객체를 참조하는 c1과 c2변수 선언.
		 */
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
		
		System.out.println(str.length());
		
		System.out.println(str.concat(" world"));
		
		System.out.println(str);  
        //위에서 concat을 통해 hello world를 만들어 줬지만 string 객체는 불변 클래스 이기 때문에, 
		// str이 hello를 가리킨다는 사실은 변하지 않는다.
		
		str = str.concat(" world"); 
        //reference를 바꾸고 싶은 경우, 이와 같이 사용해줘야 한다.
				
		System.out.println(str.substring(3)); 
        // 3번째 index ~ 끝까지 출력
		System.out.println(str.substring(3,5)); 
        // start ~ end 까지 출력.
```
