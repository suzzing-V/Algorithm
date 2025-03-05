import java.io.*;
import java.util.*;

public class Main {

    static int m;
    static int n;
    static PriorityQueue<Road> roads = new PriorityQueue<>();
    static int[] root;

    private static class Road implements Comparable<Road> {
        private int n1;
        private int n2;
        private int cost;

        Road(int n1, int n2, int cost) {
            this.n1 = n1;
            this.n2 = n2;
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

        while(true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
//            System.out.println(m + " " + n);
            if(m == 0 && n == 0) break;
            root = new int[m];
            for(int i = 0; i < m; i++) {
                root[i] = i;
            }
            roads = new PriorityQueue<>();

            int total = 0;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
//                System.out.println(n1 + " " + n2 + " " + cost);
                total += cost;
                roads.add(new Road(n1, n2, cost));
            }

            int conn = 0;
            while(!roads.isEmpty()) {
                Road curr = roads.remove();
                if(find(curr.n1) == find(curr.n2)) continue;
                conn += curr.cost;
                union(curr.n1, curr.n2);
            }

            bw.write(String.valueOf(total - conn) + "\n");
        }

        bw.close();
    }

    private static int find(int x) {
        if(root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) {
            root[b] = a;
        } else {
            root[a] = b;
        }
    }
}
