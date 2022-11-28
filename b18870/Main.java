package b18870;

import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        String[] strArr = bf.readLine().split(" ");
        HashMap<Integer, Integer> rank = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }

        int[] sort = new int[n]; //정렬된 배열
        System.arraycopy(arr, 0, sort, 0, sort.length);
        quick_sort(sort, 0, n - 1);

        int order = 0;
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                rank.put(sort[i], order);
            } else if(sort[i] != sort[i - 1]) {
                rank.put(sort[i], ++order);
            } else {
                rank.put(sort[i], order);
            }
        }

        for(int i = 0; i < n; i++) {
            bw.write(rank.get(arr[i]) + " "); //hashmap.get(key): key에 해당하는 value값
        }
        bw.close();
    }

    public static void quick_sort(int[] sort, int start, int end) {
        int pivot = sort[(start + end) / 2];
        int left = start;
        int right = end;

        if(start >= end) 
            return;
        while(left <= right) {
            while(sort[left] < pivot) {
                left++;
            }

            while(sort[right] > pivot) {
                right--;
            }

            if(left <= right) {
                swap(sort, left, right);
                left++; right--;
            }
        }

        quick_sort(sort, start, right);
        quick_sort(sort, left, end);
    }

    public static void swap(int[] sort, int left, int right) {
        int tmp;
        
        tmp = sort[left];
        sort[left] = sort[right];
        sort[right] = tmp;       
    }
}