import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long start = times[0];
        long end = times[times.length - 1] * (long)n;
        long mid = 0;
        long possible = 0;
        
        while(start != end) {
            mid = (start + end) / 2;
            possible = getPossibleNum(mid, times);
            
            if(possible < n) start = mid + 1;
            else end = mid;
        }
        
        return start;
    }
    
    public long getPossibleNum(long mid, int[] times) {
        long sum = 0;
        for(int i = 0; i < times.length; i++) sum += mid / times[i];
        return sum;
    }
}