## 목차
- [자료구조](#자료구조)
  - [강의안내](#강의안내)
    - [자료구조란?](#자료구조란)
  - [자료구조의 분류, 알고리즘, 추상자료형](#자료구조의-분류-알고리즘-추상자료형)
    - [자료구조의 분류](#자료구조의-분류)
    - [추상 자료형과 c++](#추상-자료형과-c)
  - [알고리즘의 성능분석, 자료의 표현](#알고리즘의-성능분석-자료의-표현)
  - [이름공간, 입출력 형식, 함수 오버로딩, 매개변수, 인라인 함수](#이름공간-입출력-형식-함수-오버로딩-매개변수-인라인-함수)
    - [이름공간](#이름공간)
    - [입출력](#입출력)
    - [함수 오버로딩](#함수-오버로딩)
    - [디폴트 매개변수](#디폴트-매개변수)
    - [인라인 함수](#인라인-함수)
  - [C와 C++의 문법구조, 참조자(레퍼런스)](#c와-c의-문법구조-참조자레퍼런스)
    - [switch문](#switch문)
    - [const](#const)
    - [참조자](#참조자)
    - [참조자와 함수](#참조자와-함수)
    - [const 참조자](#const-참조자)
    - [new \& delete](#new--delete)
    - [포인터를 사용하지 않고 힙에 접근](#포인터를-사용하지-않고-힙에-접근)
    - [C++에서 C언어의 표준함수 호출](#c에서-c언어의-표준함수-호출)
  - [구조체와 클래스, 객체](#구조체와-클래스-객체)
    - [구조체](#구조체)
    - [객체와 클래스](#객체와-클래스)
    - [접근제어 지시자](#접근제어-지시자)
  - [생성자, 소멸자, 객체배열 friend 선언, static](#생성자-소멸자-객체배열-friend-선언-static)
    - [생성자](#생성자)
    - [소멸자](#소멸자)
    - [객체 배열](#객체-배열)
    - [객체 포인터 배열](#객체-포인터-배열)
    - [this 포인터](#this-포인터)
    - [self-reference의 반환](#self-reference의-반환)
    - [클래스의 friend 선언](#클래스의-friend-선언)
    - [Static](#static)
  - [연산자 오버로딩, 상속](#연산자-오버로딩-상속)
    - [연산자 오버로딩](#연산자-오버로딩)
    - [상속](#상속)
  - [상속(2)](#상속2)
    - [protected 선언](#protected-선언)
    - [상속의 정리](#상속의-정리)
    - [객체 포인터](#객체-포인터)
    - [객체 포인터 + 함수 오버라이딩](#객체-포인터--함수-오버라이딩)
  - [가상 함수와 다형성](#가상-함수와-다형성)
    - [다형성](#다형성)
    - [가상 소멸자](#가상-소멸자)
    - [참조자의 참조 가능성](#참조자의-참조-가능성)
    - [리스트](#리스트)
  - [리스트와 연결리스트 구현](#리스트와-연결리스트-구현)
    - [연결 리스트로 구현된 리스트](#연결-리스트로-구현된-리스트)
  - [이중 연결 리스트, 원형 연결 리스트, 스택(1)](#이중-연결-리스트-원형-연결-리스트-스택1)
    - [원형 연결 리스트](#원형-연결-리스트)
    - [이중 연결 리스트](#이중-연결-리스트)
    - [스택](#스택)
  - [스택(2)](#스택2)
    - [스택(2)](#스택2-1)
  - [큐(선형 큐, 원형 큐)](#큐선형-큐-원형-큐)
    - [큐](#큐)
    - [선형 큐(linear queue)](#선형-큐linear-queue)
    - [원형 큐(Circular queue)](#원형-큐circular-queue)
    - [배열을 이용한 원형 큐 구현](#배열을-이용한-원형-큐-구현)
    - [연결 리스트를 이용한 큐](#연결-리스트를-이용한-큐)
  - [덱](#덱)
    - [덱](#덱-1)
    - [배열을 이용한 원형 덱 구현](#배열을-이용한-원형-덱-구현)
    - [이중 연결 리스트를 이용한 덱](#이중-연결-리스트를-이용한-덱)
  - [트리](#트리)
    - [트리](#트리-1)
    - [이진 트리](#이진-트리)
    - [배열을 이용한 이진 트리](#배열을-이용한-이진-트리)
    - [링크를 이용한 이진 트리 및 전중후 순회](#링크를-이용한-이진-트리-및-전중후-순회)
  - [트리(2)](#트리2)
    - [레벨 순회](#레벨-순회)
  - [이진 탐색 트리](#이진-탐색-트리)
    - [탐색](#탐색)
    - [삽입 연산](#삽입-연산)
    - [삭제 연산](#삭제-연산)
  - [그래프](#그래프)
    - [인접 행렬을 이용한 그래프](#인접-행렬을-이용한-그래프)
    - [인접 리스트로 표현한 그래프](#인접-리스트로-표현한-그래프)
  - [그래프(2)](#그래프2)
    - [DFS(깊이 우선 탐색)](#dfs깊이-우선-탐색)
    - [BFS(너비 우선 탐색)](#bfs너비-우선-탐색)
    - [신장 트리](#신장-트리)
    - [위상 정렬](#위상-정렬)
  - [template, pair](#template-pair)
    - [템플릿](#템플릿)
    - [함수 템플릿](#함수-템플릿)
    - [클래스 템플릿](#클래스-템플릿)
    - [템플릿 구현](#템플릿-구현)
    - [STL](#stl)
    - [Pair](#pair)
  - [최소 비용 신장 트리](#최소-비용-신장-트리)
    - [최소 비용 신장 트리(MST)](#최소-비용-신장-트리mst)
    - [Kruskal 알고리즘](#kruskal-알고리즘)
    - [Prim의 MST 알고리즘](#prim의-mst-알고리즘)
  - [최단 경로](#최단-경로)
    - [최단 경로](#최단-경로-1)
    - [Floyd의 최단경로 알고리즘](#floyd의-최단경로-알고리즘)
    - [Dijkstra vs Floyd](#dijkstra-vs-floyd)
  - [정렬](#정렬)
    - [선택 정렬](#선택-정렬)
    - [삽입 정렬](#삽입-정렬)
    - [버블 정렬](#버블-정렬)
    - [선택 정렬 vs 삽입 정렬 vs 버블 정렬](#선택-정렬-vs-삽입-정렬-vs-버블-정렬)
  - [정렬(2)](#정렬2)
    - [셀 정렬](#셀-정렬)
    - [합병 정렬](#합병-정렬)
# 자료구조
## 강의안내
### 자료구조란?
- 코드의 효율성
  - 프로그램의 코드를 개발하는 데 사용되는 안정성, 속도 및 프로그래밍 방법을 나타내는데 사용됨
  - 소프트웨어의 런타임 실행 속도와 직접 연결됨
  - **목적에 맞는 자료구조를 사용하는 것이 효율적임**
- 데이터 추상화
  - 시스템의 간략화 된 기술이나, 핵심적인 구조나 동작에만 집중
- 재사용성
  - 자료구조의 인터페이스만 이용하여 데이터를 처리
    - 모듈화(재사용) 가능
- 데이터를 체계적으로 저장하고, 효율적으로 관리
- 선택 기준
  - 자료의 크기, 처리 시간, 활용 빈도, 프로그램의 용이성
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

### 참조자와 함수
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

### 리스트
- 구현 방법
  - 배열 이용
    - 구현 간단
    - 삽입, 삭제 시 오버헤드 발생 가능
    - 항목 개수에 제한이 생길 수 있음
  - 연결 리스트 이용
    - 구현 복잡
    - 삽입, 삭제 효율적
    - 크기가 제한되지 않음
- 종류
  - 단순 연결 리스트, 원형 연결 리스트, 이중 연결 리스트
  - 단순 연결 리스트
    - 하나의 방향으로만 연결되어 있어서, 맨 마지막 노드의 링크 필드는 NULL 값을 가짐
  - 원형 연결 리스트
    - 단순 연결 리스트와 같지만 맨 마지막 노드의 링크 값이 다시 첫 번째 노드를 가리킴
  - 이중 연결 리스트
    - 각 노드마다 링크 필드(포인터)가 2개씩 존재함
    - 각각의 노드는 선행 노드와 후속 노드를 모두 가리킬 수 있음

## 리스트와 연결리스트 구현
- ![image](https://user-images.githubusercontent.com/102513932/233055595-8b48b25a-80e6-48e9-af84-d29c8bbe9283.png)
- ![image](https://user-images.githubusercontent.com/102513932/233345501-6ccf86ef-7f61-4a23-bf93-129acb55b2e1.png)

```cpp
typedef int Element;
Element data[Max_LIST_SIZE]
int length =0;
//length는 새 요소가 리스트의 맨 뒤에 추가될 때 삽입되어야 하는 위치를 나타냄
//0이면 공백상태, length == MAX_LIST_SIZE면 포화상태
```

```cpp
//배열을 이용한 리스트 클래스 구현
#include <iostream>
#define MAX_LIST_SIZE 100

inline void error(char *str){
    cout << stderr <<str <<endl;

    exit(1);
};

class ArrayList
{
    int data[MAX_LIST_SIZE];
    int length; 
public:
    ArrayList(void){length =0;}

    void insert(int pos, int e){ //POS 번째에 e 추가
        if(!isFull() && pos>=0 && pos<=length){
            for(int i= length; i>pos; i--){
                data[i] = data[i-1];
            }
            data[pos] = e;
            length++;
        }
        else error("포화상태 오류 또는 삽입 위치 오류");
    }

    void remove(int pos){
        if(!isEmpty() && 0<= pos && pos<length){
            for(int i=pos+1; i<length; i++){
                data[i-1] = data[i]
            }
            length--;
        }
        else error("공백상태 오류 또는 삭제 위치 오류");
    }

    //pos번째 항목 반환
    int gerEntry(int pos){return data[pos];} 

    //공백 상태 검사
    bool isEmpty(){return length ==0;}
    //포화 상태 검사
    bool isFull(){return length==MAX_LIST_SIZE;}

    bool find(int item){
        for(int i=0; i<length; i++){
            if(data[i] == item) return true;
        }
        return false;
    }

    void replace(int pos, int e){
        data[pos] = e;
    }

    int size(){return length;}

    void clear(){length =0;} //모든 요소 제거
}
```
- ![image](https://user-images.githubusercontent.com/102513932/233348994-b9c8813a-fe33-4c71-87f8-7e741fe063dc.png)

### 연결 리스트로 구현된 리스트
- 단순 연결 리스트 사용
  - 마지막 노드 링크 값은 NULL
- 삽입 연산
  - ![image](https://user-images.githubusercontent.com/102513932/233349372-9be54af5-a9a2-4b5d-8171-a59c2b406844.png)
- 삭제 연산
  - ![image](https://user-images.githubusercontent.com/102513932/233349492-383eb7fd-229b-4b76-b9e3-f518b91ef5a1.png)
- 연결리스트에서는 시작 노드의 주소만을 관리

```cpp
#include <iostream>
#define MAX_LIST_SIZE 100
using namespace std;

inline void error( char* str){
  cout << stderr << str << endl;
  exit(1);
};

class Node
{
  Node* link; 
  //다음 노드를 가리키는 포인터 변수
  int data; 
  // 노드의 데이터 필드

public:
  // 생성자에서 data가 0으로 초기화된다
  Node(int val=0) :data(val), link(NULL) {}
  Node* getLink() {return link; }
  void setLink(Node* next) {link = next;}
  void display() {cout << data << endl;}
  bool hasData(int val){ return data == val;}

  void insertNext(Node *n){  
    //다음에 새로운 노드 n을 삽입
    if(n!=NULL){
      n->link = link;
      link = n;
    }
  }

  //자신의 다음 노드를 리스트에서 삭제함
  Node* removeNext(){
    Node* removed = link;
    if( removed != NULL){
      link = removed-> link;
    }
    return removed;
  }
};

class LinkedList
{
  Node org; 
  //헤드 노드(헤드 포인터 아님 주의)를 가리키는 노드
  //링크 필드에 헤드 포인터 소유

public:
  LinkedList(): org(0) {}
  //생성자, null값으로 초기화(nullptr도 가능)
  ~LinkedList(){ clear(); }

  void clear() { while(!isEmpty()) delete remove(0);}
  Node* getHead() { return org.getLink();}
  bool isEmpty() { return getHead()==NULL;}

//pos 번째 항목 반환
Node* getEntry(int pos){
  Node* n = &org;
  for(int i=-1; i<pos ; i++, n=n->getLink())
    if(n==NULL) break;
  return n;
}

//리스트의 특정 위치에 항목 삽입
void insert(int pos, Node *n){
  Node* prev = getEntry(pos-1);
  if( prev != NULL)
    prev -> insertNext(n);
}

//pos위치 node 삭제
Node* remove(int pos){
  Node* prev = getEntry(pos-1);
  return prev -> removeNext();
}

//탐색 함수
Node* find(int val){
  for(Node *p = getHead(); p != NULL; p = p->getLink())
    if(p->hasData(val)) return p;
  return NULL;
}

//list의 pos번째 노드를 다른 노드로 교체
void replace(int pos, Node *n){
  Node* prev = getEntry(pos-1);
  if(prev != NULL){
    delete prev -> removeNext();
    prev->insertNext(n);
  }
}

int size(){
  int count = 0;
  for( Node *p = getHead(); p !=NULL; p = p->getLink())
    count++;
  return count;
}

void display(){
  cout << "단순연결리스트 항목 수 = " << size() << endl;
  for(Node *p = getHead(); p !=NULL; p = p->getLink())
    p->display();
  cout<<endl;
}
};

int main()
{
  LinkedList list;

  list.insert(0, new Node(10)); //10
  list.insert(0, new Node(20)); //20 10
  list.insert(1, new Node(30)); //20 30 10
  list.insert(list.size(), new Node(40));//20 30 10 40
  list.insert(2, new Node(50)); //20 30 50 10 40
  list.display();

  list.remove(2);//20 30 10 40
  list.remove(list.size()-1);//20 30 40
  list.remove(0);//30 40
  list.replace(1, new Node(90));//30 90
  list.display();

  list.clear();
  list.display();
}
```

## 이중 연결 리스트, 원형 연결 리스트, 스택(1)
### 원형 연결 리스트
- 리스트의 마지막 노드의 링크가 첫 번재 노드를 가리키는 연결 리스트
- 마지막 노드의 링크가 NULL이 아닌 첫 번째 노드 주소가 되는 리스트
```cpp
#include <iostream>

using namespace std;

//원형 연결 리스트 노드 클래스
class Node{
public:
  int data;
  Node* next;
  
  //위와 다르게 daat가 0으로 초기화되지 않는다.
  Node(int value){
    data = value;
    next = NULL;
  }
};

//원형 연결 리스트 클래스
class CircularLinkedList{
private:

  //리스트의 마지막 노드를 가리키는 포인터
  Node* tail;

  //리스트의 크기를 저장하는 변수
  int size;
public:
  CircularLinkedList(){
    //생성자를 통해 리스트 초기화
    tail=NULL;
    size =0;
  }

  //새로운 노드를 리스트의 끝에 추가
  void add(int value){
    Node* new_node = new Node(value);
    if(tail == NULL){ //리스트에 노드가 없는 경우
      tail = new_node;
      tail-> next = tail;
    } else{ //리스트에 노드가 있는 경우
      new_node->next = tail->next;
      tail->next = new_node;
      tail = new_node;
    }
    size++; //size 1증가
  }

  //리스트에서 특정 값이 있는 노드 삭제
  void remove(int value){
    if(tail == NULL){ //리스트에 노드가 없는 경우
      return;
    }
    Node* current = tail->next;
    //리스트의 첫 번째 노드부터 탐색 시작
    Node* prev = tail;
    do{
      if(current->data == value){
        //삭제하고 싶은 데이터를 찾으면
        prev->next = current->next;
        //link 필드 변경
        if(current == tail){
            //만약 current가 tail이면 tail도 변경
          tail = prev;
        }
        delete current; //삭제
        size--; //size 1 감소
        return;
      }
      //삭제하고 싶은 데이터를 찾지 못한 경우
      //한 단계씩 증가
      prev = current;
      current = current->next;
    }while(current != tail->next);
  }

  void display(){
    if(tail==NULL){
      return;
    }
    Node* current = tail->next;
    do{
      cout << current->data <<" ";
      current = current->next;
    }while(current != tail->next);
    cout << endl;
  }

  int get_size(){
    return size; 
  }
};

int main(){
  CircularLinkedList myList;
  myList.add(1); //1
  myList.add(2); //1 2 
  myList.add(3); //1 2 3
  myList.display(); //1 2 3
  myList.remove(2); // 1 3
  myList.display(); // 1 3
  cout << myList.get_size() << endl; //2

  return 0;
}
```

### 이중 연결 리스트
- 이전 노드와 다음 노드를 가리키는 두 개의 링크를 지님
  - 양쪽 방향 탐색 가능
- 어느 위치에서나 특정 노드를 검색하는 데 효율적임
- 공간을 많이 차지하고 코드가 복잡해진다는 단점
- 첫 번째 노드는 헤드 포인터가 가리키고, 마지막 노드의 마지막 링크 필드는 NULL 값
- 삽입
  - ![image](https://user-images.githubusercontent.com/102513932/233376643-3e88d636-b049-470c-9e4c-2a40900f4a51.png)
```cpp
void insertNext(Node2 *n){
    if( n!=NULL){
        n->prev = this; //(1)
        n->next = next; //(2)
        if(next !=NULL) next->prev =n; //(3)
        next = n; //(4)
    }
}
```
- 삭제
  - ![image](https://user-images.githubusercontent.com/102513932/233377208-6997337d-4f55-49f0-b243-b0b65ddd9153.png)
```cpp
Node2* remove(){
    if(prev != NULL) prev -> next = next;
    if(next != NULL) next -> prev = prev;
    return this;
}
```

```cpp
#include <iostream>
#define MAX_STACK_SIZE 100

using namespace std;

inline void error(char* str){
  cout << str << endl;
  exit(1);
};

class Node2{
  Node2* prev; //선행 노드를 가리킴
  Node2* next; //후속 노드를 가리킴
  int data; //데이터 필드

public:
  Node2(int val =0) : data(val), prev(nullptr), next(nullptr){}
Node2* getPrev() {return prev;}
Node2* getNext() {return next;}
void setPrev(Node2* p){prev = p;}
void setNext(Node2* n){next = n;}
void display() {cout << " <" << data << ">";}
bool hasData(int val){return data == val;}

//자신 다음에 새로운 노드 n을 삽입
void insertNext(Node2* n){
  if(n != nullptr){
    n->prev = this;
    n->next = next;
    if(next != nullptr) next ->prev = n;
    next = n;
  }
}

//현재 노드를 연결 리스트에서 제거
Node2* remove(){
  if(prev!=nullptr) prev->next = next;
  if(next!=nullptr) next->prev = prev;
  return this;
}
};

class DbLinkedList{
  Node2 org; 
public:
  DbLinkedList() : org(0){}
  ~DbLinkedList() { clear(); }

  void clear() { while(!isEmpty()) delete remove(0);}
  Node2* getHead() { return org.getNext(); }
  bool isEmpty() {return getHead() == nullptr;}

  //pos 번째 노드 반환
  Node2* getEntry(int pos){
    Node2* n = &org;
    for (int i = -1; i< pos; i++, n = n->getNext())
      if(n == nullptr) break;
    return n;
  }

  //pos 위치에 노드 삽입
  void insert(int pos, Node2* n){
    Node2* prev = getEntry(pos-1);
    if(prev != nullptr)
      prev -> insertNext(n);
  }

  //pos 위치 노드 삭제
  Node2* remove(int pos){
    Node2* n = getEntry(pos);
    return n-> remove();
  }

  //값이 val인 노드 삭제
  Node2* find(int val){
  for(Node2* p = getHead(); p!=nullptr; p = p->getNext())
    if(p->hasData(val)) return p;
    return  nullptr;
}
  
  //pos 위치의 노드 교체
  void replace(int pos, Node2* n){
    Node2* prev = getEntry(pos-1);
    if(prev != nullptr){
      delete prev->getNext()->remove();
      prev->insertNext(n);
    }
  }

  //리스트의 전체 노드 수 반환
  int size(){
    int count = 0;
    for(Node2* p = getHead(); p!=nullptr; p = p->getNext())
      count++;
    return count;
  }
  void display(){
    cout<<"[이중연결리스트 항목 수 =" << size() << "] : ";
    for(Node2* p = getHead(); p!= nullptr; p = p->getNext())
      p->display();
    cout << endl;
  }
};

int main(){
  DbLinkedList list;

  list.insert(0, new Node2(10)); //10
  list.insert(0, new Node2(20)); //20 10
  list.insert(1, new Node2(30)); //20 30 10
  list.insert(list.size(), new Node2(40)); //20 30 10 40
  list.insert(2, new Node2(50)); //20 30 50 10 40
  list.display();

  list.remove(2); //20 30 10 40
  list.remove(list.size()-1); // 20 30 10
  list.remove(0); // 30 10
  list.replace(1, new Node2(90)); // 30 90
  list.display();

  list.clear(); //모든 항목 삭제
  list.display();
  return 0;
}
```
- 단순 연결 리스트
  - 구현이 간단하고 쉬움, 메모리 사용이 이중 연결 리스트보다 적음
  - 뒤로 이동하는 것이 어려움, 삭제 작업 시 이전 노드에 접근이 필요하기 때문에 추가적인 순회가 필요함
- 원형 연결 리스트
  - 순환적인 구조를 가지기 때문에, 시작과 끝에서 작업을 수행할 때 효율적임
  - 구현이 복잡함, 리스트의 끝에 도달한 경우 별도 처리를 해줘야함
- 이중 연결 리스트
  - 양방향 순회가 가능하고, 삭제 작업이 단순 연결 리스트보다 효율적임
  - 구현이 복잡함, 메모리 사용이 단순 연결 리스트보다 많음
### 스택
- 한 쪽 끝에서만 자료를 넣거나 뺄 수 있는 제한적인 구조
- 후입선출
  - LIFO
```cpp
#include <iostream>
#define MAX_STACK_SIZE 100
using namespace std;

inline void error(const char* str){
  cout << str << endl;
  exit(1);
};

class ArrayStack{
//요소의 배열
int data[MAX_STACK_SIZE];
//요소의 개수
int top;

public: 
  ArrayStack(){top = -1;}
 ~ArrayStack(){}
  bool isEmpty(){return top == -1;}
  bool isFull() {return top == MAX_STACK_SIZE -1;}

  //맨 위에 항목 삽입
  void push(int e){
    if(isFull()) error("스택 포함 에러");
    data[++top] = e;
  }

  //맨 위의 요소를 삭제하고 반환
  int pop(){
    if(isFull()) error("스택 공백 에러");
    return data[top--];
  }

  //맨 위의 요소를 삭제하지 않고 요소 반환
  int peek(){
    if(isEmpty()) error("스택 공백 에러"));
    return data[top];
  }

  //스택 내용 화면 출력
  void display(){
    cout << "[스택 항목의 수 = " << top +1 << "] ==> ";
    for(int i =0; i<= top; i++)
        std::cout << "<" << data[i] << "> ";
    cout << endl;
  }
};

int main(){
  ArrayStack stack;
  for(int i =1; i< 10; i++){
    stack.push(i);
  }
  //1 2 3 4 5 6 7 8 9

  stack.display();
  stack.pop(); //1 2 3 4 5 6 7 8
  stack.pop(); //1 2 3 4 5 6 7
  stack.pop(); //1 2 3 4 5 6
  stack.display();

  return 0;
}
```

## 스택(2)

### 스택(2)
```cpp
//연결리스트를 이용한 스택 구현
#include <iostream>
#include <string>
using namespace std;

class Node{

public:
    char data;
    Node* next;
    Node(char data) : data(data), next(nullptr){}
};

class Stack{
private:
  Node* top;
public:
    Stack() : top(nullptr){}
    ~Stack(){
        Node* cur = top;
    while(cur != nullptr){
        Node* temp = cur;
        cur = cur -> next;
        delete temp;
        }
    }

    //새 노드를 스택의 top에 추가
    void push(char data){
        Node* node = new Node(data);
        node->next = top;
        top = node;
    }

    //top에 있는 노드 제거 후 값 리턴
    char pop(){
        if(isEmpty()){
            cout << "스택이 비어있습니다." << endl;
            exit(1);
        }
        char temp = top->data;
        Node* node = top;
        top = top->next;
        delete node;
        return temp;
    }

    //top에 있는 데이터를 반환만 함
    char peek(){
        if(isEmpty()){
            cout << "스택이 비어있습니다." << endl;
            exit(1);
        }
        return top->data;
    }

    bool isEmpty(){
        return top ==nullptr;
    }
};

//괄호의 짝이 맞는지 검사
bool checkBracket(string str){
    Stack stack;
    for(int i =0; i< str.length(); i++){
        char ch = str[i];
        if(ch == '(' || ch == '{' || ch == '['){
            //여는 괄호면 스택에 넣기
            stack.push(ch);
        }
        else if(ch == ')' || ch == '}' || ch ==']'){
            //닫는 괄호 확인
            if(stack.isEmpty()) return false;
            //닫는 괄호 전에 아예 여는 괄호가 안들어가 있는 경우
            char openCh = stack.pop();
            if((ch == ')' && openCh != '(') || (ch == '}' && openCh != '{') || (ch==']' && openCh !='[')){
                //짝이 다른 경우
                return false;
            }
        }
    }
    return stack.isEmpty();
}

int main(){
    string str = "{(a+b)*c}-d";
    if(checkBracket(str)){
        cout << str << " : 괄호가 맞습니다." << endl;
    }
    else{
        cout << str << " : 괄호가 틀렸습니다."<< endl; 
    }
    return 0;
}
```


## 큐(선형 큐, 원형 큐)
### 큐
- 큐(Queue)
  - 뒤에서 새로운 데이터가 추가, 앞에서 데이터가 하나씩 삭제
  - FIFO
  - 전단에서 삭제, 후단에서 삽입이 일어남
    - 각각 독립적으로 이뤄지므로 두 개 변수 필요
### 선형 큐(linear queue)
- 선형 큐
  - 큐의 시작(front)과 끝(rear)을 나타내는 포인터 2개 사용
    - front는 데이터가 처음 들어간 공간 바로 앞 자리를 가리키고 있음
  - 초기상태는 모두 `-1`로 설정
  - 삽입 시 rear +1 , 삭제 시 front +1
  - 단점
    - front와 rear의 위치 정해져 있어서 메모리가 낭비될 수 있음
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/922c89dd-9c92-46b4-a4f4-ad7502eff856)
      - (1)큐가 가득 찬 경우
        - 더 이상 데이터를 삽입할 수 없음
      - (2)가득 찬 건 아니지만, rear가 마지막 인덱스를 가리키고 있고 앞의 공간이 비어있는 경우
        - 이를 메모리 단편화 문제라 칭함
        - 데이터를 넣을 공간을 마련하기 위해 데이터를 전체적으로 앞으로 이동시켜야 함
        - 비효율적인 작업이 생김
### 원형 큐(Circular queue)
- 선형 큐의 문제를 해결한 큐
- 큐의 처음(front)와 끝(rear)가 연결되어 있는 구조
  - 선형 큐의 단점 (2)를 생각해보자
    - 더 이상 삽입할 수 없는 상황이 되더라도, 큐의 끝이 다시 큐의 시작 부분으로 이어지기 때문에 추가적으로 데이터를 삽입할 수 있음
- front와 rear의 초기값은 0임
- 마찬가지로 삽입 시 rear+1, 삭제 시 front+1
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b95affac-9d82-4656-800e-878bc34940ed)
  - 정확히 말하자면, rear = (rear+1)%M(큐 크기)
  - front = (front+1)%M
- 나머지 연산을 사용하여 front와 rear의 위치 계산
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/8e88a42f-babd-4771-9dff-b8a4184a376d)
    - 공백상태: front == rear
    - 포화상태: front % M(큐의 크기) == (rear+1)%M(큐의 크기)
    - 공백과 포화를 구분하기 위해, 항상 한 칸의 차이를 둬야함

### 배열을 이용한 원형 큐 구현
```cpp
#include <iostream>

inline void error(const char* str) {
	std::cerr << str << std::endl;
	exit(1);
};

#define MAX_QUEUE_SIZE 100

class CircularQueue {
	int front;
	// 첫 번째 요소 앞에 위치
	int rear;
	// 마지막 요소의 위치
	int data[MAX_QUEUE_SIZE];
public:
	CircularQueue() {
		front = rear = 0;
	}
	~CircularQueue(){}

	bool isEmpty() {
		return front == rear;
	}

	bool isFull() {
		return (rear + 1) % MAX_QUEUE_SIZE == front;
	}

	void enqueue(int val) {
		if (isFull())
			error("error:큐가 포화상태입니다\n");
		else {
			rear = (rear + 1) % MAX_QUEUE_SIZE; 
			//인덱스가 큐의 최대 크기를 넘어가지 않도록 나눠줌
			data[rear] = val;
			//마지막의 위치에 데이터 넣어주기!
		}
	}

	int dequeue() {
		if (isEmpty())
			error("Error:큐가 공백상태입니다\n");
		else {
			front = (front + 1) % MAX_QUEUE_SIZE;
			//인덱스가 큐의 최대 크기를 넘어가지 않도록 나눠줌
			return data[front];
		}
	}

	int peek() {
		if (isEmpty())
			error("Error:큐가 공백상태입니다\n");
		else
			return data[(front + 1) % MAX_QUEUE_SIZE];
		//인덱스가 큐의 최대 크기를 넘어가지 않도록 나눠줌
	}

	void display() {
		std::cout << "큐 내용 : ";
		int maxi = (front < rear) ? rear : rear + MAX_QUEUE_SIZE;

		for (int i = front + 1; i <= maxi; i++)
			std::cout << "[" << data[i % MAX_QUEUE_SIZE] << "] ";
		std::cout << std::endl;
	}
};

int main() {
	CircularQueue que;

	for (int i = 1; i < 10; i++)
		que.enqueue(i);

	que.display();
	que.dequeue();
	que.dequeue();
	que.dequeue();
	que.display();
	return 0;
}
// 큐 내용: 1 2 3 4 5 6 7 8 9
// 큐 내용: 4 5 6 7 8 9
// 공백 상태와 포화 상태에 대해 정확히 인지?
// 삽입, 삭제시?
// 초기값?
```

### 연결 리스트를 이용한 큐 
- 장점
  - 선형 큐의 메모리 단편화 문제 해결 가능
  - 크기가 제한되지 않고 필요한 메모리만 사용할 수 있음
- 단점
  - 코드가 더 복잡하고 링크 필드 때문에 메모리 공간을 더 사용하게 됨
- front: 가장 먼저 삽입된 노드
- rear: 가장 최근에 삽입된 노드
- 삽입
  - 공백 상테에서의 삽입
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/ce67c1bf-f20e-45fd-ae1e-24aae2b3f96b)
      - front와 rear 모두 새로운 노드 p를 가리킴
  - 비 공백상태에서의 삽입
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/9337e2e5-0ef9-40d5-8e65-a273086fe60f)
    - `rear->link = p;`
    - `rear = p;`
- 삭제
  - 노드가 두 개 이상인 경우
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/a305e4d2-204b-4821-ba3c-a016c2d0f80e)
    - front가 가리키는 노드 A를 p가 가리키도록 함
      - `p = front;`
    - front가 다음 노드 B를 가리키도록 함
      - `front = p->link;`
  - 노드가 하나인 경우
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/848734ec-db21-4a70-b376-f84f34d62ba0)
      - rear도 NULL로 만들어 줘야함

```cpp
#include <iostream>

class Node
{
	Node* link;
	int data;
public:
	Node(int val =0) : data(val), link(NULL) {}
	Node* getLink() { return link; }
	void setLink(Node* next) { link = next; }
	void display() { std::cout << " <" << data << ">"; }
};

class LinkedQueue
{
	Node* front;
	Node* rear;
public:
	LinkedQueue() : front(NULL), rear(NULL) {} //둘 다 null로 초기화
	~LinkedQueue() { while (!isEmpty()) delete dequeue(); }
	// 다 없어질 때 까지 delete

	bool isEmpty() { return front == NULL; }

	void enqueue(Node* n) {
		if (isEmpty()) front = rear = n;
		// 비어있었으면, fornt와 rear 모두 n에 할당
		else {
			rear->setLink(n);
			//rear의 링크 필드를 n으로 설정
			rear = n;
			//rear를 n으로 설정(마지막 노드니까!!)
		}
	}

	Node* dequeue() {
		if (isEmpty()) return NULL;
		Node* temp = front;
		front = front->getLink();
		if (front == NULL) rear = NULL;
		//노드가 하나일때 dequeue를 진행한 경우임, front뿐 아니라 rear도 null로 만들어줌
		return temp;
	}

	Node* peek() { return front; }

	void display() {
		std::cout << "[큐 내용] : ";
		for (Node* p = front; p != NULL; p = p->getLink())
			p->display();
		std::cout << std::endl;
	}
};

int main()
{
	LinkedQueue que;
	for (int i = 1; i < 10; i++)
		que.enqueue(new Node(i));

	que.display();
	delete que.dequeue();
	delete que.dequeue();
	delete que.dequeue();
	que.display();

	return 0;
}
// 삽입 - 공백, 비공백
// 삭제 - 노드 2개 이상, 노드 1개
```

## 덱
### 덱
- 덱(Double-Ended Queue, Deque)
  - 전단(front)과 후단(rear)에서 모두 삽입과 삭제가 가능한 큐
  - 양쪽 끝에서 높은 효율성으로 데이터 처리 가능
- 추가 연산
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/26159bc0-2df0-4af2-bf6d-cafbf405fb4c)
    - 음수가 되지 않기 위해 `+MAX_QUEUE_SIZE`가 있음을 기억하라 
    - `delete_rear(), add-front(), get_real()`
    - 후단에서 삭제, 전단으로 삽입, 마지막으로 들어온 값 확인
    - 원래 peek은 처음으로 들어온 값을 확인했었음

### 배열을 이용한 원형 덱 구현
```cpp
#include <iostream>

inline void error(const char* str) {
	std::cerr << str << std::endl;
	exit(1);
};

#define MAX_QUEUE_SIZE 100

class CircularQueue {
protected:
	int front;
	// 첫 번째 요소 앞에 위치
	int rear;
	// 마지막 요소에 위치
	int data[MAX_QUEUE_SIZE];
public:
	CircularQueue() {
		front = rear = 0;
	}
	~CircularQueue() {}

	bool isEmpty() {
		return front == rear;
	}

	bool isFull() {
		return (rear + 1) % MAX_QUEUE_SIZE == front;
	}

	void enqueue(int val) {
		if (isFull())
			error("error:큐가 포화상태입니다\n");
		else {
			rear = (rear + 1) % MAX_QUEUE_SIZE;
			//인덱스가 큐의 최대 크기를 넘어가지 않도록 나눠줌
			data[rear] = val;
			//마지막의 위치에 데이터 넣어주기!
		}
	}

	int dequeue() {
		if (isEmpty())
			error("Error:큐가 공백상태입니다\n");
		else {
			front = (front + 1) % MAX_QUEUE_SIZE;
			//인덱스가 큐의 최대 크기를 넘어가지 않도록 나눠줌
			return data[front];
		}
	}

	int peek() {
		if (isEmpty())
			error("Error:큐가 공백상태입니다\n");
		else
			return data[(front + 1) % MAX_QUEUE_SIZE];
		//인덱스가 큐의 최대 크기를 넘어가지 않도록 나눠줌
	}

	void display() {
		std::cout << "큐 내용 : ";
		int maxi = (front < rear) ? rear : rear + MAX_QUEUE_SIZE;
		//maxi가 의미하는 바는?

		for (int i = front + 1; i <= maxi; i++)
			std::cout << "[" << data[i % MAX_QUEUE_SIZE] << "] ";
		std::cout << std::endl;
	}
};

class CircularDeque : public CircularQueue {
public:
	CircularDeque(){ }
	void addRear(int val) { enqueue(val); }
	int deleteFront() { return dequeue(); }
	int getFront() { return peek(); }
	void addFront(int val) {
		if (isFull())
			error(" error: 덱이 포화상태입니다\n");
		else {
			data[front] = val;
			front = (front - 1 + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
		}
	}

	int deleteRear() {
		if (isEmpty())
			error("Error: 덱이 공백상태입니다\n");
		else {
			int ret = data[rear];
			rear = (rear - 1 + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
			return ret;
		}
	}

	int getRear() {
		if (isEmpty())
			error(" Error: 덱이 공백상태입니다\n");
		else return data[rear];
	}

	void display() {
		std::cout << "덱의 내용: ";
		int maxi = (front < rear) ? rear : rear + MAX_QUEUE_SIZE;
		for (int i = front + 1; i <= maxi; i++)
			std::cout << "[" << data[i % MAX_QUEUE_SIZE] << "] ";
		std::cout << std::endl;
	}
};

int main() {
	CircularDeque deq;

	for (int i = 1; i < 10; i++) {
		if (i % 2) deq.addFront(i);
		else deq.addRear(i);
	}

	deq.display();

	deq.deleteFront();
	deq.deleteRear();
	deq.deleteFront();
	deq.display();

	return 0;
  // 9 7 5 3 1 2 4 6 8 
  // 5 3 1 2 4 6 
}
//addRear()(enqueue 사용)과 deleteFront()(dequeue 사용)는 CircularQueue와 같음
//addRear()는 rear를 +1(%M), deleteFront()는 front+1(%M)
//addFront()는 전단으로 데이터 삽입, deleteRear()는 후단에 데이터 삭제
//새롭게 추가된 메서드 2개에서는, rear와 front 감소 연산이 두번째줄에 있다는 사실을 명심하라
//또한, front는 데이터 앞에 위치하고, rear는 데이터의 마지막에 위치함을 명심하라
//getRear()는 후단에서 peek이다.
```

### 이중 연결 리스트를 이용한 덱
- 이중 연결 리스트
  - 하나의 노드가 이전 노드와 다음 노드를 가리키는 두 개의 링크를 가짐
```cpp
#include <iostream>
#define MAX_STACK_SIZE 100

using namespace std;

inline void error(char* str){
  cout << str << endl;
  exit(1);
};

class Node2{
  Node2* prev;
  Node2* next;
  int data;

public:
  Node2(int val =0) : data(val), prev(nullptr), next(nullptr){}
  Node2* getPrev() {return prev;}
  Node2* getNext() {return next;}
  void setPrev(Node2* p){prev = p;}
  void setNext(Node2* n){next = n;}
  void display() {cout << " <" << data << ">";}
  bool hasData(int val){return data == val;}

//자신의 다음에 새로운 노드 삽입
void insertNext(Node2* n){
  if(n != nullptr){
    n->prev = this;
    n->next = next;
    if(next != nullptr) next ->prev = n;
    next = n;
  }
}

Node2* remove(){
  if(prev!=nullptr) prev->next = next;
  if(next!=nullptr) next->prev = prev;
  return this;
}
};

class DbLinkedList{
  Node2 org;
public:
  DbLinkedList() : org(0){}
  ~DbLinkedList() { clear(); }

  void clear() { while(!isEmpty()) delete remove(0);}
  Node2* getHead() { return org.getNext(); }
  bool isEmpty() {return getHead() == nullptr;}

  Node2* getEntry(int pos){
    Node2* n = &org;
    for (int i = -1; i< pos; i++, n = n->getNext())
      if(n == nullptr) break;
    return n;
  }

  void insert(int pos, Node2* n){
    Node2* prev = getEntry(pos-1);
    if(prev != nullptr)
      prev -> insertNext(n);
  }

  Node2* remove(int pos){
    Node2* n = getEntry(pos);
    return n-> remove();
  }

  Node2* find(int val){
  for(Node2* p = getHead(); p!=nullptr; p = p->getNext())
    if(p->hasData(val)) return p;
    return  nullptr;
}

  void replace(int pos, Node2* n){
    Node2* prev = getEntry(pos-1);
    if(prev != nullptr){
      delete prev->getNext()->remove();
      prev->insertNext(n);
    }
  }

  int size(){
    int count = 0;
    for(Node2* p = getHead(); p!=nullptr; p = p->getNext())
      count++;
    return count;
  }
  void display(){
    cout<<"[이중연결리스트 항목 수 =" << size() << "] : ";
    for(Node2* p = getHead(); p!= nullptr; p = p->getNext())
      p->display();
    cout << endl;
  }
};

class LinkedDeue : public DbLinkedList
{
  public:
      void addFront(Node2* n){
        insert(0,n);
        }
      Node2* deleteFront( ) {
        return remove(0);
        }
      Node2* getFront( ){
        return getEntry(0);
      }
      void addRear(Node2* n){
        insert(size(),n);
      }
      Node2* deleteRear(){
        return remove(size()-1;)
      }
      Node2* getRear(){
        return getEntry(size()-1);
      }
}
int main(){
  LinkedDeque deq;
  for(int i=1; i<10; i++){
    if(i%2)
      deq.addFront(new Node2(i));
    else deq.addRear(new Node2(i));
  }
  deq.display();
  delete deq.deleteFront();
  delete deq.deleteRear();
  delete deq.deleteFront();
  deq.display();

  //1 -> 1 2 -> 3 1 2 -> 3 1 2 4 -> 5 3 1 2 4
  // .... 9 7 5 3 1 2 4 6 8 
  // 7 5 3 1 2 4 6 8
  // 7 5 3 1 2 4 6
  // 5 3 1 2 4 6
}
```

## 트리
### 트리
- 트리
  - 하나의 루트 노드에서 시작해 여러 개의 자식 노드들로 이어짐
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/09ce8a3c-54a3-4d27-8366-c5587f2c0e42)
  - 노드
    - 트리의 구성요소
  - 간선
    - 노드 연결 선
  - 루트 노드
  - 서브 트리
    - 하나의 노드와 자손들
  - 단말 노드
    - 자식이 없는 노드(E,F,G,H,I,J)
  - 비단말노드
    - 자식을 갖는 노드(A,B,C,D)
  - 형제 노드
    - 같은 부모를 갖는 노드
- ![image](https://github.com/googoo9918/TIL/assets/102513932/e70c27ee-05b9-4acb-844a-a8e2faeb1b80)
  - 노드의 크기
    - 자신을 포함한 모든 자손 노드의 개수
      - 노드 A의 크기 : 10
  - 노드의 레벨
    - 트리의 특정 깊이를 갖는 노드의 집합(깊이의 최대치)
    - 0~2
  - 노드의 깊이
    - 루트에서 어떤 노드에 도달하기 위해 거쳐야 하는 간선의 수(조상의 수)
    - 노드 J의 깊이: 2
  - 노드의 차수
    - 노드의 자식 노드 수
    - A:3, C:1, D:2
  - 트리의 차수
    - 트리의 최대 차수
    - A와 B가 자식 노드를 가장 많이 가짐, 3
  - 트리의 높이
    - 노드 높이의 최대값
  - 트리에서 루트에서 특정 노드로 가는 경로는 유일
    - 임의의 두 노드 간의 경로도 유일
  - 한 개의 루트 노드만이 존재, 모든 자식 노드는 한 개의 부모 노드만을 가짐
### 이진 트리
- 이진 트리
  - 자식 노드를 최대 2개 까지만 가질 수 있는 트리
  - 이진 트리의 모든 노드는 차수가 2 이하임
  - 이진 트리는 노드를 하나도 갖지 않을 수 있음
    - 서브 트리가 공집합일 수 있음
  - 이진 트리에는 서브 트리 간의 순서가 존재
  - 노드의 개수가 n개이면 간선의 개수는 n-1
  - 높이가 h -> 최소 h개 ~ 최대 2^h-1개의 노드를 가짐
  - n개 노드의 이진 트리 높이 -> 최소, 최대 다 그려보자
    - [로그2(n+1)]~ n개임
- 포화 이진 트리
  - 트리의 각 레벨에 노드가 꽉 차 있는 이진 트리
  - 레벨 단위로 왼쪽 -> 오른쪽으로 번호 부여
- 완전 이진 트리
  - 모든 레벨에서 노드가 왼쪽 -> 오른쪽으로 순차적으로 채워져 있는 이진 트리
  - 포화 이진 트리도 완전 이진 트리의 한 종류겠지
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e3c683e8-ee1a-4a99-8203-737a633cefcd)

### 배열을 이용한 이진 트리
- 모든 이진 트리를 포화 이진 트리라 가정
  - 각 노드에 번호를 붙여서 배열의 인덱스로 사용
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f58587f3-c111-4130-b31c-5df8e9aec7fa)

### 링크를 이용한 이진 트리 및 전중후 순회
- 더블링크드리스트를 이용해 부모 노드가 자식 노드를 가리키게 함
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c120f0de-b8c5-4a0a-8ed4-26ce301515ab)
- 전위 순회
  - 루트 -> 왼쪽 자식 -> 오른쪽 자식
- 중위 순회
  - 왼쪽 자식 -> 루트 -> 오른쪽 자식
- 후위 순회
  - 왼쪽 자식 -> 오른쪽 자식 -> 루트
- ![image](https://github.com/googoo9918/TIL/assets/102513932/ad3b25dc-eeb1-49f8-a77a-b863980f1ebc)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/08f17d59-eb13-4320-88aa-1f1926cd321e)
  - 전위 순회: A, B, D, H, P, Q, I, R, S, E, J, K, C, F, L, M, G, N, O

  - 중위 순회: P, H, Q, D, R, I, S, B, J, E, K, A, L, F, M, C, N, G, O

  - 후위 순회: P, Q, H, R, S, I, D, J, K, E, B, L, M, F, N, O, G, C, A






```cpp
#include <iostream>
using namespace std;

//이진 트리 노드 클래스
class BinaryNode 
{
protected:
	int data;
	BinaryNode* left;
	BinaryNode* right;

public:
	BinaryNode(int val=0, BinaryNode *l=NULL, BinaryNode *r=NULL)
		: data(val), left(l), right(r){}
	~BinaryNode() { }
	
	void setData(int val) { data = val; }
	void setLeft(BinaryNode* l) { left = l; }
	void setRight(BinaryNode* r) { right = r; }
	int getData() { return data; }
	BinaryNode* getLeft() { return left; }
	BinaryNode* getRight() { return right; }
	bool isLeaf() { return left == NULL && right == NULL; }
};


//이진 트리 클래스
class BinaryTree
{
	BinaryNode* root;
public:
	BinaryTree(): root(NULL){ }
	~BinaryTree() { }

	void setRoot(BinaryNode* node) { root = node; }
	BinaryNode* getRoot() { return root; }
	bool isEmpty() { return root == NULL; }
	
	void display() {
		cout << "Binary Tree:" << endl;
		if (isEmpty()) {
			cout << "Empty tree." << endl;
		}
		else {
			display(root, 0);
		}
	}
  //트리의 현재 상태 출력
	void display(BinaryNode* node, int depth) {
		if (node == NULL) {
			return;
		}
		for (int i = 0; i < depth; i++) {
			cout << "  ";
		}
		cout << "- " << static_cast<char>(node->getData()) << endl;

		display(node->getLeft(), depth + 1);
		display(node->getRight(), depth + 1);
	}

//중위순회
	void inorder() { cout << "\n inorder: "; inorder(root); }
	void inorder(BinaryNode* node) {
		if (node != NULL) {
			inorder(node->getLeft());
			cout << "[" << static_cast<char>(node->getData()) << "] ";
			inorder(node->getRight());
		}
	}

//전위순회
	void preorder() { cout << "\n preorder: "; preorder(root); }
	void preorder(BinaryNode* node) {
		if (node != NULL) {
			cout << "[" << static_cast<char>(node->getData()) << "] ";
			preorder(node->getLeft());
			preorder(node->getRight());
		}
	}

//후위순회
	void postorder() { cout << "\n postorder: "; postorder(root); }
	void postorder(BinaryNode* node) {
		if (node != NULL) {
			postorder(node->getLeft());
			postorder(node->getRight());
			cout << "[" << static_cast<char>(node->getData()) << "] ";
		}
	}
};


int main()
{
	BinaryTree tree;

	BinaryNode* d = new BinaryNode('D', NULL, NULL);
	BinaryNode* e = new BinaryNode('E', NULL, NULL);
	BinaryNode* b = new BinaryNode('B', d, e);
	BinaryNode* f = new BinaryNode('F', NULL, NULL);
	BinaryNode* c = new BinaryNode('C', f, NULL);
	BinaryNode* a = new BinaryNode('A', b, c);

	tree.setRoot(a);

	tree.display();

	tree.inorder();
	tree.preorder();
	tree.postorder();
	return 0;
}
```

## 트리(2)
### 레벨 순회
- 레벨 순회
  - 이진 트리의 노드를 레벨별로 순서대로 방문하는 방법
  - 큐로 구현
- 1. 트리의 루트 노드를 큐에 삽입
- 2. 큐가 빌 때 까지 다음 작업 반복
  - 큐에서 노드를 하나 꺼내서 방문, 꺼낸 노드의 자식 노드들을 큐에 순서대로 삽입
- 3. 트리의 모든 노드를 방문할 때 까지 반복
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1fdddd2a-5357-4007-a5f9-8238a654b240)
- 레벨 순회 알고리즘
```cpp
void levelorder(){
  cout << "\n levelorder: ";
  if(!isEmpty()){
    CircularQueue q;
    q.enqueue(root);
    while(!q.isEmpty()){
      BinaryNode* n = q.dequeue();
      if(n!= NULL){
        cout << "[" << static_cast<char(n->getData()) << "] " ;
        q.enqueue(n->getLeft());
        q.enqueue(n->getRight());
      }
    }
  }
  cout << "\n";
}
```

## 이진 탐색 트리
### 탐색
- 탐색 관련 용어
  - 컴퓨터의 탐색은 레코드의 집합에서 특정 레코드를 찾아내는 작업을 의미
  - 레코드
    - 트리에 저장되는 개별 항목이나 데이터 단위
  - 필드
    - 특정 데이터 유형을 나타내는 값
  - 테이블
    - 레코드의 모음
  - 키
    - 각 레코드를 고유하게 식별하는 값
  - 주요키
    - 테이블 내에서 각 레코드를 고유하게 식별하는 키
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/2752d571-2045-4839-9f23-b39ea56ba54c)
- 이진 탐색 트리
  - 이진 트리 기반의 탐색을 위한 자료 구조
  - 모든 노드는 유일한 키를 가짐
  - 왼쪽 서브 트리의 키들은 루트의 키보다 작음
  - 오른쪽 서브 트리의 키들은 루트의 키보다 큼
  - 왼쪽과 오른쪽 서브 트리도 이진 탐색 트리임
    - 이진 탐색을 중위 순회화면 오름차순으로 정렬된 값을 얻을 수 있음
- 탐색 연산
  - 루트 노드부터 시작하여 특정 값을 찾을 때까지 왼쪽 또는 오른쪽 자식 노드로 이동하며 수행
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c9c358dd-1941-44e7-b629-9deb4c9d9bf1)
- 탐색 연산 구현
```cpp
//n에는 root를 넣어줘야겄제
BinaryNode* search(BinaryNode* n, int key) {
		if (n == NULL) return NULL;

		if (key == n->getData())
			return n;
		else if (key < n->getData())
			return search(n->getLeft(), key);
		else
			return search(n->getRight(), key);
	}
```

### 삽입 연산
  - 이진 탐색 트리에 원소를 삽입하기 위해 탐색이 선행되어야 함
  - 탐색에 실패한 위치가 새로운 노드를 삽입하는 위치
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/16e0fff4-319a-4c08-b2d9-583858aa8c63)
- 삽입 연산
```cpp
//insert(root,n)으로 사용
void insert(BinaryNode* r, BinaryNode* n) {
		if (n->getData() == r->getData())
			return;
		else if (n->getData() < r->getData()) {
			if (r->getLeft() == NULL)
				r->setLeft(n);
			else
				insert(r->getLeft(), n);
		}
		else {
			if (r->getRight() == NULL)
				r->setRight(n);
			else
				insert(r->getRight(), n);
		}
	}
```

### 삭제 연산
- 삭제할 값을 가진 노드를 탐색, 찾지 못한 경우 연산 중단
- 삭제할 노드의 유형에 따른 처리
  - 단말 노드인 경우
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/c3a0f597-55d8-4011-8569-07c3563fab8f)
  - 자식이 하나인 노드일 경우
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/2c54a2cb-00d3-4250-86e4-3dab00582a23)
  - 자식이 두 개인 노드일 경우
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/1dfb6f50-f63b-49d9-9f20-06251a1319eb)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/f7088b91-4090-4450-b06f-efca714308b7)
  - 삭제후 재정렬
```cpp
void remove(int data) {
		if (isEmpty()) return;
		
		BinaryNode* parent = NULL;
		BinaryNode* node = root;
		while (node != NULL && node->getData() != data) {
			parent = node;
			node = (data < node->getData())
				? node->getLeft()
				: node->getRight();
		}

		if (node == NULL) {
			printf(" Error: key is not in the tree1\n");
			return;
		}
		else remove(parent, node);
	}

	void remove(BinaryNode* parent, BinaryNode* node) {

		//case 1(leaf 노드인 경우)
    //부모 노드를 찾아서 연결을 끊어주면 된다
		if (node->isLeaf()) {
			if (parent == NULL) root = NULL;
			else {
				if (parent->getLeft() == node)
					parent->setLeft(NULL);
				else
					parent->setRight(NULL);
			}
		}

		// case 2(자식이 하나인 노드일 경우)
    // 노드는 삭제하고 서브 트리는 부모 노드에 붙여준다
		else if (node->getLeft() == NULL || node->getRight() == NULL) {
			BinaryNode* child = (node->getLeft() != NULL)
				? node->getLeft()
				: node->getRight();
			if (node == root)
				root = child;
			else {
				if (parent->getLeft() == node)
					parent->setLeft(child);
				else
					parent->setRight(child);
			}
		}

    //case 3(자식이 두 개인 노드일 경우)
		else {
			BinaryNode* succp = node;
			BinaryNode* succ = node->getRight();
			
      //오른쪽 서브 트리에서 제일 작은 값 succ에 저장
      //succp는 오른쪽 서브 트리에서 제일 작은 값(후계 노드)의 부모 노드임
      while (succ->getLeft() != NULL) {
				succp = succ;
				succ = succ->getLeft();
			}

      //while문이 한 번도 실행되지 않은 경우, if문이 false일 수 있음
			if (succp->getLeft() == succ)
				succp->setLeft(succ->getRight());
			else //succp의 right를 오른쪽 서브트리 가장 높은 레벨 오른쪽 노드와 연결
				succp->setRight(succ->getRight());

			node->setData(succ->getData());

			node = succ;
		}
		delete node;
	}
```

## 그래프
- 그래프
  - 연결되어 있는 객체 간 관계를 표현하는 자료구조
- 오일러 정리
  - 모든 정점에 연결된 간선의 수가 짝수이면 오일러 경로 존재
- 그래프 G는 (V,E)로 표시
  - 정점 또는 노드
    - V(G)는 그래프 G의 정점들의 집합
  - 간선 또는 링크
    - E(G)는 그래프 G의 간선들의 집합
- 무방향 그래프
  - 두 정점을 연결하는 간선에 방향이 없음
  - (A,B)로 표현
- 방향 그래프
  - 간선에 방향이 있는 그래프
  - <A,B>로 표현
- 가중치 그래프
  - 정점을 연결하는 간선에 가중치를 할당한 그래프
- 부분 그래프
  - 기존 그래프에서 일부 정점이나 간선을 제외해서 만든 그래프
- 그래프 표현 예시
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/30814cad-5630-42f6-b209-38acef0f2b0c)
    - V(G1) = {0, 1, 2, 3}
    - E(G1) = {(0,1), (0,2), (0,3), (1,2), (2,3)}
    - V(G2) = {0, 1, 2, 3}
    - E(G2) = {(0,1),(0,2)}
    - V(G3) = {0,1,2}
    - E(G3) = {<0,1>, <1,0>, <1,2>}
- 그래프 용어
  - 인접 정점
    - 하나의 정점에서 간선에 의해 직접 연결된 정점
  - 정점의 차수
    - 무방향 그래프에서 하나의 정점에 인접한 정점의 수
      - 무방향 그래프에 존재하는 정점의 모든 차수의 합
        - 그래프의 간선 수의 2배
  - 진입 차수
    - 외부에서 오는 간선의 수
  - 진출 차수
    - 외부로 나가는 간선의 수
  - 그래프의 경로
    - v1에서 vj까지 간선으로 연결된 정점을 순서대로 나열한 리스트
  - 단순 경로
    - 모두 다른 정점으로 구성된 경로
  - 사이클
    - 시작 지점과 종료 지점이 동일한 경로
  - 경로의 길이
    - 경로를 구성하는데 사용된 간선의 수
  - 연결 그래프
    - 모든 정점 쌍에 대한 경로 존재
  - 트리
    - 그래프의 특수한 형태, 사이클을 갖지 않는 연결 그래프
  - 완전 그래프
    - 모든 정점이 연결되어 있는 그래프
    - n개 정점을 가진 무방향 완전 그래프의 간선 수 = n(n-1)/2

### 인접 행렬을 이용한 그래프
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b3e7efa7-7323-4b6d-aa23-c5afb1d2910b)
```cpp
#include <iostream>

#define MAX_VTXS 256
//표현 가능한 최대 정점 개수

class AdjMatGraph
{
protected:
	int size; //정점 개수
	char vertices[MAX_VTXS]; //정점 이름
	int adj[MAX_VTXS][MAX_VTXS]; //인접 행렬

public:
	AdjMatGraph() { reset(); }
	~AdjMatGraph() { }

	char getVertext(int i) { return vertices[i]; }
	int getEdge(int i, int j) { return adj[i][j]; }
	void setEdge(int i, int j, int val) { adj[i][j] = val; }
	bool isEmpty() { return size == 0; }
	bool isFull() { return size >= MAX_VTXS; }

  //그래프 초기화 -> 공백 상태 그래프
	void reset() {
		size = 0;
		for (int i = 0; i < MAX_VTXS; i++)
			for (int j = 0; j < MAX_VTXS; j++)
				setEdge(i, j, 0);
	}

  //정점 삽입 연산
	void insertVertex(char name) {
		if (!isFull()) vertices[size++] = name;
		else std::cout << "Error: 그래프 정점의 개수 초과" << std::endl;
	}

  //간선 삽입 연산(무방향 그래프인 경우)
	void insertEdge(int u, int v) {
		setEdge(u, v, 1);
		setEdge(v, u, 1);
    //(방향 그래프인 경우 v,u는 삭제됨)
	}

	void display() {
		std::cout << size << std::endl;
		for (int i = 0; i < size; i++) {
			std::cout << getVertext(i) << " ";
			for (int j = 0; j < size; j++)
				std::cout << " " << getEdge(i, j);
			std::cout << std::endl;
		}
	}
};

int main()
{
	AdjMatGraph g;

	for (int i = 0; i < 4; i++) {
		g.insertVertex('A' + i);
	}

	g.insertEdge(0, 1); //a b 연결
	g.insertEdge(0, 3); //a d 연결
	g.insertEdge(1, 2); //b c 연결
 	g.insertEdge(1, 3); //b d 연결
	g.insertEdge(2, 3); //c d 연결

	std::cout << "인접 행렬로 표현한 그래프" << std::endl;

	g.display();
}
```

### 인접 리스트로 표현한 그래프
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b1412455-a81c-49b7-a52a-0139702dbe2f)
  - 각 정점이 연결 리스트를 가짐
  - 인접한 정점들을 연결리스트로 표현
```cpp
#include <iostream>
#include <fstream>

#define MAX_VTXS 256

class Node
{
protected:
	int id; //정점의 id
	Node* link; //정점의 이름
public:
	Node(int i, Node* l = NULL) : id(i), link(l) { }
	~Node(void) {
		if (link != NULL)
			delete link;
	}

	int getId() {
		return id;
	}
	Node* getLink() {
		return link;
	}
	void setLink(Node* l) {
		link = l;
	}

};

class AdjListGraph
{
	int size; //정점 개수
	char vertices[MAX_VTXS]; //정점 정보
	Node* adj[MAX_VTXS]; //각 정점의 인접 리스트

public:
		AdjListGraph(void) : size(0){}
		~AdjListGraph(void) { reset(); }

		void reset(void) {
			for (int i = 0; i < size; i++)
				if (adj != NULL) delete adj[i];
			size = 0;
		}
		bool isEmpty() { return(size == 0); }
		bool isFull() { return(size >= MAX_VTXS); }
		char getVertex(int i) { return vertices[i]; }

		void insertVertex(char val) { //정점 삽입 연산
			if (!isFull()) {
				vertices[size] = val;
				adj[size++] = NULL;
			}
			else std::cout << "Error: 그래프 정점 개수 초과" << std::endl;
		}

    //간선 삽인 연산
		void insertEdge(int u, int v) {
			adj[u] = new Node(v, adj[u]);
			adj[v] = new Node(u, adj[v]);
		}

		void display() {
			std::cout << size << std::endl;
			for (int i = 0; i < size; i++) {
				std::cout << getVertex(i) << " ";

				for (Node* v = adj[i]; v != NULL; v = v->getLink())
					std::cout << "  " << getVertex(v->getId());
				std::cout << std::endl;
			}
		}

		void load(const std::string& filename) {
			std::ifstream file(filename);

			int n;
			file >> n;
			for (int i = 0; i < n; i++) {
				char str[80];
				int val;
				file >> str;
				insertVertex(str[0]);
				for (int j = 0; j < n; j++) {
					file >> val;
					if (val != 0)
						insertEdge(i, j);
				}
			}
		}
};


int main()
{
	AdjListGraph g;

	for (int i = 0; i < 4; i++) {
		g.insertVertex('A' + i);
	}

	g.insertEdge(0, 1);
	g.insertEdge(0, 3);
	g.insertEdge(1, 2);
	g.insertEdge(1, 3);
	g.insertEdge(2, 3);

	std::cout << "인접 리스트로 표현한 그래프" << std::endl;

	g.display();
}
// 간선을 추가하는 메서드가 잘 이해가 안될 수 있는데
// 순서에 맞춰 잘 이해해보자.
```

## 그래프(2)

### DFS(깊이 우선 탐색)
- DFS(depth-first search)
  - 한 정점에서 시작해 다음 분기로 넘어가기 전에 해당 분기를 완전히 탐색
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b53df2cf-1aa1-4901-9cc4-78565aadf46c)

### BFS(너비 우선 탐색)
- BFS(breadth-first search)
  - 한 정점에서 시작해 인접한 모든 정점을 우선 탐색
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/34b5ba68-813d-4a48-bab8-aca3e0c7efc2)

### 신장 트리
- 그래프에서 모든 정점을 포함하면서 사이클이 없는 부분 그래프
- 모든 정점을 연결하면서 최소한의 간선만을 사용
  - n-1개의 간선을 가짐
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/7c8e2f42-565f-4688-b0d8-ab00f135bfe0)

### 위상 정렬
- 방향 그래프에 대해 정점들의 선행 순서를 위배하지 않으면서 모든 정점을 나열
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/cdd9ae25-d7e2-4ba6-b5c6-fa3bf4ee5c77)
    - 먼저 진입 차수가 0인 정점을 선택 후 정점과 연결된 모든 간선을 삭제
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b44a3097-0929-4f28-91ff-f6deca69b5e1)

## template, pair
### 템플릿
- 템플릿 함수는 한 번의 함수 정의로 서로 다른 자료형에 대해 유연하게 적용 가능
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/e6d7043f-4a2d-41ad-97ef-dd9a0e0089f5)
    - 함수나 클래스를 개별적으로 다시 작성하지 않아도 여러 자료형으로 사용 가능
    - 여러 자료형을 템플릿 인자로 받아 함수, 클래스, 구조체 내부에서 활용 가능
### 함수 템플릿
  - 함수의 일반화된 버전을 작성할 수 있게 해주는 기능
  - 함수의 매개변수나 반환 값의 타입을 일반화된 형태로 선언
    - 이후 특정한 타입으로 대체하여 함수를 인스턴스화
```cpp
template <typename T>
T function(T arg1, T arg2)
{
  //함수 구현
}
```
- 명시적 특수화
  - 특정 타입에 대한 명시적 특수화를 제공, 해당 타입에 대한 특별한 동작을 정의 가능
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/a3f52560-85bb-4e04-8528-4cd7af05ee91)
```cpp
template <>
return_type function_name<specific_type>(arguments)
{
  //특수화된 구현
}
```

### 클래스 템플릿
- 클래스의 일반화된 버전을 작성할 수 있게 해주는 기능
  - 클래스의 멤버 변수, 멤버 함수, 타입 등을 일반화된 형태로 선언
    - 이를 특정한 타입으로 대체하여 클래스를 인스턴스화
```cpp
template <typename T>
class ClassName
{
  //클래스 멤버 선언
}
```
- 중첩 클래스 템플릿
  - 클래스 내부에 정의된 템플릿 클래스
  - 클래스나 클래스 템블릿 내에 또 다른 템플릿을 중첩 정의 가능
```cpp
template <typename T>
class OuterCalss
{
  template <typename U>
  class InnerClass{
    //중첩 클래스 템플릿의 정의
  };
};
```
- ![image](https://github.com/googoo9918/TIL/assets/102513932/9a618f92-dd2b-4ddb-a184-6a3d4b8e4122)
- 명시적 특수화
  - 클래스 템플릿의 특정 템플릿 타입에 대해 특별한 동작을 정의할 수 있게 해줌
```cpp
template<>
class TemplateClass<specific_type>
{
  //특수화된 클래스 정의
}
```
- ![image](https://github.com/googoo9918/TIL/assets/102513932/9558b366-7c8c-49f6-a8b5-a88ba68f47fe)

### 템플릿 구현
```cpp
#include <iostream>

template <typename T>
class DynamicArray {
private:
	T* arr;
	int size;

public:
	DynamicArray(int size) : size(size) {
		arr = new T[size];
		}

	~DynamicArray() {
		delete[] arr;
	}

	void setValue(int index, T value) {
		arr[index] = value;
	}

	T getValue(int index) {
		return arr[index];
	}
};

int main() {
	DynamicArray<int> intArray(5);
	intArray.setValue(0, 10);
	intArray.setValue(1, 20);

	std::cout << "Value at index 0: " << intArray.getValue(0) << std::endl;
	std::cout << "Value at index 1: " << intArray.getValue(1) << std::endl;
  //Value at index 0: 10
  //Value at index 0: 20

	DynamicArray<double> doubleArray(3);
	doubleArray.setValue(0, 3.14);
	doubleArray.setValue(1, 2.17);
	
	std::cout << "Value at index 0: " << doubleArray.getValue(0) << std::endl;
	std::cout << "Value at index 1: " << doubleArray.getValue(1) << std::endl;
  //Value at index 0: 3.14
  //Value at index 0: 2.71
	return 0;
}
```

### STL
- Standard template librayr(STL)
  - cpp 템플릿을 사용해 만든 표준 라이브러리
  - 일반적인 자료 구조 컨테이너와 알고리즘이 구현되어 있음

### Pair
- pair 클래스
  - 두 개의 값(쌍)을 담는 클래스 템플릿
  - 두 값을 묶어 하나의 객체로 다룰 수 있으며, 보통 두 값의 쌍을 표현하거나 반환하기 위해 사용
  - `#include<utility>` 헤더파일에 존재
  - 선언 방식
    - `pair<자료형, 자료형> p;`
  - 생성 방식
    - `std::pair(자료형, 자료형)`
    - 두 개의 원소를 묶은 pair 생성
  - 조회
    - first: 첫 번째 인자 반환
    - second: 두번째 인자 반환
```cpp
#include <iostream>
#include <utility>

int main() {
	std::pair<int, double> myPair(10, 3.14);

	std::cout << "First value " << myPair.first << std::endl;
	std::cout << "Second value " << myPair.second << std::endl;

	myPair.first = 20;
	myPair.second = 2.71;

	std::cout << "Modified First value: " << myPair.first << std::endl;
	std::cout << "Modified Second value: " << myPair.second << std::endl;

	return 0;
}
```

## 최소 비용 신장 트리
### 최소 비용 신장 트리(MST)
- 최소 비용 신장 트리
  - 트리를 구성하는 간선들의 가중치를 합한 값이 최소가 되는 신장 트리
- 조건
  - 간선의 가중치의 합이 최소여야 함
  - n-1개의 간선만 사용해야 함
  - 사이클이 포함되서는 안됨
### Kruskal 알고리즘
- greedy
  - 최선의 답을 선택하는 과정을 반복
  - 항상 최적의 해답을 주는지 검증 필요
- Kruskal의 최소비용 신장 트리 알고리즘
  - 1. 그래프의 모든 간선을 가중치에 따라 오름차순으로 정렬
  - 2. 가장 가중치가 작은 간선 e를 뽑음
  - 3. e를 신장 트리에 넣을 경우 사이클이 생기면 삽입하지 않고 2번으로 이동
  - 4. 사이클이 생기지 않으면 최소 신장트리에 삽입
  - 5. n-1개의 간선이 삽입될 때 까지 2번 반복
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/35d2e6a6-7a03-4fa4-bd73-ddb71d4f9954)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/05bf5ca4-4d94-455f-b655-01e5407bd587)
- Union-Find 알고리즘
  - 서로소 집합을 표현하는 자료구조
  - Find: 집합 원소가 어떤 집합에 속해 있는지 찾는 연산
  - Union: 서로 다른 두 집합을 병합하는 연산
  - parent 배열
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/078de4b1-361b-450e-80ed-16dff06e38bf)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/9efd345a-954e-4868-84c4-36dfef53f32a)
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/0ec73b8e-ec33-4886-8880-2eb14d5f6bc0)
```cpp
#include <iostream>
#include <vector>
#include <algorithm>

#define MAX_VERTICES 100

class Edge { //간선의 두 노드와 가중치를 저장
public:
	int node[2];
	int weight;
	Edge(int a, int b, int weight) { //간선의 생성자, 노드와 가중치 설정
		this->node[0] = a;
		this->node[1] = b;
		this->weight = weight;
	}

	bool operator<(Edge& edge) { //연산자 오버로딩, 가중치 비교
		return this->weight < edge.weight;
	}
};

class VertexSets { 
	int parent[MAX_VERTICES]; //부모 정점의 id
	int size; //집합의 개수
public:
	VertexSets(int nSets) {
		size = nSets;
		for (int i = 0; i < nSets; i++)
			parent[i] = -1; //초기값 설정
	}
	bool IsRoot(int i) { return parent[i] < 0; }

	int findSet(int vertex) { //v가 속한 집합을 찾아 반환
		int id = vertex;
		while (!IsRoot(id)) id = parent[id];
		return id;
	}

	void unionSets(int s1, int s2) { //집합 s1을 집합 s2에 합침
		parent[s1] = s2;
		size--;
	}
};

class Kruskal {
public:
	VertexSets vs;
	std::vector<Edge> edges;

	Kruskal(int N) : vs(N){}

  //간선 삽입
	void insertEdge(int nodeA, int nodeB, int weight) {
		edges.push_back(Edge(nodeA, nodeB, weight));
	}

  //크루스칼 알고리즘 실행
	void execute() {
    //간선을 기중치 기준으로 정렬
		sort(edges.begin(), edges.end());

		int totalWeight = 0;
    //모든 간선에 대해
		for (auto edge : edges) {
			int set1 = vs.findSet(edge.node[0]);
			int set2 = vs.findSet(edge.node[1]);

      //두 노드가 다른 집합에 속해 있다면
			if (set1 != set2) {
				totalWeight += edge.weight; //가중치 더하기
				vs.unionSets(set1, set2); //두 집합 합치기

				std::cout << "Added edge " << edge.node[0] << " - " << edge.node[1]
					<< " with weight " << edge.weight << std::endl;
			}
		}

		std::cout << "Total weight: " << totalWeight << std::endl; //총 가중치 출력
	}
};

int main() {
	Kruskal kruskal(7);
	kruskal.insertEdge(0, 1, 9);
	kruskal.insertEdge(0, 2, 10);
	kruskal.insertEdge(1, 3, 10);
	kruskal.insertEdge(1, 4, 5);
	kruskal.insertEdge(2, 4, 7);
	kruskal.insertEdge(2, 5, 2);
	kruskal.insertEdge(3, 6, 4);
	kruskal.insertEdge(4, 6, 7);
	kruskal.insertEdge(5, 6, 6);
	kruskal.execute();
	return 0;
}
```

### Prim의 MST 알고리즘
- 처음에는 시작 정점만이 신장 트리 집합에 포함
- 시작 정점부터 신장 트리 집합을 단계적으로 확장
- 현재의 신장 트리 집합에 인접한 정점 중 최저 간선으로 연결된 정점을 선택해 신장 트리집합에 추가
- 이 과정을 n-1개의 간선을 가질 때까지 반복
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b8943b49-1ca4-469f-8c85-282594689df3)

```cpp
#include <iostream>
#include <vector>
#include <queue>

#define MAX_VERTICES 100

class Edge {
public:
	int node;
	int weight;
	Edge(int node, int weight) {
		this->node = node;
		this->weight = weight;
	}
	bool operator<(const Edge& edge) const {
		return this->weight > edge.weight;
	}
};

class Prim {
public:
	int num_vertex;
	std::vector<Edge> graph[MAX_VERTICES];

	Prim(int num_vertex) {
		this->num_vertex = num_vertex;
	}

	void addEdge(int nodeA, int nodeB, int weight) {
		graph[nodeA].push_back(Edge(nodeB, weight));
		graph[nodeB].push_back(Edge(nodeA, weight));
	}

	void execute(int start) {
		std::vector<bool> selected(num_vertex, false); //정점의 선택 여부
		std::priority_queue<Edge> Q; 
    //우선순의 큐를 사용하여 간선을 저장하고 최소 가중치 간선을 선택
		Q.push(Edge(start, 0));
    //시작 정점을 큐에 삽입

    //최소 비용의 합을 저장
		int totalWeight = 0;
		while (!Q.empty()) {
			Edge cur = Q.top();
			Q.pop();
			if (selected[cur.node]) continue;
      //이미 선택된 정점이면 스킵

			selected[cur.node] = true;
			//정점을 선택으로 표시
      totalWeight += cur.weight;
      //현재 간선의 가중치를 비용에 추가

      //현재 정점과 연결된 간선들에 대해
			for (Edge next : graph[cur.node]) {
        //선택되지 않은 정점이면 큐에 삽입
				if (!selected[next.node]) Q.push(next);
			}
		}

    //최소 비용 출력
		std::cout << "Total weight: " << totalWeight << std::endl;
	}
};

int main() {
	Prim prim(7);

	prim.addEdge(0, 1, 9);
	prim.addEdge(0, 2, 10);
	prim.addEdge(1, 3, 10);
	prim.addEdge(1, 4, 5);
	prim.addEdge(2, 4, 7);
	prim.addEdge(2, 5, 2);
	prim.addEdge(3, 6, 4);
	prim.addEdge(4, 6, 7);
	prim.addEdge(5, 6, 6);
	prim.execute(0);
	return 0;
}
```

## 최단 경로
### 최단 경로
- 정점 u와 정점 v를 연결하는 경로 중 간선들의 가중치 합이 최소가 되는 경로
- 집합 S
  - v에서부터의 최단경로가 이미 발견된 정점들의 집합
- dist 배열
  - 최단경로가 알려진 정점들을 이용한 다른 정점들까지의 최단경로 길이
- 초기값
  - dist[v] = 0
  - dist[u] = 시작 정점 v와 u간의 가중치 값
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/b9331c22-71a6-4006-be14-cf78fe7f3c46)
- 작동과정 
  - 출발 노드 설정
  - 출발 노드를 기준으로 각 노드의 최소비용 설정
  - 방문하지 않은 노드 중 가장 비용이 적은 노드 선택
  - 해당 노드를 거쳐서 특정한 노드로 가는 경우를 고려하여 최소 비용 갱신
- ![image](https://github.com/googoo9918/TIL/assets/102513932/6b0c1bb8-548b-4298-a5b0-d99d619ce658)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/661efc89-54f6-472f-a60f-1e04d62e3879)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/9436fdf1-34ab-477e-8fb9-d5f4e6062dc9)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/fefa73ab-2f8d-4bcc-b3b6-2f4da6d1fcdb)
```cpp
#include <iostream>
#include <vector>

#define INF 1000000000 //초기 비용 설정, 무한대

using namespace std;

int number = 6; //총 노드의 수
int start = 1; // 시작 노드 번호
bool visited[6]; // 방문한 노드를 표시하는 배열
int dist[6]; // 최단 거리를 저장하는 배열
vector<pair<int, int>> a[7]; // 각 노드와 연결된 다른 노드와 그 거리를 저장하는 배열

void dijkstra(int start) {
	dist[start] = 0; //시작 노드의 최단 거리는 0으로 초기화
	for (int i = 0; i < number; i++) {
		int current = -1; //현재 노드를 저장하는 변수
		int minDist = INF; //최단 거리를 저장하는 변수

		for (int j = 1; j <= number; j++) { //최단 거리의 노드 찾기
			if (!visited[j] && minDist > dist[j]) {
				minDist = dist[j];
				current = j;
			}
		}

		visited[current] = true; //현재 노드를 방문 표시
		for (int j = 0; j < a[current].size(); j++) { //현재 노드와 연결된 다른 노드들의 거리 업데이트
			int near = a[current][j].first; //연결된 노드
			int nearDist = a[current][j].second; //해당 노드까지의 거리
			if (dist[near] > dist[current] + nearDist) { //기존에 저장된 거리보다 더 짧은 거리라면 업데이트
				dist[near] = dist[current] + nearDist;
			}
		}
	}
}

int main(void) {
	for (int i = 1; i <= number; i++) {
		dist[i] = INF;
	}
	a[1].push_back(make_pair(2, 2));
	a[1].push_back(make_pair(3, 5));
	a[1].push_back(make_pair(4, 1));
	a[2].push_back(make_pair(1, 2));
	a[2].push_back(make_pair(3, 3));
	a[2].push_back(make_pair(4, 2));
	a[3].push_back(make_pair(1, 5));
	a[3].push_back(make_pair(2, 3));
	a[3].push_back(make_pair(4, 3));
	a[3].push_back(make_pair(5, 1));
	a[3].push_back(make_pair(6, 5));
	a[4].push_back(make_pair(1, 1));
	a[4].push_back(make_pair(2, 2));
	a[4].push_back(make_pair(3, 3));
	a[4].push_back(make_pair(5, 1));
	a[5].push_back(make_pair(3, 1));
	a[5].push_back(make_pair(4, 1));
	a[5].push_back(make_pair(6, 2));
	a[6].push_back(make_pair(3, 5));
	a[6].push_back(make_pair(5, 2));

	dijkstra(start);

	for (int i = 1; i <= number; i++) {
		if (dist[i] == INF) {
			cout << "INF ";
		}
		else {
			cout << dist[i] << " ";
		}
	}
	return 0;
}
```

### Floyd의 최단경로 알고리즘
- 그래프의 모든 정점 쌍 사이의 최단 경로를 구하는 알고리즘
  - 음수 가중치를 갖는 간선이 있어도 동작 가능
  - 모든 정점에서 모든 정점으로의 최단 경로를 구하고 싶을 때
  - **거쳐가는 정점**을 기준으로 최단거리를 구함
- ![image](https://github.com/googoo9918/TIL/assets/102513932/409e61a8-e316-4e8b-b3d8-bca27462fda8)
```cpp
#include <iostream>
#include <vector>

using namespace std;

const int number = 4;
const int INF = 10000000;

vector<vector<int>> a = {
	{0, 5, INF, 8},
	{7, 0, 9, INF},
	{2, INF, 0, 4},
	{INF, INF, 3, 0}
};

void floydWarshall() {
	vector<vector<int>> d(number, vector<int>(number));

	for (int i = 0; i < number; i++) {
		for (int j = 0; j < number; j++) {
			d[i][j] = a[i][j];
		}
	}

	for (int k = 0; k < number; k++) {
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (d[i][k] + d[k][j] < d[i][j]) {
					d[i][j] = d[i][k] + d[k][j];
				}
			}
		}
	}

	for (int i = 0; i < number; i++) {
		for (int j = 0; j < number; j++) {
			cout << d[i][j] << ' ';
		}
		cout << endl;
	}
}

int main() {
	floydWarshall();
	return 0;
}
```

### Dijkstra vs Floyd
- Dijkstra
  - 그래프의 한 노드에서 다른 모든 노드까지의 최단 경로를 찾는 알고리즘
  - 가중치가 있는 그래프에서 사용되며, 가중치가 음수인 간선은 다루지 못함
  - 각 단계에서 가장 거리가 짧은 노드를 선택하는 탐욕적인 방식
- Floyd Warshall
  - 그래프의 모든 노드 간 최단 경로를 찾는 알고리즘
    - 각 노드에서 다른 모든 노드까지의 최단 경로를 꼐산
  - 가중치가 양수이든 음수이든 상관 없이 모든 가중치를 처리할 수 있음
  - 음수 사이클은 처리할 수 없음

## 정렬
- 일반적으로 정렬 대상은 레코드
  - 레코드는 필드보다 작은 단위로 구성
  - 정렬이란, **레코드를을 키 값의 순서로 재배열**
  - 모든 경우에 대해 최적인 정렬 알고리즘은 없음
  - **교환, 선택, 삽입** 알고리즘을 사용해 정렬을 완료
- 내부 정렬
  - 하나의 배열에서 작업할 수 있는 경우
- 외부 정렬
  - 하나보다 더 많은 배열에서 작업해야 하는 경우
- 정렬 알고리즘의 안정성
  - 안정적인 정렬
    - 키 값이 같은 레코드의 순서가 정렬 후에도 유지됨
  - 안정적이지 않은 정렬
    - 정렬 후에도 유지된다는 보장이 없는 정렬
    - ![image](https://github.com/googoo9918/TIL/assets/102513932/29be3203-1fb5-4bc3-9fc2-7097e2033292)

### 선택 정렬
- 가장 작은 원소부터 선택하여 알맞은 위치로 옮기는 작업을 반복하는 알고리즘
  - 반복적으로 최소값을 찾아 정렬하는 방식
  - 시간 복잡도 O(n^2)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/00c2b946-d425-46ff-bb1c-41365b9e4f08)
```cpp
#include <iostream>
#include <vector>

using namespace std;

void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

void selectionSort(vector<int>& A) {
	int n = A.size();
	for (int i = 0; i < n - 1; i++) {
		int least = i;
		for (int j = i + 1; j < n; j++)
			if (A[j] < A[least])
				least = j;
		swap(A[least], A[i]);
	}
}

void printArray(vector<int>& arr) {
	for(int i = 0; i < arr.size(); i++)
		cout << arr[i] << " ";
	cout << "\n";
}

int main() {
	vector<int> arr = { 64, 34, 25, 12, 22, 11, 90 };

	selectionSort(arr);
	cout << "Sorted array using Selection Sort: \n";
	printArray(arr);
	return 0;
}
```

### 삽입 정렬
- 정렬대상에서 정렬 안 된 부분의 데이터를 정렬된 부분의 특정위치에 삽입하는 방식
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/0943be98-1fec-4c1f-af6e-e1ac8fdd9d44)
  - 배열의 두 번째 원소부터 시작
  - 현재 원소를 정렬된 부분 배열과 비교
  - 정렬된 부분 배열에서 현재 원소보다 큰 원소를 찾을 때까지 왼쪽으로 이동
  - 적절한 위치를 찾으면 해당 원소를 해당 위치에 삽입
  - 시간 복잡도 O(n^2)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/329a8467-c211-4102-877c-f2b83e92031b)
```cpp
#include <iostream>
#include <vector>

using namespace std;

void insertionSort(vector<int>& A) {
	int n = A.size(); //입력 배열 A의 크기를 n에 저장

	for (int i = 1; i < n; i++) { //두 번째 원소부터 배열 전체를 순회
		int key = A[i];
		int j = i - 1;

		while (j >= 0 && A[j] > key) { //key보다 큰 A[0..i-1]의 원소를 한 위치 앞으로 이동
			A[j + 1] = A[j];
			j = j - 1;
		}
		A[j + 1] = key; //key를 정학환 위치에 삽입
	}
}

int main() {
	vector<int> A = { 64, 34, 25, 12, 22, 11, 90 };

	insertionSort(A);

	cout << "Sorted array using Insertion Sort: \n";

	for (int i = 0; i < A.size(); i++)
		cout << A[i] << " ";
	cout << endl;

	return 0;
}
```

### 버블 정렬
- 버블 정렬
  - 인접한 두 개의 데이터를 비교해 정렬을 진행하는 방식
  - 인접한 두 요소를 비교하여 순서가 잘못되어 있다면 위치를 교환 
  - 한 칸씩 이동하면서 요소를 비교하고 교환하는 과정 반복
  - 가장 큰 값을 맨 오른쪽에 하나씩 미는 방법!
- ![image](https://github.com/googoo9918/TIL/assets/102513932/dbde56fd-b423-492c-b498-6c4fefdcaa74)
```cpp
#include <iostream>
#include <vector>

using namespace std;

void swap(int& a, int& b){
	int tmp = a;
	a = b;
	b = tmp;
}

void bubbleSort(vector<int>& A){
	int n = A.size();
	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < n - 1 - i; j++) {
			if (A[j] > A[j + 1]) {
				swap(A[j], A[j + 1]);
			}
		}
	}
}

int main() {
	vector<int> A = { 64, 34, 25, 12, 22, 11, 90 };

	bubbleSort(A);
	cout << "Sorted array using Bubble Sort: \n";
	for (int i = 0; i < A.size(); i++) {
		cout << A[i] << " ";
	}
	cout << endl;
	return 0;
}
```

### 선택 정렬 vs 삽입 정렬 vs 버블 정렬
- 선택 정렬
  - 최솟값을 찾아 맨 앞으로 이동하는 방식
- 삽입 정렬
  - 앞에서부터 차례대로 이미 정렬된 부분과 비교하여 교환하는 방식
  - 셋 중 제일 빠르지면 배열이 길어질수록 효율성 떨어짐
- 버블 정렬
  - 인접한 원소끼리 비교하여 교환하는 방식
  - 셋 중 제일 느리지만 단순함

## 정렬(2)

### 셀 정렬
- 삽입 정렬을 보완한 알고리즘
  -  배열을 일정한 간격으로 나누어 부분적으로 정렬한 후 간격을 줄여가며 정렬을 반복하는 방식
  -  gap값(k)을 줄여가며 부분적으로 정렬한 후 마지막에는 일반적인 삽입 정렬을 수행하여 전체 배열을 정렬함
  - 시간 복잡도
    - 최악 : O(n^2), 최선: O(n), 평균: O(n^1.5) 
- 과정
  - 1. 정렬할 배열을 일정한 간격으로 나눔
    - 간격은 일반적으로 배열의 절반으로 시작하여 계속해서 반으로 줄여 나감
  - 2. 나뉜 간격에 따라 여러 개의 부분 배열을 생성
    - 각 부분 배열은 간격만큼 떨어진 요소들로 구성
  - 3. 각 부분 배열에 대해 삽입 정렬을 수행
    - 간격을 줄여가며 과정을 반복, 간격이 1이 될 때 까지 정렬 반복
```cpp
#include <iostream>
#include <vector>

using namespace std;

void sortGapInsertion(vector<int>& list, int first, int last, int gap)
// gap 만큼의 간격으로 정렬하는 함수
{
	int i, j, key;
	for (i = first + gap; i <= last; i = i + gap) {
		key = list[i];
		for (j = i - gap; j >= first && key < list[j]; j = j - gap)
			list[j + gap] = list[j];
		list[j + gap] = key;
	}
}

void shellSort(vector<int>& list){
	//셸 정렬을 수행하는 함수
	int i, gap;
	int n = list.size(); //벡터의 크기 저장
	for (gap = n / 2; gap > 0; gap = gap / 2) {
		if ((gap % 2) == 0) gap++;
		//gpa이 짝수라면 홀수로 변경
		for (i = 0; i < gap; i++)
			sortGapInsertion(list, i, n - 1, gap);
		//gap 간격으로 정렬
	}
}

int main() {
	vector<int> A = { 35, 33, 42, 10, 14, 19, 27, 44 };

	shellSort(A);

	for (int i = 0; i < A.size(); i++) {
		cout << A[i] << " ";
	}
	return 0;
}
```

### 합병 정렬
- 분할 정봅 방식을 기반으로 한 정렬 알고리즘
- 리스트를 절반으로 분할 후 각각 재귀적으로 정렬한 후에 합병하여 정렬된 리스트 생성
  - 합병할 때 정렬 실행
- 시간 복잡도 평균적으로 O(nlong)의 시간 복잡도를 가짐
  - 입력 데이터의 크기에 무관하게 일정한 성능 보장
  - 안정적이며 데이터의 초기 분산 순서에 영향을 덜 받음
- ![image](https://github.com/googoo9918/TIL/assets/102513932/02ac2078-1d4c-4664-829d-1cd13f42d743)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/24ca798d-3623-42fb-aa23-7c49a35a3913)
```cpp
#include <iostream>
#include <vector>

using namespace std;

void merge(vector<int> A, int left, int mid, int right) {
	vector<int> sorted(right - left + 1);
	//병합된 결과를 임시로 저장할 배열
	int i = left;
	int j = mid + 1;
	int k = 0;

	while (i <= mid && j <= right) { //두 배열의 원소를 비교하며 병합
		if (A[i] <= A[j]) {
			sorted[k++] = A[i++];
		}
		else {
			sorted[k++] = A[j++];
		}
	}

	while (i <= mid) { //첫 번째 부분 배열의 나머지 원소 추가
		sorted[k++] = A[i++];
	}

	while (j <= right) { //두 번째 부분 배열의 나머지 원소 추가
		sorted[k++] = A[j++];
	}

	for (int idx = left, k = 0; idx <= right; ++idx, ++k) { 
		//임시 배열의 원소를 원본 배열로 복사
		A[idx] = sorted[k];
	}
}

void mergeSort(vector<int>& A, int left, int right) { //병합 정렬 함수
	if (left < right) {
		int mid = (left + right) / 2;
		mergeSort(A, left, mid);
		mergeSort(A, mid + 1, right);
		merge(A, left, mid, right);
	}
}

int main(){
  vector<int> A = {27, 10, 12, 20, 25, 13, 15, 22};
  mergeSort(A, 0, A.size() -1);

  for(int i=0; i<A.size(); i++){
    cout<<A[i] << " ";
  }

  return 0;
}
```