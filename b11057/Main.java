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
                if(j == 0) {
                    dp[i][j] += dp[i - 1][j];
                } else {
                    dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
                }
                dp[i][j] %= 10007;
            }
        }

        long result = 0;
        for(int i = 0; i < 10; i ++) {
            result += dp[n][i];
        }
        System.out.println(result % 10007);
    }
}
