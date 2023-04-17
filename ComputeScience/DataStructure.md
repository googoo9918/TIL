# 자료구조
## 자료구조의 분류, 알고리즘, 추상자료형
### 자료구조의 분류
- 단순 구조
  - 정수, 실수, 문자 및 문자열
- 선형 구조
  - 데이터 연속적 연결
  - 리스트, 스텍, 큐, 덱
- 비선형 구조
  - 자료의 순서가 불규칙하고 연결 관계가 복잡
  - 그래프, 트리, 우선순위 큐, 맵
- 파일 구조

### 추상 자료형과 c++
- 절차지향(x) 객체지향 (o) 프로그래밍
- 객체
  - 자신 고유의 속성을 가지는 모든 대상
- 클래스
  - 객체가 가지는 속성(필드)과 동작(메서드)으로 정의
- c++에서는 클래스를 통해 추상 자료형을 구현

## 알고리즘의 성능분석, 자료의 표현
- 빅오 표기법(Big O Notation)
  - f(n) = O(g(n))
  - 효율성을 시간의 **상한선** 기준으로 표기
    - 최악의 경우에도 g(n) 이상
  - O(1) < O(logn) < O(n) < O(nlogn) < O(n^2) < O(2^n) < O(n^3)
- 빅오 계산법
  - 정의: 함수 f와 g에 대하여 다음 조건을 참으로 만드는 상수 C와 k가 존재할 때, f는 O(g)라 한다.
  - 모든 x>k에 대하여 f(x)<= C*g(x)이다.
  - ex) f(x) = x^2 + x +1이다. f(x)는 O(x^2)를 성립하게 하는 C와 k를 찾고 이를 증명하라
    - C=3일 때, y=f(x)와 y=3g(x)의 그래프는 다음과 같다.
    - 따라서 k=1일때 (x>1 일때), f(x) < 3g(x) 이다.
    - 따라서 Big-O 표기법 정의에 의해, f(x)는 O(x^2)이다. 
