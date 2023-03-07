## 목차
- [c++ 기초](#c-기초)
  - [Hello, c++](#hello-c)
  - [자료형](#자료형)
  - [배열과 문자열](#배열과-문자열)
    - [배열](#배열)
    - [문자열](#문자열)
  - [구조체](#구조체)
# c++ 기초
## Hello, c++
```cpp
#include <iostream>

using namespace std;
// iostream에 있는 함수를 사용하려면 앞에 std라는 이름을 붙여야함
// 따라서 선언을 통해 std::cout이 아닌 cout과 같이 편리하게 사용할 수 있게함

int main() {
	// c++ 코드에는 반드시 main 함수가 있어야 함

	cout << "Hello, Wolrd!" << endl;
	// cout 뒤에 나오는 문장을 출력
	// endl은 줄바꿈 역할
	// <<는 데이터의 방향을 나타냄

	return 0;
}
``` 

## 자료형
```cpp
#include <iostream>
#include <climits>

using namespace std;

int main() {

	int n_int = INT_MAX;
	short n_short = SHRT_MAX;
	long n_long = LONG_MAX;
	long long n_llong = LLONG_MAX;

	cout << "int는 " << sizeof n_int << "바이트이다." << endl;
	//int는 4바이트이다.
	cout << "이 바이트의 최대값은 " << n_int << " 이다." << endl;

	cout << "short은 " << sizeof n_short << "바이트이다." << endl;
	//short은 2바이트이다
	cout << "이 바이트의 최대값은 " << n_short << " 이다." << endl;

	cout << "long은 " << sizeof n_long << "바이트이다." << endl;
	//long은 4바이트이다
	cout << "이 바이트의 최대값은 " << n_long << " 이다." << endl;

	cout << "long long은 " << sizeof n_llong << "바이트이다." << endl;
	//long long은 8바이트이다
	cout << "이 바이트의 최대값은 " << n_llong << " 이다." << endl;

	char ch[] = { 'a', 'b', 'c', '\0' };
	// null 문자 '\0'을 통해 배열의 끝을 명시해줘야함

	cout << ch << endl;
	// abc

	bool a = 0;
	bool b = 1;
	bool c = 10;
	// 0은 false 나머지는 true

	cout << a << b << c << endl;
	// 011 -> c또한 1로 출력되는 점 주의

	const float PI = 3.1415926535;
	// 상수 선언

	char ch = 'M';
	cout << (int)ch << " " << int(ch) << endl;
	// 77
	// 강제적 데이터 형변환
	// typeName(a) , (typeName)a , static_cast<typeName>
	return 0;
}
```

## 배열과 문자열
### 배열
```cpp
#include <iostream>

using namespace std;

int main() {
	short m[12] = { 1, 2, 3 };
	short n[] = { 1 };

	cout << m[0] << endl; //1
	cout << n[2] << endl; //정상적이지 않은 값 출력됨

	/*
	배열 부분 초기화 시, 나머지 원소들은 모두 0으로 설정됨
	*/

	char a[] = { 'H', 'e', 'l', 'l','o', '\0'};
	char b[] = "Hello"; 
	// 같은 방식임
	cout << a << endl; // Hello
	cout << b << endl; // Heloo 
}
```

### 문자열
```cpp
#include <iostream>
#include <cstring> // strlen() 함수 사용을 위해

using namespace std;

int main() {
    //사용자 입력
    const int Size = 15;
    char name1[Size];
    char name2[Size] = "C++programing";

    cout << "안녕하세요! 저는 " << name2;
    cout << "입니다! 성함이 어떻게 되시나요?\n";
    cin >> name1;
    //cin을 통해 입력을 받음
    //다만, 공백을 만날 경우 문장의 끝이라고 인식함 ex) panda coding -> panda만 인식
    //따라서 다음과 같이 입력을 받아야 함
    //cin.get(name1, Size); (cin.getline 또한 동일하게 동작함)
    cout << "음, " << name1 << "씨, 당신의 이름은 "; //jdg
    cout << strlen(name1) << "자입니다만\n"; //3
    cout << sizeof(name1) << "바이트 크기의 배열에 저장되었습니다. \n"; //15
    cout << "이름이 " << name1[0] << "자로 시작하는군요.\n"; //j
    name2[3] = '\0';
    cout << "제 이름의 처음 세 문자는 다음과 같습니다: "; //C++
    cout << name2 << endl;

    string str1;
    string str2 = "panda";
    str1 = str2;
    cout << str1[0] << endl; //p
    cout << str2 << endl; //panda
    return 
```

## 구조체
```cpp
#include <iostream>

using namespace std;

int main() {
	//구조체 : 다른 데이터형이 허용되는 데이터의 집합

	struct MyStruct
	{
		string name;
		string position;
		int height;
		int weight;
	}A; // 바로 변수를 생성할 수도 있음
	
	A = {

	};
	// 아무것도 없이 초기화도 가능 

	cout << A.height<< endl; //0 출력
	cout << A.position << endl; //공백 출력

	MyStruct B;
	
	B = {
		"jdg",
		"guard",
		177,
		70
	}; // 간편하게 초기화 가능
	
	MyStruct C;
	C.name = "jdg"; //지정 초기화 가능
	C.position = "guard";
	C.height = 177;
	C.weight = 70;


	MyStruct D[2] = { //구조체 배열 사용 가능
		{"a", "a", 1, 1},
		{"b", "b", 2, 2}
	};

	cout << D[0].height << endl; //1


	return 0;
}
```