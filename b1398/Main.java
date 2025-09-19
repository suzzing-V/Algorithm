import java.util.*;
import java.io.*;

// 시간복잡도: 15^t
// 1, 10, 25 / 100, 1000, 2500/ ... 100^k씩 반복된다. 각 경우의 배수에서 사용하는 동전 개수는 같다. 그러므로 n에서 100^k의 배수만큼 뽑아서 동전 개수 구하고, 남은거 계속 다음 배수로 넘기면서 동전 개수 더하기
public class Main {

    private static int[] dp = new int[100];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // 1~99까지 필요한 동전 수 구하기
        for(int i = 1; i < 100; i ++) {
            // 1,10, 25 중 어떤 동전 사용할 건지
            if(i >= 25) {
                dp[i] = Math.min(dp[i - 25] + 1, dp[i - 10] + 1);
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            } else if(i >= 10) {
                dp[i] = Math.min(dp[i - 10] + 1, dp[i - 1] + 1);
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }

//        for(int i = 1; i <= 99; i++) {
//            System.out.print(dp[i] + " ");
//        }
//        System.out.println();

        int t = Integer.parseInt(bf.readLine());
        // 100^k ~ 100^(k + 1) - 1 사이의 100^k 배수를 만드는 동전 수는 1~99를 만드는 동전 수와 같다.
        for(int i = 0; i < t; i++) {
            long n = Long.parseLong(bf.readLine());

            // n보다 작거나 같은 100^k 구하기
            int k = 0;
            while((long)Math.pow(100, k) <= n) k ++;
            k --;

            int total = 0;
            // 100^k 배수씩 추출하면서 동전 개수 구하기
            while(k >= 0) {
                total += dp[(int)(n / (long)Math.pow(100, k))];
                n %= (long)Math.pow(100, k);
                k--;
            }
            System.out.println(total);
        }
    }
}
