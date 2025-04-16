import java.util.*;

class Solution {

    // 최대한 고르게 배분하는 게 최소값. 제곱은 수가 하나 커질 때마다 급격히 커지기 때문
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {return o2 - o1;});
        for(int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }

        while(n > 0) {
            int curr = pq.remove();
            if(curr == 0) break;

            n --;
            pq.add(curr - 1);
        }

        long answer = 0;
        while(!pq.isEmpty()) {
            int curr = pq.remove();
            // System.out.println(curr);
            answer += curr * curr;
        }
        return answer;
    }
}
