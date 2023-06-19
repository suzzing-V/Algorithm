class Solution
{
    public int solution(int [][]board)
    {
        int max = 0;
        boolean[][] visit = new boolean[board.length][board[0].length];
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++)
                if(!visit[i][j] && board[i][j] == 1) max = Math.max(max, findSquare(board, i, j, visit));
        }
        return max;
    }
    
    public int findSquare(int[][] board, int cx, int cy, boolean[][] visit) {
        int count = 1;
        int x = cx;
        int y = cy;
        
        while(true) {
            if(x + 1 < board.length && y + 1 < board[0].length && board[x + 1][cy] == 1 && board[cx][y + 1] == 1) {
                if(checkLine(cx, cy, x + 1, y + 1, board)) {
                    x++; y++;
                    count ++;
                } else break;
            } else break;
        }
        
        for(int i = cx; i < cx + count; i++) {
            for(int j = cy; j < cy + count; j++) {
                visit[i][j] = true;
            }
        }
        return count * count;
    }
    
    public boolean checkLine(int cx, int cy, int x, int y, int[][] board) {
        for(int i = cy + 1; i <= y; i++) {
            if(board[x][i] == 0) return false;
        }
        
        for(int i = cx + 1; i <= x; i++) {
            if(board[i][y] == 0) return false;
        }
        
        return true;
    }
}