class Solution {
    public int solution(int n) {
        int answer = 0;
        int[][] dp = new int[5001][5001];
        int R = 1000000007;
        dp[1][0] = 1;
        dp[2][0] = 1; dp[2][1] = 1;

        for(int i = 3; i <= n; i++) {
            dp[i][0] = 1;
            for(int j = 1; j <= i / 2; j++) dp[i][j] = ((dp[i - 1][j] % R) + (dp[i - 2][j - 1] % R)) % R;
        }
        
        for(int i = 0; i <= n / 2; i++) {
            answer += ((Math.pow(2, i) % R) * (dp[n][i] % R)) % R;
        }
        return answer % R;
    }
}