class Solution {
    public int solution(int n) {
        if(n % 2 == 1) return 0;
        int R = 1000000007;
        long[] dp = new long[5001];
        dp[2] = 3;
        
        for(int i = 4; i <= n; i+=2) {
            dp[i] += dp[2] * dp[i - 2];
            for(int j = 2; i - 2 * j > 0; j++) dp[i] += 2 * dp[i - 2 * j];
            dp[i] += 2;
            dp[i] %= (long)R;
        }
        return (int)dp[n];
    }
}