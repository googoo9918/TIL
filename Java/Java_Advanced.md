### 목차
- [목차](#목차)
- [java IO 패키지](#java-io-패키지)
  - [InputStream, OutputStream](#inputstream-outputstream)
    - [FileInputStream](#fileinputstream)
    - [FileOutputStream](#fileoutputstream)
    - [FileReader](#filereader)
    - [FileWriter](#filewriter)
  - [File](#file)
- [람다(Lambda)](#람다lambda)
  - [람다식 기본 문법](#람다식-기본-문법)
  - [함수형 인터페이스](#함수형-인터페이스)
  - [메서드 참조(레퍼런스)](#메서드-참조레퍼런스)
    - [정적 메서드와 인스턴스 메서드 참조](#정적-메서드와-인스턴스-메서드-참조)
    - [생성자 참조](#생성자-참조)
- [스트림](#스트림)
  - [스트림의 특징](#스트림의-특징)
    - [람다식으로 요소 처리 코드 제공](#람다식으로-요소-처리-코드-제공)
    - [내부 반복자 사용으로 인한 병렬 처리](#내부-반복자-사용으로-인한-병렬-처리)
  - [파이프라인 구성](#파이프라인-구성)
- [스트림 생성, 중간 연산, 최종 연산](#스트림-생성-중간-연산-최종-연산)
  - [스트림 생성](#스트림-생성)
  - [중간연산](#중간연산)
    - [필터링(filter(),distinct())](#필터링filterdistinct)
    - [매핑(map())](#매핑map)
    - [정렬(sorted())](#정렬sorted)
    - [연산 결과 확인(peek(),forEach())](#연산-결과-확인peekforeach)
    - [매칭(match())](#매칭match)
    - [기본 집계(sum(), count(), average(), max(), min())](#기본-집계sum-count-average-max-min)
    - [reduce()](#reduce)
    - [collect()](#collect)
- [Optional<T>](#optionalt)
- [스레드(Thread)](#스레드thread)
    - [익명 객체를 이용한 스레드 생성 및 실행](#익명-객체를-이용한-스레드-생성-및-실행)

### java IO 패키지 
#### InputStream, OutputStream
- 스트림은 단방향 데이터 전송
  - 입출력을 위한 각각의 스트림
- 대상에 따라 종류가 나뉨
  - File
    - FileInputStream / FileOutputStream
      - 바이트 기반 스트림(입출력 단위 1byte)
    - FileReader / FileWriter
      - 문자 기반 스트림(입출력 단위 2byte)
  - Process
    - PipedInputStream / PipedOutputStream
##### FileInputStream
- 터미널에 ```echo code >> codestates.txt``` 입력
  - cdoestates.txt 파일 생성
```java
import java.io.FileInputStream;
  
public class FileInputStreamExample {
  public static void main(String args[])
    {
    try {
      FileInputStream fileInput = new FileInputStream("codestates.txt");
      BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
      // BufferedInputStream 사용시 성능 향상
      // 버퍼는 데이터 입출력의 임시 저장 공간
      int i = 0;
      while ((i = bufferedInput.read()) != -1) { // 끝까지 읽어 들이기
        System.out.print((char)i);
      }
      fileInput.close();
    }
    catch (Exception e) {
       System.out.println(e);
    }
  }
}
```  
##### FileOutputStream
```java
import java.oi.FileOutputStream;

public class FileOutputStreamExample{
  public static void main(String args[]){
    try{
      FileOutputStream fileOutput = new FileOutputStream("codestates.txt");
      String word = "code";

      byte b[] = word.getBytes();
      fileOutput.write(b);
      fileOutpu.close();
    }
    catch (Exception e){
      System.out.println(e);
    }
  }
}
- 위 코드 실행시 같은 디렉토리 내에 code라는 문자열이 입력된 codestates.txt 파일 생성
```
##### FileReader
- 문자 기반 스트림 (입출력 단위 2byte)
  - 문자 데이터를 다룰 때 사용
  - FileReader는 인코딩을 유니코드로 변환
```java
public class BufferedReaderExample {
  public static void main(String args[]) {
    try {
      String fileName = "codestates.txt";
      FileReader file = new FileReader(fileName);
      BufferedReader buffered = new BufferedReader(file);

      int data = 0;

      while((data=buffered.read()) != -1) {
        System.out.print((char)data);
      }
      file.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
```
##### FileWriter
  - FileWriter은 유니코드를 인코딩으로 변환
```java
  public class FileWriterExample{
    pulbic static void main(String args[]){
      try{
        String fileName = "codestates.txt";
        FileWriter writer = new FileWriter(fileName);

        String str = "writtend!";
        writer.write(str);
        writer.close();
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
  }
```
#### File
- File 클래스로 파일과 디렉토리에 접근 가능
```java
import java.io.*;

public class FIleExample{
  public static void main(String args[]) throws IOexception{
    File file = new File("../codestates.txt");
    // 파일 인스턴스를 생성하는 것이 파일을 생성하는 것은 아님
    // 파일을 생성하기 위해서는 파일 인스턴스를 생성할 때 첫 번째 인자에 경로를, 두 번째 인자에 파일명 작성
    // createNewFile() 메서드 호출
    // File file = new File("./", "newCdestates.txt");
    // file.createNewFile();
    System.out.println(file.getPath());
    // 파일의 경로를 문자열의 형태로 반환 / ..\codestates.txt
    System.out.println(file.getParent());
    // 부모 경로에 대한 경로명을 문자열로 반환 / ..
    System.out.println(file.getCanonicalPath());
    // 파일 정규 경로 반환 /C:\Users\LG\IdeaProjects\codestates.txt
    System.out.println(file.canWrite());
    // 파일 쓰기 권한이 있는지 여부 반환 / false
  }
}
```


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

### 스트림 생성, 중간 연산, 최종 연산
#### 스트림 생성
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

#### 중간연산
-![image](https://user-images.githubusercontent.com/102513932/190414920-9dcffaf1-8cd2-49e5-ae06-68a18a3ae834.png)
- 중간 연산은 연산 결과를 스트림으로 반환, 연속해서 여러 번 수행 가능
##### 필터링(filter(),distinct())
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
##### 매핑(map())
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
    - 리턴 타입 ```Stream<Stream>```
  - flatMap()은 스트림을 반환
    -  리턴 타입 Strema
```java
public class ComparatorExample {
	public static void main(String[] args) {
	  Stream<String[]> stringArraysStream = Stream.of(
			new String[]{"hello", "world", "java"},
			new String[]{"code", "states"});
	
	    stringArraysStream.flatMap(Arrays::stream).forEach(System.out::println);
	    }
}
//flatMap 결과 -> hello
// world
// java
// code
// states

// map 결과
//java.util.stream.ReferencePipeline$Head@6acbcfc0
// java.util.stream.ReferencePipeline$Head@5f184fc6
```

##### 정렬(sorted())
- Stream 요소 정렬을 위해 sorted 사용
  - 파라미터로 Comparator을 넘길 수도 있음
  - 인자 없이 호출시 오름차순
  - 내림차순 정렬 시 reverseOrder 이용
```java
List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

        list.stream()
            .sorted()
            .forEach(n -> System.out.println(n));
        System.out.println();

/*
Go, Groovy, Java, Python, Scala, Swift
*/
        list.stream()
            .sorted(Comparator.reverseOrder())
            .forEach(n -> System.out.println(n));

/*
Swift, Scala, Python, Java, Groovy, Go
*/
```
- Comparable 기본 구현 클래스가 아닐 시 , comparing() 메서드를 사용해 비교 가능
```java
import java.lang.*;
import java.util.*;
import java.util.stream.Stream;

class Employee implements Comparable<Employee>{
    int id;
    String name,department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }
}
class ComparatorExample {
    public static void main(String[] args) {
        Stream<Employee> workersStream =
                Stream.of(new Employee(11, "Kim Coding", "Software Engineering"),
                new Employee(5, "Hello World", "Growth Marketing"),
                new Employee(7, "Park Hacker", "Software Engineering"));
        workersStream.sorted(Comparator.comparing(Employee::getId)).forEach(System.out::println);

    }
} 
//id 기준 sort
// [5, Hello World, Growth Marketing]
// [7, Park Hacker, Software Engineering]
// [11, Kim Coding, Software Engineering]
```
##### 연산 결과 확인(peek(),forEach())
- peek(), forEach()는 요소를 하나씩 돌면서 출력
- peek() 는 중간 연산 메서드 
  - 하나의 스트림에 여러 번 사용 가능
  - 주로 연산 중간에 결과를 확인하여 디버깅하고자 할 때 사용
```java
intStream
	.filter(a -> a%2 ==0)
	.peek(n-> System.out.println(n))
	.sum();
``` 
- forEach()는 최종 연산 메서드
  - 파이프라인 마지막에서 요소를 하나씩 연산
  - 스트림의 요소를 소모하므로 한 번만 호출 가능
    - 재호출시 새로운 스트림 생성 요망
  - 출력 혹은 이메일 발송, 스케줄링 등 리턴 값이 작업에서도 많이 사용
```java
intStream
	.filter(a -> a%2 ==0)
	.forEach(n -> System.out.println(n));
```

##### 매칭(match())
- Stream의 요소들이 특정한 조건을 충족하는지 검사
  - 함수형 인터페이스 Predicate를 받아 조건 검사 후 boolean형 반환
- match() 메서드에는 3가지 존재
  - ```allMatch()```
    - 모든 요소들이 매개값으로 주어진 Predicate의 조건을 만족하는지 조사
  - ```anyMatch()```
    - 최소한 한 개의 요소가 매개값으로 주어진 Predicate의 조건을 만족하는지 조사
  - ```noneMatch()```
    - 모든 요소들이 매개값으로 주어진 Predicate의 조건을 만족하지 않는지 조사
```java
import java.util.Arrays;

public class MatchesExample {
    public static void main(String[] args) throws Exception {
        int[] intArr = {2,4,6};
        boolean result = Arrays.stream(intArr).allMatch(a->a%2==0);
        System.out.println("모두 2의 배수인가? " + result);

        result = Arrays.stream(intArr).anyMatch(a->a%3==0);
        System.out.println("하나라도 3의 배수가 있는가? " + result);

        result = Arrays.stream(intArr).noneMatch(a->a%3==0);
        System.out.println("3의 배수가 없는가? " + result);
    }
}

/*
모두 2의 배수인가? true
하나라도 3의 배수가 있는가? true
3의 배수가 없는가? false
*/
```

##### 기본 집계(sum(), count(), average(), max(), min())

```java
import java.util.Arrays;

public class AggregateExample {
    public static void main(String[] args) throws Exception {
        int[] intArr = {1,2,3,4,5};

        long count = Arrays.stream(intArr).count();
        System.out.println("intArr의 전체 요소 개수 " + count);

        long sum = Arrays.stream(intArr).sum();
        System.out.println("intArr의 전체 요소 합 " + sum);

        double avg = Arrays.stream(intArr).average().getAsDouble();
        System.out.println("전체 요소의 평균값 " + avg);

        int max = Arrays.stream(intArr).max().getAsInt();
        System.out.println("최대값 " + max);

        int min = Arrays.stream(intArr).min().getAsInt();
        System.out.println("최소값 " + min);

        int first = Arrays.stream(intArr).findFirst().getAsInt();
        System.out.println("배열의 첫번째 요소 " + first);

    }
}

/*
intArr의 전체 요소 개수 5
intArr의 전체 요소 합 15
전체 요소의 평균값 3.0
최대값 5
최소값 1
배열의 첫번째 요소 1
*/
```

##### reduce()
- 누적하여 하나로 응축하는 방식으로 동작
  - 앞의 두 요소의 연산 결과를 바탕으로 다음 요소와 연산
- 최대 3개의 매개변수를 받을 수 있음
  - Accumulator
    - 각 요소를 계산한 중간 결과를 생성하기 위해 사용 
  - Identity
    - 계산을 수행하기 위한 초기값
  - Combiner
    - 병렬 스트림에서 나누어 계산된 결과를 하나로 합치기 위한 로직
```java
import java.util.Arrays;

public class ReduceExample {
    public static void main(String[] args) throws Exception {
        int[] intArr = {1,2,3,4,5};

        long sum = Arrays.stream(intArr).sum();
        System.out.println("intArr의 전체 요소 합 " + sum);

        int sum1 = Arrays.stream(intArr)
                        .map(el -> el*2)
                        .reduce((a,b) -> a+b)
                        .getAsInt();
        System.out.println("초기값 없는 reduce " + sum1);

        int sum2= Arrays.stream(intArr)
                        .map(el -> el*2)
                        .reduce(10 (a,b) -> a+b);
        System.out.println("초기값 존재하는 reduce " + sum2)
    }
}

/*
intArr의 전체 요소 합 15
초기값 없는 reduce 30
초기값 존재하는 reduce 40
*/
```
- 초기값이 있는 reduce는 초기값과 스트림의 첫 번째 요소로 첫 연산 수행
  - 스티림에 요소가 없을 경우, 디폴트 값 리턴
- 초기값이 없는 reduce는 스트림의 첫 번쨰 요소와 두 번쨰 요소로 첫 연산 수행
  - 스트림에 요소가 없을 경우, NoSuchElementException 발생

##### collect()
- Stream의 요소들을 List나 Set, Map 등 다른 종류의 결과로 수집하고 싶은 경우
  - Collector 타입을 인자로 받음
    - Collector 인터페이스를 구현한 클래스
```java
public class Student {
    public enum Gender {Male, Female};
    private String name;
    private int score;
    private Gender gender;

    public Student(String name, int score, Gender gender) {
        this.name = name;
        this.score = score;
        this.gender = gender;
    }

    public Gender getGender(){
        return gender;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectExample {
    public static void main(String[] args) throws Exception {
        List<Student> totalList = Arrays.asList(
            new Student("김코딩", 10, Student.Gender.Male),
            new Student("김인기", 8, Student.Gender.Male),
            new Student("이자바", 9, Student.Gender.Female),
            new Student("최민선", 10, Student.Gender.Female)
        );

        List<Student> maleList = totalList.stream()
           .filter(s -> s.getGender() == Student.Gender.Male)
           .collect(Collectors.toList());

        maleList.stream().forEach(n->System.out.println(n.getName()));

        Set<Student> femaleSet = totalList.stream()
           .filter(s -> s.getGender() == Student.Gender.Female)
           .collect(Collectors.toCollection(HashSet :: new));

        femaleSet.stream().forEach(n->System.out.println(n.getName()));
    }
}
// 출력 결과
// 김코딩
// 김인기
// 이자바
// 최민선
```

### Optional<T>
- NullPointerException 방지
  -  null 값으로 인해 에러가 발생하는 현상을 객체 차원에서 효율적으로 방지
  -  연산 결과를 optional에 담아 반환시, npe 발생하지 않음
- Optional 클래스는 모든 타입의 객체를 담을 수 있는 래퍼(Wrapper) 클래스
```java
public final class Optional<T>{
  private final T value; //T 타입의 참조변수
}
```
- Optional 객체를 생성하려면 ```of()``` 혹은 ```ofNullable()``` 사용
  - 참조변수의 값이 null일 가능성이 있다면, ofNullable() 사용
```java
Optional<String> opt1 = Optional.ofNullable(null);
Optional<String> opt2 = Optional.ofNullable("123");
System.out.println(opt1.isPresent()); //Optional 객체의 값이 null인지 여부를 리턴합니다.
System.out.println(opt2.isPresent());
//출력값
// false
// true
```
- Optional 타입의 참조변수 기본값으로 초기화 시 ```empty()``` 사용
```java
Optional<String> opt3 = Optional.<String>empty();
```
- Optional 객체에 저장된 값을 가져오려면 ```get()``` 사용
- 참조변수의 값이 null일 가능성 존재시 orElse()메서드 사용으로 디폴트 값 지정
```java
Optional<String> optString = Optional.of("codestates");
System.out.println(optString);
System.out.println(optString.get());

String nullName = null;
String name = Optional.ofNullable(nullName).orElse("kimcoding");
System.out.println(name);
//출력값
// Optional[codestates]
// codestates
// kimcoding
```
- Optional 객체는 스트림과 유사하게 여러 메서드를 연결하여 작성 가능 (메서드 체이닝)

### 스레드(Thread)
- 프로세스
  - 실행중인 어플리케이션
  - 데이터, 컴퓨터 자원, 스레드로 구성
- 스레드
  - 데이터와 애플리케이션이 확보한 자원을 활용하여 소스 코드 실행
  - 프로세스 내에서 실행되는 소스 코드의 실행 흐름
  - 싱글 스레드 프로세스
    - 단 하나의 스레드를 갖는 프로세스
  - 멀티 스레드 프로세스
    - 여러 개의 스레드를 갖는 프로세스
    - 동시 작업이 가능
    - 여러 코드를 각 스레드에 분배하여 동시 실행
    - ex) 메신저 어플리케이션
      - 메시지를 주고 받으며 동시에 파일 업로드
    - 다수의 클라이언트 요청을 처리하는 서버 개발시 사용
- #### 메인 스레드
  - ```main``` 메서드
    - 자바 어플리케이션 실행시 가장 먼저 실행
    - 메인 스레드가 ```main``` 메서드 실행시킴
    - 순차적 실행, 코드의 끝이나 ```return```문 만나면 종료
  -  자바 어플리케이션 코드가 싱글 스레드로 작성 시
     -  메인 스레드만 갖는 싱글 스레드 프로세스
  - 메인 스레드에서 또 다른 스레드 생성 및 실행 시
    - 애플리케이션은 멀티 스레드로 동작
  - ![image](https://user-images.githubusercontent.com/102513932/190569227-2e0c060c-6cbf-44ca-9e4a-3f43d2f2b9c6.png)
- #### 멀티 스레드
  - 하나의 포르세스가 여러 개의 스레드를 가질 시
    - 멀티 스레드 프로세스
    - 여러 스레드가 동시에 작업 수행 가능
    - 멀티 스레딩
  - ![image](https://user-images.githubusercontent.com/102513932/190569361-19351e62-eeb8-4c88-9b37-5f818447b1c4.png)
- #### 작업 스레드의 생성과 실행
  - 메인 스레드 외의 별도 작업 스레드 생성 및 실행
    - 작업 스레드가 수행할 코드 작성 및 생성, 실행
    - 스레드가 수행할 코드 클래스 내부에 작성
    - ```run()```이라는 메서드 내 스레드가 처리할 작업을 작성하도록 규정
  - ```run```
    - Runnable 인터페이스와 Thread 클래스에 정의
  - 작업 스레드를 생성하고 실행하는 방법
    - 1. Runnable 인터페이스를 구현한 객체에서 run()을 구현하여 스레드를 생성하고 실행
    - 2. Thread 클래스를 상속 받은 하위 클래스에서 run()을 구현하여 스레드를 생성하고 실행
  - 1. Runnable 인터페이스 구현한 객체에서 생성 및 실행
    - Runnable에는 run()이 정의, 반드시 구현해야 함.
    - run() 생성 및 구현
  ```java
  public class ThreadExample1{
    public static void main(String[] args){

        }
    }
    
    //Runnalbe 인터페이스를 구현하는 클래스
    class ThreadTask1 implements Runnable{
        //run() 메서드 바디에 스레드가 수행할 작업 내용 작성
        public void run(){
            for(int i=0; i<100; i++){
                System.out.print("#");
            }
        }
    }
  ```
    - 스레드 생성
```java
  public class ThreadExample1{
    public static void main(String[] args){
        Runnable task1 = new ThreadTask1();
        Thread thread1 = new Thread(task1);

        thread1.start();
        //반복문 추가
        for(int i=0; i<100; i++){
            System.out.print("@");
        }
        }
    }
    
    //Runnalbe 인터페이스를 구현하는 클래스
    class ThreadTask1 implements Runnable{
        //run() 메서드 바디에 스레드가 수행할 작업 내용 작성
        public void run(){
            for(int i=0; i<100; i++){
                System.out.print("#");
            }
        }
    }
    //출력 결과 (매번 달라짐)
    //@@@@@@@@@@@######@@@@@############################
    //@#########@@@@@@@@@@@@@@@@############@@@@@@@@@@@@
    //@@@@@@@@@@@@@@@@@@@@@@@@##@@@@@@@@@@@@@@@@@@@@@@@@
    //@@@@@@@###########################################
  ```
    - ```@```는 main 메서드 반복문에서 출력한 문자
      - 메인 스레드의 반복문 코드 실행에 의해 출력
    - ```#```은 run 메서드의 반복문에서 출력한 문제
      - #은 작업 스레드의 반복문 코드 실행에 의해 출력
    - @과 #은 섞여 있음
      - 메인 스레드와 작업 스레드가 동시에 병렬로 실행됨
  - 2. Thread 클래스를 상속 받은 클래스에서 생성 및 실행
    - 하위 클래스 생성 및 메서드 오버라이딩
  ```java
  public class ThreadExample2{
    public static void main(String[] args){
        // Thread 클래스를 상속받은 클래스를 인스턴스화하여 스레드 생성
        ThreadTask2 thread2 = new ThreadTask2();
    }
  }
  
  //Thread 클래스를 상속받는 클래스 작성
  class ThreadTask2 extends Thread{
    //run() 메서드 바디에 스레드가 수행할 작업 내용 작성
    public void run(){
        for(int i=0; i<100; i++){
            System.out.print("#");
        }
    }
  }
  ```
  - 스레드 생성 및 실행
  ```java
  public class ThreadExample2{
    public static void main(String[] args){
        // Thread 클래스를 상속받은 클래스를 인스턴스화하여 스레드 생성
        ThreadTask2 thread2 = new ThreadTask2();
        // 작업 스레드 실행으로 run() 내부 코드 처리
        thread2.start();

        for(int i=0; i< 100; i++){
            System.out.print("@");
        }
    }
  }
  
  //Thread 클래스를 상속받는 클래스 작성
  class ThreadTask2 extends Thread{
    //run() 메서드 바디에 스레드가 수행할 작업 내용 작성
    public void run(){
        for(int i=0; i<100; i++){
            System.out.print("#");
        }
    }
  }
  // 결과 1번과 유사.
  ```
  ##### 익명 객체를 이용한 스레드 생성 및 실행
  ```java
  // 익명 Runnable 구현 객체를 활용하여 스레드 생성
  public class ThreadExample1{
    public static void main(String[] args){
      Thread thread1 = new Thread(new Runnable(){
        public void run(){
          for(int i=0; i<100; i++){
            System.out.print("#");
          }
      }
  });
  thread1.start();
  for(int i=0; i<100; i++){
    System.out.print("@");
        }
    }
  }
  ```
  ```java
  public class ThreadExample2 {
    public static void main(String[] args) {

    // 익명 Thread 하위 객체를 활용한 스레드 생성
      Thread thread2 = new Thread() {
          public void run() {
          for (int i = 0; i < 100; i++) {
                  System.out.print("#");
              }
          }
      };

      thread2.start();
      for (int i = 0; i < 100; i++) {
          System.out.print("@");
          }
      }
    }
  ```
- #### 스레드의 이름
  - 메인스레드
    - "main"
  - 추가 생성 스레드
    - "Thread-n"
  - ##### 스레드의 이름 조회하기
    - ```스레드의_참조값.getName()```
  ```java
  public class ThreadExample3 {
    public static void main(String[] args) {

        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Get Thread Name");
            }
        });

        thread3.start();

        System.out.println("thread3.getName() = " + thread3.getName());
    }
  } 
  // 출력 결과 
  Get Thread Name
  thread3.getName() = Thread-0
  ```
  - ##### 스레드의 이름 설정하기
    - ```스레드의_참조값.setName()```
  ```java
  public class ThreadExample4 {
    public static void main(String[] args) {

      Thread thread4 = new Thread(new Runnable() {
          public void run() {
              System.out.println("Set And Get Thread Name");
          }
      });

      thread4.start();

      System.out.println("thread4.getName() = " + thread4.getName());

      thread4.setName("Code States");

      System.out.println("thread4.getName() = " + thread4.getName());
    }
  }
  // 출력 결과
  // Set And Get Thread Name
  // thread4.getName() = Thread-0
  // thread4.getName() = Code States
  ``` 
  - ##### 스레드 인스턴스의 주소값 얻기
    - 이름을 조회하고 설정하는 위 두 메서드는 Thread 클래스로부터 인스턴스화 된 인스턴스 메서드
      - 호출 시 스레드 객체 참조 필요
      - 실행 중인 스레드의 주소값 사용시 정적 메서드인 ```currentThread()``` 사용
  ```java
    public class ThreadExample1{
      public static void main(String[] args){
        Thread thread1 = new Thread(new Runnable(){
          public void run() {
            System.out.println(Thread.currentThread().getName());
          }
        });
        thread1.start();
        System.out.println(Thread.currentThread().getName());
      }
    }
    // 출력 결과
    // main
    // Thread-0
  ``` 
- #### 스레드의 동기화
  - 멀티 스레드 프로세스의 경우, 두 스레드가 동일한 데이터를 공유하게 되어 문제 발생 가능성 존재
  - 예제
  ```java
  public class ThreadExample3 {
    public static void main(String[] args) {
      Runnable threadTask3 = new ThreadTask3();
      Thread thread3_1 = new Thread(threadTask3);
      Thread thread3_2 = new Thread(threadTask3);

      thread3_1.setName("김코딩");
      thread3_2.setName("박자바");

      thread3_1.start();
      thread3_2.start();
    }
  }

  class Account {

    // 잔액을 나타내는 변수
    private int balance = 1000;

    public int getBalance() {
      return balance;
    }
		
    // 인출 성공 시 true, 실패 시 false 반환
    public boolean withdraw(int money) {

      // 인출 가능 여부 판단 : 잔액이 인출하고자 하는 금액보다 같거나 많아야 함
      if (balance >= money) {

        // if문의 실행부에 진입하자마자 해당 스레드를 일시 정지 시키고, 
        // 다른 스레드에게 제어권을 강제로 넘김
        // 일부러 문제 상황을 발생시키기 위해 추가한 코드
        try { Thread.sleep(1000); } catch (Exception error) {}

        // 잔액에서 인출금을 깎아 새로운 잔액을 기록
        balance -= money;

        return true;
        }
      return false;
    }
  }

  class ThreadTask3 implements Runnable {
    Account account = new Account();

    public void run() {
      while (account.getBalance() > 0) {

        // 100 ~ 300원의 인출금을 랜덤으로 정함 
        int money = (int)(Math.random() * 3 + 1) * 100;

        // withdraw를 실행시키는 동시에 인출 성공 여부를 변수에 할당 
        boolean denied = !account.withdraw(money);

        // 인출 결과 확인
        // 만약, withraw가 false를 리턴하였다면, 즉 인출에 실패했다면,
        // 해당 내역에 -> DENIED를 출력합니다. 
        System.out.println(String.format("Withdraw %d₩ By %s. Balance : %d %s",
            money, Thread.currentThread().getName(), account.getBalance(), denied ? "-> DENIED" : "")
          );
        }
    }
  }
  //출력 결과
<!-- Withdraw 100₩ By 김코딩. Balance : 600  -->
<!-- Withdraw 300₩ By 박자바. Balance : 600  -->
<!-- Withdraw 200₩ By 김코딩. Balance : 400  -->
<!-- Withdraw 200₩ By 박자바. Balance : 200  -->
<!-- Withdraw 200₩ By 김코딩. Balance : -100  --> 
<!-- Withdraw 100₩ By 박자바. Balance : -100  -->
  ```
  - 위 코드 실행 시 두 개의 작업 스레드 생성
  - 두 개의 작업 스레드는 Account 객체 공유
