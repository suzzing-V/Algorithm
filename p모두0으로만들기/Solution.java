import java.util.*;

// list 순회할 때 for쓰기
// 시간복잡도 : O(V+E)
class Solution {

    private List<Integer>[] conn;
    private boolean[] visited;
    private long answer = 0;
    private long[] a;

    public long solution(int[] a1, int[][] edges) {
        a = new long[a1.length];
        for(int i = 0; i < a1.length; i++) {
            a[i] = a1[i];
        }

        conn = new ArrayList[a.length];
        visited = new boolean[a.length];
        for(int i = 0; i < conn.length; i++) {
            conn[i] = new ArrayList<Integer>();
        }

        // 연결 정보 저장
        for(int i = 0; i < edges.length; i++) {
            conn[edges[i][0]].add(edges[i][1]);
            conn[edges[i][1]].add(edges[i][0]);
        }

        visited[0] = true;
        dfs(0);
        if(a[0] != 0) return -1;
        return answer;
    }

    private long dfs(int node) {
        // System.out.println("node: "+ node);
        if(conn[node].size() == 1 && node != 0) {
            return a[node];
        }

        for(int i = 0; i < conn[node].size(); i++) {
            int child = conn[node].get(i);
            if(visited[child]) continue;
            visited[child] = true;

            long amount = dfs(child);
            // System.out.println(amount);
            a[node] += amount;
            answer += Math.abs(amount);
        }

        // System.out.println("a: " + node + " " + a[node]);
        return a[node];
    }
}