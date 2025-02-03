import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] peoples;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        peoples = new int[n];
        int result = 0;
        for(int i = 0; i < n; i++) {
            int p1 = Integer.parseInt(bf.readLine(), 2);
            peoples[i] = p1;
            for(int j = i - 1; j >= 0; j--) {
                int p2 = peoples[j];
                if(Integer.bitCount(p1 ^ p2) <= 2) {
                    result ++;
                }
            }
        }
        bw.write(String.valueOf(result));
        bw.close();
    }
}

