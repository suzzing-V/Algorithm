import java.util.*;

class Solution {
    class Pos implements Comparable<Pos> {
        private int x;
        private int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos p) {
            if(y == p.y) {
                return x - p.x;
            }
            return y - p.y;
        }
    }

    public int solution(int n, int[][] data) {
        List<Pos> poses = new ArrayList<>();
        // 쐐기 리스트에 넣고 y좌표, x좌표 순 정렬
        for(int i = 0; i < data.length; i++) {
            poses.add(new Pos(data[i][0], data[i][1]));
        }
        Collections.sort(poses);

        int answer = 0;
        for(int i = 0; i < poses.size(); i++) {
            int sx = poses.get(i).x;
            int sy = poses.get(i).y;
            int totalMaxX = -1;
            int rowMaxX = -1;
            int totalMinX = Integer.MAX_VALUE;
            int rowMinX = Integer.MAX_VALUE;

            for(int j = i + 1; j < poses.size(); j++) {
                int cx = poses.get(j).x;
                int cy = poses.get(j).y;
                if(poses.get(j - 1).y != cy) { // 줄 바뀔 경우 기준점에 가까운 x좌표들 갱신
                    totalMaxX = rowMaxX;
                    totalMinX = rowMinX;
                }

                if(sx == cx || sy == cy) continue; // 넓이 0인 경우 불가능

                if(cx > sx) { // 기준점 기준 오른쪽이면 기준점 x좌표보다 크면서 가장 작은 x좌표보다 작아야 함
                    rowMinX = Math.min(rowMinX, cx); // 줄의 x좌표 최소값 갱신
                    if(cx <= totalMinX) answer ++;
                } else if(cx < sx) {
                    rowMaxX = Math.max(rowMaxX, cx);
                    if(cx >= totalMaxX) answer ++;
                }
            }
        }
        return answer;
    }
}
