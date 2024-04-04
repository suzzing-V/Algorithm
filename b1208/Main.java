import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int s;
    static int[] sequence;
    static Map<Integer, Integer> sumCount = new HashMap<>();
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        sequence = new int[n];
        for(int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        left(0, 0);
        right(n/2, 0);
        if(s == 0) {
            result --;
        }

        bw.write(String.valueOf(result));
        bw.close();
    }

    public static void left(int idx, int sum) {
        if(idx >= n / 2) {
            if(sumCount.containsKey(sum)) {
                sumCount.put(sum, sumCount.get(sum) + 1);
            } else {
                sumCount.put(sum, 1);
            }
            return;
        }
        left(idx + 1, sum);
        left(idx + 1, sum + sequence[idx]);
    }

    public static void right(int idx, int sum) {
        if(idx >= n) {
            if(sumCount.containsKey(s - sum)) {
                result += sumCount.get(s - sum);
            }
            return;
        }

        right(idx + 1, sum);
        right(idx + 1, sum + sequence[idx]);
    }
}
