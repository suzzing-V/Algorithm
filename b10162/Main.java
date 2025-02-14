import java.io.*;
import java.util.*;

public class Main {

    private static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(bf.readLine());

        int a = 0;
        while(t >= 300) {
            t -= 300;
            a ++;
        }
        int b = 0;
        while(t >= 60) {
            t -= 60;
            b ++;
        }
        int c = 0;
        while(t >= 10) {
            t -= 10;
            c ++;
        }
        if(t != 0) {
            bw.write("-1");
        } else {
            bw.write(a + " " + b + " " + c);
        }
        bw.close();
    }
}
