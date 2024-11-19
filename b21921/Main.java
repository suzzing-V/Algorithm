import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] visit = new int[n];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            visit[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = left + x - 1;
        int max = 0;
        int cnt = 0;
        int sum = 0;
        for(int i = 0; i < x; i++) {
            sum += visit[i];
        }

        while(right <= visit.length - 1) {
            if(sum > max) {
                max = sum;
                cnt = 1;
            } else if(sum == max) {
                cnt ++;
            }

            if(++right != visit.length) {
                sum -= visit[left ++];
                sum += visit[right];
            }
        }

        if(max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }
    }
}
