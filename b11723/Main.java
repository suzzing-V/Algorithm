import java.io.*;
import java.util.*;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        int s = 0;
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String com = st.nextToken();
            int x = 0;
            if(st.hasMoreTokens()) {
                x = Integer.parseInt(st.nextToken());
            }

            if(com.equals("add")) {
                s |= (1 << x);
            } else if(com.equals("remove")) {
                s &= ~(1 << x);
            } else if(com.equals("check")) {
                if((s & (1 << x)) == 0) {
                    bw.write("0\n");
                } else {
                    bw.write("1\n");
                }
            } else if(com.equals("toggle")) {
                s ^= (1 << x);
            } else if(com.equals("all")) {
                s = (1 << 21) - 1;
            } else if(com.equals("empty")) {
                s = 0;
            }
        }

        bw.close();
    }
}
