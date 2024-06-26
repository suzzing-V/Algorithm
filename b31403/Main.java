import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = bf.readLine();
        String b = bf.readLine();
        int c = Integer.parseInt(bf.readLine());

        bw.write(String.valueOf(Integer.parseInt(a) + Integer.parseInt(b) - c) + "\n");
        bw.write(String.valueOf(Integer.parseInt(a + b) - c));
        bw.close();
    }
}
