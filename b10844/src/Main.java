import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());

        int[] before = new int[10];
        int[] after = new int[10];
        before[0] = 0;
        for(int i = 1; i <= 9; i++) {
            before[i] = 1;
        }

        for(int i = 2; i <= N; i++) {
            fillCountArr(before, after);
            before = after;
            after = new int[10];
        }
        
        bw.write(Integer.toString(sumArrDiv(before) % 1000000000));
        bw.close();
    }

    public static void fillCountArr(int[] before, int[] after) {
        for(int i = 1; i <= 8; i++) {
            after[i] = (before[i - 1] + before[i + 1]) % 1000000000;
        }
        after[0] = before[1] % 1000000000;
        after[9] = before[8] % 1000000000;
    }

    public static int sumArrDiv(int[] after) {
        int sum = 0;
        for(int i = 0; i <= 9; i++) {
            sum += after[i];
        }
        return sum;
    }
}