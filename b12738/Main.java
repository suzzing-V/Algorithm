import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int[] des;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        des = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int d = 1;
        des[0] = arr[0];
        for(int i = 1; i < n; i++) {
            if(arr[i] > des[d - 1]) {
                des[d ++] = arr[i];
            } else if(arr[i] < des[d - 1]) {
                int desIdx = binarySearch(arr[i], 0, d - 1); // 정렬되어 있으므로 이분탐색
                des[desIdx] = arr[i];
            }
        }

        bw.write(String.valueOf(d));
        bw.close();
    }

    private static int binarySearch(int target, int start, int end) {
        int mid = (start + end) / 2;
        if(start >= end) {
            return start;
        }
        if(target > des[mid]) {
            return binarySearch(target, mid + 1, end);
        } else {
            return binarySearch(target, start, mid);
        }
    }
}
