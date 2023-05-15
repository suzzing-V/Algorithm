import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < prices.length; i++) {
            queue1.offer(prices[i]);
        } //스택에 prices 넣기
        
        int i = 0;
        while(true) {
            if(popQueue(queue1, queue2, answer, i++) == 0) break;
            if(popQueue(queue2, queue1, answer, i++) == 0) break;
        }
        answer[prices.length - 1] = 0;
        return answer;
    }
    
    public int popQueue(Queue<Integer> queue1, Queue<Integer> queue2, int[] answer, int i) {
        int pop = queue1.remove();
        int count = 0;
        while(!queue1.isEmpty()) {
            int tmp = queue1.remove();
            queue2.offer(tmp);
            if(tmp < pop) {
                count++;
                break;
            }
            count++;
        }
        
        while(!queue1.isEmpty()) {
            queue2.offer(queue1.remove());
        }
        answer[i] = count;
        return queue2.size();
    }
}