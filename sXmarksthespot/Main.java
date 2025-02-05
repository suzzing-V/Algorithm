import java.io.*;
import java.util.*;

public class Main {

    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String s = st.nextToken();
            String t = st.nextToken();
            int xIdx = s.indexOf('x');
            if(xIdx == -1) {
                xIdx = s.indexOf('X');
            }
            char c = t.charAt(xIdx);
            if(c >= 'a' && c <= 'z') {
                c -= 'a' - 'A';
            }
            bw.write(String.valueOf(c));
        }
        bw.close();
    }
}

