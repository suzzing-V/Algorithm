import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            int[][] stickers = new int[2][n + 2];

            for(int j = 0; j < 2; j ++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for(int k = 2; k < n + 2; k ++) {
                    stickers[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][n + 2];

            for(int j = 2; j < n + 2; j++) {
                    dp[0][j] = stickers[0][j] + Math.max(dp[1][j - 1], dp[1][j - 2]);
                    dp[1][j] = stickers[1][j] + Math.max(dp[0][j - 1], dp[0][j - 2]);
            }

            bw.write(String.valueOf(Math.max(dp[0][n + 1], dp[1][n + 1])) + "\n");
        }
        bw.close();
    }
}
