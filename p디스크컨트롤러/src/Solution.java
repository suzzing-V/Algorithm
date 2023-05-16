import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                else {
                    return o1[0] - o2[0];
                }
            }
        });
        answer = jobs[0][1];
        int time = jobs[0][0] + jobs[0][1];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                else {
                    return o1[1] - o2[1];
                }
            }
        });
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i = 1; i < jobs.length; i++) {
            pq.add(jobs[i]);
        }
        while(pq.size() != 0) {
            while(pq.peek()[0] > time) {
                queue.offer(pq.remove());
            }
            
            time += pq.peek()[1];
            answer += time - pq.peek()[0];
            pq.remove();
            while(!queue.isEmpty()) {
                pq.add(queue.remove());
            }
        }
        answer /= jobs.length;
        return answer;
    }
}