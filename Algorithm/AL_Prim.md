## 최소 신장 트리의 이해

### 1. 프림 알고리즘 (Prim's algorithm)
- 대표적인 최소 신장 트리 알고리즘
  - Kruskal’s algorithm (크루스칼 알고리즘), Prim's algorithm (프림 알고리즘)
- 프림 알고리즘 
  - 시작 정점을 선택한 후, 정점에 인접한 간선중 최소 간선으로 연결된 정점을 선택하고, 해당 정점에서 다시 최소 간선으로 연결된 정점을 선택하는 방식으로 최소 신장 트리를 확장해가는 방식
- Kruskal's algorithm 과 Prim's algorithm 비교
  - 둘다, 탐욕 알고리즘을 기초로 하고 있음 (당장 눈 앞의 최소 비용을 선택해서, 결과적으로 최적의 솔루션을 찾음)
  - Kruskal's algorithm은 가장 가중치가 작은 간선부터 선택하면서 MST를 구함
  - Prim's algorithm은 특정 정점에서 시작, 해당 정점에 연결된 가장 가중치가 작은 간선을 선택, 간선으로 연결된 정점들에 연결된 간선 중에서 가장 가중치가 작은 간선을 택하는 방식으로 MST를 구함

### 2. 그림으로 이해하는 프림 알고리즘
1. 임의의 정점을 선택, '연결된 노드 집합'에 삽입
2. 선택된 정점에 연결된 간선들을 간선 리스트에 삽입
3. 간선 리스트에서 최소 가중치를 가지는 간선부터 추출해서,
   - 해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 이미 들어 있다면, 스킵함(cycle 발생을 막기 위함)
   - 해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 들어 있지 않으면, 해당 간선을 선택하고, 해당 간선 정보를 '최소 신장 트리'에 삽입
