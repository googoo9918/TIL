import java.util.*;
class Solution {
    public int solution(int[] array) {
        int[] iarr = new int[1000];
        for(int i=0; i<array.length; i++){
            iarr[array[i]]++;
        }
        int maxCnt = 0;
        int idx = 0;
        for(int i=0; i<iarr.length; i++){
            if(iarr[i]>maxCnt){
                maxCnt = iarr[i];
                idx = i;
            }
        }
        
        for(int i=0; i<iarr.length; i++){
            if(iarr[i] == maxCnt && idx != i){
                return -1;
            }
        }
        return idx;
    }
}