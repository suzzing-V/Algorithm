import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nk = new String[2];
        nk = bf.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] temper = new int[n + 1];
        String[] line = new String[n];
        line = bf.readLine().split(" ");
        for(int i = 1; i <= n; i++) {
            temper[i] = Integer.parseInt(line[i - 1]);
        }

        int[] acc = new int[n + 1];
        acc[1] = temper[1];
        for(int i = 2; i <= n; i++) {
            acc[i] = acc[i - 1] + temper[i];
        }

        int[] kSum = new int[n - k + 2];
        int max = -100;
        for(int i = 1; i <= n - k + 1; i++) {
            kSum[i] = acc[i + (k - 1)] - acc[i - 1];
            if(max < kSum[i]) {
                max = kSum[i];
            }
        }
        bw.write(Integer.toString(max));
        bw.close();
    }
}
