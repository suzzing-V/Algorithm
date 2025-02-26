import java.io.*;
import java.util.*;

public class Main {

    static int[] parents;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n];
        for(int i = 0; i < n; i ++) {
            parents[i] = i;
        }

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if(find(n1) == find(n2)) {
                bw.write(String.valueOf(i));
                bw.close();
                return;
            }
            union(n1, n2);
        }

        bw.write("0");
        bw.close();
    }

    private static int find(int x) {
        if(parents[x] == x) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a > b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }
}
