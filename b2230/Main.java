import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n];

        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(bf.readLine());
        }

        int result = Integer.MAX_VALUE;
        Arrays.sort(a);
        int start = 0;
        int end = 0;
        while(start < n && end < n) {
            int diff = a[end] - a[start];
            if(diff ==  m) {
                result = m;
                break;
            } else if(diff > m) {
                result = Math.min(diff, result);
                start ++;
            } else {
                end ++;
            }
        }
        System.out.println(result);
    }
}
