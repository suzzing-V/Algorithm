import java.io.*;
import java.util.*;

public class Main {

    private static int[][][] dp;
    private static int[][] map;
    private static int n;
    private static int m;
    private static int k;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n][m][k + 1];
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
                for(int l = 0; l <= k; l++) {
                    dp[i][j][l] = Integer.MAX_VALUE;
                }
            }
        }

        dikstra();

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i <= k; i++) {
            answer = Math.min(answer, dp[n - 1][m - 1][i]);
        }

        if(answer == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(answer);
        }
    }

    private static void dikstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dp[0][0][0] = 1;
        pq.add(new Node(0, 0,1, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();

//            if(dp[curr.x][curr.y][curr.distroyed] <= curr.dist) {
//                continue;
//            }
//            System.out.println(curr.x + " " + curr.y + " " + curr.dist + " " + curr.distroyed);

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                int nk = curr.distroyed + (map[nx][ny] == 1? 1: 0);
                if(nk > k || dp[nx][ny][nk] <= curr.dist + 1) continue;
                dp[nx][ny][nk] = curr.dist + 1;
                pq.add(new Node(nx, ny, curr.dist + 1, nk));
            }
        }
    }

    private static class Node implements Comparable<Node> {

        int x;
        int y;
        int dist;
        int distroyed;

        Node(int x, int y, int dist, int distroyed) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.distroyed = distroyed;
        }

        @Override
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }
    }
}

