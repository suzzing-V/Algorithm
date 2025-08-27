import java.io.*;
import java.util.*;

// 트리의 높이 : ceil(log2n)
// 노드의 개수: 2^ (트리의 높이 + 1)
// 세그먼트 트리 : 특정 범위의 연산값을 빠르게 찾을 수 있다.
// 부모노드의 왼쪽 자식 노드 번호: 2 * 부모노드, 부모노드의 오른쪽 자식 노드 번호: 2 * 부모노드 + 1
public class Main {

    private static int n;
    private static int[] arr;
    private static int[] seg;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        seg = new int[(int)Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)) + 1)];
        Arrays.fill(seg, Integer.MAX_VALUE);
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        // 세그먼트 트리 만들기(2^(log2(n) + 1))
        makeTree(1, 1, n);

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            System.out.println(findMin(1, 1, n, start, end));
        }
    }

    // ceil(log2(n))
    private static int findMin(int curr, int cl, int cr, int start, int end) {
        // 현재 탐색하는 범위가 내가 찾고자하는 범위에 속해있으면 그 범위에서의 값 리턴
        if(start <= cl && cr <= end) return seg[curr];
        // 완전히 벗어나 있으면 배제
        if(start > cr || end < cl) return Integer.MAX_VALUE;

        // 걸쳐있으면 더 깊게 탐색
        int mid = (cl + cr) / 2;
        int left = findMin(curr * 2, cl, mid, start, end);
        int right = findMin(curr * 2 + 1, mid + 1, cr, start, end);

        return Math.min(left, right);
    }

    private static int makeTree(int curr, int start, int end) {
        if(start == end) {
             return seg[curr] = arr[start];
        }

        int mid = (start + end) / 2;
        int left = makeTree(curr * 2, start, mid);
        int right = makeTree(curr * 2 + 1, mid + 1, end);

        return seg[curr] = Math.min(left, right);
    }
}
