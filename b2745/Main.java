import java.io.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = bf.readLine();
        String[] split = line.split(" ");
        String n = split[0];
        int b = Integer.parseInt(split[1]);

        int result = 0;
        for(int i = 0; i < n.length(); i++) {
            int num = getNum(n.charAt(i));
            System.out.println(num);
            double square = Math.pow(b, n.length() - 1 - i);
            System.out.println(square);
            result += num * (int)square;
        }
        bw.write(String.valueOf(result));
        bw.close();
    }

    public static int getNum(char c) {
        if(c >= '0' && c <= '9') return c - '0';
        return c - 55;
    }
}
