import java.util.*;

class Solution {

    private int min = Integer.MAX_VALUE;
    private int[] dist;
    private int[] weak;
    private int n;

    public int solution(int n1, int[] weak1, int[] dist1) {
        dist = dist1;
        n = n1;
        // 제일 거리가 긴 친구부터 배치하기 위해 정렬한다. 최대한 거리가 긴 친구부터 배치하는 것이 무조건 최소 인원을 구하는 방법이다.
        Arrays.sort(dist);
        weak = weak1;
        List<Integer> curr_weak = new ArrayList<>();
        for(int i = 0; i < weak.length; i++) {
            curr_weak.add(weak[i]);
        }
        // 반시계방향은 고려할 필요가 없다. 어짜피 시계방향으로 모두 탐색하면서 시계방향일 때 시작점인 경우와 반시계방향일 때 끝점인 경우가 중복되기 때문이다.
        dfs(dist.length - 1, curr_weak, 0);

        if(min == Integer.MAX_VALUE) min = -1;
        return min;
    }

    private void dfs(int d_idx, List<Integer> curr_weak, int cnt) {
        if(curr_weak.isEmpty()) { // 취약점 모두 제거했으면 최소값 갱신
            min = Math.min(cnt, min);
            return;
        }
        if(d_idx < 0) { // 취약점 아직 남아있는데 친구 다 썼으면 이 루트로는 취약점 모두 제거할 수 없다.
            return;
        }

        int m = dist[d_idx]; // 현재 친구의 거리

        // 시계방향
        // 현재 친구를 남은 취약점을 시작점으로 다 배치해본다.
        for(int i = 0; i < curr_weak.size(); i++) {
            List<Integer> rest_weak = new ArrayList<>(curr_weak);
            int end = curr_weak.get(i) + m; // 현재 친구가 도달할 수 있는 끝점
            int rest = -1; // 분기점을 넘어갈 경우 남은 거 처리 위함.
            if(end >= n) { // 넘어갈 경우 분기점까지 없애고 남은 거리
                rest = end - n;
                end = n - 1;
            }

            int j = i;
            while(true) { // 취약점 삭제
                if(j >= rest_weak.size() || rest_weak.get(j) > end) {
                    break;
                }
                rest_weak.remove(j);
            }
            if(rest != -1) { // 분기점까지 다 삭제하고 남은 경우 분기점부터 다시 삭제
                j = 0;
                while(true) {
                    if(j >= rest_weak.size() || rest_weak.get(j) > rest) {
                        break;
                    }
                    rest_weak.remove(j);
                }
            }
            dfs(d_idx - 1, rest_weak, cnt + 1);
        }
    }
}
