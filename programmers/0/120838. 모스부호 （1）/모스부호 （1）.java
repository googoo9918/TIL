class Solution {
    public String solution(String letter) {
        String[] letterArr = letter.split(" ");
        String[] alpha = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        String ans = "";
        for(int i=0; i<letterArr.length; i++){
            for(int j=0; j<alpha.length; j++){
                if(letterArr[i].equals(alpha[j])){
                    ans += (char)(j+97);
                }
            }
        }
        return ans;
    }
}