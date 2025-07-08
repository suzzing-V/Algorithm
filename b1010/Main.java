import java.io.*;
import java.util.*;

public class Main {
    static long[][] dp = new long[31][31];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int south = Integer.parseInt(st.nextToken());
            int north = Integer.parseInt(st.nextToken());
            System.out.println(combi(north, south));
        }
    }

    static long combi(int n, int r) {
        if(n == r || r == 0) {
            return dp[n][r] = 1;
        }

        if(dp[n][r] != 0) {
            return dp[n][r];
        }

        return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
    }
}
