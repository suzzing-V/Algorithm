import java.util.*;

class Solution {

    private int n;
    private int m;
    private char[][] map;
    private int k;
    private int r;
    private int c;
    private int[][] dir = { {1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private String[] dirStr = {"d", "l", "r", "u"};

    public String solution(int n1, int m1, int x, int y, int r1, int c1, int k1) {
        map = new char[n1 + 1][m1 + 1];
        n = n1;
        m = m1;
        k = k1;
        r = r1;
        c = c1;

        // 남은 거리가 도착지까지 도착하기 위한 최소 거리보다 적으면 안됨
        if(distance(x, y, r, c) > k) return "impossible";
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(i == x && j == y) map[i][j] = 'S';
                else if(i == r && j == c) map[i][j] = 'E';
                else map[i][j] = '.';
            }
        }

        return bfs(x, y);
    }

    private String bfs(int sx, int sy) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(sx, sy, new StringBuilder()));

        while(!pq.isEmpty()) {
            Node curr = pq.remove();

            // System.out.println(curr.x + " " + curr.y + " " + curr.route);
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];
                if(nx <= 0 || nx > n || ny <= 0 || ny > m || curr.route.toString().length() == k) continue;
                if(distance(nx, ny, r, c) > k - curr.route.length() - 1) continue;
                if(map[nx][ny] == 'E' && curr.route.length() + 1 == k) return curr.route.append(dirStr[i]).toString();
                StringBuilder sb = new StringBuilder(curr.route.toString());
                pq.add(new Node(nx, ny, sb.append(dirStr[i])));
                break;
            }
        }

        return "impossible";
    }

    private int distance(int sx, int sy, int ex, int ey) {
        return Math.abs(sx - ex) + Math.abs(sy - ey);
    }

    private class Node implements Comparable<Node> {
        private int x;
        private int y;
        private StringBuilder route;

        Node(int x, int y, StringBuilder route) {
            this.x = x;
            this.y = y;
            this.route = route;
        }

        @Override
        public int compareTo(Node n) {
            if(this.route.length() != n.route.length()) {
                return n.route.length() - this.route.length();
            }
            return this.route.toString().compareTo(n.route.toString());
        }
    }
}