- 빅오메가 표기법
  - ![image](https://user-images.githubusercontent.com/102513932/232308237-08d2e69d-517e-41a3-9439-4f0bf2633228.png)
  - 하한선 기준으로 표기
    - 아무리 빨라도 g(n) 이하임
- 빅세타 표기법
  - ![image](https://user-images.githubusercontent.com/102513932/232308254-5e7b45c2-4594-4c9f-a72b-d11072e8e016.png)
  - 상한 및 하한선 기준으로 표기
    - g(n)의 상한과 하한에 포함됨

## 이름공간, 입출력 형식, 함수 오버로딩, 매개변수, 인라인 함수
### 이름공간
- 이름 공간은 어떤 정의된 객체에 대해 어디 소속인지 지정해주는 것과 동일함
- 존재하는 이름 공간이 다르면 동일한 이름의 함수 및 변수를 선언하는 것이 가능
  - 이름의 충돌을 막을 목적으로 존재하는 것이 이름공간임
```cpp
namespace header1{
    int func(){
        foo(); //알아서 header1::foo()가 실행된다.
        header2::foo(); //header2::foo()가 실행된다.
    }
}
// ::는 범위지정 연산자이다.
// 특정 이름공간에 소속되지 않은 경우(in main)
// 명시적으로 이름공간을 지정해야 한다.

using namespace header1;

int main(){
    header2::foo(); //header2에 있는 함수 호출
    foo(); // header1에 있는 함수 호출
}

// 만약 선언된 이름공간의 이름이 동일하다면
// 이 둘은 동일한 이동공간으로 간주함

void BestComImpl::SimpleFunc(void){
    std::cout << "BestCom이 정의한 함수" << std::endl;
    PrettyFunc(); //동일 이름공간
    ProgComImpl::SimpleFunc(); //다른 이름공간
}

void BestComImpl::PrettyFunc(void){
    std::cout << "So Pretty!!" << std::endl;
}
// 이름공간을 명시하지 않고 함수 호출 시
// 함수의 호출문이 존재하는 함수와 동일한 이름공간 안에서 호출할 함수를 찾게됨
// SimpleFunc 안에서는 PrettyFunc을 직접 호출 할 수 있고
// 다른 이름공간에 있는 SimpleFunc()과 같은 경우는
// 앞에 이름공간을 붙여줘야 한다.

//이름공간은 중첩이 가능함 --> 계층적 구조를 갖게끔 이름공간을 구성할 수 있음
//ex) Parent::num , Parent::SubOne::num, Parent::SubTwo::num
```

### 입출력
```cpp
//출력
std::cout << "첫 번째 숫자입력: ";
//입력
int val1;
std::cin >> val1;

//연이은 데이터의 입력도 가능
std::cin >> val1 >> val2;
//std::cin을 통해서 입력되는 데이터의 구분은 스페이스바, 엔터, 탭과 같은 공백을 통해 이루어짐

//배열 기반 문자열 입출력
char name[100];
std::cin >> name;
```

### 함수 오버로딩
- cpp는 함수호출 시 함수의 이름과 전달되는 인자의 정보를 동시에 참조하여 호출할 함수를 결정함
  - 따라서 매개변수의 선언이 다르면 동일한 이름의 함수도 재정의 가능함

```cpp
int MyFunc(char c){...}
int MyFunc(int n){...}
// 매개변수의 자료형이 다르므로 성립

int MyFunc(int n){...}
int MyFunc(int n1, int n2){...}
// 매개변수의 수가 다르므로 함수 오버로딩 성립

void MyFunc(int n){...}
int MyFunc(int n)P...
// 반환형의 차이는 함수 오버로딩의 조건을 만족시키지 않음!!
// 매개변수에 focus를 맞춰라

// 호출 시 전달할 때는 인수, 호출 후 사용할 때는 매개변수
```

### 디폴트 매개변수
- 함수를 호출할 때 명시된 매개변수를 전달하지 않았을 경우 사용하게 될 기본값
  - 함수 선언 시 명시할 것
```cpp
int MyFuncOne(int num=7)
{
    return num+1;
}
//인자를 전달하지 않으면 7이 전달된 것으로 간주

int MyFuncTwo(int num1=5, int num2=7){
    return num1+num2;
}
//인자를 전달하지 않으면 각각 5와 7이 전달된 것으로 간주
```

- 부분적 디폴트 값 설정
```cpp
int YourFunc(int num1, int num2=5, int num3=7){...}

YourFunc(10); // YourFunc(10, 5, 7);
YourFunc(10, 20); // YourFunc(10, 20, 7);
// 일부에만 디폴트 값을 지정하고 채워지지 않은 매개변수에만 인자 전달 가능
// 전달받은 인자가 왼쪽에서부터 채워짐
// **디폴트 값은 반드시 오른쪽부터 채우는 형태로 정의해야함**

int WrongFunc(int num1=10, int num2, int num3){...}
int WrongFunc(int num1=10, int num2-20, int num3){...}
// 디폴트 값이 왼쪽에서부터 채워지므로
// 오른쪽 값들은 의미를 갖지 못함 -> 컴파일 에러
```

### 인라인 함수
- 일반적인 함수의 호출 과정을 거치지 않고, 함수의 모든 코드를 호출된 자리에 바로 삽입
  - 함수 호출 오버헤드를 줄일 수 있음
```cpp
inline int Sub(int x, int y){return x-y;}
```

## C와 C++의 문법구조, 참조자(레퍼런스)
### switch문
```cpp
switch(user_input){
    case 1:
    cout << ".";
    break;

    case 2:
    cout <<"..";
    break;

    default:
    cout << "...";
    break;
}
```

### const
- `const int num = 10`
  - 변수 num을 상수화
- `const int * ptr1= &val1;`
  - 포인터 ptr1을 이용해서 val1의 값을 변경할 수 없음
    - `ptr1 = &val3`은 가능하지만, `*ptr1 = 30`과 같이 역참조로 값을 바꿀 수 없음
- `int * const ptr2= &val2;`
  - 포인터 ptr2가 상수화 됨
  - 역참조로 값을 바꾸는 것은 가능하지만, 주소값을 변경하지는 못함
- `const int * const ptr3 = &val3`
  - 포인터 ptr3이 상수화 되었으며, ptr3을 이용해 val3의 값을 변경하지 못함

### 참조자
- 할당된 하나의 메모리 공간에 다른 이름을 붙이는 것을 의미
  - 별칭 같은 것이라 생각
```cpp
int num1 = 2010;
int &num2 = num1;
// 참조자의 선언으로 num1의 메모리 공간에 num2라는 이름이 추가로 붙게 됨
// `새로 선언되는 변수`의 이름 앞에 &를 넣으면 참조자 선언을 뜻함
// num1과 num2에 동일한 값과 동일한 주소가 저장되게 됨

int num1 =2759;
int &num2 = num1;
int &num3 = num2;
int &num4 = num3;
//이와 같이 연속적으로 사용도 가능함
// num1 메모리 공간을 num2, num3, num4도 같이 사용

int &ref = 20;
int &ref;
int &ref =NULL;
//상수나 NULL 대상으로 참조자 선언 불가능
//참조자는 생성과 동시에 초기화 해야함
//참조자는 참조의 대상을 변경할 수 없음!!

int main(){
    int arr[3] = {1,3,5};
    int &ref1 = arr[0]; //1 저장
    int &ref2 = arr[1]; //3 저장
    int &ref3 = arr[2]; //5 저장
}

int main(){
    int num = 12;
    int *ptr = &num; //참조자와 헷갈리지 말 것 이건 주소임
    int **dptr = &ptr;

    int &ref = num;
    int *(&pref) = ptr; 
    //참조자 pref를 포인터 변수로 선언 가능
    int **(&dpref) = dptr;
    //참조자 dpref를 2차원 포인터 변수로 선언 가능
    // ref: 12 저장
    // *pref: 12 저장
    // **dpref: 12 저장
}
```

### 참조조와 함수
- CallByValue & CallByReference
  - 값에 의한 호출, 참조에 의한 호출
  - 참조에 의한 호출은 주소값 전달과 참조자 활용 두 가지 방법으로 가능함
- CallByValue
  - ![image](https://user-images.githubusercontent.com/102513932/232315112-bf9eeb35-a19f-4e7c-8b6d-7cadb59efb83.png)
  - val1과 val2에 저장된 값이 서로 바뀌지 않음
```cpp
void SwapByRef(int * ptr1 , int * ptr2)
{
    int temp = *ptr1;
    *ptr1 = *ptr2;
    *ptr2= temp;
}

void SwapByRef2(int &ref1 , int &ref2)
{
    int temp = ref1;
    ref1 = ref2;
    ref2 = temp;

// 함수 호출부가 SwapByRef2(val1, val2)라 하자.
// ref1은 val1의 메모리 주소를 가리키고
// ref2는 val2의 메모리 주소를 가리키게 된다.
// 따라서 값 변경 시 실제 값이 변경되므로, 포인터처럼 동작한다.
}
```

### const 참조자
```cpp
void HappyFunc(const int &ref){....}
// HappyFunc 내에서 참조자 ref를 이용한 값의 변경은 허용하지 않음
// 사용 시 장점
// 원형 선언만 봐도 값의 변경이 일어나지 않음을 판단할 수 있음
// 실수로 인한 값의 변경이 일어나지 않음

const int num = 20;
int &ref =num; // --> 컴파일 에러
//이를 허용하면 ref를 통한 값의 변경을 허용하기 때문에

const int num = 20;
const int &ref = num;
//num에 대한 상수 참조를 생성함
//당연히 ref의 값을 변경하지는 못함

const int &ref2 = 50;
//이렇게 상수도 저장할 수 있음
```

### new & delete
```cpp
int * ptr1 = new int; //int형 변수 메모리 할당
double * ptr2 = new double; //double형 변수 메모리 할당
int * arr1 = new int[3] //길이가 3인 int형 배열 할당
double * arr2 = new double[7]; //길이가 7인 double형 배열 할당

delete ptr1;
delete ptr2;
delete[] arr1;
delete[] arr2;
// new 연산자로 할당된 메모리 공간은
// delete나 delete[]를 통해 꼭 소멸해야함!
```

### 포인터를 사용하지 않고 힙에 접근
```cpp
int *ptr = new int;
int &ref = *ptr; //힙 영역에 할당된 변수에 대한 참조자 선언
ref = 20;
cout<<*ptr<<endl; //20
// 참조자를 통해서 heap 영역에 접근할 수 있음!
```

### C++에서 C언어의 표준함수 호출
- c를 더하고 .h를 빼기
- `#include <stdio.h> -> #include <cstdio>`
- `#include <stdlib.h> -> #include <cstlib>`
- `#include <math.h -> #include <cmath>`
- ![image](https://user-images.githubusercontent.com/102513932/232316029-d1a675c9-0bb3-4889-b2ec-cbd795f4ca2a.png)

## 구조체와 클래스, 객체

### 구조체
- 연관 있는 데이터를 하나로 묶음
  - 데이터의 그룹화를 통해 생성 및 소멸의 시점 일치와 이동 및 전달의 시점 및 방법을 일치시킴
```cpp
//구조체 안 함수 삽입 가능
struct Car
{
    char gameId[ID_LEN];
    int fuelGauge;
    int carSpeed;

    void ShowCarState()
    {
        . . . .
    }

    void Accel()
    {
        carSpeed = 50; 
        // 함께 선언된 변수에는 직접 접근이 가능함
        return;
    }
}

int main(){
    Car c1; //c와 달리 앞에 struct 붙이지 않아도 됨
    c1.carSpeed =0;
    Car c2 = {"aa", 3, 4}; //이렇게도 선언 가능
}
```

### 객체와 클래스
- 클래스
  - 객체의 설계도 또는 틀
  - 필드와 메서드를 정의함
  - 멤버 변수와 멤버 함수를 하나의 유닛으로 묶어 **캡슐화**
- 객체
  - 클래스를 기반으로 생성된 실체
- 인스턴스
  - 클래스의 특정 객체
  - 객체와 거의 동의어라 생각해도 무방함

```cpp
class Person{
    //접근 제어자를 아무것도 지정하지 않으면 private으로 저장됨
    public:
        std::string name;
        int age;
    void introduce(){
        cout << "name is: " << name << "age is: " << age << endl;
    }
}; //마지막에 세미콜론 붙여주기

int main(){
    Person person1;
    // Person * perptr1 = new Person;
    // 동적 할당 방식으로 생성도 가능하다
    person1.name = "jung";
    person1.age = 10;
    person1.introduce();

    reutrn 0;
}
```

### 접근제어 지시자
- public
- protected
  - 상속관계 시, 유도 클래스에서 접근 허용
- private
  - 클래스 내에서만 접근 허용

```cpp
class FruitSeller
{
    private:
        int APPLE_PRICE;
        int numOfApples;
        ine myMoney;
    public:
        void InitMembers(int price, int num, int money)
        {
            APPLE_PRICE = price;
            numOfApples = num;
            myMoney = money;
        }
};
int main(){
    FruitSeller fs;
    fs.InitMembers(1000, 20, 0);
    return 0;
}
// 보통 이런식으로 멤버 변수는 private으로 생성
// 지금은 생성자를 억지로 만들어서 써줬는데
// 보통은 그냥 생성자를 통해 멤버 변수 초기화 및 생성
```
- 정보 은닉
  - 객체지향 언어적 요소를 활용, 객체에 대한 구체적인 정보를 노출시키지 않음
- 캡슐화
  - 변수나 메서드를 캡슐로 감싸서 안보이게 하는 정보 은닉 개념

## 생성자, 소멸자, 객체배열 friend 선언, static

### 생성자
- 객체 생성 시 자동으로 호출되는 함수
  - 자동 호출되며 객체 초기화
```cpp
class Date{
    int year;
    int month;
    int day;

public:
    Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    Date(){ // 생성자 오버로딩 가능
        this.year = 2023;
        this.month = 3;
        this.day = 24;
    }
}
```

### 소멸자
- 객체가 소멸될 때 자동으로 호출되는 함수
- 객체가 사용한 자원을 해제하는 등의 마무리 작업 수행
- 형태 : `~클래스이름`
  - ex) `~Date`
- 규칙
  - 반환형이 선언되지 있지 않고, 실제로 반환되지 않음
  - 오버로딩, 디폴트 값 설정 불가능
  - 클래스당 하나밖에 존재할 수 없음
  - 생성자와 마찬가지로, 따로 정의하지 않으면 디폴트 소멸자가 삽입됨

```cpp
class Person{
private:
    char name[20];
public:
    Person(const char* name){
        strcpy(this->name, name);
        // <cstring>
        // this->name에 name을 복사
    }
    
    ~Person(){
        std::cout << name << "is being destroyed" << std:endl;
    }
};

int main(){
    Person person("John");
    return 0;
}
//outpt : John is being destroyed
```

### 객체 배열
- 객체(인스턴스)를 저장하는 배열
- 형태
  - `클래스명 객체명[크기];`
  - ex) `Person persons[3] = {Person("John"), Person("Mary"), Person("Tom")}`
- 객체 배열의 동적 할당
  - `Person * persons = new Person[3];`
  - 동적 할당은 무조건 `delete[]`로 해제해줘야함!!
  - 이후 `persons[0] = Pesron("John");`과 같이 사용할 수 있음

### 객체 포인터 배열
- 객체를 저장할 수 있는 포인터 변수로 이뤄진 배열
- 형태
  - `클래스명 * 객체명[크기];`
  - ex) `Person * persons[3] = {new Person("John"), new Person("Mary"), new Person("Tom")};`
    - for문 3번과 `delete persons[i]`로 해제해야함
    - 이는 포인터의 배열임을 명심하라
      - 위와 다르게 생성자 앞에 new가 붙음
      - c++에서 new는 malloc과 같은 동적 할당임!!! Java와 헷갈리지 말 것
    - 인덱스를 이용하여 각 원소에 접근 가능
  - 객체 포인터 배열의 동적 할당
    - `Perosn** persons = new Person*[3];`
    - `persons[0] = new person("John");`
  - 해제
    - for문 3번과 `delete persons[i]`로 해제 후 delete[] persons로 해제해야함


### this 포인터
- 멤버 함수가 호출될 때 해당 함수를 호출한 객체의 주소를 가리키는 포인터
  - **현재 객체의 주소**를 가리킴
    - 이를 통해 객체 내 멤버 변수나 멤버 함수에 접근

```cpp
class Person{
private:
    std::string name;
public:
    Person(const std::string& name){ 
        //strcpy 사용할거면 const * char name
        this->name = name;
    }

    //이니셜라이즈 리스트를 사용한 생성자
    // Person(const std::string& name) : name(name) { }
}
```

### self-reference의 반환
- 자기참조
  - 객체 자신을 참조할 수 있는 참조자
  - 여러 개의 멤버 함수를 연속적으로 호출할 수 있음

```cpp
class Person{
private:
    std::string name;
public:
    Person(const std::string& name){
        this->name = name;
    }
    
    Person& setName(const std::string& name){
        this->name = name;
        return *this;
    }

    Person& printName(){ //객체에 대한 참조를 반환함!!
        std::cout << "My name is " << this -> name << std::endl;
        return *this; //참조를 반환하니까 역참조로 값을 넘겨줘야 함
    }
};

int main(){
    Person p("John");
    p.setName("Alice").printName();
    //My name is Alice
}
```

### 클래스의 friend 선언
- friend 선언
  - 클래스가 다른 클래스나 함수에게 자신의 private 멤버에 접근 권한을 부여함
```cpp
class MyClass{
    friend class FriendClass;
    friend void friendFuction();
    //이 선언은 클래스 내에 어디에든 위치할 수 있음
    //FriendClass 클래스는 MyClass의 private 멤버에 접근할 수 있음
    //friendFunction()도 마찬가지로 접근 가능
    // 단, MyClass가 FriendClass의 private 멤버에 접근하려면
    // FriendClass에도 friend 선언을 해줘야 함

    friend Point PointOP::PointAdd(const Point&, const Point&);
    // 클래스의 특정 멤버함수를 대상으로도 friend 선언이 가능함
    friend void ShowPointPos(const Point&);
    // 전역함수 대상으로도 friend 선언이 가능
}
```

### Static
- 일반적으로 static 키워드는 변수, 함수, 클래스 멤버 등에서 사용
- static 변수를 외부에서 접근 가능하게 하려면, *public*으로 선언되어야 함
- '클래스 변수'라고 지칭하기도 함
  - 클래스당 하나씩만 생성되기 때문

```cpp
class SoSimple
{
private:
    static int simObjCnt; // static 멤버변수(클래스 변수)
public:
    SoSimple()
    {
        simObjCnt++;
        cout << simObjCnt << "번째 SoSimple 객체" << endl;
    }
};

int SoSimple::simObjCnt =0; //static 멤버변수 초기화
// static 멤버 변수는 클래스 외부에서 초기화되어야 함

int main(void)
{
    SoSimple sim1; // 1번째 
    SoSimple sim2; // 2번째
    SoSimple sim3; // 3번째
}
// 모든 객체가 하나의 static 변수 simObjCnt를 공유함!!
// 메모리 공간에 딱 하나만 할당이 되어 공유됨
```

```cpp
class MyClass{
private:
    static int count; //static 멤버변수
public:
    static int getCount(){
        //static 멤버함수
        return count;
    }
};

int MyClass::count = 3;

int main(){
    std::cout << MyClass::getCount() << std::endl;
    return 0;
    }
//static 함수는 객체 내에 존재하는 함수가 아니기 때문에
//멤버변수나 멤버함수에 접근이 불가능함(객체의 멤버로 존재하는 것이 아님)

//public으로 선언이 되면 클래스의 이름을 이용해 호출이 가능함
//private으로 선언이 되면 클래스 내부에서만 사용 가능함

//static 함수는 static 변수에만 접근 가능, static 함수만 호출 가능함
```

## 연산자 오버로딩, 상속

### 연산자 오버로딩
- 객체가 생성될 때 자동으로 호출되는 함수
- 자동으로 호출되며 객체를 초기화 해주는 역할을 함
- 형식
  - `리턴 타입 operator 연산자(매개변수)`
```cpp
class Point{
private:
    int xpos, ypos;
public:
    Point(int x=0, int y=0) : xpos(x), ypos(y) { }
    
    void ShowPosition() const 
    //상수 멤버 함수, 클래스의 멤버 변수 변경 불가능
    {
        cout<< '[' << xpos << ", "  <<ypos << ']' <<endl;
    }

    // 멤버함수에 의한 연산자 오버로딩!
    Point operator+(const Point &ref) 
    {
        Point pos(xpos+ref.xpos, ypos+ref.ypos);
        return pos;
    }
};
// 전역함수로도 연산자 오버로딩이 가능하다
Point operator+(const Point& a, const Point& b){
    Point pos(a.xpos + b.xpos, a.ypos + b.ypos);
    return pos;
}
// 단, xpos와 ypos가 private로 선언되어 접근할 수 없기 때문에
// class 내부에 friend Point operator+(const Point& a, const Point& b);
// 를 추가해주어야 한다.
int main()
{
    Point pos1(3,4);
    Point pos2(10,20);
    Point pos3 = pos1.operator+(pos2);
    // 사실 Point pos3 = pos1 + pos2; 로 사용하는 것이 옳다.

    pos1.ShowPosition(); //[3, 4]
    pos2.ShowPosition(); //[10, 20]
    pos3.ShowPosition(); //[13, 24]
    return 0;
}
```
- 멤버 함수에 의한 연산자 오버로딩과 전역 함수에 의한 연산자 오버로딩 모두 사용 가능
  - 보통 새로운 타입에 대해 연산자를 정의할 때는 전역 함수를 사용
  - 이미 존재하는 타입에 대해 연산자를 정의할 때는 클래스 내부에서 멤버 함수를 정의하는 것이 일반적
  - 동일한 자료형을 대상으로 연산자를 오버로딩 하는 경우, **멤버함수 기반**이 전역함수 기반보다 우선순위가 높음!!
- 주의사항
  - 연산자의 기본 기능을 변경하는 형태의 연산자 오버로딩은 허용되지 않음

### 상속
- 이미 정의된 클래스를 기반으로 새로운 클래스를 정의
  - 오버라이딩을 통해 함수를 재정의 할 수 있음
- 형태
  - `class 자식클래스명 : 접근제어자 부모클래스명`
    - 접근제어자 기본값은 private
```cpp
class Person
{
    private:
        int age;
        char name[50];
    public:
        Person(int myage, char * myname) : age(myage)
        {
            strcpy(name, myname);
        }
};

class UnivStudent : public Person //Person 클래스를 상속
{
    private:
        char major[50];
    public:
        UnivStudent(char *myname, int myage, char *mymajor) : Person(myage, myname)
        {
            strcpy(major, mymajor);
        }
    //Person에서 상속받은 age와 name은
    //Person 생성자를 이니셜라이저 처리해서 초기화 함
    //major 멤버 변수만 strcpy로 처리
};
```

## 상속(2)
### protected 선언
- protected로 선언된 멤버변수는 외부에서 접근이 불가능
  - 상속된 클래스에서는 접근할 수 있음
- public 상속
  - 접근 제어 권한을 그대로 상속함
- protected 상속
  - protected보다 접근의 범위가 넓은 범위는 protected로 상속함
- private 상속
  - private보다 접근의 범위가 넓은 멤버는 private로 상속함
- 모든 상속에서 private는 **접근 불가**로 상속함

### 상속의 정리
- 상속으로 묶인 두 개의 클래스는 강한 연관성을 띔
- is-a 관계
  - 객체 간의 상속 관계를 나타냄
- has-a 관계
  - 한 객체가 다른 객체를 갖고 있음을 나타냄
- 간접 상속
  - 상속된 클래스가 또 다른 클래스를 상속하고 있는 경우를 의미함

### 객체 포인터
- 객체 포인터 변수
  - 클래스 타입의 객체를 가리키는 포인터
  - 객체의 주소 값을 저장
  - 형식
    - `클래스이름 *포인터변수`
    - ex: `Student *ptrStudent;`
      - Student형 포인터는 Student의 객체 뿐 아니라, Student를 상속하는 유도 클래스의 객체도 가리킬 수 있음

```cpp
class Student : public Person
{
    ...
};

class PartTimeStudent : public Student
{
    ...
};

// person <- student <- PartTimeStudent
// Person * ptr = new Person();
// Person * ptr2 = new Student();
// Person * ptr3 = new PartTimeStudent();
// Student * ptr4 = new Student();
// Student * ptr5 = new PartTimeStudent();
// PartTimeStudent * ptr6 = new PartTimeStudent();
// 단, 하위 클래스로 생성은 가능하나 하위 클래스에 있는 메서드를 사용하지는 못함
// 매우 중요함!!
// 꼭 delete로 해제할 수 있도록 하자.
// 하위 클래스 포인터로 상위 클래스를 가리킬 수는 없음
// Student * ptr6 = new Person(); (X)
```

### 객체 포인터 + 함수 오버라이딩
```cpp
class Employee
{
    private:
        char name[100];
    public:
        Employee(const std::string &name) : name(name) { }
};

class ParamentWorker : public Employee{
    private:
        int salary;
    public:
        paramentWorker(const std::string &name, int money) : Employee(name), salary(money) { }
};

class EmployeeHandler
{
    private:
        //Employee 객체의 주소값 저장
        //Employee 클래스를 상속하는 클래스(ParamentWorker)의
        //객체도 이 배열에 함께 저장 가능함
        Employee* empList[50];
        int empNum;
    public:
        //empNum만 0으로 초기화
        EmployeeHandler() : empNum(0) { }
    // Employee 객체 포인터 전달(PermenentWokrer 포인터도 전달 가능)
    void AddEmployee(Employee* emp)
    {
        empList[empNum++] = emp;
    }
};

int main(void)
{
    EmployeeHandler handler;

    handler.AddEmployee(new Employee("KIM"));
    handler.AddEmployee(new PermanentWorker("LEE", 1500));
}

//오버라이딩 예시
int GetPay() const
{
    return PemanentWorker::GetPay() +(int)(salesResult*bonusRatio);
    //이처럼 상속받는 클래스의 함수를 호출해올 수도 있음
}
```

## 가상 함수와 다형성
### 다형성
- 동일한 함수 이름을 가진 함수가 서로 다른 동작을 수행
- 다형성 개념은 가상 함수를 이용해서 구현됨
- 가상 함수
  - 자식 클래스에서 오버라이딩을 통해 구현됨
  - 형태
    - `virtual 리턴명 함수명(매개변수)`
  - 호출
    - 부모 클래스의 포인터나 참조를 통해 자식 클래스의 함수를 호출할 수 있도록 함
  - 가상 함수를 포함하는 클래스는 반드시 **가상 소멸자**를 가져야 함
```cpp
//가상 함수 미사용 코드
#include <iostream>
using namespace std;

class First
{
public:  
    void MyFunc() { cout<< "FirstFunc" << endl; }
};

class Second : public First
{
public:
    void MyFunc() { cout << "SecondFunc" << endl;}
};

class Third : public Second
{
public:
    void MyFunc() { cout << "ThirdFunc" << endl; }
};

int main()
{
  Third* tptr = new Third();
  Second* sptr = tptr;
  First* fptr = sptr;

  fptr -> MyFunc(); //FirstFunc
  sptr -> MyFunc(); //SecondFunc
  tptr -> MyFunc(); //ThirdFunc
  // 하위 클래스로 지정을 했지만
  // 하위 클래스의 메서드를 사용할 수는 없기 때문에
  // 각자 메서드를 사용해서 결과가 나오게 된다.

  delete tptr; 

  return 0;
}
```

```cpp
//가상 함수 사용 코드
#include <iostream>
using namespace std;

class First
{
public:  
    virtual void MyFunc() { cout<< "FirstFunc" << endl; }
};

class Second : public First
{
public:
    virtual void MyFunc() { cout << "SecondFunc" << endl;}
};

class Third : public Second
{
public:
    virtual void MyFunc() { cout << "ThirdFunc" << endl; }
};

int main()
{
  Third* tptr = new Third();
  Second* sptr = tptr;
  First* fptr = sptr;

  fptr -> MyFunc(); //ThirdFunc
  sptr -> MyFunc(); //ThirdFunc
  tptr -> MyFunc(); //ThirdFunc
  //가상 함수를 사용하면 하위 클래스의 메서드를 사용할 수 있다!
  //가상 함수는 실행 시점에 호출되는 메서드를 결정함
  //포인터가 가리키는 객체의 실제 타입에 따라 적절한 메서드를 호출할 수 있음
  //상속의 계층구조상 맨 위에 존재하는 클래스의 생성자만 virtual로 선언하면
  //이를 상속하는 유도 클래스의 소멸자도 모두 '가상 생성자'로 선언됨!!!!
  delete tptr; 

  return 0;
}
```

### 가상 소멸자
- 상속 관계에 있는 클래스 중 하나라도 가상 함수를 포함하고 있으면, 그 클래스는 가상 소멸자를 가져야 함
- 가상 소멸자
  - virtual로 선언된 소멸자, 동적 할당된 객체에서 주로 사용
```cpp
//그냥 소멸자를 구현한 코드
#include <iostream>
#include <cstring>

using namespace std;

class First{
private:
  char* strOne;
public:  
First(char* str) {
  strOne = new char[strlen(str)+1];
}

~First(){
  cout << "~First()" << endl;
  delete[]strOne;
}
};

class Second : public First
{
private:
  char* strTwo; 
public:
  Second(char* str1, char* str2) : First(str1)
{
  strTwo = new char[strlen(str2)+1];
}

~Second()
{
  cout << "~Second()" << endl;
  delete[] strTwo;
}
};

int main()
{
  First* ptr = new Second("simple", "complex");
  delete ptr;

  return 0;
}
// 실행결과 : ~First()
// First 클래스 포인터가 Second 클래스 객체를 가리키고 있지만
// First 클래스 소멸자만 호출되고 있음
// 파생 클래스의 소멸자도 호출되어야 함.
// 따라서 가상 함수로 선언하여 파생 클래스의 소멸자도 호출할 수 있도록 함.
```

```cpp
// 객체의 소멸과정에서는 모든 소멸자가 호출되어야 함
// 
#include <iostream>
#include <cstring>

using namespace std;

class First{
private:
  char* strOne;
public:  
First(char* str) {
  strOne = new char[strlen(str)+1];
}

virtual ~First(){
  cout << "~First()" << endl;
  delete[]strOne;
}
};

class Second : public First
{
private:
  char* strTwo; 
public:
  Second(char* str1, char* str2) : First(str1)
{
  strTwo = new char[strlen(str2)+1];
}

virtual ~Second()
{
  cout << "~Second()" << endl;
  delete[] strTwo;
}
};

int main()
{
  First* ptr = new Second("simple", "complex");
  delete ptr;

  return 0;
}
//실행결과
//~Second()
//~First()
//소멸자는 계층구조상 밑에서부터 실행된다.
//얘도 사실 계층구조상 맨 위에 존재하는
//기초 클래스의 소멸자만 virtual로 선언해주면 된다.
```

### 참조자의 참조 가능성
```cpp
#include <iostream> 

using namespace std;

class First
{
public:
    void FirstFunc()
    {
        cout<<"FirstFunc()"<<endl;
    }
    virtual void SimpleFunc()
    {
        cout<<"First's SimpleFunc()" <<endl;
    }
};

class Second: public First
{
public:
    void SecondFunc()
    {
        cout<<"SecondFunc()"<<endl;
    }
    virtual void SimpleFunc()
    {
        cout<<"Second's SimpleFunc()" <<endl;
    }
};

class Third: public Second
{
public:  
    void ThirdFunc()
    {
        cout<<"Thirdfunc()"<< endl;
    }
    virtual void SimpleFunc()
    {
        cout<<"Third's SimpleFunc()"<<endl;
    }
};

int main(void)
{
  Third obj;
  obj.FirstFunc(); 
  obj.SecondFunc();
  obj.ThirdFunc();
  //위 3개는 상속이니 당연히 가능
  obj.SimpleFunc();
  //가상함수이니 Third's SimpleFunc() 출력됨
  cout << endl;
  
  Second & sref=obj;
  sref.FirstFunc();
  sref.SecondFunc();
  // ThirdFunc()는 하위 클래스니까 호출 불가능함
  sref.SimpleFunc();
  //가상함수이니 Third's SimpleFunc() 출력됨

  cout << endl;
  
  First & fref = obj;
  fref.FirstFunc();
  fref.SimpleFunc();
  //가상함수이니 Third's SimpleFunc() 출력됨
  return 0;
}
```