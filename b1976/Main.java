import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());
        parent = new int[n + 1];
        for(int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= n; j++) {
                if(st.nextToken().equals("1")) {
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int curr = Integer.parseInt(st.nextToken());
        while(st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken());

            if(find(curr) != find(next)) {
                bw.write("NO");
                bw.close();
                return;
            }
        }

        bw.write("YES");
        bw.close();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a > b) parent[a] = find(b);
        else parent[b] = find(a);
    }

    private static int find(int x) {
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }
}
