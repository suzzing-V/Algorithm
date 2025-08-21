import java.io.*;
import java.util.*;

public class Main {

    private static int[][] dp;
    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        dp = new int[n][n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
            dp[i][i] = 0;
        }

        System.out.println(getMinExtra(0, n - 1));
    }

    // dp 이용해서 한번 구했던 구간은 다시 구하지 않는다.
    private static int getMinExtra(int start, int end) {
        // start가 end를 넘을 경우 그냥 아무것도 없는 상태이다. 따라서 0 반환. -1반환하면 최솟값 구하는 것이기에 지장을 준다.
        if(start > end) return 0;
        // 이미 한번 구했었으면 그 값 반환
        if(dp[start][end] != -1) {
            return dp[start][end];
        }

        // 양쪽 값 같으면 추가할 필요 없으므로 start+1~end-1의 추가 개수와 같다.
        if(arr[start] == arr[end]) {
            return dp[start][end] = getMinExtra(start + 1, end - 1);
        }
        // 다를 경우 start의 값을 end쪽에 추가했을 때와 end의 값을 start쪽에 추가했을 때를 비교해 작은 쪽을 선택한다.
            return dp[start][end] = Math.min(getMinExtra(start + 1, end), getMinExtra(start, end - 1)) + 1;
    }
}
