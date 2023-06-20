class Solution {
    public long solution(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        long dp1 = 1;
        long dp2 = 2;
        long answer = 0;
        int R = 1234567;
        
        for(int i = 3; i <= n; i++) {
            answer = (dp1 % R + dp2 % R) % R;
            dp1 = dp2;
            dp2 = answer;
        }
        return answer;
    }
}