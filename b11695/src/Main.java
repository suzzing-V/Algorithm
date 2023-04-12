import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nm = new String[2];
        nm = bf.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] arr = new int[n + 1];
        String[] arrStr = new String[n];
        arrStr = bf.readLine().split(" ");
        for(int a = 1; a <= n; a++) {
            arr[a] = Integer.parseInt(arrStr[a - 1]);
        }

        long result;
        int i, j;
        for(int a = 1; a <= m; a++) {
            String[] ij = new String[2];
            ij = bf.readLine().split(" ");
            i = Integer.parseInt(ij[0]);
            j = Integer.parseInt(ij[1]);

            result = rangeSum(arr, i, j);
            bw.write(Long.toString(result));
            bw.write("\n");
        }
        bw.close();
    }

    public static long rangeSum(int[] arr, int i, int j) {
        long sum = 0;
        for(int a = i; a <= j; a++) {
            sum += arr[a];
        }
        return sum;
    }
}
