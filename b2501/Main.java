import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = bf.readLine();
        String[] split = line.split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        int count = 0;
        for(int i = 1; i <= n; i++) {
            if(n % k == 0) count++;
            if(count == k) {
                bw.write(String.valueOf(i));
                break;
            }
        }
        bw.write("0");
        bw.close();
    }
}
