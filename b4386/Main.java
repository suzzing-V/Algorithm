import java.io.*;
import java.util.*;

public class Main {
    static class Line implements Comparable<Line>{
        int start;
        int end;
        double wv;

        Line(int start, int end, double wv) {
            this.start = start;
            this.end = end;
            this.wv = wv;
        }

        @Override
        public int compareTo(Line o) {
            if(this.wv - o.wv < 0) {
                return -1;
            } else if(this.wv - o.wv > 0) {
                return 1;
            }
            return 0;
        }
    }

    static class Star {
        double x;
        double y;

        Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static PriorityQueue<Line> lines = new PriorityQueue<>();
    static List<Star> stars = new ArrayList<>();
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        parents = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            stars.add(new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
        }

        for(int i = 0; i < n; i++) {
            Star s1 = stars.get(i);
            for(int j = i + 1; j < n; j++) {
                Star s2 = stars.get(j);
                lines.add(new Line(i, j, getDistance(s1, s2)));
            }
        }

        double result = 0;
        while(!lines.isEmpty()) {
            Line line = lines.remove();
            if(find(line.start) != find(line.end)) {
                result += line.wv;
                union(line.start, line.end);
            }
        }

        System.out.println(result);
    }

    private static int find(int x) {
        if(x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    private static double getDistance(Star s1, Star s2) {
        return Math.sqrt((s1.x - s2.x) * (s1.x - s2.x) + (s1.y - s2.y) * (s1.y - s2.y));
    }
}
