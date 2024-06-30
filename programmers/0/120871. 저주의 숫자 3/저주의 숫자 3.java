class Solution {
    public int solution(int n) {
        int tmp=1;
        for(int i=1; i<=n; i++){
            while(tmp%3 ==0 || String.valueOf(tmp).contains("3")){
              tmp++;  
            }
            System.out.println("i: " + i + " tmp: " + tmp);
            if(i!=n) tmp++;
        }
        return tmp;
    }
}