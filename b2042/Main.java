import java.io.*;
import java.util.*;

public class Main {

    static long[] arr;
    static int n;
    static int m;
    static int k;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n];
        tree = new long[n * 4];
        for(int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }

        buildTree(0, 0, n - 1);

        int cmdNum = m + k;
        for(int i = 0; i < cmdNum; i++) {
            st = new StringTokenizer(bf.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            if(cmd == 1) {
                updateTree(0, target - 1, value, 0, n - 1);
            } else {
                bw.write(findTree(0, target - 1, value - 1, 0, n - 1) + "\n");
            }
        }

        bw.close();
    }

    private static long buildTree(int curr, int left, int right) {
        if(left == right) {
            tree[curr] = arr[left];
            return tree[curr];
        }

        int mid = (left + right) / 2;
        long leftTree = buildTree(curr * 2, left, mid);
        long rightTree = buildTree(curr * 2 + 1, mid + 1, right);
        return tree[curr] = leftTree + rightTree;
    }

    private static void updateTree(int curr, int target, int value, int left, int right) {
        if(left == right && left == target) {
            tree[curr] = value;
            return;
        }

        if(target < left || target > right) {
            return;
        }

        int mid = (left + right) / 2;
        updateTree(curr * 2, target, value, left, mid);
        updateTree(curr * 2 + 1, target, value, mid + 1, right);
    }

    private static long findTree(int curr, int fl, int fr, int left, int right) {
        if(fl <= left && fr >= right) {
            return tree[curr];
        }

        if(fl > right || fr < left) {
            return 0;
        }

        int mid = (left + right) / 2;
        long leftTree = findTree(curr * 2, fl, fr, left, mid);
        long rightTree = findTree(curr * 2 + 1, fl, fr, mid + 1, right);
        return leftTree + rightTree;
    }
}
