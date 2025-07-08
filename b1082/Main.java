import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int[][] info;
    private static int m;
    private static String[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        info = new int[n][2];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            info[i][0] = i;
            info[i][1] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(bf.readLine());
        dp = new String[51][m + 1];

        // 숫자 큰 순서대로 정렬
        Arrays.sort(info, (o1, o2) -> (o2[0] - o1[0]));
        Arrays.fill(dp[0], "");
        for(int i = 1; i <= 50; i++) {
            for(int j = 1; j <= m; j++) {
                // j - cost[num] >= 0인 값들 중 최대 숫자 찾기
                // 같은 길이라면 맨 앞의 숫자가 가장 큰 게 가장 큰 수다. 따라서 j코스트로 i의 길이의 제일 큰 숫자를 만드는 방법은 선택할 수 있는 가장 큰 수에다가 그 수를 사는 데 사용한 금액을 전체 금액에서 뺸 금액으로 i-1길이의 가장 큰 숫자를 붙이면 된다.
                for(int k = 0; k < n; k++) {
                    // 사용할 수 있는 금액 - 첫번째 숫자의 금액 >= 0이어야 남은 돈으로 뒤의 숫자들 만들 수 있다.
                    // 만약 남은 돈으로 만들 수 있는 숫자가 없으면(null) 안된다.
                    // >0이 아닌 이유? 한글자 만들 때 금액을 온전히 다 써야하는 경우가 제외된다. 어짜피 한글자가 아닐 때 0인 경우는 무조건 null이므로 뒤의 조건에서 무시된다. 따라서 한글자가 아닐 때 첫글자에 모든 돈을 써버리는 경우는 제외된다.
                    if(j - info[k][1] >= 0 && dp[i - 1][j - info[k][1]] != null) {
                        dp[i][j] = String.valueOf(info[k][0]) + dp[i - 1][j - info[k][1]];
                        break;
                    }
                }
            }
        }

        // 금액 m으로 만들 수 있는 가장 큰 숫자는 길이가 가장 긴 숫자이다.
        // 0은 항상 붙일 게 하나도 없을 때 최후의 방도로 붙이는 것이기 때문에 숫자길이가 1이 아닌데 맨 앞이 0이라는 건 사실상 그 금액으로 그 길이의 숫자를 만들 수 없다는 뜻. 따라서 길이가 1이 아니고 맨 앞이 0인 경우는 패스한다.
        for(int i = 50; i >= 1; i --) {
            if(dp[i][m] != null && !(dp[i][m].length() != 1 && dp[i][m].charAt(0) == '0')) {
                System.out.println(dp[i][m]);
                break;
            }
        }
    }
}
