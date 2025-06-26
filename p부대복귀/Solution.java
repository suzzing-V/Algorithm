import java.util.*;
import java.io.*;

class Solution {

    private ArrayList<Integer>[] conn;
    private int n;
    private int destination;
    private int[] dist;

    public int[] solution(int n1, int[][] roads, int[] sources, int destination1) {
        n = n1;
        destination = destination1;
        conn = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            conn[i] = new ArrayList<>();
        }

        for(int i = 0; i < roads.length; i++) {
            int r1 = roads[i][0];
            int r2 = roads[i][1];
            conn[r1].add(r2);
            conn[r2].add(r1);
        }

        dikstra(destination);
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            if(dist[sources[i]] == 10000000) answer[i] = -1;
            else answer[i] = dist[sources[i]];
        }

        return answer;
    }

    private void dikstra(int start) {
        // System.out.println(start);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist = new int[n + 1];
        Arrays.fill(dist, 10000000);
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node curr = pq.remove();
            // System.out.println(curr.num + " " + curr.d);
            if(dist[curr.num] < curr.d) continue;

            for(int next : conn[curr.num]) {
                if(curr.d + 1 < dist[next]) {
                    dist[next] = curr.d + 1;
                    pq.add(new Node(next, curr.d + 1));
                }
            }
        }
    }

    private class Node implements Comparable<Node> {
        private int num;
        private int d;

        private Node(int num, int d) {
            this.num = num;
            this.d = d;
        }

        @Override
        public int compareTo(Node n) {
            return this.d - n.d;
        }
    }
}