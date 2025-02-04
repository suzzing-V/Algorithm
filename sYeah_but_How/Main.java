import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] split = bf.readLine().split("");
        int i = 0;
        while(i < split.length) {
            while(split[i].equals("(")) {
                // System.out.println(split[i]);
                bw.write("(");
                i++;
            }
            bw.write("1");
            while(i < split.length && split[i].equals(")")) {
                // System.out.println(split[i]);
                bw.write(")");
                i++;
            }
            if(i != split.length) {
                bw.write("+");
            }
        }
        bw.close();
    }
}

