import java.io.*;
import java.util.*;

// 시간복잡도: 10^7 * 4 (상태마다 4방항으로 시도하기 때문)
// 같은 자리에 머무는 게 더 이득인 경우를 찾는다. 밤일 경우엔 벽을 부수고 이동하지 못하기 때문에 한턴 기다렸다가 벽을 부수는 게 더 유리할 수 있다.
// 밤일 경우 벽이 없는 경우는 그냥 가는 게 이득. 낮일 경우 벽이 있는 경우와 없는 경우도 그냥 가는 게 이득.
public class Main {

    private static int n;
    private static int m;
    private static int k;
    private static int[][][] dp;
    private static int[][] map;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][m + 1][k + 1];
        map = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            String[] split = bf.readLine().split("");
            for(int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(split[j - 1]);
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        bfs();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= k; i++) {
            min = Math.min(dp[n][m][i], min);
        }
        if(min == Integer.MAX_VALUE) min = -1;

        System.out.println(min);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 1, k, 1));
        dp[1][1][k] = 1;

        while(!queue.isEmpty()) {
            Node curr = queue.remove();

//            System.out.println(curr.x + " " + curr.y + " " + curr.crash + " " + curr.d);
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
                if(map[nx][ny] == 1) {
                    // 낮인 경우 부술 수 있는 횟수가 남아있으면 벽 부술 수 있다.
                    if(curr.d % 2 == 1 && curr.crash > 0 && dp[nx][ny][curr.crash - 1] > curr.d + 1) {
                        queue.add(new Node(nx, ny, curr.crash - 1, curr.d + 1));
                        dp[nx][ny][curr.crash - 1] = curr.d + 1;
                    } else if(curr.d % 2 == 0 && curr.crash > 0 && dp[nx][ny][curr.crash - 1] > curr.d + 2) {
                        // 밤인 경우 횟수 남아있으면 한 턴 제자리에 있다가 벽 부수고 이동할 수 있다.
                        queue.add(new Node(nx, ny, curr.crash - 1, curr.d + 2));
                        dp[nx][ny][curr.crash - 1] = curr.d + 2;
                    }
                } else {
                    if(dp[nx][ny][curr.crash] > curr.d + 1) {
                        queue.add(new Node(nx, ny, curr.crash, curr.d + 1));
                        dp[nx][ny][curr.crash] = curr.d + 1;
                    }
                }
            }
        }
    }

    private static class Node {
        int x;
        int y;
        int crash;
        int d;

        Node(int x, int y, int crash, int d) {
            this.x =x;
            this.y = y;
            this.crash = crash;
            this.d = d;
        }
    }
}
