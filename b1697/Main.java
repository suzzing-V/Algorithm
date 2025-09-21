import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[100001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        System.out.println(bfs(n));
    }

    private static int bfs(int start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, start));
        dp[start] = 0;
        while(!queue.isEmpty()) {
            Node curr = queue.remove();

            if(curr.pos == k) {
                return curr.t;
            }

            if(curr.pos * 2 <= 100000) {
                if(dp[curr.pos * 2] > curr.t + 1) {
                    queue.add(new Node(curr.t + 1, curr.pos * 2));
                    dp[curr.pos * 2] = curr.t + 1;
                }
            }

            if(curr.pos + 1 <= 100000) {
                if(dp[curr.pos + 1] > curr.t + 1) {
                    queue.add(new Node(curr.t + 1, curr.pos + 1));
                    dp[curr.pos + 1] = curr.t + 1;
                }
            }

            if(curr.pos - 1 >= 0) {
                if(dp[curr.pos - 1] > curr.t + 1) {
                    queue.add(new Node(curr.t + 1, curr.pos - 1));
                    dp[curr.pos - 1] = curr.t + 1;
                }
            }
        }

        return -1;
    }
    private static class Node {
        int t;
        int pos;

        Node(int t, int pos) {
            this.t = t;
            this.pos = pos;
        }
    }
}
