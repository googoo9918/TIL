class Solution {
    public int solution(String my_string, String is_suffix) {
        String[] suffix = new String[my_string.length()];
        for(int i=0; i < my_string.length(); i++){
            suffix[i] = my_string.substring(i);
        }
        
        for(String s : suffix){
            if(s.equals(is_suffix))
                return 1;
        }
        return 0;
    }
}