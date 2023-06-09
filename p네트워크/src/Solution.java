class Solution {
    static boolean[] visit = new boolean[201];
    public int solution(int n, int[][] computers) {
        int answer = 0;
        for(int i = 0; i < n; i++) {
            if(visit[i] == false) {
                DFS(n, computers, i);
                answer++;
            }
        }
        return answer;
    }
    
    public void DFS(int n, int[][] computers, int com) {
        visit[com] = true;
        for(int i = 0; i < n; i++) {
            if(computers[com][i] == 1 && visit[i] == false) {
                DFS(n, computers, i);
            }
        }
    }
}