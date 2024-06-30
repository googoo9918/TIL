class Solution {
    public int[] solution(int[] em) {
        int[] answer = new int[em.length];
        for(int i=0; i<em.length; i++){
            int cnt = 1;
            for(int j=0; j<em.length; j++){
                if(em[i] < em[j]){
                    cnt++;
                }
            }
            answer[i] = cnt;
        }
        return answer;
    }
}