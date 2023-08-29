import java.util.*;

class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        long pnum = Long.parseLong(p);
        
        for(int i = 0; i < t.length() - (p.length() - 1); i++) {
            Long num = Long.parseLong(t.substring(i, i + p.length()));
            if(num <= pnum) answer++;
        }
        return answer;
    }
}
