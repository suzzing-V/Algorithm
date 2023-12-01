import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] dp;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        dp[n - 1][m - 1] = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(dp[i][j] + " " );
            }
            System.out.println();
        }

        bw.write(String.valueOf(dfs(0, 0)));
        bw.close();
    }

    public static int dfs(int x, int y) {
        if(x == n - 1 && y == m - 1) return 1;

        if(dp[x][y] == -1) {
            dp[x][y] = 0;
            for(int i = 0; i < 4; i++) {
                int goX = x + dir[i][0];
                int goY = y + dir[i][1];
                if(goX >= 0 && goX < n && goY >= 0 && goY < m && map[goX][goY] < map[x][y])
                    dp[x][y] += dfs(goX, goY);
            }
        }
        return dp[x][y];
    }
}