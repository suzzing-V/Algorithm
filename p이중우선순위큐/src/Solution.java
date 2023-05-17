import java.util.Collections;
import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> nq = new PriorityQueue<>();
        PriorityQueue<Integer> xq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i < operations.length; i++) 
            operationsProcess(operations[i], xq, nq);
        
        if(nq.isEmpty() && xq.isEmpty()) {
            answer[0] = 0; answer[1] = 0;
        } else if(nq.isEmpty()) {
            answer[0] = xq.peek();
            while(!xq.isEmpty()) nq.offer(xq.remove());
            answer[1] = nq.peek();
        } else {
            answer[1] = nq.peek();
            while(!nq.isEmpty()) xq.offer(nq.remove());
            answer[0] = xq.peek();
        }
        
        return answer;
    }
    
    public void operationsProcess(String op, PriorityQueue<Integer> xq, PriorityQueue<Integer> nq) {
        if(op.charAt(0) == 'I') {
            String[] line = new String[2];
            line = op.split(" ");
            if(xq.isEmpty()) nq.offer(Integer.parseInt(line[1]));
            else { xq.offer(Integer.parseInt(line[1])); }
        } else if(op.equals("D 1")) {
            if(xq.isEmpty()) {
                while(!nq.isEmpty()) {
                    xq.offer(nq.remove());
                }
            }
            if(!xq.isEmpty()) xq.remove();
        } else if(op.equals("D -1")) {
            if(nq.isEmpty()) {
                while(!xq.isEmpty()) {
                    nq.offer(xq.remove());
                }
            }
            if(!nq.isEmpty()) nq.remove();
        }
    }
}