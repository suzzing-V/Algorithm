import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int e;
    static ArrayList<Node>[] line;
    static int need1;
    static int need2;
    static int INF = 200000000;

    public static class Node implements Comparable<Node> {
        int pos;
        int width;
        boolean n1;
        boolean n2;

        Node(int pos, int width, boolean n1, boolean n2) {
            this.pos = pos;
            this.width = width;
            this.n1 = n1;
            this.n2 = n2;
        }

        @Override
        public int compareTo(Node other) {
            return this.width - other.width;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        line = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            line[i] = new ArrayList<Node>();
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            line[start].add(new Node(end, width, false, false));
            line[end].add(new Node(start, width, false, false));
        }

        st = new StringTokenizer(bf.readLine());
        need1 = Integer.parseInt(st.nextToken());
        need2 = Integer.parseInt(st.nextToken());

        int result1 = dijkstra(1, need1) + dijkstra(need1, need2) + dijkstra(need2, n);
        int result2 = dijkstra(1, need2) + dijkstra(need2, need1) + dijkstra(need1, n);
        bw.write(String.valueOf(Math.min(result1, result2) == INF ? -1 : Math.min(result1, result2)));
        bw.close();
    }

    public static int dijkstra(int start, int end) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(Node node : line[start]) {
            pq.add(node);
            dist[node.pos] = node.width;
        }

        while(!pq.isEmpty()) {
            Node now = pq.remove();

            for(Node node : line[now.pos]) {
                if(dist[node.pos] > dist[now.pos] + node.width) {
                    dist[node.pos] = dist[now.pos] + node.width;
                    pq.add(node);
                }
            }
        }

        return dist[end];
    }
}