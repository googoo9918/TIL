## 투 포인터, 슬라이딩 윈도우
- 효율성 : O(N^2) --> O(N)

### 1. 두 배열 합치기(투 포인터)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/TwoPointer/P1.md)
- 투 포인터의 기본
  - 사실 기본만 잘 하면..?
- 반복문을 한 번만 돌게하는게 중요!!
### 2. 공통원소 구하기(투 포인터)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/TwoPointer/P2.md)
- 반복문 한 번에 모든 것을 처리하려 하지 말 것
  - 한 번에 한 단계씩만 처리하면 됨!! 
  - 기준치보다 낮기만 하면 된다
- 케이스 분류 확실히 해줄 것
### 3. 최대 매출(슬라이딩 윈도우)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/TwoPointer/P3.md)
- 어차피 연속된 배열.. 더할건 더하고 뺄건 빼주자
  - index 처리에만 유의하면 될 듯
  - 시간 복잡도를 획기적으로 줄일 수 있음!
    - 중복된 값으로 더하지 않게 된다 (코드 비교해보기)