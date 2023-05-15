import java.util.*;
import java.util.Collections;

class Solution {
    public int solution(int[] scoville, int K) {
        int count = 0;
        List<Integer> sco = new ArrayList<>();
        Arrays.sort(scoville);
        for(int i = 0; i < scoville.length; i++) {
            sco.add(scoville[i]);
        }
        
        while(sco.get(0) < K && sco.size() >= 2) {
            int first = sco.get(0), second = sco.get(1);
            int tmp = first + second * 2;
            sco.set(0, tmp);
            sco.remove(1);
            count++;
            
            Collections.sort(sco);
        }
        
        if(sco.get(0) < K) return -1;
        return count;
    }
}