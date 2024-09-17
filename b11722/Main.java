import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n + 1];
        int[][] dp = new int[n + 1][31];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < arr[i]; j++) {
                dp[i][j] = dp[i - 1][j];

            }
            int tmpMax = dp[i - 1][arr[i]];
            for(int j = arr[i] + 1; j <= 30; j++) {
                dp[i][j] = dp[i - 1][j];
                tmpMax = Math.max(dp[i - 1][j] + 1, tmpMax);
            }
            if(tmpMax == 0) {
                dp[i][arr[i]] = tmpMax + 1;
            } else {
                dp[i][arr[i]] = tmpMax;
            }
        }

        int max = 0;
        for(int i = 1; i <= 30; i ++) {
            max = Math.max(max, dp[n][i]);
        }
        System.out.println(max);
    }
}
