import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] carr = str.toCharArray();
        for(int i=0; i<str.length(); i++){
            if(Character.isLowerCase(carr[i])){
                carr[i] = Character.toUpperCase(carr[i]);
            }
            else{
                carr[i] = Character.toLowerCase(carr[i]);
            }
        }
        System.out.println(String.valueOf(carr));
    }
}