import java.util.*;

// 10^5 * 60
class Solution {

    private int[] cnt;
    private int[] sb;

    public int[] solution(int target) {
        cnt = new int[100001];
        sb = new int[100001];

        // 1~20까지는 싱글 1개가 최선
        for(int i = 1; i <= 20; i++) {
            cnt[i] = 1;
            sb[i] = 1;
        }

        for(int i = 21; i <= 100000; i++) {
            // 싱글 하나 추가할 경우
            cnt[i] = Integer.MAX_VALUE;
            sb[i] = -1;
            for(int j = 1; j <= 20; j++) {
                // 횟수 적으면 갱신
                if(cnt[i] > cnt[i - j] + 1) {
                    cnt[i] = cnt[i - j] + 1;
                    sb[i] = sb[i - j] + 1;
                    // 횟수 같은데 싱글/불 개수가 더 많으면 갱신
                } else if(cnt[i] == cnt[i - j] + 1 && sb[i] < sb[i - j] + 1) {
                    sb[i] = sb[i - j] + 1;
                }
            }

            // 더블 하나 추가할 경우
            for(int j = 2; j <= 40; j+=2) {
                if(i - j < 0) break;
                // 횟수 적으면 갱신
                if(cnt[i] > cnt[i - j] + 1) {
                    cnt[i] = cnt[i - j] + 1;
                    sb[i] = sb[i - j];
                    // 횟수 같은데 싱글/불 개수가 더 많으면 갱신
                } else if(cnt[i] == cnt[i - j] + 1 && sb[i] < sb[i - j]) {
                    sb[i] = sb[i - j];
                }
            }

            // 트리플 하나 추가
            for(int j = 3; j <= 60; j+=3) {
                if(i - j < 0) break;
                // 횟수 적으면 갱신
                if(cnt[i] > cnt[i - j] + 1) {
                    cnt[i] = cnt[i - j] + 1;
                    sb[i] = sb[i - j];
                    // 횟수 같은데 싱글/불 개수가 더 많으면 갱신
                } else if(cnt[i] == cnt[i - j] + 1 && sb[i] < sb[i - j]) {
                    sb[i] = sb[i - j];
                }
            }

            // 불 하나 추가
            if(i - 50 >= 0) {
                if(cnt[i] > cnt[i - 50] + 1) {
                    cnt[i] = cnt[i - 50] + 1;
                    sb[i] = sb[i - 50] + 1;
                    // 횟수 같은데 싱글/불 개수가 더 많으면 갱신
                } else if(cnt[i] == cnt[i - 50] + 1 && sb[i] < sb[i - 50] + 1) {
                    sb[i] = sb[i - 50] + 1;
                }
            }
        }
        int[] answer = {cnt[target], sb[target]};
        return answer;
    }
}