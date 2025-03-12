import java.util.*;

// 현재 노드에서 갈 수 있는 노드는 자식노드와 이전노드의 인접한 노드들. 탐색하다가 멈추고 다른 거 보기.
class Solution {

    private List<Integer>[] conn;
    private Queue<Node> queue = new LinkedList<>();
    private int max = 0;
    private int[] info;

    private class Node {
        private int curr;
        private int sheep;
        private int wolf;
        private List<Integer> next;

        private Node(int curr, int sheep, int wolf, List<Integer> next) {
            this.curr = curr;
            this.sheep = sheep;
            this.wolf = wolf;
            this.next = next;
        }
    }


    public int solution(int[] info1, int[][] edges) {
        info = info1;
        conn = new ArrayList[info.length];
        for(int i = 0; i < conn.length; i++) {
            conn[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++) { // 노드 관계 저장
            conn[edges[i][0]].add(edges[i][1]);
        }

        List<Integer> initNext = new ArrayList<>();
        for(int son : conn[0]) {
            initNext.add(son);
        }

        queue.add(new Node(0, 1, 0, initNext));
        while(!queue.isEmpty()) {
            Node curr = queue.remove();
            if(curr.sheep == curr.wolf) continue; // 늑대와 양 수가 같으면 더 탐색 불가

            max = Math.max(max, curr.sheep); // 최대값 갱신
            for(int next : curr.next) { // 인접 리스트 돌면서 다음 거 탐색.
                List<Integer> nextList = new ArrayList<>();
                for(int i : conn[next]) { // 자식 노드 추가
                    nextList.add(i);
                }
                for(int i : curr.next) { // 현재 노드에 인접한 노드들 추가
                    if(next == i) continue;
                    nextList.add(i);
                }

                if(info[next] == 0) queue.add(new Node(next, curr.sheep + 1, curr.wolf, nextList));
                else queue.add(new Node(next, curr.sheep, curr.wolf + 1, nextList));
            }
        }
        return max;
    }
}
