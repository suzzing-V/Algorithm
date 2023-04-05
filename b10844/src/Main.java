import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());

        long[] before = new long[10];
        long[] after = new long[10];
        before[0] = 0;
        for(int i = 1; i <= 9; i++) {
            before[i] = 1;
        }

        for(int i = 2; i <= N; i++) {
            fillCountArr(before, after);
            before = after;
            after = new long[10];
        }
        
        bw.write(Long.toString(sumArrDiv(before) % 1000000000));
        bw.close();
    }

    public static void fillCountArr(long[] before, long[] after) {
        for(int i = 1; i <= 8; i++) {
            after[i] = (before[i - 1] + before[i + 1]) % 1000000000;
        }
        after[0] = before[1] % 1000000000;
        after[9] = before[8] % 1000000000;
    }

    public static long sumArrDiv(long[] after) {
        long sum = 0;
        for(int i = 0; i <= 9; i++) {
            sum += after[i];
        }
        return sum;
    }
}