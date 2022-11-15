### 1. 선택정렬
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Sorting%26Searching/P1.md)
- 선택정렬은 최솟값을 가장 앞으로 가져오는 정렬
  - 최솟값이 아니라 index로 조절하면 쓸데없는 변수 하나를 줄일 수 있음

### 2. 버블정렬
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Sorting%26Searching/P2.md)
  - 버블정렬은 밀어서 배열의 마지막에 숫자를 하나씩 지정해주는 정렬
    - boolean형 변수를 통해 시간복잡도를 줄일 수 있음

### 3. 삽입정렬
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Sorting%26Searching/P3.md)
  - 삽입정렬은 부분배열의 크기를 늘려가며 정렬하는 방식
    - j가 어떻게 동작하는지 집중하라

### 4. Least Recently Used
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Sorting%26Searching/P4.md)
  - 무난한 문제, 무난한 풀이
    - 구현을 통한 정렬

### 5. 중복 확인
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Sorting%26Searching/P5.md)
  - 가벼운 문제, 정렬로도 풀 수 있음!
    - 2중 for
      - O(n^2)
    - HashMap
      - O(n)
    - 정렬 후 비교
      - O(nlogn)
  - 왜인지는 모르겠지만 2중 for문이 제일 빠른..