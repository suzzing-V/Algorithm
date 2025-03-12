import java.util.*;

class Solution {

    private int[][] acc;

    public int solution(int[][] board, int[][] skill) {
        acc = new int[board.length + 1][board[0].length + 1];

        // 누적합 만들기
        for(int i = 0; i < skill.length; i ++) {
            if(skill[i][0] == 1) {
                acc[skill[i][1]][skill[i][2]] -= skill[i][5];
                acc[skill[i][3] + 1][skill[i][4] + 1] -= skill[i][5];
                acc[skill[i][1]][skill[i][4] + 1] += skill[i][5];
                acc[skill[i][3] + 1][skill[i][2]] += skill[i][5];
            } else {
                acc[skill[i][1]][skill[i][2]] += skill[i][5];
                acc[skill[i][3] + 1][skill[i][4] + 1] += skill[i][5];
                acc[skill[i][1]][skill[i][4] + 1] -= skill[i][5];
                acc[skill[i][3] + 1][skill[i][2]] -= skill[i][5];
            }
        }

        // for(int i = 0; i < acc.length; i++) {
        //     System.out.println(Arrays.toString(acc[i]));
        // }

        for(int i = 0; i < board.length; i ++) {
            for(int j = 1; j < board[0].length; j++) {
                acc[i][j] += acc[i][j - 1];
            }
        }
        for(int j = 0; j < board[0].length; j++) {
            for(int i = 1; i < board.length; i++) {
                acc[i][j] += acc[i - 1][j];
            }
        }

        // 누적합 배열과 기존 배열 합하고 파괴되지 않은 건물 세기
        int cnt = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] += acc[i][j];
                if(board[i][j] >= 1) cnt ++;
            }
        }

        return cnt;
    }
}
