import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int[][] arr = new int[501][501];
        arr[0][0] = triangle[0][0];
        int n = triangle.length;
        
        for(int i = 1; i < n; i++) {
            int l = triangle[i].length;
            arr[i][0] = arr[i - 1][0] + triangle[i][0];
            for(int j = 1; j < l - 1; j++) {
                arr[i][j] = triangle[i][j] + Math.max(arr[i - 1][j - 1], arr[i - 1][j]);
            }
            arr[i][l - 1] = arr[i - 1][l - 2] + triangle[i][l - 1];
        }
        
        int answer = arr[n - 1][0];
        for(int i = 1; i < n; i++) answer = Math.max(arr[n - 1][i], answer);
        return answer;
    }
}