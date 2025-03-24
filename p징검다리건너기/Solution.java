import java.util.*;

class Solution {

    private int k;
    private int[] stones;

    public int solution(int[] stones1, int k1) {
        k = k1;
        stones = stones1;
        int answer = binarySearch(1, 200000000);
        return answer;
    }

    // 처음으로 최장 연속 0의 길이가 k인 값 찾기
    private int binarySearch(int start, int end) {
        if(start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        // 가장 긴 연속하는 0의 길이 찾기
        int[] tmp = new int[stones.length];
        int cnt = 0;
        int max_cnt = 0;
        for(int i = 0; i < stones.length; i++) {
            tmp[i] = Math.max(0, stones[i] - mid);
            if(tmp[i] == 0) cnt ++;
            else {
                max_cnt = Math.max(max_cnt, cnt);
                cnt = 0;
            }
        }
        max_cnt = Math.max(cnt, max_cnt);

        // 최장 연속 0의 길이가 k보다 작으면 더 늘려야 한다.
        if(max_cnt < k) {
            return binarySearch(mid + 1, end);
        } else { // 크거나 같으면 앞에서 찾아야 한다.
            return binarySearch(start, mid);
        }
    }
}
