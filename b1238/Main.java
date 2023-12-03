import java.io.*;
import java.util.*;

public class Main {
    static int n, m, x;
    static ArrayList<Road>[] roads;
    static ArrayList<Road>[] reverse;
    static int INF = 10000000;

    public static class Road {
        int end;
        int dist;

        Road(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        
        roads = new ArrayList[n + 1];
        reverse = new ArrayList[n + 1];
        for(int i = 1; i < n + 1; i++) {
            roads[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        int[] dists = new int[n + 1];
        int[] reverseDists = new int[n + 1];
        Arrays.fill(dists, INF);
        Arrays.fill(reverseDists, INF);

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads[start].add(new Road(end, dist));
            reverse[end].add(new Road(start, dist));
        }

        dijkstra(dists, roads);
        dijkstra(reverseDists, reverse);

        int max = -1;
        for(int i = 1; i < n + 1; i++) {
            max = Math.max(dists[i] + reverseDists[i], max);
        }

        bw.write(String.valueOf(max));
        bw.close();
    }

    public static void dijkstra(int[] dist, ArrayList[] roads) {
        PriorityQueue<Road> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);

        pq.add(new Road(x, 0));
        dist[x] = 0;
        while(!pq.isEmpty()) {
            Road pick = pq.poll();
            List<Road> list = roads[pick.end];
            for(Road road : list) {
                if(dist[road.end] > road.dist + dist[pick.end]) {
                    dist[road.end] = road.dist + dist[pick.end];
                    pq.add(road);
                }
            }
        }
    }
}