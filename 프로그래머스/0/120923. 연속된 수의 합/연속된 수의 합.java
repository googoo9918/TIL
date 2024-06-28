import java.util.*;
class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        int weight = 0;
        while(true){
            int sum = 0;
            for(int i = weight; i>weight-num; i--){
                sum += i;
            }
            
            if(sum == total){
                int index = 0;
                for(int i= weight-num+1; i<=weight; i++){
                    answer[index++] = i;
                }
                break;
            }
            weight++;
        }
        return answer;
    }
}