import java.util.*;

// 다익스트라 초기에 n개 넣는다고 해서 시간 복잡도가 달라지지 않는다.
// (200000 + 50000)log50000
// 어짜피 각 gate에 대한 값을 구하는 게 아닌 종합적인 값을 구하는 것이므로 모든 gate를 하나의 다익스트라로 해결 가능
class Solution {

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 산봉우리 여부 빠르게 판단 위해 map에 저장
        Map<Integer, Integer> summit = new HashMap<>();
        for(int i = 0; i < summits.length; i++) {
            summit.put(summits[i], 0);
        }

        // 출입구 여부 빠르게 판단 위해 map에 저장
        Map<Integer, Integer> gate = new HashMap<>();
        for(int i = 0; i < gates.length; i++) {
            gate.put(gates[i], 0);
        }

        ArrayList<Node>[] route = new ArrayList[n + 1];
        for(int i = 0; i < route.length; i++) {
            route[i] = new ArrayList<>();
        }
        for(int i = 0; i < paths.length; i++) {
            int n1 = paths[i][0];
            int n2 = paths[i][1];
            int w = paths[i][2];

            route[n1].add(new Node(n2, w));
            route[n2].add(new Node(n1, w));
        }

        int[] dist = new int[n + 1]; // i 노드에 오기까지 가장 작은 길
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 가장 긴 경로가 제일 짧은 것부터 꺼낸다.
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작점 넣기
        for(int i = 0; i < gates.length; i++) {
            pq.add(new Node(gates[i], 0));
        }

        while(!pq.isEmpty()) {
            Node curr = pq.remove();

            // 이미 현재노드까지의 intensity가 더 작은 경우가 있다면 멈춘다.
            // 아래에서 다 거르지 않았나라고 생각할 수 있지만 갱신되는 시점보다 이전에 넣은 탐색노드는 아직 남아있다.
            // 최적화 다익스트라에서 꼭 필요하다.
            if(dist[curr.n] < curr.w) continue;

            for(Node next : route[curr.n]) {
                // 다음 노드가 출입구면 가지 않는다. 출입구는 시작 노드에만 있어야 한다.
                if(gate.containsKey(next.n)) continue;

                int maxW = Math.max(curr.w, next.w);
                // 다음 노드까지의 가장 긴 경로보다 dist에 저장된 값이 더 크면 갱신하고 탐색 계속한다.
                if(dist[next.n] > maxW) {
                    dist[next.n] = maxW;
                    // 다음이 봉우리면 탐색 멈춘다.
                    if(!summit.containsKey(next.n)) pq.add(new Node(next.n, maxW));
                }
            }
        }

        // intensity가 가장 최소인 봉우리 찾기
        int[] answer = {0, Integer.MAX_VALUE};

        for(int i = 1; i < dist.length; i++) {
            if(summit.containsKey(i) && answer[1] > dist[i]) {
                answer[0] = i;
                answer[1] = dist[i];
            }
        }
        return answer;
    }

    private class Node implements Comparable<Node> {
        private int n;
        private int w;

        private Node(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Node node) {
            return this.w - node.w;
        }
    }
}