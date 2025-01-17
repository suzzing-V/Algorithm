import java.util.*;

class Solution {
    Pos s = null;
    Pos e = null;
    Pos l = null;
    String[][] maps2;
    int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public class Pos {
        int x;
        int y;
        int time = 0;

        Pos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public int solution(String[] maps) {
        maps2 = new String[maps.length][maps[0].length()];

        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[i].length(); j++) {
                maps2[i][j] = String.valueOf(maps[i].charAt(j));
                if(maps2[i][j].equals("S")) {
                    s = new Pos(i, j, 0);
                }
                if(maps2[i][j].equals("E")) {
                    e = new Pos(i, j, 0);
                }
                if(maps2[i][j].equals("L")) {
                    l = new Pos(i, j, 0);
                }
            }
        }

        int min_l = bfs(s.x, s.y, "L");
        if(min_l == -1) {
            return -1;
        }
        int min_e = bfs(l.x, l.y, "E");
        if(min_e == -1) {
            return -1;
        }
        return min_l + min_e;
    }

    private int bfs(int startX, int startY, String des) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(startX, startY, 0));
        boolean[][] visited = new boolean[maps2.length][maps2[0].length];

        while(!queue.isEmpty()) {
            Pos curr = queue.remove();

            if(visited[curr.x][curr.y]) {
                continue;
            }
            visited[curr.x][curr.y] = true;

            if(maps2[curr.x][curr.y].equals(des)) {
                return curr.time;
            }
            for(int i = 0; i < 4; i ++) {
                int nX = curr.x + dir[i][0];
                int nY = curr.y + dir[i][1];
                if(nX >= 0 && nX < maps2.length && nY >= 0 && nY < maps2[0].length && !maps2[nX][nY].equals("X")) {
                    queue.add(new Pos(nX, nY, curr.time + 1));
                }
            }
        }
        return -1;
    }
}
