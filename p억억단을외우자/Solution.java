import java.util.*;

class Solution {
    int[] dp;
    int[] cnts;
    public int[] solution(int e, int[] starts) {
        dp = new int[e + 1]; // i ~ e까지의 수 중 가장 많이 등장하는 수
        cnts = new int[e + 1]; // e까지의 수들의 약수의 개수
        makeCnts(e);
        dp[e] = e;
        int maxCnt = cnts[e];
        // 끝은 e로 정해져 있으므로 맨 뒤에서부터 최대값 갱신하면 ?~e의 범위에서 최대값 갱신 가능
        for(int i = e - 1; i >= 1; i --) {
            int cnt = cnts[i];
            if(cnt >= maxCnt) {
                dp[i] = i;
                maxCnt = cnt;
            } else {
                dp[i] = dp[i + 1];
            }
            // System.out.println(i + " " + cnt + " " + max[e -1][0]);
        }

        int[] answer = new int[starts.length];
        for(int i = 0; i < starts.length; i++) {
            answer[i] = dp[starts[i]];
        }
        return answer;
    }

    private void makeCnts(int n) {
        // 1의 배수 ++, 2의 배수 ++, 3의 배수 ++
        for(int i = 1; i <= n; i++) {
            for(int j = i; j <= n; j+= i) {
                cnts[j] ++;
            }
        }
    }
}