### 목차
- [그래프](#그래프)
  - [1. 그래프 (Graph) 란? <br><br>](#1-그래프-graph-란-)
  - [2. 그래프 (Graph) 관련 용어](#2-그래프-graph-관련-용어)
  - [3. 그래프 (Graph) 종류](#3-그래프-graph-종류)
    - [무방향 그래프 (Undirected Graph)](#무방향-그래프-undirected-graph)
    - [방향 그래프 (Directed Graph)](#방향-그래프-directed-graph)
    - [가중치 그래프 (Weighted Graph) 또는 네트워크 (Network)](#가중치-그래프-weighted-graph-또는-네트워크-network)
    - [연결 그래프 (Connected Graph) 와 비연결 그래프 (Disconnected Graph)](#연결-그래프-connected-graph-와-비연결-그래프-disconnected-graph)
    - [사이클 (Cycle) 과 비순환 그래프 (Acyclic Graph)](#사이클-cycle-과-비순환-그래프-acyclic-graph)
## 그래프
### 1. 그래프 (Graph) 란? <br><br>
* 그래프는 실제 세계의 현상이나 사물을 정점(Vertex) 또는 노드(Node) 와 간선(Edge)로 표현하기 위해 사용 <br><br>
* ![image](https://user-images.githubusercontent.com/102513932/176654170-39bc9984-b39d-48a6-a7c1-e92bed41e8e7.png)


### 2. 그래프 (Graph) 관련 용어
- 노드 (Node): 위치를 말함, 정점(Vertex)라고도 함
- 간선 (Edge): 위치 간의 관계를 표시한 선으로 노드를 연결한 선이라고 보면 됨 (link 또는 branch 라고도 함)
- 인접 정점 (Adjacent Vertex) : 간선으로 직접 연결된 정점(또는 노드)

- 참고 용어
  - 정점의 차수 (Degree): 무방향 그래프에서 하나의 정점에 인접한 정점의 수
  - 진입 차수 (In-Degree): 방향 그래프에서 외부에서 오는 간선의 수
  - 진출 차수 (Out-Degree): 방향 그래프에서 외부로 향하는 간선의 수
  - 경로 길이 (Path Length): 경로를 구성하기 위해 사용된 간선의 수
  - 단순 경로 (Simple Path): 처음 정점과 끝 정점을 제외하고 중복된 정점이 없는 경로
  - 사이클 (Cycle): 단순 경로의 시작 정점과 종료 정점이 동일한 경우
  
> 단순 경로 (A - B - C) <br>
> A-B-C-A-B-D 와 같은 식의 경로는 중복된 정점이 있으므로 단순 경로가 아님
> ![image](https://user-images.githubusercontent.com/102513932/176654413-449cd541-1f4f-43d2-8d3b-cd6ff8252610.png)

### 3. 그래프 (Graph) 종류
#### 무방향 그래프 (Undirected Graph)
- 방향이 없는 그래프
- 간선을 통해, 노드는 양방향으로 갈 수 있음
- 보통 노드 A, B가 연결되어 있을 경우, (A, B) 또는 (B, A) 로 표기


#### 방향 그래프 (Directed Graph)
- 간선에 방향이 있는 그래프
- 보통 노드 A, B가 A -> B 로 가는 간선으로 연결되어 있을 경우, <A, B> 로 표기 (<B, A> 는 B -> A 로 가는 간선이 있는 경우이므로 <A, B> 와 다름)

#### 가중치 그래프 (Weighted Graph) 또는 네트워크 (Network)
- 간선에 비용 또는 가중치가 할당된 그래프

#### 연결 그래프 (Connected Graph) 와 비연결 그래프 (Disconnected Graph)
- 연결 그래프 (Connected Graph)
  - 무방향 그래프에 있는 모든 노드에 대해 항상 경로가 존재하는 경우
- 비연결 그래프 (Disconnected Graph)
  - 무방향 그래프에서 특정 노드에 대해 경로가 존재하지 않는 경우


> 비연결 그래프 예
> ![image](https://user-images.githubusercontent.com/102513932/176654625-b6c0bb01-247e-44f3-9a7d-36bae54a46ab.png)

#### 사이클 (Cycle) 과 비순환 그래프 (Acyclic Graph)
- 사이클 (Cycle)
  - 단순 경로의 시작 노드와 종료 노드가 동일한 경우
- 비순환 그래프 (Acyclic Graph)
  - 사이클이 없는 그래프
  
> 비순환 그래프 예
> ![image](https://user-images.githubusercontent.com/102513932/176654692-95311b3a-6aec-406d-854e-b46028e2a78b.png)

- 트리는 그래프 중에 속한 특별한 종류라고 볼 수 있음

![image](https://user-images.githubusercontent.com/102513932/176654870-de4eb828-2104-41c1-9dd0-ea766e12ef74.png)

