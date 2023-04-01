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
        
        int[][] triangle = new int[n][n];
        for(int i = 0; i < n; i++) {
            String[] line = new String[n];
            line = bf.readLine().split(" ");
            for(int j = 0; j < i + 1; j++) {
                triangle[i][j] = Integer.parseInt(line[j]);
            }
        }
        
        int[][] memo = new int[n][n];
        memo[0][0] = triangle[0][0];
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i + 1; j++) {
                if(j == 0) {
                    memo[i][j] = memo[i - 1][0] + triangle[i][j];
                } else if(j == i) {
                    memo[i][j] = memo[i - 1][i - 1];
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - 1])+ triangle[i][j];
                }
            }
        }
        
        int max = 0;
        for(int i = 0; i < n; i++) {
            if(memo[n - 1][i] >= max) {
                max = memo[n - 1][i];
            }
        }

        bw.write(Integer.toString(max));
        bw.close();
    }
}
