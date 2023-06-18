class Solution {
    static int R = 1000000007;
    public int solution(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        
        long d1 = 1;
        long d2 = 2;
        
        for(int i = 3; i <= n; i++) {
            long d3 = (d1 % R + d2 % R) % R;
            d1 = d2;
            d2 = d3;
        }
        return (int) d2;
    }
}