class Solution {
    public String solution(String polynomial) {
        int tmp1 =0, tmp2=0;
        String[] sarr = polynomial.split(" ");
        for(int i=0; i<sarr.length; i+=2){
            if(sarr[i].contains("x")){
                if(Character.isDigit(sarr[i].charAt(0))){
                    tmp1 += Integer.parseInt(sarr[i].replace("x",""));
                }else{
                    tmp1++;
                }
            }else{
                tmp2 += Integer.parseInt(sarr[i]);
            }
        }
        boolean check1 = String.valueOf(tmp1).equals("0");
        boolean check2 = String.valueOf(tmp2).equals("0");
        String stmp1 = check1 ? "" : String.valueOf(tmp1);
        String stmp2 = check2 ? "" : String.valueOf(tmp2);
        String answer = "";
        if(check1 && !check2){
            answer = stmp2;
        }else if(!check1 && check2){
            if(stmp1.equals("1")) answer = "x";
            else{
                answer = stmp1 + "x";
            }
        }else if(check1 && check2){
            answer = "";
        }else{
            if(stmp1.equals("1")) answer = "x + " + stmp2;
            else{
                answer = stmp1 + "x + " + stmp2;
            }
        }
        return answer;
    }
}