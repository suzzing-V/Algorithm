import java.util.*;
import java.io.*;

// 시간복잡도: 1000. 사실상 left만 움직인다. top down dp는 한번씩만 방문
// dp[i][j]: i~j번의 이름을 적었을 때 최소 제곱값
public class Main {

    private static int n;
    private static int m;
    private static int[][] dp;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][n + 1];
        arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[n - 1][n - 1] = 0;
        dfs(0, n - 1);
        System.out.println(dp[0][n - 1]);
    }

    private static void dfs(int left, int right) {
        if(left >= n || right >= n) {
            dp[left][right] = 0;
            return;
        }
        if(dp[left][right] != Integer.MAX_VALUE) return;
        if(left > right) {
            dp[left][right] = 0;
            return;
        }

        int sum = 0;
        int idx = left;
//        int slice = 0;
        // 현재 범위에서 left부터 한줄 채울 때까지 더하고, 그다음줄부터는 남은 범위에서의 최소 제곱값 구하기
        while(idx <= right) {
            sum += arr[idx];
            if(sum > m) break;
            idx ++;
            dfs(idx, right);
            if(dp[left][right] > (int)Math.pow(m - sum, 2) + dp[idx][right]) {
//                slice = idx;
                dp[left][right] = Math.min(dp[left][right], (int)Math.pow(m - sum, 2) + dp[idx][right]);
            }
            sum ++;
        }

        // idx가 끝까지 갔으면 마지막 줄
        if(idx == right + 1) dp[left][right] = 0;

//        System.out.println("slice: " + idx + "dp[" + left + "][" + right + "]=" + dp[left][right]);
    }
}
