import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int k;
    static int MOD = 10007;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][k + 1];

        int result = combi(n, k);
        bw.write(String.valueOf(result));
        bw.close();
    }

    private static int combi(int n, int k) {
        if(k == 1) return n;
        if(n == k || k == 0) return 1;
        if(dp[n][k] != 0) return dp[n][k];

        dp[n][k] = (combi(n - 1, k - 1) % MOD + combi(n - 1, k) % MOD) % MOD;
        return dp[n][k];
    }
}
