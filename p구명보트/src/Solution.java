import java.util.*;
import java.util.Collections;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        while(left < right) {
            if(people[left] + people[right] <= limit) {
                right --;
                left ++;
            } else {
                right --;
            }
            answer++;
        }
        
        if(left == right) answer ++;
        return answer;
    }
}