import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] arr;
    private static int[] sum;
    private static int[][][] dp; // i~j까지 k개의 묶음을 만들었을 때 묶음의 최대값의 최소값
    private static List<Integer>[][][] cnt; // 해당 경우일 때 묶음 정보 저장

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][n + 1][m + 1];
        arr = new int[n + 1];
        sum = new int[n + 1];
        cnt = new ArrayList[n + 1][n + 1][m + 1];
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sum[0] = 0;
        for(int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int answer = dfs(1, n, m);
        bw.write(String.valueOf(answer) + "\n");
        for(int i = cnt[1][n][m].size() - 1; i >= 0; i--) {
            bw.write(String.valueOf(cnt[1][n][m].get(i)) + " ");
        }
        bw.close();
    }

    private static int dfs(int start, int end, int bundle) {
//        System.out.println(start + " "+ end + " "+ bundle);
        if(dp[start][end][bundle] != 0) {
            return dp[start][end][bundle];
        }
        if(bundle == 1) { // 현재 범위로 묶음 하나 만들라면 현재 범위를 다 묶어야 한다.
            List<Integer> list = new ArrayList<>();
            list.add(end - start + 1); // 현재 범위 개수 저장
            cnt[start][end][bundle] = list;
            return dp[start][end][bundle] = sum[end] - sum[start - 1]; // 누적합으로 현재 범위의 요소들 합 구하기
        }

        int sum = 0;
        dp[start][end][bundle] = 100000;
        for(int i = start; i < end; i++) {
            if(end - i < bundle - 1) break; // 남은 게 번들수보다 작으면 못만듦
            sum += arr[i];
            int next = Math.max(sum, dfs(i + 1, end, bundle - 1));
            if(dp[start][end][bundle] > next) { // 최대값의 최소값 갱신
                dp[start][end][bundle] = next;
                List<Integer> list = new ArrayList<>(cnt[i + 1][end][bundle - 1]); // 뒤의 묶음 정보 저장하고
                list.add(i - start + 1); // 현재 묶음 저장
                cnt[start][end][bundle] = list;
            }
        }

//        System.out.println(start + " "+ end + " "+ bundle);
//        System.out.println(cnt[start][end][bundle]);

        return dp[start][end][bundle];
    }
}
