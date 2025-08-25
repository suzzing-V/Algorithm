import java.io.*;
import java.util.*;

// 시간복잡도: 100 * 100
public class Main {

    private static int[][] dp;
    private static int[][] blocks;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        dp = new int[n][3];
        blocks = new int[n][4];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            blocks[i][0] = Integer.parseInt(st.nextToken());
            blocks[i][1] = Integer.parseInt(st.nextToken());
            blocks[i][2] = Integer.parseInt(st.nextToken());
            blocks[i][3] = i + 1;
        }

        // 밑면을 기준으로 블록 내림차순 정렬
        Arrays.sort(blocks, (o1, o2) -> o2[0] - o1[0]);

        //dp 초기화
        for(int i = 0; i < n; i++) {
            dp[i][0] = blocks[i][1];
            dp[i][1] = -1;
        }

        // dp[i][0]: i번째 블록을 가장 위에 쌓았을 때 만들 수 있는 최대 높이
        // dp[i][1]: dp[i][0]의 경우 i번째 블록 바로 밑의 블록(정렬했을 때)
        // dp[i][2]: dp[i][0]의 경우 i번째 블록 바로 밑의 블록의 진짜 번호

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                // i번째 블록보다 무게가 작은 블록은 i번째 블록 밑에 올 수 없다.
                if(blocks[j][2] < blocks[i][2]) continue;
                // 현재 i번째 블록을 가장 위에 쌓았을 때 최대 높이가 비교 대상 블록을 가장 높이 쌓고 현재 블록을 쌓았을 때 높이보다 작으면 갱신
                if(dp[j][0] + blocks[i][1] > dp[i][0]) {
                    dp[i][0] = dp[j][0] + blocks[i][1];
                    dp[i][1] = j;
                    dp[i][2] = blocks[i][3];
                }
            }
        }

        // 가장 높게 쌓는 경우를 구하고, 역추적해서 결과 얻기
        int max = 0;
        int top = -1;
        for(int i = 0; i < n; i++) {
            if(max <= dp[i][0]) {
                max = dp[i][0];
                top = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        while(top != -1) {
            result.add(blocks[top][3]);
            top = dp[top][1];
        }

        System.out.println(result.size());
        for(int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
