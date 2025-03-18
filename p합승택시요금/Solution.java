import java.util.*;

class Solution {

    private int[][] dp;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        dp = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                dp[i][j] = 30000000;
                if(i == j) dp[i][j] = 0;
            }
        }

        for(int i = 0; i < fares.length; i++) {
            int n1 = fares[i][0];
            int n2 = fares[i][1];
            int fare = fares[i][2];
            dp[n1][n2] = dp[n2][n1] = fare;
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        int min = dp[s][a] + dp[s][b]; // 합승하지 않는 경우
        for(int i = 1; i <= n; i++) {
            min = Math.min(min, dp[s][i] + dp[i][a] + dp[i][b]);
        }
        return min;
    }
}
