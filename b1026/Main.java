import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Integer[] a;
    static Integer[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        n = Integer.parseInt(bf.readLine());
        a = new Integer[n];
        b = new Integer[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder());

        int result = 0;
        for(int i = 0; i < n; i++) {
            result += a[i] * b[i];
        }
        System.out.println(result);
    }
}
