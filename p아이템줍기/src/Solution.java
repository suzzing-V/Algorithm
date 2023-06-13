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
        int[][] field = new int[102][102];
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        fillField(rectangle, field);
        int answer = bfs(field, characterX, characterY, itemX, itemY) / 2;
        return answer;
    }
    
    public void fillField(int[][] rectangle, int[][] field) {
        for(int[] rec : rectangle) {
            int x1 = rec[0] * 2;
            int x2 = rec[2] * 2;
            int y1 = rec[1] * 2;
            int y2 = rec[3] * 2;
            
            for(int i = y1; i <= y2; i++) {
                for(int j = x1; j <= x2; j++) {
                    if((((j >= x1 && j <= x2) && (i == y1 || i == y2))
                       || ((i >= y1 && i <= y2) && (j == x1 || j == x2)))
                      && field[i][j] != 2) field[i][j] = 1;
                    else field[i][j] = 2;   
                }
            }
        }
    }
    
    public int bfs(int[][] field, int characterX, int characterY, int itemX, int itemY) {
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visit = new boolean[102][102];
        int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        queue.add(new Pos(characterX, characterY, 0));
        visit[characterY][characterX] = true;
        
        while(!queue.isEmpty()) {
            Pos tmp = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nx = tmp.x + dir[i][0];
                int ny = tmp.y + dir[i][1];
                
                if(ny < 0 || ny >= 101 || nx < 0 || nx >= 101 || visit[ny][nx]) continue;
                if(ny == itemY && nx == itemX) return tmp.count + 1;
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