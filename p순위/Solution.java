import java.util.*;
import java.io.*;

// 시간복잡도: 10^6
// a가 k를 이기고, k가 b를 이기면 a가 b를 이긴다
class Solution {

    private int[][] dp;

    public int solution(int n, int[][] results) {
        dp = new int[n + 1][n + 1];
        for(int i = 0; i < results.length; i++) {
            dp[results[i][0]][results[i][1]] = 1;
            dp[results[i][1]][results[i][0]] = -1;
        }

        for(int i = 1; i <= n ;i++) {
            for(int j =1; j <= n; j++) {
                for(int k = 1; k <= n; k++) {
                    if(dp[i][k] == 1 && dp[k][j] == 1) {
                        dp[i][j] = 1;
                        dp[j][i] = -1;
                    } else if(dp[i][k] == -1 && dp[k][j] == -1) {
                        dp[i][j] = -1;
                        dp[j][i] = 1;
                    }
                }
            }
        }

        // 순위 알려면 n - 1개의 결과 알아야 한다.
        int answer = 0;
        for(int i = 1; i <= n; i++) {
            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if(dp[i][j] != 0) cnt ++;
            }
            if(cnt == n - 1) answer ++;
        }
        return answer;
    }
}