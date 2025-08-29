import java.util.*;

// 최대 건널 수 있는 친구 수는 2 * 10^8 -> O(n)도 빡세므로 이진탐색
// 0~2*10^8 까지 이진탐색 돌리면서 mid명의 친구가 징검다리를 건널 수 있는지 확인한다.
// mid - 1명의 친구가 건넌 뒤 그다음 사람이 건너려할 때 0이 연속으로 k개 이상 있는 부분이 있으면 건널 수 없다.
// 징검다리를 건널 수 없는 최소 친구 수를 구하고(lower bound), 그 수 - 1하면 건널 수 있는 친구 수의 최댓값이다.
// 문제 잘 읽기.
// 시간복잡도: 9 * 4 * 10^5
class Solution {

    private int[] stones;
    private int k;

    public int solution(int[] stones1, int k1) {
        stones = stones1;
        k = k1;

        return binarySearch(0, 2 * (int)Math.pow(10, 8)) - 1;
    }

    private int binarySearch(int start, int end) {
        if(start == end) {
            return start;
        }

        int mid = (start + end) / 2;
        boolean isPossible = canGo(mid);

        // 건널 수 있으면 더 큰 범위에서 찾기
        if(isPossible) {
            return binarySearch(mid + 1, end);
        }
        // 건널 수 "없는" 최솟값 찾아야하므로 건널 수 없을 경우 mid 포함
        return binarySearch(start, mid);
    }

    private boolean canGo(int f) {
        int[] tmp = new int[stones.length];
        for(int i = 0; i < stones.length; i++) {
            tmp[i] = Math.max(0, stones[i] - (f - 1));
        }

        // 연속으로 k개 초과만큼 0인 경우가 있는지
        for(int i = 0; i < tmp.length; i++) {
            if(tmp[i] == 0) {
                int cnt = 0;
                while(i < tmp.length && tmp[i] == 0) {
                    i ++;
                    cnt ++;
                }

                if(cnt >= k) return false;
                i --;
            }
        }
        return true;
    }
}