import java.util.*;

class Solution {

    private int[] dist;
    private List<Integer>[] lines;

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

    public int solution(int n, int[][] edge) {
        dist = new int[n + 1];
        lines = new ArrayList[n + 1];
        Arrays.fill(dist, 30000);
        for(int i = 0; i <= n; i++) {
            lines[i] = new ArrayList<>();
        }

        for(int i = 0; i < edge.length; i++) {
            lines[edge[i][0]].add(edge[i][1]);
            lines[edge[i][1]].add(edge[i][0]);
        }

        dikstra();

        int max = 0;
        int cnt = 0;
        for(int i = 2; i <= n; i++) {
            if(max < dist[i]) {
                max = dist[i];
                cnt = 1;
            } else if(max == dist[i]) {
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

            if(dist[curr.num] < curr.d) continue;
            for(int i = 0; i < lines[curr.num].size(); i++) {
                int next = lines[curr.num].get(i);
                if(dist[next] > curr.d + 1) {
                    dist[next] = curr.d + 1;
                    pq.add(new Node(next, curr.d + 1));
                }
            }
        }
    }
}
