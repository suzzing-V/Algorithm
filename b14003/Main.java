import java.io.*;
import java.util.*;

public class Main {

    static List<Integer> lis = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        int[] index = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(arr[0]);
        index[0] = 0;
        for(int i = 1; i < n; i++) {
            int last = lis.get(lis.size() - 1);
            if(arr[i] > last) {
                lis.add(arr[i]);
                index[i] = lis.size() - 1;
            } else {
                int idx = binarySearch(0, lis.size() - 1, arr[i]);
                lis.set(idx, arr[i]);
                index[i] = idx;
            }
        }

        System.out.println(lis.size());
        int count = lis.size() - 1;
        List<Integer> result = new ArrayList<>();
        for(int i = n - 1; i >= 0; i--) {
            if(count == index[i]) {
                result.add(arr[i]);
                count --;
            }
        }

        for(int i = result.size() - 1; i >= 0; i --) {
            System.out.print(result.get(i) + " ");
        }
    }

    public static int binarySearch(int start, int end, int des) {
        if(start >= end) {
            return start;
        }
        int mid = (start + end) / 2;

        if(lis.get(mid) >= des) {
            return binarySearch(start, mid, des);
        } else {
            return binarySearch(mid + 1, end, des);
        }
    }
}
