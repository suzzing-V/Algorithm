import java.io.*;
import java.util.*;

public class Main {

    static List<Road> roads = new ArrayList<>();
    static int n;
    static int m;
    static int[] root = new int[n + 1];

    static class Road implements Comparable<Road>  {
        int start;
        int end;
        int cost;

        Road(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n + 1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads.add(new Road(start, end, cost));
        }

        Collections.sort(roads);

        bw.write(String.valueOf(kruskal()));
        bw.close();
    }

    public static int kruskal() {
        int result = 0;

        for(int i = 0; i <= n; i++) {
            root[i] = i;
        }

        int count = 0;
        for(Road road : roads) {
            int startRoot = find(road.start);
            int endRoot = find(road.end);
            if(startRoot != endRoot) {
                if(count == n - 2) {
                    break;
                }
                result += road.cost;
                count++;
                union(startRoot, endRoot);
            }
        }

        return result;
    }

    public static int find(int x) {
        if(x == root[x]) {
            return x;
        }

        return find(root[x]);
    }

    public static void union(int rootA, int rootB) {
        if(rootA < rootB) root[rootB] = rootA;
        else root[rootA] = rootB;
    }
}
