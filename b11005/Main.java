import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = bf.readLine();
        String[] split = line.split(" ");
        int n = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        String form = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, b);
        bw.write(getTenToB(form , n, b));
        bw.close();
    }

    public static String getTenToB(String form, int n, int b) {
        if(n <= 0) return "";
        String s = String.valueOf(form.charAt(n % b));
        return getTenToB(form, n / b, b) + s;
    }
}