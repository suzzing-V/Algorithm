import java.util.*;
import java.io.*;

// 시간복잡도: log(4*10^6) * 10^5
public class Main {

    private static long[] nodes;
    private static int h;
    private static int n;
    private static int q;
    private static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        nodes = new long[4 * n + 1];

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());
            int com = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(com == 1) {
                modify(1, 1, n, a, b);
            } else if(com == 2) {
                sb.append(search(1, 1, n, a, b)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void modify(int curr, int cl, int cr, int target, int value) {
        if(cl == cr) {
            nodes[curr] += value;
            return;
        }

        int mid = (cl + cr) / 2;
        if(target <= mid) modify(curr * 2, cl, mid, target, value);
        else modify(curr * 2 + 1, mid + 1, cr, target, value);

        nodes[curr] = nodes[curr * 2] + nodes[curr * 2 + 1];
    }

    private static long search(int curr, int cl, int cr, int tl, int tr) {
        if(cr < tl || tr < cl) return 0;
        if(tl <= cl && cr <= tr) {
            return nodes[curr];
        }

        int mid = (cl + cr) / 2;
        long left = search(curr * 2, cl, mid, tl, tr);
        long right = search(curr * 2 + 1, mid + 1, cr, tl, tr);

        return left + right;
    }
}
