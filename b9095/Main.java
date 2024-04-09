import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            bw.write(dp((Integer.parseInt(bf.readLine()))) + "\n");
        }
        bw.close();
    }

    public static int dp(int n) {
        int[] dp = new int[n + 1];
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(n == 3) return 4;

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
}
