import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        int b = (int)begin; int e = (int)end;
        int[] answer = new int[e - b + 1];
        int k = 0;
        
        for(int i = b; i <= e; i++) {
            answer[k++] = findMaxDivide(i);
        }
        return answer;
    }
    
    public int findMaxDivide(int n) {
        List<Integer> list = new ArrayList<>();
        if(n == 1) return 0;
        for(int i = 2; i <= Math.sqrt(n); i ++) {
            if(n % i == 0) {
                list.add(i);
                if(n / i <= 10000000) return n / i;
            }
        }
        if(list.size() > 0) return list.get(list.size() - 1);
        return 1;
    }
}