import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        int[] clothes = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            clothes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(clothes);
        int count = 0;
        int start = 0;
        int end = n - 1;
        while(start < end) {
            if(clothes[start] + clothes[end] == m) {
                count++;
                start ++;
                end --;
            } else if(clothes[start] + clothes[end] < m) {
                start ++;
            } else {
                end --;
            }
        }
        System.out.println(count);
    }
}
