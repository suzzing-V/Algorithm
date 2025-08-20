import java.io.*;
import java.util.*;

// !에 무조건 거울이 있는 게 아니라 설치할 수도, 설치 안할 수도 있다. 두 경우 모두 고려해줘야 한다.
// 한 지점에서 다른 모든 지점으로 갈 때 최소 거울의 수를 저장해놓고 비교하는 로직이므로 다익스트라.
// 모든 방향에서 오는 경우를 저장해야 한다.
// 시간복잡도: 2500 + 2499 * 100

public class Main {

    private static int[][][] dp;
    private static char[][] map;
    private static int n;
    private static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        map = new char[n][n];
        dp = new int[n][n][4];
        Pos start = null;
        Pos end = null;
        for(int i = 0; i < n; i++) {
            String line = bf.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
                dp[i][j][0] = 3000;
                dp[i][j][1] = 3000;
                dp[i][j][2] = 3000;
                dp[i][j][3] = 3000;
                if(map[i][j] == '#') {
                    if(start == null) start = new Pos(i, j);
                    else end = new Pos(i, j);
                }
            }
        }

        dikstra(start);
        int min = 3000;
        for(int i = 0; i < 4; i++) {
            min = Math.min(dp[end.x][end.y][i], min);
        }
        System.out.println(min);
    }

    private static void dikstra(Pos start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < 4; i++) {
            dp[start.x][start.y][i] = 0;
            int nx = start.x + dir[i][0];
            int ny = start.y + dir[i][1];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == '*') continue;
            if(dp[nx][ny][i] > 0) {
                dp[nx][ny][i] = 0;
                pq.add(new Node(nx, ny, 0, i));
            }
        }

        while(!pq.isEmpty()) {
            Node curr = pq.remove();

            if(map[curr.x][curr.y] == '#' || dp[curr.x][curr.y][curr.d] < curr.m) continue;
//            System.out.println(curr.x + " " + curr.y + " " + curr.d + " " + curr.m);
            // 거울을 설치할수 있는 위치에 설치한다면 두방향으로만 가능
            if(map[curr.x][curr.y] == '!') {
                if(curr.d == 0 || curr.d == 1) {
                    for(int i = 2; i <= 3; i++) {
                        int nx = curr.x + dir[i][0];
                        int ny = curr.y + dir[i][1];
                        if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == '*') continue;
                        if(dp[nx][ny][i] > curr.m + 1) {
                            dp[nx][ny][i] = curr.m + 1;
                            pq.add(new Node(nx, ny, curr.m + 1, i));
                        }
                    }
                } else {
                    for(int i = 0; i <= 1; i++) {
                        int nx = curr.x + dir[i][0];
                        int ny = curr.y + dir[i][1];
                        if(nx < 0 || nx >= n || ny < 0 || ny >= n|| map[nx][ny] == '*') continue;
                        if(dp[nx][ny][i] > curr.m + 1) {
                            dp[nx][ny][i] = curr.m + 1;
                            pq.add(new Node(nx, ny, curr.m + 1, i));
                        }
                    }
                }
            }
            // 설치하지 않거나 .이면 오던 방향으로
                    int nx = curr.x + dir[curr.d][0];
                    int ny = curr.y + dir[curr.d][1];
                    if(nx < 0 || nx >= n || ny < 0 || ny >= n|| map[nx][ny] == '*') continue;
                    if(dp[nx][ny][curr.d] > curr.m) {
                        dp[nx][ny][curr.d] = curr.m;
                        pq.add(new Node(nx, ny, curr.m, curr.d));
                    }
        }
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int m;
        int d;

        Node(int x, int y, int m, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.d = d;
        }

        @Override
        public int compareTo(Node n) {
            return this.m - n.m;
        }
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
