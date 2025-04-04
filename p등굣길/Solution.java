import java.util.*;

class Solution {

    private int[][] dp;
    private int m;
    private int n;
    private int[][] map;
    private int MOD = 1000000007;

    public int solution(int m1, int n1, int[][] puddles) {
        n = n1;
        m = m1;
        dp = new int[n + 1][m + 1];
        map = new int[n + 1][m + 1];
        for(int i = 0; i < puddles.length; i++) {
            map[puddles[i][1]][puddles[i][0]] = 1;
        }
        for(int i = 2; i <= n; i++) {
            if(map[i][1] == 1) break;
            dp[i][1] = 1;
        }
        for(int i = 2; i <= m; i++) {
            if(map[1][i] == 1) break;
            dp[1][i] = 1;
        }

        for(int i = 2; i <= n; i++) {
            for(int j = 2; j <= m; j++) {
                if(map[i][j] == 1) continue;
                dp[i][j] = (dp[i - 1][j] % MOD + dp[i][j - 1] % MOD) % MOD;
            }
        }

        return dp[n][m];
    }
}
