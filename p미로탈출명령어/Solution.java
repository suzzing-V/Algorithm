import java.util.*;

class Solution {
    String fast = null;
    int k;
    int r;
    int c;
    int n;
    int m;
    int[][] dir = { {1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    String[] dirStr = {"d", "l", "r", "u"};

    public String solution(int n1, int m1, int x, int y, int r1, int c1, int k1) {
        k = k1;
        r = r1;
        c = c1;
        n = n1;
        m = m1;

        int dist = Math.abs(x - r) + Math.abs(y - c);
        if(dist > k) return "impossible"; // 갈 수 있는 거리가 최단 거리보다 작을 경우
        if(dist % 2 != k % 2) return "impossible"; // 왔다갔다 했을 때 불가능한 경우

        dfs(x, y, new StringBuilder(""));
        return fast;
    }

    private void dfs(int x, int y, StringBuilder sb) {
        if(fast != null) return;
        if(sb.length() == k && x == r && y == c) { // 도착지 도달
            fast = sb.toString();
            return;
        }
        if((Math.abs(r - x) + Math.abs(c - y)) > k - sb.length()) { // 잔여 거리가 현재 위치에서의 최단 거리보다 작을 경우
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx <= 0 || ny <= 0 || nx > n || ny > m) continue;
            sb.append(dirStr[i]);
            dfs(nx, ny, sb);
            if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        }
    }
}
