class Solution {
    public int solution(int[] num_list) {
        int multiple = 1;
        int pow = 0;
        for(int i : num_list){
            multiple *= i;
            pow += i;
        }
        return multiple<pow*pow ? 1 : 0;
    }
}