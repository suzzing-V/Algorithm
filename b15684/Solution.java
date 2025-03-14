import java.util.*;

class Solution {

    private int[] pre;
    private int[] next;
    private String[] isAlive;
    private Stack<Node> deleted = new Stack<>();

    private class Node {
        private int num;

        Node(int num) {
            this.num = num;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        pre = new int[n];
        next = new int[n];
        isAlive = new String[n];
        for(int i = 0; i < n; i++) {
            pre[i] = i - 1;
            next[i] = i + 1;
            isAlive[i] = "O";
        }
        pre[0] = -1;
        next[n - 1] = -1;

        for(int i = 0; i < cmd.length; i++) {
            StringTokenizer st = new StringTokenizer(cmd[i]);
            String command = st.nextToken();
            if(command.equals("D")) {
                int move = Integer.parseInt(st.nextToken());
                while(move > 0) {
                    k = next[k];
                    move --;
                }
            } else if(command.equals("C")) {
                if(next[k] != -1) pre[next[k]] = pre[k];
                if(pre[k] != -1) next[pre[k]] = next[k];
                deleted.add(new Node(k));
                isAlive[k] = "X";

                if(next[k] == -1) k = pre[k];
                else k = next[k];
            } else if(command.equals("U")) {
                int move = Integer.parseInt(st.nextToken());
                while(move > 0) {
                    k = pre[k];
                    move --;
                }
            } else if(command.equals("Z")) {
                Node restore = deleted.pop();
                if(next[restore.num] != -1) pre[next[restore.num]] = restore.num;
                if(pre[restore.num] != -1) next[pre[restore.num]] = restore.num;
                isAlive[restore.num] = "O";
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(isAlive[i]);
        }
        return sb.toString();
    }
}
