import java.io.*;
import java.util.*;

public class Main {

    static int[][] w;
    static int n;
    static int[][] dp;
    static int INF = 16000001;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        w = new int[n][n];
        dp = new int[n][(1<<n)-1];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));
    }

    public static int dfs(int curr, int visited) {

        if(visited == (1<<n)-1) {
            if(w[curr][0] == 0) {
                return INF;
            } else {
                return w[curr][0];
            }
        }

        if(dp[curr][visited] != -1) {
            return dp[curr][visited];
        }
        dp[curr][visited] = INF;
        for(int i = 1; i < n; i++) {
            if((visited & (1 << i)) == 0 && w[curr][i] != 0) {
                dp[curr][visited] = Math.min(dp[curr][visited], dfs(i, (visited | (1 << i))) + w[curr][i]);
            }
        }

        return dp[curr][visited];
    }
}
