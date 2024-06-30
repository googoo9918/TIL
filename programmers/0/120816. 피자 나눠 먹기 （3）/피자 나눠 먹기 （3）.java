class Solution {
    public int solution(int slice, int n) {
        for(int i=1; i<=50; i++){
            if(slice*i / n >= 1){
                return i;
            }
        }
        return 0;
    }
}