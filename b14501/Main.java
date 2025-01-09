import java.io.*;
import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 2];
        int[] t = new int[n + 1];
        int[] p = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j]);
            }
            if(i + t[i] > n + 1) {
                continue;
            }
            dp[i + t[i]] = Math.max(dp[i] + p[i], dp[i + t[i]]);
        }

        int result = 0;
        for(int i = 1; i <= n + 1; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
