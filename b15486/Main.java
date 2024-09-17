import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] tp = new int[n + 2][2];
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            tp[i][0] = Integer.parseInt(st.nextToken());
            tp[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 2];
        for(int i = 1; i <= n + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i]);
            if(i + tp[i][0] <= n + 1) {
                dp[i + tp[i][0]] = dp[i] + tp[i][1];
            }
        }
        System.out.println(dp[n + 1]);
    }
}
