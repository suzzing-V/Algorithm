import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());

        int[] arr = new int[n + 1];
        String[] arrStr = new String[n];
        arrStr = bf.readLine().split(" ");
        for(int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(arrStr[i - 1]);
        }
        int[] memo = new int[n + 1];
        memo[1] = 1;
        int max;
        for(int i = 2; i <= n; i++) {
            max = 0;
            for(int j = 1; j < i; j++) {
                if(arr[j] < arr[i] && max < memo[j]) {
                    max = memo[j]; 
                }
            }
            memo[i] = max + 1;
        }
        Arrays.sort(memo);
    bw.write(Integer.toString(memo[n]));
    bw.close();
}
}
