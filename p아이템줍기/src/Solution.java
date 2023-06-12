import java.util.*;

class Solution {
    public class Pos {
            int x;
            int y;
            int count;
            public Pos(int x, int y, int count) {
                this.x = x;
                this.y = y;
                this.count = count;
            }
        }
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] field = new int[101][101];
        fillField(rectangle, field);
        int answer = bfs(field, characterX, characterY, itemX, itemY) / 2;
        return answer;
    }
    
    public void fillField(int[][] rectangle, int[][] field) {
        for(int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int x2 = rectangle[i][2] * 2;
            int y1 = rectangle[i][1] * 2;
            int y2 = rectangle[i][3] * 2;
        }
    }
    
    public int bfs(int[][] field, int characterX, int characterY, int itemX, int itemY) {
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visit = new boolean[101][101];
        int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        queue.add(new Pos(characterX * 2, (50 - characterY) * 2, 0));
        visit[50 - characterY][characterX] = true;
        
        while(!queue.isEmpty()) {
            Pos tmp = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nx = tmp.x + dir[i][0];
                int ny = tmp.y + dir[i][1];
                
                if(ny < 0 || ny >= 100 || nx < 0 || nx >= 100 || visit[ny][nx]) continue;
                if(ny == (50 - itemY) * 2 && nx == itemX * 2) return tmp.count + 1;
                if(field[ny][nx] == 1) {
                    queue.add(new Pos(nx, ny, tmp.count + 1));
                    visit[ny][nx] = true;
                } else if(field[ny][nx] == 0 || field[ny][nx] == 2) {
                    visit[ny][nx] = true;
                }
            }
        }
        
        return -1;
    }
}