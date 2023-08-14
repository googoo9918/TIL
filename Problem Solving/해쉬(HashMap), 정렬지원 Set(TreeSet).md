### 1. 학급 회장(해쉬)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/HashMap%26TreeSet/P1.md)
- getOrDefault(key, defaultValue)
  - key에 해당하는 값이 없으면, defaultValue 삽입
- 어떤 문제에서 해쉬를 사용해야 하는지 생각해 볼 것
- 해쉬 사용의 기본 용례
- 마지막 도출 과정에서 entrySet과 keySet 모두 익혀 놓을 것

### 2. 아나그램(해쉬)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/HashMap%26TreeSet/P2.md)
- 기본 용례2, 특별한 건 없음

### 3. 매출액의 종류(해쉬, 슬라이딩 윈도우)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/HashMap%26TreeSet/P3.md)
- 해쉬와 슬라이딩 윈도우 복합
  - 어떤 자료구조를 사용할 것인지

### 4. 모든 아나그램 찾기
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/HashMap%26TreeSet/P4.md)
- P3에서 내 풀이는 좋았으나.. P4에서 내 풀이는 논리적 허점이 있었음 차이점을 잘 살펴보자
- map1.equals(map2);
- for문 동작 시 경계값 check 정도는 해주자!
  - 값 넣고 비교 vs 비교하고 값 넣기
    - 언제 어떤 문제가 생길 수 있는지?

### 5. K번째 큰 수
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/HashMap%26TreeSet/P5.md)
- 정렬지원 SET 숙지 (어려운 문제는 X)
- TreeSet<Integer> treeSet = new TreeSet<>(Collections.reverseOrder());