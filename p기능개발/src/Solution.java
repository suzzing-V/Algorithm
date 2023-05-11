import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        List<Integer> result = new ArrayList<>();
        int[] days = new int[progresses.length];
        for(int i = 0; i < progresses.length; i++) {
            int j = 0;
            int sum = progresses[i];
            while(sum < 100) {
                sum += speeds[i];
                j++;
            }
            days[i] = j;
        }
        
        Stack<Integer> stack = new Stack<>();
        int c = 1;
        stack.push(days[0]);
        for(int i = 1; i < days.length; i++) {
            if(days[i] <= stack.peek()) c++;
            else {
                result.add(c);
                stack.push(days[i]);
                c = 1;
            }
        }
        result.add(c);
        
        answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) answer[i] = result.get(i);
        return answer;
    }
}