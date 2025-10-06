import java.util.*;
import java.io.*;

// 시간복잡도: 10^6 + 10^4
// 리프노드를 방문하는 순서는 정해져있다. target값을 통해 각 리프노드에 방문할 수 있는 최대횟수와 최소횟수를 저장하고, 조건에 만족하는 방문 상태가 될 때까지 리프노드를 방문한다.
// 만약 방문횟수가 최대 방문횟수를 넘는다면 이 순서대로 떨어뜨렸을 때 target값이 되도록 카드를 떨어뜨릴 수 없는 것이므로 -1 리턴
// 이렇게 하면 최소 개수의 카드로 target을 만족시킬 수 있다.
// 사전 순으로 가장 빠르도록 카드를 떨어뜨리기 위해 정해진 순서의 뒤에서부터 최대한 큰 수를 떨어뜨린다.
class Solution {

    private List<Integer>[] childs;
    private int[] next;
    private int[] target;
    private int[] answer;
    private int[] min_visited;
    private int[] max_visited;
    private int target_sum = 0;
    private List<Integer> order = new ArrayList<>();
    private int[] visited;

    public int[] solution(int[][] edges, int[] target1) {
        target = target1;
        for(int i = 0; i < target.length; i++) {
            target_sum += target[i];
        }

        // 노드 정보 저장하기
        childs = new ArrayList[target.length + 1];
        next = new int[target.length + 1];
        for(int i = 0; i < childs.length; i++) {
            childs[i] = new ArrayList<>();
        }

        for(int i = 0; i < edges.length; i++) {
            childs[edges[i][0]].add(edges[i][1]);
        }
        for(int i = 0; i < childs.length; i++) {
            Collections.sort(childs[i]);
        }

        // 리프노드 가능한 방문 횟수 범위 저장하기
        min_visited = new int[target.length + 1];
        max_visited = new int[target.length + 1];
        for(int i = 1; i <= target.length; i ++) {
            min_visited[i] = target[i - 1] / 3 + (target[i - 1] % 3 == 0 ? 0 : 1);
            max_visited[i] = target[i - 1];
            // System.out.println(min_visited[i] + " " + max_visited[i]);
        }

        // 가능한 방문횟수 만족하는 리프노드 방문 횟수, 순서 구하기
        visited = new int[target.length + 1];
        if(!get_visit_count()) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }

        answer = new int[order.size()];

        // 사전 순으로 가장 빠르도록 카드 떨어뜨리기
        fall_cards();
        return answer;
    }

    private void fall_cards() {
        for(int i = order.size() - 1; i >= 0; i--) {
            int leaf = order.get(i);
            visited[leaf] --;
            if(target[leaf - 1] - visited[leaf] >= 3) {
                answer[i] = 3;
            } else {
                answer[i] = target[leaf - 1] - visited[leaf];
            }
            target[leaf - 1] -= answer[i];
        }
    }

    private boolean get_visit_count() {
        int curr = 1;

        while(true) {
            // 리프 노드에 도달하면 방문횟수 count하고, 조건에 맞는지 확인
            if(childs[curr].size() == 0) {
                visited[curr] ++;
                order.add(curr);
                // System.out.println(curr);
                if(max_visited[curr] < visited[curr]) return false;
                break;
            }

            int pre_curr = curr;
            curr = childs[curr].get(next[curr]);
            next[pre_curr] ++;
            if(next[pre_curr] >= childs[pre_curr].size()) {
                next[pre_curr] = 0;
            }
        }

        // 현재 방문 상태가 조건에 맞는지 검사하고, 맞으면 리턴하기
        if(check_status()) {
            return true;
        }

        // 아직 아니면 더 방문하기
        return get_visit_count();
    }

    private boolean check_status() {
        for(int i = 1; i <= target.length; i++) {
            if(!(min_visited[i] <= visited[i] && max_visited[i] >= visited[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean is_same(int[] arr1, int[] arr2) {
        for(int i = 1; i <= arr1.length; i++) {
            if(arr1[i - 1] != arr2[i]) return false;
        }

        return true;
    }
}
