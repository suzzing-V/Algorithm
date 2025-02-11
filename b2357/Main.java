import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static long[] arr;
    static long[] max_tree;
    static long[] min_tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new long[n];
        max_tree = new long[n * 4];
        min_tree = new long[n * 4];
        for(int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }

        buildMaxTree(1, 0, n - 1);
        buildMinTree(1, 0, n - 1);

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            long max = findMaxTree(1, start - 1, end - 1, 0, n - 1);
            long min = findMinTree(1, start - 1, end - 1, 0, n - 1);
            bw.write(min + " " + max + "\n");
        }
        bw.close();
    }

    private static long buildMaxTree(int curr, int left, int right) {
        if(left == right) {
            max_tree[curr] = arr[left];
            return max_tree[curr];
        }

        int mid = (left + right) / 2;
        long leftTree = buildMaxTree(curr * 2, left, mid);
        long rightTree = buildMaxTree(curr * 2 + 1, mid + 1, right);
        return max_tree[curr] = Math.max(leftTree, rightTree);
    }

    private static long buildMinTree(int curr, int left, int right) {
        if(left == right) {
            min_tree[curr] = arr[left];
            return min_tree[curr];
        }

        int mid = (left + right) / 2;
        long leftTree = buildMinTree(curr * 2, left, mid);
        long rightTree = buildMinTree(curr * 2 + 1, mid + 1, right);
        return min_tree[curr] = Math.min(leftTree, rightTree);
    }

    private static long findMaxTree(int curr, int fl, int fr, int left, int right) {
        if(left >= fl && right <= fr) {
            return max_tree[curr];
        }

        if(fl > right || fr < left) {
            return 0;
        }

        int mid = (left + right) / 2;
        long leftTree = findMaxTree(curr * 2, fl, fr, left, mid);
        long rightTree = findMaxTree(curr * 2 + 1, fl, fr, mid + 1, right);
        return Math.max(leftTree, rightTree);
    }

    private static long findMinTree(int curr, int fl, int fr, int left, int right) {
        if(left >= fl && right <= fr) {
            return min_tree[curr];
        }

        if(fl > right || fr < left) {
            return Long.MAX_VALUE;
        }

        int mid = (left + right) / 2;
        long leftTree = findMinTree(curr * 2, fl, fr, left, mid);
        long rightTree = findMinTree(curr * 2 + 1, fl, fr, mid + 1, right);
        return Math.min(leftTree, rightTree);
    }
}
