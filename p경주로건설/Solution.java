import java.util.*;

class Solution {

    private int[][] dir = { {1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    private int[][][] dp;
    private int[][] board;
    private boolean[][] visited;
    private int min = 1000000;

    public int solution(int[][] board1) {
        board = board1;
        dp = new int[board.length][board[0].length][4];
        visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                for(int k = 0; k < 4; k++) {
                    dp[i][j][k] = 1000000;
                }
            }
        }
        dfs(0, 0, 0, 0);
        return min;
    }

    private void dfs(int x, int y, int from, int cost) {
        if(x == board.length - 1 && y == board[0].length - 1) {
            // System.out.println("도달!");
            min = Math.min(min, cost);
            return;
        }
        if(dp[x][y][from] < cost) return; // 저장된 최소비용이 지금까지 온 경로의 최소비용보다 작으면 더 갈 필요가 없다.이미 그 방법으로 간게 더 최소.
        else dp[x][y][from] = cost; // 아니면 지금 비용 저장하고 탐색

        visited[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] == 1 || visited[nx][ny]) continue;

            if(i == from || (x == 0 && y == 0) ) { // 이전 방향과 같은 방향으로 가면 100원
                dfs(nx, ny, i, cost + 100);
            } else {
                dfs(nx, ny, i, cost + 600); // 다른 방향으로 가면 600원
            }
        }
        visited[x][y] = false;
    }
}