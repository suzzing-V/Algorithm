package b10989;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());

        int result = 3;
        for(int i = 1; i < n; i++) result += (int)Math.pow(2, i);
        result = (int)Math.pow(result, 2);
        bw.write(String.valueOf(result));
        bw.close();
    }
}
