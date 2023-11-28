import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n + 1][m + 1];
        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < m; j++) {
                map[i + 1][j + 1] = line.charAt(j) - 48;
            }
        }

        bw.write(String.valueOf(bfs(n, m, map)));
        bw.close();
    }

    public static class Pos {
        int x;
        int y;
        int isBreak;
        int dis;

        Pos(int x, int y, int isBreak, int dis) {
            this.x = x;
            this.y = y;
            this.isBreak = isBreak;
            this.dis = dis;
        }
    }

    public static int bfs(int n, int m, int[][] map) {
        int[][] go = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        boolean[][][] visit = new boolean[n + 1][m + 1][2];
        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(1, 1, 0, 1));
        visit[1][1][0] = true;

        while(!queue.isEmpty()) {
            Pos pos = queue.poll();
            if(pos.x == n && pos.y == m) return pos.dis;

            for(int i = 0; i < 4; i++) {
                int goX = pos.x + go[i][0];
                int goY = pos.y + go[i][1];
                if(goX <= n && goX >= 1 && goY <= m && goY >= 1 && !visit[goX][goY][pos.isBreak]) {
                    if(map[goX][goY] == 0) {
                        queue.add(new Pos(goX, goY, pos.isBreak, pos.dis + 1));
                        visit[goX][goY][pos.isBreak] = true;
                    } else {
                        if(pos.isBreak == 0) {
                            queue.add(new Pos(goX, goY, pos.isBreak + 1, pos.dis + 1));
                            visit[goX][goY][1] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
