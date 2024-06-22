import java.util.*;
class Solution {
    public List<Integer> solution(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=n; i+=2){
            list.add(i);
        }
        return list;
    }
}