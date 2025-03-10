import java.util.*;

class Solution {

    private int[][] dp;
    private int min = Integer.MAX_VALUE;
    private int[][] clockHands;
    private int[] combi;
    private int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int solution(int[][] clockHands1) {
        dp = new int[clockHands1.length][clockHands1[0].length];
        clockHands = clockHands1;
        combi = new int[clockHands.length];
        dfs(0); // 첫 행 돌리는 방법 만들기
        return min;
    }

    private void dfs(int idx) {
        if(idx == dp.length) {
            copy();
            int cnt = rotate();
            if(isOpen()) {
                min = Math.min(min, cnt);
            }
            return;
        }

        for(int i = 0; i < 4; i ++) {
            combi[idx] = i;
            dfs(idx + 1);
        }
    }

    private void copy() {
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                dp[i][j] = clockHands[i][j];
            }
        }
    }

    private int rotate() {
        int cnt = 0;
        for(int j = 0; j < dp[0].length; j++) { // 완성된 combi에 따라 첫행 돌리기
            int add = (dp[0][j] + combi[j]) % 4;
            cnt += add;
            dp[0][j] = (dp[0][j] + add) % 4;
            for(int k = 0; k < 4; k++) {
                int nx = dir[k][0];
                int ny = j + dir[k][1];
                if(nx < 0 || nx >= dp.length || ny < 0 || ny >= dp[0].length) {
                    continue;
                }
                dp[nx][ny] = (dp[nx][ny] + add) % 4;
            }
        }

        for(int i = 1; i < dp.length; i++) { // 2행부터 n - 1행이 0되도록 돌리기
            for(int j = 0; j < dp[0].length; j++) {
                int add = (4 - dp[i - 1][j]) % 4;
                cnt += add;
                dp[i][j] = (dp[i][j] + add) % 4;
                for(int k = 0; k < 4; k++) {
                    int nx = i + dir[k][0];
                    int ny = j + dir[k][1];
                    if(nx < 0 || nx >= dp.length || ny < 0 || ny >= dp[0].length) {
                        continue;
                    }
                    dp[nx][ny] = (dp[nx][ny] + add) % 4;
                }
            }
        }
        return cnt;
    }

    private boolean isOpen() {
        for(int i = 0; i < dp[0].length; i++) {
            if(dp[dp.length - 1][i] != 0) return false;
        }

        return true;
    }
}
