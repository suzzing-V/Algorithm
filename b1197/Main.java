import java.io.*;
import java.util.*;

public class Main {

    static int v;
    static int e;
    static ArrayList<Line> lines;

    public static class Line implements Comparable<Line>{
        int start;
        int end;
        int width;

        Line(int start, int end, int width) {
            this.start = start;
            this.end = end;
            this.width = width;
        }

        @Override
        public int compareTo(Line o) {
            return this.width - o.width;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        lines = new ArrayList<>();
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            lines.add(new Line(start, end, width));
        }

        Collections.sort(lines);

        bw.write(String.valueOf(kruskal()));
        bw.close();
    }

    public static int kruskal() {
        int[] parent = new int[v + 1];
        int result = 0;

        for(int i = 1; i < v + 1; i++) {
            parent[i] = i;
        }

        for(Line line : lines) {
            int parent1 = findParent(parent, line.start);
            int parent2 = findParent(parent, line.end);
            if(parent1 != parent2) {
                parent[parent2] = parent1;
                result += line.width;
            }
        }

        return result;
    }

    public static int findParent(int[] parent, int x) {
        if(parent[x] == x) {
            return x;
        }
        else {
            return findParent(parent, parent[x]);
        }
    }
}
