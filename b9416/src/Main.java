import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());

        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            long[] triangle = new long[n + 1];
            triangle[1] = 1;
            if(n >= 2) triangle[2] = 1;
            if(n >= 3) triangle[3] = 1;
            for(int j = 4; j <= n; j++) {
                triangle[j] = triangle[j - 2] + triangle[j - 3];
            }
            bw.write(Long.toString(triangle[n]) + "\n");
        }
        bw.close();
    }
}
