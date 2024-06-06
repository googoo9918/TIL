class Solution {
    public int[] solution(int[] num_list) {
        int lastIndex = num_list.length - 1;
        int[] answer = new int[num_list.length+1];
        int last = num_list[lastIndex] > num_list[lastIndex-1] ?
                   num_list[lastIndex] - num_list[lastIndex-1] :
                   num_list[lastIndex] * 2;
        for(int i=0; i<num_list.length; i++){
            answer[i] = num_list[i];
        }
        answer[lastIndex+1] = last;
        
        return answer;
    }
}