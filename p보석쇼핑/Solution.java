import java.util.*;

// 시간복잡도: 10^5
class Solution {

    private String[] gems;
    private Map<String, Integer> counts = new HashMap<>();
    private int n;
    private int min = Integer.MAX_VALUE;
    private int start = 0;
    private int end = 0;

    public int[] solution(String[] gems1) {
        gems = gems1;
        n = gems.length;
        for(int i = 0; i < n; i++) {
            if(counts.get(gems[i]) == null) {
                counts.put(gems[i], 0);
            }
        }

        // 전체 보석 종류 수
        int total = counts.size();
        int left = 0;
        int right = 0;
        int cnt = 1;
        counts.put(gems[0], 1);
        while(right < n) {
            if(cnt == total) {
                if(min > right - left) {
                    min = right - left;
                    start = left;
                    end = right;
                }
                counts.put(gems[left], counts.get(gems[left]) - 1);
                if(counts.get(gems[left]) == 0) cnt --;
                left ++;
            } else {
                right ++;
                if(right >= n) break;
                counts.put(gems[right], counts.get(gems[right]) + 1);
                if(counts.get(gems[right]) == 1) cnt ++;
            }
        }

        int[] answer = {start + 1, end + 1};
        return answer;
    }
}