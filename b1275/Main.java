import java.util.*;
import java.io.*;

// 세그먼트 트리: 특정 범위의 연산값 구하기
// 트리 높이: ceil(log2(n))
// 노드 개수: 2^(트리높이 + 1)
// 시간 복잡도: 트리만들기(2^21) + 10^5 * (값 찾기(20) + 값 갱신(20))
// 공간 복잡도: 2^21 * 2^3
public class Main {

    private static long[] seg;
    private static int n;
    private static int q;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        int tree_height = (int)Math.ceil(Math.log(n) / Math.log(2));
        arr = new int[n];
        seg = new long[(int)Math.pow(2, tree_height + 1) + 1];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makeTree(1, 0, n - 1);
//        for(int i = 0; i < seg.length; i++) {
//            System.out.print(seg[i] + " ");
//        }
//        System.out.println();

        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(findSum(1, Math.min(x - 1, y - 1), Math.max(x - 1, y - 1), 0, n - 1));

            changeValue(1, 0, n - 1, a - 1, b);
//            for(int j = 0; j < seg.length; j++) {
//                System.out.print(seg[j] + " ");
//            }
//            System.out.println();
        }
    }

    private static long makeTree(int curr, int start, int end) {
        if(start == end) {
            return seg[curr] = arr[start];
        }

        int mid = (start + end) / 2;
        long left = makeTree(curr * 2, start, mid);
        long right = makeTree(curr * 2 + 1, mid + 1, end);

        return seg[curr] = left + right;
    }

    private static long findSum(int curr, int ts, int te, int s, int e) {
        // 찾고자 하는 범위 안에 현재 범위가 완전히 속해있으면 그 값 리턴
        if(ts <= s && e <= te) return seg[curr];
        // 찾고자 하는 범위에 현재 범위 아예 속해있지 않으면 0리턴. 더할 게 없다.
        if(e < ts || s > te) return 0;

        // 두 범위가 서로 걸쳐있으면 더 작은 범위로 탐색
        int mid = (s + e) / 2;
        long left = findSum(curr * 2, ts, te, s, mid);
        long right = findSum(curr * 2 + 1, ts, te, mid + 1, e);

        return left + right;
    }

    private static long changeValue(int curr, int s, int e, int target, long value) {
        // target번째 리프노드 도착하면 값 갱신
        if(s == e && target == s) {
            return seg[curr] = value;
        }
        // target이 속해있지 않으면 그냥 그 범위 값 리턴
        if(target < s || target > e) {
            return seg[curr];
        }

        // 속해있으면 더 탐색
        int mid = (s + e) / 2;
        long left = changeValue(curr * 2, s, mid, target, value);
        long right = changeValue(curr * 2 + 1, mid + 1, e, target, value);

        // 갱신된 값 반영
        return seg[curr] = left + right;
    }
}
