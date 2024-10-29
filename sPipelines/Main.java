import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] s;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        s = new int[n];
        for(int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(s);

        System.out.println(String.valueOf(n + s[n - 1] - 1));
    }
}

