import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            int k = Integer.parseInt(bf.readLine());
            long [][] dp = new long [k + 1][k + 1];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            long[] sum = new long[k + 1];
            for(int j = 1; j <= k; j++) {
                sum[j] = Long.parseLong(st.nextToken()) + sum[j - 1];
            }

            for(int j = 1; j < k; j++) {
                for(int a = 1; a <= k - j; a++) {
                    dp[a][a + j] = Long.MAX_VALUE;
                    for(int b = a; b < a + j; b++) {
                            dp[a][a + j] = Math.min(dp[a][a + j], dp[a][b] + dp[b + 1][a + j]);
                    }
                    dp[a][a + j] += sum[a + j] - sum[a - 1];
                }
            }
            System.out.println(dp[1][k]);
        }
    }
}
