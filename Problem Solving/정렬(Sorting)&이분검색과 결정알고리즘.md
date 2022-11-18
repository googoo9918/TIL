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

### 6. 장난꾸러기
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Sorting%26Searching/P6.md)
  - 정렬을 사용하면 복잡한 구현없이 깔끔하게 풀 수 있다.
    - 심지어 구현 실패함 ㅠㅠ
  - 깊은 복사
    - `int[] tmp = arr.clone();`

### 7. 좌표정렬
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Sorting%26Searching/P7.md)
  - 나는 기본 정렬에 조건식을 추가해서 풀었음
    - 기본 정렬 구현 확실히 익혀두자 계속 헷갈려함
  - **클래스 만들어 `Comparable` 구현 하고 `compareTo` 오버라이딩**
    - 도움없이 혼자 구현할 수 있어야 함

### 8. 이분검색
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Sorting%26Searching/P8.md)
  - 기본 이본검색 알고리즘! 익혀둘 수 있도록 하자
    - 처음에도 잘 풀었지만.. 조금 더 리팩토링 할 수 있을 듯
    - [이진 탐색](https://github.com/ssu18/TIL/blob/main/Algorithm/AL_BinarySearch.md)
      - 여기서는 ArryaList와 재귀를 활용