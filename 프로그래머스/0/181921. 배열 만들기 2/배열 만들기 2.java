import java.util.*;
class Solution {
    public int[] solution(int l, int r) {
        List<Integer> list = new ArrayList<>();
        int[] answer = {};
        for(int i=l; i<=r; i++){
            String str = String.valueOf(i);
            str = str.replace("5","");
            str = str.replace("0","");
            if(str.isEmpty()){
                list.add(i);
            }
            // 정규표현식과 matches로 대체할 수 있음
            // if (str.matches("[^50]*")) {
            //     list.add(i);
            // }
        }
        
        if(list.isEmpty()){
            return answer = new int[]{-1};
        }
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
        //return list.stream().mapToInt(Integer::intValue).toArray();
        //stream으로 변환하는게 가장 좋은 방법일 듯
    }
}