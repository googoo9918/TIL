## 투 포인터, 슬라이딩 윈도우
- 효율성 : O(N^2) --> O(N)

### 1. 두 배열 합치기(투 포인터)
- [정리](https://github.com/googoo9918/TIL/blob/main/Problem%20Solving/Inflearn/TwoPointer%26SlidingWindow/P1.md)
- 투 포인터의 기본
  - 사실 기본만 잘 하면..?
- 반복문을 한 번만 돌게하는게 중요!!
### 2. 공통원소 구하기(투 포인터)
- [정리](https://github.com/googoo9918/TIL/blob/main/Problem%20Solving/Inflearn/TwoPointer%26SlidingWindow/P2.md)
- 투 포인터에서는 while문을 이용
- 확실한 케이스 분류 요망
- 정렬하고 구할 것인가? 구하고 정렬할 것인가?
### 3. 최대 매출(슬라이딩 윈도우)
- [정리](https://github.com/googoo9918/TIL/blob/main/Problem%20Solving/Inflearn/TwoPointer%26SlidingWindow/P3.md)
- 어차피 연속된 배열.. 더할건 더하고 뺄건 빼주자
  - index 처리에만 유의하면 될 듯
  - 시간 복잡도를 획기적으로 줄일 수 있음!
    - 중복된 값으로 더하지 않게 된다 (코드 비교해보기)

### 4. 연속 부분수열 (복합)
- [정리](https://github.com/googoo9918/TIL/blob/main/Problem%20Solving/Inflearn/TwoPointer%26SlidingWindow/P4.md)
- 케이스만 잘 나눠준다면..
- 사실 케이스를 나눌 필요가 없을 수도 있음
  - 정답 코드와 내 코드 비교해볼 것
- 해당 챕터에서 무조건 while문을 고집하지 말 것, for문의 쓰임도 분명히 존재
### 5. 연속된 자연수의 합 (복합)
- [정리](https://github.com/googoo9918/TIL/blob/main/Problem%20Solving/Inflearn/TwoPointer%26SlidingWindow/P5.md)
- 4번과 다른 듯 같은 문제
### 6. 최대 길이 연속 부분수열(복합)
- [정리](https://github.com/googoo9918/TIL/blob/main/Problem%20Solving/Inflearn/TwoPointer%26SlidingWindow/P6.md)
- 처음 설계만 잘 한다면..
- 논리적으로, 단계적으로 잘 푼듯?
  - 해설은 너무 특이성이 있지 않나 (참고만 할 것)