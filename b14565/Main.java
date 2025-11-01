import java.util.*;
import java.io.*;

// 확장 유클리드 호제법: 두 수에 대해 각각 어떤 값을 곱하고 두 값을 더해야 이 두 값의 최대공약수가 나오는지 그때의 곱한 값들을 구하는 공식이다.
// s * a + t * b = gcd(a, c) 일 때, s와 t를 구하는 공식.
// (a * x) % n == 1인 값을 구해야 하므로, a와 n에 각각 어떤 값을 곱해야 1이 나오는지 구해야 한다.
// 따라서 n과 a의 최대공약수가 1이 아니면 안된다.
// 그 때의 t값을 구하면 된다.
// 음수가 나올 경우 양수가 나올 때까지 n을 더해주면 된다. 어짜피 더해도 나머지는 똑같다.(모듈러 연산)
public class Main {

    private static long n;
    private static long a;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Long.parseLong(st.nextToken());
        a = Long.parseLong(st.nextToken());
        // 만약 두 수가 서로소가 아니면, (a * x) % n ==1 을 만족하는 x값이 존재하지 않는다.\
        // 두 수의 약수가 없어야 나머지가 1이 될 수 있다.
        long c;
        if(gcd(n, a) != 1) {
            c = -1;
        } else {
            c = extendedUeclid(n, a);
            // 만약 c < 0 이면 양수될 때까지 n 더해준다.
            while(c < 0) {
                c += n;
            }
        }

        System.out.println((n - a) + " " + c);
    }

    private static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    private static long extendedUeclid(long a, long b) {
        long pps = 1;
        long ps = 0;
        long ppt = 0;
        long pt = 1;
        long ppr = a;
        long pr = b;

        while(true) {
            long cq = ppr / pr;
            long cr = ppr - pr * cq;
            long cs = pps - ps * cq;
            long ct = ppt - pt * cq;
//            System.out.println(ppr + " " + pr);
//            System.out.println(cs + " " + ct + " " + cr + " " + cq);
            if(cr == 0) {
                return pt;
            }

            pps = ps;
            ppt = pt;
            ppr = pr;
            ps = cs;
            pt = ct;
            pr = cr;
        }
    }
}
