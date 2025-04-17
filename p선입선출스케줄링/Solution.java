import java.util.*;

class Solution {

    private int[] cores;
    private int n;

    public int solution(int n1, int[] cores1) {
        cores = cores1;
        n = n1;

        // 이분탐색의 최대값 구하기 위함. n개의 작업을 최대시간으로 처리하는 경우는 제일 효율이 떨어지는(cores[i]가 제일 큰) 코어로 모든 작업을 처리하는 경우이므로 제일 효율 떨어지는 코어의 처리시간 * n
        int max_time = 0;
        for(int i = 0; i < cores.length; i++) {
            max_time = Math.max(max_time, cores[i]);
        }

        // 작업 끝나는 시간 구하기
        int t = binarySearch(1, max_time * n);
        // System.out.println(t);

        // t - 1시간동안 처리할 수 있는 작업량 구하기
        int cnt = 0;
        for(int i = 0; i < cores.length; i++) {
            cnt += (t - 1) / cores[i] + 1; // t - 1시간동안 i 코어로 처리할 수 있는 작업량
        }

        // 남은 작업량
        int rest = n - cnt;

        // 마지막 작업 처리하는 코어 구하기
        int i = 0;
        int answer = 0;
        while(rest > 0) {
            if(cores[i] == 1 || t % cores[i] == 0) rest --;
            if(rest == 0) {
                answer = i + 1;
                break;
            }
            i ++;
        }

        return answer;
    }

    private int binarySearch(int left, int right) {
        if(left == right) {
            return left;
        }

        int mid = (left + right) / 2;
        // mid 안에 처리할 수 있는 작업 수 구하기
        int cnt = 0;
        for(int i = 0; i < cores.length; i++) {
            cnt += mid / cores[i] + 1;
        }

        if(cnt < n) return binarySearch(mid + 1, right); // mid 시간 안에 처리할 수 있는 작업량이 n보다 작으면 시간 늘려야함
        return binarySearch(left, mid); // 크거나 같다면 mid를 포함한 더 작은 쪽에 답이 있을 확률이 높으므로 왼쪽 탐색한다.
    }
}
