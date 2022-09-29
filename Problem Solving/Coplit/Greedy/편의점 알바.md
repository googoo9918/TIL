## 문제
![image](https://user-images.githubusercontent.com/102513932/192704322-ca1f1454-2366-4e8a-a17f-1617c21b4d73.png)

### 입출력 예시
```java
// 4000원을 받았을 때 500원짜리 동전 8개를 반환합니다.
int output1 = partTimeJob(4000);
System.out.println(output1); // --> 8

// 4972원을 받았을 때 500원짜리 동전 9개, 100원짜리 동전 4개, 50원짜리 동전 1개, 10원짜리 동전 2개, 1원짜리 동전 2개, 총 18개를 반환합니다.
int output2 = partTimeJob(4972);
System.out.println(output2); // --> 18
```

### 코드
```java
public class P2 {
    public static void main(String[] args) {
        // 4000원을 받았을 때 500원짜리 동전 8개를 반환합니다.
        int output1 = partTimeJob(4000);
        System.out.println(output1); // --> 8

        // 4972원을 받았을 때 500원짜리 동전 9개, 100원짜리 동전 4개, 50원짜리 동전 1개, 10원짜리 동전 2개, 1원짜리 동전 2개, 총 18개를 반환합니다.
        int output2 = partTimeJob(4972);
        System.out.println(output2); // --> 18
    }
    // 동전 오름차순 정렬 (1, 5, 10, 50, 100 ,500)
    // 동전 개수를 최소화 하여 거스름돈 -> 필요한 동전의 최솟값은?
    // 1. 동전 내림차순 정렬 배열 생성(단순 편의)
    // 2. 총 동전 개수를 세는 cnt 설정, 크기별 동전 개수를 세는 coin_cnt 생성
    // 2.5 반복문 -> 언제까지? -> 그냥 length 만큼 ㅇㅇ
    // 3. 배열의 인덱스로 나눈 값의 몫이 동전 개수로 + 되어야 하고
    // 4. k = k - 배열 인덱스 * coin_cnt(동전 개수)
    //
    public static int partTimeJob(int k) {
        int[] coin = {500,100,50,10,5,1};
        int cnt=0, coin_cnt =0;
        for(int i=0; i<coin.length; i++){
            coin_cnt = k / coin[i];
            cnt = cnt + coin_cnt;
            k = k - coin[i] * coin_cnt;
        }
        return cnt;
    }
}

```