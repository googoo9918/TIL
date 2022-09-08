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
public class ClassName <T> { ... }
 
public class Student { ... }
 
public class Main {
	public static void main(String[] args) {
		ClassName<Student> a = new ClassName<Student>();
	}
}
 // 사용자 정의 클래스도 당연히 사용 가능하다.
```
- 기초 예시
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
```

 java.util 패키지
 -----------------------
 1. Set
```java
import java.util.Set; 

public class setExam{
  public static void main(String[] args){
    Set<String> set1 = new HashSet<>();
    //Set은 인터페이스라 객체 생성 불가능, HashSet 통해 인스턴스 생성
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
 2. List
- 순서는 존재하지만 자료의 길이를 제한하지 않는 자료구조
```java
import java.util.ArrayList;
import java.util.List;

public class ListExam{
  public static void main(String[] args){
    List<String> list = new ArrayList<>();
    //List는 인터페이스라 객체 생성 불가능, ArrayList로 인스턴스 생성할 것
    //혹은 ArrayList<String> arrayList = new ArrayList<>(); 이용
    //list에 3개의 문자열을 저장합니다.
    list.add("kim");
    list.add("lee");
    list.add("kim");

    System.out.println(list.size());
    for(int i=0; i< list.size(); i++){
      String str = list.get(i);
      System.out.println(str);

    for(String s : list){
      System.out.println(s);
    }
    }
  }
}
```
 3. Map
```java
import java.util.HaspMap;
import java.util.Map;

public class MapExam{
  public static void main(String[] args){
    Map<String,String> map = new HashMap<>();
    map.put("001", "kim"); // key, value
    map.put("002", "lee");
    map.put("003", "choi");

    map.put("001", "kang"); //001에 kim 대신 kang 입력

    System.out.println(map.size()); //3 출력
    System.out.println(map.get("001")); //"kang" 출력

    Set<String> keys = map.keySet();
    //key값 set 자료구조에 저장

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
  }
}
```

4. Date
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
5. Calendar
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