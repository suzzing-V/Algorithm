import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] list;
    static int max = -1;
    static int second = 0;

    public static class Node implements Comparable<Node> {
        int end;
        int dist;

        Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return other.dist - this.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int v = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        list = new ArrayList[v + 1];
        for(int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < v; i++) {
            st = new StringTokenizer(bf.readLine());
            int nodeNum = Integer.parseInt(st.nextToken());
            
            while(true) {
                int conNode = Integer.parseInt(st.nextToken());
                if(conNode == -1) break;
                int wire = Integer.parseInt(st.nextToken());
                
                list[nodeNum].add(new Node(conNode, wire));
            }
        }

        bfs(1, v);
        if(second != 1) bfs(second, v);
        bw.write(String.valueOf(max));
        bw.close();
    }

    public static void bfs(int start, int v) {
        boolean[] visit = new boolean[v + 1];
        int[] dist = new int[v + 1];
        Arrays.fill(visit, false);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        
        queue.add(new Node(start, 0));
        visit[start] = true;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            for(Node node : list[now.end]) {
                if(!visit[node.end] && dist[now.end] + node.dist > dist[node.end]) {
                    dist[node.end] = dist[now.end] + node.dist;
                    queue.add(node);
                    visit[node.end] = true;
                }
            }
        }

        for(int i = 0; i < dist.length; i++) {
            if(dist[i] > max) {
                max = dist[i];
                second = i;
            }
        }
    }
}