import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int[] arr;
    private static int l;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        l = Integer.parseInt(bf.readLine());

        // j번째 객차까지 봤을 때 i개의 객차를 선택할 때 최대 승객 수
        dp = new int[4][n + 1];

        for(int i = 1; i <= 3; i++) {
            int start = l * i; // 이거 이전은 담을 수 없다.
            // 새로 들어오는 거 누적합 적용
            int window = 0;
            for(int j = l * (i - 1) + 1; j <= start; j++) {
                window += arr[j];
            }

            // 새로운 객차를 포함했을 때와 포함하지 않았을 때를 비교해서 최대값을 선택한다.
            for(int j = start; j <= n; j++) {
//                System.out.println(i + "  " + j + " " + window);
                // 새로운 객차 포함 않고 i개의 객차를 골랐을 때 vs 새로운 객차 포함하고 이거 제외한 길이에서 i - 1개의 객차 골랐을 때
                dp[i][j] = Math.max(dp[i][j - 1], window + dp[i - 1][j - l]);
                if(j == n) break;
                window -= arr[j - l + 1];
                window += arr[j + 1];
            }
        }

        bw.write(String.valueOf(dp[3][n]));
        bw.close();
    }
}
