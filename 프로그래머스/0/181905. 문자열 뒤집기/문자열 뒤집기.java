import java.util.*;
class Solution {
    public String solution(String my_string, int s, int e) {
        String answer = my_string.substring(0,s) + new StringBuilder(my_string.substring(s, e+1)).reverse() + my_string.substring(e+1);
        return answer;
    }
}