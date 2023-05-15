import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int solution(int[] scoville, int K) {
        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        while(pq.peek() < K && pq.size() >= 2) {
            int first = pq.remove();
            int second = pq.remove() * 2;
            pq.add(first + second);
            count++;
        }
        
        if(pq.size() == 1 && pq.poll() < K) return -1;
        return count;
    }
}