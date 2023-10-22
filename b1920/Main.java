import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        String[] splitA = bf.readLine().split(" ");
        int m = Integer.parseInt(bf.readLine());
        String[] splitO = bf.readLine().split(" ");
        int[] arr1 = StringToInt(splitA);
        int[] arr2 = StringToInt(splitO);

        Arrays.sort(arr1);
        for(int i = 0; i < m; i++) {
            if(isPresent(arr2[i], arr1, 0, m - 1)) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }
        bw.close();
    }

    public static int[] StringToInt(String[] split) {
        int[] arr = new int[split.length];
        
        for(int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        return arr;
    }

    public static boolean isPresent(int num, int[] arr, int start, int end) {
        if(start > end) return false;
        int mid = (start + end) / 2;

        if(num == arr[mid]) {
            return true;
        } else if(num < arr[mid]) {
            if(isPresent(num, arr, start, mid - 1)) return true;
        } else {
            if(isPresent(num, arr, mid + 1, end)) return true;
        }
        return false;
    }
}
