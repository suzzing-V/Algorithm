package b1181;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        String[] word = new String[n];
        int[] length = new int[n];

        for (int i = 0; i < n; i++) {
            word[i] = bf.readLine();
        }

        for(int i = 0; i < n; i++) {
            length[i] = word[i].length();
        }

        quick_sort(word, length, 0, n - 1);

        System.out.println(word[0]);
        for (int i = 1; i < n; i++) {
            if (word[i - 1].compareTo(word[i]) != 0)
                System.out.println(word[i]);
        }
    }

    public static void quick_sort(String[] word, int[] length, int start, int end) {
        int left = start;
        int right = end;
        int pivotL = length[(start + end) / 2];
        String pivotW = word[(start + end) / 2];

        if(start >= end) {
            return;
        }
        
        while (left <= right) {
            while (length[left] < pivotL || (length[left] == pivotL&& word[left].compareTo(pivotW) < 0)) {
                left++;
            }

            while (length[right] > pivotL || (length[right] == pivotL && pivotW.compareTo(word[right]) < 0)) {
                right--;
            }

            if (left <= right) {
                swap_w(word, left, right);
                swap_l(length, left, right);
                left++; right--;
            }
        }
        quick_sort(word, length, start, right);
        quick_sort(word, length, left, end);
    }

    public static void swap_w(String[] word, int i, int j) {
        String tmp = word[i];

        word[i] = word[j];
        word[j] = tmp;
    }

    public static void swap_l(int[] word, int i, int j) {
        int tmp = word[i];

        word[i] = word[j];
        word[j] = tmp;
    }
}