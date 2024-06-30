import java.util.*;
class Solution {
    public int solution(int n) {
        return n == Math.pow((int)Math.sqrt(n),2) ? 1 : 2;
    }
}