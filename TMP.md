# 1+A 버전
N개 중 1) 중복을 허용해서
M개를  A) 순서 있게 나열하기.

BOJ 15651 - N과 M (난이도:2) (실버3)


![image](https://user-images.githubusercontent.com/102513932/177707286-993903cd-4c33-4557-baa3-3f0afd48339e.png)

1) 시간 복잡도 계산
   N^M -> 7^7 = 82만
   1초 -> 1억 가정

2) MAIN의 코드량을 늘리지 말자.
-> 입, 출 구분하기

3) 
```JAVA
   static void rec_func(int k) {
        if (k == M + 1) { // 다 골랐다!
            // selected[1...M] 배열이 새롭게 탐색된 결과
            for (int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            for (int cand = 1; cand <= N; cand++) {
                // k 번째에 cand 가 올 수 있으면
                selected[k] = cand;

                // k+1 번부터 M 번까지 잘 채워주는 함수를 호출해준다.
                rec_func(k + 1);
                selected[k] = 0;
            }
        }
    }
```

재귀가 왜 이런식으로 설계됐는지 잘 생각해보자.
# 2_A 버전
N개 중 2)중복없이
M개를 A) 순서 있게 나열하기
BOJ 15649 - N과 M(1) 실버 3

1) 시간복잡도 -> 4*3*2 = 4!
mPn = O(N! / (N-M)!) => 8!/0! => 40,320

2) 공간복잡도 -> m개의 숫자 저장 --> O(m)

```java
 static void rec_func(int k) {
        if (k == M + 1) { // 1 ~ M 번째를 전부 다 골랐다!
            // selected[1...M] 배열이 새롭게 탐색된 결과
            for (int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            for (int cand = 1; cand <= N; cand++) {
                boolean isUsed = false;
                for (int i=1;i<k;i++)
                    if (cand == selected[i])
                        isUsed = true;
                // k 번째에 cand 가 올 수 있으면
                if (!isUsed) {
                    selected[k] = cand;
                    rec_func(k + 1);
                    selected[k] = 0;
                }
            }
        }
    }
```

```java 
static int[] selected, used;
 static void rec_func(int k) {
        if (k == M + 1) { // 1 ~ M 번째를 전부 다 골랐다!
            // selected[1...M] 배열이 새롭게 탐색된 결과
            for (int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            for (int cand = 1; cand <= N; cand++) {
                if (used[cand] == 1) continue;
                // k 번째에 cand 가 올 수 있으면
                selected[k] = cand;    used[cand] = 1;

                rec_func(k + 1);

                selected[k] = 0;       used[cand] = 0;
            }
        }
    }
```
# 1+B 버전
N개 중 1) 중복을 허용해서
M개를 B_고르기 (순서O, 조합)

BOJ 15662 - N과 M(4)
실버 3 난이도 2

1. 시간 복잡도
-> 4^3 보단 작다.
-> N^M 보단 작다.

2. 공간 복잡도
-> M개의 데이터 사용
-> O(M)

```JAVA
  static void rec_func(int k) {
        if (k == M + 1) { // 1 ~ M 번째를 전부 다 골랐다!
            // selected[1...M] 배열이 새롭게 탐색된 결과
            for (int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            int start = selected[k-1];
            if (start == 0) start = 1;
            for (int cand = start; cand <= N; cand++) {
                // k 번째에 cand 가 올 수 있으면
                selected[k] = cand;
                rec_func(k + 1);
                selected[k] = 0;
            }
        }
    }
```

# 2+B 버전

N개 중 2)중복 없이
M개를 B) 고르기

BOJ 15650 - N과 M(2)
실버 3 난이도 2

1) 시간복잡도
O(nCm) -> O(8C4) -> 70

2) 공간복잡도
O(M)



## 총 정리
![image](https://user-images.githubusercontent.com/102513932/177747891-ea4da75f-2a5d-4c59-b03e-14de94d156a6.png)

- 고를 수 있는 값의 종류 파악하기
- 중복을 허용하는지
- 순서가 중요한지
