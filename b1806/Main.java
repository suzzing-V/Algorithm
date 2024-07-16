import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        long[] original = new long[n];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            original[i] = Long.parseLong(st.nextToken());
        }

        int low = 0;
        int high = 0;
        long sum = original[0];
        int len = n + 1;
        while(low <= high && high < n) {
            if(sum < s) {
                if(++ high == n) {
                    break;
                }
                sum += original[high];
            } else {
                len = Math.min(len, high - low + 1);
                sum -= original[low++];
            }
        }
        if(len == n + 1) {
            System.out.println("0");
        } else {
            System.out.println(len);
        }
    }
}
