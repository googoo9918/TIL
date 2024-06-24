class Solution {
    public int[] solution(int[] arr, int[] query) {
        for(int i=0; i<query.length; i++){
            if(i % 2 == 0){
                int[] narr = new int[query[i]+1];
                for(int j=0; j<narr.length; j++){
                    narr[j] = arr[j];
                }
                arr = narr;
            }
            else{
                int[] narr = new int[arr.length - query[i]];
                for(int j=0; j<narr.length; j++){
                    narr[j] = arr[query[i]++];
                }
                arr = narr;
            }
        }
        return arr;
    }
}