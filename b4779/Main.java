import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String read = null;
        while((read = bf.readLine()) != null) {
            int n = Integer.parseInt(read);
            bw.write(makeKant(n) + "\n");
        }
        bw.close();
    }

    private static String makeKant(int n) {
        if(n == 0) return "-";

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= (int)Math.pow(3, n - 1); i++) {
            sb.append(' ');
        }

        return makeKant(n - 1) + sb.toString() + makeKant(n - 1);
    }
}
