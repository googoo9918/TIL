### 목차
- [목차](#목차)
- [순열(Permutation)과 조합(Combination)](#순열permutation과-조합combination)
  - [순열](#순열)
  - [조합](#조합)
### 순열(Permutation)과 조합(Combination)

#### 순열
- **순서가 있게** N개 중 R개를 뽑아 나열하기
- 반복문 사용시, 뽑아야 되는 개수가 변수로 입력 되는 것을 대응할 수 없음
- 재귀문 사용
  - 시간복잡도 : ```O(N!)```
```java
import java.util.Arrays;

public class test {
  static int N=4; // nPr 에서 n
  static int R=2; // nPr 에서 r
  static int[] numbers = new int[R]; //순열을 담을 배열
  static boolean[] isSelected = new boolean[N+1];
  public static void main(String[] args) {
      permutation(0);
  }
  static void permutation(int count){
    //count : 현재 구한 순열 요소들의 개수 (처음엔 0)
    if(count == R){//count==R 이면, 재귀 종료
        System.out.println(Arrays.toString(numbers));
      }
    else{
        for(int i=1; i<=N; i++){
            // 이미 사용된 값이라면 for문 건너 뛰기
            if(isSelected[i]) continue;
            // 아니라면, 수행문 실행
            // 순열 배열에 저장
            numbers[count] = i;
            // 사용한 값 이므로 true로 변경
            isSelected[i] = true;
            // 재귀 호출 진행
            permutation(count+1);
            // 다음 값 확인을 위해 다시 false로 변경
            isSelected[i] = false;
        }
    }
  }
}
```

#### 조합
- **순서 없이** N개중 R개 뽑기
- 재귀문 사용
  - 시간복잡도 ```O(2^N)```
```java
import java.util.Arrays;

public class test2 {
    static int N =5; //nPr 에서 n
    static int R =3; //nPr 에서 r
    static int[] numbers = new int[R];
    // 조합을 담을 배열 생성
    public static void main(String[] args) {
        combination(0,1); //인자 고정
    }
    static void combination(int count, int start) {
    //count : 현재 구한 요소들의 개수
    //start : 숫자 몇부터 뽑을것인지? (보통 1)
        
        //목표만큼 구했으면 출력
        if (count == R) {
            System.out.println(Arrays.toString(numbers));
        }

        // start ~ n 까지 for문
        // i는 뽑을 숫자를 의미함
        for (int i = start; i <= N; i++) {
            //아직 덜 구했으면
            if(count<R) { 
                //배열[구한 요소 개수]에 숫자 넣어주기
                numbers[count] = i;
                
                //재귀로 인자 넣어주기 
                combination(count + 1, i + 1);
            }
            else{//다 구했음에도 for문 도는 것 방지
                break;
            }
        }
    }
}
```