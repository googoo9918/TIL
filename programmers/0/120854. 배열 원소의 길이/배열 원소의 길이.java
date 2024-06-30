class Solution {
    public int[] solution(String[] strlist) {
        int[] answer = new int[strlist.length];
        int idx = 0;
        for(String s : strlist){
            answer[idx++] = s.length();
        }
        return answer;
    }
}