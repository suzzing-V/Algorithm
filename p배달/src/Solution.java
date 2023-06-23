import java.util.*;

class Solution {
    public class Info implements Comparable<Info> {
        int cost;
        int town;
        Info(int town, int cost) {
            this.cost = cost;
            this.town = town;
        }
        
        @Override
        public int compareTo(Info info) {
            if(this.cost > info.cost) return 1;
            else if(this.cost < info.cost) return -1;
            return 0;
        }
    }
    
    boolean[] town = new boolean[51];
    public int solution(int N, int[][] road, int K) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        BFS(dist, 1, road);
        int answer = 0;
        for(int i = 1; i < dist.length; i++) {
            if(dist[i] <= K) answer ++;
        }
        return answer;
    }
    
    public void BFS(int[] dist, int start, int[][] road) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(start, 0));
        
        while(!pq.isEmpty()) {
            Info tmp = pq.poll();
            
            if(tmp.cost > dist[tmp.town]) continue;
            for(int i = 0; i < road.length; i++) {
                if(road[i][0] == tmp.town && tmp.cost + road[i][2] < dist[road[i][1]]) {
                    dist[road[i][1]] = tmp.cost + road[i][2];
                    pq.add(new Info(road[i][1], tmp.cost + road[i][2]));
                } else if(road[i][1] == tmp.town && tmp.cost + road[i][2] < dist[road[i][0]]) {
                    dist[road[i][0]] = tmp.cost + road[i][2];
                    pq.add(new Info(road[i][0], tmp.cost + road[i][2]));
                }
            }
        }
    }
}