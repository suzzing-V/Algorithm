import java.util.*;

class Solution {

    private ArrayList<Integer>[] conn;
    private int[][] dp; // dp[i][0]: i노드를 껐을 떄 자식노드 포함한 최소 전등 수
    private boolean[] visited;

    public int solution(int n, int[][] lighthouse) {
        conn = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++) {
            conn[i] = new ArrayList<>();
        }

        for(int i = 0; i < lighthouse.length; i++) {
            int n1 = lighthouse[i][0];
            int n2 = lighthouse[i][1];

            conn[n1].add(n2);
            conn[n2].add(n1);
        }

        dfs(1);
        int answer = Math.min(dp[1][0], dp[1][1]);
        return answer;
    }

    private void dfs(int node) {
        // 첫번째 노드가 아니고 연결된 노드가 하나면 말단노드. 첫번쨰 노드인 경우에는 그 하나가 부모가 아니고 자식이기 때문에 말단노드가 아니다.
        if(node != 1 && conn[node].size() == 1) {
            dp[node][0] = 0;
            dp[node][1] = 1;
            return;
        }

        visited[node] = true;
        dp[node][1] ++;
        for(int child : conn[node]) {
            if(visited[child]) continue; // 부모노드 제외
            dfs(child);
            dp[node][0] += dp[child][1]; // 내가 끄면 자식들은 켜야한다.
            dp[node][1] += Math.min(dp[child][0], dp[child][1]); // 내가 키면 자식들은 키든 말든 상관없다. 최소를 저장해야하므로 둘 중에 최솟값을 저장한다.
        }
        // System.out.println(node + " " + dp[node][0] + " " + dp[node][1]);
    }
}