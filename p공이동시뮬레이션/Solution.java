class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long x1 = x; // 좌상단
        long x2 = x; // 우하단
        long y1 = y; // 좌상단
        long y2 = y; // 우하단
        for(int i = queries.length - 1; i >= 0; i--) {
            int type = queries[i][0];
            int d = queries[i][1];
            if(type == 0) {
                if(y1 == 0) { // 전 위치에서 현재 위치로 이동 방향이 벽이면 그 범위 안의 위치가 다 가능하므로 늘린다.
                    y2 += d;
                } else { // 벽이 아니면 한 위치만 가능하므로 이동한다.
                    y1 += d;
                    y2 += d;
                }
            } else if(type == 1) {
                if(y2 == m - 1) {
                    y1 -= d;
                } else {
                    y1 -= d;
                    y2 -= d;
                }
            } else if(type == 2) {
                if(x1 == 0) {
                    x2 += d;
                } else {
                    x1 += d;
                    x2 += d;
                }
            } else if(type == 3) {
                if(x2 == n - 1) {
                    x1 -= d;
                } else {
                    x1 -= d;
                    x2 -= d;
                }
            }

            if(x1 >= n || y1 >= m || x2 < 0 || y2 < 0) return 0; // 사각형 통째로 격자 나가면 가능한 위치가 없다.

            x1 = Math.max(0, x1);
            y1 = Math.max(0, y1);
            x2 = Math.min(n - 1, x2);
            y2 = Math.min(m - 1, y2); // 범위 넘어갈 경우 조정 필요
        }
        long answer = (x2 - x1 + 1) * (y2 - y1 + 1);
        return answer;
    }
}
