## 문제
![image](https://user-images.githubusercontent.com/102513932/192705208-9e496b46-ae59-45e7-9fdb-957383ef9d54.png)
![image](https://user-images.githubusercontent.com/102513932/192705264-02c55b5c-d4d3-4d64-8c03-3c5eb603d8c6.png)

### 입출력 예시
```java
int output = ocean(50, new int[]{10, 20, 50});
System.out.println(output); // 4

output = ocean(100, new int[]{10, 20, 50});
System.out.println(output); // 10

output = ocean(30, new int[]{5, 6, 7});
System.out.println(output); // 4
```

### 코드
```java
public class P4{
    public static void main(String[] args) {
        long output = ocean(50, new int[]{10, 20, 50});
        System.out.println(output); // 4

        output = ocean(100, new int[]{10, 20, 50});
        System.out.println(output); // 10

        output = ocean(30, new int[]{5, 6, 7});
        System.out.println(output); // 4
    }
    // target : 목표 금액 / coin: 금액 종류
    // DP.. 경우의 수로 따져야 함 쉬운 케이스 부터 생각해보자 !
    // 1. 2차원 배열을 만든다 -> int[][] noc = new int[type.length+1][target+1]
    // 점화식 : d[i][j] = d[i][j-coin[i]] + d[i-1][j];
    // ex) target:10 / coin : 1,2,5
    // d[3][5] = d[3][0] + d[2][5];
    // 3가지(1,2,5)를 이용해 5를 만드는 경우의 수
    // = (1)3가지를 사용해 0을 만드는 경우의 수 + (2)2가지를 이용해 5를 만드는 경우의 수
    // (1)은 5하나 들어갔을 때!!
    // (2)는 5가 안들어가고 나머지 덧셈의 개수(2+2+1, 2+1+1+1,1+1+1+1+1)
    // 사실 1차원 배열로 푸는게 보다 효과적임
    public static long ocean(int target, int[] coin) {
        long[][] noc = new long[coin.length+1][target+1];
        noc[0][0] =1;
        for(int i=0; i<= coin.length-1; i++){
            for(int j=0; j<=target; j++){
                if(j<coin[i]){
                    noc[i+1][j] = noc[i][j];
                    //지금 문제는 i를 1부터 시작하게 설정 했는데 ,coin의 값이 2번째 index부터 들어가고 있음
                    // i를 0부터 시작하게 만들고 -> noc의 값을 +1 늘린다?
                }
                else{ // coin[i] <= j
                    noc[i+1][j] = noc[i][j] + noc[i+1][j-coin[i]];
                }
            }
        }
        return noc[coin.length][target];
    }
}
```

### 레퍼런스 코드
```java
public class P4_A {
    public static void main(String[] args) {
        long output = ocean(50, new int[]{10, 20, 50});
        System.out.println(output); // 4

        output = ocean(100, new int[]{10, 20, 50});
        System.out.println(output); // 10

        output = ocean(30, new int[]{5, 6, 7});
        System.out.println(output); // 4
    }
    public static long ocean(int target, int[] type) {
        // bag 이라는 배열에 금액을 만들 수 있는 경우의 수를 기록
        // 각 인덱스 no# = 만드려는 금액 을 의미
        // ex) target = 5, type = [1, 2, 5] 면
        // bag[3] = 2  => 3을 만드는 경우의 수 = 1만 사용 & 1,2 함께 사용
        // bag[4] = 2  => 4를 만드는 경우의 수 = 1만 사용 & 1,2 함께 사용
        // bag[5] = 4  => 5를 만드는 경우의 수 = 1*5 , 1*3 + 2, 1 + 2*2, 5*1
        // 0 을 만들 수 있는 경우는 아무것도 선택하지 않으면 되기 때문에 bag[0] = 1 로 초기값 설정
//        ArrayList<Integer> bag = new ArrayList<>();
//        bag.add(1);
        long[] bag = new long[target + 1]; //인덱스가 1부터 시작

        // 인덱스 no# = 만드려는 금액 이기 때문에
        // bag 을 target 금액만큼의 길이를 가진 배열을 만들어 주고,
        // 경우의 수를 저장하기 위해 초기값은 모두 0으로 만들어 준다
        bag[0] = 1;
        // 돈의 종류가 담겨있는 배열을 순차적으로 탐색
        for(int i = 0; i < type.length; i++) {
            // target 금액까지 순차적으로 1씩 증가하면서
            for(int j = 1; j <= target; j++)
                // bag의 인덱스가 type[i] 보다 큰 구간만
                // (작은 구간은 type[i]로 만들 수 없는 금액이기 때문에 탐색할 필요가 없다)
                if(type[i] <= j)
                    // 기존 경우의 수에 type[i]를 뺀 금액을 만들 수 있는 경우의 수를 더해준다
                    bag[j] += bag[j-type[i]];
        }
        // bag 의 target 인덱스에 target 금액을 훔칠 수 있는 경우의 수가 쌓이므로
        // 해당 값을 리턴해 준다
        return bag[target];
    }
}
```