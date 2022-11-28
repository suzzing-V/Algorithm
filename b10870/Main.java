package b10870;

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
        
        bw.write(Integer.toString(pibonacci(n)));
        bw.close();
    }

    public static int pibonacci(int n) {
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        
        return pibonacci(n - 1) + pibonacci(n - 2);
    }
}
