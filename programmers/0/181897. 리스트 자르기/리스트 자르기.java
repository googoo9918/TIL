import java.util.*;
class Solution {
    public int[] solution(int n, int[] slicer, int[] num_list) {
        if(n==1){
            return slicing(0,slicer[1], 1, num_list);
        }else if(n==2){
            return slicing(slicer[0], num_list.length-1, 1, num_list);
        }else if(n==3){
            return slicing(slicer[0], slicer[1], 1, num_list);
        }else{
            return slicing(slicer[0], slicer[1], slicer[2], num_list);
        }
    }
    public int[] slicing(int sidx, int eidx, int interval, int[] num_list){
        List<Integer> list = new ArrayList<>();
        for(int i=sidx; i<=eidx; i+=interval){
            list.add(num_list[i]);
        }
        int[] iarr = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            iarr[i] = list.get(i);
        }
        
        return iarr;
    }
}