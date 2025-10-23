import java.util.*;

// 시간복잡도: 200000 + 200000
// 틀린 이유: 무조건 0번 방에서 시작한다고 생각했다. 하지만 문제에 그런 조건은 없었고, 따라서 0번 방에도 선행 방이 있을 수 있었다.
// 0번 방에 선행 노드가 있다면, 무슨 일이 있어도 이 노드는 방문을 해야한다. 0번 방은 모든 노드와 연결돼있고, 어떤 경우에도 0번 방은 방문해야 나머지 방을 모두 방문할 수 있다.
// 따라서 0번 방에 선행 노드가 있다면, bfs의 시작을 0이 아닌 그 선행노드로 해주었다.
// 위상정렬을 떠올렸지만, 위상정렬 없이도 풀 수 있는 문제였다.
// 시작 노드부터 bfs 탐색을 하면서, 만약 선행 노드가 없으면 그냥 탐색하고, 있는데 이미 방문했으면 그냥 탐색하고, 있는데 방문 안했으면 방문을 보류하고 map에 저장한다.
// 만약에 다음 노드가 누군가의 선행 노드라면, 그 후행 노드가 보류된 노드인지 확인한다. 보류된 노드라면 다음 노드를 방문하면서 방문할 수 있게 되는 것이므로, map에서 지우고 방문한다.

class Solution {
    private Map<Integer, Integer> ungo = new HashMap<>(); // 선행 정점이 방문되지 않아서 방문 보류한 정점
    private boolean[] visited;
    private List<Integer>[] conn;
    private int[] pre;
    private int[] nxt;

    public boolean solution(int n, int[][] path, int[][] order) {
        visited = new boolean[n];
        conn = new ArrayList[n];
        pre = new int[n];
        nxt = new int[n];

        // 연결 정보 저장
        Arrays.fill(pre, -1);
        Arrays.fill(nxt, -1);
        for(int i = 0; i < n; i++) {
            conn[i] = new ArrayList<>();
        }
        for(int i = 0; i < path.length; i++) {
            int n1 = path[i][0];
            int n2 = path[i][1];
            conn[n1].add(n2);
            conn[n2].add(n1);
        }

        // 선행 정보 저장
        for(int i = 0; i < order.length; i++) {
            int p = order[i][0];
            int nx = order[i][1];
            pre[nx] = p;
            nxt[p] = nx;
        }

        bfs();

        boolean answer = true;
        // 방문 안한 노드가 있으면 false하고 멈춤
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                answer = false;
                break;
            }
        }
        return answer;
    }

    // 두 정점 사이의 최단 경로가 딱 한 가지만 있으므로, 사이클이 없다는 말이다. 사이클이 있을 경우 반대방향과 정방향 두 가지의 경로가 존재하게 된다. 따라서 트리이다.
    // 트리이므로 한 번 방문하고 굳이 또 방문해서 못 가던 곳을 갈 수 있게 되지는 않는다. 따라서 한 번만 방문하면 된다.
    private void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        if(pre[0] != -1) {
            queue.add(0);
            visited[pre[0]] = true;
        } else {
            queue.add(0);
            visited[0] = true;
        }

        while(!queue.isEmpty()) {
            int curr = queue.remove();

            for(int next : conn[curr]) {
                // 다음 노드 이미 방문했으면 넘어간다.
                if(visited[next]) continue;

                // 다음 노드의 선행 노드가 없거나, 선행 노드가 있는데 선행노드를 이미 방문한 경우 방문한다.
                visited[next] = true;
                if(pre[next] == -1 || (pre[next] != -1 && visited[pre[next]])) {
                    queue.add(next);
                }
                // 다음 노드가 누군가의 선행 노드이고 보류 상태면 그 노드를 방문한다.
                if(nxt[next] != -1 && ungo.containsKey(nxt[next])) {
                    queue.add(nxt[next]);
                    ungo.remove(nxt[next]);
                }
                // 선행 노드가 있는데 방문 안했을 경우 ungo map에 넣는다. 방문을 보류하고 만약 그 선행노드에 방문하면 그때 방문한다.
                if(pre[next] != -1 && !visited[pre[next]]) {
                    ungo.put(next, 0);
                }
            }
        }
    }
}
