import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        int[] clothes = new int[10000001];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            clothes[Integer.parseInt(st.nextToken())] ++;
        }

        int count = 0;
        int end = m / 2;
        if(m % 2 == 0) {
            count += clothes[m / 2] / 2;
            end = m / 2 - 1;
        }
        for(int i = 1; i <= end; i++) {
            if(clothes[i] > 0 && clothes[m - i] > 0) {
                count += Math.min(clothes[i], clothes[m - i]);
            }
        }
        System.out.println(count);
    }
}
