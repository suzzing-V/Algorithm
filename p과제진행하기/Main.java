import java.util.*;

class Solution {

    public class Homework {
        String name;
        int rest;

        Homework(String name, int rest) {
            this.name = name;
            this.rest = rest;
        }
    }

    public String[] solution(String[][] plans) {
        Stack<Homework> incompleted = new Stack<>();
        Arrays.sort(plans, (o1, o2) -> o1[1].compareTo(o2[1]));

        String[] result = new String[plans.length];
        int idx = 0;
        for(int i = 0; i < plans.length - 1;i ++) {
            int gap = calculateTimeGap(plans[i][1], plans[i + 1][1]);
            int time = Integer.parseInt(plans[i][2]);
            if(gap < time) {
                incompleted.push(new Homework(plans[i][0], time - gap));
            } else {
                if(gap >= time) {
                    result[idx++] = plans[i][0];
                    gap -= time;
                } else {
                    incompleted.push(new Homework(plans[i][0], time - gap));
                    continue;
                }
                while(gap > 0 && !incompleted.isEmpty()) {
                    Homework hw = incompleted.pop();
                    if(gap - hw.rest >= 0) {
                        gap -= hw.rest;
                        result[idx ++] = hw.name;
                    } else {
                        incompleted.push(new Homework(hw.name, hw.rest - gap));
                        gap = 0;
                    }
                }

            }
        }
        result[idx++] = plans[plans.length -1][0];

        while(!incompleted.isEmpty()) {
            result[idx++] = incompleted.pop().name;
        }
        return result;
    }

    public int calculateTimeGap(String front, String back) {
        StringTokenizer st = new StringTokenizer(front, ":");
        int fh = Integer.parseInt(st.nextToken());
        int fm = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(back, ":");
        int bh = Integer.parseInt(st.nextToken());
        int bm = Integer.parseInt(st.nextToken());

        int h = bh - fh;
        int m = 0;
        if(bm - fm >= 0) {
            m = bm - fm;
        } else {
            m = bm + 60 - fm;
            h --;
        }
        return h * 60 + m;
    }
}
