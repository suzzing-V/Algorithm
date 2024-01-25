import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static List<Node>[] list;
    static int INF = 987654321;

    public static class Node implements Comparable<Node> {
        int pos;
        int width;
        List<Integer> cities;

        Node(int pos, int width) {
            this.pos = pos;
            this.width = width;
            this.cities = new ArrayList<>();
        }

        @Override
        public int compareTo(Node o) {
            return this.width - o.width;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());

        list = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, width));
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
    }

    public static void dijkstra(int start, int end) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        boolean[] visit = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        List<Integer> resultList = new ArrayList<>();

        Node startNode = new Node(start, 0);
        startNode.cities.add(start);
        pq.add(startNode);

        while(!pq.isEmpty()) {
            Node now = pq.remove();
            if(visit[now.pos]) continue;
            visit[now.pos] = true;

            for(Node node : list[now.pos]) {
                if(now.width + node.width < dist[node.pos]) {
                    dist[node.pos] = now.width + node.width;
                    Node newNode = new Node(node.pos, dist[node.pos]);
                    for(int i : now.cities) {
                        newNode.cities.add(i);
                    }
                    if(node.pos == end)
                    {
                        resultList = newNode.cities;
                    }
                    pq.add(newNode);
                }
            }
        }

        System.out.println(dist[end]);
        System.out.println(resultList.size());
        for(int i : resultList) System.out.print(i + " ");
        System.out.println();
    }
}
