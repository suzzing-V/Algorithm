import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static int[][] board;
    private static boolean[][] visited;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        makeCombi(0, 0, 0);
        System.out.println(max);
    }

    private static void makeCombi(int x, int y, int sum) {
        if(y == m) {
            x ++;
            y = 0;
        }
        if(x == n) {
            max = Math.max(max, sum);
            return;
        }
        if(visited[x][y]) {
            makeCombi(x, y + 1, sum);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = x; i < n; i++) {
            if(visited[i][y]) break;
            sb.append(board[i][y]);

            for(int j = x; j <= i; j++) {
                visited[j][y] = true;
            }
//            System.out.println(x + " " + y + " " + sb.toString());
            makeCombi(x, y + 1, sum + Integer.parseInt(sb.toString()));
            for(int j = x; j <= i; j++) {
                visited[j][y] = false;
            }
        }

        sb = new StringBuilder(String.valueOf(board[x][y]));
        for(int i = y + 1; i < m; i++) {
            if(visited[x][i]) break;

            sb.append(board[x][i]);
            for(int j = y; j <= i; j++) {
                visited[x][j] = true;
            }
//            System.out.println("right :" + x + " " + y + " " + sb.toString());
            makeCombi(x, y + 1, sum + Integer.parseInt(sb.toString()));
            for(int j = y; j <= i; j++) {
                visited[x][j] = false;
            }
        }
    }
}
