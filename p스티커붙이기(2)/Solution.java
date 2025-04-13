class Solution {
    public int solution(int sticker[]) {
        int[] dp = new int[sticker.length];
        int answer = 0;

        if(sticker.length == 1) return sticker[0];

        // 첫번째 스티커 뗐을 때 -> 마지막 스티커 사용 불가
        dp[0] = sticker[0];
        dp[1] = sticker[0];
        for(int i = 2; i < sticker.length - 1; i++) {
            dp[i] = Math.max(sticker[i] + dp[i - 2], dp[i - 1]); // i번째 스티커 사용/안사용 비교
        }
        answer = dp[sticker.length - 2];

        // 첫번째 스티커 안 뗐을 때 -> 마지막 요소 사용 가능
        dp[0] = 0;
        dp[1] = sticker[1];
        for(int i = 2; i < sticker.length; i ++) {
            dp[i] = Math.max(sticker[i] + dp[i - 2], dp[i - 1]);
        }
        answer = Math.max(answer, dp[sticker.length - 1]);

        return answer;
    }
}
