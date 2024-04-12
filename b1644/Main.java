import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer> prime;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());

        prime = new ArrayList<>();
        getPrime();

        bw.write(String.valueOf(sumPrime()));
        bw.close();
    }

    public static void getPrime() {
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;

        for(int i = 2; i <= n; i++) {
            if(isPrime[i])
                prime.add(i);
            for(int j = 2; i * j <= n; j++) {
                isPrime[i * j] = false;
            }
        }
    }

    public static int sumPrime() {
        int result = 0;
        int primeSize = prime.size();

        int start = 0;
        int end = 0;
        int sum = 0;
        if(isPrime[n]) result ++;

        while(end < primeSize && start <= end) {
            if(sum < n) {
                sum += prime.get(end ++);
            } else if(sum > n) {
                sum -= prime.get(start ++);
            } else {
                result ++;
                sum -= prime.get(start ++);
            }
        }

        return result;
    }
}
