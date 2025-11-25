import java.util.*;
import java.io.*;

// 시간복잡도: 2 * 9 * 18
// 피보나치 수열의 합 = f(n + 2) - 1
// (fi+1 fi) = (1 1 1 0)^i (1 0)
// 분할정복을 통해 (1 1 1 0)의 제곱을 구한다. 그럼 log 수준에서 구할 수 있다.
// 모듈러 연산했을 때 두 수를 빼야한다면, 모듈러 연산이 적용됐으므로 음수가 나올 수 있다. 이때 음수 보정을 위해 (a - b + MOD) % MOD 해줘야 한다.
public class Main {

    private static long a;
    private static long b;
    private static long MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        A mb = matrix(b + 1);
        long sb = mb.a1 - 1;
        A ma = matrix(a);
        long sa = ma.a1 - 1;
        System.out.println((sb % MOD - sa % MOD + MOD) % MOD);
    }

    private static A matrix(long i) {
        if(i == 1) {
            return new A(1, 1, 1, 0);
        }

        A divide = matrix(i / 2);
        long a1 = (divide.a1 * divide.a1 % MOD + divide.a2 * divide.a3 % MOD) % MOD;
        long a2 = (divide.a1 * divide.a2 % MOD + divide.a2 * divide.a4 % MOD) % MOD;
        long a3 = (divide.a1 * divide.a3 % MOD + divide.a4 * divide.a3 % MOD) % MOD;
        long a4 = (divide.a2 * divide.a3 % MOD + divide.a4 * divide.a4 % MOD) % MOD;

        if(i % 2 == 0) {
            return new A(a1, a2, a3, a4);
        } else {
            return new A((a1 % MOD + a2 % MOD) % MOD, a1, (a3 % MOD + a4 % MOD) % MOD, a3);
        }
    }

    private static class A {
        long a1;
        long a2;
        long a3;
        long a4;

        A(long a1, long a2, long a3, long a4) {
            this.a1 = a1;
            this.a2 = a2;
            this.a3 = a3;
            this.a4 = a4;
        }
    }
}
