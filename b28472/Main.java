import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int r;
    private static int l;
    private static int q;
    private static ArrayList<Integer>[] conn;
    private static int[] dp; //i번노드의 값
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        conn = new ArrayList[n + 1];
        dp = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(dp, -1);
        for(int i = 0; i <= n; i++) {
            conn[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            conn[n1].add(n2);
            conn[n2].add(n1);
        }

        l = Integer.parseInt(bf.readLine());
        for(int i = 0; i < l; i++) {
            st = new StringTokenizer(bf.readLine());
            int node = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            dp[node] = v;
        }

        dfs(r, true);

        q = Integer.parseInt(bf.readLine());
        for(int i = 0; i < q; i++) {
            int node = Integer.parseInt(bf.readLine());
            System.out.println(dp[node]);
        }
    }

    private static int dfs(int curr, boolean isMax) {
        visited[curr] = true;
        if(conn[curr].size() == 1 && curr != r) {
            return dp[curr];
        }

        // 최대값 골라야 하면 -1로, 최솟값 골라야 하면 큰값으로 세팅
        int result = isMax ? -1 : Integer.MAX_VALUE;
        for(int next : conn[curr]) {
            if(visited[next]) continue;
            if(isMax) result = Math.max(result, dfs(next, !isMax));
            else result = Math.min(result, dfs(next, !isMax));
        }

        return dp[curr] = result;
    }
}
