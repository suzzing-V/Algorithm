import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        
        int[] stair = new int[n];
        for(int i = 0; i < n; i++) {
            stair[i] = Integer.parseInt(bf.readLine());
        }

        int[] memo = new int[n];
        if(n == 1) {
            bw.write(Integer.toString(stair[0]));
        } else if (n == 2) {
            bw.write(Integer.toString(stair[0] + stair[1]));
        } else {
            memo[0] = stair[0]; memo[1] = stair[0] + stair[1];
            memo[2] = Math.max(memo[1], memo[0]) + stair[2];
            for(int i = 3; i < n; i++) {
                memo[i] = Math.max(memo[i - 3] + stair[i - 1], memo[i - 2]) + stair[i];
            }
            bw.write(Integer.toString(memo[n - 1]));
    }
    bw.close();
}
}
