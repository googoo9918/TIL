class Solution {
    public int solution(int[] common) {
        int d1 =0, d2=0, r1=0, r2=0;
        d1 = common[1] - common[0];
        d2 = common[2] - common[1];
        if(common[0]!=0 && common[1] != 0){
            r1 = common[1]/common[0];
            r2 = common[2]/common[1];
        }
        if(d1 == d2){
            return common[common.length-1] + d1;
        }else if(r1 == r2){
            return common[common.length-1] * r1;
        }
        return 0;
    }
}