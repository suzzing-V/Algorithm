import java.util.*;

class Solution {

    private int[][] dp = new int[10][10];
    private int min = Integer.MAX_VALUE;
    private int[][][] memo;
    private String numbers;

    public int solution(String numbers1) {
        numbers = numbers1;
        memo = new int[numbers.length()][10][10];
        init();
        floyd();

        for(int i = 0; i < numbers.length(); i++) {
            for(int j = 0; j < 10; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        int min = getMinWeight(0, 4, 6);
        return min;
    }

    private int getMinWeight(int idx, int left, int right) {
        if(idx == numbers.length()) {
            return 0;
        }
        if(memo[idx][left][right] != -1) {
            return memo[idx][left][right];
        }
        if(left == right) {
            return 500000;
        }

        int num = numbers.charAt(idx) - '0';
        int result = getMinWeight(idx + 1, num, right) + dp[left][num];
        // 오른쪽 손가락으로 움직
        result = Math.min(getMinWeight(idx + 1, left, num) + dp[right][num], result);
        return memo[idx][left][right] = result;
    }

    private void init() {
        for(int i = 0; i < 10; i++) {
            Arrays.fill(dp[i], 10000);
        }
        for(int i = 0; i < 10; i++) {
            dp[i][i] = 1;
        }

        dp[1][2] = dp[2][3] = dp[4][5] = dp[5][6] = dp[7][8] = dp[8][9] = 2;
        dp[1][4] = dp[2][5] = dp[3][6] = dp[4][7] = dp[5][8] = dp[6][9] = dp[8][0] = 2;
        dp[1][5] = dp[2][6] = dp[4][8] = dp[5][9] = dp[7][0] = 3;
        dp[2][4] = dp[3][5] = dp[5][7] = dp[6][8] = dp[9][0] = 3;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(dp[i][j] != 10000) dp[j][i] = dp[i][j];
            }
        }

    }

    private void floyd() {
        for(int k = 0; k < 10; k++) {
            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 10; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }
}