import java.util.*;

class Solution {
    private int[][][] dp;
    private int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        dp = new int[m][n][2];

        dp[0][0][0] = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i - 1 >= 0) { // 위 확인
                    if(cityMap[i - 1][j] == 0) { // 윗길이 자유통행이면 모든 방향에서 온 경우의 수 더함
                        dp[i][j][1] += dp[i - 1][j][0] % MOD + dp[i - 1][j][1] % MOD;
                    } else if(cityMap[i - 1][j] == 2) { // 윗길이 회전 금지면 위에서 온 것만 현재 길로 올 수 있음
                        dp[i][j][1] += dp[i - 1][j][1] % MOD;
                    }
                }

                if(j - 1 >= 0) { // 왼쪽 확인
                    if(cityMap[i][j - 1] == 0) {
                        dp[i][j][0] += dp[i][j - 1][0] % MOD + dp[i][j - 1][1] % MOD;
                    } else if(cityMap[i][j - 1] == 2) {
                        dp[i][j][0] += dp[i][j - 1][0] % MOD;
                    }
                }

                dp[i][j][0] %= MOD;
                dp[i][j][1] %= MOD;
            }
        }
        int answer = dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1];
        answer %= MOD;
        return answer;
    }
}
