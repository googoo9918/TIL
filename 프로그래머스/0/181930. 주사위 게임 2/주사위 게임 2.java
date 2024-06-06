import java.io.*;
class Solution {
    public int solution(int a, int b, int c) {
        int answer = 0;
        if(same(a, b, c) == 0){
            answer = a + b + c;
        }else if(same(a,b,c)==1 || same(a,b,c)==2){
            answer = (a + b + c) * (a*a + b*b + c*c);
        }else{
            answer = (a + b + c) * (a*a + b*b + c*c) * (a*a*a + b*b*b + c*c*c); 
        }
        return answer;
    }
    public int same(int a, int b, int c){
        int cnt =0;
        if(a==b) cnt++;
        if(a==c) cnt++;
        if(b==c) cnt++;
        return cnt;
    }
}