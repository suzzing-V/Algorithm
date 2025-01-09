import java.io.*;
import java.util.*;

public class Main {
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new long[n + 1];
        dp[1] = 1;
        System.out.println(fibonacci(n));
    }

    private static long fibonacci(int n) {
        if(dp[n] != 0) {
            return dp[n];
        }
        if (n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        }
        dp[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return dp[n];
    }
}
