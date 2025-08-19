import java.util.*;

// 예시를 보면 끝나는 시간은 시청자 수에 포함하지 않는다. 따라서 i ~ i + 광고시간 - 1을 구해서 비교해야 한다.
// 먼저 구간의 끝값과 시작값에 -1, 1 표시를 해 변화량을 구한다. 그리고 이를 누적합하면 특정 시간의 시청자 수를 구할 수 있다.
// 그 다음, 이 배열을 누적합 하면 특정 구간의 시청자 수를 구할 수 있다.
// 시간 복잡도: O(n)

class Solution {

    private long[] acc;
    private int[] var;

    public String solution(String play_time, String adv_time, String[] logs) {
        int pts = toSec(play_time);
        acc = new long[pts + 1];
        var = new int[pts + 1];

        int ats = toSec(adv_time);
        // 특정 시간에 몇 명이 시청했는지 구하기
        for(int i = 0; i < logs.length; i++) {
            StringTokenizer st = new StringTokenizer(logs[i], "-");
            int ss = toSec(st.nextToken());
            int es = toSec(st.nextToken());

            var[ss] += 1;
            var[es] -= 1;
        }

        acc[0] = var[0];
        for(int i = 1; i < acc.length; i++) {
            acc[i] = acc[i - 1] + (long)var[i];
        }

        // 누적합 구하기
        for(int i = 1; i < acc.length; i++) {
            acc[i] += acc[i - 1];
        }

        // 가장 시청자 수 많은 구간 구하기
        long max = acc[ats];
        int start = 0;
        for(int i = 1; i + ats - 1 <= pts; i ++) {
            long sum = acc[i + ats - 1] - acc[i - 1];
            if(sum > max) {
                max = sum;
                start = i;
            }
        }

        String answer = toHHMMSS(start);
        return answer;
    }

    private int toSec(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        return h * 3600 + m * 60 + s;
    }

    private String toHHMMSS(int sec) {
        int h = sec / 3600;
        int m = sec % 3600 / 60;
        int s = sec % 3600 % 60;

        String ph = (h < 10)? "0" + String.valueOf(h) : String.valueOf(h);
        String pm = (m < 10)? "0" + String.valueOf(m) : String.valueOf(m);
        String ps = (s < 10)? "0" + String.valueOf(s) : String.valueOf(s);

        return ph + ":" + pm + ":" + ps;
    }
}