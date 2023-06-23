class Solution {
    boolean[] town = new boolean[51];
    public int solution(int N, int[][] road, int K) {
        boolean[] visit = new boolean[N + 1];
        visit[1] = true;
        DFS(1, road, K, 0, visit);
        int answer = countTown(town);
        return answer;
    }
    
    public void DFS(int n, int[][] road, int k, int count, boolean[] visit) {
        if(count > k || checkVisit(visit)) {
            putTown(visit, town, n);
            return;
        }
        
        if(checkVisit(visit)) {
            for(int i = 0; i < visit.length; i++) {
                town[i] = true;
            }
        }
        
        for(int i = 0; i < road.length; i++) {
            if(road[i][0] == n && !visit[road[i][1]]) {
                visit[road[i][1]] = true;
                DFS(road[i][1], road, k, count + road[i][2], visit);
                visit[road[i][1]] = false;
            } else if(road[i][1] == n && !visit[road[i][0]]) {
                visit[road[i][0]] = true;
                DFS(road[i][0], road, k, count + road[i][2], visit);
                visit[road[i][0]] = false;
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
    
    public boolean checkVisit(boolean[] visit) {
        for(int i = 1; i < visit.length; i++) {
            if(!visit[i]) return false;
        }
        return true;
    }
    
    public void putTown(boolean[] visit, boolean[] town, int n) {
        for(int i = 0; i < visit.length; i++) {
            if(visit[i] && i != n) town[i] = true;
        }
    }
}