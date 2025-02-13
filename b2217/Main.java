import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] ropes;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        ropes = new int[n];
        for(int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(ropes);

        int max = 0;
        for(int i = n - 1; i >= 0; i--) {
            int k = n - i;
            max = Math.max(k * ropes[i], max);
        }
        bw.write(max + " ");
        bw.close();
    }
}
