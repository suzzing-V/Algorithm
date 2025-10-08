import java.io.*;
import java.util.*;


// 시간복잡도: log(500000) * 2
// 2의 제곱수만큼 합성한 함수의 결과를 저장하고, 주어진 횟수를 이진수로 표현하며 2의제곱수의 덧셈 조합으로 본다. 그리고 2의 제곱수씩 합성한다.
public class Main {

    private static int[][] dp;

    private static int m;

    private static int q;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(bf.readLine());
        dp = new int[(int)(Math.ceil(Math.log(5000000) / Math.log(2)))][m + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        // f1 저장
        for(int i = 1; i <= m; i++) {
            dp[0][i] = Integer.parseInt(st.nextToken());
        }

        // f1, f2, f4, f8 ... 저장
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j <= m; j++) {
                // 2^i번 움직인 결과는 2^(i - 1)번 움직이고, 2^(i - 1)번 움직인 결과와 같다.
                dp[i][j] = dp[i - 1][dp[i - 1][j]];
            }
        }

        // 입력되는 경우의 결과를 비트 이동으로 구하기
        // n = 2^a + 2^b + 2^c
        // fn = f2^a(f2^b(f^c()))
        StringBuilder sb = new StringBuilder();
        q = Integer.parseInt(bf.readLine());
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            // 횟수를 2의 승수들의 덧셈 조합으로 보고, 그만큼씩 이동한다.
            for(int bit = dp.length - 1; bit >= 0; bit --) {
                // 만약 횟수를 이진수로 표현했을 때 bit자리수가 1이면 그만큼 건너뛴다.
                if((n & (1 << bit)) != 0) {
                    x = dp[bit][x];
                }
            }

            sb.append(x).append("\n");
        }

        System.out.println(sb);
    }
}
