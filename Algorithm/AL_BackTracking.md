### 목차
- [백 트래킹 기법의 이해](#백-트래킹-기법의-이해)
  - [1. 백 트래킹 (backtracking)](#1-백-트래킹-backtracking)
  - [상태 공간 트리 (State Space Tree)](#상태-공간-트리-state-space-tree)
  - [2. N Queen 문제 이해](#2-n-queen-문제-이해)
  - [Pruning (가지치기) for N Queen 문제](#pruning-가지치기-for-n-queen-문제)
  - [Promising for N Queen 문제](#promising-for-n-queen-문제)
  - [3. N Queen 문제코드 작성](#3-n-queen-문제코드-작성)
## 백 트래킹 기법의 이해

### 1. 백 트래킹 (backtracking)
- 백트래킹 (backtracking) 또는 퇴각 검색 (backtrack)으로 부름
- 제약 조건 만족 문제 (Constraint Satisfaction Problem) 에서 해를 찾기 위한 전략
  - 해를 찾기 위해, 후보군에 제약 조건을 점진적으로 체크하다가, 해당 후보군이 제약 조건을 만족할 수 없다고 판단되는 즉시 backtrack (다시는 이 후보군을 체크하지 않을 것을 표기)하고, 바로 다른 후보군으로 넘어가며, 결국 최적의 해를 찾는 방법
- 실제 구현시, 고려할 수 있는 모든 경우의 수 (후보군)를 상태공간트리(State Space Tree)를 통해 표현
  - 각 후보군을 DFS 방식으로 확인
  - 상태 공간 트리를 탐색하면서, 제약이 맞지 않으면 해의 후보가 될만한 곳으로 바로 넘어가서 탐색
    - Promising: 해당 루트가 조건에 맞는지를 검사하는 기법
    - Pruning (가지치기): 조건에 맞지 않으면 포기하고 다른 루트로 바로 돌아서서, 탐색의 시간을 절약하는 기법

> 즉, 백트래킹은 트리 구조를 기반으로 DFS로 깊이 탐색을 진행하면서 각 루트에 대해 조건에 부합하는지 체크(Promising), 만약 해당 트리(나무)에서 조건에 맞지않는 노드는 더 이상 DFS로 깊이 탐색을 진행하지 않고, 가지를 쳐버림 (Pruning)

### 상태 공간 트리 (State Space Tree)
- 문제 해결 과정의 중간 상태를 각각의 노드로 나타낸 트리
![image](https://user-images.githubusercontent.com/102513932/177576164-adc683c9-750c-4beb-b40c-e384775b080e.png)

### 2. N Queen 문제 이해
- 대표적인 백트래킹 문제
- NxN 크기의 체스판에 N개의 퀸을 서로 공격할 수 없도록 배치하는 문제
- 퀸은 다음과 같이 이동할 수 있으므로, 배치된 퀸 간에 공격할 수 없는 위치로 배치해야 함
![image](https://user-images.githubusercontent.com/102513932/177576223-77ac8ebb-36c6-4151-b23c-5ba3e004e2cf.png)

### Pruning (가지치기) for N Queen 문제
- 한 행에는 하나의 퀸 밖에 위치할 수 없음 (퀸은 수평 이동이 가능하므로)
- 맨 위에 있는 행부터 퀸을 배치하고, 다음 행에 해당 퀸이 이동할 수 없는 위치를 찾아 퀸을 배치
- 만약 앞선 행에 배치한 퀸으로 인해, 다음 행에 해당 퀸들이 이동할 수 없는 위치가 없을 경우에는, 더 이상 퀸을 배치하지 않고, 이전 행의 퀸의 배치를 바꿈
  - 즉, 맨 위의 행부터 전체 행까지 퀸의 배치가 가능한 경우의 수를 상태 공간 트리 형태로 만든 후, 각 경우를 맨 위의 행부터 DFS 방식으로 접근, 해당 경우가 진행이 어려울 경우, 더 이상 진행하지 않고, 다른 경우를 체크하는 방식
![image](https://user-images.githubusercontent.com/102513932/177576581-53da4d05-2d47-42c5-82dd-86effd963fa1.png)

### Promising for N Queen 문제
- 해당 루트가 조건에 맞는지를 검사하는 기법을 활용하여,
- 현재까지 앞선 행에서 배치한 퀸이 이동할 수 없는 위치가 있는지를 다음과 같은 조건으로 확인
  - 한 행에 어차피 하나의 퀸만 배치 가능하므로 수평 체크는 별도로 필요하지 않음
![image](https://user-images.githubusercontent.com/102513932/177576666-58c65bd2-d36c-4233-bc60-59f83fd5e217.png)

### 3. N Queen 문제코드 작성 
```java
import java.util.ArrayList;

public class NQueen {
    
    public boolean isAvailable(ArrayList<Integer> candidate, Integer currentCol) {
        // 조건 체크(promising)을 하기 위한 메소드.
        // CurrentCandidate와 index를 넘겨받아 candidate와 currentcol로 입력.
        Integer currentRow = candidate.size(); // candidate.size가 2라면, 1행과 2행에서 각 후보군을 갖고 있는 것이기 때문에
        // 자연스럽게 현재 행(currentRow)과 일치한다.
        for (int index = 0; index < currentRow; index++) {
            if ((candidate.get(index) == currentCol) || (Math.abs(candidate.get(index) - currentCol) == currentRow - index) ) {
                // 후보군이 갖고 있는 열이 현재 열과 동일하는가 ? -> 수직 체크
                // 행과 열의 절대값의 차가 동일한가? -> 대각선 체크 
                return false; //만약 그렇다면 false 리턴
            }
        }
        return true; // 아니라면 true 리턴
    }
    
    public void dfsFunc(Integer N, Integer currentRow, ArrayList<Integer> currentCandidate) { 
        // n개의 queen 설정 , 재귀 용법으로 사용 될텐데 현재 사용되는 행은 어디인가?(currentRow)
        // currentCandidate , 이전 행에서 이미 선택되어 있는 좌표 후보들.
        if (currentRow == N) { // n이 된 경우, 4를 기준으로 할 때 행(0,1,2,3)에서 적절한 후보를 다 찾았다는 의미,
            // 0 ~ n-1 까지 적절한 후보를 찾았으니
            System.out.println(currentCandidate); // 답만 출력해 주면 된다. 
            return ;
        }
        
        for (int index = 0; index < N; index++) { // 각 행의 모든 열의 위치를 찾기 위한 for문 
            if (this.isAvailable(currentCandidate, index) == true)  // 적합하다면
                currentCandidate.add(index); // 넣는다.
                this.dfsFunc(N, currentRow + 1, currentCandidate); // 재귀 용법을 통해 다음 행 검사.
                currentCandidate.remove(currentCandidate.size() - 1); // 재귀에서 넘어왔다는 것은 답을 출력하고 끝날 때, 혹은
            // 조건에 부합하는 값을 찾지 못할 때이다.(for문에서 다 false 나올 때) -> 가지치기 후 해당 candidate를 삭제한다.
            }
        }
    }   
```

```java
NQueen nObject = new NQueen();
nObject.dfsFunc(4, 0, new ArrayList<Integer>());
[1, 3, 0, 2]
[2, 0, 3, 1]

```