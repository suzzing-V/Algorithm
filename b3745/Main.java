import java.io.*;
import java.util.*;

public class Main {

    private static List<Integer> lis = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            String line = bf.readLine();
            if(line == null) break;

            int n = Integer.parseInt(line.trim());
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            lis = new ArrayList<>();
            lis.add(arr[0]);
            for(int i = 1; i < arr.length; i++) {
                int last = lis.get(lis.size() - 1);
                if(arr[i] > last) {
                    lis.add(arr[i]);
                } else if(arr[i] < last) {
                    int idx = binarySearch(0, lis.size() - 1, arr[i]);
                    lis.set(idx, arr[i]);
                }
            }

            bw.write(lis.size() + "\n");
        }
        bw.close();
    }

    private static int binarySearch(int left, int right, int target) {
        if(left == right) {
            return left;
        }

        int mid = (left + right) / 2;
        if(lis.get(mid) < target) {
            return binarySearch(mid + 1, right, target);
        }
        return binarySearch(left, mid, target);
    }
}
