# 인공지능
## Introduction to Artificial Intelligence
### What is Intelligence?
- 유동 지능(Fluid Intelligence)
  - 새롭고 추상적인 문제를 해결하는 능력
    - 동작성 지능
    - 빠진 곳 찾기, 모양 맞추기, 차례 맞추기 등
    - 추론, 작업 기억, 주의 조절, 억제 조절
    - 학습 지식이나 경험을 통해 얻은 지식에 의존하지 않음
- 결정 지능(Crystallized Intelligence)
  - 세상의 법칙이나 그 법칙을 알아내기 위해 필요한 절차에 관한 정보
    - 언어적 지능
    - 상식, 어휘, 이해, 공통성 등과 관련
    - 학습에 의해 축적됨
- 결국 AI란, 지능적 행위를 자동화시키려는 행위임
- Rational Decisions
  - 합리적이라는 것은, 사전에 정의된 목표를 최대한 달성하는 것
  - Utility, 내가 모델링 할 목적 함수에 따라 결정됨
    - 즉, 합리적이라는 것은 Utility를 극대화 하는 행동임(내 목표에 맞춰 최적화!)
- Turing Test
  - 사람처럼 행동하는지? 에 대한 테스트
  - Rational과는 무관하다
- Strong/Weak AI
  - Strong AI
    - 인간을 완벽하게 모방한 인공지능
  - Weak AI
    - 유용한 도구로써 설계된 인공지능
      - 사람의 지능적 행동을 흉내 내는 수준
      - Turing Test
- Symbolic/Subsymbolic AI
  - Symbolic AI
    - 인간의 사고 과정을 모방하는데 중점
    - 명확한 규칙과 심볼을 통해 문제 해결
    - ex) 알고리즘 
  - Subsymbolic AI
    - 데이터에서 패턴 학습하여 결정 내리는 기계학습과 신경망에 의존
    - ex) 딥러닝
### Examples
- Coneceptual and algorithm O/X questions
  - is Turing test acting rationally?
  - Strong AI? Symbolic AI?

## Unimformed Search Methods
### Seaarch Problems
- search problem consists of
  - state space
  - successor function(with actions, costs)
  - start state and a goal test
- Problem
  - Initial state
  - Possible actions
  - Goal test
  - Path cost
- Introduction to Artificial Intelligence
  - What is intelligence?
  - Strong and weak Ai
  - Symbolic vs subSymbolic Ai
- Search Problems(Traveling in Romania)
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/c2dbfdfe-fea4-4a04-b265-e5e8e1a907a3)
  - State space
    - cities
  - Successor funtion
    - Roads: go to adjacent city with cost(==distance)
  - Start state
    - Arad
  - Goal test
    - Is_state == Bucharest?
### State Space Graphs & Search Trees
- State Space Graphs
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/996e4947-a3c8-49cf-8064-2b51ac7f43c9)
  - 모든 state를 다 node로 표현함
  - 각각의 state는 중복 없이 하나씩만 존재함
- Search Trees
  - ![image](https://github.com/googoo9918/TIL/assets/102513932/5c2c148c-7e53-49bf-aab0-af2744b38c71)
  - 각 노드는 상태 공간 그래프에서 하나의 전체 경로임
- State Space Graphs vs Search Trees
  - 상태 공간 그래프의 노드는 problem state임
    - 추상적 상태를 지님, 후속 상태를 가지며 목표 상태가 아닐 수 있고 선행 상태를 가질 수 있음
  - 검색 트리의 노드는 plans임
    - 일련의 행동(계획)을 나타냄
    - 한 개의 부모, 경로 길이, 깊이 및 비용을 가짐
    - 당연히 같은 state가 반복될 수도 잇음
    - state graph에 사이클이 있는 경우, search tree는 무한히 확장될 수도 있음
    - *fringe*는, 현재 진행할 수 있는 노드를 의미함
- Searches in state space graph
### Search Strategies
  - Completeness
    - 유한한 시간 내에 답을 찾을 수 있는가?
  - Optimality
    - 항상 최소 비용의 해결책을 찾는가?
      - 최적 알고리즘에 의해 찾아진 첫 번째 해결책은 최소 비용의 해결책일 것임
  - Time complexity
  - Space complexity 
  - b, d, m
    - b
      - 이웃 노드의 최대 수
    - d
      - 최적의 솔루션 depth
    - m
      - Maximum depth
### Depth-First Search
- Properites
  - Complete?
    - No, depth가 무한하거나, 루프가 있는 공간에서는 실패
    - 유한 공간에서는 complete함
  - Optimal?
    - 깊이 우선 탐색이기에, 최적이 아닌 목표를 먼저 찾게될 수 있음
  - Time?
    - O(b^m)
    - m이 d보다 큰 경우 매우 나빠짐
  - Space?
    - O(b^m)

### Depth-Limited Search
- Stack 사용
- 깊이 제한 l이 있는 깊이 우선 탐색임
  - 깊이 l에서의 노드는 후속 노드가 없는 것처럼 취급합
- Complete?
  - `l<d`인 경우, 완전하지 않음
- Optimal?
  - `l>d`라고 하더라도, 최적이지 않음
- Time?
  - O(b^l)
- Space?
  - O(b^l)
- Example
  - is depth-limited search optimal?

### Iterative Deepening Depth-First Search(IDS)
- limit, l을 한 개씩 늘리면서 진행함
  - 생각보다 오버헤드가 크지 않음
- Complete?
  - b가 무한하지 않은 경우를 제외하면 완전함
- Optimal?
  - 각 단계를 진행함으로써 발생하는 비용이 0보다 클 때(즉, 무료로 이동할 수 있는 경로가 없는 경우) Optimal함
- Time?
  - O(b^d)
- Space?
  - O(bd)

### BFS
- Queue 사용
- Comlete?
  - b가 유한하다면, 그렇다
- Optimal?
  - 가장 얕은 목표 노드가 최적일 경우만 Optimal하다
    - 노드의 깊이에 따라 비용이 증가하지 않는 경우
    - ex) 모든 단계의 비용이 1인경우
