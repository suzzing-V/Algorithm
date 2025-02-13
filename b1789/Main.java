import java.io.*;
import java.util.*;

public class Main {

    static long n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Long.parseLong(bf.readLine());
        long sum = 0;
        for(long i = 1; i <= n; i++) {
            sum += i;
            if(n == sum || n - sum < i + 1) {
                bw.write(i + " ");
                break;
            }
        }
        bw.close();
    }
}
