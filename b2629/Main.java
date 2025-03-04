import java.io.*;
import java.util.*;

public class Main {

    static int c;
    static int[] chu;
    static int g;
    static boolean[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        c = Integer.parseInt(bf.readLine());
        chu = new int[c + 1];
        dp = new boolean[c + 1][40001];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i<= c; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = true;
        for(int i = 1; i <= c; i++) {
            for(int j = 0; j <= 40000; j++) {
                dp[i][j] = dp[i - 1][j] || dp[i - 1][Math.abs(j - chu[i])]; // ? + chu[i] = j, ? + j = chu[i]
                if(j + chu[i] <= 15000) dp[i][j] = dp[i][j] || dp[i - 1][j + chu[i]]; // chu[i] + j = ?
            }
        }

        g = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < g; i++) {
            if(dp[c][Integer.parseInt(st.nextToken())]) {
                bw.write("Y ");
            } else {
                bw.write("N ");
            }
        }
        bw.close();
    }
}
