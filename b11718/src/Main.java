import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < 100; i++) {
            String str = bf.readLine();
            if(str.length() == 0) break;
            bw.write(str + "\n");
        }
        bw.close();
    }
}
