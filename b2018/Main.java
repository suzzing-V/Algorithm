import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[10000001];
        for(int i = 1; i <= 10000000; i++) {
            arr[i] = i;
        }

        int start = 1;
        int end = 1;
        int sum = 1;
        int result = 0;
        while(start <= 10000000) {
            if(sum > n) {
                sum -= arr[start];
                start ++;
            } else if(sum == n) {
                sum -= arr[start];
                start ++;
                result ++;
            } else {
                end ++;
                sum += arr[end];
            }
        }

        System.out.println(result);
    }
}
