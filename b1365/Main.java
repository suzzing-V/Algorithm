import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] lis;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        lis = new int[n];
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[0] = arr[0];
        int d = 1;
        for(int i = 1; i < n; i++) {
            if(lis[d - 1] < arr[i]) {
                lis[d ++] = arr[i];
            } else if(lis[d - 1] > arr[i]) {
                int minIdx = binarySearch(arr[i], 0, d - 1);
                lis[minIdx] = arr[i];
            }
        }

        bw.write(String.valueOf(n - d));
        bw.close();
    }

    private static int binarySearch(int target, int start, int end) {
        int mid = (start + end) / 2;
        if(start >= end) {
            return start;
        }

        if(target > lis[mid]) {
            return binarySearch(target, mid + 1, end);
        } else {
            return binarySearch(target, start, mid);
        }
    }
}
