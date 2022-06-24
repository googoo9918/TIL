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

#### 5.4.3. Child Node 가 두 개인 Node 삭제
1. **삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 삭제할 Node의 Parent Node가 가리키도록 한다.**
2. 삭제할 Node의 왼쪽 자식 중, 가장 큰 값을 삭제할 Node의 Parent Node가 가리키도록 한다.
 ![image](https://user-images.githubusercontent.com/102513932/175473344-f6c52a79-ebe1-4a86-9c1f-27f31d8285be.png)

##### 5.4.3.1. 삭제할 Node의 오른쪽 자식중, 가장 작은 값을 삭제할 Node의 Parent Node가 가리키게 할 경우
- 삭제할 Node의 오른쪽 자식 선택
- 오른쪽 자식의 가장 왼쪽에 있는 Node를 선택
- 해당 Node를 삭제할 Node의 Parent Node의 왼쪽 Branch가 가리키게 함
- 해당 Node의 왼쪽 Branch가 삭제할 Node의 왼쪽 Child Node를 가리키게 함
- 해당 Node의 오른쪽 Branch가 삭제할 Node의 오른쪽 Child Node를 가리키게 함
- 만약 해당 Node가 오른쪽 Child Node를 가지고 있었을 경우에는, 해당 Node의 본래 Parent Node의 왼쪽 Branch가 해당 오른쪽 Child Node를 가리키게 함

### 5.5. 이진 탐색 트리 삭제 코드 구현과 분석
#### 5.5.1 삭제할 Node 탐색
- 삭제할 Node가 없는 경우도 처리해야 함
  - 이를 위해 삭제할 Node가 없는 경우는 False를 리턴하고, 함수를 종료 시킴
```java
    public boolean delete(int value) { // value를 가진 node가 삭제할 node이다.  
        boolean searched = false;  // 찾으면 true로 바꿔줄 것!
        
        Node currParentNode = this.head;
        Node currNode = this.head; //순회하기 위한 임시변수 2개.
        
        // 코너 케이스1: Node 가 하나도 없을 때
        if (this.head == null) {
            return false;
        } else {
            // 코너 케이스2: Node 가 단지 하나만 있고, 해당 Node 가 삭제할 Node 일 때
            if (this.head.value == value && this.head.left == null && this.head.right == null) {
                this.head = null;
                return true;
            }
            
            while (currNode != null) {
                if (currNode.value == value) {
                    searched = true;
                    break;
                } else if (value < currNode.value) {
                    currParentNode = currNode;
                    currNode = currNode.left;  //왼쪽으로 순회
                } else {
                    currParentNode = currNode;
                    currNode = currNode.right; //오른쪽으로 순회    
                }
            }
            
            if (searched == false) {
                return false;
            }
        }

        // 여기까지 실행되면,
        // currNode 에는 해당 데이터를 가지고 있는 Node,
        // currParentNode 에는 해당 데이터를 가지고 있는 Node 의 부모 Node 
        
    }
```

#### 5.5.2. Case1: 삭제할 Node가 Leaf Node인 경우
![image](https://user-images.githubusercontent.com/102513932/175473493-6a7b21f8-7aee-42b4-83e2-c384d15c552a.png)
```java
        // Case1: 삭제할 Node 가 Leaf Node 인 경우
        if (currNode.left == null && currNode.right == null) { // currNode가 leafNode인지 확인.
            if (value < currParentNode.value) { //currNode의 value가 parentNode의 value보다 작다면 왼쪽 밑에 있겠지?
                currParentNode.left = null; // 삭제해주기
                currNode = null; // 안써도 되긴 하는데...
            } else { //오른쪽에 있는 경우겠지 
                currParentNode.right = null; //삭제해주기
                currNode = null;
            }
            return true;

```

#### 5.5.2. Case2: 삭제할 Node가 Child Node를 한 개 가지고 있을 경우
![image](https://user-images.githubusercontent.com/102513932/175473565-37b297a7-d9ad-4728-b442-653b06271eda.png)

```java
        // Case2-1: 삭제할 Node 가 Child Node를 한 개 가지고 있을 경우 (왼쪽에 Child Node 가 있을 경우)
        } else if (currNode.left != null && currNode.right == null) {
            if (value < currParentNode.value) { // currentNode가 부모 노드의 왼쪽에 있는경우
                currParentNode.left = currNode.left; // ParnetNode의 왼쪽과 ChildNode를 연결시켜주기.
                currNode = null; // 안써도 되지만 명시적 표현
            } else { //currentNode가 부모 노드의 오른쪽에 있는경우.
                currParentNode.right = currNode.left; // ParentNode의 오른쪽과 childNode 연결.
                currNode = null;
            }
            return true;
        // Case2-2: 삭제할 Node 가 Child Node를 한 개 가지고 있을 경우 (오른쪽에 Child Node 가 있을 경우)
        } else if (currNode.left == null && currNode.right != null) {
            if (value < currParentNode.value) {
                currParentNode.left = currNode.right;
                currNode = null;
            } else {
                currParentNode.right = currNode.right;
                currNode = null;
            }
            return true;
```

#### 5.5.3. Case3-1: 삭제할 Node가 Child Node를 두 개 가지고 있을 경우 (삭제할 Node가 Parent Node 왼쪽에 있을 때)
* 기본 사용 가능 전략
  1. **삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 삭제할 Node의 Parent Node가 가리키도록 한다.**
  2. 삭제할 Node의 왼쪽 자식 중, 가장 큰 값을 삭제할 Node의 Parent Node가 가리키도록 한다.
* 기본 사용 가능 전략 중, 1번 전략을 사용하여 코드를 구현하기로 함
  - 경우의 수가 또다시 두가지가 있음
    - **Case3-1-1:** 삭제할 Node가 Parent Node의 왼쪽에 있고, 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node의 Child Node가 없을 때
    - **Case3-1-2:** 삭제할 Node가 Parent Node의 왼쪽에 있고, 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node의 오른쪽에 Child Node가 있을 때
       - 가장 작은 값을 가진 Node의 Child Node가 왼쪽에 있을 경우는 없음, 왜냐하면 왼쪽 Node가 있다는 것은 해당 Node보다 더 작은 값을 가진 Node가 있다는 뜻이기 때문임
![image](https://user-images.githubusercontent.com/102513932/175473739-c8c0e6fa-bf3c-4128-922d-8da17a63e8e7.png)
```java
        // Case3-1: 삭제할 Node 가 Child Node 를 두 개 가지고 있고, (삭제할 Node 가 부모 Node 의 왼쪽에 있을 때)
        } else {
            
            // 삭제할 Node 가 부모 Node 의 왼쪽에 있을 때
            if (value < currParentNode.value) {
                
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right; //currNode의 오른쪽 중 가장 왼쪽에 있는 값을 찾기 위한 임시변수 2개.
                while (changeNode.left != null) {
                    changeParentNode = changeNode;
                    changeNode = changeNode.left;
                }
                // 여기까지 실행되면, changeNode 에는 삭제할 Node 의 오른쪽 Node 중에서, 
                // 가장 작은 값을 가진 Node 가 들어있음
                
                if (changeNode.right != null) {
                    // Case 3-1-2: changeNode 의 오른쪽 Child Node 가 있을 때
                    changeParentNode.left = changeNode.right;
                } else {
                    // Case 3-1-1: changeNode 의 Child Node 가 없을 때
                    changeParentNode.left = null;
                }
                
                // currParentNode 의 왼쪽 Child Node 에, 삭제할 Node 의 오른쪽 자식중,
                // 가장 작은 값을 가진 changeNode 를 연결
                currParentNode.left = changeNode;
                
                // parentNode 의 왼쪽 Child Node 가 현재, changeNode 이고,
                // changeNode 의 왼쪽/오른쪽 Child Node 를 모두, 삭제할 currNode 의
                // 기존 왼쪽/오른쪽 Node 로 변경
                changeNode.right = currNode.right;
                changeNode.left = currNode.left;
                
                currNode = null; //삭제했다고 명시적으로 표현해주기.
```

#### 5.5.4. Case3-2: 삭제할 Node가 Child Node를 두 개 가지고 있을 경우 (삭제할 Node가 Parent Node 오른쪽에 있을 때)
* 기본 사용 가능 전략
  1. **삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 삭제할 Node의 Parent Node가 가리키도록 한다.**
  2. 삭제할 Node의 왼쪽 자식 중, 가장 큰 값을 삭제할 Node의 Parent Node가 가리키도록 한다.
* 기본 사용 가능 전략 중, 1번 전략을 사용하여 코드를 구현하기로 함
  - 경우의 수가 또다시 두가지가 있음
    - **Case3-2-1:** 삭제할 Node가 Parent Node의 오른쪽에 있고, 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node의 Child Node가 없을 때
    - **Case3-2-2:** 삭제할 Node가 Parent Node의 오른쪽에 있고, 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node의 오른쪽에 Child Node가 있을 때
       - 가장 작은 값을 가진 Node의 Child Node가 왼쪽에 있을 경우는 없음, 왜냐하면 왼쪽 Node가 있다는 것은 해당 Node보다 더 작은 값을 가진 Node가 있다는 뜻이기 때문임

![image](https://user-images.githubusercontent.com/102513932/175473829-04043587-2b23-40b0-9389-97e018668b73.png)
```java
        // Case3-2: 삭제할 Node 가 Child Node 를 두 개 가지고 있고, (삭제할 Node 가 부모 Node 의 오른쪽에 있을 때)
        } else {
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right;
                while (changeNode.left != null) {
                    changeParentNode = changeNode;
                    changeNode = changeNode.left;
                }
                // 여기까지 실행되면, changeNode 에는 삭제할 Node 의 오른쪽 Node 중에서, 
                // 가장 작은 값을 가진 Node 가 들어있음            
            
                if (changeNode.right != null) {
                    // Case 3-2-2: changeNode 의 오른쪽 Child Node 가 있을 때
                    changeParentNode.left = changeNode.right;
                } else {
                    // Case 3-2-1: changeNode 의 Child Node 가 없을 때
                    changeParentNode.left = null;
                }

                // currParentNode 의 오른쪽 Child Node 에, 삭제할 Node 의 오른쪽 자식중,
                // 가장 작은 값을 가진 changeNode 를 연결
                currParentNode.right = changeNode;
                
                // parentNode 의 왼쪽 Child Node 가 현재, changeNode 이고,
                // changeNode 의 왼쪽/오른쪽 Child Node 를 모두, 삭제할 currNode 의
                // 기존 왼쪽/오른쪽 Node 로 변경
                changeNode.right = currNode.right;
                changeNode.left = currNode.left;
                
                currNode = null;            
        }
        return true;
```

### 6. 이진 탐색 트리의 시간 복잡도와 단점
#### 6.1. 시간 복잡도 (탐색시)
  - depth (트리의 높이) 를 h라고 표기한다면, O(h)
  - n개의 노드를 가진다면, $h = log_2{n} $ 에 가까우므로, 시간 복잡도는 $ O(log{n}) $ 
     - 참고: 빅오 표기법에서 $log{n}$ 에서의 log의 밑은 10이 아니라, 2입니다.
       - 한번 실행시마다, 50%의 실행할 수도 있는 명령을 제거한다는 의미. 즉 50%의 실행시간을 단축시킬 수 있다는 것을 의미함
       - <img src="https://www.mathwarehouse.com/programming/images/binary-search-tree/binary-search-tree-sorted-array-animation.gif" />

#### 6.2. 이진 탐색 트리 단점
  - 평균 시간 복잡도는 $ O(log{n}) $ 이지만, 
    - 이는 트리가 균형잡혀 있을 때의 평균 시간복잡도이며,
  - 다음 예와 같이 구성되어 있을 경우, 최악의 경우는 링크드 리스트등과 동일한 성능을 보여줌 ( $O(n)$ )
![image](https://user-images.githubusercontent.com/102513932/175474003-505299ff-69e0-42c9-8346-78f2f95f2422.png)