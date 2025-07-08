import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int N;
    static int[] dp = new int[N + 1];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp[1] = 0;
        makeOne(N);

        System.out.println(dp[N]);
    }

    public static void makeOne(int n) {
        if (n) {
        }
    }
}