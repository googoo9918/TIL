## 배열
-----------------------------------------------------
- 배열의 기본 동작 원리 및 심화

### 0. Coplit.09
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/Coplit.09.md)
- 배열에서 요소를 뽑아 새로운 배열을 만들 때 여러 방법
- Arrays.copyOf()
  - 배열 복사 메소드
    - Arrays.copyOf(원본배열, 복사할 길이);
      - ex) int[] array1 = [1,2,3,4,5]; int[] array2 = Arrays.copyOf(array1,3);
    - Arrays.copyOfRange(원본배열, 복사할 시작 인덱스, 복사할 끝 인덱스)
      - 인덱스는 0부터 시작
      - 끝 인덱스 substring 처럼 알아서 1 빠짐
      - ex) int[] array3 = Arrays.copyOfRange(array1, 3, 4);
  - 배열 복사 메소드 2 (시작 위치 저장 가능)
    - System.arraycopy(src, srcPos, dest, destPos, length)
      - (원본 문자열, 원본 인덱스, 복사 대상, 시작 인덱스, 길이)

### 1. 큰 수 출력하기
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P1.md)
- 큰 특징 x
- 예외처리 깔끔하게
<br><br>

### 2. 보이는 학생
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P2.md)
- 큰 특징 x
<br><br>

### 3. 가위 바위 보
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P3.md)
- if / else 에서 case 분류 깔끔하게
<br><br>

### 4. 피보나치 수열
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P4.md)
- 피보나치 (배열, 변수)
- 여러개의 변수를 순서대로 swap 해주는 능력.
<br><br>

### 5. 소수(에라토스테네스 체)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P5.md)
- 어줍잖은 예외처리가 아닌, 이론 숙지.
<br><br>

### 6. 뒤집은 소수
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P6.md)
- isPrime 메소드
- reverse / 수식 이용(수식 이용 숙지 요망)
<br><br>

### 7. 점수계산
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P7.md)
- 큰 특징 x
<br><br>

### 8. 등수구하기
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P8.md)
- 한 번에 정리해서 풀려는 나쁜 습관..
- 처음에는 혼자 못풀었지만 두 번째 풀 때는 수월하게 풀림
<br><br>

### 9. 격자판 최대합
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P9.md)
- 1차원 배열에서의 대각선 표현
  - arr[i][n-i-1]
  - arr[i][i]
- 최댓값 또한 마찬가지, 마지막에 비교해줄 필요는 없다. 
  - 계속 비교해 나가면서 갱신하자!
<br><br>

### 10. 봉우리
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P10.md)
- 임시 변수를 통한 상대적 위치 조정
  - int nx = i + dx[k];
- 조건을 통한 index 차단
  - nx>=0 && nx<n
<br><br>

### 11. 임시반장 정하기
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P11.md)
- 행으로 접근할 것인가? 열로 접근할 것인가?
  - 조건을 따져 초기 전략 잘 수립할 것
- Worst Case를 따져 변수 초깃값을 잘 설정할 것
- 3중 for문 이라도 상수번 만큼 돌면 괜찮다
- 문제를 잘못 이해하고 있는건 아닌지 확인하는 자세 필요
<br><br>

### 12. 멘토링
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Inflearn/Array/P12.md)
- 변수의 크기가 작을 때 가능한 4중 for문
- 인덱스가 아닌, 학생 번호를 for문의 변수로 표현
<br><br>
