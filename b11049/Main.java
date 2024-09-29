import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] arr = new int[n][2];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][n];
        for(int i = 1; i < n; i++) {
            for(int j = 0; i + j < n; j++) {
                dp[j][j + i] = Integer.MAX_VALUE;
                for(int k = j; k < j + i; k++) {
                dp[j][j + i] = Math.min(dp[j][j + i], dp[j][k] + dp[k + 1][j + i] + arr[j][0] * arr[k][1] * arr[j + i][1]);
                }
                }
        }
        System.out.println(dp[0][n - 1]);
    }
}
