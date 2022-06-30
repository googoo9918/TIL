## 너비 우선 탐색 (Breadth-First Search)

### 1. BFS 와 DFS 란?
* 대표적인 그래프 **탐색** 알고리즘
  - 너비 우선 탐색 (Breadth First Search): 정점들과 같은 레벨에 있는 노드들 (형제 노드들)을 먼저 탐색하는 방식
  - 깊이 우선 탐색 (Depth First Search): 정점의 자식들을 먼저 탐색하는 방식

#### BFS/DFS 방식 이해를 위한 예제
- BFS 방식: A - B - C - D - G - H - I - E - F - J 
  - 한 단계씩 내려가면서, 해당 노드와 같은 레벨에 있는 노드들 (형제 노드들)을 먼저 순회함
- DFS 방식: A - B - D - E - F - C - G - H - I - J 
  - 한 노드의 자식을 타고 끝까지 순회한 후, 다시 돌아와서 다른 형제들의 자식을 타고 내려가며 순화함
![image](https://user-images.githubusercontent.com/102513932/176655191-41ede3ee-3c98-4519-9631-17bc1b5e4b1a.png)

### 2. JAVA 로 그래프를 표현하는 방법
- Java Collection Framework 에서 제공하는 Hashmap 과 ArrayList 를 활용해서 그래프를 표현할 수 있음
- ![image](https://user-images.githubusercontent.com/102513932/176655267-bdd98bfa-3958-4b47-96d9-e226bc18c5fa.png)

### 그래프를 자료구조로 작성하기
- HashMap 을 사용하여, 그래프를 자료구조로 작성할 수 있음
```JAVA
HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();

graph.put("A", new ArrayList<String>(Arrays.asList("B", "C")));
graph.put("B", new ArrayList<String>(Arrays.asList("A", "D")));
graph.put("C", new ArrayList<String>(Arrays.asList("A", "G", "H", "I")));
graph.put("D", new ArrayList<String>(Arrays.asList("B", "E", "F")));
graph.put("E", new ArrayList<String>(Arrays.asList("D")));
graph.put("F", new ArrayList<String>(Arrays.asList("D")));
graph.put("G", new ArrayList<String>(Arrays.asList("C")));
graph.put("H", new ArrayList<String>(Arrays.asList("C")));
graph.put("I", new ArrayList<String>(Arrays.asList("C", "J")));
graph.put("J", new ArrayList<String>(Arrays.asList("I")));
//{A=[B, C], B=[A, D], C=[A, G, H, I], D=[B, E, F], E=[D], F=[D], G=[C], H=[C], I=[C, J], J=[I]}
```

### 3. BFS 알고리즘 구현

- 자료구조 큐를 활용함
  - needVisit 큐와 visited 큐, 두 개의 큐를 생성
  
  ![image](https://user-images.githubusercontent.com/102513932/176655533-b9b0695a-6c95-4df1-aa78-00a6b57753a4.png)
- 큐의 구현은 간단히 ArrayList 클래스를 활용

```JAVA
import java.util.ArrayList;

ArrayList<String> aList = new ArrayList<String>();
aList.add("A");
aList.add("B");
String node = aList.remove(0);
System.out.println(aList); //[B]
System.out.println(node); // A
System.out.println(aList.contains("A")); //FALSE
```
```JAVA
ArrayList<String> aList = new ArrayList<String>();
aList.add("C");
aList.addAll(graph.get("A"));
System.out.println(aList); //[C, B, C]
```

### BFS 코드 구현 
- 각각의 알고리즘에서 자료구조가 사용됨을 이해할 수 있음 (BFS 에서는 큐 자료구조를 사용함)
- 각 자료구조는 자료구조 시간에, 변수/조건문/반복문을 기반으로 밑바닥부터 구현하는 코드도 익혔음

```JAVA
import java.util.ArrayList;
import java.util.HashMap;

public class BFSSearch {
    public ArrayList<String> bfsFunc(HashMap<String, ArrayList<String>> grpah, String startNode) { 
        //전달인자에 대한 정확한 이해 필요 , 처음 시작하는 node
        ArrayList<String> visited = new ArrayList<String>(); // 방문한 노드 리스트
        ArrayList<String> needVisit = new ArrayList<String>(); // 방문해야되는 노드 후보들 리스트
        
        needVisit.add(startNode); 
        
        while (needVisit.size() > 0) { //방문해야할 노드가 아직 있을 때
            String node = needVisit.remove(0); // 방문해야되는 노드들 중 첫 번째 지워주면서 방문하고자 하는 노드 설정.
            
            if (!visited.contains(node)) { //해당 노드에 아직 방문한 적이 없다면,
                visited.add(node); // 방문해주고
                needVisit.addAll(graph.get(node)); //graph에 있는 변수를 통해서 needVisit에 값 추가헤주기.
            }
        }
        return visited; //방문한 모든 노드 리턴해주기
    }
}
```

```JAVA
BFSSearch bObject = new BFSSearch();
bObject.bfsFunc(graph, "A"); 
// [A, B, C, D, G, H, I, E, F, J]
``` 
![image](https://user-images.githubusercontent.com/102513932/176658804-7fa84e73-aab0-48a3-99dc-3dc0343a9a97.png)

### 4. 시간 복잡도
- 일반적인 BFS 시간 복잡도
  - 노드 수: V
  - 간선 수: E
    - 위 코드에서 while needVisit 은 V + E 번 만큼 수행함
  - 시간 복잡도: O(V + E)
  