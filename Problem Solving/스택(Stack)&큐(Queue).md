### 1. 올바른 괄호(큐)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Stack%26Queue/P1.md)
  - 큐를 사용하는 가장 대표적인 유형
    - 쌍을 갖는 개체 Test 용이
  - 결국 Case 분류하는게 제일 중요하지 않을까?

### 2. 괄호문자제거(큐)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Stack%26Queue/P2.md)
  - 큐를 사용하는 대표 유형
    - 강의와 거의 유사하게 풀었으나.. 출력만 조금 더 신경써보자
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