## 목차
- [객체지향 프로그래밍 심화(Object Oriented Programming Advanced)]()
  * [상속](#상속)
  * [포함 관계](#포함-관계)
  * [메서드 오버라이딩(Method Overriding)](#메서드-오버라이딩method-overriding)
  * [super 키워드와 super()](#super-키워드와-super)
  * [Object 클래스](#object-클래스)
  * [캡슐화](#캡슐화)
  * [패키지](#패키지)
  * [접근 제어자](#접근-제어자)
  * [getter과 setter 메서드](#getter과-setter-메서드)
  * [다향성](#다향성)
  * [instanceof 연산자](#instanceof-연산자)
  * [다향성 활용 예제](#다향성-활용-예제)
  * [추상화](#추상화)
  * [final 키워드](#final-키워드)
  * [인터페이스(Interface)](#인터페이스interface)

객체지향 프로그래밍 심화(Object Oriented Programming Advanced)
=====================================
상속
----------------------------
- [상속 심화_해시넷](http://wiki.hash.kr/index.php/%EC%83%81%EC%86%8D_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D))
- [상속 심화_위키백과](https://ko.wikipedia.org/wiki/%EC%83%81%EC%86%8D_(%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D))
- [상속 심화_TCP 스쿨](http://www.tcpschool.com/java/java_inheritance_concept)
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

  
Object 클래스
------------------------------------------
- 자바의 모든 클래스는 Object 클래스로부터 확장
  - 컴파일러는 아무 상속을 받지 않는 클래스에 자동으로 ```extends object``` 추가
```java
class ParentEx {  //  컴파일러가 "extends Object" 자동 추가 
}
class ChildEx extends ParentEx {
}
```
- 상위 클래스 ParentEx는 상속하지 않기에 자동 삽입
- Object 클래스는 자바 클래스 상속계층도에 가장 위에 위치
- Object 클래스의 멤버들을 자동으로 상속받아 사용 가능
- Object 클래스의 대표적인 메서드
  - ![image](https://user-images.githubusercontent.com/102513932/188542401-29116e06-2634-4aaf-b07b-a188ccf58612.png)
  - 오버라이드를 통해 재정의 후 사용해야 한다.
    - alt + insert 이용
    - ![image](https://user-images.githubusercontent.com/102513932/189785364-9c0384ae-4971-4d2e-aee7-cbfc182ef34d.png)


캡슐화
---------------------------------
[캡슐화_해시넷](http://wiki.hash.kr/index.php/%EC%BA%A1%EC%8A%90%ED%99%94)
[캡슐화_위키백과](https://ko.wikipedia.org/wiki/%EC%BA%A1%EC%8A%90%ED%99%94)
[]
![image](https://user-images.githubusercontent.com/102513932/188549225-8cff874c-7c0c-4ca5-97e4-d02ca4afa687.png)
- 특정 객체 안에 관련된 속성과 기능을 캡슐로 만듬
  - 데이터를 외부로부터 보호
  - 내부적으로만 사용되는 데이터에 대한 외부 노출 방지
- 정보 은닉에 가장 큰 장점
  - 객체의 속성과 기능 변경 방지
  - 독립성 확보
- 코드 유지보수와 확장
  - 오류의 범위 최소화

패키지
-------------------
- 특정한 목적을 공유하는 클래스와 인터페이스의 묶음
  - 클래스들을 그룹 단위로 묶어 효과적으로 관리
  - 클래스의 충돌 방지
    - 같은 이름의 클래스라도 다른 패키지 소속이면 충돌 발생X
- 물리적인 하나의 디렉토리
  - 계층 구조를 가짐
    - 계층 구조 간 구분은 점(.)으로 표시
- 패키지가 있는 경우 첫 번째 줄에 ```package 패키지명```표시
```java
// 패키지를 생성했을 때
package practicepack.test; // 패키지 구문 포함. 패키지가 없다면 구문 필요없음
// 패키지 선언이 없으면 이름 없는 패키지에 속하게 됨
public class PackageEx {

}
```
- 대표적인 패키지
  - java.lang
    - 자바의 기본 클래스들을 모아놓음
    - ex) java.lang.String
    - import하지 않고도 사용할 수 있음.
    - 기본형 타입을 객체로 변환시킬 때 사용하는 Wrapper 클래스 존재
      - ex) Integer, Long, Float
    - Object, String, StringBuffer, StringBuilder, System, Math, Thread 관련 중요 클래스 모두 java.lang 패키지
    ```java
    public class WrapperExam{
      public static void main(String[] args){
        int i= 5;
        Integer i2 = new Integer(5);
        Integer i3 = 5; //오토박싱
        int i4 = i2.intValue();
        int i5 = i2; //오토 언박싱
      }
    }
    ```
    - 오토박싱
      - Integer i3 = 5; 
        - 숫자 5는 원래 기본형이지만 자동으로 Integer형태로 전환됨.
    - 오토 언박싱
      - int i5 = i2; 
        - Ingeter 객체타입의 값을 기본형 int로 자동 변환하여 값 할당
    - 오토박싱, 오토 언박싱 모두 JAVA 5부터 지원, 내부적으로 Wrapper 클래스 사용
  - java.util
    - 확장 클래스를 묶어 놓음
    - 자료구조 클래스 다수 존재, 이를 컬렉션 프레임워크라 지칭
      - List, Set, Collection, Map
  - java.io / java.nio
    - 입출력 관련 클래스
- Generic
  - 제네릭 사용으로 선언시 가상의 타입으로 선언
  - 사용시에는 구체적 타입 설정으로 다양한 타입의 클래스 이용 가능
  - 대표적으로 컬렉션 프레임워크와 관련된 클래스
- Import 문
  - 다른 패키지 내의 클래스를 사용하기 위해 사용
```java
import 패키지명.클래스명; 또는 import 패키지명.*;
```
  - 같은 패키지에서 여러 클래스 사용시
    - ```import 패키지명.*```
  - 컴파일시 처리 
    - 프로그램 성능에 영향 X

접근 제어자
--------------------------
- 제어자
  - 클래스, 필드, 메서드, 생성자 등에 부가적 의미 부여
  - 마치 형용사의 역할
  - ![image](https://user-images.githubusercontent.com/102513932/188550097-92f1ae77-df36-4bb1-a124-a479e1b384ca.png)
  - 하나의 대상에 대해 여러 제어자 사용 가능
  - 접근 제어자는 한 번만 사용 가능
- 접근 제어자
  - 클래스 외부로의 불필요한 데이터 노출 방지
  - 외부로부터 데이터 임의 변경 방지
  - ![image](https://user-images.githubusercontent.com/102513932/188550195-7b7ff59b-29f1-4966-addd-04b02cd6e2ed.png)
  - public > protected > defaulte > private
    - default -> 아무 접근제어를 붙이지 않는 경우
  - ![image](https://user-images.githubusercontent.com/102513932/188550327-29bc3fb6-9da6-48c8-9154-b8189287c424.png)

getter과 setter 메서드
----------------------------------
- 캡슐화의 목적을 달성하면서도 데이터의 변경이 필요한 경우
  - ex) private 객체의 변수의 데이터 값을 추가하거나 수정하고 싶을 때
  - getter 메서드
    - 설정한 변수 값을 읽어 오는데 사용
```java
int id = w.getId();
public int getId(){
    return id;
}
``` 
  - setter 메서드
    - 데이터 값을 변경 가능하게 해줌
```java 
public void setId(int id){ 
    this.id =id;
}
```

다향성
-------------------------
[다향성_해시넷](http://wiki.hash.kr/index.php/%EB%8B%A4%ED%98%95%EC%84%B1) <br>
[다향성_위키백과](https://ko.wikipedia.org/wiki/%EB%8B%A4%ED%98%95%EC%84%B1_(%EC%BB%B4%ED%93%A8%ED%84%B0_%EA%B3%BC%ED%95%99))
- 하나의 객체가 여러가지 형태를 가질 수 있는 성질
  - 한 타입의 참조변수를 통해 여러 타입의 객체를 참조
  - 상위 클래스 타입의 참조변수를 통해 하위 클래스의 객체 참조
```java
//참조변수의 다형성 예시

class Friend {
    public void friendInfo() {
        System.out.println("나는 당신의 친구입니다.");
    }

}

class GirlFriend extends Friend {
    
    public void friendInfo() {
        System.out.println("나는 당신의 여자친구입니다.");
    }

     public void friend(){
        System.out.println("친구.");
    }
}

public class FriendTest {

    public static void main(String[] args) {
        Friend friend = new Friend(); // 객체 타입과 참조변수 타입의 일치
        Friend girlfriend = new GirlFriend(); // 객체 타입과 참조변수 타입의 불일치
        //부모 타입인 friend로 자식타입인 girlfriend를 가르킬 수 있다.
        // 사실 이것도 형변환이다.
        friend.friendInfo();
        girlfriend.friendInfo();
        girlfriend.friend(); //오류 발생
        // 단, 이때 Friend 클래스의 friend 메소드는 사용할 수 없다.
        // 즉, 부모 타입으로 자식 객체를 참고하게 되면, 부모가 갖고 있는 메소드만 사용할 수 있다.
        // 단, 이 때 부모가 갖고 있는 메소드 중 자식 객체에서 오버라이딩 된 메소드가 있다면 오버라이딩 된 값으로 출력된다.

        GirlFriend girlfriend2 = girlfriend; // 오류 발생
        // girlfriend는 현재 Friend 타입이기 때문에 GirlFriend가 가르킬 수 없음.
        GirlFriend girlfriend2 = (GirlFriend)girlfriend;
        // 따라서 형 변환 필요 (다운캐스팅)
        // 이 형변환이 가능한 이유는 ,girlfriend 변수가 원래 GirlFriend 타입이지만 실제로는 Friend 타입으로 실행되었기 때문.
        // 즉, 다운캐스팅이 가능하기 위해서는 업캐스팅이 선행되어야 함.
        girlfriend2.friend();
        // 형변환시 자식 객체 메서드 사용 가능
     }
}
// 출력값
나는 당신의 친구입니다.
나는 당신의 여자친구입니다.
친구
```
- 상위 클래스의 타입으로 하위 클래스를 참조하는 것은 가능
  - 반대는 불가능
    - 참조하고 있는 인스턴스에 실제로 구현된 기능이 없어 사용이 불가능
    - 기능을 줄이는 것은 가능하나, 구현되어 있지 않은 기능을 사용하는 것은 불가능
- 타입 변환의 세 가지 조건
  - 서로 상속관계에 있는 상위 클래스 - 하위 클래스만 타입 변환 가능
  - 하위 클래스 타입 -> 상위 클래스 타입으로 변환 (업캐스팅)
    - 형변환 연산자(괄호) 생략 가능
  - 상위 클래스 타입 -> 하위 클래스 타입으로 변환 (다운 캐스팅)
    - 형변환 연산자(괄호) 필히 명시


instanceof 연산자
--------------------
- 캐스팅의 가능 여부를 boolean 타입으로 확인 가능
  - 객체를 어떤 생성자로 만들었는가?
  - 클래스 사이에 상속관계가 존재하는가?
- ```참조_변수 instanceof 타입``` ```객체 instanceof 클래스```
  - 리턴 값이 true가 나오면 타입 변환 가능
  - false가 나오면 불가능
```java
public class InstanceOfExample {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal instanceof Object); //true
        System.out.println(animal instanceof Animal); //true
        System.out.println(animal instanceof Bat); //false
        // 상위 클래스 객체가 하위 클래스로 다운 캐스팅 불가능.
        Animal cat = new Cat();
        System.out.println(cat instanceof Object); //true
        System.out.println(cat instanceof Animal); //true
        System.out.println(cat instanceof Cat); //true
        System.out.println(cat instanceof Bat); //false
        // 상속관계가 아닌 두 객체
        
        class Bat extends Animal{};
        class Cat extends Animal{};
    }
}

class Animal {};
class Bat extends Animal{};
class Cat extends Animal{};
```

다향성 활용 예제
---------------------------
```java
public class PolyMorphismEx{
    public static void main(String[] args){
        Customer customer = new Customer();
        customer.buyCoffee(new Americano());
        customer.buyCoffee(new CaffeLatte());

        System.out.println("현재 잔액은" + customer.money + " 원 입니다.");
    }
}
class Coffee{
    int price;
    public Coffee(int price){
        this.price = price;
    }
}

class Americano extends Coffee{
    public Americano(){
    super(4000); //상위 클래스 Coffee의 생성자 호출
    }
    
    public String toString(){
        return "아메리카노";
        } //toStirng 오버라이드 
}

class CaffeLatte extends Coffee{
    public CaffeLatte(){
        super(5000);
    }
    public String toString() {
        return "카페라떼";
        }
}

class Customer{
    int money = 50000;

    // void buyCoffee(Americano americano){
    //     money = money - americano.price;
    // }
    // void buyCoffee(CaffeLatte caffeLatte){
    //     money = money - caffeLatte.price;
    // }
    // 커피의 종류가 한 두개가 아니라 수십 수백가지가 된다면 매번 메서드를 추가해 줘야함 -> 이를 객체의 다향성을 이용해 해결
    void buyCoffee(Coffee coffee){
        if(money<coffee.price){
            System.out.println("잔액이 부족합니다");
            return;
        }
        money = money - coffee.price;
        System.out.println(coffee + "를 구입했습니다.");
    } // 개별적인 커피의 타입이 아닌, 상위클래스인 Coffee의 타입을 매개변수로 전달해 다향성 증가.
}
// 출력값
아메리카노를 구입했습니다.
카페라떼를 구입했습니다.
현재 잔액은 41000원 입니다.
```

추상화
----------------------------
- [추상화_해시넷](http://wiki.hash.kr/index.php/%EC%B6%94%EC%83%81%ED%99%94)
- [추상화_위키백과](https://ko.wikipedia.org/wiki/%EC%B6%94%EC%83%81%ED%99%94_(%EC%BB%B4%ED%93%A8%ED%84%B0_%EA%B3%BC%ED%95%99))
- 기존 클래스들의 공통적인 요소들을 뽑아 상위/하위 클래스를 만들어 내는 것
- ![image](https://user-images.githubusercontent.com/102513932/188605051-8fa8f37a-b346-4ad8-b719-66ce05826329.png)
  - 자동차와 오토바이의 공통 분모를 모아 이동수단이라는 클래스에 담음
  - 혹은 이동수단이 갖는 공통적인 특징을 자동차와 오토바이에 내려줌
- abstract 제어자
  - 메서드 앞에 붙은 경우
    - 추상 메서드
  - 클래스 앞에 붙은 경우
    - 추상 클래스
  - 클래스에 추상 메서드가 포함되어 있는 경우
    - 클래스는 자동으로 추상 클래스가 된다.
```java
abstract class AbstractExample{//추상 메서드가 최소 하나 이상 포함되어 있는 추상(미완성) 클래스
    abstract void start(); // 메서드 바디가 없는 추상(미완성)메서드
}
AbstractExample abstractExample = new AbstractExample(); //에러 발생
```
  - 추상 클래스는 미완성 설계도
    - 기반으로 객체 생성 불가능
- 상속 관계에 있어 새로운 클래스 작성시 유용
  - 상위 클래스에는 선언부 작성
  - 구체적인 내용은 하위 클래스에서 구현
    - 오버라이딩 이용
  - 더욱 유연한 대응 가능
    - 각각 상황에 맞는 메서드 구현 가능
- 추상 클래스는 추상화 구현시 핵심적 역할
  - 상속계층도 상층부 위치할시 추상화 정도 높음
    - 상층부에 가까울수록 더욱 공통적인 속성과 기능 정의
  - 하층부로 내려갈수록 구체화
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

final 키워드
---------------------------
![image](https://user-images.githubusercontent.com/102513932/188627585-a0c6082d-f617-44c5-8fe3-9f64acf60cd2.png)
- 공통적으로 변경이 불가능하고 확장할 수 없음
```java
final class FinalEx{ // 확장/ 상속 불가능한 클래스
    final int x = 1; //변경되지 않는 상수

    final int getNum(){//오버라이딩 불가한 메서드
        final int localVar = x; //상수
        return x;
    }
}
```
- final제어자 추가시
  - 더이상 변경이 불가하거나 확장되지 않음

인터페이스(Interface)
----------------
- 추상 메서드와 상수만을 멤버로 가질 수 있음
  - 추상 클래스에 비해 추상화 정도가 더 높다
- 인터페이스 기본 구조
  - 내부 모든 필드가 ```public static final```로 정의
  - ```static```과 ```default``` 메서드 이외 모든 메서드가 ```public abstract```로 정의됨
  - 모든 인터페이스의 필드와 메서드에는 위 요소 내포
    - 명시하지 않아도 생략 가능
```java
public interface InterfaceEx{
    public static final int rock = 1; //인터페이스 인스턴스 변수 정의
    final int scissors = 2; //public static 생략
    static int paper = 3; //public & final 생략

    public abstract String getPlayingNum();
}
```

- 인터페이스 구현
  - 그 자체로 인스턴스 생성 불가능
  - 메서드 바디 정의 클래스 따로 작성
```java
class 클래스명 implements 인터페이스명 {
    ... // 인터페이스에 정의된 모든 추상메서드 구현
}
```
  - 특정 인터페이스를 구현한 클래스
    - 해당 인터페이스에 정의된 모든 추상메서드를 구현해야함
    - 클래스에게 인터페이스의 추상 메서드 구현을 강제시킴
    - 인터페이스의 모든 추상 메서드들을 해당 클래스 내에서 오버라이딩하여 바디를 완성한다.
  - 인터페이스의 다중 구현
    - 상속에서 다중 상속은 허용되지 않음
      - 부모 클래스에 동일한 이름의 필드 또한 메서드 존재시 충돌 발생하기 떄문
    - 인터페이스는 다중적 구현 가능
      - 하나의 클래스가 여러 개의 인터페이스 구현 가능
      - 미완성된 멤버를 갖기 때문에 충볼 발생 가능성 X, 안전하게 다중 구현 가능
    - 인터페이스는 인터페이스로부터만 상속 가능
      - 클래스 와 달리 Object와 같은 최고 조상 존재 X
    - 특정 클래스는 다른 클래스로부터 상속을 받으면서 동시에 인터페이스를 구현할 수 있음.
```java
class ExampleClass implements ExampleInterface1,ExampleInterface2,ExampleInterface3{
    ... 생략 ...
}
```

- 인터페이스의 장점
  - 역할과 기능을 분리
  - 복잡한 구현의 내용 또는 변경과 상관없이 해당 기능 사용 가능
  - ![image](https://user-images.githubusercontent.com/102513932/188671898-12ff31f2-4486-41c4-b37b-5c1674f4aa89.png)
```java
interface Cover{ //인터페이스 정의
    public abstract void call();
}

public class Interface4{
    public static void main(String[] args){
        User2 user2 = new User2();
        user2.callProvider(new Provider2());
    }
}

class User{
    public void callProvider(Cover cover){ //매개변수의 다향성 활용
        cover.call();
    }
}

class Provider implements Cover{
    public void call() {
        System.out.println("무야호~");
    }
}

class Provider2 implements Cover {
    public void call() {
        System.out.println("야호~");
    }
}

//출력값
야호~
```
- ```Provider```클래스의 내용 변경 또는 교체가 발생하더라도 ```User``` 클래스는 더이상 코드를 변경해주지 않고 같은 결과를 출력할 수 있음.
  - User클래스에서 변경되어야 하는 코드가 늘어날 수록 효율은 증가
- 선언과 구현을 분리시켜 개발시간을 단축
- 독립적 프로그램을 통해 종속적 영향을 최소화 할 수 있음.
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
- JAVA 8이후 default/static 메서드 인터페이스 포함 가능
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