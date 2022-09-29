## 문제
![image](https://user-images.githubusercontent.com/102513932/193031075-e0ebfed5-5d81-4fca-a1f0-7b21e32883dd.png)

### 입출력 예시
```java
String[] input = new String[]{
  "hello",
  "wolrd",
};
String output = readVertically(input);
System.out.println(output); // --> "hweolllrod"

input = new String[]{
  "hi",
  "wolrd",
};
output = readVertically(input);
System.out.println(output); // --> "hwiolrd"
```

### 코드
```java
public class P13 {
    public static void main(String[] args) {
        String[] input = new String[]{
                "hi",
                "wolrd",
        };
        String output = readVertically(input);
        System.out.println(output);
    }
    // max 값 구하기
    // char 2차원 배열로 만들기
    // 2차원 배열에 값 다 넣어주기
    // 출력하기.
    public static String readVertically(String[] arr) {
        // 처음에 그냥 for문으로 인자 조절하며 해결하려다 큰 코 다쳤음
        // for문 해결 원할시 arr[j].charAt(i)에서의 i와 i<arr.length[i]의 i가 충돌해서
        // 정사각형 행렬이 아닌 이상 문제가 발생할 수 밖에 없다
        // 따라서 char 2차원 배열로 바꿔버리고 한개씩 다 넣어줘야됨(toCharArray 사용불가 ㅠ)
        // 시간복잡도에 큰 영향이 없을 경우, 범위를 넓게 설정하고 조건을 달아버리는게 오히려 편함
        // reference 코드 참조해보기.. 압도당했다
        int max =0;
        StringBuilder sb = new StringBuilder();
        for(String x : arr){
            max = Math.max(max,x.length());
        }
        char[][] carr = new char[max][max];
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length(); j++){
                carr[i][j] = arr[i].charAt(j);
                // 만약 toCharArray로 받아주게 되면 행의 크기가 다시 줄어들어서 outOfIndex 또 발생하게됨
            }
        }
        for(int i=0; i< max; i++){
            for(int j=0; j<max; j++){
                if(carr[j][i]!='\0')
                    sb.append(carr[j][i]);
            }
        }
        return sb.toString();
    }
}
```

### 레퍼런스 코드
```java
public class P13_A {
    public static void main(String[] args) {
        String[] input = new String[]{
                "hi",
                "wolrd",
        };
        String output = readVertically(input);
        System.out.println(output);
    }
    // 빌드업이랑 요소 조절이 그냥 지려버림
    // 주석을 이쁘게 짜서 만들면 가능..?
    public static String readVertically(String[] arr) {
        //가장 긴 문자열의 길이를 검색합니다.
        int maxLength = 0;

        for(int i = 0; i < arr.length; i++) {
            if(maxLength < arr[i].length()) {
                maxLength = arr[i].length();
            }
        }
        //임시로 해당 길이만큼의 배열을 선언한 뒤,
        String[] temp = new String[maxLength];
        //기존의 배열을 순회합니다.
        for(int i = 0; i < arr.length; i++) {
            String str = arr[i];
            //해당 배열의 요소인 문자열을 순회합니다.
            for(int j = 0; j < str.length(); j++) {
                //임시 배열이 비어있다면
                if(temp[j] == null) {
                    //문자열의 j번째 char를 String으로 변환후 배열에 삽입 (세로위치 첫 문자열)
                    temp[j] = Character.toString(str.charAt(j));
                    //임시 배열이 비어있지 않다면, 임시 배열의 기존 문자열에 현재 j번째 char를 더해줍니다.
                } else {
                    temp[j] = temp[j] + str.charAt(j);
                }
            }
        }
        //배열을 순회하며 String으로 변환합니다.
        String result = "";
        for(int i = 0; i < temp.length; i++) {
            result = result + temp[i];
        }

        return result;
    }
}

```