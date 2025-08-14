import java.util.*;

// 시간복잡도 : O(n)
// 배열을 활용한 연결리스트

class Solution {

    private Stack<Integer> deleted = new Stack<>();
    private int[] prev;
    private int[] next;
    private boolean[] isDeleted;

    public String solution(int n, int k, String[] cmd) {
        prev = new int[n];
        next = new int[n];
        isDeleted = new boolean[n];

        for(int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }

        for(int i = 0; i < cmd.length; i++) {
            StringTokenizer st = new StringTokenizer(cmd[i]);
            String c = st.nextToken();
            if(c.equals("U")) {
                int move = Integer.parseInt(st.nextToken());
                while(move > 0) {
                    k = prev[k];
                    move --;
                }
            } else if(c.equals("D")) {
                int move = Integer.parseInt(st.nextToken());
                while(move > 0) {
                    k = next[k];
                    move --;
                }
            } else if(c.equals("C")) {
                deleted.add(k);
                if(prev[k] >= 0) next[prev[k]] = next[k];
                if(next[k] < n) prev[next[k]] = prev[k];
                if(next[k] == n) {
                    k = prev[k];
                } else {
                    k = next[k];
                }
            } else if(c.equals("Z")) {
                int last_deleted = deleted.pop();
                if(prev[last_deleted] >= 0) next[prev[last_deleted]] = last_deleted;
                if(next[last_deleted] < n) prev[next[last_deleted]] = last_deleted;
            }
        }

        while(!deleted.isEmpty()) {
            int curr = deleted.pop();

            isDeleted[curr] = true;
        }


        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(isDeleted[i]) answer.append("X");
            else answer.append("O");
        }
        return answer.toString();
    }
}
