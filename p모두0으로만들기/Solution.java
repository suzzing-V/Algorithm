import java.util.*;

class Solution {

    private long[] result;
    private List<Integer>[] lines;
    private boolean[] visited;
    long answer = 0;

    public long solution(int[] a, int[][] edges) {
        result = new long[a.length];
        lines = new ArrayList[a.length];
        visited = new boolean[a.length];
        for(int i = 0; i < a.length; i++) {
            lines[i] = new ArrayList<>();
        }

        for(int i = 0; i < edges.length; i++) {
            int n1 = edges[i][0];
            int n2 = edges[i][1];

            lines[n1].add(n2);
            lines[n2].add(n1);
        }

        for(int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }

        dfs(0);
        // System.out.println(Arrays.toString(result));
        if(result[0] != 0) answer = -1;
        return answer;
    }

    private void dfs(int curr) {
        if(lines[curr].size() == 1 && curr != 0) { // 말단노드일 경우 멈춤
            return;
        }

        visited[curr] = true;
        for(int i = 0; i < lines[curr].size(); i++) {
            int child = lines[curr].get(i);
            if(visited[child]) continue;
            dfs(child);
            answer += Math.abs(result[child]); // 자식노드의 결과값만큼 변화를 줘야하므로
            result[curr] += result[child]; // 자식노드 0 만든 만큼 더해주기
            result[child] -= result[child];
        }
    }
}
