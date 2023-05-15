import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        stack.push(0);
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < prices[i - 1]) {
                while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                    answer[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - 1 - stack.peek();
            stack.pop();
        }
        return answer;
    }
}