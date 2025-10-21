import java.util.*;
import java.io.*;

// 시간복잡도: 4 * 10^5 + (log4 + 5log10) * 10^5
public class Main {

    private static int n;
    private static int m;
    private static int[] arr;
    private static long[] odd;
    private static long[] even;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i ++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(bf.readLine());
        odd = new long[n * 4];
        even = new long[n * 4];
        StringBuilder sb = new StringBuilder();
        makeTree(1, 0, n - 1);
        for(int t = 0; t < m; t++) {
            st = new StringTokenizer(bf.readLine());
            int com = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(com == 1) {
                modify(1, 0, n - 1, a - 1, b);
//                System.out.println(Arrays.toString(even));
//                System.out.println(Arrays.toString(odd));
            } else if(com == 2) {
                long cnt = findEven(1, 0, n - 1, a - 1, b - 1);
                sb.append(cnt).append("\n");
            } else if(com == 3) {
                long cnt = findOdd(1, 0, n - 1, a - 1, b - 1);
                sb.append(cnt).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void makeTree(int curr, int cl, int cr) {
        if(cl == cr) {
            if(arr[cl] % 2 == 0) even[curr] ++;
            if(arr[cl] % 2 != 0) odd[curr] ++;
            return;
        }

        int mid = (cl + cr) / 2;
        makeTree(curr * 2, cl, mid);
        makeTree(curr * 2 + 1, mid + 1, cr);
        even[curr] = even[curr * 2] + even[curr * 2 + 1];
        odd[curr] = odd[curr * 2] + odd[curr * 2 + 1];
    }

    private static void modify(int curr, int cl, int cr, int target, int value) {
        if(cl == cr) {
            arr[cl] = value;
            if(value % 2 == 0) {
                even[curr] = 1;
                odd[curr] = 0;
            }
            if(value % 2 != 0) {
                odd[curr] = 1;
                even[curr] = 0;
            }

            return;
        }

        int mid = (cl + cr) / 2;
        if(target <= mid) modify(curr * 2, cl, mid, target, value);
        if(target > mid) modify(curr * 2 + 1, mid + 1, cr, target, value);
        odd[curr] = odd[curr * 2] + odd[curr * 2 + 1];
        even[curr] = even[curr * 2] + even[curr * 2 + 1];
    }

    private static long findEven(int curr, int cl, int cr, int tl, int tr) {
        if(tr < cl || cr < tl) {
            return 0;
        } else if(tl <= cl && cr <= tr) {
            return even[curr];
        }

        int mid = (cl + cr) / 2;
        long left = findEven(curr * 2, cl, mid, tl, tr);
        long right = findEven(curr * 2 + 1, mid + 1, cr, tl, tr);
        return left + right;
    }

    private static long findOdd(int curr, int cl, int cr, int tl, int tr) {
        if(tr < cl || cr < tl) {
            return 0;
        } else if(tl <= cl && cr <= tr) {
            return odd[curr];
        }

        int mid = (cl + cr) / 2;
        long left = findOdd(curr * 2, cl, mid, tl, tr);
        long right = findOdd(curr * 2 + 1, mid + 1, cr, tl, tr);
        return left + right;
    }
}
