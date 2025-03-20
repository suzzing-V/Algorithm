import java.util.*;

class Solution {

    private int[] left_min; // 0 ~ i까지의 수 중 최소값
    private int[] right_min; // i ~ a.length - 1까지의 수 중 최소값

    public int solution(int[] a) {
        left_min = new int[a.length];
        right_min = new int[a.length];

        if(a.length == 2) return 1;

        int cnt = 2; // 첫번째 수, 마지막 수는 무조건 가능

        // 양쪽 구간 최소값 구하기
        left_min[0] = a[0];
        right_min[a.length - 1] = a[a.length - 1];
        for(int i = 1; i < a.length; i++) {
            left_min[i] = Math.min(a[i], left_min[i - 1]);
        }
        for(int i = a.length - 2; i >= 0; i--) {
            right_min[i] = Math.min(a[i], right_min[i + 1]);
        }

        // 왼쪽 최소값, 오른쪽 최소값, 현재 값 비교해서 현재 값이 제일 크면 최후의 풍선 될 수 없다.
        for(int i = 1; i < a.length - 1; i++) {
            if((left_min[i - 1] < a[i] && right_min[i + 1] < a[i])) continue;
            cnt ++;
        }
        return cnt;
    }
}