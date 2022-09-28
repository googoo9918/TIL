## 문제
![image](https://user-images.githubusercontent.com/102513932/192702888-66b99806-e4b9-4c27-bf3f-a872aac7d040.png)
![image](https://user-images.githubusercontent.com/102513932/192702972-d8bf5182-d946-4f3d-a75c-fef5a2096633.png)

### 입출력 예시
```java
int output = movingStuff(new int[]{70, 50, 80, 50}, 100);
System.out.println(output); // 3

int output2 = movingStuff(new int[]{60, 80, 120, 90, 130}, 140);
System.out.println(output); // 4
```

### 코드
```java
import java.util.ArrayList;
import java.util.Arrays;

public class P1{
    public static void main(String[] args) {
        int output = movingStuff(new int[]{42, 25, 60, 73, 103, 167}, 187);
        System.out.println(output); // 4

        output = movingStuff(new int[]{60, 73, 80, 87, 103, 109, 119, 123, 128, 129, 136, 146, 153, 168, 182}, 200);
        System.out.println(output); // 11

        output = movingStuff(new int[]{70, 50, 80, 50}, 100);
        System.out.println(output); // 3

    }
    public static int movingStuff(int[] stuff, int limit) {
        // 최대 2개의 짐이 들어가는 무게 제한이 있는 박스.
        // 박스를 최대한 적게 사용하여 모든 짐을 옮김
        int ans =0, sum=limit, tmp =0;
        boolean[] use_i = new boolean[stuff.length];
        boolean[] use_j = new boolean[stuff.length];

        // 1) 배열 정렬하기 (오름차순)
        Arrays.sort(stuff);
        // 2. 더했을 때 limit의 값과 가장 가까운 값끼리 묶어서 박스로 옮기기
        // 2.1 2중 for문 구현 -> sum값 정하고..
        // 다른 i에 같은 j가 설정되면 어떻게 할꺼? -> j도 boolean 배열을 만들어 버리자
        // 2.2. 사용한 값은 boolean[] check = true; 체크 해줄 것
        for(int i=0; i<stuff.length-1; i++){
            for(int j=i+1; j<stuff.length; j++){
                if( 0<= limit-(stuff[i]+stuff[j]) && limit-(stuff[i]+stuff[j]) <= sum && use_j[j] == false) {
                    sum = limit - (stuff[i] + stuff[j]); //limit에 가장 가까운 합을 찾아주자
                    tmp = j;
                }
            }
            // 지금 문제는 sum이 limit을 넘으면 안넣어 줘야되는데 계속해서 넣어주고 있음.
            if(sum<limit) {
                ans++;
                use_i[i] = true; //사용한 박스 true 처리
                use_j[tmp] = true;
            }
            sum = limit; //sum 초기화
        }
       
        // 4. 사용하지 않은 값이고, 더했을 때 200 미만인 값이 없다면 그냥 박스에 담기(ans++;)
        for(int k=0; k<use_i.length; k++){
            if(use_i[k]==false && use_j[k]==false) //2개로 묶이지 않은 박스 1개씩 담아주기
                ans++;
        }
        return ans;
    }
}
```

### 레퍼런스 코드
```java
import java.util.*;

public class P1_A {
    public static void main(String[] args) {
        int output = movingStuff(new int[]{42, 25, 60, 73, 103, 167}, 187);
        System.out.println(output); // 4

        output = movingStuff(new int[]{60, 73, 80, 87, 103, 109, 119, 123, 128, 129, 136, 146, 153, 168, 182}, 200);
        System.out.println(output); // 11

        output = movingStuff(new int[]{70, 50, 80, 50}, 100);
        System.out.println(output); // 3
    }
    public static int movingStuff(int[] stuff, int limit) {
        int twoStuff = 0;
        // 짐을 무게순으로 오름차순 정렬
        Arrays.sort(stuff);
        // 가장 가벼운 짐의 인덱스
        int leftIdx = 0;
        // 가장 무거운 짐의 인덱스
        int rightIdx = stuff.length - 1;
        while(leftIdx < rightIdx) {
            // 가장 가벼운 짐과 무거운 짐의 합이 limit 보다 작거나 같으면 2개를 한번에 나를 수 있다
            if(stuff[leftIdx] + stuff[rightIdx] <= limit) {
                // 다음 짐을 확인하기 위해 가장 가벼운 짐과 무거운 짐을 가리키는 인덱스를 옮겨주고
                // 한번에 2개 옮길 수 있는 개수를 +1 해준다
                leftIdx++;
                rightIdx--;
                twoStuff++;
            } else {
                // 위 조건에 맞지 않는 경우는 한번에 한 개만 나를 수 있는 경우이기 때문에
                // 가장 무거운 짐의 인덱스만 옮겨준다
                rightIdx--;
            }
        }
        // 전체 짐의 개수에서 한번에 2개를 나를 수 있는 경우를 빼 주면 총 필요한 박스의 개수를 구할 수 있다
        return stuff.length - twoStuff;
    }
}
```