## 순열과 조합(Permutation&Combination)
- [이론 및 예시](https://github.com/ssu18/TIL/blob/main/Algorithm/AL_Permutation%2CCombination.md)

### 1. 가위바위보(중복순열)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Coplit/%EC%88%9C%EC%97%B4%26%EC%A1%B0%ED%95%A9/%5B%EC%A4%91%EB%B3%B5%EC%88%9C%EC%97%B4%5D%20%EA%B0%80%EC%9C%84%EB%B0%94%EC%9C%84%EB%B3%B4.md)
  - 재귀에 사용되는 조건, 변수 / for문에 사용되는 조건,변수
    - 용도와 의미 정리 후 코드 작성 요망
    - 중복순열이 제일 쉬움..

### 2. 새로운 치킨 소스 레시피(순열)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Coplit/%EC%88%9C%EC%97%B4%26%EC%A1%B0%ED%95%A9/%5B%EC%88%9C%EC%97%B4%5D%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EC%B9%98%ED%82%A8%20%EC%86%8C%EC%8A%A4%20%EB%A0%88%EC%8B%9C%ED%94%BC.md)
- 순열보다 문자열 처리가 더 어려웠던..
  - String str = String.valueOf(new int[i]);
- 순열은 selected[i]만 잘 신경써보자
- **i와 count가 갖는 의미**


### 3. 블랙잭은 지겨워(조합)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Coplit/%EC%88%9C%EC%97%B4%26%EC%A1%B0%ED%95%A9/%5B%EC%A1%B0%ED%95%A9%5D%20%EB%B8%94%EB%9E%99%EC%9E%AD%EC%9D%80%20%EC%A7%80%EA%B2%A8%EC%9B%8C.md)
- **i와 count, start**의 의미 파악 요망!
  - 특히 start를 잘 생각해보자


### 4. 집밥이 그리워(멱집합)
- [정리](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/Coplit/%EC%88%9C%EC%97%B4%26%EC%A1%B0%ED%95%A9/%5B%EB%A9%B1%EC%A7%91%ED%95%A9%5D%20%EC%A7%91%EB%B0%A5%EC%9D%B4%20%EA%B7%B8%EB%A6%AC%EC%9B%8C.md)
- 모든 부분집합들의 합집합
- 멱집합 구하는 코드 잘 이해해 볼 것
- 조합 + 반복문으로도 해결 가능하다
  - 조합 코드를 확인하여 조합에 대한 이해를 높여보자.