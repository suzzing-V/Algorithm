import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = bf.readLine();
        String[] split = line.split(" ");

        int a = Integer.parseInt(split[0]); int b = Integer.parseInt(split[1]); int c = Integer.parseInt(split[2]);
        int d = Integer.parseInt(split[3]); int e = Integer.parseInt(split[4]); int f = Integer.parseInt(split[5]);

        for(int x = -999; x < 1000; x++) {
            for(int y = -999; y < 1000; y++) {
                if(a * x + b * y == c && d * x + e * y == f) {
                    bw.write(String.valueOf(x) + " " + String.valueOf(y));
                    bw.close();
                    return;
                }
            }
        }
    }
}
