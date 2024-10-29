import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        long[] dp = new long[n + 1];
        dp[1] = 1;
        if(n >= 2) {
            dp[2] = 3;
        }

        for(int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] % 10007 + dp[i - 2] * 2 % 10007) % 10007;
        }

        System.out.println(dp[n]);
    }
}
