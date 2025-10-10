import java.util.*;

// 시간 복잡도: 200000 * log2(10^9)
// i번째까지 봤을 때, 만들 수 있는 수와 경우의 수를 저장한다.
// 이전 위치에서 현재 값과 같은 수를 만들 수 있으면, 그 경우의 수를 더한다. 그리고 그 두수를 더해 만든 수를 이전 위치에서 만든 수의 시작 위치 - 1에서 같은 게 있는지 확인하고 반복한다.

class Solution {

    private Map<Long, Node>[] possible; // possible[i] : i번째에서 만들 수 있는 수. map의 key값은 만들 수 있는 수, value값은 이 수를 만들기 위해서 더한 시작점과 이 수를 만드는 경우의 수
    private int[] dp; // dp[i] : i번째까지 봤을 때, 만들 수 있는 안티세포의 조합 수
    private long MOD = 1000000007;

    public int[] solution(int[] a, int[] s) {
        int[] result = new int[s.length];
        int start = 0;
        int end = 0;
        possible = new HashMap[a.length];
        for(int i = 0; i < possible.length; i++) {
            possible[i] = new HashMap<>();
        }
        dp = new int[a.length];

        for(int t = 0; t < s.length; t++) {
            // 첫번째는 왼쪽에 아무것도 없으므로 자기 자신 저장하고 넘어간다.
            possible[start].put((long)a[start], new Node(1, start));
            end = start + s[t] - 1;
            // System.out.println(start + " " + end);
            dp[start] = 1;
            for(int i = start + 1; i <= end; i++) {
                // i에서 만들 수 있는 수를 저장하기.
                possible[i].put((long)a[i], new Node(dp[i - 1], i));
                dp[i] = dp[i - 1];
                // i - 1에서 현재 수와 같은 수가 있다면 dp[i]에 ++하고 더한 결과와 이걸 만들기 위한 시작점 저장하기
                // System.out.println(i + " 시작"  + possible[0].size());

                dfs((long)a[i], i - 1, i, start);
                // System.out.println(dp[i]);
            }

            result[t] =(int) (dp[end] % MOD);

            start += s[t];
        }
        return result;
    }

    private void dfs(long value, int prev, int curr, int start) {
        if(prev < start || !possible[prev].containsKey(value)) {
            return;
        }
        // prev에 현재 수와 같은 수가 있다면 dp[curr]에 ++
        Node pair = possible[prev].get(value);
        // 현재 페어를 가지고 value * 2 수를 만드는 경우의 수는 이전 경우에서 해당 수를 만드는 경우의 수
        dp[curr] += pair.cnt % MOD;
        dp[curr] %= MOD;

        // 더한 결과 저장하기
        possible[curr].put(value * 2, new Node(pair.cnt, pair.start));
        // System.out.println(value * 2 + " " + (pair.start - 1) + " " + curr + " "+  start);
        dfs(value * 2, pair.start - 1, curr, start);
        return;
    }

    private class Node {
        int cnt;
        int start;

        Node(int cnt, int start) {
            this.cnt = cnt;
            this.start = start;
        }
    }
}
