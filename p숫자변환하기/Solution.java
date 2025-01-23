
class Solution {
    int dp[];
    public int solution(int x, int y, int n) {
        dp = new int[y + 1];
        if(x == y) return 0;
        for(int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        if(x * 2 <= y) dp[x * 2] = 1;
        if(x * 3 <= y) dp[x * 3] = 1;
        if(x + n <= y) dp[x + n] = 1;
        for(int i = x; i <= y; i++) {
            if(dp[i] == Integer.MAX_VALUE) continue;
            if(i * 2 <= y) dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            if(i * 3 <= y) dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
            if(i + n <= y) dp[i + n] = Math.min(dp[i + n], dp[i] + 1);
        }

        if(dp[y] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[y];
    }
}