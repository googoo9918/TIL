import java.util.*;
class Solution {
    public int solution(int n) {
        int answer = 0;
        int tmp = n%2==0 ? 0 : 1;
        for(int i=tmp; i<=n; i+=2)
    {
            if(tmp == 1)
                answer += i;
            else
                answer += Math.pow(i,2);
    }
        return answer;
    }
}