import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int MOD = 1000000003;
    private static int k;
    private static int[][] dp; // dp[i][j] = i개의 색이 있을 때 j개의 인접하지 않은 색을 선택하는 경우의 수(처음과 마지막이 인접하는 경우는 고려하지 않는다. 왜냐하면 처음이랑 마지막이 인접하면 안되는 건 우리의 목표인 색의 개수가 n일 때만 유효하기 때문이다. 그전에는 짜피 그다음에 다음 색이 들어올 것이므로 처음과 마지막이 인접해도 된다.)

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        k = Integer.parseInt(bf.readLine());
        if(k == 1) { // k는 1일 경우 색의 수가 경우의 수이다.
            bw.write(String.valueOf(n));
            bw.close();
            return;
        }

        dp = new int[n + 1][k + 1];
        for(int i = 1; i <= n; i++) { // k는 1일 경우 색의 수가 경우의 수이다.
            dp[i][1] = i;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 2; j <= k; j++) {
                if(i % 2 == 1 && i / 2 + 1 < j) break; // 색의 수가 홀수일 때 고를 수 있는 색의 최대값은 색의수/2 + 1이다. 따라서 그보다 큰 고를 수는 0이다.
                if(i % 2 == 0 && i / 2 < j) break; // 짝수일 때의 최대값은 색의수/2이다.

                dp[i][j] = (dp[i - 2][j - 1] % MOD + dp[i - 1][j] % MOD) % MOD; // i - 1개의 색의 수에서 새로 추가된 i번째 색을 무조건 선택하는 경우, i - 1번째 색은 선택할 수 없다.
                // i번째수를 무조건 선택하는 경우 + i번째 수를 선택하지 않는 경우(i-1번째에서 j개를 고르는 경우)
            }
        }
        bw.write(String.valueOf((dp[n - 3][k - 1] % MOD + dp[n - 1][k] % MOD) % MOD)); // 우리가 구하고자 하는 색의 수는 처음과 마지막이 같이 선택되면 안된다. 따라서 n번째 수를 선택할 때 n- 1번째수와 첫번째 수 모두 선택 불가능하다.
        bw.close();
    }
}
