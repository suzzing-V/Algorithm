import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        dp = new int[n + 1]; // i까지 만들 수 있는 최대 연속 수열의 크기
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            dp[num] = dp[num - 1] + 1; // 현재 숫자 - 1까지 만든 최대 수열의 크기에 + 1하면 현재 숫자까지 만들 수있는 최대 수열의 길이이다.
        }

        // 최대한 정렬하고 나머지는 옮긴다. 옮기는 건 어떻게 하든 상관 없다. 옮기는 개수만 구하면 되기 때문이다.
        int max = 0;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, dp[i]);
        }

        bw.write(String.valueOf(n - max));
        bw.close();
    }
}
