import java.io.*;
import java.util.*;

public class Main {

    static int m;
    static int n;
    static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n];

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        dfs( 0, "", 0);
    }

    public static void dfs(int count, String seq, int index) {
        if(count == m) {
            System.out.println(seq);
            return;
        }

        for(int i = index; i < n; i++) {
            if(i > 0 && array[i] == array[i - 1]) {
                continue;
            }
            dfs(count + 1, seq + array[i] + " ", i);
        }
    }
}
