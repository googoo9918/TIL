import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int k2=0;
        for(int i=0; i<commands.length; i++){
            int k1 =0;
            int[] tmp = new int[commands[i][1] - commands[i][0] +1];
            for(int j= commands[i][0]; j<= commands[i][1]; j++){
                tmp[k1++] = array[j-1];
            }
            Arrays.sort(tmp);
            answer[k2++] = tmp[commands[i][2]-1];
        }
        return answer;
    }
}