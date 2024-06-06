import java.util.*;
class Solution {
    public Stack<Integer> solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i< arr.length; i++){
            if(stack.isEmpty()) stack.push(arr[i]);
            else if(!stack.isEmpty() && stack.peek() < arr[i]){ 
                stack.push(arr[i]);
                }
            else{
                stack.pop();
                i--;
            }
        }
        return stack;
    }
}