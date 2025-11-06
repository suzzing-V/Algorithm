import java.util.*;
import java.io.*;

// 시간복잡도: 11 + 12 + 12
public class Main {

    private static long[] sum = new long[12];
    private static long l;
    private static long u;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        l = Long.parseLong(st.nextToken());
        u = Long.parseLong(st.nextToken());

        // 1~9, 1~99, 1~999, ... 에서의 수들의 합 구하기
        sum[0] = 45;
        for(int i = 1; i <= 11; i ++) {
            sum[i] = 10 * sum[i - 1] + 45 * (long)Math.pow(10, i);
        }

        // l - 1까지와 u까지의 수들의 합 구해서 빼기
        int ulen = String.valueOf(u).length() - 1;
        int llen = String.valueOf(l - 1). length() - 1;
        System.out.println(getSum(u, ulen) - getSum(l - 1, llen));
    }

    private static long getSum(long n, int pow) {
        if(n < 10) return n * (n + 1) / 2;

        long powVal = (long)Math.pow(10, pow);
        // 296이라면 1~199까지의 100의 자리를 제외한 수들의 합
        long exceptPow =  n / powVal * sum[pow - 1];
        // 1~199까지의 100의 자리수의 합
        long sumPow = (n / powVal) * (n / powVal - 1) / 2 * powVal;
        // 200~296의 백의 자리 수 합
        long restPow = (n % powVal + 1) * (n / powVal);

//        System.out.println(exceptPow  + " " + sumPow + " "+ restPow);

        // 이렇게 하면 200까지의 자릿수 합과 200~296까지의 백의 자리 수 합을 모두 구했다. 그럼 다음은 1~96의 자릿수 합을 구하면 된다.
        return exceptPow + sumPow + restPow + getSum(n % powVal, pow - 1);
    }
}
