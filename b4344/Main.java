import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(bf.readLine());

        for(int i = 0; i < c; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int total = 0;
            int[] arr = new int[n];
            for(int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
                total += arr[j];
            }

            double avg = (double)total/n;
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(arr[j] > avg) {
                    count++;
                }
            }

            double per = (double)count/n * 100;
            per = Math.round(per*1000) / 1000.0;
            System.out.println(String.format("%.3f", per) + "%");
        }
    }
}
