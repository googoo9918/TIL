import java.util.*;
import java.io.*;
class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(ineq).append(eq);
        if(sb.toString().equals(">=")){
            answer = n >= m ? 1 : 0;
        }
        else if(sb.toString().equals("<=")){
            answer = n <= m ? 1 : 0;
        }
        else if(sb.toString().equals(">!")){
            answer = n > m ? 1 : 0;
        }
        else if(sb.toString().equals("<!")){
            answer = n < m ? 1 : 0;
        }
        return answer;
    }
}