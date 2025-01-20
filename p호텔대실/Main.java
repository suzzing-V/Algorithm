import java.util.*;

class Solution {
    boolean[][] time_rec;
    public int solution(String[][] book_time) {
        time_rec = new boolean[1440][book_time.length];
        for(int i = 0; i < book_time.length; i++) {
            StringTokenizer st = new StringTokenizer(book_time[i][0], ":");
            int startH = Integer.parseInt(st.nextToken());
            int startM = Integer.parseInt(st.nextToken());
            int startToMinute = startH * 60 + startM;
            st = new StringTokenizer(book_time[i][1], ":");
            int endH = Integer.parseInt(st.nextToken());
            int endM = Integer.parseInt(st.nextToken());
            int endToMinute = endH * 60 + endM;
            endToMinute = (endToMinute + 9 >= 1439) ? 1439 : endToMinute + 9;

            for(int j = startToMinute; j <= endToMinute; j++) {
                time_rec[j][i] = true;
            }
        }

        int max = 0;
        for(int i = 0; i < time_rec.length; i++) {
            int cnt = 0;
            for(int j = 0; j < time_rec[0].length; j++) {
                if(time_rec[i][j]) cnt ++;
            }
            max = Math.max(cnt, max);
        }
        return max;
    }
}
