import java.util.*;

class Solution {

    static int n;
    static int m;
    static int[][] edge_list;
    static int k;
    static int[] gps_log;
    static ArrayList[] conn;
    static int[][] dp;

    public int solution(int n1, int m1, int[][] edge_list1, int k1, int[] gps_log1) {
        n = n1;
        m = m1;
        edge_list = edge_list1;
        k = k1;
        gps_log = gps_log1;
        conn = new ArrayList[n + 1];
        dp = new int[k][n + 1];

        // 연결 정보 저장
        for(int i = 0; i < n + 1; i++) {
            conn[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < m; i++) {
            int node1 = edge_list[i][0];
            int node2 = edge_list[i][1];

            conn[node1].add(node2);
            conn[node2].add(node1);
        }

        // i 시점일 때 j 거점일 경우 최소 변경 횟수 구하기
        for(int i = 0; i < k; i ++) {
            for(int j = 1; j <= n; j ++) {
                dp[i][j] = 200;
            }
        }

        dp[0][1] = 0;
        for(int i = 1; i < k; i++) {
            for(int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]); // 거점 이동 안 할 경우

                // 이전 시점의 거점에서 이동할 경우
                for(int a = 0; a < conn[j].size(); a++) {
                    int node = (int)conn[j].get(a);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][node]);
                }

                // 현재 거점과 gps의 거점이 다르면 gps를 수정해야하므로 +1
                if(j != gps_log[i]) dp[i][j] ++;
            }
        }

        if(dp[k - 1][gps_log[k - 1]] == 200) return -1;
        return dp[k - 1][gps_log[k - 1]];
    }
}