### 목차
- [재귀 용법 (recursive call, 재귀 호출)](#재귀-용법-recursive-call-재귀-호출)
  - [1. 재귀 용법 (recursive call, 재귀 호출)](#1-재귀-용법-recursive-call-재귀-호출)
  - [2. 재귀 용법 이해](#2-재귀-용법-이해)
  - [예제](#예제)
  - [예제 - 시간 복잡도와 공간 복잡도](#예제---시간-복잡도와-공간-복잡도)
  - [3. 재귀 호출의 일반적인 형태](#3-재귀-호출의-일반적인-형태)
  - [일반적인 형태2 로 코드 작성해보기](#일반적인-형태2-로-코드-작성해보기)
  - [재귀 호출은 스택의 전형적인 예](#재귀-호출은-스택의-전형적인-예)
  - [4. 재귀 용법을 활용한 프로그래밍 연습](#4-재귀-용법을-활용한-프로그래밍-연습)
  - [참고: ArrayList 의 subList 메서드](#참고-arraylist-의-sublist-메서드)
  - [구현 코드](#구현-코드)
## 재귀 용법 (recursive call, 재귀 호출)

> 고급 정렬 알고리즘에서 재귀 용법을 사용하므로, 고급 정렬 알고리즘을 익히기 전에 재귀 용법을 먼저 익히기로 합니다.

### 1. 재귀 용법 (recursive call, 재귀 호출)
* 함수 안에서 동일한 함수를 호출하는 형태
* 여러 알고리즘 작성시 사용되므로, 익숙해져야 함

### 2. 재귀 용법 이해
- 예제를 풀어보며, 재귀 용법을 이해해보기

### 예제
- 팩토리얼을 구하는 알고리즘을 Recursive Call 을 활용해서 알고리즘 작성하기
```java
public class Factorial {
    public int factorialFunc(int n) {
        if (n > 1) {
            return n * this.factorialFunc(n - 1);
        } else {
            return 1;
        }
    }
}
```

### 예제 - 시간 복잡도와 공간 복잡도
* factorial(n) 은 n - 1 번의 factorial() 함수를 호출해서, 곱셈을 함 
  - 일종의 n-1번 반복문을 호출한 것과 동일
  - factorial() 함수를 호출할 때마다, 지역변수 n 이 생성됨

* 시간 복잡도/공간 복잡도는 O(n-1) 이므로 결국, 둘 다 O(n)

### 3. 재귀 호출의 일반적인 형태
> 간략히 메서드 레벨로 보기로 함

```java
// 일반적인 형태1
function(입력) {
    if (입력 > 일정값) {                       // 입력이 일정 값 이상이면
        return function(입력 - 1);           // 입력보다 작은 값
    } else {
        return 특정값; // 재귀 호출 종료
    }
}
```

```java
// 일반적인 형태2
function(입력) {
    if (입력 <= 일정값) {                       // 입력이 일정 값보다 작으면
        return 특정값              // 재귀 호출 종료
    } 
    return function(입력 - 1);
}
```

### 일반적인 형태2 로 코드 작성해보기
```java
public class Factorial {
    public int factorialFunc(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * this.factorialFunc(n - 1);
        }
    }
}
```

### 재귀 호출은 스택의 전형적인 예
* 함수는 내부적으로 스택처럼 관리된다.
* ![image](https://user-images.githubusercontent.com/102513932/176365177-d1aa8c39-96ec-47bf-8184-9eee0143e3ca.png)
* ![image](https://user-images.githubusercontent.com/102513932/176365229-193cdf96-0e44-4423-b807-4ee674341864.png)

### 4. 재귀 용법을 활용한 프로그래밍 연습
- 숫자가 들어 있는 배열이 주어졌을 때, 배열의 합을 리턴하는 코드를 작성
### 참고: ArrayList 의 subList 메서드
```java
public List<E> subList(int fromIndex, int toIndex)
```
- fromIndex : 서브셋이 시작할 인덱스
- toIndex: 서브셋의 마지막 인덱스
```java
import java.util.ArrayList;

ArrayList<Integer> testData = new ArrayList();
for (int data = 0; data < 10; data++) {
    testData.add(data);
}
System.out.println(testData); //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
System.out.println(testData.subList(1, testData.size())); //조심할 것! size는 10이지만 1뺀 값으로 출력됨.
//[1, 2, 3, 4, 5, 6, 7, 8, 9]
System.out.println(testData.subList(1, testData.size() - 1)); // 위와 잘 비교해보기.
//[1, 2, 3, 4, 5, 6, 7, 8]
```

### 구현 코드
```java
public class Factorial {
    public int factorialFunc(ArrayList<Integer> dataList) {
        if (dataList.size() <= 0) {
            return 0;
        } 
         return dataList.get(0) + this.factorialFunc(new ArrayList<Integer>(dataList.subList(1, dataList.size())));
    }
}
```

- 정수 4를 1, 2, 3의 조합으로 나타내는 방법은 다음과 같이 총 7가지가 있음 - 정수 n이 입력으로 주어졌을 때, n을 1, 2, 3의 합으로 나타낼 수 있는 방법의 수를 구하시오
1+1+1+1/ 
1+1+2/
1+2+1/
2+1+1/
2+2/
1+3/
3+1/
- 문제 분석시 연습장 등을 이용하여 작은 단위부터 시작, 규칙 찾기!

```java
public class Factorial {
    public int factorialFunc(int data) {
        if (data == 1) {
            return 1;
        } else if (data == 2) {
            return 2;
        } else if (data == 3) {
            return 4;
        }
        return this.factorialFunc(data - 1) + this.factorialFunc(data - 2) + this.factorialFunc(data - 3);
    }
}
```
