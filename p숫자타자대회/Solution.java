import java.util.*;

class Solution {

    private int[][] dp = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };
    private int[][][] memo;
    private String numbers;

    public int solution(String numbers1) {
        numbers = numbers1;
        memo = new int[numbers.length()][10][10];

        for(int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

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

        int num = numbers.charAt(idx) - '0';
        int result = Integer.MAX_VALUE;

        // 왼쪽 손가락으로 움직
        if(right != num) result = Math.min(getMinWeight(idx + 1, num, right) + dp[left][num], result);
        // 오른쪽 손가락으로 움직
        if(left != num) result = Math.min(getMinWeight(idx + 1, left, num) + dp[right][num], result);
        return memo[idx][left][right] = result;
    }
}
