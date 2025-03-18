import java.util.*;

// 시간: 이상/미만
class Solution {

    private long[] times = new long[360001];

    public String solution(String play_time, String adv_time, String[] logs) {
        // 시청 시간 초 변환해서 표시
        for(int i = 0; i < logs.length; i++) {
            StringTokenizer st = new StringTokenizer(logs[i], "-");
            int start_sec = toSec(st.nextToken());
            int end_sec = toSec(st.nextToken());

            times[start_sec] += 1;
            times[end_sec] -= 1;
        }

        // 시청자 수 누적합 구하기
        int play_sec = toSec(play_time);
        for(int i = 1; i <= play_sec; i++) {
            times[i] += times[i - 1];
        }

        // 최대 누적시간 가지는 광고 위치 구하기
        int adv_sec = toSec(adv_time);
        long slide = 0; // 현재 구간합
        long max_time = 0;
        int max_start = 0;
        // 초기 구간합 구하기
        for(int i = 0; i < adv_sec; i++) {
            slide += times[i];
        }

        for(int i = 0; i <= play_sec - adv_sec + 1; i++) {
            if(max_time < slide) {
                max_time = slide;
                max_start = i;
            }
            slide -= times[i];
            slide += times[i + adv_sec];
        }

        return toHMS(max_start);
    }

    private int toSec(String hms) {
        StringTokenizer st = new StringTokenizer(hms, ":");
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        return s + m * 60 + h * 3600;
    }

    private String toHMS(int sec) {
        int h = sec / 3600;
        sec %= 3600;
        int m = sec / 60;
        sec %= 60;
        int s = sec;

        return String.format("%02d", h) + ":" + String.format("%02d",m) + ":" + String.format("%02d",s);
    }
}