4. 추출한 간선은 간선 리스트에서 제거
5. 간선 리스트에 더 이상의 간선이 없을 때까지 3-4번을 반복
![image](https://user-images.githubusercontent.com/102513932/177575253-49df00fd-d9e5-477e-b4bf-3336ba3a271f.png)
![image](https://user-images.githubusercontent.com/102513932/177575289-daf3aefd-e0be-43cc-a002-7b821c7abcf4.png)
![image](https://user-images.githubusercontent.com/102513932/177575331-28b0ca4a-5108-4c8f-8448-333b2ae192b8.png)

### 3. 프림 알고리즘 (Prim's algorithm) 코드 작성
### 참고: Edge 클래스 정의
- 객체간 정렬 기준을 정의하기 위해, Comparable 인터페이스의 compareTo() 메서드 정의

```java
public class Edge implements Comparable<Edge> {
    public int weight; //가중치
    public String node1; //간선과 연결되는 노드 1
    public String node2; // 간선과 연결되는 노드 2
    
    public Edge(int weight, String node1, String node2) { //생성자 정의
        this.weight = weight;
        this.node1 = node1;
        this.node2 = node2;
    }
    
    public String toString() {
        return "(" + this.weight + ", " + this.node1 + ", " + this.node2 + ")";
    }
    
    @Override 
    public int compareTo(Edge edge) {
        return this.weight - edge.weight; // 가중치로 정렬하기 위한 comprable
    }
}
```

### 참고: PriorityQueue  (우선순위 큐) 사용하기
```java
import java.util.PriorityQueue;

PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
priorityQueue.add(new Edge(2, "A", "B"));
priorityQueue.add(new Edge(5, "B", "D"));
priorityQueue.add(new Edge(3, "C", "A"));

while (priorityQueue.size() > 0) {
    System.out.println(priorityQueue.poll()); //최소 heap의 동작 방식대로 저장된 것을 확인할 수 있음.
// (2, A, B)
// (3, C, A)
// (5, B, D)
}
```

### 참고: HashMap 에 특정 키 존재 여부 확인
- containsKey() 메서드 사용 가능

```java
import java.util.HashMap;

HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();

graph.put("A", new ArrayList<Edge>());
graph.put("B", new ArrayList<Edge>());

graph.containsKey("B"); //특정 키 유무 확인
```

### 참고: 찾는 키(key)에 대한 값(value)가 없을 때, 디폴트 값 반환하기
```java
graph.getOrDefault("C", new ArrayList<Edge>());
// getOrDefault(Object key, V DefaultValue)
// key : map 요소의 키이다.
// defaultValue : 지정된 키로 매핑된 값이 없거나 null이면 반환하는 기본 값이다.
```

### 프림 알고리즘 코드 (프로젝트: CH30_PRIM_BASIC) 
0. 모든 간선 정보를 저장 (**adjacentEdges**)
1. 임의의 정점을 선택, '연결된 노드 집합(**connectedNodes**)'에 삽입
2. 선택된 정점에 연결된 간선들을 간선 리스트(**candidateEdgeList**)에 삽입
3. 간선 리스트(**candidateEdgeList**)에서 최소 가중치를 가지는 간선부터 추출해서,
   - 해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 이미 들어 있다면, 스킵함(cycle 발생을 막기 위함)
   - 해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 들어 있지 않으면, 해당 간선을 선택하고, 해당 간선 정보를 '최소 신장 트리(**mst**)'에 삽입
     - 해당 간선에 연결된 인접 정점의 간선들 중, '연결된 노드 집합(**connectedNodes**)' 에 없는 노드와 연결된 간선들만 간선 리스트(**candidateEdgeList**) 에 삽입 
       - '연결된 노드 집합(**connectedNodes**)' 에 있는 노드와 연결된 간선들을 간선 리스트에 삽입해도, 해당 간선은 스킵될 것이기 때문임
       - 어차피 스킵될 간선을 간선 리스트(**candidateEdgeList**)에 넣지 않으므로 해서, 간선 리스트(**candidateEdgeList**)에서 최소 가중치를 가지는 간선부터 추출하기 위한 자료구조 유지를 위한 effort를 줄일 수 있음 (예, 최소힙 구조 사용)
     
     
4. 선택된 간선은 간선 리스트에서 제거
5. 간선 리스트에 더 이상의 간선이 없을 때까지 3-4번을 반복

```java
public class Edge implements Comparable<Edge> {
    public int weight;
    public String node1;
    public String node2;
    
    public Edge(int weight, String node1, String node2) {
        this.weight = weight;
        this.node1 = node1;
        this.node2 = node2;
    }
    
    public String toString() {
        return "(" + this.weight + ", " + this.node1 + ", " + this.node2 + ")";
    }
    
    @Override 
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}
```

```java
ArrayList<Edge> myedges = new ArrayList<Edge>();
myedges.add(new Edge(7, "A", "B"));
myedges.add(new Edge(5, "A", "D"));
myedges.add(new Edge(8, "B", "C"));
myedges.add(new Edge(9, "B", "D"));
myedges.add(new Edge(7, "D", "E"));
myedges.add(new Edge(5, "C", "E"));
myedges.add(new Edge(7, "B", "E"));
myedges.add(new Edge(6, "D", "F"));
myedges.add(new Edge(8, "E", "F"));
myedges.add(new Edge(9, "E", "G"));
myedges.add(new Edge(11, "F", "G"));
```

```java
import java.util.HashMap;
import java.util.PriorityQueue;

public class PrimPath {
    public ArrayList<Edge> primFunc(String startNode, ArrayList<Edge> edges) {
        Edge currentEdge, poppedEdge, adjacentEdgeNode;
        ArrayList<Edge> currentEdgeList, candidateEdgeList, adjacentEdgeNodes;
        PriorityQueue<Edge> priorityQueue;
        
        ArrayList<String> connectedNodes = new ArrayList<String>(); //연결된 노드 집합
        ArrayList<Edge> mst = new ArrayList<Edge>(); // 최소 신장 트리
        HashMap<String, ArrayList<Edge>> adjacentEdges = new HashMap<String, ArrayList<Edge>>(); 
        // 노드에 연결된 간선들 표현
        // 노드를 key로 주고, 노드에 연결된 간선 정보들을 arraylist에 저장.
        
        for (int index = 0; index < edges.size(); index++) { //초기화 (adjacent)
            currentEdge = edges.get(index); // currentEdge는 Myedges에 있는 값을 저장하기 위한 임시 변수.
            if (!adjacentEdges.containsKey(currentEdge.node1)) { // adjacetnEdges에 node1 없으면
                adjacentEdges.put(currentEdge.node1, new ArrayList<Edge>()); // 넣어주기
            }
            if (!adjacentEdges.containsKey(currentEdge.node2)) { // adjacetnEdges에 node2 없으면
                adjacentEdges.put(currentEdge.node2, new ArrayList<Edge>()); // 넣어주기
            }
        } // 중복된 값을 피하면서 adjacentEdges 테이블 양식을 만들어주기 위한 for문임.
        
        for (int index = 0; index < edges.size(); index++) {
            currentEdge = edges.get(index); //currentEdge는 임시변수 
            currentEdgeList = adjacentEdges.get(currentEdge.node1);
            // currentEdgeList 또한 임시변수.
            // 지금 currentEdgeList에 값을 가져오는게 아니라, currentEdge.node1에 할당되어 있는 공간을 가져오는 것임. 
            currentEdgeList.add(new Edge(currentEdge.weight, currentEdge.node1, currentEdge.node2));
            // 이 비어있는 공간에, 값 추가해주기.
            currentEdgeList = adjacentEdges.get(currentEdge.node2);
            currentEdgeList.add(new Edge(currentEdge.weight, currentEdge.node2, currentEdge.node1));
            // 비어있는 공간에, 값 추가해주기.
        } //key에 따른 공간이 할당되어 있으므로, 연산은 중복될 수 있어도 값이 튀는 일은 없을 것임.
        
        connectedNodes.add(startNode);
        candidateEdgeList = adjacentEdges.getOrDefault(startNode, new ArrayList<Edge>());
        // 선택한 정점에 연결된 모든 간선들을 간선 리스트(candidateEdgeList)에 넣는다.
        // 연결된게 없을 수 있으니 getOrDefault로 처리한다.
        priorityQueue = new PriorityQueue<Edge>();
        for (int index = 0; index < candidateEdgeList.size(); index++) {
            priorityQueue.add(candidateEdgeList.get(index)); 
            //우선순위 큐를 통해서 간선 가중치 순으로 정렬.
        }
        
        while (priorityQueue.size() > 0) { //큐에 값이 남아 있으면,
            poppedEdge = priorityQueue.poll(); // 값 뺴서 저장해줌.
            if (!connectedNodes.contains(poppedEdge.node2)) {  
                // node1은 들어가있는게 맞지만, node2는 안들어가 있을 수 있으니까 안들어가 있다면
                connectedNodes.add(poppedEdge.node2); // 해당 edge 를 추가
                mst.add(new Edge(poppedEdge.weight, poppedEdge.node1, poppedEdge.node2));
                // mst에 추가.
                adjacentEdgeNodes = adjacentEdges.getOrDefault(poppedEdge.node2, new ArrayList<Edge>());
                for (int index = 0; index < adjacentEdgeNodes.size(); index++) {
                    adjacentEdgeNode = adjacentEdgeNodes.get(index);
                    if(!connectedNodes.contains(adjacentEdgeNode.node2)) {
                        priorityQueue.add(adjacentEdgeNode);
                    }
                }
            }
        } //while문 정확히 이해하지 못함. 추후 더 생각해 볼 것.
        return mst;
        
    }
}
```

```java
PrimPath pObject = new PrimPath();
pObject.primFunc("A", myedges);
//[(5, A, D), (6, D, F), (7, A, B), (7, D, E), (5, E, C), (9, E, G)]
```
![image](https://user-images.githubusercontent.com/102513932/177575877-32829ac4-a32f-4a36-9731-584a631caacf.png)

### 4. 시간 복잡도
  - 최악의 경우, while 구문에서 모든 간선에 대해 반복하고, 최소 힙 구조를 사용하므로 O($ElogE$) 시간 복잡도를 가짐

### 참고: 개선된 프림 알고리즘
- 정확히 이해 못하겠음 주의.. 다시 살펴볼 것.
- 간선이 아닌 노드를 중심으로 우선순위 큐를 적용하는 방식
  - 초기화 - 정점:key 구조를 만들어놓고, 특정 정점의 key값은 0, 이외의 정점들의 key값은 무한대로 놓음.  모든 정점:key 값은 우선순위 큐에 넣음
  - 가장 key값이 적은 정점:key를 추출한 후(pop 하므로 해당 정점:key 정보는 우선순위 큐에서 삭제됨), (extract min 로직이라고 부름)
  - 해당 정점의 인접한 정점들에 대해 key 값과 연결된 가중치 값을 비교하여 key값이 작으면 해당 정점:key 값을 갱신
    - 정점:key 값 갱신시, 우선순위 큐는 최소 key값을 가지는 정점:key 를 루트노드로 올려놓도록 재구성함 (decrease key 로직이라고 부름)

# 개선된 프림 알고리즘 같은 경우, 추후에 다시 공부해서 정리할 것.