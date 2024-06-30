import java.io.*;
class Solution {
    public String solution(int[] numLog) {
        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        for(int i=1; i<numLog.length; i++){
            tmp = numLog[i] - numLog[i-1];
            if(tmp == 1) sb.append('w');
            else if(tmp == -1) sb.append('s');
            else if(tmp == 10) sb.append('d');
            else sb.append('a');
        }
        return sb.toString();
    }
}