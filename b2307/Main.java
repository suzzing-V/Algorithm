import java.util.*;
import java.io.*;


// 모든 간선을 제외하면 5000 * 6000 * log(10^5) 로 시간초과 가능성
// 모든 간선이 아닌 도둑이 갈 수 있는 최단 거리에 놓인 간선을 제외한다.

public class Main {

    private static List<Edge>[] conn;
    private static int n;
    private static int m;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        conn = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            conn[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            conn[n1].add(new Edge(n1, n2, t));
            conn[n2].add(new Edge(n2, n1, t));
        }

        // 간선 제외 안했을 떄 최솟값
        int[][] origin = dikstra(new Edge(-1, -1, 0));
        // 최단 거리에 있는 간선 저장
        List<Edge> excepts = new ArrayList<>();
        int curr = n;
        while(true) {
            excepts.add(new Edge(curr, origin[curr][1], 0));
            curr = origin[curr][1];
            if(curr == 1) break;
            if(curr == Integer.MAX_VALUE) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        // 간선 하나씩 제외하면서 dikstra
        for(Edge edge : excepts) {
            int[][] dist = dikstra(edge);
            max = Math.max(max, dist[n][0]);
        }

        if(max == Integer.MAX_VALUE) {
            max = -1;
        } else {
            max -= origin[n][0];
        }

        System.out.println(max);
    }

    private static int[][] dikstra(Edge except) {
//        System.out.println(except + " 제외");
        int[][] dist = new int[n + 1][2];
        for(int i = 1; i <= n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[1][0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();
//            System.out.println(curr.n + " " + curr.t);
            if(curr.n == n) break;

            if(dist[curr.n][0] < curr.t) {
                continue;
            }

            for(Edge next : conn[curr.n]) {
                if((except.n1 == next.n1 && except.n2 == next.n2) || (except.n1 == next.n2 && except.n2 == next.n1) ) continue;

                if(dist[next.n2][0] > dist[curr.n][0] + next.t) {
                    dist[next.n2][0] = dist[curr.n][0] + next.t;
                    dist[next.n2][1] = curr.n;
                    pq.add(new Node(next.n2, dist[next.n2][0]));
                }
            }
        }

//        for(int i = 1; i <= n; i++) System.out.println(Arrays.toString(dist[i]));
        return dist;
    }

    private static class Node implements Comparable<Node> {
        int n;
        int t;

        Node(int n, int t) {
            this.n = n;
            this.t = t;
        }

        @Override
        public int compareTo(Node n) {
            return this.t - n.t;
        }
    }

    private static class Edge {
        int n1;
        int n2;
        int t;

        Edge(int n1, int n2, int t) {
            this.n1 = n1;
            this.n2 = n2;
            this.t = t;
        }
    }
}
