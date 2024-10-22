import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());

        int flag = 0;
        if(ccw(x1, y1, x2, y2, x3, y3) == 0 && ccw(x1, y1, x2, y2, x4, y4) == 0) {
            long minX = Math.min(x3, x4);
            long maxX = Math.max(x3, x4);
            long minY = Math.min(y3, y4);
            long maxY = Math.max(y3, y4);
            if(Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(y1, y2) <= Math.max(y3, y4)
                && Math.min(x3, x4) <= Math.max(x1, x2)&& Math.min(y3, y4) <= Math.max(y1, y2)) {
                flag = 1;
            }
        }
        else if((ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) < 0 && ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2) < 0)
        || (ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) <= 0 && ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2)== 0)
        || (ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) == 0 && ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2) <= 0)) {
            flag = 1;
        }
        System.out.println(flag);
    }

    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long result = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if(result > 0) return 1;
        if(result < 0) return -1;
        return 0;
    }
}
