import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i = 0; i < enemy.length; i++) {
            n -= enemy[i];
            pq.add(enemy[i]);
            // System.out.println("n k: " + n + " " + k);

            if(n < 0 && k > 0) {
                int max = pq.remove();
                // System.out.println("max: " + max);
                k --;
                n += max;
            }

            if(n < 0) return i;
        }

        return enemy.length;
    }
}