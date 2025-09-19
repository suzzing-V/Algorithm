import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int[][] combi = new int[53][53];
    private static int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        for(int i = 0; i <= 52; i ++) {
            combi[i][0] = 1;
            combi[i][i] = 1;
            for(int j = 1; j < i; j++) {
                combi[i][j] = (combi[i - 1][j] + combi[i - 1][j - 1]) % MOD;
            }
        }

        int result = 0;
        for(int i = 1; i <= n / 4; i ++) {
            result += ((i % 2 == 0 ? -1 : 1) * combi[13][i] * combi[52 - 4 * i][n - 4 * i]);
            result %= MOD;
            result = (result + MOD) % MOD;
        }
        System.out.println(result);
    }
}
