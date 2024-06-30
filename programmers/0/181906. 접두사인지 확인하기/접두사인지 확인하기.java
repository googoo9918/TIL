class Solution {
    public int solution(String my_string, String is_prefix) {
        String ans = "";
        for(int i=0; i<my_string.length(); i++){
            ans = ans + my_string.charAt(i);
            if(ans.equals(is_prefix)){
                return 1;
            }
        }
        return 0;
    }
}