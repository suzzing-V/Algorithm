import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < prices.length; i++) {
            stack.push(prices[i]);
        }
        
        int j = prices.length - 1;
        while(!stack.isEmpty()) {
            int pop = stack.pop();
            
            int i = pop - 1;
            while(i > 0) {
                if(hm.containsKey(i)) {
                    answer[j] = hm.get(i) - j;
                    break;
                }
                i--;
            }
            if(i == 0) {
                answer[j] = prices.length - 1 - j;
            }
            
            if(!hm.containsKey(pop)) hm.put(pop, j);
            j--;
        }
        return answer;
    }
}