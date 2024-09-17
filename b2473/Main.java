import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        long[] liquid = new long[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            liquid[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(liquid);

        long[] result = new long[3];
        long min = Long.MAX_VALUE;
        for(int i = 0; i <= n - 3; i++) {
            long selected = liquid[i];
            int left = i + 1;
            int right = n - 1;
            while(left < right) {
                long sum = selected + liquid[left] + liquid[right];
                if(Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    result[0] = selected;
                    result[1] = liquid[left];
                    result[2] = liquid[right];
                }

                if(sum > 0) {
                    right --;
                } else if(sum < 0) {
                    left ++;
                } else {
                    result[0] = selected;
                    result[1] = liquid[left];
                    result[2] = liquid[right];
                    Arrays.sort(result);
                    for(int j = 0; j < 3; j++) {
                        System.out.print(result[j] + " ");
                    }
                    return;
                }
            }
        }
        Arrays.sort(result);
        for(int j = 0; j < 3; j++) {
            System.out.print(result[j] + " ");
        }
    }
}
