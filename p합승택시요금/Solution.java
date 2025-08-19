import java.util.*;

// 중간 지점을 정하고, 시작지점~중간지점 + 중간지점~a + 중간지점~b의 값의 최솟값을 구한다.
// 여러 지점에서 시작하고 끝나는 최소 거리를 구해야 하므로 플로이드 워셜이 적절하다.
// 플로이드 워셜은 k 먼저해야.
// 시간복잡도: 200^3
class Solution {
    private int[][] dp;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        dp = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) dp[i][j] = 0;
                else dp[i][j] = 30000000;
            }
        }

        for(int i = 0; i < fares.length; i++) {
            int n1 = fares[i][0];
            int n2 = fares[i][1];
            int fare = fares[i][2];

            dp[n1][n2] = fare;
            dp[n2][n1] = fare;
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        // 중간지점 하나씩 정해보면서 시작지점~중간지점 + 중간지점~a + 중간지점~b
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, dp[s][i] + dp[i][a] + dp[i][b]);
        }
        return answer;
    }
}