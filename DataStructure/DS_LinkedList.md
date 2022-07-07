## 링크드 리스트 (Linked List)
### 1. 링크드 리스트 (Linked List) 구조
* 연결 리스트라고도 함
* 배열은 순차적으로 연결된 공간에 데이터를 나열하는 데이터 구조
* 링크드 리스트는 떨어진 곳에 존재하는 데이터를 화살표로 연결해서 관리하는 데이터 구조
* * 링크드 리스트 기본 구조와 용어
  - 노드(Node): 데이터 저장 단위 (데이터값, 포인터) 로 구성
  - 포인터(pointer): 각 노드 안에서, 다음이나 이전의 노드와의 연결 정보를 가지고 있는 공간

<br>
* 일반적인 링크드 리스트 형태

![image](https://user-images.githubusercontent.com/102513932/173850795-4a4ec5cf-6499-4674-a783-897c8f677fc7.png)

### 2. 간단한 링크드 리스트
##### 2.1. Node 클래스 구현
```java
 public class Node<T> {
        T data;
        Node<T> next = null; 
        
        public Node(T data) {
            this.data = data;  // 생성자!
        }
    }
```

##### 2.2. Node와 Node 연결하기 : Node 인스턴스간 연결
```java
Node<Integer> node1 = new Node<Integer>(1);
Node<Integer> node2 = new Node<Integer>(2);
node1.next = node2;
Node<Integer> head = node1; // 헤드 주소 저장해줄 것.
```

##### 2.3. 링크드 리스트에 데이터 추가하기
```java
  
    public void addNode(T data) { //노드 추가 메소드
        if (head == null) {
            head = new Node<T>(data); // 처음 추가하는 거라면, 인스턴스 생성하여 head에 넣어주기.
        } else {
            Node<T> node = this.head; // 리스트를 쭉 돌아야 하기 때문에 변수 node 생성해줌.
            while (node.next != null) {
                node = node.next;     // 마지막 전까지 node 넘겨주기.
            }
            node.next = new Node<T>(data); // 마지막에 새 노드 추가해주기!
        }
    }
```
```java
SingleLinkedList<Integer> MyLinkedList = new SingleLinkedList<Integer>();
MyLinkedList.addNode(1);
System.out.println(MyLinkedList.head.data) // 1
MyLinkedList.addNode(2);
System.out.println(MyLinkedList.head.next.data) //2
MyLinkedList.head.next // 객체 위치 출력되겠지?
//REPL.$JShell$19$SingleLinkedList$Node@2254e14c
```

##### 2.4. 링크드 리스트 데이터 출력하기
```java
public void printAll() { //값 출력 메소드
        if (head != null) {
            Node<T> node = this.head;  //head가 비어있지 않으면, node변수 만들고 거기에 head(현재상황) 넣어주기.
            System.out.println(node.data); 
            while (node.next != null) {
                node = node.next;   // 끝까지 넘겨주기.
                System.out.println(node.data);
            }
        }
    }
```

### 3. 링크드 리스트의 장단점 (전통적인 C언어에서의 배열과 링크드 리스트)
* 장점
  - 미리 데이터 공간을 미리 할당하지 않아도 됨
    - 배열은 **미리 데이터 공간을 할당** 해야 함
* 단점
  - 연결을 위한 별도 데이터 공간이 필요하므로, 저장공간 효율이 높지 않음
  - 연결 정보를 찾는 시간이 필요하므로 접근 속도가 느림
  - 중간 데이터 삭제시, 앞뒤 데이터의 연결을 재구성해야 하는 부가적인 작업 필요

### 4. 링크드 리스트의 복잡한 기능1 (링크드 리스트 데이터 사이에 데이터를 추가)
- 링크드 리스트는 유지 관리에 부가적인 구현이 필요함
- ![image](https://user-images.githubusercontent.com/102513932/173852776-9240160c-fab9-45b9-8c8c-d3dacde39c20.png)
```java
 public Node<T> search(T data) { //값 검색 메소드, addNodeInside에서 사용.
        if (this.head == null) {
            return null;
        } else {
            Node<T> node = this.head; //똑같이 계속 반복됨.
            while (node != null) { 
                if (node.data == data) {
                    return node;  //data 일치시 node 리턴
                } else {
                    node = node.next; //아니면 끝까지 넘겨주기.
                }
            }
            return null; //찾는 값 없으면 null 리턴.
        }
    }
    
     public void addNodeInside(T data, T isData) { //중간에 node 삽입하는 메소드, isData를 통해 어떤 노드 뒤에 값 넣을지 설정.
        Node<T> searchedNode = this.search(isData); // isdata를 찾는다면 searchednode에 삽입.
        
        if (searchedNode == null) {
            this.addNode(data); // searchednode가 null 이면 중간에 삽입할 필요가 없으니, 마지막에 추가.
        } else {   
            Node<T> nextNode = searchedNode.next;  //swap을 생각해주면 편할 것 같음. 작동 원리를 머리속으로 그림 그리며 잘 생각해보자.   
            searchedNode.next = new Node<T>(data); 
            searchedNode.next.next = nextNode;
        }
    }
```
- 링크드리스트 생성하고, 1, 2, 3 데이터 넣기
```java
SingleLinkedList<Integer> MyLinkedList = new SingleLinkedList<Integer>();
MyLinkedList.addNode(1);
MyLinkedList.addNode(2);
MyLinkedList.addNode(3);
// 1 2 3
```
- 1 데이터 뒤에 5 넣어보기
```java
MyLinkedList.addNodeInside(5, 1);
MyLinkedList.printAll();
// 1 5 2 3
```

- 3 데이터 뒤에 6 넣어보기
```java
MyLinkedList.addNodeInside(6, 3);
MyLinkedList.printAll();
// 1 5 2 3 6
```

- 없는 데이터를 찾도록 해서, 맨 마지막에 데이터 넣기
```java
MyLinkedList.addNodeInside(7, 20);
MyLinkedList.printAll();
```

### 5. 링크드 리스트의 복잡한 기능2 (특정 노드를 삭제)
* 다음 코드는 위의 코드에서 delete 메서드만 추가한 것이므로 해당 메서드만 확인하면 됨

```java
 public boolean delNode(T isData) { 
    //노드 삭제 메소드.
        if (this.head == null) { 
            return false;  
            // 들어있는게 없을 때.
        } else {
            Node<T> node = this.head; 
            // 위와 같은 양식 ㅇㅇ
            if (node.data == isData) { 
                // 첫 번째 노드가 내가 삭제하고 싶은 데이터 일때 (예외 케이스) 
                this.head = this.head.next; 
                // head 넘겨버리기
                return true;
            } else {
                while (node.next != null) { 
                    if (node.next.data == isData) { 
                        node.next = node.next.next; 
                        //값 일치시 중간 값 재껴버리기. 
                        return true;
                    }
                    node = node.next; 
                    //넘기면서 찾아줘야겠지? , 증감식 같은 느낌.
                }
                return false; 
                // 찾는 값 없을 때.
            }
        }
    }
```

##### 테스트1: 5개의 노드 생성
```java
SingleLinkedList<Integer> MyLinkedList = new SingleLinkedList<Integer>();

MyLinkedList.addNode(1);
MyLinkedList.addNode(2);
MyLinkedList.addNode(3);
MyLinkedList.addNode(4);
MyLinkedList.addNode(5);

MyLinkedList.printAll(); // 1 2 3 4 5
```

##### 테스트2: 중간 노드 삭제
```java
MyLinkedList.delNode(3);
MyLinkedList.printAll(); // 1 2 4 5 
```

##### 테스트3: 맨 앞의 노드(헤드) 삭제
```java
MyLinkedList.delNode(1);
MyLinkedList.printAll(); // 2 4 5 
```

##### 테스트4: 맨 마지막 노드 삭제
```java
MyLinkedList.delNode(5);
MyLinkedList.printAll(); // 2 4
```

##### 테스트5: 없는 노드 삭제 시도
```java
MyLinkedList.delNode(20); // false
```

### 링크드리스트 전체 코드
```java
public class SingleLinkedList<T> {
    public Node<T> head = null; 
    //가장 앞 부분을 가르킬 head 
    
    public class Node<T> {
        T data;
        Node<T> next = null; 
        
        public Node(T data) {
            this.data = data;  
            // 생성자!
        }
    }
    
    public void addNode(T data) { //노드 추가 메소드
        if (head == null) {
            head = new Node<T>(data); 
            // 처음 추가하는 거라면, 인스턴스 생성하여 head에 넣어주기.
        } else {
            Node<T> node = this.head; 
            // 리스트를 쭉 돌아야 하기 때문에 변수 node 생성해줌.
            while (node.next != null) {
                node = node.next;     
                // 마지막 전까지 node 넘겨주기.
            }
            node.next = new Node<T>(data);
             // 마지막에 새 노드 추가해주기!
        }
    }
    
    public void printAll() { //값 출력 메소드
        if (head != null) {
            Node<T> node = this.head;  
            //head가 비어있지 않으면, node변수 만들고 거기에 head(현재상황) 넣어주기.
            System.out.println(node.data); 
            while (node.next != null) {
                node = node.next;   
                // 끝까지 넘겨주기.
                System.out.println(node.data);
            }
        }
    }
    
    public Node<T> search(T data) { //값 검색 메소드, addNodeInside에서 사용.
        if (this.head == null) {
            return null;
        } else {
            Node<T> node = this.head; //똑같이 계속 반복됨.
            while (node != null) { 
                if (node.data == data) {
                    return node;  //data 일치시 node 리턴
                } else {
                    node = node.next; //아니면 끝까지 넘겨주기.
                }
            }
            return null; //찾는 값 없으면 null 리턴.
        }
    }
    
    public void addNodeInside(T data, T isData) { 
        //중간에 node 삽입하는 메소드, isData를 통해 어떤 노드 뒤에 값 넣을지 설정.
        Node<T> searchedNode = this.search(isData); 
        // isdata를 찾는다면 searchednode에 삽입.
        
        if (searchedNode == null) {
            this.addNode(data); 
            // searchednode가 null 이면 중간에 삽입할 필요가 없으니, 마지막에 추가.
        } else {   
            Node<T> nextNode = searchedNode.next;  
            //swap을 생각해주면 편할 것 같음. 작동 원리를 머리속으로 그림 그리며 잘 생각해보자.   
            searchedNode.next = new Node<T>(data); 
            searchedNode.next.next = nextNode;
        }
    }
    
    public boolean delNode(T isData) { //노드 삭제 메소드.
        if (this.head == null) { 
            return false;  // 들어있는게 없을 때.
        } else {
            Node<T> node = this.head; // 위와 같은 양식 ㅇㅇ
            if (node.data == isData) { 
                // 첫 번째 노드가 내가 삭제하고 싶은 데이터 일때 (예외 케이스) 
                this.head = this.head.next; // head 넘겨버리기
                return true;
            } else {
                while (node.next != null) { 
                    if (node.next.data == isData) { 
                        node.next = node.next.next; 
                        //값 일치시 중간 값 재껴버리기. 
                        return true;
                    }
                    node = node.next; 
                    //넘기면서 찾아줘야겠지? , 증감식 같은 느낌.
                }
                return false; 
                // 찾는 값 없을 때.
            }
        }
    }
}
```

### 6. 다양한 링크드 리스트 구조: 더블 링크드 리스트(Doubly linked list)
* 더블 링크드 리스트(Doubly linked list) 기본 구조 
  - 이중 연결 리스트라고도 함
  - 장점: 양방향으로 연결되어 있어서 노드 탐색이 양쪽으로 모두 가능
  <br>
  ![image](https://user-images.githubusercontent.com/102513932/175000234-c388a33c-a3a5-427f-ab6b-f4ccd724d42e.png)
```java
public class DoubleLinkedList<T> {
    public Node<T> head = null; 
    public Node<T> tail = null; 
    //끝에서 부터도 찾을 수 있게 tail 변수도 존재.
    
    public class Node<T> {
        T data;
        Node<T> prev = null;
        Node<T> next = null; // 포인터 2개 형성.
        
        public Node(T data) {
            this.data = data; //생성자
        }
    }
    
    public void addNode(T data) { // 노드 추가 메소드
        if (this.head == null) {
            this.head = new Node<T>(data);
            this.tail = this.head; 
            // 처음 값을 생성하는 것이니까, tail에도 값 넣어주기
        } else {
            Node<T> node = this.head; //노드가 존재하는 경우, 임시 변수 설정
            while (node.next != null) {
                node = node.next; //끝을 찾기 위해서!
            }
            node.next = new Node<T>(data); 
            // 맨 끝에 노드 추가해주기.
            node.next.prev = node; 
            // node.next의 next는 null로 설정되는것이 맞지만, prev는 그 전 노드를 가르키게 해 줘야함.
            this.tail = node.next; 
            // 현재 node의 tail 은 node.next를 가르키면 되겠지?
        } // 지금 this는 DoubleLinkedList를 가르킨다고 보면 된다.
    }
    
    public void printAll() {
        if (this.head != null) {
            Node<T> node = this.head; 
            // 임시 변수 설정 (출력해야 되니까)
            System.out.println(node.data); 
            // while문 안에서 출력 시작하면 마지막 값 출력 못함.. do while 느낌
            while (node.next != null) {
                node = node.next;
                System.out.println(node.data);
            }
        }
    }
}


public T searchFromHead(T isData) { //head 부터 data 찾는 메소드
        if (this.head == null) { 
            return null; // 노드 없는 경우
        } else {
            Node<T> node = this.head;
            while (node != null) {
                if (node.data == isData) { // 내가 찾는 data라면
                    return node.data; // 리턴
                } else {
                    node = node.next; // 아니면 순회
                }
            }
            return null; // 찾는 data 없으면 null 리턴해주기.
        }
        
    }
    
    public T searchFromTail(T isData) { // tail에서 부터 data찾는 메소드
        if (this.head == null) {
            return null;
        } else {
            Node<T> node = this.tail;
            while (node != null) {
                if (node.data == isData) {
                    return node.data;
                } else {
                    node = node.prev; // 순회 방식만 달리하면 됨.
                }
            }
            return null;
        }
    }

// 임의 노드 앞에 노드에 원하는 데이터를 추가하는 메소드 
public boolean insertToFront(T existedData, T addData) { 
    //해당 DATA를 가진 노드, 새로 만들 노드가 가질 DATA
        if (this.head == null) { // DATA가 없을 경우
            this.head = new Node<T>(addData); 
            // addData 값을 가진 노드를 만들어 주면 되겠지!
            this.tail = this.head;
            return true;
        } else if (this.head.data == existedData) { 
            // 제일 앞에 있는 data가 찾는 data일때. (이 앞에 새 노드 추가해줘야겠지?)
            Node<T> newHead = new Node<T>(addData); 
            // 새 head 만들고 만들 node가 가질 data 추가.
            newHead.next = this.head; // 연결해주기.
            this.head.next.prev = this.head; // prev도 연결해주기.
            this.head = newHead; // head 바꿔주기! 
            return true;
        } else { // head가 아닌 다른 node 사이에 값을 넣는 경우
            Node<T> node = this.head; // 순회를 위한 node
            while (node != null) {
                if (node.data == existedData) { 
                    Node<T> nodePrev = node.prev; 
                    // 현재 노드값 이전 node를 가르키는 nodeprev 변수 임시 설정.
                    
                    nodePrev.next = new Node<T>(addData);
                    // 중간에 새 노드 만들어주기!
                    nodePrev.next.next = node; 
                    // 새로 만든 노드의 next 부분 채워주기.
                    
                    nodePrev.next.prev = nodePrev; 
                    // 새로 만든 노드의 prev 부분 채워주기.
                    node.prev = nodePrev.next; 
                    // 현재 노드값의 이전 부분도 수정해주기.
                    return true;
                } else {
                    node = node.next; //순회
                }
            }
            return false; // 값을 찾지 못한경우, false.
        }
    }
```

