import java.util.*;
import java.io.*;

// 모든 도시에서 면접장으로 가는 루트가 무조건 하나는 있다. 면접장을 기준으로 도시들까지의 최소 거리를 갱신한다.
// 면접장인 도시의 최소거리는 0으로 저장하고 다익스트라를 돌린다. 주어진 간선들은 면접자가 면접장에 가는 방향의 화살표이므로, 면접장 기준으로 탐색하려면 간선의 방향을 반대로 저장해줘야 한다.
// 시간복잡도: (100000 + 500000) 5log10 + 100000
public class Main {

    private static List<Edge>[] conn;
    private static int n;
    private static int m;
    private static int k;
    private static long[] dist;
//    private static Map<Integer, Integer> iv = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dist = new long[n + 1];
        conn = new ArrayList[n + 1];
        for(int i = 0; i <= n ;i++) {
            conn[i] = new ArrayList<>();
            dist[i] = Long.MAX_VALUE;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            long d = Integer.parseInt(st.nextToken());

            conn[n2].add(new Edge(n1, d));
        }

        st = new StringTokenizer(bf.readLine());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0; i < k ;i ++) {
            int v = Integer.parseInt(st.nextToken());
//            iv.put(v, 0);
            pq.add(new Edge(v, 0));
            dist[v] = 0;
        }

        while(!pq.isEmpty()) {
            Edge curr = pq.remove();
//            System.out.println(curr.n + " " + curr.d);

            if(dist[curr.n] < curr.d) continue;

            for(Edge edge : conn[curr.n]) {
                int next = edge.n;

                if(dist[next] > curr.d + edge.d) {
                    dist[next] = curr.d + edge.d;
                    pq.add(new Edge(next, dist[next]));
                }
            }
        }

        int max = -1;
        long maxd = -1;
        for(int i = 1; i <= n; i++) {
            if(maxd < dist[i]) {
                maxd = dist[i];
                max = i;
            }
        }

        System.out.println(max);
        System.out.println(maxd);
    }

    private static class Edge implements Comparable<Edge> {
        int n;
        long d;

        Edge(int n, long d) {
            this.n = n;
            this.d = d;
        }

        @Override
        public int compareTo(Edge e) {
            if(this.d < e.d) return -1;
            else if(this.d == e.d) return 0;
            return 1;
        }
    }
}
