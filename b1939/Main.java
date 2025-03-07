import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static List<Node>[] bridges;
    static int[] weights;
    static int start;
    static int end;

    private static class Node implements Comparable<Node> {
        private int num;
        private int w;

        Node(int num, int w) {
            this.num = num;
            this.w = w;
        }

        @Override
        public int compareTo(Node n) {
            return n.w - this.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        weights = new int[n + 1];
        bridges = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            bridges[i] = new ArrayList<>();
        }

        while(true) {
            st = new StringTokenizer(bf.readLine());
            if(st.countTokens() == 2) {
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                break;
            }

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            bridges[n1].add(new Node(n2, w));
            bridges[n2].add(new Node(n1, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, Integer.MAX_VALUE));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();
//            System.out.println(curr.num + " "+ curr.w);
            if(curr.num == end || curr.w < weights[curr.num]) continue;

            for(Node next : bridges[curr.num]) {
                if(Math.min(curr.w, next.w) > weights[next.num]) {
                    weights[next.num] = Math.min(curr.w, next.w);
                    pq.add(new Node(next.num, Math.min(curr.w, next.w)));
                }
            }
        }

        bw.write(String.valueOf(weights[end]));
        bw.close();
    }
}
