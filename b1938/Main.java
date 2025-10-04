import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static String[][] map;
    private static int[][][] dp;
    private static int init_s = 0;
    private static int init_x = 0;
    private static int init_y = 0;
    private static List<Pos> init_b = new ArrayList<>();
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        map = new String[n][n];
        dp = new int[n][n][2];
        boolean get_b = false;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j ++) {
                dp[i][j][0] = Integer.MAX_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < n; i++) {
            String[] split = bf.readLine().split("");
            for(int j = 0; j < n; j++) {
                map[i][j] = split[j];
                // 처음으로 b 등장하면 b에 대한 정보 얻기
                if(map[i][j].equals("B")) {
                    init_b.add(new Pos(i, j));
                    map[i][j] = "0";
                }
            }
        }

        if(init_b.get(0).x != init_b.get(1).x) {
            init_s = 1;
        } else {
            init_s = 0;
        }
        int min_x = Math.min(init_b.get(0).x, Math.min(init_b.get(1).x, init_b.get(2).x));
        int max_x = Math.max(init_b.get(0).x, Math.max(init_b.get(1).x, init_b.get(2).x));
        int min_y = Math.min(init_b.get(0).y, Math.min(init_b.get(1).y, init_b.get(2).y));
        int max_y = Math.max(init_b.get(0).y, Math.max(init_b.get(1).y, init_b.get(2).y));
        init_x = (min_x + max_x) / 2;
        init_y = (min_y + max_y) / 2;

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(init_x, init_y, init_s, 0));
        dp[init_x][init_y][init_s] = 0;

        while(!queue.isEmpty()) {
            Node curr = queue.remove();
//            System.out.println(curr.x + " " + curr.y + " " + curr.s + " " + curr.cnt);

            if(map[curr.x][curr.y].equals("E")) {
                if(curr.s == 0) {
                    if(curr.y - 1 >= 0 && curr.y + 1 < n && map[curr.x][curr.y - 1].equals("E") && map[curr.x][curr.y + 1].equals("E")) {
                        return curr.cnt;
                    }
                } else if(curr.s == 1) {
                    if(curr.x - 1 >= 0 && curr.x + 1 < n && map[curr.x - 1][curr.y].equals("E") && map[curr.x + 1][curr.y].equals("E")) {
                        return curr.cnt;
                    }
                }
            }

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(isOut(nx, ny, curr.s) || dp[nx][ny][curr.s] <= curr.cnt + 1) continue;

                queue.add(new Node(nx, ny, curr.s, curr.cnt + 1));
                dp[nx][ny][curr.s] = curr.cnt + 1;
            }

            int ns = curr.s == 0? 1: 0;
            if(canRotate(curr.x, curr.y) && dp[curr.x][curr.y][ns] > curr.cnt + 1) {
                queue.add(new Node(curr.x, curr.y, ns, curr.cnt + 1));
                dp[curr.x][curr.y][ns] = curr.cnt + 1;
            }
        }

        return 0;
    }

    private static boolean canRotate(int x, int y) {
        for(int i = 0; i < 8; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny].equals("1")) return false;
        }

        return true;
    }

    private static boolean isOut(int x, int y, int s) {
        if(s == 0) {
            if(y + 1 >= n || y - 1 < 0 || x >= n || x < 0) return true;
            if(map[x][y + 1].equals("1") || map[x][y - 1].equals("1") || map[x][y].equals("1")) return true;
        } else if(s == 1) {
            if(x + 1 >= n || x - 1 < 0 || y >= n || y < 0) return true;
            if(map[x + 1][y].equals("1") || map[x - 1][y].equals("1") || map[x][y].equals("1")) return true;
        }

        return false;
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Node {
        int x;
        int y;
        int s;
        int cnt;

        Node(int x, int y, int s, int cnt) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.cnt = cnt;
        }
    }
}
