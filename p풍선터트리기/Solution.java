import java.util.*;

// 작은 값 터뜨리는 건 마지막에 쓴다. 그전까지는 큰값만 터뜨리므로 남아있는 건 가장 작은 값아다.
// 검사하려는 값 기준 왼쪽과 오른쪽 구간에서 가장 큰 값을 찾고, 그 수들이 검사하려는 값보다 둘다 작으면 어떻게 해도 검사값을 남길 수 없다.
// 시간복잡도: O(n)
class Solution {

    private int[] left_min;
    private int[] right_min;
    private int n;

    public int solution(int[] a) {
        n = a.length;
        left_min = new int[n];
        right_min = new int[n];
        left_min[0] = a[0];
        right_min[n - 1] = a[n - 1];

        //왼쪽 구간에서 가장 작은 값 구하기
        for(int i = 1; i < n; i++) {
            left_min[i] = Math.min(left_min[i - 1], a[i]);
        }
        //오른쪽 구간에서 가장 작은 값 구하기
        for(int i = n - 2; i >= 0; i --) {
            right_min[i] = Math.min(right_min[i + 1], a[i]);
        }

        // 특정 값에서 양쪽에서의 최솟값이 특정값보다 둘다 작으면 그 수는 마지막까지 남을 수 없다.
        // 양쪽값이 하나밖에 없다면 무조건 가능
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(i == 0 || i == n - 1) {
                cnt ++;
                continue;
            }

            int left = left_min[i - 1];
            int right = right_min[i + 1];

            if(left < a[i] && right < a[i]) continue;
            cnt ++;
        }

        return cnt;
    }
}