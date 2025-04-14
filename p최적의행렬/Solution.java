import java.util.*;

class Solution {

    private int[][] dp;
    private int n;

    public int solution(int[][] matrix_sizes) {
        n = matrix_sizes.length;
        dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for(int i = 1; i < n; i++) { // 배열 길이 짧은 거부터 구한다.
            for(int start = 0; start < n; start++) {
                int end = start + i;
                if(end >= n) break;

                for(int k = start; k < end; k++) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][k] + dp[k + 1][end] + matrix_sizes[start][0] * matrix_sizes[k][1] * matrix_sizes[end][1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
