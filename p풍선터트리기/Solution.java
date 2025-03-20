import java.util.*;

class Solution {

    private PriorityQueue<Integer> right = new PriorityQueue<>();
    private Map<Integer, Integer> visited = new HashMap<>();

    public int solution(int[] a) {
        if(a.length <= 2) return a.length;

        int cnt = 1;
        int left_min = a[0];
        for(int i = 1; i < a.length; i++) {
            right.add(a[i]);
            visited.put(a[i], 0);
        }
        int right_min = right.peek();

        for(int i = 1; i < a.length - 1; i++) {
            visited.remove(a[i]); // 오른쪽에서 현재 수 삭제
            while(true) { // 오른쪽 수 중 가장 작은 수 갱신
                int peek = right.peek();
                if(visited.get(peek) != null) {
                    right_min = peek;
                    break;
                }
                right.poll();
            }
            // System.out.println(left_min + " " + a[i] + " "+ right_min);

            if(!(a[i] > left_min && a[i] > right_min)) cnt ++; // 세 수 중 현재 수가 가장 크면 현재 수는 최후까지 남을 수 없다.
            left_min = Math.min(a[i], left_min); // 현재 수가 왼쪽 수들 중 가장 작으면 갱신
        }

        cnt ++; // 마지막 수도 무조건 가능
        return cnt;
    }
}
