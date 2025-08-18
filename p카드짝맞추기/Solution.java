import java.util.*;

// while문 잘 쓰기
// dfs로 뒤집을 카드 순서 정하고, 그 순서대로 카드 뒤집기
// 시간 복잡도: 6! *
class Solution {

    private int[][] board;
    private int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int answer = Integer.MAX_VALUE;
    private Pos[][] poses = new Pos[7][2];
    private int card_cnt = 0;
    private int r;
    private int c;

    public int solution(int[][] board1, int r1, int c1) {
        board = board1;
        r = r1;
        c = c1;
        // 카드 위치 정보 저장
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j] != 0) {
                    if(poses[board[i][j]][0] == null) {
                        poses[board[i][j]][0] = new Pos(i, j, 0);
                        card_cnt ++;
                    }
                    else poses[board[i][j]][1] = new Pos(i, j, 0);
                }
            }
        }

        dfs(0, new ArrayList<>());
        // enter 수는 카드 수 * 2
        return answer + card_cnt * 2;
    }

    private void dfs(int cnt, List<Integer> cards) {
        // 카드 순서 완성. 이 순서대로 뒤집었을 때 거리 합과 answer 값 비교해 최솟값 갱신
        if(cnt == card_cnt) {
            // System.out.println(cards.toString());
            answer = Math.min(answer, getDistance(cards));
            return;
        }

        for(int i = 1; i <= 6; i++) {
            if(poses[i][0] == null || cards.contains(i)) continue;

            cards.add(i);
            dfs(cnt + 1, cards);
            cards.remove((Integer)i);
        }
    }

    private int getDistance(List<Integer> cards) {
        int x = r;
        int y = c;
        int sum = 0;
        int[][] tmp = new int[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) tmp[i][j] = board[i][j];
        }

        for(int i = 0; i < cards.size(); i++) {
            int card = cards.get(i);
            Pos p1 = poses[card][0];
            Pos p2 = poses[card][1];
            // System.out.println(p1.x + " " + p1.y + " " + p2.x + " " + p2.y);
            // d1: 현재 위치 ~ 두번째 카드 위치 가는 최소 거리
            int d1 = bfs(x, y, p1.x, p1.y, tmp); // 현재 위치 ~ 첫번째 카드 위치
            // 첫번째 카드 뒤집기
            tmp[p1.x][p1.y] = 0;
            // 첫번째 카드 위치~두번째 카드 위치
            d1 += bfs(p1.x, p1.y, p2.x, p2.y, tmp);
            // 다시 복구
            tmp[p1.x][p1.y] = card;

            // d2: 현재 위치 ~ 첫번째 카드 가는 최소 거리
            int d2 = bfs(x, y, p2.x, p2.y, tmp);
            tmp[p2.x][p2.y] = 0;
            d2 += bfs(p2.x, p2.y, p1.x, p1.y, tmp);
            tmp[p2.x][p2.y] = card;

            // 현재 카드 뒤집음으로 표시
            tmp[p1.x][p1.y] = 0;
            tmp[p2.x][p2.y] = 0;

            // 두 경우 중 최소거리인 경우 선택
            if(d1 < d2) {
                sum += d1;
                x = p2.x;
                y = p2.y;
            } else {
                sum += d2;
                x = p1.x;
                y = p1.y;
            }
        }

        return sum;
    }

    private int bfs(int sx, int sy, int ex, int ey, int[][] tmp) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(new Pos(sx, sy, 0));
        boolean[][] visited = new boolean[4][4];

        while(!pq.isEmpty()) {
            Pos curr = pq.remove();

            // System.out.println("curr: " + curr.x + " " + curr.y + " "+ curr.d);
            if(curr.x == ex && curr.y == ey) {
                // System.out.println();

                return curr.d;
            }

            // 한칸 이동
            for(int i = 0 ; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                pq.add(new Pos(nx, ny, curr.d + 1));
            }

            // 가장 가까운 카드로 이동
            for(int i = 0; i < 4; i++) {
                int nx = curr.x;
                int ny = curr.y;

                // 카드를 만날 경우 그 위치, 끝에 다다를 경우 그 줄의 마지막 칸
                while(true) {
                    nx += dir[i][0];
                    ny += dir[i][1];

                    if(nx < 0) {
                        nx = 0;
                        break;
                    } else if(ny < 0) {
                        ny = 0;
                        break;
                    } else if(nx >= 4) {
                        nx = 3;
                        break;
                    } else if(ny >= 4) {
                        ny = 3;
                        break;
                    } else if(tmp[nx][ny] != 0) {
                        break;
                    }
                }
                // System.out.println(nx + " "+ ny + " " + curr.d);
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                pq.add(new Pos(nx, ny, curr.d + 1));
            }
        }

        return -1;
    }

    private class Pos implements Comparable<Pos> {
        int x;
        int y;
        int d;

        Pos(int x, int y, int d) {
            this.x =x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Pos p) {
            return this.d - p.d;
        }
    }
}