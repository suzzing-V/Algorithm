import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static int x;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(bf.readLine());
        if(n == 1) {
            bw.write("0");
            bw.close();
            return;
        }
        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;
        long cnt = 0;
        while(left != right) {
            int sum = arr[left] + arr[right];
//            System.out.println(left + " "+ right + " " + sum);
            if(sum < x) {
                left ++;
            } else if(sum > x) {
                right --;
            } else {
                cnt ++;
                left ++;
            }
        }

        bw.write(String.valueOf(cnt));
        bw.close();
    }
}
