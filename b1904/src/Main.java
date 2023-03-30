import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int[] tileCount = new int[n + 1];
        tileCount[1] = 1;
        if(n != 1) {
            tileCount[2] = 2;
            for(int i = 3; i <= n; i++) {
                tileCount[i] = tileCount[i - 1] + tileCount[i - 2];
            }
        }
        bw.write(Integer.toString(tileCount[n]));
        bw.close();
    }
}
