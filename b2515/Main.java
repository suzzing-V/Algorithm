import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int s;
    private static int[][] info;
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        info = new int[n][2];
        dp = new long[n + 1];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(info, (o1, o2) -> {
            if(o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        // dp[i] = i번째 위치에서 마지막까지 만들 수 있는 조합 중 가장 최대 가격
        dp[n - 1] = info[n - 1][1];
        for(int i = n - 2; i >= 0; i --) {
            // 가장 먼저 등장하는 현재 그림과의 높이 차이가 s보다 같거나 큰 그림의 인덱스 찾기
            int idx = lowerBound(i + 1, n, info[i][0] + s);
            dp[i] = Math.max(dp[i + 1], dp[idx] + info[i][1]);
//            System.out.println(idx + " " + (dp[idx] + info[i][1]) + " " + dp[i + 1]);
        }

        System.out.println(dp[0]);
    }

    private static int lowerBound(int start, int end, int target) {
        if(start == end) return start;

        int mid = (start + end) / 2;
        if(info[mid][0] < target) {
            return lowerBound(mid + 1, end, target);
        } else {
            return lowerBound(start, mid, target);
        }
    }
}
