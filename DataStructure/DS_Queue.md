### 목차
- [큐 (Queue)](#큐-queue)
  - [1. 큐 구조](#1-큐-구조)
  - [2. 알아둘 용어](#2-알아둘-용어)
  - [3. JAVA 에서의 큐 자료 구조 사용하기](#3-java-에서의-큐-자료-구조-사용하기)
      - [Queue 클래스 사용해보기](#queue-클래스-사용해보기)
  - [4. 프로그래밍 연습](#4-프로그래밍-연습)

## 큐 (Queue)

### 1. 큐 구조
* 줄을 서는 행위와 유사
* 가장 먼저 넣은 데이터를 가장 먼저 꺼낼 수 있는 구조
  - FIFO(First-In, First-Out) 또는 LILO(Last-In, Last-Out) 방식으로 스택과 꺼내는 순서가 반대
  
<img src="https://www.fun-coding.org/00_Images/queue.png" />
<br/>
<br/>

### 2. 알아둘 용어
* Enqueue: 큐에 데이터를 넣는 기능
* Dequeue: 큐에서 데이터를 꺼내는 기능

### 3. JAVA 에서의 큐 자료 구조 사용하기
* JAVA 에서는 기본적으로 java.util 패키지에 Queue 클래스를 제공하고 있음
  - Enqueue 에 해당하는 기능으로 Queue 클래스에서는 add(value) 또는 offer(value) 메서드를 제공함
  - Dequeue 에 해당하는 기능으로 Queue 클래스에서는 poll() 또는 remove() 메서드를 제공함
  - Queue 클래스에 데이터 생성을 위해서는 java.util 패키지에 있는 LinkedList 클래스를 사용해야 함

##### Queue 클래스 사용해보기
```java
// Queue 사용을 위해, LinkedList 클래스를 사용하므로, 두 클래스 모두 import 해야 함
import java.util.LinkedList; 
import java.util.Queue; 

// 자료형 매개변수를 넣어서, 큐에 들어갈 데이터의 타입을 지정해야 함
Queue<Integer> queue_int = new LinkedList<Integer>(); // Integer 형 queue 선언, 링크드리스트로  생성해야됨 주의.
Queue<String> queue_str = new LinkedList<String>(); // String 형 queue 선언

// 데이터 추가는 add(value) 또는 offer(value) 를 사용함
queue_int.add(1);
queue_int.offer(2);

// Queue 인스턴스를 출력하면, 해당 큐에 들어 있는 아이템 리스트가 출력됨
System.out.println(queue_int)

// poll() 은 큐의 첫 번째 값 반환, 해당 값은 큐에서 삭제
queue_int.poll();

// poll() 과 마찬가지로, 첫 번째 값 반환하고, 해당 값은 큐에서 삭제
queue_int.remove();

// 요소 개수 출력 
queue_int.size(); 

// 맨 처음 넣은 값 출력
queue_int.peek()

// 큐 비어있는지 확인
queue_int.isEmpty();

// 큐 비우기
queue_int.clear();

```


### 4. 프로그래밍 연습 
- JAVA ArrayList 클래스를 활용해서 큐를 다루는 enqueue, dequeue 기능 구현해보기 <br>
- dequeue 기능 호출 시, 큐에 데이터가 없을 경우, null 을 리턴하도록 함 <br>
- 다양한 데이터 타입을 다룰 수 있도록, Java Genric 타입 문법을 활용해보기    
</div>
```java
public class MyQueue<T> {
    private ArrayList<T> queue = new ArrayList<T>();
    
    public void enqueue(T item) {
        queue.add(item);
    }
    
    public T dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.remove(0);
    }
    
    public static void main(String[] args) {
        MyQueue<Integer> mq = new MyQueue<Integer>();
        mq.enqueue(1);
        mq.enqueue(2);
        mq.enqueue(3);
        System.out.println(mq.dequeue());
        System.out.println(mq.dequeue());
        System.out.println(mq.dequeue());        
    }
}
```
