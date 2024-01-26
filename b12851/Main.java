import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int INF = 100000;

    public static class Node implements Comparable<Node> {
        int pos;
        int width;

        Node(int pos, int width) {
            this.pos = pos;
            this.width = width;
        }

        @Override
        public int compareTo(Node o) {
            return this.width - o.width;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs();
    }

    public static void bfs() {
        boolean[] visit = new boolean[INF + 1];
        int count = 0;
        Queue<Node> pq = new LinkedList<>();
        int min = INF;
        pq.add(new Node(n, 0));

        while(!pq.isEmpty()) {
            Node now = pq.remove();
            visit[now.pos] = true;

            if(now.pos == k) {
                if(now.width < min) {
                    min = now.width;
                    count = 1;
                } else if(now.width == min) {
                    count++;
                }
            }
            if(now.pos - 1 >= 0 && !visit[now.pos - 1]) pq.add(new Node(now.pos - 1, now.width + 1));
            if(now.pos + 1 <= INF && !visit[now.pos + 1]) pq.add(new Node(now.pos + 1, now.width + 1));
            if(now.pos * 2 <= INF && !visit[now.pos * 2]) pq.add(new Node(now.pos * 2, now.width + 1));
        }
        System.out.println(min);
        System.out.println(count);
    }
}