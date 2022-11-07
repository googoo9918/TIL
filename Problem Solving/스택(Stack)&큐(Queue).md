### 1. 올바른 괄호(큐)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Stack%26Queue/P1.md)
  - 큐를 사용하는 가장 대표적인 유형
    - 쌍을 갖는 개체 Test 용이
  - 조건이 많든 적든 결국 Case 분류하는게 제일 중요하지 않을까?

### 2. 괄호문자제거(큐)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Stack%26Queue/P2.md)
  - 큐를 사용하는 대표 유형
    - 강의와 거의 유사하게 풀었으나.. 출력만 조금 더 신경써보자
  - 스택(Stirng) 출력 방법
    - 1. for문 -> `stack.get(int index)` 
    - 2. for each -> for(char x : stack) ans += x
    - 3. stack.toString()
      - 대괄호와 콤마 같이 나옴

### 3. 크레인 인형뽑기(카카오)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Stack%26Queue/P3.md)
  - 뽑으면서 넣고 추후에 비교하는 것보다, 뽑고 비교하고 넣는게 더 나음..
    - 물론 둘 다 가능하겠지만, 최대한 행동을 잘게 쪼개는게 나을 듯 싶음
  - 스택처럼 보인다고 스택에 너무 집착하지 않아도 됨!
    - 크기가 정해져 있지 않다면 스택이 맞겠지만, 크기가 정해져 있다면 배열로 접근하는게 편할 수 있다

### 4. 후위식 연산(postfix)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Stack%26Queue/P4.md)
  - 대단한건 없다. 후위식 연산 하는 법만 숙지할 것
  - if-else if 에서 중복되는 부분 있으면 통일 처리할 것

### 5. 쇠막대기
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Stack%26Queue/P5.md)
  - 논리의 흐름, 사고의 흐름도 괜찮았으나.. 되는 것과 안되는 것을 명확히 분류하고 해결책을 찾아 나가야 함!!
    - 같은 고민을 반복하다 무의미하게 소요되는 시간이 많다
  - 괄호 문제가 나오면 일단 때려박자!!
    - 첫 if문의 조건을 어떻게 설정하는지에 따라 코드의 간결성이 달라질 수 있다

### 6. 공주 구하기
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Stack%26Queue/P6.md)
  - Queue 대표 유형