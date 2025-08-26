import java.io.*;
import java.util.*;

// 시간 복잡도: 2^15 * 15 * 15
// 공간복잡도:
public class Main {

    private static int n;
    private static int[][] prices;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        prices = new int[n][n];
        dp = new int[(1 << n)][n][10];
        for(int i = 0; i < n; i++) {
            String[] line = bf.readLine().split("");
            for(int j = 0; j < n; j++) {
                prices[i][j] = Integer.parseInt(line[j]);
            }
        }

        for(int i = 0; i < (1 << n); i ++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        dfs(1, 0, 0);
        System.out.println(dp[1][0][0]);
    }

    // 현재 아티스트부터 끝까지 갔을 때 거칠 수 있는 사람의 최댓값 저장
    private static int dfs(int visited, int artist, int price) {
        // 이미 방문한 상태라면 그 값 반환
        if(dp[visited][artist][price] != -1) {
            return dp[visited][artist][price];
        }

        dp[visited][artist][price] = 1;
        for(int i = 0; i < n; i++) {
            // 이미 거친 아티스트거나, 현재 아티스트가 그림을 산 금액보다 이 아티스트에게 파는 금액이 더 적으면 안됨
            if((visited & (1 << i)) != 0 || prices[artist][i] < price) continue;

            // 조건에 맞는 아티스트 다 넘겨보고 최댓값 저장
            dp[visited][artist][price] = Math.max(dp[visited][artist][price], dfs(visited | (1 << i), i, prices[artist][i]) + 1);
        }

        return dp[visited][artist][price];
    }
}
