import java.io.OutputStreamWriter;
import java.nio.BufferOverflowException;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(bf.readLine());

        for(int i = 0; i < T; i++) {
            String str = bf.readLine();
            int l = str.length();
            bw.write(str.charAt(0));
            bw.write(str.charAt(l - 1));
            bw.write("\n");
        }
        bw.close();
    }
}
