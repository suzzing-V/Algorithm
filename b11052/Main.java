import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] packs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        packs = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            packs[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            dp[i] = packs[i];
        }
        for(int i = 2; i <= n; i++) {
            for(int j = i - 1; j >= i/2; j--) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }

        System.out.println(dp[n]);
    }
}