- Time?
  - 1 + b + b^2 + … + b^(d-1) + b^d = O(b^(d+1)) 
- Space?
  - O(b^d)

### DFS vs BFS
- When will BFS outperform DFS?
  - 최단 경로를 찾아야 할 때
    - 시작 노드로부터 가장 가까운 노드로부터 차례대로 탐색, 최단 경로 또는 최소 단계 요구 문제에 적합함
  - 균일 비용 탐색이 필요할 때
    - 모든 이동의 비용이 같고, 가장 낮은 비용의 해결책을 찾아야 하는 경우
- When will DFS outperform BFS?
  - 해결책의 깊이가 깊을 것으로 예상되는 경우
  - 답을 찾는 것이 중요하고, 답이 최단 경로일 필요가 없는 경우
  - 특정 조건을 만족하는 모든 해결책을 찾아야 하는 경우
- Properties of search methods
  - DFS vs BFS?
  - Complete?
    - what is the major problem?

### Uniform Cost Search
- 최소 비용 노드를 먼저 확장하는 탐색 전략
  - 우선순위 큐를 가장자리(fringe)로 사용
  - 기본적으로 BFS를 따름, 만약 모든 단계의 비용이 같다면 BFS와 동일하게 작동함
- Complete?
  - 모든 비용이 양수이고, 해결책의 총 비용이 유한하다면 그렇다
- Optimal?
  - UCS는 비용이 점점 증가하는 순서대로 상태를 탐색하기에, 최소 비용이 보장됨
- UCS는 경로 비용에 의한 탐색이기 때문에, 이동 단계 기반 깊이를 생각할 수는 있으나, 총 비용이 더 중요한 역할을 함
- 단점
  - 모든 방향으로 옵션을 탐색하기에, 목표 위치에 대한 정보가 없는 경우 비효율적일 수 있음
- Uniformed Search [heurastic search Methods]
  - Depth/ Breadth first search
  - Depth-limited search and IDS
  - UCS
- ![image](https://github.com/googoo9918/TIL/assets/102513932/e70461e3-d31a-4047-8c9e-9bf6a0506b65)

- Infromed Search
  - Greedy & A* search
  - 정의, 차이점

- Graph Search
  - Tree vs Graph Search
  - 정의, 차이점

- Definition of AI, categories
  - search properties, actual search and evaluation

- Game Search
  - minimax Search and Alpha-Beta Pruning

- Search under Uncertainty
  - Expectimax
  - Monte Carlo Tree Search

- Local Search Method
  - Hiill-climbin Search
  - Optimizations
  - Gradient Descent Method

- Non-Derivative Unconstrained Optimizations
  - Downhill simplex Method
  - Genetic Algorithm

-  Deteministic/Optimal search, optimization Search under uncertainty, and optimizations
   - minimax search
   - 우리가 왜 먹어가고 있는지?
   - uncertainty 기반 search 수행은 왜 하는 것?
     - time limit이 있고, search를 다 못하고 evalutaion function이 필요한데 그러면 어떻게 해야 하는가?, evaluation function과의 관계와 필요성


Examples

- Optimistic vs Pessimistic Heuristics
- Tree vs Graph Search
  - w.r.t optimally
- Deteministic & optimal adversarial search methods
  - optimization(alpha-beta)
  - what is the problem?
- Approximation of evaluation(value-function)
  - probabillity(e.g. Expertimax)
  - Estimating evaluatin function
  - Relationship wiht search(e.g complexity tradeoff)
- Monte Carlo Tree Search(MCTS)
  - How to approximate evaluation?
- Hill-climbing in TSP?
- Properites of optimization methods
  - Discrete or coninuous? Combinatorial? Optimal? Parameter-dependent properties
    - Optimize 방법에서 정할 수 있는 것들이 많음(step size, selection 방법 등)