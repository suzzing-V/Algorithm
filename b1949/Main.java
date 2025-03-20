import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static List<Integer>[] neighbors;
    private static int[] people;
    private static int[][] dp; // i 마을이 우수마을일 때와 우수마을이 아닐 때의 최대 우수마을 주민 술
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        people = new int[n + 1];
        neighbors = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
            neighbors[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            neighbors[n1].add(n2);
            neighbors[n2].add(n1);
        }

        dfs(1);
        bw.write(String.valueOf(Math.max(dp[1][0], dp[1][1])));
        bw.close();
    }

    private static void dfs(int curr) {
//        System.out.println(curr);
        visited[curr] = true;
        for(int i = 0; i < neighbors[curr].size(); i++) {
            int child = neighbors[curr].get(i);
            if(visited[child]) continue;
            dfs(child);
            dp[curr][0] += Math.max(dp[child][1], dp[child][0]); // 현재 마을이 우수 마을이 아닐 때 자식노드들은 우수마을 주민 수 최대인 경우 고른다.
            dp[curr][1] += dp[child][0]; // 현재 마을이 우수마을이면 자식 노드는 모두 우수마을 아니다.
        }

        dp[curr][1] += people[curr]; // 현재 마을이 우수마을일 경우 현재 마을의 주민 수 더해줘야 함.
    }
}
