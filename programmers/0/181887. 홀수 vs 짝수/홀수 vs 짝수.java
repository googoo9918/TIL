import java.util.*;
class Solution {
    public int solution(int[] num_list) {
        int osum =0, esum=0;
        for(int i=0; i<num_list.length; i++){
            if(i%2==0){
                osum += num_list[i];
            }
            else{
                esum += num_list[i];
            }
        }
        return Math.max(osum, esum);
    }
}