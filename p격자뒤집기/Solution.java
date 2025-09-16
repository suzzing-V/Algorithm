import java.util.*;

// 시간복잡도: 2^14 * (1400 + 10000)
class Solution {

    private int[][] visible;
    private int[][] hidden;
    private int k;
    private int n;
    private int m;
    private int max = Integer.MIN_VALUE;
    public int solution(int[][] visible1, int[][] hidden1, int k1) {
        visible = visible1;
        hidden = hidden1;
        k = k1;
        n = visible.length;
        m = visible[0].length;
        // 행 뒤집기
        dfs(0, 0);
        return max;
    }

    private void dfs(int idx, int status) {
        if(idx == n) {
            // 해당 행 상태에서 열 최선으로 뒤집은 결과
            int sum = changeCol(status);
            int cost = k * Integer.bitCount(status);
            max = Math.max(max, sum - cost);
            return;
        }

        // idx 번째 행 뒤집기
        dfs(idx + 1, status | (1 << idx));
        // idx 번째 행 안 뒤집기
        dfs(idx + 1, status);
    }

    private int changeCol(int status) {
        // System.out.println(status);
        // 안뒤집었을 경우 해당 열의 점수 총함
        int[] tot_f = new int[m];
        // 뒤집었을 경우 해당 열의 점수 총합
        int[] tot_t = new int[m];
        // 안 뒤집었을 경우 해당 열에서 가장 작은 값
        int[] min_f = new int[m];
        // 뒤집었을 경우 해당 열에서 가장 작은 값
        int[] min_t = new int[m];
        Arrays.fill(min_f, Integer.MAX_VALUE);
        Arrays.fill(min_t, Integer.MAX_VALUE);

        for(int j = 0; j < m; j ++) {
            // 해당 열 뒤집는 경우와 뒤집지 않는 경우 합
            for(int i = 0; i < n; i++) {
                // 행이 뒤집혀있지 않은 경우
                if(((1 << i) & status) == 0) {
                    // 1번 뒤집
                    tot_t[j] += hidden[i][j];
                    // 2번 뒤집
                    tot_f[j] += visible[i][j];
                    if((i + j) % 2 == 1) {
                        min_f[j] = Math.min(min_f[j], visible[i][j]);
                        min_t[j] = Math.min(min_t[j], hidden[i][j]);
                    }
                } else {
                    tot_t[j] += visible[i][j];
                    tot_f[j] += hidden[i][j];
                    if((i + j) % 2 == 1) {
                        min_f[j] = Math.min(min_f[j], hidden[i][j]);
                        min_t[j] = Math.min(min_t[j], visible[i][j]);
                    }
                }
            }
        }

        // m과 n이 짝수일 경우 한 열씩 가장 작은 수 뺐을 때의 뒤집/안뒤집 중 점수 총합 큰 거 선택해보면서 최댓값 구하기

        if(m % 2 == 0 && n % 2 == 0) {
            int result = Integer.MIN_VALUE;
            for(int j = 0; j < m ;j++) {
                // j열에서 숫자 뺄 경우 점수 총합
                int sum = 0;
                for(int b = 0; b < m; b++) {
                    if(b == j) {
                        sum += Math.max(tot_f[b] - min_f[b], tot_t[b] - min_t[b] - k);
                    } else {
                        sum += Math.max(tot_f[b], tot_t[b] - k);
                    }
                }
                result = Math.max(result, sum);
            }
            return result;
        }
        int result = 0;
        for(int b = 0; b < m; b++) {
            result += Math.max(tot_f[b], tot_t[b] - k);
        }

        // 열 뒤집은 수만큼 비용 빼기
        return result;
    }
}