import java.util.*;

class Solution {
    // 선분이 겹칠 때 --> 중복으로 겹칠 수 있는지 확인해야 함
    // '선분'이니, 점이 중복된다고 길이가 늘어나지 않는 점 주의
    public int solution(int[][] lines) {
        int answer =0;
        int[] check = new int[201];
        for(int[] line : lines){
            int start = line[0]+100;
            int end = line[1]+100;
            
            for(int i=start; i<end; i++){
                check[i]++;
            }
        }
        
        for(int i=0; i<check.length; i++){
            if(check[i]>=2) answer++;
        }
        
        return answer;
    }
}