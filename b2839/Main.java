import java.io.*;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        int[] dp = new int[6000];

        for(int i = 0; i < 6000; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        for(int i = 0; i <= n; i++) {
            if(dp[i] != Integer.MAX_VALUE) {
                dp[i + 3] = Math.min(dp[i + 3], dp[i] + 1);
                dp[i + 5] = Math.min(dp[i + 5], dp[i] + 1);
            }
        }
        if(dp[n] == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(dp[n]);
        }
    }
}
