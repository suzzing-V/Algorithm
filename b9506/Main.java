import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = "";
        while(!(line = bf.readLine()).equals("-1")) {
            isPerfect(Integer.parseInt(line));
        }
    }

    public static void isPerfect(int num) {

    }
}
