import java.io.*;

public class Main {
    static int n;
    static int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());

        int[][][] dp = new int[n + 1][10][1<<10];
        for(int i = 1; i < 10; i++) {
            dp[1][i][1<<i] = 1;
        }

        for(int i = 2; i <= n; i ++) {
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k <= 1023; k ++) {
                    if(j == 0) {
                        dp[i][j][k | (1<<j)] += dp[i - 1][j + 1][k];
                    } else if(j == 9) {
                        dp[i][j][k | (1<<j)] += dp[i - 1][j - 1][k];
                    } else {
                        dp[i][j][k | (1<<j)] += dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k];
                    }

                    dp[i][j][k | (1<<j)] %= MOD;
                }
            }
        }

        int result = 0;
        for(int i = 0; i < 10; i++) {
            result += dp[n][i][1023];
            result %= MOD;
        }

        bw.write(String.valueOf(result));
        bw.close();
    }
}
