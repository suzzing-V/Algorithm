import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        parent = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            union(start, end);
        }

        int result = 0;
        for(int i = 2; i <= n; i++) {
            if(find(i) == 1) {
                result ++;
            }
        }
        System.out.println(result);
    }

    public static int find(int x) {
        if(parent[x] !=  x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}
