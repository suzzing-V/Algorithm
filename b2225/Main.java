import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int k;
    static long[][] dp;
    static long MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new long[k + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            dp[1][i] = 1;
        }
        for(int i = 1; i <= k; i++) {
            dp[i][0] = 1;
        }

        for(int i = 2; i <= k; i++) {
            for(int j = 1; j <= n; j++) {
                for(int k = j; k >= 0; k--) {
                    dp[i][j] += dp[i - 1][k] % MOD;
                }
                dp[i][j] %= MOD;
            }
        }

        bw.write(String.valueOf(dp[k][n]));
        bw.close();
    }
}
