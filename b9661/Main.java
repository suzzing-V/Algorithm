import java.util.*;
import java.io.*;

// 시간복잡도: O(1)

public class Main {

    private static long n;
//    private static boolean[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(bf.readLine());
//        dp = new boolean[n + 1];

        // 현재 i보다 작거나 같은 4의 제곱을 이번 턴에 선택하는 경우 중, 이전 턴에서 상근이가 모두 이겼으면 이번 턴에서는 창용이가 지는 경우가 하나도 없다.
        // 따라서 이 경우에는 창용이가 이기고, 나머지 경우에는 상근이가 이긴다.
        // n의 최대값은 10^12 이므로 dp로는 구할 수 없다. 규칙을 찾는다.
//        dp[1] = true;
//
//        for(int i = 2; i <= n; i ++) {
//            // 이전 턴에서 창용이가 이긴 경우가 있는지. 이긴 경우 있으면 이번 턴에 지는 경우가 생기는 것이므로 이번 턴에는 상근이가 이긴다.
//            boolean cWin = false;
//
//            for(int j = 0; (int)Math.pow(4, j) <= i; j++) {
//                if(!dp[i - (int)Math.pow(4, j)]) {
//                    cWin = true;
//                    break;
//                }
//            }
//
//            if(cWin) dp[i] = true;
//            else dp[i] = false;
//        }

        if(n % 5 == 2 || n % 5 == 0) System.out.println("CY");
        else System.out.println("SK");

//        System.out.println(Arrays.toString(dp));
    }
}
