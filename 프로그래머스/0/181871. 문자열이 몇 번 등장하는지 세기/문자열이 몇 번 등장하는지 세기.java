class Solution {
    public int solution(String myString, String pat) {
        int answer = 0;
        for(int i=0; i<myString.length()-pat.length()+1; i++){
            int cnt = 0;
            for(int j=0; j<pat.length(); j++){
                if(myString.charAt(i+j) == pat.charAt(j)){
                    cnt++;
                }
                if(cnt == pat.length()) answer++;
            }
        }
        return answer;
    }
}