객체지향 프로그래밍 심화(Object Oriented Programming Advanced)
=====================================
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
  - 반드시 첫 줄에 와야 함
  - object클래스를 제외한 자식 클래스의 모든 생성자의 첫 줄
    - ```this()``` 또는 ```super()```가 선언되어야 함
    - ```super()```가 없는 경우 컴파일러가 자동으로 ```super()``` 삽입
    - 상위 클래스에 기본생성자가 없으면 오류 발생
      - 기본 생성자 생성의 습관화 요망 
  
