import java.util.*;
class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int comdenom1 = denom1 * denom2;
        int newnumber1 = numer1 * denom2;
        int newnumber2 = numer2 * denom1;
        int sumNumber = newnumber1 + newnumber2;
        
        int tmp = 0;
        for(int i=1; i<=Math.min(comdenom1, sumNumber); i++){
            if(comdenom1 % i==0 && sumNumber % i ==0) tmp = i;
        }
        int[] answer = {sumNumber/tmp, comdenom1/tmp};
        return answer;
    }
}