import java.io.*;

public class Main {
    static int[][][] memo = new int[21][21][21];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int a, b, c;
        memo[0][0][0] = 1;

        while(true) {
            String[] line = new String[3];
            line = bf.readLine().split(" ");
            a = Integer.parseInt(line[0]); b = Integer.parseInt(line[1]); c = Integer.parseInt(line[2]);
            if(a == -1 && b == -1 && c == -1) break;
            bw.write("w(" + a + ", " + b + ", " + c + ") = " + w(a, b, c) + "\n");
        }
        bw.close();
    }

    public static int w(int a, int b, int c) {
        if(a <= 0 || b <= 0 || c <= 0) {
            a = 0; b = 0; c = 0;
        } else {
            if(a > 20 || b > 20 || c > 20) {
                a = 20; b = 20; c = 20;
            }
            if(memo[a][b][c] == 0) {
                if(a < b && b < c) {
                    memo[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
                } else {
                    memo[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
                }
            }
        }
        return memo[a][b][c];
    }
}
