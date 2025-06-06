import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int[] w;
    private static ArrayList<Integer>[] lines;
    private static boolean[] visited;
    private static ArrayList<Integer>[][] dp;
    private static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        w = new int[n + 1];
        lines = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        dp = new ArrayList[n + 1][2];
        sum = new int[n + 1][2];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i <= n; i++) {
            lines[i] = new ArrayList<Integer>();
            dp[i][0] = new ArrayList<Integer>();
            dp[i][1] = new ArrayList<Integer>();
        }
        for(int i = 0; i < n - 1; i++) { // 사이클 없는 트리의 간선 개수는 n - 1
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            lines[n1].add(n2);
            lines[n2].add(n1);
        }

        dfs(1);
        if(sum[1][0] > sum[1][1]) {
            System.out.println(sum[1][0]);
            Collections.sort(dp[1][0]);
            for(int i : dp[1][0]) {
                System.out.print(i + " ");
            }
        } else {
            System.out.println(sum[1][1]);
            Collections.sort(dp[1][1]);
            for(int i : dp[1][1]) {
                System.out.print(i + " ");
            }
        }
    }

    private static void dfs(int node) {
        visited[node] = true;
        dp[node][1].add(node); // 자기자신을 넣었을 때
        sum[node][1] += w[node];
        if(lines[node].size() == 1 && visited[lines[node].get(0)]) { // 말단 노드일 경우
            return;
        }

        for(int nxt : lines[node]) {
            if(visited[nxt]) continue;
            dfs(nxt);
            // 현재 노드를 사용하지 않을 경우 다음 노드 사용/비사용 중 가중치 큰 값 고르기
            if(sum[nxt][0] > sum[nxt][1]) {
                sum[node][0] += sum[nxt][0];
                for(int e : dp[nxt][0]) {
                    dp[node][0].add(e);
                }
            } else {
                sum[node][0] += sum[nxt][1];
                for(int e : dp[nxt][1]) {
                    dp[node][0].add(e);
                }
            }

            // 현재 노드를 사용할 경우 무조건 다음 노드는 비사용
            sum[node][1] += sum[nxt][0];
            for(int e : dp[nxt][0]) {
                dp[node][1].add(e);
            }
        }
//        System.out.println(node + " " + sum[node][0] + " " + sum[node][1]);
    }
}
