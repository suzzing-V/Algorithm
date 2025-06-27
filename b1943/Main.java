import java.io.*;
import java.util.*;

public class Main {

    // 주어진 동전으로 반으로 나눈 값을 만들 수 있으면 가능.
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 0; t < 3; t++) {
            int n = Integer.parseInt(bf.readLine());
            int[] coins = new int[n + 1];
            int[] cnt = new int[n + 1];
            int total = 0;
            for(int i = 1; i <= n; i ++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                coins[i] = Integer.parseInt(st.nextToken());
                cnt[i] = Integer.parseInt(st.nextToken());
                total += coins[i] * cnt[i];
            }

            // 전체 합이 홀수면 반으로 나누지 못한다.
            if(total % 2 != 0) {
                System.out.println(0);
                continue;
            }

            // dp[i][j] : i번째 동전까지 봤을 때 j원을 만들 수 있는지
            boolean[][] dp = new boolean[n + 1][total / 2 + 1];
            for(int k = 1; k <= cnt[1]; k++) {
                if(coins[1] * k > total / 2) break;
                dp[1][coins[1] * k] = true;
            }

            for(int i = 2; i <= n; i++) {
                for(int j = 1; j < dp[0].length; j++) {
                    // 만약에 이미 전 조합에서 j원 만들 수 있으면 i번째 동전 추가해도 만들 수 있다.
                    if(dp[i - 1][j]) dp[i][j] = true;

                    // i번째 동전 개수마다 사용했을 때 j원 만들 수 있는지?
                    for(int k = 1; k <= cnt[i]; k++) {
                        if(j - coins[i] * k < 0) break;
                        // 이전 조합에서 j - 새로 추가한 동전들의 값을 만들 수 있으면 추가할 수 있다. 되냐/안되냐가 결정됐으므로 break
                        if(dp[i - 1][j - coins[i] * k]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                }
            }

//            for(int i = 1; i <= n; i++) {
//                for(int j = 1; j <= total / 2; j++) {
//                    System.out.print(dp[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            if(dp[n][total/2]) System.out.println(1);
            else System.out.println(0);
        }
    }
}
