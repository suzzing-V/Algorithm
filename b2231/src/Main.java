import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int total = 0;
        int j;

        for(int i = 1; i < n; i++) {
            total = i;
            j = i;
            while(j != 0) {
                total += j % 10;
                j /= 10;
            }
            if (total == n) {
                bw.write(Integer.toString(i));
                break;
            }
        }
        if(total != n) {
            bw.write("0");
        }
        bw.close();
    }
}