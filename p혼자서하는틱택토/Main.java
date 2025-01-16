import java.util.*;

class Solution {
    String[] board2;
    int[][] num = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    List<Integer> o = new ArrayList<>();
    List<Integer> x = new ArrayList<>();

    public int solution(String[] board) {
        board2 = board;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board2[i].charAt(j) == 'O') {
                    o.add(num[i][j]);
                } else if(board2[i].charAt(j) == 'X') {
                    x.add(num[i][j]);
                }
            }
        }

        int o_len = o.size();
        int x_len = x.size();
        // System.out.println("개수: " + o_len + " " + x_len);
        // if((o_len + x_len) == 9) return 0;
        if((o_len + x_len) % 2 == 0 && o_len != x_len) return 0;
        if((o_len + x_len) % 2 != 0 && o_len - 1 != x_len) return 0;

        boolean isOWinner = isWinner("O");
        boolean isXWinner = isWinner("X");
        // System.out.println(isOWinner + " " + isXWinner);
        if(isOWinner && o_len != x_len + 1) return 0;
        if(isXWinner && o_len != x_len) return 0;
        if(isOWinner && isXWinner) return 0;

        return 1;
    }

    private boolean isWinner(String player) {
        int[][] win = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 5, 9}, {3, 5, 7}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        List<Integer> pos = null;
        if(player.equals("O")) pos = o;
        if(player.equals("X")) pos = x;

        for(int i = 0; i < win.length; i++) {
            int[] cur = win[i];
            int cnt = 0;
            for(int j = 0; j < 3; j++) {
                if(pos.contains(cur[j])) {
                    cnt ++;
                    if(cnt == 3) return true;
                }
            }
        }
        return false;
    }
}