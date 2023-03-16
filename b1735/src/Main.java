import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] fountain1 = bf.readLine().split(" ");
        int a1 = Integer.parseInt(fountain1[0]);
        int b1 = Integer.parseInt(fountain1[1]);
        String[] fountain2 = bf.readLine().split(" ");
        int a2 = Integer.parseInt(fountain2[0]);
        int b2 = Integer.parseInt(fountain2[1]);

        int lcm = getLcm(b1, b2);
        a1 *= lcm / b1;
        a2 *= lcm / b2;

        int a3 = a1 + a2, b3 = lcm;
        int gcd = getGcd(a3, b3);
        bw.write(Integer.toString(a3 / gcd) + " " + Integer.toString(b3 / gcd));
        bw.close();
    }

    public static int getLcm(int x, int y) {
        int min = x < y ? x : y;
        int i = 2;
        int gcd = 1;
        while(i <= min) {
            if(x % i == 0 && y % i == 0) {
                x /= i; y /= i;
                gcd *= i;
            } else {
                i++;
            }
        }
        return gcd * x * y;
    }

    public static int getGcd(int x, int y) {
        int min = x < y ? x : y;
        int i = 2;
        int gcd = 1;
        while(i <= min) {
            if(x % i == 0 && y % i == 0) {
                x /= i; y /= i;
                gcd *= i;
            } else {
                i++;
            }
        }
        return gcd;
    }
}
