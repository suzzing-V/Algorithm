import java.util.*;

class Solution2 {
    public int solution(int[] nums) {
        int answer = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i : nums) {
        hm.put(i, hm.getOrDefault(i, 0) + 1);
        }
        
        int[] count = new int[200001];
        for(int i : nums) {
            if(count[i] == 0) {
                count[i]++;
                answer++;
                if(answer == nums.length / 2) break;
            }
        }
        return answer;
    }
}