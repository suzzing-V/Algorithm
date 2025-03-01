import java.util.*;

class Solution {

    private int[] intensity; // 노드에 도달하기 위한 경로 중 최소 intensity
    private List<Node>[] lines;
    private boolean[] isSummit;

    private class Node implements Comparable<Node> {
        private int num;
        private int w;

        Node(int num, int w) {
            this.num = num;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        lines = new ArrayList[n + 1];
        intensity = new int[n + 1];
        isSummit = new boolean[n + 1];
        for(int i = 1;i <= n; i++) {
            lines[i] = new ArrayList<>();
        }

        for(int i = 0; i < paths.length; i++) {
            int n1 = paths[i][0];
            int n2 = paths[i][1];
            int w = paths[i][2];
            lines[n1].add(new Node(n2, w));
            lines[n2].add(new Node(n1, w));
        }
        Arrays.fill(intensity, Integer.MAX_VALUE);
        for(int i = 0; i < summits.length;i ++) {
            isSummit[summits[i]] = true;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        for(int i = 0; i < gates.length;i ++) { // 출입구들부터 시작
            queue.add(new Node(gates[i], 0));
        }

        while(!queue.isEmpty()) {
            Node curr = queue.remove();
            if(curr.w > intensity[curr.num] || isSummit[curr.num]) continue; // 산봉우리 만나면 경로 끝

            for(Node next : lines[curr.num]) {
                int newInten = Math.max(next.w, curr.w);
                if(intensity[next.num] > newInten) {
                    intensity[next.num] = newInten;
                    queue.add(new Node(next.num, newInten));
                }
            }
        }

        int s = 0;
        int minInten = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            if(isSummit[i] && intensity[i] < minInten) {
                s = i;
                minInten = intensity[i];
            }
        }
        int[] answer = new int[2];
        answer[0] = s;
        answer[1] = minInten;
        return answer;
    }
}
