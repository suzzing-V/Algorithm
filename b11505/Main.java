import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int k;
    static long MOD = 1000000007;
    static long[] arr;
    static long[] seg_tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n];
        seg_tree = new long[n * 4];

        for(int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }

        buildTree(1, 0, n - 1);

        int cmdNum = m + k;
        for(int i = 0; i < cmdNum; i++) {
            st = new StringTokenizer(bf.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            long first = Long.parseLong(st.nextToken());
            long second = Long.parseLong(st.nextToken());

            if(cmd == 1) {
                updateTree(1, first - 1, second, 0, n - 1);
            } else {
                bw.write(findTree(1, first - 1, second - 1, 0, n - 1) + "\n");
            }
        }

        bw.close();
    }

    private static long buildTree(int curr, int left, int right) {
        if(left == right) {
            seg_tree[curr] = arr[left];
            return seg_tree[curr];
        }

        int mid = (left + right) / 2;
        long leftTree = buildTree(curr * 2, left, mid);
        long rightTree = buildTree(curr * 2 + 1, mid + 1, right);
        return seg_tree[curr] = ((leftTree % MOD) * (rightTree % MOD)) % MOD;
    }

    private static void updateTree(int curr, long target, long value, int left, int right) {
        if(left == right && target == right) {
            seg_tree[curr] = value;
            return;
        }

        if(target > right || target < left) {
            return;
        }

        int mid = (left + right) / 2;
        updateTree(curr * 2, target, value, left, mid);
        updateTree(curr * 2 + 1, target, value, mid + 1, right);
        seg_tree[curr] = ((seg_tree[curr * 2] % MOD) * (seg_tree[curr * 2 + 1] % MOD)) % MOD;
    }

    private static long findTree(int curr, long fl, long fr, int left, int right) {
        if(left >= fl && right <= fr) {
            return seg_tree[curr];
        }
        if(fl > right || fr < left) {
            return 1;
        }

        int mid = (left + right) / 2;
        long leftTree = findTree(curr * 2, fl, fr, left, mid);
        long rightTree = findTree(curr * 2 + 1, fl, fr, mid + 1, right);
        return ((leftTree % MOD) * (rightTree % MOD)) % MOD;
    }
}
