import java.util.*;
import java.io.*;

public class Main {

    private static int w;
    private static int h;
    private static String[][] map;
    private static int[][][] dp;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int dirty = 0;
    private static int[][] dirtyIdx; // i, j에 위치한 더러운 곳 idx

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String[] split = bf.readLine().split(" ");
            h = Integer.parseInt(split[0]);
            w = Integer.parseInt(split[1]);
//            System.out.println(h + " " + w);
            if(w == 0 && h == 0) break;

            map = new String[w][h];
            dirtyIdx = new int[w][h];
            dirty = 0;
            int sx = 0;
            int sy = 0;
            for (int i = 0; i < w; i++) {
                String[] split1 = bf.readLine().split("");
                for (int j = 0; j < h; j++) {
                    String square = split1[j];
                    if (square.equals("o")) {
                        sx = i;
                        sy = j;
                    } else if (square.equals("*")) {
                        // 더러운 곳마다 고유번호 부여
                        dirtyIdx[i][j] = dirty;
                        dirty++;
                    }
                    map[i][j] = square;
                }
            }

            // dp[i][j][k] : i, j 위치에서 k의 상태일 때 최소시간
            dp = new int[w][h][(int) Math.pow(2, dirty)];
            for(int i = 0; i < w; i ++) {
                for(int j = 0; j < h; j ++) {
                    for(int k = 0; k < dp[0][0].length; k++) {
                        dp[i][j][k] = Integer.MAX_VALUE;
                    }
                }
            }

            clean(sx, sy);
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < w; i++) {
                for(int j = 0; j < h; j++) {
                    min = Math.min(min, dp[i][j][(int)Math.pow(2, dirty) - 1]);
                }
            }
            if(min == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(min);
        }
    }

    private static void clean(int sx, int sy) {
        Queue<Node> pq = new LinkedList<>();
        pq.add(new Node(sx, sy, 0, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();
//            System.out.println(curr.x + " " + curr.y + " " + Integer.toBinaryString(curr.clean) + " "  + curr.time);

            if(dp[curr.x][curr.y][curr.clean] <= curr.time) continue;

            dp[curr.x][curr.y][curr.clean] = curr.time;
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= w || ny < 0 || ny >= h || map[nx][ny].equals("x")) continue;

                if(map[nx][ny].equals("*")) {
                    // 더러운 거 체크
                    pq.add(new Node(nx, ny, curr.clean | (1 << dirtyIdx[nx][ny]), curr.time + 1));
                } else {
                    pq.add(new Node(nx, ny, curr.clean, curr.time + 1));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int clean;
        private int time;

        Node(int x, int y, int clean, int time) {
            this.x = x;
            this.y = y;
            this.clean = clean;
            this.time = time;
        }

        @Override
        public int compareTo(Node n) {
            if(this.time == n.time) {
                return Integer.bitCount(n.clean) - Integer.bitCount(this.clean);
            }
            return this.time - n.time;
        }
    }
}
