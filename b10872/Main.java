package b10872;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());

        bw.write(Integer.toString(factorial(n)));
        bw.close();
    }

    public static int factorial(int n) {
        if(n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }
}
