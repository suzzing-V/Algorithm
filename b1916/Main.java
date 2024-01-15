import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] bus;
    static int INF = 987654321;
    static int n;

    public static class Node implements Comparable<Node> {
        int pos;
        int width;

        Node(int pos, int width) {
            this.pos = pos;
            this.width = width;
        }

        @Override
        public int compareTo(Node other) {
            return this.width - other.width;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());

        bus = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            bus[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            bus[start].add(new Node(end, width));
        }

        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(dijkstra(start, end)));
        bw.close();
    }

    public static int dijkstra(int startNode, int endNode) {
        int[] dist = new int[n + 1];
        boolean[] visit = new boolean[n + 1];
        Arrays.fill(dist, INF);
        dist[startNode] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode, 0));

        while(!pq.isEmpty()) {
            Node now = pq.remove();

            if(visit[now.pos]) continue;

            visit[now.pos] = true;
            for(Node node : bus[now.pos]) {
                if(dist[node.pos] > dist[now.pos] + node.width) {
                    dist[node.pos] = dist[now.pos] + node.width;
                    pq.add(new Node(node.pos, dist[node.pos]));
                }
            }
        }

        return dist[endNode];
    }
}