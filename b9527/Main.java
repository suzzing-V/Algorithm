import java.io.*;
import java.util.*;

public class Main {
    static long start;
    static long end;
    static long[] sum = new long[70];
    static long[] d = new long[70];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        start = Long.parseLong(st.nextToken());
        end = Long.parseLong(st.nextToken());

        d[0] = 1;
        sum[0] = 1;
        for(int i = 1; i < 65; i++) {
            d[i] = d[i - 1] + d[i - 1] + (1L <<(i - 1));
            sum[i] = d[i] + sum[i - 1];
//            System.out.println( d[i] + " " + sum[i]);
        }

        start --;
        System.out.println(countOne(makeTwo(end), end) - countOne(makeTwo(start), start));
    }

    private static String makeTwo(long num) {
        StringBuilder sb = new StringBuilder();
        if(num == 0) {
            return "0";
        }

        while(true) {
            if(num == 1) {
                sb.insert(0, 1);
                break;
            }
            sb.insert(0, num % 2);
            num /= 2;
        }

        return sb.toString();
    }

    private static long countOne(String two, long num) {
        long result = 0;
        for(int i = two.length() - 1; i > 0; i--) {
//            System.out.println(1L << i);
            if((num & (1L << i)) == (1L << i)) {
                result += sum[i - 1] + num - (1L << i) + 1;
                num -= (1L << i);
//                System.out.println(result);
            }
        }
        if(num == 1) {
            result ++;
        }
        return result;
    }
}
