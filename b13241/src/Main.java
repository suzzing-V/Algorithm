import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] ab = bf.readLine().split(" ");
        long a = Long.parseLong(ab[0]);
        long b = Long.parseLong(ab[1]);
        long min = a < b ? a : b;

        int i = 2;
        long lcm = 1;
        while(i <= min) {
            if(a % i == 0 && b % i == 0) {
                a /= i; b /= i;
                lcm *= i;
            } else i ++;
        }

        bw.write(Long.toString(lcm * a * b));
        bw.close();
    }
}
