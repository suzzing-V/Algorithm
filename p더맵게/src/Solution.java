import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int count = 0;
        List<Integer> sco = new ArrayList<>();
        Arrays.sort(scoville);
        for(int i = 0; i < scoville.length; i++) {
            sco.add(scoville[i]);
        }
        
        int i = 0;
        while(sco.get(i) < K && i + 1 < sco.size()) {
            int first = sco.get(i), second = sco.get(i + 1);
            int tmp = Math.min(first, second) + Math.max(first, second) * 2;
            sco.set(i, tmp);
            sco.remove(i + 1);
            if(tmp >= K) i++;
            count++;
        }
        
        if(sco.size() == 2) count++;
        if(sco.size() == 1 && sco.get(0) > K) return -1;
        return count;
    }
}