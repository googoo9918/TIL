import java.util.*;
import java.io.*;
class Solution {
    public int solution(int a, int b) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int answer = 0;
        sb.append(a).append(b);
        sb2.append(b).append(a);
        answer = Math.max(Integer.parseInt(sb.toString()), Integer.parseInt(sb2.toString()));
        return answer;
    }
    // Integer.parseInt(""+a+b), 혹은 String.valueOf(a) 등을 사용하는 것이 더 좋을 수 있다.
}