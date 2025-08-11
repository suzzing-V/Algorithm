import java.util.*;

// 쿼리를 거꾸로 돌면서 현재 범위 상태에 도달할 수 있는 이전 범위 상태를 구한다. 이전 상태에서 현재 상태로 밀리는 방향으로 더이상 밀 수 없을 때는 이전 범위가 현재 범위에서 늘어난다. 밀 수 있을 때는 그냥 범위를 이동만 시킨다.
// 시간 복잡도: O(n)
class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        int x1 = x;
        int x2 = x;
        int y1 = y;
        int y2 = y;

        for(int i = queries.length - 1; i >= 0; i--) {
            int type = queries[i][0];
            int dx = queries[i][1];

            if(type == 0 && y1 == 0) {
                y2 = Math.min(m - 1, y2 + dx);
            } else if(type == 1 && y2 == m - 1) {
                y1 = Math.max(0, y1 - dx);
            } else if(type == 2 && x1 == 0) {
                x2 = Math.min(n - 1, x2 + dx);
            } else if(type == 3 && x2 == n - 1) {
                x1 = Math.max(0, x1 - dx);
            } else if(type == 0) {
                if(y1 + dx >= m) return 0;
                y1 = y1 + dx;
                y2 = Math.min(y2 + dx, m - 1);
            } else if(type == 1) {
                if(y2 - dx < 0) return 0;
                y2 = y2 - dx;
                y1 = Math.max(0, y1 - dx);
            } else if(type == 2) {
                if(x1 + dx >= n) return 0;
                x1 = x1 + dx;
                x2 = Math.min(x2 + dx, n - 1);
            } else if(type == 3) {
                if(x2 - dx < 0) return 0;
                x2 = x2 - dx;
                x1 = Math.max(0, x1 - dx);
            }
            // System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
        }

        long answer = (long)(x2 - x1 + 1) * (long)(y2 - y1 + 1);
        return answer;
    }
}