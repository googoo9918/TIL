class Solution {
    public int solution(int[] num_list) {
        int[] cnt = new int[num_list.length];
        while(true){
        boolean check = true;
        for(int i=0; i<num_list.length; i++){
            if(num_list[i]%2 ==0){
                num_list[i] /= 2;
                cnt[i]++;
            }
            else if(num_list[i] %2 !=0 && num_list[i] != 1){
                num_list[i] = (num_list[i] - 1) / 2;
                cnt[i]++;
            }
        }
        for(int i : num_list){
            if(i != 1){
                check = false;
                break;
            }
        }
            if(check) break;
        }
        int ans = 0;
        for(int c: cnt){
            ans += c;
        }
        return ans;
    }
}