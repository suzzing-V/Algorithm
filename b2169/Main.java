import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static int[][] dir = { {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];
        visited = new boolean[n][m];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i == 1) dp[i][j] = map[i][j] + dp[i][j - 1];
            }
        }

        for(int i = 2; i <= n; i++) {
            int[] from_left = new int[m + 1];
            int[] from_right = new int[m + 1];
            from_left[1] = dp[i - 1][1] + map[i][1];
            for(int j = 2; j <= m; j++) {
                from_left[j] = Math.max(dp[i - 1][j], from_left[j - 1]) + map[i][j];
            }

            from_right[m] = dp[i - 1][m] + map[i][m];
            for(int j = m - 1; j >= 1; j --) {
                from_right[j] = Math.max(dp[i - 1][j], from_right[j + 1]) + map[i][j];
            }

            for(int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(from_left[j], from_right[j]);
            }
        }
        bw.write(String.valueOf(dp[n][m]));
        bw.close();
    }

    private static int dfs(int x, int y) {
        if(dp[x][y] != -1000) {
            return dp[x][y];
        }

        visited[x][y] = true;
        System.out.println(x + " " + y);
        int max = -1000;
        for(int i = 0; i < 3; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;

            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny));
        }

        return dp[x][y] = map[x][y] + max;
    }
}
