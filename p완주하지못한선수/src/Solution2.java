import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        for(String p: participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        
        for(String c: completion) {
            map.put(c, map.get(c) - 1);
        }
        
        for(String m: participant) {
            if(map.get(m) != 0) {
                answer = m;
                break;
            }
        }
        return answer;
    }
}