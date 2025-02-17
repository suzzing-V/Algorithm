import java.util.*;

class Solution {

    private Map<Integer, Integer> chul = new HashMap<>();
    private Map<Integer, Integer> sis = new HashMap<>();

    public int solution(int[] topping) {
        for(int i = 0; i < topping.length; i++) {
            int t = topping[i];
            sis.put(t, sis.getOrDefault(t, 0) + 1);
        }

        int answer = 0;
        for(int i = 0; i < topping.length; i++) {
            int t = topping[i];
            chul.put(t, chul.getOrDefault(t, 0) + 1);
            sis.put(t, sis.get(t) - 1);
            if(sis.get(t) == 0) sis.remove(t);
            if(chul.size() == sis.size()) {
                answer ++;
            }
        }
        return answer;
    }
}
