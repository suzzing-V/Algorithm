import java.io.*;
import java.util.*;

// 시간복잡도: 10^6
public class Main {

    private static int n;
    private static int m;
    private static Integer[] man;
    private static Integer[] woman;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        man = new Integer[n];
        woman = new Integer[m];
        dp = new int[n + 1][m + 1];

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            man[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < m; i++) {
            woman[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬하고 짝지어야 가장 차이가 적다
        Arrays.sort(man);
        Arrays.sort(woman);
        // dp[i][j] : i번째 남자와 j번째 여자까지 봤을 때 가장 작은 차이의 합
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                // 지금까지 보는 남자 수와 여자 수가 같으면 모든 사람을 커플로 만들어야 한다.
                // i번째 남자와 j번째 여자를 커플 성사시켜야 한다.
                if(i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + Math.abs(man[i - 1] - woman[j - 1]);
                } else if(i > j) {
                    // 남자 수가 더 많을 경우 이 남자를 커플 성사시키는 경우와 시키지 않는 경우 비교해서 작은 값 저장
                    // 만약에 커플 성사 시킨다면 마지막 여자를 시키는 게 맞다. 오름차순 정렬이고 현재까지 본 여자 중 마지막 여자가 가장 차이가 적기 때문이다.
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + Math.abs(man[i - 1] - woman[j - 1]), dp[i - 1][j]);
                } else {
                    // 여자가 많을 경우 위와 같다.
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + Math.abs(man[i - 1] - woman[j - 1]), dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
