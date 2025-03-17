import java.util.*;

class Solution {

    private Map<Integer, List<Pos>> poses = new HashMap<>();
    private boolean[] visited;
    private int min = Integer.MAX_VALUE;
    private int n;
    private int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private int[][] board;

    private class Pos {
        private int x;
        private int y;

        private Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int cost;

        private Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    public int solution(int[][] board1, int r, int c) {
        board = board1;
        visited = new boolean[7];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != 0) {
                    if(r == i && c == j) continue;
                    if(poses.get(board[i][j]) == null) {
                        poses.put(board[i][j], new ArrayList<>());
                    }
                    poses.get(board[i][j]).add(new Pos(i, j));
                }
            }
        }
        n = poses.size();
        int enter = n * 2; // enter 수는 카드 종류 * 2로 일정하다. 거리계산만 하기 위함.

        // 시작점에 카드가 있을 경우 처리
        int init = 0;
        if(board[r][c] != 0) {
            Pos p = poses.get(board[r][c]).get(0);
            init = bfs(new Pos(r, c), new Pos(p.x, p.y));
            r = p.x;
            c = p.y;
            visited[board[r][c]] = true;
            poses.remove(board[r][c]);
            n --;
        }

        // 카드 뒤집는 순서 dfs
        dfs(r, c, 0, n);
        return min + init + enter;
    }

    private void dfs(int x, int y, int cnt, int rest) {
        if(rest == 0) {
            // System.out.println("end: " + cnt);
            min = Math.min(cnt, min);
            return;
        }

        // System.out.println(board[x][y] + " " + cnt + " " + rest);
        for(int key : poses.keySet()) {
            if(visited[key]) continue; // 이미 뒤집은 카드
            // System.out.println(key);
            Pos p1 = poses.get(key).get(0);
            Pos p2 = poses.get(key).get(1);
            visited[key] = true;
            // 해당 카드의 두 위치로 갈 경우를 모두 생각한다.
            // 현재 위치 -> p1 -> p2일 경우
            dfs(p2.x, p2.y, cnt + bfs(new Pos(x, y), new Pos(p1.x, p1.y)) + bfs(new Pos(p1.x, p1.y), new Pos(p2.x, p2.y)), rest - 1);
            // 현재 위치 -> p2 -> p1일 경우
            dfs(p1.x, p1.y, cnt + bfs(new Pos(x, y), new Pos(p2.x, p2.y)) + bfs(new Pos(p2.x, p2.y), new Pos(p1.x, p1.y)), rest - 1);
            visited[key] = false;
        }
    }

    private int bfs(Pos start, Pos end) {
        boolean[][] v = new boolean[4][4];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start.x, start.y, 0));

        while(!queue.isEmpty()) {
            Node curr = queue.remove();
            // System.out.println(curr.x + " " +curr.y);
            if(curr.x == end.x && curr.y == end.y) {
                // System.out.println(start.x + " " + start.y + " " + end.x + " " + end.y + " " + curr.cost);
                return curr.cost;
            }

            for(int i = 0; i < 4; i++) {
                // 방향키 + ctrl
                Pos nxt = ctrl(curr.x, curr.y, i, end);
                v[nxt.x][nxt.y] = true;
                // System.out.println("ctrl: " + nxt.x + " " + nxt.y + " " + curr.cost);
                queue.add(new Node(nxt.x, nxt.y, curr.cost + 1));

                // 방향키
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];
                if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || v[nx][ny]) continue;

                v[nx][ny] = true;
                // System.out.println(nx + " " + ny + " " + curr.cost);
                queue.add(new Node(nx, ny, curr.cost + 1));
            }
        }

        return 0;
    }

    private Pos ctrl(int x, int y, int d, Pos target) {
        int cnt = 0;
        while(true) {
            x += dir[d][0];
            y += dir[d][1];
            if(x < 0) return new Pos(0, y);
            if(x >= 4) return new Pos(3, y);
            if(y < 0) return new Pos(x, 0);
            if(y >= 4) return new Pos(x, 3);
            // System.out.println(x + " " + y + " " + board[x][y]);
            if(board[x][y] != 0 && !visited[board[x][y]]) break; // 방문하지 않은 다른 카드 만날 경우 멈춤
            if(x == target.x && y == target.y) break; // 현재 도달하고자 하는 값이면 멈춤. 같은 카드끼리의 거리를 계산할 때 이 조건이 없다면 위에서 해당 카드에 방문 처리를 했기 때문에 해당 카드를 만나도 카드를 만났다고 처리하지 않음.
        }
        return new Pos(x, y);
    }
}
