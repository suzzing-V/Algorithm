import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        Map<Double, Integer> map = new HashMap<>();
        for(int i = 0; i < weights.length; i++) {
            double a = (weights[i] * 2.0) / 3.0;
            double b = (weights[i] * 1.0) / 2.0;
            double c = (weights[i] * 3.0) / 4.0;
            if(map.containsKey(a)) answer += map.get(a);
            if(map.containsKey(b)) answer += map.get(b);
            if(map.containsKey(c)) answer += map.get(c);
            if(map.containsKey((double)weights[i])) answer += map.get((double)weights[i]);
            map.put((double)weights[i], map.getOrDefault((double)weights[i], 0) + 1);
        }

        return answer;
    }
}