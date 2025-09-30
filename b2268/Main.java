import java.io.*;
import java.util.*;

// 시간복잡도: log(10^6) * 10^6
public class Main {

    private static int n;
    private static int m;
    private static long[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int h = (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
        nodes = new long[(int)Math.pow(2, h)];

//        System.out.println(h);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int func = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(func == 0) {
                sb.append(sum(1, 1, n, Math.min(a, b), Math.max(a, b))).append("\n");
            } else {
                modify(1, 1, n, a, b);
            }
        }

        System.out.println(sb);
    }

    private static long sum(int curr, int cl, int cr, int tl, int tr) {
        if(cr < tl || cl > tr) {
            return 0;
        } else if(cl >= tl && cr <= tr) {
            return nodes[curr];
        }
            int mid = (cl + cr) / 2;
            long left = sum(curr * 2, cl, mid, tl, tr);
            long right = sum(curr * 2 + 1, mid + 1, cr, tl, tr);
            return left + right;
    }

    private static void modify(int curr, int cl, int cr, int target, int value) {
//        System.out.println(curr + " " + cl + " " + cr);
        if(cl == cr) {
            nodes[curr] = value;
            return;
        }

        int mid = (cl + cr) / 2;
        if(target <= mid) {
            modify(curr * 2, cl, mid, target, value);
        } else {
            modify(curr * 2 + 1, mid + 1, cr, target, value);
        }
        nodes[curr] = nodes[curr * 2] + nodes[curr * 2 + 1];
//        System.out.println((curr * 2) + " " + (curr * 2 + 1) + " " + nodes[curr]);
    }
}
