import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int m = (int)Math.pow(n, 2);
        long[] ab = new long[m];
        long[] cd = new long[m];
        long[] a = new long[n];
        long[] b = new long[n];
        long[] c = new long[n];
        long[] d = new long[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            a[i] = Long.parseLong(st.nextToken());
            b[i] = Long.parseLong(st.nextToken());
            c[i] = Long.parseLong(st.nextToken());
            d[i] = Long.parseLong(st.nextToken());
        }

        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                ab[idx++] = a[i] + b[j];
            }
        }

        idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                cd[idx++] = c[i] + d[j];
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        int left = 0;
        int right = m - 1;
        long count = 0;
        while(left <= m - 1 && right >= 0) {
            long abv = ab[left];
            long cdv = cd[right];
            if(abv + cdv == 0) {
                long abCount = 0;
                long cdCount = 0;
                while(left <= m - 1 && ab[left] == abv) {
                    left++;
                    abCount++;
                }
                while(right >= 0 && cd[(int)right] == cdv) {
                    right--;
                    cdCount++;
                }
                count += abCount * cdCount;
            } else if(abv + cdv < 0) {
                left++;
            } else {
                right --;
            }
        }

        System.out.println(String.valueOf(count));
    }
}
