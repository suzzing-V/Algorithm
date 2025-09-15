import java.util.*;
import java.io.*;

class Solution {

    private int[] dist;
    private ArrayList<Node>[] conn;

    public int solution(int n, int[][] edge) {
        dist = new int[n + 1];
        conn = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            conn[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < edge.length; i++) {
            conn[edge[i][0]].add(new Node(edge[i][1], 0));
            conn[edge[i][1]].add(new Node(edge[i][0], 0));
        }

        dist[1] = 0;
        dikstra();

        int max = 0;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }

        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == max) {
                cnt ++;
            }
        }

        return cnt;
    }

    private void dikstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();

            if(dist[curr.n] < curr.d) continue;

            for(Node node : conn[curr.n]) {
                if(dist[node.n] > curr.d + 1) {
                    dist[node.n] = curr.d + 1;
                    pq.add(new Node(node.n, curr.d + 1));
                }
            }
        }
    }

    private class Node implements Comparable<Node> {
        int n;
        int d;

        Node(int n, int d) {
            this.n = n;
            this.d = d;
        }

        @Override
        public int compareTo(Node n) {
            return this.d - n.d;
        }
    }
}