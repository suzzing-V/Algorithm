import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int minSix = 2000;
    static int minOne = 2000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            minSix = Math.min(minSix, Integer.parseInt(st.nextToken()));
            minOne = Math.min(minOne, Integer.parseInt(st.nextToken()));
        }

        int result = 0;
        if(minSix >= minOne * 6) {
            result = minOne * n;
        } else {
            int sixCnt = n / 6;
            result += sixCnt * minSix;
            n %= 6;
            if(minSix < n * minOne) {
                result += minSix;
            } else {
                result += n * minOne;
            }
        }

        bw.write(String.valueOf(result));
        bw.close();
    }
}
