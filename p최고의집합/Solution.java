import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if(s < n) {
            answer = new int[1];
            answer[0] = -1;
            return answer; // 합이 개수보다 적으면 안됨
        }

        // 원소들이 골고루 있으면 무조건 최대값. 그러므로 n을 s로 나누어 하나의 원소 값을 정하고, 나머지를 1씩 배분.
        int piece = s / n;
        int rest = s % n;

        Arrays.fill(answer, piece);
        int i = n - 1;
        while(rest > 0) {
            rest --;
            answer[i --] ++;
        }
        return answer;
    }
}
