import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        String[] arr = new String[n];
        arr = bf.readLine().split(" ");
        int sum = 0;
        for(int i = 1; i <= n; i++) sum += i;
        int[] total = new int[sum];
        int i = 0;
        while(i < n) {
            total[i] = Integer.parseInt(arr[i]);
            i++;
        }
        
        for(int j = 1; j <= n; j++) {
            for(int k = j; k <= n - 1; k++) {
                total[i] = total[k] + total[i - (n - (j - 1))];
                i++;
            }
        }
        Arrays.sort(total);
    
        bw.write(Integer.toString(total[i - 1]));
        bw.close();
    }
}
