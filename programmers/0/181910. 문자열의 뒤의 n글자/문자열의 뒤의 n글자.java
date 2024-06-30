class Solution {
    public String solution(String my_string, int n) {
        int idx = my_string.length() - n;
        String answer = my_string.substring(idx);
        return answer;
    }
}