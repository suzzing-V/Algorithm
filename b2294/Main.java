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
            if(coin > 10000 || value.contains(coin)) continue;
            value.add(coin);
        }
        Collections.sort(value);
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for(int i = 1; i <= k; i++) {
            for(int v = 0; v < value.size(); v++) {
                int va = value.get(v);
                if(va > i) break;
                for(int r = 1; r * va <= i; r ++) {
//                    System.out.println(dp[i] + " " +  r + dp[i - value[v] * r]);
                    if(dp[i - va * r] != Integer.MAX_VALUE) dp[i] = Math.min(dp[i], r + dp[i - va * r]);
                }
            }
//            System.out.println("dp: " + i + " " + dp[i]);
        }

        if(dp[k] == Integer.MAX_VALUE) dp[k] = -1;
        bw.write(String.valueOf(dp[k]));
        bw.close();
    }
}
