import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static char[][] map;
    private static int[][][] dp;
    private static boolean[][] visited;
    private static int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        dp = new int[n][m][4];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'H') map[i][j] = '0'; // 구멍의 경우 0칸 갈 수 있으므로
                dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = dp[i][j][3] = -1;
            }
        }

        int max = dfs(0, 0, 0);
        bw.write(String.valueOf(max));
        bw.close();
    }

    private static int dfs(int x, int y, int from) {
        if(x < 0 || y < 0 || x >= n || y >= m || map[x][y] == '0') return 0; // 더이상 갈 수 없을 경우
        if(visited[x][y]) { // 사이클 발생 시 무한 루프 // 현재 루트 방문했나
            System.out.println("-1");
            System.exit(0);
        }
        if(dp[x][y][from] != -1) { // 다른 루트에서 방문한 적 있나
            return dp[x][y][from];
        }

        visited[x][y] = true;
        dp[x][y][from] = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + (dir[i][0] * (map[x][y] - '0'));
            int ny = y + (dir[i][1] * (map[x][y] - '0'));
            dp[x][y][from] = Math.max(dp[x][y][from], dfs(nx, ny, i));
        }
        visited[x][y] = false;
        return dp[x][y][from] += 1;
    }
}
