import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static int[][][] dp;
    static boolean[][] visited;
    static int[][] dir = { {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1][3];
        visited = new boolean[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = -200000000;
            }
        }

        int max = dfs(1, 1, 0);
        bw.write(String.valueOf(max));
        bw.close();
    }

    private static int dfs(int x, int y, int from) { // dir: 0 - 위에서 1 - 왼쪽에서 2 - 오른쪽에서 옴
        if(dp[x][y][from] != -200000000) {
            return dp[x][y][from];
        }
        if(x == n && y == m) {
            return map[x][y];
        }

        visited[x][y] = true;
//        System.out.println(x + " " + y);
        for(int i = 0; i < 3; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx <= 0 || ny <= 0 || nx > n || ny > m || visited[nx][ny]) continue;

            dp[x][y][from] = Math.max(dp[x][y][from], dfs(nx, ny, i));
        }
        visited[x][y] = false;
        return dp[x][y][from] += map[x][y];
    }
}
