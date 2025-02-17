import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[][] miro;
    static int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] minWall;

    private static class Pos {
        private int x;
        private int y;

        private Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        miro = new int[n + 1][m + 1];
        minWall = new int[n + 1][m + 1];
        for(int i = 1; i < n + 1; i ++) {
            String row = bf.readLine();
            for(int j = 1; j < m + 1; j++) {
                miro[i][j] = row.charAt(j - 1) - '0';
                minWall[i][j] = 100000;
            }
        }

        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(1, 1));
        minWall[1][1] = miro[1][1];
        while(!q.isEmpty()) {
            Pos curr = q.remove();

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx > 0 && ny > 0 && nx <= n && ny <= m && minWall[nx][ny] > minWall[curr.x][curr.y] + miro[nx][ny]) {
                    minWall[nx][ny] = minWall[curr.x][curr.y] + miro[nx][ny];
                    q.add(new Pos(nx, ny));
                }
            }
        }

        bw.write(String.valueOf(minWall[n][m]));
        bw.close();
    }
}
