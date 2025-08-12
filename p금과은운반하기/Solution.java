// 특정 시간 안에 a, b만큼의 금과 은을 운반할 수 있을까? -> binarySearch
// 가장 오래 걸리는 운반 시간은 대략 (10^9 + 10^9) * 2 * 10^5
// a >= 0, b >= 0이므로 0부터 탐색해야 한다.
// a, b를 운반할 수 있는 가장 작은 시간을 구해야하므로 lower bound
// 운반할 수 있는 최대 금의 수 >= a, 운반할 수 있는 최대 은의 수 >= b, 운반할 수 있는 금과 은의 수  >= a + b면 주어진 시간 안에 a, b만큼의 금과 은을 운반할 수 있다는 것.

class Solution {

    private long answer = 4 * (long)Math.pow(10, 14) + (long)Math.pow(10, 5);
    private int n;
    private int a;
    private int b;
    private int[] g;
    private int[] s;
    private int[] w;
    private int[] t;

    public long solution(int a1, int b1, int[] g1, int[] s1, int[] w1, int[] t1) {
        n = g1.length;
        a = a1;
        b = b1;
        g = g1;
        s = s1;
        w = w1;
        t = t1;
        return binarySearch(0, answer);

        // return answer;
    }

    private long binarySearch(long start, long end) {
        if(start >= end) return start;

        long mid = (start + end) / 2;

        long total_gold = 0;
        long total_silver = 0;
        long total_both = 0;

        for(int i = 0; i < n; i++) {
            int cg = g[i];
            int cs = s[i];
            int cw = w[i];
            int ct = t[i];
            long move_cnt = mid / (2 * ct);
            if(mid % (2 * ct) >= ct) move_cnt ++;

            total_gold += Math.min(cg, move_cnt * cw);
            total_silver += Math.min(cs, move_cnt * cw);
            total_both += Math.min(cg + cs, move_cnt * cw);
        }

        if(a <= total_gold && b <= total_silver && a + b <= total_both) {
            // answer = Math.min(answer, mid);
            return binarySearch(start, mid);
        } else {
            return binarySearch(mid + 1, end);
        }
    }
}