import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[][] info;
    private static int[][] dp; // i개 기업에 j원 투자했을 때 얻을 수 있는 최대 이익
    private static int[][] costByCom; // dp[i][j]일 때 i기업이 낸 금액

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        info = new int[m + 1][n + 1]; // i기업에 j원 투자했을 때의 이익
        dp = new int[m + 1][n + 1];
        costByCom = new int[m + 1][n + 1];

        // 기업 투자 정보 저장
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            int input = Integer.parseInt(st.nextToken());
            for(int j = 1; j <= m; j ++) {
                int prize = Integer.parseInt(st.nextToken());
                info[j][input] = prize;
            }
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                // i 기업에 0~j원까지 투자하는 경우 중 최댓값 저장
                for(int k = 0; k <= j; k++) {
                    // k원 투자했을 때 이익이 지금까지의 최댓값보다 크다면 갱신
                    if(dp[i - 1][j - k] + info[i][k] > dp[i][j]) {
                        dp[i][j] = dp[i - 1][j - k] + info[i][k];
                        costByCom[i][j] = k; // dp[i][j]에서 i 기업에 투자한 금액 갱신
                    }
                }
            }
        }

//        for(int i = 1; i <= m; i++) {
//            for(int j = 1; j <= n; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

//        for(int i = 1; i <= m; i++) {
//            for(int j = 1; j <= n; j++) {
//                System.out.print(costByCom[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[m][n]);
        // costByCom에는 각 기업이 추가될 때마다 낸 금액이 담겨있다. 따라서 i, j상황에서 k를 냈다면 i - 1기업은 i - 1, j - k상황에서 낸 i -1이 낸 금액을 냈다.
        List<Integer> costs = new ArrayList<>();
        int com = m;
        int cost = n;
        while(com > 0) {
            costs.add(costByCom[com][cost]);

            cost -= costByCom[com][cost];
            com --;
        }

        for(int i = costs.size() - 1; i >= 0; i --) {
            System.out.print(costs.get(i) + " ");
        }
    }

    private static class Node {
        private int company;
        private int cost;

        private Node(int company, int cost) {
            this.company = company;
            this.cost = cost;
        }
    }
}
