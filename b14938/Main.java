import java.io.*;
import java.util.*;

public class Main {

    static int[] items;
    static List<Node>[] roads;
    static int max = 0;
    static int n;
    static int m;
    static int r;

    public static class Node implements Comparable<Node> {
        int num;
        int distance;

        Node(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        roads = new List[n + 1];
        for(int i = 0; i < n + 1; i++) {
            roads[i] = new ArrayList<>();
        }

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            roads[start].add(new Node(end, width));
            roads[end].add(new Node(start, width));
        }

        for(int i = 1; i < n + 1; i++) {
            bfs(i);
        }

        bw.write(String.valueOf(max));
        bw.close();
    }

    public static void bfs(int startNode) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int total = items[startNode];
        boolean[] visit = new boolean[n + 1];
        visit[startNode] = true;
        for(Node node : roads[startNode]) {
            queue.add(node);
        }

        while(!queue.isEmpty()) {
            Node popNode = queue.remove();

            if(!visit[popNode.num] && popNode.distance <= m) {
                total += items[popNode.num];
                visit[popNode.num] = true;

                for(Node road : roads[popNode.num]) {
                    queue.add(new Node(road.num, popNode.distance + road.distance));
                }
            }
        }
        max = Math.max(total, max);
    }
}
