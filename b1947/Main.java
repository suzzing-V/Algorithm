import java.io.*;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        long[] dp = new long[n + 1];
        // dp[i]: i명의 사람들이 자기 자신과 선물 주고받지 않는 경우의 수

        if(n == 1) {
            System.out.println("0");
            return;
        }

        dp[2] = 1;
        for(int i = 3; i <= n; i++) {
            // 1. i-1명의 자기 자신과 선물 주고 받지 않는 경우에서 각 사람들과 선물 바꾸는 경우의 수
            // 2. 위의 경우뿐만 아니라 자기 자신과 주고 받는 경우에서도 새로 들어온 사람이 선물을 바꿀 수 있다. i-1명 중 한명만 자기자신과 선물을 주고받는다면 그 사람과 새로 들어온 사람이 선물을 바꿔서 조건에 맞는 조합을 완성할 수 있다.
            // i - 1명 중 한 명은 자기 자신과 선물 주고받고 나머지는 아닌 경우는 dp[i - 2]이다. 이 한 명은 i-1명 모두가 될 수 있으므로 (i - 1) *dp[i - 2]는 i-1명중 한사람이 자기자신과 선물을 주고 받았을 때 새로운 사람이 들어온 경우 만들 수 있는 조합이다.
            dp[i] = ((i - 1) * (dp[i - 1] + dp[i - 2])) % 1000000000;
            // int + int 의 답은 무조건 int가 나온다. 저절로 long으로 되지 않는다.
        }
        System.out.println(dp[n]);
    }
}
