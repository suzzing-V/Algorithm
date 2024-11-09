import java.io.*;
import java.util.*;

public class Main {

    static class Line implements Comparable<Line>{
        int start;
        int end;
        int wv;

        Line(int start, int end, int wv) {
            this.start = start;
            this.end = end;
            this.wv = wv;
        }

        @Override
        public int compareTo(Line o) {
            return this.wv - o.wv;
        }
    }

    static PriorityQueue<Line> lines = new PriorityQueue<>();
    static int n;
    static int m;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());
        parent = new int[n + 1];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            lines.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int result = 0;
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
        if(x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
