import java.util.*;

class Solution {
    class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<int[][]> gList = new ArrayList<>();
    static List<int[][]> tList = new ArrayList<>();
    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }};
    public int solution(int[][] game_board, int[][] table) {
        int len = game_board.length;
        
        boolean[][] visit = new boolean[len][len];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(game_board[i][j] == 0 && !visit[i][j]) makePiece(game_board, visit, i, j, 0, len);
            }
        }
        
        visit = new boolean[len][len];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(table[i][j] == 1 && !visit[i][j]) makePiece(table, visit, i, j, 1, len);
            }
        }
        
        int answer = 0;
        for(int[][] block : gList) answer += compareBlock(block);
        return answer;
    }
    
    public void makePiece(int[][] game_board, boolean[][] visit, int cx, int cy, int flag, int len) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(cx, cy));
        visit[cx][cy] = true;
        int maxX = cx;
        int maxY = cy;
        int minX = cx;
        int minY = cy;
        
        while(!queue.isEmpty()) {
            Pos tmp = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = tmp.x + dir[i][0];
                int ny = tmp.y + dir[i][1];
                
                if(nx < 0 || nx > len - 1|| ny < 0 || ny > len - 1 || visit[nx][ny]) continue;
                if(game_board[nx][ny] == flag) {
                    queue.add(new Pos(nx, ny));
                    visit[nx][ny] = true;
                    maxX = Math.max(maxX, nx);
                    maxY = Math.max(maxY, ny);
                    minX = Math.min(minX, nx);
                    minY = Math.min(minY, ny);
                }
            }
        }
        
        int[][] block = new int[maxX - minX + 1][maxY - minY + 1];
        int x = minX;
        int y = minY;
        for(int i = 0; i < block.length; i++) {
            y = minY;
            for(int j = 0; j < block[0].length; j++) {
                block[i][j] = game_board[x][y];
                y ++;
            }
            x ++;
        }
        
        if(flag == 0) {
            gList.add(block);
        } else {
            tList.add(block);
        }
    }
    
    public int compareBlock(int[][] block) {
        for(int[][] tb: tList) {
            int[][] tmp = tb;
            for(int i = 0; i < 4; i++) {
                tmp = rotateBlock(tmp);
                if((tmp.length == block.length && tmp[0].length == block[0].length)) {
                    int count = compareSquare(block, tmp);
                    if(count == 0) continue;
                    else {
                        tList.remove(tb);
                        return count;
                    }
                } else continue;
            }
        }
        return 0;
    }
    
    public int compareSquare(int[][] block, int[][] tb) {
        int count = 0;
        for(int i = 0; i < block.length; i++) {
            for(int j = 0; j < block[0].length; j++) {
                if(block[i][j] == tb[i][j]) return 0;
                if(block[i][j] == 0) count++;
            }
        }
        return count;
    }
    
    public int[][] rotateBlock(int[][] tb) {
        int[][] result = new int[tb[0].length][tb.length];
        for(int i = 0; i < tb.length; i++) {
            for(int j = 0; j < tb[0].length; j++) {
                result[j][tb.length - 1 - i] = tb[i][j];
            }
        }
        return result;
    }
}