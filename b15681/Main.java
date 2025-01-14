import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int r;
    static int q;
    static List<Integer>[] tree;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        memo = new int[n + 1];
        for(int i = 0; i < n + 1; i++) {
            memo[i] = -1;
        }
        tree = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }
        makeTreeByRoot(r, 0);

        for(int i = 0; i < q; i++) {
            int root = Integer.parseInt(br.readLine());
            System.out.println(countNode(root));
        }
    }

    private static void makeTreeByRoot(int node, int parent) {
        List<Integer> connected = tree[node];
        connected.remove(Integer.valueOf(parent));
        if(connected.isEmpty()) {
            return;
        }
        for(int i : connected) {
            makeTreeByRoot(i, node);
        }
    }

    private static int countNode(int root) {
        if(memo[root] != -1) {
            return memo[root];
        }
        List<Integer> connected = tree[root];
        if(connected.isEmpty()) {
            return 1;
        }
        memo[root] = 1;
        for(int i : connected) {
            int cnt = countNode(i);
            //System.out.println(cnt);
            memo[root] += cnt;
        }
        return memo[root];
    }
}
