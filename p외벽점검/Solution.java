import java.util.*;

// 시간복잡도: 이론상 15^8이지만 프루닝 적용해서 줄임
// 가장 탐색 거리가 긴 친구부터 특정 weak에 두면서 이 친구의 범위 안에 있는 weak들을 삭제시키고 다음 dfs로 넘어간다.
// 이 친구의 범위 시작점이 start고, end가 start + end일 때, end가 끝부분을 넘어간다면 범위 안의 수는 start보다 크고, end % n보다 작아야 한다.
// 넘어가지 않는다면 start보다 크고, end보다 작으면 된다.
class Solution {

    private int min = 10;
    private Integer[] dist;
    private int n;

    public int solution(int n1, int[] weak, int[] dist1) {
        dist = new Integer[dist1.length];
        for(int i = 0; i < dist1.length; i++) {
            dist[i] = (Integer) dist1[i];
        }
        n = n1;
        // 제일 거리가 긴 친구부터 투입해야 유리하므로 가지치기를 빨리할 수 있어 탐색 시간을 줄일 수 있다.
        Arrays.sort(dist, Collections.reverseOrder());

        List<Integer> rest = new ArrayList<>();
        for(int i = 0; i < weak.length; i++) {
            rest.add(weak[i]);
        }
        dfs(0, rest);

        if(min == 10) return -1;
        return min;
    }

    private void dfs(int idx, List<Integer> rest) {
        // 이미최솟값에 저장된 친구 수가 더 작으면 어짜피 친구 더쓸일밖에 없기 때문에 탐색할 필요가 없다
        if(min <= idx) {
            return;
        }

        // 외벽 다 삭제함 -> 끝
        if(rest.size() == 0) {
            min = Math.min(idx, min);
            return;
        }

        // 친구 다 썼는데 외벽 남으면 갱신 안하고 끝
        if(idx == dist.length) {
            return;
        }

        int range = dist[idx];
        for(int i = 0; i < rest.size(); i++) {
            int start = rest.get(i);
            int end = start + range;

            List<Integer> next_rest = new ArrayList<>(rest);
            for(int j = 0; j < next_rest.size(); j++) {
                // 범위가 끝을 안 넘어갈 경우 start~end 사이의 값이면 삭제
                if(end < n && start <= next_rest.get(j) && next_rest.get(j) <= end) {
                    next_rest.remove(j--);
                    // 범위가 끝을 넘어갈 경우 start보다 같거나 크거나, end % n보다 작거나 같으면 범위 안에 있는 것
                } else if(end >= n && (start <= next_rest.get(j) || next_rest.get(j) <= end % n)) {
                    next_rest.remove(j--);
                }
            }
            // System.out.println(start + " " + end + " " + next_rest.toString());
            dfs(idx + 1, next_rest);
        }
    }
}