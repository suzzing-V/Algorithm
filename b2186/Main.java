import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int m;
    private static int k;
    private static int[][][] dp;
    private static char[][] board;
    private static String word;
    private static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        word = bf.readLine();
        dp = new int[n][m][word.length() + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < dp[0][0].length; k++){
                    dp[i][j][k] = -1;
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == word.charAt(0)) {
                    cnt += dfs(i, j, 1);
                }
            }
        }

        bw.write(String.valueOf(cnt));
        bw.close();
    }

    private static int dfs(int x, int y, int cnt) {
        if(cnt == word.length()) {
            return 1;
        }
        if(dp[x][y][cnt] != 0) {
            return dp[x][y][cnt];
        }

        dp[x][u]
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= k; j++) {
                int nx = x + dir[i][0] * k;
                int ny = y + dir[i][1] * k;

                if(nx < 0 || ny < 0 || nx >= n || ny >= m || board[nx][ny] != word.charAt(cnt)) continue;
                dp[x][y][cnt] += dfs(nx, ny, cnt + 1);
            }
        }
        return dp[x][y][cnt];
    }
}
