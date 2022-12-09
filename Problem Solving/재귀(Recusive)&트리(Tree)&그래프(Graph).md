### 1. 재귀함수
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Recuresive%2C%20Tree%2C%20Graph(DFS%2C%20BFS%20Basic)/P1.md)
  - 재귀함수 기초
  - 재귀가 어떻게 호출되고, 실행되는지만 머릿속에 잘 넣어두자

### 2. 이진수 출력(재귀)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Recuresive%2C%20Tree%2C%20Graph(DFS%2C%20BFS%20Basic)/P2.md)
  - 재귀함수 기초
  - 같은 논리구조가 반복될 때 종료조건만 잘 설정한다면..
  - 헷갈릴 때는 스택을 사용해 순서를 잘 따져볼 것!

### 3. 팩토리얼
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Recuresive%2C%20Tree%2C%20Graph(DFS%2C%20BFS%20Basic)/P3.md)
  - 리턴형이 void가 아닌 기본적인 재귀
    - 쓸데없는 인자를 넣지 않아도 됨

### 4. 피보나치 재귀(메모이제이션)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Recuresive%2C%20Tree%2C%20Graph(DFS%2C%20BFS%20Basic)/P4.md)
  - 원래 알고있던 피보나치 재귀를 2단계 디벨롭
    - 원래 모든 항의 재귀를 실행시켜 값을 구함 -> 마지막 항의 재귀만 실행시키고, 나머지 값은 배열의 저장하여 출력만 함
      - `fibo[]` 추가, for문으로 solution 호출 -> solution(n)만 호출하고 for문으로 fibo[] 호출
    - 배열에 저장되는 값도 수많은 재귀로 이뤄짐 -> 어차피 같은 연산을 계속 반복하니.. 미리 저장해놓고 값이 있으면 꺼내서 쓰자!
      - `if(fibo[n]!=0) return fibo[n];` 추가

### 5. 이진트리순회(DFS)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Recuresive%2C%20Tree%2C%20Graph(DFS%2C%20BFS%20Basic)/P5.md)
  - DFS 기본
  - 재귀 원리를 생각하여, 전위 중위 후위 순회에 대해 완벽히 이해할 것

### 6. 부분집합 구하기(DFS)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Recuresive%2C%20Tree%2C%20Graph(DFS%2C%20BFS%20Basic)/P6.md)
  - 양갈래로 뻗어나가는 dfs(사용 유무에 따라)
    - 기본적인 구조부터 확실히 익혀놔야 응용이 가능할 것

### 7. 이진트리 레벨탐색(BFS)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Recuresive%2C%20Tree%2C%20Graph(DFS%2C%20BFS%20Basic)/P7.md)
  - 계층마다 이뤄지는 기본 BFS
    - 기본 구조 잘 익혀둘 것! 

### 8. 송아지 찾기(BFS)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Recuresive%2C%20Tree%2C%20Graph(DFS%2C%20BFS%20Basic)/P8.md)
  - 레벨을 임의로 지정해주며(L), for문이 끝날 때 증가시켜주면 됨!
  - 또한 큐에 넣어주기만 하면 자연스럽게 BFS를 돌릴 수 있음 

### 12. 경로탐색(DFS)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Recuresive%2C%20Tree%2C%20Graph(DFS%2C%20BFS%20Basic)/P12.md)
  - 사실 구현하는 것이 그렇게 어렵지 않음.. 논리구조만 잘 따져서 단순하게 생각해보자!!
  - 경로 출력 부분 잘 확인할 것
    - 너무 배운대로만, 틀에 박힌 사고로만 행하지 말 것