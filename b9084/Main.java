import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            int[] coin = new int[n + 1];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                coin[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(coin);

            int m = Integer.parseInt(bf.readLine());
            int[][] dp = new int[n + 1][m + 1];
            for(int j = 1; j <= n; j++) {
                dp[j][0] = 1;
            }
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
                    dp[j][k] += dp[j - 1][k];
                    if (coin[j] <= k) dp[j][k] += dp[j][k - coin[j]];
                }
            }

            bw.write(dp[n][m] + "\n");
        }
        bw.close();
    }
}
