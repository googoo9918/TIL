import java.util.*;
class Solution {
    public String[] solution(String[] str_list) {
        List<String> list = new ArrayList<>();
        int idx = 0;
        for(int i=0; i<str_list.length; i++){
            if(str_list[i].equals("l")){
                for(int j=0; j<i; j++){
                    list.add(str_list[j]);
                }
                break;
            }
            else if(str_list[i].equals("r")){
                for(int j=i+1; j<str_list.length; j++){
                    list.add(str_list[j]);
                }
                break;
            }
        }
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}