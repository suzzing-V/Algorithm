import java.util.*;

class Solution {
    public class Pos {
        int x;
        int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] visit = new boolean[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!visit[i][j] && picture[i][j] != 0) {
                    numberOfArea ++;
                    maxSizeOfOneArea = Math.max(bfs(picture, visit, i, j), maxSizeOfOneArea);
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int bfs(int[][] picture, boolean[][] visit, int cx, int cy) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(cx, cy));
        int count = 0;
        
        while(!queue.isEmpty()) {
            Pos tmp = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int gx = tmp.x + dir[i][0];
                int gy = tmp.y + dir[i][1];
                
                if(gx < 0 || gx >= picture.length || gy < 0 || gy >= picture[0].length
                  || visit[gx][gy]) continue;
                if(picture[gx][gy] == picture[cx][cy]) {
                    visit[gx][gy] = true;
                    queue.add(new Pos(gx, gy));
                    count++;
                }
            }
        }
        
        return count;
    }
}