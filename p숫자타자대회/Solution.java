import java.util.*;

// dfs로 하면 같은 idx끼리의 비교가 훨씬 오래 걸린다. 따라서 `dp를 사용하더라도 중복요소를 훨씬 늦게 거르므로 같은 idx끼리의 비교가 훨씬 빠른 bfs를 사용한다.
class Solution {

    private boolean[][][] dp;
    private int[][] cost = { {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
        {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
        {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
        {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
        {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
        {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
        {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
        {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
        {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
        {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}};
    private String numbers;

    public int solution(String numbers1) {
        numbers = numbers1;
        dp = new boolean[10][10][numbers.length()];
        // dp[i][j][k] : 왼손이 i, 오른손이 j에 위치할 때 idx번째 숫자로 가는 경우를 탐색했는지
        // 항상 가장 최소를 pq에서 꺼내기 때문에 처음 뽑는 게 가장 최소

        int answer = bfs(4, 6);
        return answer;
    }

    private int bfs(int sl, int sr) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(sl, sr, 0, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();

            if(curr.idx == numbers.length()) {
                return curr.sum;
            }
            if(dp[curr.left][curr.right][curr.idx]) continue; // 이미 방문했으면 탐색하지 않는다

            dp[curr.left][curr.right][curr.idx] = true; // 방문처리
            int next = numbers.charAt(curr.idx) - '0';
            // 다음에 가려는 숫자와 같은 경우 그 손가락으로 눌러야 함
            if(next == curr.left) pq.add(new Node(next, curr.right, curr.idx + 1, curr.sum + 1));
            else if(next == curr.right) pq.add(new Node(curr.left, next, curr.idx + 1, curr.sum + 1));
            else {
                // 왼손 움직
                pq.add(new Node(next, curr.right, curr.idx + 1, curr.sum + cost[curr.left][next]));
                // 오른손 움직
                pq.add(new Node(curr.left, next, curr.idx + 1, curr.sum + cost[curr.right][next]));
            }
        }
        return 0;
    }
    private class Node implements Comparable<Node> {
        private int left;
        private int right;
        private int idx;
        private int sum;

        Node(int left, int right, int idx, int sum) {
            this.left = left;
            this.right = right;
            this.idx = idx;
            this.sum = sum;
        }

        @Override
        public int compareTo(Node n) {
            return this.sum - n.sum;
        }
    }
}