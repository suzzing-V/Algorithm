import java.util.*;

class Solution {

    private int[][] dp;

    public int[] solution(int target) {
        dp = new int[target + 1][2];
        for(int i = 1; i <= Math.min(20, target); i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }

        for(int i = 21; i <= target; i++) {
            int cnt = 0;
            int bs = 0;
            int minus = 0;
            if(i / 2 * 2 > 40) {
                minus = 40;
            } else {
                minus = i / 2 * 2;
            }
            dp[i][0] = dp[i - minus][0] + 1;
            dp[i][1] = dp[i - minus][1];

            minus = 20;
            if(dp[i - minus][0] + 1 < dp[i][0] || (dp[i - minus][0] + 1 == dp[i][0] && dp[i - minus][1] + 1 > dp[i][1])) {
                dp[i][0] = dp[i - minus][0] + 1;
                dp[i][1] = dp[i - minus][1] + 1;
            }

            if(i / 3 * 3 > 60) {
                minus = 60;
            } else {
                minus = i / 3 * 3;
            }
            if(dp[i - minus][0] + 1 < dp[i][0] || (dp[i - minus][0] + 1 == dp[i][0] && dp[i - minus][1] > dp[i][1])) {
                dp[i][0] = dp[i - minus][0] + 1;
                dp[i][1] = dp[i - minus][1];
            }

            if(i >= 50) {
                minus = 50;
                if(dp[i - minus][0] + 1 < dp[i][0] || (dp[i - minus][0] + 1 == dp[i][0] && dp[i - minus][1] + 1 > dp[i][1])) {
                    dp[i][0] = dp[i - minus][0] + 1;
                    dp[i][1] = dp[i - minus][1] + 1;
                }
            }
            // System.out.println(i + " " + dp[i][0] + " " + dp[i][1]);
        }

        int[] answer = new int[2];
        answer[0] = dp[target][0];
        answer[1] = dp[target][1];
        return answer;
    }
}
