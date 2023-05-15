import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < prices.length; i++) {
            stack.push(prices[i]);
        } //스택에 prices 넣기
        
        for(int i = 5; i > 0; i--) {
            int cur = stack.pop();
            for(int j = 1; j < cur; j++) { //cur보다 작은 수가 앞에 있었는지
                if(hm.containsKey(cur- j)) { //
                    int tmp = hm.get(cur - j) - i; //answer에 넣을 값
                    answer[i - 1] = tmp;
                    break;
                }
            }
            if(answer[i - 1] == 0) answer[i - 1] = prices.length - i;
            //끝까지 자신보다 작은 수 없을 경우
            if(!hm.containsKey(cur)) { //제일 처음 등장한 수일 경우
                        hm.put(cur, i);
            }
        }
        return answer;
    }
}