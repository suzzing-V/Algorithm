import java.io.*;

public class Main {
    static int code1;
    static int code2;
    static int[] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        memo = new int[n];
        fib(n);
        fibonacci(n);
        bw.write(Integer.toString(code1) + " " + Integer.toString(code2));
        bw.close();
    }
    
    public static int fib(int n) {
        if (n == 1 || n == 2) {
            code1++;
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    public static int fibonacci(int n) {
        memo[0] = 1;
        memo[1] = 1;
        for(int i = 2; i < n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
            code2++;
        }
        return memo[n - 1];
    }
}
