import java.util.*;

class Solution {

    private PriorityQueue<Integer> bq = new PriorityQueue<>((o1, o2) -> { return o2 - o1;});

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        for(int i = 0; i < B.length; i++) {
            bq.add(B[i]);
        }

        int cnt = 0;
        for(int i = A.length - 1; i >= 0; i--) {
            int a = A[i];
            int b = bq.peek();
            if(b > a) {
                cnt ++;
                bq.remove();
            }
        }
        return cnt;
    }
}
