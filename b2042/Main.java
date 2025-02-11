import java.io.*;
import java.util.*;

public class Main {

    static long[] arr;
    static long n;
    static long m;
    static long k;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());
        arr = new long[(int)n];
        tree = new long[(int)n * 4];
        for(int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }

        buildTree(1, 0, n - 1);

        long cmdNum = m + k;
        for(int i = 0; i < cmdNum; i++) {
            st = new StringTokenizer(bf.readLine());
            long cmd = Long.parseLong(st.nextToken());
            long target = Long.parseLong(st.nextToken());
            long value = Long.parseLong(st.nextToken());
            if(cmd == 1) {
                updateTree(1, target - 1, value, 0, n - 1);
            } else {
                bw.write(findTree(1, target - 1, value - 1, 0, n - 1) + "\n");
            }
        }

        bw.close();
    }

    private static long buildTree(long curr, long left, long right) {
        if(left == right) {
            tree[(int)curr] = arr[(int)left];
            return tree[(int)curr];
        }

        long mid = (left + right) / 2;
        long leftTree = buildTree(curr * 2, left, mid);
        long rightTree = buildTree(curr * 2 + 1, mid + 1, right);
        return tree[(int)curr] = leftTree + rightTree;
    }

    private static void updateTree(long curr, long target, long value, long left, long right) {
        if(left == right && left == target) {
            tree[(int)curr] = value;
            return;
        }

        if(target < left || target > right) {
            return;
        }

        long mid = (left + right) / 2;
        updateTree(curr * 2, target, value, left, mid);
        updateTree(curr * 2 + 1, target, value, mid + 1, right);
        tree[(int)curr] = tree[(int)curr * 2 + 1] + tree[(int)curr * 2];
    }

    private static long findTree(long curr, long fl, long fr, long left, long right) {
        if(fl <= left && fr >= right) {
            return tree[(int)curr];
        }

        if(fl > right || fr < left) {
            return 0;
        }

        long mid = (left + right) / 2;
        long leftTree = findTree(curr * 2, fl, fr, left, mid);
        long rightTree = findTree(curr * 2 + 1, fl, fr, mid + 1, right);
        return leftTree + rightTree;
    }
}
