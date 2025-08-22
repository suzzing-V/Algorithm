import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] arr;
    private static int[][] dp;
    private static int MOD = 10000003;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        // dp[i][j] = i까지 봤을 때 최대공약수가 j인 경우의 수
        dp = new int[n][100001];
        dp[0][arr[0]] = 1;
        for(int i = 1; i < n; i ++) {
            // i - 1행 카피(현재 수를 안 썼을 경우의 경우의 수)
            for(int  j = 1; j <= 100000; j++) {
                dp[i][j] = dp[i - 1][j];
            }

            // 자기자신 +1 (현재수만 썼을 때의 경우의 수)
            dp[i][arr[i]] ++;

            // i - 1까지 봤을 때 최대 공약수가 j인 경우의 수를 dp[i][arr[i]와 j의 최대공약수] 에 더하기
            // 현재 수를 썼을 때의 경우의 수
            for(int j = 1; j <= 100000; j++) {
                // 전까지 봤을 때 최대공약수가 j인 경우의 수 없으면 만들 수 없다.
                if(dp[i - 1][j] == 0) continue;
                int gcd = gcd(Math.max(arr[i], j), Math.min(arr[i], j));
                // j와 현재 수의 최대공약수는 i - 1이전까지의 최대공약수가 j인 수의 모음들과 현재 수의 최대공약수이다. 따라서 i까지 봤을 때의 최대공약수이므로 dp[i][gcd(현재수, j)]에 dp[i - 1][j]를 더해줘야 한다.
                dp[i][gcd] = dp[i][gcd] % MOD + dp[i - 1][j] % MOD;
                dp[i][gcd] %= MOD;
            }
        }

//        for(int i = 0; i < n; i++) {
//            for(int j = 1; j <= 4; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(dp[n - 1][1]);
    }

    private static int gcd(int a, int b) {
        if(b == 0) return a;

        return gcd(b, a%b);
    }
}
