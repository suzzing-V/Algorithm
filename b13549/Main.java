import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int INF = 100000;
    static int min = 100000;

    public static class Node {
        int pos;
        int time;

        Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        bfs();

        bw.write(String.valueOf(min));
        bw.close();
    }

    public static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(n, 0));
        boolean[] visit = new boolean[100001];

        while(!queue.isEmpty()) {
            Node now = queue.remove();
            visit[now.pos] = true;
            if(now.pos == k) min = Math.min(min, now.time);

            if(now.pos * 2 <= INF && !visit[now.pos * 2]) queue.add(new Node(now.pos * 2, now.time));
            if(now.pos + 1 <= INF && !visit[now.pos + 1]) queue.add(new Node(now.pos + 1, now.time + 1));
            if(now.pos - 1 >= 0 && !visit[now.pos - 1]) queue.add(new Node(now.pos - 1, now.time + 1));
        }
    }
}