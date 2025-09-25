import java.util.*;
import java.io.*;

// 시간복잡도: 10^8
public class Main {

    private static int n;
    private static int m;
    private static int[][] dp = new int[101][101];
    private static ArrayList<Integer>[][] cities = new ArrayList[101][101];
    private static int MAX = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 1; i <= 100; i ++) {
            for(int j = 1; j <= 100; j++) {
                dp[i][j] = MAX;
                cities[i][j] = new ArrayList<>();

                if(i == j) {
                    dp[i][j] = 0;
                    cities[i][j].add(i);
                } else {
                    cities[i][j].add(i);
                    cities[i][j].add(j);
                }
            }
        }

        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(dp[a][b] > c) dp[a][b] = c;
        }


        for(int k = 1; k <= 100; k ++) {
            for(int i = 1; i <= 100; i ++) {
            for(int j = 1; j <= 100; j ++) {
                    if(dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                        ArrayList<Integer> updated = new ArrayList<>();
                        for(int c : cities[i][k]) {
                            updated.add(c);
                        }
                        for(int c : cities[k][j]) {
                            if(c != k) updated.add(c);
                        }
                        cities[i][j] = updated;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n ;j++) {
                if(dp[i][j] != MAX) sb.append(dp[i][j]).append(" ");
                else sb.append("0").append(" ");
            }
            sb.append("\n");
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n ;j++) {
                if(dp[i][j] == MAX || dp[i][j] == 0) {
                    sb.append("0").append("\n");
                    continue;
                }

                sb.append(cities[i][j].size()).append(" ");
                for(int c : cities[i][j]) {
                    sb.append(c).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
