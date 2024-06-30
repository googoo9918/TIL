class Solution {
    public String solution(String s) {
        String[] sarr = s.split(" ");
        int[] iarr = new int[sarr.length];
        int index = 0;
        for(String str : sarr){
            iarr[index++] = Integer.parseInt(str);
        }
        int min = iarr[0], max = iarr[0];
        for(int i=1; i< sarr.length; i++){
            min = Math.min(min, iarr[i]);
            max = Math.max(max, iarr[i]);
        }
        String answer = String.valueOf(min) +" " + String.valueOf(max);
        return answer;
    }
}