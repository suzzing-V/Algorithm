import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = bf.readLine();
        int i = Integer.parseInt(bf.readLine());
        bw.write(str.charAt(i - 1)); //char도 그냥 출력됨
        bw.close();
    }
}
