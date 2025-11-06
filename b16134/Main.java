import java.util.*;
import java.io.*;

// 시간복잡도: 10^6 + 2log10^5
// n이 매우 클때, 페르마의 소정리를 통해 역원을 구해서 조합을 빠르게 구할 수 있다. logMOD의 시간복잡도로!
public class Main {

//    private static int[][] combi;
    private static int n;
    private static int r;
    private static int MOD = 1000000007;
    private static long[] fac;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        // n까지의 팩토리얼 구하기
        fac = new long[n + 1];
        fac[0] = 1;
        fac[1] = 1;
        for(int i = 2; i <= n; i++) {
            fac[i] = fac[i - 1] * i;
            fac[i] %= MOD;
        }

        // 페르마의 소정리로 r!, (n - r)!의 역원 구하기
        // a^(-1) = a^(mod - 2)
        long result = fac[n];
        long invR = getPowMod(fac[r], MOD - 2) % MOD;
        result = (result * invR) % MOD;
        long invNR = getPowMod(fac[n - r], MOD - 2) % MOD;
        result = (result * invNR) % MOD;
        System.out.println(result);
    }

    private static long getPowMod(long base, int exp) {
        long result = 1;

        // 지수를 이진수로 바꾸고, 끝의 자리가 1이면 현재 base를 곱한다.
        // 다음 턴으로 넘어갈 때 base를 제곱해준다.
        // ex) 3^12 = 3^8 * 3^4
        while(exp > 0) {
            if((exp & 1) == 1) result = (result * base) % MOD;

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}
