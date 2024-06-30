class Solution {
    public int solution(int[] arr) {
        int[] newArr = new int[arr.length];
        boolean check = true;
        for(int i = 0; i<arr.length; i++){
            if(arr[i]>=50 && arr[i]%2==0){
                newArr[i] = arr[i] / 2;
            }
            else if(arr[i]<50 && arr[i]%2 !=0){
                newArr[i] = arr[i]*2 + 1;
            }
            else{
                newArr[i] = arr[i];
            }
            
            if(arr[i] != newArr[i]) check = false;
        }
        if(check) return 0;
        
        return 1 + solution(newArr);
    }
}