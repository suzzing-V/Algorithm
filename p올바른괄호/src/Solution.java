import java.util.*;

class Solution {
    boolean solution(String s) {
        String[] arr = new String[s.length()];
        arr = s.split("");
        Stack<String> stack = new Stack<>();
        
        for(String mark : arr) {
            if(mark.equals("(")) stack.push("(");
            else {
                if(stack.size() == 0) return false;
                else stack.pop();
            }
        }
        
        if(stack.size() == 0) return true;
        return false;
    }
}