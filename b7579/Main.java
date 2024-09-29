import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][10001];
        int[] mem = new int[n + 1];
        int[] c = new int[n + 1];

        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= 10000; j++) {
                dp[i][j] = -1;
            }
        }

        for(int i = 1; i <= n; i++) {
                dp[i][c[i]] = Math.max(dp[i - 1][c[i]], mem[i]);
            for(int j = 0; j <= 10000; j++) {
                if(dp[i - 1][j] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                    dp[i][c[i] + j] = Math.max(Math.max(dp[i - 1][c[i] + j], mem[i] + dp[i - 1][j]), dp[i][c[i] + j]);
                }
            }
        }

        for(int i = 0; i <= 10000; i++) {
            if(dp[n][i] >= m) {
                System.out.println(i);
                break;
            }
        }
    }
}
