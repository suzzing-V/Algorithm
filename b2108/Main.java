package b2108;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int[] count = new int[8001];
        int[] arr = new int[n];
        double total = 0;
        int order = 0;
        int avg;
        int max = 0;
        int start = 0;
        int end = 0;
        int mid = 0;
        int mode = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            total += arr[i];
        }
        avg = (int)Math.round(total/n); //산술평균

        for (int i = 0; i < n; i++) {
            count[arr[i] + 4000]++;
        }

        for (int i = 0; i < 8001; i++) {
            for(int j = count[i]; j > 0; j--) {
                order++;
                if (order == 1) {
                    start = i - 4000;
                }
                if (order == n) {
                    end = i - 4000;
                }
                if (order == n / 2 + 1) {
                    mid = i - 4000; //중앙값
                }
            }
        }

        for (int i = 0; i < 8001; i++) {
            if (count[i] > max) {
                max = count[i];
            }
        }

        order = 0;
        for(int i = 0; i < 8001; i++) {
            if (count[i] == max) {
                order++;
                mode = i - 4000;
                if (order == 2) {
                    mode = i - 4000;
                    break;
                }
            }
        }

        bw.write(Integer.toString(avg));
        bw.newLine();
        bw.write(Integer.toString(mid));
        bw.newLine();
        bw.write(Integer.toString(mode));
        bw.newLine();
        bw.write(Integer.toString(end - start));
        bw.newLine();
        bw.close();
    }
}