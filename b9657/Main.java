import java.util.*;
import java.io.*;


// 시간복잡도: 1000 * 3
public class Main {

    private static int n;
    private static boolean[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        dp = new boolean[1001];
        dp[1] = true;
        dp[2] = false;
        dp[3] = true;
        dp[4] = true;

        // 이전 턴에 이겼으면 이번 턴에 무조건 진다.
        for(int i = 5; i <= n; i++) {
            // 이번 턴에서 창용이가 지는 경우가 하나라도 발생하면(이전 턴에서 상근이가 이김) 상근이가 이긴다.
            if(dp[i - 1] && dp[i - 3] && dp[i - 4]) dp[i] = false;
            else dp[i] = true;
        }

        if(dp[n]) System.out.println("SK");
        else System.out.println("CY");
    }
}
