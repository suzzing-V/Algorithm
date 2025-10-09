import java.util.*;

// 시간복잡도: 3 * 10^5
class Solution {

    private int[] sales;
    private List<Integer>[] conn;
    private long[][] dp;
    public int solution(int[] sales1, int[][] links) {
        sales = sales1;
        conn = new ArrayList[sales1.length + 1];
        dp = new long[sales.length + 1][2];
        for(int i = 1; i < conn.length; i++) {
            conn[i] = new ArrayList<>();
        }

        for(int i = 0; i < links.length; i++) {
            conn[links[i][0]].add(links[i][1]);
        }

        dfs(1);
        int answer = (int)Math.min(dp[1][0], dp[1][1]);
        return answer;
    }

    private void dfs(int curr) {
        // 팀에 자기밖에 없으면 자기꺼만 저장
        if(conn[curr].isEmpty()) {
            dp[curr][0] = 0;
            dp[curr][1] = sales[curr - 1];
            return;
        }

        // System.out.println(curr);
        // 팀장이 참여하는 경우 팀원들의 각 경우 중 최소값 저장
        boolean is_go = false;
        long min_diff = Long.MAX_VALUE;
        for(int child : conn[curr]) {
            dfs(child);
            dp[curr][1] += Math.min(dp[child][0], dp[child][1]);
            if(dp[child][0] >= dp[child][1]) {
                is_go = true;
            }
            min_diff = Math.min(min_diff, dp[child][1] - dp[child][0]);
        }

        // 팀장이 참여하지 않는 경우, 최소값을 저장해도 참여하는 팀원이 있으면 dp[i][0]값과 같고,
        // 아니면 dp[i][0]에 제일 작은 dp[i][1] - dp[i][0]을 더한다.
        if(is_go) {
            dp[curr][0] = dp[curr][1];
        } else {
            dp[curr][0] = dp[curr][1] + min_diff;
        }
        dp[curr][1] += sales[curr - 1];
        // System.out.println(curr + " "+ dp[curr][0] + " " + dp[curr][1]);
    }
}
