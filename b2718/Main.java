import java.io.*;
import java.util.*;

// 시간복잡도: 1000
// 이전 타일에 (현재타일 - 이전타일)의 고유한 타일을 붙인다. 그리고 현재 타일에서 만들 수 있는 고유한 타일의 개수를 더한다.
// n이 홀수면 고유한 타일은 2개고, n이 짝수면 고유한 타일은 3개다.
public class Main {

    private static int t;
    private static int[] acc = new int[1001];
    private static int[] dp = new int[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        dp[1] = 1;
        dp[2] = 5;
        dp[3] = 11;
        acc[1] = 1;
        acc[2] = 5;
        acc[3] = 12;
        for(int i = 4; i <= 1000; i++) {
            // 짝수번째일 때는 그 순서에 다른 걸 조합하지 않고 고유하게 만들 수 있는 타일이 3개, 홀수일 때는 2개다
            int pure = i % 2 == 0 ? 3 : 2;
            dp[i] += dp[i - 1] + dp[i - 2] * 4 + 2 * acc[i - 3] + 3 * acc[i - 4] + pure;
            // 짝수번째 건 짝수번째끼리, 홀수번째 건 홀수번째끼리 더하기
            acc[i] = acc[i - 2] + dp[i];
        }

        t = Integer.parseInt(bf.readLine());
        for(int i = 0; i < t; i ++) {
            int num = Integer.parseInt(bf.readLine());
            System.out.println(dp[num]);
        }
    }
}
