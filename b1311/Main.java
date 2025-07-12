import java.io.*;
import java.util.*;

// dfs로 할 경우 최소 경우가 아닌데 처음에 나오면 끝까지 탐색한다. bfs로 하면 최소경우를 먼저 뽑고, 그걸 먼저 저장하기 때문에 뒤에 나오는 같은 경우를 더 빨리 가지 칠 수 있다.
// 시간: 20 * 2^20
// 공간: 20 * 2^20 * 4

public class Main {

    // i번째 일 할 때 j의 상태일 경우 최소 비용
    private static int[][] dp;
    private static int n;
    private static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        dp = new int[n + 1][(int)Math.pow(2, n + 1)];
        d = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);

            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= n; j++) {
                d[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

//        System.out.println(Integer.toBinaryString((int)Math.pow(2, n) - 1));
        System.out.println(dp[n][(int)Math.pow(2, n) - 1]);
    }

    private static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 1; i <= n; i++) {
            pq.add(new Node(1, (1 << (i - 1)), d[i][1]));
            dp[1][(1 << (i - 1))] = d[i][1];
        }

        while(!pq.isEmpty()) {
            Node curr = pq.remove();

//            System.out.println(curr.turn + " " + Integer.toBinaryString(curr.visited) + " " + curr.cost);
            // 마지막 일이면 끝냄
            if(curr.turn == n) continue;

            for(int i = 1; i <= n; i++) {
                // 이미 i번 사람 사용했으면 넘어감
                if((curr.visited & (1 << (i - 1))) != 0) continue;

                int nextVisited = (1 << (i - 1)) | curr.visited;
                // dp에 저장된 값이 더 클 경우 갱신
                if(dp[curr.turn + 1][nextVisited] > curr.cost + d[i][curr.turn + 1]) {
                    pq.add(new Node(curr.turn + 1, nextVisited, curr.cost + d[i][curr.turn + 1]));
                    dp[curr.turn + 1][nextVisited] = curr.cost + d[i][curr.turn + 1];
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        private int turn;
        private int visited;
        private int cost;

        Node(int turn, int visited, int cost) {
            this.turn = turn;
            this.visited = visited;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
}
