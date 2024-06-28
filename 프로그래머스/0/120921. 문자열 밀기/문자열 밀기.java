class Solution {
    public int solution(String A, String B) {
        if(A.equals(B)) return 0;
        char[] arrA = A.toCharArray();
        for(int i=0; i<arrA.length; i++){
            char[] newArr = new char[arrA.length];
            newArr[0] = arrA[arrA.length-1];
            for(int j=1; j<arrA.length; j++){
                newArr[j] = arrA[j-1];
            }
            if(String.valueOf(newArr).equals(B)){
                return i+1;
            }
            arrA = newArr;
        }
        return -1;
    }
}