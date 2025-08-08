// 시간 복잡도: 17 * 17
// 각 인접노드를 방문했을 때의 상황마다 큐에 넣는다. 가능한 경우만 넣는다.

import java.util.*;

class Solution {

    private Queue<Node> queue = new LinkedList<>();
    private List<Integer>[] conn;
    private int answer = 0;

    public int solution(int[] info, int[][] edges) {
        conn = new ArrayList[info.length];
        for(int i = 0; i < conn.length; i++) {
            conn[i] = new ArrayList<>();
        }

        for(int i = 0; i < edges.length; i++) {
            conn[edges[i][0]].add(edges[i][1]);
        }

        List<Integer> init = new ArrayList<>();
        init.addAll(conn[0]);
        queue.add(new Node(1, 0, 0, init));

        while(!queue.isEmpty()) {
            Node curr = queue.remove();

            answer = Math.max(answer, curr.sheep);

            // 인접 노드들 중 갈 수 있는 노드 큐에 넣기
            for(int next : curr.near) {
                int ns = curr.sheep + (info[next] == 0? 1 : 0);
                int nw = curr.wolf + (info[next] == 1? 1: 0);

                // 다음 노드로 갈 경우 양의 개수가 늑대의 개수보다 작거나 같으면 못감
                if(ns <= nw) continue;

                List<Integer> nl = new ArrayList<>();
                nl.addAll(curr.near);
                // 현재노드는 제거
                nl.remove((Integer)next);
                // 현재 노드의 자식 노드들 넣기
                nl.addAll(conn[next]);

                queue.add(new Node(ns, nw, next, nl));
            }
        }
        return answer;
    }

    private class Node {
        int sheep; // num에서 양의 개수
        int wolf; // num에서 늑대의 개수
        List<Integer> near; // 현재 상태에서 인접 노드들
        int num;

        Node(int sheep, int wolf, int num, List<Integer> near) {
            this.sheep = sheep;
            this.wolf = wolf;
            this.num = num;
            this.near = near;
        }
    }
}