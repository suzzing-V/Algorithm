import java.util.*;

class Solution {

    private int[] acc = new int[1322];
    private int n;
    private int m;
    private int[][] timetable;

    private class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int n1, int m1, int[][] timetable1) {
        n = n1;
        m = m1;
        timetable = timetable1;
        // 가장 많은 손님이 있는 시간의 손님 수 구하기
        int customer = getMostBusyTime();
        if(customer == 1) {
            return 0;
        }

        // 각 손님 간의 거리를 최대로 하여 손님 배치하기
        int answer = putCustomers(customer);
        return answer;
    }

    private int getMostBusyTime() {
        for(int i = 0; i < m; i ++) {
            int startTime = timetable[i][0];
            int endTime = timetable[i][1];

            acc[startTime] += 1;
            acc[endTime + 1] -= 1;
        }


        int max = acc[0];
        for(int i = 1; i < acc.length; i++) {
            acc[i] += acc[i - 1];
            max = Math.max(max, acc[i]);
        }

        return max;
    }

    private int putCustomers(int customer) {
        int dist = 2 * (n - 1);

        while(dist > 1) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    int cnt = 1;
                    List<Pos> poses = new ArrayList<>();
                    poses.add(new Pos(i, j)); // 첫번째 손님 놓기

                    for(int x = i; x < n; x ++) {
                        for(int y = 0; y < n; y++) {
                            if(x == i && y <= j) continue; // 첫번째 위치 이후부터 놓아야 함
                            if(canPlace(x, y, dist, poses)) { // 배치하기
                                poses.add(new Pos(x, y));
                                cnt ++;
                            }
                            if(cnt == customer) { // 손님 다 배치시켰으면 이 값이 정답
                                return dist;
                            }
                        }
                    }
                }
            }
            dist --;
        }
        return dist;
    }

    private boolean canPlace(int x, int y, int dist, List<Pos> poses) {
        for(int i = 0; i < poses.size(); i++) {
            Pos pos = poses.get(i);
            if(getDist(x, y, pos.x, pos.y) < dist) {
                return false;
            }
        }
        return true;
    }

    private int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
