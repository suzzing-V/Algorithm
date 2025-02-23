import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] cost;
    static int[][] dp;
    static ArrayList<Integer>[] lastIdx = new ArrayList[3];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        cost = new int[n][3];
        dp = new int[n][3];
        for(int i = 0; i < n; i ++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < 3; j ++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i != j) dp[1][j] = cost[0][i] + cost[1][j];
                else dp[1][j] = Integer.MAX_VALUE;
            }
            for(int j = 2; j < n; j ++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j - 1][1], dp[j - 1][0]) + cost[j][2];
            }

            for(int j = 0; j < 3; j++) {
                if(j == i) continue;
                min = Math.min(dp[n - 1][j], min);
            }
        }

        bw.write(String.valueOf(min));
        bw.close();
    }
}
