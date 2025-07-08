import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] stair = new int[n + 1];
        int[][] dp = new int[n + 1][2];
        for(int i = 1; i <= n; i++) {
            stair[i] = Integer.parseInt(bf.readLine());
        }

        dp[1][0] = stair[1];
        dp[1][1] = stair[1];
        for(int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][1] + stair[i];
            dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + stair[i];
        }
        System.out.println(Math.max(dp[n][0], dp[n][1]));
}
    public static int d
}
