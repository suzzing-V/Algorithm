import java.util.*;

class Solution {
    int[][] dp;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        dp = new int[onboard.length][51];
        t1 += 10;
        t2 += 10;
        temperature += 10;
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 1000 * 100 + 1);
        }

        dp[0][temperature] = 0; // 시작 온도 == 실외온도 // 여기서만 시작 가능 나머지는 무시
        for(int i = 0; i < onboard.length - 1; i++) {
            for(int j = 0; j <= 50; j ++) {
                if(onboard[i] == 1 && (j < t1 || j > t2)) continue; // 손님 있을 때는 범위 안에 있어야함

                // 온도 유지하기
                if(temperature == j) { // 실내온도랑 현재온도랑 같으면 꺼도 유지됨
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                } else { // 실내온도랑 현재온도랑 다르면 켜야 유지됨
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + b);
                }

                // 온도 조절하기
                if(temperature > j) { // 외부온도 > 실내온도면 끄면 +1, 키면 -1
                    if(j < 50) dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j]);
                    if(j > 0) dp[i + 1][j - 1] = Math.min(dp[i + 1][j - 1], dp[i][j] + a);
                } else if(temperature == j) { // 외부온도 == 실내온도면 키면 +1, 키면 -1
                    if(j < 50) dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + a);
                    if(j > 0) dp[i + 1][j - 1] = Math.min(dp[i + 1][j - 1], dp[i][j] + a);
                } else { // 외부온도 < 실내온도면 키면 +1, 끄면 -1
                    if(j < 50) dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + a);
                    if(j > 0) dp[i + 1][j - 1] = Math.min(dp[i + 1][j - 1], dp[i][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < 51; i++) {
            if(onboard[onboard.length - 1] == 1 && (i < t1 || i > t2)) continue;
            answer = Math.min(answer, dp[onboard.length - 1][i]);
        }
        return answer;
    }
}