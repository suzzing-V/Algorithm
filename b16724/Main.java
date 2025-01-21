import java.util.*;
import java.io.*;

public class Main {
    static String[][] map;
    static int n;
    static int m;
    static boolean[][] visited;
    static boolean[][] finished;
    static int sz = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new String[n][m];
        visited = new boolean[n][m];
        finished = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            String[] split = br.readLine().split("");
            for(int j = 0; j < m; j++) {
                map[i][j] = split[j];
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(visited[i][j] || map[i][j].equals("S")) continue;
                dfs(i, j);
                finished[i][j] = true;
            }
        }

        System.out.println(sz);
    }

    private static void dfs(int x, int y) {
        if(finished[x][y] || map[x][y].equals("S")) {
            return;
        }
        if(visited[x][y]) {
            map[x][y] = "S";
            sz ++;
            return;
        }
        visited[x][y] = true;

        int nx = x;
        int ny = y;
        if(map[x][y].equals("U")) {
            nx = x - 1;
            if(nx < 0) {
                map[x][y] = "S";
                sz ++;
                return;
            }
        } else if(map[x][y].equals("D")) {
            nx = x + 1;
            if(nx >= n) {
                map[x][y] = "S";
                sz ++;
                return;
            }
        } else if(map[x][y].equals("L")) {
            ny = y - 1;
            if(ny < 0) {
                map[x][y] = "S";
                sz ++;
                return;
            }
        } else if(map[x][y].equals("R")) {
            ny = y + 1;
            if(ny >= m) {
                map[x][y] = "S";
                sz ++;
                return;
            }
        }
        dfs(nx, ny);
        finished[nx][ny] = true;
    }
}
