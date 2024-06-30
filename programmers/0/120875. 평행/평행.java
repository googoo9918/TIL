class Solution {
    public int solution(int[][] dots) {
        // 의미 없는 완전탐색은 독이 될 수 있음
        // 그냥 다 돌려보려고 하지 말고, 논리부터 제대로 세울 것
        int answer = 0;
        double tan1=0, tan2=0;
        tan1 = calculateTan(dots[0], dots[1]);
        tan2 = calculateTan(dots[2], dots[3]);
        if(tan1 == tan2) return 1;
        
        tan1 = calculateTan(dots[0], dots[2]);
        tan2 = calculateTan(dots[1], dots[3]);
        if(tan1 == tan2) return 1;
        
        tan1 = calculateTan(dots[0], dots[3]);
        tan2 = calculateTan(dots[1], dots[2]);
        if(tan1 == tan2) return 1;
        
        return answer;
    }
    
    public double calculateTan(int[] dot1, int dot2[]){
        return (double)(dot2[1]-dot1[1]) / (double)(dot2[0] - dot1[0]);
    }
}