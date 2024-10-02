import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;
    static int d;
    static ArrayList<Node>[] roads;

    static class Node implements Comparable<Node>{
        int num;
        int dist;

        Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        roads = new ArrayList[d + 1];
        for(int i = 0; i < d; i++) {
            roads[i] = new ArrayList<>();
            roads[i].add(new Node(i + 1, 1));
        }
        roads[d] = new ArrayList<>();

        dp = new int[d + 1];
        for(int i = 0; i <= d; i++) {
                dp[i] = i;
        }

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(end > d) {
                continue;
            }
            int dist = Integer.parseInt(st.nextToken());
            roads[start].add(new Node(end, dist));
        }

        dijkstra(0);

        System.out.println(dp[d]);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node node = pq.remove();

            for(Node road : roads[node.num]) {
                int next = road.num;
                dp[next] = Math.min(dp[next], node.dist + road.dist);
                pq.add(new Node(next, dp[next]));
            }
        }
    }
}
