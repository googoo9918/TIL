import java.util.*;
class Solution {
    public int solution(int[] array) {
        Arrays.sort(array);
        int idx = array.length / 2;
        return array[idx];
    }
}