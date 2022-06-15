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