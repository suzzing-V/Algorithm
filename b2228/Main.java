import java.util.*;
import java.io.*;

public class Main {

    private static int[][] dp;
    private static int[] sum;
    private static int n;
    private static int[] arr;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][m + 1];
        arr = new int[n + 1];
        sum = new int[n + 1];
        arr[1] = sum[1] = Integer.parseInt(bf.readLine());
        for(int i = 2; i <= n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            sum[i] = sum[i - 1] + arr[i];
        }
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                dp[i][j] = -10000000;
            }
        }

        dp[1][1] = sum[1];

        for(int i = 2; i <= n; i++) {
            dp[i][1] = dp[i - 1][1]; // i번째 원소 포함 안함
            for(int j = 0; j <= i - 1; j ++) {
                dp[i][1] = Math.max(dp[i][1], sum[i] - sum[j]); //1개의 구간 만들었을 때 최대값(i번째 원소 포함)
            }
        }

        for(int i = 2; i <= n; i++) {
            int limit = i % 2 == 0 ? i / 2 : i / 2 + 1; //원소 개수가 홀수면 원소개수/2+1개의 구간까지 만들 수 있고, 짝수면 원소개수/2개의 구간까지 만들 수 있다.
            for(int j = 2; j <= Math.min(limit, m); j++) {
                dp[i][j] = dp[i - 1][j]; // i번째 원소를 포함하지 않았을 때 j개의 구간을 만드는 최대값
                for(int k = i; k >= (j - 1) * 2 + 1; k--) { // j번째 구간 시작점 정하기
                    dp[i][j] = Math.max(sum[i] - sum[k - 1] + dp[k - 2][j - 1], dp[i][j]);
                    // (j번째 구간 합 + j번째 구간을 정했을 때 남은 구간에서 j-1개의 구간을 만들었을 때 최대값)
                    // j번째 구간 시작을 하나하나 정해보면서 비교
                }
            }
        }

        bw.write(String.valueOf(dp[n][m]));
        bw.close();
    }
}
