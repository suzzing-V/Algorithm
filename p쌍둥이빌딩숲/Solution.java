import java.util.*;
import java.io.*;

// 시간복잡도: 100 * 100
// dp[i][j] : i개 종류의 빌딩이 있을 때 j개의 빌딩이 보이는 경우의 수
// i - 1개의 빌딩이 있을 경우에서 빌딩들을 다 +1로 치환해준다. ex) 211233 -> 322344
// 그다음에 11을 배치해준다. 11은 붙어다니면서 어디든 넣을 수 있기 때문이다.
// i - 1개의 빌딩을 j개 보이도록 하는 경우에서, 11을 맨 앞을 제외한 자리에 다 넣으면 i개의 빌딩을 j개 보이게 할 수 있다.
// i - 1개의 빌딩을 j - 1개 보이도록 하는 경우에서, 11을 맨 앞에 두면 i개의 빌딩을 j개 보이게 할 수 있다.
// 11만 움직일 수 있기 때문에, j - 2개 보이게 하는 경우에서는 11을 어떻게 넣어도 j개 보이게 할 수 없다.
// 맨앞을 제외한 자리는 (i - 1) * 2이다.
class Solution {

    private long[][] dp;
    private long MOD = 1000000007;

    public int solution(int n, int count) {
        dp = new long[n + 1][n + 1];
        dp[1][1] = 1;

        for(int i = 2; i <= n; i++) {
            int limit = Math.min(count, i);
            // 어짜피 j - 1의 값을 구하므로, j + 1은 필요가 없다. 우리가 구하는 건 count인 경우이기 때문에 count까지만 구해도 된다.
            for(int j = 1; j <= limit; j++) {
                dp[i][j] = (dp[i - 1][j] * 2 * (i - 1)) % MOD + (dp[i - 1][j - 1]) % MOD;
                dp[i][j] %= MOD;
            }
        }
        return (int)dp[n][count];
    }
}