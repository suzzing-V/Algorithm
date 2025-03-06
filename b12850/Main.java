import java.io.*;
import java.util.*;

public class Main {

    static long d;
    static long MOD = 1000000007;
    static long[][] arr = { // 1번 이동해서 i에서 j로 도달하는 경우의 수
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        d = Long.parseLong(bf.readLine());
        arr = divide(d);
        bw.write(String.valueOf(arr[0][0]));
        bw.close();
    }

    private static long[][] divide(long n) { // 2로 나누었을 때 H^(n/2) 두 개가 똑같음. 따라서 한번만 계산하고 그거 제곱해주면 됨
        if(n == 1) return arr;
        if(n % 2 == 0) {
            long[][] byTwo = divide(n / 2);
            return multifySquare(byTwo, byTwo);
        } else {
            return multifySquare(divide(n - 1), arr);
        }
    }

    private static long[][] multifySquare(long[][] arr1, long[][] arr2) { // i에서 j로 가는 경우의 수 구하기. arr1번만에 i에서 k로 이동하고, arr2번만에 k에서 j로 이동.
        long[][] result = new long[8][8];

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                for(int k = 0; k < 8; k++) {
                    result[i][j] += (arr1[i][k] * arr2[k][j]) % MOD;
                }
                result[i][j] %= MOD;
            }
        }
        return result;
    }
}
