import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static boolean[][] board;
    static int[][] original;
    static int[][] pos = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        original = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                original[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while(true) {
            board = new boolean[n][m];
            if (bfs() == 0) break;

            for(int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int count = 0;
                    if (!board[i][j]) {
                        for (int k = 0; k < 4; k++) {
                            if (i + pos[k][0] >= 0 && i + pos[k][0] < n && j + pos[k][1] >= 0 && j + pos[k][1] < m) {
                                if (board[i + pos[k][0]][j + pos[k][1]]) count++;
                            }
                        }
                        if (count >= 2) {
                            original[i][j] = 0;
                        }
                    }
                }
            }
            time ++;
        }

        bw.write(String.valueOf(time));
        bw.close();
    }

    public static int bfs() {
        boolean[][] visit = new boolean[n][m];
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(0, 0));
        int count = 0;
        if(original[0][0] == 0) {
            board[0][0] = true;
        }

        while(!queue.isEmpty()) {
            Pos now = queue.remove();

            if(visit[now.x][now.y]) continue;
            if(original[now.x][now.y] == 0) count++;
            visit[now.x][now.y] = true;

            for(int i = 0; i < 4; i++) {
                int nextX = now.x + pos[i][0];
                int nextY = now.y + pos[i][1];

                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || visit[nextX][nextY]) continue;
                if(original[nextX][nextY] == 0) {
                    board[nextX][nextY] = true;
                    queue.add(new Pos(nextX, nextY));
                }
            }
        }
        return n * m - count;
    }
}
