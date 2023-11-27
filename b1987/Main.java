import java.io.*;
import java.util.*;

public class Main {
    static int r;
    static int c;
    static int[][] board;
    static boolean[] visit = new boolean[26];
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new int[r][c];

        for(int i = 0; i < r; i++) {
            String line = bf.readLine();
            for(int j = 0; j < c; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0);
        bw.write(String.valueOf(max));
        bw.close();
    }

    public static void dfs(int x, int y) {
        if(x < 0 || x >= r || y < 0 || y >= c || visit[board[x][y] - 65]) {
            max = Math.max(max, countVisit());
            System.out.println("max: " + max);
            return;
        }

        visit[board[x][y] - 65] = true;
        dfs(x - 1, y);
        dfs(x + 1, y);
        dfs(x, y - 1);
        dfs(x, y + 1);
        visit[board[x][y] - 65] = false;
    }

    public static int countVisit() {
        int count = 0;
        for(int i = 0; i < 26; i++) {
            if(visit[i]) count++;   
        }
        return count;
    }
}
