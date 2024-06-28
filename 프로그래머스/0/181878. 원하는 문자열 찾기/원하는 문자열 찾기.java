class Solution {
    public int solution(String myString, String pat) {
        if(myString.length() < pat.length()) return 0;
        myString = myString.toLowerCase();
        pat = pat.toLowerCase();
        return myString.contains(pat) ? 1 : 0;
    }
}