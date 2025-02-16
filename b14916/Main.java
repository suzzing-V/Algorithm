import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int result = n / 5;
        n %= 5;
        if(result == 0 && n % 2 != 0) {
            result = -1;
        } else if(n % 2 != 0) {
            result --;
            n += 5;
            result += n / 2;
        } else {
            result += n / 2;
        }

        bw.write(String.valueOf(result));
        bw.close();
    }
}
