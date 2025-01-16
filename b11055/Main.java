import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] dp;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = arr[0];
        for(int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        System.out.println(max);
    }
}
