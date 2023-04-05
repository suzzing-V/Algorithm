import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        long[] d = new long[N + 1];

        d[1] = 9;
        for(int i = 2; i <= N; i++) {
            d[i] = d[i - 1] * 2 - (i * 2 - 3);
            d[i] %= 1000000000;
        }
        
        bw.write(Long.toString(d[N]));
        bw.close();
    }

    public static void fillCountArr(long[] before, long[] after) {
        for(int i = 2; i <= 8; i++) {
            after[i] = before[i - 1] + before[i + 1];
        }
        after[0] = before[1];
        after[1] = before[2];
        after[9] = before[8];
    }
}
