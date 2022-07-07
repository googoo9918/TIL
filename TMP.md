https://github.com/rhs0266/FastCampus
알고리즘 강의 자료 링크
-> 질문은 issue에 올리기!

![image](https://user-images.githubusercontent.com/102513932/177700973-1da7c2c9-914f-4be7-87c5-57553d31e304.png)

1. 문제를 올바른 순서로 이해한다
i) 읽기 (시간, 메모리 제한 / 문제 전체를 꼼꼼히!)

ii) 이해하기 (제공되는 정보(변수들) 정리 / 예제 데이터에 대해 이해) 

iii) 파악하기 (가능한 최대, 최소 정답에 맞는 데이터를 직접 생성 / 키워드가 되는 단어들을 체크)

2. 시간과 공간 복잡도를 계산한다.
-> 시간을 아끼기 : "짤 가치가 나"
-> ex) 짜봤자 시간 초과, 시간 낭비

3. 코드를 효율적으로 함수화해서 구현한다 .

-> 길어질수록 실수할 구멍이 많아진다. ![image](https://user-images.githubusercontent.com/102513932/177698221-f862de48-bc4d-438b-8e98-3a7e685d6362.png)
![image](https://user-images.githubusercontent.com/102513932/177698247-1950a653-9f65-4527-8663-2f7abff2b120.png)
-> 중복되는 코드는 함수화하기!

4. 코딩 테스트에서 부분 점수를 챙긴다.

![image](https://user-images.githubusercontent.com/102513932/177698371-d8a81462-d9fd-4bd3-b0b7-171d9848ab3b.png)
-> 문제를 전략적으로 풀어라!!
-> 결국 점수 싸움이다.

<완전 탐색>
문제를 해결하기 위해 확인해야 하는 모든 경우를 전부 탐색하는 방법. 
그 중에서도 백 트래킹을 통해야 하는 상황을 해결하기! 
* 모든 코테 문제에서 기본적으로 접근해 봐야 한다.  많은 연습이 필요!

장점: 부분 점수를 얻기 좋다
단점: 전부 탐색하기 때문에 시간 복잡도가 상대적으로 높다 

n개 중 1) 중복을 허용해서
2) 중복 없이


m개를 A) 순서 있게 나열하기 B) 고르기

완전 탐색은 함수 정의가 50%

![image](https://user-images.githubusercontent.com/102513932/177700772-fa7232ab-fbff-4084-bec7-08e7816be4dc.png)
-> n개의 숫자중 m개를 전부 골랐으면, 조건에 맞는 탐색을 성공한 것이고
-> 아직 m개를 모두 다 고르지 못했다면, brute-force를 통해 모든 방법을 시도해야한다.

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
