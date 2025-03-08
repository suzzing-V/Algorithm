import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static List<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(arr[0]);
        for(int i = 1; i < n; i++) {
            if(arr[i] > lis.get(lis.size() - 1)) {
                lis.add(arr[i]);
            } else if(arr[i] < lis.get(lis.size() - 1)) {
                lis.set(binarySearch(0, lis.size() - 1, arr[i]), arr[i]);
            }
        }

        bw.write(String.valueOf(lis.size()));
        bw.close();
    }

    private static int binarySearch(int left, int right, int target) {
        if(left == right) return left;

        int mid = (left + right) / 2;
        if(lis.get(mid) < target) {
            return binarySearch(mid + 1, right, target);
        }
        return binarySearch(left, mid, target);
    }
}
