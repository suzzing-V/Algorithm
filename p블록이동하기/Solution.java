import java.util.*;

class Solution {

    private int[][][] dp;
    private int n;
    private int[][] board;
    private int[][] second = {{0, 1}, {1, 0}};
    private int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int solution(int[][] board1) {
        board = board1;
        n = board.length;
        dp = new int[n][n][4];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        bfs();
        int answer = Math.min(dp[n - 1][n - 1][3], dp[n - 1][n - 1][0]);
        return answer;
    }

    private void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0, 0));
        // dp[0][0][1] = 0;
        // dp[0][1][3] = 0;
        while(!pq.isEmpty()) {
            Node curr = pq.remove();

            int x2 = curr.x + second[curr.dir][0];
            int y2 = curr.y + second[curr.dir][1];
            int dir1 = 0;
            int dir2 = 0;
            if(curr.dir == 0) {
                dir1 = 1;
                dir2 = 3;
            } else {
                dir1 = 2;
                dir2 = 0;
            }
            // 두 지점에서의 저장값이 모두 현재까지 걸린 시간보다 적으면 리턴
            // if(dp[curr.x][curr.y][dir1] < curr.t && dp[x2][y2][dir2] < curr.t) {
            //     continue;
            // }
            // System.out.println(curr.x + " " + curr.y + " "+ curr.dir + " " + curr.t);
            // dp[curr.x][curr.y][dir1] = curr.t;
            // dp[x2][y2][dir2] = curr.t;

            // 상하좌우 이동
            for(int i = 0; i < 4; i++) {
                int nx1 = curr.x + move[i][0];
                int ny1 = curr.y + move[i][1];
                int nx2 = x2 + move[i][0];
                int ny2 = y2 + move[i][1];
                if(isInBoard(nx1, ny1) && isInBoard(nx2, ny2) && board[nx1][ny1] == 0 && board[nx2][ny2] == 0 && !(dp[nx1][ny1][dir1] <= curr.t + 1 && dp[nx2][ny2][dir2] <= curr.t + 1)) {
                    // System.out.println("가로전진");
                    // if(nx2 == 4 && ny2 == 4) System.out.println(dir2);
                    dp[nx2][ny2][dir2] = Math.min(dp[nx2][ny2][dir2], curr.t + 1);
                    dp[nx1][ny1][dir1] = Math.min(dp[nx1][ny1][dir1], curr.t + 1);
                    if(curr.dir == 0) pq.add(new Node(nx1, Math.min(ny1, ny2), 0, curr.t + 1));
                    if(curr.dir == 1) pq.add(new Node(Math.min(nx1, nx2), ny1, 1, curr.t + 1));
                }
            }

            // x1, y1을 축으로 해 시계 회전
            // 가로일 경우
            if(curr.dir == 0 && isInBoard(curr.x + 1, curr.y + 1) && board[curr.x + 1][curr.y + 1] == 0) {
                int nx2 = x2 + 1;
                int ny2 = y2 - 1;
                if(isInBoard(nx2, ny2) && board[nx2][ny2] == 0 && !(dp[curr.x][curr.y][2] <= curr.t + 1 && dp[nx2][ny2][0] <= curr.t + 1)) {
                    dp[nx2][ny2][0] = Math.min(dp[nx2][ny2][0], curr.t + 1);
                    dp[curr.x][curr.y][2]  = Math.min(dp[curr.x][curr.y][2] , curr.t + 1);
                    pq.add(new Node(curr.x, curr.y, 1, curr.t + 1));
                }
                // 세로일 경우
            } else if(curr.dir == 1 && isInBoard(curr.x + 1, curr.y - 1) && board[curr.x + 1][curr.y - 1] == 0) {
                int nx2 = x2 - 1;
                int ny2 = y2 - 1;
                if(isInBoard(nx2, ny2) && board[nx2][ny2] == 0 && !(dp[curr.x][curr.y][3] <= curr.t + 1 && dp[nx2][ny2][1] <= curr.t + 1)) {
                    dp[curr.x][curr.y][3] = Math.min(dp[curr.x][curr.y][3], curr.t + 1);
                    dp[nx2][ny2][1] = Math.min(dp[nx2][ny2][1], curr.t + 1);
                    pq.add(new Node(nx2, ny2, 0, curr.t + 1));
                }
            }

            // x1, y1을 축으로 해 반시계 회전
            // 가로일 경우
            if(curr.dir == 0 && isInBoard(curr.x - 1, curr.y + 1) && board[curr.x - 1][curr.y + 1] == 0) {
                int nx2 = x2 - 1;
                int ny2 = y2 - 1;
                if(isInBoard(nx2, ny2) && board[nx2][ny2] == 0 && !(dp[curr.x][curr.y][0] <= curr.t + 1 && dp[nx2][ny2][2] <= curr.t + 1)) {
                    dp[nx2][ny2][2] = Math.min(dp[nx2][ny2][2], curr.t + 1);
                    dp[curr.x][curr.y][0]  = Math.min(dp[curr.x][curr.y][0] , curr.t + 1);
                    pq.add(new Node(nx2, ny2, 1, curr.t + 1));
                }
                // 세로일 경우
            } else if(curr.dir == 1 && isInBoard(curr.x + 1, curr.y + 1) && board[curr.x + 1][curr.y + 1] == 0) {
                int nx2 = x2 - 1;
                int ny2 = y2 + 1;
                if(isInBoard(nx2, ny2) && board[nx2][ny2] == 0 && !(dp[curr.x][curr.y][1] <= curr.t + 1 && dp[nx2][ny2][3] <= curr.t + 1)) {
                    dp[curr.x][curr.y][1] = Math.min(dp[curr.x][curr.y][1], curr.t + 1);
                    dp[nx2][ny2][3] = Math.min(dp[nx2][ny2][3], curr.t + 1);
                    pq.add(new Node(curr.x, curr.y, 0, curr.t + 1));
                }
            }

            // x2, y2을 축으로 해 시계 회전
            // 가로일 경우
            if(curr.dir == 0 && isInBoard(x2 - 1, y2 - 1) && board[x2 - 1][y2 - 1] == 0) {
                int nx1 = curr.x - 1;
                int ny1 = curr.y + 1;
                if(isInBoard(nx1, ny1) && board[nx1][ny1] == 0 && !(dp[x2][y2][0] <= curr.t + 1 && dp[nx1][ny1][2] <= curr.t + 1)) {
                    dp[x2][y2][0] = Math.min(dp[x2][y2][0], curr.t + 1);
                    dp[nx1][ny1][2]  = Math.min(dp[nx1][ny1][2], curr.t + 1);
                    pq.add(new Node(nx1, ny1, 1, curr.t + 1));
                }
                // 세로일 경우
            } else if(curr.dir == 1 && isInBoard(x2 - 1, y2 + 1) && board[x2 - 1][y2 + 1] == 0) {
                int nx1 = curr.x + 1;
                int ny1 = curr.y + 1;
                if(isInBoard(nx1, ny1) && board[nx1][ny1] == 0 && !(dp[x2][y2][1] <= curr.t + 1 && dp[nx1][ny1][3] <= curr.t + 1)) {
                    dp[x2][y2][1]  = Math.min(dp[x2][y2][1] , curr.t + 1);
                    dp[nx1][ny1][3]  = Math.min(dp[nx1][ny1][3], curr.t + 1);
                    pq.add(new Node(x2, y2, 0, curr.t + 1));
                }
            }

            // x2, y2을 축으로 해 반시계 회전
            // 가로일 경우
            if(curr.dir == 0 && isInBoard(x2 + 1, y2 - 1) && board[x2 + 1][y2 - 1] == 0) {
                int nx1 = curr.x + 1;
                int ny1 = curr.y + 1;
                if(isInBoard(nx1, ny1) && board[nx1][ny1] == 0 && !(dp[x2][y2][2] <= curr.t + 1 && dp[nx1][ny1][0] <= curr.t + 1)) {
                    dp[x2][y2][2] = Math.min(dp[x2][y2][2], curr.t + 1);
                    dp[nx1][ny1][0]  = Math.min(dp[nx1][ny1][0], curr.t + 1);
                    pq.add(new Node(x2, y2, 1, curr.t + 1));
                }
                // 세로일 경우
            } else if(curr.dir == 1 && isInBoard(x2 - 1, y2 - 1) && board[x2 - 1][y2 - 1] == 0) {
                int nx1 = curr.x + 1;
                int ny1 = curr.y - 1;
                if(isInBoard(nx1, ny1) && board[nx1][ny1] == 0 && !(dp[x2][y2][3] <= curr.t + 1 && dp[nx1][ny1][1] <= curr.t + 1)) {
                    dp[x2][y2][3]  = Math.min(dp[x2][y2][3] , curr.t + 1);
                    dp[nx1][ny1][1]  = Math.min(dp[nx1][ny1][1], curr.t + 1);
                    pq.add(new Node(nx1, ny1, 0, curr.t + 1));
                }
            }
        }
    }

    private boolean isInBoard(int x, int y) {
        return (x >= 0) && x < n && y >= 0 && y < n;
    }

    private class Node implements Comparable<Node> {
        int x;
        int y;
        int dir;
        int t;

        Node(int x, int y, int dir, int t) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.t = t;
        }

        @Override
        public int compareTo(Node n) {
            return this.t - n.t;
        }
    }
}