import java.util.*;
class Solution {
    public int solution(int a, int b) {
        if(isOdd(a) && isOdd(b)){
            return a*a + b*b;
        }
        else if(isOdd(a) && !isOdd(b) || !isOdd(a) && isOdd(b)){
            return 2 * (a + b);
        }
        else{
        return Math.abs(a-b);
        }
    }
    public boolean isOdd(int x){
        if(x%2==0){
            return false;   
        }
        return true;
    }
}