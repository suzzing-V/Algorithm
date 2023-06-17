class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        int[] rest = new int[times.length];
        n -= times.length;
        for(int i = 0; i < times.length; i++) rest[i] = times[i];
        
        int save = -1;
        while(n >= 0 && !isVoid(rest)) {
            timeGoes(rest);
            while(n > 0 && isContainsZero(rest)) {
                if(save < 0) save = getMinIndex(rest, times);
                if(save >= 0 && rest[save] == 0) {
                    rest[save] = times[save];
                    n --;
                    save = -1;
                } else break;
            }
            answer++;
        }
        return answer;
    }
    
    public void timeGoes(int[] rest) {
        for(int i = 0; i < rest.length; i++)
            if(rest[i] > 0) rest[i] --;
    }
    
    public boolean isContainsZero(int[] rest) {
        for(int i = 0; i < rest.length; i++) {
            if(rest[i] == 0) return true;
        }
        return false;
    }
    
    public boolean isVoid(int[] rest) {
        for(int i = 0; i < rest.length; i++) {
            if(rest[i] != 0) return false;
        }
        return true;
    }
    
    public int getMinIndex(int[] rest, int[] times) {
        int min = rest[0] + times[0];
        int minIndex = 0;
        for(int i = 0; i < rest.length; i++) {
            if(min > rest[i] + times[i]) {
                min = rest[i] + times[i];
                minIndex = i;
            } else if (min == rest[i] + times[i]) {
                if(rest[minIndex] > rest[i]) {
                    min = rest[i] + times[i];
                    minIndex = i;
                }
            }
        }
        
        return minIndex;
    }
}