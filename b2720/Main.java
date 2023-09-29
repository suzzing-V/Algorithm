import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bf.readLine());
        for(int i = 0; i < t; i++) {
            int num = Integer.parseInt(bf.readLine());

            num = calculateUnit(num, 25, bw);
            num = calculateUnit(num, 10, bw);
            num = calculateUnit(num, 5, bw);
            calculateUnit(num, 1, bw);
            bw.write("\n");
        }

        bw.close();
    }

    public static int calculateUnit(int num, int unit, BufferedWriter bw) throws IOException {
        int i = 1;

        while(num >= unit) {
            num -= unit;
            i++;
        }
        bw.write(String.valueOf(i - 1) + " ");
        return num;
    }
}