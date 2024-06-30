class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String front = my_string.substring(0,s);
        String back = my_string.substring(s+overwrite_string.length());
        return front + overwrite_string + back;
    }
}