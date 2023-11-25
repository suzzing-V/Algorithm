import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {
        int end, w;
        public Node(int end, int w) {
            this.end = end;
            this.w = w;
        }

        @Override
        public int compareTo(Node other) {
            return this.w - other.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(bf.readLine());

        int[] dist = new int[v + 1];
        Arrays.fill(dist, 30000000);

        ArrayList<Node>[] weight = new ArrayList[v + 1];
        for(int i = 1; i < v + 1; i++) {
            weight[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            weight[start].add(new Node(end, w));
        }

        dijkstra(k, weight, dist);

        for(int i = 1; i < v + 1; i++) {
            if(dist[i] == 30000000) bw.write("INF");
            else bw.write(String.valueOf(dist[i]));
            bw.write("\n");
        }
        bw.close();
    }

    public static void dijkstra(int k, ArrayList<Node>[] weight, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(k, 0));
        dist[k] = 0;
        while(!pq.isEmpty()) {
            Node minNode = pq.poll();
            for(Node node : weight[minNode.end]) {
                if(dist[minNode.end] + node.w < dist[node.end]) {
                    dist[node.end] = dist[minNode.end] + node.w;
                    pq.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }
}
