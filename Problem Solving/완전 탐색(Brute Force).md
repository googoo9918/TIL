## 완전 탐색
- 문제를 해결하기 위해 확인해야 하는 모든 경우를 전부 탐색하는 방법. 
- 그 중에서도 백 트래킹을 통해야 하는 상황을 해결하기! 
- 모든 코테 문제에서 기본적으로 접근해 봐야 한다.  많은 연습이 필요! <br><br><br>

- 장점: 부분 점수를 얻기 좋다
- 단점: 전부 탐색하기 때문에 시간 복잡도가 상대적으로 높다 <br><br><br>

- n개 중 
  - 1) 중복을 허용해서 
  - 2) 중복 없이

- m개를 
  - A) 순서 있게 나열하기 
  - B) 고르기 
<br><br>

### 완전 탐색은 함수 정의가 50%

- ![image](https://user-images.githubusercontent.com/102513932/177700772-fa7232ab-fbff-4084-bec7-08e7816be4dc.png)
  - n개의 숫자중 m개를 전부 골랐으면, 조건에 맞는 탐색을 성공한 것이고
  - 아직 m개를 모두 다 고르지 못했다면, brute-force를 통해 모든 방법을 시도해야한다.

### 1+A 버전
- N개 중 1) 중복을 허용해서
- M개를 A) 순서 있게 나열하기(순열)
- [BOJ 15651](https://www.acmicpc.net/problem/15651) 
  - N과 M(3) (난이도:2) (실버3)
- [정리 및 풀이](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/BackJoon/%EC%99%84%EC%A0%84%20%ED%83%90%EC%83%89(BruteForce)/%EC%99%84%EC%A0%84%ED%83%90%EC%83%89/P15651(N%EA%B3%BC%20M(3)).md)
- 핵심 코드
```java
for (int cand = 1; cand <= n; cand++) { //핵심
                selected[k] = cand;
                rec_func(k + 1);
                selected[k] = 0;
```


### 2+A 버전
- N개 중 2) 중복 없이
- M개를 A) 순서 있게 나열하기(순열)
- [BOJ 15649](https://www.acmicpc.net/problem/15649) 
  - N과 M(1) (난이도:2) (실버3)
- [정리 및 풀이](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/BackJoon/%EC%99%84%EC%A0%84%20%ED%83%90%EC%83%89(BruteForce)/%EC%99%84%EC%A0%84%ED%83%90%EC%83%89/P15649(N%EA%B3%BC%20M(1)).md)
- 핵심 코드
```java
if(bool[cand]==true){ //핵심
                    continue;
                }
                selected[k] = cand;
                bool[cand] = true;
                rec_func(k+1);
                selected[k] = 0;
                bool[cand] = false;
            }
```


### 1+B 버전
- N개 중 1) 중복을 허용해서
- M개를 B) 고르기 (조합)
- [BOJ 15652](https://www.acmicpc.net/problem/15652) 
  - N과 M(4) (난이도:2) (실버3)
- [정리 및 풀이](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/BackJoon/%EC%99%84%EC%A0%84%20%ED%83%90%EC%83%89(BruteForce)/%EC%99%84%EC%A0%84%ED%83%90%EC%83%89/P15662(N%EA%B3%BC%20M(4)).md)
- 핵심 코드
```java
for(int cand =1; cand<=n; cand++){ //핵심
                if(selected[k-1]<=cand){
                    selected[k] = cand;
                    rec_func(k+1);
                    selected[k] =0;
                }
            }
```


### 2+B 버전
- N개 중 2) 중복 없이
- M개를 B) 고르기 (조합)
- [BOJ 15650](https://www.acmicpc.net/problem/15650) 
  - N과 M(2) (난이도:2) (실버3)
- [정리 및 풀이](https://github.com/ssu18/TIL/blob/main/Problem%20Solving/BackJoon/%EC%99%84%EC%A0%84%20%ED%83%90%EC%83%89(BruteForce)/%EC%99%84%EC%A0%84%ED%83%90%EC%83%89/P15650(N%EA%B3%BC%20M(2)).md)
- 핵심 코드
```java
for(int cand=selected[k-1]+1; cand<=n; cand++){
                selected[k] = cand;
                rec_func(k+1);
                selected[k] = 0;
            }
```

