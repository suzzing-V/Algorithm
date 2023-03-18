import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());
        
        for(int i = 0; i < t; i++) {
            long n = Long.parseLong(bf.readLine());
            if(n == 0 || n == 1) { bw.write(Integer.toString(2) + "\n"); }
            else {
                while(true) {
                if(isPrime(n)) {
                    bw.write(Long.toString(n) + "\n");
                    break;
                }
                n++;
            }
        }
        }
        bw.close();
    }

    public static boolean isPrime(long n) {
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) { return false; }
        }
        return true;
    }
}
