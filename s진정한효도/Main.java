import java.io.*;
import java.util.*;

public class Main {

    static int[][] ground = new int[3][3];
    static int minPower = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < 3; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
            }
            minPower = Math.min(minPower, getPowerToN(1, i));
            minPower = Math.min(minPower, getPowerToN(2, i));
            minPower = Math.min(minPower, getPowerToN(3, i));
        }

        for(int i = 0; i < 3; i++) {
            minPower = Math.min(minPower, getPowerToNReverse(1, i));
            minPower = Math.min(minPower, getPowerToNReverse(2, i));
            minPower = Math.min(minPower, getPowerToNReverse(3, i));
        }

        bw.write(String.valueOf(minPower));
        bw.close();
    }

    private static int getPowerToN(int n, int i) {
        int power = 0;
        for(int j = 0; j < 3; j++) {
            power += Math.abs(ground[i][j] - n);
        }
        return power;
    }

    private static int getPowerToNReverse(int n, int i) {
        int power = 0;
        for(int j = 0; j < 3; j++) {
            power += Math.abs(ground[j][i] - n);
        }
        return power;
    }
}
