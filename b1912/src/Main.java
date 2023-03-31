import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        String[] arrStr = new String[n];
        arrStr = bf.readLine().split(" ");

        int[] arr = new int[n];
        long[] total = new long[n - 1];
        long max = -1000;
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }
        for(int i = 0; i < n - 1; i++) {
            total[i] = arr[i];
            if(max <= total[i])
                max = total[i];
        }
        
        int j;
        for(int i = 1; i < n; i++) {
            j = 0;
            for(int k = i; k < n; k++) {
                total[j] += arr[k];
                if(max <= total[j])
                    max = total[j];
                j++;
            }
        }
    
        bw.write(Long.toString(max));
        bw.close();
    }
}
