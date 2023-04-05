import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int[] grape = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            grape[i] = Integer.parseInt(bf.readLine());
        }

        int[] d = new int[n + 1];
        d[1] = grape[1];
        if(n >= 2) {
            d[2] = grape[1] + grape[2];
        }
        if(n >= 3) {
            d[3] = Math.max(grape[1], grape[2]) + grape[3];
            for(int i = 4; i <= n; i++) {
                d[i] = Math.max(d[i - 2], grape[i - 1] + d[i - 3]) + grape[i];
            }
        }

        Arrays.sort(d);
        bw.write(Integer.toString(d[n]));
        bw.close();
    }
}
