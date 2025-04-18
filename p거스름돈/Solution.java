import java.util.*;

class Solution {

    private int[][] dp;
    private int MOD = 1000000007;

    public int solution(int n, int[] money) {
        dp = new int[money.length][n + 1];
        Arrays.sort(money);

        int start = money[0];
        // 첫줄 채우기
        for(int i = 1; i <= n; i++) {
            if(i % money[0] == 0) dp[0][i] = 1;
        }

        for(int i = 1; i < money.length; i++) {
            for(int j = start; j <= n; j++) {
                if(money[i] == j) dp[i][j] ++; // 현재 동전과 만들어야될 숫자의 값이 같으면 이 동전 하나로 만들 수 있으므로 +1
                if(j - money[i] >= 0) dp[i][j] += (dp[i][j - money[i]] + dp[i - 1][j]) % MOD; // 현재 동전이 만들어야될 수보다 크면 현재 동전 포함해서 이 수 못 만든다. 따라서 현재 동전이 더 작은 경우, 이 동전을 포함해서 만드는 경우의 수를 구한다. 이전 동전들만 사용해서 만드는 수 + 새로운 동전 포함해서 만드는 수
                else dp[i][j] += dp[i - 1][j]; // 새로운 동전 포함 못하므로 이전까지의 동전들만 가지고 만드는 경우의 수만 더한다.
            }
            // System.out.println(Arrays.toString(dp[i]));
        }

        return dp[money.length - 1][n] % MOD;
    }
}
