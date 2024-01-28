import java.io.*;
import java.util.*;

public class Main {
    static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(bf.readLine());
        long N = 1;
        long S = 0;
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            S = s * N + n * S;
            N *= n;
            S %= MOD;
            N %= MOD;
        }

        if(S % N != 0) {
            bw.write(String.valueOf(pow(N, MOD - 2) * S % MOD));
        } else {
            bw.write(String.valueOf(S/N));
        }
        bw.close();
    }

    public static long pow(long N, long exp) {
        if(exp == 1) return N;
        long tmp = pow(N, exp / 2);
        if(exp % 2 == 1) return tmp * tmp % MOD * N % MOD;
        else return tmp * tmp % MOD;
    }
}