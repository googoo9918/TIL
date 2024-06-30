class Solution {
    public String solution(String code) {
        String answer = "";
        int mode =0;
        for(int i=0; i<code.length(); i++){
            // 따옴표 붙여줘야 하는 것 주의
            if(code.charAt(i) == '1'){
                //모드 변환
                mode = (mode==0) ? 1 : 0;
            }
            // code[idx]가 1이 아닐 때
            else{
                if(mode == 0){
                    if(i%2 == 0){
                        answer += code.charAt(i);
                    }
                }
                //mode가 1일 때
                else{
                    if(i%2 != 0){
                        answer += code.charAt(i);
                    }
                }
            }
        }
        if(answer.equals("")) return "EMPTY";
        else{
            return answer;
        }
    }
}