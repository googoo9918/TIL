##  트리
### 1. 트리 (Tree) 구조
- 트리: Node와 Branch를 이용해서, 사이클을 이루지 않도록 구성한 데이터 구조
- 실제로 어디에 많이 사용되나? 
  - 트리 중 이진 트리 (Binary Tree) 형태의 구조로, 탐색(검색) 알고리즘 구현을 위해 많이 사용됨

### 2. 알아둘 용어
- Node: 트리에서 데이터를 저장하는 기본 요소 (데이터와 다른 연결된 노드에 대한 Branch 정보 포함)
- Root Node: 트리 맨 위에 있는 노드
- Level: 최상위 노드를 Level 0으로 하였을 때, 하위 Branch로 연결된 노드의 깊이를 나타냄
- Parent Node: 어떤 노드의 다음 레벨에 연결된 노드
- Child Node: 어떤 노드의 상위 레벨에 연결된 노드
- Leaf Node (Terminal Node): Child Node가 하나도 없는 노드
- Sibling (Brother Node): 동일한 Parent Node를 가진 노드
- Depth: 트리에서 Node가 가질 수 있는 최대 Level
![image](https://user-images.githubusercontent.com/102513932/175300165-96f49d72-ea88-4dc3-a740-9ac2bcf92210.png)

### 3. 이진 트리와 이진 탐색 트리 (Binary Search Tree)
- 이진 트리: 노드의 최대 Branch가 2인 트리
- 이진 탐색 트리 (Binary Search Tree, BST): 이진 트리에 다음과 같은 추가적인 조건이 있는 트리
  - 왼쪽 노드는 해당 노드보다 작은 값, 오른쪽 노드는 해당 노드보다 큰 값을 가지고 있음!
  
<img src="https://www.mathwarehouse.com/programming/images/binary-search-tree/binary-search-tree-insertion-animation.gif" />

### 4. 자료 구조 이진 탐색 트리의 장점과 주요 용도
- 주요 용도: 데이터 검색(탐색) 
- 장점: 탐색 속도를 개선할 수 있음

> 단점은 이진 탐색 트리 알고리즘 이해 후에 살펴보기로 함

### 이진트리와 정렬된 배열간의 탐색 비교
<img src="https://www.mathwarehouse.com/programming/images/binary-search-tree/binary-search-tree-sorted-array-animation.gif" />

(출처: https://www.mathwarehouse.com/programming/gifs/binary-search-tree.php#binary-search-tree-insertion-node)

### 5.1. 노드 클래스 만들기
```java
public class NodeMgmt {
    public class Node {
        Node left;
        Node right;
        int value;
        public Node (int data) {
            this.value = data;
            this.left = null;
            this.right = null;
        }
    }
}
```

### 5.2. 이진 탐색 트리에 데이터 넣기
* 이진 탐색 트리 조건에 부합하게 데이터를 넣어야 함

```java
 public boolean insertNode(int data) {
        // CASE1: Node 가 하나도 없을 때
        if (this.head == null) {
            this.head = new Node(data); //그냥 만들어주면 된다.
        } else {
            // CASE2: Node 가 하나 이상 들어가 있을 때
            Node findNode = this.head; 
            // 순회를 돌리기 위한 임시변수 findNode.
            while (true) {
                // CASE2-1: 현재 Node 의 왼쪽에 Node 가 들어가야할 때
                if (data < findNode.value) {
                    if (findNode.left != null) { 
                        // 왼쪽에 값이 있을경우
                        findNode = findNode.left; 
                        // 새로운 선회를 위해서 findnode 값 변경.
                    } else { // 왼쪽이 비어있는경우
                        findNode.left = new Node(data); 
                        //새 값을 만들어 주면 된다. 
                        break;
                    }
                // CASE2-2: 현재 Node 의 오른쪽에 Node 가 들어가야할 때
                // CASE2-1과 같은 맥락이다. 
                } else {
                    if (findNode.right != null) {
                        findNode = findNode.right;
                    } else {
                        findNode.right = new Node(data);
                        break;
                    }
                }
            }
        }
        return true;        
    }
```

### 5.3. 이진 탐색 트리 탐색
```java
 public Node search(int data) {
        // CASE1: Node 가 하나도 없을 때
        if (this.head == null) { 
            // head가 없으면 null 리턴해야겠지?
            return null;
        // CASE2: Node 가 하나 이상 있을 때            
        } else {
            Node findNode = this.head; //임시변수 설정
            while (findNode != null) {
                if (findNode.value == data) { 
                    //찾는 data값이면 리턴!
                    return findNode;
                } else if (data < findNode.value) {
                    findNode = findNode.left; 
                    // 작으면 왼쪽으로 돌려주기
                } else {
                    findNode = findNode.right; 
                    // 크면 오른쪽으로 돌려주기
                }
            }
            return null;
        }
    }
```
### 5.4. 이진 탐색 트리 삭제 
#### 5.4.1. Leaf Node 삭제 
* Leaf Node: Child Node 가 없는 Node
* 삭제할 Node의 Parent Node가 삭제할 Node를 가리키지 않도록 한다. 
* ![image](https://user-images.githubusercontent.com/102513932/175300633-1b26b3f9-0afa-4b0e-bb08-4bbef84e5a81.png)

#### 5.4.2. Child Node 가 하나인 Node 삭제 
* 삭제할 Node의 Parent Node가 삭제할 Node의 Child Node를 가리키도록 한다.
* ![image](https://user-images.githubusercontent.com/102513932/175300679-59f2f66a-830d-41a5-822c-409cbb29eccc.png)

