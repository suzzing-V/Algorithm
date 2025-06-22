import java.util.*;

class Solution {

    // 사전 순으로 해당 차례에 어디 방향으로 갈지 결정한다. 사전 순이기 때문에 현재 탐색하고 있는 방향으로 도착지까지 갈 수 있다면 무조건 이 방향을 선택하는 게 맞다.
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        if(distance(x, y, r, c) > k) return "impossible";
        StringBuilder sb = new StringBuilder();
        for(int i = k - 1; i >= 0; i --) {
            if(x + 1 <= n && distance(x + 1, y, r, c) <= i) {
                x ++;
                sb.append("d");
            } else if(y - 1 > 0 && distance(x, y - 1, r, c) <= i) {
                y --;
                sb.append("l");
            } else if(y + 1 <= m && distance(x, y + 1, r, c) <= i) {
                y ++;
                sb.append("r");
            } else if(x - 1 > 0 && distance(x - 1, y, r, c) <= i) {
                x --;
                sb.append("u");
            } else if(i == 0) { // 남은 거리가 없는데 갈 방향도 없다면 불가능
                return "impossible";
            }
        }

        return sb.toString();
    }

    private int distance(int sx, int sy, int ex, int ey) {
        return Math.abs(sx - ex) + Math.abs(sy - ey);
    }
}