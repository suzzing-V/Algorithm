import java.io.*;

public class Main {
    static long[] factorialMemo = new long[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());

        factorialMemo[0] = 1;
        factorialMemo[1] = 1;
        bw.write(Integer.toString(getTileCount(n) % 15746));
        bw.close();
    }

    public static int getTileCount(int n) {
        int count = 0;
        for(int i = 0; i <= n / 2; i++) {
            count += factorial(n - i) / (factorial(n - 2 * i) * factorial(i));
        }
        return count;
    }

    public static long factorial(int n) {
        if(factorialMemo[n] != 0) {
            return factorialMemo[n];
        }
        factorialMemo[n] = n * factorial(n - 1);
        System.out.println(n + " " + factorialMemo[n]);
        return factorialMemo[n];
    }
}
