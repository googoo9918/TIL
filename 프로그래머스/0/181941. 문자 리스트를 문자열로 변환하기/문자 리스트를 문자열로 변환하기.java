import java.util.*;
import java.io.*;
class Solution {
    public String solution(String[] arr) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length; i++){
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}