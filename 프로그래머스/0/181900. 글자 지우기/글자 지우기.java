class Solution {
    public String solution(String my_string, int[] indices) {
        char[] carr = my_string.toCharArray();
        for(int i=0; i<indices.length; i++){
            carr[indices[i]] = ' ';
        }
        String answer = "";
        for(int i=0; i<carr.length; i++){
            answer += carr[i];
        }
        answer = answer.replaceAll(" ","");
        return answer;
    }
}