import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] isP;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        isP = new boolean[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            countOddP(arr, i);
            if(i + 1 <= n && arr[i] == arr[i + 1]) {
                countEvenP(arr, i , i + 1);
            }
        }
        int t = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(isP[start][end]) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
        System.out.print(sb.toString());
    }

    public static void countOddP(int[] arr, int mid) {
        int left = mid;
        int right = mid;
        while(left >= 1 && right < arr.length && arr[left] == arr[right]) {
            isP[left--][right++] = true;
        }
    }

    public static void countEvenP(int[] arr, int mid1, int mid2) {
        int left = mid1;
        int right = mid2;
        while(left >= 1 && right < arr.length && arr[left] == arr[right]) {
            isP[left--][right++] = true;
        }
    }
}
