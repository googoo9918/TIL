import java.util.*;
class Solution {
    public List<String> solution(String[] strArr) {
        List<String> list = new ArrayList<>();
        for(int i=0; i<strArr.length; i++){
            if(strArr[i].contains("ad")){
                strArr[i] = "";
            }
        }
        for(String s : strArr){
            if(!s.equals("")){
                list.add(s);
            }
        }
        return list;
    }
}