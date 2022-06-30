## 깊이 우선 탐색 (Depth-First Search)
### 1. BFS 와 DFS 란?
* 대표적인 그래프 **탐색** 알고리즘
  - 너비 우선 탐색 (Breadth First Search): 정점들과 같은 레벨에 있는 노드들 (형제 노드들)을 먼저 탐색하는 방식
  - 깊이 우선 탐색 (Depth First Search): 정점의 자식들을 먼저 탐색하는 방식
  
#### BFS/DFS 방식 이해를 위한 예제
- BFS 방식: A - B - C - D - G - H - I - E - F - J 
  - 한 단계씩 내려가면서, 해당 노드와 같은 레벨에 있는 노드들 (형제 노드들)을 먼저 순회함
- DFS 방식: A - B - D - E - F - C - G - H - I - J 
  - 한 노드의 자식을 타고 끝까지 순회한 후, 다시 돌아와서 다른 형제들의 자식을 타고 내려가며 순회함

![image](https://user-images.githubusercontent.com/102513932/176659042-8032e01e-9679-4ff9-abe2-737baad2ab9d.png)

### 2. 그래프를 자료구조로 작성하기
- HashMap 을 사용하여, 그래프를 자료구조로 작성할 수 있음 

```java
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
// {A=[B, C], B=[A, D], C=[A, G, H, I], D=[B, E, F], E=[D], F=[D], G=[C], H=[C], I=[C, J], J=[I]}
```
![image](https://user-images.githubusercontent.com/102513932/176659176-49271af8-aff2-4d16-ba6c-3fef4ccfb613.png)

### 3. DFS 알고리즘 구현 

- 자료구조 스택과 큐를 활용함
  - needVisit 스택과 visited 큐, 두 개의 자료 구조를 생성

> BFS 자료구조는 두 개의 큐를 활용하는데 반해, DFS 는 스택과 큐를 활용한다는 차이가 있음을 인지해야 함

- 큐와 스택의 구현도 간단히 ArrayList 클래스를 활용

```java
import java.util.ArrayList;

ArrayList<String> aList = new ArrayList<String>();
aList.add("A");
aList.add("B");
String node = aList.remove(aList.size() - 1); //stack이니까 제일 마지막에 있는 것을 빼줘야겠지!
System.out.println(aList); //[A]
System.out.println(node); //B
```

```java
public class DFSSearch {
    public ArrayList<String> dfsFunc(HashMap<String, ArrayList<String>> graph, String startNode) {
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> needVisit = new ArrayList<String>();        
        
        needVisit.add(startNode);
        
        while (needVisit.size() > 0) {
            String node = needVisit.remove(needVisit.size() - 1); // BFS와 이 부분만 다름 (큐 -> 스택)
            if (!visited.contains(node)) {
                visited.add(node);
                needVisit.addAll(graph.get(node));
            }
        }
        return visited;
    }
}
```

```java
DFSSearch dObject = new DFSSearch();
dObject.dfsFunc(graph, "A"); //[A, C, I, J, H, G, B, D, F, E]
```

### 4. 시간 복잡도
- 일반적인 DFS 시간 복잡도
  - 노드 수: V
  - 간선 수: E
    - 위 코드에서 while need_visit 은 V + E 번 만큼 수행함
  - 시간 복잡도: O(V + E)
  
