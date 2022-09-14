### 목차
- [최소 신장 트리의 이해](#최소-신장-트리의-이해)
  - [1. 최소 신장 트리 알고리즘](#1-최소-신장-트리-알고리즘)
  - [2. 크루스칼 알고리즘 (Kruskal's algorithm)](#2-크루스칼-알고리즘-kruskals-algorithm)
  - [3. Union-Find 알고리즘](#3-union-find-알고리즘)
  - [Union-Find 알고리즘의 고려할 점](#union-find-알고리즘의-고려할-점)
  - [union-by-rank 기법](#union-by-rank-기법)
  - [path compression](#path-compression)
  - [6. 크루스칼 알고리즘 (Kruskal's algorithm) 코드 작성](#6-크루스칼-알고리즘-kruskals-algorithm-코드-작성)
  - [Edge 클래스 정의](#edge-클래스-정의)
  - [Edge 데이터 자료구조화](#edge-데이터-자료구조화)
  - [Union-Find 알고리즘 메서드 작성](#union-find-알고리즘-메서드-작성)
  - [크루스칼 알고리즘 (Kruskal's algorithm) 작성](#크루스칼-알고리즘-kruskals-algorithm-작성)
  - [최종 코드](#최종-코드)
  - [테스트](#테스트)
  - [7. 시간 복잡도](#7-시간-복잡도)
## 최소 신장 트리의 이해

### 1. 최소 신장 트리 알고리즘
- 그래프에서 최소 신장 트리를 찾을 수 있는 알고리즘이 존재함
- 대표적인 최소 신장 트리 알고리즘
  - Kruskal’s algorithm (크루스칼 알고리즘), Prim's algorithm (프림 알고리즘)

### 2. 크루스칼 알고리즘 (Kruskal's algorithm)
1. 모든 정점을 독립적인 집합으로 만든다.
2. 모든 간선을 비용을 기준으로 정렬하고, 비용이 작은 간선부터 양 끝의 두 정점을 비교한다.
3. 두 정점의 최상위 정점을 확인하고, 서로 다를 경우 두 정점을 연결한다. (최소 신장 트리는 사이클이 없으므로, 사이클이 생기지 않도록 하는 것임)

> 탐욕 알고리즘을 기초로 하고 있음 (당장 눈 앞의 최소 비용을 선택해서, 결과적으로 최적의 솔루션을 찾음)

![image](https://user-images.githubusercontent.com/102513932/177572394-e4da28e0-5248-4e1a-84d6-1e5d712bb3a7.png)

![image](https://user-images.githubusercontent.com/102513932/177572528-1422d907-ee2e-441a-89a1-3f4976641f67.png)

### 3. Union-Find 알고리즘
- Disjoint Set을 표현할 때 사용하는 알고리즘으로 트리 구조를 활용하는 알고리즘
- 간단하게, 노드들 중에 연결된 노드를 찾거나, 노드들을 서로 연결할 때 (합칠 때) 사용
    - CYCLE을 체크하기 위해서 사용!
- Disjoint Set이란
  - 서로 중복되지 않는 부분 집합들로 나눠진 원소들에 대한 정보를 저장하고 조작하는 자료구조
  - 공통 원소가 없는 (서로소) 상호 배타적인 부분 집합들로 나눠진 원소들에 대한 자료구조를 의미함
  - Disjoint Set = 서로소 집합 자료구조

1. 초기화
   - n 개의 원소가 개별 집합으로 이뤄지도록 초기화
![image](https://user-images.githubusercontent.com/102513932/177572877-789b97f3-b263-46b7-b362-ae99a2f89e70.png)

2. Union
   - 두 개별 집합을 하나의 집합으로 합침, 두 트리를 하나의 트리로 만듬
![image](https://user-images.githubusercontent.com/102513932/177572941-3ad9a809-de77-4711-a688-f64db87e4dc8.png)


3. Find
   - 여러 노드가 존재할 때, 두 개의 노드를 선택해서, 현재 두 노드가 서로 같은 그래프에 속하는지 판별하기 위해, 각 그룹의 최상단 원소 (즉, 루트 노드)를 확인 
   - 같은 그래프에 속하는 노드끼리 이어준다면, cycle이 생겨 버리겠지? (같은 root 노드를 갖는지 확인하기)
![image](https://user-images.githubusercontent.com/102513932/177572985-ac2c1d21-76d4-44d9-b2df-3f168932a8d5.png)


### Union-Find 알고리즘의 고려할 점
- Union 순서에 따라서, 최악의 경우 링크드 리스트와 같은 형태가 될 수 있음.
- 이 때는 Find/Union 시 계산량이 O(N) 이 될 수 있으므로, 해당 문제를 해결하기 위해, union-by-rank, path compression 기법을 사용함 

![image](https://user-images.githubusercontent.com/102513932/177573195-e78880d4-e813-4f8a-abc8-971ed1f5e516.png)


### union-by-rank 기법
- 트리를 합칠 때 어떤 기법으로 합칠 것인가?
- 각 트리에 대해 높이(rank)를 기억해 두고,
- Union시 두 트리의 높이(rank)가 다르면, 높이가 작은 트리를 높이가 큰 트리에 붙임 (즉, 높이가 큰 트리의 루트 노드가 합친 집합의 루트 노드가 되게 함)
![image](https://user-images.githubusercontent.com/102513932/177573407-f2c493a9-c62c-4ecd-a484-88ff224924fa.png)

- 높이가 h - 1 인 두 개의 트리를 합칠 때는 한 쪽의 트리 높이를 1 증가시켜주고, 다른 쪽의 트리를 해당 트리에 붙여줌
![image](https://user-images.githubusercontent.com/102513932/177573497-adf1d5ca-8c59-4940-b51d-33015394970a.png)

- 초기화시, 모든 원소는 높이(rank) 가 0 인 개별 집합인 상태에서, 하나씩 원소를 합칠 때, union-by-rank 기법을 사용한다면,
  - 높이가 h 인 트리가 만들어지려면, 높이가 h - 1 인 두 개의 트리가 합쳐져야 함
  - 높이가 h - 1 인 트리를 만들기 위해 최소 n개의 원소가 필요하다면, 높이가 h 인 트리가 만들어지기 위해서는 최소 2n개의 원소가 필요함
  - 따라서 union-by-rank 기법을 사용하면, union/find 연산의 시간복잡도는 O(N) 이 아닌, $ O(log{N}) $ 로 낮출 수 있음

### path compression
- Find를 실행한 노드에서 거쳐간 노드를 루트에 다이렉트로 연결하는 기법
- Find를 실행한 노드는 이후부터는 루트 노드를 한번에 알 수 있음

![image](https://user-images.githubusercontent.com/102513932/177573742-669617f0-aa68-4e52-bbbf-5e3817cc90b5.png)

- union-by-rank 와 path compression 기법 사용시 시간 복잡도는 다음 계산식을 만족함이 증명되었음
  - $ O(M log^*{N}) $
  - $ log^*{N} $ 은 다음 값을 가짐이 증명되었음
    - N이 $ 2^{65536} $ 값을 가지더라도, $ log^*{N} $ 의 값이 5의 값을 가지므로, 거의 O(1), 즉 상수값에 가깝다고 볼 수 있음

<div style="text-align:left">
<table>
  <tr>
    <th style="text-align:center">N</th>
    <th style="text-align:center">$ log^*{N} $</th>
  </tr>
  <tr>
    <td style="text-align:left">1</td>
    <td style="text-align:left">0</td>
  </tr>
  <tr>
    <td style="text-align:left">2</td>
    <td style="text-align:left">1</td>
  </tr>
  <tr>
    <td style="text-align:left">4</td>
    <td style="text-align:left">2</td>
  </tr>
  <tr>
    <td style="text-align:left">16</td>
    <td style="text-align:left">3</td>
  </tr>
  <tr>
    <td style="text-align:left">65536</td>
    <td style="text-align:left">4</td>
  </tr>
  <tr>
    <td style="text-align:left">$ 2^{65536} $</td>
    <td style="text-align:left">5</td>
  </tr>
</table>
</div>

### 6. 크루스칼 알고리즘 (Kruskal's algorithm) 코드 작성
### Edge 클래스 정의

```java
public class Edge implements Comparable<Edge> {
    public int weight;
    public String nodeV;
    public String nodeU;
    // 위 세줄로 간선 가중치 + 간선이 연결된 두 노드 표현
    
    public Edge(int weight, String nodeV, String nodeU) {
        this.weight = weight;
        this.nodeV = nodeV;
        this.nodeU = nodeU;
    }
    
    public String toString() {
        return "(" + this.weight + ", " + this.nodeV + ", " + this.nodeU + ")";
    }
    
    @Override 
    public int compareTo(Edge edge) {
        return this.weight - edge.weight; //가중치 기준으로 정렬
    }
}
```

### Edge 데이터 자료구조화
![image](https://user-images.githubusercontent.com/102513932/177573959-43bd17fe-455b-4967-a7e3-8cee2ffcd97e.png)

```java
ArrayList<String> vetices = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
ArrayList<Edge> edges = new ArrayList<Edge>();
edges.add(new Edge(7, "A", "B"));
edges.add(new Edge(5, "A", "D"));
edges.add(new Edge(7, "B", "A"));
edges.add(new Edge(8, "B", "C"));
edges.add(new Edge(9, "B", "D"));
edges.add(new Edge(7, "B", "E"));
edges.add(new Edge(8, "C", "B"));
edges.add(new Edge(5, "C", "E"));
edges.add(new Edge(5, "D", "A"));
edges.add(new Edge(9, "D", "B"));
edges.add(new Edge(7, "D", "E"));
edges.add(new Edge(6, "D", "F"));
edges.add(new Edge(7, "E", "B"));
edges.add(new Edge(5, "E", "C"));
edges.add(new Edge(7, "E", "D"));
edges.add(new Edge(8, "E", "F"));
edges.add(new Edge(9, "E", "G"));
edges.add(new Edge(6, "F", "D"));
edges.add(new Edge(8, "F", "E"));
edges.add(new Edge(11, "F", "G"));
edges.add(new Edge(9, "G", "E"));
edges.add(new Edge(11, "G", "F"));
```

### Union-Find 알고리즘 메서드 작성
```java
import java.util.HashMap;

HashMap<String, String> parent = new HashMap<String, String>(); // 부모 노드의 정보 있어야됨.
HashMap<String, Integer> rank = new HashMap<String, Integer>(); // 자신의 RANK에 대한 정보 있어야됨.
```

```java
// 참고: path compression 기법 구현시, 각 node 의 rank 정보까지 업데이트할 필요는 없음.
// rank 정보를 사용하는 union-by-rank 기법은 루트 node 의 rank 정보만을 사용하기 때문.

public String find(String node) {
    // path compression 기법 구현 메소드
    // root 노드를 return 한다. (길에 있는 모든 값들은 하나의 root 노드를 가지게 되긴 하겠지)
    if (parent.get(node) != node) { //부모 노드에서 가져온 노드가 내가 찾고하는 노드가 아니면
        parent.put(node, find(parent.get(node))); // 재귀를 돌면서 root 노드를 찾을 때까지 반복한다.
        // find(parent.get(node))에서 root 노드가 맞다면 return 해줄 것이고
        // 아니면 재귀가 돌게 되겠지.. 어쨌든 root 노드가 결과값으로 나올 것.
        // 여기서 주의해야 할 것은 처음에 시작한 rank와 상관없이(재귀를 몇번 돌았는지와 상관없이) 다 같은 root 노드를 가리키게 될 것.
        // -> path compression 기법
     }
    return parent.get(node);
}
```

```java
public void union(String nodeV, String nodeU) { //간선을 잇는 메소드
    // 단, 두 노드를 연결했을때 cycle이 생기지 않을 때만 메소드를 호출한다고 가정.
    String root1 = find(nodeV); // root1은 nodev의 root 노드
    String root2 = find(nodeU); // root2은 nodeu의 root 노드
    
    // union-by-rank 기법
    if (rank.get(root1) > rank.get(root2)) { //root1의 랭크가 더 크다면
        parent.put(root2, root1); //root2의 부모 노드를 root1로 설정해주기.
    } else { // root2의 랭크가 작거나 같다면
        parent.put(root1, root2); //root1의 부모를 root2로 설정해주기
        if (rank.get(root1) == rank.get(root2)) { // 만약 둘의 랭크가 같다면
            rank.put(root2, rank.get(root2) + 1); // root2의 랭크를 1 더해주자.
        }
    }
}
```

```java
public void makeSet(String node) { //n개의 원소가 개별적인 집합으로 이뤄지도록 초기화
    // 1. 초기화에서 사용
    parent.put(node, node);
    rank.put(node, 0);
}
```

### 크루스칼 알고리즘 (Kruskal's algorithm) 작성
```java
import java.util.Collections;

public ArrayList<Edge> kruskalFunc(ArrayList<String> vetices, ArrayList<Edge> edges) {
    ArrayList<Edge> mst = new ArrayList<Edge>();
    Edge currentEdge;
    
    // 1. 초기화
    for (int index = 0; index < vertices.size(); index++) {
        makeSet(vertices.get(index));
    }
    
    // 2. 간선 weight 기반, sorting
    Collections.sort(edges); //edge에 이미 가중치 기준으로 comparable 정의되어있기 때문에 ㄱㅊ
    
    for (int index = 0; index < edges.size(); index++) {
        currentEdge = edges.get(index); //edge 저장하기 위한 임시변수.
        if (find(currentEdge.nodeV) != find(currentEdge.nodeU)) { // 루트노드가 다르다면 (사이클이 없다면)
            union(currentEdge.nodeV, currentEdge.nodeU); //합치고
            mst.add(currentEdge); // mst(최소신장트리)에 추가해주기
        }
    }
}
```

### 최종 코드
```java
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class KruskalPath {
    HashMap<String, String> parent = new HashMap<String, String>();
    HashMap<String, Integer> rank = new HashMap<String, Integer>();
    
    public String find(String node) {
        // path compresion 기법
        if (this.parent.get(node) != node) {
            this.parent.put(node, this.find(this.parent.get(node)));
        }
        return this.parent.get(node);
    }
    
    public void union(String nodeV, String nodeU) {
        String root1 = this.find(nodeV);
        String root2 = this.find(nodeU);
        
        // union-by-rank 기법
        if (this.rank.get(root1) > this.rank.get(root2)) {
            this.parent.put(root2, root1);
        } else {
            this.parent.put(root1, root2);
            if (this.rank.get(root1) == this.rank.get(root2)) {
                this.rank.put(root2, this.rank.get(root2) + 1);
            }
        }
    }
    
    public void makeSet(String node) {
        this.parent.put(node, node);
        this.rank.put(node, 0);
    }
    
    public ArrayList<Edge> kruskalFunc(ArrayList<String> vertices, ArrayList<Edge> edges) {
        ArrayList<Edge> mst = new ArrayList<Edge>();
        Edge currentEdge;
        
        // 1. 초기화
        for (int index = 0; index < vertices.size(); index++) {
            this.makeSet(vertices.get(index));
        }
        
        // 2. 간선 weight 기반 sorting
        Collections.sort(edges);
        
        for (int index = 0; index < edges.size(); index++) {
            currentEdge = edges.get(index);
            if (this.find(currentEdge.nodeV) != this.find(currentEdge.nodeU)) {
                this.union(currentEdge.nodeV, currentEdge.nodeU);
                mst.add(currentEdge);
            }
        }
        
        return mst;
    }
}
```

### 테스트
![image](https://user-images.githubusercontent.com/102513932/177574517-cbe90e53-778b-47af-a0ab-05f20286068f.png)

```java
KruskalPath kObject = new KruskalPath();
kObject.kruskalFunc(vertices, edges);
//[(5, A, D), (5, C, E), (6, D, F), (7, A, B), (7, B, E), (9, E, G)]
```

### 7. 시간 복잡도
- 크루스컬 알고리즘의 시간 복잡도는 O(E log E)
  - 다음 단계에서 2번, 간선을 비용 기준으로 정렬하는 시간에 좌우됨 (즉 간선을 비용 기준으로 정렬하는 시간이 가장 큼)
  1. 모든 정점을 독립적인 집합으로 만든다.
  2. 모든 간선을 비용을 기준으로 정렬하고, 비용이 작은 간선부터 양 끝의 두 정점을 비교한다.
     - 퀵소트를 사용한다면 시간 복잡도는 O(n log n) 이며, 간선이 n 이므로 O(E log E)
  3. 두 정점의 최상위 정점을 확인하고, 서로 다를 경우 두 정점을 연결한다. (최소 신장 트리는 사이클이 없으므로, 사이클이 생기지 않도록 하는 것임)
     - union-by-rank 와 path compression 기법 사용시 시간 복잡도가 결국 상수값에 가까움, O(1)
![image](https://user-images.githubusercontent.com/102513932/177574979-66b7f50c-c8ac-421d-9778-e3906dd450bd.png)