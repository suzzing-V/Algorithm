import java.util.*;
import java.io.*;

// 오버플로우 생각해서 예외 처리 신경쓰기
public class Main {

    private static int n;
    private static int m;
    private static long k;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
        dp = new long[n + m + 1][n + m + 1];

        if(combi(n + m, n) < k) { // 나올 수 있는 조합 수보다 k가 크면 답이 없다.
            bw.write("-1");
            bw.close();
            return;
        }

        String str = makeStr(new StringBuilder(), 0);
        bw.write(str);
        bw.close();
    }

    private static String makeStr(StringBuilder sb, long start) {
        if(n == 0) { // a를 다 썼을 경우 남은 z 다 붙여준다.
            for(int i = 0; i < m; i++) {
                sb.append("z");
            }
            return sb.toString();
        }
        if(m == 0) { // z를 다 썼을 경우 남은 a 다 붙여준다.
            for(int i = 0; i < n; i++) {
                sb.append("a");
            }
            return sb.toString();
        }

        long divide = combi(n + m - 1, n - 1); // a를 고정했을 때 나올 수 있는 경우의 수
//        System.out.println(start + divide);
        if(k <= start + divide) { // 현재 보고 있는 범위의 시작점에 a를 고정했을 때 나올 수 있는 경우의 수를 기준으로 a를 붙일지 z를 붙일지 결정된다.
            n --;
            sb.append("a");
            return makeStr(sb, start); // a를 붙일 경우 시작점은 변하지 않으므로 그대로 전달해준다.
        } else {
            m --;
            sb.append("z");
            return makeStr(sb, start + divide); // z를 붙일 경우 시작점은 z로 시작하는 수열의 시작점이다.
        }
    }

    private static long combi(int i, int j) {
        if(j == 0 || i == j) return dp[i][j] = 1;
        if(j == 1) return dp[i][j] = i;
        if(dp[i][j] != 0) return dp[i][j];

        long v1 = combi(i - 1, j - 1);
        long v2 = combi(i - 1, j);
        if(v1 + v2 > 1000000000) return dp[i][j] = Integer.MAX_VALUE; // k <= 10억이므로 조합이 10억이 넘어간다면 그냥 적당한 큰 수랑 비교해도 된다. 200c100같은 경우 오버플로우가 나므로 정확하게 계산하지 말고 적당한 큰 값을 저장한다.
        return dp[i][j] = v1 + v2;
    }
}
