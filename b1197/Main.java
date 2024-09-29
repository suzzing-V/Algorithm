import java.io.*;
import java.util.*;

public class Main {

    static int v;
    static int e;
    static int[] parent;
    static ArrayList<Line> lines = new ArrayList<>();

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
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        parent = new int[v + 1];
        for(int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int wv = Integer.parseInt(st.nextToken());
            lines.add(new Line(start, end, wv));
        }
        Collections.sort(lines);

        int result = 0;
        for(Line line : lines) {
            int start = line.start;
            int end = line.end;
            if(find(start) != find(end)) {
                result += line.wv;
                union(start, end);
            }
        }

        System.out.println(result);
    }

    static int find(int x) {
        if(parent[x] == x) {
            return x;
        }

        parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
