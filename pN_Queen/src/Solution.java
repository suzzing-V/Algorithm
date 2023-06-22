class Solution {
    int count = 0;
    public int solution(int n) {
        boolean[][] chess = new boolean[n][n];
        queen(chess, 0, n);
        return count;
    }
    
    public void queen(boolean[][] chess, int x, int n) {
        if(x == n) {
            count ++;
            return;
        }
        
        for(int i = 0; i < n; i++) {
            if(!checkDiagonal(chess, x, i) && !checkColumn(chess, x, i)) {
                chess[x][i] = true;
                queen(chess, x + 1, n);
                chess[x][i] = false;
            }
        }
    }
    
    public boolean checkDiagonal(boolean[][] chess, int x, int y) {
        int i = x - 1;
        int j = y - 1;
        while(i >= 0 && j >= 0) if(chess[i--][j--]) return true;
        
        i = x - 1;
        j = y + 1;
        while(i >= 0 && j < chess.length) if(chess[i--][j++]) return true;
        
        return false;
    }
    
    public boolean checkColumn(boolean[][] chess, int x, int y) {
        for(int i = x - 1; i >= 0; i--) if(chess[i][y]) return true;
        return false;
    }
}