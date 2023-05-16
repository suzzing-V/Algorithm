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
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                else {
                    return o1[1] - o2[1];
                }
            }
        });
        
        int i = 0;
        int time = 0;
        int count = 0;
        while(count < jobs.length) {
            while(i < jobs.length && jobs[i][0] <= time) {
                pq.add(jobs[i++]);
            }
            
            if(pq.isEmpty()) {
                time += jobs[i][0] - time + jobs[i][1];
                answer += jobs[i][1];
                i++;
                count++;
            }
            else {
                time += pq.peek()[1];
                answer += time - pq.peek()[0];
                pq.remove();
                count++;
            }
        }
        answer /= jobs.length;
        return answer;
    }
}