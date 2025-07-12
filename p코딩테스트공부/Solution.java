import java.util.*;

class Solution {

    // 알고력 i, 코딩력 j을 만드는 데 드는 최소 비용
    private int[][] dp;
    private int n = 0;
    private int m = 0;

    public int solution(int alp, int cop, int[][] problems) {

        // 목표점수 구하기
        for(int i = 0; i < problems.length; i++) {
            n = Math.max(problems[i][0], n);
            m = Math.max(problems[i][1], m);
        }

        // 목표점수가 초기 점수보다 작으면 이미 목표 달성한 것이므로 0 리턴
        if(n <= alp && m <= cop) return 0;

        // 알고력의 초기점수가 목표점수보다 크면 초기점수를 목표점수로 설정. 이렇게 안하면 초기점수의 코스트 초기화할 수 없음
        if(alp > n) n = alp;
        if(cop > m) m = cop;

        dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // System.out.println(n + " " + m);
        dp[alp][cop] = 0;

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                if(dp[i][j] == Integer.MAX_VALUE) continue;

                // 공부는 보정 안해도 되는 이유: 어떤 경우라도 dp[n][j]보다 dp[n][j] + 1보다 작으므로 비교하는 게 의미가 없다.
                // 알고력 공부
                if(i + 1 <= n) dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                // 코딩력 공부
                if(j + 1 <= m) dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                // 문제 해결
                for(int k = 0; k < problems.length; k++) {
                    int alg_req = problems[k][0];
                    int cop_req = problems[k][1];
                    int alg_rwd = problems[k][2];
                    int cop_rwd = problems[k][3];
                    int cost = problems[k][4];
                    // 알고력과 코딩력이 지금 점수보다 낮거나 같아야 풀 수 있다
                    if(alg_req <= i && cop_req <= j) {
                        // 목표점수보다 더 높은 점수 얻을 수 있는 경우도 목표를 달성한 것이므로 이 경우도 고려한다.
                        int x = Math.min(i + alg_rwd, n);
                        int y = Math.min(j + cop_rwd, m);
                        dp[x][y] = Math.min(dp[x][y], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[n][m];
    }
}