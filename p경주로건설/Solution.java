import java.util.*;

// 온 방향에 따른 최솟값도 저장해야 하는 이유: 현재 칸에 도착했을 경우 최솟값이지만 코너를 돌아서 후의 행보는 더 많이 들 수도 있다. 따라서 방향에 따른 최솟값도 저장해야한다.
class Solution {

    private int n;
    private int[][] board;
    private int[][][] dp;
    private int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int solution(int[][] board1) {
        n = board1.length;
        board = board1;
        dp = new int[n][n][4];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++)
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
        }

        for(int i = 0; i < 4; i++) dp[0][0][i] = 0;
        dikstra();
        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j < n; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        int min = Integer.MAX_VALUE;
        for(int i =0;i  <4; i++) {
            min = Math.min(dp[n - 1][n - 1][i], min);
        }
        return min;
    }

    private void dikstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < 2; i++) {
            pq.add(new Node(0, 0, 0, i));
        }

        while(!pq.isEmpty()) {
            Node curr = pq.remove();

            if(dp[curr.x][curr.y][curr.dir] < curr.cost) {
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) continue;

                if(i == curr.dir && dp[nx][ny][i] >= curr.cost + 100) {
                    dp[nx][ny][i] = curr.cost + 100;
                    pq.add(new Node(nx, ny, curr.cost + 100, i));
                } else if(i != curr.dir && dp[nx][ny][i] >= curr.cost + 600) {
                    dp[nx][ny][i] = curr.cost + 600;
                    pq.add(new Node(nx, ny, curr.cost + 600, i));
                }
            }
        }
    }

    private class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;
        int dir;

        Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
}