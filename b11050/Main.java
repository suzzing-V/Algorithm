import java.io.*;
import java.util.*;

public class Main {

    static int[] fac = new int[11];
    public static void main(String[] arsg) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int result = factorial(n) / factorial(n - k) / factorial(k);
        System.out.println(result);
    }

    public static int factorial(int n) {
        if(n <= 1) {
            return 1;
        }

        if(fac[n] != 0) return fac[n];
        fac[n] = n * factorial(n - 1);
        return fac[n];
    }
}
