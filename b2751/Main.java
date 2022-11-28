package b2751;

import java.io.*;

public class Main {
    public static void sortArr(int[] tmp, int[] arr, int left, int right) {
        int L = left;
        int mid = (left + right) / 2;
        int R = mid + 1;
        int k = left;

        while (L <= mid && R <= right) {
            tmp[k++] = arr[L] <= arr[R]? arr[L++] : arr[R++];
        }

        if (L > mid) {
            for (int a = R; a <= right; a++) {
                tmp[k++] = arr[a];
            }
        } else {
            for (int a = L; a <= mid; a++) {
                tmp[k++] = arr[a];
            }
        }

        for (int i = left; i <= right; i++) {
            arr[i] = tmp[i];
        }
    }

    public static void mergeSort(int[] tmp, int[] arr, int left, int right) {
        int mid = (left + right) / 2;

        if(left == right) {
            return ;
        }
        mergeSort(tmp, arr, left, mid);
        mergeSort(tmp, arr, mid + 1, right);
        sortArr(tmp, arr, left, right);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int[] tmp = new int[n];
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        mergeSort(tmp, arr, 0, n - 1);

        for (int i = 0; i < n; i++) {
            bw.write(Integer.toString(arr[i]));
            bw.newLine();
        }
        bw.close();
    }
}
