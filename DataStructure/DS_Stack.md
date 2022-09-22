### 목차
- [스택 (Stack)](#스택-stack)
  - [1. 스택 구조](#1-스택-구조)
  - [2. 자료 구조 스택의 장단점](#2-자료-구조-스택의-장단점)
  - [3. JAVA 에서의 스택 자료 구조 사용하기](#3-java-에서의-스택-자료-구조-사용하기)
      - [JAVA Stack 클래스](#java-stack-클래스)
      - [Stack 클래스 사용하기](#stack-클래스-사용하기)
  - [5. 프로그래밍 연습](#5-프로그래밍-연습)
## 스택 (Stack)
* 데이터를 제한적으로 접근할 수 있는 구조
  - 한쪽 끝에서만 자료를 넣거나 뺄 수 있는 구조
* 가장 나중에 쌓은 데이터를 가장 먼저 빼낼 수 있는 데이터 구조
  - 큐: FIFO 정책
  - 스택: LIFO 정책

### 1. 스택 구조
* 스택은 LIFO(Last In, Fisrt Out) 또는 FILO(First In, Last Out) 데이터 관리 방식을 따름
  - LIFO: 마지막에 넣은 데이터를 가장 먼저 추출하는 데이터 관리 정책
  - FILO: 처음에 넣은 데이터를 가장 마지막에 추출하는 데이터 관리 정책

* 대표적인 스택의 활용
  - 컴퓨터 내부의 프로세스 구조의 함수 동작 방식

* 주요 기능
  - push(): 데이터를 스택에 넣기
  - pop(): 데이터를 스택에서 꺼내기
<br/><br/>  
![image](https://user-images.githubusercontent.com/102513932/173505904-54042bc7-f5c5-46d1-8888-939d849e8adb.png)

### 2. 자료 구조 스택의 장단점
- 장점
  - 구조가 단순해서, 구현이 쉽다.
  - 데이터 저장/읽기 속도가 빠르다.
- 단점 (일반적인 스택 구현시) 
  - 데이터 최대 갯수를 미리 정해야 한다. 
  - 저장 공간의 낭비가 발생할 수 있음
    - 미리 최대 갯수만큼 저장 공간을 확보해야 함

> 스택은 단순하고 빠른 성능을 위해 사용되므로, 보통 배열 구조를 활용해서 구현하는 것이 일반적임.
> 이 경우, 위에서 열거한 단점이 있을 수 있음

### 3. JAVA 에서의 스택 자료 구조 사용하기
- 자료구조와 알고리즘은 주요 개념을 이해하고
- 알고리즘은 변수,조건,반복문으로 직접 구현할 수 있어야 합니다.

##### JAVA Stack 클래스
- java.util 패키지에서 Stack 클래스 제공
  - push(아이템) 메서드 : 아이템을 Stack 에 추가
  - pop() 메서드 : Stack 에서 마지막에 넣은 아이템을 리턴하고, 해당 아이템은 Stack 에서 삭제.

##### Stack 클래스 사용하기
```java
//  java.util.Stack 클래스 임포트
import java.util.Stack; 

// 자료형 매개변수를 넣어서, 스택에 들어갈 데이터의 타입을 지정해야 함
Stack<Integer> stack_int = new Stack<Integer>(); 
// Integer 형 스택 선언
stack_int.push(1);     // Stack 에 1 추가
stack_int.push(2);     // Stack 에 2 추가
stack_int.push(3);     // Stack 에 3 추가

stack_int.pop();       // 3
// Stack 에서 데이터 추출 (마지막에 넣은 3이 출력)
stack_int.pop();       // 2
// Stack 에서 데이터 추출 (현재 Stack 에 있는 데이터 중, 가장 나중에 넣어진 데이터 출력)
stack_int.pop();       // 1
// stack에 값 제거

stack_int.peek(); // 가장 상단의 값 출력 (삭제x)
stack_int.size(); // 크기 출력
stack.empty(); // stack 비어있으면 true
stack.contains(1) // 1이 있으면 true
stack.remove(2) //stack의 2번째 인덱스 삭제
```
### 5. 프로그래밍 연습 

- JAVA ArrayList 클래스를 활용해서 스택을 다루는 push, pop 기능 구현해보기 <br>
- pop 기능 호출 시, 스택에 데이터가 없을 경우, null 을 리턴하도록 함 <br>
- 다양한 데이터 타입을 다룰 수 있도록, Java Genric 타입 문법을 활용해보기
</div>

```java
import java.util.ArrayList;

public class MyStack<T> {
    private ArrayList<T> stack = new ArrayList<T>();
    
    public void push(T item) {
        stack.add(item);
    }
    
    public T pop() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        MyStack<Integer> ms = new MyStack<Integer>();
        ms.push(1);
        ms.push(2);
        System.out.println(ms.pop());
        ms.push(3);
        System.out.println(ms.pop());
        System.out.println(ms.pop());        
    }
}
```
