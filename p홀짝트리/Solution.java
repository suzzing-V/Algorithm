import java.util.*;

// 루트가 없다고 가정할 때의 노드 상태와 루트가 있을 경우 노드 상태는 루트 노드 빼고 나머지 노드는 다 역으로 반전된다.
// 따라서 그렇게 했을 때 모든 노드가 역 아니면 역x 상태여야 하므로 루트가 없는 상태에서 한 노드만 역인 경우에는 역홀짝트리, 한 노드만 역이 아닌 경우는 홀짝트리가 될 수 있다.
class Solution {

    private Map<Integer, Tree> tree_infos = new HashMap<>();
    private Map<Integer, Integer> groups = new HashMap<>();
    private Map<Integer, Integer> line_cnt = new HashMap<>();

    private class Tree {
        int foward;
        int reverse;

        Tree() {
            this.foward = 0;
            this.reverse = 0;
        }
    }

    public int[] solution(int[] nodes, int[][] edges) {
        // 각 노드 그룹 초기화
        for(int i = 0; i < nodes.length; i++) {
            groups.put(nodes[i], nodes[i]);
            line_cnt.put(nodes[i], 0);
        }

        // 트리 만들기 + 간선 카운트
        for(int i = 0; i < edges.length; i++) {
            int n1 = edges[i][0];
            int n2 = edges[i][1];

            union(n1, n2);
            // System.out.println(n1 + ": " + find(n1) + " " + n2 + ": " + find(n2));
            line_cnt.put(n1, line_cnt.getOrDefault(n1, 0) + 1);
            line_cnt.put(n2, line_cnt.getOrDefault(n2, 0) + 1);
        }

//         // 트리의 역홀/역짝/짝/홀 노드 정보 담기
        for(int node : groups.keySet()) {
            int group = find(node);
            // System.out.println(group + " " + node);
            if(!tree_infos.containsKey(group)) {
                tree_infos.put(group, new Tree());
            }

            int line = line_cnt.get(node);
            Tree tree = tree_infos.get(group);
            if((isOdd(node) && isOdd(line)) || (isEven(node) && isEven(line))) tree.foward ++;
            if((isOdd(node) && isEven(line)) || (isEven(node) && isOdd(line))) tree.reverse ++;
        }

        int[] answer = new int[2];
        // 하나만 짝/홀이고 나머지 다 역인 / 하나만 역이고 나머지 다 짝/홀인 트리 찾기
        for(int group : tree_infos.keySet()) {
            Tree tree = tree_infos.get(group);
            // System.out.println(tree.reverse + " " + tree.foward);
            // 역홀짝트리
            if(tree.reverse == 1) answer[1] ++;
            if(tree.foward == 1) answer[0] ++;
        }

        return answer;
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) groups.put(b, a);
    }

    private int find(int x) {
        if(groups.get(x) == x) {
            return x;
        }

        // map.put는 새로운 값을 집어넣지만 이전 값을 반환한다.
        groups.put(x, find(groups.get(x)));
        return groups.get(x);
    }

    private boolean isOdd(int n) {
        return n % 2 != 0;
    }

    private boolean isEven(int n) {
        return n % 2 == 0;
    }
}
