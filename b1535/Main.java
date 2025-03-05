import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] happy;
    private static int[] power;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        happy = new int[n + 1];
        power = new int[n + 1];
        dp = new int[n + 1][100];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < 100; j++) {
                if (power[i] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - power[i]] + happy[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        bw.write(String.valueOf(dp[n][99]));
        bw.close();
    }
}
