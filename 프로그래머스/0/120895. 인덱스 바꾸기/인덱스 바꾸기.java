import java.util.*;
class Solution {
    public String solution(String my_string, int num1, int num2) {
        StringBuilder sb = new StringBuilder();
        char[] carr = my_string.toCharArray();
        char ctmp = carr[num1];
        carr[num1] = carr[num2];
        carr[num2] = ctmp;
        for(char c: carr){
            sb.append(c);
        }
        
        return sb.toString();
    }
}