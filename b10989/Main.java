package b10989;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[10000000];

        for (int i = 0; i < n; i++) {
            arr[Integer.parseInt(bf.readLine()) - 1]++;
        }

        for (int i = 0; i < 10000000; i++) {
            for(int j = arr[i]; j > 0; j--) {
                bw.write(Integer.toString(i + 1));
                bw.newLine();
            }
        }
        bw.close();
    }
}
