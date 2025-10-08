import java.util.*;

// 노드의 상태가 같더라도 다른 간선들의 상태는 다를 수 있기 때문에, 단순히 노드의 상태에 따라 방문을 처리하면 한번 상태가 같은 노드를 방문할 경우 다시는 그 상태의 노드를 방문하지 못한다.
// 하지만 방문한 상태의 노드라고 다시 방문할 수 없는 것이 아니다. 같은 상태의 노드라도 간선의 상태는 다를 수 있기 때문에, 전체 노드의 상태를 기준으로 방문처리를 해주어야 한다.
// 시간복잡도: 2^10 * 3000 + 2^10
// 역방향 간선과 순방향 간선을 저장하고, 연결노드와 현재노드의 상태가 같으면 순방향 간선, 다르면 역방향 간선을 선택하면서 다익스트라 돌려준다.
class Solution {

    private int n;
    private int start;
    private int end;
    private int[][] roads;
    private int[] traps;
    private List<Edge>[] edges;
    private int[][] dist;
    private int[] trap_idx;

    public int solution(int n1, int start1, int end1, int[][] roads1, int[] traps1) {
        n = n1;
        start = start1;
        end = end1;
        roads = roads1;
        traps = traps1;
        edges = new ArrayList[n + 1];
        dist = new int[n + 1][(int)Math.pow(2, 11) - 1];
        trap_idx = new int[n + 1];
        Arrays.fill(trap_idx, -1);

        // 트랩의 비트마스캉에서의 index 저장
        int idx = 0;
        for(int i = 0; i < traps.length; i++) {
            trap_idx[traps[i]] = idx ++;
        }

        for(int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
            // if(i == start) {
            //     continue;
            // }

            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 순방향 간선과 역방향 간선 정보 저장
        for(int i = 0; i < roads.length; i++) {
            int node1 = roads[i][0];
            int node2 = roads[i][1];
            int time = roads[i][2];

            edges[node1].add(new Edge(node1, node2, time, true));
            edges[node1].add(new Edge(node2, node1, time, false));
            edges[node2].add(new Edge(node2, node1, time, false));
            edges[node2].add(new Edge(node1, node2, time, true));
        }

        dikstra();

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < dist[0].length; i++) {
            min = Math.min(min, dist[end][i]);
        }
        return min;
    }

    private void dikstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {return o1.time - o2.time;});
        pq.add(new Node(start, 0, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();
            // System.out.println(curr.n + " " + curr.time + " " + curr.visited);

            // 현재 노드 상태 변화시키기. 트랩이 아니면 변화 없다.
            // 현재 노드 마킹한 정보를 XOR하면 그 부분만 1->0, 0->1된다.
            int curr_visited = trap_idx[curr.n] == -1 ? curr.visited : curr.visited^(1 << trap_idx[curr.n]);

            for(Edge edge : edges[curr.n]) {
                if(edge.s != curr.n) continue;
                // 연결노드
                int conn_n = edge.e;

                // 현재 노드가 트랩이 아닌 경우, 연결 노드와 상태가 같으면 순방향 활성화
                // 현재 노드가 트랩인 경우, 연결노드가 트랩이 아니고 현재노드와 상태가 같으면 순방향 활성화
                // 현재 노드와 연결노드가 모두 트랩인 경우, 두 노드의 상태가 같으면 순방향 활성화
                // -> 현재노드와 연결노드의 상태가 다르면 역방향, 같으면 순방향 간선 사용 가능

                int curr_status = trap_idx[curr.n] == -1 ? 0 : Integer.bitCount(curr.visited & (1 << trap_idx[curr.n]));
                int conn_status = trap_idx[conn_n] == -1 ? 0 : Integer.bitCount(curr.visited & (1 << trap_idx[conn_n]));
                // System.out.println("길: " + conn_n + " " + curr_status + " " + conn_status);
                if((curr_status != conn_status && !edge.is_forward)
                        || (curr_status == conn_status && edge.is_forward)) {
                    if(dist[conn_n][curr.visited] > curr.time + edge.time) {
                        // System.out.println("선택: " + conn_n);
                        dist[conn_n][curr.visited] = curr.time + edge.time;
                        int next_visited = trap_idx[conn_n] == -1 ? curr.visited : curr.visited^(1 << trap_idx[conn_n]);
                        pq.add(new Node(conn_n, curr.time + edge.time, next_visited));
                    }
                }
            }
        }
    }

    private class Edge {
        int s;
        int e;
        int time;
        boolean is_forward;

        Edge(int s, int e, int time, boolean is_forward) {
            this.s = s;
            this.e = e;
            this.time = time;
            this.is_forward = is_forward;
        }
    }

    private class Node {
        int n;
        int time;
        int visited;

        Node(int n, int time, int visited) {
            this.n = n;
            this.time = time;
            this.visited = visited;
        }
    }
}
