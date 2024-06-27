class Solution {
    public int solution(int hp) {
        int a = hp/5;
        int b = hp%5/3;
        int c = hp%5%3/1;
        int answer = a+b+c;
        return answer;
    }
}