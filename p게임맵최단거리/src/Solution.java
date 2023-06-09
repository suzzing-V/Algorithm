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
    
    public int solution(int[][] maps) {
        int answer = bfs(maps, 0, 0);
        return answer;
    }
    
    public int bfs(int[][] maps, int x, int y) {
        Queue<Pos> queue = new LinkedList<>();
        int count = 0;
        int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visit = new boolean[n][m];
        queue.add(new Pos(x, y, 1));
        
        while(!queue.isEmpty()) {
            Pos tmp = queue.poll();
            for(int i = 0; i < 4; i++) {
                int mx = tmp.x + dir[i][0];
                int my = tmp.y + dir[i][1];
                
                if(mx == n - 1 && my == m - 1) return tmp.count + 1;
                if(mx < 0 || mx >= n || my < 0 || my >= m 
                   || visit[mx][my]) continue; 
                if(maps[mx][my] == 0) {
                    visit[mx][my] = true;
                    continue;
                }
                visit[mx][my] = true;
                queue.add(new Pos(mx, my, tmp.count + 1));
            }
        }
        return -1;
    }
}