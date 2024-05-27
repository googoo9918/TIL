class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        // 사실 replace가 contains를 포함하고 있기 때문에, 중복 처리가 들어가고 있음!
        // contatins를 확인하지 않고, 그냥 replace를 순차적으로 진행해도 괜찮다.
        for(String s : babbling){
            boolean check = true;
            if(s.contains("aya")){
                s = cut(s, "aya");
            }
            if(s.contains("ye")){
                s = cut(s, "ye");
            }
            if(s.contains("woo")){
                s = cut(s, "woo");
            }
            if(s.contains("ma")){
                s = cut(s, "ma");
            }            
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) != '1') check = false;
            }
            
            if(check) answer++;
        }
        return answer;
    }
    
    public String cut(String s1, String s2){
        if(s1.contains(s2)){
            return s1.replace(s2,"1");
        }
        return s1;
    }
}