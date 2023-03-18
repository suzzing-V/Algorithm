import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());
        int count;

        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            count = 0;
            for(int j = 2; j <= n / 2; j++) {
                if(isPrime(j) && isPrime(n - j)) {
                    count ++;
                }
            }
            bw.write(Integer.toString(count) + "\n");
        }
        bw.close();
    }

    public static boolean isPrime(int n) {
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) { return false; }
        }
        return true;
    }
}
