import java.util.*;

class Solution {

    private int[][] dp;
    private int talp = 0;
    private int tcop = 0;

    public int solution(int alp, int cop, int[][] problems) {
        // 도달해야하는 알고력과 코딩력 구하기
        for(int i = 0; i < problems.length; i++) {
            talp = Math.max(problems[i][0], talp);
            tcop = Math.max(problems[i][1], tcop);
        }

        dp = new int[talp + 1][tcop + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 초기 코딩력과 초기 알고력이 타겟값보다 큰 경우 타겟값으로 보정. 넘었을 때 타겟값은 충족하는 것이므로 타겟값까지의 걸리는 시간이다.
        alp = Math.min(alp, talp);
        cop = Math.min(cop, tcop);
        dp[alp][cop] = 0; // 초기 알고력, 초기 코딩력에 도달하기 위해 필요한 시간은 0.

        for(int i = alp; i <= talp; i++) {
            for(int j = cop; j <= tcop; j++) {
                // 트레이닝. 알고력, 코딩력 +1 이 범위 안인 경우 갱신.
                if(i + 1 <= talp) dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                if(j + 1 <= tcop) dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                // 문제 풀기
                for(int k = 0; k < problems.length; k++) {
                    if(problems[k][0] <= i && problems[k][1] <= j) { // 현재 상태에서 풀 수 있는 문제 선별
                        int newAlp = Math.min(talp, i + problems[k][2]);
                        int newCop = Math.min(tcop, j + problems[k][3]); // 타겟 넘는 값 보정. 타겟값보다 더 크면 타겟값을 충족한다는 뜻이므로 타겟값의 시간 갱신.
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + problems[k][4]);
                    }
                }
            }
        }
        return dp[talp][tcop];
    }
}
