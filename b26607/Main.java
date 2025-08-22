import java.io.*;
import java.util.*;

// 처음 접근법인 a의 합과 b의 합을 곱한 게 가장 큰 경우의 a의 합과 b의 합만을 저장하는 것은 그 시점에는 최적의 해여도 다른 시점에서는 그 수만큼 팀원을 뽑았을 때 다른 경우를 써야 더 최적인 경우가 존재한다.
// a + b는 정해져있으므로, a의 합을 알면 b의 합을 알 수 있다.
// 어짜피 a의 합과 b의 합만 중요하므로, a의 합이 같은 경우는 같게 취급.
// i번쨰까지 봤을 때 j개를 뽑았을 경우 나올 수 있는 a의 합에 모두 표시한다. 현재 팀원을 쓸 경우 i - 1번째까지 봤을 때 j - 1명을 뽑았을 경우 나올 수 있는 a의 합에 현재 팀원의 a값을 더하면 현재 팀원을 써서 나올 수 있는 a의 합들을 표시할 수 있다.
// 현재 팀원을 쓰지 않을 경우 i - 1번째까지 봤을 때 j명을 뽑았을 경우 나올 수 있는 a의 값을 i번째에도 표시하면 된다.
// dp가 끝나면 dp[n - 1][k]에는 끝까지 봤을 때 k명을 뽑았을 때 나올 수 있는 a의 합이 표시되어 있을 것이다. (x * k - a의 합)이 b의 합이므로, 이 둘을 곱한 값 중 가장 큰 값이 답이다.
public class Main {

    private static int[][] stat;
    private static boolean[][][] dp;
    private static int n;
    private static int k;
    private static int x;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        stat = new int[n][2];
        // dp[i][j][m] : i까지 봤을 때, j명을 선택했을 때, a의 합이 m인 조합을 만들 수 있나?
        dp = new boolean[n][k + 1][x * k + 1];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            stat[i][0] = Integer.parseInt(st.nextToken());
            stat[i][1] = Integer.parseInt(st.nextToken());
        }

        dp[0][1][stat[0][0]] = true;
        dp[0][0][0] = true;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= k; j++) {
                for(int m = 0; m <= x * k; m ++) {
                    // 현재 팀원 선택할 경우
                    if(j >= 1 && dp[i - 1][j - 1][m]) {
                        dp[i][j][m + stat[i][0]] = true;
//                        System.out.println(i + " " + j + " " + (m + stat[i][0]));
                    }
                    // 현재 팀원 선택하지 않을 경우
                    if(dp[i - 1][j][m]) {
                        dp[i][j][m] = true;
//                        System.out.println(i + " "+ j + " "+ m);
                    }
                }
            }
        }

        int max = 0;
        for(int i = 1; i <= x * k; i++) {
            if(dp[n - 1][k][i]) {
//                System.out.println(i);
                max = Math.max(max, i * (x * k - i));
            }
        }
        System.out.println(max);
    }
}
