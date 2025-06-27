import java.io.*;
import java.util.*;

public class Main {

    private static int[][] chart;
    private static int n;
    private static int m;
    private static ArrayList<Node>[] conn;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        chart = new int[n + 1][n + 1];
        conn = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            conn[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i ++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            conn[n1].add(new Node(n2, w));
            conn[n2].add(new Node(n1, w));
        }

        // 각 집하장에서 시작해서 다른 집하장에 도착하는 최단 시간 구하면서 제일 먼저 거치는 집하장 저장
        // (200 + 10^4) * 200
        for(int i = 1; i <= n; i++) {
            dikstra(i);
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) System.out.print("- ");
                else System.out.print(chart[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void dikstra(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        // 가장 먼저 거쳐야 하는 노드 저장
        int[] first = new int[n + 1];

        // 다음 노드 미리 넣어놓고 저장
        for(Node node : conn[start]) {
            pq.add(new Info(node.num, node.w, node.num));
            dist[node.num] = node.w;
            first[node.num] = node.num;
        }

        while(!pq.isEmpty()) {
            Info curr = pq.remove();

//            System.out.println(curr.num + " " + curr.w);
            if(curr.w > dist[curr.num]) continue;

            for(Node next : conn[curr.num]) {
                if(dist[next.num] > next.w + dist[curr.num]) {
                    dist[next.num] = next.w + dist[curr.num];
                    first[next.num] = curr.first;
                    pq.add(new Info(next.num, curr.w + next.w, curr.first));
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            chart[start][i] = first[i];
        }
    }

    private static class Node {
        private int num;
        private int w;

        Node(int num, int w) {
            this.num = num;
            this.w = w;
        }
    }

    private static class Info implements Comparable<Info> {
        private int num;
        private int w;
        private int first;

        Info(int num, int w, int first) {
            this.num = num;
            this.w = w;
            this.first = first;
        }

        @Override
        public int compareTo(Info i) {
            return this.w - i.w;
        }
    }
}
