import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 1][10];

        for(int i = 0; i < 10; i ++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < 10; j ++) {
                for(int k = j; k >= 0; k--) {
                    dp[i][j] += dp[i - 1][k] % 10007;
                }
                dp[i][j] %= 10007;
            }
        }

        long result = 0;
        for(int i = 0; i < 10; i ++) {
            result += dp[n][i] % 10007;
        }
        System.out.println(result % 10007);
    }
}
