import java.util.*;

class Solution {

    PriorityQueue<String>[] bus;

    public String solution(int n, int t, int m, String[] timetable) {
        bus = new PriorityQueue[n];
        Arrays.sort(timetable);

        // 승객 버스에 태우기
        String departTime = "09:00";
        for(int i = 0; i < n; i++) {
            // System.out.println(departTime);
            bus[i] = new PriorityQueue<>((o1, o2) -> {return o2.compareTo(o1);});

            for(int j = 0; j < timetable.length; j++) {
                String crew = timetable[j];
                if(timetable[j] != null && crew.compareTo(departTime) <= 0) {
                    // System.out.println(crew);
                    bus[i].add(crew);
                    timetable[j] = null;
                    if(bus[i].size() == m) break;
                }
            }
            if(i == n - 1) break;
            departTime = addTime(departTime, t);
        }

        // 마지막 버스 확인하고 콘 태우기
        if(bus[n - 1].size() == m) { // 마지막 버스 꽉 차있으면 마지막으로 탄 크루의 도착시간 - 1
            return addTime(bus[n - 1].peek(), -1);
        }
        return departTime; // 마지막 버스 자리 있으면 셔틀버스 도착시간
    }

    private String addTime(String curr, int plus) {
        StringTokenizer st = new StringTokenizer(curr, ":");
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int curr_minute = h * 60 + m;
        curr_minute += plus;

        StringBuilder sb = new StringBuilder();
        String rh = String.format("%02d", curr_minute / 60);
        String rm = String.format("%02d", curr_minute % 60);
        sb.append(rh);
        sb.append(":");
        sb.append(rm);

        return sb.toString();
    }
}
