package b24060;

import java.io.*;

public class Main {
    static int num = 0;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] nk = bf.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[] arr = new int[n];
        int[] sorted = new int[n];
        String[] strArr = bf.readLine().split(" ");

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }
        
        merge_sort(arr, 0, n - 1, k, sorted);
        if(count < k)
            bw.write(Integer.toString(-1));
        else
            bw.write(Integer.toString(num));
        bw.close();
    }

    public static void merge_sort(int[] arr, int start, int end, int k, int[] sorted) {
        int mid = (start + end) / 2;
    
        if(start >= end)
            return;
        merge_sort(arr, start, mid, k, sorted);
        merge_sort(arr, mid + 1, end, k, sorted);
        merge(arr, start, end, k, sorted);
    }

    public static void merge(int[] arr, int start, int end, int k, int[] sorted) {
        int mid = (start + end) / 2;
        int left = start;
        int right = mid + 1;
        int m = start;

        while(left <= mid && right <= end) {
            if (arr[left] < arr[right]) {
                sorted[m++] = arr[left++];
            } else {
                sorted[m++] = arr[right++];
            }
        }  
        
        if(left > mid) {
            for(int i = right; i <= end; i++) {
                sorted[m++] = arr[i];
            }
        } else {
            for(int i = left; i <= mid; i++) {
                sorted[m++] = arr[i];
            }
        }

        for(int i = start; i <= end; i++) {
            arr[i] = sorted[i];
            count++;
            if(count == k) {
                num = arr[i];
            }
        }
    }
}