import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long a;
    static long b;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        dfs(a, 0);
        if(min == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(min);
        }
    }

    public static void dfs(long num, int count) {
        if(num > Integer.MAX_VALUE) {
            return;
        }
        if(num == b) {
            min = Math.min(count + 1, min);
            return;
        }

        dfs(num * 2, count + 1);
        dfs(Long.parseLong(String.valueOf(num) + "1"), count + 1);
    }
}
