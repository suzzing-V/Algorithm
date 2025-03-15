import java.io.*;
import java.util.*;

public class Main {

    private static List<Integer>[] friends;
    private static int n;
    private static int[][] dp; // 현재 노드의 상태에 따른 현재 노드까지의 early 최소 개수
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        friends = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        for(int i = 0; i <= n ; i++) {
            friends[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            friends[f1].add(f2);
            friends[f2].add(f1);
        }

        dfs(1);
        bw.write(String.valueOf(Math.min(dp[1][0], dp[1][1])));
        bw.close();
    }

    private static void dfs(int curr) {
        dp[curr][0] = 0; // 현재 노드가 early가 아닐 때 초기 early의 개수
        dp[curr][1] = 1; // 현재 노드가 early일 때 초기 early의 개수
        visited[curr] = true; // 밑으로만 내려가면서 탐색
        for(int f : friends[curr]) {
            if(visited[f]) continue;
            dfs(f);
            dp[curr][0] += dp[f][1]; // 현재 노드가 early가 아니면 주변 노드가 early여야 하므로 주변 노드 early인 경우에서의 최소 early수를 더한다.
            dp[curr][1] += Math.min(dp[f][0], dp[f][1]); // 현재 노드가 early면 주변 노드가 early든 아니든 상관 없으므로 둘 중 최소값 저장한다.
        }
    }
}
