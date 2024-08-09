import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> order =  new ArrayList<>();
    static int orderLen;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        String input = "";
        while(!(input = st.nextToken()).equals("0")) {
            order.add(Integer.parseInt(input));
        }
        orderLen = order.size();
        dp = new int[5][5][orderLen];

        System.out.println(dfs(0, 0, 0));
    }

    public static int dfs(int left, int right, int count) {
        if(count >= orderLen) {
                return 0;
        }
        if(dp[left][right][count] != 0) {
            return dp[left][right][count];
        }

        int next = order.get(count);
        dp[left][right][count] = Math.min(dfs(next, right, count + 1) + calculatePower(left, next), dfs(left, next, count + 1) + calculatePower(right, next));
        return dp[left][right][count];
    }

    public static int calculatePower(int curr, int next) {
        int amount = Math.abs(curr - next);

        if(curr == 0) {
            return 2;
        } else if(amount == 1 || amount == 3) {
            return 3;
        } else if(amount == 0) {
            return 1;
        }
        return 4;
    }
}
