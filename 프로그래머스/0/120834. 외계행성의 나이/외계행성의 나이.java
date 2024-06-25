class Solution {
    public String solution(int age) {
        Character[] cage = new Character[10];
        int tmp = 97;
        for(int i=0; i<cage.length; i++){
            cage[i] = (char)tmp++;
        }
        String sage = String.valueOf(age);
        
        String answer = "";
        for(int i=0; i<sage.length(); i++){
            // sage.charAt(i)는 숫자의 형태를 하고 있지만, 실제로는 char값이 출력됨
            // 이를 index로 사용하게 되면, '2'는 50의 ascii code로 변환됨
            // 실제 숫자를 사용하고 싶으면, 그에 맞게 변환하는 작업이 필요
            answer += cage[sage.charAt(i) - '0'];
        }
        return answer;
    }
}