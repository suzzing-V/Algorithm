import java.util.*;

/**
 * 0 -2 -2 0
 * 0 -2 -2 0
 * 0 0 0 0
 * 을 만드려면 어떻게 해야할까?
 * 0 -2 0 2
 * 0 -2 0 2
 * 0 0 0 0
 * 에서 왼쪽에서 오른쪽 방향으로 누적합 적용하면 된다.
 * 그럼 이걸 만드려면 어떻게 해야할까?
 * 0 -2 0 2
 * 0 0 0 0
 * 0 2 0 -2
 * 여기서 위쪽에서 아래쪽방향으로 누적합 적용하면 된다.
 * 따라서 이렇게 4지점만 찍어줘도 해당 범위의 변동값을 구할 수 있다.
 * 이렇게 찍어주고 왼-> 오 방향, 상 -> 하 방향으로 누적합 적용해주면 해당 범위의 변동값을 넣을 수 있다.
 */
// 4 * 250000 + 10^6 + 10^6 + 10^6
class Solution {

    private int[][] v;

    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        v = new int[n + 1][m + 1];

        for(int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = type == 1 ? - skill[i][5] : skill[i][5];

            v[r1][c1] += degree;
            v[r2 + 1][c2 + 1] += degree;
            v[r2 + 1][c1] -= degree;
            v[r1][c2 + 1] -= degree;

//             for(int x = 0; x <= n; x++) {
//                 for(int y = 0; y <= m; y++) {
//                     System.out.print(v[x][y] + " ");
//                 }

//                 System.out.println();
//             }
//             System.out.println();
        }



        // 행 누적합 적용
        for(int i = 0; i <= n; i++) {
            for(int j = 1; j <= m ;j++) {
                v[i][j] += v[i][j - 1];
            }
        }

        // 열 누적합 적용
        for(int i = 0; i <= m; i++) {
            for(int j = 1; j <= n ;j++) {
                v[j][i] += v[j - 1][i];
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j++) {
                // System.out.print(v[i][j] + " ");
                board[i][j] += v[i][j];
                if(board[i][j] >= 1) cnt ++;
            }
            // System.out.println();
        }


        return cnt;
    }
}