import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        boolean[] count = new boolean[31];
        int both = 0; //여벌옷 있는데 도난당한 경우
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < lost.length; i++) {
            count[lost[i]] = true;
        }
        for(int i = 0; i < reserve.length; i++) {
            if(count[reserve[i]]) both++;
            set.add(reserve[i]);
        }
        
        int borrow = 0;
        Arrays.sort(lost);
        for(int i = 0; i < lost.length; i++) {
            if(set.contains(lost[i] - 1)) { //1 작은 옷 있는 경우
                set.remove(lost[i] - 1);
                borrow++;
            } else if(set.contains(lost[i] + 1)) { //1 큰 옷 있는 경우
                set.remove(lost[i] + 1);
                borrow++;
            }
        }
        answer = n - (lost.length - both) + borrow + both;
        return answer;
    }
}