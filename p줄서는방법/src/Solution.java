import java.util.*;

class Solution {
    long[] facto = new long[21];
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();
        
        for(int i = 1; i <= n; i++) list.add(i);
        
        n -= 1;
        int idx = 0;
        while(true) {
            long fac = factorial(n--);
            //System.out.println(fac);
            if(k % fac == 0) {
                answer[idx++] = list.get((int)(k / fac) - 1);
                list.remove((int)(k / fac) - 1);
                putAnswer(list, answer, idx);
                break;
            } else {
                answer[idx++] = list.get((int)(k / fac));
                list.remove((int)(k / fac));
                if(fac == 2) {
                    answer[idx++] = list.get(0);
                    answer[idx++] = list.get(1);
                    break;
                }
            }
            k %= fac;
        }
        return answer;
    }
    
    public long factorial(int n) {
        if(n == 0 || n == 1) return 1;
        if(facto[n] != 0) return facto[n];
        facto[n] = n * factorial(n - 1);
        return facto[n];
    }
    
    public void putAnswer(List<Integer> list, int[] answer, int idx) {
        int k = 1;
        for(int i = idx; i < answer.length; i++) {
            answer[i] = list.get(list.size() - k);
            k++;
        }
    }
}