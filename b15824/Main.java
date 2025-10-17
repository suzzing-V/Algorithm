import java.util.*;
import java.io.*;

// 시간복잡도: 300000 + 600000
// 나머지 연산 시 음수가 나올 가능성이 있다면 MOD를 더해주고 다시 MOD로 나눠준다.
// 최소, 최대값을 먼저 정하고, 그에 따른 조합의 수를 구한다. 그 계산식을 다 병합해보면, 특정 수를 최대값으로 삼았을 때의 조합의 수에 특정 수를 곱한 값에서, 특정 수를 최소값으로 삼았을 떄의 조합 수에 특정 수를 곱한 값을 뺴준 결과와 같다.
// 2의 300000제곱수는 범위가 너무 크므로 나머지 연산을 해서 저장해두고 사용한다.
// 2의 제곱수의 합은 2^(k + 1) -1 과 같다. 등비수열의 합
// 빼는 연산이 있으므로 나머지 연산을 했을 때 음수가 나올 가능성이 있다.
public class Main {

    private static int n;
    private static int[] arr;
    private static long[] pow2;
    private static int MOD = 1000000007;
    private static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        pow2 = new long[300001];
        Arrays.sort(arr);

        // (2의 제곱수 - 1) 저장하기
        long tmp = 2;
        for(int i = 1; i <= 300000; i++) {
            pow2[i] = tmp - 1;
            tmp *= 2;
            tmp %= MOD;
        }

        // 각 수를 최대값으로 가지는 조합 수만큼 더하기
        for(int i = 0; i < n ;i++) {
            long multi = pow2[i] * arr[i];
            result += multi % MOD;
            result %= MOD;
        }

        // 각 수를 최소값으로 가지는 조합 수만큼 빼기
        for(int i = 0;i < n; i++) {
            long multi = pow2[n - 1 - i] * arr[i];
            result -= multi % MOD;
            result %= MOD;
        }

        if(result < 0) result += MOD;
        System.out.println(result % MOD);
    }
}
