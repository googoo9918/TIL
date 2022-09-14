### 목차
- [최단 경로 알고리즘의 이해](#최단-경로-알고리즘의-이해)
  - [1. 최단 경로 문제란?](#1-최단-경로-문제란)
  - [최단 경로 문제 종류](#최단-경로-문제-종류)
  - [2. 최단 경로 알고리즘 - 다익스트라 알고리즘](#2-최단-경로-알고리즘---다익스트라-알고리즘)
  - [다익스트라 알고리즘 로직](#다익스트라-알고리즘-로직)
  - [3. 예제로 이해하는 다익스트라 알고리즘 (우선순위 큐 활용)](#3-예제로-이해하는-다익스트라-알고리즘-우선순위-큐-활용)
  - [1단계: 초기화](#1단계-초기화)
  - [2단계: 우선순위 큐에서 추출한 (A, 0) [노드, 첫 노드와의 거리] 를 기반으로 인접한 노드와의 거리 계산](#2단계-우선순위-큐에서-추출한-a-0-노드-첫-노드와의-거리-를-기반으로-인접한-노드와의-거리-계산)
  - [3단계: 우선순위 큐에서 (C, 1) [노드, 첫 노드와의 거리] 를 기반으로 인접한 노드와의 거리 계산](#3단계-우선순위-큐에서-c-1-노드-첫-노드와의-거리-를-기반으로-인접한-노드와의-거리-계산)
  - [4단계: 우선순위 큐에서 (D, 2) [노드, 첫 노드와의 거리] 를 기반으로 인접한 노드와의 거리 계산](#4단계-우선순위-큐에서-d-2-노드-첫-노드와의-거리-를-기반으로-인접한-노드와의-거리-계산)
  - [5단계: 우선순위 큐에서 (E, 5) [노드, 첫 노드와의 거리] 를 기반으로 인접한 노드와의 거리 계산](#5단계-우선순위-큐에서-e-5-노드-첫-노드와의-거리-를-기반으로-인접한-노드와의-거리-계산)
  - [6단계: 우선순위 큐에서 (B, 6), (F, 6) 를 순차적으로 추출해 각 노드  기반으로 인접한 노드와의 거리 계산](#6단계-우선순위-큐에서-b-6-f-6-를-순차적으로-추출해-각-노드--기반으로-인접한-노드와의-거리-계산)
  - [6단계: 우선순위 큐에서 (F, 7), (B, 8) 를 순차적으로 추출해 각 노드  기반으로 인접한 노드와의 거리 계산](#6단계-우선순위-큐에서-f-7-b-8-를-순차적으로-추출해-각-노드--기반으로-인접한-노드와의-거리-계산)
  - [우선순위 큐 사용 장점](#우선순위-큐-사용-장점)
  - [4. 구현을 위해 참고해야하는 JAVA 문법](#4-구현을-위해-참고해야하는-java-문법)
  - [참고: PriorityQueue 와 정렬](#참고-priorityqueue-와-정렬)
  - [참고: 우선순위 큐](#참고-우선순위-큐)
  - [5. 다익스트라 알고리즘 구현 (우선순위 큐 활용까지 포함)](#5-다익스트라-알고리즘-구현-우선순위-큐-활용까지-포함)
  - [다익스트라 알고리즘](#다익스트라-알고리즘)
  - [참고: 위 Hashmap 에서 각 키에 해당하는 값, 즉 ArrayList 의 모든 Edge 객체 추출하기](#참고-위-hashmap-에서-각-키에-해당하는-값-즉-arraylist-의-모든-edge-객체-추출하기)
  - [1단계 구현: 초기화](#1단계-구현-초기화)
  - [2단계 구현: 다익스트라 알고리즘](#2단계-구현-다익스트라-알고리즘)
  - [3단계: 그래프 정의 및 실행](#3단계-그래프-정의-및-실행)
  - [6. 시간 복잡도](#6-시간-복잡도)
  - [총 시간 복잡도](#총-시간-복잡도)
  - [참고: 힙의 시간 복잡도](#참고-힙의-시간-복잡도)
## 최단 경로 알고리즘의 이해

### 1. 최단 경로 문제란?
- 최단 경로 문제란 두 노드를 잇는 가장 짧은 경로를 찾는 문제임
- 가중치 그래프 (Weighted Graph) 에서 간선 (Edge)의 가중치 합이 최소가 되도록 하는 경로를 찾는 것이 목적

### 최단 경로 문제 종류
1. 단일 출발 (single-source) 최단 경로 문제
  - 그래프 내의 특정 노드 u 에서 출발하여, 그래프 내의 모든 다른 노드에 도착하는 가장 짧은 경로를 찾는 문제
2. 단일 도착 (single-destination) 최단 경로 문제
  - 모든 노드들로부터 출발해서, 그래프 내의 특정 노드 u 로 도착하는 가장 짧은 경로를 찾는 문제
3. 단일 쌍(single-pair) 최단 경로 문제
  - 주어진 노드 u 와 v 간의 최단 경로를 찾는 문제
4. 전체 쌍(all-pair) 최단 경로: 그래프 내의 모든 노드 쌍 (u, v) 사이에 대한 최단 경로를 찾는 문제

### 2. 최단 경로 알고리즘 - 다익스트라 알고리즘
- 다익스트라 알고리즘은 위의 최단 경로 문제 종류 중, 1번에 해당
  - 하나의 정점에서 다른 모든 정점에 도착하는 **가장 짧은 거리**를 구하는 문제

### 다익스트라 알고리즘 로직
- 첫 정점을 기준으로 연결되어 있는 정점들을 추가해 가며, 최단 거리를 갱신하는 기법
- 다익스트라 알고리즘은 너비우선탐색(BFS)와 유사
  - 첫 정점부터 각 노드간의 거리를 저장하는 배열을 만든 후, 첫 정점의 인접 노드 간의 거리부터 먼저 계산하면서, 첫 정점부터 해당 노드간의 가장 짧은 거리를 해당 배열에 업데이트
>  다익스트라 알고리즘의 다양한 변형 로직이 있지만, 가장 개선된 우선순위 큐를 사용하는 방식에 집중해서 설명하기로 함

- 우선순위 큐를 활용한 다익스트라 알고리즘
  - 우선순위 큐는 MinHeap 방식을 활용해서, 현재 가장 짧은 거리를 가진 노드 정보를 먼저 꺼내게 됨

  1) 첫 정점을 기준으로 배열을 선언하여 첫 정점에서 각 정점까지의 거리를 저장
     - 초기에는 첫 정점의 거리는 0, 나머지는 무한대로 저장함 (inf 라고 표현함)
     - 우선순위 큐에 (첫 정점, 거리 0) 만 먼저 넣음 
  
  2) 우선순위 큐에서 노드를 꺼냄
     - 처음에는 첫 정점만 저장되어 있으므로, 첫 정점이 꺼내짐
     - 첫 정점에 인접한 노드들 각각에 대해, 첫 정점에서 각 노드로 가는 거리와 현재 배열에 저장되어 있는 첫 정점에서 각 정점까지의 거리를 비교한다.
     - 배열에 저장되어 있는 거리보다, 첫 정점에서 해당 노드로 가는 거리가 더 짧을 경우, 배열에 해당 노드의 거리를 업데이트한다.
     - 배열에 해당 노드의 거리가 업데이트된 경우, 우선순위 큐에 넣는다.
       - 결과적으로 너비 우선 탐색 방식과 유사하게, 첫 정점에 인접한 노드들을 순차적으로 방문하게 됨
       - 만약 배열에 기록된 현재까지 발견된 가장 짧은 거리보다, 더 긴 거리(루트)를 가진 (노드, 거리)의 경우에는 해당 노드와 인접한 노드간의 거리 계산을 하지 않음

  3) 2번의 과정을 우선순위 큐에 꺼낼 노드가 없을 때까지 반복한다.

### 3. 예제로 이해하는 다익스트라 알고리즘 (우선순위 큐 활용)
![image](https://user-images.githubusercontent.com/102513932/176878483-ccb09bf2-050e-4fd1-96e1-fb96346eb45d.png)

### 1단계: 초기화
- 첫 정점을 기준으로 배열을 선언하여 첫 정점에서 각 정점까지의 거리를 저장
   - 초기에는 첫 정점의 거리는 0, 나머지는 무한대로 저장함 (inf 라고 표현함)
   - 우선순위 큐에 (첫 정점, 거리 0) 만 먼저 넣음 

![image](https://user-images.githubusercontent.com/102513932/176884830-347699b8-5225-4ea7-9a71-d0b24e7703d1.png)
### 2단계: 우선순위 큐에서 추출한 (A, 0) [노드, 첫 노드와의 거리] 를 기반으로 인접한 노드와의 거리 계산
- 우선순위 큐에서 노드를 꺼냄
     - 처음에는 첫 정점만 저장되어 있으므로, 첫 정점이 꺼내짐
     - 첫 정점에 인접한 노드들 각각에 대해, 첫 정점에서 각 노드로 가는 거리와 현재 배열에 저장되어 있는 첫 정점에서 각 정점까지의 거리를 비교한다.
     - 배열에 저장되어 있는 거리보다, 첫 정점에서 해당 노드로 가는 거리가 더 짧을 경우, 배열에 해당 노드의 거리를 업데이트한다.
     - 배열에 해당 노드의 거리가 업데이트된 경우, 우선순위 큐에 넣는다.
       - 결과적으로 너비 우선 탐색 방식과 유사하게, 첫 정점에 인접한 노드들을 순차적으로 방문하게 됨
       - 만약 배열에 기록된 현재까지 발견된 가장 짧은 거리보다, 더 긴 거리(루트)를 가진 (노드, 거리)의 경우에는 해당 노드와 인접한 노드간의 거리 계산을 하지 않음
       
> 이전 표에서 보듯이, 첫 정점 이외에 모두 inf 였었으므로, 첫 정점에 인접한 노드들은 모두 우선순위 큐에 들어가고, 첫 정점과 인접한 노드간의 거리가 배열에 업데이트됨

![image](https://user-images.githubusercontent.com/102513932/176884887-22265751-f98b-4ce6-9f7f-055cec6d9b8e.png)

### 3단계: 우선순위 큐에서 (C, 1) [노드, 첫 노드와의 거리] 를 기반으로 인접한 노드와의 거리 계산
- 우선순위 큐가 MinHeap(최소 힙) 방식이므로, 위 표에서 넣어진 (C, 1), (D, 2), (B, 8) 중 (C, 1) 이 먼저 추출됨 (pop)
- 위 표에서 보듯이 1단계까지의 A - B 최단 거리는 8 인 상황임
  - A - C 까지의 거리는 1, C 에 인접한 B, D에서 C - B는 5, 즉 A - C - B 는 1 + 5 = 6 이므로, A - B 최단 거리 8보다 더 작은 거리를 발견, 이를 배열에 업데이트
    - 배열에 업데이트했으므로 B, 6 (즉 A에서 B까지의 현재까지 발견한 최단 거리) 값이 우선순위 큐에 넣어짐
  - C - D 의 거리는 2, 즉 A - C - D 는 1 + 2 = 3 이므로, A - D의 현재 최단 거리인 2 보다 긴 거리, 그래서 D 의 거리는 업데이트되지 않음

![image](https://user-images.githubusercontent.com/102513932/176884936-74af44fd-936b-46d4-909a-1e95fa410f0a.png)

### 4단계: 우선순위 큐에서 (D, 2) [노드, 첫 노드와의 거리] 를 기반으로 인접한 노드와의 거리 계산
- 지금까지 접근하지 못했던 E와 F 거리가 계산됨
  - A - D 까지의 거리인 2 에 D - E 가 3 이므로 이를 더해서 E, 5
  - A - D 까지의 거리인 2 에 D - F 가 5 이므로 이를 더해서 F, 7

![image](https://user-images.githubusercontent.com/102513932/176884983-dffbdba9-7ff4-459b-bca7-0639ccd4d414.png)

### 5단계: 우선순위 큐에서 (E, 5) [노드, 첫 노드와의 거리] 를 기반으로 인접한 노드와의 거리 계산
- A - E 거리가 5인 상태에서, E에 인접한 F를 가는 거리는 1, 즉 A - E - F 는 5 + 1 = 6, 현재 배열에 A - F 최단거리가 7로 기록되어 있으므로, F, 6 으로 업데이트
  - 우선순위 큐에 F, 6 추가

![image](https://user-images.githubusercontent.com/102513932/176885015-549d4085-6720-40f2-803e-e202a47b16f3.png)
### 6단계: 우선순위 큐에서 (B, 6), (F, 6) 를 순차적으로 추출해 각 노드  기반으로 인접한 노드와의 거리 계산
- 예제의 방향 그래프에서 B 노드는 다른 노드로 가는 루트가 없음 
- F 노드는 A 노드로 가는 루트가 있으나, 현재 A - A 가 0 인 반면에 A - F - A 는 6 + 5 = 11, 즉 더 긴 거리이므로 업데이트되지 않음

![image](https://user-images.githubusercontent.com/102513932/176885050-6d77fa49-1e81-4e68-8dd3-63e13ef2db3f.png)

### 6단계: 우선순위 큐에서 (F, 7), (B, 8) 를 순차적으로 추출해 각 노드  기반으로 인접한 노드와의 거리 계산
- A - F 로 가는 하나의 루트의 거리가 7 인 상황이나, 배열에서 이미 A - F 로 가는 현재의 최단 거리가 6인 루트의 값이 있는 상황이므로, 더 긴거리인 F, 7 루트 기반 인접 노드까지의 거리는 계산할 필요가 없음, 그래서 계산없이 스킵함
  - 계산하더라도 A - F 거리가 6인 루트보다 무조건 더 긴거리가 나올 수 밖에 없음
- B, 8 도 현재 A - B 거리가 6이므로, 인접 노드 거리 계산이 필요 없음. 

> 우선순위 큐를 사용하면 불필요한 계산 과정을 줄일 수 있음

![image](https://user-images.githubusercontent.com/102513932/176885113-b8d071d5-3f63-444e-ad13-8593e500aff5.png)

### 우선순위 큐 사용 장점
- 지금까지 발견된 가장 짧은 거리의 노드에 대해서 먼저 계산
- 더 긴 거리로 계산된 루트에 대해서는 계산을 스킵할 수 있음


### 4. 구현을 위해 참고해야하는 JAVA 문법
### 참고: PriorityQueue 와 정렬
- 다음 참고 항목에서 익히는 PriorityQueue 는 내부적으로 정렬 기능을 수행하며,
- 따라서 PriorityQueue 에 넣어지는 특별한 형태의 객체인 경우, 객체간의 정렬을 위한 기준을 정의해야 함
- PriorityQueue 에서 내부적으로 정렬 기능을 사용할 때는, 해당 객체의 Comparable 인터페이스의 compareTo() 메서드를 호출하므로,
- 객체간 정렬 기준을 정의하기 위해, Comparable 인터페이스의 compareTo() 메서드를 정의해줘야 함

```java
public class Edge implements Comparable<Edge> {
    public int distance;
    public String vertex;
    
    public Edge(int distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }
    
    // System.out.println() 으로 객체 자체 출력시, 
    public String toString() {
        return "vertex: " + this.vertex + ", distance: " + this.distance;
    }
    
    @Override
    public int compareTo(Edge edge) {
        return this.distance - edge.distance;
    }
}
```

### 참고: 우선순위 큐
- java.util.PriorityQueue 클래스 사용

> 우선순위큐는 특성상 (1) 데이터를 넣고, (2) 우선순위에 따라 꺼내면서, 해당 데이터를 큐에서 삭제하는 기능을 수행하므로, 위 두가지 사용법을 이해하기로 함

```java
// PriorityQueue 선언 : (1) 데이터를 넣기 (우선순위 큐 선언)

import java.util.PriorityQueue;

PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
```

```java
// PriorityQueue 데이터 추가 (add() 와 offer() 둘다 동일하게 데이터를 추가하는 기능을 제공함) : (1) 데이터를 넣기
priorityQueue.add(new Edge(2, "A"));
priorityQueue.add(new Edge(5, "B"));
priorityQueue.offer(new Edge(1, "C"));
priorityQueue.offer(new Edge(7, "D"));
System.out.println(priorityQueue);
//[vertex: C, distance: 1, vertex: B, distance: 5, vertex: A, distance: 2, vertex: D, distance: 7]
// PriorityQueue 의 최상단 값을 가져오기만 하기 (해당 값이 삭제되지는 않음)
priorityQueue.peek();
// vertex: C, distance: 1
// PriorityQueue 의 데이터 읽기 (PriorityQueue 는 우선순위가 높은 값을 읽기 위해 사용함) :  (2) 우선순위에 따라 꺼내고, 해당 데이터 삭제하기
// PriorityQueue 의 최상단 값을 가져오고, 해당 값을 삭제함 (내부적으로 두번째 우선순위 값이 최상단으로 이동함)
// poll() 이외에 remove() 메서드도 동일한 역할 

Edge edge1 = priorityQueue.poll();
System.out.println(edge1);
System.out.println(priorityQueue);
// vertex: C, distance: 1
// [vertex: A, distance: 2, vertex: B, distance: 5, vertex: D, distance: 7]
```

### 5. 다익스트라 알고리즘 구현 (우선순위 큐 활용까지 포함)

### 다익스트라 알고리즘
- 탐색할 그래프의 시작 정점과 다른 정점들간의 최단 거리 구하기

```
### 참고: Hashmap 에 들어 있는 모든 Key 가져오기
> 굉장히 다양한 방법이 있지만, 가장 간단한 기법을 사용하기로 함

```java
for (String key : graph.keySet()) {
    System.out.println(key);
    System.out.println(graph.get(key));    
}
// A
// [vertex: B, distance: 8, vertex: C, distance: 1, vertex: D, distance: 2]
// B
// []
// C
// [vertex: B, distance: 5, vertex: D, distance: 2]
// D
// [vertex: E, distance: 3, vertex: F, distance: 5]
// E
// [vertex: F, distance: 1]
// F
// [vertex: A, distance: 5]
```

### 참고: 위 Hashmap 에서 각 키에 해당하는 값, 즉 ArrayList 의 모든 Edge 객체 추출하기
```java
ArrayList<Edge> nodeList = graph.get("A");
for (int index = 0; index < nodeList.size(); index++) {
    System.out.println(nodeList.get(index));
}
// vertex: B, distance: 8
// vertex: C, distance: 1
// vertex: D, distance: 2
```

### 1단계 구현: 초기화
- 첫 정점을 기준으로 배열을 선언하여 첫 정점에서 각 정점까지의 거리를 저장
   - 초기에는 첫 정점의 거리는 0, 나머지는 무한대로 저장함 (inf 라고 표현함)

```java
public class Edge implements Comparable<Edge> {
    public int distance;
    public String vertex;
    
    public Edge(int distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }
    
    // System.out.println() 으로 객체 자체 출력시, 
    public String toString() {
        return "vertex: " + this.vertex + ", distance: " + this.distance;
    }
    
    @Override
    public int compareTo(Edge edge) {
        return this.distance - edge.distance;
    }
}
```

```java
import java.util.PriorityQueue;
import java.util.HashMap;

public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<Edge>> graph, String start) {
        // grpah는 노드와 vertex의 정보를 담음, string은 시작지점. 리턴값은 distance 변수와 동일하게
        HashMap<String, Integer> distances = new HashMap<String, Integer>(); // 각 노드별 거리를 담은 distance 리턴
        for (String key : graph.keySet()) {
            distances.put(key, Integer.MAX_VALUE); //일단 다 최대값으로 설정해 놓기.
        }
        distances.put(start, 0); // 시작부분은 0으로 설정하기 (자기 자신 노드)
        // 거리 저장 배열 생성 완료
        
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        priorityQueue.add(new Edge(distances.get(start), start));  // 첫 부분에는 자기 자신(start)을 넣으면서 시작.
        //우선순위 큐 생성 완료
        
        // 알고리즘 작성
        return distances;
    }
}
```

```java
DijkstraPath dObject = new DijkstraPath();
dObject.dijkstraFunc(graph, "A");
// {A=0, B=2147483647, C=2147483647, D=2147483647, E=2147483647, F=2147483647}
```

### 2단계 구현: 다익스트라 알고리즘 
- while 구문 내에서 모두 작성되므로, 내부 코드를 한번에 작성하기로 함

```java
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<Edge>> graph, String start) {
        Edge edgeNode, adjacentNode;
        ArrayList<Edge> nodeList;
        int currentDistance, weight, distance;
        String currentNode, adjacent;
        HashMap<String, Integer> distances = new HashMap<String, Integer>();
        for (String key : graph.keySet()) {
            distances.put(key, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        priorityQueue.add(new Edge(distances.get(start), start));
        
        // 알고리즘 작성
        while (priorityQueue.size() > 0) { //우선순위 큐에 더 이상 검색할 노드가 없을 경우 끝냄.
            edgeNode = priorityQueue.poll();  
            // 우선순위 큐에서 제일 앞에 있는 노드 가져와서 edgenode에 저장.(우선순위 큐에서는 지워짐)
            currentDistance = edgeNode.distance;
            currentNode = edgeNode.vertex;
            // 각각의 attribute 값도 저장해줌.
            
            if (distances.get(currentNode) < currentDistance) {
                continue; 
                //현재 거리 저장 배열에 있는 값보다 우선순위 큐에서 빼온 distance의 값이 더 크다면 계산을 생략한다.
            }
            
            nodeList = graph.get(currentNode); //현재 노드의 graph 값 nodeList에 저장. 
            for (int index = 0; index < nodeList.size(); index++) {
                adjacentNode = nodeList.get(index); //인접 노드의 정보를 갖기 위한 임시 변수
                adjacent = adjacentNode.vertex; // 인접 노드의 vertex 저장
                weight = adjacentNode.distance; // 인접 노드의 거리 저장.
                distance = currentDistance + weight; 
                // currentDIstance는 우선순위 큐에서 빠져 나온 노드의 기본 거리값임
                // 이 거리값과 index마다 달라지는 가중치를 더한 값을 distance로 설정.
                
                if (distance < distances.get(adjacent)) { //distance가 원래 거리저장 배열에 있던 값보다 작으면
                    distances.put(adjacent, distance); // 거리저장 배열에 존자해는 값 변경
                    priorityQueue.add(new Edge(distance, adjacent)); // 우선순위 큐에 바뀐 edge 넣어줌.
                }
            }
        }
        return distances; //거리 저장 배열 리턴.
    }
}
```
![image](https://user-images.githubusercontent.com/102513932/176881856-43ac3f18-c210-4dec-9654-4eb1a0fd6f52.png)
### 3단계: 그래프 정의 및 실행
```java
HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
graph.put("A", new ArrayList<Edge>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D"))));
graph.put("B", new ArrayList<Edge>());
graph.put("C", new ArrayList<Edge>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
graph.put("D", new ArrayList<Edge>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
graph.put("E", new ArrayList<Edge>(Arrays.asList(new Edge(1, "F"))));
graph.put("F", new ArrayList<Edge>(Arrays.asList(new Edge(5, "A"))));
```

```java
DijkstraPath dObject = new DijkstraPath();
dObject.dijkstraFunc(graph, "A");
//{A=0, B=6, C=1, D=2, E=5, F=6}
```
![image](https://user-images.githubusercontent.com/102513932/176881846-05d2dd8e-569b-4059-8782-147e5f2351a2.png)
### 6. 시간 복잡도
- 위 다익스트라 알고리즘은 크게 다음 두 가지 과정을 거침
  - 과정1: 각 노드마다 인접한 간선들을 모두 검사하는 과정
  - 과정2: 우선순위 큐에 노드/거리 정보를 넣고 삭제(pop)하는 과정
  
- 각 과정별 시간 복잡도
  - 과정1: 각 노드는 최대 한 번씩 방문하므로 (첫 노드와 해당 노드간의 갈 수 있는 루트가 있는 경우만 해당), 그래프의 모든 간선은 최대 한 번씩 검사
    - 즉, 각 노드마다 인접한 간선들을 모두 검사하는 과정은 O(E) 시간이 걸림, E 는 간선(edge)의 약자

  - 과정2: 우선순위 큐에 가장 많은 노드, 거리 정보가 들어가는 경우, 우선순위 큐에 노드/거리 정보를 넣고, 삭제하는 과정이 최악의 시간이 걸림
    - 우선순위 큐에 가장 많은 노드, 거리 정보가 들어가는 시나리오는 그래프의 모든 간선이 검사될 때마다, 배열의 최단 거리가 갱신되고, 우선순위 큐에 노드/거리가 추가되는 것임
    - 이 때 추가는 각 간선마다 최대 한 번 일어날 수 있으므로, 최대 O(E)의 시간이 걸리고, O(E) 개의 노드/거리 정보에 대해 우선순위 큐를 유지하는 작업은 $ O(log{E}) $ 가 걸림
      - 따라서 해당 과정의 시간 복잡도는 $ O(Elog{E}) $ 
    
### 총 시간 복잡도
  - 과정1 + 과정2 = O(E) + $ O(Elog{E}) $  = $ O(E + Elog{E}) = O(Elog{E}) $
  
### 참고: 힙의 시간 복잡도
- depth (트리의 높이) 를 h라고 표기한다면,
- n개의 노드를 가지는 heap 에 데이터 삽입 또는 삭제시, 최악의 경우 root 노드에서 leaf 노드까지 비교해야 하므로  h=log2n  에 가까우므로, 시간 복잡도는  O(logn)

