import java.util.*;

class Solution {

    class Pos implements Comparable<Pos> {
        private int x;
        private int y;
        private char c;

        Pos(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Pos p) {
            return this.c - p.c;
        }
    }

    public String solution(int m, int n, String[] board) {
        PriorityQueue<Pos> tmp = new PriorityQueue<>();
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        char[][] game = new char[m][n];

        // 큐에 타일 넣기
        for(int i = 0; i < m; i++) {
            String line = board[i];
            for(int j = 0; j < n; j++) {
                char c = line.charAt(j);
                game[i][j] = c;
                if(c <= 'Z' && c >= 'A') pq.add(new Pos(i, j, line.charAt(j)));
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            Pos t1 = pq.remove();
            Pos t2 = pq.remove();

            if(t1.x == t2.x) { // x좌표 같을 경우 일직선으로 가는 방법뿐
                if(checkRow(game, t1, t2)) {
                    sb.append(t1.c);
                    game[t1.x][t1.y] = '.';
                    game[t2.x][t2.y] = '.';
                    emptyTmp(tmp, pq);
                } else {
                    tmp.add(t1);
                    tmp.add(t2);
                }
            } else if(t1.y == t2.y) { // y좌표 같을 경우 일직선으로 가는 방법뿐
                if(checkCol(game, t1, t2)) {
                    sb.append(t1.c);
                    game[t1.x][t1.y] = '.';
                    game[t2.x][t2.y] = '.';
                    emptyTmp(tmp, pq);
                } else {
                    tmp.add(t1);
                    tmp.add(t2);
                }
            } else { // x,y 좌표 둘다 다를 경우 대칭점 두개 놓고 그 점을 코너로 해서 돌기
                Pos s1 = new Pos(t1.x, t2.y, t1.c);
                Pos s2 = new Pos(t2.x, t1.y, t1.c);

                if(game[s2.x][s2.y] == '.' && checkCol(game, t1, s2) && checkRow(game, t2, s2)) {
                    sb.append(t1.c);
                    game[t1.x][t1.y] = '.';
                    game[t2.x][t2.y] = '.';
                    emptyTmp(tmp, pq);
                } else if(game[s1.x][s1.y] == '.' && checkCol(game, t2, s1) && checkRow(game, t1, s1)) {
                    sb.append(t1.c);
                    game[t1.x][t1.y] = '.';
                    game[t2.x][t2.y] = '.';
                    emptyTmp(tmp, pq);
                } else {
                    tmp.add(t1);
                    tmp.add(t2);
                }
            }

            if(pq.isEmpty() && !tmp.isEmpty()) { // 한바퀴 다 돌았는데 깰 타일 없을 경우 impossible
                return "IMPOSSIBLE";
            }
        }

        String answer = sb.toString();
        return answer;
    }

    private static boolean checkRow(char[][] game, Pos t1, Pos t2) {
        Pos p1 = t1; // y값이 더 작은
        Pos p2 = t2; // y값이 더 큰
        if(p1.y > p2.y) {
            p1 = t2;
            p2 = t1;
        }

        for(int i = p1.y + 1; i < p2.y; i++) {
            if(game[p1.x][i] != '.') return false;
        }
        return true;
    }

    private static boolean checkCol(char[][] game, Pos t1, Pos t2) {
        Pos p1 = t1; // x값이 더 작은
        Pos p2 = t2; // x값이 더 큰
        if(p1.x > p2.x) {
            p1 = t2;
            p2 = t1;
        }

        for(int i = p1.x + 1; i < p2.x; i++) {
            if(game[i][p1.y] != '.') return false;
        }
        return true;
    }

    private static void emptyTmp(PriorityQueue<Pos> tmp, PriorityQueue<Pos> pq) {
        while(!tmp.isEmpty()) {
            Pos p = tmp.remove();
            pq.add(p);
        }
    }
}
