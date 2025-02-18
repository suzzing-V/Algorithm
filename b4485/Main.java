import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int[][] minRupee;
    static int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};

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

        int t = 1;
        while(true) {
            int n = Integer.parseInt(bf.readLine());
            if(n == 0) break;

            map = new int[n][n];
            minRupee = new int[n][n];
            for(int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    minRupee[i][j] = Integer.MAX_VALUE;
                }
            }

            Queue<Pos> queue = new LinkedList<>();
            queue.add(new Pos(0, 0));
            minRupee[0][0] = map[0][0];
            while (!queue.isEmpty()) {
                Pos curr = queue.remove();

                for(int i = 0; i < 4; i ++) {
                    int nx = curr.x + dir[i][0];
                    int ny = curr.y + dir[i][1];

                    if(nx >= 0 && nx < n && ny >= 0 && ny < n && minRupee[nx][ny] > minRupee[curr.x][curr.y] + map[nx][ny]) {
                        minRupee[nx][ny] = minRupee[curr.x][curr.y] + map[nx][ny];
                        queue.add(new Pos(nx, ny));
                    }
                }
            }
            bw.write("Problem " + (t ++) + ": " + minRupee[n - 1][n - 1] + "\n");
        }
        bw.close();
    }
}
