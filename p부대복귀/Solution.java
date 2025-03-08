import java.util.*;

class Solution {

    private List<Integer>[] lines;
    private int n;
    private int[] minDist;

    private class Node {
        private int num;
        private int dist;

        private Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    public int[] solution(int n1, int[][] roads, int[] sources, int destination) {
        n = n1;
        minDist = new int[n + 1];
        Arrays.fill(minDist, -1);
        minDist[destination] = 0;
        lines = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            lines[i] = new ArrayList<>();
        }

        for(int i = 0; i < roads.length; i++) {
            lines[roads[i][0]].add(roads[i][1]);
            lines[roads[i][1]].add(roads[i][0]);
            if(roads[i][0] == destination) {
                minDist[roads[i][1]] = 1;
            }
            if(roads[i][1] == destination) {
                minDist[roads[i][0]] = 1;
            }
        }

        bfs(destination);
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            answer[i] = minDist[sources[i]];
            // System.out.println(Arrays.toString(minDist));
        }
        return answer;
    }

    private void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int curr = queue.remove();

            for(int next : lines[curr]) {
                if(visited[next]) continue;
                minDist[next] = minDist[curr] + 1;
                visited[next] = true;
                queue.add(next);
            }
        }
    }
}
