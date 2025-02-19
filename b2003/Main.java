import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        arr = new int[n + 1];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        long sum = arr[0];
        int cnt = 0;
        while(right < n) {
//            System.out.println(left + " " + right + " " + sum);
            if(sum < m) {
                right ++;
                sum += arr[right];
            } else if(sum > m) {
                sum -= arr[left];
                left ++;
            } else {
                cnt ++;
                right ++;
                sum += arr[right];
            }
        }

        right --;
        sum -= arr[left];
        left ++;
        while(left <= right) {
            if(sum == m) {
                cnt ++;
                break;
            }
            sum -= arr[left];
            left ++;
        }

        bw.write(String.valueOf(cnt));
        bw.close();
    }
}
