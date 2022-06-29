##  힙
### 1. 힙 (Heap) 이란?
- 힙: 데이터에서 최대값과 최소값을 빠르게 찾기 위해 고안된 완전 이진 트리(Complete Binary Tree)
  - 완전 이진 트리: 노드를 삽입할 때 최하단 왼쪽 노드부터 차례대로 삽입하는 트리
  - ![image](https://user-images.githubusercontent.com/102513932/176202051-f9c2079b-86ac-432b-a0e2-91868bd483ff.png)
- 힙을 사용하는 이유
  - 배열에 데이터를 넣고, 최대값과 최소값을 찾으려면 O(n) 이 걸림
  - 이에 반해, 힙에 데이터를 넣고, 최대값과 최소값을 찾으면, $ O(log n) $ 이 걸림
  - 우선순위 큐와 같이 최대값 또는 최소값을 빠르게 찾아야 하는 자료구조 및 알고리즘 구현 등에 활용됨

### 2. 힙 (Heap) 구조
- 힙은 최대값을 구하기 위한 구조 (최대 힙, Max Heap) 와, 최소값을 구하기 위한 구조 (최소 힙, Min Heap) 로 분류할 수 있음
- 힙은 다음과 같이 두 가지 조건을 가지고 있는 자료구조임
  1. 각 노드의 값은 해당 노드의 자식 노드가 가진 값보다 크거나 같다. (최대 힙의 경우)
     - 최소 힙의 경우는 각 노드의 값은 해당 노드의 자식 노드가 가진 값보다 크거나 작음
  2. 완전 이진 트리 형태를 가짐

### 힙과 이진 탐색 트리의 공통점과 차이점
- 공통점: 힙과 이진 탐색 트리는 모두 이진 트리임
- 차이점: 
  - 힙은 각 노드의 값이 자식 노드보다 크거나 같음(Max Heap의 경우)
  - 이진 탐색 트리는 왼쪽 자식 노드의 값이 가장 작고, 그 다음 부모 노드, 그 다음 오른쪽 자식 노드 값이 가장 큼
  - 힙은 이진 탐색 트리의 조건인 자식 노드에서 작은 값은 왼쪽, 큰 값은 오른쪽이라는 조건은 없음
    - 힙의 왼쪽 및 오른쪽 자식 노드의 값은 오른쪽이 클 수도 있고, 왼쪽이 클 수도 있음
- 이진 탐색 트리는 탐색을 위한 구조, 힙은 최대/최소값 검색을 위한 구조 중 하나로 이해하면 됨  
- ![image](https://user-images.githubusercontent.com/102513932/176202198-28d77192-13c3-4106-b766-284be64b259e.png)
- ### 3. 힙 (Heap) 동작
- 데이터를 힙 구조에 삽입, 삭제하는 과정을 그림을 통해 선명하게 이해하기

### 힙에 데이터 삽입하기 - 기본 동작 
- 힙은 완전 이진 트리이므로, 삽입할 노드는 기본적으로 왼쪽 최하단부 노드부터 채워지는 형태로 삽입
- ![image](https://user-images.githubusercontent.com/102513932/176202333-e29861b7-34ee-42bd-9159-b1d768e85ed3.png)

### 힙의 데이터 삭제하기 (Max Heap 의 예)
- 보통 삭제는 최상단 노드 (root 노드)를 삭제하는 것이 일반적임
  - 힙의 용도는 최대값 또는 최소값을 root 노드에 놓아서, 최대값과 최소값을 바로 꺼내 쓸 수 있도록 하는 것임
- 상단의 데이터 삭제시, 가장 최하단부 왼쪽에 위치한 노드 (일반적으로 가장 마지막에 추가한 노드) 를 root 노드로 이동
- root 노드의 값이 child node 보다 작을 경우, root 노드의 child node 중 가장 큰 값을 가진 노드와 root 노드 위치를 바꿔주는 작업을 반복함 (swap)
- ![image](https://user-images.githubusercontent.com/102513932/176202444-2cb1e5b3-48be-4817-a91e-0df0ee894371.png)

### 4. 힙 구현
### 힙과 배열
- 일반적으로 힙 구현시 배열 자료구조를 활용함
- 배열은 인덱스가 0번부터 시작하지만, 힙 구현의 편의를 위해, root 노드 인덱스 번호를 1로 지정하면, 구현이 좀더 수월함
  - 부모 노드 인덱스 번호 (parent node's index) = 자식 노드 인덱스 번호 (child node's index) / 2
     - JAVA 에서는 / 연산자로 몫 을 구할 수 있음 
  - 왼쪽 자식 노드 인덱스 번호 (left child node's index) = 부모 노드 인덱스 번호 (parent node's index) * 2
  - 오른쪽 자식 노드 인덱스 번호 (right child node's index) = 부모 노드 인덱스 번호 (parent node's index) * 2 + 1
  - ![image](https://user-images.githubusercontent.com/102513932/176202566-82f1affa-9fb5-462b-b00b-67199e08148e.png)

### 힙에 데이터 삽입 구현 (Max Heap 예)
### 힙 클래스 구현1
- import java.util.ArrayList 를 활용해서 구현

```java
import java.util.ArrayList;

public class Heap {
    public ArrayList<Integer> heapArray = null;
    
    public Heap (Integer data) {
        heapArray = new ArrayList<Integer>();
        
        heapArray.add(null); //계산복잡도를 떨어트리기 위해 0번 index 비워두기.
        heapArray.add(data);
    }
}
```
### 힙 클래스 구현2 - insert1
- 인덱스 번호는 1번부터 시작하도록 변경
 ```java
    public boolean insert(Integer data) {
        if (heapArray == null) {
            heapArray = new ArrayList<Integer>(); // 비어 있다면 새로 만들어주기.
            
            heapArray.add(null);
            heapArray.add(data);
        } else {
            heapArray.add(data); // 아니면 순차적으로 넣어주기만 하면 된다.
        }
        return true;
    }
}
```

### 힙 클래스 구현3 - insert2
  - 삽입한 노드가 부모 노드의 값보다 클 경우, 부모 노드와 삽입한 노드 위치를 바꿈
  - 삽입한 노드가 루트 노드가 되거나, 부모 노드보다 값이 작거나 같을 경우까지 반복

### 특정 노드의 관련 노드 위치 알아내기
  - 부모 노드 인덱스 번호 (parent node's index) = 자식 노드 인덱스 번호 (child node's index) / 2
  - 왼쪽 자식 노드 인덱스 번호 (left child node's index) = 부모 노드 인덱스 번호 (parent node's index) * 2
  - 오른쪽 자식 노드 인덱스 번호 (right child node's index) = 부모 노드 인덱스 번호 (parent node's index) * 2 + 1

### 힙 구현에 사용된 Collections.swap() 메서드 사용법 이해하기
- swap (스왑) 이란, 두 데이터의 위치를 맞바꾸는 것을 의미함
- swap 함수를 별도로 구현할 수도 있지만, JAVA 에서는 Collections 패키지에서 swap() 메서드를 제공해주고 있음
  - 하나의 배열 안에 있는 두 데이터의 위치를 서로 맞바꾸고 싶을 때 사용 가능
  
```
import  java.util.Collections;

Collections.swap(List list, int a, int b)
```
- list : 스왑할 데이터들이 들어 있는 배열 변수
- a : 스왑할 데이터 인덱스 번호
- b : 스왑할 데이터 인덱스 번호

```java
    public boolean insert(Integer data) {
        Integer inserted_idx, parent_idx; //swap이 필요한 경우를 대비한 임시변수
        
        if (heapArray == null) { // 하나도 없을 때에는 동일.
            heapArray = new ArrayList<Integer>();
            
            heapArray.add(null);
            heapArray.add(data);
            return true;
        }
        
        this.heapArray.add(data);
        inserted_idx = this.heapArray.size() - 1; // index 번호 (맨 앞 빼고)
        
        while (this.move_up(inserted_idx)) { //조건 함수 잘 살펴보기.
            parent_idx = inserted_idx / 2; //부모 인덱스 번호는 자식의 몫!
            Collections.swap(this.heapArray, inserted_idx, parent_idx);
            inserted_idx = parent_idx;
        }
        return true;
    }
```

```java
Heap heapTest = new Heap(15);
heapTest.insert(10);
heapTest.insert(8);
heapTest.insert(5);
heapTest.insert(4);
heapTest.insert(20);

System.out.println(heapTest.heapArray); 
//[null, 20, 10, 15, 5, 4, 8]
```
### 힙에 데이터 삭제 구현 (Max Heap 예)
### 힙 클래스 구현4 - delete1
- 보통 삭제는 최상단 노드 (root 노드)를 삭제하는 것이 일반적임
  - 힙의 용도는 최대값 또는 최소값을 root 노드에 놓아서, 최대값과 최소값을 바로 꺼내 쓸 수 있도록 하는 것임

### 힙 클래스 구현4 - delete2
  - 상단의 데이터 삭제시, 가장 최하단부 왼쪽에 위치한 노드 (일반적으로 가장 마지막에 추가한 노드) 를 root 노드로 이동
  - root 노드의 값이 child node 보다 작을 경우, root 노드의 child node 중 가장 큰 값을 가진 노드와 root 노드 위치를 바꿔주는 작업을 반복함 (swap)

### 특정 노드의 관련 노드 위치 알아내기
  - 부모 노드 인덱스 번호 (parent node's index) = 자식 노드 인덱스 번호 (child node's index) / 2
  - 왼쪽 자식 노드 인덱스 번호 (left child node's index) = 부모 노드 인덱스 번호 (parent node's index) * 2
  - 오른쪽 자식 노드 인덱스 번호 (right child node's index) = 부모 노드 인덱스 번호 (parent node's index) * 2 + 1

### 구현을 위한 move_down 메서드 작성하기
```java
    public boolean move_down(Integer popped_idx) {
        Integer left_child_popped_idx, right_child_popped_idx;
        
        left_child_popped_idx = popped_idx * 2; //왼쪽 자식 노드 번호 설정
        right_child_popped_idx = popped_idx * 2 + 1; // 오른쪽 자식 노드 번호 설정
        // 자식 노드가 있는지 없는지는 모름 ㅇㅇ 번호만 설정.
        // CASE1: 왼쪽 자식 노드도 없을 때 (자식 노드가 하나도 없을 때)
        if (left_child_popped_idx >= this.heapArray.size()) { //index로 비교!
            return false; 
        // CASE2: 오른쪽 자식 노드만 없을 때
        } else if (right_child_popped_idx >= this.heapArray.size()) { //if문에서 left는 검사 완료.
            if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) { //
                return true;
            } else {
                return false;
            }
        // CASE3: 왼쪽/오른쪽 자식 노드가 모두 있을 때
        } else {
            if (this.heapArray.get(left_child_popped_idx) > this.heapArray.get(right_child_popped_idx)) { //왼쪽 자식 노드가 더 큰가?
                if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) { 
                    //현재 노드 보다 왼쪽 자식 노드가 더 큰가?
                    return true;
                } else {
                    return false;
                }
            } else { // 오른쪽 자식 노드의 값이 더 클 때
                if (this.heapArray.get(popped_idx) < this.heapArray.get(right_child_popped_idx)) { 
                    //현재 노드보다 오른쪽 자식 노드가 더 큼
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
```

### 구현을 위한 pop 메서드 작성하기
```java
    public Integer pop() {
        Integer returned_data, popped_idx, left_child_popped_idx, right_child_popped_idx; 
        // pop을 통해 가져올 변수 , 비교해야할 노드의 인덱스 번호 , pop_idx의 왼 자식 노드, 오른쪽 자식 노드
        
        if (this.heapArray == null) {
            return null;
        } else {
            returned_data = this.heapArray.get(1); // 값 뽑고
            this.heapArray.set(1, this.heapArray.get(this.heapArray.size() - 1)); //  제일 마지막의 있는 값 맨 위로 올려주기.
            this.heapArray.remove(this.heapArray.size() - 1); // 올려줬으니까 삭제해주기. 
            popped_idx = 1; //루트노드의 값을 바꿨으니까 ㅇㅇ 
            
            while (this.move_down(popped_idx)) { //while문으로 현재 루트 노드보다 더 큰 값 찾아주기!
                left_child_popped_idx = popped_idx * 2;
                right_child_popped_idx = popped_idx * 2 + 1;

                // CASE2: 오른쪽 자식 노드만 없을 때 (case1 은 어차피 false 리턴)
                if (right_child_popped_idx >= this.heapArray.size()) { 
                    if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) {
                        Collections.swap(this.heapArray, popped_idx, left_child_popped_idx); //조건에 부합한다면, 바꿔주기!
                        popped_idx = left_child_popped_idx; // 기준 노드 또한 바꿔줘야겠지?
                    }
                // CASE3: 왼쪽/오른쪽 자식 노드가 모두 있을 때                    
                } else {
                    if (this.heapArray.get(left_child_popped_idx) > this.heapArray.get(right_child_popped_idx)) {
                        if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) {
                            Collections.swap(this.heapArray, popped_idx, left_child_popped_idx); //조건 부합시 swap
                            popped_idx = left_child_popped_idx; // 기준 노드 변동
                        } 
                    } else {
                        if (this.heapArray.get(popped_idx) < this.heapArray.get(right_child_popped_idx)) {
                            Collections.swap(this.heapArray, popped_idx, right_child_popped_idx); //조건 부합시 swap
                            popped_idx = right_child_popped_idx; // 기준 노드 변경
                        } 
                    }
                }
            }
            
            return returned_data;
        }
    }
```

### 5. 힙 (Heap) 시간 복잡도
  - depth (트리의 높이) 를 h라고 표기한다면,
  - n개의 노드를 가지는 heap 에 데이터 삽입 또는 삭제시, 최악의 경우 root 노드에서 leaf 노드까지 비교해야 하므로 $h = log_2{n} $ 에 가까우므로, 시간 복잡도는 $ O(log{n}) $ 
     - 참고: 빅오 표기법에서 $log{n}$ 에서의 log의 밑은 10이 아니라, 2입니다.
     - 한번 실행시마다, 50%의 실행할 수도 있는 명령을 제거한다는 의미. 즉 50%의 실행시간을 단축시킬 수 있다는 것을 의미함
