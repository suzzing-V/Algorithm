class Solution {
    public int solution(int N, int[][] road, int K) {
        boolean[] visit = new boolean[N + 1];
        DFS(1, road, K, 0, visit);
        int answer = countTown(visit);
        return answer;
    }
    
    public void DFS(int n, int[][] road, int k, int count, boolean[] visit) {
        if(count > k) return;
        
        visit[n] = true;
        for(int i = 0; i < road.length; i++) {
            if(road[i][0] == n) {
                DFS(road[i][1], road, k, count + road[i][2], visit);
            } else if(road[i][1] == n) {
                DFS(road[i][0], road, k, count + road[i][2], visit);
            }
        }
    }
                               
    public int countTown(boolean[] visit) {
        int count = 0;
        for(int i = 1; i < visit.length; i++) {
            if(visit[i]) count++;
        }
        return count;
    }
}