import java.io.*;
import java.util.*;

// 1차원 냅색: 이전 숫자까지 사용했을 때의 상태를 사용해야 하므로, 뒤에서부터 구한다.
// 개수마다 dp를 전체 갱신하면 이전 개수만큼 사용한 상태가 저장되므로, 현재 숫자를 중복 사용하는 것이 된다. 따라서 앞의 숫자까지 사용하고 현재 숫자 0, 1, 2, 3, ..개 사용해 i를 만든 경우의 수를 구해야 현재 숫자까지 사용했을 때의 경우의 수 상태가 된다.
// 시간복잡도: 500000 * 50 * 50
// 0은 합에 영향을 주지 않으므로 0의 개수만큼 경우의 수가 늘어난다. 따라서 0의 개수를 따로 세고, 이를 모든 경우의 수에 곱해준다.
public class Main {

    private static long[] dp;
    private static int n;
    private static Map<Integer, Integer> candies = new HashMap<>();
    private static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        dp = new long[(int)Math.pow(10, 5) * 5 + 1];
        // 캔디 가격별로 개수 저장
        int zero = 1;
        for(int i = 1; i <= n; i++) {
            int price = Integer.parseInt(bf.readLine());
            if(price == 0) {
                zero ++;
                continue;
            }
            if(candies.get(price) == null) candies.put(price, 0);
            candies.put(price, candies.get(price) + 1);
        }

        // 캔디 가격별로 돌면서 개수만큼 써보기
        dp[0] = 1;
            for(int key : candies.keySet()) {
                int cnt = candies.get(key);
                    for(int i = (int)Math.pow(10, 5) * 5; i >= 0; i--) {
                        for(int j = 1; j <= cnt; j++) {
                    if(i - j * key >= 0) dp[i] += dp[i - j * key];
                }
            }
        }

        // 소수 구하기
        isPrime = new boolean[(int)Math.pow(10, 5) * 5 + 1];
        Arrays.fill(isPrime, true);
        for(int i = 2; i <= (int)Math.pow(10, 5) * 5; i++) {
            if(!isPrime[i]) continue;
            for(int j = i + i; j <= (int)Math.pow(10, 5) * 5; j += i) {
                isPrime[j] = false;
            }
        }

        // 합이 소수인 경우의 수 합하기
        long result = 0;
        for(int i = 2; i <= (int)Math.pow(10, 5) * 5; i++) {
            if(!isPrime[i]) continue;

            result += dp[i];
        }

        System.out.println(result * zero);
    }
}
