import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        String[] arrStr = new String[n];
        arrStr = bf.readLine().split(" ");

        int[] arr = new int[n];
        long[] sum = new long[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }

        sum[0] = arr[0];
        for(int i = 1; i < n; i++) {
            sum[i] = Math.max(arr[i], sum[i - 1] + arr[i]);
        }
        Arrays.sort(sum);
        bw.write(Long.toString(sum[n - 1]));
        bw.close();
    }
}
