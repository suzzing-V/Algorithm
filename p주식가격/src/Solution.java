import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int count = 0;
        for(int i = 0; i < prices.length; i++) {
            count = 0;
            for(int j = i + 1; j < prices.length; j++) {
                count++;
                if(prices[j] < prices[i]) break;
            }
            answer[i] = count;
        }
        return answer;
    }
}