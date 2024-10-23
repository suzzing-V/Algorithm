import java.io.*;
import java.util.*;

public class Main {

    static int[] parents;

    static class Line {
        int x1;
        int y1;
        int x2;
        int y2;

        Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        parents = new int[n];
        Line[] lines = new Line[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            Line now = new Line(x1, y1, x2, y2);
            lines[i] = now;
            for(int j = 0; j < i; j++) {
                if(isCross(now, lines[j])) {
                    union(i, j);
                }
            }
        }

        int[] count = new int[n];
        for(int i = 0; i < count.length; i++) {
            count[find(parents[i])] ++;
        }

        int grpCnt = 0;
        int max = 0;
        for(int i = 0; i < n; i++) {
            if(count[i] != 0) {
                max = Math.max(max, count[i]);
                grpCnt ++;
            }
        }
        System.out.println(grpCnt);
        System.out.println(max);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) parents[b] = a;
        else parents[a] = b;
    }

    static int find(int x) {
        if(x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    public static boolean isCross(Line line1, Line line2) {
        int ccw1 = ccw(line1.x1, line1.y1, line1.x2, line1.y2, line2.x1, line2.y1);
        int ccw2 = ccw(line1.x1, line1.y1, line1.x2, line1.y2, line2.x2, line2.y2);
        int ccw3 = ccw(line2.x1, line2.y1, line2.x2, line2.y2, line1.x1, line1.y1);
        int ccw4 = ccw(line2.x1, line2.y1, line2.x2, line2.y2, line1.x2, line1.y2);

        if(ccw1 * ccw2 == 0 && ccw3 * ccw4 == 0) {
            if(Math.min(line1.x1, line1.x2) <= Math.max(line2.x1, line2.x2) && Math.min(line1.y1, line1.y2) <= Math.max(line2.y1, line2.y2)
            && Math.min(line2.x1, line2.x2) <= Math.max(line1.x1, line1.x2) && Math.min(line2.y1, line2.y2) <= Math.max(line1.y1, line1.y2)) {
                return true;
            }
        }

        else if((ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0)
        || (ccw1 * ccw2 <= 0 && ccw3 * ccw4 == 0)
        || (ccw1 * ccw2 == 0 && ccw3 * ccw4 <= 0)) {
            return true;
        }
        return false;
    }

    public static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        int result = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if(result == 0) return 0;
        else if(result < 0) return -1;
        return 1;
    }
}
