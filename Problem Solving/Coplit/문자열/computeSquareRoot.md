## 문제
![image](https://user-images.githubusercontent.com/102513932/193713040-ee3992a6-9c81-40fd-99c2-5c781f51f241.png)

### 입출력 예시
```java
String output = computeSquareRoot(9);
System.out.println(output); // --> "3.00"

output = computeSquareRoot(6);
System.out.println(output); // --> "2.45"
```

### 코드
```java
public class P17 {
    public static void main(String[] args) {
        String output = computeSquareRoot(9);
        System.out.println(output); // --> "3.00"

        output = computeSquareRoot(6);
        System.out.println(output);
    }
    public static String computeSquareRoot(int num){
        String ans;
        double dnum =  Math.sqrt(num);
        ans = String.format("%.2f", dnum);
        return ans;
    }
}

```