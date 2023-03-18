import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());
        int count;

        boolean[] prime = new boolean[1000001];
        Arrays.fill(prime,true);

        for(int i = 2; i < 1000001; i++) {
            if(prime[i]) {
                for(int j = 2; i * j < 1000001; j++) {
                    if(prime[i * j]) { prime[i * j] = false; }
                }
            }
        }

        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            count = 0;
            for(int j = 2; j <= n / 2; j++) {
                if(prime[j] && prime[n - j]) {
                    count++;
                }
            }
            bw.write(Integer.toString(count) + "\n");
        }
        bw.close();
    }
}