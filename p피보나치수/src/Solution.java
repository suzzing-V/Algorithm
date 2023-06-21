class Solution {
    public int solution(int n) {
        int dp1 = 0;
        int dp2 = 1;
        int dp3 = 0;
        
        for(int i = 2; i <= n; i++) {
            dp3 = (dp1 % 1234567 + dp2 % 1234567) % 1234567;
            dp1 = dp2;
            dp2 = dp3;
}
        return dp3;
    }
}