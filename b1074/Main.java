import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int r;
    static int c;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        findRC(r, c, (int)Math.pow(2, n));
        bw.write(String.valueOf(result));
        bw.close();
    }

    private static void findRC(int r, int c, int size) {
        if(size == 1) {
            return;
        }
        int nextSize = size / 2;

        if(r < nextSize && c < nextSize) {
            findRC(r, c, nextSize);
        } else if(r < nextSize && c >= nextSize) {
            result += size * size / 4;
            findRC(r, c - nextSize, nextSize);
        } else if(r >= nextSize && c < nextSize) {
            result += size * size / 4 * 2;
            findRC(r - nextSize, c, nextSize);
        } else if(r >= nextSize && c >= nextSize) {
            result += size * size / 4 * 3;
            findRC(r - nextSize, c - nextSize, nextSize);
        }
    }
}
