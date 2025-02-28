import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int k;
    static List<Integer> value;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        value = new ArrayList<>();
        dp = new int[k + 1];
        for(int i = 0; i < n; i++) {
            int coin = Integer.parseInt(bf.readLine());
            value.add(coin);
        }
        Collections.sort(value);
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for(int v = 0; v < n; v++) {
            for(int i = value.get(v); i <= k; i++) {
                if(dp[i - value.get(v)] != Integer.MAX_VALUE) dp[i] = Math.min(dp[i], 1 + dp[i - value.get(v)]);
            }
        }

        if(dp[k] == Integer.MAX_VALUE) dp[k] = -1;
        bw.write(String.valueOf(dp[k]));
        bw.close();
    }
}
