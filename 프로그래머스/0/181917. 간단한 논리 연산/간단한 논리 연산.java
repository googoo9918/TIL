class Solution {
    public boolean solution(boolean x1, boolean x2, boolean x3, boolean x4) {
        return func2(func1(x1,x2), func1(x3,x4));
        //(x1||x2)&&(x3||x4);로 한 번에 처리할 수 있다.
    }
    public boolean func1(boolean t1, boolean t2){
        if(t1 == false && t2 == false){
            return false;
        }
        return true;
    }
    
    public boolean func2(boolean t1, boolean t2){
        if(t1 == true && t2 == true){
            return true;
        }
        return false;
    }
}