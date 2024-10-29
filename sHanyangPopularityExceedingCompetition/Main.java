import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        dp = new int[n + 1][2];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dp[i][0] = Math.max(getDP(dp[i - 1][0], p, c), getDP(dp[i - 1][1], p, c));
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }

        System.out.println(Math.max(dp[n][0], dp[n][1]));
    }

    public static int getDP(int x, int p, int c) {
        if(Math.abs(x - p) <= c) {
            return x + 1;
        }
        return 0;
    }
}

