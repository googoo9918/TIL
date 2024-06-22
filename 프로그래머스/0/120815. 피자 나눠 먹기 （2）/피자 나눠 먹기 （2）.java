class Solution {
    public int solution(int n) {
        for(int i=6; i<=n*6; i+=6){
            if(i%n==0) return i/6;
        }
        return 0;
    }
}