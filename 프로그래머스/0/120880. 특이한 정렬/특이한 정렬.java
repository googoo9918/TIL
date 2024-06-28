import java.util.*;
class Solution {
    public List<Integer> solution(int[] numlist, int n) {
        int[] len = new int[numlist.length];
        for(int i=0; i<numlist.length; i++){
            len[i] = Math.abs(numlist[i] - n);
        }
        List<Integer> list = new ArrayList<>();
        boolean[] check = new boolean[numlist.length];
        for(int i=0; i<numlist.length; i++){
            int idx = -1;
            int min = Integer.MAX_VALUE;
            for(int j=0; j<numlist.length; j++){
                if(check[j]==false && len[j]<min){
                    idx = j;
                    min = len[idx];
                }
                else if(check[j] == false && len[j] == min){
                    idx = numlist[idx]>numlist[j] ? idx : j;
                }
            }
            check[idx] = true;
            list.add(numlist[idx]);
        }
        return list;
    }
}