import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = bf.readLine();
        int oneCnt = 0;
        int zeroCnt = 0;
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '0') {
                zeroCnt ++;
                while(i < str.length() && str.charAt(i) == '0') {
                    i ++;
                }
                i --;
            } else {
                oneCnt ++;
                while(i < str.length() && str.charAt(i) == '1') {
                    i ++;
                }
                i --;
            }
        }
        bw.write(String.valueOf(Math.min(oneCnt, zeroCnt)));
        bw.close();
    }
}
