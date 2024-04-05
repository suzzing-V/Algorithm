import java.io.*;
import java.util.*;

public class Main {
    static String str;
    static boolean[][] palin;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str = bf.readLine();
        palin = new boolean[str.length()][str.length()];
        dp = new int[str.length() + 1];

        makePalin();
        dp[0] = 0;

        for(int end = 0; end < str.length(); end ++) {
            dp[end + 1] = 5000;
            for(int start = 0; start <= end; start ++) {
                if(palin[start][end]) {
                    dp[end + 1] = Math.min(dp[end + 1], dp[start] + 1);
                }
            }
        }

        bw.write(String.valueOf(dp[str.length()]));
        bw.close();
    }

    public static void makePalin() {
        for(int i = 0; i < str.length(); i++) {
            palin[i][i] = true;
        }

        for(int i = 0; i < str.length() - 1; i++) {
            if(str.charAt(i) == str.charAt(i + 1)) {
                palin[i][i + 1] = true;
            }
        }

        for(int len = 3; len <= str.length(); len ++) {
            for(int i = 0; i <= str.length() - len; i ++) {
                if(str.charAt(i) == str.charAt(i + len - 1)
                && palin[i + 1][i + len - 2]) {
                    palin[i][i + len - 1] = true;
                }
            }
        }
    }
}
