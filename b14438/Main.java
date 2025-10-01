import java.util.*;
import java.io.*;

// 시간복잡도: 10^5 * 29
public class Main {

    private static int[] nodes;
    private static int n;
    private static int[] arr;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(bf.readLine());
        int h = (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
        nodes = new int[(int)Math.pow(2, h)];

        makeTree(1, 0, n - 1);

//        System.out.println(Arrays.toString(nodes));

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i ++) {
            st = new StringTokenizer(bf.readLine());
            int func = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(func == 1) {
                modify(1, 0, n - 1, a - 1, b);
            } else if(func == 2) {
                sb.append(search(1, 0, n - 1, a - 1, b - 1)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static int makeTree(int curr, int left, int right) {
        if(left == right) {
            nodes[curr] = arr[left];
            return nodes[curr];
        }

        int mid = (left + right) / 2;
        int lm = makeTree(curr * 2, left, mid);
        int rm = makeTree(curr * 2 + 1, mid + 1, right);

        return nodes[curr] = Math.min(lm, rm);
    }

    private static int modify(int curr, int left, int right, int target, int value) {
        if(left == right) {
            nodes[curr] = value;
            return value;
        }

        int mid = (left + right) / 2;
        int lm = nodes[curr * 2];
        int rm = nodes[curr * 2 + 1];

        if(target <= mid) {
            lm = modify(curr * 2, left, mid, target, value);
        } else {
            rm = modify(curr * 2 + 1, mid + 1, right, target, value);
        }

        return nodes[curr] = Math.min(lm, rm);
    }

    private static int search(int curr, int cl, int cr, int tl, int tr) {
        // 찾고자 하는 범위와 전혀 겹치지 않으면 절대 선택되지 않도록 큰 값 리턴
        if(cr < tl || tr < cl) {
            return Integer.MAX_VALUE;
        } else if(tl <= cl && cr <= tr) {
            return nodes[curr];
        }

        int mid = (cl + cr) / 2;
        int lm = search(curr * 2, cl, mid, tl, tr);
        int rm = search(curr * 2 + 1, mid + 1, cr, tl, tr);

        return Math.min(lm, rm);
    }
}
