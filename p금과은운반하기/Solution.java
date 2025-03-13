class Solution {

    private int a;
    private int b;
    private int[] g;
    private int[] s;
    private int[] w;
    private int[] t;

    public long solution(int a1, int b1, int[] g1, int[] s1, int[] w1, int[] t1) {
        a = a1;
        b = b1;
        g = g1;
        s = s1;
        w = w1;
        t = t1;
        long answer = binarySearch(1, 4 * (long)Math.pow(10, 14));
        return answer;
    }

    private long binarySearch(long left, long right) {
        if(left == right) {
            return left;
        }

        long mid = (left + right) / 2;
        long totalGold = 0; // mid 안에 옮길 수 있는 금의 양
        long totalSilver = 0; // mid 안에 옮길 수 있는 은의 양
        long totalBoth = 0; // mid 안에 옮길 수 있는 금과 은의 양
        for(int i = 0; i < g.length; i++) {
            long cnt = 0;
            if(mid % (2 * t[i]) < t[i]) cnt = mid / (2 * t[i]);
            else cnt = mid / (2 * t[i]) + 1;

            totalGold += Math.min(g[i], cnt * w[i]);
            totalSilver += Math.min(s[i], cnt * w[i]);
            totalBoth += Math.min((long) g[i] + s[i], cnt * w[i]);
        }

        if(totalGold < a || totalSilver < b || totalBoth < a + b) { // 시간 안에 못 옮기면 시간 늘리기
            return binarySearch(mid + 1, right);
        }
        return binarySearch(left, mid);
    }
}
