import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int sidx = sidx(arr);
        int eidx = eidx(arr);
        if(sidx == -1 && eidx == -1) return new int[]{-1};
        for(int i=sidx; i<=eidx; i++){
            list.add(arr[i]);
        }
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
    public int sidx(int[] arr){
        for(int i=0; i<arr.length; i++){
            if(arr[i] ==2){
                return i;
            }
        }
        return -1;
    }
    
    public int eidx(int[] arr){
        for(int i=arr.length-1; i>=0; i--){
            if(arr[i] == 2){
                return i;
            }
        }
        return -1;
    }
}