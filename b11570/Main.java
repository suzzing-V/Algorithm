import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] note;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        note = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i < n + 1; i++) {
            note[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;
        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                int last = Math.max(i, j);
                if(last == n || (i != 0 && j != 0 && i == j)) {
                    continue;
                }
                int iGap = getGap(i, last + 1);
                dp[last + 1][j] = Math.min(dp[i][j] + iGap, dp[last + 1][j]);
//                System.out.println((last + 1) + " " + j + " " + dp[last + 1][j]);
                int jGap = getGap(j, last + 1);
                dp[i][last + 1] = Math.min(dp[i][j] + jGap, dp[i][last + 1]);
//                System.out.println(i + " " + (last + 1) + " " + dp[i][last + 1]);
            }
        }

//        for(int i = 0; i< n + 1; i++) {
//            for(int j = 0; j < n + 1; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i ++) {
            min = Math.min(min, dp[i][n]);
        }
        for(int i = 0; i < n; i ++) {
            min = Math.min(min, dp[n][i]);
        }

        System.out.println(min);
    }

    private static int getGap(int curr, int next) {
        if(curr == 0) {
            return 0;
        }

        return Math.abs(note[curr] - note[next]);
    }
}
