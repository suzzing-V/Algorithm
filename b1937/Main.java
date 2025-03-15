import java.util.*;
import java.io.*;


// dfs 밖에 방법이 없는데 시초 나면 dp를 섞어보자
public class Main {

    static int n;
    static int[][] map;
    static int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int max = 0;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작 위치마다 dfs
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                max = Math.max(dfs(i, j), max);
            }
        }

        bw.write(String.valueOf(max));
        bw.close();
    }

    private static int dfs(int x, int y) {
        // 이미 값이 있는 경우 그 값 리턴
        if(dp[x][y] != 0) {
            return dp[x][y];
        }

        // 판다가 현재 칸에서 최소로 살 수 있는 기간 1
        dp[x][y] = 1;

        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if(map[nx][ny] > map[x][y]) { // 다음에 갈 칸이 지금 칸보다 커야 간다.
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1); // 최대값 저장
            }
        }
        return dp[x][y];
    }
}
