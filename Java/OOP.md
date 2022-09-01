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
- 특정 작업을 수행하는 일련의 명령문들의 집합
  - 
    
