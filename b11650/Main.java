package b11650;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr_x = new int[n];
        int[] arr_y = new int[n];

        for (int i = 0; i < n; i++) {
            String[] line = bf.readLine().split(" ");
            arr_x[i] = Integer.parseInt(line[0]);
            arr_y[i] = Integer.parseInt(line[1]);
        }

        quick_sort(arr_x, arr_y, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.println(arr_x[i] + " " + arr_y[i]);
        }
        bf.close();
    }

    public static void quick_sort(int[] arr_x, int[] arr_y, int start, int end) {
        int left = start;
        int right = end;
        int pivot = arr_x[(left + right) / 2];
        int pivot_y = arr_y[(left + right) / 2];

        if (start >= end)
            return;

        while (left < right) { //left와 right가 뒤바뀌면 멈춤 //pivot에서 뒤바뀔 수 밖에 없음
            while (arr_x[left] < pivot || (arr_x[left] == pivot && arr_y[left] < pivot_y)) //조건에 안 맞을 때까지 left 이동하기
                left++;
            while (arr_x[right] > pivot || (arr_x[right] == pivot && arr_y[right] > pivot_y)) //조건에 안 맞을 때까지 right 이동하기
                right--;
 
            if (left <= right) { //증가하고도 뒤바뀌지 않는지 검사해야됨
                swap_RL(arr_x, arr_y, left, right);
                left ++;
                right --;
            }
        }

        if(start < right) quick_sort(arr_x, arr_y, start, right);
        if(end > left) quick_sort(arr_x, arr_y, left, end);
    }

    public static void swap_RL(int[] arr_x, int[] arr_y, int left, int right) {
        int tmp_x;
        int tmp_y;
            tmp_x = arr_x[left];
            arr_x[left] = arr_x[right];
            arr_x[right] = tmp_x;

            tmp_y = arr_y[left];
            arr_y[left] = arr_y[right];
            arr_y[right] = tmp_y;
    }
}