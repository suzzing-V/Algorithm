import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static List<Integer> saved = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        arr = new int[n];

        StringTokenizer st =new StringTokenizer(bf.readLine());
        for(int i = 0; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        saved.add(arr[0]);
        for(int i = 1; i < n; i++) {
            if(arr[i] > saved.get(saved.size() - 1)) {
                saved.add(arr[i]);
            } else {
                binarySearch(arr[i], 0, saved.size() - 1);
            }
        }

        System.out.println(saved.size());
    }

    public static void binarySearch(int des, int startIdx, int endIdx) {
        if(startIdx >= endIdx) {
            saved.set(startIdx, des);
            return;
        }
        int midIdx = (startIdx + endIdx) / 2;

        if(saved.get(midIdx) < des) {
            binarySearch(des, midIdx + 1, endIdx);
        } else {
            binarySearch(des, startIdx, midIdx);
        }
    }
}
