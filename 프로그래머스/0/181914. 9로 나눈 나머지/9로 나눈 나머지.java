class Solution {
    public int solution(String number) {
        int sum =0;
        for(char c: number.toCharArray()){
            sum+= Integer.parseInt(String.valueOf(c));
        }
        return sum%9;
    }
}