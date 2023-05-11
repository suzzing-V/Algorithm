import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        for(int i = 0; i < arr.length; i++) {
            if(stack.peek() != arr[i]) {
                stack.push(arr[i]);
            }
        }
        
        answer = new int[stack.size()];
        int k = 0;
        for(int i : stack) {
            answer[k++] = i;
        }
        return answer;
    }
}