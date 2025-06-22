import java.io.*;
import java.util.*;

public class Main {

    private static int k;
    private static int n;
    private static int m;
    private static int[][] map;
    private static int[][][] dp;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
    private static int MAX = 2100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m][k + 1];

        for(int i = 0; i< n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                for(int l = 0; l <= k; l++) dp[i][j][l] = MAX;
            }
        }
        dp[0][0][0] = 0;
        bfs(k);
        int min = MAX;
        for(int i = 0; i <= k; i++) {
            min = Math.min(min, dp[n - 1][m - 1][i]);
        }
        if(min == MAX) System.out.println("-1");
        else System.out.println(min);
    }

    private static void bfs(int k) {
        Queue<Node> pq = new LinkedList<>();
        pq.add(new Node(0, 0, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();

//            System.out.println(curr.x + " " + curr.y + " " + curr.cnt + " " + curr.horse);
            for(int i = 0; i < 12; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m ) continue;
//                System.out.println(nx + " " + ny + " " + map[nx][ny]);
                if(map[nx][ny] == 1) continue;
                if(i >= 4) {
                    if(curr.horse < k) {
                        if(dp[nx][ny][curr.horse + 1] > dp[curr.x][curr.y][curr.horse] + 1) {
                            dp[nx][ny][curr.horse + 1] = dp[curr.x][curr.y][curr.horse] + 1;
                            pq.add(new Node(nx, ny, curr.horse + 1));
                        }
                    }
                } else {
                    if(dp[nx][ny][curr.horse] > dp[curr.x][curr.y][curr.horse] + 1) {
                        dp[nx][ny][curr.horse] = dp[curr.x][curr.y][curr.horse] + 1;
                        pq.add(new Node(nx, ny, curr.horse));
                    }
                }
            }
        }
    }

    private static class Node {
        private int x;
        private int y;
        private int horse;

        Node(int x, int y, int horse) {
            this.x = x;
            this.y = y;
            this.horse = horse;
        }
    }
}
