import java.io.*;
import java.util.*;

// (100 * 100 + 9900 + 9900) * log(10^4)
public class Main {

    private static int[][][] dp;
    private static String[][] map;
    private static int w;
    private static int h;
    private static int sx = -1;
    private static int sy = -1;
    private static int ex = -1;
    private static int ey = -1;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        dp = new int[h][w][4];
        map = new String[h][w];

        for(int i = 0; i < h; i ++) {
            String[] split = bf.readLine().split("");
            for(int j = 0; j < w; j++) {
                if(split[j].equals("C")) {
                    if(sx != -1) {
                        ex = i;
                        ey = j;
                    } else {
                        sx = i;
                        sy = j;
                    }
                }
                map[i][j] = split[j];
                for(int k = 0; k < 4; k++) dp[i][j][k] = Integer.MAX_VALUE;
            }
        }

        dikstra();
//        for(int i = 0; i < h; i++) {
//            for(int j = 0; j < w; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        int min = dp[ex][ey][0];
        for(int i = 1; i < 4; i++) {
            min = Math.min(min, dp[ex][ey][i]);
        }
        System.out.println(min);
    }

    private static void dikstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < 4; i++) {
            int nx = sx + dir[i][0];
            int ny = sy + dir[i][1];
//            System.out.println(nx + " " + ny);
            if(nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny].equals("*")) continue;
            pq.add(new Node(nx, ny, 0, i));
            dp[nx][ny][i] = 0;
            dp[sx][sy][i] = 0;
        }

        while(!pq.isEmpty()) {
            Node curr = pq.remove();
//            System.out.println(curr.x + " " + curr.y + " " + curr.mirror + " " + curr.dir);
            if(dp[curr.x][curr.y][curr.dir] < curr.mirror) continue;

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny].equals("*")) continue;

                int nd = getDir(curr.x, curr.y, nx, ny);
                if(Math.abs(nd - curr.dir) == 2) continue; // 현재 자기가 온 방향으로는 안간다

                int mirror = curr.mirror;
                // 현재 온 방향에서 90도 꺾으면 거울 추가
                if(nd != curr.dir) mirror ++;
                // 방향 저장 안하고 그냥 >= 해버리면 무한루프
                if(dp[nx][ny][nd] > mirror) {
                    dp[nx][ny][nd] = mirror;
                    pq.add(new Node(nx, ny, mirror, nd));
                }
            }
        }
    }

    private static int getDir(int cx, int cy, int nx, int ny) {
        if(cx == nx) {
            if(cy < ny) return 1;
            return 3;
        }

        if(cx < nx) return 2;
        return 0;
    }

    private static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int mirror;
        private int dir;

        Node(int x, int y, int mirror, int dir) {
            this.x = x;
            this.y = y;
            this.mirror = mirror;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node node) {
            return this.mirror - node.mirror;
        }
    }
}
