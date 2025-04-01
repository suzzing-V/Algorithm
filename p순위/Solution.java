import java.util.*;

class Solution {

    private int[][] dp;

    public int solution(int n, int[][] results) {
        dp = new int[n + 1][n + 1];
        for(int i = 0; i < results.length; i++) {
            int winner = results[i][0];
            int loser = results[i][1];
            dp[winner][loser] = 1;
            dp[loser][winner] = -1;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                for(int k = 1; k <= n; k++) {
                    if(dp[i][k] == 1 && dp[k][j] == 1) { // a가 b한테 이기고, b가 c한테 이기면 a는 c한테 이긴다
                        dp[i][j] = 1;
                        dp[j][i] = -1;
                    } else if(dp[i][k] == -1 && dp[k][j] == -1) { // a가 b한테 지고, b가 c한테 지면 a는 c한테 진다
                        dp[i][j] = -1;
                        dp[j][i] = 1;
                    }
                }
            }
        }

        int answer = 0;
        for(int i = 1; i <= n; i++) {
            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if(dp[i][j] != 0) cnt ++;
            }
            if(cnt == n - 1) answer ++; // n명의 선수가 있을 때, 각 선수는 n - 1번의 승패 알 수 있어야 순위를 확정할 수 있다.
        }
        return answer;
    }
}
