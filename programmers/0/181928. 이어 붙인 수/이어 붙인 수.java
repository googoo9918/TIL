import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] num_list) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i : num_list){
            if(i%2 ==0) sb.append(i);
            else sb2.append(i);
        }
        int answer = Integer.parseInt(sb.toString()) + Integer.parseInt(sb2.toString());
        return answer;
    }
}