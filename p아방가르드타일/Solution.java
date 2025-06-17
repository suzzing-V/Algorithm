class Solution {

    private long[] dp;
    private int MOD = 1000000007;

    public int solution(int n) {
        dp = new long[100001];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = dp[3] + dp[2] * 2 + dp[1] * 5 + 2;
        dp[5] = dp[4] + dp[3] * 2 + dp[2] * 5 + dp[1] * 2 + 2;
        dp[6] = dp[5] + dp[4] * 2 + dp[3] * 5 + dp[2] * 2 + dp[1] * 2 + 4;

        long[] sum = {dp[2] * 2 + dp[1] * 2, 0, dp[1] * 2}; // 3의 배수마다 추가로 더해지는 값이 반복된다.
        int[] notSplit = {4, 2, 2};
        for(int i = 7; i <= n; i++) {
            dp[i] += notSplit[i % 3]; // 쪼개지지 않는 n * i 타일 만드는 경우의 수
            dp[i] += dp[i - 1] % MOD;
            dp[i] += dp[i - 2] * 2 % MOD;
            dp[i] += dp[i - 3] * 5 % MOD;

            // sum 갱신
            sum[i % 3] += 2 * dp[i - 4] % MOD;
            sum[i % 3] += 2 * dp[i - 5] % MOD;
            sum[i % 3] += 4 * dp[i - 6] % MOD;

            dp[i] += sum[i % 3];
            dp[i] %= MOD;
        }

        return (int)dp[n];
    }
}