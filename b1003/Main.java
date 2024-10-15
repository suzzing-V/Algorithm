import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[][] dp = new int[41][2];

        dp[0][0] = 1;
        dp[1][1] = 1;
        for(int i = 2; i <= 40; i ++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }

        int t = Integer.parseInt(bf.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            System.out.println(dp[n][0] + " " + dp[n][1]);
        }
    }
}
