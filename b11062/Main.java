import java.util.*;
import java.io.*;

public class Main {

    private static int t;
    private static int[][] dp;
    private static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            cards = new int[n + 1];
            dp = new int[n + 1][n + 1]; // i~j번째 카드 있을 경우 근우의 최대 점수
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= n; j++) {
                cards[j] = Integer.parseInt(st.nextToken());
            }
            System.out.println(game(1, n, 1));
        }
    }

    private static int game(int left, int right, int turn) {
        if(left > right) return 0;
        if(dp[left][right] != 0) return dp[left][right];

        // 근우의 차례: 왼쪽카드를 뽑았을 때와 오른쪽 카드를 뽑았을 때 중 더 큰 점수 선택
        if(turn % 2 == 1) return dp[left][right] = Math.max(cards[left] + game(left + 1, right, turn + 1), cards[right] + game(left, right - 1, turn + 1));
        // 명우 차례 : 명우는 최선을 다하므로 근우의 점수가 더 작은 경우를 택한다.
        return dp[left][right] = Math.min(game(left + 1, right, turn + 1), game(left, right - 1, turn + 1));
    }
}