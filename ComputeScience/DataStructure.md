## 목차
- [자료구조](#자료구조)
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
    - [이진 탐색 트리](#이진-탐색-트리-1)
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
    - [dijkstra](#dijkstra)
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
    - [퀵 정렬](#퀵-정렬)
    - [기수 정렬](#기수-정렬)
  - [탐색](#탐색-1)
    - [탐색](#탐색-2)
    - [이진 탐색](#이진-탐색)
    - [색인 순차 탐색](#색인-순차-탐색)
    - [보간 탐색](#보간-탐색)
    - [AVL 트리](#avl-트리)
    - [AVL 트리 삽입 연산](#avl-트리-삽입-연산)
    - [AVL 예시 및 구현](#avl-예시-및-구현)
# 자료구조
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
### 이진 탐색 트리
  - 이진 트리 기반의 탐색을 위한 자료 구조
  - 모든 노드는 유일한 키를 가짐
  - 왼쪽 서브 트리의 키들은 루트의 키보다 작음
  - 오른쪽 서브 트리의 키들은 루트의 키보다 큼
  - 왼쪽과 오른쪽 서브 트리도 이진 탐색 트리임
    - 이진 탐색을 중위 순회화면 오름차순으로 정렬된 값을 얻을 수 있음
- 탐색 연산
  - 루트 노드부터 시작하여 특정 값을 찾을 때까지 왼쪽 또는 오른쪽 자식 노드로 이동하며 수행
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c9c358dd-1941-44e7-b629-9deb4c9d9bf1)
- 이진 탐색과 차이점
  - 이진 탐색은 자료들이 배열에 저장되어 있어 삽입/삭제가 매우 비효율적
    - 자료의 삽입/삭제 시 원소들을 모두 이동시켜야 함
  - 이진 탐색 트리는 빠르게 삽입/삭제를 수행할 수 있음
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
### dijkstra
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

### 퀵 정렬
- 분할 정복법을 사용하며, 평균적으로 가장 빠른 정렬 방식
  - 하나의 리스트를 피벗을 기준으로 2개의 비 균등한 크기로 분할
  - 분할된 부분 리스트를 정렬한 다음 합하여 전체가 정렬된 리스트가 되게 함
- 시간 복잡도
  - 최악의 경우: O(n^2)
  - 최선의 경우: O(nlongn)
  - 평균적인 경우: O(nlogn)
  - 퀵 정렬의 성능은 피벗의 선택에 크게 영향을 받음
    - 최선: 분할을 균등하게 하는 피벗을 선택
    - 최악: 이미 정렬된 배열에서 가장 작거나 가장 큰 값을 피벗으로 선택
- ![image](https://github.com/googoo9918/TIL/assets/102513932/57803244-818f-49ca-9a14-0b56d4d91b58)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/d1c7ea93-7de6-4cc0-bc41-f5c0c866fd1b)
```cpp
#include <iostream>
#include <vector>

using namespace std;

int partition(vector<int>& A, int left, int right) {
	int low = left + 1;
	int high = right;
	int pivot = A[left]; //피벗 설정

	while (low < high) { //low와 high가 역전되지 않는 한 반복
		for (; low <= right && A[low] < pivot; low++);
		for (; high >= left && A[high] > pivot; high--);
		if (low < high)
			swap(A[low], A[high]);
	}

	swap(A[left], A[high]);
	return high;
}

void quickSort(vector<int>& A, int left, int right) {
	if(left < right) {
		int pivot = partition(A, left, right);
		quickSort(A, left, pivot - 1);
		quickSort(A, pivot + 1, right);
	}
}

int main() {
	vector<int> A = { 5, 3, 8, 4, 9, 1, 6, 2, 7 };
	quickSort(A, 0, A.size() - 1);

	for (int num : A) {
		cout << num << " ";
	}

	return 0;
}
```

### 기수 정렬
- 기수 정렬
  - 비교 기반 정렬 알고리즘이 아닌, 자릿수를 기준으로 정렬하는 정렬 알고리즘
  - 각 원소의 자릿수를 비교하여 정렬을 수행함
- 구조
  - 각 자릿수에 따라 여러 번의 반복을 통해 전체 원소들을 정렬
- 과정
  - 가장 작은 자릿수부터 시작하여 각 자릿수를 기준으로 그룹화
    - 일의 자리부터 시작하여 각 원소를 0부터 9까지의 그룹으로 나눔
  - 그룹화된 원소들을 순서대로 다시 배열에 저장
  - 가장 낮은 자릿수에 대한 그룹화와 정렬 완료 시, 다음으로 높은 자릿수로 이동하여 과정 반복
- 시간 복잡도 : O(n)
  - n개의 레코드, d개의 자릿수로 이루어진 키를 기수 정렬
- ![image](https://github.com/googoo9918/TIL/assets/102513932/2a1ccbb4-f8ec-4f1d-8964-6ef3de4aebaa)
  - 버킷 개수는 키의 표현 방법과 밀접한 관계를 가짐
```cpp
#include <iostream>
#include <vector>
#include <queue>

const int BUCKETS = 10;
const int DIGITS = 10;

void radixSort(std::vector<int>& list) {
	std::queue<int> queues[BUCKETS];
	int factor = 1; //현재 정렬 중인 자릿수를 나타내는 데 사용
	int n = list.size();

	for (int d = 0; d < DIGITS; d++) {
		for (int i = 0; i < n; i++) //리스트의 모든 요소를 해당 자릿수에 따라 적절한 버킷에 배치
			queues[(list[i] / factor) % 10].push(list[i]);

		for(int b=0, i=0; b< BUCKETS; b++) //버킷의 요소를 순서대로 다시 리스트로 복사
			while (!queues[b].empty()) {
				list[i++] = queues[b].front();
				queues[b].pop();
			}

		factor *= 10; //다음 자릿수로 이동
	}
}

int main() {
	std::vector<int> A = { 28, 93, 39, 81, 62, 72, 38, 26 };
	radixSort(A);
	for (int i = 0; i < A.size(); i++)
		std::cout << A[i] << " ";
	std::cout << "\n";
	return 0;
}
```

## 탐색
### 탐색
- 맵
  - 맵은 키를 가진 레코드 또는 엔트리라 불리는 키-값 쌍을 테이블에 저장함
- 순차 탐색
  - 탐색 방법 중에서 가장 간단하고 직접적인 탐색 방법
- 평균 비교 횟수
  - 탐색 성공: (n+1)/2번 비교
  - 탐색 실패: n번 비교
- 시간복잡도: O(n)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/8907fd42-07c7-41b3-bcd8-e0efef5a1174)
- 개선된 순차 탐색
  - 리스트 끝에 탐색 키를 저장함으로써, 배열 인덱스 조건을 무시할 수 있음

### 이진 탐색
- 정렬된 배열의 탐색에 적합
  - 반드시 배열이 정렬되어 있어야 함
- ![image](https://github.com/googoo9918/TIL/assets/102513932/164cb6e5-f7f5-4f48-9ab2-e3c271cf6381)
```cpp
#include <iostream>

using namespace std;

int binarySearchIter(int list[], int key, int low, int high)
{
	int middle;
	while(low <= high) {
		middle = (low + high) / 2;
		if (key == list[middle])
			return middle;
		else if (key > list[middle])
			low = middle + 1;
		else
			high = middle - 1;
	}
	return -1;
}

int main() {
	int list[] = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 };
	int size = sizeof(list) / sizeof(list[0]);
	int key = 7;

	int result = binarySearchIter(list, key, 0, size - 1);

	if (result != -1)
		cout << "Element found at index: " << result << endl;
	else
		cout << "Element not found in the array." << endl;
	return 0;
}
```

### 색인 순차 탐색
- 인덱스를 사용하여 그룹을 나눠 탐색의 효율을 높임
  - 주 자료 테이블에서 일정 간격으로 발췌한 자료 저장
  - 주 자료 테이블과 인덱스 테이블은 모두 정렬된 상태
- 시간 복잡도: O(m+n/m)
  - 인덱스 테이블 크기:m, 주 자료 리스트 크기:n
- ![image](https://github.com/googoo9918/TIL/assets/102513932/e89704d7-cd84-4ff7-bfce-0a29bbecf232)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/75d0cefe-cb2e-4ce2-8cc8-1344cf0ae918)

### 보간 탐색
- 탐색키 값의 위치를 예측하여 탐색을 진행
- 시간 복잡도: O(log(n))
- 이진 탐색과 유사하나 리스트를 불균등 분할하여 탐색
- ![image](https://github.com/googoo9918/TIL/assets/102513932/b2ecf25c-772b-45e4-88d0-64c8ce026ea3)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/4f9844fb-1d15-4466-a103-c688df00bd8f)
- 구현
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/f7a915b5-a6d5-4b7a-a72d-9bc2349f27f2)

### AVL 트리
- 모든 노드의 왼쪽과 오른쪽 서브 트리의 높이 차가 1 이하인 이진 탐색 트리
- 시간 복잡도: O(log(n))
- 균형 인수
  - 왼쪽 서브트리 높이 - 오른쪽 서브 트리 높이
  - 모든 노드의 균형 인수가 +-1 이하이면 AVL 트리임
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/1407069d-fcd3-4d3f-b51a-487cbeb47358)
- 탐색 연산은 이진 탐색 트리와 동일
- 삽입 연산과 삭제 연산 시 균형 상태가 깨질 수 있음

### AVL 트리 삽입 연산
- 균형이 깨지는 4가지 경우
- 삽입된 노드 N으로부터 가장 가까우면서 균형인수가+-2가 된 조상 노드가 A라 할 대
  - LL타입: N이 A의 왼쪽 서브트리의 왼쪽 서브 트리에 삽입
  - LR타입: N이 A의 왼쪽 서브 트리의 오른쪽 서브 트리에 삽입
  - RR타입: N이 A의 오른쪽 서브 트리의 오른쪽 서브 트리에 삽입
  - RL타입: N이 A의 오른쪽 서브 트리의 왼쪽 서브 트리에 삽입
- 타입별 재균형 방법
  - LL회전: A부터 N까지의 경로상 노드의 오른쪽 회전
  - LR회전: A부터 N까지의 경로상 노드의 왼쪽-오른쪽 회전
  - RR회전: A부터 N까지의 경로상 노드의 왼쪽 회전
  - RL회전: A부터 N까지의 경로상 노드의 오른쪽-왼쪽 회전
- LL 회전
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/41299afb-e604-4a88-9c39-285ae9f695b0)
- RR 회전
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/24f1b574-386e-477a-8929-7b4e16468175)
- RL 회전
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/32111b32-39f7-42cb-8e94-6ac2a31d06f7)
- LR 회전
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/ad1cd293-4419-4e4c-89d8-a037b6b16b63)
### AVL 예시 및 구현
- ![image](https://github.com/googoo9918/TIL/assets/102513932/5af0b2bf-4088-4cb5-8590-b496531b1536)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/a1ff336a-5780-440b-92f1-12f3f1d9d988)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/680d711e-e3bd-4fc8-b772-98770efb0ec6)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/a8e3aa7c-ef5a-4533-be51-d09f173da8df)
- ![image](https://github.com/googoo9918/TIL/assets/102513932/dfe80d89-5500-4cc9-91d1-020346ed02a7)
