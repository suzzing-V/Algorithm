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
        int[][] wnh = new int[n][2];

        for(int i = 0; i < n; i++) {
            String[] wnhStr = new String[2];
            wnhStr = bf.readLine().split(" ");
            wnh[i][0] = Integer.parseInt(wnhStr[0]);
            wnh[i][1] = Integer.parseInt(wnhStr[1]);
        }    
    }
}
