import java.util.*;
import java.io.*;

// 피사노 주기: 피보나치 수열을 특정 수로 나눈 값은 주기를 갖는다. 주기의 길이 k(m) = n이 반드시 존재한다.
// k(10^n) = 15 * 10^(n - 1)
public class Main {

    private static long[] pibo = new long[(int)Math.pow(10, 5) * 15 + 1];
    private static long n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(bf.readLine());

        pibo[1] = 1;
        pibo[2] = 1;
        int mod = (int)Math.pow(10, 6);
        for(int i = 3; i <= (int)Math.pow(10, 5) * 15; i++) {
            pibo[i] = (pibo[i - 1] % mod + pibo[i - 2] % mod) % mod;
        }

        System.out.println(pibo[(int)(n % ((int) Math.pow(10, 5) * 15))]);
    }
}
