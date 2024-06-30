class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        for(int i=0; i<quiz.length; i++){
            String[] sarr = quiz[i].split(" ");
            int a = Integer.parseInt(sarr[0]);
            int b = Integer.parseInt(sarr[2]);
            int c = Integer.parseInt(sarr[4]);
            if(sarr[1].equals("-")){
                if(a-b == c){
                    answer[i] = "O";
                }else{
                    answer[i] = "X";
                }
            }else{
                if(a+b == c){
                    answer[i] = "O";
                }
                else{
                    answer[i] = "X";
                }
            }
        }
        return answer;
    }
}