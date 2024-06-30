class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for(int query[] : queries){
            swap(arr, query[0], query[1]);
        }
        return arr;
    }
    public int[] swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
        
        return arr;
    }
}