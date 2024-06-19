import java.util.*;
class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<intStrs.length; i++){
            String stmp = "";
            for(int j=0; j<l; j++){
                stmp = stmp + intStrs[i].charAt(s+j);
            }
            int itmp = Integer.parseInt(stmp);
            if(itmp > k){
                list.add(itmp);
            }
        }
        int[] answer = new int[list.size()];
        int idx = 0;
        for(int i : list){
            answer[idx++] = i; 
        }
        return answer;
    }
}