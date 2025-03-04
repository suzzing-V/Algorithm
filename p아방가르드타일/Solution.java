class Solution {

    int MOD = 1000000007;
    public int solution(int n) {
        long[] dp = new long[n + 1];
        if(n == 1) return 1;
        if(n == 2) return 3;
        if(n == 3) return 10;
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        long[] add = {12, 2, 4};

        for(int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] * 1 % MOD + dp[i - 2] * 2 % MOD + dp[i - 3] * 5 % MOD + add[i % 3]) % MOD;
            add[i % 3] += (dp[i - 1] * 2 % MOD + dp[i - 2] * 2 % MOD + dp[i - 3] * 4 % MOD);
        }
        return (int)(dp[n]);
    }
}
