import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Line>[] lines;
    static int n;
    static int max = 0;

    public static class Line {
        int start;
        int end;
        int weight;

        Line(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());
        
        lines = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            lines[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            lines[start].add(new Line(start, end, weight));
            lines[end].add(new Line(end, start, weight));
        }

        for(int i = 1; i < n + 1; i++) {
            boolean[] visit = new boolean[n + 1];
            visit[i] = true;
            dfs(i, visit, 0);
        }
        bw.write(String.valueOf(max));
        bw.close();
    }

    public static void dfs(int startNode, boolean[] visit, int total) {
        max = Math.max(total, max);
        for(Line line : lines[startNode]) {
            if(visit[line.end]) continue;
            visit[line.end] = true;
            dfs(line.end, visit, total + line.weight);
            visit[line.end] = false;
        }
    }
}