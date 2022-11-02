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