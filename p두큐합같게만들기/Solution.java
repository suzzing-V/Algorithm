import java.util.*;

class Solution {

    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2 = 0;
        int max = 0;
        int answer = 0;
        for(int i = 0; i < queue1.length; i++) {
            max = Math.max(queue1[i], max);
            sum1 += queue1[i];
            q1.add(queue1[i]);
        }
        for(int i = 0; i < queue2.length; i++) {
            max = Math.max(queue2[i], max);
            sum2 += queue2[i];
            q2.add(queue2[i]);
        }
        long target = (sum1 + sum2) / 2;
        if(max > target) return -1;

        while(sum1 != sum2) {
            if(answer > (q1.size() + q2.size()) * 2) return -1;
            // System.out.println("sum: " + sum1 + " " + sum2);
            if(sum1 > sum2) {
                int first = q1.remove();
                sum1 -= first;
                while(target - sum2 < first) {
                    int r = q2.remove();
                    sum2 -= r;
                    q1.add(r);
                    sum1 += r;
                    answer ++;
                }
                q2.add(first);
                sum2 += first;
            } else {
                int first = q2.remove();
                sum2 -= first;
                while(target - sum1 < first) {
                    int r = q1.remove();
                    sum1 -= r;
                    q2.add(r);
                    sum2 += r;
                    answer ++;
                }
                q1.add(first);
                sum1 += first;
            }
            answer ++;
        }
        return answer;
    }
}
